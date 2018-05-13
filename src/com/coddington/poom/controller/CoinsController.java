package com.coddington.poom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.coddington.poom.service.CoinTransactionsService;

@Controller
public class CoinsController {

	private CoinTransactionsService coinTransactionsService;
	
	public void setCoinTransactionsService(CoinTransactionsService coinTransactionsService) {
		this.coinTransactionsService = coinTransactionsService;
	}
	
	@RequestMapping("/test.poom")
	public String test() {
		return "test";
	}//test
}
