/*
package com.example.joinair.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import java.io.IOException;

public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private static final Logger logger = LoggerFactory.getLogger(CustomAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        String userId = authentication.getName();
        HttpSession session = request.getSession();
        session.setAttribute("User_Id", userId);
        System.out.println("세션에 아이디가 등록되었습니다");
        logger.info("로그인 성공! 사용자 ID: {}", userId);
        logger.debug("Authentication success. User Id: {}", userId);  // 디버그 레벨로 변경


        super.onAuthenticationSuccess(request, response, authentication);
    }
}*/
