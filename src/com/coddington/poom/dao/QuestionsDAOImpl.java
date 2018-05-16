package com.coddington.poom.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.coddington.poom.vo.Question;

public class QuestionsDAOImpl implements QuestionsDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}// setSession() end

	@Override
	public List<Question> selectList(int serviceNo) {
		// TODO Auto-generated method stub
		return session.selectList("questions.selectList", serviceNo);
	}
}
