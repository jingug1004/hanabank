<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : orderApproval.jsp
 * @메뉴명 : 영업관리 > 주문승인
 * @최초작성일 : 2014/12/05            
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
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/formCheck.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/business.js"></script>
	<script type="text/javascript">
	
	var orderApprovalFlag = false; // 주문승인 부가기능(인쇄, 엑셀다운로드) 사용 가능 여부 flag
	var lastsel = ""; // 마지막으로 선택한 row
	var orderDetailArr = []; // 주문 상세 수정사항을 담을 배열
	var isWorking = false; // 저장 프로세스 수행 여부
	
	$(document).ready(function(){
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리.  */
	    jsonReader : {
            repeatitems: false;
    	}
	
		/* 담보 약속 목록 grid */
		$("#grid_list_promise").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["주문요청일","약속기일","상태","담보약속내용","반려사유"],
			colModel:[
						{name:"req_date",		index:"req_date",		width:75, align:"center"},
						{name:"promise_date",	index:"promise_date",	width:75, align:"center"},
						{name:"status",			index:"status",			width:60, align:"center"},
						{name:"promise_bigo",	index:"promise_bigo", 	width:150, align:"left"},
						{name:"return_desc",	index:"return_desc",	width:150, align:"left"}
					],
			height:261,
			rownumWidth : 30,
			rowNum: -1,
			rownumbers: true,
		});
	
		/* 주문 내역 상세 목록 grid */
		$("#grid_list_orderDetail").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["코드","품목","규격","요청수량", "승인수량","단가","공급가액","세액","공급총액","사전(%)","할인액","주문한도","해당 월","초과수량","담당자","금액오류","요청일","접수번호","등록번호","색상","할인단가"],
			colModel:[
						{name:"item_id",	index:"item_id", 	align:"center", width:50},
						{name:"item_nm",	index:"item_nm", 	align:"left", width:150},
						{name:"standard",	index:"standard", 	align:"center", width:60},
						{name:"req_qty",	index:"req_qty", 	align:"right", width:50},
						{name:"qty",		index:"qty", 		align:"right", width:50,	editable:true, edittype:"text", editoptions:Commons.jqgridEditOptions("grid_list_orderDetail", "Y", calcAmount)},
						{name:"danga",		index:"danga", 		align:"right", width:100,	formatter:"integer"},
						{name:"amt",		index:"amt", 		align:"right", width:100,	formatter:"integer"},
						{name:"vat",		index:"vat", 		align:"right", width:100,	formatter:"integer"},
						{name:"tot_amt",	index:"tot_amt", 	align:"right", width:100,	formatter:"integer"},
						{name:"rate",		index:"rate", 		align:"right", width:50},
						{name:"dc_amt",		index:"dc_amt", 	align:"right", width:100,	formatter:"integer"},
						{name:"mavg_qty",	index:"mavg_qty", 	align:"right", width:50},
						{name:"mqty",		index:"mqty", 		align:"right", width:50},
						{name:"psb_qty",	index:"psb_qty", 	align:"right", width:50},
						{name:"sawon_nm",	index:"sawon_nm", 	align:"center", width:50},
						{name:"error_gb_d",	index:"error_gb_d", align:"center", width:50},
						{name:"ymd",		index:"ymd", 		hidden:true},
						{name:"gumae_no",	index:"gumae_no", 	hidden:true},
						{name:"input_seq",	index:"input_seq", 	hidden:true},
						{name:"color",		index:"color",		hidden:true},
						{name:"dc_danga",	index:"dc_danga",	hidden:true}
					],
			formatter : {
				integer : {thousandsSeparator: ",", defaultValue: '0'} // 천단위 마다 콤마
	        },
	        height:261,
			rownumWidth : 30,
			rowNum: -1,
			rownumbers: true,
			
			/* row 추가될때마다 실행 */
			afterInsertRow:function(rowid, rowdata , rowelem){
				if (rowdata.color == "1") {
					$("#grid_list_orderDetail tr[id=" + rowid + "]").css('color', 'red');
				} else if (Number(rowdata.psb_qty < 0)) {
					$("#grid_list_orderDetail tr[id=" + rowid + "]").css('color', 'blue');
				}
				if (rowdata.error_gb_d == "오류") {
					$("#grid_list_orderDetail tr[id=" + rowid + "]").css('color', 'red');
				}  
			}, 
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				//현재 조회된 상세데이터의 거래처명 추가
				try{
					var order_id = $("#grid_list_order").jqGrid('getGridParam','selrow'); // 현재 선택한 row의 id
					var ret = $("#grid_list_order").jqGrid('getRowData',order_id); // 현재 row의 정보
					$('#spanDetailName').html(" (" + ret.cust_id + " " + ret.cust_nm+")");		
				}catch(e){
					$('#spanDetailName').html("");
				}
				
				
				if (data.records > 0) {
					
					/* 결과내용 */
					var total_amt = data.totalCountInfo.total_amt;
					var total_vat = data.totalCountInfo.total_vat;
					var total_tot_amt = data.totalCountInfo.total_tot_amt;
					var reamt = data.totalCountInfo.reamt == "" ? 0 : data.totalCountInfo.reamt;
					
					$("#reamt").text(Commons.addComma(reamt));
					$("#total_amt").text(Commons.addComma(total_amt));
					$("#total_vat").text(Commons.addComma(total_vat));
					$("#total_tot_amt").text(Commons.addComma(total_tot_amt));
					
					/* 참조사항 */
					var input_ymd = data.approvalInfo.input_ymd; // 주문일시
					var sawon_nm = data.approvalInfo.sawon_nm; // 거래처 담당자
					var dept_nm = data.approvalInfo.dept_nm; // 거래처 담당자 부서
					var wiban_conf_dtm = data.approvalInfo.wiban_conf_dtm; // 팀장 승인
					var rsawon_nm = data.approvalInfo.rsawon_nm; // 판매처 담당자
					var rdept_nm = data.approvalInfo.rdept_nm; // 판매처 담당자 부서
					var credit_limit_amt = data.customerInfo.credit_limit_amt; // 여신한도
					var before_amt = data.customerInfo.before_amt; // 전월잔고
					//var dambo = data.customerInfo.dambo == 0 ? "" : data.customerInfo.dambo; // 연대보증인
					var yeondae_3 = data.customerInfo.yeondae_3; // 연대보증(담보예외)
					var yeondae_2 = data.customerInfo.yeondae_2 == "Y" ? "O" : "X"; // 연대보증(제3자) 연대보증인에 제3자체크된 거래처의 경우 O 를 아니면 X 표시
					var sale_amt = data.customerInfo.sale_amt; // 금월판매
					var sale_dambo_amt = data.customerInfo.sale_dambo_amt; // 담보확보액
					var cash_amt = data.customerInfo.cash_amt; // 전월수금
					var cur_amt = data.customerInfo.cur_amt; // 현잔고
					var bill_kind = data.customerInfo.bill_kind; // 담보종류
					var bill_cnt = data.customerInfo.bill_cnt; // 담보갯수
					var jasu_amt = data.customerInfo.jasu_amt; // 미도래(자수)
					var tasu_amt = data.customerInfo.tasu_amt; // 미도래(타수)
					var tot_credit = Number(cur_amt) + Number(jasu_amt); // 총 여신
					var control_rate_day = data.approvalInfo.control_rate_day; // 회전일
					var credit_rate = "0.00%"; // 담보확보율
					
					if ((Number(cur_amt) + Number(jasu_amt)) > 0) { // 분모가 0이 아닐 경우 연산
						credit_rate = String(((Number(sale_dambo_amt) / (Number(cur_amt) + Number(jasu_amt))) * 100).toFixed(2)) + "%";
					}
					
					if (bill_kind != "" && bill_cnt > 2) { // 담보가 다수일 경우
						bill_kind += " 外 " + (parseInt(bill_cnt,10) - 1) + "건";
					}
					
					$("#input_ymd").text(input_ymd.substring(0, (input_ymd.length-2)));
					$("#sawon_nm").text(sawon_nm);
					$("#dept_nm").text(dept_nm);
					$("#wiban_conf_dtm").text(wiban_conf_dtm);
					$("#rsawon_nm").text(rsawon_nm);
					$("#rdept_nm").text(rdept_nm);
					$("#credit_limit_amt").text(Commons.addComma(credit_limit_amt));
					$("#before_amt").text(Commons.addComma(before_amt));
					$("#yeondae_2").text(yeondae_2);
					$("#sale_amt").text(Commons.addComma(sale_amt));
					
					/* 담보확보액에 담보예외거래처의 경우 금액대신 "담보예외"  를 보여줄것 */
					if ("Y" == yeondae_3) {
						$("#sale_dambo_amt").text("담보예외");
					} else if ("N" == yeondae_3){
						$("#sale_dambo_amt").text(Commons.addComma(sale_dambo_amt));
					}
					
					$("#cash_amt").text(Commons.addComma(cash_amt));
					$("#credit_rate").text(credit_rate);
					$("#cur_amt").text(Commons.addComma(cur_amt));
					$("#bill_kind").text(bill_kind);
					$("#jasu_amt").text(Commons.addComma(jasu_amt));
					$("#tasu_amt").text(Commons.addComma(tasu_amt));
					$("#tot_credit").text(Commons.addComma(tot_credit));
					$("#control_rate_day").text(Commons.addComma(control_rate_day));
					
					orderDetailArr = []; // 배열 초기화
				}
			},
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				if(id){
					var order_id = $("#grid_list_order").jqGrid('getGridParam','selrow'); // 현재 선택한 row의 id
					var ret = $("#grid_list_order").jqGrid('getRowData',order_id); // 현재 row의 정보
					
					if ("접수" == ret.receipt_gb && $("#chk_order_" + order_id).prop("checked")) { // 마스터 주문이 접수상태이고, checked 경우에만 승인수량 수정 가능하도록
						$("#grid_list_orderDetail").jqGrid("saveRow",lastsel); // 수정폼 저장
						$("#grid_list_orderDetail").jqGrid("restoreRow",lastsel);
						
						$("#grid_list_orderDetail").jqGrid("editRow",id,true);
						
						if(lastsel != id && lastsel != ""){
							calcAmount(lastsel);	
						}
							
						lastsel=id;
							
						Business.addArray(orderDetailArr, id); // update 배열에 현재 id를 넣는다.	
					}
				}
			},
			
		});
		
		/* 주문 내역 grid */
		$("#grid_list_order").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["<input type='checkbox' id='chk_header' onclick='checkBox(event, this)'/>","승인상태","주문요청일","승인일","거래처","거래처명","판매처","판매처명","간납구분","접수번호","승인번호","요청사항","작업일시","승인자", "금액오류","회전일 위반 주문 여부", "회전일 위반 주문 승인 여부", "주문구분"],
			colModel:[
						{name:"chk",					index:"chk", 					align:"center", sortable:false, width:20, fixed:true, formatter:orderCheckbox},
						{name:"receipt_gb",				index:"receipt_gb", 			align:"center", width:60, formatter:receiptGbFormatter},
						{name:"ymd",					index:"ymd", 					align:"center", width:80},
						{name:"ymdt",					index:"ymdt", 					align:"center", width:80},
						{name:"cust_id",				index:"cust_id", 				align:"center", width:60},
						{name:"cust_nm",				index:"cust_nm", 				align:"left", width:150},
						{name:"rcust_id",				index:"rcust_id", 				align:"center", width:60},
						{name:"rcust_nm",				index:"rcust_nm", 				align:"left", width:150},
						{name:"rcust_gb1",				index:"rcust_gb1", 				align:"center", width:70},
						{name:"gumae_no",				index:"gumae_no", 				align:"center", width:110, formatter:Commons.gumaeNo},
						{name:"app_no",					index:"app_no", 				align:"center", width:110, formatter:Commons.gumaeNo, hidden:true},
						{name:"bigo",					index:"bigo", 					align:"left", width:200},
						{name:"adate",					index:"adate", 					align:"center", width:150},
						{name:"asawon_nm",				index:"asawon_nm", 				align:"center", width:50},
						{name:"error_gb",				index:"error_gb", 			align:"center", width:50},
						{name:"wiban_order_req_yn",		index:"wiban_order_req_yn",		hidden:true},
						{name:"wiban_order_conf_yn",	index:"wiban_order_conf_yn",	hidden:true},
						{name:"slip_gb",				index:"slip_gb",				hidden:true}
					],
			height:231,
			rownumWidth : 30,
			rowNum: -1,
			rownumbers: true,
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				/* 하위 그리드 초기화 */
				$("#grid_list_orderDetail").jqGrid("clearGridData");
				$("#grid_list_promise").jqGrid("clearGridData");
				
				if (data.records > 0) {
					/* 결과내용 */
					var total_reamt = data.totalCountInfo.total_reamt == "" ? 0 : data.totalCountInfo.total_reamt; // 총 미승인액
					var total_reamt_real = data.totalCountInfo.total_reamt_real == "" ? 0 : data.totalCountInfo.total_reamt_real; // 총 미승인액(실조건)
					
					//alert("total_reamt_real:"  + total_reamt);
					//alert("total_reamt_real:"  + total_reamt_real);
					
					$("#total_cnt").text(data.records);
					$("#total_reamt").text(Commons.addComma(total_reamt));
					$("#total_reamt_real").text(Commons.addComma(total_reamt_real));
					$("#grid_list_order").setSelection(1, true);
					
					orderApprovalFlag = true; // 인쇄, 엑셀 사용 가능하도록
				} else {
					alert("조회 결과가 없습니다.");
					$("#total_cnt").text("0");
					orderApprovalFlag = false;
				}
			},
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				var ret = $("#grid_list_order").getRowData(id); // 현재 선택한 row
				
				$("#wiban_order_req_yn").text(ret.wiban_order_req_yn); // 회전일 위반 주문 여부
				$("#wiban_order_conf_yn").text(ret.wiban_order_conf_yn); // 회전일 위반 주문 승인 여부
				
				getOrderDetail(ret); // 상세 호출
				getPromiseGridList(ret); // 담보약속 목록 호출	 
				
			}, 
			
			/* row 추가될때마다 실행 */
			afterInsertRow:function(rowid, rowdata , rowelem){
				if (rowdata.error_gb == "오류") {
					$("#grid_list_order tr[id=" + rowid + "]").css('color', 'red');
				}  
			},
			
   		});
		
		/* 조회 버튼 클릭 */
		$("#ct_retrieve_btn_type03").on("click",function(){
			getOrderApprovalGridList();
		});
		
		/* 저장 버튼 클릭 */ 
		$("#p_save_appro_result_btn").on("click",function(){
			goUpdateApproval();
		});
		
		/* 인쇄 버튼 클릭 */
		$("#p_print_appro_result_btn").on("click",function(){
			Commons.extraAction(orderApprovalFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', 'orderApproval');
		});
		
		/* 엑셀 버튼 클릭 */
		$("#p_excel_appro_result_btn").on("click",function(){
			Commons.extraAction(orderApprovalFlag, 'excel', '<%=ONLINE_ROOT%>/business/orderApprovalGridListExcelDown.do', '');
		});
		
		/* 닫기 버튼 클릭 */
		$("#p_close_appro_result_btn").on("click",function(){
			parent.Commons.closeTab('주문승인');
		});
		
		/* 조회일자 셋팅 */
		setDays("adt_fr_date", "adt_to_date", "as_app_date"); // 주문기간, 승인일
		Business.setThisMonth("as_avg_month"); // 월평균주문월
		
		/* grid 영역 넓이 셋팅 */
		var width = document.documentElement.clientWidth - $("#grid_list_order").parents('td').next().width() - ($("#grid_list_order").parents('table').offset().left * 2) - 4;
		
		$("#grid_list_order").setGridWidth(width, false);
		$("#grid_list_orderDetail").setGridWidth(width, false);
		$("#grid_list_promise").setGridWidth(356, false);
		
		/* 엔터키 눌렸을 경우 조회되도록. */
		$("body").on("keydown", function(event){
			if($("#ct_retrieve_btn_type03").prop('disabled') == false){
				if(event.keyCode==13 && event.target.name!="grid_count"){
					getOrderApprovalGridList();
				return false;
				}
			}
		});
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
	$(window).resize(function(){
		/* 그리드 영역 width */
		var width = document.documentElement.clientWidth - $("#grid_list_order").parents('td').next().width() - ($("#grid_list_order").parents('table').offset().left * 2) - 4;
		
		$("#grid_list_order").setGridWidth(width, false);
		$("#grid_list_orderDetail").setGridWidth(width, false);
	});
	
	/* 승인상태 포맷 */
	function receiptGbFormatter(cellvalue){
		var returnValue = "";
		
		if ("1" == cellvalue) {
			returnValue = "접수";
		} else if ("2" == cellvalue) {
			returnValue = "승인";
		} else if ("3" == cellvalue) {
			returnValue = "반려";
		}
		
		return returnValue;
	}
	
	/* 헤더 체크박스 클릭 시 이벤트 */
	function checkBox(e, obj) 
	{ 
	   e = e||event;/* get IE event ( not passed ) */ 
	   e.stopPropagation? e.stopPropagation() : e.cancelBubble = true;
	   
	   /* 전체 선택 여부에 따라 하위 체크박스 check */
	   $("input:checkbox[name='chk_order']").prop("checked", obj.checked);
	}
	
	/* checkbox 포맷 */
	function orderCheckbox(cellvalue, rowObject){
		var returnValue = "<input type='checkbox' name='chk_order' id='chk_order_"+rowObject.rowId+"' value='"+rowObject.rowId+"' onclick='javascript:getOrderDetailByRowId(\""+rowObject.rowId+"\");'/>";
		return returnValue;
	}
	
	
	/* 주문내역 검색 */
	function getOrderApprovalGridList(){
		/* 유효성 체크 */
		if (!formCheck.isDateFormat($("#adt_fr_date").val())) { // 조회기간 fr
			alert("조회 기간을 입력하세요!");
			$("#adt_fr_date").focus();
			return false;
		}
		
		if (!formCheck.isHour($("#adt_fr_hour"))) { // 조회시간 fr
			alert("올바른 시간을 입력하세요!");
			$("#adt_fr_hour").focus();
			return false;
		}
		
		if (!formCheck.isMinutes($("#adt_fr_minute"))) { // 조회분 fr
			alert("올바른 시간을 입력하세요!");
			$("#adt_fr_minute").focus();
			return false;
		}
		
		if (!formCheck.isDateFormat($("#adt_to_date").val())) { // 조회기간 to
			alert("조회 기간을 입력하세요!");
			$("#adt_to_date").focus();
			return false;
		}
		
		if (!formCheck.isHour($("#adt_to_hour"))) { // 조회시간 to
			alert("올바른 시간을 입력하세요!");
			$("#adt_to_hour").focus();
			return false;
		}
		
		if (!formCheck.isMinutes($("#adt_to_minute"))) { // 조회분 to
			alert("올바른 시간을 입력하세요!");
			$("#adt_to_minute").focus();
			return false;
		}
		
		if (formCheck.timeChk("adt_fr", "adt_to") < 0) { // 조회기간 비교
			alert("조회기간을 확인하세요.");
			return false;
		}
		
		/* 파라미터 셋팅 */
		var paramStr = $("#frm").serialize();
		
		/* 헤더 체크박스 체크 해제 */
		$("#chk_header").attr("checked", false);
		
		/* 호출 */
		$("#grid_list_order").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/orderApprovalGridList.do?"+paramStr}).trigger("reloadGrid", [{page:1}]);
		
		/* 배열 삭제 초기화 */
		orderDetailArr = [];	
	}
	
	/* row에 선택색표시  */
	function getOrderDetailByRowId(rowId){
		$("#grid_list_order").setSelection(rowId);
	} 
	
	/* 주문세부내역 검색 */
	function getOrderDetail(obj){
		
		$("#detailDiv").html(""); // 폼 초기화
		
		/* 파라미터 셋팅 */
		var params = {
			adt_fr_date : $("#adt_fr_date").val(),
			adt_fr_hour : $("#adt_fr_hour").val(),
			adt_fr_minute : $("#adt_fr_minute").val(),
			adt_to_date : $("#adt_to_date").val(),
			adt_to_hour : $("#adt_to_hour").val(),
			adt_to_minute : $("#adt_to_minute").val(),
			ls_ymd : obj.ymd,
			as_cust_id : obj.cust_id,
			as_rcust_id : obj.rcust_id,
			as_gumae_no : obj.gumae_no,
			as_avg_month : $("#as_avg_month").val(),
			as_slip_gb : $("#as_slip_gb").val()
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list_orderDetail").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/orderDetail.do?" + paramStr}).trigger("reloadGrid", [{page:1}]);		
	}
	
	/* 담보약속 검색 */
	function getPromiseGridList(obj){
		
		/* 파라미터 셋팅 */
		var params = {
			adt_fr_date : $("#adt_fr_date").val(),
			adt_fr_hour : $("#adt_fr_hour").val(),
			adt_fr_minute : $("#adt_fr_minute").val(),
			adt_to_date : $("#adt_to_date").val(),
			adt_to_hour : $("#adt_to_hour").val(),
			adt_to_minute : $("#adt_to_minute").val(),
			as_cust_id : obj.cust_id
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list_promise").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/promiseGridList.do?" + paramStr}).trigger("reloadGrid", [{page:1}]);		
	}
	
	/* 거래처명, 거래처 타입 검색 */
	function getCustomerType(){
		var cust_id = $("#as_cust_id").val();
		
		if ("" != cust_id) {
			$.ajax({
				type:"POST",
				url:ONLINE_ROOT + "/business/common/customerTypeAjax.do",
				data:{ cust_id : cust_id },
				dataType:"json",
				success:function(data){
					if (null != data && "" != data.cust_type) {
						$("#as_cust_id_name").val(data.cust_type);
					} else {
						//alert("등록된 거래처 코드가 아닙니다.");
						$("#as_cust_id_name").val("");
					}
				}
			});
		} else {
			$("#as_cust_id_name").val("");
		}
	}
	
	/* 주문일, 승인일 셋팅 */
	function setDays(yesterdayInput, todayInput, tomorrowInput){
		var today = new Date();
		var yesterday = new Date(today.valueOf() - (24*60*60*1000));
		
		var year = "";
		var month = "";
		var day = "";
		
		// 주문일 셋팅
		year = yesterday.getFullYear();
		month = (yesterday.getMonth()+1)>9 ? ""+(yesterday.getMonth()+1) : "0"+(yesterday.getMonth()+1);
		
		$("#" + yesterdayInput).val(year + "-" + month + "-01");
		
		year = today.getFullYear();
		month = (today.getMonth()+1)>9 ? ""+(today.getMonth()+1) : "0"+(today.getMonth()+1);
		day = today.getDate()>9 ? ""+today.getDate() : "0"+today.getDate();
		
		$("#" + todayInput).val(year + "-" + month + "-" + day);
		
		// 승인일 셋팅
		year = today.getFullYear();
		month = (today.getMonth()+1)>9 ? ""+(today.getMonth()+1) : "0"+(today.getMonth()+1);
		day = today.getDate()>9 ? ""+today.getDate() : "0"+today.getDate();
		
		$("#" + tomorrowInput).val(year + "-" + month + "-" + day);
	}
	
	/* 승인 수량 변경 시 수량 계산 */
	function calcAmount(rowId){
		var order_id = $("#grid_list_order").jqGrid('getGridParam','selrow'); // 현재 선택한 row의 id
		var order_ret = $("#grid_list_order").jqGrid('getRowData',order_id); // 현재 row의 정보

		var ret = $("#grid_list_orderDetail").getRowData(rowId); // 수정 된 row
		var afterQty = ret.qty; // 승인수량
		
		var afterAmt = 0; // 공급가액
		var afterVat = 0; // 세액
		var afterTotAmt = 0; // 공급총액
		var afterDcAmt = 0; // 할인액
		
		//---
		/* 승인수량과 단가에 따라 금액 재계산 */
		if ("1" == order_ret.slip_gb) { // 온라인 주문일 경우
			afterAmt = (Number(afterQty) * Number(ret.danga));
			afterVat = (Number(afterAmt) * 0.1).toFixed(0);
		} else if ("2" == order_ret.slip_gb){ // PDA 주문일 경우
			afterAmt = (((Number(afterQty) * Number(ret.danga)) / 1.1) + 0.4).toFixed(0);
			afterVat = (Number(afterQty) * Number(ret.danga)) - afterAmt;
		}
		afterTotAmt = Number(afterAmt) + Number(afterVat);
		afterDcAmt = (Number(afterQty) * Number(ret.dc_danga));
		
		/* 계산 후 row에 반영 */
		$("#grid_list_orderDetail").jqGrid("setCell",rowId,"amt",afterAmt);
		$("#grid_list_orderDetail").jqGrid("setCell",rowId,"vat",afterVat);
		$("#grid_list_orderDetail").jqGrid("setCell",rowId,"tot_amt",afterTotAmt);
		$("#grid_list_orderDetail").jqGrid("setCell",rowId,"dc_amt",afterDcAmt);
		
		//--
		
		var totalList = $("#grid_list_orderDetail").getRowData();
		var totalAmt = 0;
		var totalVat = 0;
		var totalTotAmt = 0;
		if(totalList && totalList.length){
			for(var i = 0; i < totalList.length; i++){
				totalAmt += parseInt(totalList[i].amt, "10");
				totalVat += parseInt(totalList[i].vat, "10");
				totalTotAmt += parseInt(totalList[i].tot_amt, "10");
			}
		}
		
		
		/* grid 상단 합계 정보 반영 */
		$("#total_amt").text(Commons.addComma(totalAmt));
		$("#total_vat").text(Commons.addComma(totalVat));
		$("#total_tot_amt").text(Commons.addComma(totalTotAmt));
	}
	
	/* 주문 승인 */
	function goUpdateApproval(){
		
		var approvalType = $("#as_receipt_gb2").val(); // 요청 작업
		var msg = "";
		
		/* 승인 구분에 따른 alert 메세지 분기 */
		if (approvalType == "return") {
			msg = "반려";
		} else if (approvalType == "cancel_order") {
			msg = "취소";
		} else if (approvalType == "approval" || approvalType == "add_order") {
			msg = "출하";
		}
		
		if (isWorking) {
			alert(msg + "작업이 진행 중입니다.");
			return false;
		}
		
		isWorking = true; // 프로세스 진행 여부
		
		/*
		* 저장버튼 클릭시 현재 조회된 주문 detail 그리드에서 요청 수량과 변경 수량이 다를 경우
		* 승인 수량을 변경한 거므로 체크가 안 되었을 때 체크 되게 한다.  
		*/
		/* var detailRowData = $("#grid_list_orderDetail").getRowData();
		var editYn = 0;		//주문 detail 그리드 승인수량 변경 갯수
		for (var i = 0; i < detailRowData.length; i++) {
			var chgQtyVal =  $('#'+(i+1)+'_qty').size() > 0 ? $('#'+(i+1)+'_qty').val() : detailRowData[i].qty;
			if(chgQtyVal != detailRowData[i].req_qty){
				editYn++;
			}
		}
		if(editYn > 0 && $("#chk_order_" + $("#grid_list_order").getGridParam("selrow")).prop("checked") == false){		//주문 detail 그리드에서 요청 수량과 변경 수량되었을 경우
			$("#chk_order_" + $("#grid_list_order").getGridParam("selrow")).prop("checked", true);			// 주문 마스터 체크
		} */
		
		var selectedOrder = $("input:checkbox[name='chk_order']:checked"); // 승인 대상
		$("#grid_list_orderDetail").jqGrid("saveRow",lastsel); // 수정폼 저장
		$("#grid_list_orderDetail").jqGrid("restoreRow",lastsel); // 수정폼 닫기
		
		/* 유효성 체크 */
		if (selectedOrder.length == 0) {
			alert("주문내역을 선택해 주세요.");
			isWorking = false;
			return false;
		}
		
		if (!formCheck.isDateFormat($("#as_app_date").val())) {
			alert("승인 일자를 입력하세요!");
			$("#as_app_date").focus();
			isWorking = false;
			return false;
		}
		
		/* 승인수량 변경에 대한 유효성 체크 */
		for (var i = 0; i < orderDetailArr.length; i++) {
			var ret = $("#grid_list_orderDetail").getRowData(orderDetailArr[i]);
			var qty = formCheck.isNumer(ret.qty) ? "" : ret.qty; // 승인 수량
			var req_qty = ret.req_qty; // 요청 수량
			
			$("#grid_list_orderDetail").jqGrid("setCell",orderDetailArr[i],"qty",qty); // parseInt

			if (isNaN(qty) || formCheck.isEmpty(qty)) {
				alert("숫자만 입력할 수 있습니다.");
				$("#grid_list_orderDetail").jqGrid("editRow",orderDetailArr[i],true);
				lastsel = orderDetailArr[i];
				isWorking = false;
				return;
			}
			
			if (parseInt(req_qty) < parseInt(qty)) {
				alert("승인수량은 요청수량보다 클 수 없습니다.");
				$("#grid_list_orderDetail").jqGrid("editRow",orderDetailArr[i],true);
				lastsel = orderDetailArr[i];
				isWorking = false;
				return;
			}
			
			calcAmount(orderDetailArr[i]);	//로우별 금액 계산 
		}
		
		/* 주문 상세 수량이 모두 0일 경우 프로세스 중단 2015-02-03 추가 */
		if ((approvalType == "approval" || approvalType == "add_order") && $("#grid_list_orderDetail").jqGrid("getCol","qty", false, "sum") == 0) {
			alert("모든 제품 수량이 0인 경우 승인할 수 없습니다. ");
			isWorking = false;
			return false;
		}
		
		/* 출하일 확인을 위해 현재 시간 셋팅 */
		var today = new Date();
		var year = today.getFullYear();
		var month = (today.getMonth()+1)>9 ? ""+(today.getMonth()+1) : "0"+(today.getMonth()+1);
		var day = today.getDate()>9 ? ""+today.getDate() : "0"+today.getDate();
		var hour = today.getHours();
		var minute = today.getMinutes();
		var seconds = today.getSeconds();
		var currentTime = year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + seconds;
		
		if (confirm("현재 일시가 " + currentTime + " 이고,\n" + msg + " 일자가 " + $("#as_app_date").val() + " 일 입니다.\n" + msg + "하시겠습니까?")){
			/* 폼에 parameter set */
			var frm = $("#approvalFrm"); // 승인 폼
			var approvalDiv = $("#approvalDiv"); // 승인정보
			var detailDiv = $("#detailDiv"); // 상세정보
			
			/* 기본 승인 정보 */
			$("<input></input>").attr({type:"hidden", name:"receipt_gb", value: $("#as_receipt_gb2").val()}).appendTo(approvalDiv);
			$("<input></input>").attr({type:"hidden", name:"return_desc", value: $("#as_return_desc").val()}).appendTo(approvalDiv);
			$("<input></input>").attr({type:"hidden", name:"app_date", value: $("#as_app_date").val()}).appendTo(approvalDiv);
			 
			/*현재 선택된 주문의 공급총액*/
			$("<input></input>").attr({type:"hidden", name:"this_order_amt", value: $("#total_tot_amt").text()}).appendTo(approvalDiv);
			
			/* checkbox 선택한 주문 마스터 */
			selectedOrder.each(function(){
				var ret = $("#grid_list_order").getRowData($(this).val());
				$("<input></input>").attr({type:"hidden", name:"gumae_no", value: ret.gumae_no}).appendTo(approvalDiv);
				$("<input></input>").attr({type:"hidden", name:"app_no", value: ret.app_no}).appendTo(approvalDiv);
				$("<input></input>").attr({type:"hidden", name:"req_date", value: ret.ymd}).appendTo(approvalDiv);
				$("<input></input>").attr({type:"hidden", name:"cust_id", value: ret.cust_id}).appendTo(approvalDiv);
				$("<input></input>").attr({type:"hidden", name:"cust_nm", value: ret.cust_nm}).appendTo(approvalDiv);
				$("<input></input>").attr({type:"hidden", name:"rcust_id", value: ret.rcust_id}).appendTo(approvalDiv);
				$("<input></input>").attr({type:"hidden", name:"rcust_nm", value: ret.rcust_nm}).appendTo(approvalDiv);
				$("<input></input>").attr({type:"hidden", name:"slip_gb", value: ret.slip_gb}).appendTo(approvalDiv);
			});
			
			/* 주문 상세 승인 수량 변경 */
			for (var i = 0; i < orderDetailArr.length; i++) {
				var ret = $("#grid_list_orderDetail").getRowData(orderDetailArr[i]);
				
				$("<input></input>").attr({type:"hidden", name:"ymd", value: ret.ymd.replace(/-/g, "").substring(0,8)}).appendTo(detailDiv);
				$("<input></input>").attr({type:"hidden", name:"detail_gumae_no", value: ret.gumae_no}).appendTo(detailDiv);
				$("<input></input>").attr({type:"hidden", name:"input_seq", value: ret.input_seq}).appendTo(detailDiv);
				$("<input></input>").attr({type:"hidden", name:"qty", value: parseInt(ret.qty)}).appendTo(detailDiv);
				$("<input></input>").attr({type:"hidden", name:"amt", value: parseInt(ret.amt)}).appendTo(detailDiv);
				$("<input></input>").attr({type:"hidden", name:"vat", value: parseInt(ret.vat)}).appendTo(detailDiv);
				$("<input></input>").attr({type:"hidden", name:"dc_amt", value: parseInt(ret.dc_amt)}).appendTo(detailDiv);
			}
			
			$.ajax({
				type:"POST",
				data: frm.serialize(),
				url:"<%=ONLINE_ROOT%>/business/updateOrderApproval.do",
				dataType:"json",
				success:function(data){
					if (data.updateResult) { // 전체 프로세스 결과
						var msg = ""; // 결과 alert
						var successCount = 0; // 성공 row 갯수
						var failCount = 0; // 실패 row 갯수
						var errorMessageArr = []; // 실패 row 위치
						var rowResultArr = data.rowResultArr; // 각 row 별 성공실패여부 
						
						for (var i = 0; i < rowResultArr.length; i++) {
							if (rowResultArr[i]) {
								successCount++;
							} else {
								failCount++;
								errorMessageArr.push(i); // 실패한 row의 결과 저장 위치를 담는다
							}
						}
						
						msg = $("#as_receipt_gb2 option:selected").text() + " : 성공 총 " + successCount + "건, 실패 총 " + failCount + "건\n처리 되었습니다.";
						
						if (failCount > 0) { // 저장 실패한 row가 있을 경우 상세 내역 표시
							msg += "\n\n(실패사유)";
							for (var i = 0; i< errorMessageArr.length; i++) {
								msg += "\n\n" + data.resultMessageArr[errorMessageArr[i]];
							}
						}
						
						alert(msg);
						getOrderApprovalGridList();
					} else {
						alert(data.resultMessageArr[0]);
					}
					
				},complete:function(){
					approvalDiv.html(""); // 폼 초기화
					detailDiv.html(""); // 폼 초기화
					$("#chk_header").prop("checked", false); // checkbox 해제
					$("input:checkbox[name='chk_order']").prop("checked", false); // checkbox 해제
					//$("#as_return_desc").val("");  2015-04-30 초기화 안되도록 수정함
					//$("#as_receipt_gb2").find("option:first").attr("selected", "selected"); 2015-01-30 초기화 안되도록 수정함
					isWorking = false;
				}
			});
		} else {
			isWorking = false;
		}
		
	}
	
	/* 주문 구분에 따른 검색조건 변경 및 초기화 */
	function changeSlibGb(obj){
		
		$("#as_part_gb option:first").attr("selected", "selected"); // 간납구분
		$("#as_receipt_gb option:eq(1)").attr("selected", "selected"); // 처리구분
		$("#as_limit_yn option:first").attr("selected", "selected"); // 여신규정
		$("#as_wiban_kind option:first").attr("selected", "selected"); // 위반종류
		$("#as_psb_gb option:first").attr("selected", "selected"); // 수량한도
		$("#as_pre_deposit").prop("checked", false); // 선입금 거래처
		
		if ("1" == obj.value) {
			$("#limitYn").css("display", "inline-block");
			$("#wibanKind").css("display", "none");
		} else if ("2" == obj.value) {
			$("#wibanKind").css("display", "inline-block");
			$("#limitYn").css("display", "none");
		}
		
		/* selectbox 배경 색 변경 */
		obj.style.backgroundColor = obj.options[obj.selectedIndex].style.backgroundColor;
	}
	
	/* 다음날 셋팅 */
	function setNextDay(obj, target){
		var val = obj.value;
		
		if (!formCheck.isDateFormat(val)) { // 조회기간 fr
			alert("조회 기간을 입력하세요!");
			obj.focus();
			return false;
		}
		var targetVal = $("#" + target).val();
		var date_fr = new Date(val);
		var date_to = new Date(targetVal);
		if (formCheck.isDateFormat(targetVal)&&(date_fr <= date_to) ) { // 조회기간 fr 이 to 보다 적으면 return
			return;
		}
		
		var targetDay = new Date(val);
		var nextDay = new Date(Date.parse(targetDay) + 1 * 1000 * 60 * 60 * 24);
		var year = nextDay.getFullYear();
		var month = (nextDay.getMonth()+1)>9 ? ""+(nextDay.getMonth()+1) : "0"+(nextDay.getMonth()+1);
		var day = nextDay.getDate()>9 ? ""+nextDay.getDate() : "0"+nextDay.getDate();
		var nextDate = year + "-" + month + "-" + day;
		
		$("#" + target).val(nextDate);
	}
	
	/* 승인일 과거로 셋팅 불가능하도록 체크 */
	function chkAppDate(obj){
		
		var appDate = obj.value.replace(/-/gi, ""); // 타겟 날짜 

		/* 오늘날짜 */
		var today = new Date();
		var year = "";
		var month = "";
		var day = "";
		
		year = today.getFullYear();
		month = (today.getMonth()+1)>9 ? ""+(today.getMonth()+1) : "0"+(today.getMonth()+1);
		day = today.getDate()>9 ? ""+today.getDate() : "0"+today.getDate();
		
		var currentDay = year + month + day; // 오늘날짜
		
		if (Number(currentDay) - Number(appDate) > 0) {
			alert("과거일을 지정할 수 없습니다.");
			$("#as_app_date").val(year + "-" + month + "-" + day);
			return false;
		}
	}
	
	</script>
