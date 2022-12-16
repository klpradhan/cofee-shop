package com.itpothitech.coffee.shopservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

@Getter
@Setter
public class MenuDTO {

    private List<ItemDTO> items = new ArrayList<>();

    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date createDate = new Date();

    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date modifiedDate = new Date();

}
