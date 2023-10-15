package com.example.joinair.controller;


import com.example.joinair.entity.Review;
import com.example.joinair.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

        return "redirect:/review/list";

    }

    @GetMapping("/review/list")
    public String reviewList(Model model){

       model.addAttribute("list",reviewService.reviewList());
        
        return "reviewlist";
    }


    @GetMapping("/review/view")
    public String reviewView(Model model, Integer Rev_No) {
        model.addAttribute("Review", reviewService.reviewView(Rev_No).orElse(null));
        return "reviewview";
    }


    @GetMapping("/review/delete")
    public String boardDelete(Integer Rev_No){
        reviewService.reviewDelete(Rev_No);

        return "redirect:/review/list";
    }

    @GetMapping("/review/modify/{Rev_No}")
    public String reviewModify(@PathVariable("Rev_No") Integer Rev_No,Model model){

        model.addAttribute("review", reviewService.reviewView(Rev_No));

        return "reviewmodify";
    }


}
