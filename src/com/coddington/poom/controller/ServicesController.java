package com.coddington.poom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.coddington.poom.service.ServicesService;

@Controller
public class ServicesController {

  private ServicesService servicesService;

  public void setServicesService(ServicesService servicesService) {
    this.servicesService = servicesService;
  }

  @RequestMapping(value = {"/", "/index.poom"})
  public String index() {
    return "index";
  }
}
