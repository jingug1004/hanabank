/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.hanaph.saleon.business.vo.TurnsdayVO;

@Repository("turnsdayDAO")
public class TurnsdayDAOImpl extends SqlSessionDaoSupport implements TurnsdayDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.TurnsdayDAO#getTurnsdayGridList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<TurnsdayVO> getTurnsdayGridList(Map<String, String> paramMap) {
		return (List<TurnsdayVO>)getSqlSession().selectList("turnsday.getTurnsdayGridList", paramMap); // 회전일현황 목록
	}

}
