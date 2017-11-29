<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : customerLedgerList.jsp
 * @메뉴명 : 영업관리 > 거래처원장조회
 * @최초작성일 : 2014/11/14            
 * @author : 윤범진                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%@ page import="com.hanaph.saleon.business.vo.BusinessVO" %>
<%
	/* 사용자 정보 셋팅(부서코드, pda 권한) */
	BusinessVO commonEmpInfo = new BusinessVO();
	commonEmpInfo = (BusinessVO)request.getAttribute("commonEmpInfo");
	
	boolean isNull = true; // null flag
	String dept_cd = ""; // 부서코드
	String pda_auth = ""; // pda 권한
	
	if(null != commonEmpInfo) { // null이 아닐 경우
		dept_cd = commonEmpInfo.getDept_cd();
		pda_auth = commonEmpInfo.getPda_auth();
		isNull = false;
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/formCheck.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/business.js"></script>
	<script type="text/javascript">
	
	var customerLedgerActionFlag = false; // 거래처원장조회 부가기능(인쇄, 엑셀다운로드) 사용 가능 여부 flag
	var indirectLedgerActionFlag = false; // 간납처원장조회 부가기능(인쇄, 엑셀다운로드) 사용 가능 여부 flag
	
	$(document).ready(function(){
		
		/* 사원 정보 존재 여부 */
		if (<%=isNull%>) {
			alert("사원정보 자료가 없습니다.");
		}
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리.  */
	    jsonReader : {
            repeatitems: false;
    	}
	
		/* 거래처 원장 목록 grid*/
		$("#grid_list_customerLedger").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["일자","납품처", "품목", "단위", "수량", "단가", "공급가액", "세액", "합계금액", "수금", "잔액", "매출할인", "비고"],
			colModel:[
						{name:"ymd",		index:"ymd", 		align:"center", width:80},
						{name:"rcust_nm",	index:"rcust_nm", 	align:"left", width:150},
						{name:"item_nm",	index:"item_nm", 	align:"left", width:150},
						{name:"standard",	index:"standard", 	align:"left", width:60},
						{name:"qty",		index:"qty", 		align:"right", width:50,	formatter:amountFormatter},
						{name:"danga",		index:"danga", 		align:"right", width:100,	formatter:amountFormatter},
						{name:"amt",		index:"amt", 		align:"right", width:100,	formatter:amountFormatter},
						{name:"vat",		index:"vat", 		align:"right", width:100,	formatter:amountFormatter},
						{name:"tot",		index:"tot", 		align:"right", width:100,	formatter:amountFormatter},
						{name:"sukum",		index:"sukum", 		align:"right", width:100,	formatter:amountFormatter},
						{name:"rem_amt",	index:"rem_amt", 	align:"right", width:100,	formatter:amountFormatter},
						{name:"dc_amt",		index:"dc_amt", 	align:"right", width:100,	formatter:amountFormatter},
						{name:"bigo",		index:"bigo", 		align:"left", width:200}
					],
			height:231,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40,50],
			
			/* 1 row 생성 될 때 마다 호출 */
			afterInsertRow:function(rowid, rowdata , rowelem){
				$("#grid_list_customerLedger").jqGrid("setCell",rowid,"rem_amt","",{color:"#0000FF"});
			},
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if (data.records > 0) {
					if(data.page==1){
						var datarow = {
							ymd : "",
							rcust_nm : "",
							item_nm : "[ 이월잔액 ]",
							standard : "",
							qty : "",
							danga : "",
							amt : "",
							vat : "",
							tot : "",
							sukum : "",
							rem_amt : data.rows[0].before_amt,
							dc_amt : "",
							bigo : ""
						}; // 이월잔액 정보
						$("#grid_list_customerLedger").addRowData(0,datarow,"first"); // list 맨 처음에 넣어준다.
					}
					
					/* 합계정보 */
					var amt = data.totalCountInfo.total_amt;
					var vat = data.totalCountInfo.total_vat;
					var tot = Number(amt) + Number(vat);
					var sukum = data.totalCountInfo.total_sukum;
					var dc_amt = data.totalCountInfo.total_dc_amt;
					
					$("#customer_amt").val(Commons.addComma(amt)); // 공급가액
					$("#customer_vat").val(Commons.addComma(vat)); // 세액
					$("#customer_tot").val(Commons.addComma(tot)); // 공급총액
					$("#customer_sukum").val(Commons.addComma(sukum)); // 수금액
					$("#customer_dc_amt").val(Commons.addComma(dc_amt)); // 할인액
					
					customerLedgerActionFlag = true; // 인쇄, 엑셀 사용 가능하도록
					
				} else {
					alert("조회 결과가 없습니다.");
					customerLedgerActionFlag = false;
				}
			},
			
			pager: "#grid_Pager_customerLedger"
		});
		
		/* 간납처 원장 목록 grid */
		$("#grid_list_indirectLedger").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["일자","거래처", "품목", "단위", "수량", "단가", "공급가액", "세액", "합계금액", "수금", "잔액", "매출할인", "비고"],
			colModel:[
						{name:"ymd",		index:"ymd", 		align:"center", width:80},
						{name:"rcust_nm",	index:"rcust_nm", 	align:"left", width:150},
						{name:"item_nm",	index:"item_nm", 	align:"left", width:150},
						{name:"standard",	index:"standard", 	align:"left", width:60},
						{name:"qty",		index:"qty", 		align:"right", width:50,	formatter:amountFormatter},
						{name:"danga",		index:"danga", 		align:"right", width:100,	formatter:amountFormatter},
						{name:"amt",		index:"amt", 		align:"right", width:100,	formatter:amountFormatter},
						{name:"vat",		index:"vat", 		align:"right", width:100,	formatter:amountFormatter},
						{name:"tot",		index:"tot", 		align:"right", width:100,	formatter:amountFormatter},
						{name:"sukum",		index:"sukum", 		align:"right", width:100,	formatter:amountFormatter},
						{name:"rem_amt",	index:"rem_amt", 	align:"right", width:100,	formatter:amountFormatter},
						{name:"dc_amt",		index:"dc_amt", 	align:"right", width:100,	formatter:amountFormatter},
						{name:"bigo",		index:"bigo", 		align:"left", width:200}
					],
			height:231,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40,50],

			/* 1 row 생성 될 때 마다 호출 */
			afterInsertRow:function(rowid, rowdata , rowelem){
				$("#grid_list_indirectLedger").jqGrid("setCell",rowid,"rem_amt","",{color:"#0000FF"});
			},
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if (data.records > 0) {
					if(data.page==1){
						var datarow = {
							ymd : "",
							rcust_nm : "",
							item_nm : "[ 이월잔액 ]",
							standard : "",
							qty : "",
							danga : "",
							amt : "",
							vat : "",
							tot : "",
							sukum : "",
							rem_amt : data.rows[0].before_amt,
							dc_amt : "",
							bigo : ""
						}; // 이월잔액 정보
						$("#grid_list_indirectLedger").addRowData(0,datarow,"first");  // list 맨 처음에 넣어준다.
					}
					
					/* 합계정보 */
					var amt = data.totalCountInfo.total_amt;
					var vat = data.totalCountInfo.total_vat;
					var tot = Number(amt) + Number(vat);
					var sukum = data.totalCountInfo.total_sukum;
					var dc_amt = data.totalCountInfo.total_dc_amt;
					
					$("#indirect_amt").val(Commons.addComma(amt)); // 공급가액
					$("#indirect_vat").val(Commons.addComma(vat)); // 세액
					$("#indirect_tot").val(Commons.addComma(tot)); // 공급총액
					$("#indirect_sukum").val(Commons.addComma(sukum)); // 수금액
					$("#indirect_dc_amt").val(Commons.addComma(dc_amt)); // 할인액
					
					indirectLedgerActionFlag = true; // 인쇄, 엑셀 사용 가능하도록
					
				} else {
					alert("조회 결과가 없습니다.");
					indirectLedgerActionFlag = false;
				}
			},
			
			pager: "#grid_Pager_indirectLedger"
		});
	
		/* 거래처 목록 grid */
		$("#grid_list_customer").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["거래처코드","거래처명","부서명","사원명"],
			colModel:[
						{name:"cust_id",	index:"cust_id", 	align:"center"},
						{name:"cust_nm",	index:"cust_nm", 	align:"left"},
						{name:"dept_nm",	index:"dept_nm", 	align:"left"},
						{name:"sawon_nm",	index:"sawon_nm", 	align:"center"}
					],
			height:231,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			autowidth:true,
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if (data.records > 0) {
					if(data.page == 1){
						$("#grid_list_customer").setSelection(1, true); // 첫번째 row 선택
					}
				} else {
					alert("조회 결과가 없습니다.");
					$("#grid_list_customerLedger").jqGrid("clearGridData"); // 거래처원장목록 grid 영역 초기화
				}
			},
			
			/* row 선택 시 */
			onSelectRow: function(){
				var id = $("#grid_list_customer").jqGrid('getGridParam','selrow'); // 현재 선택한 row의 id
				var ret = $("#grid_list_customer").jqGrid('getRowData',id); // 해당 id를 갖고 있는 row의 정보
				getCustomerLedgerGridList(ret.cust_id); // 거래처원장목록 조회
			},
			
			pager: "#grid_Pager_customer"
   		});
		
		/* 간납처 목록 grid */
		$("#grid_list_indirect").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["간납처코드","거래처명","부서명","사원명"],
			colModel:[
						{name:"cust_id",	index:"cust_id", 	align:"center"},
						{name:"cust_nm",	index:"cust_nm", 	align:"left"},
						{name:"dept_nm",	index:"dept_nm", 	align:"left"},
						{name:"sawon_nm",	index:"sawon_nm", 	align:"center"}
					],
			height:231,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			autowidth:true,
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if (data.records > 0) {
					if(data.page == 1){
						$("#grid_list_indirect").setSelection(1, true); // 첫번째 row 선택
					}
				} else {
					alert("조회 결과가 없습니다.");
					$("#grid_list_indirectLedger").jqGrid("clearGridData"); // 간납처원장목록 grid 영역 초기화
				}
				
			},
			
			/* row 선택 시 */
			onSelectRow: function(){
				var id = $("#grid_list_indirect").jqGrid('getGridParam','selrow'); // 현재 선택한 row의 id
				var ret = $("#grid_list_indirect").jqGrid('getRowData',id); // 해당 id를 갖고 있는 row의 정보
				getCustomerLedgerGridList(ret.cust_id); // 간납처원장목록 조회
			},
			
			pager: "#grid_Pager_indirect"
   		});
		
		/* 조회 버튼 클릭 */
		$("#ct_retrieve_btn_type02").on("click",function(){
			getCustomerGridList();
		});
		
		/* 인쇄 버튼 클릭 */
		$("#p_print").on("click",function(){
			goExtraAction('print');
		});
		
		/* 엑셀 버튼 클릭 */
		$("#p_excel").on("click",function(){
			goExtraAction('excel');
		});
		
		/* 닫기 버튼 클릭 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('거래처원장조회');
		});
		
		/* 조회일자 셋팅(이번 달 1일 ~ 오늘) */
		Commons.setDate("ad_fr_date", "ad_to_date");
		
		/* grid 영역 넓이 셋팅 */
		$("#grid_list_customer").setGridWidth($("#customerLedger").width() , true);
		$("#grid_list_indirect").setGridWidth($("#customerLedger").width() , true);
		$("#grid_list_customerLedger").setGridWidth($("#customerLedger").width() , false);
		$("#grid_list_indirectLedger").setGridWidth($("#customerLedger").width() , false);
		
		/* 엔터키 눌렸을 경우 조회되도록. */
		$("body").on("keydown", function(event){
			if($("#ct_retrieve_btn_type02").prop('disabled') == false){
				if(event.keyCode==13 && event.target.name!="grid_count"){
					getCustomerGridList();
				return false;
				}
			}
		});
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
	$(window).resize(function(){
		$("#grid_list_customer").setGridWidth($("#customerLedger").width() , true);
		$("#grid_list_indirect").setGridWidth($("#customerLedger").width() , true);
		$("#grid_list_customerLedger").setGridWidth($("#customerLedger").width() , false);
		$("#grid_list_indirectLedger").setGridWidth($("#customerLedger").width() , false);
	});
	
	/* 거래처, 간납처 목록조회 */
	function getCustomerGridList(){
		var selectType = $("#selectType option:selected").val();
		var ad_fr_date = $("#ad_fr_date").val();
		var ad_to_date = $("#ad_to_date").val();
		var as_fr_cust = $("#as_fr_cust").val();
		var as_to_cust = $("#as_to_cust").val();
		var as_dept_cd = $("#as_dept_cd").val();
		var as_pda_auth = $("#as_pda_auth").val();
		
		/* 유효성 체크 */
		if (!formCheck.isDateFormat(ad_fr_date)) {
			alert("조회 기간을 입력하세요!");
			$("#ad_fr_date").focus();
			return false;
		}
		
		if (!formCheck.isDateFormat(ad_to_date)) {
			alert("조회 기간을 입력하세요!");
			$("#ad_to_date").focus();
			return false;
		}
		
		if (formCheck.dateChk(ad_fr_date, ad_to_date) < 0) {
			alert("조회 기간을 확인하세요!");
			return false;
		}
		
		/* 파라미터 셋팅 */
		var params = {
			selectType : selectType,
			ad_fr_date : ad_fr_date,
			ad_to_date : ad_to_date,
			as_fr_cust : as_fr_cust,
			as_to_cust : as_to_cust,
			as_dept_cd : as_dept_cd,
			as_pda_auth : as_pda_auth
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list_" + selectType).jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/customerGridList.do?" + paramStr}).trigger("reloadGrid", [{page:1}]);
	}
	
	/* 거래처, 간납처 원장목록조회 */
	function getCustomerLedgerGridList(cust_id){
		var selectType = $("#selectType option:selected").val();
		var ad_fr_date = $("#ad_fr_date").val();
		var ad_to_date = $("#ad_to_date").val();
		
		/* 엑셀 다운로드 용 인자 셋팅 */
		$("#as_cust_id_" + selectType).val(cust_id);
		
		/* 유효성 체크 */
		if (!formCheck.isDateFormat(ad_fr_date)) {
			alert("올바른 조회 기간을 입력해주세요.");
			$("#ad_fr_date").focus();
			return false;
		}
		
		if (!formCheck.isDateFormat(ad_to_date)) {
			alert("올바른 조회 기간을 입력해주세요.");
			$("#ad_to_date").focus();
			return false;
		}
		
		if (formCheck.dateChk(ad_fr_date, ad_to_date) < 0) {
			alert("조회 기간을 확인하세요!");
			return false;
		}
		
		/* 파라미터 셋팅 */
		var params = {
			selectType : selectType,
			ad_fr_date : ad_fr_date,
			ad_to_date : ad_to_date,
			as_cust_id : cust_id
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list_" + selectType + "Ledger").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/customerLedgerGridList.do?" + paramStr}).trigger("reloadGrid", [{page:1}]);
	}
	
	/* 금액 커스텀 포맷 */
	function amountFormatter(cellvalue){
		var returnValue = "";
		if ("0" != cellvalue && "" != cellvalue) {
			returnValue = Math.round(cellvalue);
			returnValue = Commons.addComma(returnValue);
		}
		
		return returnValue.toString();
	}
	
	/* 조회유형에 따른 grid 영역 교체 */
	function changeType(value) {
		$(".gridArea").hide();
		$("#" + value + "Ledger").show();
		
		if (!formCheck.isEmpty($("#as_fr_cust").val())) {
			getCustomerGridList();
		}
	}
	
	/* 인쇄, 엑셀 실행 */
	function goExtraAction(type) {
		var selectType = $("#selectType option:selected").val()
		var selectFlag = false;
		var url = "";
		
		/* 현재 선택한 조회타입에 따라 실행 가능 여부 셋팅 */
		if ("customer" == selectType) {
			selectFlag = customerLedgerActionFlag; 
		} else if ("indirect" == selectType) {
			selectFlag = indirectLedgerActionFlag;
		}
		
		/* 현재 선택한 기능에 따라 대상 url 셋팅 */
		if ("print" == type) {
			
			var id = $("#grid_list_"+selectType).jqGrid('getGridParam','selrow'); // 현재 선택한 row의 id
			var ret = $("#grid_list_"+selectType).jqGrid('getRowData',id); // 해당 id를 갖고 있는 row의 정보
			
			var ad_fr_date = $("#ad_fr_date").val();
			var ad_to_date = $("#ad_to_date").val();
			
			var param = "ad_fr_date=" + ad_fr_date + "&ad_to_date=" + ad_to_date + "&selectType=" + selectType + "&as_cust_id=" + ret.cust_id;	// 파라미터 셋팅
			
			url = "<%=ONLINE_ROOT%>/business/ledgerListPrintPopup.do?" + param;
		} else if ("excel" == type) {
			url = "<%=ONLINE_ROOT%>/business/ledgerGridListExcelDown.do";
		}
		
		Commons.extraAction(selectFlag, type, url, selectType);
	}
	
	</script>
