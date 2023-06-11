package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Battery;

import java.util.List;

public interface BatteryService {

    List<Battery> findAll();

    Battery findBayId(Long id);

    void add(Battery battery);

    void update (Battery battery);

    void delete(Long id);
}
