package com.coddington.poom.service;

import java.util.Map;
import com.coddington.poom.vo.Question;

public interface ServicesService {

	public Map<String, Object> getDetails(int no);
	
	public Map<String, Object> getService(int userNo, int serviceNo);
		
}
