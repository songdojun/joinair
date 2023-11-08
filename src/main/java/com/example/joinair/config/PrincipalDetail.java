package com.example.joinair.config;

import com.example.joinair.dto.USERS;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetail implements UserDetails {

    private USERS user;

    public PrincipalDetail(USERS user) {
        this.user = user;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    // 계정이 만료되지 않았는지를 확인(true = 만료 안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정이 잠겨있지 않은지를 확인(true = 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    // 비밀번호가 만료되지 않았는지를 확인(true = 만료 안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    // 계정 활성화(사용 가능)가 되어있는지를 확인(true = 활성화)
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
        return true;
    }

    // 계정이 갖고 있는 권한 목록을 확인
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(() -> {
            return "ROLE_" + user.getUser_Mode().toString();	// ROLE_USER
        });

        return authorities;
    }

}
