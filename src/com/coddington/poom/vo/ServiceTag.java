package com.coddington.poom.vo;

import java.sql.Date;

public class ServiceTag {
  private int no, serviceNo, tagNo;
  private Date regdate;
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
  public int getTagNo() {
    return tagNo;
  }
  public void setTagNo(int tagNo) {
    this.tagNo = tagNo;
  }
  public Date getRegdate() {
    return regdate;
  }
  public void setRegdate(Date regdate) {
    this.regdate = regdate;
  }
}
