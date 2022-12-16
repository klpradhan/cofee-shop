package com.itpothitech.coffee.customerservice.controller;

import com.itpothitech.coffee.customerservice.models.Customer;
import com.itpothitech.coffee.customerservice.services.CustomerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Customer App Service
 * Features:
 * A) Register as a new customer
 * B) Manage customer account
 * C) Find a shop
 *
 */

@RestController
@RequestMapping(path="/coffee/api/v1")
public class CustomerResource {

    @Autowired
    CustomerInfo customerInfo;

    @GetMapping(path = "/customers/{custId}")
    public ResponseEntity<Customer> getCustomerInfo(@PathVariable("custId") String custId) {
        Customer foundCustomer = customerInfo.getCustomerDetails(Long.valueOf(custId));
        if(foundCustomer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundCustomer);
        }
    }

    @PostMapping(path = "/customers")
    public ResponseEntity<Customer> addCustomerInfo(@RequestBody Customer customer)
            throws URISyntaxException {
        Customer createdCustomer = customerInfo.addCustomerDetails(customer);
        if(createdCustomer == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdCustomer.getCustomerId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdCustomer);
        }
    }

    @PutMapping(path = "/customers/{custId}")
    public ResponseEntity<Customer> updateCustomerInfo(@RequestBody Customer customer, @PathVariable("custId") String custId) {
        Customer updatedCustomer = customerInfo.updateCustomerDetails(customer);
        if(updatedCustomer == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedCustomer);
        }
    }

    @DeleteMapping(path = "/customers/{custId}")
    public ResponseEntity<Object> deleteCustomerInfo(@PathVariable("custId") String custId) {
        customerInfo.deleteCustomerDetails(Long.valueOf(custId));
        return ResponseEntity.noContent().build();
    }
}
