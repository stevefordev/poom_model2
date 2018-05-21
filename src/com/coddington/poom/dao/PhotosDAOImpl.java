package com.coddington.poom.dao;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.coddington.poom.vo.Photo;

public class PhotosDAOImpl implements PhotosDAO {

  private SqlSession session;

  public void setSession(SqlSession session) {
    this.session = session;
  }// setSession() end

  @Override
  public List<Photo> selectByServiceNo(int serviceNo) {
    // TODO Auto-generated method stub
    return session.selectList("photos.selectByServiceNo", serviceNo);
  }

  @Override
  public int insert(Photo photo) {
    // TODO Auto-generated method stub
    return session.insert("photos.insert", photo);
  }
}
