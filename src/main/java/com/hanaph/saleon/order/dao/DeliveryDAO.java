/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.order.vo.DeliveryVO;

/**
 * 
 * <pre>
 * Class Name : DeliveryDAO.java
 * 설명 : 원장조회  관련 DAO Interface.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015.08.07   김태안           최초작성                
 * </pre>
 * 
 * @version : 
 * @author  : 김태안
 * @since   : 2015. 08. 07.
 */
public interface DeliveryDAO {

	/**
	 * <pre>
	 * 1. 개요     : 원장조회 grid
	 * 2. 처리내용 : 원장조회 값을 select
	 * </pre>
	 * @Method Name : getDeliveryGridList
	 * @param paramMap    Map<String, String> 
	 * @return	List<DeliveryVO> 
	 */		
	public List<DeliveryVO> getDeliveryGridList(Map<String, String> paramMap);

	
	/**
	 * <pre>
	 * 1. 개요     : 원장조회 grid 하단 합계
	 * 2. 처리내용 : 원장조회 grid 합계를 select
	 * </pre>
	 * @Method Name : getSumDelivery
	 * @param paramMap    Map<String, String> 
	 * @return	DeliveryVO
	 */		
	public DeliveryVO getSumDelivery(Map<String, String> paramMap);

	
}
