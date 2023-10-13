package com.example.joinair.service;

import com.example.joinair.entity.Review;
import com.example.joinair.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    private static ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public static void write(Review review) {
        reviewRepository.save(review);
    }
}
