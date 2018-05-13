package com.coddington.poom.controller;

import org.springframework.stereotype.Controller;

import com.coddington.poom.service.UsersService;

@Controller
public class UsersController {

	private UsersService usersService;
	
	public void setUsersService(UsersService usersService) {
		this.usersService = usersService;
	}
}
