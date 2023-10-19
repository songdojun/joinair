package com.example.joinair.mapper;

import com.example.joinair.dto.USERS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserMapper {
    USERS getUserById(@Param("User_Id") String User_Id);
    void registerUser(USERS user);
    void  updateUser(USERS user);
    boolean isUserIdExists(String User_Id);

}