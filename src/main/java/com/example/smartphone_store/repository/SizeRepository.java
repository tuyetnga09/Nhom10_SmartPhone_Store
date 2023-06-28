package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {


    @Query(value = "SELECT * FROM Size WHERE Status =?1 ORDER BY DateCreate DESC, Id DESC", nativeQuery = true)
    Page<Size> viewShowActivityRam(Integer status, Pageable pageable);

    @Query(value = "SELECT * FROM Size WHERE ((Code LIKE %?1% OR Name LIKE %?1% OR " +
            " DateCreate LIKE %?1% OR DateUpdate LIKE %?1% OR PersonCreate LIKE %?1% OR" +
            " PersonUpdate LIKE %?1%) AND Status = 0)", nativeQuery = true)
    Page<Size> viewSeachAllRam(String seach, Pageable pageable);
}
