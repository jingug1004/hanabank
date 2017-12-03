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

import com.hanaph.saleon.business.dao.PerformanceDAO;
import com.hanaph.saleon.business.vo.PerformanceVO;

@Service(value="performanceService")
public class PerformanceServiceImpl implements PerformanceService{
	
	@Autowired
	private PerformanceDAO performanceDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.PerformanceService#getPerformanceGridList(java.util.Map)
	 */
	@Override
	public List<PerformanceVO> getPerformanceGridList(Map<String, String> paramMap) {
		
		String selectType = paramMap.get("selectType"); // 조회구분
		List<PerformanceVO> performanceList = null;
		
		/*조회구분에 따라 분기*/
		if ("part".equals(selectType)) {
			performanceList = performanceDAO.getPerformanceForPartGridList(paramMap); // 파트별 조회
		} else if ("team".equals(selectType)) {
			performanceList = performanceDAO.getPerformanceForTeamGridList(paramMap); // 부서별 조회
		} else if ("emp".equals(selectType)) {
			performanceList = performanceDAO.getPerformanceForEmpGridList(paramMap); // 사원별 조회
		}
		
		return performanceList;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.PerformanceService#getPerformanceGridTotalCount(java.util.Map)
	 */
	@Override
	public PerformanceVO getPerformanceGridTotalCount(Map<String, String> paramMap) {
		
		String selectType = paramMap.get("selectType"); // 조회구분
		PerformanceVO performanceVO = null;
		
		/*조회구분에 따라 분기*/
		if ("part".equals(selectType)) {
			performanceVO = performanceDAO.getPerformanceForPartGridTotalCount(paramMap); // 파트별 조회
		} else if ("team".equals(selectType)) {
			performanceVO = performanceDAO.getPerformanceForTeamGridTotalCount(paramMap); // 부서별 조회
		} else if ("emp".equals(selectType)) {
			performanceVO = performanceDAO.getPerformanceForEmpGridTotalCount(paramMap); // 사원별 조회
		}
		
		return performanceVO;
	}

}
