<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : inquireRole.jsp
 * @메뉴명 : MANAGER > 권한 조회
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
	<link rel="StyleSheet" href="<%=ONLINE_WEB_ROOT%>/css/dtree.css" type="text/css" />
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/dtree.js"></script>	
	<script type="text/javascript">
		
		var roleNo = "";			//	role no
		var lastsel2 = "";			// 선택 된 sel데이터를 담기 위한 변수
		var selectedPgmNo = "";		// 선택 된 프로그램 no를 담기 위한 변수
		var roleNoArray = [];		// role no를 담기 위한 배열
		var empText = "";			// 변경될 text
		
		/**
		 * window dom load시 최초 호출되는 함수
		 */
		$(document).ready(function(){
			$('#divTabsWrap > li > a').eq(0).on("click", function(){	// 사용자 탭 클릭 시
				$('#pgmTab').hide();		// 프로그램 탭 숨기기
				$('#userTab').show();		// 사용자 탭 노출
				$('#divTabsWrap > li').eq(0).attr("class","active");	// 사용자 탭 클래스 활성화
				$('#divTabsWrap > li').eq(1).attr("class","");			// 프로그램 탭 클래스 비 활성화
				resizeJqGrid();				// jqgrid resize
			});
			
			$('#divTabsWrap > li > a').eq(1).on("click", function(){	// 사용자 탭 클릭 시
				$('#userTab').hide();		// 사용자 탭 숨기기
				$('#pgmTab').show();		// 프로그램 탭 노출
				$('#divTabsWrap > li').eq(0).attr("class","");			// 사용자 탭 클래스 활성화
				$('#divTabsWrap > li').eq(1).attr("class","active");	// 프로그램 탭 클래스 활성화
				resizeJqGrid();
			});
			
			// 조회버튼 클릭
			$("#lt_search_btn_type02").on("click",function(){
				getEmpListAjax();
			});
			
			//전체프로그램 조회
			$("#totPgmTree").empty();
			Commons.treeMenuLeftAjax("IN_USE");
			
		});
		
		/**
		 * window resize함수
		 */
		$(window).resize(function(){
			resizeJqGrid();
		});
		
		/**
		 * 엔터 키 입력시 해당 계정의 조회 버튼을 호출 하며 조회 버튼 권한이 있을때만 조회버튼 기능이 동작 하도록 설정
		 */
		$(window).load(function(){
			$("body").on("keydown", function(event){
				if($("#lt_search_btn_type02").prop('disabled') == false){
					if(event.keyCode==13 && event.target.name!="grid_count"){
						getEmpListAjax();
					return false;
					}
				}
			});
		});
		
		/**
		 * jqgrid resize 함수
		 */
		var resizeJqGrid = function(){
			if($("#userTab").css("display") == "none"){	// 트리 선택시 프로그램탭이 활성화되었을 경우만...
				$("#pgmRoleList").setGridWidth($("#pgmRoleList").parents('.author_box').width() , false);
				$("#pgmUserList").setGridWidth($("#pgmUserList").parents('.author_box').width() , false);
			} else {
				$("#empList").setGridWidth($("#empList").parents('.author_search').width() , false);
				$("#roleList").setGridWidth($("#roleList").parents('.author_box').width() , false);
			}
		};
		
		/**
		 * 사용자 탭의 조회 버튼 클릭 시 호출 되는 함수
		 */
		var getEmpListAjax = function(){
			$("#empList").clearGridData(); 	// 이전 그리드 삭제
			
			var postData = { 
				selectedType:$("#selectedType option:selected").val(), 
				code:$("#code").val(),
				name:$("#name").val()
				};
			
			$("#empList").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/mgmt/common/userPopupGridList.do", postData:postData}).trigger("reloadGrid");
			
			//jqgrid 설정
			$("#empList").jqGrid({
					
					url : "<%=ONLINE_ROOT%>/mgmt/common/userPopupGridList.do",
					// 요청방식
					mtype:"post",
					// 결과물 받을 데이터 타입
					datatype:"json",
					
					// parameter
					postData : postData,
				
					// 컬럼명
					colNames:["부서","사용자 코드","사용자명"],
					
					colModel:[
								{name:"dept_name",	index:"dept_name",		align:"left", width:100},	// 부서명	
								{name:"emp_code",	index:"emp_code",		align:"left", width:60},	// 사원 코드
								{name:"emp_name",	index:"emp_name",		align:"left", width:147}	// 사원 명
							 ],
							 
					rowNum: -1,		  // 페이지 출력 글 수
					height:410,
					//autowidth:true,
					
					// 선택된 행의 데이터 가져오기.
					onSelectRow: function(id){
						
						lastsel2=id;
				    	
				    	var selectedList = $("#empList").getRowData(lastsel2);
				    	empText = selectedList.emp_name;
				    	
			    		$("#pgmTree").empty();
			    		var empCode = $("#empList").getRowData(id).emp_code;
			    		getUserPgmTreeAjax(empCode);	// 사용자 탭의 조회 버튼 클릭 후 사용자 선택 시 사용 프로그램 jqgrid호출
			    		getUserRoleListAjax(empCode);	// 사용자 탭의 조회 버튼 클릭 후 사용자 선택 시 사용권한 jqgrid호출
					
					},
					
					/**
					 * 사용자 탭의 조회 버튼 클릭 후 선택 된 사용자 더블클릭 시 호출 되는 role copy팝업
					 */
					ondblClickRow: function(){
						var roleNoArrayJoin = roleNoArray.join();
						empText = encodeURI(encodeURIComponent(empText));
						Commons.popupOpen("userPopup","<%=ONLINE_ROOT%>/mgmt/common/userPopup.do?roleNo="+roleNoArrayJoin+"&type=inquireRole&empText="+empText,"530","675");
					}
				});
		};
		
		/**
		 * 사용자 탭의 조회 버튼 클릭 후 선택 된 사용자의 사용프로그램 jqgrid
		 */
		var getUserPgmTreeAjax = function(emp_code){
			$.ajax({
				type : "POST"
				       	, async : true
				    	, url : ONLINE_ROOT+"/mgmt/getUserPgmListAjax.do"
				    	, dataType : "json"
				    	, data : {emp_code:emp_code}
				    	, success : function(data) {
				    		if(data.menuList.length > 0 && typeof data.menuList != "undefined"){
					    		d2 = new dTree('d2');
					    		d2.add(0,-1,data.menuList[0].pgm_name);
					    		for(var menuIdx=0; menuIdx<data.menuList.length; menuIdx++){
					    			d2.add(data.menuList[menuIdx].pgm_no,data.menuList[menuIdx].parent_pgm,data.menuList[menuIdx].pgm_name,data.menuList[menuIdx].pgm_id,"right"+data.menuList[menuIdx].pgm_name);
					    		}
					    		$("#displayTree").show();
					    		$('#pgmTree')[0].innerHTML = d2;
					    	}
				    	}
				});
		};
		
		/**
		 * 사용자 탭의 조회 버튼 클릭 후 선택 된 사용자의 사용권한 jqgrid
		 */
		var getUserRoleListAjax = function(emp_code){
			
			var postData = {emp_code:emp_code};
			
			$("#roleList").clearGridData(); 	// 이전 그리드 삭제
			$("#roleList").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/mgmt/getUserRoleListAjax.do", postData:postData}).trigger("reloadGrid");
			
			//jqgrid 설정
			$("#roleList").jqGrid({
					
					url : "<%=ONLINE_ROOT%>/mgmt/getUserRoleListAjax.do",
					// 요청방식
					mtype:"post",
					// 결과물 받을 데이터 타입
					datatype:"json",
					// parameter
					postData : postData,
					// 컬럼명
					colNames:["Role No","구분코드","Role 명"],
					colModel:[
								{name:"role_no",		index:"role_no",			align:"left", width:55},	// role no	
								{name:"role_cat_code",	index:"role_cat_code",		align:"left", width:55},	// role 카테고리 코드
								{name:"role_name",		index:"role_name",			align:"left", width:197}	// role명
							 ],
					// 그리드 캡션
					height:488,
					rowNum: -1,		  // 페이지 출력 글 수
					//autowidth:true,
					
					loadComplete: function(data){
						if (data.records > 0) {
							roleNoArray = [];
							for(var idx=0; idx<data.records; idx++){
								roleNoArray.push(data.rows[idx].role_no);	
							}
						}
					}
			});
		};
		
		/**
		 * 프로그램 탭의 사용권한 jqgrid 호출
		 */
		var getRoleListByPgmnoAjax = function(pgm_no){
			
			var postData = {pgm_no:pgm_no};
			
			$("#pgmRoleList").clearGridData(); 	// 이전 그리드 삭제
			$("#pgmRoleList").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/mgmt/getRoleListByPgmnoAjax.do", postData:postData}).trigger("reloadGrid");
			
			//jqgrid 설정
			$("#pgmRoleList").jqGrid({
					
					url : "<%=ONLINE_ROOT%>/mgmt/getRoleListByPgmnoAjax.do",
					// 요청방식
					mtype:"post",
					// 결과물 받을 데이터 타입
					datatype:"json",
					// parameter
					postData : postData,
					// 컬럼명
					colNames:["Role No","구분코드","Role 명"],
					colModel:[
								{name:"role_no",		index:"role_no",		align:"left", width:55},	// role no		
								{name:"role_cat_code",	index:"role_cat_code",	align:"left", width:55},	// role 카테고리 코드
								{name:"role_name",		index:"role_name",		align:"left", width:196}	// role명
							 ],
					// 그리드 캡션
					height:488,
					rowNum: -1,		  // 페이지 출력 글 수
					//autowidth:true
					
			});
		};
		
		/**
		 * 프로그램 탭의 프로그램 사용자 jqgrid 호출
		 */
		var getEmpListByPgmnoAjax = function(pgm_no){
			
			var postData = {pgm_no:pgm_no};
			
			$("#pgmUserList").clearGridData(); 	// 이전 그리드 삭제
			$("#pgmUserList").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/mgmt/getEmpListByPgmnoAjax.do", postData:postData}).trigger("reloadGrid");
			
			//jqgrid 설정
			$("#pgmUserList").jqGrid({
					url : "<%=ONLINE_ROOT%>/mgmt/getEmpListByPgmnoAjax.do",
					// 요청방식
					mtype:"post",
					// 결과물 받을 데이터 타입
					datatype:"json",
					// parameter
					postData : postData,
					// 컬럼명
					colNames:["부서","사원코드","사원명"],
					colModel:[
								{name:"dept_name",	index:"dept_name",		align:"left", width:120},		// 부서
								{name:"emp_code",	index:"emp_code",		align:"left", width:55},		// 사원코드
								{name:"emp_name",	index:"emp_name",		align:"left", width:131}		// 사원명
							 ],
					rowNum: -1,		  // 페이지 출력 글 수
					height:488,
					//autowidth:true,
			});
		};
		
		/**
		 * 메뉴 tree
		 */
		function leftTreeComplete(d){
			$("#totPgmTree")[0].innerHTML = d;
		}
		
		/**
		 * 메뉴 tree의 프로그램 선택 시 호출 되는 함수
		 * @param programNumber		프로그램 no
		 */
		function selectedMenu(programNumber){
		
			if($("#userTab").css("display") == "none"){	// 트리 선택시 프로그램탭이 활성화되었을 경우만...
				if(programNumber != ""){
					var pgmNo = Commons.leadingZeros(programNumber,5);
					getRoleListByPgmnoAjax(pgmNo);	// 프로그램 탭의 사용권한 jqgrid 호출
					getEmpListByPgmnoAjax(pgmNo);	// 프로그램 탭의 프로그램 사용자 jqgrid 호출
				}
				
			} 
		}
		
		/**
		 * 메뉴 tree의 메뉴폴더 선택 시 호출 되는 함수
		 * @param programNumber		프로그램 no
		 */
		function sortSetting(programNumber){
			selectedMenu(programNumber);
		}
		
		/**
		 * 탭 선택 시 text 변경
		 */
		function textChange(){
			var selectedType = $("#selectedType").val();
			
			if(selectedType == "dept"){
				$("#txtGubun").text("부서코드");
				$("#txtName").text("부서이름");
			}else{
				$("#txtGubun").text("사용자코드");
				$("#txtName").text("사용자이름");
			}
		}
		
	</script>
