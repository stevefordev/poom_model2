package com.coddington.poom.service;

import java.util.List;
import java.util.Map;
import com.coddington.poom.vo.Question;

public interface QuestionsService {

  public Map<String, Object> getQuestions(int serviceNo, int page);
  
  public Question getQuestion(int no);
  
  public boolean register(Question question);
 
  public boolean modify(Question question);
  
  public boolean remove(int no);
}
