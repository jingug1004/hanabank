<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../include/head.jsp"%>
</head>
<body>
	<div class="wrap_header">
		<div class="header">
			<h1><a href="#"><img src="<%=ONLINE_ROOT%>/asset/img/logo.jpg" alt="" /></a></h1>
		</div>
	</div>
	
	<div class="wrap_con">
		<div class="wrap_login login_bg">
			<div class="login">
				<p class="slogan">
					For the Best Medicine, Better Life
				</p>
				<h1>
					하나제약 주식회사 <span>온라인결제</span>
				</h1>
				<p class="txt">
					하나제약 주식회사 온라인결제입니다.<br />
					온라인결제 시스템을 이용하시려면 로그인을 하시기 바랍니다.
				</p>
				<div class="wrap_log">
					<div class="wrap_input">
						<label>아이디</label> <input type="text" /> <br />
						<label>비밀번호</label> <input type="password" /> <br />
						<input type="checkbox" class="save_id_chk" /> <label class="save_id">아이디 저장</label>
					</div>
					<div class="wrap_btn">
						<button class="btn_login">LOGIN</button>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<%@include file="../include/footer.jsp"%>
</body>
</html>