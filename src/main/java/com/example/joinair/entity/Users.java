package com.example.joinair.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


//User entity
@Entity
@Table(name = "USERS") // USERS 테이블과 매핑
public class Users {

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getUser_Email() {
        return User_Email;
    }

    public void setUser_Email(String user_Email) {
        User_Email = user_Email;
    }

    public String getUser_Password() {
        return User_Password;
    }

    public void setUser_Password(String user_Password) {
        User_Password = user_Password;
    }

    public String getUser_Address() {
        return User_Address;
    }

    public void setUser_Address(String user_Address) {
        User_Address = user_Address;
    }

    public String getUser_Phone() {
        return User_Phone;
    }

    public void setUser_Phone(String user_Phone) {
        User_Phone = user_Phone;
    }

    public int getUser_Mileage() {
        return User_Mileage;
    }

    public void setUser_Mileage(int user_Mileage) {
        User_Mileage = user_Mileage;
    }

    public String getUser_DetailAddress() {
        return User_DetailAddress;
    }

    public void setUser_DetailAddress(String user_DetailAddress) {
        User_DetailAddress = user_DetailAddress;
    }

    @Id
    private String User_Id;
    private String User_Name;
    private String User_Email;
    private String User_Password;
    private String User_Address;
    private String User_Phone;
    private int User_Mileage;
    private String User_DetailAddress; // 추가: 상세 주소 필드
    private String User_Postcode;
    private String User_Mode;

    public String getUser_Mode() {
        return User_Mode;
    }

    public void setUser_Mode(String user_Mode) {
        User_Mode = user_Mode;
    }
// 다른 필드, Getter, Setter 및 설정
}
