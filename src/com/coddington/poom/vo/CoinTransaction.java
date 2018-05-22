package com.coddington.poom.vo;

import java.sql.Date;

import com.coddington.poom.vo.CoinTransaction.TransactionType;
import java.util.Arrays;
import java.util.List;

public class CoinTransaction {
	public enum TransactionType {
		EXCHANGE(11, "환전"), CHARGE(12, "충전"), INCOME(21, "수익"), OUTCOME(22, "지출"), REVIEW(31, "리뷰"), REPLY(32, "답글");
		private int code;
		private String title;

		TransactionType(int code, String title) {
			this.code = code;
			this.title = title;
		}

		public int getCode() {
			return code;
		}

		public String getTitle() {
			return title;
		}

		public static TransactionType findByCode(int code) {
			return Arrays.stream(TransactionType.values()).filter(type -> type.getCode() == code).findFirst().get();
		}
	}

	private int no, userNo, contractNo, type, coin, money, serviceNo;
	private List<Tag> tags;
	private String nickname;// users테이블 조인한 컬럼
	private Date regdate;

	public int getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(int serviceNo) {
		this.serviceNo = serviceNo;
	}

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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
