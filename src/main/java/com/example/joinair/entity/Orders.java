package com.example.joinair.entity;

import com.example.joinair.entity.Product;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ORDERS")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Orders_Num;         // 주문번호

    private String User_Id;          // UserID
    private Integer Pro_Code;           // 상품코드
    private Integer Orders_Total_Price; // 총 주문금액
    private String Orders_State;     // 상태
    private Integer Orders_Total;    // 총주문수량

    @ManyToOne
    @JoinColumn(name = "Pro_Code", referencedColumnName = "Pro_Code", insertable = false, updatable = false)
    private Product product;

    public Integer getOrders_Num() {
        return Orders_Num;
    }

    public void setOrders_Num(Integer orders_Num) {
        Orders_Num = orders_Num;
    }

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public Integer getPro_Code() {
        return Pro_Code;
    }

    public void setPro_Code(Integer pro_Code) {
        Pro_Code = pro_Code;
    }

    public Integer getOrders_Total_Price() {
        return Orders_Total_Price;
    }

    public void setOrders_Total_Price(Integer orders_Total_Price) {
        Orders_Total_Price = orders_Total_Price;
    }

    public String getOrders_State() {
        return Orders_State;
    }

    public void setOrders_State(String orders_State) {
        Orders_State = orders_State;
    }

    public Integer getOrders_Total() {
        return Orders_Total;
    }

    public void setOrders_Total(Integer orders_Total) {
        Orders_Total = orders_Total;
    }
}
