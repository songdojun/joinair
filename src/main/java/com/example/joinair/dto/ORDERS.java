package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ORDERS {
        private int Orders_Num;         // 주문번호 (1씩 자동증가 생성하는 시퀀스 만들어줘야함 DB)

        private String User_Id;          // UserID(생성함)

        private int Orders_Total_Price; // 총 주문금액 (session에 담긴 Subtotal의 합)

        private int Orders_Totalweight; // 총 무게(session에 담긴 weight의 합)

        private String Pay_Method; //결제 수단

        private String Orders_Pname;

        private String Orders_Dest;

        private String Orders_Phone;

        private int Del_No; //배송 코드






}
