<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : itemListPopup.jsp
 * @메뉴명 : 영업관리 > myP/L > 제품 팝업
 * @최초작성일 : 2014/10/29            
 * @author : 우정아                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>제품 검색</title>
	<%@ include file ="/include/head.jsp" %>
	<!--[if lte IE 9]><script src="asset/js/IE9.js"></script><![endif]-->
	<!--[if lte IE 9]><script src="asset/js/html5.js"></script><![endif]-->
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/business.js"></script>
	<script type="text/javascript">
	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리. */
	    jsonReader : {
            repeatitems: false;
    	}		
		var item_id = $("#item_id",opener.document).val();
		
		if(item_id != ''){
			$('#searchKeyword').val(item_id);
		}
	
		// 파라미터 셋팅
		var searchKeyword = $("#searchKeyword").val();
		var param = "searchKeyword=" + encodeURI(encodeURIComponent(searchKeyword));
		
		// 검색어가 있을 경우 바로 검색
		if ("" != searchKeyword) {
			$("#grid_list").jqGrid({
				url:"<%=ONLINE_ROOT%>/order/common/itemAllGridList.do?"+param,
				// 요청방식
				mtype:"get",
				// 결과물 받을 데이터 타입
				datatype:"json",
				// 컬럼명
				colNames:["제품코드","제품명","규격"],
		
				// 컬럼 데이터(추가, 삭제, 수정이 가능하게 하려면 autoincrement컬럼을 제외한 모든 컬럼을 editable:true로 지정)
				// edittyped은 text, password, ... input type명을 사용 
		
				colModel:[	
							{name:"item_id",	index:"item_id", 	align:"center", key:true	},		//제품코드
							{name:"item_nm",	index:"item_nm", 	align:"left"				},		//제품명
							{name:"standard",	index:"standard", 	align:"coenter"				}		//규격
						],
				page: 1,
				rowNum: 20,
				rowList: [20,30,40],
				height:415,
				autowidth:true,
				beforeProcessing: function(status){
					if (status.records < 1) {
						alert("해당 제품이 없습니다.");
					}
				},
				ondblClickRow: itemOverlapCheck,
				pager: "#grid_Pager"
	   		});
		}else{
			// 검색어가 있을 경우 바로 검색
			$("#grid_list").jqGrid({
				// 요청방식
				mtype:"get",
				// 결과물 받을 데이터 타입
				datatype:"json",
				// 컬럼명
				colNames:["제품코드","제품명","규격"],
		
				// 컬럼 데이터(추가, 삭제, 수정이 가능하게 하려면 autoincrement컬럼을 제외한 모든 컬럼을 editable:true로 지정)
				// edittyped은 text, password, ... input type명을 사용 
		
				colModel:[	
				          	{name:"item_id",	index:"item_id", 	align:"center", key:true	},		//제품코드
							{name:"item_nm",	index:"item_nm", 	align:"left"				},		//제품명
							{name:"standard",	index:"standard", 	align:"coenter"				}		//규격
						],
				page: 1,
				rowNum: 20,
				rowList: [20,30,40],
				height:415,
				autowidth:true,
				beforeProcessing: function(status){
					if (status.records < 1) {
						alert("해당 제품이 없습니다.");
					}
				},
				ondblClickRow: function(id){
					var ret = $("#grid_list").getRowData(id);
					
					itemOverlapCheck(ret.item_id);
				},
				pager: "#grid_Pager"
	   		});
		}
	});

    
	/**
	*	제품 목록 조회
	*/
	function getGridList(){
		
		var searchKeyword = $("#searchKeyword").val();
		
		// 파라미터 셋팅
		var param = "searchKeyword=" + encodeURI(encodeURIComponent(searchKeyword));
		// 호출
		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/common/itemAllGridList.do?" + param}).trigger("reloadGrid");
	}
	
	/**
	*	제품 기등록 여부 확인
	*/
	function itemOverlapCheck(item_id){
		if(item_id === undefined){
			item_id =  $('#grid_list').getGridParam("selrow");
		}
		
		if(item_id == null || item_id == ''){
			alert('제품을 선택하세요.');
			return;
		}
		
		$.ajax({
			type:"POST",
			data: {item_id: item_id},
			url:"<%=ONLINE_ROOT%>/business/itemOverlapCheckAjax.do",
			dataType:"json",
			success:function(data){
				if(data.result =="Y"){
					Business.selectValue('item');
				}else{
					alert("동일한 제품이 P/L 제품목록에 존재합니다.");
					return;
				}
			}
		});
		
		
	}
	</script>
</head>


<body onKeyPress="if(event.keyCode=='13'){getGridList(); return false;}">
	<div class="popup">
		
		<!-- ##### content start ##### -->
		<!-- window size : 600 * 655 -->
			<h1 class="tit">제품목록</h1>
			
			<div class="wrap_pop_search">
				<label>제품코드/명</label>
				<input type="text" class="w340" type="text" id="searchKeyword" name="searchKeyword"/>
				<button type="button" onclick="javascript:getGridList();">조회</button>
				<button type="button" onclick="javascript:itemOverlapCheck();" >선택</button>
			</div>
			
			<div class="pop_grid box_type01 h440">
				<table id="grid_list"></table>
				<div id="grid_Pager"></div>
			</div>
			
			<button type="button" class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
	</div>
	<%@ include file ="/include/footer_pop.jsp" %>
</body>

</html>
