package com.example.joinair.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class USERS implements UserDetails {
    @Id
    private String User_Id;
    private String User_Name;
    private String User_Email;
    private String User_Password;
    private String User_Address;
    private String User_Phone;
    private int User_Mileage;
    private String User_Postcode;
    public String User_RoadAddress;
    private String User_DetailAddress;
    private String Authority;

    /*@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.User_Mode));
    }*/
    /*@Override//권한 반환                    스프링시큐리티 유저디테일 인터페이스에 정의된 메서드
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //권한 컬렉션 선언
        ArrayList<GrantedAuthority> auth = new ArrayList<GrantedAuthority>();
        //권한 컬렉션에 Dto의 권한값인 authority를 추가
        //SimpleGrantedAuthority는 GrantedAuthority인터페이스의 구현체
        auth.add(new SimpleGrantedAuthority(this.User_Mode));
        return auth;
    }*/
    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(this.getAuthority().toString()));

        return authorities;

    }

    @Override
    public String getPassword() {
        return User_Password; // User_Password 필드의 값을 반환하도록 수정
    }

    @Override
    public String getUsername() {
        return this.User_Id;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(String user_Id) {
        User_Id = user_Id;
    }

    public String getUser_Name() {
        return this.User_Name;
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

    public String getUser_Mode() {
        return Authority;
    }

    public void setUser_Mode(String authority) {
        Authority = authority;
    }




    public String getUser_RoadAddress() {
        return User_RoadAddress;
    }

    public void setUser_RoadAddress(String user_RoadAddress) {
        User_RoadAddress = user_RoadAddress;
    }


    public String getUser_Postcode() {
        return User_Postcode;
    }

    public void setUser_Postcode(String user_Postcode) {
        User_Postcode = user_Postcode;
    }



}