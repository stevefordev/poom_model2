package com.coddington.poom.dao;

import java.util.Map;
import org.apache.ibatis.session.SqlSession;

public class ContractsDAOImpl implements ContractsDAO {

  private SqlSession session;

  public void setSession(SqlSession session) {
    this.session = session;
  }// setSession() end

  @Override
  public Map<String, Object> selectScoreAndCountByServiceNo(int serviceNo) {
    // TODO Auto-generated method stub
    return session.selectOne("contracts.selectScoreAndCountByServiceNo", serviceNo);
  }
}
