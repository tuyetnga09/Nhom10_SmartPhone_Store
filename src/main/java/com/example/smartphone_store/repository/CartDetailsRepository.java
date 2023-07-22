package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.CartDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface CartDetailsRepository extends JpaRepository<CartDetails, Long> {

    @Modifying
    @Transactional
    @Query(value = "update CartDetails set quantity = quantity + ?2 where id = ?1")
    void updateQuantity(Long id, int quantity);

    @Modifying
    @Transactional
    @Query(value = "update CartDetails set totalMoney = totalMoney * ?2 where id = ?1")
    void updateTotalMoney(Long id, int quantity);

    @Query(value = "select ctd from CartDetails ctd where ctd.id_productDetail.id = ?1")
    CartDetails countProductDetails(Long id_productDetails);

}
