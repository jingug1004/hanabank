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
		<div class="wrap_search">
			<div class="search">
				<div class="float_l">
					<label class="w70">주문일</label>
					<p class="wrap_date">
						<input type="text">
						<button class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					<select class="w50">
						<option>9</option>
					</select>
					<select class="w50">
						<option>30</option>
					</select>
					~
					<p class="wrap_date">
						<input type="text">
						<button class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					<select class="w50">
						<option>9</option>
					</select>
					<select class="w50">
						<option>30</option>
					</select>
					
					<label class="ml10">승인일</label>
					<p class="wrap_date">
						<input type="text">
						<button class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					
					<label class="ml10">월평균주문월</label>
					<input type="text" class="w80"><br>
					
					<label class="w70">수량한도</label>
					<select class="w125">
						<option>이내(승인가)</option>
					</select>
					
					<label class="ml10">승인구분</label>
					<select class="w125">
						<option>대기</option>
					</select>
					
					<label class="ml10">주문구분</label>
					<select class="w125">
						<option>온라인주문</option>
					</select>
					
					<label class="ml10">여신규정</label>
					<select class="w125">
						<option>대기</option>
					</select>
					
					<input type="checkbox">
					<label>선입금거래처</label><br>
					
					<label class="w70">거래처</label>
					<input type="text" class="w140">
					<button class="btn_search"><span class="blind">찾기</span></button>
					<input type="text" class="w435">
					
					<label class="ml10">간납구분</label>
					<select class="w125">
						<option>일반</option>
					</select>
				</div>
				<div class="wrap_search_btn">
					<button class="btn_type03">조회</button>
				</div>
			</div>
		</div>	
		<div class="inner_cont">		

		<div class="wrap_result_group">
			<div class="result_group">
				<div class="float_r">
					<label class="point">승인구분</label>
					<select class="w100">
						<option>승인</option>
						<option>반려</option>
					</select>
					<label class="point ml10">승인/반려 사유</label>
					<input type="text" class="w350" name="as_return_desc" id="as_return_desc">
					<span class="appro_result_txt ml10">위 : 승 :</span>
					<button class="appro_result_btn ml10">저장</button>
					<button class="appro_result_btn">인쇄</button>
					<button class="appro_result_btn">엑셀</button>
					<button class="appro_result_btn">닫기</button>
				</div>
			</div>
		</div>	

		<div class="table_appro_box mt15">
			<table>
			<colgroup>
				<col style="width:100%" />
				<col style="width:360px;" />
			</colgroup>
			
			<tbody>
			<tr>
				<th>
					<h2>주문내역</h2>
					<div class="approval_txt">
						<span class="float_l" style="margin-top:3px">결과 총 <span>00</span>건</span>
						<dl class="float_l">
							<dt class="float_l ml20">총미승인액</dt>
							<dd class="float_l">000,000</dd>
							<dt class="float_l ml20">미승인액</dt>
							<dd class="float_l">72,456,456</dd>
						</dl>
					</div>					
				</th>
				<th><h2 class="pl20">참조사항</h2></th>
			</tr>
			<tr>
				<td>
					<div class="wrap_approval01 box_type01" style="margin-top:0">
					
					</div>
				</td>
				<td>
					<div class="table_appro_r">
										
						<div class="wrap_approval02 box_type01" style="margin-top:0">
							<table class="type01 re_style04">
								<colgroup>
									<col style="width:25%" />
									<col style="width:25%" />
									<col style="width:25%" />
									<col style="width:25%" />
								</colgroup>
								<tbody>
									<tr>
										<th class="no_border_l">주문일시</th>
										<td>2014-05-05 15:15:15</td>
										<th>거래처 담당자</th>
										<td class="no_border_r"></td>
									</tr>
									<tr>
										<th class="no_border_l">팀장승인</th>
										<td>2014-05-05 15:15:15</td>
										<th>판매처 담당자</th>
										<td class="no_border_r"></td>
									</tr>
									<tr>
										<th class="no_border_l">전월잔고</th>
										<td>000,000,000</td>
										<th>연대보증인</th>
										<td class="no_border_r"></td>
									</tr>
									<tr>
										<th class="no_border_l">금월판매</th>
										<td>000,000,000</td>
										<th>담보확보액</th>
										<td class="no_border_r"></td>
									</tr>
									<tr>
										<th class="no_border_l">금월수금</th>
										<td>-</td>
										<th>담보확보율</th>
										<td class="no_border_r">000,000,000</td>
									</tr>
									<tr>
										<th class="no_border_l">현잔고</th>
										<td>-</td>
										<th>담보종류</th>
										<td class="no_border_r">약속어음 外4</td>
									</tr>
									<tr>
										<th class="no_border_l">미도래(자수)</th>
										<td>00,000,000</td>
										<th>여신한도액</th>
										<td class="no_border_r">108.16%</td>
									</tr>
									<tr>
										<th class="no_border_l">미도래(타수)</th>
										<td>-</td>
										<th>미정리매출할인</th>
										<td class="no_border_r">10123</td>
									</tr>
									<tr>
										<th class="no_border_l no_border_b">총여신</th>
										<td class=" no_border_b">000,000,000</td>
										<th class="no_border_b">회전일</th>
										<td class="no_border_r no_border_b">160</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</td>
			</tr>
			<tr>
				<th>
					<h2>주문세부내역</h2>
					<div class="approval_txt">
						<dl class="float_l">
							<dt class="float_l ml20">공급가액</dt>
							<dd class="float_l">1,456,789</dd>
							<dt class="float_l ml20">세액</dt>
							<dd class="float_l">123,123</dd>
							<dt class="float_l ml20">공급총액</dt>
							<dd class="float_l">1,123,456</dd>
						</dl>
					</div>	
				</th>	
				<th><h2 class="pl20">담보약속내용</h2>	</th>
			</tr>
			<tr>
				<td>
					<div class="wrap_approval01 box_type01 h286" style="margin-top:0">
					
					</div>
				</td>
				<td>
					<div class="table_appro_r wrap_approval02" style="margin-top:0">
									
						<div class="wrap_approval02 box_type01" style="margin-top:0">
							<table class="type01 re_style05">
								<colgroup>
									<col style="width:22%" />
									<col style="width:22%" />
									<col style="width:12%" />
									<col style="width:22%" />
									<col style="width:22%" />
								</colgroup>
								<thead>
									<tr>
										<th class="no_border_l no_border_t">주문요청일</th>
										<th class="no_border_t">약속기일</th>
										<th class="no_border_t">상태</th>
										<th class="no_border_t">담보약속내용</th>
										<th class="no_border_r no_border_t">반려사유</th>
									</tr>
								</thead>
								<tbody>
									<tr>
										<td class="no_border_l">2014-05-05</td>
										<td>2014-05-05</td>
										<td>접수</td>
										<td>현금결재</td>
										<td class="no_border_r">반려</td>
									</tr>
									<tr>
										<td class="no_border_l">2014-05-05</td>
										<td>2014-05-05</td>
										<td>접수</td>
										<td>현금결재</td>
										<td class="no_border_r">반려</td>
									</tr>
									<tr>
										<td class="no_border_l">2014-05-05</td>
										<td>2014-05-05</td>
										<td>접수</td>
										<td>현금결재</td>
										<td class="no_border_r">반려</td>
									</tr>
									<tr>
										<td class="no_border_l">2014-05-05</td>
										<td>2014-05-05</td>
										<td>접수</td>
										<td>현금결재</td>
										<td class="no_border_r">반려</td>
									</tr>
									<tr>
										<td class="no_border_l">2014-05-05</td>
										<td>2014-05-05</td>
										<td>접수</td>
										<td>현금결재</td>
										<td class="no_border_r">반려</td>
									</tr>
									<tr>
										<td class="no_border_l">2014-05-05</td>
										<td>2014-05-05</td>
										<td>접수</td>
										<td>현금결재</td>
										<td class="no_border_r">반려</td>
									</tr>
									<tr>
										<td class="no_border_l">2014-05-05</td>
										<td>2014-05-05</td>
										<td>접수</td>
										<td>현금결재</td>
										<td class="no_border_r">반려</td>
									</tr>
									<tr>
										<td class="no_border_l no_border_b">2014-05-05</td>
										<td class="no_border_b">2014-05-05</td>
										<td class="no_border_b">접수</td>
										<td class="no_border_b">현금결재</td>
										<td class="no_border_r no_border_b">반려</td>
									</tr>
									<tr>
										<td class="no_border_l no_border_b">2014-05-05</td>
										<td class="no_border_b">2014-05-05</td>
										<td class="no_border_b">접수</td>
										<td class="no_border_b">현금결재</td>
										<td class="no_border_r no_border_b">반려</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>	
				</td>
			</tr>
			</tbody>
			</table>
		</div>	
	
		</div>
		<!-- ##### content end ##### -->
		
		</div>
	</div>
	
	<%@include file="../../include/footer.jsp"%>
</body>
</html>