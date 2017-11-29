/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;

import com.hanaph.saleon.common.interceptor.NoLoginCheck;
import com.hanaph.saleon.common.utils.CommonUtil;
import com.hanaph.saleon.common.utils.Environment;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.service.LoginUserService;
import com.hanaph.saleon.member.vo.LoginUserVO;
import com.hanaph.saleon.member.vo.SessionUserInfoVO;
import com.hanaph.saleon.mgmt.service.MgmtProgramService;


/**
 * <pre>
 * Class Name : LoginUserController.java
 * 설명 : 로그인/로그아웃/비밀번호변경 관련 컨트롤러 클래스
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 4.      장일영            최초생성
 * </pre>
 * 
 * @version : 1.0
 * @author  : 장일영(goodhi@irush.co.kr)
 * @since   : 2014. 12. 4.
 */
@Controller
public class LoginUserController {
	
	/**
	 * LoginUserService
	 */
	@Autowired
	private LoginUserService loginUserService;
	
	/**
	 * 세션빈을 로드하기 위한 sessionContextFactory 설정
	 */
	@SuppressWarnings("rawtypes")
	@Resource(name="sessionContextFactory")
	ObjectFactory sessionContextFactory;
	
	/**
	 * MgmtProgramService
	 */
	@Autowired
	private MgmtProgramService mgmtProgramService;
	
	/**
	 * 환경변수
	 */
	private Environment env = new Environment();
	
