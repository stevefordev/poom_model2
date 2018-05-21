package com.coddington.poom.dao;

import java.util.List;

import com.coddington.poom.vo.Photo;

public interface PhotosDAO {

	public List<Photo> selectByServiceNo(int serviceNo);
	
	public int insert(Photo photo);
}
