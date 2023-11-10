package com.example.joinair.service;


import com.example.joinair.dto.ORDER_DETAIL;


import java.util.List;

public interface OrderService {
    void placeOrder(List<ORDER_DETAIL> orderDetails);
}
