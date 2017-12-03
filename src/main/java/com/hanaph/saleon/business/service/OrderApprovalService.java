/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.OrderApprovalVO;

/**
 * <pre>
 * Class Name : OrderApprovalService.java
 * 설명 : 주문승인 Service
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 9.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 12. 9.
 */
public interface OrderApprovalService {

	/**
	 * <pre>
	 * 1. 개요     : 주문내역 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 주문내역의 목록을 반환한다.
	 * </pre>
	 * @Method Name : getOrderApprovalGridList
	 * @param paramMap
	 * @return
	 */		
	public List<OrderApprovalVO> getOrderApprovalGridList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 주문내역 엑셀 목록
	 * 2. 처리내용 : 검색조건에 따른 주문내역 엑셀 목록을 반환한다.
	 * </pre>
	 * @Method Name : getOrderApprovalGridListExcel
	 * @param paramMap
	 * @return
	 * kta
	 */		
	public List<OrderApprovalVO> getOrderApprovalGridListExcel(Map<String, String> paramMap);	

	/**
	 * <pre>
	 * 1. 개요     : 주문내역 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 주문내역의 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getOrderApprovalGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalVO getOrderApprovalGridTotalCount(Map<String, String> paramMap);
 
	
	/**
	 * <pre>
	 * 1. 개요     : 주문세부내역 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 주문세부내역의 목록을 반환한다.
	 * </pre>
	 * @Method Name : getOrderDetailGridList
	 * @param paramMap
	 * @return
	 */		
	
	
	public List<OrderApprovalVO> getOrderDetailGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문세부내역 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 주문세부내역의 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getOrderDetailGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalVO getOrderDetailGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 정보
	 * 2. 처리내용 : 검색조건에 따른 거래처 정보를 반환한다.
	 * </pre>
	 * @Method Name : getCustomerInfo
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalVO getCustomerInfo(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문승인 정보
	 * 2. 처리내용 : 검색조건에 따른 주문승인 정보를 반환한다.
	 * </pre>
	 * @Method Name : getApprovalInfo
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalVO getApprovalInfo(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 담보약속 목록
	 * 2. 처리내용 : 검색조건에 따른 담보약속 목록을 반환한다.
	 * </pre>
	 * @Method Name : getPromiseList
	 * @param paramMap
	 * @return
	 */		
	public List<OrderApprovalVO> getPromiseGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 담보약속 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 담보약속 총 갯수 반환한다.
	 * </pre>
	 * @Method Name : getPromiseGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalVO getPromiseGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 승인 내용 저장
	 * 2. 처리내용 : 승인 내용을 저장한다.
	 * </pre>
	 * @Method Name : updateOrderApproval
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalVO updateOrderApproval(Map<String, Object> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 이월 작업 갯수
	 * 2. 처리내용 : 이월 작업 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getStoreLocCount
	 * @return
	 */		
	public int getStoreLocCount();

	/**
	 * <pre>
	 * 1. 개요     : 승인 취소
	 * 2. 처리내용 : 승인, 반려, 추가승인을 취소한다.
	 * </pre>
	 * @Method Name : cancelOrderApproval
	 * @param paramMap
	 * @return
	 */		
	public OrderApprovalVO cancelOrderApproval(Map<String, Object> paramMap);
}
