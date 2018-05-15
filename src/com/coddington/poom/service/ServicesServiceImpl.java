package com.coddington.poom.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import com.coddington.poom.dao.ContractsDAO;
import com.coddington.poom.dao.ReviewsDAO;
import com.coddington.poom.dao.ServicesDAO;
import com.coddington.poom.dao.TagsDAO;
import com.coddington.poom.vo.Card;
import com.coddington.poom.vo.Service;

public class ServicesServiceImpl implements ServicesService {

  private ServicesDAO servicesDAO;
  private ContractsDAO contractsDAO;
  private TagsDAO tagsDAO;
  private ReviewsDAO reviewsDAO;

  public void setServicesDAO(ServicesDAO servicesDAO) {
    this.servicesDAO = servicesDAO;
  }

  public void setContractsDAO(ContractsDAO contractsDAO) {
    this.contractsDAO = contractsDAO;
  }

  public void setTagsDAO(TagsDAO tagsDAO) {
    this.tagsDAO = tagsDAO;
  }
  
  public void setReviewsDAO(ReviewsDAO reviewsDAO) {
    this.reviewsDAO = reviewsDAO;
  }

  @Override
  public Map<String, Object> getDetails(int no) {
    // TODO Auto-generated method stub

    Map<String, Object> map = new HashMap();

    Service service = servicesDAO.selectByNo(no);
    map.put("service", service);
    map.put("tags", tagsDAO.selectListByServiceNo(no));

    Map<String, Object> scoreInfoMap = contractsDAO.selectScoreAndCountByServiceNo(no);
    String icon = "sun";
    if (service.getRole() == 1) {
      icon = Card.getIcon(Integer.parseInt(scoreInfoMap.get("SCOREGIVER").toString()));
    } else {
      icon = Card.getIcon(Integer.parseInt(scoreInfoMap.get("SCORETAKER").toString()));
    }
    
    scoreInfoMap.put("ICON", icon);
    /*
     * for (Map.Entry<String, Object> entry : scoreInfoMap.entrySet()) {
     * System.out.print(entry.getKey()+":"); System.out.println(entry.getValue()); } ;
     */

    map.put("scoreAndCountContract", scoreInfoMap);    
    map.put("reviews", reviewsDAO.selectList(no));

    // map.put("photos", servicesDAO.selectByNo(no));

    return map;
  }
}
