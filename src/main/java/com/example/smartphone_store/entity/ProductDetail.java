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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "ProductDetail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductDetail {
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

    @Column(name = "DateCreate")
//    @Temporal(TemporalType.DATE)
    private LocalDate dateCreate;

    @Column(name = "DateUpdate")
//    @Temporal(TemporalType.DATE)
    private LocalDate dateUpdate;

    @Column(name = "PersonCreate")
    @NotNull(message = "Chưa nhập tên người tạo!")
    private String personCreate;

    @Column(name = "PersonUpdate")
    private String personUpdate;

    @Column(name = "Status")
    private int status;

    @Column(name = "Describe")
    private String describe;

    @Column(name = "Price")
    @NotNull(message = "* Mời Nhập Giá Sản Phẩm!")
    private Float price;

    @Column(name = "images")
    private String images;

    @Column(name = "Quantity")
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "Id_Capacity")
    private Capacity capacity;

    @ManyToOne
    @JoinColumn(name = "Id_Color")
    private Color color;

    @ManyToOne
    @JoinColumn(name = "Id_Manufacture")
    private Manufacture manufacture;

    @ManyToOne
    @JoinColumn(name = "Id_Category")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Battery")
    @ToString.Exclude
    private Battery battery;

    @ManyToOne
    @JoinColumn(name = "Id_Chip")
    private Chip chip;

    @ManyToOne
    @JoinColumn(name = "Id_Ram")
    private Ram ram;

    @ManyToOne
    @JoinColumn(name = "Id_Screen")
    private Screen screen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Id_Product")
    @ToString.Exclude
    private Product product;

}

