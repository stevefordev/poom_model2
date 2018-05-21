package com.coddington.poom.vo;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class ContractSchedule {

	private int no, scheduleNo, contractNo;
	private Timestamp serviceDate, regdate;

	private Map<String, Object> serviceDateMap; // js 객체 위해서 만듬

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(int scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public int getContractNo() {
		return contractNo;
	}

	public void setContractNo(int contractNo) {
		this.contractNo = contractNo;
	}

	public Timestamp getServiceDate() {
		return serviceDate;
	}

	public void setServiceDate(Timestamp serviceDate) {
		this.serviceDate = serviceDate;
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	// serviceDate를 원하는 String형으로 가공하는 method
	// date와 fullHour 따로 넘겨주기 위해 리턴자료형 Map<>형
	public Map<String, Object> getServiceDateMap() {

		String fullString = this.serviceDate.toString();
		String date = fullString.substring(0, 10);
		String hourStr = fullString.substring(11, 13);
		int hour = Integer.parseInt(hourStr);
		String fullHour = "";

		if (hour > 12) {
			fullHour += "오후 " + (hour - 12) + "-" + (hour - 11) + "시";
		} else if (hour < 12) {
			fullHour += "오전 " + hour + "-" + (hour + 1) + "시";
		} else if (hour == 12) {
			fullHour += "오후 " + hour + "-1시";
		} // if~else if end

		Map<String, Object> serviceDateMap = new HashMap<String, Object>();

		serviceDateMap.put("date", date);
		serviceDateMap.put("fullHour", fullHour);

		return serviceDateMap;

	}

}
