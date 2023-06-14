package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Size;
import com.example.smartphone_store.repository.SizeRepository;
import com.example.smartphone_store.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {
    @Autowired
    private SizeRepository sizeRepository;

    @Override
    public List<Size> getAll() {
        return sizeRepository.findAll();
    }

    @Override
    public Size findById(Long id) {
        return sizeRepository.findById(id).get();
    }

    @Override
    public Page<Size> pageSizeGetAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return sizeRepository.findAll(pageable);
    }

    @Override
    public void save(Size size) {
        size.setDateCreate(java.time.LocalDate.now());
        sizeRepository.save(size);
    }

    @Override
    public void update(Size size) {
        Size sizeFind = findById(size.getId());
        size.setDateCreate(sizeFind.getDateCreate());
        size.setDateUpdate(java.time.LocalDate.now());
        sizeRepository.save(size);
    }

    @Override
    public void delete(Long id) {
        sizeRepository.deleteById(id);
    }

    @Override
    public void deleteActivitytatus(Long id) {
        Size size = findById(id);
        size.setStatus(1);
        sizeRepository.save(size);
    }

    @Override
    public void returnActivitytatus(Long id) {
        Size size = findById(id);
        size.setStatus(0);
        sizeRepository.save(size);
    }

    @Override
    public Page<Size> viewShowActivitySize(Integer status, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return sizeRepository.viewShowActivityRam(status, pageable);
    }

    @Override
    public Page<Size> viewSeachAllSize(String seach, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return sizeRepository.viewSeachAllRam(seach, pageable);
    }
}
