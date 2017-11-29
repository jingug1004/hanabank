<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : regAuthority.jsp
 * @메뉴명 : MANAGER > 권한 등록관리    
 * @최초작성일 : 2015/01/16            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%@ page import="com.hanaph.saleon.mgmt.vo.MgmtRoleVO"%>
<%@ page import="com.hanaph.saleon.common.utils.StringUtil"%>
<%@ page import="java.util.List"%>
<%@ include file ="/common/path.jsp" %>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript">
		
		var topRoleNo = "";
		var roleNo = "";
		var lastsel2 = "";
		var topSel = "";
		var selectedUserCode = "";
		var selectedPgmNo = "";
		var addDataRecode = 0;
		var roleTitle = "";
		
		/**
		 * window dom load시 최초 호출되는 role목록 jqgrid
		 */
		$(document).ready(function(){
			
			$("#roleList").jqGrid({
				
				url : "<%=ONLINE_ROOT%>/mgmt/regRoleListAjax.do",
				// 요청방식
				mtype:"post",
				// 결과물 받을 데이터 타입
				datatype:"json",
				// 컬럼명
				colNames:["Role No","구분코드","Role 명","부서","사원","dept_code","emp_code"],
				colModel:[
							{name:"role_no",			index:"role_no",			align:"left", width:46},		// 롤 코드
							{name:"role_cat_code",		index:"role_cat_code",		align:"left", width:50},		// 롤 카테고리 코드
							{name:"role_name",			index:"role_name",			align:"left", width:90},		// 롤 이름
							{name:"dept_name",			index:"dept_name",			align:"left", width:50},		// 부서 이름
							{name:"emp_name",			index:"emp_name",			align:"left", width:50},		// 사원 이름
							{name:"dept_code",			index:"dept_code",			align:"left", hidden:true},		// hidden 부서 코드
							{name:"emp_code",			index:"emp_code",			align:"left", hidden:true}		// hidden 사원 코드
				
						 ],
				// 그리드 캡션
				height:381,
				
				rowNum: -1,		  // 페이지 출력 글 수
				//기본 정렬 값 -> 매개변수이름은 sidx(컬럼), sord(차순)로 요청된다
				sortname :'role_no',
				sortorder:'desc',
				//autowidth:true,
				
				loadComplete: function(data){
					if(data.records > 0){
						oriDataRecode = data.records;			// role 총 건수
						addDataRecode = data.records +1;		 
						topRoleNo = data.rows[0].role_no;		 
						topSel = addDataRecode-oriDataRecode;	 
						
						if($("#rowids").val() != ""){
							$("#roleList").jqGrid('setSelection', $("#rowids").val());
						}else{
							$("#roleList").jqGrid('setSelection', 1);
						}
						
						if($("#roleNo").val() != ""){
							regUserListAjax($("#roleNo").val());
							regPgmListAjax($("#roleNo").val());
						}else{
							$("#roleNo").val(topRoleNo);
							regUserListAjax(topRoleNo);
							regPgmListAjax(topRoleNo);
						}
						
						roleTitle = "Role별 프로그램 등록 ("+data.rows[0].role_name+")";
						$("#roleTitle").text(roleTitle);
						
					}
				},
				
				// 선택된 행의 데이터 가져오기.
				onSelectRow: function(id){
					
					if(id && id!==lastsel2){
						$('#roleList').restoreRow(lastsel2);
					}
					
					lastsel2=id;
			    	
			    	var selectedList = $("#roleList").getRowData(lastsel2);
			    	
			    	$("#rowids").val(lastsel2);						// 선택된 행 id셋팅
			    	$("#roleNo").val(selectedList.role_no);			// 선택된 행 role no셋팅
			    	$("#roleNoTxet").text(selectedList.role_no);	// 선택된 행 role text셋팅
			    	$("#roleName").val(selectedList.role_name);		// 선택된 행 role명 셋팅
			    	$("#deptName").val(selectedList.dept_name);		// 선택된 행 부서명 셋팅
			    	$("#deptCode").val(selectedList.dept_code);		// 선택된 행 부서코드 셋팅
			    	$("#empName").val(selectedList.emp_name);		// 선택된 행 사원명 셋팅	
			    	$("#empCode").val(selectedList.emp_code);		// 선택된 행 사원코드 셋팅
			    	
			    	regUserListAjax(selectedList.role_no);			// 선택 된role의 role별 사용자 jqgrid호출
			    	regPgmListAjax(selectedList.role_no);			// 선택 된role의 role별 프로그램 등록 jqgrid호출
			    	
			    	roleNo = selectedList.role_no;
			    	
			    	roleTitle = "Role별 프로그램 등록 ("+selectedList.role_name+")";
			    	$("#roleTitle").text(roleTitle);
				
				},
				
			});
			
			if(roleNo == ""){
				roleNo = topRoleNo;
			}
			
			/**
		 	 * Role 복사 버튼 클릭
		 	 */
			$("#lb_copy").on("click",function(){
				insertRoleCopy();
			});
			
			/**
		 	 * 추가 버튼 클릭
		 	 */
			$("#lb_add").on("click",function(){
				
				if(roleCheck()){
					procRole("insert");	
				}
				
				$("#roleProgramList").clearGridData(); 	// 이전 그리드 삭제
				$("#roleUserList").clearGridData(); 	// 이전 그리드 삭제
				
			});
			
			/**
		 	 * 삭제 버튼 클릭
		 	 */
			$("#lb_delete").on("click",function(){
				
				var rowid = $("#rowids").val();
					
				if(rowid == ""){
					alert("Role No를 선택 해주세요.");
					return;
				}else{
					if(confirm("해당 Role을 삭제 하시겠습니까?")){
						//행삭제
						$("#roleList").delRowData(rowid);
						// 데이터 삭제
						deleteRole();
					}	
				}
			});
			
			/**
		 	 * 저장 버튼 클릭
		 	 */
			$("#lb_save").on("click",function(){
				
				if(roleCheck()){ 
					procRole("save");	
				}
				
			});
				
			/**
		 	 * 등록관리 버튼 클릭
		 	 */
			$("#rb_rpconfig").on("click",function(){
				Commons.popupOpen("roleProgramPopup","<%=ONLINE_ROOT%>/mgmt/common/roleProgramPopup.do?roleNo="+roleNo,"1020","770");
			});
			
			/**
		 	 * 롤 사용자 추가 클릭
		 	 */
			$("#cb_add").on("click",function(){
				Commons.popupOpen("userPopup","<%=ONLINE_ROOT%>/mgmt/common/userPopup.do?roleNo="+roleNo,"530","675");
			});
			
			/**
		 	 * 롤 사용자 삭제 클릭
		 	 */
			$("#cb_delete").on("click",function(){
				if(selectedUserCode == ""){
					alert("사용자를 선택 해주세요.");
					return;
				}
				
				if(confirm("선택된 사용자를 삭제 하시겠습니까?")){
					
					var empCode = selectedUserCode;
					
					$.ajax({
						type : "POST"
						       	, async : true
						    	, url : "<%=ONLINE_ROOT %>/mgmt/deleteUserRoleAjax.do"
						    	, dataType : "json"
								, data : {empCode:empCode,roleNo:roleNo}
						, success : function(data) {
							if(data.resultCode > 0){
								alert("삭제 되었습니다.");
								// 파라미터 셋팅
								var param = "roleNo="+roleNo;
								
								$("#roleUserList").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/mgmt/regUserListAjax.do?" + param}).trigger("reloadGrid");
								selectedUserCode = "";
							}
						}
						, error : function(request, status, error) {
							if(request.status == '403'){Commons.sessionOut('A');}
						}
					});
				}
			});
				
		});
		
		/**
		 * window resize함수
		 */
		$(window).resize(function(){
			$("#roleList").setGridWidth($('.author_box01').width() , false);
			$("#roleUserList").setGridWidth($('.author_box02').width() , false);
			$("#roleProgramList").setGridWidth($('.author_box02').width() , false);
		});
		
		/**
		 * role 선택 시 호출 되는 role별 사용자 jqgrid 함수
		 * @param roleNo		role no
		 */
		function regUserListAjax(roleNo){
				
				if(roleNo == ""){
					roleNo = topRoleNo;
				}
				
				// 파라미터 셋팅
				var param = "roleNo="+roleNo;
				
				$("#roleUserList").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/mgmt/regUserListAjax.do?" + param}).trigger("reloadGrid");
				$("#roleUserList").jqGrid({
					
					url : "<%=ONLINE_ROOT%>/mgmt/regUserListAjax.do?roleNo="+roleNo,
					// 요청방식
					mtype:"post",
					// 결과물 받을 데이터 타입
					datatype:"json",
					// 컬럼명
					colNames:["Role No","부서","사원","emp_code"],
					colModel:[
								{name:"role_no",		index:"role_no",			align:"left", width:46},				// role no
								{name:"dept_name",		index:"dept_name",			align:"left", width:115},
								{name:"emp_name",		index:"emp_name",			align:"left", width:100},
								{name:"emp_code",		index:"emp_code",			align:"left", hidden:true}
					
							 ],
					rowNum: -1,		  // 페이지 출력 글 수
					rownumbers: true, // 줄번호 보이기
					rownumWidth : 30,
					
					height:546,		  // 그리드 캡션
				
					//기본 정렬 값 -> 매개변수이름은 sidx(컬럼), sord(차순)로 요청된다
					sortname :'emp_name',
					sortorder:'asc',
					
					//autowidth:true,
					
					loadComplete: function(data){
						if(topRoleNo != "" && roleNo == ""){
							regUserListAjax(topRoleNo);
						}
						
						if(data.records > 0){
							selectedUserCode = data.rows[0].emp_code;
						}else{
							selectedUserCode = "";
						}
					},
					
					// 선택된 행의 데이터 가져오기.
					onSelectRow: function(id){
					
						if(id && id!==lastsel2){
							
							$('#roleUserList').restoreRow(lastsel2);
					    	lastsel2=id;
					    	
					    	var selectedList = $("#roleUserList").getRowData(lastsel2);
					    	selectedUserCode = selectedList.emp_code;
					    	
						}
					
					},
					
					loadError : function(xhr, st, str){if(xhr.status == '403'){Commons.sessionOut('A');};}
				});
			
		}
		
		/**
		 * role 선택 시 호출 되는 role별 프로그램 등록 jqgrid 함수
		 * @param roleNo		role no
		 */
		function regPgmListAjax(roleNo){
			
			if(roleNo == ""){
				roleNo = topRoleNo;
			}
			
			// 파라미터 셋팅
			var param = "roleNo="+roleNo;
			
			$("#roleProgramList").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/mgmt/regPgmListAjax.do?" + param}).trigger("reloadGrid");
			$("#roleProgramList").jqGrid({
				
				url : "<%=ONLINE_ROOT%>/mgmt/regPgmListAjax.do?roleNo="+roleNo,
				// 요청방식
				mtype:"post",
				// 결과물 받을 데이터 타입
				datatype:"json",
			
				// 컬럼명
				colNames:["PGM No.","프로그램 명","프로그램 URL","버튼","Role No"],
				
				colModel:[
							{name:"pgm_no",		index:"pgm_no",			align:"left", width:46},		// 프로그램 no
							{name:"pgm_name",	index:"pgm_name",		align:"left", width:80},		// 프로그램명	
							{name:"pgm_id",		index:"pgm_id",			align:"left", width:80},		// 프로그램 id
							{name:"use_btn",	index:"use_btn",		align:"left", width:85},		// 버튼 string
							{name:"role_no",	index:"role_no",		align:"left", hidden:true},		// role no
				
						 ],
				// 그리드 캡션
				height:546,
				rowNum: -1,		  // 페이지 출력 글 수
				//기본 정렬 값 -> 매개변수이름은 sidx(컬럼), sord(차순)로 요청된다
				sortname :'pgm_no',
				sortorder:'asc',
				//autowidth:true,
				// 선택된 행의 데이터 가져오기.
				onSelectRow: function(id){
				
					if(id && id!==lastsel2){
						
						$('#roleProgramList').restoreRow(lastsel2);
				    	lastsel2=id;
				    	
				    	var selectedList = $("#roleProgramList").getRowData(lastsel2);
				    	selectedPgmNo = selectedList.pgm_no;
				    	roleNo = selectedList.role_no;
					}
				
				},
				
				ondblClickRow: function(){
					Commons.popupOpen("roleDetailPopup", "<%=ONLINE_ROOT%>/mgmt/common/roleDetailPopup.do?pgmNo="+selectedPgmNo+"&roleNo="+roleNo, "520", "777");
				},
				
				loadComplete: function(data){
					if(data.records > 0){
						selectedPgmNo = data.rows[0].pgm_no;
					}
				}
			});
		}
		
		/**
		 * role 삭제
		 * @param roleNo		role no
		 */
		function deleteRole(){
			
			$.ajax({
				type : "POST"
				       	, async : true
				    	, url : "<%=ONLINE_ROOT %>/mgmt/deleteRoleAjax.do"
				    	, dataType : "json"
				    	, data : {roleNo:roleNo}
				    	, success : function(data) {
				    		if(data.resultCode > 0){
								alert("삭제 되었습니다.");
								$("#roleNo").val("");
								$("#roleList").trigger("reloadGrid");
				    		}else{
				    			alert("처리 중 장애가 발생 하였습니다.");
				    		}
				    }
				    , error : function(request, status, error) {
					     if(request.status == '403'){Commons.sessionOut('A');}
				    }
			});
		}
		
		/**
		 * role 추가 및 저장 함수
		 */
		function procRole(type){
			
			var formData = $("form[id=frm]").serialize();
			
			$.ajax({
				type : "POST"
				       	, async : true
				    	, url : "<%=ONLINE_ROOT %>/mgmt/procRoleAjax.do?type="+type
				    	, dataType : "json"
				    	, data : formData
				    	, success : function(data) {
				    		if(data.resultCode > 0){
				    			if(data.type == "insert"){
				    				alert("추가 되었습니다.");
				    			}else{
				    				alert("저장 되었습니다.");
				    			}
								$("#roleList").trigger("reloadGrid");
				    		}else{
				    			alert("처리 중 장애가 발생 하였습니다.");
				    		}
				    }
				    , error : function(request, status, error) {
					     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
				    }
			});
		}
		
		/**
		 * role 복사 함수
		 */
		function insertRoleCopy(){
			
			var formData = $("form[id=frm]").serialize();
			
			$.ajax({
				type : "POST"
				       	, async : true
				    	, url : "<%=ONLINE_ROOT %>/mgmt/insertRoleCopyAjax.do"
				    	, dataType : "json"
				    	, data : formData
				    	, success : function(data) {
				    		if(data.resultCode > 0){
								alert("Role복사 저장 되었습니다.");
								$("#roleList").trigger("reloadGrid");
								$("#rowids").val() == "";
				    		}else{
				    			alert("Role복사 저장에 실패 하였습니다.");
				    			$("#roleList").trigger("reloadGrid");
				    			$("#rowids").val() == "";
				    		}
				    }
				    , error : function(request, status, error) {
					     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
				    }
			});
		}
		
		/**
		 * role 구분코드의 가상조직,실제조직,사용자 셀렉트 박스 변경 시 호출 되는 함수
		 */
		function changeRole(){
			
			var selectedVal = $("#roleCatCode option:selected").val();
			
			if(selectedVal == "01"){	// 가상 조직 선택 시
				$("#deptCode").attr('onclick','').unbind('click');  
				$("#deptName").attr('onclick','').unbind('click');  
				$("#empCode").attr('onclick','userPop();'); 
				$("#empName").attr('onclick','userPop();');
				$("#deptCode").attr("readonly",true);
				$("#deptName").attr("readonly",true);
				$("#deptCode").attr("class","resize_w01 ipt_disable");
				$("#deptName").attr("class","resize_w02 ipt_disable");
				$("#empCode").attr("readonly",false);
				$("#empName").attr("readonly",false);
				$("#empCode").attr("class","resize_w01");
				$("#empName").attr("class","resize_w02");
				$("#deptCode").val("");
				$("#deptName").val("");
			}else if(selectedVal == "02"){		// 부서 선택 시
				$("#deptCode").attr('onclick','deptPop();'); 
				$("#deptName").attr('onclick','deptPop();'); 
				$("#empCode").attr('onclick','').unbind('click'); 
				$("#empName").attr('onclick','').unbind('click');
				$("#empCode").attr("readonly",true);
				$("#empName").attr("readonly",true);
				$("#empCode").attr("class","resize_w01 ipt_disable");
				$("#empName").attr("class","resize_w02 ipt_disable");
				$("#deptCode").attr("readonly",false);
				$("#deptName").attr("readonly",false);
				$("#deptCode").attr("class","resize_w01");
				$("#deptName").attr("class","resize_w02");
				$("#empCode").val("");
				$("#empName").val("");
			}else{								// 사용자 선택 시
				$("#deptCode").attr('onclick','').unbind('click'); 
				$("#deptName").attr('onclick','').unbind('click'); 
				$("#empCode").attr('onclick','').unbind('click'); 
				$("#empName").attr('onclick','').unbind('click'); 
				$("#deptCode").attr("readonly",true);
				$("#deptName").attr("readonly",true);
				$("#empCode").attr("readonly",true);
				$("#empName").attr("readonly",true);
				$("#empCode").attr("class","resize_w01 ipt_disable");
				$("#empName").attr("class","resize_w02 ipt_disable");
				$("#deptCode").attr("class","resize_w01 ipt_disable");
				$("#deptName").attr("class","resize_w02 ipt_disable");
				$("#deptCode").val("");
				$("#deptName").val("");
				$("#empCode").val("");
				$("#empName").val("");
				$("#roleText").focus();
			}
		}
		
		/**
		 * role별 프로그램 등록 jqgrid reload함수
		 */
		function reloadRoleProgram(){
			$("#roleProgramList").trigger("reloadGrid");
		}
		
		/**
		 * role 구분코드의 사용자 선택 시 호출 되는 사용자 목록 팝업  
		 */
		function userPop(){
			Commons.popupOpen("userPopup","<%=ONLINE_ROOT%>/mgmt/common/userPopup.do?roleNo="+roleNo+"&type=roleReg","530","675");
		}
		
		/**
		 * role 구분코드의 사용자 선택 시 호출 되는 사용자 목록 팝업에서 해당 사용자 선택시 부모창의 사원코드 및 사원명 셋팅 해주는 함수 
		 */
		function userSet(selectedEmpCode,selectedEmpName){
			$("#empCode").val(selectedEmpCode);
			$("#empName").val(selectedEmpName);
		}
		
		/**
		 * role 구분코드의 실제조직 선택 시 호출 되는 부서 목록 팝업  
		 */
		function deptPop(){
			Commons.popupOpen("deptPopup","<%=ONLINE_ROOT%>/mgmt/common/deptListPopup.do","530","675");
		}
		
		/**
		 * role 구분코드의 실제조직 선택 시 호출 되는 부서 목록 팝업에서 해당 부서 선택시 부모창의 부서코드 및 부서명 셋팅 해주는 함수  
		 */
		function deptSet(selectedDeptCode,selectedDeptName){
			$("#deptCode").val(selectedDeptCode);
			$("#deptName").val(selectedDeptName);
		}
		
		/**
		 * role 추가 저장 시 validation chek 함수
		 */
		function roleCheck(){
			
			var roleName = $("roleName").val();		// role명
			var selectedVal = $("#roleCatCode option:selected").val();	// role 구분코드 선택 값
			var empCode = $("#empCode").val();		// 사원코드
			var empName = $("#empName").val();		// 사원명
			var deptCode = $("#deptCode").val();	// 부서코드
			var deptName = $("#deptName").val();	// 부서명
			
			if(roleName == ""){
				alert("Role명을 입력 해주세요.");
				return false;
			}
			
			if(selectedVal == "01"){
				if(empCode == "" || empName == ""){
					alert("사원을 선택 해주세요.");
					return false;
				}
			}else if(selectedVal == "02"){
				if(deptCode == "" || deptName == ""){
					alert("부서를 선택 해주세요.");
					return false;	
				}
			}
			return true;
		}
		
	</script>
