package com.example.joinair.controller;

import com.sun.security.auth.PrincipalComparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class PageController {

    @GetMapping("/about")
    public String aboutPage() {
        return "about"; // "about.html" 파일을 반환
    }

    @GetMapping("/index")
    public String indexPage() {
        return "index2"; // "index.html" 파일을 반환
    }
}