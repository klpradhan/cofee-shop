package com.itpothitech.coffee.shopservice.services;

import com.itpothitech.coffee.shopservice.models.ShopUser;
import com.itpothitech.coffee.shopservice.repository.ShopUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Shop User Service
 * Features:
 * A) Register a new Shop User with appropriate roles
 * B) Manage user account
 *
 */

@Service
@Transactional
public class ShopUserInfo {

    @Autowired
    ShopUserRepository shopUserRepository;

    public ShopUser getUserDetails(Long userId) {
        return shopUserRepository.findById(userId).orElse(null);
    }

    public ShopUser addUserDetail(ShopUser shopUser) {
        shopUserRepository.save(shopUser);
        Long newUserId = shopUser.getShopUserId();
        return shopUserRepository.findById(newUserId).orElse(null);
    }

    public ShopUser updateUserDetails(ShopUser shopUser) {
        shopUserRepository.save(shopUser);
        Long newUserId = shopUser.getShopUserId();
        return shopUserRepository.findById(newUserId).orElse(null);
    }


    public void deleteUserDetails(Long userId) {
        shopUserRepository.deleteById(userId);
    }
}
