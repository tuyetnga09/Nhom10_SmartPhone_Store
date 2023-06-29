package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.repository.ProductRepository;
import com.example.smartphone_store.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProduct {

    @Autowired
    private ProductRepository repository;

    @Override
    public Page<Product> selectByStatus(int status, Pageable pageable) {
        return this.repository.selectByStatus(status, pageable);
    }

    @Override
    public List<Product> findByStatus(int status) {
        return this.repository.findByStatus(status);
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
    public void delete(Long id) {
        this.repository.delete(id);
    }

    @Override
    public Product findById(Long id) {
        return this.repository.findById(id).orElseThrow(null);
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

}
