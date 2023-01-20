package com.ifood.repository;

import com.ifood.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Page<Order> findOrdersByRestaurantId(Pageable pageable,Long restaurantId);
}
