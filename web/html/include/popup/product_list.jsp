<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../../include/head.jsp"%>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/placeholders.js"></script>
	
</head>
<body>
	<div class="popup" title="Main">
		
		<!-- ##### content start ##### -->
		<!-- window size : 560 * 700 -->
			<h1 class="tit">출력 제품리스트<span class="subtitle">출력할 제품리스트의 그룹을 만들어주십시오.</span></h1>
			
			<div class="wrap_pop_search">
				<label>P/L그룹 추가</label>
				<input type="text" class="w100" placeholder="그룹명" />
				<input type="text" class="w120" placeholder="그룹설명" />
				<input type="text" class="w120" placeholder="정렬순서" />
				
				<div class="wrap_pop_search_btn">
					<button>저장</button>
				</div>
			</div>
			
			<div class="ta_r mt10">
				<button>조회</button>
				<button>삭제</button>
			</div>
			
			<p class="total">결과 총 00건</p>
			<div class="box_type01 h440">
				
			</div>
			
			<button class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
	</div>
	
	<%@include file="../../include/footer_pop.jsp"%>
</body>
</html>