package com.example.joinair.controller;


import com.example.joinair.entity.Review;
import com.example.joinair.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static com.example.joinair.service.ReviewService.write;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/review/write") //localhost:8080/review/write
    public String reviewWriteForm() {
        return "reviewwrite";
    }

    @PostMapping("review/writepro")
    public String reviewWritePro(Review review) {

        write(review);

        return "";

    }

}
