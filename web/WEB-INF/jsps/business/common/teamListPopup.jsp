<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : teamListPopup.jsp
 * @메뉴명 : 영업관리 > 부서 검색 팝업
 * @최초작성일 : 2014/11/03           
 * @author : 윤범진                  
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
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/business.js"></script>
	<script type="text/javascript">
	
	$(document).ready(function(){
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리.  */
	    jsonReader : {
            repeatitems: false;
    	}
	
		/* 파트 목록 jqgrid */
		$("#grid_list").jqGrid({
			mtype:"get", // 요청방식
			datatype:"json", // 결과물 받을 데이터 타입
			colNames:["부서코드","부서명"], // 컬럼명
			colModel:[
						{name:"dept_id",	index:"dept_id", 	align:"center"},
						{name:"dept_nm",	index:"dept_nm", 	align:"left"},
					],
			height:391,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			autowidth:true,
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if (data.records < 1) {
					alert("해당 부서가 없습니다.");
				}
			},
		
			/* row 더블 클릭 시 이벤트 */
			ondblClickRow: function(){
				Business.selectValue('team');
			},
			
			pager: "#grid_Pager"
   		});
		
		/* 엔터키 눌렸을 경우 조회되도록. */
		$("body").on("keydown", function(event){
			if(event.keyCode==13 && event.target.name!="grid_count"){
				getGridList();
				return false;
			}
		});
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
	$(window).resize(function(){
		$("#grid_list").setGridWidth($("#result").width() , true);
	});
	
	/* 부서 목록 조회 */
	function getGridList(){
		/* 파라미터 셋팅 */
		var searchKeyword = $("#searchKeyword").val();
		var param = "searchKeyword=" + encodeURI(encodeURIComponent(searchKeyword));
		
		/* 호출 */
		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/common/teamGridList.do?" + param}).trigger("reloadGrid");
	}
	
	</script>
</head>


<body>
	<div class="popup" title="Main">
		<h1 class="tit">부서조회</h1>
		
		<div class="wrap_pop_search">
			<label for="code_name">부서코드/명</label>
			<input type="text" class="w340" maxlength="30" id="searchKeyword" name="searchKeyword" />
			<button type="button" onclick="javascript:getGridList();" >조회</button>
			<button type="button" onclick="javascript:Business.selectValue('team');" >선택</button>
		</div>
		
		<div class="pop_grid box_type01 h440" id="result">
			<table id="grid_list"></table>
			<div id="grid_Pager"></div>
		</div>
		
		<button class="close"><span class="blind">닫기</span></button>
	</div>
	
	<%@include file="/include/footer_pop.jsp"%>
</body>

</html>
