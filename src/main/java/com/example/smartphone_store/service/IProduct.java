package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Product;

import java.util.List;

public interface IProduct {

    List<Product> selectByStatus(int status);

    void save(Product product);

    void update(Product product);

    void delete(Long id);

    Product findById(Long id);

    List<Product> getAll();

}
