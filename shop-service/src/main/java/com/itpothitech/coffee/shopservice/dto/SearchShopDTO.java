package com.itpothitech.coffee.shopservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchShopDTO {
    private String address;
    private String street;
    private String city;
    private String state;
    private String country;

}
