package com.example.joinair.service;

import com.example.joinair.entity.Review;
import com.example.joinair.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    //특정 게시글 불러오기

    public Optional<Review> reviewView(Integer Rev_No) {
        Optional<Review> optionalReview = reviewRepository.findById(Rev_No);
        return optionalReview;
    }



    //특정 게시글 삭제
    public void reviewDelete(Integer Rev_No){

        reviewRepository.deleteById(Rev_No);
    }

}
