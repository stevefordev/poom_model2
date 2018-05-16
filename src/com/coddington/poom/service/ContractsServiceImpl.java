package com.coddington.poom.service;

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
    
    contractsDAO.insert(contract);
    //contractSchedulesDAO.insert(contractSchedule)
    
    return true;
  }
}
