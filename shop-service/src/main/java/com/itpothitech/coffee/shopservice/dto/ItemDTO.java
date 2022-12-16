package com.itpothitech.coffee.shopservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDTO {
    private String itemName;
    private Integer price;
    private Integer prepTime;
}
