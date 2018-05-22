package com.coddington.poom.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.coddington.poom.vo.Relationship;
import com.coddington.poom.vo.User;

public class UsersDAOImpl implements UsersDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}// setSession() end

	@Override
	public User selectLogin(User user) {
		// TODO Auto-generated method stub
		return session.selectOne("users.selectLogin", user);
	}

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return session.insert("users.insert", user);
	}

	// 로그인한 유저의 프로필의 이름과 내용을 출력함
	@Override
	public User selectProfile(int no) {
		return session.selectOne("users.selectProfile", no);
	}

	// 프로필 이름 수정
	@Override
	public int updateNickName(User user) {
		// TODO Auto-generated method stub
		return session.update("users.updateNickName", user);
	}

	// 프로필 내용 수정
	@Override
	public int updateIntroduction(User user) {
		// TODO Auto-generated method stub
		return session.update("users.updateIntroduction", user);
	}

	@Override
	public int update(User user) {
		// TODO Auto-generated method stub
		return session.update("users.update", user);
	}

	// 팔로잉 리스트 목록을 출력하고 페이징 처리 해줌
	@Override
	public List<User> selectFollowingList(Map<String, Object> pageUserList) {
		return session.selectList("users.selectFollowingList", pageUserList);
	}

	// select 팔로잉 to_user_no(팔로잉 당하는 유저)의 카운트 해서 from_user_no인 유저가
	// 팔로잉 한 유저를 버튼색깔 변화로 알 수 있게 하는 부분
	@Override
	public int selectConfirmFollowing(Relationship relationship) {
		return session.selectOne("users.selectConfirmFollowing", relationship);
	}

	// 유저 차단 카운트 하기
	@Override
	public int selectConfirmBlock(Relationship relationship) {
		return session.selectOne("users.selectConfirmBlock", relationship);
	}

	@Override
	public int updateCoinAmount(User user) {
		// TODO Auto-generated method stub
		return session.update("users.updateCoinAmount", user);
	}
}
