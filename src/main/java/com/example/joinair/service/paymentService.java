package com.example.joinair.service;

import com.example.joinair.dto.PAYMENT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class paymentService {
    @Autowired
    private com.example.joinair.mapper.paymentMapper paymentMapper;

    public List<PAYMENT> paymentList() {
        return paymentMapper.paymentList();
    }
}
