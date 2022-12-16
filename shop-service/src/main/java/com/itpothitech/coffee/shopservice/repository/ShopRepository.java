package com.itpothitech.coffee.shopservice.repository;

import com.itpothitech.coffee.shopservice.models.Shop;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShopRepository extends JpaRepository<Shop, Long> {
    List<Shop> searchByStreet(String street);
}
