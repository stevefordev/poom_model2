package com.coddington.poom.service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.coddington.poom.vo.Card;
import com.coddington.poom.vo.Contract;
import com.coddington.poom.vo.LikeService;
import com.coddington.poom.vo.Question;
import com.coddington.poom.vo.Service;
import com.coddington.poom.vo.Tag;

public interface ServicesService {

	public Map<String, Object> getDetails(int no);

	public Map<String, Object> getService(int userNo, int serviceNo);

	public List<Tag> getTags(String name);

	public Tag getTagByName(String name);

	public int register(Service service, int[] tagNos, String scheduleListJson);

	public boolean modify(Service service, int[] tagNos, String scheduleListJson);

	public boolean removeSchedule(int scheduleNo);

	public List<Card> getRecommendationServiceCard(int role, int userNo);

	public Map<String, Object> registerLikeSevice(LikeService likeService);

	public Map<String, Object> deleteLikeSevice(LikeService likeService);

	public boolean checkLikeSevice(LikeService likeService);

	public List<Card> getLikeServiceCard(int likeServiceUserNo);

	public boolean deleteLikeService(LikeService likeService);

	public List<Card> getContractServiceCardList(int contractStatus, String contractType, int userNo);

	public Map<String, Object> getContractList(int contractStatus, int cardNo, int userNo);

	public boolean updateContractStatus(int contractNo, int btnType);

	public boolean updateScoreFromTaker(Contract contract, String reviewContent, int userNo, int coinAmount, int giverNo);

	public boolean updateScoreFromGiver(Contract contract, int userNo, int coinAmount, int takerNo);
	
	public List<Card> getServiceCard(Service service, Date serviceDate, int term, int score, String order);
	
}
