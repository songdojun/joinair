package com.example.joinair.service;

import com.example.joinair.dto.USERS;

public interface UserService {
    USERS getUserById(String User_Id);
    void registerUser(USERS user, String userMode);
    void updateUser(USERS user);
    boolean isUserIdDuplicate(String User_Id);

}