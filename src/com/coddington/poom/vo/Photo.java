package com.coddington.poom.vo;

import java.sql.Timestamp;

public class Photo {

  private int no, serviceNo;
  private String filename;
  private Timestamp regdate;

  public Photo() {
    // TODO Auto-generated constructor stub
  }

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

  public String getFilename() {
    return filename;
  }

  public void setFilename(String filename) {
    this.filename = filename;
  }

  public Timestamp getRegdate() {
    return regdate;
  }

  public void setRegdate(Timestamp regdate) {
    this.regdate = regdate;
  }


}
