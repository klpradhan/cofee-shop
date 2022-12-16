package com.itpothitech.coffee.orderservice.repository;

import com.itpothitech.coffee.orderservice.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
