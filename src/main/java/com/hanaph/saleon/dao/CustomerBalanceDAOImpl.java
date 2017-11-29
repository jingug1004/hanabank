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

import com.hanaph.saleon.business.vo.CustomerBalanceVO;

/**
 * <pre>
 * Class Name : CustomerBalanceDAOImpl.java
 * 설명 : 영업관리 > 잔고 담보현황 DAOImpl class
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
@Repository("customerBalanceDAO")
public class CustomerBalanceDAOImpl extends SqlSessionDaoSupport implements CustomerBalanceDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerBalanceDAO#getCustomerBalanceGridList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerBalanceVO> getCustomerBalanceGridList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return (List<CustomerBalanceVO>)getSqlSession().selectList("customerBalance.getCustomerBalanceGridList", paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerBalanceDAO#getCustomerSumBalance(java.util.Map)
	 */
	@Override
	public CustomerBalanceVO getCustomerSumBalance(Map<String, String> paramMap) {
		return (CustomerBalanceVO)getSqlSession().selectOne("customerBalance.getCustomerSumBalance", paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerBalanceDAO#getCustomerBalanceDetailCount(java.util.Map)
	 */
	@Override
	public int getCustomerBalanceDetailCount(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return (Integer) getSqlSession().selectOne("customerBalance.getCustomerBalanceDetailCount", paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CustomerBalanceDAO#getCustomerBalanceGridDetail(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerBalanceVO> getCustomerBalanceGridDetail(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return (List<CustomerBalanceVO>)getSqlSession().selectList("customerBalance.getCustomerBalanceGridDetail", paramMap);
	}

}
