package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.ProductDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetails, Long> {
    @Query(value = "SELECT Id, Code, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status, Id_Capacity FROM ProductDetails WHERE Status = 0 ", nativeQuery = true)
    Page<ProductDetails> getPageProductDetails(Pageable pageable);
}
