package com.coddington.poom.dao;

import java.util.List;
import java.util.Map;

import com.coddington.poom.vo.Card;
import com.coddington.poom.vo.Service;

public interface ServicesDAO {

	public Service selectByNo(int no);

	public Service selectByServiceNoAndUserNo(Service service);

	public int insert(Service service);

	public int update(Service service);

	// 추천 목록 불러오기
	public List<Card> selectRecommendationList(int role);
  
	// 찜 목록 불러오기
	public List<Card> selectLikeServicesByLikeServiceUserNo(int likeServiceUserNo);

	// 원하는 계약이 있는 서비스카드 불러오기
	public List<Card> selectContractServiceCardList(Map<String, Object> map);

	public Service selectFullAddress(int serviceNo);
	 
	//serchPage 카드목록 불러오기(2)
	public List<Card> selectCardList(Map<String, Object> map);

	public List<Card> selectUserServiceList(Map<String, Object> map);
}
