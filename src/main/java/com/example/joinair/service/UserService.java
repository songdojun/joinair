package com.example.joinair.service;

import com.example.joinair.dto.USERS;

import java.util.List;

public interface UserService {
    USERS getUserById(String User_Id);
    void registerUser(USERS user, String userMode);
    void updateUser(USERS user);
    void adminUpdateUser(USERS user);
    boolean isUserIdDuplicate(String User_Id);
    List<USERS> getAllUsers();

}