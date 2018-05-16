package com.coddington.poom.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.coddington.poom.service.ContractsService;
import com.coddington.poom.vo.Contract;
import com.coddington.poom.vo.Question;

@Controller
public class ContractsController {

  private ContractsService contractsService;

  public void setContractsService(ContractsService contractsService) {
    this.contractsService = contractsService;
  }
  
  @RequestMapping(value = "/ajax/contract/register.poom", method = RequestMethod.POST)
  @ResponseBody
  public String registerQuestion(Contract contract, Model model) {

    contractsService.register(contract);

    return "{ \"data\" : true }";
  }
}
