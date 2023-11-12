package com.example.joinair.repository;

import com.example.joinair.entity.Notice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Integer> {
                                            /*
                                            JpaRepository<Notice, Integer>:
                                            Notice 엔티티와 관련된 데이터베이스 작업을 수행하기 위해 Spring Data JPA의 JpaRepository를 확장하고 있습니다.
                                            Notice는 공지사항 엔티티를 나타냅니다.
                                            Integer는 엔티티의 기본 키 타입을 나타냅니다.
                                             */
//    커스텀 쿼리 활용  => 흔히 쓰는 방식으로 하면 JPA가 Not_No인식못함   , 페이징처리 쿼리문
    @Query("SELECT n FROM Notice n ORDER BY n.Not_No DESC")
    Page<Notice> findAllOrderedByNotnoWithPagination(Pageable pageable);

    @Query("SELECT n FROM Notice n WHERE n.Not_Title LIKE %:searchKeyword% ORDER BY n.Not_No DESC")
    Page<Notice> findNoticesByTitleContaining(@Param("searchKeyword") String searchKeyword, Pageable pageable);







}
