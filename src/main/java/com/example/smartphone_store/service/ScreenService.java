package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Screen;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScreenService {
    List<Screen> getAll();

    Screen findById(Long id);

    Page<Screen> pageScreenGetAll(Integer pageNo, Integer size);

    void save(Screen screen);

    void update(Screen screen);

    void delete(Long id);

    void deleteActivitytatus(Long id);

    void returnActivitytatus(Long id);


    Page<Screen> viewShowActivityScreen(Integer status, Integer pageNo, Integer size);

    Page<Screen> viewSeachAllScreen(String seach, Integer pageNo, Integer size);
}
