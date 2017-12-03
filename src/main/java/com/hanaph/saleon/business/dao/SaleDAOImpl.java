/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.dao;

import com.hanaph.saleon.business.vo.SaleVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("saleDAO")
public class SaleDAOImpl extends SqlSessionDaoSupport implements SaleDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.SaleDAO#getSaleGridList(java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<SaleVO> getSaleGridList(Map<String, String> paramMap) {
		return getSqlSession().selectList("sale.getSaleGridList", paramMap); // 판매현황 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.SaleDAO#getSaleGridTotalCount(java.util.Map)
	 */
	@Override
	public SaleVO getSaleGridTotalCount(Map<String, String> paramMap) {
		return (SaleVO) getSqlSession().selectOne("sale.getSaleGridTotalCount", paramMap); // 판매현황 목록 총 수
	}

}
