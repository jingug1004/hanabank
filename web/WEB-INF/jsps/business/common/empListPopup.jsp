<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : empListPopup.jsp
 * @메뉴명 : 영업관리 > 사원 검색 팝업
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
		
		/* 부모창에 검색조건이 있을 경우 셋팅해준다. */
		$("#dept_cd").val($("#as_team_cd", opener.document).val()); // 부서코드 셋팅
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리.  */
	    jsonReader : {
            repeatitems: false;
    	}

		/* 사원 목록 jqgrid */
		$("#grid_list").jqGrid({
			mtype:"get", // 요청방식
			datatype:"json", // 결과물 받을 데이터 타입
			colNames:["사원코드","사원명","재직구분","부서명"], // 컬럼명
			colModel:[
						{name:"emp_id"  ,index:"emp_id"  ,align:"center" , width:200},
						{name:"emp_nm"  ,index:"emp_nm"  ,align:"center"   , width:200},
						{name:"gubun"   ,index:"gubun"   ,align:"center" , width:100 , formatter:gubunFormatter},
						{name:"dept_nm" ,index:"dept_nm" ,align:"left"   , width:300}
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
					alert("해당 사원이 없습니다."); // 팝업을 닫고 부모창에 선택한 값 셋팅
				}
			},
		
			/* row 더블 클릭 시 이벤트 */
			ondblClickRow: function(){
				Business.selectValue('emp');
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
	
	/* 재직구분 커스텀 포맷 */
	function gubunFormatter(cellvalue){
		var returnValue = "";
		
		if ("Y" == cellvalue) {
			returnValue = "재직";
		} else if ("N" == cellvalue) {
			returnValue = "퇴직";
		}
		
		return returnValue;
	}
	
	/* 사원 목록 조회 */
	function getGridList(){
		/* 파라미터 셋팅 */
		var searchKeyword = $("#searchKeyword").val();
		var dept_cd = $("#dept_cd").val();
		var param = "searchKeyword=" + encodeURI(encodeURIComponent(searchKeyword)) + "&dept_cd=" + dept_cd;
		
		/* 호출 */
		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/common/empGridList.do?" + param}).trigger("reloadGrid");
	}
	
	</script>
</head>


<body>
	<div class="popup" title="Main">
		<h1 class="tit">사원조회</h1>
	
		<div class="wrap_pop_search">
			<label>사원코드/명</label>
			<input type="text" class="w340" maxlength="20" id="searchKeyword" name="searchKeyword" />
			<input type="hidden" name="dept_cd" id="dept_cd" />
			<button type="button" onclick="javascript:getGridList();" >조회</button>
			<button type="button" onclick="javascript:Business.selectValue('emp');" >선택</button>
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
