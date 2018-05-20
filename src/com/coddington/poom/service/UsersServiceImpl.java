package com.coddington.poom.service;

import com.coddington.poom.dao.UsersDAO;
import com.coddington.poom.vo.User;

public class UsersServiceImpl implements UsersService {

  private UsersDAO usersDAO;

  public void setUsersDAO(UsersDAO usersDAO) {
    this.usersDAO = usersDAO;
  }

  @Override
  public User login(User user) {
    // TODO Auto-generated method stub
    return usersDAO.selectLogin(user);
  }
  
  @Override
  public boolean join(User user) {
	return 1 == usersDAO.insert(user);
	  
  }
}
