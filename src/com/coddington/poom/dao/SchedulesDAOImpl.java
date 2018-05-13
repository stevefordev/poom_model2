package com.coddington.poom.dao;

import org.apache.ibatis.session.SqlSession;

public class SchedulesDAOImpl implements SchedulesDAO{

	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}//setSession() end
}
