package com.example.joinair.service;

import com.example.joinair.dto.USERS;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserService extends UserDetailsService {
    USERS getUserById(String User_Id);
    void registerUser(USERS user, String userMode);
    void updateUser(USERS user);
    void adminUpdateUser(USERS user);
    boolean isUserIdDuplicate(String User_Id);
    List<USERS> getAllUsers();
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    void updateUserMileage(String userId, double newMileage);




}