/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.CustomerBalanceVO;

/**
 * <pre>
 * Class Name : CustomerBalanceService.java
 * 설명 : 영업관리 > 잔고 담보현황 Service class
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
public interface CustomerBalanceService {
	
	/**
	 * <pre>
	 * 1. 개요     : 영업관리 잔고/담보현황 거래처 jqgrid 목록
	 * 2. 처리내용 : 거래처코드 검색 결과에 따라 grid list를 호출 한다.
	 * </pre>
	 * @Method Name : getCustomerBalanceGridList
	 * @param request
	 * @return CustomerBalanceVO
	 */			
	public List<CustomerBalanceVO> getCustomerBalanceGridList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 영업관리 잔고/담보현황 
	 * 2. 처리내용 : 전월잔고,미도래(자수,타수),현잔고,총여신,금월판매,담보확보액,담보확보율
	 * </pre>
	 * @Method Name : getCustomerSumBalance
	 * @param paramMap
	 * @return CustomerBalanceVO
	 */		
	public CustomerBalanceVO getCustomerSumBalance(Map<String, String> paramMap);
	
	
	/**
	 * <pre>
	 * 1. 개요     : 영업관리 잔고/담보현황 거래처
	 * 2. 처리내용 : 거래처코드 검색 결과에 따라 count
	 * </pre>
	 * @Method Name : getCustomerBalanceDetailCount
	 * @param paramMap
	 * @return int
	 */		
	public int getCustomerBalanceDetailCount(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 영업관리 잔고/담보현황 거래처 jqgrid 목록
	 * 2. 처리내용 : 거래처코드 검색 결과에 따라 grid list를 호출 한다.
	 * </pre>
	 * @Method Name : getCustomerBalanceGridDetail
	 * @param request
	 * @param response
	 * @return CustomerBalanceVO
	 */		
	public List<CustomerBalanceVO> getCustomerBalanceGridDetail(Map<String, String> paramMap);
	
}
