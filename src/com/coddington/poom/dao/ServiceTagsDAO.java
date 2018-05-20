package com.coddington.poom.dao;

import java.util.List;

import com.coddington.poom.vo.ServiceTag;
import com.coddington.poom.vo.Tag;

public interface ServiceTagsDAO {

  public int insert(ServiceTag serviceTag);
  
  public int deleteByServiceNo(int serviceNo);
  
  public List<Tag> selectTagNameByServiceNo(int serviceNo);

}
