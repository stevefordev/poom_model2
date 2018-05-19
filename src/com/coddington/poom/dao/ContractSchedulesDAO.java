package com.coddington.poom.dao;

import com.coddington.poom.vo.ContractSchedule;

public interface ContractSchedulesDAO {
  public int insert(ContractSchedule contractSchedule);

  public int selectCountByServiceNo(int serviceNo);
  
  public int selectCountByScheduleNo(int scheduleNo);
}
