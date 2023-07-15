package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Product;
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

    List<Product> getProductIphoneX();

    List<Product> getProductIphone11();

    List<Product> getProductIphone12();

    List<Product> getProductIphone13();

    List<Product> getProductIphone14();

    Page<Product> getProductByPriceLess10000000(Integer page, Integer size);

    Page<Product> getProductByPriceFrom10000000To20000000(Integer page, Integer size);

    Page<Product> getProductByPriceBigger20000000(Integer page, Integer size);
}
