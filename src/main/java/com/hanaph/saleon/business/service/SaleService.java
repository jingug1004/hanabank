/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.SaleVO;

/**
 * <pre>
 * Class Name : SaleService.java
 * 설명 : 판매현황 Service
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
public interface SaleService {
	
	/**
	 * <pre>
	 * 1. 개요     : 판매현황 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 판매현황의 목록을 반환한다.
	 * </pre>
	 * @Method Name : getSaleGridList
	 * @param paramMap
	 * @return
	 */		
	public List<SaleVO> getSaleGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 판매현황 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 판매현황의 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getSaleGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public SaleVO getSaleGridTotalCount(Map<String, String> paramMap);
}
