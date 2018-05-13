package com.coddington.poom.vo;

import java.sql.Date;

import com.coddington.poom.util.FieldUtil;

public class Contract {
	
	private int no, giverNo, takerNo, serviceNo, poom, status,
				scoreUser, scorePrice, scoreKind, scoreKnowhow, scoreHonest;
	private String content;
	private Date regdate;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getGiverNo() {
		return giverNo;
	}
	public void setGiverNo(int giverNo) {
		this.giverNo = giverNo;
	}
	public int getTakerNo() {
		return takerNo;
	}
	public void setTakerNo(int takerNo) {
		this.takerNo = takerNo;
	}
	public int getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(int serviceNo) {
		this.serviceNo = serviceNo;
	}
	public int getPoom() {
		return poom;
	}
	public void setPoom(int poom) {
		this.poom = poom;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getScoreUser() {
		return scoreUser;
	}
	public void setScoreUser(int scoreUser) {
		this.scoreUser = scoreUser;
	}
	public int getScorePrice() {
		return scorePrice;
	}
	public void setScorePrice(int scorePrice) {
		this.scorePrice = scorePrice;
	}
	public int getScoreKind() {
		return scoreKind;
	}
	public void setScoreKind(int scoreKind) {
		this.scoreKind = scoreKind;
	}
	public int getScoreKnowhow() {
		return scoreKnowhow;
	}
	public void setScoreKnowhow(int scoreKnowhow) {
		this.scoreKnowhow = scoreKnowhow;
	}
	public int getScoreHonest() {
		return scoreHonest;
	}
	public void setScoreHonest(int scoreHonest) {
		this.scoreHonest = scoreHonest;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
