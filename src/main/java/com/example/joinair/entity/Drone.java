package com.example.joinair.entity;

import jakarta.persistence.*;


@Entity
public class Drone {

    public Integer getD_Code() {
        return D_Code;
    }

    public void setD_Code(Integer d_Code) {
        D_Code = d_Code;
    }

    public Integer getD_Payload() {
        return D_Payload;
    }

    public void setD_Payload(Integer d_Payload) {
        D_Payload = d_Payload;
    }

    public Integer getD_Count() {
        return D_Count;
    }

    public void setD_Count(Integer d_Count) {
        D_Count = d_Count;
    }

    public Integer getD_Size() {
        return D_Size;
    }

    public void setD_Size(Integer d_Size) {
        D_Size = d_Size;
    }

    public Integer getD_Weight() {
        return D_Weight;
    }

    public void setD_Weight(Integer d_Weight) {
        D_Weight = d_Weight;
    }

    public Integer getD_Speed() {
        return D_Speed;
    }

    public void setD_Speed(Integer d_Speed) {
        D_Speed = d_Speed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DRONE_SEQ")
    @SequenceGenerator(name = "DRONE_SEQ", sequenceName = "DRONE_SEQ", allocationSize = 1)
    private Integer D_Code;      // 드론식별코드
    private Integer D_Payload;   // 페이로드 용량
    private Integer D_Count;     // 수량
    private Integer D_Size;      // 크기
    private Integer D_Weight;    // 무게
    private Integer D_Speed;     // 속도

    // 생성자, 게터, 세터, 기타 메서드 등을 추가할 수 있습니다.

}
