package com.coddington.poom.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.coddington.poom.vo.Tag;

public class TagsDAOImpl implements TagsDAO {

	private SqlSession session;
	
	public void setSession(SqlSession session) {
		this.session = session;
	}//setSession() end
	
	@Override
	public List<Tag> selectListByServiceNo(int no) {
		// TODO Auto-generated method stub
		return session.selectList("tags.selectListByServiceNo", no);
	}
}
