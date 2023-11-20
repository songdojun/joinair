package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ORDER_DETAIL {
    private int OD_Num; // 주문상세번호    (ORDER_DETAIL의 기본키, 자동1증가생성 시퀀스 만들어줘야 하고 DB에 적용해줘야함 . )
    private int Orders_Num; // 주문번호    (x)
    private String OD_Pro_Name; // 주문한 상품명    ( DB컬럼 생성 필요함)
    private int OD_Pro_Price; // 주문한 상품명의 단가  (DB컬럼 생성 필요함)
    private int OD_Qua; // 수량             (session에 담긴 Quantity)
    private int OD_Totalweight; // 총 무게     (session에 담긴 weight)
    private int OD_Total_Price; // 총 소계금액  (session에 담긴 Subtotal)





    private String OD_Location; // 위치             (x)
    private Date OD_Reqtime; // 요청배달시간         (X)
    private String OD_Request; // 배송요청사항   (X)
    private Date OD_Date; // 주문날짜          (X)
}
