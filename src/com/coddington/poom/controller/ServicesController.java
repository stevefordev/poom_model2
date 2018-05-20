package com.coddington.poom.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

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
import com.coddington.poom.service.ServicesService;
import com.coddington.poom.vo.Card;
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

	@RequestMapping(value = { "/", "/index.poom" })
	public String index() {
		return "index";
	}

	/**
	 * 추천 목록 호출
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
	 * 서비스 상세
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
	 * @return
	 */
	@RequestMapping(value = "/service/registerForm.poom")
	public String registerForm() {

		return "register_service_form";
	}

	/**
	 * 서비스 수정 폼
	 * @param serviceNo
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/service/updateForm.poom")
	public String registerForm(@RequestParam(value = "no") int serviceNo, Model model, HttpSession session) {

		User loginUser = (User) session.getAttribute(UsersController.LOGIN);
		System.out.println(loginUser.getNo());
		System.out.println(serviceNo);
		model.addAllAttributes(servicesService.getService(loginUser.getNo(), serviceNo));

		return "update_service_form";
	}

	/**
	 * 서비스 등록
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
			@ModelAttribute Service service, BindingResult bindingResult, @RequestParam(value = "tags") int[] tagNos,
			String photo, HttpSession session) {

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
	 * @param scheduleListJson
	 * @param service
	 * @param bindingResult
	 * @param tagNos
	 * @param photo
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/serviceUpdate.poom", method = RequestMethod.POST)
	public String update(@RequestParam(value = "scheduleList") String scheduleListJson, @ModelAttribute Service service,
			BindingResult bindingResult, @RequestParam(value = "tags") int[] tagNos, String photo,
			HttpSession session) {

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

}
