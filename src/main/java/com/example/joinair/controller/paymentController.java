package com.example.joinair.controller;

import com.example.joinair.service.paymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/payment")
public class paymentController {

    @Autowired
    private paymentService paymentService;

    @PostMapping("/paymentpage")
    public ModelAndView showPaymentPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("paymentpage");
        mv.setStatus(HttpStatus.OK);
        return mv;
    }
    @GetMapping("/Deposit") //무통장입금 메소드
    public ModelAndView showBankTransferForm() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("Deposit"); // 무통장 입금 정보 입력 폼을 보여줄 HTML 템플릿
        mv.setStatus(HttpStatus.OK);
        return mv;
    }
}
