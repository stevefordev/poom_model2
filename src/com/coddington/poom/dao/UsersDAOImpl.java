package com.coddington.poom.dao;

import org.apache.ibatis.session.SqlSession;
import com.coddington.poom.vo.User;

public class UsersDAOImpl implements UsersDAO {

	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}//setSession() end
	
	@Override
	public User selectLogin(User user) {
	// TODO Auto-generated method stub
	return session.selectOne("users.selectLogin", user);
	}
}
