package com.coddington.poom.dao;

import java.util.List;

import com.coddington.poom.vo.ContractSchedule;

public interface ContractSchedulesDAO {
	public int insert(ContractSchedule contractSchedule);

	public int selectCountByServiceNo(int serviceNo);

	public int selectCountByScheduleNo(int scheduleNo);

	public List<ContractSchedule> selectScheduleListByContractNo(int contractNo);

	public int deleteByContractNo(int contractNo);
}
