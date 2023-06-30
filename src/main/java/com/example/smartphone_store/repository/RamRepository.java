package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Capacity;
import com.example.smartphone_store.entity.Ram;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RamRepository extends JpaRepository<Ram, Long> {

    @Query(value = "SELECT * FROM Ram WHERE Status =?1 ORDER BY DateCreate DESC, Id DESC", nativeQuery = true)
    Page<Ram> viewShowActivityRam(Integer status, Pageable pageable);

    @Query(value = "SELECT * FROM Ram WHERE ((Code LIKE %?1% OR Name LIKE %?1% OR " +
            " DateCreate LIKE %?1% OR DateUpdate LIKE %?1% OR PersonCreate LIKE %?1% OR" +
            " PersonUpdate LIKE %?1%) AND Status = 0)", nativeQuery = true)
    Page<Ram> viewSeachAllRam(String seach, Pageable pageable);

    Ram findByCode(String code);


}
