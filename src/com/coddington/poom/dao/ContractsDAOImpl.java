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
	public Map<String, Object> selectScoreAndCountByUserNo(int userNo) {
		// TODO Auto-generated method stub
		return session.selectOne("contracts.selectScoreAndCountByUserNo", userNo);
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

	@Override
	public List<Contract> selectContractList(Map<String, Object> contractMap) {
		// TODO Auto-generated method stub
		return session.selectList("contracts.selectContractList", contractMap);
	}

	@Override
	public int updateContractStatus(Map<String, Object> contractMap) {
		// TODO Auto-generated method stub
		return session.update("contracts.updateContractStatus", contractMap);
	}

	@Override
	public int deleteContract(int contractNo) {
		// TODO Auto-generated method stub
		return session.delete("contracts.deleteContract", contractNo);
	}

	@Override
	public int updateScoreFromTaker(Contract contract) {
		// TODO Auto-generated method stub
		return session.update("contracts.updateScoreFromTaker", contract);
	}

	@Override
	public int updateScoreFromGiver(Contract contract) {
		// TODO Auto-generated method stub
		return session.update("contracts.updateScoreFromGiver", contract);
	}
}
