package com.example.joinair.controller;

import com.example.joinair.dto.ORDER_DETAIL;
import com.example.joinair.dto.PAYMENT;
import com.example.joinair.service.paymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/payment")
public class paymentController {

    @Autowired
    private paymentService paymentService;

    @PostMapping("/completePayment")
    public ModelAndView completePayment(@ModelAttribute("orderDetails") List<ORDER_DETAIL> orderDetails) {
        // 로깅을 통해 전송된 데이터 확인
        System.out.println("Received order details: " + orderDetails);

        // 주문번호 생성
        int ordersNum = paymentService.generateNextOrderNumber();

        // 결제 서비스 호출
        paymentService.savePayment(orderDetails, ordersNum);

        ModelAndView modelAndView = new ModelAndView("redirect:/payment/paymentpage");
        modelAndView.addObject("orderDetails", orderDetails); // 모델에 데이터 추가

        return modelAndView;
    }


}
