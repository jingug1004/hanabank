<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../../include/head.jsp"%>
</head>
<body>
	<%@include file="../../include/header.jsp"%>
	
	<div class="wrap_con easyui-tabs">
		<div class="content" title="Main">
		
		<!-- ##### content start ##### -->
		<div class="inner_cont">
			<div class="w967 m0auto">
				<h2>My P/L</h2>
				<p class="mt5">
					자신만의 제품리스트를 그룹별로 출력할 수 있는 화면입니다.
					<button class="btn">조회</button>
					<button class="btn">인쇄</button>
				</p>
				
				<div class="wrap_mypl">
					<div class="box float_l">
						<p class="tit ta_c">
							P/L그룹 (총3건)
						</p>
						<div class="box_type01 w120 h460">
							
						</div>
						<div class="ta_c mt10">
							<button class="btn">P/L그룹 관리</button>
						</div>
					</div>
					<div class="box float_l ml37">
						<div class="wrap_tit">
							<p class="tit float_l">
								MY P/L 정렬순서 변경시 저장버튼을 사용하세요.
							</p>
							<div class="float_r">
								<button class="btn">저장</button>
							</div>
						</div>
						<div class="box_type01 w330 h485">
							
						</div>
					</div>
					<div class="change float_l">
						<button class="btn right"><span class="blind">오른쪽으로 이동</span></button><br />
						<button class="btn left"><span class="blind">왼쪽으로 이동</span></button>
					</div>
					<div class="box float_l">
						<label>제품목록</label>
						<select class="w130">
							<option>전체</option>
							<option>해열.진통.소염제</option>
						</select>
						<input type="text" class="w100" />
						<button class="btn ml10">찾기</button>
						
						<div class="box_type01 w340 h485">
							
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ##### content end ##### -->
		
		</div>
	</div>
	
	<%@include file="../../include/footer.jsp"%>
</body>
</html>