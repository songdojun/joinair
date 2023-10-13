package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ORDER_DETAIL {
    private Long OD_Num; // 주문상세번호
    private Long Orders_Num; // 주문번호
    private String OD_Request; // 배송요청사항
    private Date OD_Date; // 주문날짜
    private int OD_Qua; // 수량
    private double OD_Total_Price; // 총 소계금액
    private double OD_Totalweight; // 총 무게
    private String OD_Location; // 위치
    private Date OD_Reqtime; // 요청배달시간
}
