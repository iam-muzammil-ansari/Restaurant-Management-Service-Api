package com.geekster.RestaurantManagementServiceApi.repository;

import com.geekster.RestaurantManagementServiceApi.model.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepo extends JpaRepository<FoodItem,Long> {
}
