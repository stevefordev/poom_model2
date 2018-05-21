package com.coddington.poom.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import com.coddington.poom.service.ServicesService;
import com.coddington.poom.util.ResizeImageUtil;
import com.coddington.poom.vo.Card;
import com.coddington.poom.vo.Contract;
import com.coddington.poom.vo.LikeService;
import com.coddington.poom.vo.Question;
import com.coddington.poom.vo.Schedule;
import com.coddington.poom.vo.Service;
import com.coddington.poom.vo.Service.Category;
import com.coddington.poom.vo.Tag;
import com.coddington.poom.vo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class ServicesController {

  private ServicesService servicesService;

  public void setServicesService(ServicesService servicesService) {
    this.servicesService = servicesService;
  }

  @RequestMapping(value = {"/", "/index.poom"})
  public String index() {
    return "index";
  }

  /**
   * 추천 목록 호출
   * 
   * @param role
   * @param session
   * @return
   */
  @RequestMapping("/ajax/recommendationCardList.poom")
  @ResponseBody
  public List<Card> selectRecommendationList(int role, HttpSession session) {

    User loginUser = (User) session.getAttribute(User.LOGIN_USER);

    int userNo = 0;

    if (loginUser != null) {
      userNo = loginUser.getNo();
    }

    return servicesService.getRecommendationServiceCard(role, userNo);

  }

  /**
   * 찜등록
   * 
   * @param serviceNo
   * @param session
   * @return
   */
  @RequestMapping(value = "/ajax/likeService/register.poom")
  @ResponseBody
  public Map<String, Object> registerLikeService(int serviceNo, HttpSession session) {

    User loginUser = (User) session.getAttribute(User.LOGIN_USER);

    int userNo = loginUser.getNo();

    LikeService likeService = new LikeService();

    likeService.setServiceNo(serviceNo);
    likeService.setUserNo(userNo);

    return servicesService.registerLikeSevice(likeService);
  }

  /**
   * 찜삭제
   * 
   * @param serviceNo
   * @param session
   * @return
   */
  @RequestMapping(value = "/ajax/likeService/delete.poom")
  @ResponseBody
  public Map<String, Object> deleteLikeService(int serviceNo, HttpSession session) {

    User loginUser = (User) session.getAttribute(User.LOGIN_USER);

    int userNo = loginUser.getNo();

    LikeService likeService = new LikeService();

    likeService.setServiceNo(serviceNo);
    likeService.setUserNo(userNo);

    return servicesService.deleteLikeSevice(likeService);
  }

  /**
   * 찜 체크
   * 
   * @param serviceNo
   * @param session
   * @return
   */
  @RequestMapping(value = "/ajax/likeService/check.poom")
  @ResponseBody
  public boolean checkLikeService(int serviceNo, HttpSession session) {

    User loginUser = (User) session.getAttribute(User.LOGIN_USER);

    int userNo = loginUser.getNo();

    LikeService likeService = new LikeService();

    likeService.setServiceNo(serviceNo);
    likeService.setUserNo(userNo);

    return servicesService.checkLikeSevice(likeService);
  }

  /**
   * 서비스 상세
   * 
   * @param no
   * @param model
   * @param session
   * @return
   */
  @RequestMapping(value = "/service/details.poom")
  public String detail(@RequestParam(defaultValue = "0") int no, Model model, HttpSession session) {

    //
    if (no > 0) {
      model.addAllAttributes(servicesService.getDetails(no));
      if (session.getAttribute("loginUser") == null) {
        // session.setAttribute("loginUser", arg1);
      }
    } else {
      // 비정상적으로 상세페이지로 넘어온 경우 no 가 부정확하기 때문에 인트로로 리다이렉트
      return "redirect:/";
    }

    return "details";
  }

  /**
   * 서비스 등록 폼
   * 
   * @return
   */
  @RequestMapping(value = "/service/registerForm.poom")
  public String registerForm() {

    return "register_service_form";
  }

  /**
   * 서비스 수정 폼
   * 
   * @param serviceNo
   * @param model
   * @param session
   * @return
   */
  @RequestMapping(value = "/service/updateForm.poom")
  public String registerForm(@RequestParam(value = "no") int serviceNo, Model model,
      HttpSession session) {

    User loginUser = (User) session.getAttribute(UsersController.LOGIN);
    System.out.println(loginUser.getNo());
    System.out.println(serviceNo);
    model.addAllAttributes(servicesService.getService(loginUser.getNo(), serviceNo));

    return "update_service_form";
  }

  /**
   * 서비스 등록
   * 
   * @param scheduleListJson
   * @param service
   * @param bindingResult
   * @param tagNos
   * @param photo
   * @param session
   * @return
   */
  @RequestMapping(value = "/service.poom", method = RequestMethod.POST)
  public String register(@RequestParam(value = "scheduleList") String scheduleListJson,
      @ModelAttribute Service service, BindingResult bindingResult,
      @RequestParam(value = "tags") int[] tagNos, String photo, HttpSession session) {

    User loginUser = (User) session.getAttribute(UsersController.LOGIN);

    System.out.println(service.toString());

    service.setUserNo(loginUser.getNo());
    // 디비도 string 으로 하는게 좋을듯
    service.setCategory(Category.find(service.getCategoryEng()).getCode());

    // 위도 경도 길이를 15에 맞춘다
    service.setLongitude(service.getLongitude().substring(0, 15));
    service.setLatitude(service.getLatitude().substring(0, 15));

    // service 모델로 바로 받을 수 있도록 추후 수정 필요
    service.setPhotoUrl(photo);
    int serviceNo = servicesService.register(service, tagNos, scheduleListJson);

    return "redirect:/service/details.poom?no=" + serviceNo;
  }

  /**
   * 서비스 수정
   * 
   * @param scheduleListJson
   * @param service
   * @param bindingResult
   * @param tagNos
   * @param photo
   * @param session
   * @return
   */
  @RequestMapping(value = "/serviceUpdate.poom", method = RequestMethod.POST)
  public String update(@RequestParam(value = "scheduleList") String scheduleListJson,
      @ModelAttribute Service service, BindingResult bindingResult,
      @RequestParam(value = "tags") int[] tagNos, String photo, HttpSession session) {

    User loginUser = (User) session.getAttribute("loginUser");

    System.out.println(service.toString());

    service.setUserNo(loginUser.getNo());
    // 디비도 string 으로 하는게 좋을듯
    service.setCategory(Category.find(service.getCategoryEng()).getCode());

    // 위도 경도 길이를 15에 맞춘다
    service.setLongitude(service.getLongitude().substring(0, 15));
    service.setLatitude(service.getLatitude().substring(0, 15));

    // service 모델로 바로 받을 수 있도록 추후 수정 필요
    service.setPhotoUrl(photo);
    boolean isSucc = servicesService.modify(service, tagNos, scheduleListJson);

    return "redirect:/service/details.poom?no=" + service.getNo();
  }

  /**
   * 태그 리스트 호출
   * 
   * @param isEqual
   * @param name
   * @return
   */
  @RequestMapping(value = "/ajax/service/getTagList.poom")
  @ResponseBody
  public Object getTags(@RequestParam(defaultValue = "false") boolean isEqual, String name) {

    // 태그를 검색하고 없으면 insert 후 tagId 리턴
    // isEqual => like 검색 여부
    if (isEqual) {
      return servicesService.getTagByName(name);

    } else {
      // like 검색으로 자동 완성 리스트 리턴
      return servicesService.getTags(name);
    }
  }

  /**
   * 스케줄 삭제
   * 
   * @param scheduleNo
   * @return
   */
  @RequestMapping(value = "/ajax/service/deleteSchedule.poom")
  @ResponseBody
  public String removeSchedule(int scheduleNo) {
    boolean isSuccRemove = false;

    isSuccRemove = servicesService.removeSchedule(scheduleNo);

    ObjectMapper om = new ObjectMapper();
    ObjectNode o = om.createObjectNode();

    o.put("isSuccRemove", isSuccRemove);
    String json = "";
    try {
      json = om.writeValueAsString(o);
    } catch (JsonProcessingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return json;
  }

  // 찜 목록 대시보드 이동
  @RequestMapping(value = "/dashboard_like_service.poom", method = RequestMethod.GET)
  public void dashboardLikeService() {

    System.out.println("GET /dashboard_like_service.poom");

  }// dashboardLikeService() end

  // 찜 목록 불러오기(ajax)
  @RequestMapping(value = "/ajax/getLikeService.json", method = RequestMethod.GET)
  @ResponseBody
  public List<Card> getLikeService(HttpSession session) {

    User loginUser = (User) session.getAttribute(UsersController.LOGIN);

    int likeServiceUserNo = loginUser.getNo();

    // System.out.println("loginUser no :"+likeServiceUserNo);

    return servicesService.getLikeServiceCard(likeServiceUserNo);

  }// getLikeService() end

  // 찜 목록에서 삭제(ajax)
  @RequestMapping(value = "/ajax/deleteLikeService.json")
  @ResponseBody
  public boolean deleteLikeService(@ModelAttribute LikeService likeService, HttpSession session) {

    System.out.println("POST /ajax/deleteLikeService.json");

    return servicesService.deleteLikeService(likeService);

  }// deleteLikeService() end

  // 계약 목록 대시보드 이동
  @RequestMapping(value = "/dashboard_contract.poom", method = RequestMethod.GET)
  public void dashboardContract() {

    System.out.println("GET /dashboard_contract.poom");

  }// dashboardContract() end

  // 계약에 해당하는 서비스카드 불러오기(ajax)
  @RequestMapping(value = "ajax/contractServiceCardList.json", method = RequestMethod.GET)
  @ResponseBody
  public List<Card> getContractServiceCardList(@RequestParam int contractStatus,
      @RequestParam String contractType, HttpSession session) {

    System.out.println("GET /ajax/contractServiceCardList.json");

    User user = (User) session.getAttribute("loginUser");

    int userNo = user.getNo();
    // System.out.println("userNo : "+userNo);

    List<Card> contractServiceCardList =
        servicesService.getContractServiceCardList(contractStatus, contractType, userNo);

    return contractServiceCardList;

  }// getContractServiceCardList() end

  // 각 서비스카드의 계약목록 불러오기
  @RequestMapping(value = "/ajax/contractList.json", method = RequestMethod.POST)
  @ResponseBody
  public Map<String, Object> getContractList(HttpSession session, @RequestParam int contractStatus,
      @RequestParam int cardNo) {

    System.out.println("POST /ajax/contractList.json");

    User user = (User) session.getAttribute("loginUser");

    int userNo = user.getNo();

    Map<String, Object> resultMap = servicesService.getContractList(contractStatus, cardNo, userNo);

    Service test = (Service) resultMap.get("serviceWithFullAddress");
    System.out.println(test.getCategory());

    return resultMap;
  };

  // 계약 수락/거절
  @RequestMapping(value = "/ajax/updateContractStatus.json", method = RequestMethod.POST)
  @ResponseBody
  public boolean updateContractStatus(@RequestParam int contractNo, @RequestParam int btnType) {

    return servicesService.updateContractStatus(contractNo, btnType);
  }// updateContractStatus() end

  // taker가 평점 남기기
  @RequestMapping(value = "/ajax/updateScoreFromTaker.poom", method = RequestMethod.POST)
  @ResponseBody
  public boolean updateScoreFromTaker(@ModelAttribute Contract contract, String reviewContent,
      HttpSession session) {
    System.out.println("POST /ajax/updateScoreFromTaker.poom");

    // System.out.println(contract.getScorePrice()+
    // "/"+contract.getScoreKind()+
    // "/"+contract.getScoreKnowhow()+
    // "/"+contract.getScoreHonest()+
    // "/"+contract.getNo()+
    // "/"+contract.getServiceNo()+
    // "/"+reviewContent);

    User loginUser = (User) session.getAttribute("loginUser");

    int userNo = loginUser.getNo();

    boolean result = servicesService.updateScoreFromTaker(contract, reviewContent, userNo);

    return result;
  }// updateScoreFromTaker() end

  // giver가 평점 남기기
  @RequestMapping(value = "/ajax/updateScoreFromGiver.poom", method = RequestMethod.POST)
  @ResponseBody
  public boolean updateScoreFromGiver(@ModelAttribute Contract contract, HttpSession session) {

    // System.out.println(contract.getScoreUser()+
    // "/"+contract.getNo()+
    // "/"+contract.getServiceNo());

    User loginUser = (User) session.getAttribute("loginUser");

    int userNo = loginUser.getNo();

    boolean result = servicesService.updateScoreFromGiver(contract, userNo);

    return result;
  }

  // giver가 사진 등록
  @RequestMapping(value = "/ajax/registerServicePhoto.poom", method = RequestMethod.POST)
  @ResponseBody
  public String registerServicePhoto(@RequestParam(required = false) Integer width,
      @RequestParam(required = false) Integer height, @RequestParam(required = false) Integer size,
      HttpSession session, MultipartFile upload, HttpServletRequest request) throws Exception {

    // 1) ServletContext얻기
    ServletContext sc = request.getServletContext();

    // 2) 기본경로 얻기
    String path = sc.getRealPath("");

    // 3) upload경로
    String uploadPath = path + "img/upload" + File.separator;

    // 4) image경로
    String resizePath = path + "img/service" + File.separator;

    // 5) 고유한 값을 위한 UUID
    UUID uuid = UUID.randomUUID();

    String ext = upload.getOriginalFilename();

    int dotIdx = ext.lastIndexOf(".");

    ext = ext.substring(dotIdx, ext.length());

    System.out.println(ext);

    String fileName = uuid + ext;

    // 6) 경로+파일이름
    String fullPath = uploadPath + fileName;

    // 7) 실제 생성될 파일
    File file = new File(fullPath);

    // 8) 파일 옮기기
    upload.transferTo(file);
    System.out.println("파일 생성 성공");
    // 9) 리사이징 (200x200)
    if (size != null) {
      width = size;
      height = size;
    }

    ResizeImageUtil.resize(fullPath, resizePath + fileName, width, height);

    return "{\"name\":\"" + fileName + "\"}";
  }

}
