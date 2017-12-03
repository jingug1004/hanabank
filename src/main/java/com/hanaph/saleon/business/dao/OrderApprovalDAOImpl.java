/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.business.dao;

import com.hanaph.saleon.business.vo.OrderApprovalVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("orderApprovalDAO")
public class OrderApprovalDAOImpl extends SqlSessionDaoSupport implements OrderApprovalDAO {

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getOrderApprovalGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<OrderApprovalVO> getOrderApprovalGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("orderApproval.getOrderApprovalGridList", paramMap); // 주문내역 목록
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getOrderApprovalGridListExcel(java.util.Map)
     * kta
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<OrderApprovalVO> getOrderApprovalGridListExcel(
            Map<String, String> paramMap) {
        return getSqlSession().selectList("orderApproval.getOrderApprovalGridListExcel", paramMap); // 주문내역 엑셀 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getOrderApprovalGridTotalCount(java.util.Map)
     */
    @Override
    public OrderApprovalVO getOrderApprovalGridTotalCount(Map<String, String> paramMap) {
        return (OrderApprovalVO) getSqlSession().selectOne("orderApproval.getOrderApprovalGridTotalCount", paramMap); // 주문내역 총 수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getOrderDetailGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<OrderApprovalVO> getOrderDetailGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("orderApproval.getOrderDetailGridList", paramMap); // 주문세부내역
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getOrderDetailGridTotalCount(java.util.Map)
     */
    @Override
    public OrderApprovalVO getOrderDetailGridTotalCount(Map<String, String> paramMap) {
        return (OrderApprovalVO) getSqlSession().selectOne("orderApproval.getOrderDetailGridTotalCount", paramMap); // 주문세부내역 총 수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getCustomerInfo(java.util.Map)
     */
    @Override
    public OrderApprovalVO getCustomerInfo(Map<String, String> paramMap) {
        return (OrderApprovalVO) getSqlSession().selectOne("orderApproval.getCustomerInfo", paramMap); // 거래처 정보
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getApprovalInfo(java.util.Map)
     */
    @Override
    public OrderApprovalVO getApprovalInfo(Map<String, String> paramMap) {
        return (OrderApprovalVO) getSqlSession().selectOne("orderApproval.getApprovalInfo", paramMap); // 주문승인 정보
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getPromiseGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<OrderApprovalVO> getPromiseGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("orderApproval.getPromiseGridList", paramMap); // 담보약속 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getUnapprovedAmount(java.util.Map)
     */
    @Override
    public String getUnapprovedAmount(Map<String, String> paramMap) {
        return (String) getSqlSession().selectOne("orderApproval.getUnapprovedAmount", paramMap); // 총미승인금액(실조건아님) 정보
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getUnapprovalAmountReal(java.util.Map)
     */
    @Override
    public String getUnapprovalAmountReal(Map<String, String> paramMap) {
        return (String) getSqlSession().selectOne("orderApproval.getUnapprovedAmountReal", paramMap); // 총미승인금액(실조건) 정보
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getPromiseGridTotalCount(java.util.Map)
     */
    @Override
    public OrderApprovalVO getPromiseGridTotalCount(Map<String, String> paramMap) {
        return (OrderApprovalVO) getSqlSession().selectOne("orderApproval.getPromiseGridTotalCount", paramMap); // 담보약속 목록 총 수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getStoreLocCount()
     */
    @Override
    public int getStoreLocCount() {
        return (Integer) getSqlSession().selectOne("orderApproval.getStoreLocCount"); // 이월작업 여부
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getCreditLimit(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public OrderApprovalVO getCreditLimit(OrderApprovalVO orderApprovalVO) {
        return (OrderApprovalVO) getSqlSession().selectOne("orderApproval.getCreditLimit", orderApprovalVO); // 여신한도
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getBudongFlag(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public String getBudongFlag(OrderApprovalVO orderApprovalVO) {
        return (String) getSqlSession().selectOne("orderApproval.getBudongFlag", orderApprovalVO); // 출하중지처 여부
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getItemInfo(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public OrderApprovalVO getItemInfo(OrderApprovalVO orderApprovalVO) {
        return (OrderApprovalVO) getSqlSession().selectOne("orderApproval.getItemInfo", orderApprovalVO); // 제품 상태
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getDangaQtyAmtErrJumun(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public OrderApprovalVO getDangaQtyAmtErrJumun(OrderApprovalVO orderApprovalVO) {
        return (OrderApprovalVO) getSqlSession().selectOne("orderApproval.getDangaQtyAmtErrJumun", orderApprovalVO); // 주문금액체크
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getReceiptGb(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public OrderApprovalVO getReceiptGb(OrderApprovalVO orderApprovalVO) {
        return (OrderApprovalVO) getSqlSession().selectOne("orderApproval.getReceiptGb", orderApprovalVO); // 주문상태
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getThisorderAmt(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public OrderApprovalVO getThisorderAmt(OrderApprovalVO orderApprovalVO) {
        // TODO Auto-generated method stub
        return (OrderApprovalVO) getSqlSession().selectOne("orderApproval.getThisorderAmt", orderApprovalVO); // 이 주문의 주문금액
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getProcedureCall(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public String getProcedureCall(OrderApprovalVO orderApprovalVO) { // 승인번호 생성
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("tableType", "SALE0203");
        paramMap.put("app_date", orderApprovalVO.getApp_date());
        getSqlSession().selectOne("orderApproval.getProcedureCall", paramMap);
        return paramMap.get("max");
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#updateOrderMasterApproval(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int updateOrderMasterApproval(OrderApprovalVO orderApprovalVO) {
        return getSqlSession().update("orderApproval.updateOrderMasterApproval", orderApprovalVO); // SALE_ON 주문 MASTER 승인
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#updateOrderStock(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int updateOrderStock(OrderApprovalVO orderApprovalVO) {
        return getSqlSession().update("orderApproval.updateOrderStock", orderApprovalVO); // 재고변경
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#insertTransferOrderMaster(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int insertTransferOrderMaster(OrderApprovalVO orderApprovalVO) {
        return getSqlSession().insert("orderApproval.insertTransferOrderMaster", orderApprovalVO); // ERP 주문 MASTER 이관

    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#insertTransferOrderDetail(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int insertTransferOrderDetail(OrderApprovalVO orderApprovalVO) {
        return getSqlSession().insert("orderApproval.insertTransferOrderDetail", orderApprovalVO); // ERP 주문 DETAIL 이관
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#updateAddOrderStock(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int updateAddOrderStock(OrderApprovalVO orderApprovalVO) {
        return getSqlSession().update("orderApproval.updateAddOrderStock", orderApprovalVO); // 재고변경(추가 승인)
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#insertTransferAddOrderMaster(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int insertTransferAddOrderMaster(OrderApprovalVO orderApprovalVO) {
        return getSqlSession().insert("orderApproval.insertTransferAddOrderMaster", orderApprovalVO); // ERP 주문 MASTER 이관(추가 승인)
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#updateOrderMasterReturn(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int updateOrderMasterReturn(OrderApprovalVO orderApprovalVO) {
        return getSqlSession().update("orderApproval.updateOrderMasterReturn", orderApprovalVO); // SALE_ON 주문 MASTER 반려
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#deleteTransferOrderDetail(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int deleteTransferOrderDetail(OrderApprovalVO paramVO) {
        return getSqlSession().delete("orderApproval.deleteTransferOrderDetail", paramVO); // ERP 주문 DETAIL 삭제
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#deleteTransferOrderMaster(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int deleteTransferOrderMaster(OrderApprovalVO paramVO) {
        return getSqlSession().delete("orderApproval.deleteTransferOrderMaster", paramVO); // ERP 주문 MASTER 삭제
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#cancelOrderMasterApproval(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int cancelOrderMasterApproval(OrderApprovalVO paramVO) {
        return getSqlSession().update("orderApproval.cancelOrderMasterApproval", paramVO); // 승인/반려 취소
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#updateOrderDetailQty(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int updateOrderDetailQty(OrderApprovalVO paramVO) {
        return getSqlSession().update("orderApproval.updateOrderDetailQty", paramVO); // 주문 상세 승인 수량 수정
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#updateCancelOrderStock(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int updateCancelOrderStock(OrderApprovalVO paramVO) {
        return getSqlSession().update("orderApproval.updateCancelOrderStock", paramVO); // 재고변경

    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#cancelOrderDetailApproval(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @Override
    public int cancelOrderDetailApproval(OrderApprovalVO paramVO) {
        return getSqlSession().update("orderApproval.cancelOrderDetailApproval", paramVO); // 승인/반려 취소
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.OrderApprovalDAO#getItemYn(com.hanaph.saleon.business.vo.OrderApprovalVO)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<OrderApprovalVO> getItemYnList(Map<String, String> paramMap) {
        return getSqlSession().selectList("orderApproval.getItemYnList", paramMap); // 제품의 마약류, 향정신성의약품류 여부
    }
}
