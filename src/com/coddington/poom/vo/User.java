package com.coddington.poom.vo;

import java.sql.Date;

import com.coddington.poom.util.FieldUtil;

public class User {

	public static final String LOGIN_USER = "loginUser";
	public static final String LOGIN_ERROR_MESSAGE = "loginErrorMessage" ;
	private int no, coin;
	private String email, password, name, nickName, phone, introduction, photoUrl;
	private Date regdate;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	
	public User(String email, String password, String name, String nickName, String phone,
			String introduction, String photoUrl,int coin, Date regdate) {
		super();
		this.coin = coin;
		this.email = email;
		this.password = password;
		this.name = name;
		this.nickName = nickName;
		this.phone = phone;
		this.introduction = introduction;
		this.photoUrl = photoUrl;
		this.regdate = regdate;
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getIntroduction() {
		return introduction;
	}
	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return FieldUtil.getAllFields(this).toString();
	} 
}
