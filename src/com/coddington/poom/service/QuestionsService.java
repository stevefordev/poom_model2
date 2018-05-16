package com.coddington.poom.service;

import java.util.List;
import com.coddington.poom.vo.Question;

public interface QuestionsService {

  public List<Question> getQuestions(int serviceNo);
  
  public Question getQuestion(int no);
  
  public boolean register(Question question);
 
  public boolean modify(Question question);
  
  public boolean remove(int no);
}
