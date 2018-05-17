package com.coddington.poom.dao;

import org.apache.ibatis.session.SqlSession;

import com.coddington.poom.vo.Service;

public class ServicesDAOImpl implements ServicesDAO {

	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}//setSession() end
	
	@Override
	public Service selectByNo(int no) {
		// TODO Auto-generated method stub
		return session.selectOne("services.selectByNo", no);
	}
	
	@Override
	public Service selectByServiceNoAndUserNo(Service service) {
		// TODO Auto-generated method stub
		return session.selectOne("services.selectByServiceNoAndUserNo", service);
	}
}
