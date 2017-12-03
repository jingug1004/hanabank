/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.order.vo.ItemVO;
import com.hanaph.saleon.order.vo.OrderVO;

/**
 * 
 * <pre>
 * Class Name : OrderDAO.java
 * 설명 : 온라인발주 주문 관련 DAO Interface.
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
public interface OrderDAO {


	/**
	 * <pre>
	 * 1. 개요     : 판매처검색 grid 목록
	 * 2. 처리내용 :  판매처검색 grid 목록 select
	 * </pre>
	 * @Method Name : getStoreGridList
	 * @param paramMap    Map<String, String> 
	 * @return	List<OrderVO>
	 */		
	public List<OrderVO> getStoreGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 여신규정 
	 * 2. 처리내용 : 여신규정 값 select
	 * </pre>
	 * @Method Name : getCreditRate
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getCreditRate(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 담당자,보증인
	 * 2. 처리내용 : emp_no의 담당자,보증인 select  
	 * </pre>
	 * @Method Name : getGuarantor
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getGuarantor(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처의 여신 갯수
	 * 2. 처리내용 : emp_no의 거래처의 여신 갯수 select
	 * </pre>
	 * @Method Name : getCreditCount
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getCreditCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처등록의 여신한도
	 * 2. 처리내용 : emp_no의 거래처등록의 여신한도 select
	 * </pre>
	 * @Method Name : getCreditLimit
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getCreditLimit(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 사원정보
	 * 2. 처리내용 : emp_no의 사원정보 select
	 * </pre>
	 * @Method Name : getSawonInfo
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getSawonInfo(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 온라인주문상 여신/담보정보
	 * 2. 처리내용 : emp_no의 온라인주문상 여신/담보정보 select
	 * </pre>
	 * @Method Name : getCreditDamboInfo
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getCreditDamboInfo(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : ERP상 주문 총여신
	 * 2. 처리내용 : emp_no의 ERP상 주문 총여신 select
	 * </pre>
	 * @Method Name : getSaleTotCredit
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getSaleTotCredit(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 출하중지 체크
	 * 2. 처리내용 : emp_no의 출하중지 체크여부 select
	 * </pre>
	 * @Method Name : getBudongYn
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getBudongYn(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문한도
	 * 2. 처리내용 : emp_no의 주문한도 select
	 * </pre>
	 * @Method Name : getJumunLimit
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getJumunLimit(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 판매처 확인 체크
	 * 2. 처리내용 : emp_no의 판매처 확인  체크 select
	 * </pre>
	 * @Method Name : getStoreYn
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getStoreYn(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 판매처 확인
	 * 2. 처리내용 : 판매처 확인 select
	 * </pre>
	 * @Method Name : getStoreSawonInfo
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getStoreSawonInfo(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 출하중지
	 * 2. 처리내용 : 판매처의 출하중지 select
	 * </pre>
	 * @Method Name : getRcustBudongYn
	 * @param paramMap    Map<String, String> 
	 * @return	OrderVO
	 */		
	public OrderVO getRcustBudongYn(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 판매처별 제품 목록
	 * 2. 처리내용 : 판매처별 제품 목록을 select 
	 * </pre>
	 * @Method Name : getItemGridList
	 * @param paramMap    Map<String, String> 
	 * @return	List<ItemVO>
	 */		
	public List<ItemVO> getItemGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 해당제품이 마약,향정일때 납품처의 향정마약여부 체크
	 * 2. 처리내용 : 납품처의 향정마약여부 체크여부를 select
	 * </pre>
	 * @Method Name : getItemYn
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */		
	public ItemVO getItemYn(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 제품이 마약, 향정일 때 납품여부 체크
	 * 2. 처리내용 : 제품이 마약, 향정일 때 납품여부 체크 여부 select 
	 * </pre>
	 * @Method Name : getItemGb
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */		
	public ItemVO getItemGb(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처,주문일자,제품별 월평균, 해당월, 주문가능수량
	 * 2. 처리내용 : 거래처,주문일자,제품별 월평균, 해당월, 주문가능수량 select 
	 * </pre>
	 * @Method Name : getQtyCnt
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */		
	public ItemVO getQtyCnt(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 제품체크
	 * 2. 처리내용 : 제품상태 여부체크 select
	 * </pre>
	 * @Method Name : getItemChk
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */		
	public ItemVO getItemChk(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 창고재고
	 * 2. 처리내용 : 제품의 재고상태 select 
	 * </pre>
	 * @Method Name : getInvjaego
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */		
	public ItemVO getInvjaego(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 창고재고 : 제조번호별 재고의 합계
	 * 2. 처리내용 : 창고재고 : 제조번호별 재고의 합계 select
	 * </pre>
	 * @Method Name : getJaego
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */			
	public ItemVO getJaego(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 개시일자부터 3개월간은 주문수량
	 * 2. 처리내용 : 개시일자부터 3개월간은 주문수량 count
	 * @Method Name : getLiCnt
	 * @param paramMap    Map<String, String> 
	 * @return	ItemVO
	 */		
	public ItemVO getLiCnt(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문번호를 받기 위한 프로시져
	 * 2. 처리내용 : SP_SYS100C_MAX_VALUE procedure CALL
	 * </pre>
	 * @Method Name : getProcedureCall
	 * @param paramMap    Map<String, String> 
	 * @return	int
	 */		
	public int getProcedureCall(Map<String, Object> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 주문 Detail insert
	 * 2. 처리내용 :	주문 Detail insert
	 * </pre>
	 * @Method Name : insertDetailOrder
	 * @param detailItemVO
	 * @return	int
	 */		
	public int insertDetailOrder(ItemVO detailItemVO);

	/**
	 * <pre>
	 * 1. 개요     : 주문 마스터 테이블에 저장
	 * 2. 처리내용 :	주문 마스터 테이블에 저장
	 * </pre>
	 * @Method Name : insertMasterOrder
	 * @param masterItemVO
	 * @return	int
	 */		
	public int insertMasterOrder(ItemVO masterItemVO);

	/**
	 * <pre>
	 * 1. 개요     : 출고 수량 수정
	 * 2. 처리내용 :	출고 수량 수정
	 * </pre>
	 * @Method Name : updateChulgoQty
	 * @param detailItemVO
	 * @return	int
	 */		
	public int updateChulgoQty(ItemVO detailItemVO);

	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 master grid 
	 * 2. 처리내용 : 주문 master grid select
	 * </pre>
	 * @Method Name : getMasterGridList
	 * @param paramMap    Map<String, String> 
	 * @return	int
	 */
	public List<ItemVO> getMasterGridList(Map<String, String> paramMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 detail grid 
	 * 2. 처리내용 : 주문 detail grid select
	 * </pre>
	 * @Method Name : getDetailGridList
	 * @param paramMap    Map<String, String> 
	 * @return	int
	 */
	public List<ItemVO> getDetailGridList(Map<String, String> paramMap);

	/**
	 * 
	 * <pre>
	 * 1. 개요     : bas_amt  
	 * 2. 처리내용 : bas_amt select
	 * </pre>
	 * @Method Name : getBasDanga
	 * @param paramMap    Map<String, String> 
	 * @return	int
	 */
	public ItemVO getBasDanga(Map<String, String> paramMap);

	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문승인 체크
	 * 2. 처리내용 : 주문승인 여부 select
	 * </pre>
	 * @Method Name : getReceiptChk
	 * @param paramMap    Map<String, String> 
	 * @return
	 */
	public ItemVO getReceiptChk(Map<String, String> paramMap);

	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 detail 수정
	 * 2. 처리내용 : 주문 detail update
	 * </pre>
	 * @Method Name : updateDetailOrder
	 * @param detailItemVO
	 * @return	int
	 */
	public int updateDetailOrder(ItemVO detailItemVO);

	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 master 수정
	 * 2. 처리내용 : 주문 master update
	 * </pre>
	 * @Method Name : updateMasterOrder
	 * @param masterItemVO
	 * @return
	 */
	public int updateMasterOrder(ItemVO masterItemVO);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 master 금액만  수정
	 * 2. 처리내용 : 주문 master 금액만 update
	 * </pre>
	 * @Method Name : updateMasterAmt
	 * @param masterItemVO
	 * @return
	 */
	public int updateMasterAmt(ItemVO masterItemVO);

	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 detail 삭제
	 * 2. 처리내용 : 주문 status delete 
	 * </pre>
	 * @Method Name : deleteDetailOrder
	 * @param detailItemVO
	 * @return	int
	 */
	public int deleteDetailOrder(ItemVO detailItemVO);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 master 삭제
	 * 2. 처리내용 : 주문 master delete
	 * </pre>
	 * @Method Name : deleteMasterOrder
	 * @param masterItemVO
	 * @return	int
	 */
	public int deleteMasterOrder(ItemVO masterItemVO);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 주문 status grid 
	 * 2. 처리내용 : 주문 status grid select
	 * </pre>
	 * @Method Name : getOrderStatusGridList
	 * @param paramMap    Map<String, String> 
	 * @return	int
	 */
	public List<ItemVO> getOrderStatusGridList(Map<String, Object> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 회전일, 주문제어회전일 조회
	 * 2. 처리내용 : 거래처 회전일, 주문제어회전일 select
	 * </pre>
	 * @Method Name : getRateDay
	 * @param paramMap
	 * @return
	 */		
	public OrderVO getRateDay(Map<String, String> paramMap);


	/**
	 * <pre>
	 * 1. 개요     : 접수상태의주문금액 - 등록화면오픈시 주문가능금액계산용
	 * 2. 처리내용 : 접수상태의주문금액 select
	 * </pre>
	 * @Method Name : getJupsuAmt
	 * @param paramMap
	 * @return
	 */		
	public OrderVO getJupsuAmt(Map<String, String> paramMap);	
}
