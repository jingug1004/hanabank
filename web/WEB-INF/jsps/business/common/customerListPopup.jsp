<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : customerListPopup.jsp
 * @메뉴명 : 영업관리 > 거래처 검색 팝업
 * @최초작성일 : 2014/10/29            
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
		/* 회전일 현황 화면에서 사용하기 위해. 창 이름이 as_fr_rcust일 경우 거래처를 판매처로 변경*/
		var custNameType = "거래";
		if(self.name == 'as_fr_rcust'){
			custNameType = "매출";
			
			$('h1.tit').html(custNameType+'처');
			$('div.wrap_pop_search label').html(custNameType+'처코드/명');
		}
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리.  */
	    jsonReader : {
            repeatitems: false;
    	}
	
		/* 거래처 목록 jqgrid */
		$("#grid_list").jqGrid({
			mtype:"get", // 요청방식
			datatype:"json", // 결과물 받을 데이터 타입
			colNames:[custNameType+"처코드",custNameType+"처명","사업자번호","대표자명","사원코드","사원명"], // 컬럼명  
			colModel:[
						{name:"cust_id",	index:"cust_id", 	align:"center",	width:90},
						{name:"cust_nm",	index:"cust_nm", 	align:"left"},
						{name:"vou_no",		index:"vou_no", 	align:"center",	width:140, formatter:vouNoFormatter},
						{name:"president",	index:"president", 	align:"left",	width:90},
						{name:"sawon_id",	index:"sawon_id", 	align:"center", width:60},
						{name:"sawon_nm",	index:"sawon_nm", 	align:"left",	width:90}
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
					alert("해당 "+custNameType+"처가 없습니다.");
				}
			},

			/* row 더블 클릭 시 이벤트 */
			ondblClickRow: function(){
				Business.selectValue('customer'); // 팝업을 닫고 부모창에 선택한 값 셋팅
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
	
	/* 거래처 목록 조회 */
	function getGridList(){
		/* 파라미터 셋팅 */
		var searchKeyword = $("#searchKeyword").val();
		var param = "searchKeyword=" + encodeURI(encodeURIComponent(searchKeyword));
		
		/* 호출 */
		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/common/customerGridList.do?" + param}).trigger("reloadGrid");
	}
	
	/* 사업자번호 커스텀 포맷 */
	function vouNoFormatter(cellvalue){
		var returnValue = "";
		
		if ("" != cellvalue) {
			returnValue = cellvalue.substring("0", "3") + "-" + cellvalue.substring("3", "5") + "-" + cellvalue.substring("5", "10");
		}
		
		return returnValue;
	}
	
	</script>
</head>


<body>
	<div class="popup" title="Main">
		<h1 class="tit">거래처</h1>
		
		<div class="wrap_pop_search">
			<label>거래처코드/명</label>
			<input type="text" class="w330" maxlength="10" id="searchKeyword" name="searchKeyword" />
			<button type="button" onclick="javascript:getGridList();" >조회</button>
			<button type="button" onclick="javascript:Business.selectValue('customer');" >선택</button>
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
