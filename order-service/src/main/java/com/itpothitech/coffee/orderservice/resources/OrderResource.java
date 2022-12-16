package com.itpothitech.coffee.orderservice.resources;

import com.itpothitech.coffee.orderservice.models.Order;
import com.itpothitech.coffee.orderservice.services.OrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path="/coffee/api/v1")
public class OrderResource {

    @Autowired
    OrderInfo orderInfo;

    @GetMapping(path = "/orders/{id}")
    public ResponseEntity<Order> getOrderInfo(@PathVariable("id") String id) {
        Order foundOrder = orderInfo.getOrderDetails(Long.valueOf(id));
        if(foundOrder == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundOrder);
        }
    }

    @PostMapping(path = "/orders")
    public ResponseEntity<Order> addOrderInfo(@RequestBody Order order)
            throws URISyntaxException {
        Order createdOrder = orderInfo.addOrderDetails(order);
        if(createdOrder == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdOrder.getCustomerId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdOrder);
        }
    }

    @PutMapping(path = "/orders/{id}")
    public ResponseEntity<Order> updateOrderInfo(@RequestBody Order order, @PathVariable("id") String id) {
        Order updatedOrder = orderInfo.updateOrderDetails(order);
        if(updatedOrder == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedOrder);
        }
    }

    @DeleteMapping(path = "/orders/{id}")
    public ResponseEntity<Object> deleteOrderInfo(@PathVariable("id") String id) {
        orderInfo.deleteOrder(Long.valueOf(id));
        return ResponseEntity.noContent().build();
    }
}