</head>
<body>
	<div class="inner_cont">
		<div class="wrap_authority">
				<h2>권한조회</h2>
				<ul id="divTabsWrap" class="search_navi">
					<li class="active"><a href="#userTab">사용자</a></li>
					<li><a href="#pgmTab">프로그램</a></li>
				</ul>
			<div id="userTab" class="search_cont">
				<div class="float_l mr20">
					<h2>조회</h2>
					<div class="search_box mt10">
						<div class="input_set float_l">
							<div>
								<span id="txtGubun">부서코드</span>
									<select name="selectedType" id="selectedType" onChange="javascript:textChange();">
										<option value="dept">부서</option>
										<option value="emp">사원</option>
									</select>
								<input type="text" name="code" id="code"/>
							</div>
							<div class="code_box mt5">
								<span id="txtName">부서이름</span>
								<input type="text" name="name" id="name"/>
							</div>
						</div>
						<%=WebUtil.createButtonArea(currPgmNoByUri, "lt_")%>
					</div>
					<div class="author_search box_type01">
						<table id="empList"></table>
					</div>
				</div>
				<div class="float_l mr20">
					<h2>사용프로그램</h2>
					<div class="author_pro_box box_type01">
						<div id="displayTree" class="list_button" style="width:92px; margin:10px auto; display:none;">
							<button class="type_01" onclick="javascript: d2.openAll();">펼치기</button>
							<button class="type_01" onclick="javascript: d2.closeAll();">접기</button>
						</div>
						<div id="pgmTree" class="mt10"></div>
					</div>
				</div>
				<div class="float_l">
					<h2>사용권한</h2>
					<div class="author_box box_type01">
						<table id="roleList"></table>
					</div>
				</div>					
			</div>
			<div id="pgmTab" class="search_cont" style="display:none">
				<div class="float_l mr20">
					<h2>전체 프로그램 메뉴</h2>
					<div class="author_pro_box box_type01">
						<div class="list_button" style="width:92px; margin:10px auto">
							<button class="type_01" onclick="javascript: d.openAll();">펼치기</button>
							<button class="type_01" onclick="javascript: d.closeAll();">접기</button>
						</div>
						<div id="totPgmTree" class="mt10"></div>
					</div>
				</div>
				<div class="float_l mr20">
					<h2>사용권한</h2>
					<div class="author_box box_type01">
						<table id="pgmRoleList"></table>
					</div>
				</div>
				<div class="float_l">
					<h2>프로그램 사용자</h2>
					<div class="author_box box_type01">
						<table id="pgmUserList"></table>
					</div>
				</div>					
			</div>
		</div>
	</div>
	<%@include file="/include/footer.jsp"%>
</body>
</html>