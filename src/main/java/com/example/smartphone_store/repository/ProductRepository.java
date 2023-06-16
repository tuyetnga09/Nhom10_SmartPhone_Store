package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select pr from Product pr where pr.status = ?1")
    List<Product> selectByStatus(int status);

    @Transactional
    @Modifying
    @Query("update Product set status = 1 where id = ?1")
    void delete(Long id);

}
