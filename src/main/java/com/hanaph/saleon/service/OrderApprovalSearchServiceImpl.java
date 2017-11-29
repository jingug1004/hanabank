/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanaph.saleon.business.dao.OrderApprovalSearchDAO;
import com.hanaph.saleon.business.vo.OrderApprovalSearchVO;

@Service(value="orderApprovalSearchService")
public class OrderApprovalSearchServiceImpl implements OrderApprovalSearchService {
	
	@Autowired
	private OrderApprovalSearchDAO orderApprovalSearchDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalSearchService#getOrderApprovalSearchGridList(java.util.Map)
	 */
	@Override
	public List<OrderApprovalSearchVO> getOrderApprovalSearchGridList(Map<String, String> paramMap) {
		
		List<OrderApprovalSearchVO> returnList = new ArrayList<OrderApprovalSearchVO>();
		
		/*조회구분에 따라 분기*/
		if ("order".equals(paramMap.get("selectType"))) {
			returnList = orderApprovalSearchDAO.getOrderSearchGridList(paramMap); // 주문 목록
		} else if ("approval".equals(paramMap.get("selectType"))) {
			returnList = orderApprovalSearchDAO.getApprovalSearchGridList(paramMap); // 승인 목록
		}
		
		return returnList;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalSearchService#getOrderApprovalSearchGridTotalCount(java.util.Map)
	 */
	@Override
	public OrderApprovalSearchVO getOrderApprovalSearchGridTotalCount(Map<String, String> paramMap) {
		
		OrderApprovalSearchVO returnVO = new OrderApprovalSearchVO();
		
		/*조회구분에 따라 분기*/
		if ("order".equals(paramMap.get("selectType"))) {
			returnVO = orderApprovalSearchDAO.getOrderSearchGridTotalCount(paramMap); // 주문 목록 총 수
		} else if ("approval".equals(paramMap.get("selectType"))) {
			returnVO = orderApprovalSearchDAO.getApprovalSearchGridTotalCount(paramMap); // 승인 목록 총 수
		}

		return returnVO;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalSearchService#getOrderApprovalDetailGridList(java.util.Map)
	 */
	@Override
	public List<OrderApprovalSearchVO> getOrderApprovalDetailGridList(Map<String, String> paramMap) {
		return orderApprovalSearchDAO.getOrderApprovalDetailGridList(paramMap); // 주문 상세 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalSearchService#getOrderApprovalDetailGridTotalCount(java.util.Map)
	 */
	@Override
	public OrderApprovalSearchVO getOrderApprovalDetailGridTotalCount(Map<String, String> paramMap) {
		return orderApprovalSearchDAO.getOrderApprovalDetailGridTotalCount(paramMap); // 주문 상세 목록 총 수
	}
	
}
