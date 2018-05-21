package com.coddington.poom.service;

import java.util.Map;

public interface RelationshipsService {

	// 
	public boolean registerFollowing(int toUserNo, int fromUserNo);

	public boolean removeFollowing(int toUserNo, int fromUserNo);

	// 차단 버튼 클릭시 리스트에 추가 됨
	public boolean registerBlock(int toUserNo, int fromUserNo);

	// 차단 해제 버튼 클릭시 삭제됨
	public boolean removeBlock(int toUserNo, int fromUserNo);

}// RelationshipsService end
