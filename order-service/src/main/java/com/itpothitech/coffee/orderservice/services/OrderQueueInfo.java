package com.itpothitech.coffee.orderservice.services;

import com.itpothitech.coffee.orderservice.models.Order;
import com.itpothitech.coffee.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderQueueInfo {

    @Autowired
    OrderRepository orderRepository;

    private final Map<Long, Map<Long, Order>> inMemoryQeue = new HashMap<>();


    public Set<Order> getOrderFromQueue(Long shopId) {
        if(inMemoryQeue.size() == 0) return null;
        if(inMemoryQeue.containsKey(shopId)) {
            return new HashSet<>(inMemoryQeue.get(shopId).values());
        } else {
            return null;
        }
    }

    public Set<Order> addOrderToQueue(Long shopId, Long orderId) {
        Order orderObj = orderRepository.findById(orderId).orElse(null);
        if(inMemoryQeue.containsKey(shopId)) {
            //adding to existing queue
            Map<Long, Order> existingQHolder = inMemoryQeue.get(shopId);
            if(orderObj != null) {
                existingQHolder.put(orderId, orderObj);
                inMemoryQeue.put(shopId, existingQHolder);
            }
        } else { //Creating a new queue
            Map<Long, Order> qHolder = new HashMap<>();
            if(orderObj != null) {
                qHolder.put(orderId, orderObj);
                inMemoryQeue.put(shopId, qHolder);
            }
        }
        return new HashSet<>(inMemoryQeue.get(shopId).values());
    }

    public Set<Order> removeOrderFromQueue(Long shopId, Long orderId) {
        Map<Long, Order> qHolder = inMemoryQeue.get(shopId);
        qHolder.remove(orderId);
        inMemoryQeue.put(shopId, qHolder);
        return new HashSet<>(inMemoryQeue.get(shopId).values());
    }

}
