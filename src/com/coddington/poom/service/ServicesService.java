package com.coddington.poom.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import com.coddington.poom.vo.Card;
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
}
