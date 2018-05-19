package com.coddington.poom.service;

import java.util.List;
import com.coddington.poom.dao.QuestionsDAO;
import com.coddington.poom.vo.Question;

public class QuestionsServiceImpl implements QuestionsService {

  private QuestionsDAO questionsDAO;

  public void setQuestionsDAO(QuestionsDAO questionsDAO) {
    this.questionsDAO = questionsDAO;
  }

  @Override
  public List<Question> getQuestions(int serviceNo, int page) {
    // TODO Auto-generated method stub
    return questionsDAO.selectList(serviceNo);
  }

  @Override
  public Question getQuestion(int no) {
    // TODO Auto-generated method stub
    return questionsDAO.selectOne(no);
  }

  @Override
  public boolean register(Question question) {
    // TODO Auto-generated method stub
    return 1 == questionsDAO.insert(question);
  }

  @Override
  public boolean modify(Question question) {
    // TODO Auto-generated method stub
    return 1 == questionsDAO.update(question);
  }

  @Override
  public boolean remove(int no) {
    // TODO Auto-generated method stub
    return true;
  }

}
