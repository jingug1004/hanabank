<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../../include/head.jsp"%>
</head>
<body>
	<div class="popup" title="Main">
		
		<!-- ##### content start ##### -->
		<!-- window size : 1000 * 650 -->
			<h1 class="tit">거래처 기타사항</h1>
			
			<div class="box_type01 etc_customer">
				<table class="type01 ta_l">
					<colgroup>
						<col style="width:175px;" />
						<col />
						<col style="width:175px;" />
						<col />
						<col style="width:175px;" />
						<col />
					</colgroup>
					<tr>
						<th class="no_border_l no_border_t">구분</th>
						<td class="no_border_t"><input type="text" class="" /></td>
						<th class="no_border_t">업태</th>
						<td class="no_border_t"><input type="text" class="" /></td>
						<th class="no_border_t">전화번호</th>
						<td class="no_border_r no_border_t"><input type="text" class="" /></td>
					</tr>
					<tr>
						<th class="no_border_l">거래처 코드</th>
						<td><input type="text" class="" /></td>
						<th>종목</th>
						<td><input type="text" class="" /></td>
						<th>핸드폰번호</th>
						<td class="no_border_r"><input type="text" class="" /></td>
					</tr>
					<tr>
						<th class="no_border_l">거래처명</th>
						<td><input type="text" class="" /></td>
						<th>개업일</th>
						<td class="date_search">
							<div class="date_position">
								<input type="text" class="" /><button type="button" class="btn_date" >날짜찾기</button>
							</div>
						</td>
						<th>팩스번호</th>
						<td class="no_border_r"><input type="text" class="" /></td>
					</tr>
					<tr>
						<th class="no_border_l">거래처명</th>
						<td><input type="text" class="" /></td>
						<th>개업일</th>
						<td><input type="text" class="" /></td>
						<th>팩스번호</th>
						<td class="no_border_r"><input type="text" class="" /></td>
					</tr>
					<tr>
						<th class="no_border_l">대표자</th>
						<td><input type="text" class="" /></td>
						<th>거래개시일</th>
						<td><input type="text" class="" /></td>
						<th>병실수</th>
						<td class="no_border_r"><input type="text" class="" /></td>
					</tr>
					<tr>
						<th class="no_border_l">주민(법인)번호</th>
						<td><input type="text" class="" /></td>
						<th>결재일</th>
						<td class="date_search">
							<div class="date_position">
								<input type="text" class="" /><button type="button" class="btn_date" >날짜찾기</button>
							</div>
						</td>
						<th>BED수</th>
						<td class="no_border_r"><input type="text" class="" /></td>
					</tr>
					<tr>
						<th class="no_border_l">사업자번호</th>
						<td><input type="text" class="" /></td>
						<th>중지일자</th>
						<td><input type="text" class="" /></td>
						<th>이메일주소</th>
						<td class="no_border_r"><input type="text" class="" /></td>
					</tr>
					<tr>
						<th class="no_border_l no_border_b">우편번호</th>
						<td class="no_border_b"><input type="text" class="" /></td>
						<th class="no_border_b">주소</th>
						<td class="no_border_b"><input type="text" class="" /></td>
						<th class="no_border_b">상세주소</th>
						<td class="no_border_r no_border_b"><input type="text" class="" /></td>
					</tr>
				</table>
			</div>
			
			<div class="box_type01 h200 mt30">
				
			</div>
			
			<div class="mt10 ta_r">
				<button>조회</button>
				<button>입력</button>
				<button>저장</button>
				<button>삭제</button>
				<button disabled="disabled">인쇄</button>
				<button disabled="disabled">엑셀</button>
				<button>닫기</button>
			</div>
			
			<button class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
	</div>
	
	<%@include file="../../include/footer_pop.jsp"%>
</body>
</html>