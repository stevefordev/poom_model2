package com.coddington.poom.dao;

import org.apache.ibatis.session.SqlSession;

import com.coddington.poom.vo.LikeService;

public class LikeServicesDAOImpl implements LikeServicesDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}// setSession() end

	@Override
	public int selectTotalLikeNumByServiceNo(int serviceNo) {
		// TODO Auto-generated method stub
		return session.selectOne("likeServices.selectTotalLikeNumByServiceNo", serviceNo);
	}// selectTotalLikeNumByServiceNo() end

	@Override
	public LikeService selectByUserNoServiceNo(LikeService likeServiceWithUserNoServiceNo) {
		// TODO Auto-generated method stub
		return session.selectOne("likeServices.selectByUserNoServiceNo", likeServiceWithUserNoServiceNo);
	}// selectByUserNoServiceNo

	@Override
	public int insertLikeService(LikeService likeService) {
		// TODO Auto-generated method stub
		return session.insert("likeServices.insertLikeService", likeService);
	}// insertLikeService

	@Override
	public int deleteLikeService(LikeService likeService) {
		// TODO Auto-generated method stub
		return session.delete("likeServices.deleteLikeService", likeService);
	}
}
