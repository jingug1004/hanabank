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
					<li><a href="authority_search_user.jsp">사용자</a></li>
					<li class="active"><a href="authority_search_program.jsp">프로그램</a></li>
				</ul>
				<div class="search_cont">
					<div class="float_l mr20">
						<h2>전체 프로그램 메뉴</h2>
						<div class="author_pro_box box_type01">
							<div class="list_button" style="width:92px; margin:10px auto">
								<button class="type_01" onclick="javascript: d.openAll();">펼치기</button>
								<button class="type_01" onclick="javascript: d.closeAll();">접기</button>
							</div>
							<div class="mt10"></div>						
						</div>
					</div>
					<div class="float_l mr20">
						<h2>사용권한</h2>
						<div class="author_box box_type01">
						
						</div>
					</div>
					<div class="float_l">
						<h2>조회</h2>
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