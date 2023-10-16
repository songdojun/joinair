package com.example.joinair.controller;


import com.example.joinair.entity.Review;
import com.example.joinair.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

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
    public String reviewWritePro(Review review, Model model, MultipartFile file) throws Exception {

        write(review,file);

        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/review/list");
        return "message";

    }

    @GetMapping("/review/list")
    public String reviewList(Model model,
                             @PageableDefault(page = 0, size = 10, sort = "Rev_No", direction = Sort.Direction.DESC) Pageable pageable,
                             String searchKeyword) {

        Page<Review> list = null;

        if(searchKeyword ==null){
             list = reviewService.reviewListWithPagination(pageable);
        }else{
             list = reviewService.reviewSearchList(searchKeyword, pageable);
        }



        int nowPage= list.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage -4,1);
        int endPage= Math.min(nowPage+5,list.getTotalPages());


        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);

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
    public String reviewModify(@PathVariable("Rev_No") Integer Rev_No, Model model){
        Review review = reviewService.reviewView(Rev_No).orElse(null);

        if (review == null) {
            // Review가 null인 경우에 대한 처리 (예: 에러 페이지로 리다이렉트)
            return "redirect:/error";
        }

        model.addAttribute("review", review);
        return "reviewmodify";
    }

    @PostMapping("/review/update/{Rev_No}")
    public String reviewUpdate(@PathVariable("Rev_No") Integer Rev_No, Review updatedReview, Model model, MultipartFile file)throws Exception {
        // 현재 리뷰 정보를 가져옴
        Review reviewTemp = reviewService.reviewView(Rev_No).orElse(null);

        if (reviewTemp != null) {
            // 업데이트할 정보를 새로운 리뷰 정보에 설정
            reviewTemp.setRev_Title(updatedReview.getRev_Title());
            reviewTemp.setRev_Content(updatedReview.getRev_Content());

            // 리뷰 정보 저장
            reviewService.write(reviewTemp, file);
        }

        model.addAttribute("message", "수정이 완료되었습니다.");
        model.addAttribute("searchUrl", "/review/list");
        return "message";
    }



}
