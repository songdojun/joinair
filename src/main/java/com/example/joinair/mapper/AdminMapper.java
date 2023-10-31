package com.example.joinair.mapper;

import com.example.joinair.dto.SALES;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface AdminMapper {
    List<SALES> getDailySales();

    List<SALES> getMonthlySales();

    List<SALES> getYearlySales(@Param("year") int year);

    List<SALES> getCustomSales(@Param("searchInput") String user);

    List<SALES> getMonthlySalesByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month);

    List<SALES> searchUsers(@Param("searchCategory") String searchCategory, @Param("searchInput") String searchInput);

}

