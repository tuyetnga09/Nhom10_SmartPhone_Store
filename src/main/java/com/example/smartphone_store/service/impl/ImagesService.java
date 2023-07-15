package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Images;
import com.example.smartphone_store.repository.ImagesRepository;
import com.example.smartphone_store.service.IImages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ImagesService implements IImages {

    @Autowired
    private ImagesRepository repository;

    @Override
    public Page<Images> selectAll(int status, Pageable pageable) {
        return this.repository.selectByStatus(status, pageable);
    }

    @Override
    public List<Images> findByStatus(int status) {
        return this.repository.findByStatus(status);
    }

    @Override
    public void save(Images images) {
        this.repository.save(images);
    }

    @Override
    public void update(Images images) {
        this.repository.save(images);
    }

    @Override
    public void delete(Long id) {
        this.repository.delete(id);
    }

    @Override
    public Images findById(Long id) {
        return this.repository.findById(id).orElse(null);
    }


}
