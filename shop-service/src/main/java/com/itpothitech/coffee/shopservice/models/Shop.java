package com.itpothitech.coffee.shopservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@Table(name = "shop")
public class Shop {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "shop_id", nullable = false)
    private Long shopId;
    private String address;
    private String street;
    private String city;
    private String state;
    private String country;

    @Column(name="menu_id", nullable = false)
    private Long menuId;

    @Column(name="queue_num")
    private Integer queueNum;

    @Column(name="max_queue_size")
    private Integer maxQueueSize;

    @Column(name="opening_time")
    private String openingTime;

    @Column(name="closing_time")
    private String closingTime;

    @Column(name = "created_by")
    private Integer createdBy;

    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date createDate = new Date();

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date modifiedDate = new Date();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("shop")
    private Menu menu;

    public Shop(String address, String street, String city, String state, String country, Long menuId,
                Integer queueNum, Integer maxQueueSize, String openingTime, String closingTime, Integer createdBy, Date createDate, Date modifiedDate) {
        this.address = address;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.menuId = menuId;
        this.queueNum = queueNum;
        this.maxQueueSize = maxQueueSize;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.createdBy = createdBy;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }
}
