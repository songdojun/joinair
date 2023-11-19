package com.example.joinair.service;


import com.example.joinair.dto.ORDERS;
import com.example.joinair.dto.ORDER_DETAIL;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface OrderService {
    void placeOrderFromCart(HttpSession session, String total);
    ORDERS getOrder(@Param("orderId") int orderId);

    List<ORDER_DETAIL> getOrderDetails(@Param("orderId") int orderId);
    void savePaymentInformation(int orderId, int totalPrice);
}

