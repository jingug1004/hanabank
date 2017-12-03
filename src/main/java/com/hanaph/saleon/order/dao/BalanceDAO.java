/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.order.vo.BalanceVO;

/**
 * 
 * <pre>
 * Class Name : BalanceDAO.java
 * 설명 : 잔고/담보현황 관련 DAO Interface.
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
public interface BalanceDAO {


	/**
	 * <pre>
	 * 1. 개요     : 잔고/담보현황 grid
	 * 2. 처리내용 : 잔고/담보현황 값을 select
	 * </pre>
	 * @Method Name : getBalanceGridList
	 * @param paramMap    Map<String, String> 
	 * @return	List<BalanceVO>
	 */		
	public List<BalanceVO> getBalanceGridList(Map<String, String> paramMap);

	
	/**
	 * <pre>
	 * 1. 개요     : 잔고/담보현황 grid 상단 합계
	 * 2. 처리내용 : 잔고/담보현황 grid 합계 select
	 * </pre>
	 * @Method Name : getSumBalance
	 * @param paramMap    Map<String, String> 
	 * @return	BalanceVO
	 */		
	public BalanceVO getSumBalance(Map<String, String> paramMap);

	
	/**
	 * <pre>
	 * 1. 개요     : 잔고/담보현황 grid 갯수
	 * 2. 처리내용 : 잔고/담보현황 grid 목록의 갯수 count
	 * </pre>
	 * @Method Name : getBalanceCount
	 * @param paramMap    Map<String, String> 
	 * @return	BalanceVO
	 */		
	public BalanceVO getBalanceCount(Map<String, String> paramMap);

}
