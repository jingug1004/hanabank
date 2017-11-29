<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : orderList.jsp 
 * @메뉴명 : 온라인발주 > 주문검색       
 * @최초작성일 : 2014/10/29            
 * @author : 우정아                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.hanaph.saleon.member.vo.LoginUserVO" %>
<%@ page import="com.hanaph.saleon.order.vo.OrderVO" %>
<%
	/*
	*	주문 등록에 필요한 거래처 정보 셋팅
	*/
	OrderVO orderInit = new OrderVO();
	orderInit = (OrderVO)request.getAttribute("orderInit");
	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/formCheck.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/order.js"></script>
	
	<script type="text/javascript">
	var saleActionFlag = false;		//기능(엑셀, 인쇄) 제어를 위한 전역변수
	var loadFlag = false;			//더블클릭방지 flag	
	
	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리. */
		jsonReader : {
            repeatitems: false;
    	}
		
		/* 주문 detail jqGrid 생성 및 options 설정. */
		$("#grid_list2").jqGrid({
			mtype:"post",
			datatype:"json",
			colNames:["제품코드","제품명","규격","요청수량","승인수량","단가","공급가액","세액","공급총액"],
			colModel:[
				{name:"item_id",		index:"item_id",		align:"center",		width:120,	key:true },												//제품코드
				{name:"item_nm",		index:"item_nm ",		align:"left",		width:400	},														//제품명
				{name:"standard",		index:"standard", 		align:"center",		width:120	},														//규격
				{name:"req_qty",		index:"req_qty", 		align:"center", 	width:100	},														//주문수량(요청)
				{name:"qty",			index:"qty", 			align:"center", 	width:100	},														//주문수량(승인)
				{name:"danga",			index:"danga", 			align:"right",  	width:120,	formatter:Order.amountFormatter },						//단가
				{name:"amt",			index:"amt", 			align:"right",		width:120,	formatter:Order.amountFormatter },						//공급가액
				{name:"vat",			index:"vat",			align:"right",		width:150,	formatter:Order.amountFormatter },						//세액
				{name:"tot_amt",		index:"tot_amt", 		align:"right",		width:150,	formatter:Order.amountFormatter }						//공급총액
			],
			rowNum: -1,
			height:300,
			autowidth:true,
			loadComplete: function(data){	//조회 완료시 호출되는 function
				if(data.records != 0){		//조회결과가 있을 경우
					
					/* 주문 마스터의 status가 접수일때는 수량으로, 승인/반려일 경우네는 승인수량으로 */
					var status = $("#grid_list").getRowData($("#grid_list").getGridParam("selrow")).status;		//선택된 주문 마스터의 상태값
					if('승인' == status){
						$("#grid_list2").showCol("qty");
					} else {
						$("#grid_list2").hideCol("qty");
					}
					$("#grid_list2").setGridWidth($('.wrap_result_group').width(), true);			//grid 영역의 넓이가 동적으로 조절 
					
					/* 하단 총액 계산 */
					footerSum();			
				}
			}			
		});

		Commons.setDate('fr_date','to_date');	//주문요청일 오늘날짜로 set

		var param = "fr_date="+$("#fr_date").val()+"&to_date="+$("#to_date").val()+"&receipt_gb=1&receipt_gb=2&receipt_gb=3";	//주문 master 조회 파라메터  set
		
		/* 주문 master jqGrid 생성 및 options 설정. */
		$("#grid_list").jqGrid({
			url : "<%=ONLINE_ROOT%>/order/orderGridList.do?"+param,
			mtype:"post",
			datatype:"json",
			colNames:["판매처","판매처명","접수번호","주문요청일","주문상태","주문종류","주문승인일","승인번호","비고","담보약속"],
			colModel:[
				{name:"rcust_id",	index:"rcust_id ",	align:"center",		width:100	},			//판매처
				{name:"rcust_nm",	index:"rcust_nm", 	align:"left",		width:250	},			//판매처명
				{name:"gumae_no",	index:"gumae_no", 	align:"center", 	width:150,	key:true},	//접수번호
				{name:"ymd",		index:"ymd",		align:"center",		width:120	},			//주문요청일
				{name:"status",		index:"status", 	align:"center", 	width:80	},			//주문상태
				{name:"gumae_gb",	index:"gumae_gb", 	align:"center",  	width:100	},			//주문종류
				{name:"app_ymd",	index:"app_ymd", 	align:"center",  	width:120	},			//주문승인일
				{name:"app_no",		index:"app_no", 	align:"center",  	width:150	},			//승인번호
				{name:"bigo",		index:"bigo", 		align:"left",		width:300,	editable: true, editype : "text", formatter:"textbox"	},	//요청사항
				{name:"pbigo",		index:"pbigo", 		align:"left",		width:300}
			],
			rowNum: -1,
			onSelectRow: detailOrderGrid,				//row를 클릭등으로 selection할 때 detailOrderGrid 함수를 실행함
			height:200,
			autowidth:true,
			rownumbers: true, 
			loadComplete: function(data){				//조회 완료시 호출되는 function
				if(data.records == 0){					//조회결과가 없을 경우
					alert("해당 주문자료가 없습니다.");
					saleActionFlag=false;
				}else{									//조회결과가 있을 경우
					saleActionFlag=true;
					$("#grid_list").setSelection(data.rows[0].gumae_no);
				}
				
			}			
   		});
		
		/* 조회 버튼 클릭 */
		$("#ct_retrieve_btn_type01").on("click",function(){
			masterOrderGrid();
		});
		
		/* 인쇄 버튼 클릭 */
		$("#p_print").on("click",function(){
			gridPrint();
		});
		
		/* 엑셀 버튼 클릭 */
		$("#p_excel").on("click",function(){
			Commons.extraAction(saleActionFlag, 'excel', '<%=ONLINE_ROOT%>/order/orderGridListExcelDown.do', '');
		});
		
		/* 닫기 버튼 클릭 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('주문검색');
		});
		
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
    $(window).resize(function(){
		$("#grid_list").setGridWidth($('.wrap_result_group').width(), false);			//grid 영역의 넓이가 동적으로 조절 
		$("#grid_list2").setGridWidth($('.wrap_result_group').width(), false);			//grid 영역의 넓이가 동적으로 조절 
	});

    /* 화면의 모든 객체가 모두 로드되었을 때 */
	$(window).load(function(){
		/* 엔터키 눌렸을 경우 조회되도록. */
		if($("#ct_retrieve_btn_type01").prop('disabled') == false){
			$("body").on("keydown", function(event){
				if(event.keyCode==13 && event.target.name!="grid_count"){
					masterOrderGrid();
					return false;
				}
			});
		}
	});
	
	/**
	*	조회버튼 클릭시
	*/
	function masterOrderGrid(){

		var fr_date = $('#fr_date');
		if(!formCheck.isDateFormat(fr_date.val())){
			alert('주문요청일 형식(YYYY-MM-DD)을 확인해주세요.');
			fr_date.focus();
			return;
		}
       	
       	var to_date = $('#to_date');
       	if(!formCheck.isDateFormat(to_date.val())){
			alert('주문요청일 형식(YYYY-MM-DD)을 확인해주세요.');
			to_date.focus();
			return;
		}
		
       	if (formCheck.dateChk(fr_date.val(),to_date.val())<0) {
			alert('조회 종료일이 조회 시작일보다 이전일자입니다.');
			fr_date.focus();
			return;
		}

      	//값 초기화
    	saleActionFlag = false;
       	
   		//파라미터 셋팅
   		var param = $('#frm').serialize();
   		
   		// 주문 detail jqgrid 호출
   		$("#grid_list").jqGrid('clearGridData');
   		$("#grid_list2").jqGrid('clearGridData');
   		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/orderGridList.do?" + param}).trigger("reloadGrid");	

	}
	
	/**
	*	주문 master row 선택했을 때
	*/
	function detailOrderGrid(id){
		
		var grid = $("#grid_list");
		var ymd = grid.getRowData(id).ymd.replace(/-/gi,"");
       	var gumae_no = grid.getRowData(id).gumae_no.replace(/-/gi,"");
 
		//파라미터 셋팅
		var param = "ymd=" + ymd +"&gumae_no=" + gumae_no;
		// 주문 detail jqgrid 호출
		$("#grid_list2").jqGrid('clearGridData');
		$("#grid_list2").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/detailGridList.do?" + param}).trigger("reloadGrid");
		
	}
	
	/**
	*	하단 총액 계산
	*/
	function footerSum(){
		
		//변수생성
		var dGrid = $("#grid_list2");

		//detail grid 생성 후 아래 하단 총액 계산 
		var amt=0;
		var vat=0;
		var total_amt=0;
		
		
		//grid 하단 총 공급가액, 세액, 공급총액 구하는 방식
		var col_amt = dGrid.getCol('amt', true);
		var col_vat = dGrid.getCol('vat', true);
		var col_tot_amt = dGrid.getCol('tot_amt', true);
		
		for(var colInt=0; colInt < col_amt.length; colInt++){
			if(col_amt[colInt].value!=''){
				var str_amt = (col_amt[colInt].value).replace(/,/gi,"");
				var str_vat = (col_vat[colInt].value).replace(/,/gi,"");
				var str_tot_amt = (col_tot_amt[colInt].value).replace(/,/gi,"");
				amt += parseInt(str_amt);
				vat += parseInt(str_vat);
				total_amt += parseInt(str_tot_amt);
			}
		}
		
		//grid 하단 공급가액, 세액, 공급총액, 주문 가능액 value set
		$('#amt').val(Commons.addComma(amt));
		$('#vat').val(Commons.addComma(vat));
		$('#tot_amt').val(Commons.addComma(total_amt));
		
	}
	
	
	/**
	*	프린트를 하기위해 grid 재생성
	*/
	function gridPrint(){
		
		$('#orderList').html('<table id=\"grid_list_clone\"></table>');
		
		//파라미터 셋팅
		var param = $('#frm').serialize();
		
		$("#grid_list_clone").jqGrid({
			url:"<%=ONLINE_ROOT%>/order/orderGridList.do?" + param,
			mtype:"post",
			datatype:"json",
			colNames:["판매처","판매처명","접수번호","주문요청일","주문상태","주문종류","주문승인일","승인번호","비고"],
			colModel:[
				{name:"rcust_id",	index:"rcust_id ",	align:"center",		width:100	},			//판매처
				{name:"rcust_nm",	index:"rcust_nm", 	align:"left",		width:250	},			//판매처명
				{name:"gumae_no",	index:"gumae_no", 	align:"center", 	width:150,	key:true},	//접수번호
				{name:"ymd",		index:"ymd",		align:"center",		width:120	},			//주문요청일
				{name:"status",		index:"status", 	align:"center", 	width:80	},			//주문상태
				{name:"gumae_gb",	index:"gumae_gb", 	align:"center",  	width:100	},			//주문종류
				{name:"app_ymd",	index:"app_ymd", 	align:"center",  	width:120	},			//주문승인일
				{name:"app_no",		index:"app_no", 	align:"center",  	width:150	},			//승인번호
				{name:"bigo",		index:"bigo", 		align:"left",		width:300,	editable: true, editype : "text", formatter:"textbox"	}	//요청사항
			],
			rowNum: -1,
			height:200,
			rownumbers: true, 
			loadComplete: function(data){
				Commons.extraAction(saleActionFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', 'orderList');
			}			
   		});		
	}
	
	</script>
	
</head>
<body>
	<form id="frm" name="frm" method="post">
		
		<!-- ##### content start ##### -->
		<div class="wrap_search">
			<div class="search">
				<label class="w70">주문요청일</label>
				<p class="wrap_date">
					<input type="text" id="fr_date" name="fr_date" maxlength="10"/>
					<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				~
				<p class="wrap_date">
					<input type="text" id="to_date" name="to_date" maxlength="10"/>
					<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				
				<label class="ml10">주문상태</label>
				<input type="checkbox" id="status1" name="receipt_gb" value="1" checked="checked"/>
				<span>접수</span>
				<input type="checkbox" id="status2" name="receipt_gb" value="2" checked="checked"/>
				<span>승인</span>
				<input type="checkbox" id="status3" name="receipt_gb" value="3" checked="checked"/>
				<span>반려</span>
				
				<div class="wrap_search_btn">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "ct_")%>
				</div>
			</div>
			
		</div>
			
		<div class="wrap_btn_group">
			<div class="btn_group">
				<div class="float_r ta_r">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
				</div>
			</div>
		</div>
		
		<div class="inner_cont2">
			<div class="wrap_result_group">
				<table id="grid_list" id="orderList"></table>
			</div>
			
			<div class="wrap_result_group">
				<table id="grid_list2"></table>
			</div>
			<div class="wrap_result_group">
				<div class="result_group">
					<div class="float_r">
						<label class="point">공급가액</label>
						<input type="text" class="w100 ta_right ipt_disable" id="amt" readonly/>
						<label class="point ml10">세액</label>
						<input type="text" class="w100 ta_right ipt_disable" id="vat" readonly/>
						<label class="point ml10">공급총액</label>
						<input type="text" class="w100 ta_right ipt_disable" id="tot_amt" readonly/>
					</div>
				</div>
			</div>
		</div>
		<!-- ##### content end ##### -->
		
	</form>
	<div id="orderList" style="display:none;">
		<table id="grid_list_clone"></table>
	</div>
	<form id="itemFrm" name="itemFrm">
	</form>
	<%@include file="/include/footer.jsp"%>
</body>
</html>