package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Chip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ChipRepository extends JpaRepository<Chip, Long> {
    @Query(value = "SELECT Id, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Chip WHERE Status = 0 ", nativeQuery = true)
    Page<Chip> getPageChip(Pageable pageable);

    @Query(value = "SELECT Id, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Chip WHERE Status = 1 ", nativeQuery = true)
    Page<Chip> returnDeleteChip(Pageable pageable);

    @Query(value = "SELECT * FROM Chip WHERE ((Code LIKE %?1% OR Name LIKE %?1% OR " +
            " DateCreate LIKE %?1% OR DateUpdate LIKE %?1% OR PersonCreate LIKE %?1% OR" +
            " PersonUpdate LIKE %?1%) AND Status = 0)", nativeQuery = true)
    Page<Chip> viewSearchAllChip(String search, Pageable pageable);

}
