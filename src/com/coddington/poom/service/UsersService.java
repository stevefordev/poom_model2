package com.coddington.poom.service;

import com.coddington.poom.vo.User;

public interface UsersService {

  public User login(User user);
  
  public boolean join(User user);
}
