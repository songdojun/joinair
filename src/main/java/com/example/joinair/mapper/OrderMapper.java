package com.example.joinair.mapper;

import com.example.joinair.dto.ORDERS;
import com.example.joinair.dto.ORDER_DETAIL;

public interface OrderMapper {
    void insertOrderDetail(ORDER_DETAIL orderDetail);
    void insertOrder(ORDERS order);
}