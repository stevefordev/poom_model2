package com.coddington.poom.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import com.coddington.poom.vo.CoinTransaction;
import com.coddington.poom.vo.Contract;

public class CoinTransactionsDAOImpl implements CoinTransactionsDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}// setSession() end

	@Override
	public int selectTotal(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.selectOne("coinTransactions.selectTotal", map);
	}

	@Override
	public List<CoinTransaction> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.selectList("coinTransactions.selectList", map);
	}

	@Override
	public List<Contract> selectListByServiceNo(Contract contract) {
		// TODO Auto-generated method stub
		return session.selectList("contracts.selectListByServiceNo", contract);
	}// selectListByServiceNo() end

	@Override
	public List<CoinTransaction> selectListForChart(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return session.selectList("coinTransactions.selectListForChart", map);
	}

	@Override
	public int insertAfterContract(CoinTransaction coinTransaction) {
		// TODO Auto-generated method stub
		return session.insert("coinTransactions.insertAfterContract", coinTransaction);
	}
}
