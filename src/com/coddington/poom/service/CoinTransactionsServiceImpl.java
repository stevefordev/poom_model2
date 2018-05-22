package com.coddington.poom.service;

import java.sql.Date;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coddington.poom.dao.CoinTransactionsDAO;
import com.coddington.poom.dao.TagsDAO;
import com.coddington.poom.util.PaginateUtil;
import com.coddington.poom.vo.CoinTransaction;

public class CoinTransactionsServiceImpl implements CoinTransactionsService {

	private CoinTransactionsDAO coinTransactionsDAO;

	private TagsDAO tagsDAO;

	private Calendar cal = Calendar.getInstance();
	
	public void setCoinTransactionsDAO(CoinTransactionsDAO coinTransactionsDAO) {
		this.coinTransactionsDAO = coinTransactionsDAO;
	}

	public void setTagsDAO(TagsDAO tagsDAO) {
		this.tagsDAO = tagsDAO;
	}

	@Override
	public Map<String, Object> getCoinList(int userNo, int type, int page) {

		Map<String, Object> map = new HashMap<>();

		// 한 페이지에 보여질 게시물 수
		int numPage = 5;
		// 한 페이지에 보여질
		// 페이징 블록 갯수
		int numBlock = 3;

		int end = page * numPage;
		int start = end - (numPage - 1);

		map.put("end", end);
		map.put("start", start);
		map.put("userNo", userNo);
		map.put("type", type);

		Map<String, Object> model = new HashMap();

		List<CoinTransaction> coinTransactionList = coinTransactionsDAO.selectList(map);

		for (CoinTransaction coinTransaction : coinTransactionList) {
			// System.out.println(coinTransaction.getServiceNo());
			int contractNo = coinTransaction.getContractNo();
			if (coinTransaction.getType() > 20) {
				coinTransaction.setTags(tagsDAO.selectListByContractNo(contractNo));
			}
		}

		model.put("coinTransactions", coinTransactionList);

		Map<String, Object> pageVO = new HashMap<>();
		pageVO.put("type", type);
		pageVO.put("no", userNo);
		// 페이지네이션 처리
		int total = coinTransactionsDAO.selectTotal(pageVO);

		String url = "/dashboard_coin.poom";

		String paginate = PaginateUtil.getPaginate(page, total, numPage, numBlock, url, "page=");

		model.put("paginate", paginate);

		return model;
	}
	
	@Override
	public List<CoinTransaction> getCoinListForChart(int userNo, int type, Date regdate) {
		// TODO Auto-generated method stub

		System.out.println("getCoinListForChart");
		
		Map<String, Object> map = new HashMap<>();
		cal.setTime(regdate);
		cal.add(Calendar.MONTH, 1);
		Date enddate = new Date(cal.getTime().getTime());
		  
		map.put("startdate", regdate);
		map.put("enddate", enddate);
		map.put("userNo", userNo);
		map.put("type", type);
 
		List<CoinTransaction> coinTransactionList = coinTransactionsDAO.selectListForChart(map);
		System.out.println("coinTransactionList size:" + coinTransactionList.size());
		return coinTransactionList;
	}
}
