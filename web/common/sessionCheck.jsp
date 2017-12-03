<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : sessionCheck.jsp
 * @설명 : 세션체크 공통 jsp 
 * @최초작성일 : 2015/01/20            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%@ page import="com.hanaph.saleon.member.vo.LoginUserVO" %>
<%@ page import="com.hanaph.saleon.common.utils.StringUtil" %>
<%@ page import="com.hanaph.saleon.common.utils.WebUtil" %>
<%
	LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");	//세션생성 세션에서 emp_code를 가져온다.
	
	String userEmpCode  = "";		// 사원 코드
	String userEmpName  = "";		// 사원 이름
	String userEmpGb    = "";		// 사원 구분 
	String userDeptCode = "";		// 부서코드
	String userDeptName = "";		// 부서명
	String userGradeName = "";		// 사원 Grade Name
	String userDeptCd = "";			//hanahr 부서정보
	String userAssgnCd = "";			//직책 코드
	String userAssgnName = "";			//직책 코드명
	/*
	 * 로그인 정보가 있는경우
	 */
	if(loginUserVO != null){
		userEmpCode = StringUtil.nvl(loginUserVO.getEmp_code());    	// 사용자코드
		userEmpName = StringUtil.nvl(loginUserVO.getEmp_name());		// 사용자명
		userEmpGb = StringUtil.nvl(loginUserVO.getEmp_gb());			// 사용자 구분 코드
		userDeptCode = StringUtil.nvl(loginUserVO.getDept_code());  	// 부서 코드
		userDeptName = StringUtil.nvl(loginUserVO.getDept_name());		// 부서 명
		userGradeName = StringUtil.nvl(loginUserVO.getGrade_name());	// 사용자 부서명
		userDeptCd = StringUtil.nvl(loginUserVO.getDept_cd());  	    // 부서 코드
		userAssgnCd = StringUtil.nvl(loginUserVO.getAssgn_cd());  	    // 직책 코드
		userAssgnName = StringUtil.nvl(loginUserVO.getAssgn_name());  	// 직책 코드명
	}
	
	/* 현재 URI에 해당하는 프로그램 No(pgm_no) */
	String currPgmNoByUri = request.getAttribute("pgm_no") == null ? "" : (String) request.getAttribute("pgm_no");
%>
