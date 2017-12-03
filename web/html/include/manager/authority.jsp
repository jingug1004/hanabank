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
			<div class="wrap_authority">
				<div class="role_admin_box float_l mr10">
					<div>
						<h2>Role 등록</h2>
						<div class="box_type01 author_box00">
							<table class="type01 re_style02">
								<colgroup>
									<col style="width:30%" />
									<col style="width:70%" />
								</colgroup>
								<tbody>
									<tr>
										<th class="no_border_l">Role NO.</th>
										<td class="no_border_r">00010</td>
									</tr>
									<tr>
										<th class="no_border_l">Role 구분코드</th>
										<td class="no_border_r">
											<select>
												<option>가상조직</option>
												<option>가상조직01</option>
												<option>가상조직02</option>
											</select>
										</td>
									</tr>
									<tr>
										<th class="no_border_l">Role명</th>
										<td class="no_border_r">
											<input type="text" />
										</td>
									</tr>
									<tr>
										<th class="no_border_l">부서</th>
										<td class="no_border_r">
											<input type="text" class="resize_w01"/>
											<input type="text" class="resize_w02" />
										</td>
									</tr>
									<tr>
										<th class="no_border_l no_border_b">사원</th>
										<td class="no_border_r no_border_b">
											<input type="text" class="resize_w01" />
											<input type="text" class="resize_w02" />
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
					<div class="mt25">
						<div class="box_type01 author_box01">
						
						</div>
					</div>
					<div class="manager_btn_group">
						<div class="ta_r">
							<button>Role복사</button>
							<button>추가</button>						
							<button>삭제</button>
							<button>저장</button>							
						</div>
					</div>					
				</div>
				<div class="role_admin_box float_l mr10">
					<h2>Role사용자 등록</h2>
					<div class="box_type01 author_box02">
					
					</div>
					<div class="manager_btn_group">
						<div class="ta_r">
							<button>추가</button>						
							<button>삭제</button>
						</div>
					</div>	
				</div>
				<div class="role_admin_box float_l">
					<h2>Role프로그램 등록</h2>
					<div class="box_type01 author_box02">
					
					</div>
					<div class="manager_btn_group">
						<div class="ta_r">
							<button>등록관리</button>							
						</div>
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