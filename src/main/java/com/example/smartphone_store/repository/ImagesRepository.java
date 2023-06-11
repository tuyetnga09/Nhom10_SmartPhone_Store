package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImagesRepository extends JpaRepository<Images, Long> {

    @Query("select im from Images im where im.status=?1")
    List<Images> selectByStatus(int status);

    @Transactional
    @Modifying
    @Query("update Images set status = 0 where id = ?1")
    void delete(Long id);

}
