package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

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

    //lấy ra tổng số lượng imei thuộc ID_ProductDetail
    Integer countCodeImei(Long id);

    List<ProductDetail> findProductDetailByNameAndStatus(String name, Integer status);

    List<String> findNameCapacityByNameProductDetail(String name);

    List<String> findNameColorByNameProductDetail(String name);

    List<String> findImagesByNameProductDetail(String name);

    //lấy ra list productDetail theo điều kiện capacity và color trong trang single_product.html
    List<ProductDetail> findProductDetailByNameAndCapacityOrColor(String name,String color, String capacity);

    //lấy ra list productDetail theo điều kiện capacity and color trong trang single_product.html
    List<ProductDetail> findProductDetailByNameAndCapacityAndColor(String name,String color, String capacity);

}
