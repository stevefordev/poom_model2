package com.coddington.poom.dao;

import com.coddington.poom.vo.ServiceTag;

public interface ServiceTagsDAO {

  public int insert(ServiceTag serviceTag);
  
  public int deleteByServiceNo(int serviceNo);

}
