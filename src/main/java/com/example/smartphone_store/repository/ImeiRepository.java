package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Imei;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ImeiRepository extends JpaRepository<Imei, Long> {
    @Query(value = "SELECT Id, Code, DateAddded, SaleDate, PersonSale, PersonUpdate, Status, Id_ProductDetail FROM Imei WHERE Status = 0 ", nativeQuery = true)
    Page<Imei> getPageImei(Pageable pageable);
}
