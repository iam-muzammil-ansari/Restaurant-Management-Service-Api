package com.geekster.RestaurantManagementServiceApi.repository;

import com.geekster.RestaurantManagementServiceApi.model.AuthenticationToken;
import com.geekster.RestaurantManagementServiceApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByTokenValue(String authTokenValue);

    AuthenticationToken findFirstByUser(User user);
}
