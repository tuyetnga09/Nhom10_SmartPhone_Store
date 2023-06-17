package com.example.smartphone_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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

@Entity
@Table(name = "ProductDetail")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ProductDetails {
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

    @Column(name = "Describe")
    private String describe;
}
