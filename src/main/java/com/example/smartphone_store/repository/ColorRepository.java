package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Color;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ColorRepository extends JpaRepository<Color, Long> {

    @Query(value = "SELECT ID, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Color WHERE Status = 0", nativeQuery = true)
    Page<Color> getColorStatusOne(Pageable pageable);

    @Query(value = "SELECT * FROM Color WHERE ((Code LIKE %?1% OR Name LIKE %?1% OR " +
            " DateCreate LIKE %?1% OR DateUpdate LIKE %?1% OR PersonCreate LIKE %?1% OR" +
            " PersonUpdate LIKE %?1%) AND Status = 0)", nativeQuery = true)
    Page<Color> search(String keyword, Pageable pageable);
}
