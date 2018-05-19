package com.coddington.poom.dao;

import java.util.List;
import com.coddington.poom.vo.Review;

public interface ReviewsDAO {

  public Review selectOne(int no);
  
  public List<Review> selectList(int serviceNo);
  
  public int selectCountTotal(int serviceNo);
  
  public int update(Review review);
}
