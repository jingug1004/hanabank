<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../../include/head.jsp"%>
</head>
<body>
	<div class="popup" title="Main">
		
		<!-- ##### content start ##### -->
		<!-- window size : 480 * 391 -->
			<h1 class="tit">담보제공 약속이 필요합니다</h1>
			
			<div class="box_type01">
				<table class="type01">
					<colgroup>
						<col style="width:100px;" />
						<col />
					</colgroup>
					<tr>
						<th class="no_border_l no_border_t">약속기일</th>
						<td class="no_border_r no_border_t">
							<p class="wrap_date">
								<input type="text" />
								<button class="btn_date"><span class="blind">달력보기</span></button>
							</p>
						</td>
					</tr>
					<tr>
						<th class="no_border_l no_border_b">약속사항</th>
						<td class="no_border_r no_border_b">
							<textarea class="w300 h150"></textarea>
						</td>
					</tr>
				</table>
			</div>
			<div class="wrap_confirm">
				<button>확인</button>
				<button>취소</button>
			</div>
			
			<button class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
	</div>
	
	<%@include file="../../include/footer_pop.jsp"%>
</body>
</html>