</head>
<body>
<form id="frm" name="frm" method="post">
	<input type="hidden" name="rowids" id="rowids" value="" />
	<input type="hidden" name="roleNo" id="roleNo" value="" />	
	<div class="inner_cont">
		<div class="wrap_authority">
			<div class="role_admin_box float_l mr10">
				<div>
					<h2>Role</h2>
					<div class="box_type01 author_box00">
						<table class="type01 re_style02">
							<colgroup>
								<col style="width:30%" />
								<col style="width:70%" />
							</colgroup>
							<tbody>
								<tr>
									<th class="no_border_l">Role NO.</th>
									<td id="roleNoTxet" class="no_border_r"></td>
								</tr>
								<tr>
									<th class="no_border_l">Role 구분코드</th>
									<td class="no_border_r">
										<select name="roleCatCode" id="roleCatCode" onChange="javascript:changeRole();">
											<option value="03" selected>가상조직</option>
											<option value="02">실제조직</option>
											<option value="01">사용자</option>
										</select>
									</td>
								</tr>
								<tr>
									<th class="no_border_l">Role명</th>
									<td class="no_border_r">
										<input id="roleName" name="roleName" type="text" maxlength="100" />
									</td>
								</tr>
								<tr>
									<th class="no_border_l">부서</th>
									<td class="no_border_r">
										<input id="deptCode" name="deptCode" type="text" class="resize_w01 ipt_disable" readonly />
										<input id="deptName" name="deptName" type="text" class="resize_w02 ipt_disable" readonly />
									</td>
								</tr>
								<tr>
									<th class="no_border_l no_border_b">사원</th>
									<td class="no_border_r no_border_b">
										<input id="empCode" name="empCode" type="text" class="resize_w01 ipt_disable" readonly />
										<input id="empName" name="empName" type="text" class="resize_w02 ipt_disable" readonly />
									</td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
				<div class="mt25">
					<div class="box_type01 author_box01">
						<table id="roleList"></table>
					</div>
				</div>
				<div class="manager_btn_group">
					<div class="ta_r">
						<%=WebUtil.createButtonArea(currPgmNoByUri, "lb_")%>							
					</div>
				</div>					
			</div>
			<div class="role_admin_box float_l mr10">
				<h2>Role별 사용자</h2>
				<div class="box_type01 author_box02">
					<table id="roleUserList"></table>
				</div>
				<div class="manager_btn_group">
					<div class="ta_r">
						<%=WebUtil.createButtonArea(currPgmNoByUri, "cb_")%>		
					</div>
				</div>	
			</div>
			<div class="role_admin_box float_l">
				<h2 id="roleTitle"></h2>
				<div class="box_type01 author_box02">
					<table id="roleProgramList"></table>
				</div>
				<div class="manager_btn_group">
					<div class="ta_r">
						<%=WebUtil.createButtonArea(currPgmNoByUri, "rb_")%>									
					</div>
				</div>	
			</div>	
		</div>
	</div>
	<%@include file="/include/footer.jsp"%>
</form>
</body>
</html>