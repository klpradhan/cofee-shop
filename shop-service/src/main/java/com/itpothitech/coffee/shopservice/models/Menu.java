package com.itpothitech.coffee.shopservice.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.itpothitech.coffee.shopservice.dto.ItemDTO;
import com.itpothitech.coffee.shopservice.dto.MenuDTO;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "menu")
public class Menu {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "menu_id", nullable = false)
    private Long menuId;

    @Lob
    @Column(nullable = false)
    private String items;

    @Column(name = "create_date")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date createDate = new Date();

    @Column(name = "modified_date")
    @DateTimeFormat(pattern = "yyyy-MM-DD hh:mm:ss")
    private Date modifiedDate = new Date();

    @OneToOne(mappedBy = "menu")
    @JsonIgnoreProperties("menu")
    private Shop shop;

    public Menu(String items, Date createDate, Date modifiedDate) {
        this.items = items;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }
}
