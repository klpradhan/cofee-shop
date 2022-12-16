package com.itpothitech.coffee.customerservice.repository;

import com.itpothitech.coffee.customerservice.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
