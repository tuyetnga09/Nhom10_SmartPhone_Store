package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.CartDetails;
import com.example.smartphone_store.repository.CartDetailsRepository;
import com.example.smartphone_store.service.ICartDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartDetailsService implements ICartDetail {

    @Autowired
    private CartDetailsRepository repository;

    @Override
    public void add(CartDetails cartDetails) {
        this.repository.save(cartDetails);
    }

    @Override
    public void delete(Long id) {
        this.repository.deleteById(id);
    }

    @Override
    public void update(Long id, int quantity) {
        this.repository.updateQuantity(id, quantity);
    }

    @Override
    public void updateTotalMoney(Long id, int quantity) {
        this.repository.updateTotalMoney(id, quantity);
    }

    @Override
    public CartDetails countProductDetails(Long id_productDetails) {
        return this.repository.countProductDetails(id_productDetails);
    }

}
