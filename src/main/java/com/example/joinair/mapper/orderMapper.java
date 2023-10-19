package com.example.joinair.mapper;

import com.example.joinair.dto.ORDERS;

import java.util.List;

public interface orderMapper {
    ORDERS getOrderById(int Orders_Num);

    List<ORDERS> getAllOrders();

    void addOrder(ORDERS orders);

    void updateOrder(ORDERS orders);

    void deleteOrder(int Orders_Num);
}
