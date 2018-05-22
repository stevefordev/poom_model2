package com.coddington.poom.service;

import java.sql.Date;
import java.util.List;
import java.util.Map;

import com.coddington.poom.vo.CoinTransaction;

public interface CoinTransactionsService {

	public Map<String, Object> getCoinList(int no, int type, int page);

	public List<CoinTransaction> getCoinListForChart(int userNo, int type, Date regdate);

}
