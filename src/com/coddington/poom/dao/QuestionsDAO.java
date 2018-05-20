package com.coddington.poom.dao;

import java.util.List;
import java.util.Map;
import com.coddington.poom.vo.Question;

public interface QuestionsDAO {

  public List<Question> selectList(Map<String, Object> map);

  public int insert(Question question);
  
  public int update(Question question);
  
  public Question selectOne(int no);
  
  public int selectCountTotal(int serviceNo);
}
