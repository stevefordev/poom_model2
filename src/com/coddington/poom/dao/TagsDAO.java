package com.coddington.poom.dao;

import java.util.List;

import com.coddington.poom.vo.Tag;

public interface TagsDAO {

	public List<Tag> selectListByServiceNo(int no);
	
	public int insert(Tag tag);
	
	public List<Tag> selectListLikeName(String name);
	
	public Tag selectByName(String name);
	
}
