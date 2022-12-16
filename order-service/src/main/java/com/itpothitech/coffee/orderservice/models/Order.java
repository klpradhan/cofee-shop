package com.itpothitech.coffee.orderservice.models;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    private String item;
    private Integer price;

    @Column(name = "time_to_prep")
    private Integer timeToPrep;

    private String status;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date createDate = new Date();

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date modifiedDate = new Date();

}
