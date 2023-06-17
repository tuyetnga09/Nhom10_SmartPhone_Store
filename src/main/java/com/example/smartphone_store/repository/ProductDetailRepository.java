package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetails, Long> {
}
