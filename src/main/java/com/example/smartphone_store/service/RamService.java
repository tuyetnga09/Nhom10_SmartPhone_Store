package com.example.smartphone_store.service;


import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.entity.Ram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RamService {
    List<Ram> getAll();

    Ram findById(Long id);

    Page<Ram> pageRamGetAll(Integer pageNo, Integer size);

    void save(Ram ram);

    void update(Ram ram);

    void delete(Long id);

    void deleteActivitytatus(Long id);

    void returnActivitytatus(Long id);

    Page<Ram> viewShowActivityRam(Integer status, Integer pageNo, Integer size);

    Page<Ram> viewSeachAllRam(String seach, Integer pageNo, Integer size);

    Ram findByCode(String code);

}
