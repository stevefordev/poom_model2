package com.coddington.poom.dao;

import java.util.List;

import com.coddington.poom.vo.Card;
import com.coddington.poom.vo.Service;

public interface ServicesDAO {

	public Service selectByNo(int no);
	
	public Service selectByServiceNoAndUserNo(Service service);
	
	public int insert(Service service);
	
	public int update(Service service); 
	
	//추천 목록 불러오기
	public List<Card> selectRecommendationList(int role);
}
