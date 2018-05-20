package com.coddington.poom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.coddington.poom.dao.QuestionsDAO;
import com.coddington.poom.util.CamelHashMap;
import com.coddington.poom.util.PaginateUtil;
import com.coddington.poom.vo.Question;
import com.coddington.poom.vo.Review;

public class QuestionsServiceImpl implements QuestionsService {

  private QuestionsDAO questionsDAO;

  public void setQuestionsDAO(QuestionsDAO questionsDAO) {
    this.questionsDAO = questionsDAO;
  }

  @Override
  public Map<String, Object> getQuestions(int serviceNo, int page) {
    // TODO Auto-generated method stub
    // 한 페이지에 보여질 게시물 수
    int numPage = 5;

    // 한 페이지에 보여질
    // 페이징 블록 갯수
    int numBlock = 3;
 
    // 페이징 처리용 Map
    Map<String, Object> map = new HashMap<>();

    int end = page * numPage;
    int start = end - (numPage - 1);

    map.put("start", start);
    map.put("end", end);
    map.put("serviceNo", serviceNo);
    // 아이돌 목록(list)
    List<Question> list = questionsDAO.selectList(map);
 
    // 모델(View에출력할)용 맵
    Map<String, Object> resultMap = new CamelHashMap();

    resultMap.put("list", list);

    // 페이지네이션 처리
    int total = questionsDAO.selectCountTotal(serviceNo);
    
    String url = String.format("/ajax/question.poom?serviceNo=%d&page=", serviceNo);

    String paginate = PaginateUtil.getPaginate(page, total, numPage, numBlock, url);

    resultMap.put("paginate", paginate);
    resultMap.put("count_total", total);
    
    return resultMap;
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
