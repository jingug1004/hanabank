/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.OrderApprovalVO;

/**
 * <pre>
 * Class Name : OrderApprovalDAO.java
 * 설명 : 주문승인 DAO
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
/**
 * <pre>
 * Class Name : OrderApprovalDAO.java
 * 설명 : 
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015. 11. 4.      KTA          
 * </pre>
 * 
 * @version : 
 * @author  : KTA(@irush.co.kr)
 * @since   : 2015. 11. 4.
 */
/**
 * <pre>
 * Class Name : OrderApprovalDAO.java
 * 설명 : 
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015. 11. 4.      KTA          
 * </pre>
 * 
 * @version : 
 * @author  : KTA(@irush.co.kr)
 * @since   : 2015. 11. 4.
 */
/**
 * <pre>
 * Class Name : OrderApprovalDAO.java
 * 설명 : 
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015. 11.04.      KTA
 *  2016. 10.07.   단가수량금액오류 주문내역화 주문세무내역 화면에 표시되도록 기능 추가          
 * </pre>
 * 
 * @version : 
 * @author  : KTA(@irush.co.kr)
 * @since   : 2015. 11. 4.
 */ 
public interface OrderApprovalDAO {

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
	 * 1. 개요     : 주문내역 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 주문내역의 목록을 반환한다.
	 * </pre>
	 * @Method Name : getOrderApprovalGridList
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
	 * 1. 개요     : 미승인액(실조건아님)
	 * 2. 처리내용 : 미승인액을 반환한다.
	 * </pre>
	 * @Method Name : getUnapprovedAmount
	 * @param paramMap
	 * @return
	 */		
	public String getUnapprovedAmount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 미승인액(실조건)
	 * 2. 처리내용 : 미승인액(실조건)을 반환한다.
	 * </pre>
	 * @Method Name : getUnapprovalAmountReal
	 * @param paramMap
	 * @return
	 */		
	public String getUnapprovalAmountReal(Map<String, String> paramMap); 
	
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
	 * 1. 개요     : 이월 작업 체크
	 * 2. 처리내용 : 오늘 기준으로 이월 작업을 체크한다.
	 * </pre>
	 * @Method Name : getStoreLocCount
	 * @return
	 */		
	public int getStoreLocCount();

	/**
	 * <pre>
	 * 1. 개요     : 여신한도 체크
	 * 2. 처리내용 : 여신한도를 체크한다.
	 * </pre>
	 * @Method Name : getCreditLimit
	 * @param orderApprovalVO
	 * @return
	 */		
	public OrderApprovalVO getCreditLimit(OrderApprovalVO orderApprovalVO);

	/**
	 * <pre>
	 * 1. 개요     : 출하중지처 체크
	 * 2. 처리내용 : 출하중지 여부를 체크한다.
	 * </pre>
	 * @Method Name : getBudongFlag
	 * @param orderApprovalVO
	 * @return
	 */		
	public String getBudongFlag(OrderApprovalVO orderApprovalVO);

	/**
	 * <pre>
	 * 1. 개요     : 제품 체크
	 * 2. 처리내용 : 제품의 상태를 체크한다.
	 * </pre>
	 * @Method Name : getItemInfo
	 * @param orderApprovalVO
	 * @return
	 */		
	public OrderApprovalVO getItemInfo(OrderApprovalVO orderApprovalVO);



