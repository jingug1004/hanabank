<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : gnb.jsp
 * @설명 : 공통 GNB
 * @최초작성일 : 2015/01/20            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%@ page import="java.util.*, com.hanaph.saleon.mgmt.vo.*"%>
<%@ page import="java.util.Map.Entry,
	org.springframework.web.context.WebApplicationContext,
 	org.springframework.web.context.request.RequestContextHolder,
 	org.springframework.web.context.request.ServletRequestAttributes,
 	org.springframework.web.context.support.WebApplicationContextUtils,
 	com.hanaph.saleon.member.vo.SessionUserInfoVO,
 	org.springframework.beans.factory.ObjectFactory"%>
<%
	WebApplicationContext wContext = WebApplicationContextUtils.getWebApplicationContext(request.getSession().getServletContext());
	SessionUserInfoVO sessionUserInfoVO = (SessionUserInfoVO)((ObjectFactory)wContext.getBean("sessionContextFactory")).getObject();
	String menu1 = sessionUserInfoVO.getGnbMenuHtml("00001");
	String menu2 = sessionUserInfoVO.getGnbMenuHtml("00016");
	String menu3 = sessionUserInfoVO.getGnbMenuHtml("00034");
%> 
<script type="text/javascript">
 	function goLogout(){
		location.href = "<%=ONLINE_ROOT %>/member/logOut.do";
	}
</script>
<div class="wrap_header">
	<div class="header">
	<h1><a href="javascript:Commons.addTab('Main');"><img src="<%=ONLINE_WEB_ROOT%>/img/logo.jpg" alt="하나제약 ONLINE SALE" /></a></h1>
	<ul class="gnb">
		<%if(!menu1.isEmpty()){ %>
		<li><a href="#" class="m01">MANAGER</a>
			<div class="wrap_sub_top">
				<div class="wrap_sub_bottom">
					<ul>
						<%=menu1 %>
					</ul>
				</div>
			</div>
		</li>
		<%} %>
		<%if(!menu2.isEmpty()){ %>
		<li><a href="#" class="m02">온라인발주</a>
			<div class="wrap_sub_top">
				<div class="wrap_sub_bottom">
					<ul>
						<%=menu2 %>
					</ul>
				</div>
			</div>
		</li>
		<%} %>
		<%if(!menu3.isEmpty()){ %>
		<li><a href="#" class="m03">영업관리</a>
			<div class="wrap_sub_top">
				<div class="wrap_sub_bottom">
					<ul>
						<%=menu3 %>
					</ul>
				</div>
			</div>
		</li>
		<%} %>
		
	</ul>
	
	<div class="info">
		<span class="name"><%=userDeptName %> <%= userAssgnName  %></span><br/>
		<%if("jdbc:oracle:thin:@58.229.234.12:1521:ORCL".equals(JDBC_URL)){ %> 
		<span class="name">(테스트서버)</span>
		<%} %>
		<span class="name"><%=userEmpName %><%=userGradeName.isEmpty() ? "" : " " + userGradeName  %></span>
		<span class="pwc"><a href="javascript:Commons.popupOpen('pwPop','<%=ONLINE_ROOT %>/member/passwordChangeForm.do','500','400');">비밀번호 변경</a></span>
		<span class="log"><a href="javascript:goLogout();">로그아웃</a></span>
	</div>

	</div>
</div>