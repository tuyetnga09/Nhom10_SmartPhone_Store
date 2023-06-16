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
//import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Size")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Size {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @NotBlank(message = "* Dữ liệu không để rỗng!")
    @jakarta.validation.constraints.Size(max = 20, message = "* Không vượt quá 20 ký tự!")
    @Column(name = "Code")
    private String code;

    @NotBlank(message = "* Dữ liệu không để rỗng!")
    @jakarta.validation.constraints.Size(max = 200, message = "* Không vượt quá 200 ký tự!")
    @Column(name = "Name")
    private String name;

    @Column(name = "DateCreate")
    @Temporal(TemporalType.DATE)
    private LocalDate dateCreate;

    @Column(name = "DateUpdate")
    @Temporal(TemporalType.DATE)
    private LocalDate dateUpdate;

    @Column(name = "PersonCreate")
    private String personCreate;

    @Column(name = "PersonUpdate")
    private String personUpdate;

    @Column(name = "Status")
    private Integer status;

}