	/**
	 * <pre>
	 * 1. 개요     : 주문금액체크
	 * 2. 처리내용 :수량*단가가 금액과 다른지 체크하여 정보리턴
	 * </pre>
	 * @Method Name : getDangaQtyAmtErrJumun
	 * @param orderApprovalVO
	 * @return
	 */		
	public OrderApprovalVO getDangaQtyAmtErrJumun(OrderApprovalVO orderApprovalVO);
	
	
	/**
	 * <pre>
	 * 1. 개요     : 주문상태 체크
	 * 2. 처리내용 : 주문상태를 체크한다.
	 * </pre>
	 * @Method Name : getReceiptGb
	 * @param orderApprovalVO
	 * @return
	 */		
	public OrderApprovalVO getReceiptGb(OrderApprovalVO orderApprovalVO);
 
	
	/**
	 * <pre>
	 * 1. 개요     : 현 주문의 주문금액을 가져옴
	 * 2. 처리내용 : 승인시 for 문안에서 각 주문의 주문금액을 가져옴
	 * </pre>
	 * @Method Name : getThisorderAmt
	 * @param orderApprovalVO
	 * @return
	 */		
	public OrderApprovalVO getThisorderAmt(OrderApprovalVO orderApprovalVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 승인번호 생성
	 * 2. 처리내용 : 승인번호를 생성한다.
	 * </pre>
	 * @Method Name : getProcedureCall
	 * @param orderApprovalVO
	 * @return
	 */		
	public String getProcedureCall(OrderApprovalVO orderApprovalVO);

	/**
	 * <pre>
	 * 1. 개요     : SALE_ON 주문 MASTER 승인
	 * 2. 처리내용 : 주문 내용을 승인한다.
	 * </pre>
	 * @Method Name : updateOrderMasterApproval
	 * @param orderApprovalVO
	 * @return
	 */		
	public int updateOrderMasterApproval(OrderApprovalVO orderApprovalVO);

	/**
	 * <pre>
	 * 1. 개요     : 재고변경
	 * 2. 처리내용 : 승인 월의 1일자 가출고를 빼주고 가용재고를 더해준다.
	 * </pre>
	 * @Method Name : updateOrderStock
	 * @param orderApprovalVO
	 */		
	public int updateOrderStock(OrderApprovalVO orderApprovalVO);

	/**
	 * <pre>
	 * 1. 개요     : ERP 주문 MASTER 이관
	 * 2. 처리내용 : 주문 내역 MASTER를 이관한다.
	 * </pre>
	 * @Method Name : insertTransferOrderMaster
	 * @param orderApprovalVO
	 */		
	public int insertTransferOrderMaster(OrderApprovalVO orderApprovalVO);

	/**
	 * <pre>
	 * 1. 개요     : ERP 주문 DETAIL 이관
	 * 2. 처리내용 : 주문 내역 DETAIL을 이관한다.
	 * </pre>
	 * @Method Name : insertTransferOrderDetail
	 * @param orderApprovalVO
	 */		
	public int insertTransferOrderDetail(OrderApprovalVO orderApprovalVO);

	/**
	 * <pre>
	 * 1. 개요     : 재고변경(추가 승인)
	 * 2. 처리내용 : 승인 월의 1일자 가출고를 빼주고 가용재고를 더해준다.
	 * </pre>
	 * @Method Name : updateAddOrderStock
	 * @param orderApprovalVO
	 */		
	public int updateAddOrderStock(OrderApprovalVO orderApprovalVO);

	/**
	 * <pre>
	 * 1. 개요     : ERP 주문 MASTER 이관(추가 승인)
	 * 2. 처리내용 : 주문 내역 MASTER를 이관한다.
	 * </pre>
	 * @Method Name : insertTransferAddOrderMaster
	 * @param orderApprovalVO
	 */		
	public int insertTransferAddOrderMaster(OrderApprovalVO orderApprovalVO);

	/**
	 * <pre>
	 * 1. 개요     : SALE_ON 주문 MASTER 반려
	 * 2. 처리내용 : 주문 내용을 반려한다.
	 * </pre>
	 * @Method Name : updateOrderMasterReturn
	 * @param orderApprovalVO
	 * @return
	 */		
	public int updateOrderMasterReturn(OrderApprovalVO orderApprovalVO);

	/**
	 * <pre>
	 * 1. 개요     : ERP 주문 DETAIL 삭제
	 * 2. 처리내용 : 주문 detail을 삭제한다.
	 * </pre>
	 * @Method Name : deleteTransferOrderDetail
	 * @param paramVO
	 * @return
	 */		
	public int deleteTransferOrderDetail(OrderApprovalVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : ERP 주문 MASTER 삭제
	 * 2. 처리내용 : 주문 master를 삭제한다.
	 * </pre>
	 * @Method Name : deleteTransferOrderMaster
	 * @param paramVO
	 * @return
	 */		
	public int deleteTransferOrderMaster(OrderApprovalVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 승인/반려 취소
	 * 2. 처리내용 : 이미 승인/반려 된 주문을 취소한다.
	 * </pre>
	 * @Method Name : cancelOrderMasterApproval
	 * @param paramVO
	 * @return
	 */		
	public int cancelOrderMasterApproval(OrderApprovalVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 주문 상세 승인 수량 수정
	 * 2. 처리내용 : 주문 상세 내역의 승인 수량을 수정한다.
	 * </pre>
	 * @Method Name : updateOrderDetailQty
	 * @param paramVO
	 */		
	public int updateOrderDetailQty(OrderApprovalVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 재고변경(취소)
	 * 2. 처리내용 : 승인 월의 1일자 가출고를 더해준다.
	 * </pre>
	 * @Method Name : updateCancelOrderStock
	 * @param paramVO
	 */		
	public int updateCancelOrderStock(OrderApprovalVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 승인/반려 취소 시 상세 수정
	 * 2. 처리내용 : 이미 승인/반려 된 주문상세를 수정한다.
	 * </pre>
	 * @Method Name : cancelOrderDetailApproval
	 * @param paramVO
	 */		
	public int cancelOrderDetailApproval(OrderApprovalVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 제품의 마약, 향정신성의약품 체크
	 * 2. 처리내용 : 주문 접수 된 제품이 마약인지, 향정신성의약품인지 체크한다.
	 * </pre>
	 * @Method Name : getItemYn
	 * @param paramVO
	 * @return
	 */		
	public List<OrderApprovalVO> getItemYnList(Map<String, String> paramMap);
}
