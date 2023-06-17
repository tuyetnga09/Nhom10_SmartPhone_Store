package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Color;
import com.example.smartphone_store.repository.ColorRepository;
import com.example.smartphone_store.service.ColorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColorServiceImpl implements ColorService {

    @Autowired
    private ColorRepository colorRepository;

    @Override
    public List<Color> getAll() {
        return colorRepository.findAll();
    }

    @Override
    public Color detail(Long id) {
        return colorRepository.findById(id).orElse(null);
    }

    @Override
    public void add(Color color) {
        color.setDateCreate(java.time.LocalDate.now());
        color.setStatus(0);
        colorRepository.save(color);
    }

    @Override
    public void update(Color color) {
        color.setDateUpdate(java.time.LocalDate.now());
        color.setStatus(0);
        colorRepository.save(color);
    }

    @Override
    public void delete(Color color) {
        color.setStatus(1);
        colorRepository.save(color);
    }

    @Override
    public void undo(Color color) {
        color.setStatus(0);
        colorRepository.save(color);
    }

    @Override
    public Page<Color> paging(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return colorRepository.getColorStatusZero(pageable);
    }

    @Override
    public Page<Color> pagingViewDelete(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return colorRepository.getColorStatusOne(pageable);
    }

    @Override
    public Page<Color> searchColor(String keyword, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return colorRepository.search(keyword, pageable);
    }

}
