package com.coddington.poom.dao;

import java.util.List;

import com.coddington.poom.vo.Schedule;

public interface SchedulesDAO {

	public List<Schedule> selectList(int serviceNo);
}
