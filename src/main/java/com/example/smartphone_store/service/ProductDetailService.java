package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.ProductDetails;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetails> getAll();

    Page<ProductDetails> getPage(Integer pageNo, Integer size);
}
