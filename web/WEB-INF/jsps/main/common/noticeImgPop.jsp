<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : noticeImgPop.jsp
 * @메뉴명 : 공지사항 이미지 팝업         
 * @최초작성일 : 2015/01/29            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%
	String dateString = StringUtil.nvl((String)request.getParameter("dateString"),""); // 셋팅한 날짜가 이미지 이름임.
%>
<%@ include file ="/common/path.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="/include/head.jsp"%>
	<script>
	
	/* 화면의 Dom객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		/*
		* 닫기 버튼 클릭했을 때 chkReadYn(체크박스)가 체크되었을 경우 cookie에 sale_on_noticeImgReadDate을 오늘 일자로 셋팅해준다.
		*/
		$('#btnClose').on('click', function(e){
			if($('#chkReadYn').is(":checked")){
				var d = new Date();
				var today = '' + d.getFullYear() + Commons.leadingZeros((d.getMonth()+1), 2) + Commons.leadingZeros(d.getDate(), 2);
				Commons.setCookie('sale_on_noticeImgReadDate', today, 365);
			}
			
			self.close();
		});
	});
		
	</script>
</head>
<body onload="window.focus();">
	<div title="Main" class="wrap_notice_pop">
		<!-- ##### content start ##### -->
		<!-- window size : 700 * 700 -->
			<div class="inner">
			<% if(!"".equals(dateString)){ %>
				<img src="<%=ONLINE_WEB_ROOT %>/img/notice/<%=dateString %>.jpg">
			<% } %>
				<div class="wrap_confirmed ta_r mt10">
					<input type="checkbox" id="chkReadYn" />
					<label for="chkReadYn">이 공지내용을 확인하였습니다.</label>
					<button id= "btnClose" class="close">닫기</button>
				</div>
			</div>
		<!-- ##### content end ##### -->
	</div>
</body>
</html>