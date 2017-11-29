/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.order.vo.BalanceVO;

/**
 * <pre>
 * Class Name : ledgerService.java
 * 설명 : 잔고/담보현황 관련 Service Interface.
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
public interface BalanceService {

	
	/**
	 * <pre>
	 * 1. 개요     : 잔고/담보현황 grid
	 * 2. 처리내용 : 잔고/담보현황 grid 목록
	 * </pre>
	 * @Method Name : getBalanceGridList
	 * @param paramMap    Map<String, String> 
	 * @return
	 */		
	public List<BalanceVO> getBalanceGridList(Map<String, String> paramMap);

	
	/**
	 * <pre>
	 * 1. 개요     : 잔고/담보 현황 상단 합계 
	 * 2. 처리내용 : 잔고/담보 현황 합계 금액 계산
	 * </pre>
	 * @Method Name : getSumBalance
	 * @param paramMap    Map<String, String> 
	 * @return
	 */		
	public BalanceVO getSumBalance(Map<String, String> paramMap);

	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 잔고/담보 현황 grid count
	 * 2. 처리내용 : 잔고/담보 현황 grid 갯수
	 * </pre>
	 * @Method Name : getBalanceCount
	 * @param paramMap    Map<String, String> 
	 * @return
	 */
	public BalanceVO getBalanceCount(Map<String, String> paramMap);
	

	
}
