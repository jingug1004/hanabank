/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.OrderApprovalSearchVO;

/**
 * <pre>
 * Class Name : OrderApprovalSearchService.java
 * 설명 : 주문/승인 조회 Service
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 26.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 12. 26.
 */
public interface OrderApprovalSearchService {

	/**
	 * <pre>
	 * 1. 개요     : 주문/승인 jqgrid 목록
	 * 2. 처리내용 : 검색 조건에 따른 주문/승인 목록을 반환한다.
	 * </pre>
	 * @Method Name : getOrderApprovalSearchGridList
	 * @param paramMap
	 * @return
	 */		
	public List<OrderApprovalSearchVO> getOrderApprovalSearchGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문/승인 총 갯수
	 * 2. 처리내용 : 검색 조건에 따른 주문/승인 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getOrderApprovalSearchGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalSearchVO getOrderApprovalSearchGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문/승인 상세 jqgrid 목록
	 * 2. 처리내용 : 검색 조건에 따른 주문/승인 상세 목록을 반환한다.
	 * </pre>
	 * @Method Name : getOrderApprovalDetailGridList
	 * @param paramMap
	 * @return
	 */		
	public List<OrderApprovalSearchVO> getOrderApprovalDetailGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문/승인 상세 총 갯수
	 * 2. 처리내용 : 검색 조건에 따른 주문/승인 상세 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getOrderApprovalDetailGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalSearchVO getOrderApprovalDetailGridTotalCount(Map<String, String> paramMap);
	
}
