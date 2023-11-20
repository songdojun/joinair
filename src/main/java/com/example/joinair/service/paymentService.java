package com.example.joinair.service;

import com.example.joinair.dto.ORDER_DETAIL;
import com.example.joinair.dto.PAYMENT;
import com.example.joinair.mapper.paymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class paymentService {

    @Autowired
    private paymentMapper paymentMapper;

    public int generateNextOrderNumber() {
        return paymentMapper.getNextOrderNumber();
    }

    public int generateNextPaymentNumber() {
        return paymentMapper.getNextPaymentNumber();
    }

    public void savePayment(List<ORDER_DETAIL> orderDetails, int ordersNum) {
        int payNo = generateNextPaymentNumber();
        int totalPrice = getTotalPrice(orderDetails);

        PAYMENT payment = new PAYMENT();
        payment.setPAY_NO(payNo);
        payment.setORDERS_NUM(ordersNum); // 주문번호 설정
        payment.setPAY_PRICE(totalPrice);
        payment.setPAY_METHOD("카드");
        payment.setPAY_STATUS("완료");

        paymentMapper.savePayment(payment);
    }

    public int getTotalPrice(List<ORDER_DETAIL> orderDetails) {
        int totalPrice = 0;
        for (ORDER_DETAIL detail : orderDetails) {
            totalPrice += detail.getOD_Pro_Price() * detail.getOD_Qua();
        }
        return totalPrice;
    }
}
