package com.example.EStore.service;

import com.example.EStore.model.dto.ReviewDTO;
import com.example.EStore.model.entity.ReviewEntity;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewService {

    private ReviewRepository reviewRepository;
    private UserService userService;

    private ProductService productService;

    public ReviewService(ReviewRepository reviewRepository, UserService userService, ProductService productService) {
        this.reviewRepository = reviewRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public List<ReviewEntity> getAllReviewsByProduct(Long productId) {

        return this.reviewRepository.findByProduct(productService.getProductById(productId));
    }

    public ReviewEntity saveReview(ReviewDTO review, UserEntity author, Long productId) {


        ReviewEntity reviewEntity = new ReviewEntity()
                .setAuthor(author)
                .setProduct(this.productService.getProductById(productId))
                .setText(review.getText())
                .setEmail(author.getEmail())
                .setUsername(review.getUsername())
                .setCreated(LocalDate.now());

        this.reviewRepository.save(reviewEntity);

        return reviewEntity;

    }

    public ReviewEntity getReview(Long reviewId) {
        return this.reviewRepository.findById(reviewId).get();
    }

    public ReviewEntity deleteReview(Long reviewId) {
        ReviewEntity review = getReview(reviewId);
        this.reviewRepository.delete(review);
        return review;
    }
}
