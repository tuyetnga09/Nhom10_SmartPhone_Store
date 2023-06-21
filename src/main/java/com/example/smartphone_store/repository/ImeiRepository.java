package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Imei;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImeiRepository extends JpaRepository<Imei, Long> {
}
