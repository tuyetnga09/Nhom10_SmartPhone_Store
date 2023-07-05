package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductDetailService {
    List<ProductDetail> getAll();

    Page<ProductDetail> getPage(Integer pageNo, Integer size);

    void addProductDetail(ProductDetail productDetail);

    void removeProductDetail(ProductDetail productDetail);

    ProductDetail getOne(Long id);

    void returnProductDetail(Long id);

    Page<ProductDetail> getReturnDelete(Integer pageNo, Integer size);

    void updateProduct(ProductDetail productDetail);

    Page<ProductDetail> viewSeachAllProductDetail(String search, Integer pageNo, Integer size);

    ProductDetail findByCode(String code);

    List<ProductDetail> getTop10NewProductDetail();

    List<ProductDetail> getBestSelling();

    List<ProductDetail> getLineProductDetail();

    Page<ProductDetail> findProductDetailByStatusAndProductId(Integer status, Long id, Integer page, Integer size);

    Page<ProductDetail> getProductDetailByPriceLess10000000(Integer page, Integer size);

    Page<ProductDetail> getProductDetailByPriceFrom10000000To20000000(Integer page, Integer size);

    Page<ProductDetail> getProductDetailByPriceBigger20000000(Integer page, Integer size);
}
