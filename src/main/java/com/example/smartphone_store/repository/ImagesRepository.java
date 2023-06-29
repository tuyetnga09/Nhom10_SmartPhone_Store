package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Images;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {

    @Query("select im from Images im where im.status=?1 order by im.dateCreate desc")
    Page<Images> selectByStatus(int status, Pageable pageable);

    @Query("select im from Images im where im.status=?1")
    List<Images> findByStatus(int status);

    @Transactional
    @Modifying
    @Query("update Images set status = 1 where id = ?1")
    void delete(Long id);

}
