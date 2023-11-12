package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ORDERS {
        private int Orders_Num;         // 주문번호 (1씩 자동증가 생성하는 시퀀스 만들어줘야함 DB)

        private int Orders_Total_Price; // 총 주문금액 (session에 담긴 Subtotal의 합)

        private int Orders_Totalweight; // 총 무게(session에 담긴 weight의 합)



        private String User_Id;          // UserID(X)

        private String Orders_State;     // 상태(X)





}
