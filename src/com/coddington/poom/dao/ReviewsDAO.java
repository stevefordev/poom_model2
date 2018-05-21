package com.coddington.poom.dao;

import java.util.List;
import java.util.Map;
import com.coddington.poom.vo.Review;

public interface ReviewsDAO {

	public Review selectOne(int no);

	public List<Review> selectList(Map<String, Object> map);

	public int selectCountTotal(int serviceNo);

	public int update(Review review);

	public int insertReview(Review review);
}
