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
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Capacity")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Capacity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Code")
    @NotBlank(message = "(*) Không để trống")
    @Size(max = 20, message = "(*) Không quá 20 ký tự")
    private String code;

    @Column(name = "Name")
    @NotBlank(message = "(*) Không để trống")
    @Size(max = 200, message = "(*) Không quá 200 ký tự")
    private String name;

    @Column(name = "DateCreate")
    @Temporal(TemporalType.DATE)
    private LocalDate dateCreate;

    @Column(name = "DateUpdate")
    @Temporal(TemporalType.DATE)
    private LocalDate dateUpdate;

    @Column(name = "PersonCreate")
    @NotBlank(message = "(*) Không để trống")
    @Size(max = 200, message = "(*) Không quá 200 ký tự")
    private String personCreate;

    @Column(name = "PersonUpdate")
    @NotBlank(message = "(*) Không để trống")
    @Size(max = 200, message = "(*) Không quá 200 ký tự")
    private String personUpdate;

    @Column(name = "Status")
    private Integer status;
}
