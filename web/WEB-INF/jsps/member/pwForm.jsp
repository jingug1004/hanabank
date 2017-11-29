<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : pwForm.jsp
 * @메뉴명 : 비밀번호 변경 팝업    
 * @최초작성일 : 2014/10/29            
 * @author : 김재갑                 
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>하나제약 온라인 비밀번호 변경</title>
<%@include file="/include/head.jsp"%>
<script type="text/javascript">
	var passwordFlag = false;		//기존 비밀번호 확인 여부 
	
	/* 화면의 모든 DOM객체가 준비되었을 때 */
	$(document).ready(function(){
		
		$('#btnConfirm').on('click', function(e){passwordChange();});	//확인 버튼에 클릭 이벤트
		$('#btnClose').on('click', function(e){cancel();});				//닫기 버튼에 클릭 이벤트
		$('.close').on('click', function(e){cancel();});				//close 라는 class를 가진 객체들에게 클릭 이벤트
		
		/* 기존 비밀번호 텍스트 박스에 focusout 이벤트 발생시 입력된 값이 있으면  
		*  ajax로 기존 비밀번호를 DB에서 조회해서 입력된 값과 비교해서 맞다면 passwordFlag를 true로 변경한다. 
		*/
		$("#oldPassword").on('focusout', function(){	
			if($("#oldPassword").val().length > 0){
				$.ajax({
			           type:"POST",
			           /* data : "oldPassword="+$("#oldPassword").val(), */
			           data : {oldPassword : $("#oldPassword").val()},
			           url:"<%=ONLINE_ROOT%>/member/passwordCheck.do",
			           dataType:"json",
			           
			           success : function(data) {
			        	   if(data.resultcode == 1){
			        		   $("#newPassword").focus();
			        		   passwordFlag = true;
			        	   } else if(data.resultcode == 2){
			        		   alert("온라인 주문 회원 데이터가 존재하지 않습니다.\n전산팀에게 문의 하세요.");
			        		   $("#oldPassword").focus();
			        		   $("#oldPassword").val("");
			        		   passwordFlag = false;
			        	   }else{
			        		   alert("현재 사용중인 비밀번호와 입력한 현재 비밀번호가 다릅니다.");
			        		   $("#oldPassword").focus();
			        		   $("#oldPassword").val("");
			        		   passwordFlag = false;
			        	   }
			           },
			           error : function(xhr, status, error) {
			                 alert("장애가 발생하였습니다.\n잠시 후 다시 시도해주세요.");
			                 passwordFlag = false;
			           }
			     });	
			}
		});
		
	});
	
	/**
	*	비밀번호 변경
	*/
	function passwordChange(){
		
		var oldPassword = $("#oldPassword").val();
		var newPassword = $("#newPassword").val();
		var newRePassword =	$("#newRePassword").val();
		
		if(oldPassword == ""){
			alert("현재 비밀번호를 입력 하세요.");
			$("#oldPassword").focus();
			return;
		}
		
		if(newPassword == ""){
			alert("새 비밀번호를 입력 하세요.");
			$("#newPassword").focus();
			return;
		}
		
		/* if(!isValidFormPassword(newPassword)){
			$("#newPassword").val("");
			$("#newRePassword").val("");
			$("#newPassword").focus();
			return; 
		} */
		
		if(newRePassword == ""){
			alert("새 비밀번호 확인을 입력 하세요.");
			$("#newRePassword").focus();
			return;
		}
		
		if(newPassword != newRePassword){
			alert("입력한 두개의 패스워드가 일치 하지 않습니다.");
			$("#newPassword").focus();
			return;
		}
		
		if(oldPassword == newPassword){
			alert("현재 패스워드와 새로 입력한 패스워드가 같습니다.");
			$("#newPassword").focus();
			return;
		}
		
		if(passwordFlag){
			$.ajax({
		           type:"POST",
		           data : {oldPassword : oldPassword, newPassword : newPassword},
		           url:"<%=ONLINE_ROOT%>/member/updatePassword.do",
		           dataType:"json",
		           
		           success : function(data) {
		        	   if(data.resultcode > 0){
		        		   alert("비밀번호가 변경 되었습니다.");
		        		   pwFormClose();
		        	   }else if(data.resultcode == -2){ // 비밀번호 유효성 체크 false
		        		   alert(data.out_MSG);
		        		   $("#newPassword").val("");
		        		   $("#newRePassword").val("");
		        		   $("#newPassword").focus();
		        		   return;
		        	   }else{
		        		   alert("비밀번호가 변경되지 않았습니다.\n 잠시 후 다시 시도해주세요.");
		        		   return;
		        	   }
		           },
		           error : function(xhr, status, error) {
		                 alert("장애가 발생하였습니다.\n잠시 후 다시 시도해주세요.");
		           }
		     });
		}else{
			alert("현재 사용중인 비밀번호와 입력한 현재 비밀번호가 다릅니다.");
			$("#oldPassword").focus();
			return;
		}
	}
	
	/**
	 *	새 비밀번호의 규칙 체크
	 * @param pw	입력한 비밀번호
	 * @returns {Boolean}
	 */
	function isValidFormPassword(pw) {
		var regex = /^[a-zA-Z0-9]{6,20}$/;
		if (!regex.test(pw))     {
		    alert("6~20자의 영문 대소문자, 숫자만 혼용 사용할 수 있습니다.");
		    return false;
		}
		         
	    return true;
	}
	
	/**
	 * 비밀번호 팝업/레이어 닫기
	 */
	function pwFormClose(){
		if(opener){
			window.self.close();
		} else {
			parent.popPassChangelayerClose();
		}
	}
	
	/**
	 * 비밀번호 레이어일 경우 사이트 이용 제한 알림 띄우기
	 */
	function cancel(){
		if(opener){
			pwFormClose();
		} else {
			if(confirm("비빌번호를 변경하지 않으면 사이트 이용이 제한됩니다.")){
				parent.location.href = "<%=ONLINE_ROOT %>/member/logOut.do";
				parent.popPassChangelayerClose();
			}
		}
	}
</script>
</head>
<body>
	<div class="popup pw_bg" title="Main">
	<!-- ##### content start ##### -->
	<!-- window size : 500 * 400 -->
		<h1 class="tit">비밀번호 변경</h1>
		
		<div class="wrap_pw">
			<form name="loginFrm">
			<div class="pw h200">
				<dl>
					<dt>현재비밀번호</dt>
					<dd><input type="password" class="w115" name="oldPassword" id="oldPassword"/></dd>
					
					<dt>새비밀번호</dt>
					<dd><input type="password" class="w115" name="newPassword" id="newPassword"/></dd>
					
					<dt>새비밀번호확인</dt>
					<dd><input type="password" class="w115" name="newRePassword" id="newRePassword"/></dd>
				</dl>
				<p class="txt ta_c mt30">
					보안규정에 따라 공백없이 숫자,영문자,특수문자 포함 5 ~ 20 <br />
					자리로 입력해 주세요 <br /> <br /> 
					사용불가한 특수문자는  "  %  &  +  `  <  >  \  ^  {  |  } 입니다
				</p>
			</div>
			</form>
			<div class="wrap_confirm">
				<button id="btnConfirm">확인</button>
				<button id="btnClose">취소</button>
			</div>
		</div>
		
		<button class="close"><span class="blind">닫기</span></button>
	<!-- ##### content end ##### -->
	</div>
</body>
</html>