package com.coddington.poom.service;

import java.util.List;

import com.coddington.poom.dao.ContractSchedulesDAO;
import com.coddington.poom.dao.ContractsDAO;
import com.coddington.poom.vo.Contract;
import com.coddington.poom.vo.ContractSchedule;

public class ContractsServiceImpl implements ContractsService {

	private ContractsDAO contractsDAO;
	private ContractSchedulesDAO contractSchedulesDAO;

	public void setContractsDAO(ContractsDAO contractsDAO) {
		this.contractsDAO = contractsDAO;
	}

	public void setContractSchedulesDAO(ContractSchedulesDAO contractSchedulesDAO) {
		this.contractSchedulesDAO = contractSchedulesDAO;
	}

	@Override
	public boolean register(Contract contract) {
		// TODO Auto-generated method stub

		try {

			// 계약 입력
			contractsDAO.insert(contract);

			List<ContractSchedule> contractSchedules = contract.getContractSchedules();
			
			// 계약에 선택된 스케줄 입력
			for (ContractSchedule contractSchedule : contractSchedules) {
				contractSchedule.setContractNo(contract.getNo());
				contractSchedulesDAO.insert(contractSchedule);
			}
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} 
		return false;		
	}
}
