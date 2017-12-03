/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.mgmt.dao;

import com.hanaph.saleon.mgmt.vo.MgmtUserVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : MgmtUserDAOImpl.java
 * 설명 : MANAGER 사용자 관리 DAOImpl class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015. 1. 7.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2015. 1. 7.
 */
@Repository("mgmtUserDao")
public class MgmtUserDAOImpl extends SqlSessionDaoSupport implements MgmtUserDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtUserDAO#getUserMgmtMain(java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<MgmtUserVO> getUserMgmtMain(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("mgmt_user.getUserMgmtMain", paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtUserDAO#insertMember(com.hanaph.saleon.mgmt.vo.MgmtUserVO)
	 */
	@Override
	public int insertMember(MgmtUserVO paramMgmtUserVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().insert("mgmt_user.insertMember", paramMgmtUserVO);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtUserDAO#updateMember(com.hanaph.saleon.mgmt.vo.MgmtUserVO)
	 */
	@Override
	public int updateMember(MgmtUserVO paramMgmtUserVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().update("mgmt_user.updateMember", paramMgmtUserVO);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtUserDAO#deleteMember(com.hanaph.saleon.mgmt.vo.MgmtUserVO)
	 */
	@Override
	public int deleteMember(MgmtUserVO paramMgmtUserVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().update("mgmt_user.deleteMember", paramMgmtUserVO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MgmtUserVO> getRoleList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return getSqlSession().selectList("mgmt_user.getRoleList", paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtUserDAO#getRoleUserCount(java.util.Map)
	 */
	@Override
	public int getRoleUserCount(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("mgmt_user.getRoleUserCount", paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtUserDAO#deleteUserRole(com.hanaph.saleon.mgmt.vo.MgmtUserVO)
	 */
	@Override
	public int deleteUserRole(MgmtUserVO paramMgmtUserVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().update("mgmt_user.deleteUserRole", paramMgmtUserVO);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtUserDAO#insertUserRole(com.hanaph.saleon.mgmt.vo.MgmtUserVO)
	 */
	@Override
	public int insertUserRole(MgmtUserVO paramMgmtUserVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().update("mgmt_user.insertUserRole", paramMgmtUserVO);
	}
	
	
}
