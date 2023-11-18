package com.example.joinair.mapper;

import com.example.joinair.dto.PAYMENT;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface paymentMapper {

    int getNextOrderNumber();

    int getNextPaymentNumber();

    void savePayment(PAYMENT payment);
}
