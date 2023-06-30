package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CapacityService {
    Page<Capacity> getPage(Integer pageNo, Integer size);

    Page<Capacity> getReturnDelete(Integer pageNo, Integer size);

    Page<Capacity> searchCapacity(String search, Integer pageNo, Integer size);

    List<Capacity> getAll();

    void addCapacity(Capacity capacity);

    Capacity updateCapacity(Capacity capacity);

    void removeCapacity(Capacity capacity);

    void returnCapacity(Capacity capacity);

    Capacity getOne(Integer id);

    void delete(Integer id);

    Capacity findByCode(String code);
}
