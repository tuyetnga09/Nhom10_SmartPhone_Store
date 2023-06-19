package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Query(value = "SELECT Id, Code, Name, DateCreate, Describe , DateUpdate, PersonCreate, PersonUpdate, Status, Id_Capacity, Id_Color, Id_Manufacture, Id_Category, Id_Battery, Id_Chip, Id_Ram, Id_Screen, Id_Product FROM ProductDetail WHERE Status = 0 ", nativeQuery = true)
    Page<ProductDetail> getPageProductDetails(Pageable pageable);

    @Query(value = "SELECT Id, Code, Name, DateCreate, Describe , DateUpdate, PersonCreate, PersonUpdate, " +
            " Status, Id_Capacity, Id_Color, Id_Manufacture, Id_Category, Id_Battery, Id_Chip, Id_Ram, Id_Screen, " +
            " Id_Product FROM ProductDetail WHERE Status = 1 ", nativeQuery = true)
    Page<ProductDetail> getReturnProductDetails(Pageable pageable);

    @Query(value = "SELECT * FROM ProductDetail WHERE ((Code LIKE %?1% OR Name LIKE %?1% OR " +
            " DateCreate LIKE %?1% OR DateUpdate LIKE %?1% OR PersonCreate LIKE %?1% OR" +
            " PersonUpdate LIKE %?1%) AND Status = 0)", nativeQuery = true)
    Page<ProductDetail> viewSeachAllProductDetails(String search, Pageable pageable);

}