</head>

<body>
		<form name="frm" id="frm">
			<div class="wrap_search">
				<div class="search">
					<div class="float_l">
						<select class="w70" name="searchType" id="searchType">
							<option value="order">주문일</option>
							<option value="approval">승인일</option>
						</select>
						<p class="wrap_date">
							<input type="text" name="adt_fr_date" id="adt_fr_date" maxlength="10" onchange="javascript:setNextDay(this, 'adt_to_date');">
							<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						<input type="text" class="w50" name="adt_fr_hour" id="adt_fr_hour" value="00" maxlength="2"/>
						<input type="text" class="w50" name="adt_fr_minute" id="adt_fr_minute" value="00" maxlength="2"/>
						~
						<p class="wrap_date">
							<input type="text" name="adt_to_date" id="adt_to_date" maxlength="10">
							<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						<input type="text" class="w50" name="adt_to_hour" id="adt_to_hour" value="23" maxlength="2"/>
						<input type="text" class="w50" name="adt_to_minute" id="adt_to_minute" value="59" maxlength="2"/>
						
						<label class="ml10">간납구분</label>
						<select class="w125" name="as_part_gb" id="as_part_gb">
							<option value="2">전체</option>
							<option value="0">일반</option>
							<option value="1">약국분</option>
						</select>
						
						<label class="ml20">월평균주문월</label>
						<input type="text" class="w70" name="as_avg_month" id="as_avg_month" maxlength="7"/><br>
						
						<label class="w70">주문구분</label>
						<select class="w125" name="as_slip_gb" id="as_slip_gb" onchange="javascript:changeSlibGb(this);" style="background-color:#F6CEF5">
							<option value="1" style="background-color:#F6CEF5">온라인 주문</option>
							<option value="2" style="background-color:#CEF6F5">PDA 주문</option>
						</select>
						
						<label class="ml10">처리구분</label>
						<select class="w125" name="as_receipt_gb" id="as_receipt_gb">
							<option value="0">전체</option>
							<option value="1" selected>접수</option>
							<option value="2">승인</option>
							<option value="3">반려</option>
						</select>
						
						<div id="limitYn" style="display:inline-block;">
							<label class="ml10">여신규정</label>
							<select class="w125" name="as_limit_yn" id="as_limit_yn">
								<option value="A">전체</option>
								<option value="N">이내</option>
								<option value="Y">초과</option>
								<option value="E">예외</option>
							</select>
						</div>
						
						<div id="wibanKind" style="display:none;">
							<label class="ml10">위반종류</label>
							<select class="w110" name="as_wiban_kind" id="as_wiban_kind">
								<option value="%">전체</option>
								<option value="0">정상주문</option>
								<option value="1">회전일위반</option>
								<option value="2">수량위반</option>
							</select>
						</div>
						
						<label class="ml10">수량한도</label>
						<select class="w90" name="as_psb_gb" id="as_psb_gb">
							<option value="0">전체</option>
							<option value="1">이내(승인가)</option>
							<option value="2">초과(승인불)</option>
						</select>
						
						<label class="ml10">선입금거래처</label>
						<select class="w60" name="as_pre_deposit" id="as_pre_deposit">
							<option value="%">전체</option>
							<option value="Y">Y</option>
							<option value="N">N</option>
						</select><br>

