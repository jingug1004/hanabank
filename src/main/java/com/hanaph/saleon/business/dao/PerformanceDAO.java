/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.PerformanceVO;

/**
 * <pre>
 * Class Name : PerformanceDAO.java
 * 설명 : 실적현황 Batch DAO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 31.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 10. 31.
 */
public interface PerformanceDAO {

	/**
	 * <pre>
	 * 1. 개요     : 파트별 실적현황 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 실적현황 목록을 반환한다.
	 * </pre>
	 * @Method Name : getPerformanceForPartGridList
	 * @param paramMap
	 * @return
	 */		
	public List<PerformanceVO> getPerformanceForPartGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 부서별 실적현황 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 실적현황 목록을 반환한다.
	 * </pre>
	 * @Method Name : getPerformanceForTeamGridList
	 * @param paramMap
	 * @return
	 */		
	public List<PerformanceVO> getPerformanceForTeamGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 사원별 실적현황 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 실적현황 목록을 반환한다.
	 * </pre>
	 * @Method Name : getPerformanceForEmpGridList
	 * @param paramMap
	 * @return
	 */		
	public List<PerformanceVO> getPerformanceForEmpGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 파트별 실적현황 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 실적현황 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getPerformanceForPartGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public PerformanceVO getPerformanceForPartGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 부서별 실적현황 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 실적현황 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getPerformanceForTeamGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public PerformanceVO getPerformanceForTeamGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 사원별 실적현황 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 실적현황 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getPerformanceForEmpGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public PerformanceVO getPerformanceForEmpGridTotalCount(Map<String, String> paramMap);

}
