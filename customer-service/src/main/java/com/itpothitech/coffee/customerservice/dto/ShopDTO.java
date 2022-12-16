package com.itpothitech.coffee.customerservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
public class ShopDTO {
    private Long shopId;
    private String address;
    private String street;
    private String city;
    private String state;
    private String country;
    private MenuDTO menu;
    private Integer queueNum;
    private Integer maxQueueSize;
    private String openingTime;
    private String closingTime;
    private Integer created_by;

    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date create_date = new Date();

    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date modified_date = new Date();

}
