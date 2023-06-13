package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Manufacture;
import com.example.smartphone_store.repository.ManufactureRepository;
import com.example.smartphone_store.service.ManufactureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ManufactureServiceImpl implements ManufactureService {

    @Autowired
    private ManufactureRepository manufactureRepository;

    @Override
    public List<Manufacture> getAll() {
        return manufactureRepository.findAll();
    }

    @Override
    public Manufacture detail(Long id) {
        return manufactureRepository.findById(id).orElse(null);
    }

    @Override
    public void add(Manufacture manufacture) {
        manufacture.setDateCreate(java.time.LocalDate.now());
        manufacture.setStatus(0);
        manufactureRepository.save(manufacture);
    }

    @Override
    public void update(Manufacture manufacture) {
        manufacture.setDateUpdate(java.time.LocalDate.now());
        manufacture.setStatus(0);
        manufactureRepository.save(manufacture);
    }

    @Override
    public void delete(Manufacture manufacture) {
        manufacture.setStatus(1);
        manufactureRepository.save(manufacture);
    }

    @Override
    public void undo(Manufacture manufacture) {
        manufacture.setStatus(0);
        manufactureRepository.save(manufacture);
    }

    @Override
    public Page<Manufacture> paging(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return manufactureRepository.getManufactureStatusZero(pageable);
    }

    @Override
    public Page<Manufacture> pagingViewDelete(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return manufactureRepository.getManufactureStatusOne(pageable);
    }

    @Override
    public Page<Manufacture> searchManufacture(String keyword, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return manufactureRepository.search(keyword, pageable);
    }
}