<!-- kta 2015.11.04
						<input type="checkbox" name="as_pre_deposit" id="as_pre_deposit" value="Y"/>
						<label>선입금거래처</label><br>
 -->
						
						<label class="w70">거래처</label>
						<input type="text" class="w140" name="as_cust_id" id="as_cust_id" onblur="javascript:getCustomerType();"/>
						<button type="button" class="btn_search" onclick="javascript:Commons.popupOpen('as_cust_id', '<%=ONLINE_ROOT%>/business/common/customerList.do', '600', '655');" ><span class="blind">찾기</span></button>
						<input type="text" class="w435 ipt_disable" name="as_cust_nm" id="as_cust_id_name" readonly/>
						
						<label class="ml37">승인일</label>
						<p class="wrap_date">
							<input type="text" name="as_app_date" id="as_app_date" maxlength="10" onchange="javascript:chkAppDate(this);"/>
							<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						
					</div>
					<div class="wrap_search_btn">
						<%=WebUtil.createButtonArea(currPgmNoByUri, "ct_")%>
					</div>
				</div>
			</div>
			<div class="inner_cont2">
				<div class="wrap_result_group">
					<div class="result_group">
						<div class="float_r">
							<label class="point">승인구분</label>
							<select class="w100" name="as_receipt_gb2" id="as_receipt_gb2">
								<option value="approval">승인</option>
								<option value="return">반려</option>
								<option value="add_order">추가승인</option>
								<option value="cancel_order">취소</option>
							</select>
							<label class="point ml10">승인/반려 사유</label>
							<input type="text" class="w350" name="as_return_desc" id="as_return_desc"/>
							<span class="appro_result_txt ml10">위 : <span id="wiban_order_req_yn"></span> 승 : <span id="wiban_order_conf_yn"></span></span>
							<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
						</div>
					</div>
				</div>		
				<div class="table_appro_box mt15">
					<table>
					<colgroup>
						<col style="width:100%" />
						<col style="width:360px;" />
					</colgroup>
					<tbody>
						<tr>
							<th style="position:relative;">
								<h2>주문내역</h2>
								<div class="approval_txt">
									<span class="float_l" style="margin-top:3px">결과 총 <span id="total_cnt">0</span>건</span>
									<dl class="float_l">
										<dt class="float_l ml20">총미승인액</dt>
										<dd class="float_l" id="total_reamt">0</dd>
										<dt class="float_l ml20">총미승인액(조건)</dt>
										<dd class="float_l" id="total_reamt_real">0</dd>
										<dt class="float_l ml20">거래처 미승인액</dt>
										<dd class="float_l" id="reamt">0</dd>
									</dl>
								</div>
							</th>
							<th><h2 class="pl20">참조사항</h2></th>
						</tr>
						<tr>
							<td>
								<div class="wrap_approval01 box_type01">
									<div id="orderApproval">
										<table id="grid_list_order"></table>
									</div>
								</div>
							</td>
							<td>
								<div class="table_appro_r">
									<div class="wrap_approval02 box_type01">
										<table id="infoTb" class="type01 re_style04" style="border-spacing:0">
											<colgroup>
												<col style="width:25%" />
												<col style="width:25%" />
												<col style="width:25%" />
												<col style="width:25%" />
											</colgroup>
											<tbody>
												<tr>
													<th class="no_border_l">주문일시</th>
													<td id="input_ymd"></td>
													<th>거래처 담당자</th>
													<td class="no_border_r">
														<span id="sawon_nm"></span><br/>
														<span id="dept_nm"></span>
													</td>
												</tr>
												<tr>
													<th class="no_border_l">팀장승인</th>
													<td id="wiban_conf_dtm"></td>
													<th>판매처 담당자</th>
													<td class="no_border_r">
														<span id="rsawon_nm"></span><br/>
														<span id="rdept_nm"></span>
													</td>
												</tr>
												<tr>
													<th class="no_border_l">전월잔고</th>
													<td id="before_amt"></td>
													<th>연대보증인</th>
													<td class="no_border_r" id=yeondae_2></td>
												</tr>
												<tr>
													<th class="no_border_l">금월판매</th>
													<td id="sale_amt"></td>
													<th>담보확보액</th>
													<td class="no_border_r" id="sale_dambo_amt"></td>
												</tr>
												<tr>
													<th class="no_border_l">금월수금</th>
													<td id="cash_amt"></td>
													<th>담보확보율</th>
													<td class="no_border_r" id="credit_rate"></td>
												</tr>
												<tr>
													<th class="no_border_l">현잔고</th>
													<td id="cur_amt"></td>
													<th>담보종류</th>
													<td class="no_border_r" id="bill_kind"></td>
												</tr>
												<tr>
													<th class="no_border_l">미도래(자수)</th>
													<td id="jasu_amt"></td>
													<th>여신한도액</th>
													<td class="no_border_r" id="credit_limit_amt"></td>
												</tr>
												<tr>
													<th class="no_border_l">미도래(타수)</th>
													<td id="tasu_amt"></td>
													<th>미정리매출할인</th>
													<td class="no_border_r"></td>
												</tr>
												<tr>
													<th class="no_border_l no_border_b">총여신</th>
													<td class=" no_border_b" id="tot_credit"></td>
													<th class="no_border_b">회전일</th>
													<td class="no_border_r no_border_b" id="control_rate_day"></td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<th style="position:relative;">
								<h2>주문세부내역 <span id="spanDetailName"></span></h2>
								<div class="approval_txt">
									<dl class="float_l">
										<dt class="float_l ml20">공급가액</dt>
										<dd class="float_l" id="total_amt">0</dd>
										<dt class="float_l ml20">세액</dt>
										<dd class="float_l" id="total_vat">0</dd>
										<dt class="float_l ml20">공급총액</dt>
										<dd class="float_l" id="total_tot_amt">0</dd>
									</dl>
								</div>
							</th>	
							<th><h2 class="pl20">담보약속내용</h2>	</th>
						</tr>
						<tr>
							<td>
								<div class="wrap_approval01 box_type01 h286">
									<table id="grid_list_orderDetail"></table>
								</div>
							</td>
							<td>
								<div class="table_appro_r wrap_approval02 h286" id="promise">
									<div class="wrap_approval02 box_type01">
										<table id="grid_list_promise"></table>
									</div>
								</div>	
							</td>
						</tr>
					</tbody>
					</table>
				</div>
			</div>
		</form>
		<form id="approvalFrm" name="approvalFrm">
			<div id="approvalDiv"></div>
			<div id="detailDiv"></div>
		</form>
	</body>
	<%@include file="/include/footer.jsp"%>
</html>