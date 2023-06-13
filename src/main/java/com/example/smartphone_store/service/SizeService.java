package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Size;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SizeService {
    List<Size> getAll();

    Size findById(Long id);

    Page<Size> pageSizeGetAll(Integer pageNo, Integer size);

    void save(Size size);

    void update(Size size);

    void delete(Long id);

    void deleteActivitytatus(Long id);

    Page<Size> viewShowActivitySize(Integer status, Integer pageNo, Integer size);

    Page<Size> viewSeachAllSize(String seach, Integer pageNo, Integer size);
}
