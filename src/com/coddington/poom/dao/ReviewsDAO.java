package com.coddington.poom.dao;

import java.util.List;
import com.coddington.poom.vo.Review;

public interface ReviewsDAO {

  public List<Review> selectList(int no);
}
