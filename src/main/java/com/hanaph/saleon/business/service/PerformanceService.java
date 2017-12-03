/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.PerformanceVO;

/**
 * <pre>
 * Class Name : PerformanceService.java
 * 설명 : 실적현황_Batch Service
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
public interface PerformanceService {

	/**
	 * <pre>
	 * 1. 개요     : 실적현황 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 실적현황 목록을 반환한다.
	 * </pre>
	 * @Method Name : getPerformanceGridList
	 * @param paramMap
	 * @return
	 */		
	public List<PerformanceVO> getPerformanceGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 실적현황 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 실적현황 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getPerformanceGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public PerformanceVO getPerformanceGridTotalCount(Map<String, String> paramMap);

}
