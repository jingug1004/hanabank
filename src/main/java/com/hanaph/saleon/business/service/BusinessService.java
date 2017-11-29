/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.BusinessVO;

/**
 * <pre>
 * Class Name : BusinessService.java
 * 설명 : 영업관리 공통 Service
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 24.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 10. 24.
 */
public interface BusinessService {
	
	/**
	 * <pre>
	 * 1. 개요     : 거래처 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 거래처 목록을 반환한다.
	 * </pre>
	 * @Method Name : getCustomerGridList
	 * @param paramMap
	 * @return
	 */		
	public List<BusinessVO> getCustomerGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 명
	 * 2. 처리내용 : 거래처 코드에 따른 거래처 명을 반환한다.
	 * </pre>
	 * @Method Name : getCustomerName
	 * @param paramMap
	 * @return
	 */		
	public String getCustomerName(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 거래처 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getCustomerGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public int getCustomerGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 파트 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 파트 목록을 반환한다.
	 * </pre>
	 * @Method Name : getPartGridList
	 * @param paramMap
	 * @return
	 */		
	public List<BusinessVO> getPartGridList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 파트 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 파트 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getPartGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public int getPartGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 부서 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 부서 목록을 반환한다.
	 * </pre>
	 * @Method Name : getTeamGridList
	 * @param paramMap
	 * @return
	 */		
	public List<BusinessVO> getTeamGridList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 부서 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 부서 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getTeamGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public int getTeamGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 사원 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 사원 목록을 반환한다.
	 * </pre>
	 * @Method Name : getEmpGridList
	 * @param paramMap
	 * @return
	 */		
	public List<BusinessVO> getEmpGridList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 사원 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 사원 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getEmpGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public int getEmpGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 파트/부서/사원 명
	 * 2. 처리내용 : 거래처 코드에 따른 파트/부서/사원 명을 반환한다.
	 * </pre>
	 * @Method Name : getPerformanceName
	 * @param paramMap
	 * @return
	 */		
	public String getPerformanceName(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 사원정보
	 * 2. 처리내용 : 로그인한 사원의 부서코드, pda 권한을 반환한다.
	 * </pre>
	 * @Method Name : getCommonEmpInfo
	 * @param paramMap
	 * @return
	 */		
	public BusinessVO getCommonEmpInfo(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 상세 거래처 명 검색 ajax
	 * 2. 처리내용 : 거래처 코드에 따른 상세 거래처 명을 반환한다.
	 * </pre>
	 * @Method Name : getCustomerType
	 * @param paramMap
	 * @return
	 */		
	public BusinessVO getCustomerType(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 담보약속 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 담보약속 목록을 반환한다.
	 * </pre>
	 * @Method Name : getPromiseGridList
	 * @param paramMap
	 * @return
	 */		
	public List<BusinessVO> getPromiseGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 담보약속 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 담보약속 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getPromiseGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public int getPromiseGridTotalCount(Map<String, String> paramMap);

}
