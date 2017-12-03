<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../include/head.jsp"%>
</head>
<body>
	<%@include file="../include/header.jsp"%>
	
	<div class="wrap_con easyui-tabs">
		<div class="content" title="Main">
		
		<!-- ##### content start ##### -->
		<div class="inner_cont">
			<div class="wrap_search02 w967 m0auto">
				<label>조회조건</label>
				<select class="w100">
					<option>전체</option>
				</select>
				<input type="text" class="w150" />
				<button>검색</button>
				
				<label>등록일자</label>
				<p class="wrap_date">
					<input type="text">
					<button class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				~
				<p class="wrap_date">
					<input type="text">
					<button class="btn_date"><span class="blind">달력보기</span></button>
				</p>
			</div>
			<div class="box_type01 h460">
				
			</div>
		</div>
		<!-- ##### content end ##### -->
		
		</div>
	</div>
	
	<%@include file="../include/footer.jsp"%>
</body>
</html>