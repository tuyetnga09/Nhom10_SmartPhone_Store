package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.repository.CapacityRepository;
import com.example.smartphone_store.service.CapacityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CapacityServiceImpl implements CapacityService {
    @Autowired
    private CapacityRepository capacityRepository;

    @Override
    public Page<Capacity> getPage(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return capacityRepository.getPageCapacity(pageable);
    }

    @Override
    public Page<Capacity> getReturnDelete(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return capacityRepository.returnDeleteCapacity(pageable);
    }

    @Override
    public Page<Capacity> searchCapacity(String search, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return capacityRepository.searchCapacity(search, pageable);
    }

    @Override
    public List<Capacity> getAll() {
        return capacityRepository.findAll();
    }

    @Override
    public void addCapacity(Capacity capacity) {
        capacity.setDateCreate(java.time.LocalDate.now());
        capacity.setDateUpdate(java.time.LocalDate.now());
        capacity.setStatus(0);
        capacityRepository.save(capacity);
    }

    @Override
    public Capacity updateCapacity(Capacity capacity) {
        Capacity capacityId = getOne(capacity.getId());
        capacity.setDateCreate(capacityId.getDateCreate());
        capacity.setDateUpdate(java.time.LocalDate.now());
        capacity.setStatus(capacityId.getStatus());
        return capacityRepository.save(capacity);
    }

    @Override
    public void removeCapacity(Capacity capacity) {
        capacity.setStatus(1);
        capacityRepository.save(capacity);
    }

    @Override
    public void returnCapacity(Capacity capacity) {
        capacity.setStatus(0);
        capacityRepository.save(capacity);
    }

    @Override
    public Capacity getOne(Integer id) {
        return capacityRepository.findById(id).orElse(null);
    }
}
