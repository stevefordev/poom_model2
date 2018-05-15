package com.coddington.poom.vo;

import java.sql.Date;
import com.coddington.poom.util.FieldUtil;
import java.util.Arrays;

public class Service {
	public enum Category {

		EDU(1, "교육"), HOUSE(2, "가사"), DELIVERY(3, "심부름");

		private int code;
		private String title;

		Category(int code, String title) {
			this.code = code;
			this.title = title;
		}

		public int getCode() {
			return code;
		}

		public String getTitle() {
			return title;
		}

		public static Category find(int code) {
			return Arrays.stream(Category.values()).filter(type -> type.getCode() == code).findFirst().get();
		}
		
		public static Category find(String title) {
			return Arrays.stream(Category.values()).filter(type -> type.getTitle().equals(title)).findFirst().get();
		}
	}

	private int no, userNo, poom, category, role;
	private String title, area1, area2, detailAddress1, detailAddress2, latitude, longitude, content, photoUrl,
			userNickName, userPhotoUrl;
	private Date regdate;

	public int getNo() {
		
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUserNo() {
		return userNo;
	}

	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}

	public int getPoom() {
		return poom;
	}

	public void setPoom(int poom) {
		this.poom = poom;
	}

	public int getCategory() {
		return category;
	}

	public void setCategory(int category) {
		this.category = category;
	}

	public void setCategory(String category) {

		this.category = Category.find(category).getCode();		
	}

	public int getRole() {
		return role;
	}

	public char getRoleChar() {
		return role == 1 ? 'g' : 't';
	}

	public void setRole(int role) {
		this.role = role;
	}

	// g(giver) = 1, t(taker) = 2
	public void setRole(String r) {
		// @steve 차라리 디비 컬럼 자료형을 char 하는것이 더 나을듯
		this.role = r.equals("g") ? 1 : 2;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArea1() {
		return area1;
	}

	public void setArea1(String area1) {
		this.area1 = area1;
	}

	public String getArea2() {
		return area2;
	}

	public void setArea2(String area2) {
		this.area2 = area2;
	}

	public String getDetailAddress1() {
		return detailAddress1;
	}

	public void setDetailAddress1(String detailAddress1) {
		this.detailAddress1 = detailAddress1;
	}

	public String getDetailAddress2() {
		return detailAddress2;
	}

	public void setDetailAddress2(String detailAddress2) {
		this.detailAddress2 = detailAddress2;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return FieldUtil.getAllFields(this).toString();
	}
}
