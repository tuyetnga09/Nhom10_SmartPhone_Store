package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Capacity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface CapacityRepository extends JpaRepository<Capacity, Integer> {

    @Query(value = "SELECT Id, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Capacity WHERE Status = 0 ", nativeQuery = true)
    Page<Capacity> getPageCapacity(Pageable pageable);

    @Query(value = "SELECT Id, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Capacity WHERE Status = 1 ", nativeQuery = true)
    Page<Capacity> returnDeleteCapacity(Pageable pageable);

    @Query(value = "SELECT Id, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Capacity WHERE Code LIKE %?1% OR Name LIKE %?1% OR DateCreate LIKE %?1% OR DateUpdate LIKE %?1% OR PersonCreate LIKE %?1% OR PersonUpdate LIKE %?1% AND Status = 0", nativeQuery = true)
    Page<Capacity> searchCapacity(String search, Pageable pageable);

}
