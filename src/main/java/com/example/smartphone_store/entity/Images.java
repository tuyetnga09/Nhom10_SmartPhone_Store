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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "Images")
@Builder
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    @Temporal(TemporalType.DATE)
    private Date dateUpdate;

    @Column(name = "PersonCreate")
    @Temporal(TemporalType.DATE)
    private String personCreate;

    @Column(name = "PersonUpdate")
    private String personUpate;

    @ManyToOne()
    @JoinColumn(name = "Id_Product")
    private Product idProduct;

    @Column(name = "Status")
    private int status;

    public Images() {
        this.dateCreate = new Date(new java.util.Date().getTime());
        this.status = 0;
    }

}
