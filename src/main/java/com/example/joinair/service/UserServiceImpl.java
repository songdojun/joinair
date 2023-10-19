package com.example.joinair.service;


import com.example.joinair.dto.USERS;
import com.example.joinair.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void registerUser(USERS user, String userMode) {
        String combinedAddress = user.getUser_Address() + " " + user.getUser_DetailAddress();
        user.setUser_Address(combinedAddress);
        user.setUser_Mode(userMode);
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

}