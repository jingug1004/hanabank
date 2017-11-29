<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : roleDetailPopup.jsp      
 * @메뉴명 : 사원 조회 팝업      
 * @최초작성일 : 2015/01/15            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%@page import="java.net.URLDecoder"%>
<%@ include file ="/common/path.jsp" %>
<%
	String roleNo = StringUtil.nvl((String)request.getParameter("roleNo"),"");		// role no
	String type = StringUtil.nvl((String)request.getParameter("type"),"");			// 페이지 type
	String deptCode = StringUtil.nvl((String)request.getParameter("deptCode"),"");	// 부서 코드
	String deptName = StringUtil.nvl((String)request.getParameter("deptName"),"");	// 부서명
	String empText = URLDecoder.decode(StringUtil.nvl(request.getParameter("empText")), "UTF-8");	// 사용자 이름
	
	String pageTitle = "";	// 페이지 타이틀
	String col1Name = "";	// jqgrid 컬럼에1 노출 될 text
	String col2Name = "";	// jqgrid 컬럼에2 노출 될 text
	String col3Name = "";	// jqgrid 컬럼에3 노출 될 text
	
	boolean multiselectFlag = false;	// 체크박스 여부
	
	// 권한 등록관리의 사원 팝업인 경우
	if(type.equals("roleReg")){
		pageTitle = "사원조회";
		col1Name = "부서";
		col2Name = "사원코드";
		col3Name = "사원명";
		multiselectFlag = false;
	}else{
		pageTitle = "사용자 조회";
		col1Name = "부서";
		col2Name = "사용자 코드";
		col3Name = "사용자명";
		multiselectFlag = true;
	}
	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript">
		
		$(document).ready(function(){ 
			
			var lastsel2 = "";			// 선택 된 행
			var selectedEmpCode = "";	// 선택 된 사원,사용자 코드
			var selectedEmpName = "";	// 선택 된 사원,사용자 이름
			
			/**
			 * window dom load시 최초 호출되는 jqgrid
			 */
			$("#userList").jqGrid({
				
				url : "<%=ONLINE_ROOT%>/mgmt/common/userPopup.do",
				
				// 요청방식
				mtype:"post",
				// 결과물 받을 데이터 타입
				datatype:"json",
			
				// 컬럼명
				colNames:["<%=col1Name%>","<%=col2Name%>","<%=col3Name%>"],
				
				colModel:[
							{name:"dept_name",			index:"dept_name",			align:"left"},					// 부서
							{name:"emp_code",			index:"emp_code",			align:"left", key:true},		// 사원코드 
							{name:"emp_name",			index:"emp_name",			align:"left"},					// 사원명
				
						 ],
				// 그리드 캡션
				height:416,
				
				rowNum: -1,	
				
				//기본 정렬 값 -> 매개변수이름은 sidx(컬럼), sord(차순)로 요청된다
				sortname :'dept_name',
				sortorder:'desc',
				autowidth:true,
				multiselect: <%=multiselectFlag%>,
				
				// 선택된 행의 데이터 가져오기.
				onSelectRow: function(id){
				
					if(id && id!==lastsel2){
						
						$('#userList').restoreRow(lastsel2);
				    	lastsel2=id;
				    	
				    	var selectedList = $("#userList").getRowData(lastsel2);
				    	
				    	selectedEmpCode = selectedList.emp_code;		// 사원코드 셋팅
				    	selectedEmpName = selectedList.emp_name;		// 사원명 셋팅
					}
				
				},
				
			});
			
			/**
			 * window resize
			 */
			$(window).resize(function(){
				$("#userList").setGridWidth($('pop_grid box_type01 h440').width() , false);
			});
			
			// 추가 버튼 클릭
			$("#uc_save").bind("click",function(){
				
				var empCode = $("#userList").getGridParam('selarrrow');
				var roleNo = "<%=roleNo%>";		
				var type = "<%=type%>";
				var locationUrl = "";		// 호출 될 url
				
				if(type == "roleReg"){
					opener.userSet(selectedEmpCode,selectedEmpName);
					self.close();
				}else{
					
					if(empCode == ""){
						alert("선택 된 사용자가 없습니다.");
						return;
					}
					
					if(type == "inquireRole"){
						locationUrl = "<%=ONLINE_ROOT %>/mgmt/userRoleCopyAjax.do?empCode="+empCode;
					}else{
						locationUrl = "<%=ONLINE_ROOT %>/mgmt/insertUserRoleAjax.do?empCode="+empCode;
					}
					
					$.ajax({
						type : "POST"
						    	, url : locationUrl
						    	, dataType : "json"
						    	, data : {roleNo:roleNo}
						, success : function(data) {
							if(data.resultCode > 0){
								if(type == "inquireRole"){
									alert("선택 된 사용자에 <%=empText%>의 Role이 추가 되었습니다.");
									self.close();
								}else{
									alert("추가 되었습니다.");
									self.close();
								}
								opener.regUserListAjax('<%=roleNo%>');
							}
						}
						, error : function(request, status, error) {
							alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
							self.close();
						}
					});
				}
			});
				
		});
		
		/**
		 * 조회 버튼 클릭 : 코드,이름 검색 조건이 있는경우와 없는 경우 사용자 조회	
		 * @param jqgrid			selectedType
		 * @param jqgrid			code
		 * @param jqgrid			name
		 * @param request			type
		 * @return jqgrid			userList
		 */
		function search(){
			
			$("#userList").clearGridData(); 	// 이전 그리드 삭제
			$("#userList").setGridParam({
				url : "<%=ONLINE_ROOT%>/mgmt/common/userPopupGridList.do",
				postData : {
					selectedType:$("#selectedType").val(),		// type
					code:$("#code").val(),						// 사원,사용자 코드
					name:$("#name").val(),						// 사원,사용자 명
					pageType:"<%=type%>"						
				}
			}).trigger("reloadGrid");
	
		}
		
		/**
		 * window load 권한 조회의 사용자 팝업일 경우
		 */
		$(window).load(function(){
			<%if("inquireRole".equals(type)){%>
				var empText = "<%=empText%>";
				$("#typeTitle").text(empText + "의 Role 복사");
			<%}%>
		});
		
	</script>
</head>
<body onkeydown="javascript:if(event.keyCode==13){search(); return false;}">
	<div class="popup" title="Main">
		<!-- ##### content start ##### -->
		<!-- window size : 480 * 655 -->
			<h1 class="tit" id="typeTitle"><%=pageTitle %></h1>
			<div class="wrap_pop_search">
				<select name="selectedType" id="selectedType">
					<option value="dept" <%if("inquireRole".equals(type)){%> selected <%}%>>부서</option>
					<option value="emp">사원/거래처</option>
				</select>
					<input type="text" class="w100" id="code" name="code" value="<%=deptName %>" />
					<input type="text" class="w100" id="name" name="name" value="<%=deptCode %>" />
				<div class="wrap_pop_search_btn">
					<button onclick="javascript:search();">조회</button>
					<button id="uc_save">확인</button>
					<button id="uc_close" onclick="self.close();">취소</button>
				</div>
			</div>
			<div class="pop_grid box_type01 h440">
				<table id="userList"></table>
			</div>
			<button class="close"><span class="blind">닫기</span></button>
		<!-- ##### content end ##### -->
	</div>
	<%@include file="/include/footer_pop.jsp"%>
</body>
</html>