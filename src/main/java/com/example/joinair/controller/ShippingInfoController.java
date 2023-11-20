package com.example.joinair.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ShippingInfoController {

    @GetMapping("/shipping/info")
    public String shippingInfo() {

        return "shippingInfo";
    }
}
