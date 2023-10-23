package com.example.joinair.service;

import com.example.joinair.dto.SALES;
import com.example.joinair.mapper.SalesMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesServiceImpl implements SalesService {

    private final SalesMapper salesMapper;

    @Autowired
    public SalesServiceImpl(SalesMapper salesMapper) {
        this.salesMapper = salesMapper;
    }

    @Override
    public List<SALES> getDailySales() {
        return salesMapper.getDailySales();
    }


    @Override
    public List<SALES> getMonthlySales() {
        return salesMapper.getMonthlySales();
    }


    @Override
    public List<SALES> getYearlySales(int year) {
        return salesMapper.getYearlySales(year);
    }
    @Override
    public List<SALES> getCustomSales(@Param("user") String user) {
        return salesMapper.getCustomSales(user);
    }
    @Override
    public List<SALES> getMonthlySalesByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month) {
        return salesMapper.getMonthlySalesByYearAndMonth(year, month);
    }
}
