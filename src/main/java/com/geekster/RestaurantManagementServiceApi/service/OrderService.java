package com.geekster.RestaurantManagementServiceApi.service;

import com.geekster.RestaurantManagementServiceApi.model.AuthenticationToken;
import com.geekster.RestaurantManagementServiceApi.model.Order;
import com.geekster.RestaurantManagementServiceApi.model.User;
import com.geekster.RestaurantManagementServiceApi.model.enums.OrderStatus;
import com.geekster.RestaurantManagementServiceApi.model.enums.UserRole;
import com.geekster.RestaurantManagementServiceApi.repository.AuthRepo;
import com.geekster.RestaurantManagementServiceApi.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    AuthRepo authRepo;


    public void createOrder(Order order) {
        // Additional validation or business logic can be added here

        orderRepo.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    public boolean authenticateUser(String authToken) {
        AuthenticationToken authenticationToken = authRepo.findFirstByTokenValue(authToken);
        if (authenticationToken != null) {
            User user = authenticationToken.getUser();
            return user.getRole() == UserRole.NORMAL_USER;
        }
        return false;
    }

    public Order getOrderById(Long orderId) {
        return orderRepo.findById(orderId).orElse(null);
    }

    public boolean authenticateAdmin(String authToken) {
        AuthenticationToken authenticationToken = authRepo.findFirstByTokenValue(authToken);
        if (authenticationToken != null) {
            User user = authenticationToken.getUser();
            return user.getRole() == UserRole.ADMIN;
        }
        return false;
    }


    public void updateOrderStatus(Long orderId,OrderStatus newStatus) {
        Order order = orderRepo.findFirstById(orderId);
        if (order != null) {
            order.setStatus(newStatus);
            orderRepo.save(order);
        }
    }

    public void CancelOrder(Long orderId) {
        Order order = orderRepo.findFirstById(orderId);
        if (order != null) {
            orderRepo.delete(order);
        }
    }
}
