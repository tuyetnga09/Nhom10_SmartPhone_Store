package com.example.smartphone_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "Code")
    @NotBlank(message = "Mã không được để trống!")
    private String code;

    @Column(name = "Name")
    @NotBlank(message = "Tên không được để trống!")
    private String name;

    @Column(name = "ImportPrice")
    @NotNull(message = "Chưa nhập giá!")
    private Double importPrice;

    @Column(name = "Price")
    @NotNull(message = "Chưa nhập giá!")
    private Double price;

    @Column(name = "Quantity")
    @NotNull(message = "Chưa nhập số lượng!")
    private int quantity;

    @Column(name = "DateCreate")
    @Temporal(TemporalType.DATE)
    private Date dateCreate;

    @Column(name = "DateUpdate")
    @Temporal(TemporalType.DATE)
    private Date dateUpdate;

    @Column(name = "PersonCreate")
    @NotNull(message = "Chưa nhập tên người tạo!")
    private String personCreate;

    @Column(name = "PersonUpdate")
    private String personUpdate;

    @Column(name = "Status")
    private int status;

    @OneToMany(mappedBy = "idProduct")
    private List<Images> idImages;

//    @OneToMany(mappedBy = "idProduct")
//    private List<ProductDetail> productDetailList;

    public Product() {
        this.dateCreate = new Date(new java.util.Date().getTime());
        this.status = 0;
    }

}
