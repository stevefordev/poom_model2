package com.coddington.poom.dao;

import com.coddington.poom.vo.LikeService;

public interface LikeServicesDAO {

	public int selectTotalLikeNumByServiceNo(int serviceNo);

	public LikeService selectByUserNoServiceNo(LikeService likeServiceWithUserNoServiceNo);

	public int insertLikeService(LikeService likeService);

	public int deleteLikeService(LikeService likeService);
}
