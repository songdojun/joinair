package com.example.joinair.controller;

import com.example.joinair.dto.SALES;
import com.example.joinair.dto.USERS;
import com.example.joinair.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/sales/report")
    public String showSalesReportPage() {
        return "SalesReport";
    }

    @PostMapping("/sales/reportData")
    public String generateSalesReport(
            @RequestParam("reportType") String reportType,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "searchInput", required = false) String user,
            Model model) {
        List<SALES> salesData = new ArrayList<>(); // 결과 목록 초기화

        if ("daily".equals(reportType)) {
            salesData = adminService.getDailySales();
        } else if ("monthly".equals(reportType)) {
            if (year != null && month != null) {
                // 필터링된 월별 데이터를 가져오는 서비스 메소드 호출
                salesData = adminService.getMonthlySalesByYearAndMonth(year, month);
            } else {
                // 모든 월별 데이터 가져오기
                salesData = adminService.getMonthlySales();
            }
        } else if ("yearly".equals(reportType)) {
            if (year != null) {
                // 연도별 데이터 가져오는 서비스 메소드 호출
                salesData = adminService.getYearlySales(year);
            }
        } else if ("custom".equals(reportType) && user != null) {
            // "user" 값을 사용하여 사용자 지정 필터를 적용하여 데이터 가져오는 서비스 메소드 호출
            salesData = adminService.getCustomSales(user);
        }

        model.addAttribute("salesData", salesData);
        return "SalesReport";
    }


    @GetMapping("/searchUsers")
    public String searchUsers(
            @RequestParam("searchCategory") String searchCategory,
            @RequestParam("searchInput") String searchInput,
            Model model) {
        List<USERS> searchResults = adminService.searchUsers(searchCategory, searchInput);
        model.addAttribute("users", searchResults);
//        model.addAttribute("isAdmin", true);
        // 현재 사용자가 관리자임을 나타내는 플래그


        return "adminEditUserList"; // 사용자 검색 결과를 보여줄 뷰 페이지의 이름으로 교체하세요.
    }
}



