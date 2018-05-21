package com.coddington.poom.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.coddington.poom.vo.Card;
import com.coddington.poom.vo.Service;

public class ServicesDAOImpl implements ServicesDAO {

  private SqlSession session;

  public void setSession(SqlSession session) {
    this.session = session;
  }// setSession() end

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

  @Override
  public int insert(Service service) {
    // TODO Auto-generated method stub
    return session.insert("services.insert", service);
  }

  @Override
  public int update(Service service) {
    // TODO Auto-generated method stub
    return session.update("services.update", service);
  } 
  
  @Override
	public List<Card> selectRecommendationList(int role) {
		// TODO Auto-generated method stub
	  return session.selectList("services.selectRecommendationList", role);
	}

//찜 목록 불러오기
	@Override
	public List<Card> selectLikeServicesByLikeServiceUserNo(int likeServiceUserNo) {
		// TODO Auto-generated method stub
		return session.selectList("services.selectLikeServicesByLikeServiceUserNo", likeServiceUserNo);
	}//selectLikeServicesByUserNo() end
	
	//원하는 계약이 있는 서비스카드 불러오기
	@Override
	public List<Card> selectContractServiceCardList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.selectList("services.selectContractServiceCardList", map);
	}
	
	@Override
	public Service selectFullAddress(int serviceNo) {
		// TODO Auto-generated method stub
		return session.selectOne("services.selectFullAddress", serviceNo);
	}
}
