package com.example.joinair.service;

import com.example.joinair.dto.ORDERS;
import com.example.joinair.mapper.orderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class orderService {
    @Autowired
    private orderMapper orderMapper;

    public ORDERS getOrderById(int Orders_Num) {
        return orderMapper.getOrderById(Orders_Num);
    }

    public List<ORDERS> getAllOrders() {
        return orderMapper.getAllOrders();
    }

    public void addOrder(ORDERS orders) {
        orderMapper.addOrder(orders);
    }

    public void updateOrder(ORDERS orders) {
        orderMapper.updateOrder(orders);
    }

    public void deleteOrder(int Orders_Num) {
        orderMapper.deleteOrder(Orders_Num);
    }
}
