/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.CollectionVO;

/**
 * <pre>
 * Class Name : CollectionDAO.java
 * 설명 : 수금현황 DAO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 28.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 10. 28.
 */
public interface CollectionDAO {
	
	/**
	 * <pre>
	 * 1. 개요     : 수금현황 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 수금현황 목록을 반환한다.
	 * </pre>
	 * @Method Name : getCollectionGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CollectionVO> getCollectionGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 수금현황 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 수금현황 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getCollectionGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CollectionVO getCollectionGridTotalCount(Map<String, String> paramMap);
}
