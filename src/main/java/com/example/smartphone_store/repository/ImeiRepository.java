package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.Imei;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImeiRepository extends JpaRepository<Imei, Long> {
    @Query(value = "SELECT Id, Code, DateAddded, SaleDate, PersonSale, PersonUpdate, Status, Id_ProductDetail FROM Imei WHERE Status = 0 ", nativeQuery = true)
    Page<Imei> getPageImei(Pageable pageable);

    //lấy ra list chỉ có 1 trường mã imei
    @Query(value = "SELECT Code FROM Imei WHERE Id_ProductDetail =?1", nativeQuery = true)
    List<CharSequence> findByCodeImei(Long idProductDetail);

    //lấy ra list all tất cả các trường imei theo mã imei
    List<Imei> findByCode(String code);

    // lấy ra list all tất cả các trường imei không phải cua Id_produc cần update để kiểm tra tính tồn tại của mã imei đó
    // trên dòng sản phẩm khác
    @Query(value = "SELECT Code FROM Imei WHERE Id_ProductDetail !=?1", nativeQuery = true)
    List<CharSequence> getImeiByIdProductDetail(Long idProductDetail);
}
