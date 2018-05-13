package com.coddington.poom.vo;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * 카드 정보 호출을 위한 VO
 * @author Administrator
 *
 */
public class Card {

	private String nickname, title, tags, area1, 
  				   area2, schedule, contract, icon, profilePic;
	private int no, countLike, scoreTotal, countScore, poom, role;
	private List<String> photos;
	private boolean isLike;
	private Timestamp regdate;
  
	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}


	public String getNickname() {
		return nickname;
	}
	
	public void setNickName(String nickName) {
		this.nickname = nickName;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTags() {
		return tags;
	}
	
	public void setTags(String tags) {
		this.tags = tags;
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
	
	public String getSchedule() {
		return schedule;
	}
	
	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}
	
	public String getContract() {
		return contract;
	}
	
	public void setContract(String contract) {
		this.contract = contract;
	}
	
	public String getIcon() {
		return icon;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String getProfilePic() {
		return profilePic;
	}
	
	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}
	
	public int getCountLike() {
		return countLike;
	}
	
	public void setCountLike(int countLike) {
		this.countLike = countLike;
	}
	
	public int getScoreTotal() {
		return scoreTotal;
	}
	
	public void setScoreTotal(int scoreTotal) {
		this.scoreTotal = scoreTotal;
	}
	
	public int getCountScore() {
		return countScore;
	}
	
	public void setCountScore(int countScore) {
		this.countScore = countScore;
	}
	
	public int getPoom() {
		return poom;
	}
	
	public void setPoom(int poom) {
		this.poom = poom;
	}
	
	public List<String> getPhotos() {
		return photos;
	}
	
	public void setPhotos(List<String> photos) {
		this.photos = photos;
	}
	
	public boolean getIsLike() {
		return isLike;
	}
	
	public void setIsLike(boolean isLike) {
		this.isLike = isLike;
	}


	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}


	public Timestamp getRegdate() {
		return regdate;
	}


	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}
	
	
		 
	
   
  
}
