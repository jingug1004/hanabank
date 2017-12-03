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
				<label>거래처</label>
				<input type="text" class="w80">
				<span>(약)원광메디칼써플라이어</span>
				
				<label class="ml10">거래구분</label>
				<select class="w100">
					<option>거래</option>
				</select>
				
				<div class="wrap_search_btn">
					<button class="btn_type01">검색</button>
				</div>
			</div>
		</div>	
		<div class="inner_cont">		
			<div class="wrap_btn_group mb10">
				<div class="btn_group ta_r">
					<button>인쇄</button>
					<button>엑셀</button>
					<button>닫기</button>
				</div>
			</div>		
			<div class="wrap_customer01">
				<p class="customer_num">결과 총 <span>000</span>건</p>
				<div class="customer_box01 box_type01">
				
				</div>
			</div>
			<ul class="customer_teb">
				<li><a href="#tab_customer01" class="active">개인정보</a></li>
				<li><a href="#tab_customer02">소속학회</a></li>
				<li><a href="#tab_customer03">가족관계</a></li>
				<li><a href="#tab_customer04">기념일</a></li>
				<li><a href="#tab_customer05">교우관계</a></li>
				<li class="last_menu"><a href="#tab_customer06">기타사항</a></li>
			</ul>
			<div class="tab_all tab_customer01" id="tab_customer01">
				<div class="customer_box01 box_type01">
				
				</div>
				<div class="re_style06 box_type01">
					<table class="table_info type01">
						<colgroup>
							<col style="width:9%" />
							<col style="width:16%" />
							<col style="width:9%" />
							<col style="width:16%" />
							<col style="width:9%" />
							<col style="width:16%" />
							<col style="width:9%" />
							<col style="width:16%" />
						</colgroup>
						<tbody>
							<tr>
								<th class="no_border_l no_border_t">고객</th>
								<td class="customer no_border_t">
									<input type="text" class="ctm01" />
									<input type="text" class="ctm02" />
								</td>
								<th class="no_border_t">성별</th>
								<td class="no_border_t">
									<input type="radio" id="man" name="sex" />
									<label for="man">남자</label>
									<input type="radio" id="woman" name="sex"/>
									<label for="woman">여자</label>
								</td>
								<th class="no_border_t">생년월일</th>
								<td class="input_full no_border_t">
									<input type="text" />
									<button class="btn_search"><span class="blind">찾기</span></button>
								</td>
								<th class="no_border_t">실제생일</th>
								<td class="input_full no_border_r no_border_t">
									<input type="text" />
									<button class="btn_search"><span class="blind">찾기</span></button>
								</td>
							</tr>
							<tr>
								<th class="no_border_l">종교</th>
								<td class="input_full">
									<input type="text" />
								</td>
								<th>결혼여부</th>
								<td>
									<input type="radio" id="man" name="sex" />
									<label for="man">남자</label>
									<input type="radio" id="woman" name="sex"/>
									<label for="woman">여자</label>
								</td>
								<th>결혼기념일</th>
								<td class="input_full">
									<input type="text" />
									<button class="btn_search"><span class="blind">찾기</span></button>
								</td>
								<th>자녀관계</th>
								<td class="input_full01 no_border_r">
									<input type="text" />
								</td>
							</tr>
							<tr>
								<th class="no_border_l">성향</th>
								<td class="input_full">
									<input type="text" />
								</td>
								<th>취미</th>
								<td class="input_full">
									<input type="text" />
								</td>
								<th>출신고교</th>
								<td class="input_full01">
									<input type="text" />
								</td>
								<th>출신대학</th>
								<td class="input_full01 no_border_r">
									<input type="text" />
								</td>
							</tr>
							<tr>
								<th class="no_border_l">금기사항</th>
								<td class="input_full">
									<input type="text" />
								</td>
								<th>전화번호</th>
								<td class="input_full">
									<input type="text" />
								</td>
								<th>핸드폰번호</th>
								<td class="input_full01">
									<input type="text" />
								</td>
								<th>팩스번호</th>
								<td class="input_full01 no_border_r">
									<input type="text" />
								</td>
							</tr>
							<tr>
								<th class="no_border_l">직위</th>
								<td class="input_full">
									<input type="text" />
								</td>
								<th>전문과</th>
								<td class="input_full">
									<input type="text" />
								</td>
								<th>수련병원</th>
								<td class="input_full01">
									<input type="text" />
								</td>
								<th>전공</th>
								<td class="input_full01 no_border_r">
									<input type="text" />
								</td>
							</tr>
							<tr>
								<th class="no_border_l">해외연수</th>
								<td class="input_full">
									<input type="text" />
								</td>
								<th>대인관계</th>
								<td class="input_full">
									<input type="text" />
								</td>
								<th>차량번호</th>
								<td class="input_full01">
									<input type="text" />
								</td>
								<th>차종/색상</th>
								<td class="input_full01 no_border_r">
									<input type="text" />
								</td>
							</tr>
							<tr>
								<th class="no_border_l">기타사항</th>
								<td class="input_full">
									<input type="text" />
								</td>
								<th>주거래도매</th>
								<td class="input_full">
									<input type="text" />
								</td>
								<th>메일주소</th>
								<td class="input_full02 no_border_r" colspan="3">
									<input type="text" />
								</td>
							</tr>
							<tr>
								<th class="no_border_l no_border_b">우편번호</th>
								<td class="input_full03 no_border_b">
									<input type="text" />
									<button class="btn_search"><span class="blind">찾기</span></button>
								</td>
								<th class="no_border_b">주소</th>
								<td class="input_full no_border_b">
									<input type="text" />
								</td>
								<th class="no_border_b">상세주소</th>
								<td class="input_full02 no_border_r no_border_b" colspan="3">
									<input type="text" />
								</td>
							</tr>							
						</tbody>
					</table>
				</div>
			</div>
			<div class="tab_all tab_customer02" id="tab_customer02">
				<div class="custom_box box_type01">
					<table>
						<colgroup>
							<col style="width:260px" />
							<col style="width:100%;" />
						</colgroup>
						<tbody>
							<tr>
								<td class="left_table"><div class="left_box">left</div></td>
								<td class="right_table"><div class="right_box">right</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>

			<div class="tab_all tab_customer03" id="tab_customer03">
				<div class="custom_box box_type01">
					<table>
						<colgroup>
							<col style="width:260px" />
							<col style="width:100%;" />
						</colgroup>
						<tbody>
							<tr>
								<td class="left_table"><div class="left_box">left</div></td>
								<td class="right_table"><div class="right_box">right</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tab_all tab_customer04" id="tab_customer04">
				<div class="custom_box box_type01">
					<table>
						<colgroup>
							<col style="width:260px" />
							<col style="width:100%;" />
						</colgroup>
						<tbody>
							<tr>
								<td class="left_table"><div class="left_box">left</div></td>
								<td class="right_table"><div class="right_box">right</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tab_all tab_customer05" id="tab_customer05">
				<div class="custom_box box_type01">
					<table>
						<colgroup>
							<col style="width:260px" />
							<col style="width:100%;" />
						</colgroup>
						<tbody>
							<tr>
								<td class="left_table"><div class="left_box">left</div></td>
								<td class="right_table"><div class="right_box">right</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="tab_all tab_customer06" id="tab_customer06">
				<div class="custom_box box_type01">
					<table>
						<colgroup>
							<col style="width:260px" />
							<col style="width:100%;" />
						</colgroup>
						<tbody>
							<tr>
								<td class="left_table"><div class="left_box">left</div></td>
								<td class="right_table"><div class="right_box">right</div></td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<!-- ##### content end ##### -->
		
		</div>
	</div>
	
	<%@include file="../../include/footer.jsp"%>
</body>
</html>