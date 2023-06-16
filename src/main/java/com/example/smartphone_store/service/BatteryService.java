package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.entity.Ram;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BatteryService {

    Page<Battery> getPage(Integer pageNo, Integer size);

    Page<Battery> getReturnDelete(Integer pageNo, Integer size);


    List<Battery> getAll();

    void addBattery(Battery battery);

    Battery updateBattery(Battery battery);

    void removeBattery(Battery battery);

    void returnBattery(Battery battery);

    Battery getOne(Long id);

    Page<Battery> viewSeachAllBattery(String seach, Integer pageNo, Integer size);

}
