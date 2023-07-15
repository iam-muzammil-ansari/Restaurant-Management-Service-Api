package com.geekster.RestaurantManagementServiceApi.controller;

import com.geekster.RestaurantManagementServiceApi.model.FoodItem;
import com.geekster.RestaurantManagementServiceApi.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fooditem")
public class FoodItemController {
    @Autowired
    private FoodItemService foodItemService;

    @PostMapping
    public String createFoodItem(@RequestParam String authToken, @RequestBody FoodItem foodItem) {
        // Check if the provided authorization token is valid and has admin privileges
        boolean isAdmin = foodItemService.authenticateAdmin(authToken);
        if (isAdmin) {
            foodItemService.createFoodItem(foodItem);
            return "Food item created successfully.";
        } else {
            return "Only admins can create food items.";
        }
    }

    @GetMapping("/fooditems")
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }
}
