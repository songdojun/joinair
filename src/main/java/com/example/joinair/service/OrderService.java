package com.example.joinair.service;


import com.example.joinair.dto.ORDERS;
import com.example.joinair.dto.ORDER_DETAIL;
import jakarta.servlet.http.HttpSession;
import org.apache.ibatis.annotations.Param;


import java.util.List;

public interface OrderService {
    void placeOrderFromCart(HttpSession session, String total, String orderPname, String orderDest1, String orderPhone1);
    ORDERS getOrder(@Param("orderId") int orderId);

    List<ORDER_DETAIL> getOrderDetails(@Param("orderId") int orderId);

}
