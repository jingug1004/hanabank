<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : error.jsp
 * @메뉴명 : 공통 > error page       
 * @최초작성일 : 2014/12/9            
 * @author : 김재갑               
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<script>
	$(window).load(function() { 
		if(parent && parent!=this){ 
			$("#headerTag").html("");
		}
	});
	</script>
</head>
<body>
	<div id="headerTag" class="wrap_header">
		<div class="header">
			<h1><a href="#"><img src="<%=ONLINE_ROOT%>/asset/img/logo.jpg" alt="" /></a></h1>
		</div>
	</div>
	
	<div class="wrap_con error_bg">
		<div class="wrap_error">
			<div class="error">
				<p>요청 처리 과정에서 <span>에러</span>가 발생하였습니다.</p>
				<p>잠시 후 다시 시도하시거나 전산팀에 문의 바랍니다.</p>
			</div>
		</div>
	</div>
	
	<%@include file="/include/footer.jsp"%>
</body>
</html>