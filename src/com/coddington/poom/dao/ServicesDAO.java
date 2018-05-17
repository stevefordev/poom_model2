package com.coddington.poom.dao;

import com.coddington.poom.vo.Service;

public interface ServicesDAO {

	public Service selectByNo(int no);
	
	public Service selectByServiceNoAndUserNo(Service service);
	
	public int insert(Service service);
	
	public int update(Service service); 
}
