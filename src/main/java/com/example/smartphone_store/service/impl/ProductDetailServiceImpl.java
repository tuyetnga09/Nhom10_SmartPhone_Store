package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.ProductDetails;
import com.example.smartphone_store.repository.ProductDetailRepository;
import com.example.smartphone_store.service.ProductDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductDetailServiceImpl implements ProductDetailService {
    @Autowired
    private ProductDetailRepository productDetailRepository;

    @Override
    public List<ProductDetails> getAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public Page<ProductDetails> getPage(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return productDetailRepository.getPageProductDetails(pageable);
    }
}
