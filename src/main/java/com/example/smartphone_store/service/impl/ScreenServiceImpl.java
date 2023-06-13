package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Screen;
import com.example.smartphone_store.repository.ScreenRepository;
import com.example.smartphone_store.service.ScreenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScreenServiceImpl implements ScreenService {
    @Autowired
    private ScreenRepository screenRepository;

    @Override
    public List<Screen> getAll() {
        return screenRepository.findAll();
    }

    @Override
    public Screen findById(Long id) {
        return screenRepository.findById(id).get();
    }

    @Override
    public Page<Screen> pageScreenGetAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return null;
    }

    @Override
    public void save(Screen screen) {
        screen.setDateCreate(java.time.LocalDate.now());
        screenRepository.save(screen);
    }

    @Override
    public void update(Screen screen) {
        Screen screenFind = findById(screen.getId());
        screen.setDateCreate(screenFind.getDateCreate());
        screen.setDateUpdate(java.time.LocalDate.now());
        screenRepository.save(screen);
    }

    @Override
    public void delete(Long id) {
        screenRepository.deleteById(id);
    }

    @Override
    public void deleteActivitytatus(Long id) {
        Screen screen = findById(id);
        screen.setStatus(1);
        screenRepository.save(screen);
    }

    @Override
    public Page<Screen> viewShowActivityScreen(Integer status, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return screenRepository.viewShowActivityScreen(0, pageable);
    }

    @Override
    public Page<Screen> viewSeachAllScreen(String seach, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return screenRepository.viewSeachAllScreen(seach, pageable);
    }
}
