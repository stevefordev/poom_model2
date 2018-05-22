package com.coddington.poom.dao;

import java.util.List;
import java.util.Map;

import com.coddington.poom.vo.Relationship;
import com.coddington.poom.vo.User;

public interface UsersDAO {
	public User selectLogin(User user);

	public int insert(User user);

	// 프로필의 이름과 내용 부분을 DB에서 정보를 가져와서 출력
	public User selectProfile(int no);

	// 프로필 이름 수정 부분
	public int updateNickName(User user);

	// 프로필 내용 수정 부분
	public int updateIntroduction(User user);
	
	// 업데이트 전체
	public int update(User user);
	
	// 팔로우 유저 리스트 출력하고 페이징 부분 추가
	public List<User> selectFollowingList(Map<String, Object> pageUserList);

	// select 팔로잉 to_user_no(팔로잉 당하는 유저)의 카운트 해서 from_user_no인 유저가
	// 팔로잉 한 유저를 버튼색깔 변화로 알 수 있게 하는 부분
	public int selectConfirmFollowing(Relationship relationship);

	// 유저 차단 카운트 하기
	public int selectConfirmBlock(Relationship relationship);

	public int updateCoinAmount(User user);
}
