<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../../include/head.jsp"%>
</head>
<body>
	<div class="popup notice_bg" title="Main">
		
		<!-- ##### content start ##### -->
		<!-- window size : 600 * 480 -->
			<div class="wrap_notice">
				<h1 class="mt10 ta_c">공지사항</h1>
				
				<div class="box_type01">
					<table class="type03">
						<colgroup>
							<col style="width:80px;" />
							<col style="width:70px;" />
							<col style="width:75px;" />
							<col style="width:130px;" />
							<col style="width:70px;" />
							<col style="width:;" />
						</colgroup>
						<tr>
							<th class="no_border_t no_border_l">공지번호</th>
							<td class="no_border_t">20140902001</td>
							<th class="no_border_t">작성일시</th>
							<td class="no_border_t">2014-09-02 오전12:00</td>
							<th class="no_border_t">유효기간</th>
							<td class="no_border_t no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">작성자</th>
							<td>윤홍주</td>
							<th>E-Mail</th>
							<td colspan="3" class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">제목</th>
							<td colspan="5" class="no_border_r">추석연휴의 건</td>
						</tr>
						<tr>
							<td colspan="6" class="no_border_l no_border_b no_border_r h200 va_t">
								당사의 추석연휴기간은 09.05~09.09입니다.<br />
								업무에 착오없으시기 바랍니다.
							</td>
						</tr>
					</table>
				</div>
				
				<div class="wrap_confirmed ta_r mt10">
					<input type="checkbox" />
					<label>이 공지내용을 확인하였습니다.</label>
					<button class="closed"><span class="blind">닫기</span></button>
				</div>
			</div>
		
		<!-- ##### content end ##### -->
	
	</div>
	<%@include file="../../include/footer_pop.jsp"%>
</body>
</html>