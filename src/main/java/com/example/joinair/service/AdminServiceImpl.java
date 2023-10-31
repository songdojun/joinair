package com.example.joinair.service;

import com.example.joinair.dto.SALES;
import com.example.joinair.mapper.AdminMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final AdminMapper adminMapper;

    @Autowired
    public AdminServiceImpl(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Override
    public List<SALES> getDailySales() {
        return adminMapper.getDailySales();
    }


    @Override
    public List<SALES> getMonthlySales() {
        return adminMapper.getMonthlySales();
    }


    @Override
    public List<SALES> getYearlySales(int year) {
        return adminMapper.getYearlySales(year);
    }
    @Override
    public List<SALES> getCustomSales(@Param("searchInput") String user) {
        return adminMapper.getCustomSales(user);
    }
    @Override
    public List<SALES> getMonthlySalesByYearAndMonth(@Param("year") Integer year, @Param("month") Integer month) {
        return adminMapper.getMonthlySalesByYearAndMonth(year, month);
    }
    @Override
    public List<SALES> searchUsers(String searchCategory, String searchInput) {
        return adminMapper.searchUsers(searchCategory, searchInput);
    }


}
