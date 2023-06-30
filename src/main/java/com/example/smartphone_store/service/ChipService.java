package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Chip;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ChipService {

    Page<Chip> getPage(Integer pageNo, Integer size);

    Page<Chip> getReturnDelete(Integer pageNo, Integer size);


    List<Chip> getAll();

    void addChip(Chip chip);

    Chip updateChip(Chip chip);

    void removeChip(Chip chip);

    void returnChip(Chip chip);

    Chip getOne(Long id);

    Page<Chip> viewSearchAllBattery(String search, Integer pageNo, Integer size);

    Chip findByCode(String code);
}
