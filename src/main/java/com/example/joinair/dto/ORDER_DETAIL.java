package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ORDER_DETAIL {
    private Long odNum; // 주문상세번호
    private Long ordersNum; // 주문번호
    private String odRequest; // 배송요청사항
    private Date odDate; // 주문날짜
    private int odQua; // 수량
    private double odTotalPrice; // 총 소계금액
    private double odTotalWeight; // 총 무게
    private String odLocation; // 위치
    private Date odReqTime; // 요청배달시간
}
