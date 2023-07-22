package com.example.smartphone_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Table(name = "CartDetailShooping")
@AllArgsConstructor
@Setter
@Getter
public class CartDetails {

    @Id
    @Column(name = "Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Quantity")
    private int quantity;

    @Column(name = "DateCreate")
    private Date dateCreate;

    @Column(name = "DateUpdate")
    private Date dateUpdate;

    @Column(name = "PersonCreate")
    private String personCreate;

    @Column(name = "PersonUpdate")
    private String personUpdate;

    @Column(name = "Price")
    private Long price;

    @Column(name = "TotalMoney")
    private Long totalMoney;

    @Column(name = "Status")
    private int status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Cart")
    private Cart id_cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_ProductDetail")
    private ProductDetail id_productDetail;

    public CartDetails() {
        this.id_cart = new Cart(1L);
        this.dateUpdate = new Date(new java.util.Date().getTime());
        this.status = 0;
    }

}
