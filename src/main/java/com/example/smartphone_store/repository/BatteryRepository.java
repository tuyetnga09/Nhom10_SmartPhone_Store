package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Battery;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BatteryRepository extends JpaRepository<Battery, Long> {
    @Transactional
    @Modifying
    @Query("update Battery set status = 0 where id = ?1")
    void delete(int id);

    @Query("select btr from Battery btr where btr.status = 1")
    List<Battery> SelectWhereStatus();

}
