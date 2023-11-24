package com.example.joinair.service;

import com.example.joinair.entity.Review;
import com.example.joinair.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReviewService {

    private static ReviewRepository reviewRepository;


    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    //글 작성 처리
    public static void write(Review review, MultipartFile file) throws Exception{

        String projectpath = System.getProperty("user.dir") + "\\src\\main\\resources\\static\\files";

        UUID uuid = UUID.randomUUID();

        String fileName = uuid + "_" + file.getOriginalFilename();

        File saveFile = new File(projectpath, fileName);
        file.transferTo(saveFile);

        review.setRev_Filename(fileName);
        review.setRev_Filepath("/files/"+ fileName);
        reviewRepository.save(review);
    }


    //게시글 리스트 처리
    public List<Review> reviewList(){

        return reviewRepository.findAll();
    }

    public Page<Review> reviewSearchList(String searchKeyword, Pageable pageable) {
        return reviewRepository.findReviewsByRevTitleContaining(searchKeyword, pageable);
    }

    public Page<Review> reviewSearchList(String searchOption, String searchKeyword, Pageable pageable) {
        Page<Review> list;

        // Pageable 객체를 적절하게 설정
        pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by("Rev_No").descending());

        if ("Rev_No".equals(searchOption)) {
            list = reviewRepository.findReviewsByRevNo(Integer.parseInt(searchKeyword), pageable);
        } else if ("Pro_Name".equals(searchOption)) {
            list = reviewRepository.findReviewsByProNameContaining(searchKeyword, pageable);
        } else if ("Rev_Title".equals(searchOption)) { // "Rev_Title" 검색 옵션 추가
            list = reviewRepository.findReviewsByRevTitleContaining(searchKeyword, pageable);
        } else {
            list = reviewRepository.findAllOrderedByRevNoWithPagination(pageable);
        }

        return list;
    }






    //특정 게시글 불러오기

    public Optional<Review> reviewView(Integer Rev_No) {
        Optional<Review> optionalReview = reviewRepository.findById(Rev_No);
        return optionalReview;
    }



    //특정 게시글 삭제
    public void reviewDelete(Integer Rev_No){

        reviewRepository.deleteById(Rev_No);
    }

    public Page<Review> reviewListWithPagination(Pageable pageable) {
        return reviewRepository.findAllOrderedByRevNoWithPagination(pageable);
    }



}
