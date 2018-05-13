package com.coddington.poom.vo;

import java.sql.Timestamp;

public class Relationship {

	private int no, fromUserNo, toUserNo, type;
	private Timestamp regdate;
	
	//차단목록에 띄우기 위해 멤버필드 추가
	private String nickName, photoUrl;
	
	

	public Relationship() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhotoUrl() {
		return photoUrl;
	}

	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getFromUserNo() {
		return fromUserNo;
	}

	public void setFromUserNo(int fromUserNo) {
		this.fromUserNo = fromUserNo;
	}

	public int getToUserNo() {
		return toUserNo;
	}

	public void setToUserNo(int toUserNo) {
		this.toUserNo = toUserNo;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	
}
