package com.geekster.RestaurantManagementServiceApi.repository;

import com.geekster.RestaurantManagementServiceApi.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    Order findFirstById(Long orderId);
}
