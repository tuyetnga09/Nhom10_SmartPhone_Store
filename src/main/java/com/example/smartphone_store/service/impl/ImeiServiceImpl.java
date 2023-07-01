package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Imei;
import com.example.smartphone_store.entity.ProductDetail;
import com.example.smartphone_store.repository.ImeiRepository;
import com.example.smartphone_store.service.ImeiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImeiServiceImpl implements ImeiService {
    @Autowired
    private ImeiRepository imeiRepository;

    @Override
    public Page<Imei> getPage(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return imeiRepository.getPageImei(pageable);
    }

    @Override
    public void addImei(Imei imei) {
        imei.setDateAdd(java.time.LocalDate.now());
        imei.setSaleDate(java.time.LocalDate.now());
        imei.setStatus(0);
        imeiRepository.save(imei);
    }

    @Override
    public void update(Imei imei) {


        imei.setSaleDate(java.time.LocalDate.now());
        imei.setStatus(0);
        imeiRepository.save(imei);
    }

    @Override
    public List<Imei> getAll() {
        return imeiRepository.findAll();
    }

    @Override
    public List<Imei> findByCode(String code) {
        return imeiRepository.findByCode(code);
    }

    @Override
    public List<CharSequence> findByCodeImei(Long idProductDetail) {
        return imeiRepository.findByCodeImei(idProductDetail);
    }

    // lấy ra list all tất cả các trường imei không phải cua Id_produc cần update để kiểm tra tính tồn tại của mã imei đó
    // trên dòng sản phẩm khác
    @Override
    public List<CharSequence> getImeiByIdProductDetail(Long idProductDetail) {
        return imeiRepository.getImeiByIdProductDetail(idProductDetail);
    }
}
