package com.example.joinair.controller;

import com.example.joinair.service.paymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/payment")
public class paymentController {

    @Autowired
    private paymentService paymentService;

    @PostMapping("/paymenttest")
    public ModelAndView showPaymentPage() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("paymenttest");
        mv.setStatus(HttpStatus.OK);
        return mv;
    }
}
