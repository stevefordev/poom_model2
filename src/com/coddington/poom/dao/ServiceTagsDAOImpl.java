package com.coddington.poom.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.coddington.poom.vo.ServiceTag;
import com.coddington.poom.vo.Tag;

public class ServiceTagsDAOImpl implements ServiceTagsDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}// setSession() end

	@Override
	public int insert(ServiceTag serviceTag) {
		// TODO Auto-generated method stub
		return session.insert("serviceTags.insert", serviceTag);
	}

	@Override
	public int deleteByServiceNo(int serviceNo) {
		// TODO Auto-generated method stub
		return session.delete("serviceTags.deleteByServiceNo", serviceNo);
	}

	@Override
	public List<Tag> selectTagNameByServiceNo(int serviceNo) {
		// TODO Auto-generated method stub
		return session.selectList("serviceTags.selectTagNameByServiceNo", serviceNo);
	}// selectTagNameByServiceNo() end

}
