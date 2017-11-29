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

import com.hanaph.saleon.business.dao.CustomerBalanceDAO;
import com.hanaph.saleon.business.vo.CustomerBalanceVO;

/**
 * <pre>
 * Class Name : CustomerBalanceServiceImpl.java
 * 설명 : 영업관리 > 잔고 담보현황 ServiceImpl class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015. 2. 2.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2015. 2. 2.
 */
@Service(value="customerBalanceService")
public class CustomerBalanceServiceImpl implements CustomerBalanceService{
	
	@Autowired
	private CustomerBalanceDAO customerBalanceDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerBalanceService#getCustomerBalanceGridList(java.util.Map)
	 */
	@Override
	public List<CustomerBalanceVO> getCustomerBalanceGridList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return customerBalanceDAO.getCustomerBalanceGridList(paramMap);
	}
	
	@Override
	public CustomerBalanceVO getCustomerSumBalance(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return customerBalanceDAO.getCustomerSumBalance(paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerBalanceService#getCustomerBalanceDetailCount(java.util.Map)
	 */
	@Override
	public int getCustomerBalanceDetailCount(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return customerBalanceDAO.getCustomerBalanceDetailCount(paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerBalanceService#getCustomerBalanceGridDetail(java.util.Map)
	 */
	@Override
	public List<CustomerBalanceVO> getCustomerBalanceGridDetail(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return customerBalanceDAO.getCustomerBalanceGridDetail(paramMap);
	}

}
