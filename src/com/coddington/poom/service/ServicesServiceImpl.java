package com.coddington.poom.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.coddington.poom.dao.ContractsDAO;
import com.coddington.poom.dao.QuestionsDAO;
import com.coddington.poom.dao.ReviewsDAO;
import com.coddington.poom.dao.SchedulesDAO;
import com.coddington.poom.dao.ServiceTagsDAO;
import com.coddington.poom.dao.ServicesDAO;
import com.coddington.poom.dao.TagsDAO;
import com.coddington.poom.vo.Card;
import com.coddington.poom.vo.Schedule;
import com.coddington.poom.vo.Service;
import com.coddington.poom.vo.ServiceTag;
import com.coddington.poom.vo.Tag;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;

public class ServicesServiceImpl implements ServicesService {

  private ServicesDAO servicesDAO;
  private ContractsDAO contractsDAO;
  private TagsDAO tagsDAO;
  private ReviewsDAO reviewsDAO;
  private QuestionsDAO questionsDAO;
  private SchedulesDAO schedulesDAO;
  private ServiceTagsDAO serviceTagsDAO;

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

  public void setQuestionsDAO(QuestionsDAO questionsDAO) {
    this.questionsDAO = questionsDAO;
  }

  public void setSchedulesDAO(SchedulesDAO schedulesDAO) {
    this.schedulesDAO = schedulesDAO;
  }

  public void setServiceTagsDAO(ServiceTagsDAO serviceTagsDAO) {
    this.serviceTagsDAO = serviceTagsDAO;
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

      icon = Card.getIcon(Integer.parseInt(scoreInfoMap.get("scoreGiver").toString()));
    } else {
      icon = Card.getIcon(Integer.parseInt(scoreInfoMap.get("scoreTaker").toString()));
    }

    scoreInfoMap.put("ICON", icon);
    /*
     * for (Map.Entry<String, Object> entry : scoreInfoMap.entrySet()) {
     * System.out.print(entry.getKey()+":"); System.out.println(entry.getValue()); } ;
     */

    map.put("scoreAndCountContract", scoreInfoMap);
    map.put("schedules", schedulesDAO.selectList(no));

    map.put("reviews", reviewsDAO.selectList(no));
    // map.put("questions", questionsDAO.selectList(no));

    // map.put("photos", servicesDAO.selectByNo(no));

    return map;
  }

  @Override
  public Map<String, Object> getService(int userNo, int serviceNo) {
    // TODO Auto-generated method stub
    /*
     * String noStr = request.getParameter("no"); Service serviced = new Service();
     * serviced.setNo(Integer.parseInt(noStr)); serviced.setUserNo(loginUser.getNo()); Service
     * service = ServicesDAO.selectByServiceNoAndUserNo(serviced);
     * 
     * List<Tag> tags = TagsDAO.selectListByServiceNo(service.getNo());
     * 
     * List<Schedule> schedules = SchedulesDAO.selectListByServiceNo(service.getNo());
     * 
     * ObjectMapper mapper = new ObjectMapper(); SimpleDateFormat sdf = new
     * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); mapper.setDateFormat(sdf); String scheduleJson =
     * mapper.writeValueAsString(schedules);
     */

    Map<String, Object> map = new HashMap();

    Service service = new Service();

    try {
      service.setNo(serviceNo);
      service.setUserNo(userNo);
      map.put("service", servicesDAO.selectByServiceNoAndUserNo(service));
      map.put("tags", tagsDAO.selectListByServiceNo(serviceNo));

      ObjectMapper mapper = new ObjectMapper();
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      mapper.setDateFormat(sdf);
      String schedulesJson = mapper.writeValueAsString(schedulesDAO.selectList(serviceNo));

      map.put("schedulesJson", schedulesJson);
    } catch (Exception e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    return map;
  }

  @Override
  public List<Tag> getTags(String name) {
    // TODO Auto-generated method stub
    return tagsDAO.selectListLikeName(name);
  }

  @Override
  public Tag getTagByName(String name) {
    // TODO Auto-generated method stub

    Tag tag = tagsDAO.selectByName(name);
    if (tag == null) {
      tag = new Tag();
      tag.setName(name);
      tagsDAO.insert(tag);
    }
    return tag;
  }

  @Override
  public int register(Service service, int[] tagNos, String scheduleListJson) {
    // TODO Auto-generated method stub

    // 서비스 입력
    servicesDAO.insert(service);
    System.out.println(service.getNo());
    // 서비스 태그 입력
    // 태그 입력 당시에 ajax 로 새로 입련된 태그에 대해서 먼저 삽입하고
    // 폼 서브밋 당시에는 태그 no 만 가지고 와서 servicetag 에 정보 삽입
    ServiceTag serviceTag = new ServiceTag();
    for (int tagNo : tagNos) {
      serviceTag.setServiceNo(service.getNo());
      serviceTag.setTagNo(tagNo);
      serviceTagsDAO.insert(serviceTag);
    }

    ObjectMapper mapper = new ObjectMapper();

    List<Schedule> schedules = new ArrayList();
    try {
      System.out.println(scheduleListJson);
      schedules = mapper.readValue(scheduleListJson, new TypeReference<List<Schedule>>() {});
      
    } catch (JsonParseException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JsonMappingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

    for (Schedule eachSchedule : schedules) {

      eachSchedule.setServiceNo(service.getNo());
      schedulesDAO.insert(eachSchedule);
    }

    return service.getNo();
  }

}
