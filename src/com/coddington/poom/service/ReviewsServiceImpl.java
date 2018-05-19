package com.coddington.poom.service;

import java.util.List;
import com.coddington.poom.dao.ReviewsDAO;
import com.coddington.poom.vo.Review;

public class ReviewsServiceImpl implements ReviewsService {

  private ReviewsDAO reviewsDAO;

  public void setReviewsDAO(ReviewsDAO reviewsDAO) {
    this.reviewsDAO = reviewsDAO;
  }

  @Override
  public List<Review> getReviews(int serviceNo, int page) {
    // TODO Auto-generated method stub
    return reviewsDAO.selectList(serviceNo);
  }
  
  @Override
  public Review getReview(int no) {
    // TODO Auto-generated method stub
    return reviewsDAO.selectOne(no);
  }
  
  @Override
  public boolean modify(Review review) {
    // TODO Auto-generated method stub
    return 1 == reviewsDAO.update(review);
  }
}
