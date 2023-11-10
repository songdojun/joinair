package com.example.joinair.controller;

import com.example.joinair.dto.ORDER_DETAIL;
import com.example.joinair.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {


    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public String placeOrder(HttpSession session) {
        // 세션에서 카트 정보를 가져와서 ORDER_DETAIL 객체로 변환하여 DB에 저장
        orderService.placeOrderFromCart(session);

        // 로그 추가
        logger.info("Order placed successfully!");

        // 여기서 추가적인 로직을 수행하거나 리다이렉트 없이 그냥 종료
        // ...

        // 리다이렉트 없이 현재 페이지에서 유지
        return null;
    }


}