</head>

<body>
	
	<form name="frm" id="frm">
		<input type="hidden" name="as_cust_id_customer" id="as_cust_id_customer" />
		<input type="hidden" name="as_cust_id_indirect" id="as_cust_id_indirect" />
		<input type="hidden" name="as_dept_cd" id="as_dept_cd" value="<%=dept_cd %>" />
		<input type="hidden" name="as_pda_auth" id="as_pda_auth" value="<%=pda_auth %>" />
		
		<div class="wrap_search">
			<div class="search">
				<div class="float_l">
					<label class="w70">주문일</label>
					<p class="wrap_date">
						<input type="text" maxlength="10" id="ad_fr_date" name="ad_fr_date" />
						<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					~
					<p class="wrap_date">
						<input type="text" maxlength="10" id="ad_to_date" name="ad_to_date" />
						<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					<label class="ml10">구분</label>
					<select id="selectType" class="w100" name="selectType" onchange="javascript:changeType(this.value);">
						<option value="customer">거래처</option>
						<option value="indirect">간납처</option>
					</select><br />
					
					<label class="w70">거래처</label>
					<input type="text" maxlength="10" class="w140" id="as_fr_cust" name="as_fr_cust" onblur="javascript:Business.getCustomerName(this.id);"/>
					<button type="button" class="btn_search" onclick="javascript:Commons.popupOpen('as_fr_cust', '<%=ONLINE_ROOT%>/business/common/customerList.do', '600', '655');" ><span class="blind">찾기</span></button>
					<input type="text" class="w230 ipt_disable" id="as_fr_cust_name" name="as_fr_cust_name" readonly />
				</div>
				
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
			<div class="wrap_result_group gridArea" id="customerLedger" style="display:block;">
				<!-- 거래처 목록 -->
				<div class="customerLedger" style="margin-bottom:15px;">
					<table id="grid_list_customer"></table>
					<div id="grid_Pager_customer"></div>
				</div>
				<!-- 거래처 원장 목록 -->
				<table id="grid_list_customerLedger"></table>
				<div id="grid_Pager_customerLedger" class="customerLedger"></div>
				
				<div class="wrap_result_group customerLedger">
					<div class="result_group">
						<div class="float_r">
							<label class="point">공급가액</label>
							<input type="text" class="w100 ta_right ipt_disable" id="customer_amt" readonly />
							<label class="point ml10">세액</label>
							<input type="text" class="w100 ta_right ipt_disable" id="customer_vat" readonly />
							<label class="point ml10">공급총액</label>
							<input type="text" class="w100 ta_right ipt_disable" id="customer_tot" readonly />
							<label class="point ml10">수금액</label>
							<input type="text" class="w100 ta_right ipt_disable" id="customer_sukum" readonly />
							<label class="point ml10">할인액</label>
							<input type="text" class="w100 ta_right ipt_disable" id="customer_dc_amt" readonly />
						</div>
					</div>
				</div>
			</div>
			
			<div class="wrap_result_group gridArea" id="indirectLedger" style="display:none;">
				<!-- 간납처 목록 -->
				<div class="indirectLedger" style="margin-bottom:15px;">
					<table id="grid_list_indirect"></table>
					<div id="grid_Pager_indirect"></div>
				</div>
				<!-- 간납처 원장 목록 -->
				<table id="grid_list_indirectLedger"></table>
				<div id="grid_Pager_indirectLedger" class="indirectLedger"></div>
				
				<div class="wrap_result_group indirectLedger">
					<div class="result_group">
						<div class="float_r">
							<label class="point">공급가액</label>
							<input type="text" class="w100 ta_right ipt_disable" id="indirect_amt" readonly />
							<label class="point ml10">세액</label>
							<input type="text" class="w100 ta_right ipt_disable" id="indirect_vat" readonly />
							<label class="point ml10">공급총액</label>
							<input type="text" class="w100 ta_right ipt_disable" id="indirect_tot" readonly />
							<label class="point ml10">수금액</label>
							<input type="text" class="w100 ta_right ipt_disable" id="indirect_sukum" readonly />
							<label class="point ml10">할인액</label>
							<input type="text" class="w100 ta_right ipt_disable" id="indirect_dc_amt" readonly />
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<%@include file="/include/footer.jsp"%>
</body>
</html>