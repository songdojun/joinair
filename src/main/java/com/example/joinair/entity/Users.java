package com.example.joinair.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


//User entity
@Entity
@Table(name = "USERS") // USERS 테이블과 매핑
public class Users {

    @Id
    private String User_Id;
    private String User_Name;
    private String User_Email;
    private String User_Password;
    private String User_Address;
    private String User_Phone;
    private int User_Mileage;
    private String User_DetailAddress; // 추가: 상세 주소 필드
    // 다른 필드, Getter, Setter 및 설정
}
