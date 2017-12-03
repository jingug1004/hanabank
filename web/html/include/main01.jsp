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
			<div class="main w967 m0auto">
				<h2>기본사항</h2>
				<div class="box_type02">
					<table class="type01 ta_c">
						<thead>
							<tr>
								<th class="no_border_l no_border_t">거래처코드</th>
								<th class="no_border_t">거래처명</th>
								<th class="no_border_t">사업자 번호</th>
								<th class="no_border_t">주소</th>
								<th class="no_border_t">대표자명</th>
								<th class="no_border_r no_border_t">계산서 이메일</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="no_border_l no_border_b">1100230</td>
								<td class="no_border_b">지오영(주)</td>
								<td class="no_border_b">110111-2533910</td>
								<td class="no_border_b">서울특별시 서대문구 연희동 421-1</td>
								<td class="no_border_b">조선혜</td>
								<td class="no_border_r no_border_b">tax@geo-young.kr</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<h2 class="mt30">여신현황<button>현재현황보기</button></h2>
				<div class="box_type02">
					<table class="type01 ta_c">
						<colgroup>
							<col style="width:160px;" />
							<col />
							<col style="width:160px;" />
							<col />
							<col style="width:160px;" />
							<col />
						</colgroup>
						<tr>
							<th class="no_border_l no_border_t">전월잔고</th>
							<td class="no_border_t">51,597,409</td>
							<th class="no_border_t">총여신</th>
							<td class="no_border_t">235,886,924</td>
							<th class="no_border_t">미정리매출</th>
							<td class="no_border_r no_border_t"></td>
						</tr>
						<tr>
							<th class="no_border_l">금월판매</th>
							<td>27,289,515</td>
							<th>연대보증인</th>
							<td></td>
							<th>회전일</th>
							<td class="no_border_r">42</td>
						</tr>
						<tr>
							<th class="no_border_l">금일수금</th>
							<td></td>
							<th>담보확보액</th>
							<td>80,000,000</td>
							<th>거래처 담당자</th>
							<td class="no_border_r">이상섭</td>
						</tr>
						<tr>
							<th class="no_border_l">현잔고</th>
							<td></td>
							<th>담보확보율</th>
							<td></td>
							<th>판매처 담당자</th>
							<td class="no_border_r">도매로컬</td>
						</tr>
						<tr>
							<th class="no_border_l">미도래(자수)</th>
							<td>157,000,000</td>
							<th>담보종류</th>
							<td>약속어음 外 3</td>
							<th></th>
							<td class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l no_border_b">미도래(타수)</th>
							<td class="no_border_b">3,500,000</td>
							<th class="no_border_b">여신한도액</th>
							<td class="no_border_b"></td>
							<th class="no_border_b"></th>
							<td class="no_border_r no_border_b"></td>
						</tr>
					</table>
				</div>
				
				<h2 class="mt30">년간 주문 현황</h2>
				<div class="wrap_year_order">
					<div class="wrap_graph">
						<div class="search mt10">
							<label>년도</label>
							<input type="text" class="w100 ta_r" />
							<button>검색</button>
						</div>
						
						<div class="graph">
							
						</div>
					</div>
					<div class="year_table">
						<table class="type01 ta_c">
							<colgroup>
								<col style="width:50%;" />
								<col style="width:50%;" />
							</colgroup>
							<thead>
								<tr>
									<th class="no_border_l no_border_t">월</th>
									<th class="no_border_r no_border_t">금액 (원)</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="no_border_l">1月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
								<tr>
									<td class="no_border_l">2月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
								<tr>
									<td class="no_border_l">3月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
								<tr>
									<td class="no_border_l">4月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
								<tr>
									<td class="no_border_l">5月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
								<tr>
									<td class="no_border_l">6月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
								<tr>
									<td class="no_border_l">7月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
								<tr>
									<td class="no_border_l">8月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
								<tr>
									<td class="no_border_l">9月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
								<tr>
									<td class="no_border_l">10月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
								<tr>
									<td class="no_border_l">11月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
								<tr>
									<td class="no_border_l">12月</td>
									<td class="no_border_r">123,456,789</td>										
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<th class="no_border_l no_border_b">합계</th>
									<td class="no_border_r no_border_b">1,040,740,734</td>										
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
				
				<!-- 12.11 + 더보기 링크 추가 수정 -->
				<div class="wrap_notice_list">
					<h2 class="mt30">공지사항</h2>
					<div class="box_type02">
						<table class="notice">
							<colgroup>
								<col style="width:730px;" />
								<col />
								<col />
							</colgroup>
							<tr>
								<td class="title no_border_t"><a href="#" class="fc_b">전체/제품리스트 신청합니다. (3)</a></td>
								<td class="no_border_t">2014-05-05</td>
								<td class="no_border_t">이정민</td>
							</tr>
							<tr>
								<td class="title"><a href="#" class="fc_b">명함신청합니다. (0)</a></td>
								<td>2014-05-05</td>
								<td>이정민</td>
							</tr>
							<tr>
								<td class="title"><a href="#" class="fc_b">전체/제품리스트 신청합니다. (3)</a></td>
								<td>2014-05-05</td>
								<td>이정민</td>
							</tr>
							<tr>
								<td class="title"><a href="#" class="fc_b">명함신청합니다. (0)</a></td>
								<td>2014-05-05</td>
								<td>이정민</td>
							</tr>
							<tr>
								<td class="title"><a href="#">전체/제품리스트 신청합니다. (3)</a></td>
								<td>2014-05-05</td>
								<td>이정민</td>
							</tr>
						</table>
					</div>
					<p class="more"><a href="#">+ 더 보기</a></p>
				</div>
				<!-- 12.11 + 더보기 링크 추가 수정 //-->
			</div>
		</div>
		<!-- ##### content end ##### -->
		
		</div>
	</div>
	
	<%@include file="../include/footer.jsp"%>
</body>
</html>