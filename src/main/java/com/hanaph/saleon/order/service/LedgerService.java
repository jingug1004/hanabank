/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.order.vo.LedgerVO;

/**
 * <pre>
 * Class Name : ledgerService.java
 * 설명 : 원장조회 관련 Service Interface.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 27. jung a Woo         
 * </pre>
 * 
 * @version : 
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 10. 27.
 */
public interface LedgerService {
	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 원장조회 grid
	 * 2. 처리내용 : 원장조회 값을 가져온다.
	 * </pre>
	 * @Method Name : getLedgerGridList
	 * @param paramMap    Map<String, String> 
	 * @return
	 */
	public List<LedgerVO> getLedgerGridList(Map<String, String> paramMap);

	/**
	 * 
	 * <pre>
	 * 1. 개요     : 원장조회 grid 하단 합계
	 * 2. 처리내용 : 원장조회 grid 합계를 가져온다.
	 * </pre>
	 * @Method Name : getSumLedger
	 * @param paramMap    Map<String, String> 
	 * @return
	 */
	public LedgerVO getSumLedger(Map<String, String> paramMap);

}
