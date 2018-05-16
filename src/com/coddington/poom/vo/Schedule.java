package com.coddington.poom.vo;

import java.sql.Timestamp;
import java.util.Calendar;
import com.coddington.poom.util.FieldUtil;

public class Schedule {
	private int no, serviceNo;
	private String type;// 실제 디비에서는 필요 없음, js 객체를 위해 사용

	private String serviceDay, serviceDateStr, serviceDayOfWeek, serviceDayHour, serviceDayHourExpression;

	private Timestamp serviceStartdate, serviceDate, regdate;

	private Calendar cal;

	public Schedule() {
		cal = Calendar.getInstance();

	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getType() {

		return this.serviceDay == null ? "single" : "repeat";
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getServiceNo() {
		return serviceNo;
	}

	public void setServiceNo(int serviceNo) {
		this.serviceNo = serviceNo;
	}

	public String getServiceDay() {
		return serviceDay;
	}

	public void setServiceDay(String serviceDay) {
		this.serviceDay = serviceDay;
	}
	
	public String getServiceDayOfWeek() {

		return this.serviceDay.substring(0, 3);
	}

	public String getServiceDayHour() {

		return this.serviceDay.substring(3, 5);
	}

	public String getServiceDayHourExpression() {

		int hour = Integer.parseInt(getServiceDayHour());		
		return String.format("%02d-%02d시", hour, hour + 1);
	}

	public Timestamp getServiceStartdate() {
		return serviceStartdate;
	}

	public void setServiceStartdate(Timestamp serviceStartdate) {
		this.serviceStartdate = serviceStartdate;
	}

	public Timestamp getServiceDate() {
		return serviceDate;
	}

	public int getServiceDateHour() {
		return cal.get(Calendar.HOUR_OF_DAY);
	}

	public void setServiceDate(Timestamp serviceDate) {
		this.serviceDate = serviceDate;
		cal.setTime(this.serviceDate);
	}

	public Timestamp getRegdate() {
		return regdate;
	}

	public void setRegdate(Timestamp regdate) {
		this.regdate = regdate;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return FieldUtil.getAllFields(this).toString();
	}
}
