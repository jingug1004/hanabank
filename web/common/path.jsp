<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : path.jsp
 * @설명 : 공통 환경변수 및 web,was url 변수 
 * @최초작성일 : 2015/01/16            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%@ include file ="/common/sessionCheck.jsp" %>
<%
	com.hanaph.saleon.common.utils.Environment env = new com.hanaph.saleon.common.utils.Environment();	//환경 변수
	
	String HTTPS_ONLINE_WEB_ROOT = env.getValue("https.web_root_dir.url");
	String HTTPS_ONLINE_ROOT = env.getValue("https.root_dir.url");
	String ONLINE_WEB_ROOT = env.getValue("web_root_dir.url");		//URL 프로토콜, 도메인, 포트, 서브컨텍스트명, web디렉토리
	String ONLINE_ROOT = env.getValue("root_dir.url");				//URL 프로토콜, 도메인, 포트, 서브컨텍스트명
	String JDBC_URL = env.getValue("jdbc.url");
	
%>
