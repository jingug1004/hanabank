/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.dao;

import com.hanaph.saleon.order.vo.BalanceVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * Class Name : BalanceDAOImpl.java
 * 설명 :  잔고/담보현황 DaoImpl
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 27.      jung a Woo          
 * </pre>
 * 
 * @version : 
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 10. 27.
 */
@Repository("balanceDAO")
public class BalanceDAOImpl extends SqlSessionDaoSupport implements BalanceDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.dao.BalanceDAO#getBalanceGridList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BalanceVO> getBalanceGridList(Map<String, String> paramMap) {
		return getSqlSession().selectList("balance.getBalanceGridList", paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.dao.BalanceDAO#getSumBalance(java.util.Map)
	 */
	@Override
	public BalanceVO getSumBalance(Map<String, String> paramMap) {
		return (BalanceVO)getSqlSession().selectOne("balance.getSumBalance", paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.dao.BalanceDAO#getBalanceCount(java.util.Map)
	 */
	@Override
	public BalanceVO getBalanceCount(Map<String, String> paramMap) {
		return (BalanceVO) getSqlSession().selectOne("balance.getBalanceCount", paramMap);
	}




}
