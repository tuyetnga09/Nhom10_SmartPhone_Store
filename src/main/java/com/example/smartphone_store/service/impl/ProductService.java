package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Product;
import com.example.smartphone_store.entity.ProductDetail;
import com.example.smartphone_store.repository.ProductRepository;
import com.example.smartphone_store.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Override
    public List<Product> getLineProduct() {
        return null;
    }

    @Override
    public List<Product> getProductIphoneX() {
        return repository.getProductIphoneX();
    }

    @Override
    public List<Product> getProductIphone11() {
        return repository.getProductIphone11();
    }

    @Override
    public List<Product> getProductIphone12() {
        return repository.getProductIphone12();
    }

    @Override
    public List<Product> getProductIphone13() {
        return repository.getProductIphone13();
    }

    @Override
    public List<Product> getProductIphone14() {
        return repository.getProductIphone14();
    }

    @Override
    public Page<Product> getProductByPriceLess10000000(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.getProductByPriceLess10000000(pageable);
    }

    @Override
    public Page<Product> getProductByPriceFrom10000000To20000000(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.getProductByPriceFrom10000000To20000000(pageable);
    }

    @Override
    public Page<Product> getProductByPriceBigger20000000(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.getProductByPriceBigger20000000(pageable);
    }

}
