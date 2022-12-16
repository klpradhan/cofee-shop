package com.itpothitech.coffee.shopservice.resources;

import com.itpothitech.coffee.shopservice.models.ShopUser;
import com.itpothitech.coffee.shopservice.services.ShopUserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
@RequestMapping(path="/coffee/api/v1")
public class ShopUserResource {

    @Autowired
    ShopUserInfo shopUserInfo;

    @GetMapping(path = "/users/{userId}")
    public ResponseEntity<ShopUser> getUserInfo(@PathVariable("userId") String userId) {
        ShopUser foundShopUser = shopUserInfo.getUserDetails(Long.valueOf(userId));
        if(foundShopUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundShopUser);
        }
    }

    @PostMapping(path = "/users")
    public ResponseEntity<ShopUser> addUserInfo(@RequestBody ShopUser shopUser)
            throws URISyntaxException {
        ShopUser createdShopUser = shopUserInfo.addUserDetail(shopUser);
        if(createdShopUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdShopUser.getShopUserId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdShopUser);
        }
    }

    @PutMapping(path = "/users/{userId}")
    public ResponseEntity<ShopUser> updateUserInfo(@RequestBody ShopUser shopUser, @PathVariable("userId") String userId) {
        ShopUser updatedShopUser = shopUserInfo.updateUserDetails(shopUser);
        if(updatedShopUser == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedShopUser);
        }
    }

    @DeleteMapping(path = "/users/{userId}")
    public ResponseEntity<Object> deleteShopUserInfo(@PathVariable("userId") String userId) {
        shopUserInfo.deleteUserDetails(Long.valueOf(userId));
        return ResponseEntity.noContent().build();
    }
}
