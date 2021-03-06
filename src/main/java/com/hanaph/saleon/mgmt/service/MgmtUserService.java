/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.mgmt.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.mgmt.vo.MgmtUserVO;

/**
 * <pre>
 * Class Name : MgmtUserService.java
 * 설명 : MANAGER 사용자 관리 Service class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 28.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2014. 11. 28.
 */
public interface MgmtUserService {
	
	/**
	 * <pre>
	 * 1. 개요     : MANAGER > 사용자관리 > 사용자 리스트
	 * 2. 처리내용 : 사용자 리스트를 조회하는 ajax
	 * </pre>
	 * @Method Name : getUserMgmtMain
	 * @param Map<String, String> paramMap
	 * @return List<MgmtUserVO>
	 */		
	public List<MgmtUserVO> getUserMgmtMain(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : MANAGER > 사용자관리 > 사용자 리스트
	 * 2. 처리내용 : 사용자 정보를 수정하는 메소드
	 * </pre>
	 * @Method Name : updateMember
	 * @param MgmtUserVO paramMgmtUserVO
	 * @return int 
	 */		
	public int updateMember(MgmtUserVO paramMgmtUserVO);
	
	/**
	 * <pre>
	 * 1. 개요     : MANAGER > 사용자관리 > 사용자 리스트
	 * 2. 처리내용 : 사용자 정보를 삭제하는 메소드
	 * </pre>
	 * @Method Name : deleteMember
	 * @param MgmtUserVO paramMgmtUserVO
	 * @return int 
	 */		
	public int deleteMember(MgmtUserVO paramMgmtUserVO);
	
	/**
	 * <pre>
	 * 1. 개요     : MANAGER > 사용자관리 > Role선택 팝업 page
	 * 2. 처리내용 : 사용자 현재 적용된 role list 
	 * </pre>
	 * @Method Name : getRoleList
	 * @param Map<String, String> paramMap
	 * @return List<MgmtUserVO>
	 */		
	public List<MgmtUserVO> getRoleList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : MANAGER > 사용자관리 > Role insert ajax
	 * 2. 처리내용 : 팝업에서 선택 된 role 추가
	 * </pre>
	 * @Method Name : insertUserRole
	 * @param MgmtUserVO paramMgmtUserVO
	 * @return int
	 */			
	public int insertUserRole(MgmtUserVO paramMgmtUserVO);
}
