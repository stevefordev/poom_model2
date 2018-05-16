package com.coddington.poom.controller;

import org.springframework.stereotype.Controller;
import com.coddington.poom.service.ReviewsService;

@Controller
public class ReviewsController {

  private ReviewsService reviewsService;

  public void setReviewsService(ReviewsService reviewsService) {
    this.reviewsService = reviewsService;
  }
}
