package com.itpothitech.coffee.orderservice.services;

import com.itpothitech.coffee.orderservice.models.Order;
import com.itpothitech.coffee.orderservice.repository.OrderRepository;
import com.itpothitech.coffee.orderservice.resources.OrderResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OrderInfo {

    @Autowired
    OrderRepository orderRepository;

    public Order getOrderDetails(Long orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }


    public Order addOrderDetails(Order order) {
        orderRepository.save(order);
        return orderRepository.findById(order.getId()).orElse(null);
    }

    public Order updateOrderDetails(Order order) {
        orderRepository.save(order);
        return orderRepository.findById(order.getId()).orElse(null);
    }


    public void deleteOrder(Long orderId) {
        orderRepository.deleteById(orderId);
    }
}
