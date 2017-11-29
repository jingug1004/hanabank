<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : orderUpdateForm.jsp
 * @메뉴명 : 온라인발주 > 주문수정       
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
	var mlastSelection = 0;		// 주문 master jqGrid에서 마지막으로 선택한 구매번호
	var lastSelection = 0;		// 주문 detail jqGrid에서 마지막으로 선택한 제품 코드
	
	var itemIdArray = '';		//수량 입력한 제품 코드명을 구분자(,)를 함께 배열로 넣는다.
	var bigoValue = '';			//비고 수정 유무
	
	var saleActionFlag = false;		//기능(엑셀, 인쇄) 제어를 위한 전역변수
	
	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){

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
			editurl:"<%=ONLINE_ROOT%>/order/masterGridList.do",
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
					if(mlastSelection != 0){
						$("#grid_list").setSelection(mlastSelection);
					}else{
						$("#grid_list").setSelection(data.rows[0].gumae_no);
					}
					$('#grid_list .ui-state-highlight').focus();
				}
			}			
   		});
		
		/* 주문 detal jqGrid 생성 및 options 설정. */
		$("#grid_list2").jqGrid({
			editurl:"<%=ONLINE_ROOT%>/order/detailGridList.do",
			mtype:"post",
			datatype:"json",
			colNames:["제품코드","제품명","규격","주문수량","변경수량","단가","공급가액","세액","공급총액","tot_amt","dc_danga","dc_amt","input_seq"],
			colModel:[
				{name:"item_id",		index:"item_id",		align:"center",		width:120,	key:true },												//제품코드
				{name:"item_nm",		index:"item_nm ",		align:"left",		width:400	},														//제품명
				{name:"standard",		index:"standard", 		align:"center",		width:120	},														//규격
				{name:"qty",			index:"qty", 			align:"center", 	width:100	},														//주문수량
				{name:"amend_qty",		index:"amend_qty", 		align:"center", 	width:100,	editable: true, editype : "text", formatter:"textbox",
					editoptions : 	Commons.jqgridEditOptions('grid_list2', 'N', jqgridEditOptions_callback)	},										//변경수량
				{name:"danga",			index:"danga", 			align:"right",  	width:120,	formatter:Order.amountFormatter },						//단가
				{name:"amend_amt",		index:"amend_amt", 		align:"right",		width:120,	formatter:Order.amountFormatter },						//공급가액
				{name:"amend_vat",		index:"amend_vat",		align:"right",		width:150,	formatter:Order.amountFormatter },						//세액
				{name:"amend_tot_amt",	index:"amend_tot_amt", 	align:"right",		width:150,	formatter:Order.amountFormatter },						//공급총액
				{name:"tot_amt",		index:"tot_amt", 		hidden: true 					},												//tot_amt
				{name:"dc_danga",		index:"dc_danga", 		hidden:true						},			//dc_danga
				{name:"dc_amt",			index:"dc_amt", 		hidden:true						},			//dc_amt
				{name:"input_seq",		index:"input_seq", 		hidden:true						}
			],
			rowNum: -1,
			onSelectRow:function (id){		//row를 클릭등으로 selection할 때 함수를 실행함
				if (id && id != lastSelection) {	//현재 선택한 제품코드와 lastSelection값이 다를 경우 현재 선택한 row의 변경수량 컬럼을 편집모드로 변경
					editRow(id,'row');
				}
			},
			height:300,
			autowidth:true,
			loadComplete: function(data){	//조회 완료시 호출되는 function
				if(data.records != 0){		//조회 결과가 있을 경우
					$("#grid_list2").jqGrid('setSelection', data.rows[0].item_id);	//첫번째 로우 편집 모드로 변경
					footerSum();		//하단 총액 계산
				}
			}			
		});
		
		/* 조회 버튼 클릭 */
		$("#ct_retrieve_btn_type02").on("click",function(){
			masterOrderGrid();
		});
		
		/* 저장 버튼 클릭 */
		$("#p_save").on("click",function(){
			editRow(0, 'save');
		});
		
		/* 인쇄 버튼 클릭 */
		$("#p_print").on("click",function(){
			gridPrint();
		});
		
		/* 엑셀 버튼 클릭 */
		$("#p_excel").on("click",function(){
			Commons.extraAction(saleActionFlag, 'excel', '<%=ONLINE_ROOT%>/order/onderUpdateGridListExcelDown.do', '');
		});
		
		/* 닫기 버튼 클릭 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('주문수정');
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
		
		if(lastSelection!=0){
			
			//파라미터 셋팅
			var param = "gumae_no=" + lastSelection;
			// 주문 detail jqgrid 호출
			$("#grid_list2").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/detailGridList.do?" + param}).trigger("reloadGrid");
		}
		
		//값 초기화
       	/**
       	 * @param id
       	 */
       	mlastSelection=0;
       	lastSelection=0;
       	itemIdArray = '';
		bigoValue = '';
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
       	
		if (id && id !== mlastSelection) {
			bigoValue=grid.getRowData(id).bigo;
			grid.restoreRow(mlastSelection);
			grid.editRow(id, true);
			mlastSelection = id;
		}
		
       	//detail grid 선택, 수정값 clean
       	lastSelection = 0;
       	itemIdArray = '';
       	
		//파라미터 셋팅
		var param = "ymd=" + ymd +"&gumae_no=" + gumae_no;
		// 주문 detail jqgrid 호출
		$("#grid_list2").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/detailGridList.do?" + param}).trigger("reloadGrid");
	}
	
	/**
	 * tab키나 enter키를 입력했을 경우 현재 로우가 마지막 로우일 경우 현재 로우에서 입력된 수량으로  validation check를 한다.
	 * @param rowId	현재 row id
	 * @param nextRowId	다음 row id
	 */
   function jqgridEditOptions_callback(rowId, nextRowId){
		if(rowId && rowId != null && !(nextRowId && nextRowId != null)){
			lastSelection = rowId;
			editRow(rowId, 'row');
		}
    }

	 /**
     *	jqGrid에서 제품을 선택했을 시 하단 평균수량, 주문한도 정보등을 DB에서 조회한 후 화면에 셋팅한다.
     *	현재 선택한 제품 코드가 lastSelection변수에 저장된 값과 다를 경우이전에 입력한 제품의 수량의 유효성을 체크한다.
     *  주문 수정시 함수 호출 흐름을 본다면 editRow(0, 'save') -> orderApprovalAjax -> updateOrderAjax()
     * @param id	선택한 제품 코드
     * @param proc	saveRow에서 호출한건지, editRow함수에서 호출한건지 구분
     */
	function editRow(id, proc){
		var mGrid = $("#grid_list");
		var dGrid = $("#grid_list2");

		//saveRow에서 호출했을 경우 lastSelection id가 현재 select row id여야 한다.(제품수량은 수정하지 않고 요청사항)
		if(id==0){
			if(dGrid.getGridParam("selrow") != null){
				lastSelection=dGrid.getGridParam("selrow");	
			}else{
				orderApprovalAjax();
				return;
			}
		}
		
		/*
		*	lastSelection가 0인 경우 제품선택을 한번도 하지 않았으므로 선택한 제품의 수량 변경할 수 있도록 해주고,
		*	0이 아닐 경우 lastSelection에 저장된 제품코드와 입력된 수량으로 가격 계산을 하고, 제품의 상태(재고,출하중지 등)를 DB에서 조회한 후 체크한다. 			
		*/
		if(lastSelection == 0){
			dGrid.editRow(id);
			lastSelection = id;
			
		}else{		//row를 선택하기전 수량을 입력한 그 lastSelection에 대한 제품 validation check									
			
			
			var row = dGrid.getRowData(lastSelection);
			var ls_rcust_id = mGrid.getRowData(mlastSelection).rcust_id;
			var ld_amend_qty = $('#'+row.item_id+'_amend_qty').val();
			
			if(formCheck.isNumer(ld_amend_qty)){
				alert("숫자만 입력가능합니다.");
				dGrid.setSelection(lastSelection);
				//$('#'+row.item_id+'_amend_qty').val("");
				//inserYn=0;
				return;
			}
			
			if(proc=='save'){
				if(!confirm("변경한 수량을 저장하겠습니까?")){
					return;
				}
			}
			
			$.ajax({
				type:"POST",
				async: false,
				url:"<%=ONLINE_ROOT%>/order/basDangaAjax.do",
				data:{ls_rcust_id:ls_rcust_id, ls_item_id:row.item_id},
				dataType:"json",
				success:function(data){
						ld_bas_danga = data.ld_bas_danga;
				},error : function(xhr, st, str) {
			    	alert("editRow 데이터베이스 관련 오류 발생 "+xhr.status+'error');
			   	}, global: false,
			});
			
			
			//제품 체크
			var ls_item_cd = row.item_id;
			var ls_item_nm = row.item_nm;
			var ls_standard = row.standard;
			var ldt_jaego = mGrid.getRowData(mlastSelection).ymd.replace(/-/gi,"");

			//수량이 0 
			if(ld_amend_qty == '0' || ld_amend_qty == ''){
				
				var arry = itemIdArray.split(',');			//주문 등록할 상품 id의 배열값		
				var itemIdArray2 = "";
				for(var i=0; i < arry.length; i++ ){
					if(arry[i] != row.item_id ){
						if("" == itemIdArray2){
							itemIdArray2=arry[i];	
						}else{
							itemIdArray2 += ','+arry[i];
						}
					}
				}
				itemIdArray = itemIdArray2;
				if(ld_amend_qty == '0'){
					alert('주문삭제 처리 하세요 \n제품코드:'+ ls_item_cd +' '+ ls_item_nm + ' ' + ls_standard + '는 0으로 수량변경 할수 없습니다');
					$('#'+row.item_id+'_amend_qty').val("");
 					dGrid.setSelection(lastSelection);
					return;
				}else{
					dGrid.saveRow(lastSelection);
					dGrid.editRow(id);
					lastSelection = id;
					
					if(proc=='save'){
						orderApprovalAjax();
					}
				}
				
				//하단 총액 계산
				footerSum();
				
			}else{
				/*
				*	수량이 입력되엇을 경우 제품의 상태(재고,출하중지,단종 등)를 DB에서 조회한 후 체크한다.
				*	변경수량이 기존 수량보다 클 경우 금액을 다시 계산한 후 주문금액 > 주문가능금액인 경우에는 저장진행 암함.
				*/
				$.ajax({
					type:"POST",
					async: false,
					url:"<%=ONLINE_ROOT%>/order/itemChkEditAjax.do",
					data:{ls_item_cd:ls_item_cd, ldt_jaego:ldt_jaego, ls_rcust_id:ls_rcust_id},
					dataType:"json",
					success:function(data){
						if (data.ls_chul_yn == '' && data.ls_use_yn == ''){
							alert('제품코드 : '+ (ls_item_cd) +'\n' + (ls_item_nm) + ' ' + (ls_standard) + '는 등록된 제품코드가 아닙니다.!');
							dGrid.setSelection(lastSelection);
							$('#'+row.item_id+'_amend_qty').val("");
							dGrid.editRow(lastSelection,true);
							inserYn=0;
							return;
						}else{
							
							if (data.ls_use_yn == 'N'){
								alert('주문삭제 처리 하세요.\n제품코드 : '+ (ls_item_cd)+'\n' + (ls_item_nm) + ' ' + (ls_standard) + '는 단종된 제품입니다.\n필요시 담당자에게 문의 하시기 바랍니다.');
								dGrid.setSelection(lastSelection);
								$('#'+row.item_id+'_amend_qty').val("");
								inserYn=0;
								return;
							}
							if (data.ls_chul_yn == 'Y'){
								alert('주문삭제 처리 하세요.\n제품코드 : '+ (ls_item_cd) +'\n' + (ls_item_nm) + ' ' + (ls_standard) + '는 출하중지된 제품입니다.\n필요시 담당자에게 문의 하시기 바랍니다.');
								dGrid.setSelection(lastSelection);
								$('#'+row.item_id+'_amend_qty').val("");
								inserYn=0;
								return;
							}
							
							//기존주문수량보다 변경수량이 적거나 같은면 재고채크 하지 않음.
							if(parseInt(ld_amend_qty) <= parseInt(row.qty) ){
								if (data.ld_invjaego_qty ==''){
									alert('제품코드: '+ (ls_item_cd) +'\n' + (ls_item_nm) + ' ' + (ls_standard) + '는 재고가 없습니다.');
									dGrid.setSelection(lastSelection);
									$('#'+row.item_id+'_amend_qty').val("");
									dGrid.editRow(lastSelection,true);
									inserYn=0;
									return;
								}else{
									if( parseInt(data.ld_invjaego_qty) >= parseInt(data.ld_jaego_qty) ){				
										//가용재고만큼만
										if ( ld_req_qty > parseInt(data.ld_jaego_qty) ){
											alert('주문수량이 재고수량을 초과하였습니다.\n제품코드: '+ (ls_item_cd) +'\n' + (ls_item_nm) +' '+(ls_standard)+'는 현재고: '+ Commons.addComma(data.ld_jaego_qty)+ ' 입니다.');
											dGrid.setSelection(lastSelection);
											$('#'+row.item_id+'_amend_qty').val("");
											dGrid.editRow(lastSelection,true);
											inserYn=0;
											return;
										}
									}
									//창고재고만큼만
									if( parseInt(ld_req_qty) > parseInt(data.ld_invjaego_qty) ){
										alert('주문수량이 재고수량을 초과하였습니다.\n제품코드: '+ (ls_item_cd) +'\n' + (ls_item_nm) +' '+(ls_standard)+'는 현재고: '+ Commons.addComma(data.ld_jaego_qty)+ ' 입니다.');
										dGrid.setSelection(lastSelection);
										$('#'+row.item_id+'_amend_qty').val("");
										dGrid.editRow(lastSelection,true);
										inserYn=0;
										return;
									}
								}
							}
							
							var ld_amend_qty = $('#'+row.item_id+'_amend_qty').val();
							var ls_rcust_id = mGrid.getRowData(mlastSelection).rcust_id;
							var ld_rem_dambo = $('#credit_dambo').val();
							var ld_danga = parseInt((row.danga).replace(/,/gi,""));
							var dc_danga = row.dc_danga;
							var ld_bas_danga = 0;
							var amend_amt = 0;
							var amend_vat = 0;
							var amend_tot_amt = 0;
							var dc_amt = 0;
							var tot_amt = 0;
							
							
							//공급가액
							amend_amt = ld_danga * ld_amend_qty;
							
							//세액
							//3,4번대거래처는 기준가로 계산한다.
							if (ls_rcust_id.charAt(0) == '3' || ls_rcust_id.charAt(0) == '4' ){
								amend_vat = (ld_bas_danga * ld_amend_qty) - (ld_danga * ld_amend_qty);		
							}else{
								amend_vat = Math.floor(ld_danga * ld_amend_qty * 0.1);
							}
							dc_amt = dc_danga * ld_amend_qty;						//dc_amt
							tot_amt = parseInt(row.tot_amt);		//수정전 공급총액
							amend_tot_amt = amend_amt+amend_vat;
							rem_dambo = ld_rem_dambo - (amend_tot_amt - tot_amt);	//수정된 최초 주문 가능액
								
							//dGrid cell에 값 셋팅
							dGrid.setCell(lastSelection,"amend_amt", amend_amt);
							dGrid.setCell(lastSelection,"amend_vat", amend_vat);
							dGrid.setCell(lastSelection,"dc_amt", dc_amt);
							
							//공급가액+세액 = 합계금액
							dGrid.setCell(lastSelection,"amend_tot_amt",amend_tot_amt);
							
							$('#rem_dambo').val(Commons.addComma(rem_dambo));
							//하단 총액 계산
							footerSum();
							
							//거래처 여신한도금액이 있는경우에만
							var ld_credit_limit_amt = $('#ld_credit_limit_amt').val();
							var total_amt = $('#tot_amt').val().replace(/,/gi,"");
							var rem_dambo = $('#rem_dambo').val().replace(/,/gi,"");
							var ls_limit_yn = 'N';
							
							if(parseInt(ld_credit_limit_amt) > 0){
								//주문금액 > 주문가능금액인 경우에는 저장불가능...

                                //ld_rem_dambo : 변경수량 입력후 엔터키 전  의 주문가능금액
                                //rem_dambo    : 변경수량 입력후 엔터키 이후의 주문가능금액
                                //total_amt    : 변경수량 입력후 이 판매처의 공급총액
                                //화면상에서 변경수량 입력후 엔터키치면 이곳을 타므로    ld_rem_dambo 로 비교해야 함.
                                //
                                //ld_rem_dambo 는 현재 수정한 제품의 공급총액
								if(parseInt(total_amt) > (parseInt(ld_rem_dambo) + parseInt(tot_amt ))){
									alert('여신한도액을 초과하였습니다. 담당자에게 문의하세요.');
									dGrid.setSelection(lastSelection);
									dGrid.editRow(lastSelection,true);
									$('#rem_dambo').val(Commons.addComma(ld_rem_dambo));
									$('#'+row.item_id+'_amend_qty').val(0);
									return;
								}
							}else{
								if('<%=StringUtil.nvl(orderInit.getIs_yeondae3(),"").equals("")%>'== 'N'){ 		//담보예외가 아님
									if (dw_3.object.amend_tot_amt_sum[1] > dw_1.object.credit_dambo[1] ){
										ls_limit_yn = 'Y';
									}
									
								}else if('<%=StringUtil.nvl(orderInit.getIs_yeondae3(),"").equals("")%>' == 'Y'){ 	//담보예외
									ls_limit_yn = 'E';
								}
							}
							$('#ls_limit_yn').val(ls_limit_yn);
							
							
							//수량 입력한 폼을 저장하고 새로 클릭한 row를 입력폼으로 바꾼다.
							$('#'+row.item_id+'_amend_qty').val(Number($('#'+row.item_id+'_amend_qty').val()));
							dGrid.saveRow(lastSelection);
							dGrid.editRow(id);
							lastSelection = id;
							
							//저장할 제품ID , 배열로 넣기
							if(itemIdArray ==''){
								itemIdArray = row.item_id;
							}else{
								var yn='N';								
								var arry = itemIdArray.split(',');		
								for(var i=0; i < arry.length; i++ ){
									if(arry[i] == row.item_id ){
										yn='Y';
									}
								}
								if(yn=='N'){
									itemIdArray += ','+row.item_id;
								}
							}
							
							if(proc=='save'){
								orderApprovalAjax();
							}
							
						}
					},error:function(err){
						alert("itemChkAjax 데이터베이스 관련 오류 발생 "+err.status+'error');
					}
				});
			}
		}
	}
	
	/**
	*	하단 총액 계산
	*/
	function footerSum(){
		
		//변수생성
		var dGrid = $("#grid_list2");

		//detail grid 생성 후 아래 하단 총액 계산 
		var amend_amt=0;
		var amend_vat=0;
		var amend_total_amt=0;
		
		
		//grid 하단 총 공급가액, 세액, 공급총액 구하는 방식
		var colAmend_amt = dGrid.getCol('amend_amt', true);
		var colAmend_vat = dGrid.getCol('amend_vat', true);
		var colAmend_tot_amt = dGrid.getCol('amend_tot_amt', true);
		
		for(var colInt=0; colInt < colAmend_amt.length; colInt++){
			if(colAmend_amt[colInt].value!=''){
				var str_amend_amt = (colAmend_amt[colInt].value).replace(/,/gi,"");
				var str_amend_vat = (colAmend_vat[colInt].value).replace(/,/gi,"");
				var str_amend_tot_amt = (colAmend_tot_amt[colInt].value).replace(/,/gi,"");
				amend_amt += parseInt(str_amend_amt);
				amend_vat += parseInt(str_amend_vat);
				amend_total_amt += parseInt(str_amend_tot_amt);
			}
		}
		//grid 하단 공급가액, 세액, 공급총액, 주문 가능액 value set
		$('#amt').val(Commons.addComma(amend_amt));
		$('#vat').val(Commons.addComma(amend_vat));
		$('#tot_amt').val(Commons.addComma(amend_total_amt));
		
	}
	
	/**
	*	주문 수정전 주문의 상태(승인/반려여부 등)를 DB에서 조회한 후 체크한다.
	*	주문 수정시 함수 호출 흐름을 본다면 editRow(0, 'save') -> orderApprovalAjax -> updateOrderAjax()
	*/
	function orderApprovalAjax(){
		
		var mGrid = $("#grid_list");
		var row = mGrid.getRowData(mlastSelection);
		var ls_gumae_no = mGrid.getRowData(mlastSelection).gumae_no.replace(/-/gi,"");
		
		if(itemIdArray=='' && (bigoValue == $('#'+row.gumae_no+'_bigo').val()) ){
			alert("수정한 주문 자료가 없습니다.");
			return;
		}		
		
		$.ajax({
			type:"POST",
			async: false,
			url:"<%=ONLINE_ROOT%>/order/orderApprovalAjax.do",
			data:{ls_gumae_no:ls_gumae_no},
			dataType:"json",
			success:function(data){
				
				if(data.ls_receipt_gb=='2'){
					alert('요청일자:'+ mGrid.getRowData(mlastSelection).ymd +'\n접수번호:' + mGrid.getRowData(mlastSelection).gumae_no + '는 주문이 승인된 상태입니다.');
					return;						
				}else if(data.ls_receipt_gb=='3'){
					alert('요청일자:'+ mGrid.getRowData(mlastSelection).ymd +'\n접수번호:' + mGrid.getRowData(mlastSelection).gumae_no + '는 주문이 반려된 상태입니다.');
					return;
				}else if(data.ls_receipt_gb==''){
					alert('요청일자:'+ mGrid.getRowData(mlastSelection).ymd +'\n접수번호:' + mGrid.getRowData(mlastSelection).gumae_no + '로 주문 자료가 없습니다.');
					return;
				}
				
				updateOrderAjax();
			},error:function(err){
				alert("orderApprovalAjax 데이터베이스 관련 오류 발생 "+err.status+'error');
			}
		});
		
	}
	
	/**
	*	주문 수정.
	*	주문 수정시 함수 호출 흐름을 본다면 editRow(0, 'save') -> orderApprovalAjax() -> updateOrderAjax()
	*/
	function updateOrderAjax(){
		
		var mGrid = $("#grid_list");
		var dGrid = $("#grid_list2");
		var mRow = mGrid.getRowData(mGrid.getGridParam("selrow"));
		var itemFrm = $('#itemFrm');		
		var ymd = mRow.ymd.replace(/-/gi,"") ;
		var gumae_no = mRow.gumae_no.replace(/-/gi,"");
		var ld_amt_sum = $('#amt').val().replace(/,/gi,"");
		var ld_vat_sum = $('#vat').val().replace(/,/gi,"");
		var ls_limit_yn = $('#ls_limit_yn').val();
		
		$("<input></input>").attr({type:"hidden", name:"ldt_req_date",	value: ymd}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"gumae_no",		value: gumae_no}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"bigo",			value: $('#'+mRow.gumae_no+'_bigo').val()}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"ls_limit_yn",	value: ls_limit_yn }).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"ld_amt_sum",	value: ld_amt_sum}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"ld_vat_sum",	value: ld_vat_sum}).appendTo(itemFrm);
		
		if(itemIdArray != ''){
			var arry = itemIdArray.split(',');
			for(var i=0; i < arry.length; i++ ){
				var row = dGrid.getRowData(arry[i]);
				var amend_amt = row.amend_amt.replace(/,/gi,"");
				var amend_vat = row.amend_vat.replace(/,/gi,"");
				$("<input></input>").attr({type:"hidden", 	name:"item_id", 	value:row.item_id}).appendTo(itemFrm);
				$("<input></input>").attr({type:"hidden", 	name:"input_seq", 	value:row.input_seq}).appendTo(itemFrm);
				$("<input></input>").attr({type:"hidden", 	name:"qty", 		value:row.qty}).appendTo(itemFrm);
				$("<input></input>").attr({type:"hidden", 	name:"amend_qty", 	value:row.amend_qty}).appendTo(itemFrm);
				$("<input></input>").attr({type:"hidden", 	name:"amend_amt", 	value:amend_amt}).appendTo(itemFrm);
				$("<input></input>").attr({type:"hidden", 	name:"amend_vat", 	value:amend_vat}).appendTo(itemFrm);
				$("<input></input>").attr({type:"hidden", 	name:"dc_amt", 		value:row.dc_amt}).appendTo(itemFrm);
			}
		}
		
		$.ajax({
			type:"POST",
			async: false,
			url:"<%=ONLINE_ROOT%>/order/updateOrderAjax.do",
			data: itemFrm.serialize(),
			dataType:"json",
			success:function(data){
				
				alert("주문요청 사항을 수정 하였습니다.");

				//파라미터 셋팅
				var param = $('#frm').serialize();
				// 주문 master jqgrid 호출
				$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/masterGridList.do?" + param}).trigger("reloadGrid");
				
				//파라미터 셋팅
				var param2 = "ymd=" + ymd +"&gumae_no=" + gumae_no;
				// 주문 detail jqgrid 호출
				$("#grid_list2").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/detailGridList.do?" + param2}).trigger("reloadGrid");
				$('#itemFrm').empty();
				itemIdArray = '';
				lastSelection = 0;
				bigoValue = '';
			},error:function(err){
				alert("orderApprovalAjax 데이터베이스 관련 오류 발생 "+err.status+'error');
			}
		});
	
	}
	
	/**	
	*	프린트를 하기위해 grid 재생성
	*/
	function gridPrint(){
		
		$('#orderUpdate').html('<table id=\"grid_list_clone\"></table>');
		
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
			rownumbers: true, 
			loadComplete: function(data){
				Commons.extraAction(saleActionFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', 'orderUpdate');
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
			<div class="btn_group">
				<div class="float_l">
					주문수정은 주문수량만 변경가능합니다.
				</div>
				<div class="float_r ta_r">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
				</div>
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
	
	<div id="orderUpdate" style="display:none;"><table id="grid_list_clone"></table></div>
	
	<form id="itemFrm" name="itemFrm">
	</form>
	<%@include file="/include/footer.jsp"%>
</body>
</html>