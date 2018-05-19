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
  public Review selectOne(int no) {
    // TODO Auto-generated method stub
    return session.selectOne("reviews.selectOne", no);
  }
  
  @Override
  public List<Review> selectList(int serviceNo) {
    // TODO Auto-generated method stub
    return session.selectList("reviews.selectList", serviceNo);
  }

  @Override
  public int selectCountTotal(int serviceNo) {
    // TODO Auto-generated method stub
    return session.selectOne("reviews.selectCountTotal", serviceNo);
  }
  
  @Override
  public int update(Review review) {
    // TODO Auto-generated method stub
    return session.update("reviews.update", review);
  }

}
