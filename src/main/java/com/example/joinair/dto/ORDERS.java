package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ORDERS {
        public int getOrders_Num() {
                return Orders_Num;
        }

        public void setOrders_Num(int orders_Num) {
                Orders_Num = orders_Num;
        }

        public String getUser_Id() {
                return User_Id;
        }

        public void setUser_Id(String user_Id) {
                User_Id = user_Id;
        }

        public int getOrders_Total_Price() {
                return Orders_Total_Price;
        }

        public void setOrders_Total_Price(int orders_Total_Price) {
                Orders_Total_Price = orders_Total_Price;
        }

        public int getOrders_Totalweight() {
                return Orders_Totalweight;
        }

        public void setOrders_Totalweight(int orders_Totalweight) {
                Orders_Totalweight = orders_Totalweight;
        }

        public String getPay_Method() {
                return Pay_Method;
        }

        public void setPay_Method(String pay_Method) {
                Pay_Method = pay_Method;
        }

        public String getOrders_Pname() {
                return Orders_Pname;
        }

        public void setOrders_Pname(String orders_Pname) {
                Orders_Pname = orders_Pname;
        }

        public String getOrders_Dest() {
                return Orders_Dest;
        }

        public void setOrders_Dest(String orders_Dest) {
                Orders_Dest = orders_Dest;
        }

        public String getOrders_Phone() {
                return Orders_Phone;
        }

        public void setOrders_Phone(String orders_Phone) {
                Orders_Phone = orders_Phone;
        }

        public int getDel_No() {
                return Del_No;
        }

        public void setDel_No(int del_No) {
                Del_No = del_No;
        }

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
