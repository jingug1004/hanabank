/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.common.interceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hanaph.saleon.common.utils.CommonUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;
import com.hanaph.saleon.member.vo.SessionUserInfoVO;

/**
 * <pre>
 * Class Name : AuthCheckInterceptor.java
 * 설명 : <pre>
 * 로그인을 담당하는 check 인터셉터.
 * spring-servlet.xml 파일에서 설정하며 preHandle,postHandle,afterHandle순으로 수행한다.
 * </pre>		
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 6. slamwin          
 * </pre>
 * 
 * @version : 1.0
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2014. 10. 6.
 */
public class AuthCheckInterceptor extends HandlerInterceptorAdapter {
	
	/**
	 * log
	 */
	private static final Logger logger = Logger.getLogger(AuthCheckInterceptor.class);
	
	/**
	 * ObjectFactory
	 */
	@SuppressWarnings("rawtypes")
	@Resource(name="sessionContextFactory")
	ObjectFactory sessionContextFactory;
	
	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.handler.HandlerInterceptorAdapter#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		NoLoginCheck usingAuth = ((HandlerMethod) handler).getMethodAnnotation(NoLoginCheck.class);		// NoLoginCheck 어노테이션 여부
		
        if(usingAuth == null) {	//NoCheckLogin 어노테이션이 없음으로 무조건 로그인 체크
        	
        	/*
        	 * 세션에 저장된 유저 정보(LoginUserVO)를 가져와 로그인 여부를 판별해, 로그인 정보가 없을 경우 에러메시지를 송출한다.
        	 */
        	HttpSession session = request.getSession();
        	if (session == null) {		//세션이 없거나 끊어진 경우
        		logger.debug("login fail!");
				if(CommonUtil.isAjaxRequest(request)) {
					response.sendError(HttpServletResponse.SC_FORBIDDEN);
					return false;
				} else {
					throw new ModelAndViewDefiningException(CommonUtil.getMessageView("로그인 정보가 없습니다.", "Commons.sessionOut();", null));
				}
        		
        		
        	}else{
        		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
            	
            	if (loginUserVO == null || loginUserVO.getEmp_code() == null) {	//세션이 있지만 유저 정보가 없을 경우
            		logger.debug("login fail!");
            		if(CommonUtil.isAjaxRequest(request)) {
    					response.sendError(HttpServletResponse.SC_FORBIDDEN);
    					return false;
    				} else {
                		throw new ModelAndViewDefiningException(CommonUtil.getMessageView("로그인 정보가 없습니다.", "Commons.sessionOut();", null));
    				}
            	}else{
            		logger.debug("login sucsess!");
            	}
        	}
        	
        	/*
        	 * 유저가 요청한 URI에 대한 접근 권한 여부를 체크한 뒤 없을 경우 에러 메시지를 송출한다.
        	 * ajax 요청은 체크하지 않음.
        	 */
        	SessionUserInfoVO sessionUserInfoVO = (SessionUserInfoVO)sessionContextFactory.getObject();
        	if(!CommonUtil.isAjaxRequest(request) && !sessionUserInfoVO.isAccessibleUrl(request.getRequestURI())){
        		logger.error("요청하신 URL에 대한 접근 권한이 없습니다. URI=>"+request.getRequestURI());
				/*
				//테스트를 위해 주석처리함
        		throw new ModelAndViewDefiningException(CommonUtil.getMessageView("요청하신 URL에 대한 접근 권한이 없습니다.", "Commons.sessionOut();", null));
        		*/
        	}
        	request.setAttribute("pgm_no", sessionUserInfoVO.getPgmNo(request.getRequestURI()));	// 권한이 있으므로 현재 URI에 해당하는 프로그램의 No를 request에 심어줌.
        
        
        }else {		//NoCheckLogin 어노테이션이 있음으로 로그인 체크 하지 않음
        	logger.debug("not login page!");
        }
        
		return true;
	}
}
