package com.coddington.poom.service;

import com.coddington.poom.dao.ReviewsDAO;

public class ReviewsServiceImpl implements ReviewsService {

  private ReviewsDAO reviewsDAO;

  public void setReviewsDAO(ReviewsDAO reviewsDAO) {
    this.reviewsDAO = reviewsDAO;
  }
  
}
