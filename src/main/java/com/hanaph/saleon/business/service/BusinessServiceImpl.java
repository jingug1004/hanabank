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

import com.hanaph.saleon.business.dao.BusinessDAO;
import com.hanaph.saleon.business.vo.BusinessVO;

@Service(value="businessService")
public class BusinessServiceImpl implements BusinessService {
	
	@Autowired
	private BusinessDAO businessDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getCustomerGridList(java.util.Map)
	 */
	@Override
	public List<BusinessVO> getCustomerGridList(Map<String, String> paramMap) {
		return businessDAO.getCustomerGridList(paramMap); // 거래처 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getCustomerName(java.util.Map)
	 */
	@Override
	public String getCustomerName(Map<String, String> paramMap) {
		return businessDAO.getCustomerName(paramMap); // 거래처 명
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getCustomerGridTotalCount(java.util.Map)
	 */
	@Override
	public int getCustomerGridTotalCount(Map<String, String> paramMap) {
		return businessDAO.getCustomerGridTotalCount(paramMap); // 거래처 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getPartGridList(java.util.Map)
	 */
	@Override
	public List<BusinessVO> getPartGridList(Map<String, String> paramMap) {
		return businessDAO.getPartGridList(paramMap); // 파트 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getPartGridTotalCount(java.util.Map)
	 */
	@Override
	public int getPartGridTotalCount(Map<String, String> paramMap) {
		return businessDAO.getPartGridTotalCount(paramMap); // 파트 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getTeamGridList(java.util.Map)
	 */
	@Override
	public List<BusinessVO> getTeamGridList(Map<String, String> paramMap) {
		return businessDAO.getTeamGridList(paramMap); // 부서 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getTeamGridTotalCount(java.util.Map)
	 */
	@Override
	public int getTeamGridTotalCount(Map<String, String> paramMap) {
		return businessDAO.getTeamGridTotalCount(paramMap); // 부서 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getEmpGridList(java.util.Map)
	 */
	@Override
	public List<BusinessVO> getEmpGridList(Map<String, String> paramMap) {
		return businessDAO.getEmpGridList(paramMap); // 사원 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getEmpGridTotalCount(java.util.Map)
	 */
	@Override
	public int getEmpGridTotalCount(Map<String, String> paramMap) {
		return businessDAO.getEmpGridTotalCount(paramMap); // 사원 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getPerformanceName(java.util.Map)
	 */
	@Override
	public String getPerformanceName(Map<String, String> paramMap) {
		String searchType = paramMap.get("searchType");
		String performanceName = "";
		
		/*조회구분에 따라 분기*/
		if ("part".equals(searchType)) {
			performanceName = businessDAO.getPartName(paramMap); // 파트 명
		} else if ("team".equals(searchType)) {
			performanceName = businessDAO.getTeamName(paramMap); // 부서 명
		} else if ("emp".equals(searchType)) {
			performanceName = businessDAO.getEmpName(paramMap); // 사원 명
		}
		
		return performanceName;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getCommonEmpInfo(java.util.Map)
	 */
	@Override
	public BusinessVO getCommonEmpInfo(Map<String, String> paramMap) {
		return businessDAO.getCommonEmpInfo(paramMap); // 부서코드, pda 권한
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getCustomerType(java.util.Map)
	 */
	@Override
	public BusinessVO getCustomerType(Map<String, String> paramMap) {
		return businessDAO.getCustomerType(paramMap); // 거래처 타입
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getPromiseGridList(java.util.Map)
	 */
	@Override
	public List<BusinessVO> getPromiseGridList(Map<String, String> paramMap) {
		return businessDAO.getPromiseGridList(paramMap); // 담보약속 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.BusinessService#getPromiseGridTotalCount(java.util.Map)
	 */
	@Override
	public int getPromiseGridTotalCount(Map<String, String> paramMap) {
		return businessDAO.getPromiseGridTotalCount(paramMap); // 담보약속 목록 총 수
	}
	
}
