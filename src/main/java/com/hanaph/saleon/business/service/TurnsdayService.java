/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.TurnsdayVO;

/**
 * <pre>
 * Class Name : TurnsdayService.java
 * 설명 : 회전일현황 Service
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 22.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 10. 22.
 */
public interface TurnsdayService {
	
	/**
	 * <pre>
	 * 1. 개요     : 회전일현황 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 회전일현황의 목록을 반환한다.
	 * </pre>
	 * @Method Name : getTurnsdayGridList
	 * @param paramMap
	 * @return
	 */		
	public List<TurnsdayVO> getTurnsdayGridList(Map<String, String> paramMap);
}
