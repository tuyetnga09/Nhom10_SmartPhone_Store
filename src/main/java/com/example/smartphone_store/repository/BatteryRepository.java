package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Battery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {
    @Query(value = "SELECT Id, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Battery WHERE Status = 0 ", nativeQuery = true)
    Page<Battery> getPageBattery(Pageable pageable);

    @Query(value = "SELECT Id, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Battery WHERE Status = 1 ", nativeQuery = true)
    Page<Battery> returnDeleteBattery(Pageable pageable);

    @Query(value = "SELECT * FROM Battery WHERE ((Code LIKE %?1% OR Name LIKE %?1% OR " +
            " DateCreate LIKE %?1% OR DateUpdate LIKE %?1% OR PersonCreate LIKE %?1% OR" +
            " PersonUpdate LIKE %?1%) AND Status = 0)", nativeQuery = true)
    Page<Battery> viewSeachAllBattery(String seach, Pageable pageable);

}
