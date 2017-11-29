/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.hanaph.saleon.business.vo.OrderApprovalSearchVO;

@Repository("orderApprovalSearchDAO")
public class OrderApprovalSearchDAOImpl extends SqlSessionDaoSupport implements OrderApprovalSearchDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.OrderApprovalSearchDAO#getOrderSearchGridList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderApprovalSearchVO> getOrderSearchGridList(Map<String, String> paramMap) {
		return (List<OrderApprovalSearchVO>)getSqlSession().selectList("orderApprovalSearch.getOrderSearchGridList", paramMap); // 주문 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.OrderApprovalSearchDAO#getApprovalSearchGridList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderApprovalSearchVO> getApprovalSearchGridList(Map<String, String> paramMap) {
		return (List<OrderApprovalSearchVO>)getSqlSession().selectList("orderApprovalSearch.getApprovalSearchGridList", paramMap); // 승인 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.OrderApprovalSearchDAO#getOrderSearchGridTotalCount(java.util.Map)
	 */
	@Override
	public OrderApprovalSearchVO getOrderSearchGridTotalCount(Map<String, String> paramMap) {
		return (OrderApprovalSearchVO)getSqlSession().selectOne("orderApprovalSearch.getOrderSearchGridTotalCount", paramMap); // 주문 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.OrderApprovalSearchDAO#getApprovalSearchGridTotalCount(java.util.Map)
	 */
	@Override
	public OrderApprovalSearchVO getApprovalSearchGridTotalCount(Map<String, String> paramMap) {
		return (OrderApprovalSearchVO)getSqlSession().selectOne("orderApprovalSearch.getApprovalSearchGridTotalCount", paramMap); // 승인 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.OrderApprovalSearchDAO#getOrderApprovalDetailGridList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<OrderApprovalSearchVO> getOrderApprovalDetailGridList(Map<String, String> paramMap) {
		return (List<OrderApprovalSearchVO>)getSqlSession().selectList("orderApprovalSearch.getOrderApprovalDetailGridList", paramMap); // 주문 상세 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.OrderApprovalSearchDAO#getOrderApprovalDetailGridTotalCount(java.util.Map)
	 */
	@Override
	public OrderApprovalSearchVO getOrderApprovalDetailGridTotalCount(Map<String, String> paramMap) {
		return (OrderApprovalSearchVO)getSqlSession().selectOne("orderApprovalSearch.getOrderApprovalDetailGridTotalCount", paramMap); // 주문 상세 목록 총 수
	}
}
