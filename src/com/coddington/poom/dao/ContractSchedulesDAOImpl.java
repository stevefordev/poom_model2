package com.coddington.poom.dao;

import org.apache.ibatis.session.SqlSession;
import com.coddington.poom.vo.ContractSchedule;

public class ContractSchedulesDAOImpl implements ContractSchedulesDAO {

	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}//setSession() end
	
	@Override
	public int insert(ContractSchedule contractSchedule) {
	// TODO Auto-generated method stub
	return session.insert("contractSchedules.insert", contractSchedule);
	}
}
