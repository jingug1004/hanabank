/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanaph.saleon.order.dao.BalanceDAO;
import com.hanaph.saleon.order.vo.BalanceVO;

/**
 * 
 * <pre>
 * Class Name : BalanceServiceImpl.java
 * 설명 : 잔고/담보현황 ServiceImpl
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 28.      jung a Woo          
 * </pre>
 * 
 * @version : 
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 10. 28.
 */
@Service(value="balanceService")
public class BalanceServiceImpl implements BalanceService {
	
	/**
	 * BalanceDAO
	 */
	@Autowired
	private BalanceDAO balanceDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.service.BalanceService#getBalanceGridList(java.util.Map)
	 */
	@Override
	public List<BalanceVO> getBalanceGridList(Map<String, String> paramMap) {
		
		return balanceDAO.getBalanceGridList(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.service.BalanceService#getSumBalance(java.util.Map)
	 */
	@Override
	public BalanceVO getSumBalance(Map<String, String> paramMap) {
		
		return balanceDAO.getSumBalance(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.service.BalanceService#getBalanceCount(java.util.Map)
	 */
	@Override
	public BalanceVO getBalanceCount(Map<String, String> paramMap) {
		
		return balanceDAO.getBalanceCount(paramMap);
	}


	
}
