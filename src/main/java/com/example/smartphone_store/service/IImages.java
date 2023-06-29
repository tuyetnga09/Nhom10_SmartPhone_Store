package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Images;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IImages {

    Page<Images> selectAll(int status, Pageable pageable);

    List<Images> findByStatus(int status);

    void save(Images images);

    void update(Images images);

    void delete(Long id);

    Images findById(Long id);

}
