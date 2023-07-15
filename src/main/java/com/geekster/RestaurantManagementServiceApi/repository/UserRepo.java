package com.geekster.RestaurantManagementServiceApi.repository;

import com.geekster.RestaurantManagementServiceApi.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    User findFirstByUserEmail(String signInEmail);


}
