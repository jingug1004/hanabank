<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : promiseListPopup.jsp
 * @메뉴명 : 영업관리 > 담보약속 검색 팝업
 * @최초작성일 : 2014/12/29           
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
	
		/* 부모창에서 파라미터 셋팅 */
		var selectType = $("#selectType option:selected", opener.document).val();
		var id = $("#grid_list_" + selectType, opener.document).jqGrid('getGridParam','selrow');
		var ret = $("#grid_list_" + selectType, opener.document).jqGrid('getRowData',id);
		var adt_fr_date = $("#adt_fr_date", opener.document).val();
		var adt_to_date = $("#adt_to_date", opener.document).val();
		
		$("#as_cust_id").val(ret.cust_id); // 거래처 코드
		$("#adt_fr_date").val(adt_fr_date); // 조회기간 fr
		$("#adt_to_date").val(adt_to_date); // 조회기간 to
		
		/* 담보약속 목록 jqgrid */
		$("#grid_list").jqGrid({
			mtype:"get", // 요청방식
			datatype:"json", // 결과물 받을 데이터 타입
			colNames:["주문요청일","약속기일","상태","약속내용","반려사유"], // 컬럼명
			colModel:[
						{name:"req_date",		index:"req_date",		align:"center"},
						{name:"promise_date",	index:"promise_date", 	align:"center"},
						{name:"status",			ndex:"status",			align:"center"},
						{name:"promise_bigo",	index:"promise_bigo",	align:"left"},
						{name:"return_desc",	index:"return_desc", 	align:"left"}
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
					alert("조회 결과가 없습니다.");
				}
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
	
	/* 담보약속 목록 조회 */
	function getGridList(){
		/* 파라미터 셋팅 */
		var params = {
			adt_fr_date : $("#adt_fr_date").val(),
			adt_to_date : $("#adt_to_date").val(),
			as_cust_id : $("#as_cust_id").val()
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/common/promiseGridList.do?" + paramStr}).trigger("reloadGrid");
	}
	
	</script>
</head>


<body>
	<div class="popup" title="Main">
		<input type="hidden" name="as_cust_id" id="as_cust_id" />
		
		<h1 class="tit">담보약속이력</h1>
				
		<div class="wrap_pop_search">
			<label class="w70">주문요청일</label>
			<p class="wrap_date">
				<input type="text" maxlength="10" id="adt_fr_date" name="adt_fr_date" />
				<button class="btn_date" style="top:2px;"><span class="blind">달력보기</span></button>
			</p>
			~
			<p class="wrap_date">
				<input type="text" maxlength="10" id="adt_to_date" name="adt_to_date" />
				<button class="btn_date" style="top:2px;"><span class="blind">달력보기</span></button>
			</p>
			
			<div class="wrap_pop_search_btn">
				<button type="button" onclick="javascript:getGridList();" >조회</button>
				<button type="button" onclick="javascript:self.close();">닫기</button>
			</div>
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
