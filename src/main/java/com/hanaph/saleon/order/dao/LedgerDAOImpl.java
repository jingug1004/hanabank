/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.dao;

import com.hanaph.saleon.order.vo.LedgerVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 
 * <pre>
 * Class Name : LedgerDAOImpl.java
 * 설명 :  원장조회 DaoImpl
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
@Repository("ledgerDAO")
public class LedgerDAOImpl extends SqlSessionDaoSupport implements LedgerDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.dao.LedgerDAO#getLedgerGridList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<LedgerVO> getLedgerGridList(Map<String, String> paramMap) {
		return getSqlSession().selectList("ledger.getLedgerGridList", paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.dao.LedgerDAO#getSumLedger(java.util.Map)
	 */
	@Override
	public LedgerVO getSumLedger(Map<String, String> paramMap) {
		return (LedgerVO)getSqlSession().selectOne("ledger.getSumLedger", paramMap);
	}

;
}
