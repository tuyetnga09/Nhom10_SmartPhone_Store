package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Cart;
import com.example.smartphone_store.repository.CartRepository;
import com.example.smartphone_store.service.ICart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICart {

    @Autowired
    private CartRepository repository;

    @Override
    public Cart findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }

}
