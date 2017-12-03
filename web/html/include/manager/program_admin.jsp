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
			<div class="wrap_program_admin">
				<div class="float_l mr10">
					<h2>전체 프로그램 메뉴</h2>
					<div class="program_box box_type01">
						<div class="list_button mtn" style="width:92px; margin:10px auto">
							<button class="type_01" onclick="javascript: d.openAll();">펼치기</button>
							<button class="type_01" onclick="javascript: d.closeAll();">접기</button>
						</div>
						<div class="mt10"></div>
					</div>				
				</div>
				<div class="float_l mr10">
					<div>
						<h2>Program</h2>
						<div class="program_detail box_type01">
							<table class="type01 re_style">
								<colgroup>
									<col style="width:30%" />
									<col style="width:70%" />
								</colgroup>
								<tbody>
									<tr>
										<th class="no_border_l">PGM NO.</th>
										<td class="no_border_r">
											<input type="text" />
										</td>
									</tr>
									<tr>
										<th class="no_border_l">PGM ID</th>
										<td class="no_border_r">
											<input type="text" />
										</td>
									</tr>
									<tr>
										<th class="no_border_l">PGM Name</th>
										<td class="no_border_r">
											<input type="text" />
										</td>
									</tr>
									<tr>
										<th class="no_border_l">PGM 구분</th>
										<td class="no_border_r">
											<select>
												<option>Program</option>
												<option>Program01</option>
												<option>Program02</option>
											</select>
										</td>
									</tr>
									<tr>
										<th class="no_border_l">사용여부</th>
										<td class="no_border_r">
											<input type="checkbox" id="check_pro"/>
											<label for="check_pro">프로그램</label>
											<input type="checkbox" id="check_menu" />
											<label for="check_menu">메뉴</label>
										</td>
									</tr>
									<tr>
										<th class="no_border_l">상위메뉴</th>
										<td class="no_border_r">
											<input type="text" />
										</td>
									</tr>
									<tr>
										<th class="no_border_l">정렬순서</th>
										<td class="no_border_r">
											<input type="text" />
										</td>
									</tr>
									<tr>
										<th class="no_border_l">아이콘</th>
										<td class="no_border_r">
											<select>
												<option>sample</option>
												<option>sample01</option>
												<option>sample02</option>
											</select>
										</td>
									</tr>
									<tr>
										<th class="no_border_l no_border_b">선택 아이콘</th>
										<td class="no_border_r no_border_b">
											<select>
												<option>sample</option>
												<option>sample01</option>
												<option>sample02</option>
											</select>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="manager_btn_group">
							<div class="ta_r">
								<button>추가</button>						
								<button>삭제</button>
								<button>저장</button>							
							</div>
						</div>
					</div>
					<div class="mt8">
						<h2>Button List</h2>
						<div class="botton_list box_type01">
							
						</div>
						<div class="manager_btn_group">
							<div class="ta_r">
								<button>프로그램버튼Sync</button>
								<button>추가</button>						
								<button>삭제</button>
								<button>저장</button>							
							</div>
						</div>
					</div>
				</div>
				<div class="float_l">
					<h2>사용 프로그램 미리보기</h2>
					<div class="program_box box_type01">
					
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