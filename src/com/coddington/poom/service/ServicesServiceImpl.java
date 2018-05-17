package com.coddington.poom.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import com.coddington.poom.dao.ContractsDAO;
import com.coddington.poom.dao.QuestionsDAO;
import com.coddington.poom.dao.ReviewsDAO;
import com.coddington.poom.dao.SchedulesDAO;
import com.coddington.poom.dao.ServicesDAO;
import com.coddington.poom.dao.TagsDAO;
import com.coddington.poom.vo.Card;
import com.coddington.poom.vo.Service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ServicesServiceImpl implements ServicesService {

	private ServicesDAO servicesDAO;
	private ContractsDAO contractsDAO;
	private TagsDAO tagsDAO;
	private ReviewsDAO reviewsDAO;
	private QuestionsDAO questionsDAO;
	private SchedulesDAO schedulesDAO;

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
		 * System.out.print(entry.getKey()+":"); System.out.println(entry.getValue()); }
		 * ;
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
		 * serviced.setNo(Integer.parseInt(noStr));
		 * serviced.setUserNo(loginUser.getNo()); Service service =
		 * ServicesDAO.selectByServiceNoAndUserNo(serviced);
		 * 
		 * List<Tag> tags = TagsDAO.selectListByServiceNo(service.getNo());
		 * 
		 * List<Schedule> schedules =
		 * SchedulesDAO.selectListByServiceNo(service.getNo());
		 * 
		 * ObjectMapper mapper = new ObjectMapper(); SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); mapper.setDateFormat(sdf); String
		 * scheduleJson = mapper.writeValueAsString(schedules);
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
}
