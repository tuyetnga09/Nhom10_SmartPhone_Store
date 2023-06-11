package com.example.smartphone_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Id")
    private Long id;

    @Column(name = "LinkImage")
    private String linkImage;

    @Column(name = "NameImage")
    private String nameImage;

    @Column(name = "Describe")
    private String describe;

    @Column(name = "DateCreate")
    private Date dateCreate;

    @Column(name = "DateUpdate")
    private Date dateUpdate;

    @Column(name = "PersonCreate")
    private String personCreate;

    @Column(name = "PersonUpate")
    private String personUpate;

    @Column(name = "Status")
    private int status;

    public Images() {
        this.dateCreate = new Date(new java.util.Date().getTime());
        this.status = 1;
    }

}
