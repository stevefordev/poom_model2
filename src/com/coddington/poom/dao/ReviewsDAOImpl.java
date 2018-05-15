package com.coddington.poom.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.coddington.poom.vo.Review;

public class ReviewsDAOImpl implements ReviewsDAO {

  private SqlSession session;

  public void setSession(SqlSession session) {
    this.session = session;
  }// setSession() end

  @Override
  public List<Review> selectList(int no) {
    // TODO Auto-generated method stub
    return session.selectList("reviews.selectList", no);
  }
}
