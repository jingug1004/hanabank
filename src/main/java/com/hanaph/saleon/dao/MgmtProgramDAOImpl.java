package com.hanaph.saleon.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.hanaph.saleon.mgmt.vo.MgmtButtonVO;
import com.hanaph.saleon.mgmt.vo.MgmtProgramVO;

/**
 * <pre>
 * Class Name : MgmtDaoImpl.java
 * 설명 : MANAGER 프로그램 등록 관리 DaoImpl class
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
@Repository("mgmtProgramDao")
public class MgmtProgramDAOImpl extends SqlSessionDaoSupport implements MgmtProgramDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#getMenuList(java.util.Map)
	 */
	@Override
    @SuppressWarnings("unchecked")
	public List<MgmtProgramVO> getMenuList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return (List<MgmtProgramVO>)getSqlSession().selectList("mgmt_program.getMenuList", paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#getRoleMenuList(java.util.Map)
	 */
	@Override
    @SuppressWarnings("unchecked")
	public List<MgmtProgramVO> getRoleMenuList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return (List<MgmtProgramVO>)getSqlSession().selectList("mgmt_program.getRoleMenuList", paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#detailProgramInfo(java.util.Map)
	 */
	@Override
	public MgmtProgramVO detailProgramInfo(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		MgmtProgramVO MgmtProgramVO = (MgmtProgramVO) getSqlSession().selectOne("mgmt_program.detailProgramInfo", paramMap);
		return MgmtProgramVO;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#getProgramSortNum(java.util.Map)
	 */
	@Override
	public MgmtProgramVO getProgramSortNum(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		MgmtProgramVO MgmtProgramVO = (MgmtProgramVO) getSqlSession().selectOne("mgmt_program.getProgramSortNum", paramMap);
		return MgmtProgramVO;
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#insertProgram(com.hanaph.saleon.mgmt.vo.MgmtProgramVO)
	 */
	@Override
	public int insertProgram(MgmtProgramVO paramMgmtProgramVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().insert("mgmt_program.insertProgram", paramMgmtProgramVO);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#updateProgram(com.hanaph.saleon.mgmt.vo.MgmtProgramVO)
	 */
	@Override
	public int updateProgram(MgmtProgramVO paramMgmtProgramVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().update("mgmt_program.updateProgram", paramMgmtProgramVO);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#deleteProgram(com.hanaph.saleon.mgmt.vo.MgmtProgramVO)
	 */
	@Override
	public int deleteProgram(MgmtProgramVO paramMgmtProgramVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().delete("mgmt_program.deleteProgram", paramMgmtProgramVO);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#getButtonList(java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<MgmtButtonVO> getButtonList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return (List<MgmtButtonVO>)getSqlSession().selectList("mgmt_program.getButtonList", paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#getButton(java.util.Map)
	 */
	@Override
	public int getButton(MgmtButtonVO paramMgmtButtonVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().selectOne("mgmt_program.getButton", paramMgmtButtonVO);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#insertButton(com.hanaph.saleon.mgmt.vo.MgmtButtonVO)
	 */
	@Override
	public int insertButton(MgmtButtonVO paramMgmtButtonVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().insert("mgmt_program.insertButton", paramMgmtButtonVO);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#updateButton(com.hanaph.saleon.mgmt.vo.MgmtButtonVO)
	 */
	@Override
	public int updateButton(MgmtButtonVO paramMgmtButtonVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().update("mgmt_program.updateButton", paramMgmtButtonVO);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#deleteButton(com.hanaph.saleon.mgmt.vo.MgmtButtonVO)
	 */
	@Override
	public int deleteButton(MgmtButtonVO paramMgmtButtonVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().delete("mgmt_program.deleteButton", paramMgmtButtonVO);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#updateRoleButton(com.hanaph.saleon.mgmt.vo.MgmtButtonVO)
	 */
	@Override
	public int updateRoleButton(MgmtButtonVO paramMgmtButtonVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().update("mgmt_program.updateRoleButton", paramMgmtButtonVO);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtDao#deleteRoleButton(com.hanaph.saleon.mgmt.vo.MgmtButtonVO)
	 */
	@Override
	public int deleteRoleButton(MgmtButtonVO paramMgmtButtonVO) {
		// TODO Auto-generated method stub
		return (Integer)getSqlSession().update("mgmt_program.deleteRoleButton", paramMgmtButtonVO);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtProgramDAO#getUserPgmList(java.util.Map)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MgmtProgramVO> getUserPgmList(Map<String, String> paramMap) {
		return (List<MgmtProgramVO>)getSqlSession().selectList("mgmt_program.getUserPgmList", paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtProgramDAO#getMenuAuthByUser(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MgmtProgramVO> getMenuAuthByUser(String empCode) {
		return (List<MgmtProgramVO>)getSqlSession().selectList("mgmt_program.getMenuAuthByUser", empCode);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.dao.MgmtProgramDAO#getBtnAuthInPgmByUser(com.hanaph.saleon.mgmt.vo.MgmtProgramVO)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<MgmtButtonVO> getBtnAuthInPgmByUser(MgmtProgramVO paramVO) {
		return (List<MgmtButtonVO>)getSqlSession().selectList("mgmt_program.getBtnAuthInPgmByUser", paramVO);
	}
	
	
}
