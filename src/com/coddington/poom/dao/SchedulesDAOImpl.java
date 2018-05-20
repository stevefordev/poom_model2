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
	public List<Schedule> selectListByServiceNo(int serviceNo) {
		// TODO Auto-generated method stub
		return session.selectList("schedules.selectListByServiceNo", serviceNo);
	}
	
	@Override
	public int insert(Schedule schedule) {
	// TODO Auto-generated method stub
	return session.insert("schedules.insert", schedule);
	}
	
	@Override
	public int insertIfNotExists(Schedule schedule) {
	// TODO Auto-generated method stub
	return session.insert("schedules.insertIfNotExists", schedule);
	}
	
	@Override
	public int delete(int no) {
	// TODO Auto-generated method stub
	return session.delete("schedules.delete", no);
	}
}
