package com.geekster.RestaurantManagementServiceApi.service;

import com.geekster.RestaurantManagementServiceApi.model.AuthenticationToken;
import com.geekster.RestaurantManagementServiceApi.model.FoodItem;
import com.geekster.RestaurantManagementServiceApi.model.User;
import com.geekster.RestaurantManagementServiceApi.model.enums.UserRole;
import com.geekster.RestaurantManagementServiceApi.repository.AuthRepo;
import com.geekster.RestaurantManagementServiceApi.repository.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemService {
    @Autowired
    private FoodItemRepo foodItemRepo;

    @Autowired
    private AuthRepo authRepo;

    public FoodItem createFoodItem(FoodItem foodItem) {
        // Additional validation or business logic can be added here

        return foodItemRepo.save(foodItem);
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepo.findAll();
    }

    public boolean authenticateAdmin(String authToken) {
        AuthenticationToken authenticationToken = authRepo.findFirstByTokenValue(authToken);
        if (authenticationToken != null) {
            User user = authenticationToken.getUser();
            return user.getRole() == UserRole.ADMIN;
        }
        return false;
    }
}