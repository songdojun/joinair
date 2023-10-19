package com.example.joinair.controller;

import com.example.joinair.service.orderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class orderController {
    @Autowired
    private orderService orderService;

    @GetMapping("/orderMain") // 주문창
    public String showOrderForm() {
        return "orderMain"; // 머스태치 템플릿의 이름
    }

}