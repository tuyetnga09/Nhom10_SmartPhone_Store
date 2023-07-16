package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Capacity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CapacityRepository extends JpaRepository<Capacity, Integer> {

    @Query(value = "SELECT Id, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Capacity WHERE Status = 0 ORDER BY DateCreate DESC, Id DESC", nativeQuery = true)
    Page<Capacity> getPageCapacity(Pageable pageable);

    @Query(value = "SELECT Id, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Capacity WHERE Status = 1 ", nativeQuery = true)
    Page<Capacity> returnDeleteCapacity(Pageable pageable);

    @Query(value = "SELECT Id, Code, Name, DateCreate, DateUpdate, PersonCreate, PersonUpdate, Status FROM Capacity WHERE Code LIKE %?1% OR Name LIKE %?1% OR DateCreate LIKE %?1% OR DateUpdate LIKE %?1% OR PersonCreate LIKE %?1% OR PersonUpdate LIKE %?1% AND Status = 0", nativeQuery = true)
    Page<Capacity> searchCapacity(String search, Pageable pageable);

    Capacity findByCode(String code);

//    //lấy ra nam của capacity
//    @Transactional
//    @Modifying
//    @Query(value = "SELECT DISTINCT Capacity.Name, Capacity.Id FROM ProductDetail JOIN Capacity ON ProductDetail.Id_Capacity = Capacity.Id " +
//            " WHERE Id_Product =?1 AND [Capacity].[Status] = 0 AND ProductDetail.[Status] =0\n", nativeQuery = true)
//    List<Capacity> listIdAndNameCapacity(Long id_Product);
}
