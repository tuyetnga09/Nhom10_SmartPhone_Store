package com.example.smartphone_store.service;

import com.example.smartphone_store.entity.Manufacture;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.util.List;

public interface ManufactureService {

    List<Manufacture> getAll();

    Manufacture detail(Long id);

    void add(Manufacture manufacture);

    void update(Manufacture manufacture);

    void delete(Manufacture manufacture);

    void undo(Manufacture manufacture);

    Page<Manufacture> paging(Integer page, Integer size);

    Page<Manufacture> pagingViewDelete(Integer page, Integer size);

    Page<Manufacture> searchManufacture(String keyword, Integer page, Integer size);
}
