/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanaph.saleon.business.dao.ReturnDAO;
import com.hanaph.saleon.business.vo.ReturnVO;

@Service(value="returnService")
public class ReturnServiceImpl implements ReturnService{
	
	@Autowired
	private ReturnDAO returnDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.ReturnService#getReturnGridList(java.util.Map)
	 */
	@Override
	public List<ReturnVO> getReturnGridList(Map<String, String> paramMap) {
		return returnDAO.getReturnGridList(paramMap); // 반품현황 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.ReturnService#getReturnGridTotalCount(java.util.Map)
	 */
	@Override
	public ReturnVO getReturnGridTotalCount(Map<String, String> paramMap) {
		return returnDAO.getReturnGridTotalCount(paramMap); // 반품현황 총 수
	}

}
