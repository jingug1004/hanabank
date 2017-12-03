/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.dao;

import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.hanaph.saleon.order.vo.CompanyVO;

/**
 * <pre>
 * Class Name : CompanyDAOImpl.java
 * 설명 : 회사정보 DAOImpl
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
@Repository("companyDAO")
public class CompanyDAOImpl extends SqlSessionDaoSupport implements CompanyDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.dao.CompanyDAO#getCompanyInfo(java.util.Map)
	 */
	@Override
	public CompanyVO getCompanyInfo(Map<String, String> paramMap) {
		
		return (CompanyVO)getSqlSession().selectOne("company.getCompanyInfo", paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.dao.CompanyDAO#updateCompany(com.hanaph.saleon.order.vo.CompanyVO)
	 */
	@Override
	public int updateCompany(CompanyVO paramVO) {
		return (Integer)getSqlSession().update("company.updateCompany", paramVO);
	}

}
