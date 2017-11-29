<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : orderApprovalSearch.jsp
 * @메뉴명 : 영업관리 > 주문/승인 조회
 * @최초작성일 : 2014/12/26            
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
	
	var searchActionFlag = false; // 판매현황 부가기능(인쇄, 엑셀다운로드) 사용 가능 여부 flag
	
	$(document).ready(function(){
		
		/* 사원 정보 존재 여부 */
		if (<%=isNull%>) {
			alert("사원정보 자료가 없습니다.");
		}
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리.  */
	    jsonReader : {
            repeatitems: false;
    	}
	
		/* 상세 목록 grid */
		$("#grid_list_detail").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["제품코드","제품명", "규격", "요청수량", "승인수량", "단가", "공급가액", "세액", "공급총액", "사전 %", "할인단가", "할인액", "색상"],
			colModel:[
						{name:"item_id",	index:"item_id",	align:"center", width:50},
						{name:"item_nm",	index:"item_nm", 	align:"left", width:150},
						{name:"standard",	index:"standard",	align:"left", width:60},
						{name:"req_qty",	index:"req_qty",	align:"right", width:50},
						{name:"qty",		index:"qty",		align:"right", width:50},
						{name:"danga",		index:"danga", 		align:"right", width:100, formatter:amountFormatter},
						{name:"amt",		index:"amt", 		align:"right", width:100, formatter:amountFormatter},
						{name:"vat",		index:"vat", 		align:"right", width:100, formatter:amountFormatter},
						{name:"tot_amt",	index:"tot_amt",	align:"right", width:100, formatter:amountFormatter},
						{name:"rate",		index:"rate", 		align:"right", width:50},
						{name:"dc_danga",	index:"dc_danga",	align:"right", width:100, formatter:amountFormatter},
						{name:"dc_amt",		index:"dc_amt",		align:"right", width:100, formatter:amountFormatter},
						{name:"color",		index:"color",		hidden:true}
					],
			height:231,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* row 추가될때마다 실행 */
			afterInsertRow:function(rowid, rowdata , rowelem){
				if (rowdata.color == "R") {
					$("#grid_list_detail tr[id=" + rowid + "]").css('color', 'red');
				}
			},
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if (data.records > 0) {
					var amt = data.totalCountInfo.total_amt;
					var vat = data.totalCountInfo.total_vat;
					var tot_amt = data.totalCountInfo.total_tot_amt;
					var dc_amt = data.totalCountInfo.total_dc_amt;
					
					$("#amt").val(Commons.addComma(amt)); // 공급가액
					$("#vat").val(Commons.addComma(vat)); // 세액
					$("#tot_amt").val(Commons.addComma(tot_amt)); // 공급총액
					$("#dc_amt").val(Commons.addComma(dc_amt)); // 할인액
					
					searchActionFlag = true; // 인쇄, 엑셀 사용 가능하도록
					
				} else {
					alert("조회 결과가 없습니다.");
					searchActionFlag = false;
				}
			},
			
			pager: "#grid_Pager_detail"
		});
	
		/* 승인조회 목록 grid */
		$("#grid_list_approval").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["주문승인일","승인일시","거래처","거래처명","판매처","판매처명","승인번호","승인구분","승인사번","승인사원명","주문요청일","접수번호","주문종류","요청사항","승인/반려사유"],
			colModel:[
						{name:"app_ymd",		index:"app_ymd", 		align:"center", width:80},
						{name:"app_time",		index:"app_time", 		align:"center"},
						{name:"cust_id",		index:"cust_id", 		align:"center", width:60},
						{name:"cust_nm",		index:"cust_nm", 		align:"left", width:150},
						{name:"rcust_id",		index:"rcust_id", 		align:"center", width:60},
						{name:"rcust_nm",		index:"rcust_nm", 		align:"left", width:150},
						{name:"app_no",			index:"app_no", 		align:"center", width:110, formatter:Commons.gumaeNo},
						{name:"status",			index:"status", 		align:"center", width:60},
						{name:"asawon_id",		index:"asawon_id", 		align:"center", width:60},
						{name:"asawon_nm",		index:"asawon_nm", 		align:"left", width:60},
						{name:"ymd",			index:"ymd", 			align:"center", width:80},
						{name:"gumae_no",		index:"gumae_no", 		align:"center", width:110, formatter:Commons.gumaeNo},
						{name:"gumae_gb",		index:"gumae_gb",	 	align:"center", width:60},
						{name:"bigo",			index:"bigo",			align:"left", width:200},
						{name:"return_desc",	index:"return_desc",	align:"left", width:200}
					],
			height:231,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if (data.records > 0) {
					if(data.page == 1){
						$("#grid_list_approval").setSelection(1, true); // 첫번째 row 선택
					}
				} else {
					alert("조회 결과가 없습니다.");
					$("#grid_list_detail").jqGrid("clearGridData"); // 상세 영역 초기화
				}
			},
			
			/* row 선택 시 */
			onSelectRow: function(){
				var id = $("#grid_list_approval").jqGrid('getGridParam','selrow'); // 현재 선택한 row의 id
				var ret = $("#grid_list_approval").jqGrid('getRowData',id); // 현재 row의 정보
				
				getOrderApprovalDetailGridList(ret.ymd, ret.gumae_no); // 상세 내역 목록 조회
			},
			
			pager: "#grid_Pager_approval"
   		});
		
		/* 주문조회 목록 grid */
		$("#grid_list_order").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["주문요청일","거래처","거래처명","판매처","판매처명","주문시간","접수번호","위반주문","팀장승인","주문상태","주문종류","비고"],
			colModel:[
						{name:"ymd",					index:"ymd", 					align:"center", width:80},
						{name:"cust_id",				index:"cust_id", 				align:"center", width:60},
						{name:"cust_nm",				index:"cust_nm", 				align:"left", width:150},
						{name:"rcust_id",				index:"rcust_id",			 	align:"center", width:60},
						{name:"rcust_nm",				index:"rcust_nm",			 	align:"left", width:150},
						{name:"req_time",				index:"req_time",			 	align:"center", width:90},
						{name:"gumae_no",				index:"gumae_no",			 	align:"center", width:110, formatter:Commons.gumaeNo},
						{name:"wiban_order_req_yn",		index:"wiban_order_req_yn",		align:"center", width:60, formatter:wibanFormatter},
						{name:"wiban_order_conf_yn",	index:"wiban_order_conf_yn", 	align:"center", width:60, formatter:confirmFormatter},
						{name:"status",					index:"status",					align:"center", width:60},
						{name:"gumae_gb",				index:"gumae_gb",				align:"center", width:60},
						{name:"return_desc",			index:"return_desc",			align:"left", width:200}
					],
			height:231,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if (data.records > 0) {
					if(data.page == 1){
						$("#grid_list_order").setSelection(1, true); // 첫번째 row 선택
					}
				} else {
					alert("조회 결과가 없습니다.");
					$("#grid_list_detail").jqGrid("clearGridData"); // 상세 영역 초기화
				}
				
			},
			
			/* row 선택 시 */
			onSelectRow: function(){
				var id = $("#grid_list_order").jqGrid('getGridParam','selrow'); // 현재 선택한 row의 id
				var ret = $("#grid_list_order").jqGrid('getRowData',id); // 현재 row의 정보
				
				getOrderApprovalDetailGridList(ret.ymd, ret.gumae_no); // 상세 내역 목록 조회
			},
			
			pager: "#grid_Pager_order"
   		});
		
		/* 조회 버튼 클릭 */
		$("#ct_retrieve_btn_type02").on("click",function(){
			getOrderApprovalSearchGridList();
		});
		
		/* 인쇄 버튼 클릭 */
		$("#p_print").on("click",function(){
			var selectType = $("#selectType option:selected").val();
			Commons.extraAction(searchActionFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', selectType);
		});
		
		/* 엑셀 버튼 클릭 */
		$("#p_excel").on("click",function(){
			Commons.extraAction(searchActionFlag, 'excel', '<%=ONLINE_ROOT%>/business/orderApprovalSearchGridListExcelDown.do', '');
		});
		
		/* 닫기 버튼 클릭 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('주문/승인 조회');
		});
		
		/* 조회일자 셋팅(이번 달 1일 ~ 오늘) */
		Commons.setDate("adt_fr_date", "adt_to_date");
		
		/* grid 영역 넓이 셋팅 */
		var width = $("#order").width();
		
		$("#grid_list_order").setGridWidth(width , false);
		$("#grid_list_approval").setGridWidth(width , false);
		$("#grid_list_detail").setGridWidth(width , false);
		
		/* 엔터키 눌렸을 경우 조회되도록. */
		$("body").on("keydown", function(event){
			if($("#ct_retrieve_btn_type02").prop('disabled') == false){
				if(event.keyCode==13 && event.target.name!="grid_count"){
					getOrderApprovalSearchGridList();
					return false;
				}
			}
		});
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
	$(window).resize(function(){
		var width = $("#order").width();
		
		$("#grid_list_order").setGridWidth(width , false);
		$("#grid_list_approval").setGridWidth(width , false);
		$("#grid_list_detail").setGridWidth(width , false);
	});
	
	/* 주문/승인 목록조회 */
	function getOrderApprovalSearchGridList(){
		var selectType = $("#selectType option:selected").val();
		var adt_fr_date = $("#adt_fr_date").val();
		var adt_to_date = $("#adt_to_date").val();
		
		/* 유효성 체크 */
		if (!formCheck.isDateFormat(adt_fr_date)) {
			alert("조회 기간을 입력하세요!");
			$("#ad_fr_date").focus();
			return false;
		}
		
		if (!formCheck.isDateFormat(adt_to_date)) {
			alert("조회 기간을 입력하세요!");
			$("#ad_to_date").focus();
			return false;
		}
		
		if (formCheck.dateChk(adt_fr_date, adt_to_date) < 0) {
			alert("조회 기간을 확인하세요!");
			return false;
		}
		
		/* 파라미터 셋팅 */
		var paramStr = $("#frm").serialize();
		
		/* 호출 */
		$("#grid_list_" + selectType).jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/orderApprovalSearchGridList.do?" + paramStr}).trigger("reloadGrid", [{page:1}]);
	}
	
	/* 주문/승인 상세 목록 조회 */
	function getOrderApprovalDetailGridList(ymd, gumae_no){
		/* 파라미터 셋팅 */
		var params = {
			ymd : ymd,
			gumae_no : gumae_no,
			selectType : $("#selectType option:selected").val()
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list_detail").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/orderApprovalDetailGridList.do?" + paramStr}).trigger("reloadGrid", [{page:1}]);
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
	
	/* 위반주문 커스텀 포맷 */
	function wibanFormatter(cellvalue){
		var returnValue = "";
		if ("N" == cellvalue) {
			returnValue = "정상";
		} else if ("Y" == cellvalue) {
			returnValue = "위반";
		}
		
		return returnValue;
	}
	
	/* 팀장승인 커스텀 포맷 */
	function confirmFormatter(cellvalue){
		var returnValue = "";
		if ("0" == cellvalue) {
			returnValue = "대기";
		} else if ("1" == cellvalue) {
			returnValue = "승인";
		} else if ("2" == cellvalue) {
			returnValue = "반려";
		}
		
		return returnValue;
	}
	
	/* 조회유형에 따른 grid 영역 교체 */
	function changeType(value) {
		
		if ("order" == value) { // 주문 조회
			$(".type_order").css("display", "inline-block");
			$(".type_approval").css("display", "none");
		} else if ("approval" == value) { // 승인 조회
			$(".type_approval").css("display", "inline-block");
			$(".type_order").css("display", "none");
		}
		
		$(".gridArea").hide();
		$("#" + value).show();
		
		$("#grid_list_detail").jqGrid("clearGridData"); // 상세영역 초기화
	}
	
	/* 담보약속 팝업 */
	function openPromisePopup(){
		if (!searchActionFlag) { // 조회 결과가 없을 경우
			alert("조회 결과가 없습니다.");
			return false;
		}
		
		var selectType = $("#selectType option:selected").val();
		var id = $("#grid_list_" + selectType).jqGrid('getGridParam','selrow');
		var ret = $("#grid_list_" + selectType).jqGrid('getRowData',id);
		var cust_id = ret.cust_id;
		
		if ("" == cust_id) {
			alert("주문내역을 선택해주세요.");
			return false;
		}
		
		Commons.popupOpen('promise', '<%=ONLINE_ROOT%>/business/common/promiseList.do', '600', '655');
	}
	
	</script>
</head>

<body>
	<div class="wrap_search">
		<form name="frm" id="frm">
		<input type="hidden" name="as_dept_cd" id="as_dept_cd" value="<%=dept_cd %>" />
		<input type="hidden" name="as_pda_auth" id="as_pda_auth" value="<%=pda_auth %>" />
		
		<div class="search">
			<div class="float_l">
				<label class="w70 type_order" style="display:inline-block;">주문일</label>
				<label class="w70 type_approval" style="display:none;">승인일</label>
				<p class="wrap_date">
					<input type="text" maxlength="10" id="adt_fr_date" name="adt_fr_date" />
					<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				~
				<p class="wrap_date">
					<input type="text" maxlength="10" id="adt_to_date" name="adt_to_date" />
					<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				
				<label class="ml10">조회구분</label>
				<select id="selectType" class="w100" name="selectType" onchange="javascript:changeType(this.value);">
					<option value="order">주문조회</option>
					<option value="approval">승인조회</option>
				</select>
				
				<div class="type_order" style="display:inline-block;">
					<label class="ml10">주문상태</label>
					<select id="as_receipt_gb" class="w100" name="as_receipt_gb">
						<option value="0">전체</option>
						<option value="1">접수</option>
						<option value="2">승인</option>
						<option value="3">반려</option>
					</select>
				</div>
				
				<div class="type_approval" style="display:none;">
					<label class="ml10">승인구분</label>
					<select id="as_receipt_gb2" class="w100" name="as_receipt_gb2">
						<option value="">전체</option>
						<option value="2">승인</option>
						<option value="3">반려</option>
					</select>
				</div>
				
				<label class="ml10">여신규정</label>
				<select id="as_limit_yn" class="w100" name="as_limit_yn">
					<option value="A">전체</option>
					<option value="N">이내</option>
					<option value="Y">초과</option>
					<option value="E">예외</option>
				</select><br />
				
				<label class="w70">주문구분</label>
				<select id="as_slip_gb" class="w100" name="as_slip_gb">
					<option value="1">온라인 주문</option>
					<option value="2">PDA 주문</option>
				</select>
				
				<label class="ml10">거래처</label>
				<input type="text" maxlength="10" class="w140" id="as_cust_id" name="as_cust_id" onblur="javascript:Business.getCustomerName(this.id);"/>
				<button type="button" class="btn_search" onclick="javascript:Commons.popupOpen('as_cust_id', '<%=ONLINE_ROOT%>/business/common/customerList.do', '600', '655');" ><span class="blind">찾기</span></button>
				<input type="text" class="w200 ipt_disable" id="as_cust_id_name" name="as_cust_id_name" readonly />
				
				<div class="type_order" style="display:inline-block;">
					<label class="ml10">주문위반</label>
					<select id="as_wiban_order_req_yn" class="w100" name="as_wiban_order_req_yn">
						<option value="%">전체</option>
						<option value="N">정상</option>
						<option value="Y">위반</option>
					</select>
				</div>
				
				<div class="type_order" style="display:inline-block;">
					<label class="ml10">팀장반려</label>
					<select id="as_wiban_order_conf_yn" class="w100" name="as_wiban_order_conf_yn">
						<option value="%">전체</option>
						<option value="0">대기</option>
						<option value="1">승인</option>
						<option value="2">반려</option>
					</select>
				</div>
				
				<div class="type_approval" style="display:none;">
					<label class="ml10">담보약속</label>
					<button type="button" class="btn_search" onclick="javascript:openPromisePopup();" ><span class="blind">찾기</span></button>
				</div>
			</div>
			
			<div class="wrap_search_btn">
				<%=WebUtil.createButtonArea(currPgmNoByUri, "ct_")%>
			</div>
		</div>
		</form>
	</div>
	
	<div class="wrap_btn_group">
		<div class="btn_group ta_r">
			<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
		</div>
	</div>
	
	<div class="inner_cont2">
		<div class="wrap_result_group">
			<!-- 주문 조회 -->
			<div id="order" class="gridArea" style="margin-bottom:15px; display:block;">
				<table id="grid_list_order"></table>
				<div id="grid_Pager_order" class="order"></div>
			</div>
			<!-- 승인 조회 -->
			<div id="approval" class="gridArea" style="margin-bottom:15px; display:none;">
				<table id="grid_list_approval"></table>
				<div id="grid_Pager_approval" class="approval"></div>
			</div>
			
			<!-- 상세 -->
			<table id="grid_list_detail"></table>
			<div id="grid_Pager_detail"></div>
			
			<div class="wrap_result_group">
				<div class="result_group">
					<div class="float_r">
						<label class="point">공급가액</label>
						<input type="text" class="w100 ta_right ipt_disable" id="amt" readonly />
						<label class="point ml10">세액</label>
						<input type="text" class="w100 ta_right ipt_disable" id="vat" readonly />
						<label class="point ml10">공급총액</label>
						<input type="text" class="w100 ta_right ipt_disable" id="tot_amt" readonly />
						<label class="point ml10">할인액</label>
						<input type="text" class="w100 ta_right ipt_disable" id="dc_amt" readonly />
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/include/footer.jsp"%>
</body>
</html>