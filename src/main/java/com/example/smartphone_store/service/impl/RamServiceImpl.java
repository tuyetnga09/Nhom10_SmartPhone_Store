package com.example.smartphone_store.service.impl;

import com.example.smartphone_store.entity.Ram;
import com.example.smartphone_store.repository.RamRepository;
import com.example.smartphone_store.service.RamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RamServiceImpl implements RamService {
    @Autowired
    private RamRepository ramRepository;

    @Override
    public List<Ram> getAll() {
        return ramRepository.findAll();
    }

    @Override
    public Ram findById(Long id) {
        return ramRepository.findById(id).get();
    }

    @Override
    public Page<Ram> pageRamGetAll(Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return ramRepository.findAll(pageable);
    }

    @Override
    public void save(Ram ram) {
        ram.setDateCreate(java.time.LocalDate.now());
        ramRepository.save(ram);
    }

    @Override
    public void update(Ram ram) {
        Ram ramFind = findById(ram.getId());

        ram.setDateCreate(ramFind.getDateCreate());
        ram.setDateUpdate(java.time.LocalDate.now());
        ramRepository.save(ram);
    }

    @Override
    public void delete(Long id) {
        ramRepository.deleteById(id);

    }

    @Override
    public void deleteActivitytatus(Long id) {
        Ram ram = ramRepository.findById(id).get();
        ram.setStatus(1);
        ramRepository.save(ram);
    }

    @Override
    public void returnActivitytatus(Long id) {
        Ram ram = ramRepository.findById(id).get();
        ram.setStatus(0);
        ramRepository.save(ram);
    }

    @Override
    public Page<Ram> viewShowActivityRam(Integer status, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return ramRepository.viewShowActivityRam(status, pageable);
    }

    @Override
    public Page<Ram> viewSeachAllRam(String seach, Integer pageNo, Integer size) {
        Pageable pageable = PageRequest.of(pageNo, size);
        return ramRepository.viewSeachAllRam(seach, pageable);
    }
}
