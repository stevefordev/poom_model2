package com.coddington.poom.vo;

import java.sql.Date;
import java.sql.Timestamp;

public class Review {
	private int no, serviceNo, userNo, type;
	private String content, reply, userNickName, userPhotoUrl;
	private Timestamp regdate;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(int serviceNo) {
		this.serviceNo = serviceNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public Timestamp getRegdate() {
		return regdate;
	}
	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
  public String getUserNickName() {
    return userNickName;
  }
  public void setUserNickName(String userNickName) {
    this.userNickName = userNickName;
  }
  public String getUserPhotoUrl() {
    return userPhotoUrl;
  }
  public void setUserPhotoUrl(String userPhotoUrl) {
    this.userPhotoUrl = userPhotoUrl;
  }
	
	
}
