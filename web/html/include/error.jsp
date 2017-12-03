<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../include/head.jsp"%>
</head>
<body>
	<div class="wrap_header">
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
	
	<%@include file="../include/footer.jsp"%>
</body>
</html>