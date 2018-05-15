package com.coddington.poom.dao;

import java.util.Map;

public interface ContractsDAO {
  public Map<String, Object> selectScoreAndCountByServiceNo(int serviceNo);
}
