package com.example.joinair.mapper;

import com.example.joinair.dto.USERS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    USERS getUserById(@Param("User_Id") String User_Id);
    void registerUser(USERS user);
    void  updateUser(USERS user);
    void adminUpdateUser(USERS user);
    boolean isUserIdExists(String User_Id);
    USERS getUserAccount(String userId);

    // 회원가입
    void saveUser(USERS userVo);
    List<USERS> getAllUsers();


}