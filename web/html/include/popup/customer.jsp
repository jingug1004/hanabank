<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../../include/head.jsp"%>
</head>
<body>
	<div class="popup" title="Main">
		
		<!-- ##### content start ##### -->
		<!-- window size : 600 * 655 -->
			<h1 class="tit">거래처</h1>
			
			<div class="wrap_pop_search">
				<label>거래처코드/명</label>
				<input type="text" class="w340" />
				<button>조회</button>
				<button>선택</button>
			</div>
			
			<div class="pop_grid box_type01 h440">
				
			</div>
			
			<button class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
	</div>
	
	<%@include file="../../include/footer_pop.jsp"%>
</body>
</html>