package com.coddington.poom.service;

import java.util.List;
import com.coddington.poom.vo.Review;

public interface ReviewsService {

  public Review getReview(int no);
  
  public List<Review> getReviews(int serviceNo, int page);
 
  public boolean modify(Review review);
}
