<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
  * @파일명     : order_delivery.xml
  * @메뉴명     : 온라인 주문 발주 > 배송정보  
  * @최초작성일 : 2015/08/07            
  * @author    : 김태안                  
  * @수정내역   :	
--%>
<%@ include file ="/common/path.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/formCheck.js"></script>
	<script type="text/javascript">

	var saleActionFlag = false;	//기능(엑셀, 인쇄) 제어를 위한 전역변수
	
	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리. */
	    jsonReader : {
            repeatitems: false;
    	}
		
		/* jqGrid 생성 및 options 설정. */
		$("#grid_list").jqGrid({
			mtype:"post",
			datatype:"json",
			colNames:["배송일자","배송처","품목","단위","수량","단가","공급가액","세액","합계금액","수금","잔액"],
			colModel:[
						{name:"ymd",		index:"ymd",		align:"center",	width:100 },			   			        //일자
						{name:"rcust_nm",	index:"rcust_nm ",	align:"left",	width:200 },						        //납품처
						{name:"item_nm",	index:"item_nm", 	align:"left",	width:300},							        //품목
						{name:"standard",	index:"standard", 	align:"center",	width:55,},							        //단위
						{name:"qty",		index:"qty", 		align:"center", width:55, formatter:Commons.integerFmt},	//수량
						{name:"danga",		index:"danga", 		align:"right", 	width:55, formatter:Commons.integerFmt},	//단가
						{name:"amt",		index:"amt", 		align:"right",  width:100, formatter:Commons.integerFmt},	//공급가액
						{name:"vat",		index:"vat", 		align:"right",	width:100, formatter:Commons.integerFmt},	//세액
						{name:"tot",		index:"tot",		align:"right",	width:100, formatter:Commons.integerFmt},	//합계금액
						{name:"sukum",		index:"sukum", 		align:"right",	width:100, formatter:Commons.integerFmt},	//수금
						{name:"rem_amt",	index:"rem_amt",	align:"right",	width:100, formatter:Commons.integerFmt}	//잔액
						
					],
			height:461,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			rownumbers: false,
			autowidth:true,
			loadComplete: function(data){	//조회 완료시 호출되는 function
				if(data.records > 0){		//조회 결과가 있을 경우
					saleActionFlag = true;
					$("#amt").val(Commons.addComma(data.sumDelivery.amt));
					$("#vat").val(Commons.addComma(data.sumDelivery.vat));
					$("#tot").val(Commons.addComma(data.sumDelivery.tot));
					$("#sukum").val(Commons.addComma(data.sumDelivery.sukum));
					
					if(data.page==1){
						var datarow = {ymd:"",rcust_nm:"",item_nm:"<b>[ 이월잔액 ]</b>",standard:"",qty:"",danga:"",amt:"",vat:"",tot:"",sukum:"",rem_amt:'<b>'+data.rows[0].before_amt+'</b>'};
						$("#grid_list").addRowData(0,datarow,"first");
						$("#grid_list").setSelection('0');
					}
					
				}else{
					saleActionFlag = false;
					alert("해당 자료가 없습니다.");
				}
			},
			pager: "#grid_Pager"
   		});
		
		/* 조회 버튼 클릭 */
		$("#ct_retrieve_btn_type01").on("click",function(){
			getGridList();
		});
		
		/* 인쇄 버튼 클릭 */
		$("#p_print").on("click",function(){
			gridPrint();
		});
		
		/* 엑셀 버튼 클릭 */
		$("#p_excel").on("click",function(){
			Commons.extraAction(saleActionFlag, 'excel', '<%=ONLINE_ROOT%>/order/deliveryGridListExcelDown.do', '');
		});
		
		/* 닫기 버튼 클릭 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('조회');
		});
		
		/* 조회일자 셋팅 */
		Commons.setDate('adt_from','adt_to');
		
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
    $(window).resize(function(){
		$("#grid_list").setGridWidth($('.wrap_result_group').width(), true);	//grid 영역의 넓이가 동적으로 조절 
	});
    
    /* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(window).load(function(){
		/*
		*	엔터키를 눌렀을 때 조회버튼이 비활성화되어 있고, 엔터키가 눌러진 객체의 name속성이 grid_count 아닐 경우 조회
		*/
		$("body").on("keydown", function(event){
			if($("#ct_retrieve_btn_type01").prop('disabled') == false){
				if(event.keyCode==13 && event.target.name!="grid_count"){
					getGridList();
				return false;
				}
			}
		});
	});
	
	/**
	*	원장 조회
	*/
	function getGridList(){
		
		//validation check//
		var start_date = $('#adt_from');
		var end_date = $('#adt_to');
		
		if (!formCheck.isDateFormat(start_date.val())){
			alert("조회 기간을 확인하세요.");
			start_date.focus();
			return
	    }

		if (!formCheck.isDateFormat(end_date.val())){
			alert("조회 기간을 확인하세요.");
			end_date.focus();
			return
		}
		
		if(formCheck.dateChk(start_date.val(),end_date.val())<0){
			alert("조회 시작일이 종료일보다 이후 입니다.");
			start_date.focus();
			return
		}
				
				
		var adt_from = $("#adt_from").val();
		var adt_to = $("#adt_to").val();
		
		var param = "adt_from=" + adt_from + "&adt_to=" + adt_to ;	// 파라미터 셋팅
		
		
		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/deliveryGridList.do?" + param}).trigger("reloadGrid", [{page:1}]);	// 호출
	}
	

	/**
	*	인쇄 버튼 클릭시 인쇄 전용 jqGrid를 생성한다.
	*/
	function gridPrint(){
		
		if(!saleActionFlag){
			alert("조회결과가 없습니다.");
			return;
		}
		
		var grid = $("#grid_list");
		var adt_from = $("#adt_from").val();
		var adt_to = $("#adt_to").val();
		var page = $('.ui-pg-input').val();
		var rows=0;
		if(page==1){
			rows = grid.getCol('ymd',true,'count')-1;
		}else{
			rows = grid.getCol('ymd',true,'count');
		}
		
		$('#delivery').html('<table id=\"grid_list_clone\"></table>');
		
		
		var param = "adt_from=" + adt_from + "&adt_to=" + adt_to;	// 파라미터 셋팅
		
		/* 프린트를 하기위해 grid 재생성 */
		$("#grid_list_clone").jqGrid({
			url:"<%=ONLINE_ROOT%>/order/deliveryGridList.do?" + param,
			mtype:"post",
			datatype:"json",
			colNames:["배송일자","배송처","품목","단위","수량","단가","공급가액","세액","합계금액","수금","잔액"],
			colModel:[
						{name:"ymd",		index:"ymd",		align:"center",	width:100 },						        //일자
						{name:"rcust_nm",	index:"rcust_nm ",	align:"left",	width:150 },						        //납품처
						{name:"item_nm",	index:"item_nm", 	align:"left",	width:300},							        //품목
						{name:"standard",	index:"standard", 	align:"center",	width:55,},							        //단위
						{name:"qty",		index:"qty", 		align:"center", width:55, formatter:Commons.integerFmt},	//수량
						{name:"danga",		index:"danga", 		align:"right", 	width:55, formatter:Commons.integerFmt},	//단가
						{name:"amt",		index:"amt", 		align:"right",  width:100, formatter:Commons.integerFmt},	//공급가액
						{name:"vat",		index:"vat", 		align:"right",	width:100, formatter:Commons.integerFmt},	//세액
						{name:"tot",		index:"tot",		align:"right",	width:100, formatter:Commons.integerFmt},	//합계금액
						{name:"sukum",		index:"sukum", 		align:"right",	width:100, formatter:Commons.integerFmt},	//수금
						{name:"rem_amt",	index:"rem_amt",	align:"right",	width:100, formatter:Commons.integerFmt}	//잔액
					],
			page: page,
			rowNum: rows,
			rownumbers: true, 
			loadComplete: function(data){
				Commons.extraAction(saleActionFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', 'delivery');
			}
   		});
		
		
	}
	</script>
	
</head>
<body>
	<form name="frm" id="frm">
			
		<!-- ##### content start ##### -->
		<div class="wrap_search">
			<div class="search">
				<label>조회기간</label>
				<p class="wrap_date">
					<input type="text" id="adt_from" name="adt_from" maxlength="10"/>
					<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				~
				<p class="wrap_date">
					<input type="text" id="adt_to" name="adt_to" maxlength="10"/>
					<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				<div class="wrap_search_btn">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "ct_")%>
				</div>
			</div>
		</div>
		
		<div class="wrap_btn_group">
			<div class="btn_group ta_r">
				<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
			</div>
		</div>
		
		<div class="inner_cont2">
			<div class="wrap_result_group">
				<table id="grid_list"></table>
				<div id="grid_Pager"></div>
			</div>
			<div class="wrap_result_group">
				<div class="result_group">
					<div class="float_r">
						<label class="point">공급가액</label>
						<input type="text" id="amt" readonly class="w100 ta_right ipt_disable" />
						<label class="point ml10">세액</label>
						<input type="text" id="vat" readonly class="w100 ta_right ipt_disable" />
						<label class="point ml10">공급총액</label>
						<input type="text" id="tot" readonly class="w100 ta_right ipt_disable" />
						<label class="point ml10">수금</label>
						<input type="text" id="sukum" readonly class="w100 ta_right ipt_disable" />
					</div>
				</div>
			</div>
		</div>
		<!-- ##### content end ##### -->
		
		<div id="delivery" style="display:none;"><table id="grid_list_clone"></table></div>
	</form>
	<%@include file="/include/footer.jsp"%>
</body>
</html>