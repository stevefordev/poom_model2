package com.coddington.poom.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.coddington.poom.service.CoinTransactionsService;
import com.coddington.poom.service.UsersService;
import com.coddington.poom.vo.CoinTransaction;
import com.coddington.poom.vo.User;

@Controller
public class CoinsController {

	private CoinTransactionsService coinTransactionsService;
	private UsersService usersService;

	public void setCoinTransactionsService(CoinTransactionsService coinTransactionsService) {
		this.coinTransactionsService = coinTransactionsService;
	}

	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}

	@RequestMapping("/test.poom")
	public String test() {
		return "test";
	}// test

	@RequestMapping("/dashboard_coin.poom")
	public void cointransaction(HttpSession session) {
		User loginUser = (User) session.getAttribute(UsersController.LOGIN);
		loginUser.setNickName(loginUser.getNickName());
		session.setAttribute(UsersController.LOGIN, usersService.login(loginUser));
	}

	@RequestMapping("/ajax/dashboard_coin.json")
	@ResponseBody
	public Map<String, Object> ajaxCointransaction(Model model, int page, int type, HttpSession session) {
		User user = (User) session.getAttribute(UsersController.LOGIN);

		return coinTransactionsService.getCoinList(user.getNo(), type, page);
	}

	@RequestMapping(value = "/ajax/coinTransactionsForChart.poom", method = RequestMethod.GET)
	@ResponseBody
	public List<CoinTransaction> coinTransactionsForChart(Model model, int type, String timestamp,
			HttpSession session) {
		User loginUser = (User) session.getAttribute(UsersController.LOGIN);

		long longParse = Long.parseLong(timestamp);
		Date regdate = new Date(longParse);
		System.out.println(String.format("type:%d / regdate:%s / user:%d", type, regdate, loginUser.getNo()));
		List<CoinTransaction> coinTransactions = coinTransactionsService.getCoinListForChart(loginUser.getNo(), type,
				regdate);
		return coinTransactions;
	}
}
