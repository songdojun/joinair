package com.example.joinair.controller;


import com.example.joinair.dto.USERS;
import com.example.joinair.entity.Product;
import com.example.joinair.entity.Review;
import com.example.joinair.service.ProductBuyService;
import com.example.joinair.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.joinair.service.ReviewService.write;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @Autowired
    private ProductBuyService productBuyService;

    @GetMapping("/review/write")
    public String showReviewForm(Model model) {
        List<Product> productList = productBuyService.productbuyList(); // productBuyRepository를 통해 상품 목록을 가져옴
        model.addAttribute("productList", productList); // 모델에 productList를 추가
        return "reviewwrite"; // 리뷰 작성 페이지로 이동
    }

    @PostMapping("review/writepro")
    public String reviewWritePro(Review review, Model model, MultipartFile file) throws Exception {
        write(review, file);
        model.addAttribute("message", "글 작성이 완료되었습니다.");
        model.addAttribute("searchUrl", "/review/list");
        return "message";
    }




    @GetMapping("/review/list")
    public String reviewList(Model model,
                                @PageableDefault(page = 0, size = 10, sort = "Rev_No", direction = Sort.Direction.DESC) Pageable pageable,
                                @RequestParam(name = "searchOption", required = false) String searchOption,
                                @RequestParam(name = "searchKeyword", required = false) String searchKeyword) {
        Page<Review> list = reviewService.reviewSearchList(searchOption, searchKeyword, pageable);

        int nowPage = list.getPageable().getPageNumber() + 1;
        int startPage = Math.max(nowPage - 4, 1);
        int endPage = Math.min(nowPage + 5, list.getTotalPages());

        // Add search parameters to the model
        model.addAttribute("list", list);
        model.addAttribute("nowPage", nowPage);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("searchOption", searchOption);
        model.addAttribute("searchKeyword", searchKeyword); // Pass search keyword to the view

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

        return "reviewlist";
    }



    @GetMapping("/review/view")
    public String reviewView(Model model, Integer Rev_No) {
        model.addAttribute("Review", reviewService.reviewView(Rev_No).orElse(null));


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
