package com.coddington.poom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.coddington.poom.dao.ReviewsDAO;
import com.coddington.poom.util.CamelHashMap;
import com.coddington.poom.util.PaginateUtil;
import com.coddington.poom.vo.Review;

public class ReviewsServiceImpl implements ReviewsService {

  private ReviewsDAO reviewsDAO;

  public void setReviewsDAO(ReviewsDAO reviewsDAO) {
    this.reviewsDAO = reviewsDAO;
  }

  @Override
  public Map<String, Object> getReviews(int serviceNo, int page) {
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
    List<Review> list = reviewsDAO.selectList(map);
 
    // 모델(View에출력할)용 맵
    Map<String, Object> resultMap = new CamelHashMap();

    resultMap.put("list", list);

    // 페이지네이션 처리

    int total = reviewsDAO.selectCountTotal(serviceNo);
    
    String url = String.format("/ajax/review.poom?serviceNo=%d&page=", serviceNo);

    String paginate = PaginateUtil.getPaginate(page, total, numPage, numBlock, url);

    resultMap.put("paginate", paginate);
    resultMap.put("count_total", total);
    
    return resultMap;
  }

  @Override
  public Review getReview(int no) {
    // TODO Auto-generated method stub
    return reviewsDAO.selectOne(no);
  }

  @Override
  public boolean modify(Review review) {
    // TODO Auto-generated method stub
    return 1 == reviewsDAO.update(review);
  }
}
