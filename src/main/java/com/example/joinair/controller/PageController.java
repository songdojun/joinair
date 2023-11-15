package com.example.joinair.controller;

import com.example.joinair.dto.USERS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.sun.security.auth.PrincipalComparator;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PageController {

    @GetMapping("/about")
    public String aboutPage(Model model, HttpSession session, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        // 여기에서 필요한 사용자 정보를 모델에 추가합니다.
        model.addAttribute("userId", username);
        model.addAttribute("userAuthorities", authentication.getAuthorities());

        // 마일리지 정보를 얻어와 모델에 추가
        if (authentication.getPrincipal() instanceof USERS) {
            USERS user = (USERS) authentication.getPrincipal();
            model.addAttribute("userMileage", user.getUser_Mileage());
        }


        return "about"; // "about.html" 파일을 반환
    }

    @GetMapping("/index")
    public String showIndexPage(Model model, HttpSession session, Principal principal) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        // 로그인 여부를 확인하고, 로그인되지 않은 경우 로그인 페이지로 리다이렉트합니다.
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        // Principal에서 직접 사용자 정보를 가져옵니다.
        String username = authentication.getName();

        // 여기에서 필요한 사용자 정보를 모델에 추가합니다.
        model.addAttribute("userId", username);
        model.addAttribute("userAuthorities", authentication.getAuthorities());

        // 마일리지 정보를 얻어와 모델에 추가
        if (authentication.getPrincipal() instanceof USERS) {
            USERS user = (USERS) authentication.getPrincipal();
            model.addAttribute("userMileage", user.getUser_Mileage());
        }

        return "index2"; // index.html 템플릿 반환
    }
}