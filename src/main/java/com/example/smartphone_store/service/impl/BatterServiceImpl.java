package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.repository.BatteryRepository;
import com.example.smartphone_store.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BatterServiceImpl implements BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;

    @Override
    public List<Battery> findAll() {
        return batteryRepository.findAll();
    }

    @Override
    public Battery findBayId(Long id) {
        return batteryRepository.findById(id).orElse(null);
    }

    @Override
    public void add(Battery battery) {
        batteryRepository.save(battery);
    }

    @Override
    public void update(Battery battery) {
        batteryRepository.save(battery);
    }

    @Override
    public void delete(Long id) {
        batteryRepository.deleteById(id);
    }
}
