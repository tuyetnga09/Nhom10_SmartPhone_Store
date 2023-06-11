package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Images;

import java.util.List;

public interface IImages {

    List<Images> findAll(int status);

    void save(Images images);

    void update(Images images);

    void delete(Long id);

    Images findById(Long id);

}
