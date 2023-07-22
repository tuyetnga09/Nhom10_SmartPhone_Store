package com.example.smartphone_store.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "Cart")
@AllArgsConstructor
@Setter
@Getter
public class Cart {

    @Id
    @Column(name = "Id")
    private Long id;

    @Column(name = "Code")
    private String code;

    @Column(name = "DateCreate")
    private Date dateCreate;

    @Column(name = "DateUpdate")
    private Date dateUpdate;

    @Column(name = "PersonCreate")
    private String personCreate;

    @Column(name = "PersonUpdate")
    private String personUpdate;

    @Column(name = "Status")
    private int status;

    @OneToMany(mappedBy = "id_cart", fetch = FetchType.LAZY)
    private List<CartDetails> cartDetails;

    public Cart() {
        this.dateCreate = new Date(new java.util.Date().getTime());
        this.status = 0;
    }

    public Cart(Long id){
        this.id = id;
    }

}
