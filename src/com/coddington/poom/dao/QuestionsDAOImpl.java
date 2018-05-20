package com.coddington.poom.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.coddington.poom.vo.Question;

public class QuestionsDAOImpl implements QuestionsDAO {

  private SqlSession session;

  public void setSession(SqlSession session) {
    this.session = session;
  }// setSession() end

  @Override
  public List<Question> selectList(Map<String, Object> map) {
    // TODO Auto-generated method stub
    return session.selectList("questions.selectList", map);
  }

  @Override
  public int insert(Question question) {
    // TODO Auto-generated method stub
    return session.insert("questions.insert", question);
  }

  @Override
  public int update(Question question) {
    // TODO Auto-generated method stub
    return session.update("questions.update", question);
  }

  @Override
  public Question selectOne(int no) {
    // TODO Auto-generated method stub
    return session.selectOne("questions.selectOne", no);
  }
  
  @Override
  public int selectCountTotal(int serviceNo) {
    // TODO Auto-generated method stub
    return session.selectOne("questions.selectCountTotal", serviceNo);
  }
}
