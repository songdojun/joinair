package com.example.joinair.repository;


import com.example.joinair.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    //커스텀 쿼리 활용  => 흔히 쓰는 방식으로 하면 JPA가 Rev_No인식못함   , 페이징처리 쿼리문
    @Query("SELECT r FROM Review r ORDER BY r.Rev_No DESC")
    Page<Review> findAllOrderedByRevNoWithPagination(Pageable pageable);

    @Query("SELECT r FROM Review r WHERE r.Rev_Title LIKE %:searchKeyword% ORDER BY r.Rev_No DESC")
    Page<Review> findReviewsByTitleContaining(@Param("searchKeyword") String searchKeyword, Pageable pageable);






}
