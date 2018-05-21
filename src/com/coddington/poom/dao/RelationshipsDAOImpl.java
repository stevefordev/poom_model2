package com.coddington.poom.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.coddington.poom.vo.Relationship;

public class RelationshipsDAOImpl implements RelationshipsDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}// setSession() end
 
	// 팔로잉 리스트 페이지네이션 처리 하는 부분
	public int selectTotal(int fromUserNo) {
		return session.selectOne("relationships.selectTotal", fromUserNo);
	}

	// 팔로우 버튼을 클릭했을때 버튼색이 변화하며 팔로우가 목록 리스트에 추가되게 해주는 부분
	@Override
	public int insertFollowing(Relationship relationship) {
		return session.insert("relationships.insertFollowing", relationship);
	}

	// //팔로잉 버튼을 클릭했을때 버튼색이 원래대로 돌아오며 목록 리스트에 삭제되게 해주는 부분
	@Override
	public int deleteFollowing(Relationship relationship) {
		// TODO Auto-generated method stub
		return session.delete("relationships.deleteFollowing", relationship);
	}

	// 차단 버튼 클릭시 리스트에 추가 됨
	@Override
	public int insertBlock(Relationship relationship) {
		// TODO Auto-generated method stub
		return session.insert("relationships.insertBlock", relationship);
	}

	// 차단 해제 버튼 클릭시 삭제됨
	@Override
	public int deleteBlock(Relationship relationship) {
		// TODO Auto-generated method stub
		return session.delete("relationships.deleteBlock", relationship);
	}
	 
	@Override
	public List<Relationship> selectBlockList(int fromUserNo) {
		// TODO Auto-generated method stub
		return session.selectList("relationships.selectBlockList", fromUserNo);
	}//selectBlockList() end
	
	@Override
	public int deleteByNo(int no) {
		// TODO Auto-generated method stub
		return session.delete("relationships.deleteByNo", no);
	}//deleteByNo() end

}
