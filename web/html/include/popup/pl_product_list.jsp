<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../../include/head.jsp"%>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/placeholders.js"></script>
	
</head>
<body>
	<div class="popup" title="Main">
		
		<!-- ##### content start ##### -->
		<!-- window size : 800 * 675 -->
			<h1 class="tit">P/L용 제품리스트 관리</h1>
			
			<div class="wrap_pop_search">
				<div class="float_l">
					<label>구분1</label>
					<select class="w100">
						<option>전체</option>
						<option>전체</option>
					</select>
					
					<label class="ml10">구분2</label>
					<select class="w100">
						<option>전체</option>
						<option>전체</option>
					</select>
					
					<label class="ml10">제품명</label>
					<input type="text" class="w150" />
				</div>
				
				<div class="float_r">
					<button>조회</button>
					<button>입력</button>
					<button>저장</button>
					<button>삭제</button>
					<button>닫기</button>
				</div>
			</div>
			
			<div class="wrap_pl_list">
				<div class="box_type01 float_l w430 h451">
				
				</div>
				
				<div class="float_r wrap_tbl">
					<div class="box_type01">
						<table class="type01">
							<tr>
								<th class="no_border_t no_border_l">분류</th>
								<td class="no_border_t no_border_r">
									<select class="w80">
										<option>전문</option>
									</select>
									<select class="w100">
										<option>마약제제</option>
									</select>
								</td>
							</tr>
							<tr>
								<th class="no_border_l">제품</th>
								<td class="no_border_r">
									<input type="file" id="file" class="blind" onchange="fileNameInput()" />
									<input type="text" id="file_name" class="w230" />
									<button class="pl_file_btn">찾기</button>
								</td>
							</tr>
							<tr>
								<th class="no_border_l">사진</th>
								<td rowspan="2" class="no_border_r">
									<div class="wrap_photo">
										<img src="<%=ONLINE_ROOT%>/asset/img/tmp.gif" />
										<div class="wrap_photo_btn">
											<button>사진저장</button><br />
											<button>사진삭제</button>
										</div>
									</div>
								</td>
							</tr>
							<tr>
								<!-- td class="ta_c no_border_l">
									<input type="text" class="w50 ta_c" /><br />
									<button class="mt5 btn_help">도움말</button><br />
									<button class="pl_photo_btn mt5">사진폴더</button>
									<input type="file" id="photo" class="blind" onchange="photoNameInput()" />
								</td --> <!-- 201412118 수정 -->
							</tr>
							<tr>
								<td colspan="2" class="no_border_l no_border_r no_border_b pdrn">
									<input type="text" id="photo_name" class="w258" />
									<button>사진폴더</button><br />
								</td>
							</tr>
						</table>
					</div>
					<div class="box_type01">
						<table class="type01">
							<colgroup>
								<col width="25%"/>
								<col width="25%"/>
								<col width="25%"/>
								<col width="25%"/>
							</colgroup>
							<tr>
								<th class="no_border_t no_border_l">KD</th>
								<th class="no_border_t">보험약가</th>
								<th class="no_border_t">주성분·함량</th>
								<th class="no_border_t no_border_r">포장단위</th>
							</tr>
							<tr>
								<td class="no_border_b no_border_l">
									<textarea class="w68 ta_c"></textarea>
								</td>
								<td class="no_border_b">
									<textarea class="w68 ta_c"></textarea>
								</td>
								<td class="no_border_b">
									<textarea class="w68 ta_c"></textarea>
								</td>
								<td class="no_border_b no_border_r">
									<textarea class="w68 ta_c"></textarea>
								</td>
							</tr>
						</table>
					</div>
					<div class="wrap_pl_box">
						<div class="box_type01 float_l">
							<table class="type01">
								<colgroup>
									<col style="width:97px;" />
									<col style="width:;" />
									<col style="width:21px;" />
								</colgroup>
								<tr>
									<th colspan="3" class="no_border_t no_border_l no_border_r">적응중</th>
								</tr>
								<tr>
									<td colspan="3" class="no_border_l no_border_r">
										<textarea class="w143 h50"></textarea>
									</td>
								</tr>
								<tr>
									<th class="no_border_b no_border_l">출력글자크기</th>
									<td class="no_border_b">
										<input type="text" class="w25" />
									</td>
									<td class="no_border_b no_border_r pd0">
										<button class="btn_up"><span class="blind"></span></button><br />
										<button class="btn_down"><span class="blind"></span></button>
									</td>
								</tr>
							</table>
						</div>
						<div class="box_type01 float_r ml10">
							<table class="type01">
								<colgroup>
									<col style="width:97px;" />
									<col style="width:;" />
									<col style="width:21px;" />
								</colgroup>
								<tr>
									<th colspan="3" class="no_border_t no_border_l no_border_r">용법·용량</th>
								</tr>
								<tr>
									<td colspan="3" class="no_border_l no_border_r">
										<textarea class="w143 h50"></textarea>
									</td>
								</tr>
								<tr>
									<th class="no_border_b no_border_l">출력글자크기</th>
									<td class="no_border_b">
										<input type="text" class="w25" />
									</td>
									<td class="no_border_b no_border_r pd0">
										<button class="btn_up"><span class="blind"></span></button><br />
										<button class="btn_down"><span class="blind"></span></button>
									</td>
								</tr>
							</table>
						</div>
					</div>
					
					<table class="type01 mt10">
						<tr>
							<th>사용여부</th>
							<td>
								<input type="radio" name="used" />
								<label>사용함</label>
								<input type="radio" name="used" />
								<label>사용안함</label>
							</td>
						</tr>
					</table>
					
				</div>
			</div>
			
			
			
			<button class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
	</div>
	
	<%@include file="../../include/footer_pop.jsp"%>
</body>
</html>