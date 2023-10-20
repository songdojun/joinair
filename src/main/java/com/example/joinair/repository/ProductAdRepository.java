package com.example.joinair.repository;


import com.example.joinair.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAdRepository extends JpaRepository<Product, Integer> {

    @Query("SELECT p FROM Product p ORDER BY p.Pro_Code DESC")
    Page<Product> findAllOrderedByProCodeWithPagination(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.Pro_Name LIKE %?1% ORDER BY p.Pro_Code DESC")
    Page<Product> findProductsByProNameContaining(String searchKeyword, Pageable pageable);





}
