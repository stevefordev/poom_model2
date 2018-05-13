package com.coddington.poom.service;

import com.coddington.poom.dao.CoinTransactionsDAO;

public class CoinTransactionsServiceImpl implements CoinTransactionsService {
	
	private CoinTransactionsDAO coinTransactionsDAO;
	
	public void setCoinTransactionsDAO(CoinTransactionsDAO coinTransactionsDAO) {
		this.coinTransactionsDAO = coinTransactionsDAO;
	}

}
