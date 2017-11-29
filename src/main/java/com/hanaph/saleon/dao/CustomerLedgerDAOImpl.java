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

import com.hanaph.saleon.business.vo.CustomerLedgerVO;

@Repository("customerLedgerDAO")
public class CustomerLedgerDAOImpl extends SqlSessionDaoSupport implements CustomerLedgerDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerLedgerDAO#getCustomerGridList(java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CustomerLedgerVO> getCustomerGridList(Map<String, String> paramMap) {
		return (List<CustomerLedgerVO>)getSqlSession().selectList("customerLedger.getCustomerGridList", paramMap); // 거래처 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerLedgerDAO#getCustomerGridTotalCount(java.util.Map)
	 */
	@Override
	public CustomerLedgerVO getCustomerGridTotalCount(Map<String, String> paramMap) {
		return (CustomerLedgerVO) getSqlSession().selectOne("customerLedger.getCustomerGridTotalCount", paramMap); // 거래처 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerLedgerDAO#getIndirectGridList(java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CustomerLedgerVO> getIndirectGridList(Map<String, String> paramMap) {
		return (List<CustomerLedgerVO>)getSqlSession().selectList("customerLedger.getIndirectGridList", paramMap); // 간납처 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerLedgerDAO#getIndirectGridTotalCount(java.util.Map)
	 */
	@Override
	public CustomerLedgerVO getIndirectGridTotalCount(Map<String, String> paramMap) {
		return (CustomerLedgerVO) getSqlSession().selectOne("customerLedger.getIndirectGridTotalCount", paramMap); // 간납처 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerLedgerDAO#getCustomerLedgerGridList(java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CustomerLedgerVO> getCustomerLedgerGridList(Map<String, String> paramMap) {
		return (List<CustomerLedgerVO>)getSqlSession().selectList("customerLedger.getCustomerLedgerGridList", paramMap); // 거래처 원장 목록
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerLedgerDAO#getCustomerLedgerGridTotalCount(java.util.Map)
	 */
	@Override
	public CustomerLedgerVO getCustomerLedgerGridTotalCount(Map<String, String> paramMap) {
		return (CustomerLedgerVO) getSqlSession().selectOne("customerLedger.getCustomerLedgerGridTotalCount", paramMap); // 거래처 원장 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerLedgerDAO#getIndirectLedgerGridList(java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CustomerLedgerVO> getIndirectLedgerGridList(Map<String, String> paramMap) {
		return (List<CustomerLedgerVO>)getSqlSession().selectList("customerLedger.getIndirectLedgerGridList", paramMap); // 간납처 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerLedgerDAO#getIndirectLedgerGridTotalCount(java.util.Map)
	 */
	@Override
	public CustomerLedgerVO getIndirectLedgerGridTotalCount(Map<String, String> paramMap) {
		return (CustomerLedgerVO) getSqlSession().selectOne("customerLedger.getIndirectLedgerGridTotalCount", paramMap); // 간납처 목록 총 수
	}

}
