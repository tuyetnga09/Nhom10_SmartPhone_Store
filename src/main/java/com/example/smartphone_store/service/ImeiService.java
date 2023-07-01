package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Imei;
import com.example.smartphone_store.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ImeiService {
    Page<Imei> getPage(Integer pageNo, Integer size);

    void addImei(Imei imei);

    void update(Imei imei);

    List<Imei> getAll();

    List<Imei> findByCode(String code);

    List<CharSequence> findByCodeImei(Long idProductDetail);

    // lấy ra list all tất cả các trường imei không phải cua Id_produc cần update để kiểm tra tính tồn tại của mã imei đó
    // trên dòng sản phẩm khác
    List<CharSequence> getImeiByIdProductDetail(Long idProductDetail);
}
