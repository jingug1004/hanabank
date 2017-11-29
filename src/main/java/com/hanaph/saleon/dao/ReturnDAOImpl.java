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

import com.hanaph.saleon.business.vo.ReturnVO;

@Repository("returnDAO")
public class ReturnDAOImpl extends SqlSessionDaoSupport implements ReturnDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.ReturnDAO#getReturnGridList(java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<ReturnVO> getReturnGridList(Map<String, String> paramMap) {
		return (List<ReturnVO>)getSqlSession().selectList("return.getReturnGridList", paramMap); // 반품현황 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.ReturnDAO#getReturnGridTotalCount(java.util.Map)
	 */
	@Override
	public ReturnVO getReturnGridTotalCount(Map<String, String> paramMap) {
		return (ReturnVO) getSqlSession().selectOne("return.getReturnGridTotalCount", paramMap); // 반품현황 총 수
	}

}
