/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.main.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.main.vo.CustDashboardVO;
import com.hanaph.saleon.main.vo.EmpDashboardVO;
import com.hanaph.saleon.main.vo.NoticeVO;

/**
 * <pre>
 * Class Name : MainDashBoardDAO.java
 * 설명 : 공지사항, 메인 대시보드에 사용되는 데이터를 조회하기 위한 dao interface.
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
public interface MainDashBoardDAO {
	
	/**
	 * <pre>
	 * 1. 개요     : 전체/단건 공지사항 리스트 조회
	 * 2. 처리내용 :	전체/단건 공지사항 리스트 조회
	 * </pre>
	 * @Method Name : getNoticeList
	 * @param paramMap	notice_id값이 있을 경우 단건 조회, 없을 경우 전체 조회
	 * @return	공지사항 리스트
	 */		
	public List<NoticeVO> getNoticeList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 거래처 정보 조회
	 * 2. 처리내용 :	거래처 정보 조회
	 * </pre>
	 * @Method Name : getCustInfo
	 * @param paramMap	custId : 거래처ID
	 * @return	거래처 정보
	 */		
	public CustDashboardVO getCustInfo(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 거래처 여신 현황 조회
	 * 2. 처리내용 :	거래처 여신 현황 조회
	 * </pre>
	 * @Method Name : getCustLoanPresentCondition
	 * @param paramMap	ymd : 조회년월일 , custId : 거래처ID
	 * @return	거래처 여신 현황
	 */		
	public CustDashboardVO getCustLoanPresentCondition(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 거래처 월별 주문 현황 조회
	 * 2. 처리내용 :	거래처 월별 주문 현황 조회
	 * </pre>
	 * @Method Name : getCustOrderPresentCondition
	 * @param paramMap	custId : 거래처ID, ymdFr : 조회기간 시작 년월일, ymdTo : 조회기간 끝 년월일
	 * @return	거래처 월별 주문 현황
	 */		
	public List<CustDashboardVO> getCustOrderPresentCondition(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 거래처 주문 현황 합계 조회
	 * 2. 처리내용 :	거래처 주문 현황 합계 조회
	 * </pre>
	 * @Method Name : getCustOrderTotal
	 * @param paramMap	custId : 거래처ID, ymdFr : 조회기간 시작 년월일, ymdTo : 조회기간 끝 년월일
	 * @return	거래처 주문 현황 합계
	 */		
	public CustDashboardVO getCustOrderTotal(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 영업사원 정보 조회
	 * 2. 처리내용 :	영업사원 정보 조회
	 * </pre>
	 * @Method Name : getEmpInfo
	 * @param paramMap	empId : 사원 코드
	 * @return	영업사원 정보
	 */		
	public EmpDashboardVO getEmpInfo(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 영업사원 년간 실적 조회
	 * 2. 처리내용 :	영업사원 년간 실적 조회
	 * </pre>
	 * @Method Name : getEmpResultYear
	 * @param paramMap	year : 조회기간, empId : 사원 코드
	 * @return	영업사원 년간 실적
	 */		
	public List<EmpDashboardVO> getEmpResultYear(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 파트 년간 실적 조회
	 * 2. 처리내용 :	파트 년간 실적 조회
	 * </pre>
	 * @Method Name : getPartResultYear
	 * @param paramMap	year : 조회기간, partCd : 파트 코드
	 * @return	파트 년간 실적
	 */		
	public List<EmpDashboardVO> getPartResultYear(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 회사 년간 실적 조회
	 * 2. 처리내용 :	회사 년간 실적 조회
	 * </pre>
	 * @Method Name : getCompanyResultYear
	 * @param paramMap	year : 조회기간
	 * @return	회사 년간 실적
	 */		
	public List<EmpDashboardVO> getCompanyResultYear(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 파트별 월별 실적 조회 
	 * 2. 처리내용 :	파트별 월별 실적 조회 
	 * </pre>
	 * @Method Name : getCompanyResultMonthByPart
	 * @param paramMap	year : 조회기간
	 * @return	파트별 월별 실적
	 */		
	public List<EmpDashboardVO> getCompanyResultMonthByPart(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 부서 년간 실적 조회
	 * 2. 처리내용 : 부서 년간 실적 조회
	 * </pre>
	 * @Method Name : getTeamResultYear
	 * @param paramMap
	 * @return
	 */		
	public List<EmpDashboardVO> getTeamResultYear(Map<String, String> paramMap);
	
}
