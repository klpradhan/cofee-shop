package com.itpothitech.coffee.shopservice.repository;

import com.itpothitech.coffee.shopservice.models.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuRepository extends JpaRepository<Menu, Long> {
}
