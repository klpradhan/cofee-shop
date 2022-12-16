package com.itpothitech.coffee.orderservice.resources;

import com.itpothitech.coffee.orderservice.dto.OrderQDTO;
import com.itpothitech.coffee.orderservice.models.Order;
import com.itpothitech.coffee.orderservice.services.OrderQueueInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Set;

/**
 * Order Queue Service - the orchestrator
 * Features:
 * A) Add to the queue
 * B) Remove from the queue
 * C) Display list of orders in the queue for a shop
 *
 */
@RestController
@RequestMapping(path="/coffee/api/v1")
public class OrderQueueResources {

    @Autowired
    OrderQueueInfo orderQueueInfo;

    @GetMapping(path = "/orders/queues/{shopId}")
    public ResponseEntity<Set<Order>> getOrderQueue(@PathVariable("shopId") String shopId) {
        Set<Order> foundOrder = orderQueueInfo.getOrderFromQueue(Long.valueOf(shopId));
        if(foundOrder == null || foundOrder.size() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundOrder);
        }
    }

    @PostMapping(path = "/orders/queues")
    public ResponseEntity<Set<Order>> addOrderToQueue(@RequestBody OrderQDTO orderQ)
            throws URISyntaxException {
        Set<Order> createdOrder = orderQueueInfo.addOrderToQueue(orderQ.getShopId(), orderQ.getOrderId());
        if(createdOrder.size() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdOrder.size())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdOrder);
        }
    }

    @DeleteMapping(path = "/orders/queues")
    public ResponseEntity<Object> deleteOrderInfo(@RequestBody OrderQDTO orderQ) {
        orderQueueInfo.removeOrderFromQueue(orderQ.getShopId(), orderQ.getOrderId());
        return ResponseEntity.noContent().build();
    }
}
