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
			<h1 class="tit">담보약속이력</h1>
			
			<div class="wrap_pop_search">
				<label class="w70">주문요청일</label>
				<p class="wrap_date">
					<input type="text" />
					<button class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				~
				<p class="wrap_date">
					<input type="text" />
					<button class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				
				<div class="wrap_pop_search_btn">
					<button>조회</button>
					<button>닫기</button>
				</div>
			</div>
			
			<div class="pop_grid box_type01 h440">
				
			</div>
			
			<button class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
	</div>
	
	<%@include file="../../include/footer_pop.jsp"%>
</body>
</html>