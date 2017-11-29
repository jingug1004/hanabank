<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : deptListPopup.jsp       
 * @메뉴명 : 부서 조회 팝업            
 * @최초작성일 : 2015/01/15            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){ 
		
		var lastsel2 = "";			// 선택된 행의 값을 담기위한 변수
		var selectedDeptCode = "";	// 선택된 부서 코드를 담기위한 변수
		var selectedDeptName = "";	// 선택된 부서명을 담기위한 변수
		
		/**
		 * window dom load시 최초 호출되는 jqgrid
		 */
		$("#deptList").jqGrid({
			
			url : "<%=ONLINE_ROOT%>/mgmt/common/userPopup.do",
			
			// 요청방식
			mtype:"post",
			// 결과물 받을 데이터 타입
			datatype:"json",
			// 컬럼명
			colNames:["부서코드","부서"],
			
			colModel:[
						{name:"dept_code",			index:"dept_code",			align:"left", key:true},			// 부서 코드 
						{name:"dept_name",			index:"dept_name",			align:"left"}						// 부서
			
					 ],
			// 그리드 캡션
			height:416,
			rowNum: -1,	
			
			//기본 정렬 값 -> 매개변수이름은 sidx(컬럼), sord(차순)로 요청된다
			sortname :'dept_name',
			sortorder:'desc',
			autowidth:true,
			
			// 선택된 행의 데이터 가져오기.
			onSelectRow: function(id){
			
				if(id && id!==lastsel2){
					
					$('#deptList').restoreRow(lastsel2);
			    	lastsel2=id;
			    	
			    	var selectedList = $("#deptList").getRowData(lastsel2);
			    	
			    	selectedDeptCode = selectedList.dept_code;
			    	selectedDeptName = selectedList.dept_name;
				}
			
			},
			
			// 더블 클릭시 진행되는 이벤트 부모 창에 부서코드 및 부서명을 셋팅 해준다.
			ondblClickRow: function(){
				opener.deptSet(selectedDeptCode,selectedDeptName);
				self.close();
			},
			
		});
		
		/**
		 * window창 크기 조절
		 */
		$(window).resize(function(){
			$("#deptList").setGridWidth($('pop_grid box_type01 h440').width() , false);
		});
		
		/**
		 * 조회 버튼 클릭 : 부서목록을 노출하며 부서코드 또는 부서이름의 검색어 가 있을 시 해당 검색	
		 * @param code		부서코드
		 * @param name		부서명
		 * @returns jqgrid	deptList
		 */
		$("#uc_retrieve").bind("click",function(){
			$("#deptList").clearGridData(); 	// 이전 그리드 삭제
			$("#deptList").setGridParam({
				url : "<%=ONLINE_ROOT%>/mgmt/common/deptPopupGridList.do",
				postData : {
					code:$("#code").val(),
					name:$("#name").val()
				}
			}).trigger("reloadGrid");			
		});
		
		/**
		 * 확인 버튼 클릭 : 부서 검색 후 권한 등록관리 창의 부서 코드,부서명을 셋팅	
		 * @returns var	selectedDeptCode,selectedDeptName
		 */
		$("#uc_save").bind("click",function(){
			
			if(selectedDeptCode == ""){
				alert("부서를 선택 해주세요.");
				return;
			}
			
			opener.deptSet(selectedDeptCode,selectedDeptName);	// 부모 창에 부서코드 및 부서명을 셋팅 해준다.
			self.close();
		});
			
	});
	
 	/**
	 * 팝업 page에서 엔터 키 : 부서목록을 노출하며 부서코드 또는 부서이름의 검색어 가 있을 시 해당 검색	
	 * @param code		부서코드
	 * @param name		부서명
	 * @returns jqgrid	deptList
	 */
	function getGridList(){
		$("#deptList").clearGridData(); 	// 이전 그리드 삭제
		$("#deptList").setGridParam({
			url : "<%=ONLINE_ROOT%>/mgmt/common/deptPopupGridList.do",
			postData : {
				code:$("#code").val(),
				name:$("#name").val()
			}
		}).trigger("reloadGrid");	
	}
 	
</script>
</head>
<body onKeyDown="if(event.keyCode==13){getGridList();return false;}">
	<div class="popup" title="Main">
		<!-- ##### content start ##### -->
		<!-- window size : 480 * 655 -->
			<h1 class="tit">부서조회</h1>
			<div class="wrap_pop_search">
				부서
					<input type="text" class="w100" id="code" name="code" />
					<input type="text" class="w150" id="name" name="name" />
				<div class="wrap_pop_search_btn">
					<button id="uc_retrieve">조회</button>
					<button id="uc_save">확인</button>
					<button id="uc_close" onclick="self.close();">취소</button>
				</div>
			</div>
			<div class="pop_grid box_type01 h440">
				<table id="deptList"></table>
			</div>
			<button class="close"><span class="blind">닫기</span></button>
		<!-- ##### content end ##### -->
	</div>
	<%@include file="/include/footer_pop.jsp"%>
</body>
</html>