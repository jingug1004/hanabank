<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../../include/head.jsp"%>
</head>
<body>
	<div class="popup pw_bg" title="Main">
		
		<!-- ##### content start ##### -->
		<!-- window size : 500 * 460 -->
			<h1 class="tit">비밀번호 변경</h1>
			
			<div class="wrap_pw">
				<div class="pw h200">
					<dl>
						<dt>현재비밀번호</dt>
						<dd><input type="text" class="w120" /></dd>
						
						<dt>새비밀번호</dt>
						<dd><input type="text" class="w120" /></dd>
						
						<dt>새비밀번호확인</dt>
						<dd><input type="text" class="w120" /></dd>
					</dl>
					<p class="txt ta_c mt30">
						6~20자의 영문 대소문자, 숫자, 특수문자<br />
						혼용사용할 수 있습니다.
					</p>
				</div>
				
				<div class="wrap_confirm">
					<button>확인</button>
					<button>취소</button>
				</div>
			</div>
			
			<button class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
	</div>
	<%@include file="../../include/footer_pop.jsp"%>
</body>
</html>