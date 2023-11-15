package com.example.joinair.controller;

import com.example.joinair.dto.USERS;
import com.example.joinair.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    private void storeUserInSession(HttpSession session, String userId) {
        USERS user = userService.getUserById(userId);
        if (user != null) {
            session.setAttribute("User_Id", user.getUser_Id());
            // 여기서 필요한 사용자 정보를 세션에 저장할 수 있습니다.
        }
    }
    /*@PostMapping("/login")
    public String loginPost(@ModelAttribute("loginUser") USERS loginUser, HttpSession session) {
        USERS storedUser = userService.getUserById(loginUser.getUser_Id());
        if (storedUser != null) {
            // 데이터베이스에서 사용자를 찾았습니다.
            // 이제 입력한 비밀번호와 저장된 비밀번호 해시를 비교합니다.
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            if (passwordEncoder.matches(loginUser.getUser_Password(), storedUser.getUser_Password())) {
                System.out.println("Password matches");
                // 비밀번호가 일치하는 경우 세션에 사용자 아이디를 저장합니다.
                //session.setAttribute("User_Id", storedUser.getUser_Id());
                storeUserInSession(session, storedUser.getUser_Id());

                // 이후 로그인 된 사용자의 권한에 따라 리디렉션합니다.
                if ("user".equals(storedUser.getAuthority())) {
                    // 사용자 모드로 로그인한 경우 사용자 페이지로 리디렉션합니다.
                    return "redirect:/index";
                } else if ("admin".equals(storedUser.getAuthority())) {
                    // 관리자 모드로 로그인한 경우 관리자 페이지로 리디렉션합니다.
                    return "redirect:/adminWelcome";
                }
            }
        }
        // 로그인에 실패하거나 사용자를 찾을 수 없는 경우, 다시 로그인 페이지로 리디렉션합니다.
        System.out.println("Password does not match");
        return "redirect:/login";
    }*/


        /*
        if (storedUser != null && storedUser.getUser_Password().equals(loginUser.getUser_Password())) {
            String userMode = storedUser.getUser_Mode();
            if ("user".equals(userMode)) {
                session.setAttribute("User_Id", storedUser.getUser_Id());

                // 로그 확인
                System.out.println("storedUser: " + storedUser);
                System.out.println("loginUser: " + loginUser);
                System.out.println("User_mode: " + storedUser.getUser_Mode());


                System.out.println("사용자 모드로 로그인");
                return "redirect:/index"; // 사용자 모드로 리디렉션
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
    }*/

    @GetMapping("/adminWelcome")
    public String showAdminWelcomePage(Model model, HttpSession session) {
        /*if (session.getAttribute("User_Id") == null) {
            return "redirect:/login"; // 로그인되지 않았으면 로그인 페이지로 리디렉션
        }*/
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))) {
            model.addAttribute("userId", auth.getName());
            model.addAttribute("userMode", "admin");
            List<USERS> users = userService.getAllUsers();
            model.addAttribute("users", users);

            return "adminWelcome";
        } else {
            // 관리자 권한이 없으면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }
        /*String userId = (String) session.getAttribute("User_Id");
        USERS user = userService.getUserById(userId);

        if (user != null && "admin".equals(user.getAuthority())) {
//            model.addAttribute("isAdmin", true);
            model.addAttribute("userId", userId);
            model.addAttribute("userMode", user.getAuthority()); // 사용자 권한을 모델에 추가
            List<USERS> users = userService.getAllUsers();
            model.addAttribute("users", users);


            return "adminWelcome";// 관리자 역할이면 isAdmin을 true로 설정
        } else {
            return "redirect:/login"; // 관리자가 아니면 로그인 페이지로 리디렉션
        }*/
    }


    @GetMapping("/adminEditUserList")
    public String showAdminEditUserListPage(Model model, HttpSession session) {
//        System.out.println(userInfo);

        /*if (session.getAttribute("User_Id") == null) {
            return "redirect:/login"; // 로그인되지 않았으면 로그인 페이지로 리디렉션
        }*//*
        String userId = (String) session.getAttribute("User_Id");
        USERS user = userService.getUserById(userId);
//        model.addAttribute("isAdmin", true); // 관리자 역할이면 isAdmin을 true로 설정
        model.addAttribute("user", user);


        *//*if (user != null && "admin".equals(user.getUser_Mode())) {
            model.addAttribute("isAdmin", true); // 관리자 역할이면 isAdmin을 true로 설정
        } else {
            return "redirect:/login"; // 관리자가 아니면 로그인 페이지로 리디렉션
        }*//*
        List<USERS> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "adminEditUserList";
    }*/
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))) {
            model.addAttribute("user", userService.getUserById(auth.getName()));
            List<USERS> users = userService.getAllUsers();
            model.addAttribute("users", users);
            return "adminEditUserList";
        } else {
            // 관리자 권한이 없으면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }
    }

    @GetMapping("/adminEditUserList/{userId}")
    public String showAdminEditUserListDetailPage(@PathVariable String userId, Model model, HttpSession session) {
        /*if (session.getAttribute("User_Id") == null) {
            return "redirect:/login";
        }
        String adminUserId = (String) session.getAttribute("User_Id");
        USERS adminUser = userService.getUserById(adminUserId);

        if (adminUser != null && "admin".equals(adminUser.getAuthority())) {
            model.addAttribute("isAdmin", true);
            USERS user = userService.getUserById(userId);
            model.addAttribute("user", user); // 사용자 정보를 모델에 추가
            return "adminEditUser"; // adminEditUser.html 템플릿 반환
        } else {
            return "redirect:/login";
        }*/
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))) {
            // 현재 로그인한 사용자가 관리자인 경우
            model.addAttribute("isAdmin", true);
            USERS user = userService.getUserById(userId);
            if (user != null) {
                model.addAttribute("user", user);
                return "adminEditUser"; // adminEditUser.html 템플릿 반환
            } else {
                // 사용자를 찾을 수 없으면 관리자 페이지로 리다이렉트
                return "redirect:/adminEditUserList";
            }
        } else {
            // 관리자 권한이 없으면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }
    }



    @PostMapping("/adminUpdateUser")
    //public String showAdminEditUserPage(Model model, HttpSession session) {

    public String adminUpdateUser(@ModelAttribute("user") USERS user, HttpSession session) {
        /*if (session.getAttribute("User_Id") == null) {
            return "redirect:/login"; // 로그인되지 않았으면 로그인 페이지로 리디렉션
        }
        System.out.println("adminUpdateUser 고객정보 수정 성공");

        userService.adminUpdateUser(user);
        return "redirect:/adminEditUserList";*/
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("admin"))) {
            // 현재 로그인한 사용자가 관리자인 경우
            //model.addAttribute("isAdmin", true);
            // 여기에 필요한 모델 데이터를 추가하십시오.
            userService.adminUpdateUser(user);
            return "redirect:/adminEditUserList";
        } else {
            // 관리자 권한이 없으면 로그인 페이지로 리다이렉트
            return "redirect:/login";
        }
    }

    @PostMapping("/membership")
    public String register(@ModelAttribute("signupUser") USERS signupUser, HttpSession session) {
        String combinedAddress = signupUser.getUser_Postcode() + " " + signupUser.getUser_RoadAddress();
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
        session.removeAttribute("User_Id"); // 세션에서 User_Id 속성을 제거
        session.invalidate(); // 세션을 무효화하여 사용자 세션 데이터 삭제
        System.out.println("Log-out!!!");

        return "redirect:/index";
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