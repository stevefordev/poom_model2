package com.coddington.poom.service;

import java.util.List;
import java.util.Map;

import com.coddington.poom.vo.Relationship;
import com.coddington.poom.vo.User;

public interface UsersService {

	public User login(User user);

	public boolean join(User user);

	// 프로필 이름 수정 출력
	public boolean ModifyUserNickname(User user);

	// 프로필 내용 수정 출력
	public boolean ModifyUserIntroduction(User user);
	
	// 유저 정보 전체 수정
	public boolean ModifyUser(User user);

	// 로그인한 유저의 번호와 팔로잉 리스트 페이징 처리 프로필의 from_user_no(팔로잉 하는 자)와
	// to_user_no(팔로잉 당하는자) 의 번호를 얻는다 map을 사용한 이유는 vo에 없는 인자도 있어서
	// map타입으로 정해서 했습니다.
	public Map<String, Object> getUserInformation(int profileUserNo, int page, int loginUserNo);
	 
	public List<Relationship> blockList(int fromUserNo);
	
	public boolean deleteBlock(int no);
}
