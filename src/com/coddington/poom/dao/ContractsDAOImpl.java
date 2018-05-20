package com.coddington.poom.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.session.SqlSession;
import com.coddington.poom.vo.Contract;
import com.coddington.poom.vo.ContractSchedule;

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

	@Override
	public int insert(Contract contract) {
		// TODO Auto-generated method stub
		return session.insert("contracts.insert", contract);
	}

	@Override
	public List<Contract> selectListByServiceNo(Contract contract) {
		// TODO Auto-generated method stub
		return session.selectList("contracts.selectListByServiceNo", contract);
	}// selectListByServiceNo() end
}
