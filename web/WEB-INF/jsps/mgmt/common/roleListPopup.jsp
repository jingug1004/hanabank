<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : roleListPopup.jsp      
 * @메뉴명 : Role 선택 팝업      
 * @최초작성일 : 2015/01/15            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%
	String empCode = StringUtil.nvl((String)request.getParameter("empCode"),"");	// 사원코드
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript">
	
	// 사원코드
	var empCode = "<%=empCode%>";		
	
	/**
	 * window dom load시 최초 호출되는 jqgrid
	 */
	$(document).ready(function(){ 
		
		$("#roleList").jqGrid({
			
			url : "<%=ONLINE_ROOT%>/mgmt/common/rolePopupGridList.do?empCode="+empCode,
			
			// 요청방식
			mtype:"post",
			// 결과물 받을 데이터 타입
			datatype:"json",
		
			// 컬럼명
			colNames:["","Role No","Role명"],
			
			colModel:[
						{name:"check_yn",	index:"check_yn", width:20, align:"center",	type:"checkbox", formatter: 'checkbox'
							 ,formatoptions:{disabled: false}, editable:true, edittype:"checkbox"
							 ,editoptions:{value:'Y:N'}  
							},		// 사용 여부
						{name:"role_no",			index:"role_no",			align:"left", key:true},			// role명 
						{name:"role_name",			index:"role_name",			align:"left"}						// role이름
			
					 ],
			// 그리드 캡션
			height:416,
			rowNum: -1,	
			
			//기본 정렬 값 -> 매개변수이름은 sidx(컬럼), sord(차순)로 요청된다
			sortname :'role_no',
			sortorder:'desc',
			autowidth:true,
			
			// 선택된 행의 데이터 가져오기.
		    beforeSelectRow: function(rowid, e) {     //사용자가 row를 클릭하는 순간 발생 (return값 false시 선택안한걸로 간주함)
		    	$("#roleList").jqGrid('setSelection', rowid);
		    },
		    
		});
		
		/**
		 * window창 크기 조절
		 */
		$(window).resize(function(){
			$("#roleList").setGridWidth($('.pop_grid box_type01.h440').width() , false);
		});
		
		/**
		 * 조회 버튼 클릭 : getGridList() function호출	
		 */
		$("#uc_retrieve").on("click",function(){
			getGridList();	
		});
		
		/**
		 * 확인 버튼 클릭 : roleInsert() function호출	
		 */
		$("#uc_save").on("click",function(){
			roleInsert();		
		});
			
	});
	
	/**
	 * window load 엔터키 입력 시 조회 버튼을 바로 호출 할 수 있게 작업
	 */
	$(window).load(function(){
		$("body").on("keydown", function(event){
			if(event.keyCode==13 && event.target.name!="grid_count"){
				getGridList();
			return false;
			}
		});
	});
	
	/**
	 * 조회 버튼 클릭
	 * @returns jqgrid			roleList
	 */
	function getGridList(){
		$("#roleList").clearGridData(); 	// 이전 그리드 삭제
		$("#roleList").setGridParam({
			url : "<%=ONLINE_ROOT%>/mgmt/common/rolePopupGridList.do",
			postData : {
				empCode:empCode,
				code:$("#code").val(),
				name:$("#name").val()
			}
		}).trigger("reloadGrid");	
	}
	
	/**
	 * 저장 버튼 클릭 : role insert DB insert	
	 * @param jqgrid			getRowData
	 */
	function roleInsert(){
		
		var uniqueRoleCodeArray = [];			// 선택 된 role을 담기 위한 배열 셋팅
		var selarrrow = $("#roleList").jqGrid("getRowData");	// 선택 된 행의 데이터 가지고 오기
		
		for(var selIdx=0; selIdx<selarrrow.length; selIdx++){	// 선택 된 행 만큼 for
			if(selarrrow[selIdx].check_yn == "Y"){				// 선택 된 행 중 체크 박스가 체크 되어 있는 경우만
				uniqueRoleCodeArray.push(selarrrow[selIdx].role_no);	// role_no를 배열에 담아준다.
			}
		}
		
		uniqueRoleCodeArrayJoin = uniqueRoleCodeArray.join();	// 배열을 str,로 구분하여 변환
		
		if(uniqueRoleCodeArrayJoin == ""){
			alert("선택 된 Role No가 없습니다.");
			return;
		}
		
		$.ajax({
			type : "POST"
			       	, async : true
			    	, url : "<%=ONLINE_ROOT %>/mgmt/common/insertRoleAjax.do"
			    	, dataType : "json"
					, data : {roleNo:uniqueRoleCodeArrayJoin,empCode:empCode}
			, success : function(data) {
					alert("저장되었습니다.");
					opener.reloadRoleProgram();
					self.close();
			}
			, error : function(request, status, error) {
				alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
			}
		});
		
	}
	
</script>
</head>
<body onKeyDown="if(event.keyCode==13){getGridList();return false;}">
	<div class="popup" title="Main">
		<!-- ##### content start ##### -->
		<!-- window size : 480 * 655 -->
			<h1 class="tit">Role 목록</h1>
			<div class="wrap_pop_search">
				Role No
					<input type="text" class="w100" id="code" name="code" />
					<input type="text" class="w150" id="name" name="name" />
				<div class="wrap_pop_search_btn">
					<button id="uc_retrieve">조회</button>
					<button id="uc_save">수정</button>
					<button id="uc_close" onclick="self.close();">취소</button>
				</div>
			</div>
			<div class="pop_grid box_type01 h440">
				<table id="roleList"></table>
			</div>
			<button class="close"><span class="blind">닫기</span></button>
		<!-- ##### content end ##### -->
	</div>
	<%@include file="/include/footer_pop.jsp"%>
</body>
</html>