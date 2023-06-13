package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Screen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {


    @Query(value = "SELECT * FROM Screen WHERE Status =?1", nativeQuery = true)
    Page<Screen> viewShowActivityScreen(Integer status, Pageable pageable);

    @Query(value = "SELECT * FROM Screen WHERE ((Code LIKE %?1% OR Name LIKE %?1% OR " +
            " DateCreate LIKE %?1% OR DateUpdate LIKE %?1% OR PersonCreate LIKE %?1% OR" +
            " PersonUpdate LIKE %?1%) AND Status = 0)", nativeQuery = true)
    Page<Screen> viewSeachAllScreen(String seach, Pageable pageable);
}
