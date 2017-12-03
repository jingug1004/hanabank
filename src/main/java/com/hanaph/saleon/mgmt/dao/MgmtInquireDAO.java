/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.mgmt.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.mgmt.vo.MgmtInquireVO;

/**
 * <pre>
 * Class Name : MgmtInquireDAO.java
 * 설명 : MANAGER 권한조회 DAO class
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
public interface MgmtInquireDAO {
	
	/**
	 * <pre>
	 * 1. 개요     : 권한조회 사용 프로그램
	 * 2. 처리내용 : 사용자별 사용 프로그램 조회
	 * </pre>
	 * @Method Name : getUserPgmList
	 * @param response
	 * @return list
	 */	
	public List<MgmtInquireVO> getUserPgmList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한조회 사용권한
	 * 2. 처리내용 : 사용자별 사용 권한 list
	 * </pre>
	 * @Method Name : getUserRoleList
	 * @param paramMap
	 * @return list
	 */		
	public List<MgmtInquireVO> getUserRoleList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 조회 사용자 list
	 * 2. 처리내용 : 사용자 list 
	 * </pre>
	 * @Method Name : getEmpListByPgmnoAjax
	 * @param paramMap
	 * @return list
	 */			
	public List<MgmtInquireVO> getEmpListByPgmno(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 조회 프로그램 tab
	 * 2. 처리내용 : 권한 조회 프로그램 tab 사용권한 list ajax
	 * </pre>
	 * @Method Name : getRoleListByPgmno
	 * @param paramMap
	 * @return list
	 */		
	public List<MgmtInquireVO> getRoleListByPgmno(Map<String, String> paramMap);
	
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 조회 사용자 role 복사
	 * 2. 처리내용 : 권한 조회 사용자 role 등록 전 해당 role이 존재하는 지 체크 한다.
	 * </pre>
	 * @Method Name : getRoleUserCount
	 * @param paramMap
	 * @return int
	 */		
	public int getRoleUserCount(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 권한 조회 사용자 role 복사
	 * 2. 처리내용 : 권한 조회 사용자 list 더블 클릭 시 팝업에서 사용자 조회 후 role 복사
	 * </pre>
	 * @Method Name : insertUserRoleCopy
	 * @param mgmtInquireVO
	 * @return int
	 */			
	public int insertUserRoleCopy(MgmtInquireVO mgmtInquireVO);
	
}
