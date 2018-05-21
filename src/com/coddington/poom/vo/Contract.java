package com.coddington.poom.vo;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import com.coddington.poom.util.FieldUtil;

public class Contract {

  private int no, giverNo, takerNo, serviceNo, poom, status, scoreUser, scorePrice, scoreKind,
      scoreKnowhow, scoreHonest;
  private String content;
  private Timestamp regdate;

  private String giverNickname, giverProfile, takerNickname, takerProfile; // users 테이블과의 join
  private String servicePhoto;
  private List<ContractSchedule> contractSchedules;
  private List<ContractSchedule> contractScheduleList;// contractSchedule 리스트 담을 멤버필드 선언

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

  public Timestamp getRegdate() {
    return regdate;
  }

  public void setRegdate(Timestamp regdate) {
    this.regdate = regdate;
  }

  public List<ContractSchedule> getContractSchedules() {
    return contractSchedules;
  }

  public void setContractSchedules(List<ContractSchedule> contractSchedules) {
    this.contractSchedules = contractSchedules;
  }

  @Override
  public String toString() {
    // TODO Auto-generated method stub
    return FieldUtil.getAllFields(this).toString();
  }

  public String getGiverNickname() {
    return giverNickname;
  }

  public void setGiverNickname(String giverNickname) {
    this.giverNickname = giverNickname;
  }

  public String getGiverProfile() {
    return giverProfile;
  }

  public void setGiverProfile(String giverProfile) {
    this.giverProfile = giverProfile;
  }

  public String getTakerNickname() {
    return takerNickname;
  }

  public void setTakerNickname(String takerNickname) {
    this.takerNickname = takerNickname;
  }

  public String getTakerProfile() {
    return takerProfile;
  }

  public void setTakerProfile(String takerProfile) {
    this.takerProfile = takerProfile;
  }

  public List<ContractSchedule> getContractScheduleList() {
    return contractScheduleList;
  }

  public void setContractScheduleList(List<ContractSchedule> contractScheduleList) {
    this.contractScheduleList = contractScheduleList;
  }

  public String getServicePhoto() {
    return servicePhoto;
  }

  public void setServicePhoto(String servicePhoto) {
    this.servicePhoto = servicePhoto;
  }

}
