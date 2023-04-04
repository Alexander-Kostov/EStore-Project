package com.example.EStore.web.rest;

import com.example.EStore.model.dto.ReviewDTO;
import com.example.EStore.model.entity.ReviewEntity;
import com.example.EStore.model.entity.UserEntity;
import com.example.EStore.model.views.ReviewView;
import com.example.EStore.service.ReviewService;
import com.example.EStore.service.UserService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.EStore.model.enums.UserRoleEnum.ADMIN;
import static com.example.EStore.model.enums.UserRoleEnum.MODERATOR;

@RestController
public class ReviewRestController {

    private ReviewService reviewService;

    private UserService userService;

    public ReviewRestController(ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
    }

    @GetMapping("/products-details/{id}/reviews")
    public ResponseEntity<List<ReviewView>> getAllReviews(@PathVariable("id") Long id) {
       var reviews = this.reviewService.getAllReviewsByProduct(id).stream()
               .map(ReviewRestController::mapReviewEntityToView).collect(Collectors.toList());

        return ResponseEntity.ok(reviews);
    }

    private static ReviewView mapReviewEntityToView(ReviewEntity r) {
        return new ReviewView(
                r.getId(), r.getText(), r.getUsername(), r.getEmail(), r.getCreated().format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
        );
    }

    @GetMapping("/products-details/{productId}/reviews/{reviewId}")
    private ResponseEntity<ReviewView> getReview(@PathVariable("reviewId") Long reviewId) {
        return ResponseEntity.ok(mapReviewEntityToView(this.reviewService.getReview(reviewId)));
    }


    @PostMapping(value = "/products-details/{id}/reviews", consumes = "application/json", produces = "application/json")
    public ResponseEntity<ReviewView> createReview(@AuthenticationPrincipal UserDetails principal,
                                                   @RequestBody ReviewDTO reviewDTO,
                                                   @PathVariable("id") Long id) {

        UserEntity author = userService.getUserByPrincipal(principal.getUsername());

        ReviewEntity reviewEntity = this.reviewService.saveReview(reviewDTO, author, id);

        ReviewView reviewView = mapReviewEntityToView(reviewEntity);

        return ResponseEntity.created(URI.create(String.format("/products-details/%s/reviews/%s", id, reviewEntity.getId())))
                .body(reviewView);

    }


    @DeleteMapping("/products-details/{productId}/reviews/{reviewId}")
    public ResponseEntity<ReviewView> deleteReview(@PathVariable("reviewId") Long reviewId,
                                                   @AuthenticationPrincipal UserDetails principal) {

        if (userService.getUserByPrincipal(principal.getUsername()) == null) {
            return ResponseEntity.status(403).build();
        }

        UserEntity user = userService.getUserByPrincipal(principal.getUsername());
        ReviewEntity reviewEntity = reviewService.getReview(reviewId);

        if(user.getRoles().stream().anyMatch(r -> r.getRole() == MODERATOR || r.getRole() == ADMIN) ||  user.getId() == reviewEntity.getAuthor().getId()) {
            ReviewEntity deletedReview = reviewService.deleteReview(reviewId);
            return ResponseEntity.ok(mapReviewEntityToView(deletedReview));

        }
        return ResponseEntity.status(403).build();
    }
}
