package com.example.joinair.controller;

import com.example.joinair.dto.ORDER_DETAIL;
import com.example.joinair.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/place")
    public String placeOrder(HttpSession session) {
        // 세션에서 카트 정보를 가져와서 ORDER_DETAIL 객체로 변환하여 DB에 저장
        orderService.placeOrder((List<ORDER_DETAIL>) session.getAttribute("cart"));
        // 주문이 완료되면 세션의 카트를 비워줄 수도 있습니다. (선택적으로)
        // session.removeAttribute("cart");

        // 여기서 추가적인 로직을 수행하거나 리다이렉트 없이 그냥 종료
        // ...

        // 리다이렉트 없이 현재 페이지에서 유지
        return null;
    }

}