package com.coddington.poom.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.coddington.poom.service.ServicesService;

@Controller
public class ServicesController {

	private ServicesService servicesService;

	public void setServicesService(ServicesService servicesService) {
		this.servicesService = servicesService;
	}

	@RequestMapping(value = { "/", "/index.poom" })
	public String index() {
		return "index";
	}

	@RequestMapping(value = "details.poom")
	public String detail(@RequestParam(defaultValue = "0") int no, Model model) {

		if (no > 0) {
			model.addAllAttributes(servicesService.getDetails(no));
		} else {
			// 비정상적으로 상세페이지로 넘어온 경우 no 가 부정확하기 때문에 인트로로 리다이렉트
			return "redirect:/";
		}

		return "details";
	}
}
