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
				<h2>권한조회</h2>
				<ul class="search_navi">
					<li class="active"><a href="authority_search_user.jsp">사용자</a></li>
					<li><a href="authority_search_program.jsp">프로그램</a></li>
				</ul>
				<div class="search_cont">
					<div class="float_l mr20">
						<h2>조회</h2>
						<div class="search_box">
							<div class="input_set float_l">
								<div>
									<span>사용자구분</span>
									<select>
										<option>사원</option>
										<option>대리</option>
										<option>과장</option>
										<option>차장</option>
									</select>
									<input type="text" />
								</div>
								<div class="code_box mt5">
									<span>사원코드</span>
									<input type="text" />
								</div>
							</div>
							<button class="float_r">검색</button>
						</div>
						<div class="author_search box_type01">
						
						</div>
					</div>
					<div class="float_l mr20">
						<h2>사용프로그램</h2>
						<div class="author_pro_box box_type01">
						
						</div>
					</div>
					<div class="float_l">
						<h2>사용권한</h2>
						<div class="author_box box_type01">
						
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