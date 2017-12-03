/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.CustomerLedgerVO;

/**
 * <pre>
 * Class Name : CustomerLedgerDAO.java
 * 설명 : 거래처원장조회 DAO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 14.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 11. 14.
 */
public interface CustomerLedgerDAO {
	
	/**
	 * <pre>
	 * 1. 개요     : 거래처 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 거래처 목록을 반환한다.
	 * </pre>
	 * @Method Name : getCustomerGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerLedgerVO> getCustomerGridList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 거래처 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 거래처 목록을 반환한다.
	 * </pre>
	 * @Method Name : getCustomerGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerLedgerVO getCustomerGridTotalCount(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 간납처 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 간납처 목록을 반환한다.
	 * </pre>
	 * @Method Name : getIndirectGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerLedgerVO> getIndirectGridList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 간납처 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 거래처/간납처 목록을 반환한다.
	 * </pre>
	 * @Method Name : getIndirectGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerLedgerVO getIndirectGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 원장 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 거래처 원장 목록을 반환한다.
	 * </pre>
	 * @Method Name : getCustomerLedgerGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerLedgerVO> getCustomerLedgerGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 원장 총 갯수
	 * 2. 처리내용 : 검색조건에 다른 거래처 원장 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getIndirectLedgerGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerLedgerVO> getIndirectLedgerGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 간납처 원장 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 간납처 원장 목록을 반환한다.
	 * </pre>
	 * @Method Name : getCustomerLedgerGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerLedgerVO getCustomerLedgerGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 간납처 원장 총 갯수
	 * 2. 처리내용 : 검색조건에 다른 간납처 원장 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getIndirectLedgerGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerLedgerVO getIndirectLedgerGridTotalCount(Map<String, String> paramMap);
}
