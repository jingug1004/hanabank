<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../../include/head.jsp"%>
</head>
<body>
	<div class="popup" title="Main">
		
		<!-- ##### content start ##### -->
		<!-- window size : 1000 * 770 -->
			<h1 class="tit">Role 프로그램등록</h1>
			
			<div class="wrap_program">
				<div class="float_l">
					<h2 class="tit">프로그램 사용 메뉴</h2>
					<div class="box_type01 w245 h550">
						<div class="list_button" style="width:92px; margin:10px auto">
							<button class="type_01" onclick="javascript: d.openAll();">펼치기</button>
							<button class="type_01" onclick="javascript: d.closeAll();">접기</button>
						</div>
						<div class="mt10"></div>
					</div>
				</div>
				<div class="float_l ml10">
					<h2 class="tit">Role 등록 프로그램</h2>
					<div class="box_type01 w420 h260"></div>
					
					<h2 class="tit mt30">프로그램 버튼 List</h2>
					<div class="box_type01 w420 h200"></div>
					<div class="ta_r mt10">
						<button>삭제</button>
						<button>저장</button>
						<button>닫기</button>
					</div>
				</div>
				<div class="float_l ml10">
					<h2 class="tit">Role 등록 미리보기</h2>
					<div class="box_type01 w245 h550"></div>
				</div>
			</div>
			
			<button class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
	</div>
	
	<%@include file="../../include/footer_pop.jsp"%>
</body>
</html>