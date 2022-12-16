package com.itpothitech.coffee.customerservice.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import java.util.Date;

@Getter
@Setter
public class MenuDTO {
    private Long menuId;
    private String itemName;
    private Integer price;
    private Integer prepTime;

    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date createDate = new Date();

    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date modifiedDate = new Date();
}
