package com.example.joinair.repository;


import com.example.joinair.entity.Product;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAdRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p ORDER BY p.Pro_Code DESC")
    Page<Product> findAllOrderedByProCodeWithPagination(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.Pro_Name LIKE %:searchkeyword% ORDER BY p.Pro_Code DESC")
    Page<Product> findProductsByTitleContaining(@Param("searchKeyword") String searchKeyword, Pageable pageable);

}
