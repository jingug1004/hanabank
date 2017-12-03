/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.ReturnVO;

/**
 * <pre>
 * Class Name : ReturnDAO.java
 * 설명 : 반품현황 DAO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 30.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 10. 30.
 */
public interface ReturnDAO {
	
	/**
	 * <pre>
	 * 1. 개요     : 반품현황 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 반품현황의 목록을 반환한다.
	 * </pre>
	 * @Method Name : getReturnGridList
	 * @param paramMap
	 * @return
	 */		
	public List<ReturnVO> getReturnGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 반품현황 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 반품현황의 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getReturnGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public ReturnVO getReturnGridTotalCount(Map<String, String> paramMap);
}
