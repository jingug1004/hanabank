/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanaph.saleon.order.dao.CompanyDAO;
import com.hanaph.saleon.order.vo.CompanyVO;

/**
 * <pre>
 * Class Name : CompanyServiceImpl.java
 * 설명 : 회사정보 ServiceImpl
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
@Service(value="companyService")
public class CompanyServiceImpl implements CompanyService {

	/**
	 * CompanyDAO
	 */
	@Autowired
	private CompanyDAO companyDAO;
		
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.service.CompanyService#getCompanyInfo(java.util.Map)
	 */
	@Override
	public CompanyVO getCompanyInfo(Map<String, String> paramMap) {
		
		return companyDAO.getCompanyInfo(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.service.CompanyService#updateCompany(com.hanaph.saleon.order.vo.CompanyVO)
	 */
	@Override
	public int updateCompany(CompanyVO paramVO) {
		
		return companyDAO.updateCompany(paramVO);
	}

}
