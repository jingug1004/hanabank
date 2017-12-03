<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../../include/head.jsp"%>
</head>
<body>
	<div class="popup" title="Main">
		
		<!-- ##### content start ##### -->
		<!-- window size : 520 * 747 -->
			<h1 class="tit">Role 프로그램 등록</h1>
			
			<div class="wrap_program_role mt30">
				<h2 class="tit">Role 등록 프로그램</h2>
				<div class="box_type01">
					<table class="type02">
						<colgroup>
							<col style="width:150px;" />
							<col />
						</colgroup>
						<tr>
							<th class="no_border_t no_border_l">PGM NO.</th>
							<td class="no_border_t no_border_r">00049</td>
						</tr>
						<tr>
							<th class="no_border_l">프로그램 명</th>
							<td class="no_border_r">수금현황</td>
						</tr>
						<tr>
							<th class="no_border_l" rowspan="2">프로그램 ID</th>
							<td class="no_border_r">W_SALEONRPT01_001</td>
						</tr>
						<tr>
							<td class="no_border_r">p_close, P_excel, P_retrieve</td>
						</tr>
						<tr>
							<th class="no_border_b no_border_l">윈도우 버튼 권한</th>
							<td class="no_border_b no_border_r">
								<textarea class="w340 h100"></textarea>
							</td>
						</tr>
					</table>
				</div>
				
				<h2 class="tit mt30">프로그램 버튼 List</h2>
				<div class="box_type01 h200">
					
				</div>
				
				<div class="ta_r mt10">
					<button>저장</button>
					<button>닫기</button>
				</div>
			</div>
			
			<button class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
	</div>
	
	<%@include file="../../include/footer_pop.jsp"%>
</body>
</html>