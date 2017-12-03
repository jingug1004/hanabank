/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.mgmt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanaph.saleon.mgmt.dao.MgmtRoleDAO;
import com.hanaph.saleon.mgmt.vo.MgmtRoleVO;

/**
 * <pre>
 * Class Name : MgmtRoleServiceImpl.java
 * 설명 : MANAGER 권한 등록관리 ServiceImpl class 
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
@Service(value="mgmtRoleService")
public class MgmtRoleServiceImpl implements MgmtRoleService{
	
	@Autowired
	private MgmtRoleDAO mgmtRoleDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#getRegAuthority(java.util.Map)
	 */
	@Override
	public List<MgmtRoleVO> getRegAuthority(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return mgmtRoleDAO.getRegAuthority(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#getRoleUserList(java.util.Map)
	 */
	@Override
	public List<MgmtRoleVO> getRoleUserList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return mgmtRoleDAO.getRoleUserList(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#getRoleProgramList(java.util.Map)
	 */
	@Override
	public List<MgmtRoleVO> getRoleProgramList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return mgmtRoleDAO.getRoleProgramList(paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#insertRole(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
	 */
	@Override
	public int insertRole(MgmtRoleVO paramMgmtRoleVO) {
		// TODO Auto-generated method stub
		
		String roleNo = "";		// 기본 변수 셋팅
		
		/*
		 * role 등록을 위한 role no 생성
		 */
		roleNo = mgmtRoleDAO.getRoleNum();
		paramMgmtRoleVO.setNewRoleNum(roleNo);
		
		return mgmtRoleDAO.insertRole(paramMgmtRoleVO);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#updateRole(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
	 */
	@Override
	public int updateRole(MgmtRoleVO paramMgmtRoleVO) {
		// TODO Auto-generated method stub
		return mgmtRoleDAO.updateRole(paramMgmtRoleVO);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#insertRoleCopy(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
	 */
	@Transactional
	@Override
	public int insertRoleCopy(MgmtRoleVO paramMgmtRoleVO) {
		// TODO Auto-generated method stub
		
		String roleNo = "";		// 기본 변수 셋팅
		int result = 0;			// 기본 결과 값 셋팅
		
		/*
		 * role 복사 등록을 위한 role no 생성
		 */
		roleNo = mgmtRoleDAO.getRoleNum();
		paramMgmtRoleVO.setNewRoleNum(roleNo);
		
		/*
		 * 선택 된 role의 사용자 카피
		 */
		result+= mgmtRoleDAO.insertRoleUserCopy(paramMgmtRoleVO);
		
		/*
		 * 선택 된 role의 프로그램 카피
		 */
		result+= mgmtRoleDAO.insertRoleProgramCopy(paramMgmtRoleVO);
		
		/*
		 * role no
		 */
		paramMgmtRoleVO.setRole_no(roleNo);
		
		/*
		 * 신규 role 등록
		 */
		result+= mgmtRoleDAO.insertRole(paramMgmtRoleVO);
		
		return result;		// 결과 값 반환
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#deleteRole(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
	 */
	@Transactional
	@Override
	public int deleteRole(MgmtRoleVO paramMgmtRoleVO) {
		// TODO Auto-generated method stub
		int result = 0;		// 기본 결과 값 셋팅
		
		/*
		 * role 삭제 
		 */
		result = mgmtRoleDAO.deleteRole(paramMgmtRoleVO);
		
		/*
		 * role 사용자 삭제
		 */
		result += mgmtRoleDAO.deleteRoleUserAll(paramMgmtRoleVO);
		
		/*
		 * role 프로그램 삭제
		 */
		result += mgmtRoleDAO.deleteRoleProgramAll(paramMgmtRoleVO);
		
		return result;		// 결과 값 반환	
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#getUserList(java.util.Map)
	 */
	@Override
	public List<MgmtRoleVO> getUserList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return mgmtRoleDAO.getUserList(paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#getDeptList(java.util.Map)
	 */
	@Override
	public List<MgmtRoleVO> getDeptList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return mgmtRoleDAO.getDeptList(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#insertUserRole(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
	 */
	public int insertUserRole(MgmtRoleVO paramMgmtRoleVO) {
		// TODO Auto-generated method stub
		
		int idCount = 0;		// 기본 id 카운트 셋팅
		int result = 0;			// 기본 결과 값 셋팅
		String empCode = paramMgmtRoleVO.getEmp_code();		// 사원 번호
		
		/*
		 *  parameter를 map에 setting
		 */
		Map<String, String> initparamMap = new HashMap<String, String>();
		
		initparamMap.put("roleNo",paramMgmtRoleVO.getRole_no());
		initparamMap.put("empCode",paramMgmtRoleVO.getEmp_code());
		
		/*
		 * 사용자 코드가 다수로 넘어 온 경우
		 */
		if(!"".equals(empCode) && empCode.indexOf(",") > -1){
			String empCodes[] = empCode.split(",");
			for(int i= 0; i<empCodes.length; i++){
				paramMgmtRoleVO.setEmp_code(empCodes[i]);
				
				/*
				 * 사용자 중복체크
				 */
				idCount = mgmtRoleDAO.getRoleUserCount(initparamMap);
				
				/*
				 * 중복이 아닐때만 등록
				 */
				if(idCount == 0){
					result = mgmtRoleDAO.insertUserRole(paramMgmtRoleVO);
					result++;
				}
			}
		/*
		 * 사용자가 단일 인 경우
		 */
		}else{
			
			/*
			 * 사용자 중복체크
			 */
			idCount = mgmtRoleDAO.getRoleUserCount(initparamMap);
			
			/*
			 * 중복이 아닐때만 등록
			 */
			if(idCount == 0){
				result = mgmtRoleDAO.insertUserRole(paramMgmtRoleVO);
			}
		}
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#deleteUserRole(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
	 */
	@Override
	public int deleteUserRole(MgmtRoleVO paramMgmtRoleVO) {
		// TODO Auto-generated method stub
		return mgmtRoleDAO.deleteUserRole(paramMgmtRoleVO);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#getRoleDetail(java.util.Map)
	 */
	@Override
	public MgmtRoleVO getRoleDetail(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return mgmtRoleDAO.getRoleDetail(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#updateRoleDetail(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
	 */
	@Override
	public int updateRoleDetail(MgmtRoleVO paramMgmtRoleVO) {
		// TODO Auto-generated method stub
		return mgmtRoleDAO.updateRoleDetail(paramMgmtRoleVO);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#insertRoleProgram(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
	 */
	@Override
	@Transactional
	public int insertRoleProgram(MgmtRoleVO paramMgmtRoleVO) {
		// TODO Auto-generated method stub
		
		int result = 0;			//	기본 결과 값 셋팅
		int roleCount = -1;		// role 카운트 셋팅
		
		/*
		 * 상위 메뉴 리스트 select
		 */
		List<MgmtRoleVO> roleParentProgramList = mgmtRoleDAO.getRoleParentProgramList(paramMgmtRoleVO);
		
		/*
		 * 상위 코드가 존재 하는 경우
		 */
		if(roleParentProgramList != null && roleParentProgramList.size() > 0){
			for(int i=0; i<roleParentProgramList.size(); i++){
				/*
				 * 셋팅 될 객체 생성
				 */
				MgmtRoleVO mgmtRoleVO = new MgmtRoleVO();
				
				/*
				 * list수 만큼 셋팅
				 */
				mgmtRoleVO = roleParentProgramList.get(i);
				mgmtRoleVO.setRole_no(paramMgmtRoleVO.getRole_no());
				mgmtRoleVO.setUse_btn(""); // 초기 빈값 설정
				
				/*
				 * role 카운트 체크
				 */
				roleCount = mgmtRoleDAO.getRoleProgramCount(mgmtRoleVO);
				
				/*
				 * role 미존재시 role 프로그램 등록
				 */
				if(roleCount == 0){
					result += mgmtRoleDAO.insertRoleProgram(mgmtRoleVO);
				}
				
			}
		}
		
		return result;		// 결과 값 반환
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtRoleService#deleteRoleProgram(com.hanaph.saleon.mgmt.vo.MgmtRoleVO)
	 */
	@Override
	public int deleteRoleProgram(MgmtRoleVO paramMgmtRoleVO) {
		// TODO Auto-generated method stub
		return mgmtRoleDAO.deleteRoleProgram(paramMgmtRoleVO);
	}
	
}
