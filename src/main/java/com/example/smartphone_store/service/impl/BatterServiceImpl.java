package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.repository.BatteryRepository;
import com.example.smartphone_store.service.BatteryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BatterServiceImpl implements BatteryService {

    @Autowired
    private BatteryRepository batteryRepository;


    @Override
    public Page<Battery> getPage(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return batteryRepository.getPageBattery(pageable);
    }

    @Override
    public Page<Battery> getReturnDelete(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return batteryRepository.returnDeleteBattery(pageable);
    }

    @Override
    public List<Battery> getAll() {
        return batteryRepository.findAll();
    }

    @Override
    public void addBattery(Battery battery) {
        battery.setDateCreate(java.time.LocalDate.now());
        battery.setDateUpdate(java.time.LocalDate.now());
        battery.setStatus(0);
        batteryRepository.save(battery);
    }

    @Override
    public Battery updateBattery(Battery battery) {
        Battery batteryId = getOne(battery.getId());
        battery.setDateCreate(batteryId.getDateCreate());
        battery.setDateUpdate(java.time.LocalDate.now());
        battery.setStatus(batteryId.getStatus());
        return batteryRepository.save(battery);
    }

    @Override
    public void removeBattery(Battery battery) {
        battery.setStatus(1);
        batteryRepository.save(battery);
    }

    @Override
    public void returnBattery(Battery battery) {
        battery.setStatus(0);
        batteryRepository.save(battery);
    }

    @Override
    public Battery getOne(Long id) {
        return batteryRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Battery> viewSeachAllBattery(String seach, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return batteryRepository.viewSeachAllBattery(seach, pageable);
    }

    @Override
    public Battery findByCode(String code) {
        return batteryRepository.findByCode(code);
    }
}
