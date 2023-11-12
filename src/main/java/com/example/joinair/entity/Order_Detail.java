package com.example.joinair.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "ORDER_DETAIL")
public class Order_Detail {

    @Id
    private Integer OD_Num; // 주문상세번호

    private Integer Orders_Num; // 주문번호
    private String OD_Request; // 배송요청사항
    private Date OD_Date; // 주문날짜
    private Integer OD_Qua; // 수량
    private Integer OD_Total_Price; // 총 소계금액
    private Integer OD_Totalweight; // 총 무게
    private String OD_Location; // 위치
    private Date OD_Reqtime; // 요청배달시간



}
