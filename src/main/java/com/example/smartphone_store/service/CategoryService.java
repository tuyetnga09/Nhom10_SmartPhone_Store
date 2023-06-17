package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Category;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CategoryService {
    Page<Category> getPage(Integer pageNo, Integer size);

    Page<Category> getReturnDelete(Integer pageNo, Integer size);

    Page<Category> searchCategory(String search, Integer pageNo, Integer size);

    List<Category> getAll();

    void addCategory(Category category);

    Category updateCategory(Category category);

    void removeCategory(Category category);

    void returnCapacity(Category category);

    Category getOne(Integer id);

    void delete(Integer id);
}
