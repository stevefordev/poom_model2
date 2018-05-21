package com.coddington.poom.dao;

import java.util.List;

import com.coddington.poom.vo.Relationship;

public interface RelationshipsDAO {

	// 팔로잉 리스트 페이지네이션 처리 하는 부분
	public int selectTotal(int fromUserNo);

	// 팔로우 버튼을 클릭했을때 버튼색이 변화하며 팔로우가 목록 리스트에 추가되게 해주는 부분
	public int insertFollowing(Relationship relationship);

	// 팔로잉 버튼을 클릭했을때 버튼색이 원래대로 돌아오며 목록 리스트에 삭제되게 해주는 부분
	public int deleteFollowing(Relationship relationship);

	// 차단 버튼 클릭시 리스트에 추가 됨
	public int insertBlock(Relationship relationship);

	// 차단 해제 버튼 클릭시 삭제됨
	public int deleteBlock(Relationship relationship);
	 
	public List<Relationship> selectBlockList(int fromUserNo);
	
	/**
	 * 
	 * @param no relationship no
	 * @return
	 */
	public int deleteByNo(int no);

}
