package com.coddington.poom.service;

import com.coddington.poom.dao.ServicesDAO;

public class ServicesServiceImpl implements ServicesService {

	private ServicesDAO servicesDAO;
	
	public void setServicesDAO(ServicesDAO servicesDAO) {
		this.servicesDAO = servicesDAO;
	}
}
