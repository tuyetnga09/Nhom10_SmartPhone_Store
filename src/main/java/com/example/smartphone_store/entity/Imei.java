package com.example.smartphone_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name = "Imei")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Imei {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Code")
    private String code;
    @Column(name = "DateAddded")
    private LocalDate dateAdd;
    @Column(name = "SaleDate")
    private LocalDate saleDate;
    @Column(name = "PersonSale")
    private String personSale;
    @Column(name = "PersonUpdate")
    private String personUpdate;
    @Column(name = "Status")
    private Integer status;
    @ManyToOne
    @JoinColumn(name = "Id_ProductDetail")
    private ProductDetail productDetail;

}
