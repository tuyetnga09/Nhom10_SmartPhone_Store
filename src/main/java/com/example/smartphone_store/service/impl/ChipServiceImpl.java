package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Chip;
import com.example.smartphone_store.repository.ChipRepository;
import com.example.smartphone_store.service.ChipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChipServiceImpl implements ChipService {
    @Autowired
    private ChipRepository chipRepository;

    @Override
    public Page<Chip> getPage(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return chipRepository.getPageChip(pageable);
    }

    @Override
    public Page<Chip> getReturnDelete(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return chipRepository.returnDeleteChip(pageable);
    }

    @Override
    public List<Chip> getAll() {
        return chipRepository.findAll();
    }

    @Override
    public void addChip(Chip chip) {
        chip.setDateCreate(java.time.LocalDate.now());
        chip.setDateUpdate(java.time.LocalDate.now());
        chip.setStatus(0);
        chipRepository.save(chip);
    }

    @Override
    public Chip updateChip(Chip chip) {
        Chip chipId = getOne(chip.getId());
        chip.setDateCreate(chipId.getDateCreate());
        chip.setDateUpdate(java.time.LocalDate.now());
        chip.setStatus(chipId.getStatus());
        return chipRepository.save(chip);
    }

    @Override
    public void removeChip(Chip chip) {
        chip.setStatus(1);
        chipRepository.save(chip);
    }

    @Override
    public void returnChip(Chip chip) {
        chip.setStatus(0);
        chipRepository.save(chip);
    }

    @Override
    public Chip getOne(Long id) {
        return chipRepository.findById(id).orElse(null);
    }

    @Override
    public Page<Chip> viewSearchAllBattery(String search, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return chipRepository.viewSearchAllChip(search, pageable);
    }
}
