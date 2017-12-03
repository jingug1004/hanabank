/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.member.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.member.vo.LoginUserVO;

/**
 * <pre>
 * Class Name : LoginUserDAO.java
 * 설명 : 로그인/로그아웃/비밀번호변경 관련 DAO interface.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 16.      KIMJAEKAP          
 * </pre>
 * 
 * @version : 
 * @author  : KIMJAEKAP(slamwin@irush.co.kr)
 * @since   : 2014. 10. 16.
 */
public interface LoginUserDAO {
	
	/**
	 * <pre>
	 * 1. 개요     : SALE_ON 유저의 회원 정보 조회
	 * 2. 처리내용 : SALE_ON 유저의 회원 정보 조회
	 * </pre>
	 * @Method Name : getLogin
	 * @param paramMap	empCode(사원코드)
	 * @return	LoginUserVO(회원정보)
	 */		
	public LoginUserVO getLogin(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : SALE_ON 유저의 회원 비밀번호 업데이트
	 * 2. 처리내용 : SALE_ON 유저의 회원 비밀번호 업데이트
	 * </pre>
	 * @Method Name : updatePassword
	 * @param paramMap
	 * @param type 
	 * @return	업데이트건수
	 */		
	public LoginUserVO updatePassword(Map<String, String> paramMap, String type);
	
	/**
	 * <pre>
	 * 1. 개요     : HANACOMM 유저의 회원 테이블 조회
	 * 2. 처리내용 :	HANACOMM 유저의 회원 테이블 조회
	 * </pre>
	 * @Method Name : getLoginForEmployee
	 * @param paramMap	empCode(사원코드)
	 * @return	LoginUserVO(회원정보)
	 */		
	public LoginUserVO getLoginForEmployee(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : HANACOMM 유저의 회원 테이블에서 비밀번호 변경
	 * 2. 처리내용 : HANACOMM 유저의 회원 테이블에서 비밀번호 변경
	 * </pre>
	 * @Method Name : updatePasswordForEmployee
	 * @param paramMap	newPassword(새 패스워드), empCode(사원코드)
	 * @return	LoginUserVO(회원정보)
	 */		
	public LoginUserVO updatePasswordForEmployee(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : HANACOMM 유저의 이력테이블에 비밀번호 이력정보 인서트
	 * 2. 처리내용 : HANACOMM 유저의 이력테이블에 비밀번호 이력정보 인서트
	 * </pre>
	 * @Method Name : insertHisPasswordForEmployee
	 * @param paramMap	newPassword(새 패스워드), empCode(사원코드)
	 */		
	public void insertHisPasswordForEmployee(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 오라클 유저 정보 조회.
	 * 2. 처리내용 : 오라클 유저 정보 조회해서 Map 리스트 형태로 리턴한다.
	 * </pre>
	 * @Method Name : getOracleUserList
	 * @return	오라클 유저 리스트
	 */		
	public List<Map<String, String>> getOracleUserList();

	/**
	 * <pre>
	 * 1. 개요     : 비밀번호 유효성 검사 프로시저 호출
	 * 2. 처리내용 : 비밀번호 유효성 검사 프로시저 호출
	 * </pre>
	 * @Method Name : callPasswordValidateProcedure
	 * @param validateParamMap
	 * @return
	 */		
	public LoginUserVO callPasswordValidateProcedure(Map<String, String> paramMap);
	
}
