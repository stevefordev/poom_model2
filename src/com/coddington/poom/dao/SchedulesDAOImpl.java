package com.coddington.poom.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.coddington.poom.vo.Schedule;

public class SchedulesDAOImpl implements SchedulesDAO{

	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}//setSession() end
	
	@Override
	public List<Schedule> selectList(int serviceNo) {
		// TODO Auto-generated method stub
		return session.selectList("schedules.selectList", serviceNo);
	}
}
