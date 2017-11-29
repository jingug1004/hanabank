/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanaph.saleon.business.dao.CustomerLedgerDAO;
import com.hanaph.saleon.business.vo.CustomerLedgerVO;

@Service(value="customerLedgerService")
public class CustomerLedgerServiceImpl implements CustomerLedgerService{
	
	@Autowired
	private CustomerLedgerDAO customerLedgerDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerLedgerService#getCustomerGridList(java.util.Map)
	 */
	@Override
	public List<CustomerLedgerVO> getCustomerGridList(Map<String, String> paramMap) {
		
		String selectType = paramMap.get("selectType"); // 조회구분
		List<CustomerLedgerVO> customerList = null;
		
		/*조회구분에 따라 분기*/
		if ("customer".equals(selectType)) {
			customerList = customerLedgerDAO.getCustomerGridList(paramMap); // 거래처 목록
		} else if ("indirect".equals(selectType)) {
			customerList = customerLedgerDAO.getIndirectGridList(paramMap); // 간납처 목록
		}
		
		return customerList;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerLedgerService#getCustomerGridTotalCount(java.util.Map)
	 */
	@Override
	public CustomerLedgerVO getCustomerGridTotalCount(Map<String, String> paramMap) {
		
		String selectType = paramMap.get("selectType"); // 조회구분
		CustomerLedgerVO customerLedgerVO = null;
		
		/*조회구분에 따라 분기*/
		if ("customer".equals(selectType)) {
			customerLedgerVO = customerLedgerDAO.getCustomerGridTotalCount(paramMap); // 거래처 목록 총 수
		} else if ("indirect".equals(selectType)) {
			customerLedgerVO = customerLedgerDAO.getIndirectGridTotalCount(paramMap); // 간납처 목록 총 수
		}
		
		return customerLedgerVO;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerLedgerService#getCustomerLedgerGridList(java.util.Map)
	 */
	@Override
	public List<CustomerLedgerVO> getCustomerLedgerGridList(Map<String, String> paramMap) {
		
		String selectType = paramMap.get("selectType"); // 조회구분
		List<CustomerLedgerVO> customerLedgerList = null;
		
		/*조회구분에 따라 분기*/
		if ("customer".equals(selectType)) {
			customerLedgerList = customerLedgerDAO.getCustomerLedgerGridList(paramMap); // 거래처 원장 목록
		} else if ("indirect".equals(selectType)) {
			customerLedgerList = customerLedgerDAO.getIndirectLedgerGridList(paramMap); // 간납처 원장 목록
		}
		
		return customerLedgerList;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerLedgerService#getCustomerLedgerGridTotalCount(java.util.Map)
	 */
	@Override
	public CustomerLedgerVO getCustomerLedgerGridTotalCount(Map<String, String> paramMap) {
		
		String selectType = paramMap.get("selectType"); // 조회구분
		CustomerLedgerVO customerLedgerVO = null;
		
		/*조회구분에 따라 분기*/
		if ("customer".equals(selectType)) {
			customerLedgerVO = customerLedgerDAO.getCustomerLedgerGridTotalCount(paramMap); // 거래처 원장 목록 총 수
		} else if ("indirect".equals(selectType)) {
			customerLedgerVO = customerLedgerDAO.getIndirectLedgerGridTotalCount(paramMap); // 간납처 원장 목록 총 수
		}
		
		return customerLedgerVO;
	}

}
