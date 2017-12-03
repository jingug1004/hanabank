/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.CompanyCardMgmtVO;

/**
 * <pre>
 * Class Name : CompanyCardMgmtDAO.java
 * 설명 : 법인카드관리 IBK DAO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 24.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 11. 24.
 */
public interface CompanyCardMgmtDAO {

	/**
	 * <pre>
	 * 1. 개요     : 로그인한 사원의 사번, 사원명
	 * 2. 처리내용 : 로그인한 사원의 사번, 사원명을 반환한다.
	 * </pre>
	 * @Method Name : getEmpInfo
	 * @param paramMap
	 * @return
	 */		
	public CompanyCardMgmtVO getEmpInfo(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 로그인한 사원의 직책
	 * 2. 처리내용 : 로그인한 사원의 직책을 반환한다.
	 * </pre>
	 * @Method Name : getAssgnCd
	 * @param paramMap
	 * @return
	 */		
	public String getAssgnCd(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 법인카드사용내역 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 법인카드사용내역 목록을 반환한다.
	 * </pre>
	 * @Method Name : getCompanyCardHistoryGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CompanyCardMgmtVO> getCompanyCardHistoryGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 법인카드사용내역 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 법인카드사용내역 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getCompanyCardHistoryGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CompanyCardMgmtVO getCompanyCardHistoryGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 계정과목 목록
	 * 2. 처리내용 : 계정과목 목록을 반환한다.
	 * </pre>
	 * @Method Name : getGaejungCodeList
	 * @return
	 */		
	public List<CompanyCardMgmtVO> getGaejungCodeList();

	/**
	 * <pre>
	 * 1. 개요     : 법인카드사용내역 추가정보 수정
	 * 2. 처리내용 : 법인카드사용내역 추가정보를 수정한다.
	 * </pre>
	 * @Method Name : updateCardUseDetail
	 * @param companyCardMgmtVO
	 */		
	public void updateCardUseDetail(CompanyCardMgmtVO companyCardMgmtVO);
	
}
