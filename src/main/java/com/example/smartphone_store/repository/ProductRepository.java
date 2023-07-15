package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("select pr from Product pr where pr.status = ?1 order by pr.dateCreate DESC")
    Page<Product> selectByStatus(int status, Pageable pageable);

    @Query("select pr from Product pr where pr.status = ?1")
    List<Product> findByStatus(int status);

    @Transactional
    @Modifying
    @Query("update Product set status = 1 where id = ?1")
    void delete(Long id);

    @Query("select pr.code from Product pr")
    List<String> selectCode();
    //Hê hê hê hê

    @Query(value = "select * from Product where Name IN ('iphone X', 'iphone Xs', 'iphone Xsmax') and Status = 0", nativeQuery = true)
    List<Product> getProductIphoneX();

    @Query(value = "select * from Product where Name IN ('iphone 11', 'iphone 11 Pro', 'iphone 11 Pro Max') and Status = 0", nativeQuery = true)
    List<Product> getProductIphone11();

    @Query(value = "select * from Product where Name IN ('iphone 12', 'iphone 12 Pro', 'iphone 12 Pro Max') and Status = 0", nativeQuery = true)
    List<Product> getProductIphone12();

    @Query(value = "select * from Product where Name IN ('iphone 13', 'iphone 13 plus', 'iphone 13 pro', 'iphone 13 pro max') and Status = 0", nativeQuery = true)
    List<Product> getProductIphone13();

    @Query(value = "select * from Product where Name IN ('iphone 14', 'iphone 14 plus', 'iphone 14 pro', 'iphone 14 pro max') and Status = 0", nativeQuery = true)
    List<Product> getProductIphone14();

    @Query(value = "select * from Product where Status = 0 and Price < 10000000", nativeQuery = true)
    Page<Product> getProductByPriceLess10000000(Pageable pageable);

    @Query(value = "select * from Product where Status = 0 and Price >= 10000000 and Price <= 20000000", nativeQuery = true)
    Page<Product> getProductByPriceFrom10000000To20000000(Pageable pageable);

    //    @Query(value = "select ProductDetail.Id, ProductDetail.Code, ProductDetail.Name, ProductDetail.DateCreate, ProductDetail.DateUpdate, ProductDetail.PersonCreate, ProductDetail.PersonUpdate, ProductDetail.Describe, ProductDetail.Status, ProductDetail.Id_Capacity, ProductDetail.Id_Color, ProductDetail.Id_Manufacture, ProductDetail.Id_Category, ProductDetail.Id_Battery, ProductDetail.Id_Chip, ProductDetail.Id_Ram, ProductDetail.Id_Screen, ProductDetail.Id_Size, ProductDetail.Id_Product, ProductDetail.images, ProductDetail.Price, ProductDetail.Quantity from ProductDetail join Product on ProductDetail.Id_Product = Product.Id where ProductDetail.Status = 0 and Product.Price > 20000000", nativeQuery = true)
    @Query(value = "select * from Product where Status = 0 and Price > 20000000", nativeQuery = true)
    Page<Product> getProductByPriceBigger20000000(Pageable pageable);
}
