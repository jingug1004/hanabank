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

import com.hanaph.saleon.business.dao.SaleDAO;
import com.hanaph.saleon.business.vo.SaleVO;

@Service(value="saleService")
public class SaleServiceImpl implements SaleService{
	
	@Autowired
	private SaleDAO saleDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.SaleService#getSaleGridList(java.util.Map)
	 */
	@Override
	public List<SaleVO> getSaleGridList(Map<String, String> paramMap) {
		return saleDAO.getSaleGridList(paramMap); // 판매현황 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.SaleService#getSaleGridTotalCount(java.util.Map)
	 */
	@Override
	public SaleVO getSaleGridTotalCount(Map<String, String> paramMap) {
		return saleDAO.getSaleGridTotalCount(paramMap); // 판매현황 목록 총 수
	}

}
