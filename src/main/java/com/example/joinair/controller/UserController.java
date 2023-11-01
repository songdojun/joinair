package com.example.joinair.controller;

import com.example.joinair.dto.USERS;
import com.example.joinair.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String showSignInUpPage(Model model) {
        System.out.println("Show Login Page");
        model.addAttribute("loginUser", new USERS()); // 수정: login 폼 데이터를 저장할 모델 추가
        return "login";
    }

    @GetMapping("/membership")
    public String showMembershipPage(Model model) {
        System.out.println("Show Membership Page");
        model.addAttribute("signupUser", new USERS()); // 수정: signup 폼 데이터를 저장할 모델 추가
        return "membership";
    }
    @PostMapping("/login")
    public String loginPost(@ModelAttribute("loginUser") USERS loginUser, HttpSession session) {
        USERS storedUser = userService.getUserById(loginUser.getUser_Id());

        if (storedUser != null && storedUser.getUser_Password().equals(loginUser.getUser_Password())) {
            String userMode = storedUser.getUser_Mode();
            if ("user".equals(userMode)) {
                session.setAttribute("User_Id", storedUser.getUser_Id());

                // 로그 확인
                System.out.println("storedUser: " + storedUser);
                System.out.println("loginUser: " + loginUser);
                System.out.println("User_mode: " + storedUser.getUser_Mode());


                System.out.println("사용자 모드로 로그인");
                return "redirect:/index2"; // 사용자 모드로 리디렉션
            } else if ("admin".equals(userMode)) {
                session.setAttribute("User_Id", storedUser.getUser_Id());

                // 로그 확인
                System.out.println("storedUser: " + storedUser);
                System.out.println("loginUser: " + loginUser);
                System.out.println("User_mode: " + storedUser.getUser_Mode());

                System.out.println("관리자 모드로 로그인");
                return "redirect:/adminWelcome"; // 관리자 모드로 리디렉션
            }
        } else {
            System.out.println("Login Failed!");
            return "redirect:/login"; // 로그인 실패 시 로그인 페이지로 리디렉션
        }

        // 이 경우에 대한 기본 반환 값, 필요에 따라 변경할 수 있습니다.
        return "redirect:/login"; // 또는 다른 기본 리디렉션
    }

    @GetMapping("/adminWelcome")
    public String showAdminWelcomePage(Model model, HttpSession session) {
        if (session.getAttribute("User_Id") == null) {
            return "redirect:/login"; // 로그인되지 않았으면 로그인 페이지로 리디렉션
        }

        String userId = (String) session.getAttribute("User_Id");
        USERS user = userService.getUserById(userId);

        if (user != null && "admin".equals(user.getUser_Mode())) {
            model.addAttribute("isAdmin", true);
            List<USERS> users = userService.getAllUsers();
            model.addAttribute("users", users);


            return "adminWelcome";// 관리자 역할이면 isAdmin을 true로 설정
        } else {
            return "redirect:/login"; // 관리자가 아니면 로그인 페이지로 리디렉션
        }
    }


    @GetMapping("/adminEditUserList")
    public String showAdminEditUserListPage(Model model, HttpSession session) {
        if (session.getAttribute("User_Id") == null) {
            return "redirect:/login"; // 로그인되지 않았으면 로그인 페이지로 리디렉션
        }
        String userId = (String) session.getAttribute("User_Id");
        USERS user = userService.getUserById(userId);

        if (user != null && "admin".equals(user.getUser_Mode())) {
            model.addAttribute("isAdmin", true); // 관리자 역할이면 isAdmin을 true로 설정
        } else {
            return "redirect:/login"; // 관리자가 아니면 로그인 페이지로 리디렉션
        }
        List<USERS> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "../templates/adminEditUserList";
    }

    @GetMapping("/adminEditUserList/{userId}")
    public String showAdminEditUserListDetailPage(@PathVariable String userId, Model model, HttpSession session) {
        if (session.getAttribute("User_Id") == null) {
            return "redirect:/login";
        }
        String adminUserId = (String) session.getAttribute("User_Id");
        USERS adminUser = userService.getUserById(adminUserId);

        if (adminUser != null && "admin".equals(adminUser.getUser_Mode())) {
            model.addAttribute("isAdmin", true);
            USERS user = userService.getUserById(userId);
            model.addAttribute("user", user); // 사용자 정보를 모델에 추가
            return "adminEditUser"; // adminEditUser.html 템플릿 반환
        } else {
            return "redirect:/login";
        }
    }



    @PostMapping("/adminUpdateUser")
    public String adminUpdateUser(@ModelAttribute("user") USERS user, HttpSession session) {
        if (session.getAttribute("User_Id") == null) {
            return "redirect:/login"; // 로그인되지 않았으면 로그인 페이지로 리디렉션
        }
        System.out.println("adminUpdateUser 고객정보 수정 성공");

        userService.adminUpdateUser(user);
        return "redirect:/adminEditUserList";
    }

    @PostMapping("/membership")
    public String register(@ModelAttribute("signupUser") USERS signupUser, HttpSession session) {
        String combinedAddress = signupUser.getUser_Address() + " " + signupUser.getUser_DetailAddress();
        signupUser.setUser_Address(combinedAddress);
        // 회원가입 처리 로직
        // 여기에 회원가입 로직을 추가
        userService.registerUser(signupUser, "user"); // 두 번째 매개변수로 "user"를 전달
        session.setAttribute("User_Id", signupUser.getUser_Id());
        System.out.println("Sign-Up User Register Success!!");
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
        session.invalidate(); // 세션을 무효화하여 사용자 세션 데이터 삭제
        System.out.println("Log-out!!!");

        return "redirect:/login";
    }


    // ---------사용자 개인정보 변경 페이지 보여주기----------------------

    @GetMapping("/profile")
    public String showProfilePage(Model model, HttpSession session) {
        System.out.println("Personal Profile Update page Show!!");
        String userId = (String) session.getAttribute("User_Id");
        if (userId == null) {
            return "redirect:/login"; // 사용자 ID가 없으면 로그인 페이지로 리디렉션
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
            return "redirect:/login"; // 사용자 ID가 없으면 로그인 페이지로 리디렉션
        }

        // 사용자 정보 업데이트
        user.setUser_Id(userId);
        userService.updateUser(user);

        return "redirect:/welcome"; // 업데이트 후 환영 페이지로 이동
    }

}