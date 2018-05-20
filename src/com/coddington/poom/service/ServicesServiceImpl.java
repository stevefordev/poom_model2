package com.coddington.poom.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.coddington.poom.dao.ContractSchedulesDAO;
import com.coddington.poom.dao.ContractsDAO;
import com.coddington.poom.dao.LikeServicesDAO;
import com.coddington.poom.dao.QuestionsDAO;
import com.coddington.poom.dao.ReviewsDAO;
import com.coddington.poom.dao.SchedulesDAO;
import com.coddington.poom.dao.ServiceTagsDAO;
import com.coddington.poom.dao.ServicesDAO;
import com.coddington.poom.dao.TagsDAO;
import com.coddington.poom.vo.Card;
import com.coddington.poom.vo.Contract;
import com.coddington.poom.vo.LikeService;
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
  private ContractSchedulesDAO contractSchedulesDAO;
  private LikeServicesDAO likeServicesDAO;

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

  public void setContractSchedulesDAO(ContractSchedulesDAO contractSchedulesDAO) {
    this.contractSchedulesDAO = contractSchedulesDAO;
  }

  public void setLikeServicesDAO(LikeServicesDAO likeServicesDAO) {
    this.likeServicesDAO = likeServicesDAO;
  }

  @Override
  public Map<String, Object> getDetails(int no) {
    // TODO Auto-generated method stub

    Map<String, Object> map = new HashMap();

    Service service = servicesDAO.selectByNo(no);
    map.put("service", service);
    map.put("tags", tagsDAO.selectListByServiceNo(no));
    map.put("countTotalReviews", reviewsDAO.selectCountTotal(no));
    map.put("countTotalQuestions", questionsDAO.selectCountTotal(no));

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
    map.put("schedules", schedulesDAO.selectListByServiceNo(no));

    // map.put("reviews", reviewsDAO.selectList(no));
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
      String schedulesJson =
          mapper.writeValueAsString(schedulesDAO.selectListByServiceNo(serviceNo));

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

      for (Schedule eachSchedule : schedules) {

        eachSchedule.setServiceNo(service.getNo());
        schedulesDAO.insert(eachSchedule);
      }

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

    return service.getNo();
  }

  @Override
  public boolean modify(Service service, int[] tagNos, String scheduleListJson) {
    // TODO Auto-generated method stub
    servicesDAO.update(service);

    serviceTagsDAO.deleteByServiceNo(service.getNo());

    ServiceTag serviceTag = new ServiceTag();
    for (int tagNo : tagNos) {
      System.out.println(String.format("serviceNo:%d / tagNo:%d", service.getNo(), tagNo));

      serviceTag.setServiceNo(service.getNo());
      serviceTag.setTagNo(tagNo);
      serviceTagsDAO.insert(serviceTag);
    }

    // 서비스 일정의 삭제는 수정 페이지에서 실시간 수행
    // 삽입은 ifnotexist 사용
    ObjectMapper mapper = new ObjectMapper();
    List<Schedule> schedules = new ArrayList();
    try {
      System.out.println(scheduleListJson);
      schedules = mapper.readValue(scheduleListJson, new TypeReference<List<Schedule>>() {});
      for (Schedule eachSchedule : schedules) {

        eachSchedule.setServiceNo(service.getNo());
        schedulesDAO.insertIfNotExists(eachSchedule);
      }

      return true;

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

    return false;
  }

  @Override
  public boolean removeSchedule(int scheduleNo) {
    // TODO Auto-generated method stub
    // 받은 계약서가 하나도 없으면 스케줄 삭제
    if (contractSchedulesDAO.selectCountByScheduleNo(scheduleNo) > 0) {
      return false;
    } else {
      return 1 == schedulesDAO.delete(scheduleNo);
    }
  }

  @Override
  public List<Card> getRecommendationServiceCard(int role, int userNo) {
    // TODO Auto-generated method stub

    // 나머지 데이터는 가공해서 넣어줌
    List<Card> recommendationServiceCardList = servicesDAO.selectRecommendationList(role);

    // 오늘 날짜 초기시간(00:00:00)에 해당하는 Calendar 객체 생성
    Calendar calendarForNow = Calendar.getInstance();
    calendarForNow.set(Calendar.HOUR_OF_DAY, 0);
    calendarForNow.set(Calendar.MINUTE, 0);
    calendarForNow.set(Calendar.SECOND, 0);
    calendarForNow.set(Calendar.MILLISECOND, 0);
    // System.out.println("오늘 날짜 : "+calendarForNow.getTime());

    Calendar calendarForMaxDate = Calendar.getInstance();
    calendarForMaxDate.add(Calendar.DATE, 56);// 오늘 날짜 기준 56일 뒤 날짜로 setting
    calendarForMaxDate.set(Calendar.HOUR_OF_DAY, 0);
    calendarForMaxDate.set(Calendar.MINUTE, 0);
    calendarForMaxDate.set(Calendar.SECOND, 0);
    calendarForMaxDate.set(Calendar.MILLISECOND, 0);
    // System.out.println("오늘+56일 : "+calendarForMaxDate.getTime());

    for (Card likeServiceCard : recommendationServiceCardList) {

      // tag 데이터(tags) 가공 및 setting
      List<Tag> tags = serviceTagsDAO.selectTagNameByServiceNo(likeServiceCard.getNo());

      String wholeTags = "";

      for (Tag tag : tags) {
        wholeTags += "#" + tag.getName() + " ";
      } // for~each end

      // tags setting
      likeServiceCard.setTags(wholeTags);

      // 좋아요 갯수(countLike) 값 구하기 및 setting
      int totalLikeNum = likeServicesDAO.selectTotalLikeNumByServiceNo(likeServiceCard.getNo());

      // countLike setting
      likeServiceCard.setCountLike(totalLikeNum);

      // 찜 여부(isLike) 구하기 및 setting
      LikeService likeServiceWithUserNoServiceNo = new LikeService();

      likeServiceWithUserNoServiceNo.setUserNo(userNo);

      likeServiceWithUserNoServiceNo.setServiceNo(likeServiceCard.getNo());

      LikeService likeService =
          likeServicesDAO.selectByUserNoServiceNo(likeServiceWithUserNoServiceNo);

      // isLike setting
      if (likeService != null) {
        likeServiceCard.setIsLike(true);
      }

      // 계약 정보(contract, scoreTotal, countScore, icon) 가공
      // 및 setting

      // 완료계약 정보
      Contract contractWithInfo1 = new Contract();
      contractWithInfo1.setServiceNo(likeServiceCard.getNo());
      contractWithInfo1.setStatus(9);

      List<Contract> contractDoneList = contractsDAO.selectListByServiceNo(contractWithInfo1);

      // countScore setting
      likeServiceCard.setCountScore(contractDoneList.size());

      // 완료계약 정보로 scoreTotal 및 icon 정보 가공

      // icon default값 "rain"
      String icon = "rain";

      if (contractDoneList.size() != 0) {
        // 완료계약건수가 0이 아닐 때 (평균 구할 때 0으로 나눌 수 x)

        if (likeServiceCard.getRole() == 1) {
          // giver 카드일 때 (score컬럼 4개로 계산)
          int totalScore = 0;

          for (Contract contractDone : contractDoneList) {
            if (contractDone.getScoreHonest() == 1) {
              // score가 good일 때
              totalScore += 100;
            } // if end
            if (contractDone.getScoreKind() == 1) {
              // score가 good일 때
              totalScore += 100;
            } // if end
            if (contractDone.getScoreKnowhow() == 1) {
              // score가 good일 때
              totalScore += 100;
            } // if end
            if (contractDone.getScorePrice() == 1) {
              // score가 good일 때
              totalScore += 100;
            } // if end
          } // for end

          int scoreTotal = totalScore / (contractDoneList.size() * 4);

          // scoreTotal에 따른 icon
          if (scoreTotal > 90) {
            icon = "sun";
          } else if (scoreTotal > 80) {
            icon = "cloudy";
          } else if (scoreTotal > 70) {
            icon = "clouds";
          }

          // scoreTotal setting
          likeServiceCard.setScoreTotal(scoreTotal);

        } else {
          // taker 카드일 때 (scoreUser컬럼으로 계산)
          int totalScore = 0;

          for (Contract contractDone : contractDoneList) {
            if (contractDone.getScoreUser() == 1) {
              // score가 good일 때
              totalScore += 100;
            } // if end
          } // for end

          int scoreTotal = totalScore / contractDoneList.size();

          // scoreTotal에 따른 icon
          if (scoreTotal > 90) {
            icon = "sun";
          } else if (scoreTotal > 80) {
            icon = "cloudy";
          } else if (scoreTotal > 70) {
            icon = "clouds";
          }

          // scoreTotal setting
          likeServiceCard.setScoreTotal(scoreTotal);

        } // if~else end

      } // if end

      // icon setting
      likeServiceCard.setIcon(icon);

      // 진행중인 계약 정보
      Contract contractWithInfo2 = new Contract();
      contractWithInfo2.setServiceNo(likeServiceCard.getNo());
      contractWithInfo2.setStatus(1);

      List<Contract> contractProgressList = contractsDAO.selectListByServiceNo(contractWithInfo2);

      String contract =
          "진행 중인 계약 " + contractProgressList.size() + "건 | 완료된 계약 " + contractDoneList.size() + "건";

      // contract setting
      likeServiceCard.setContract(contract);

      // 일정(schedule)관련 정보 가공(일정 갯수, 처음 일정) 및 setting

      // System.out.println("시작! 카드 서비스 번호 : "+likeServiceCard.getNo());

      List<Schedule> schedules = schedulesDAO.selectListByServiceNo(likeServiceCard.getNo());

      // 서비스에 등록된 스케줄 일정 갯수 (오늘 이후로)
      int scheduleTotalCount = 0;
      String representativeSchedule = "";

      for (Schedule schedule : schedules) {

        if (schedule.getType() == "repeat") {
          // 반복형일 때
          // System.out.println(likeServiceCard.getNo());
          // System.out.println(schedule.getServiceDay().substring(0, 3));

          // String형(ex:mon10) serviceDay에서 요일 String 얻어옴
          String dayNameFromServiceDay = schedule.getServiceDay().substring(0, 3);// mon, tue,
                                                                                  // wed...
          int dayNumFromServiceDay = 0; // serviceDay로부터 얻은 요일의 index 넘버

          // 문자열 요일을 index 숫자로 바꿔줌
          if (dayNameFromServiceDay.equals("sun")) {
            dayNumFromServiceDay = 1;
          } else if (dayNameFromServiceDay.equals("mon")) {
            dayNumFromServiceDay = 2;
          } else if (dayNameFromServiceDay.equals("tue")) {
            dayNumFromServiceDay = 3;
          } else if (dayNameFromServiceDay.equals("wed")) {
            dayNumFromServiceDay = 4;
          } else if (dayNameFromServiceDay.equals("thu")) {
            dayNumFromServiceDay = 5;
          } else if (dayNameFromServiceDay.equals("fri")) {
            dayNumFromServiceDay = 6;
          } else if (dayNameFromServiceDay.equals("sat")) {
            dayNumFromServiceDay = 7;
          } // if~else if end
            // System.out.println(dayNumFromServiceDay);

          // Timestamp형인 serviceStartdate을 calendar에 setting하여 요일 숫자 얻어옴
          Calendar calendarForServiceStartDate = Calendar.getInstance();
          calendarForServiceStartDate.setTimeInMillis(schedule.getServiceStartdate().getTime());

          int dayNumFromServiceStartDate = calendarForServiceStartDate.get(Calendar.DAY_OF_WEEK);
          // System.out.println(dayNumFromServiceStartDate);
          // System.out.println(calendarForServiceStartDate.getTime());

          // serviceStartdate요일 숫자와 serviceDay요일 숫자 비교
          int dayNumDiff = dayNumFromServiceDay - dayNumFromServiceStartDate;
          // 숫자 비교해서 실제 시작날짜로(요일에 맞게) serviceStartDate 조정
          if (dayNumDiff > 0) {
            calendarForServiceStartDate.add(Calendar.DATE, dayNumDiff);
            // System.out.println("실제 시작 날짜 : "+calendarForServiceStartDate.getTime());
          } else {
            calendarForServiceStartDate.add(Calendar.DATE, dayNumDiff + 7);
            // System.out.println("실제 시작 날짜 : "+calendarForServiceStartDate.getTime());
          } // if~else end

          // 오늘+56일과 실제시작날짜 사이 일 수 차이
          int dayDiff = ((int) ((calendarForMaxDate.getTimeInMillis()
              - calendarForServiceStartDate.getTimeInMillis()) / (1000 * 60 * 60 * 24))) - 1;

          // System.out.println("일 수 차이 : "+dayDiff);

          if (dayDiff >= 49) {
            scheduleTotalCount += 8;// 일 수 차이가 49일 이상이면 1개의 반복 스케쥴이 8개의 일정이다.
          } else {
            scheduleTotalCount += (dayDiff / 7) + 1;// 예를 들면 일 수 차이가 14~20일이면 1개의 반복 스케쥴이 3개의 일정이
                                                    // 된다.
                                                    // 0~6일이면 1개, 7~13일이면 2개, 즉, 일 수 차이를 7로 나눈 몫 +
                                                    // 1개의 일정이
                                                    // 된다.
          } // if~else end

          // System.out.println("스케쥴 갯수 : "+scheduleTotalCount);

        } else {
          // 단발성일 때

          // Timestamp인 serviceDate를 calendar에 setting함
          Calendar calendarForServiceDate = Calendar.getInstance();
          calendarForServiceDate.setTimeInMillis(schedule.getServiceDate().getTime());

          boolean isAfterToday = calendarForServiceDate.after(calendarForNow);// 단발성 스케쥴이 오늘 이후인지
          boolean isBeforeMaxDate = calendarForServiceDate.before(calendarForMaxDate);// 단발성 스케쥴이
                                                                                      // 56일 이후보다

          // System.out.println("단발성 스케쥴 날짜 : "+calendarForServiceDate.getTime());
          // System.out.println("스케줄이 오늘 날짜보다 이후인가? : "+isAfterToday);
          // System.out.println("스케줄이 max 날짜보다 이전인가? : "+isBeforeMaxDate);

          if (isAfterToday && isBeforeMaxDate) {
            // 단발성 스케줄이 오늘부터 56일 안에 있다면 scheduleTotalCount에 1 더함
            // System.out.println("통과");

            // 맨 처음 해당하는 스케쥴 정보 입력
            if (representativeSchedule.equals("")) {

              String fullRepresentativeSchedule = schedule.getServiceDate().toString();
              String date = fullRepresentativeSchedule.substring(0, 10);
              String hourStr = fullRepresentativeSchedule.substring(11, 13);
              int hour = Integer.parseInt(hourStr);
              String fullHour = "";

              if (hour > 12) {
                fullHour += "오후 " + (hour - 12) + "-" + (hour - 11) + "시";
              } else if (hour < 12) {
                fullHour += "오전 " + hour + "-" + (hour + 1) + "시";
              } else if (hour == 12) {
                fullHour += "오후 " + hour + "-1시";
              } // if~else if end

              representativeSchedule = date + " " + fullHour;

              // System.out.println(representativeSchedule);
            } // if end

            scheduleTotalCount++;
          }

          // System.out.println("스케쥴 갯수 : "+scheduleTotalCount);

        } // if~else end

      } // for(schedule) end

      // schedule setting
      likeServiceCard
          .setSchedule(representativeSchedule + " 외 " + (scheduleTotalCount - 1) + "개 일정");

    } // for end

    return recommendationServiceCardList;

  }// getLikeServiceCard() end

  public Map<String, Object> registerLikeSevice(LikeService likeService) {
    // TODO Auto-generated method stub

    int result = likeServicesDAO.insertLikeService(likeService);

    int totalLikeNum = likeServicesDAO.selectTotalLikeNumByServiceNo(likeService.getServiceNo());

    Map<String, Object> resultMap = new HashMap<>();

    resultMap.put("result", result);
    resultMap.put("totalLikeNum", totalLikeNum);

    return resultMap;

  }

  public Map<String, Object> deleteLikeSevice(LikeService likeService) {
    // TODO Auto-generated method stub

    int result = likeServicesDAO.deleteLikeService(likeService);

    int totalLikeNum = likeServicesDAO.selectTotalLikeNumByServiceNo(likeService.getServiceNo());

    Map<String, Object> resultMap = new HashMap<>();

    resultMap.put("result", result);
    resultMap.put("totalLikeNum", totalLikeNum);

    return resultMap;
  }

  @Override
  public boolean checkLikeSevice(LikeService likeService) {
    // TODO Auto-generated method stub
    return likeServicesDAO.selectByUserNoServiceNo(likeService) != null;
  }

}
