<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : orderDeleteForm.jsp 
 * @메뉴명 : 온라인발주 > 주문삭제          
 * @최초작성일 : 2014/10/29            
 * @author : 우정아                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%@ page import="javax.servlet.http.HttpSession" %>
<%@ page import="com.hanaph.saleon.member.vo.LoginUserVO" %>
<%@ page import="com.hanaph.saleon.order.vo.OrderVO" %>
<%
	/* 주문 삭제를 위해 거래처 기본 정보 셋팅 */
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
	var mlastSelection = 0;		// 주문 master jqGrid에서 마지막으로 선택한 구매번호
	var lastSelection = 0;		// 주문 detail jqGrid에서 마지막으로 선택한 제품 코드
	var itemIdArray = '';		//수량 선택한 제품 코드명을 구분자(,)를 함께 배열로 넣는다.
	var saleActionFlag = false;	//기능(엑셀, 인쇄) 제어를 위한 전역변수
	var deleteProcFlag = false;	//삭제버튼 flag
	
	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		
		/*
		* 거래처 기본정보를 체
		*/
		Commons.setDate('fr_date','to_date');	//주문요청일 오늘날짜로 set
		
		
		$('#tot_dambo').val(Commons.addComma(<%=(long)orderInit.getLd_tot_dambo()%>));	//총 담보액 set
		$('#rem_dambo').val(Commons.addComma(<%=(long)orderInit.getRem_dambo()%>));		//주문 가능한 set
		
		<%
			/* 파워빌더 wf_credit_chk() */
			if(orderInit.isResult()==false){
				out.println("alert('여신한도액을 초과하였습니다. 담당자에게 문의하세요.');");		//javascript alert
			}else{
				/* 여신규정 */
				if(orderInit.getLd_dambo_rate() == 0 && 
					orderInit.getLd_credit_amt() == 0 && 
						orderInit.getLd_credit_dambo() == 0){
					out.println("alert('여신규정 자료가 없습니다.');");		//javascript alert
				}
			
				/* 담당자,보증인 */
				if(StringUtil.nvl(orderInit.getLs_sawon_id(),"").equals("") &&
					StringUtil.nvl(orderInit.getLs_yeondae(),"").equals("") &&
						StringUtil.nvl(orderInit.getIs_yeondae3(),"").equals("")){
					out.println("alert('거래처코드: "+userEmpCode+"로 거래처 자료가 없습니다.');");		//javascript alert
				}else{
					if(StringUtil.nvl(orderInit.getLs_sawon_info(),"").equals("")){
					out.println("alert('사원코드:"+orderInit.getLs_sawon_id()+"로 담당자 자료가 없습니다.');");		//javascript alert
					}
				}
		}
		%>
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리. */
	    jsonReader : {
            repeatitems: false;
    	}
		
		/* 주문 master jqGrid 생성 및 options 설정. */
		$("#grid_list").jqGrid({
			mtype:"post",
			datatype:"json",
			colNames:["주문요청일","판매처","판매처명","접수번호","주문상태","주문종류","요청사항"],
			colModel:[
				{name:"ymd",		index:"ymd",		align:"center",		width:120	},			//주문요청일
				{name:"rcust_id",	index:"rcust_id ",	align:"center",		width:100	},			//판매처
				{name:"rcust_nm",	index:"rcust_nm", 	align:"left",		width:250	},			//판매처명
				{name:"gumae_no",	index:"gumae_no", 	align:"center", 	width:150,	key:true},	//접수번호
				{name:"status",		index:"status", 	align:"center", 	width:80	},			//주문상태
				{name:"gumae_gb",	index:"gumae_gb", 	align:"center",  	width:100	},			//주문종류
				{name:"bigo",		index:"bigo", 		align:"left",		width:300,	editable: true, editype : "text", formatter:"textbox"	}	//요청사항
			],
			rowNum: -1,
			onSelectRow: detailOrderGrid,		//row를 클릭등으로 selection할 때 detailOrderGrid 함수를 실행함
			height:200,
			autowidth:true,
			rownumbers: true, 
			loadComplete: function(data){	//조회 완료시 호출되는 function
				if(data.records == 0){		//조회결과가 없을 경우
					alert("해당 주문자료가 없습니다.");
					saleActionFlag=false;
					$("#grid_list2").jqGrid('clearGridData');
				}else{						//조회결과가 있을 경우
					saleActionFlag=true;
					$("#grid_list").setSelection(data.rows[0].gumae_no);
				}
			}			
   		});
		
		/* 주문 detal jqGrid 생성 및 options 설정. */
		$("#grid_list2").jqGrid({
			mtype:"post",
			datatype:"json",
			colNames:["제품코드","제품명","규격","주문수량","단가","공급가액","세액","공급총액","input_seq"],
			colModel:[
				{name:"item_id",		index:"item_id",		align:"center",		width:120,	key:true },												//제품코드
				{name:"item_nm",		index:"item_nm ",		align:"left",		width:400	},														//제품명
				{name:"standard",		index:"standard", 		align:"center",		width:120	},														//규격
				{name:"qty",			index:"qty", 			align:"center", 	width:100	},														//주문수량
				{name:"danga",			index:"danga", 			align:"right",  	width:120,	formatter:Order.amountFormatter },						//단가
				{name:"amt",			index:"amt", 			align:"right",		width:120,	formatter:Order.amountFormatter },						//공급가액
				{name:"vat",			index:"vat",			align:"right",		width:150,	formatter:Order.amountFormatter },						//세액
				{name:"tot_amt",		index:"tot_amt", 		align:"right",		width:150,	formatter:Order.amountFormatter },						//공급총액
				{name:"input_seq",		index:"input_seq", 		hidden:true						}
			],
			rowNum: -1,
			height:300,
			autowidth:true,
			multiselect: true,
			loadComplete: function(data){	//조회 완료시 호출되는 function
				if(data.records != 0){		//조회 결과가 있을 경우
					footerSum();
				}
			}			
		});
		
		/* 조회 버튼 클릭 */
		$("#ct_retrieve_btn_type02").on("click",function(){
			masterOrderGrid();
		});
		
		/* 삭제 버튼 클릭 */
		$("#p_delete").on("click",function(){
			getSelectedRows();
		});
		
		/* 인쇄 버튼 클릭 */
		$("#p_print").on("click",function(){
			gridPrint();
		});
		
		/* 엑셀 버튼 클릭 */
		$("#p_excel").on("click",function(){
			Commons.extraAction(saleActionFlag, 'excel', '<%=ONLINE_ROOT%>/order/orderUpdateGridListExcelDown.do', '');
		});
		
		/* 닫기 버튼 클릭 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('주문삭제');
		});
		
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
    $(window).resize(function(){
		$("#grid_list").setGridWidth($('.wrap_result_group').width(), true);		//grid 영역의 넓이가 동적으로 조절 
		$("#grid_list2").setGridWidth($('.wrap_result_group').width(), true);		//grid 영역의 넓이가 동적으로 조절 
	});
    
    /* 화면의 모든 객체가 모두 로드되었을 때 */
	$(window).load(function(){
		/* 엔터키 눌렸을 경우 조회되도록. */
		$("body").on("keydown", function(event){
			if($("#ct_retrieve_btn_type02").prop('disabled') == false){
				if(event.keyCode==13 && event.target.name!="grid_count"){
					masterOrderGrid();
				return false;
				}
			}
		});
	});

	/**
	 * 판매처 input에서 포커스 아웃되면 실행.
	 * 판매처 정보를 조회해서 판매처에 대한 체크를 진행해서 이상이 없을 경우 제품 목록 조회한다.
	 * @param rcust_id	판매처 코드
	 */
	function storeChkAjax(rcust_id){
		
		if(rcust_id != ''){
			
			$.ajax({
				type:"POST",
				url:"<%=ONLINE_ROOT%>/order/common/storeChkAjax.do",
				data:{ rcust_id : rcust_id },
				dataType:"json",
				success:function(data){
					
					if(data.ls_cust_nm==''){
						alert("등록된 판매처코드가 아닙니다.");
						$("#rcust_id_name").val("");
					}else{
						$("#rcust_id_name").val(data.ls_cust_nm);
					}
					
				},error:function(err){
					alert("storeChkAjax 데이터베이스 관련 오류 발생 "+err.status+'error');
				}
			});
			
		}else {
			$("#rcust_id_name").val("");
		}
	}
	
	/**
	*	주문 master 목록 조회
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
       	
		//파라미터 셋팅
		var param = $('#frm').serialize();
		// 주문 detail jqgrid 호출
		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/masterGridList.do?" + param}).trigger("reloadGrid");
		
		//값 초기화
    	saleActionFlag = false;
		
	}
	
	/**
	*	주문 master row 선택했을 때 선택한 주문의 구매번호를 기준으로 주문 detail정보를 조회해서 주문 detail 그리드에 뿌려준다.
	*	@param id	주문 master 그리드에서 선택한 구매번호
	*/
	function detailOrderGrid(id){
		
		var grid = $("#grid_list");
		var ymd = grid.getRowData(id).ymd.replace(/-/gi,"");
       	var gumae_no = grid.getRowData(id).gumae_no.replace(/-/gi,"");
 
		//파라미터 셋팅
		var param = "ymd=" + ymd +"&gumae_no=" + gumae_no;
		// 주문 detail jqgrid 호출
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
	*	삭제버튼 클릭시 주문 삭제. 체크된 제품들의 정보를 itemIdArray에 담는다.
	*	주문 삭제시 함수 호출 흐름을 본다면 getSelectedRows() -> orderApprovalAjax() -> deleteOrderAjax()
	*/
	function getSelectedRows() {
		
		if(deleteProcFlag){
			return;
		} else {
			deleteProcFlag = true;
		}
		
		var dGrid = $("#grid_list2");
        var rowKey = dGrid.getGridParam("selrow");

        if (!rowKey){
            alert("선택된 주문 자료가 없습니다.");
            deleteProcFlag = false;
        	return;
        }else{
        	itemIdArray='';
            var selectedIDs = dGrid.getGridParam("selarrrow");
            for (var i = 0; i < selectedIDs.length; i++) {
            	if(itemIdArray==''){
            		itemIdArray += selectedIDs[i];	
            	}else{
            		itemIdArray += "," + selectedIDs[i];	
            	}
            }
            
			//제품 승인 체크
			orderApprovalAjax();
        }                
    }
	
	/**
	*	주문 삭제전 주문의 상태(승인/반려여부 등)를 DB에서 조회한 후 체크한다.
	*	주문 삭제시 함수 호출 흐름을 본다면 getSelectedRows() -> orderApprovalAjax() -> deleteOrderAjax()
	*/
	function orderApprovalAjax(){
		
		var mGrid = $("#grid_list");
		var ls_gumae_no = mGrid.getGridParam("selrow").replace(/-/gi,"");
		
		$.ajax({
			type:"POST",
			async: false,
			url:"<%=ONLINE_ROOT%>/order/orderApprovalAjax.do",
			data:{ls_gumae_no:ls_gumae_no},
			dataType:"json",
			success:function(data){
				
				if(data.ls_receipt_gb=='2'){
					alert('요청일자:'+ mGrid.getRowData(mlastSelection).ymd +'\n접수번호:' + mGrid.getRowData(mlastSelection).gumae_no + '는 주문 자료가 승인되어 삭제할수 없습니다.');
					deleteProcFlag = false;
					return;						
				}else if(data.ls_receipt_gb=='3'){
					alert('요청일자:'+ mGrid.getRowData(mlastSelection).ymd +'\n접수번호:' + mGrid.getRowData(mlastSelection).gumae_no + '는 주문 자료가 반려되어 삭제할수 없습니다.');
					deleteProcFlag = false;
					return;
				}else if(data.ls_receipt_gb==''){
					alert('요청일자:'+ mGrid.getRowData(mlastSelection).ymd +'\n접수번호:' + mGrid.getRowData(mlastSelection).gumae_no + '로 주문 자료가 없습니다.');
					deleteProcFlag = false;
					return;
				}
				
				//db 삭제
				deleteOrderAjax();
			},error:function(err){
				deleteProcFlag = false;
				alert("orderApprovalAjax 데이터베이스 관련 오류 발생 "+err.status+'error');
			}
		});
		
	}
	
	/**
	*	주문 삭제.
	*	주문 삭제시 함수 호출 흐름을 본다면 getSelectedRows() -> orderApprovalAjax() -> deleteOrderAjax()
	*/
	function deleteOrderAjax(){
		
		var mGrid = $("#grid_list");
		var dGrid = $("#grid_list2");
		var mRow = mGrid.getRowData(mGrid.getGridParam("selrow"));
		var itemFrm = $('#itemFrm');		
		var ymd = mRow.ymd.replace(/-/gi,"") ;
		var gumae_no = mRow.gumae_no.replace(/-/gi,"");
		
		$("<input></input>").attr({type:"hidden", name:"ldt_req_date",	value: ymd}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"gumae_no",		value: gumae_no}).appendTo(itemFrm);

		if(itemIdArray != ''){
			var arry = itemIdArray.split(',');
			
			for(var i=0; i < arry.length; i++ ){
				var row = dGrid.getRowData(arry[i]);
				$("<input></input>").attr({type:"hidden", 	name:"item_id", 	value:row.item_id}).appendTo(itemFrm);
				$("<input></input>").attr({type:"hidden", 	name:"input_seq", 	value:row.input_seq}).appendTo(itemFrm);
				$("<input></input>").attr({type:"hidden", 	name:"qty", 		value:row.qty}).appendTo(itemFrm);
			}
			if(arry.length==dGrid.getCol("item_id",false,"count")){
				$("<input></input>").attr({type:"hidden", 	name:"masterDelYn", 	value:'Y'}).appendTo(itemFrm);
			}else{
				$("<input></input>").attr({type:"hidden", 	name:"masterDelYn", 	value:'N'}).appendTo(itemFrm);
			}
		}
		
		$.ajax({
			type:"POST",
			async: false,
			url:"<%=ONLINE_ROOT%>/order/deleteOrderAjax.do",
			data: itemFrm.serialize(),
			dataType:"json",
			success:function(data){
				
				
				if(data.result=="Y"){
					alert("주문요청 자료를 삭제 하였습니다.");

					//파라미터 셋팅
					var param = $('#frm').serialize();
					
					//주문 master가 삭제됬을경우에는 주문mastergrid reload
					if($("input[name=masterDelYn]").val()=='Y'){
						$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/masterGridList.do?" + param}).trigger("reloadGrid");
					
						
					//주문 detail 몇개만 삭제되었을 경우는 detailgrid만 reload
					}else{		
						$("#grid_list").setSelection(mRow.gumae_no);
					}
					
					$('#itemFrm').empty();
					
					itemIdArray = '';
	
				}else{
					alert("주문요청 자료를 삭제 실패하였습니다.");
				}
				
								
			},error:function(err){
				alert("deleteOrderAjax 데이터베이스 관련 오류 발생 "+err.status+'error');
			},complete:function(){
				deleteProcFlag = false;
			}
		});
	
	}
	
	/**
	*	프린트를 하기위해 grid 재생성
	*/
	function gridPrint(){
		
		$('#orderDelete').html('<table id=\"grid_list_clone\"></table>');
		
		var param = $('#frm').serialize();
		
		$("#grid_list_clone").jqGrid({
			url:"<%=ONLINE_ROOT%>/order/masterGridList.do?"+param,
			mtype:"post",
			datatype:"json",
			colNames:["주문요청일","판매처","판매처명","접수번호","주문상태","주문종류","요청사항"],
			colModel:[
				{name:"ymd",		index:"ymd",		align:"center",		width:120	},			//주문요청일
				{name:"rcust_id",	index:"rcust_id ",	align:"center",		width:100	},			//판매처
				{name:"rcust_nm",	index:"rcust_nm", 	align:"left",		width:250	},			//판매처명
				{name:"gumae_no",	index:"gumae_no", 	align:"center", 	width:150,	key:true},	//접수번호
				{name:"status",		index:"status", 	align:"center", 	width:80	},			//주문상태
				{name:"gumae_gb",	index:"gumae_gb", 	align:"center",  	width:100	},			//주문종류
				{name:"bigo",		index:"bigo", 		align:"left",		width:300,	editable: true, editype : "text", formatter:"textbox"	}	//요청사항
			],
			rowNum: -1,
			height:200,
			autowidth:true,
			rownumbers: true, 
			loadComplete: function(data){
				Commons.extraAction(saleActionFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', 'orderDelete');
			}			
   		});		
	}
	
	</script>
	
</head>
<body>
	<form id="frm" name="frm" method="post">
	<input type="hidden" id="ld_credit_limit_amt" name="ld_credit_limit_amt" value="<%=(long)orderInit.getLd_credit_limit_amt()%>" />
	<input type="hidden" id="ld_rem_dambo" name="ld_rem_dambo" value="<%=(long)orderInit.getCredit_dambo()%>" />
	<input type="hidden" id="credit_dambo" name="credit_dambo" value="<%=(long)orderInit.getCredit_dambo()%>" />
	<input type="hidden" id="ls_limit_yn" name="ls_limit_yn" value="" />
	
		<!-- ##### content start ##### -->
		<div class="wrap_search">
			<div class="search">
				<div class="float_l">
					<label class="w70">주문요청일</label>
					<p class="wrap_date">
						<input type="text" id="fr_date" name="fr_date" maxlength="10"/>
						<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					~
					<p class="wrap_date">
						<input type="text" id="to_date" name="to_date" maxlength="10"/>
						<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
					</p><br />
					
					<label class="w70">판매처</label>
					<input type="text" class="w140" id="rcust_id" name="rcust_id"  onchange="javascript:storeChkAjax(this.value);"/>
					<button type="button" class="btn_search" onclick="javascript:Commons.popupOpen('rcust_id', '<%=ONLINE_ROOT%>/order/common/storeListPopup.do', '600', '655');"><span class="blind">찾기</span></button>
					<input type="text" class="w230 ipt_disable" id="rcust_id_name" name="rcust_id_name" readonly/>
				</div>
				<div class="float_l ml10">
					<label class="w70">도매담당자</label>
					<input type="text" class="w350 ipt_disable" id="sawon_info" name="sawon_info" value="<%=StringUtil.nvl(orderInit.getLs_sawon_info(),"") %>" readonly/><br />
					<label class="w70">총담보</label>
					<input type="text" class="w120 ta_right ipt_disable" id="tot_dambo" name="tot_dambo" value="" readonly/>
					<label class="w70 ml10">주문가능액</label>
					<input type="text" class="w140 ta_right ipt_disable" id="rem_dambo" name="rem_dambo" value="" readonly/>
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
			<div class="wrap_result_group">
				<table id="grid_list" id="orderUpdate"></table>
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
	<div id="orderDelete" style="display:none;"><table id="grid_list_clone"></table></div>
	<form id="itemFrm" name="itemFrm">
	</form>
	<%@include file="/include/footer.jsp"%>
</body>
</html>