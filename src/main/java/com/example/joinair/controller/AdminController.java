package com.example.joinair.controller;

import com.example.joinair.dto.SALES;
import com.example.joinair.service.SalesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final SalesService salesService;

    @Autowired
    public AdminController(SalesService salesService) {
        this.salesService = salesService;
    }

    @GetMapping("/sales/report")
    public String showSalesReportPage() {
        return "salesReport";
    }

    @GetMapping("/sales/reportData")
    public String generateSalesReport(
            @RequestParam("reportType") String reportType,
            @RequestParam(value = "year", required = false) Integer year,
            @RequestParam(value = "month", required = false) Integer month,
            @RequestParam(value = "user", required = false) String user,
            Model model) {
        List<SALES> salesData = new ArrayList<>(); // 결과 목록 초기화

        if ("daily".equals(reportType)) {
            salesData = salesService.getDailySales();
        } else if ("monthly".equals(reportType)) {
            if (year != null && month != null) {
                // 필터링된 월별 데이터를 가져오는 서비스 메소드 호출
                salesData = salesService.getMonthlySalesByYearAndMonth(year, month);
            } else {
                // 모든 월별 데이터 가져오기
                salesData = salesService.getMonthlySales();
            }
        } else if ("yearly".equals(reportType)) {
            if (year != null) {
                // 연도별 데이터 가져오는 서비스 메소드 호출
                salesData = salesService.getYearlySales(year);
            }
        } else if ("custom".equals(reportType) && user != null) {
            // "user" 값을 사용하여 사용자 지정 필터를 적용하여 데이터 가져오는 서비스 메소드 호출
            salesData = salesService.getCustomSales(user);
        }

        model.addAttribute("salesData", salesData);
        return "salesReport";
    }

}




