/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.mgmt.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.mgmt.vo.MgmtRoleVO;

/**
 * <pre>
 * Class Name : MgmtRoleService.java
 * 설명 : MANAGER 권한 등록관리 Service class 
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
public interface MgmtRoleService {

	/**
	 * <pre>
	 * 1. 개요     : 권한 등록관리 화면
	 * 2. 처리내용 : 권한 등록관리 화면의 role list ajax 
	 * </pre>
	 * @Method Name : getRegAuthority
	 * @param paramMap
	 * @return list
	 */		
	public List<MgmtRoleVO> getRegAuthority(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 등록관리 화면
	 * 2. 처리내용 : 권한 등록관리 화면의 role 사용자 등록 list ajax 
	 * </pre>
	 * @Method Name : getRoleUserList
	 * @param paramMap
	 * @return list
	 */		
	public List<MgmtRoleVO> getRoleUserList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 등록관리 화면
	 * 2. 처리내용 : 권한 등록관리 화면의 role 프로그램 등록 list ajax  
	 * </pre>
	 * @Method Name : getRoleProgramList
	 * @param request
	 * @param response
	 * @return list
	 */		
	public List<MgmtRoleVO> getRoleProgramList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 등록관리 화면 role 처리
	 * 2. 처리내용 : 권한 등록관리 화면 추가 처리
	 * </pre>
	 * @Method Name : insertRole
	 * @param paramMgmtRoleVO
	 * @return int
	 */		
	public int insertRole(MgmtRoleVO paramMgmtRoleVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 등록관리 화면 role 처리
	 * 2. 처리내용 : 권한 등록관리 화면 수정 처리
	 * </pre>
	 * @Method Name : updateRole
	 * @param paramMgmtRoleVO
	 * @return int
	 */	
	public int updateRole(MgmtRoleVO paramMgmtRoleVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 등록관리 화면 role 처리
	 * 2. 처리내용 : 권한 등록관리 role 복사
	 * </pre>
	 * @Method Name : insertRoleCopy
	 * @param paramMgmtRoleVO
	 * @return int
	 */		
	public int insertRoleCopy(MgmtRoleVO paramMgmtRoleVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 등록관리 화면 role 처리
	 * 2. 처리내용 : 권한 등록관리 role 삭제
	 * </pre>
	 * @Method Name : deleteRole
	 * @param request
	 * @return int
	 */			
	public int deleteRole(MgmtRoleVO paramMgmtRoleVO);
	
	/**
	 * <pre>
	 * 1. 개요     : role등록 사원 리스트 팝업 ajax
	 * 2. 처리내용 : role등록 사원 리스트 팝업 사원 list ajax
	 * </pre>
	 * @Method Name : getUserList
	 * @param paramMap
	 * @return list
	 */		
	public List<MgmtRoleVO> getUserList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : role등록 부서 리스트 팝업
	 * 2. 처리내용 : role등록 부서 리스트 팝업 사원 list ajax
	 * </pre>
	 * @Method Name : getDeptList
	 * @param paramMap
	 * @return list
	 */		
	public List<MgmtRoleVO> getDeptList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : role 사용자 추가
	 * 2. 처리내용 : role등록 사원 리스트 팝업에서 사용자 선택 후 추가
	 * </pre>
	 * @Method Name : insertUserRole
	 * @param paramMgmtRoleVO
	 * @return int
	 */		
	public int insertUserRole(MgmtRoleVO paramMgmtRoleVO);
	
	/**
	 * <pre>
	 * 1. 개요     : role 사용자 삭제
	 * 2. 처리내용 : role 사용자 리스트에서 사용자 선택 후 삭제
	 * </pre>
	 * @Method Name : deleteUserRole
	 * @param paramMgmtRoleVO
	 * @return int
	 */		
	public int deleteUserRole(MgmtRoleVO paramMgmtRoleVO);
	
	/**
	 * <pre>
	 * 1. 개요     : role 프로그램 등록 팝업
	 * 2. 처리내용 : role 프로그램 리스트에서 프로그램 선택 시 보여지는 팝업 
	 * </pre>
	 * @Method Name : getRoleDetail
	 * @param paramMap
	 * @return MgmtRoleVO
	 */		
	public MgmtRoleVO getRoleDetail(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : role 프로그램 등록 팝업
	 * 2. 처리내용 : role 프로그램 리스트에서 프로그램 선택 시 보여지는 팝업 버튼 수정
	 * </pre>
	 * @Method Name : paramMgmtRoleVO
	 * @param request
	 * @return int
	 */		
	public int updateRoleDetail(MgmtRoleVO paramMgmtRoleVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 등록관리 role 등록관리 팝업
	 * 2. 처리내용 : role추가
	 * </pre>
	 * @Method Name : insertRoleProgram
	 * @param paramMgmtRoleVO
	 * @return int
	 */		
	public int insertRoleProgram(MgmtRoleVO paramMgmtRoleVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 등록관리 role 등록관리 팝업
	 * 2. 처리내용 : role삭제
	 * </pre>
	 * @Method Name : deleteRoleProgram
	 * @param paramMgmtRoleVO
	 * @return int
	 */		
	public int deleteRoleProgram(MgmtRoleVO paramMgmtRoleVO);
	
}
