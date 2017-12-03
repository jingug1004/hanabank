/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.mgmt.dao;

import com.hanaph.saleon.mgmt.vo.MgmtInquireVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : MgmtInquireDAOImpl.java
 * 설명 : MANAGER 권한조회 DAOImpl class
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 4.      slamwin          
 * </pre>
 *
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2014. 12. 4.
 */
@Repository("mgmtInquireDao")
public class MgmtInquireDAOImpl extends SqlSessionDaoSupport implements MgmtInquireDAO {

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtInquireDAO#getUserPgmList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MgmtInquireVO> getUserPgmList(Map<String, String> paramMap) {
        return getSqlSession().selectList("mgmt_inquire.getUserPgmList", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtInquireDAO#getUserRoleList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MgmtInquireVO> getUserRoleList(Map<String, String> paramMap) {
        return getSqlSession().selectList("mgmt_inquire.getUserRoleList", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtInquireDAO#getEmpListByPgmno(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MgmtInquireVO> getEmpListByPgmno(Map<String, String> paramMap) {
        return getSqlSession().selectList("mgmt_inquire.getEmpListByPgmno", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtInquireDAO#getRoleListByPgmno(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MgmtInquireVO> getRoleListByPgmno(Map<String, String> paramMap) {
        return getSqlSession().selectList("mgmt_inquire.getRoleListByPgmno", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtInquireDAO#getRoleUserCount(java.util.Map)
     */
    @Override
    public int getRoleUserCount(Map<String, String> paramMap) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().selectOne("mgmt_inquire.getRoleUserCount", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtInquireDAO#insertUserRoleCopy(java.util.Map)
     */
    @Override
    public int insertUserRoleCopy(MgmtInquireVO mgmtInquireVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().insert("mgmt_inquire.insertUserRoleCopy", mgmtInquireVO);
    }

}
