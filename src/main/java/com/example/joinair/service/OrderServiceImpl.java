package com.example.joinair.service;

import com.example.joinair.dto.ORDER_DETAIL;
import com.example.joinair.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderMapper orderMapper;

    @Autowired
    public OrderServiceImpl(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Override
    public void placeOrder(List<ORDER_DETAIL> orderDetails) {
        // 주문 상세 정보를 데이터베이스에 저장
        for (ORDER_DETAIL orderDetail : orderDetails) {
            orderMapper.insertOrderDetail(orderDetail);
        }
    }
}