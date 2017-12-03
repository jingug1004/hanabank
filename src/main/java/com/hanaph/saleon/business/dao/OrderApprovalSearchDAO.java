/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.OrderApprovalSearchVO;

/**
 * <pre>
 * Class Name : OrderApprovalSearchDAO.java
 * 설명 : 주문/승인 조회 DAO
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
public interface OrderApprovalSearchDAO {

	/**
	 * <pre>
	 * 1. 개요     : 주문 조회
	 * 2. 처리내용 : 검색 조건에 따른 주문 내역을 반환한다.
	 * </pre>
	 * @Method Name : orderSearchGridList
	 * @param paramMap
	 * @return
	 */		
	public List<OrderApprovalSearchVO> getOrderSearchGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 승인 조회
	 * 2. 처리내용 : 검색 조건에 따른 승인 내역을 반환한다.
	 * </pre>
	 * @Method Name : approvalSearchGridList
	 * @param paramMap
	 * @return
	 */		
	public List<OrderApprovalSearchVO> getApprovalSearchGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문 총 갯수
	 * 2. 처리내용 : 검색 조건에 따른 주문 내역의 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : orderSearchGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalSearchVO getOrderSearchGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 승인 총 갯수
	 * 2. 처리내용 : 검색 조건에 따른 승인 내역의 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : approvalSearchGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalSearchVO getApprovalSearchGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문/승인 상세 목록
	 * 2. 처리내용 : 검색 조건에 다른 주문/승인 목록을 반환한다.
	 * </pre>
	 * @Method Name : getOrderApprovalDetailGridList
	 * @param paramMap
	 * @return
	 */		
	public List<OrderApprovalSearchVO> getOrderApprovalDetailGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문/승인 총 갯수
	 * 2. 처리내용 : 검색 조건에 다른 주문/승인 목록의 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getOrderApprovalDetailGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalSearchVO getOrderApprovalDetailGridTotalCount(Map<String, String> paramMap);
}
