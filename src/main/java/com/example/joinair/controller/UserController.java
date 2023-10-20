package com.example.joinair.controller;

import com.example.joinair.dto.USERS;
import com.example.joinair.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signInUp")
    public String showSignInUpPage(Model model) {
        System.out.println("Show Sign-In/Up Page");
        model.addAttribute("loginUser", new USERS()); // 수정: login 폼 데이터를 저장할 모델 추가
        model.addAttribute("signupUser", new USERS()); // 수정: signup 폼 데이터를 저장할 모델 추가
        return "signInUp";
    }

    @PostMapping("/signInUp")
    public String signInUp(@ModelAttribute("loginUser") USERS loginUser, HttpSession session) {
        USERS storedUser = userService.getUserById(loginUser.getUser_Id());
        if (storedUser != null && storedUser.getUser_Password().equals(loginUser.getUser_Password())) {
            session.setAttribute("User_Id", storedUser.getUser_Id());
            System.out.println("Login Success!!");
            return "redirect:/welcome";
        } else {
            System.out.println("Login Failed!");
            return "redirect:/signInUp";
        }
    }


    @PostMapping("/register")
    public String register(@ModelAttribute("signupUser") USERS signupUser, HttpSession session) {
        String combinedAddress = signupUser.getUser_Address() + " " + signupUser.getUser_DetailAddress();
        signupUser.setUser_Address(combinedAddress);
        // 회원가입 처리 로직
        // 여기에 회원가입 로직을 추가
        userService.registerUser(signupUser);
        session.setAttribute("User_Id", signupUser.getUser_Id());
        System.out.println("Sign-Up Success!");
        return "redirect:/welcome";
    }


    @PostMapping("/check-duplicate")
    @ResponseBody
    public String checkDuplicate(@RequestParam("User_Id") String userId) {
        // 중복 확인 로직
        boolean isDuplicate = userService.isUserIdDuplicate(userId);
        if (isDuplicate) {
            return "duplicate";
        }
        return "not-duplicate";
    }



    @GetMapping("/welcome")
    public String showWelcomePage(Model model, HttpSession session) {
        String userId = (String) session.getAttribute("User_Id");
        if (userId == null) {
            return "redirect:/login"; // 사용자 ID가 없으면 로그인 페이지로 리디렉션
        }

        // 사용자 정보를 가져와 모델에 추가
        USERS user = userService.getUserById(userId);
        model.addAttribute("user", user);

        return "welcome"; // welcome.html 템플릿 반환
    }
    // ---------로그 아웃----------------------
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 세션에서 사용자 ID를 제거하여 로그아웃 상태로 만듭니다.
        session.removeAttribute("User_Id");
        return "redirect:/signInUp";
    }


    // ---------사용자 개인정보 변경 페이지 보여주기----------------------

    @GetMapping("/profile")
    public String showProfilePage(Model model, HttpSession session) {
        System.out.println("Personal Profile Update page Show!!");
        String userId = (String) session.getAttribute("User_Id");
        if (userId == null) {
            return "redirect:/signInUp"; // 사용자 ID가 없으면 로그인 페이지로 리디렉션
        }

        // 사용자 정보를 가져와 모델에 추가
        USERS user = userService.getUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("userId", userId);
        return "profile"; // profile.html 템플릿 반환
    }
    // ---------사용자 개인정보 변경 메소드 추가----------------------
    @PostMapping("/update-profile")
    public String updateProfile(@ModelAttribute("user") USERS user, HttpSession session) {
        System.out.println("Personal Profile Update Method Execute");

        String userId = (String) session.getAttribute("User_Id");
        if (userId == null) {
            return "redirect:/signInUp"; // 사용자 ID가 없으면 로그인 페이지로 리디렉션
        }

        // 사용자 정보 업데이트
        user.setUser_Id(userId);
        userService.updateUser(user);

        return "redirect:/welcome"; // 업데이트 후 환영 페이지로 이동
    }

}