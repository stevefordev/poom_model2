package com.coddington.poom.dao;

import java.util.List;
import java.util.Map;

import com.coddington.poom.vo.CoinTransaction;
import com.coddington.poom.vo.Contract;

public interface CoinTransactionsDAO {
 
	public int selectTotal(Map<String, Object> map);
	
	public List<CoinTransaction> selectList(Map<String, Object> map);
	
	public List<Contract> selectListByServiceNo(Contract contract);
	 
	public List<CoinTransaction> selectListForChart(Map<String, Object> map);

	public int insertAfterContract(CoinTransaction coinTransaction);
	
}
