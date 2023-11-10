package com.example.joinair.service;


import com.example.joinair.dto.ORDER_DETAIL;
import jakarta.servlet.http.HttpSession;


import java.util.List;

public interface OrderService {
    void placeOrderFromCart(HttpSession session);
}
