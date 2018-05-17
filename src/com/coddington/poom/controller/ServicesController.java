package com.coddington.poom.controller;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.coddington.poom.service.ServicesService;
import com.coddington.poom.vo.Question;
import com.coddington.poom.vo.Service;
import com.coddington.poom.vo.User;

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

	@RequestMapping(value = "details.poom")
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

	@RequestMapping(value = "/service.poom", method = RequestMethod.POST)
	public String register(Service service, String[] tags, HttpSession session) {
		
		/* 
		  area1: 서울
		  area2: 관악구
		  latitude: 37.48130258426689
		  longitude: 126.95285338253898
		  scheduleList: [{"type":"single","serviceStartdate":"","serviceDate":"2018-04-17 04:00:00"}]
		  photo: edu/1.jpg
		  category: edu
		  role: g
		  title: ssssss
		  detailAddress1: 서울 관악구 봉천동 979-1
		  detailAddress2: 
		  tag: #교육,#수영,#여름
		  
		  poom: 10
		  startDate: 2018-04-17
		  contents: <p>asgdfgdfh</p>
		  */

		  User loginUser = (User) session.getAttribute("loginUser");

		  System.out.println(service.toString());

/*		  // 서비스 입력
		  ServicesDAO.insert(service);

		  // 서비스 태그 입력
		  // 태그 입력 당시에 ajax 로  새로 입련된 태그에 대해서 먼저 삽입하고
		  // 폼 서브밋 당시에는 태그 no 만 가지고 와서 servicetag 에 정보 삽입
		  for (String eachTagId : tags) {

		    ServiceTag serviceTag = new ServiceTag();
		    serviceTag.setServiceNo(service.getNo());
		    serviceTag.setTagNo(Integer.parseInt(eachTagId));
		    ServiceTagsDAO.insert(serviceTag);
		  }


		  System.out.println("scheduleList:" + scheduleList);

		  // 서비스 일정 입력
		  ObjectMapper mapper = new ObjectMapper();
		  //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		  //mapper.setDateFormat(sdf);

		  List<Schedule> schedules =
		      mapper.readValue(scheduleList, new TypeReference<List<Schedule>>() {});
		  
		  for (Schedule eachSchedule : schedules) {    
		  
		    //System.out.println(eachSchedule.getServiceDate());
		    //System.out.println(eachSchedule.getServiceDateHour());
		    eachSchedule.setServiceNo(service.getNo());    
		    SchedulesDAO.insert(eachSchedule);
		  }
		  response.sendRedirect("index.jsp");
*/
		return "register_service_form";
	}

	@RequestMapping(value = "/service/registerForm.poom")
	public String registerForm() {

		return "register_service_form";
	}

	@RequestMapping(value = "/service/updateForm.poom")
	public String update(@RequestParam(value = "no") int serviceNo, Model model, HttpSession session) {

		User loginUser = (User) session.getAttribute("loginUser");
		System.out.println(loginUser.getNo());
		System.out.println(serviceNo);
		model.addAllAttributes(servicesService.getService(loginUser.getNo(), serviceNo));

		return "update_service_form";
	}

}
