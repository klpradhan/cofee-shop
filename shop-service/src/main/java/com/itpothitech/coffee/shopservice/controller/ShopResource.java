package com.itpothitech.coffee.shopservice.controller;

import com.itpothitech.coffee.shopservice.dto.SearchShopDTO;
import com.itpothitech.coffee.shopservice.dto.ShopDTO;
import com.itpothitech.coffee.shopservice.models.Shop;
import com.itpothitech.coffee.shopservice.services.ShopInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path="/coffee/api/v1")
public class ShopResource {

    @Autowired
    ShopInfo shopInfo;

    @GetMapping(path = "/shops/{shopId}")
    public ResponseEntity<Shop> getShopInfo(@PathVariable("shopId") String shopId) {
        Shop foundShop = shopInfo.getDetails(Long.valueOf(shopId));
        if (foundShop == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundShop);
        }
    }

    @GetMapping(path = "/shops/search")
    public ResponseEntity<List<Shop>> searchShopInfo(@RequestBody SearchShopDTO searchShopDTO) {
        List<Shop> foundShops = shopInfo.searchDetails(searchShopDTO);
        if(foundShops.size() == 0) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundShops);
        }
    }

    @PostMapping(path = "/shops")
    public ResponseEntity<Shop> addShopInfo(@RequestBody ShopDTO shopObj)
        throws URISyntaxException {
        Shop createdShop = shopInfo.addDetails(shopObj);
        if(createdShop == null) {
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdShop.getShopId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(createdShop);
        }
    }

    @PutMapping("/shops/{shopId}")
    public ResponseEntity<Shop> updateShopInfo(@RequestBody ShopDTO shopObj, @PathVariable Long shopId) {
        Shop updatedShop = shopInfo.updateDetails(shopObj);
        if (updatedShop == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedShop);
        }
    }

    @DeleteMapping("/shops/{shopId}")
    public ResponseEntity<Object> deleteShopInfo(@PathVariable Long shopId) {
        shopInfo.deletedShop(shopId);
        return ResponseEntity.noContent().build();
    }

}
