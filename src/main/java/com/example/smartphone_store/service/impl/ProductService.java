package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.repository.ProductRepository;
import com.example.smartphone_store.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProduct {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> selectByStatus(int status) {
        return this.repository.selectByStatus(status);
    }

    @Override
    public void save(Product product) {
        this.repository.save(product);
    }

    @Override
    public void update(Product product) {
        this.repository.save(product);
    }

    @Override
    public void delete(int id) {
        this.repository.delete(id);
    }

    @Override
    public Product findById(int id) {
        return this.repository.findById(id).orElseThrow(null);
    }

}
