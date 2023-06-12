package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Manufacture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, Long> {

    @Query(value = "SELECT ID, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Manufacture WHERE Status = 0", nativeQuery = true)
    Page<Manufacture> getManufactureStatusZero(Pageable pageable);

    @Query(value = "SELECT ID, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Manufacture WHERE Status = 1", nativeQuery = true)
    Page<Manufacture> getManufactureStatusOne(Pageable pageable);

    @Query(value = "SELECT * FROM Manufacture WHERE ((Code LIKE %?1% OR Name LIKE %?1% OR " +
            " DateCreate LIKE %?1% OR DateUpdate LIKE %?1% OR PersonCreate LIKE %?1% OR" +
            " PersonUpdate LIKE %?1%) AND Status = 0)", nativeQuery = true)
    Page<Manufacture> search(String keyword, Pageable pageable);
}
