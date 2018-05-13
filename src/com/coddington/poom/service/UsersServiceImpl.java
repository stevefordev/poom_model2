package com.coddington.poom.service;

import com.coddington.poom.dao.UsersDAO;

public class UsersServiceImpl implements UsersService {

	private UsersDAO usersDAO;
	
	public void setUsersDAO(UsersDAO usersDAO) {
		this.usersDAO = usersDAO;
	}
}
