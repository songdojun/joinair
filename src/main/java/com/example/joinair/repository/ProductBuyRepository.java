package com.example.joinair.repository;

import com.example.joinair.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductBuyRepository extends JpaRepository<Product, Integer>{

    @Query("SELECT p FROM Product p ORDER BY p.Pro_Code DESC")
    Page<Product> findAllOrderedByProCodeWithPagination(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.Pro_Name LIKE %?1% ORDER BY p.Pro_Code DESC")
    Page<Product> findProductsByProNameContaining(String searchKeyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.Pro_Code = ?1 ORDER BY p.Pro_Code DESC")
    Page<Product> findProductsByProCode(Integer searchKeyword, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.Pro_Name LIKE %?1% OR p.Pro_Code = ?1 ORDER BY p.Pro_Code DESC")
    Page<Product> findProductsByProNameOrProCode(String searchKeyword, Pageable pageable);

    // 수정: "Cate_Name" 검색을 위한 쿼리 추가
    @Query("SELECT p FROM Product p WHERE p.category.Cate_Name LIKE %?1% ORDER BY p.Pro_Code DESC")
    Page<Product> findProductsByCateNameContaining(String searchKeyword, Pageable pageable);



}