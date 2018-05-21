package com.coddington.poom.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coddington.poom.dao.RelationshipsDAO;
import com.coddington.poom.dao.UsersDAO;
import com.coddington.poom.util.PaginateUtil;
import com.coddington.poom.vo.Relationship;
import com.coddington.poom.vo.User;

public class UsersServiceImpl implements UsersService {

	private UsersDAO usersDAO;
	private RelationshipsDAO relationshipsDAO;

	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}

	public void setRelationshipsDAO(RelationshipsDAO relationshipsDAO) {
		this.relationshipsDAO = relationshipsDAO;
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return usersDAO.selectLogin(user);
	}

	@Override
	public boolean join(User user) {
		return 1 == usersDAO.insert(user);
	}

	// 프로필 이름수정 부분
	@Override
	public boolean ModifyUserNickname(User user) {
		// TODO Auto-generated method stub
		return 1 == usersDAO.updateNickName(user);
	}

	// 프로필 내용수정 부분
	@Override
	public boolean ModifyUserIntroduction(User user) {
		// TODO Auto-generated method stub
		return 1 == usersDAO.updateIntroduction(user);
	}

	@Override
	public boolean ModifyUser(User user) {
		// TODO Auto-generated method stub
		return 1 == usersDAO.update(user);
	}

	// 로그인한 유저의 번호와 팔로잉 리스트 페이징 처리 프로필의 from_user_no(팔로잉 하는 자)와
	// to_user_no(팔로잉 당하는자) 의 번호를 얻는다 map을 사용한 이유는 vo에 없는 인자도 있어서
	// map타입으로 정해서 했습니다.

	@Override
	public Map<String, Object> getUserInformation(int profileUserNo, int page, int loginUserNo) {
		// 페이지 나타낼 개수와 페이지를 설정
		int numPage = 12;
		int numBlock = 3;
		int end = page * numPage;
		int start = end - (numPage - 1);

		// 팔로잉한 유저의 리스트를 생성하고 페이징 처리(인자4개)
		Map<String, Object> pageUserList = new HashMap<String, Object>();
		pageUserList.put("start", start);
		pageUserList.put("end", end);
		pageUserList.put("fromUserNo", profileUserNo);

		// 팔로잉 리스트 출력 부분
		List<User> followlist = usersDAO.selectFollowingList(pageUserList);

		// 유저의 프로필의 이름,내용,팔로잉한 유저의 리스트
		Map<String, Object> userProfile = new HashMap<String, Object>();
		userProfile.put("profile", usersDAO.selectProfile(profileUserNo));

		// 팔로잉 리스트 부분
		userProfile.put("followList", followlist);
		// 팔로잉한 유저와 팔로잉 당한 유저의 번호를 세팅
		Relationship relationship = new Relationship();
		relationship.setFromUserNo(loginUserNo);
		relationship.setToUserNo(profileUserNo);

		// 팔로우를 한것을 알 수 있게 버튼색의 변화에 따라 알 수 있게 해주는 부분 1이었을떄 true
		// 0일때 false 이다 그러므로 1일때 팔로잉 당한것으로 버튼색이 변화 하며 0일때는 버튼의 색이 변화가
		// 없다
		// 팔로잉 버튼 부분
		int checkFollowing = usersDAO.selectConfirmFollowing(relationship);
		System.out.println("팔로우를 했는지???:" + checkFollowing);
		userProfile.put("confirmFollowing", 1 == checkFollowing);

		// 차단 버튼 부분
		int checkBlock = usersDAO.selectConfirmBlock(relationship);
		System.out.println("차단을 했는지???:" + checkBlock);
		userProfile.put("confirmBlock", 1 == checkBlock);

		// 페이지네이션 처리
		int total = relationshipsDAO.selectTotal(profileUserNo);
		String url = "/profile.poom";
		String param = "no=" + profileUserNo + "&fpage=";
		String paginate = PaginateUtil.getPaginate(page, total, numPage, numBlock, url, param);
		userProfile.put("paginate", paginate);
		return userProfile;
	}// getUserInformation end
	 
	//차단목록 불러오기
	@Override
	public List<Relationship> blockList(int fromUserNo) {
		// TODO Auto-generated method stub
		return relationshipsDAO.selectBlockList(fromUserNo);
	}
	
	//차단목록에서 삭제
	@Override
	public boolean deleteBlock(int no) {
		// TODO Auto-generated method stub
		return 1==relationshipsDAO.deleteByNo(no);
	}
}
