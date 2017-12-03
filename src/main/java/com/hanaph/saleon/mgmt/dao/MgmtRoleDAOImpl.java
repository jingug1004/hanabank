/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.mgmt.dao;

import com.hanaph.saleon.mgmt.vo.MgmtRoleVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : MgmtRoleDAOImpl.java
 * 설명 : MANAGER 권한 등록관리 DAOImpl class 
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 4.      slamwin          
 * </pre>
 *
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2014. 11. 4.
 */
@Repository("mgmtRoleDao")
public class MgmtRoleDAOImpl extends SqlSessionDaoSupport implements MgmtRoleDAO {

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#getRegAuthority(java.util.Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<MgmtRoleVO> getRegAuthority(Map<String, String> paramMap) {
        // TODO Auto-generated method stub
        return getSqlSession().selectList("mgmt_role.getRoleList", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#getRoleUserList(java.util.Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<MgmtRoleVO> getRoleUserList(Map<String, String> paramMap) {
        // TODO Auto-generated method stub
        return getSqlSession().selectList("mgmt_role.getRoleUserList", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#getRoleProgramList(java.util.Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<MgmtRoleVO> getRoleProgramList(Map<String, String> paramMap) {
        // TODO Auto-generated method stub
        return getSqlSession().selectList("mgmt_role.getRoleProgramList", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#getRoleNum()
     */
    @Override
    public String getRoleNum() {
        // TODO Auto-generated method stub
        return (String) getSqlSession().selectOne("mgmt_role.getRoleNum");
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#insertRole(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int insertRole(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().insert("mgmt_role.insertRole", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#updateRole(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int updateRole(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().update("mgmt_role.updateRole", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#insertRoleUserCopy(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int insertRoleUserCopy(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().update("mgmt_role.insertRoleUserCopy", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#insertRoleProgramCopy(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int insertRoleProgramCopy(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().update("mgmt_role.insertRoleProgramCopy", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#deleteRole(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int deleteRole(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().update("mgmt_role.deleteRole", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#getUserList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MgmtRoleVO> getUserList(Map<String, String> paramMap) {
        // TODO Auto-generated method stub
        return getSqlSession().selectList("mgmt_role.getUserList", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#getDeptList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MgmtRoleVO> getDeptList(Map<String, String> paramMap) {
        // TODO Auto-generated method stub
        return getSqlSession().selectList("mgmt_role.getDeptList", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#getRoleUserCount(java.util.Map)
     */
    @Override
    public int getRoleUserCount(Map<String, String> paramMap) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().selectOne("mgmt_role.getRoleUserCount", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#insertUserRole(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int insertUserRole(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().insert("mgmt_role.insertUserRole", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#deleteUserRole(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int deleteUserRole(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().delete("mgmt_role.deleteUserRole", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#deleteRoleUserAll(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int deleteRoleUserAll(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().update("mgmt_role.deleteRoleUserAll", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#deleteRoleProgramAll(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int deleteRoleProgramAll(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().update("mgmt_role.deleteRoleProgramAll", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#getRoleDetail(java.util.Map)
     */
    @Override
    public MgmtRoleVO getRoleDetail(Map<String, String> paramMap) {
        // TODO Auto-generated method stub
        return (MgmtRoleVO) getSqlSession().selectOne("mgmt_role.getRoleDetail", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#updateRoleDetail(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int updateRoleDetail(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().update("mgmt_role.updateRoleDetail", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#insertRoleProgram(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int insertRoleProgram(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().update("mgmt_role.insertRoleProgram", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#deleteRoleProgram(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int deleteRoleProgram(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().update("mgmt_role.deleteRoleProgram", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#getRoleParentProgramList(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MgmtRoleVO> getRoleParentProgramList(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return getSqlSession().selectList("mgmt_role.getRoleParentProgramList", paramMgmtRoleVO);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.mgmt.dao.MgmtRoleDAO#getRoleProgramCount(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
     */
    @Override
    public int getRoleProgramCount(MgmtRoleVO paramMgmtRoleVO) {
        // TODO Auto-generated method stub
        return (Integer) getSqlSession().selectOne("mgmt_role.getRoleProgramCount", paramMgmtRoleVO);
    }
}
