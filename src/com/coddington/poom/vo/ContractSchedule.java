package com.coddington.poom.vo;

import java.sql.Timestamp;

public class ContractSchedule {

  private int no, scheduleNo, contractNo;
  private Timestamp serviceDate, regdate;
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

}
