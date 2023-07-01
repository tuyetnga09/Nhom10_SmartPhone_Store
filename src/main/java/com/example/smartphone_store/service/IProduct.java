package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IProduct {

    Page<Product> selectByStatus(int status, Pageable page);

    List<Product> findByStatus(int status);

    void save(Product product);

    void update(Product product);

    void delete(Long id);

    Product findById(Long id);

    List<Product> getAll();

    List<Product> getLineProduct();

}
