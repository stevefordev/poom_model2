package com.coddington.poom.service;

import java.util.List;
import java.util.Map;
import com.coddington.poom.vo.Review;

public interface ReviewsService {

  public Review getReview(int no);
  
  public Map<String, Object> getReviews(int serviceNo, int page);
 
  public boolean modify(Review review);
}
