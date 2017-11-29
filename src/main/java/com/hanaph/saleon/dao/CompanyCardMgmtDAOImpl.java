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

import com.hanaph.saleon.business.vo.CompanyCardMgmtVO;

@Repository("companyCardMgmtDAO")
public class CompanyCardMgmtDAOImpl extends SqlSessionDaoSupport implements CompanyCardMgmtDAO{

	@Override
	public CompanyCardMgmtVO getEmpInfo(Map<String, String> paramMap) {
		return (CompanyCardMgmtVO)getSqlSession().selectOne("companyCardMgmt.getEmpInfo", paramMap); // 로그인한 사원의 사번, 사원명
	}

	@Override
	public String getAssgnCd(Map<String, String> paramMap) {
		return (String)getSqlSession().selectOne("companyCardMgmt.getAssgnCd", paramMap); // 로그인한 사원의 직책
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CompanyCardMgmtVO> getGaejungCodeList() {
		return (List<CompanyCardMgmtVO>)getSqlSession().selectList("companyCardMgmt.getGaejungCodeList"); // 계정과목
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<CompanyCardMgmtVO> getCompanyCardHistoryGridList(Map<String, String> paramMap) {
		return (List<CompanyCardMgmtVO>)getSqlSession().selectList("companyCardMgmt.getCompanyCardHistoryGridList", paramMap); // 법인카드 사용내역
	}

	@Override
	public CompanyCardMgmtVO getCompanyCardHistoryGridTotalCount(Map<String, String> paramMap) {
		return (CompanyCardMgmtVO)getSqlSession().selectOne("companyCardMgmt.getCompanyCardHistoryGridTotalCount", paramMap); // 법인카드 사용내역 총 수
	}

	@Override
	public void updateCardUseDetail(CompanyCardMgmtVO companyCardMgmtVO) {
		getSqlSession().update("companyCardMgmt.updateCardUseDetail", companyCardMgmtVO); // 법인카드 사용내역 수정
	}

}
