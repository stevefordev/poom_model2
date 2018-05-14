package com.coddington.poom.service;

import java.util.HashMap;
import java.util.Map;

import com.coddington.poom.dao.ServicesDAO;
import com.coddington.poom.dao.TagsDAO;

public class ServicesServiceImpl implements ServicesService {

	private ServicesDAO servicesDAO;
	private TagsDAO tagsDAO;
	public void setServicesDAO(ServicesDAO servicesDAO) {
		this.servicesDAO = servicesDAO;
	}
	
	public void setTagsDAO(TagsDAO tagsDAO) {
		this.tagsDAO = tagsDAO;
	}
	
	@Override
	public Map<String, Object> getDetails(int no) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap();
		
		map.put("service", servicesDAO.selectByNo(no));
		map.put("tags", tagsDAO.selectListByServiceNo(no));
		//map.put("photos", servicesDAO.selectByNo(no));
		
		return map;
	}
}
