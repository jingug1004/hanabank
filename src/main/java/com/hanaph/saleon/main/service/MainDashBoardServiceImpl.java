/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.main.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanaph.saleon.main.dao.MainDashBoardDAO;
import com.hanaph.saleon.main.vo.CustDashboardVO;
import com.hanaph.saleon.main.vo.EmpDashboardVO;
import com.hanaph.saleon.main.vo.NoticeVO;

/**
 * <pre>
 * Class Name : MainDashBoardServiceImpl.java
 * 설명 : 공지사항, 메인 대시보드에 사용되는 데이터를 조회하기 위한 service implement class.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 8.      장일영            최초생성
 * </pre>
 * 
 * @version : 1.0
 * @author  : 장일영(goodhi@irush.co.kr)
 * @since   : 2014. 12. 8.
 */
@Service
public class MainDashBoardServiceImpl implements MainDashBoardService {

	@Autowired
	MainDashBoardDAO mainDashBoardDAO;
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.main.service.MainDashBoardService#getNoticeList(java.util.Map)
	 */
	@Override
	public List<NoticeVO> getNoticeList(Map<String, String> paramMap) {
		return mainDashBoardDAO.getNoticeList(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.main.service.MainDashBoardService#getCustInfo(java.util.Map)
	 */
	@Override
	public CustDashboardVO getCustInfo(Map<String, String> paramMap) {
		return mainDashBoardDAO.getCustInfo(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.main.service.MainDashBoardService#getCustLoanPresentCondition(java.util.Map)
	 */
	@Override
	public CustDashboardVO getCustLoanPresentCondition(
			Map<String, String> paramMap) {
		paramMap.put("ymd", DateFormatUtils.format(new Date(), "yyyyMMdd"));	//조회기준. 현재 년월일.
		return mainDashBoardDAO.getCustLoanPresentCondition(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.main.service.MainDashBoardService#getCustOrderPresentCondition(java.util.Map)
	 */
	@Override
	public List<CustDashboardVO> getCustOrderPresentCondition(
			Map<String, String> paramMap) {
		paramMap.put("ymdFr", paramMap.get("reqYear") + "0101");	//조회기간. 년월일 형태로 조회. 기본적으로 1월 1일부터
		paramMap.put("ymdTo", paramMap.get("reqYear") + "1231");	//조회기간. 년월일 형태로 조회. 기본적으로 12월 31일까지
		
		List<CustDashboardVO> list = mainDashBoardDAO.getCustOrderPresentCondition(paramMap);
		if(list != null && !list.isEmpty() ){
			list.add(list.size(), mainDashBoardDAO.getCustOrderTotal(paramMap));
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.main.service.MainDashBoardService#getEmpInfo(java.util.Map)
	 */
	@Override
	public EmpDashboardVO getEmpInfo(Map<String, String> paramMap) {
		return mainDashBoardDAO.getEmpInfo(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.main.service.MainDashBoardService#getEmpResultYear(java.util.Map)
	 */
	@Override
	public List<EmpDashboardVO> getEmpResultYear(Map<String, String> paramMap) {
		return mainDashBoardDAO.getEmpResultYear(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.main.service.MainDashBoardService#getPartResultYear(java.util.Map)
	 */
	@Override
	public List<EmpDashboardVO> getPartResultYear(Map<String, String> paramMap) {
		return mainDashBoardDAO.getPartResultYear(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.main.service.MainDashBoardService#getCompanyResultYear(java.util.Map)
	 */
	@Override
	public List<EmpDashboardVO> getCompanyResultYear(
			Map<String, String> paramMap) {
		return mainDashBoardDAO.getCompanyResultYear(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.main.service.MainDashBoardService#getCompanyResultMonthByPart(java.util.Map)
	 */
	@Override
	public List<EmpDashboardVO> getCompanyResultMonthByPart(
			Map<String, String> paramMap) {
		return mainDashBoardDAO.getCompanyResultMonthByPart(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.main.service.MainDashBoardService#getTeamResultYear(java.util.Map)
	 */
	@Override
	public List<EmpDashboardVO> getTeamResultYear(Map<String, String> paramMap) {
		return mainDashBoardDAO.getTeamResultYear(paramMap);
	}
}
