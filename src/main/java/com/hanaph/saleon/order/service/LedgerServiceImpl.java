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

import com.hanaph.saleon.order.dao.LedgerDAO;
import com.hanaph.saleon.order.vo.LedgerVO;

/**
 * 
 * <pre>
 * Class Name : LedgerServiceImpl.java
 * 설명 : 원장조회 ServiceImpl
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
@Service(value="ledgerService")
public class LedgerServiceImpl implements LedgerService {
	
	/**
	 * LedgerDAO
	 */
	@Autowired
	private LedgerDAO ledgerDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.service.LedgerService#getLedgerGridList(java.util.Map)
	 */
	@Override
	public List<LedgerVO> getLedgerGridList(Map<String, String> paramMap) {
		
		String arrDay[] = paramMap.get("adt_from").split("-");		//- 를 구분자로 짤라서 array객체로 
		String adt_from_first = arrDay[0]+"-"+arrDay[1]+"-01";		//조회 시작일 format (yyyy-mm-01)
		
		paramMap.put("adt_from_first", adt_from_first);
		
		return ledgerDAO.getLedgerGridList(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.service.LedgerService#getSumLedger(java.util.Map)
	 */
	@Override
	public LedgerVO getSumLedger(Map<String, String> paramMap) {
		
		String arrDay[] = paramMap.get("adt_from").split("-");		//- 를 구분자로 짤라서 array객체로 
		String adt_from_first = arrDay[0]+"-"+arrDay[1]+"-01";		//조회 시작일 format (yyyy-mm-01)
		
		paramMap.put("adt_from_first", adt_from_first);
		
		return ledgerDAO.getSumLedger(paramMap);
	}

}
