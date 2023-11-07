package com.example.joinair.config;

import com.example.joinair.entity.Users;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {
    private Users user; // 콤포지션(객체를 들고 있는)

    //생성자
    public PrincipalDetails(Users user) {
        this.user = user;
    }

    //alt+shift+s
    @Override
    public String getPassword() {
        return user.getUser_Password();
    }

    @Override
    public String getUsername() {
        return user.getUser_Name();
    }

    //계정이 만료되지 않았는지 리턴한다. ( true : 만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 감져있지 않았는지 리턴한다. ( true : 잠기지 않음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았는지 리턴한다. ( true : 만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정 활성화(사용가능)인지 리턴한다. ( true : 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    //계정이 갖고있는 권한 목록을 리턴한다. (권한이 여러개 있을수있어서 루프를 돌아야 하는데  우리는 한개만)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(()->{return user.getUser_Mode();}); //add에 들어올 파라미터는 GrantedAuthority밖에 없으니

        return collectors;
    }
}
