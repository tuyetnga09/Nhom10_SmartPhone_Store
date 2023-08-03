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
    public Integer countCodeImei(Long id) {
        return productDetailRepository.countCodeImei(id);
    }

    @Override
    public List<ProductDetail> findProductDetailByNameAndStatus(String name, Integer status) {
        return productDetailRepository.findProductDetailByNameAndStatus(name, status);
    }

    @Override
    public List<String> findNameCapacityByNameProductDetail(String name) {
        return productDetailRepository.findNameCapacityByNameProductDetail(name);
    }

    @Override
    public List<String> findNameColorByNameProductDetail(String name) {
        return productDetailRepository.findNameColorByNameProductDetail(name);
    }

    @Override
    public List<String> findImagesByNameProductDetail(String name) {
        return productDetailRepository.findImagesByNameProductDetail(name);
    }

    //lấy ra list productDetail theo điều kiện capacity và color trong trang single_product.html
    @Override
    public List<ProductDetail> findProductDetailByNameAndCapacityOrColor(String name, String color, String capacity) {
        return productDetailRepository.findProductDetailByNameAndCapacityOrColor(name, color, capacity);
    }

    //lấy ra list productDetail theo điều kiện capacity and color trong trang single_product.html
    @Override
    public List<ProductDetail> findProductDetailByNameAndCapacityAndColor(String name, String color, String capacity) {
        return productDetailRepository.findProductDetailByNameAndCapacityAndColor(name, color, capacity);
    }

    @Override
    public ProductDetail getProductDetails(String name, String color, String capacity) {
        return this.productDetailRepository.getProductDetail(name, color, capacity);
    }

    @Override
    public List<ProductDetail> findProductDetailByNameandImei(String name) {
        return productDetailRepository.findProductDetailByNameandImei(name);
    }

    @Override
    public List<ProductDetail> getImageTop3ProductDetail(String name) {
        return productDetailRepository.getImageTop3ProductDetail(name);
    }
}
