package com.example.joinair.service;


import com.example.joinair.dto.USERS;
import com.example.joinair.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public USERS getUserById(String User_Id) {

        return userMapper.getUserById(User_Id);
    }
    @Override
    public List<USERS> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    @Transactional
    public void registerUser(USERS user, String authority) {
        String password = user.getPassword();

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("비밀번호를 제공하세요.");
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setUser_Password(passwordEncoder.encode(password));
        user.setAuthority("user");
        String combinedAddress = user.getUser_Address() + " " + user.getUser_DetailAddress();
        user.setUser_Address(combinedAddress);
        userMapper.registerUser(user);
    }
    @Override
    public void updateUser(USERS user) {

        userMapper.updateUser(user);
    }

    @Override
    public void adminUpdateUser(USERS user) {
        userMapper.adminUpdateUser(user);
    }

    @Override
    public boolean isUserIdDuplicate(String User_Id) {
        // 아이디 중복 확인 로직
        USERS existingUser = getUserById(User_Id);
        return existingUser != null;
    }


        /*public void joinUser(USERS users){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            users.setUser_Password(passwordEncoder.encode(users.getPassword()));
            users.setUser_Mode("USER");

            userMapper.saveUser(users);
        }*/

        @Override
        public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
            //여기서 받은 유저 패스워드와 비교하여 로그인 인증
            UserDetails users = userMapper.getUserAccount(userId);
            if (users == null){
                throw new UsernameNotFoundException("User not authorized.");
            }

            return users;
        }
        public void updateUserMileage(String userId, double newMileage) {
        userMapper.updateUserMileage(userId, newMileage);
            // 현재 로그인한 사용자의 Authentication 객체를 가져옴
            Authentication currentAuthentication = SecurityContextHolder.getContext().getAuthentication();

            // 업데이트된 UserDetails로 새로운 AuthenticationToken을 생성
            UserDetails updatedUserDetails = userMapper.getUserAccount(userId);
            UsernamePasswordAuthenticationToken newAuthentication = new UsernamePasswordAuthenticationToken(
                    updatedUserDetails, updatedUserDetails.getPassword(), updatedUserDetails.getAuthorities()
            );

            // 가져온 Authentication 객체를 업데이트된 Authentication으로 교체
            SecurityContextHolder.getContext().setAuthentication(newAuthentication);
        }
    }

