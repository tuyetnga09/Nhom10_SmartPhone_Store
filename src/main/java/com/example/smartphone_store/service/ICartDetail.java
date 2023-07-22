package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.CartDetails;

import java.util.List;

public interface ICartDetail {

    void add(CartDetails cartDetails);

    void delete(Long id);

    void update(Long id, int quantity);

    void updateTotalMoney(Long id, int quantity);

    CartDetails countProductDetails(Long id_productDetails);

}
