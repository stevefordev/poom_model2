package com.coddington.poom.vo;

import java.sql.Date;

public class CoinTransaction {

	private int no, userNo, contractNo, type, coin, money, serviceNo;
	public int getServiceNo() {
		return serviceNo;
	}
	public void setServiceNo(int serviceNo) {
		this.serviceNo = serviceNo;
	}
	private String nickname;//users테이블 조인한 컬럼
	private Date regDate;
	public CoinTransaction() {
		// TODO Auto-generated constructor stub
	}
	public CoinTransaction(int no, int type) {
		super();
		this.no = no;
		this.type = type;
	}
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
	public int getContractNo() {
		return contractNo;
	}
	public void setContractNo(int contractNo) {
		this.contractNo = contractNo;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getCoin() {
		return coin;
	}
	public void setCoin(int coin) {
		this.coin = coin;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

}
