/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.dao;

import java.util.Map;

import com.hanaph.saleon.order.vo.CompanyVO;

/**
 * <pre>
 * Class Name : CompanyDAO.java
 * 설명 : 회사정보 관련 DAO Interface.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 29.      jung a Woo          
 * </pre>
 * 
 * @version : 
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 10. 29.
 */
public interface CompanyDAO {

	
	/**
	 * <pre>
	 * 1. 개요     : 회사 정보  
	 * 2. 처리내용 : 회사 정보를 select
	 * </pre>
	 * @Method Name : getCompanyInfo
	 * @param paramMap    Map<String, String> 
	 * @return	CompanyVO
	 */		
	public CompanyVO getCompanyInfo(Map<String, String> paramMap);

	
	/**
	 * <pre>
	 * 1. 개요     : 회사 정보 수정 
	 * 2. 처리내용 : 회사 정보를 db에 update
	 * </pre>
	 * @Method Name : updateCompany
	 * @param paramVO
	 * @return	int
	 */		
	public int updateCompany(CompanyVO paramVO);
	
}
