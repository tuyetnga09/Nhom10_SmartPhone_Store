package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Category;
import com.example.smartphone_store.repository.CategoryRepository;
import com.example.smartphone_store.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Page<Category> getPage(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return categoryRepository.getPageCategory(pageable);
    }

    @Override
    public Page<Category> getReturnDelete(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return categoryRepository.returnDeleteCategory(pageable);
    }

    @Override
    public Page<Category> searchCategory(String search, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return categoryRepository.searchCategory(search, pageable);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public void addCategory(Category category) {
        category.setDateCreate(java.time.LocalDate.now());
        category.setDateUpdate(java.time.LocalDate.now());
        category.setStatus(0);
        categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Category categoryId = getOne(category.getId());
        category.setDateCreate(categoryId.getDateCreate());
        category.setDateUpdate(java.time.LocalDate.now());
        category.setStatus(categoryId.getStatus());
        return categoryRepository.save(category);
    }

    @Override
    public void removeCategory(Category category) {
        category.setStatus(1);
        categoryRepository.save(category);
    }

    @Override
    public void returnCapacity(Category category) {
        category.setStatus(0);
        categoryRepository.save(category);
    }

    @Override
    public Category getOne(Integer id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Integer id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category findByCode(String code) {
        return categoryRepository.findByCode(code);
    }
}
