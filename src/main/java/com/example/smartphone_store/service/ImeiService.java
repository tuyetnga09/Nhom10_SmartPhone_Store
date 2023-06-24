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

    List<Imei> getAll();

}
