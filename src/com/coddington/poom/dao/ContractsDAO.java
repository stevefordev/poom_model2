package com.coddington.poom.dao;

import java.util.List;
import java.util.Map;
import com.coddington.poom.vo.Contract;
import com.coddington.poom.vo.ContractSchedule;

public interface ContractsDAO {

	public Map<String, Object> selectScoreAndCountByServiceNo(int serviceNo);

	public int insert(Contract contract);

	public List<Contract> selectListByServiceNo(Contract contract);

}
