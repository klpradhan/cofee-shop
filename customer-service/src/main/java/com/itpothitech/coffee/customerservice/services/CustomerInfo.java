package com.itpothitech.coffee.customerservice.services;

import com.itpothitech.coffee.customerservice.models.Customer;
import com.itpothitech.coffee.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CustomerInfo {

    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomerDetails(Long custId) {
        return customerRepository.findById(custId).orElse(null);
    }

    public Customer addCustomerDetails(Customer customer) {
        customerRepository.save(customer);
        return customerRepository
                .findById(customer.getCustomerId())
                .orElse(null);
    }

    public Customer updateCustomerDetails(Customer customer) {
        customerRepository.save(customer);
        return customerRepository
                .findById(customer.getCustomerId())
                .orElse(null);
    }

    public void deleteCustomerDetails(Long custId) {
        customerRepository.deleteById(custId);
    }
}
