package com.example.joinair.mapper;

import com.example.joinair.dto.ORDERS;
import com.example.joinair.dto.ORDER_DETAIL;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderMapper {
    void insertOrderDetail(ORDER_DETAIL orderDetail);
    void insertOrder(ORDERS order);
    ORDERS getOrder(@Param("orderId") int orderId);

    List<ORDER_DETAIL> getOrderDetails(@Param("orderId") int orderId);
}