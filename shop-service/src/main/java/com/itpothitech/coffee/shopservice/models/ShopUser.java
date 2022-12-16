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
@Table(name = "shop_user")
public class ShopUser {
    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", nullable = false)
    private Long shopUserId;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "contact_num")
    private String contactNum;

    @Column(name = "contact_email")
    private String contactEmail;

    @Getter(AccessLevel.NONE)
    private String credentials;

    private String role;

    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date createDate = new Date();

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date modifiedDate = new Date();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "shop_id", referencedColumnName = "shop_id", insertable = false, updatable = false)
    @JsonIgnoreProperties("shop_user")
    private Shop shop;
}
