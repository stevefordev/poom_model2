package com.coddington.poom.dao;

import java.util.List;

import com.coddington.poom.vo.Question;

public interface QuestionsDAO {

	public List<Question> selectList(int serviceNo);
}
