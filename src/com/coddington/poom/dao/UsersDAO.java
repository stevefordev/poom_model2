package com.coddington.poom.dao;

import com.coddington.poom.vo.User;

public interface UsersDAO {
  public User selectLogin(User user);
  
  public int insert(User user);
}
