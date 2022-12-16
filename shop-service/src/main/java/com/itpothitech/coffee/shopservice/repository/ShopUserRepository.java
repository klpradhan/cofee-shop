package com.itpothitech.coffee.shopservice.repository;

import com.itpothitech.coffee.shopservice.models.ShopUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopUserRepository extends JpaRepository<ShopUser, Long> {
}
