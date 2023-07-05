package com.example.smartphone_store.repository;

import com.example.smartphone_store.entity.Battery;
import com.example.smartphone_store.entity.ProductDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDetailRepository extends JpaRepository<ProductDetail, Long> {
    @Query(value = "SELECT Id, Code, Name, DateCreate, Describe , DateUpdate, PersonCreate, PersonUpdate, Status, Id_Capacity, Id_Color, Id_Manufacture, \n" +
            " Id_Category, Id_Battery, Id_Chip, Id_Ram, Id_Screen, Id_Product, images, Quantity, Price FROM ProductDetail WHERE Status = 0 ORDER BY DateCreate DESC, Id DESC", nativeQuery = true)
    Page<ProductDetail> getPageProductDetails(Pageable pageable);

    @Query(value = "SELECT Id, Code, Name, DateCreate, Describe , DateUpdate, PersonCreate, PersonUpdate, " +
            " Status, Id_Capacity, Id_Color, Id_Manufacture, Id_Category, Id_Battery, Id_Chip, Id_Ram, Id_Screen, " +
            " Id_Product FROM ProductDetail WHERE Status = 1 ", nativeQuery = true)
    Page<ProductDetail> getReturnProductDetails(Pageable pageable);

    @Query(value = "SELECT * FROM ProductDetail WHERE ((Code LIKE %?1% OR Name LIKE %?1% OR " +
            " DateCreate LIKE %?1% OR DateUpdate LIKE %?1% OR PersonCreate LIKE %?1% OR" +
            " PersonUpdate LIKE %?1%) AND Status = 0)", nativeQuery = true)
    Page<ProductDetail> viewSeachAllProductDetails(String search, Pageable pageable);

    ProductDetail findByCode(String code);

    @Query(value = "SELECT Top 10 Id, Code, Name, DateCreate, Describe , DateUpdate, PersonCreate, PersonUpdate, Status, Id_Capacity, Id_Color, Id_Manufacture, Id_Category, Id_Battery, Id_Chip, Id_Ram, Id_Screen, Id_Product, images, Quantity, Price FROM ProductDetail WHERE Status = 0", nativeQuery = true)
    List<ProductDetail> getTop10NewProductDetails();

    @Query(value = "SELECT Id, Code, Name, DateCreate, Describe , DateUpdate, PersonCreate, PersonUpdate, Status, Id_Capacity, Id_Color, Id_Manufacture, Id_Category, Id_Battery, Id_Chip, Id_Ram, Id_Screen, Id_Product, images, Quantity, Price FROM ProductDetail WHERE Status = 0", nativeQuery = true)
    List<ProductDetail> getBestSelling();

    @Query(value = "SELECT Id, Code, Name, DateCreate, Describe , DateUpdate, PersonCreate, PersonUpdate, Status, Id_Capacity, Id_Color, Id_Manufacture, Id_Category, Id_Battery, Id_Chip, Id_Ram, Id_Screen, Id_Product, images, Quantity, Price \n" +
            "FROM ProductDetail WHERE\n" +
            "Status = 0 ", nativeQuery = true)
    List<ProductDetail> getLineProductDetail();

    Page<ProductDetail> findProductDetailByStatusAndProductId(Integer status, Long id, Pageable pageable);

    @Query(value = "select * from ProductDetail where Status = 0 and Price < 10000000", nativeQuery = true)
    Page<ProductDetail> getProductDetailByPriceLess10000000(Pageable pageable);

    @Query(value = "select * from ProductDetail where Status = 0 and Price >= 10000000 and Price <= 20000000", nativeQuery = true)
    Page<ProductDetail> getProductDetailByPriceFrom10000000To20000000(Pageable pageable);

//    @Query(value = "select ProductDetail.Id, ProductDetail.Code, ProductDetail.Name, ProductDetail.DateCreate, ProductDetail.DateUpdate, ProductDetail.PersonCreate, ProductDetail.PersonUpdate, ProductDetail.Describe, ProductDetail.Status, ProductDetail.Id_Capacity, ProductDetail.Id_Color, ProductDetail.Id_Manufacture, ProductDetail.Id_Category, ProductDetail.Id_Battery, ProductDetail.Id_Chip, ProductDetail.Id_Ram, ProductDetail.Id_Screen, ProductDetail.Id_Size, ProductDetail.Id_Product, ProductDetail.images, ProductDetail.Price, ProductDetail.Quantity from ProductDetail join Product on ProductDetail.Id_Product = Product.Id where ProductDetail.Status = 0 and Product.Price > 20000000", nativeQuery = true)
    @Query(value = "select * from ProductDetail where Status = 0 and Price > 20000000", nativeQuery = true)
    Page<ProductDetail> getProductDetailByPriceBigger20000000(Pageable pageable);
}
