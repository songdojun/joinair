package com.example.joinair.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ORDER_DETAIL {
    public int getOD_Num() {
        return OD_Num;
    }

    public void setOD_Num(int OD_Num) {
        this.OD_Num = OD_Num;
    }

    public int getOrders_Num() {
        return Orders_Num;
    }

    public void setOrders_Num(int orders_Num) {
        Orders_Num = orders_Num;
    }

    public String getOD_Pro_Name() {
        return OD_Pro_Name;
    }

    public void setOD_Pro_Name(String OD_Pro_Name) {
        this.OD_Pro_Name = OD_Pro_Name;
    }

    public int getOD_Pro_Price() {
        return OD_Pro_Price;
    }

    public void setOD_Pro_Price(int OD_Pro_Price) {
        this.OD_Pro_Price = OD_Pro_Price;
    }

    public int getOD_Qua() {
        return OD_Qua;
    }

    public void setOD_Qua(int OD_Qua) {
        this.OD_Qua = OD_Qua;
    }

    public int getOD_Totalweight() {
        return OD_Totalweight;
    }

    public void setOD_Totalweight(int OD_Totalweight) {
        this.OD_Totalweight = OD_Totalweight;
    }

    public int getOD_Total_Price() {
        return OD_Total_Price;
    }

    public void setOD_Total_Price(int OD_Total_Price) {
        this.OD_Total_Price = OD_Total_Price;
    }

    public String getOD_Location() {
        return OD_Location;
    }

    public void setOD_Location(String OD_Location) {
        this.OD_Location = OD_Location;
    }

    public Date getOD_Reqtime() {
        return OD_Reqtime;
    }

    public void setOD_Reqtime(Date OD_Reqtime) {
        this.OD_Reqtime = OD_Reqtime;
    }

    public String getOD_Request() {
        return OD_Request;
    }

    public void setOD_Request(String OD_Request) {
        this.OD_Request = OD_Request;
    }

    public Date getOD_Date() {
        return OD_Date;
    }

    public void setOD_Date(Date OD_Date) {
        this.OD_Date = OD_Date;
    }

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
