package com.coddington.poom.service;

import java.util.HashMap;
import java.util.Map;

import com.coddington.poom.dao.RelationshipsDAO;
import com.coddington.poom.vo.Relationship;

public class RelationshipsServiceImpl implements RelationshipsService {

	private RelationshipsDAO relationshipsDAO;

	public void setRelationshipsDAO(RelationshipsDAO relationshipsDAO) {
		this.relationshipsDAO = relationshipsDAO;
	}

	// 팔로우의 버튼을 클릭시 팔로잉이 되어 리스트에 추가됨
	@Override
	public boolean registerFollowing(int toUserNo, int fromUerNo) {
		Relationship relationship = new Relationship();
		relationship.setFromUserNo(fromUerNo);
		relationship.setToUserNo(toUserNo);
		return 1 == relationshipsDAO.insertFollowing(relationship);
	}

	// //팔로잉으 버튼을 클릭시 팔로잉 취소가 되어 리스트 에서 삭제됨
	@Override
	public boolean removeFollowing(int toUserNo, int fromUerNo) {
		Relationship relationship = new Relationship();
		// TODO Auto-generated method stub
		relationship.setFromUserNo(fromUerNo);
		relationship.setToUserNo(toUserNo);
		return 1 == relationshipsDAO.deleteFollowing(relationship);
	}

	// 차단 버튼 클릭시 리스트에 추가 됨
	@Override
	public boolean registerBlock(int toUserNo, int fromUerNo) {
		Relationship relationship = new Relationship();
		// TODO Auto-generated method stub
		relationship.setFromUserNo(fromUerNo);
		relationship.setToUserNo(toUserNo);
		return 1 == relationshipsDAO.insertBlock(relationship);
	}

	// 차단 해제 버튼 클릭시 삭제됨
	@Override
	public boolean removeBlock(int toUserNo, int fromUerNo) {
		Relationship relationship = new Relationship();
		// TODO Auto-generated method stub
		relationship.setFromUserNo(fromUerNo);
		relationship.setToUserNo(toUserNo);
		return 1 == relationshipsDAO.deleteBlock(relationship);
	}

}// RelationshipsServiceImpl end
