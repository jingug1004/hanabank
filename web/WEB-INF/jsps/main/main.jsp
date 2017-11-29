<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : main.jsp
 * @메뉴명 : Top Frame 메인 화면 jsp   
 * @최초작성일 : 2014/10/29            
 * @author : 장일영                   
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%
	//http:// 로 들어왔을 경우 https://로 redirect
	String url = request.getRequestURL().toString();

	//https:// 로 시작하고, 운영 url일 경우에만 redirect	
	if( url.startsWith("https://") && ( url.indexOf("saleon.") > 1) ) {
		response.sendRedirect(ONLINE_ROOT + "/main.do");
	}

	String dashboardUrl = (String)request.getAttribute("dashboardUrl");		//대시보드 url
	String reqChgPassword = session.getAttribute("reqChgPassword") == null ? "false" : (String)session.getAttribute("reqChgPassword");		//영업사원의 경우 비밀번호 변경 팝업 보여주는 지 여부
	String chgPasswordReason = session.getAttribute("chgPasswordReason") == null ? "" : (String)session.getAttribute("chgPasswordReason");
%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>하나제약 온라인주문</title>
	<%@ include file ="/include/head.jsp" %>
	<script type='text/javascript' src='<%=ONLINE_WEB_ROOT%>/js/jquery.bpopup.min.js'></script>
	<script>
	/* 브라우저 창의 사이즈가 변경될 때 */
	$(window).resize(function(){
		$("#tabs").tabs("resize");		//tab 사이즈 변경
		Commons.resizePanel();			//tab안에 있는 iframe의 height에 맞춰서 tab panel의 height값 변경
	});
	
	/* 화면의 Dom객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		
		if(<%=reqChgPassword %>){		// 비밀번호 변경 팝업 보여주는 경우 
			$('#pop_pass_change').bPopup({
				content:'iframe', //'ajax', 'iframe' or 'image'
				iframeAttr:'scrolling="no" frameborder="0" width="500px" height="400px"',
				follow: [true, true],
				contentContainer:'.pass_content',
				modalClose: false,
	            opacity: 0.6,
	            positionStyle: 'fixed',
	            easing: 'easeOutBack', //uses jQuery easing plugin
	            speed: 950,
	            transition: 'slideDown',
				loadUrl:'<%=ONLINE_ROOT %>/member/passwordChangeForm.do'
			}, function(){
				if ("<%=chgPasswordReason%>" != "") {
					alert("<%=chgPasswordReason%>");
				}
			});
		
		} else {						//비밀번호 변경 팝업을 보여주지 않은 경우 대시보드 tab 생성
			<%if(dashboardUrl != null && !dashboardUrl.isEmpty()){ %>
			Commons.addTab('Main', '<%=dashboardUrl %>', false);
			<%} %>
		}
		
	});
	
	
	/**
	 * 비밀번호 변경 팝업 닫으면서 대시보드 tab 생성
	 */
	function popPassChangelayerClose(){ 
		$('#pop_pass_change').bPopup().close();
		<%if(dashboardUrl != null && !dashboardUrl.isEmpty()){ %>
		Commons.addTab('Main', '<%=dashboardUrl %>', false);
		<%} %>
	}
	
	</script>
</head>
<body style="overflow-y: hidden;">
	<%@include file="/include/gnb.jsp"%>
	
	<!-- 실제 업무 화면을 생성하는 tab -->
	<div id="tabs" class="wrap_con easyui-tabs" headerTotalWidth="967">
	</div>
	
	<!-- 비번변경 layer -->
	<div id='pop_pass_change' style='display:none; width:auto; height:auto; '>
		<div class='pass_content'></div> 
	</div>
</body>
</html>