	/**
	 * <pre>
	 * 1. 개요     : 로그인 화면 호출 
	 * 2. 처리내용 : 로그인 화면 호출 
	 * </pre>
	 * @Method Name : loginForm
	 * @param request	HttpServletRequest
	 * @return	ModelAndView
	 */		
	@RequestMapping("/member/login.do")
	@NoLoginCheck
	public ModelAndView loginForm(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("/member/loginForm");
		return mav;
	}
	
			
	/**
	 * <pre>
	 * 1. 개요     : 로그인 처리 메소드
	 * 2. 처리내용 : 입력받은 사원코드와 비밀번호를 DB 조회해서 비교한 후 결과를 리턴한다.
	 * </pre>
	 * @Method Name : getLoginPassword
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException	
	 */
	@RequestMapping("/member/getLogin.do")
	@NoLoginCheck
	public void getLoginPassword(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/*
		 * parameter를 변수에 저장
		 */
		String password = StringUtil.nvl(request.getParameter("password"),"password");		//유저가 입력한 패스워드
		
		/*
		 * parameter를 paramMap에 저장
		 */
		Map<String, String> paramMap = new HashMap<String, String>();	//서비스단에 전달한 parameter를 담는 Map객체
		paramMap.put("empCode", StringUtil.nvl(request.getParameter("empCode"),"empCode"));	//유저가 입력한 사원 코드
		paramMap.put("password", password);
		
		/**
		 * 유저가 입력한 사원코드를 기준으로 사원 정보를 조회한다.
		 * 유저가 입력한 패스워드와 DB에서 조회한 패스워드가 일치하면 로그인 처리를 진행한다.
		 */
		LoginUserVO loginUserVO = loginUserService.getLogin(paramMap);
		
		if(loginUserVO == null){	//사원코드에 해당하는 사원이 없은 경우
			loginUserVO = new LoginUserVO();
			loginUserVO.setResultcode(0);	//사원정보가 없다는 에러코드 
			
		}else{
			/*
			 * 거래처이거나
			 * 하나제약 임직원인데 HANACOMM.CO_US_MEMBER_O, SALE0007 둘 다 데이터가 있는 경우
			 */
			if("1".equals(loginUserVO.getEmp_gb()) || 				
					(!"1".equals(loginUserVO.getEmp_gb()) && loginUserVO.getSawon_id() != null && !loginUserVO.getSawon_id().equals(""))){

				if("1".equals(loginUserVO.getEmp_gb()) && (!"Y".equals(loginUserVO.getUse_yn())) ){
					loginUserVO.setResultcode(4);	//중지거래처						
				}else{ 
					HttpSession session = request.getSession(false);	//세션 객체 생성. 세션이 없다면 null을 반환
						
					if(session != null){	//세션 객체가 있을 경우 세션을 종료함
						session.invalidate(); 
					}
					
					session = request.getSession(true);	//세션 객체 생성. 세션이 없다면 새로 세션을 생성함.
					
					session.setMaxInactiveInterval(60*60); 	// 세션 시간 설정 1시간
					session.setAttribute("onlineUser", loginUserVO);	// 유저정보를 onlineUser라는 이름으로 세션에 저장함.
					
					
					// 비밀번호가 보안 기준에 맞는지 체크, 맞지 않다면 로그인 후 비밀번호 변경 팝업을 띄우게 한다.
					Map<String, String> validateParamMap = new HashMap<String, String>();
					validateParamMap.put("in_EMP_NO", StringUtil.nvl(request.getParameter("empCode"),"empCode"));
					validateParamMap.put("in_PASSWORD", password);
					
					LoginUserVO validateResultVO = loginUserService.callPasswordValidateProcedure(validateParamMap);
					
					if (!"0".equals(validateResultVO.getOut_CODE())) {
						session.setAttribute("reqChgPassword", "true");		// 비밀번호 변경 팝업을 띄우게 설정
						session.setAttribute("chgPasswordReason", validateResultVO.getOut_MSG());	// alert 메세지
					} else {
						session.setAttribute("reqChgPassword", "false");
						session.setAttribute("chgPasswordReason", "");
					}
					
					/*
					 *  메뉴 권한 조회
					 */
					SessionUserInfoVO sessionUserInfoVO = (SessionUserInfoVO)sessionContextFactory.getObject();			//세션빈 객체를 생성
					sessionUserInfoVO.setLoginUserVO(loginUserVO);														//세션빈 객체에 사원 정보를 저장함.
					sessionUserInfoVO.setMenuList(mgmtProgramService.getMenuAuthByUser(loginUserVO.getEmp_code()));		//세션빈의 menuList변수에 사원이 접근가능한 프로그램 정보를 셋팅함
					
					loginUserVO.setResultcode(1);	//로그인 성공
				}	
				
			}else{
				loginUserVO.setResultcode(2);	//HANACOMM.CO_US_MEMBER_O에만 데이터가 존재하는 경우 SALE.SALE0007 데이터 미 존재	
			}
		}
		
		MarshallerUtil.marshalling("json", response, loginUserVO);	//VO를 json으로 변경해서 뷰단에 응답함
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 비밀번호 변경 화면 호출
	 * 2. 처리내용 : 비밀번호 변경 화면 호출
	 * </pre>
	 * @Method Name : passwordChangeForm
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @return	ModelAndView
	 */		
	@RequestMapping("/member/passwordChangeForm.do")
	public ModelAndView passwordChangeForm(HttpServletRequest request, HttpServletResponse response){
		ModelAndView mav = new ModelAndView("/member/pwForm");
		return mav;
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 현재 로그인된 사원의 패스워드와 입력받은 패스워드를 비교
	 * 2. 처리내용 : 현재 로그인된 사원의 패스워드와 입력받은 패스워드를 비교하여 결과를 리턴한다.
	 * </pre>
	 * @Method Name : passwordCheck
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException	
	 */		
	@RequestMapping("/member/passwordCheck.do")
	public void passwordCheck(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/*
		 *  세션에서 사원 정보를 가져온다.
		 */
		HttpSession session = request.getSession();
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		
		/*
		 * 사원코드로 사원 정보를 조회한다.
		 */
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("empCode", StringUtil.nvl(loginUserVO.getEmp_code()));
		paramMap.put("password", StringUtil.nvl(request.getParameter("oldPassword"),""));
		loginUserVO = loginUserService.getLogin(paramMap);
		
		if(loginUserVO == null){		//사원 정보가 없을 경우 틀렸다는 에러코드 반환
			loginUserVO = new LoginUserVO();
			loginUserVO.setResultcode(0);
		}else{
			if("".equals(loginUserVO.getSawon_id())){
				loginUserVO.setResultcode(2);	//HANACOMM.CO_US_MEMBER_O에만 데이터가 존재하는 경우 SALE.SALE0007 데이터 미 존재
			} else { //비밀번호가 일치할 경우
				loginUserVO.setResultcode(1);	//성공 코드 반환
			}
				
		}
		
		MarshallerUtil.marshalling("json", response, loginUserVO);		//VO를 json으로 변경해서 뷰단에 응답함
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 비밀번호 변경 
	 * 2. 처리내용 : 현재 비밀번호를 변경 한다.
	 * </pre>
	 * @Method Name : updatePassword
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws Exception	 
	 */		
	@RequestMapping("/member/updatePassword.do")
	public void updatePassword(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		/*
		 * 세션에서 emp_code를 가져온다.
		 */
		HttpSession session = request.getSession();
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String empCode = StringUtil.nvl(loginUserVO.getEmp_code());		//사원 코드
		
		/*
		 * parameter 처리
		 */
		String oldPassword = StringUtil.nvl(request.getParameter("oldPassword"),"");
		String newPassword = StringUtil.nvl(request.getParameter("newPassword"),"");
		
		/*
		 * 기존 비밀번호와 신규비밀번호가 없을 경우 에러 코드 반환
		 */
		if("".equals(oldPassword) || "".equals(newPassword)){	
			loginUserVO.setResultcode(-1);
		}
		
		/*
		 *  service단으로 넘겨줄 파라메터를 담는 Map객체 
		 */
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("empCode", empCode);		//사원 코드
		paramMap.put("password", oldPassword);
		
		/*
		 *  새 비밀번호 정규식 체크. 
		 *  5~20자의 영문 대소문자, 숫자, 특수기호 혼용사용할 수 있음
		 */
		Map<String, String> validateParamMap = new HashMap<String, String>();
		validateParamMap.put("in_EMP_NO", empCode);
		validateParamMap.put("in_PASSWORD", newPassword);
		
		LoginUserVO validateResultVO = loginUserService.callPasswordValidateProcedure(validateParamMap);
		
		/*
		 *	 새 비밀번호가 규칙에 맞다면 
		 */
		if ("0".equals(validateResultVO.getOut_CODE())) {
			loginUserVO = loginUserService.getLogin(paramMap);		//사원 정보 조회
				
			if(loginUserVO == null){		//사원정보가 없을 경우 에러 코드 반환
				loginUserVO = new LoginUserVO();
				loginUserVO.setResultcode(0);
				
			}else{
				paramMap.put("newPassword", newPassword);
				paramMap.put("firstEmpNo", empCode);
				paramMap.put("lastEmpNo", "");
				paramMap.put("lastIp", "");
				
				LoginUserVO resultVO = loginUserService.updatePassword(paramMap);		//새 비밀번호로 변경
				
				if("0".equals(resultVO.getOut_CODE())){		//비밀번호가 변경되었을 경우 세션에 저장된 사원 정보의 비밀번호를 갱신한다.
					LoginUserVO sessionLoginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
					sessionLoginUserVO.setPassword(newPassword);
					session.setAttribute("onlineUser", sessionLoginUserVO);
					session.setAttribute("reqChgPassword", "false");
					session.setAttribute("chgPasswordReason", "");
					loginUserVO.setResultcode(1);		//비밀 번호 변경 성공 코드
				} else {
					loginUserVO.setResultcode(0);		//비밀 번호 변경 실패 코드
				}
			}
		} else {
			loginUserVO.setResultcode(-2);		//새 비밀번호가 비밀번호 규칙에 어긋난다는 에러 코드
			loginUserVO.setOut_MSG(validateResultVO.getOut_MSG()); // 에러 메세지
		}
		
		MarshallerUtil.marshalling("json", response, loginUserVO);		//VO를 json으로 변경해서 뷰단에 응답함
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 로그아웃
	 * 2. 처리내용 : 현재 로그인 되어있는 session을 지운다.
	 * </pre>
	 * @Method Name : logOut
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @return	ModelAndView
	 * @throws ModelAndViewDefiningException	ModelAndViewDefiningException
	 */		
	@RequestMapping("/member/logOut.do")
	public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) throws ModelAndViewDefiningException{
		/*
		 *  세션 종료
		 */
		request.getSession().invalidate();		
		
		/*
		 * logout을 알려주고 로그인페이지로 이동
		 */
		ModelAndView mav = CommonUtil.getMessageView("로그아웃 되었습니다.", "top.location.href='"+env.getValue("root_dir.url")+"/member/login.do'","");
		
		throw new ModelAndViewDefiningException(mav);
		
	}
	
}
