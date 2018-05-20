package com.coddington.poom.dao;

import java.util.List;
import com.coddington.poom.vo.Schedule;

public interface SchedulesDAO {

  public List<Schedule> selectListByServiceNo(int serviceNo);

  public int insert(Schedule schedule);
  // insertIfNotExists
  public int insertIfNotExists(Schedule schedule);

  public int delete(int no);
 
}
