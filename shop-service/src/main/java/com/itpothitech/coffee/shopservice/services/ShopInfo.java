package com.itpothitech.coffee.shopservice.services;

import com.google.gson.*;
import com.itpothitech.coffee.shopservice.dto.ItemDTO;
import com.itpothitech.coffee.shopservice.dto.SearchShopDTO;
import com.itpothitech.coffee.shopservice.dto.ShopDTO;
import com.itpothitech.coffee.shopservice.models.Menu;
import com.itpothitech.coffee.shopservice.models.Shop;
import com.itpothitech.coffee.shopservice.repository.MenuRepository;
import com.itpothitech.coffee.shopservice.repository.ShopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Shop App Service
 * Features:
 * A) Register a new Shop and its menu details
 * B) Manage Shop : modify queue and max size of the queue
 *
 */
@Service
@Transactional
public class ShopInfo {

    @Autowired
    ShopRepository shopRepository;

    @Autowired
    MenuRepository menuRepository;

    public Shop getDetails(Long shopId) {
        return shopRepository.findById(shopId)
                .orElse(null);
    }

    public Shop addDetails(ShopDTO shopObj) {
        List<ItemDTO> items = shopObj.getMenu().getItems();
        Gson itemsJsonStr = new GsonBuilder().create();
        String itemStr = itemsJsonStr.toJson(items);

        Menu newMenu = new Menu(itemStr, shopObj.getMenu().getCreateDate(),
                                    shopObj.getMenu().getModifiedDate());
        menuRepository.save(newMenu);
        Long newMenuId = newMenu.getMenuId();

        Shop newShop = new Shop(shopObj.getAddress(),shopObj.getStreet(), shopObj.getCity(), shopObj.getState(), shopObj.getCountry(), newMenuId,
                shopObj.getQueueNum(), shopObj.getMaxQueueSize(), shopObj.getOpeningTime(), shopObj.getClosingTime(), shopObj.getCreatedBy(),
                shopObj.getCreate_date(), shopObj.getModified_date());
        shopRepository.save(newShop);
        Long newShopId = newShop.getShopId();
        return shopRepository.findById(newShopId).orElse(null);

    }

    public Shop updateDetails(ShopDTO shopObj) {
        List<ItemDTO> items = shopObj.getMenu().getItems();
        Gson itemsJsonStr = new GsonBuilder().create();
        String itemStr = itemsJsonStr.toJson(items);

        Menu newMenu = new Menu(itemStr, shopObj.getMenu().getCreateDate(),
                shopObj.getMenu().getModifiedDate());
        menuRepository.save(newMenu);
        Long newMenuId = newMenu.getMenuId();

        Shop newShop = new Shop(shopObj.getAddress(),shopObj.getStreet(), shopObj.getCity(), shopObj.getState(), shopObj.getCountry(), newMenuId,
                shopObj.getQueueNum(), shopObj.getMaxQueueSize(), shopObj.getOpeningTime(), shopObj.getClosingTime(), shopObj.getCreatedBy(),
                shopObj.getCreate_date(), shopObj.getModified_date());
        shopRepository.save(newShop);
        Long newShopId = newShop.getShopId();
        return shopRepository.findById(newShopId).orElse(null);
    }

    public void deletedShop(Long shopId) {
        shopRepository.deleteById(shopId);
    }

    public List<Shop> searchDetails(SearchShopDTO searchShopDTO) {
        List<Shop> shopList = shopRepository.searchByStreet(searchShopDTO.getStreet());
        return shopList;
    }
}