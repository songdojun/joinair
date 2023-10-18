package com.example.joinair.mapper;

import com.example.joinair.dto.PAYMENT;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface paymentMapper {

    List<PAYMENT> paymentList();
}
