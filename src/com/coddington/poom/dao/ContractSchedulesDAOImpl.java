package com.coddington.poom.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import com.coddington.poom.vo.ContractSchedule;

public class ContractSchedulesDAOImpl implements ContractSchedulesDAO {

	private SqlSession session;

	public void setSession(SqlSession session) {
		this.session = session;
	}// setSession() end

	@Override
	public int insert(ContractSchedule contractSchedule) {
		// TODO Auto-generated method stub
		return session.insert("contractSchedules.insert", contractSchedule);
	}

	@Override
	public int selectCountByServiceNo(int serviceNo) {
		// TODO Auto-generated method stub
		return session.selectOne("contractSchedules.selectCountByServiceNo", serviceNo);
	}

	@Override
	public int selectCountByScheduleNo(int scheduleNo) {
		// TODO Auto-generated method stub
		return session.selectOne("contractSchedules.selectCountByScheduleNo", scheduleNo);
	}

	@Override
	public List<ContractSchedule> selectScheduleListByContractNo(int contractNo) {
		// TODO Auto-generated method stub
		return session.selectList("contractSchedules.selectScheduleListByContractNo", contractNo);
	}

	@Override
	public int deleteByContractNo(int contractNo) {
		// TODO Auto-generated method stub
		return session.delete("contractSchedules.deleteByContractNo", contractNo);

	}
}
