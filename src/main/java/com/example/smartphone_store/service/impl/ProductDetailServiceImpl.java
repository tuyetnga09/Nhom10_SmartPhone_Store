package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.ProductDetail;
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
    public List<ProductDetail> getAll() {
        return productDetailRepository.findAll();
    }

    @Override
    public Page<ProductDetail> getPage(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return productDetailRepository.getPageProductDetails(pageable);
    }

    @Override
    public void addProductDetail(ProductDetail productDetail) {
        productDetail.setDateCreate(java.time.LocalDate.now());
        productDetail.setDateUpdate(java.time.LocalDate.now());
        productDetail.setStatus(0);
        productDetailRepository.save(productDetail);
    }

    @Override
    public void removeProductDetail(ProductDetail productDetail) {
        productDetail.setStatus(1);
        productDetailRepository.save(productDetail);
    }

    @Override
    public ProductDetail getOne(Long id) {
        return productDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void returnProductDetail(Long id) {
        ProductDetail productDetail = getOne(id);
        productDetail.setStatus(0);
        productDetailRepository.save(productDetail);
    }

    @Override
    public Page<ProductDetail> getReturnDelete(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return productDetailRepository.getReturnProductDetails(pageable);
    }

    @Override
    public void updateProduct(ProductDetail productDetail) {
        ProductDetail productDetailId = getOne(productDetail.getId());
        productDetail.setDateCreate(productDetailId.getDateCreate());
        productDetail.setDateUpdate(java.time.LocalDate.now());
        productDetail.setStatus(productDetailId.getStatus());
        productDetailRepository.save(productDetail);
    }

    @Override
    public Page<ProductDetail> viewSeachAllProductDetail(String search, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return productDetailRepository.viewSeachAllProductDetails(search, pageable);
    }

    @Override
    public ProductDetail findByCode(String code) {
        return productDetailRepository.findByCode(code);
    }

    @Override
    public List<ProductDetail> getTop10NewProductDetail() {
        return productDetailRepository.getTop10NewProductDetails();
    }

    @Override
    public List<ProductDetail> getBestSelling() {
        return productDetailRepository.getBestSelling();
    }

    @Override
    public List<ProductDetail> getLineProductDetail() {
        return productDetailRepository.getLineProductDetail();
    }

    @Override
    public Page<ProductDetail> findProductDetailByStatusAndProductId(Integer status, Long id, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return productDetailRepository.findProductDetailByStatusAndProductId(status, id, pageable);
    }

    @Override
    public Page<ProductDetail> getProductDetailByPriceLess10000000(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return productDetailRepository.getProductDetailByPriceLess10000000(pageable);
    }

    @Override
    public Page<ProductDetail> getProductDetailByPriceFrom10000000To20000000(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return productDetailRepository.getProductDetailByPriceFrom10000000To20000000(pageable);
    }

    @Override
    public Page<ProductDetail> getProductDetailByPriceBigger20000000(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return productDetailRepository.getProductDetailByPriceBigger20000000(pageable);
    }
}
