/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.member.service;

import java.util.Map;

import com.hanaph.saleon.member.vo.LoginUserVO;

/**
 * <pre>
 * Class Name : LoginUserService.java
 * 설명 : 로그인/로그아웃/비밀번호변경 관련 service interface.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 17.      KIMJAEKAP
 * </pre>
 * 
 * @version :
 * @author  : KIMJAEKAP(slamwin@irush.co.kr)
 * @since : 2014. 10. 17.
 */
public interface LoginUserService {

	/**
	 * 
	 * <pre>
	 * 1. 개요     : 요청받은 사원코드의 로그인 정보를 조회
	 * 2. 처리내용 : 온라인 유저 테이블을 사원코드를 조회 후 없을 경우 SALE.SALE0007,  HANACOMM.CO_US_MEMBER_0 두 테이블을 조인해서 가져온다. 
	 * </pre>
	 * @Method Name : getLogin
	 * @param paramMap	empCode(사원코드)
	 * @return
	 */
	public LoginUserVO getLogin(Map<String, String> paramMap);
	
	/**
	 * 
	 * <pre>
	 * 1. 개요     : 패스워드 변경
	 * 2. 처리내용 : 패스워드 변경한다. 사원일 경우 HANACOMM.CO_US_MEMBER_0을 업데이트시키고 이력도 남긴다.
	 * </pre>
	 * @Method Name : updatePassword
	 * @param paramMap	newPassword(새 패스워드), empCode(사원코드)
	 * @return
	 */
	public LoginUserVO updatePassword(Map<String, String> paramMap);

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
