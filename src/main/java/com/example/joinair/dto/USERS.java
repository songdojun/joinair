package com.example.joinair.dto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class USERS {
    private String User_Id;
    private String User_Name;
    private String User_Email;
    private String User_Password;
    private String User_Address;
    private String User_Phone;
    private int User_Mileage;
    private String User_DetailAddress; // 추가: 상세 주소 필드

    private String User_Mode;


}

