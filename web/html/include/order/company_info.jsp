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
			<div class="wrap_btn_group">
				<div class="btn_group ta_r">
					<button disabled="disabled">조회</button>
					<button>조회</button>
					<button>저장</button>
					<button>인쇄</button>
					<button>엑셀</button>
					<button>닫기</button>
				</div>
			</div>
			<div class="wrap_company box_type01 w967 m0auto mt25">		
				<table class="type01 re_style03">
					<colgroup>
						<col style="width:15%" />
						<col style="width:85%" />
					</colgroup>
					<tbody>
						<tr>
							<th class="no_border_l no_border_t">회사번호</th>
							<td class="no_border_r no_border_t"></td>
						</tr>
						<tr>
							<th class="no_border_l">회사명</th>
							<td class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">사업자번호</th>
							<td class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">대표자명</th>
							<td class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">주민(법인)번호</th>
							<td class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">업테</th>
							<td class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">종목</th>
							<td class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">우편번호</th>
							<td class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">주소</th>
							<td class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">전화번호</th>
							<td class="no_border_r"><input type="text" class="input_size" /></td>
						</tr>
						<tr>
							<th class="no_border_l">핸드폰번호</th>
							<td class="no_border_r"><input type="text" class="input_size" /></td>
						</tr>
						<tr>
							<th class="no_border_l">팩스번호</th>
							<td class="no_border_r"><input type="text" class="input_size" /></td>
						</tr>
						<tr>
							<th class="no_border_l">이메일주소</th>
							<td class="no_border_r"><input type="text" class="input_size" /></td>
						</tr>
						<tr>
							<th class="no_border_l no_border_b">온라인입금 전용계좌</th>
							<td class="no_border_r no_border_b online_num">
								<span class="name_tit">* 예금주 : <span class="name_txt">하나제약</span></span>
								<span class="name_tit">* 입금은행 : <span class="name_txt">기업은행</span></span>
							</td>
						</tr>
					</tbody>			
				</table>
			</div>
			<p class="company_txt w967 mt30">※  전화번호 및 핸드폰, 팩스번호, 메일주소 등은 입력/수정하여 주시기 바라며, 이외 다른 변경사항은 담당자와 통화 후 관련서류를 제출하여 주시기 바랍니다.</p>
		</div>
		<!-- ##### content end ##### -->
		
		</div>
	</div>
	
	<%@include file="../../include/footer.jsp"%>
</body>
</html>