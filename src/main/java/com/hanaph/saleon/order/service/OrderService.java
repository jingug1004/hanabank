/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.order.vo.ItemVO;
import com.hanaph.saleon.order.vo.OrderVO;

/**
 * <pre>
 * Class Name : orderService.java
 * 설명 : 온라인 주문 관련 Service Interface.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 30.      jung a Woo          
 * </pre>
 * 
 * @version : 
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 10. 30.
 */
public interface OrderService {

	
	/**
	 * <pre>
	 * 1. 개요     : 판매처검색 grid 목록
	 * 2. 처리내용 : 판매처검색 grid 목록 ajax
	 * </pre>
	 * @Method Name : getStoreGridList
	 * @param paramMap    Map<String, String> 
	 * @return	List<OrderVO>
	 */		
	public List<OrderVO> getStoreGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 온라인주문 등록시 필요한 기본값 setting
	 * 2. 처리내용 : 여신규정, 담당자, 보증인, 사원정보, 거래처의 여신 갯수, 거래처등록의 여신한도,온라인주문상여신/담보정보
	 * 				ERP상 주문 총여신,출하중지 체크 등의 값을 가져온다.
	 * </pre>
	 * @Method Name : getOrderInit
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getOrderInit(Map<String, String> paramMap);

	/**
	 * 
	 * <pre>
	 * 1. 개요     : 온라인주문 수정시 필요한 기본값 setting
	 * 2. 처리내용 : 여신규정, 담당자, 보증인, 사원정보, 온라인주문상여신/담보정보, ERP상 주문 총여신 등의 값을 가져온다.
	 * </pre>
	 * @Method Name : getOrderUpInit
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */
	public OrderVO getOrderUpInit(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 판매처가 주문 가능한지 check
	 * 2. 처리내용 : 판매처 확인 및 출하중지 체크를 한다.
	 * </pre>
	 * @Method Name : getstoreChk
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getstoreChk(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 판매처별 제품자료 
	 * 2. 처리내용 : 판매처별 제품 grid를 가져온다.
	 * </pre>
	 * @Method Name : getItemGridList
	 * @param paramMap    Map<String, String> 
	 * @return	List<ItemVO>
	 */		
	public List<ItemVO> getItemGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 납품처의 향정마약여부 체크
	 * 2. 처리내용 : 제품이 향정마약일 경우에 납품처의 향정마약여부 체크를 한다.
	 * </pre>
	 * @Method Name : getItemGbYn
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */		
	public ItemVO getItemGbYn(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 수량입력시 제품 체크
	 * 2. 처리내용 : 제품 체크, 창고재고, 제조번호별 재고의 합계, 개시일자부터 3개월간은 주문수량 체크를 한다.
	 * </pre>
	 * @Method Name : getItemChkReg
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */		
	public ItemVO getItemChkReg(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문등록 ajax
	 * 2. 처리내용 : db에서 주문 Master, 주문 Detail, 입력하고 창고 재고 수량을 수정한다.
	 * </pre>
	 * @Method Name : insertOrder
	 * @param paramMap    Map<String, String> 
	 * @return	boolean
	 */		
	public boolean insertOrder(Map<String, Object> paramMap);

	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 Master Grid List
	 * 2. 처리내용 : 주문 Master 목록을 가져온다.
	 * </pre>
	 * @Method Name : getMasterGridList
	 * @param paramMap    Map<String, String> 
	 * @return	List<ItemVO>
	 */
	public List<ItemVO> getMasterGridList(Map<String, String> paramMap);

	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 Detail Grid List
	 * 2. 처리내용 : 주문 Detail 목록을 가져온다.
	 * </pre>
	 * @Method Name : getDetailGridList
	 * @param paramMap    Map<String, String> 
	 * @return	List<ItemVO> 
	 */
	public List<ItemVO> getDetailGridList(Map<String, String> paramMap);

	/**
	 * 
	 * <pre>
	 * 1. 개요     : 제품별 bas_amt값 ajax
	 * 2. 처리내용 : 제품별 bas_amt값을 가져온다
	 * </pre>
	 * @Method Name : getBasDanga
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */
	public ItemVO getBasDanga(Map<String, String> paramMap);

	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 제품 수량 입력시 제품 체크 ajax
	 * 2. 처리내용 : 제품 수량 입력시 제품 체크값을 가져온다
	 * </pre>
	 * @Method Name : getItemChkEdit
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */
	public ItemVO getItemChkEdit(Map<String, String> paramMap);

	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 승인체크 ajax
	 * 2. 처리내용 : 주문 승인여부를 가져온다.
	 * </pre>
	 * @Method Name : getReceiptChk
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */
	public ItemVO getReceiptChk(Map<String, String> paramMap);

	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 수정
	 * 2. 처리내용 : db에서 주문 Master, 주문 Detail, 창고 재고 수량을 수정한다.
	 * </pre>
	 * @Method Name : updateOrder
	 * @param paramMap    Map<String, String> 
	 * @return	boolean
	 */
	public boolean updateOrder(Map<String, Object> paramMap);

	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 삭제 
	 * 2. 처리내용 : db에서 주문 Master, 주문 Detail 삭제하고 창고 재고 수량을 수정한다.
	 * </pre>
	 * @Method Name : deleteOrder
	 * @param paramMap    Map<String, String> 
	 * @return	boolean
	 */
	public boolean deleteOrder(Map<String, Object> paramMap);

	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 Master status grid List
	 * 2. 처리내용 : 주문 Master status 목록을 가져온다.
	 * </pre>
	 * @Method Name : getOrderStatusGridList
	 * @param paramMap    Map<String, String> 
	 * @return	List<ItemVO>
	 */
	public List<ItemVO> getOrderStatusGridList(Map<String, Object> paramMap);
}
