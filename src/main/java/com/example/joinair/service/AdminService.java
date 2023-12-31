package com.example.joinair.service;

import com.example.joinair.dto.SALES;
import com.example.joinair.dto.USERS;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminService {
    List<SALES> getDailySales();
    List<SALES> getMonthlySales();
    //List<SALES> getYearlySales(int year);
    List<SALES> getYearlySales(@Param("year") int year);
    List<SALES> getCustomSales(@Param("searchInput") String user);
        // 월별 데이터를 연도 및 월로 필터링
    List<SALES> getMonthlySalesByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);
    List<USERS> searchUsers(String searchCategory, String searchInput);

    // 모든 사용자 가져오기
    }

