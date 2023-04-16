package com.example.Estore.service;


import com.example.EStore.model.dto.ReviewDTO;
import com.example.EStore.model.entity.ReviewEntity;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.repository.ReviewRepository;
import com.example.EStore.service.ProductService;
import com.example.EStore.service.ReviewService;
import com.example.EStore.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private UserService userService;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ReviewService reviewService;

    @Test
    public void testGetAllReviewsByProduct() {
        Long productId = 1L;
        ReviewEntity review = new ReviewEntity();
        List<ReviewEntity> reviews = new ArrayList<>();
        reviews.add(review);
        when(productService.getProductById(productId)).thenReturn(null);
        when(reviewRepository.findByProduct(null)).thenReturn(reviews);

        List<ReviewEntity> result = reviewService.getAllReviewsByProduct(productId);

        verify(productService, times(1)).getProductById(productId);
        verify(reviewRepository, times(1)).findByProduct(null);
        assertEquals(result, reviews);
    }

    @Test
    public void testSaveReview() {
        ReviewDTO reviewDTO = new ReviewDTO();
        UserEntity author = new UserEntity();
        Long productId = 1L;
        when(productService.getProductById(productId)).thenReturn(null);

        ReviewEntity result = reviewService.saveReview(reviewDTO, author, productId);

        verify(productService, times(1)).getProductById(productId);
        verify(reviewRepository, times(1)).save(any(ReviewEntity.class));
        assertEquals(result.getAuthor(), author);
        assertEquals(result.getProduct(), null);
        assertEquals(result.getText(), null);
        assertEquals(result.getEmail(), author.getEmail());
        assertEquals(result.getUsername(), reviewDTO.getUsername());
        assertEquals(result.getCreated(), LocalDate.now());
    }

    @Test
    public void testGetReview() {
        Long reviewId = 1L;
        ReviewEntity review = new ReviewEntity();
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        ReviewEntity result = reviewService.getReview(reviewId);

        verify(reviewRepository, times(1)).findById(reviewId);
        assertEquals(result, review);
    }

    @Test
    public void testDeleteReview() {
        Long reviewId = 1L;
        ReviewEntity review = new ReviewEntity();
        when(reviewRepository.findById(reviewId)).thenReturn(Optional.of(review));

        ReviewEntity result = reviewService.deleteReview(reviewId);

        verify(reviewRepository, times(1)).findById(reviewId);
        verify(reviewRepository, times(1)).delete(review);
        assertEquals(result, review);
    }

}
