package com.example.joinair.controller;

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
    public String aboutPage() {
        return "about"; // "about.html" 파일을 반환
    }

    @GetMapping("/index")
    public String showIndexPage(Model model, HttpSession session) {
            String userId = (String) session.getAttribute("User_Id");
//            if (userId == null) {
//                return "redirect:/login";
//            }

            // Spring Security의 Authentication 객체를 통해 사용자 정보를 가져옵니다.
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

            // 여기에서 필요한 사용자 정보를 모델에 추가합니다.
            model.addAttribute("userId", userId);
            model.addAttribute("userAuthorities", authentication.getAuthorities());

        return "index2"; // "index.html" 파일을 반환
    }
}