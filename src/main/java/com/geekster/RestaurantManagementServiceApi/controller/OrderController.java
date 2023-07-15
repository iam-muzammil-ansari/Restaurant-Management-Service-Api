package com.geekster.RestaurantManagementServiceApi.controller;

import com.geekster.RestaurantManagementServiceApi.model.FoodItem;
import com.geekster.RestaurantManagementServiceApi.model.Order;
import com.geekster.RestaurantManagementServiceApi.model.User;
import com.geekster.RestaurantManagementServiceApi.model.enums.OrderStatus;
import com.geekster.RestaurantManagementServiceApi.model.enums.UserRole;
import com.geekster.RestaurantManagementServiceApi.service.AuthenticationService;
import com.geekster.RestaurantManagementServiceApi.service.FoodItemService;
import com.geekster.RestaurantManagementServiceApi.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    OrderService orderService;

    @Autowired
    AuthenticationService authenticationService;

    @PostMapping
    public String createOrder(@RequestBody Order order, @RequestParam String authToken) {
        // Check if the provided authorization token is valid and has normal user privileges
        boolean isNormalUser = orderService.authenticateUser(authToken);
        if (isNormalUser) {
            orderService.createOrder(order);
            return "Order created successfully.";
        } else {
            return "Only normal users can create orders.";
        }
    }

    @GetMapping("/Orders")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("/updateOrderStatus/{orderId}")
    public String updateOrderStatus(@PathVariable Long orderId, @RequestParam String authToken, @RequestParam OrderStatus newStatus) {
        boolean isAdmin = orderService.authenticateAdmin(authToken);
        if(isAdmin) {
            orderService.updateOrderStatus(orderId,newStatus);
            return "Order Status Updated!!!";
        }else {
            return "Only admin can Update order Status";
        }
    }

    @DeleteMapping("/cancelOrder/{orderId}")
    public String CancelOrder(@PathVariable Long orderId,@RequestParam String authToken) {
        boolean isAdmin = orderService.authenticateAdmin(authToken);
        boolean isNormalUser = orderService.authenticateUser(authToken);
        if(isAdmin) {
            orderService.CancelOrder(orderId);
            return "Order is Canceled By admin because food is out of Stock";
        } else if (isNormalUser) {
            orderService.CancelOrder(orderId);
            return "Order is Canceled";
        } else {
            return "Access Denied";
        }
    }


}

