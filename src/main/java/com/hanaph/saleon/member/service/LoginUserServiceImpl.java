/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.member.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanaph.saleon.member.dao.LoginUserDAO;
import com.hanaph.saleon.member.vo.LoginUserVO;

/**
 * <pre>
 * Class Name : LoginUserServiceImpl.java
 * 설명 : 로그인/로그아웃/비밀번호변경 관련 service 구현 클래스
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
@Service
public class LoginUserServiceImpl implements LoginUserService {

	/**
	 * LoginUserDAO
	 */
	@Autowired
	LoginUserDAO loginUserDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.member.service.LoginUserService#getLogin(java.util.Map)
	 */
	@Override
	public LoginUserVO getLogin(Map<String, String> paramMap) {
		/**
		 하나제약 임직원 사원번호 매핑 정보(매핑 테이블은 SALE.SALE0007)
		 SALE.SALE0007.SAWON_ID => (시스템)사원번호. (5/7). 세션. 실적.
	     SALE.SALE0007.INSA_SAWON_ID => 인사사원번호(7)
	     HANACOMM.CO_US_MEMBER_0.EMP_NO => 인사사원번호 (7)
	     HANACOMM.CO_US_PASSWORD_HISTORY => 인사사원번호 (7)
		 */
		
		LoginUserVO retVO = loginUserDAO.getLogin(paramMap);	
		if(retVO == null){
			retVO = loginUserDAO.getLoginForEmployee(paramMap);
		}
		return retVO;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.member.service.LoginUserService#updatePassword(java.util.Map)
	 */
	@Transactional
	@Override
	public LoginUserVO updatePassword(Map<String, String> paramMap){
		/**
		 하나제약 임직원 사원번호 매핑 정보(기준이 되는 테이블은 SALE.SALE0007)
		 SALE.SALE0007.SAWON_ID => (시스템)사원번호. (5/7). 세션. 실적.
	     SALE.SALE0007.INSA_SAWON_ID => 인사사원번호(7)
	     HANACOMM.CO_US_MEMBER_0.EMP_NO => 인사사원번호 (7)
	     HANACOMM.CO_US_PASSWORD_HISTORY => 인사사원번호 (7)
		 */
		
		LoginUserVO resultVO = new LoginUserVO();
		
		try {
			// online 유저 패스워드 변경
			resultVO = loginUserDAO.updatePassword(paramMap, "CUST");
			
			if(!"0".equals(resultVO.getOut_CODE())){
				// hanacomm 유저 패스워드 변경
				resultVO = loginUserDAO.updatePassword(paramMap, "");
				if("0".equals(resultVO.getOut_CODE())){
					// hanacomm 유저 패스워드 변경 이력 인서트
					loginUserDAO.insertHisPasswordForEmployee(paramMap);
					
				}
			}
		} catch(Exception e){
			resultVO.setOut_CODE("-1");
			return resultVO;
		}
		return resultVO;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.member.service.LoginUserService#callPasswordValidateProcedure(java.util.Map)
	 */
	@Override
	public LoginUserVO callPasswordValidateProcedure(Map<String, String> paramMap) {
		return loginUserDAO.callPasswordValidateProcedure(paramMap);
	}

}
