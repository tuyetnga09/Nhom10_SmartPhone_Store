package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Color;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ColorService {

    List<Color> getAll();

    Color detail(Long id);

    void add(Color color);

    void update(Color color);

    void delete(Color color);

    Page<Color> paging(Integer page, Integer size);

    Page<Color> searchColor(String keyword, Integer page, Integer size);

}
