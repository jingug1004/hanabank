<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : loginForm.jsp
 * @메뉴명 : 로그인 화면    
 * @최초작성일 : 2014/10/29            
 * @author : 김재갑                 
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%
	//http:// 로 들어왔을 경우 https://로 redirect
	String url = request.getRequestURL().toString();

	//http:// 로 시작하고, 운영 url일 경우에만 redirect
	if( url.startsWith("http://") && ( url.indexOf("saleon.") > 1) ) {
		response.sendRedirect(HTTPS_ONLINE_ROOT + "/member/login.do");
	}
%>
<html lang="ko">
<head>
	<%@include file="/include/headLoginForm.jsp"%>
<title>하나제약 온라인주문</title>
<script type="text/javascript">
	/**
	*	로그인 
	*/
	function goLogin(){
		
		var empCode = $("#empCode").val();
		var password = $("#password").val();
		
		if(empCode == ""){
			alert("아이디를 입력 하세요.");
			$("#empCode").focus();
			return;
		}
		
		if(password == ""){
			alert("비밀번호를 입력 하세요.");
			$("#password").focus();
			return;
		}
		
		saveid();
		
		$.ajax({
	           type:"POST",
	           //data : "empCode="+empCode+"&password="+password,
	           data : {empCode : empCode, password : password},
	           url:"<%=HTTPS_ONLINE_ROOT%>/member/getLogin.do",
	           dataType:"json",
	           
	           success : function(data) {
	        	   	//console.log(data.resultcode);
	        	   	if(data.resultcode != 1){
	        			if(data.resultcode == 2){
	        				alert("온라인 주문 회원 데이터가 존재하지 않습니다.\n영업관리부에 문의 하세요.");
	        	   		}else if(data.resultcode == 3){
		        			alert("비밀번호가 틀립니다."); 
		        			$("#password").val("");
		        			$("#password").focus();
	        	   		}else if(data.resultcode == 4){
		        			alert("중지거래처 입니다.\n영업관리부에 문의 하세요.");  
		       			}else{
	        				alert("ID 또는 Password를 잘못 입력하셨습니다.");
		        			$("#empCode").val("");
		        			$("#password").val("");
		        			$("#empCode").focus();
	        		  	}
	        	   }else{
	        		   location.href = "<%=ONLINE_ROOT%>/main.do"; 
	        	   }
	           },
	           error : function(xhr, status, error) {
	                 alert("장애가 발생하였습니다.\n잠시 후 다시 시도해주세요." + error);
	           }
	     });

	}
	
	/**
	*	cookie에 로그인 아이디 저장하기
	*/
	function saveid() {
		if ($("input:checkbox[id='checksaveid']").is(":checked")) {	//쿠키 저장
			Commons.setCookie("saveid", $("#empCode").val(), 1);	// 기본적으로 1일동안 기억하게 함. 일수를 조절하려면 * 1에서 숫자를 조절하면 됨
		} else {
			Commons.setCookie("saveid", $("#empCode").val(), -1);	//쿠키 삭제
		}
	}
	
	/* 화면의 모든 객체가 로드 되었을 때 */
	$(window).load(function() { 
		
		var idCookie = Commons.getCookie("saveid");
		
		if($.trim(idCookie) != ""){
			$("#empCode").val(idCookie);
			$("#checksaveid").attr('checked',true);
		}
		
	});
	
</script>
</head>
<body onkeydown="javascript:if(event.keyCode==13){goLogin(); return false;}">
	<div class="wrap_header">
		<div class="header">
			<h1><a href="#"><img src="<%=HTTPS_ONLINE_ROOT%>/asset/img/logo.jpg" alt="" /></a></h1>
		</div>
	</div>
	<div class="wrap_con">
		<div class="wrap_login login_bg">
			<div class="login">
				<p class="slogan">
					For the Best Medicine, Better Life
				</p>
				<h1>
					하나제약 주식회사 <span>온라인주문</span>
				</h1>   
				<p class="txt">
					하나제약 주식회사 온라인주문입니다.<br />
					온라인주문 시스템을 이용하시려면 로그인을 하시기 바랍니다.
				</p>
				<div class="wrap_log">
					<div class="wrap_input">
						<label>아이디</label> <input type="text" name="empCode" id="empCode" /> <br />
						<label>비밀번호</label> <input type="password" name="password" id="password" /> <br />
						<input type="checkbox" class="save_id_chk" name="checksaveid" id="checksaveid" onClick="javascript:saveid();" /> <label for="checksaveid" class="save_id">아이디 저장</label>
					</div>
					<div class="wrap_btn">
						<button type="button" class="btn_login" onClick="javascript:goLogin();">LOGIN</button>
					</div>
				</div>
				<font color="red">사용가능 브라우저<br />Internet Explorer8이상,Chrome,Firefox (Chrome 사용권장)</font>
			</div>
		</div>
	</div>
	<%@include file="/include/footer.jsp"%>
</body>
</html>
