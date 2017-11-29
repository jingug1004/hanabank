<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : orderInsertForm.jsp
 * @메뉴명 : 온라인발주 > 주문등록       
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
	
	/*
	*	도매담당자 정보
	*/
	String ls_sawon_info = StringUtil.nvl(orderInit.getLs_sawon_info(),"");
	
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
	var lastSelection=0;			//마지막에 선택한 ROW의 ID (제품코드)
	var itemIdArray = '';			//수량 입력한 제품 코드명을 구분자(,)를 함께 배열로 넣는다.
	var saleActionFlag = false;		//기능(엑셀, 인쇄) 제어를 위한 전역변수
	var inserYn=0;					//저장버튼 flag

	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		
		Commons.setDate('','req_date');		//주문요청일 오늘날짜로 set
		
		$('#tot_dambo').val(Commons.addComma(<%=(long)orderInit.getLd_tot_dambo()%>));	//총 담보액 set
		$('#rem_dambo').val(Commons.addComma(<%=(long)orderInit.getRem_dambo()%>));		//주문 가능한 set
		
		$('#control_rate_day').val(<%=orderInit.getControl_rate_day()%>); // 주문제어회전일
		$('#rate_day').val(<%=orderInit.getRate_day()%>); // 회전일
		
		<%
			/*
			*	파워빌더 wf_credit_chk()
			*	여신한도액 초과여부, 여신규정 자료 유무, 거래처/담당자 정보 유무 체크
			*/
			if(orderInit.isResult()==false){		//여신한도액 초과
				out.println("alert('여신한도액을 초과하였습니다. 담당자에게 문의하세요.');");		//javascript alert
			
			}else{
				if(orderInit.getLd_dambo_rate() == 0 && 
					orderInit.getLd_credit_amt() == 0 && 
						orderInit.getLd_credit_dambo() == 0){	//여신규정 자료 유무 체크
					out.println("alert('여신규정 자료가 없습니다.');");
				}
		
				if(StringUtil.nvl(orderInit.getLs_sawon_id(),"").equals("") &&
					StringUtil.nvl(orderInit.getLs_yeondae(),"").equals("") &&
						StringUtil.nvl(orderInit.getIs_yeondae3(),"").equals(""))	{	//거래처 자료 유무 체크
					out.println("alert('거래처코드: "+userEmpCode+"로 거래처 자료가 없습니다.');");			//javascript alert
				}else{
					if(StringUtil.nvl(orderInit.getLs_sawon_info(),"").equals("")){		//담당자 자료 유무 체크
						out.println("alert('사원코드:"+orderInit.getLs_sawon_id()+"로 담당자 자료가 없습니다.');");			//javascript alert
					}
				}
				if(orderInit.getLl_count() > 1){		//거래처 사업번호 체크
					out.println("alert('이 거래처에 사업자번호가 1개 이상 존재합니다. 관련부서로 문의 바랍니다.');");			//javascript alert
				}
				
				if(StringUtil.nvl(orderInit.getLs_sawon_info(),"").equals("")){		//담당자 자료 유무 체크
					out.println("alert('사원코드: "+userEmpCode+"로 담당자 자료가 없습니다.');");			//javascript alert
				}
			}
		%>
		
		
		<%
			/*
			*	파워빌더 wf_budong_chk()
			*	출하중지처 여부 체크 및 거래처 이메일 유무 체크
			*/
			if(!StringUtil.nvl2(orderInit.getLs_budong_yn(),"").equals("")){		
				if(StringUtil.nvl2(orderInit.getLs_budong_yn(),"").equals("Y")){	
					out.println("alert('주문이 제한 되었습니다.\\n담당자에게 문의하시기 바랍니다.');");			//javascript alert
				}
			
				if (StringUtil.nvl2(orderInit.getLs_email(),"").equals("")){	//E-mail체크
					out.println("alert('해당거래처의 E-mail주소 정보가 없습니다.\\n담당자에게 문의하시기 바랍니다.');");			//javascript alert
				}
				
			}else{
				out.println("alert('거래처코드: "+userEmpCode+"로 거래처 자료가 없습니다.');");			//javascript alert
			}
		%>
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리. */
	    jsonReader : {
            repeatitems: false;
    	}
		
		/* jqGrid 생성 및 options 설정. */
		$("#grid_list").jqGrid({
			editurl: "<%=ONLINE_ROOT%>/order/itemGridList.do",
			mtype:"post",
			datatype:"json",
			colNames:["제품코드","제품명","규격","수량","단가","공급가액","세액","합계금액","bas_amt","dc_danga","dc_amt","percent","saupjang_cd","psb_qty","m_qty"],
			colModel:[
						{name:"item_id",	index:"item_id",	align:"center",	width:120,	key:true},							//제품코드
						{name:"item_nm",	index:"item_nm ",	align:"left",	width:300,	formatter:Order.saupjangFormatter},	//제품명
						{name:"standard",	index:"standard", 	align:"center",	width:120},										//규격
						{name:"qty",		index:"qty", 		align:"center", width:100,	editable: true, editype : "text", formatter:"textbox",
							editoptions : 	Commons.jqgridEditOptions('grid_list', 'N', jqgridEditOptions_callback)	
						},																										//수량
						{name:"bal_amt",	index:"bal_amt", 	align:"right", 	width:100,	formatter:Order.amountFormatter},	//단가
						{name:"supply_net",	index:"supply_net", align:"right",  width:120,	formatter:Order.amountFormatter},	//공급가액
						{name:"supply_vat",	index:"supply_vat", align:"right",	width:120,	formatter:Order.amountFormatter},	//세액
						{name:"tot",		index:"tot",		align:"right",	width:150,	formatter:Order.amountFormatter},	//합계금액
						{name:"bas_amt",	index:"bas_amt", 	hidden:true},	//bas_amt
						{name:"dc_danga",	index:"dc_danga", 	hidden:true},	//dc_danga
						{name:"dc_amt",		index:"dc_amt", 	hidden:true},	//dc_amt
						{name:"percent",	index:"percent", 	hidden:true},	//percent
						{name:"saupjang_cd",index:"saupjang_cd",hidden:true},	//제품 구분 코드(일반,마약,향정)
						{name:"psb_qty",	index:"psb_qty",	hidden:true},	//주문한도
						{name:"m_qty",		index:"m_qty",		hidden:true}	//m_qty
					],
			rowNum: -1,
			onSelectRow: editRow,		//row를 클릭등으로 selection할 때 editRow 함수를 실행함
			height:461,
			autowidth:true,
			loadComplete: function(data){	//조회 완료시 호출되는 function
				
				if(data.records == 0){		//조회 결과가 없을 경우
					alert("해당 제품단가 자료가 없습니다.");
					saleActionFlag=false;
				}else{						//조회 결과가 있을 경우
					saleActionFlag=true;
					$("#grid_list").jqGrid('setSelection', data.rows[0].item_id);	//첫번째 로우 편집 모드로 변경
				}
			}			
   		});
		
		/* 입력 버튼 클릭 */
		$("#p_insert").on("click",function(){
			searchStore();
		});
		
		/* 저장 버튼 클릭 */
		$("#p_save").on("click",function(){
			saveRow();
		});
		
		/* 인쇄 버튼 클릭 */
		$("#p_print").on("click",function(){
			gridPrint();
		});
		
		/* 엑셀 버튼 클릭 */
		$("#p_excel").on("click",function(){
			Commons.extraAction(saleActionFlag, 'excel', '<%=ONLINE_ROOT%>/order/onderInsertGridListExcelDown.do', '');
		});
		
		/* 닫기 버튼 클릭 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('주문등록');
		});
		
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
    $(window).resize(function(){
		$("#grid_list").setGridWidth($('.wrap_result_group').width()-2, true);	//grid 영역의 넓이가 동적으로 조절 
	});
    
    /* 화면의 모든 객체가 모두 로드되었을 때 */
	$(window).load(function(){
		/* 엔터키를 눌렀을 경우 조회되도록 */
		$("body").on("keydown", function(event){
			if($("#p_insert").prop('disabled') == false){
				if(event.keyCode==13 && event.target.name!="grid_count"){
					searchStore();
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
		//CHOE 2017.02.22 관리부 이준 요청 : 선입금 처가 아닌 경우에만 회전일 관리를 확인한다. (선입금처 회전일 무시하고 주문)
		if('<%=orderInit.getPre_deposit()%>' == 'N'){
			if (("" != $("#control_rate_day").val() && 0 < Number($("#control_rate_day").val())) && Number($("#rate_day").val()) > Number($("#control_rate_day").val())) {
				alert("회전일이 기준 회전일(" + $("#control_rate_day").val() + "일) 보다 초과되었습니다.");
				$("#rcust_id_name").val("");
				$("#rsawon_info").val("");
				$("#rsawon_id").val("");
				return;
			}
		}
		
		if(rcust_id != '' ){
			$.ajax({
				type:"POST",
				url:"<%=ONLINE_ROOT%>/order/common/storeChkAjax.do",
				data:{ rcust_id : rcust_id },
				dataType:"json",
				success:function(data){
					if(data.ls_budong_yn=='y'){
						alert("출하중지처 입니다.");
						$("#rcust_id").val("");
						$("#rcust_id_name").val("");
						return;
					}
					if(data.ls_cust_nm=='' && data.ls_sawon_id==''){
						alert("등록된 판매처코드가 아닙니다.");
						$("#rcust_id_name").val("");
						$("#rsawon_info").val("");
					}else{
						$("#rcust_id_name").val(data.ls_cust_nm);
						if(data.ls_sawon_info==''){
							alert("사원코드: "+data.ls_sawon_id+"로 담당자 자료가 없습니다.");
						}else{
							$("#rsawon_info").val(Commons.trim(data.ls_sawon_info));
							$("#rsawon_id").val(data.ls_sawon_id);
						}
						
						// 파라미터 셋팅
						var param = "rcust_id=" + $('#rcust_id').val() ;
						// 제품 jqgrid 호출
						$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/itemGridList.do?" + param}).trigger("reloadGrid");
						lastSelection=0;
						itemIdArray='';
						$("#ls_limit_yn").val('');
						$("#ls_pro_date").val('');
						$("#ls_pro_bigo").val('');
					}
				},error:function(err){
					alert("storeChkAjax 데이터베이스 관련 오류 발생 "+err.status+'error');
				}
			});
		} else {
			$("#rcust_id_name").val("");
			$("#rsawon_info").val("");
			$("#rsawon_id").val("");
		}
	}
	
    /**
     *	jqGrid에서 제품을 선택했을 시 하단 평균수량, 주문한도 정보등을 DB에서 조회한 후 화면에 셋팅한다.
     *	현재 선택한 제품 코드가 lastSelection변수에 저장된 값과 다를 경우 rowItemChk 함수를 호출해서 이전에 입력한 제품의 수량의 유효성을 체크한다.
     * @param id	선택한 제품 코드
     */
    function editRow(id) {
		var grid = $("#grid_list");
		//하단 평균수량, 주문한도 수량 parameter
		var ls_item_id = grid.getRowData(id).item_id;
       	var saupjang_cd = grid.getRowData(id).saupjang_cd;
       	var req_date = $('#req_date').val();
       	
       	if(!formCheck.isDateFormat(req_date)){
			alert('주문요청일 형식(YYYY-MM-DD)을 확인해주세요.');
			return;
		}
       	var ls_ymd = req_date.replace(/-/gi,"");
       	
       	var lsYn = '';											//허가증 여부
       	//하단 평균수량, 주문한도 수량 ajax
  		$.ajax({
			type:"POST",
			url:"<%=ONLINE_ROOT%>/order/common/itemGbYnAjax.do",
			data:{ ls_item_id : ls_item_id, rcust_id : $('#rcust_id').val(), ls_ymd:ls_ymd, saupjang_cd : saupjang_cd},
			dataType:"json",
			async: false ,
			success:function(data){
				lsYn = data.ls_yn;
				if(lsYn == 'N'){ 
					grid.restoreRow(id);
					alert('허가증이 필요합니다.(해당 주문 내역은 저장되지 않습니다.)');
					return;
				}else{
					if(data.ll_mavg_qty != 0){
						$("#mavg_qty").val(data.ll_mavg_qty);
					}else{
						$("#mavg_qty").val("");
					}
					if(data.ll_mqty != 0){
						$("#m_qty").val(data.ll_mqty);
						grid.setCell(id,"m_qty", data.ll_mqty);
					}else{
						$("#m_qty").val("");
					}
					if(data.ll_psb_qty != 0){
						$("#psb_qty").val(data.ll_psb_qty);
						grid.setCell(id,"psb_qty", data.ll_psb_qty);
					}else{
						$("#psb_qty").val("");
					}
				}
			},error:function(err){
				alert("itemGbYnAjax 데이터베이스 관련 오류 발생 "+err.status+'error');
			}
      	});	
  		//선택한 row를 수정폼으로 변경하고, 그전에 수량을 입력한 row가 있다면 그 수량에 대한 validation check를 한다.
  		if (id && id != lastSelection) {
  			if(lsYn != 'N'){
  				rowItemChk('row', id);
  			} else {
  				if(lastSelection != 0){
  					grid.setSelection(lastSelection);
  	  				rowItemChk('row', lastSelection);
  				}
  			}
  			
        } 
    }
	
	/**
	 * tab키나 enter키를 입력했을 경우 현재 로우가 마지막 로우일 경우 현재 로우에서 입력된 수량으로  validation check를 한다.
	 * @param rowId	현재 row id
	 * @param nextRowId	다음 row id
	 */
    function jqgridEditOptions_callback(rowId, nextRowId){
		if(rowId && rowId != null && !(nextRowId && nextRowId != null)){
			rowItemChk('row', rowId);
		}
    }
	
	/**
	 * 	저장버튼 click시 거래처에 대한 유효성을 판단한 후 rowItemChk 함수를 호출해서 다음 저장 프로세스 진행한다.
	 *	함수 호출 흐름을 본다면 saveRow() -> rowItemChk() -> damboCheck() -> insertOrderAjax()
	 */
	function saveRow(){
		
		if('<%=orderInit.getPre_deposit()%>' == 'Y'){
				alert("주문금액만큼의 선입금이 필요합니다.");
		}
		//더블클릭 방지
		if(inserYn==1){
			return;
		}
		<%if(!StringUtil.nvl2(orderInit.getLs_budong_yn(),"").equals("")){%>	
		
		//출하중지처체크
			<%if(StringUtil.nvl2(orderInit.getLs_budong_yn(),"").equals("Y")){%>
				alert("주문이 제한 되었습니다.\n담당자에게 문의하시기 바랍니다.");
				return;
			<%}%>
			//E-mail체크
			<%if (StringUtil.nvl2(orderInit.getLs_email(),"").equals("")){%>
				alert("해당거래처의 E-mail주소 정보가 없습니다.\n담당자에게 문의하시기 바랍니다.");
				return;
			<%}%>
		<%}%>
		if($("#rcust_id").val()==''){
			alert("판매처를 먼저 선택해주세요.");
			return;
		}
		var req_date = $('#req_date');
       	if(!formCheck.isDateFormat(req_date.val())){
			alert('주문요청일 형식(YYYY-MM-DD)을 확인해주세요.');
			req_date.focus();
			return;
		}
		// DB 80byte 길이 제한
		if (formCheck.getByteLength($('#bigo').val())>75){
			alert('요청사항의 내용은 한글 37자, 영문 80자로 입력해주세요(75byte)');
			return;
		}
		inserYn=1;
		rowItemChk('save',0);
	}
	
	
	/**
	 * 선택한 row를 수정폼으로 변경하고, 그전에 수량을 입력한 row가 있다면 그 수량에 대한 validation check를 한다.
	 *	함수 호출 흐름을 본다면 saveRow() -> rowItemChk() -> damboCheck() -> insertOrderAjax()
	 * @param proc	saveRow에서 호출한건지, editRow함수에서 호출한건지 구분
	 * @param id	현재 선택한 제품 코드
	 */
	function rowItemChk(proc,id){
		
		//변수 생성
		var grid = $("#grid_list");
		var ld_req_qty = 0;
		var ld_bas_danga = 0;
		var ld_bal_danga = 0;
		var ld_dc_danga = 0;
		var supply_net = 0;
		var supply_vat = 0;
		var amt=0;
		var vat=0;
		var tot_amt=0;
		
		/* 제품 선택 여부*/
		if(!grid.getGridParam("selrow")){
			alert("제품을 선택한 뒤 수량을 입력해 주세요.");
			inserYn=0;
			return;
		}
		/* saveRow에서 호출했을 경우 lastSelection id가 현재 select row id여야 한다. */
		if(id==0){
			lastSelection=grid.getGridParam("selrow");	
		}
		
		/*
		*	lastSelection가 0인 경우 제품선택을 한번도 하지 않았으므로 선택한 제품의 수량 변경할 수 있도록 해주고,
		*	0이 아닐 경우 lastSelection에 저장된 제품코드와 입력된 수량으로 가격 계산을 하고, 제품의 상태(재고,출하중지 등)를 DB에서 조회한 후 체크한다. 			
		*/
		if(lastSelection == 0){		//lastSelection가 0인 경우
			grid.editRow(id);
			lastSelection = id;
			
		}else{						//lastSelection가 0이 아닌 경우 						
			
			var row = grid.getRowData(lastSelection);
			ld_req_qty = $('#'+row.item_id+'_qty').val();
			
			if(ld_req_qty == "" || typeof ld_req_qty == "undefined"){
				ld_req_qty = row.qty;	//editing이 아닐 경우
			}
			
			if(isNaN(ld_req_qty)){
				alert("숫자만 입력가능합니다.");
				grid.setSelection(lastSelection);
				$('#'+row.item_id+'_qty').val("");
				inserYn=0;
				return;
			}	
			
			
			ld_bas_danga = row.bas_amt;
			ld_bal_danga = (row.bal_amt).replace(/,/gi,"");
			ld_dc_danga = row.dc_danga;
			

			//3,4번대거래처는 기준가로 계산한다.
			var ls_rcust_id = $('#rcust_id').val();
			
			//공급가액
			supply_net = ld_bal_danga * ld_req_qty;
			
			//세액
			if (ls_rcust_id.charAt(0) == '3' || ls_rcust_id.charAt(0) == '4' ){
				supply_vat = (ld_bas_danga * ld_req_qty) - (ld_bal_danga * ld_req_qty);		
			}else{
				supply_vat = Math.floor(ld_bal_danga * ld_req_qty * 0.1);
			}
			
			var ll_dc_amt = ld_dc_danga * ld_req_qty;
			
			//grid cell에 값 셋팅
			grid.setCell(lastSelection,"supply_vat", supply_vat);
			grid.setCell(lastSelection,"supply_net", supply_net);
			grid.setCell(lastSelection,"dc_amt",ll_dc_amt);
			
			//공급가액+세액 = 합계금액
			grid.setCell(lastSelection,"tot",supply_net + supply_vat);
			
			//grid 하단 총 공급가액, 세액, 공급총액 구하는 방식
			var colSupply_net = grid.getCol('supply_net', true);
			var colSupply_vat = grid.getCol('supply_vat', true);
			var colTot = grid.getCol('tot', true);
			
			for(var colInt=0; colInt < colSupply_net.length;colInt++){
				if(colSupply_net[colInt].value!=''){
					var str = (colSupply_net[colInt].value).replace(/,/gi,"");
					amt += parseInt(str);
				}
				if(colSupply_net[colInt].value!=''){
					var str = (colSupply_vat[colInt].value).replace(/,/gi,"");
					vat += parseInt(str);
				}
				if(colSupply_net[colInt].value!=''){
					var str = (colTot[colInt].value).replace(/,/gi,"");
					tot_amt += parseInt(str);
				}
			}
			
			var ld_rem_dambo = parseInt($('#ld_rem_dambo').val()); 
			 
			//grid 하단 공급가액, 세액, 공급총액, 주문 가능액 value set
			$('#amt').val(Commons.addComma(amt));
			$('#vat').val(Commons.addComma(vat));
			$('#tot_amt').val(Commons.addComma(tot_amt));
			$('#rem_dambo').val(Commons.addComma(parseInt(ld_rem_dambo) - tot_amt));
			
			var ll_supply_net = parseInt(supply_net);
			if( ll_supply_net > 0 && ll_dc_amt > 0 ){
				if( ll_supply_net <= ll_dc_amt ){
					alert('에러가 발생 되었습니다.\n화면을 닫고 다시열어어서 주문을 시도하여도 계속발생시\n하나제약 본사로 연락바랍니다.\n'+
							'error code:'+ll_supply_net+':'+ll_dc_amt+':'+ld_req_qty);
					grid.setSelection(lastSelection);
					grid.editRow(lastSelection,true);
					inserYn=0;
					return;
				}
			}
			/* 신용담보금액은 기존로직대로 구하고, 주문가능액은 김태안팀장님이 요청한대로 수정함.2015-01-22 
			 * 주문가능금액 은 수량을 입력할때마다 변경되어야 하는데...계산식은 다음과 같다.   수정요청=> 
	      		주문가능금액 = 여신한도액 - 총여신 - 현재접수상태의주문금액- 현재주문액 합계.    
	      		이때, 주문가능금액이 마이너스가 되는 순간 여신금액위반이라고 떠야 함. 
	        		> 총여신     : 메인화면의 여신현황의 총여신 가져오는 로직과 동일
	        		> 여신한도액 : sale.sale0003 에 credit_limit_amt (여신한도액)
	      		ld_rem_dambo => 주문가능액(여신한도액 - 총여신)
			 * */
			/*
			선입금 거래처 체크로직이 현재 운영에는 없지만 추가되어야 함
        	sale0003.pre_deposit  가 'Y' 이면 "주문금액만큼의 선입금이 필요합니다." 라고 메세지만 보여주고
              확인하면 주문은 되도록. 여신한도액 체크 안 함.
			*/
			if('<%=orderInit.getPre_deposit()%>' == 'N'){
				var v_ld_credit_limit_amt = $('#ld_credit_limit_amt').val().replace(/,/gi,"");
				var v_rem_dambo = $('#rem_dambo').val().replace(/,/gi,"");
				if(v_ld_credit_limit_amt>0 && parseInt(tot_amt)>0){
					if(v_rem_dambo < 0){
						alert('주문금액이 여신 한도액을 초과하였습니다. 담당자에게 문의 바랍니다.');
						grid.setSelection(lastSelection);
						grid.editRow(lastSelection,true);
						inserYn=0;
						return;
					}
				}
			}
			
		
			var ls_rcust_id = $('#rcust_id').val();							//판매처 코드
			var ld_req_qty = parseInt($('#'+row.item_id+'_qty').val());		//사용자가 입력한 수량
			
			
			if(ld_req_qty == 0 || $('#'+row.item_id+'_qty').val()==''){		//수량이 0 또는 아무것도 입력하지 않았을 경우
				
				$('#'+row.item_id+'_qty').val("");
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
				grid.saveRow(lastSelection);
				lastSelection = id;
				
				if(proc=='save'){			//저장버튼을 클릭했을 경우 damboCheck() 호출해서 다음 저장 프로세스를 진행한다.
					damboCheck();
				}else{						
					grid.editRow(id);
					$('#'+id+'_qty').focus();
				}
			}else{			
				/*
				*	수량이 입력되엇을 경우 제품의 상태(재고,출하중지 등)를 DB에서 조회한 후 체크한다.
				*/
				//태그 제거 정규식
				var pattern = /<[^>]+>/g;
							
				//제품 체크
				var ls_item_cd = row.item_id;
				var ls_item_nm = row.item_nm.replace(pattern, '');
				var ls_standard = row.standard;
				var ldt_jaego = $('#req_date').val().replace(/-/gi,"");
				$.ajax({
					type:"POST",
					url:"<%=ONLINE_ROOT%>/order/itemChkRegAjax.do",
					data:{ls_item_cd:ls_item_cd, ldt_jaego:ldt_jaego, ls_rcust_id:ls_rcust_id},
					dataType:"json",
					success:function(data){
						
						if (data.ls_chul_yn == '' && data.ls_use_yn == ''){
							alert('제품코드 : '+ (ls_item_cd) +'\n' + (ls_item_nm) + ' ' + (ls_standard) + '는 등록된 제품코드가 아닙니다.!');
							grid.setSelection(lastSelection);
							$('#'+row.item_id+'_qty').val("");
							grid.editRow(lastSelection,true);
							inserYn=0;
							return;
						}else{
							
							if (data.ls_use_yn == 'N'){
								alert('제품코드 : '+ (ls_item_cd)+'\n' + (ls_item_nm) + ' ' + (ls_standard) + '는 단종된 제품입니다.\n담당자에게 문의 하시기 바랍니다.');
								grid.setSelection(lastSelection);
								$('#'+row.item_id+'_qty').val("");
								grid.editRow(lastSelection,true);
								inserYn=0;
								return;
							}
							if (data.ls_chul_yn == 'Y'){
								alert('제품코드 : '+ (ls_item_cd) +'\n' + (ls_item_nm) + ' ' + (ls_standard) + '는 출하중지된 제품입니다.\n담당자에게 문의 하시기 바랍니다.');
								grid.setSelection(lastSelection);
								$('#'+row.item_id+'_qty').val("");
								grid.editRow(lastSelection,true);
								inserYn=0;
								return;
							}
						}
		
						if (data.ld_invjaego_qty ==''){
							alert('제품코드: '+ (ls_item_cd) +'\n' + (ls_item_nm) + ' ' + (ls_standard) + '는 재고가 없습니다.');
							grid.setSelection(lastSelection);
							$('#'+row.item_id+'_qty').val("");
							grid.editRow(lastSelection,true);
							inserYn=0;
							return;
						}else{
							if( parseInt(data.ld_invjaego_qty) >= parseInt(data.ld_jaego_qty) ){				
								//가용재고만큼만
								if ( parseInt(ld_req_qty) > parseInt(data.ld_jaego_qty) ){
									alert('주문수량이 가용 재고 수량을 초과하였습니다.\n제품코드: '+ (ls_item_cd) +'\n' + (ls_item_nm) +' '+(ls_standard)+'는 현재고: '+ Commons.addComma(data.ld_jaego_qty)+ ' 입니다.');
									grid.setSelection(lastSelection);
									$('#'+row.item_id+'_qty').val("");
									grid.editRow(lastSelection,true);
									inserYn=0;
									return;
								}
							} else {
								//창고재고만큼만
								if( parseInt(ld_req_qty) > parseInt(data.ld_invjaego_qty) ){
									alert('주문수량이 창고 재고 수량을 초과하였습니다.\n제품코드: '+ (ls_item_cd) +'\n' + (ls_item_nm) +' '+(ls_standard)+'는 현재고: '+ Commons.addComma(data.ld_invjaego_qty)+ ' 입니다.');
									grid.setSelection(lastSelection);
									$('#'+row.item_id+'_qty').val("");
									grid.editRow(lastSelection,true);
									inserYn=0;
									return;
								}
							}
							
						} 
						//kta 2016.05.25
						//아시크라듀오시럽50m 1병  30003 은 40개 단위로만 주문가능하도록 제어
						if (ls_item_cd == '30003'){
							if ( (parseInt(ld_req_qty) % 40) != 0) {
								alert('제품코드: '+ (ls_item_cd) +'\n' + (ls_item_nm) + ' ' +(ls_standard)+ '는 40개 단위로 주문되어야 합니다.\n ');
								return;								
							}
						}
						
						//제품별 주문한도
						var psb_qty = row.psb_qty;		//주문한도
						var m_qty = row.m_qty;			//이달 합계수량
						var tot_m_qty = parseInt(ld_req_qty)  + parseInt(m_qty);
						if( data.li_cnt == 0 ){		//개시후 3개월 초과거래처 => (이달합계수량 + 현재입력수량) > 30 이면 alert
							//if((parseInt(ld_req_qty)  + parseInt(m_qty)) > parseInt(psb_qty) ){
							if( tot_m_qty > parseInt(psb_qty) ){	
								alert('제품코드: '+ (ls_item_cd) +'\n'+(ls_item_nm) + ' ' +(ls_standard)+ ' 는(은) 주문한도 수량(' + parseInt(psb_qty) + ')보다 초과되었습니다.이달총주문수량(' + tot_m_qty + ')\n승인이 보류 또는 반려될 수 있으니 담당자와 상의바랍니다.1');
							}	
						} else {	//개시후3개월 이내거래처 => (이달합계수량 + 현재입력수량) > 주문한도 이면 alert
							//if( parseInt(ld_req_qty) + parseInt(m_qty) > 30 ){
							if( tot_m_qty > 30 ){
								if (ls_item_cd != '30003'){  //아시크라듀오시럽50m 1병 은 40병 단위로 구매해야 하므로 제외한다
									alert('제품코드: '+ (ls_item_cd) +'\n' + (ls_item_nm) + ' ' +(ls_standard)+ ' 는(은) 주문한도 수량(' + parseInt(psb_qty) + ')보다 초과되었습니다.이달총주문수량(' + tot_m_qty + ')\n승인이 보류 또는 반려될 수 있으니 담당자와 상의바랍니다.2');
								}
							}
						}
						
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
						//$('#'+row.item_id+'_qty').val(Number($('#'+row.item_id+'_qty').val()));
						grid.saveRow(lastSelection);
						
						if(proc=='save'){				//저장버튼을 클릭했을 경우 damboCheck() 호출해서 다음 저장 프로세스를 진행한다.
							damboCheck();
						}else{
							grid.editRow(id);
							lastSelection = id;
						}
					},error:function(err){
						alert("itemChkAjax 데이터베이스 관련 오류 발생 "+err.status+'error');
					}
				});
			}
		}
	}
	
	/**
	 * 판매처 검색
	 */
	function searchStore(){
		
		//CHOE 2017.02.22 관리부 이준 요청 : 선입금 처가 아닌 경우에만 회전일 관리를 확인한다. (선입금처 회전일 무시하고 주문)
		if('<%=orderInit.getPre_deposit()%>' == 'N'){
			if (("" != $("#control_rate_day").val() && 0 < Number($("#control_rate_day").val())) && Number($("#rate_day").val()) > Number($("#control_rate_day").val())) {
				alert("회전일이 기준 회전일(" + $("#control_rate_day").val() + "일) 보다 초과되었습니다.");
				return false;
			}
		}
		
		if($('#rcust_id').val()==''){
			alert("판매처를 먼저 입력하세요.");
			return;
		}else{
			lastSelection=0;
			itemIdArray='';
			storeChkAjax($('#rcust_id').val());
			$("#ls_limit_yn").val('');
			$("#ls_pro_date").val('');
			$("#ls_pro_bigo").val('');
		}
	}
	
	/**
	 * 선입금 거래처 체크, 담보체크
	 * 담보예의가 아니면서 공급가합계 > 주문가능금액 일 경우 담보제공 약속 등록 팝업
	 *	함수 호출 흐름을 본다면 saveRow() -> rowItemChk() -> damboCheck() -> insertOrderAjax()
	 */
	function damboCheck(){
		if(itemIdArray==''){
			alert("수량 입력된 제품이 없습니다.");
			var grid = $("#grid_list");
			//grid.editRow(grid.getGridParam("selrow"),true);
			inserYn=0;
			return;
		}else{
			
			var ibCredit_Over = false;
			var ld_supply_tot_sum = $('#tot_amt').val().replace(/,/gi,"");

			if ('<%=StringUtil.nvl(orderInit.getIs_yeondae3(),"")%>' == 'Y'){		//담보예외
				$("#ls_limit_yn").val('E');
			}else{
				if (parseInt(ld_supply_tot_sum) > parseInt(<%=orderInit.getCredit_dambo()%>)){	//공급가합계 > 주문가능금액 
					ibCredit_Over = true;
					$("#ls_limit_yn").val('Y');		//여신초과
				}else{
					$("#ls_limit_yn").val('N');		//여신이내
				}
			}
			
			if(ibCredit_Over && '<%=orderInit.getPre_deposit()%>' != 'Y'){		// 선주문 거래처가 아니거나 담보예의가 아니면서 공급가합계 > 주문가능금액 일 경우 담보제공 약속 등록 팝업
				Commons.popupOpen('damboCheck', '<%=ONLINE_ROOT%>/order/common/guaranteePopup.do', '480', '391');	//팝업 호출
			}else{
				insertOrderAjax();
			}
		}
	}
		
	/**
	 * 담보제공 약속 등록 팝업에서 기일과 약속사항을 입력하고 닫았을 경우 주문 등록 진행
	 * @param ls_pro_date	기일
	 * @param ls_pro_bigo	약속사항
	 */
	function damboValue(ls_pro_date, ls_pro_bigo){
		$('#ls_pro_date').val(ls_pro_date);
		$('#ls_pro_bigo').val(ls_pro_bigo);
	
		insertOrderAjax();
	}
		
	/**
	 * 주문 등록.
	 *	함수 호출 흐름을 본다면 saveRow() -> rowItemChk() -> damboCheck() -> insertOrderAjax()
	 */
	function insertOrderAjax(){
		var grid = $("#grid_list");
		var arry = itemIdArray.split(',');
		var itemFrm = $('#itemFrm');
		
		$('#itemFrm').empty();		// input tag 생성전에 초기화
		
		$("<input></input>").attr({type:"hidden", name:"ldt_req_date",	value: $('#req_date').val().replace(/-/gi,"")}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"ls_sawon_id",	value:'<%=StringUtil.nvl(orderInit.getLs_sawon_id(),"")%>'}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"ls_rsawon_id",	value:$("#rsawon_id").val()}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"ls_rcust_cd",	value:$("#rcust_id").val()}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"ls_limit_yn",	value:$("#ls_limit_yn").val()}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"ls_pro_date",	value:$("#ls_pro_date").val()}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"ls_pro_bigo",	value:$("#ls_pro_bigo").val()}).appendTo(itemFrm);
		$("<input></input>").attr({type:"hidden", name:"bigo",			value:$("#bigo").val()}).appendTo(itemFrm);
		
		var chkQtyCnt = 0;
		var firstItemId = "";
		for(var i=0; i < arry.length; i++ ){
			var row = grid.getRowData(arry[i]);
			
			$("<input></input>").attr({type:"hidden", name:"item_id", 		value:row.item_id}).appendTo(itemFrm);
			$("<input></input>").attr({type:"hidden", name:"qty", 			value:row.qty}).appendTo(itemFrm);
			$("<input></input>").attr({type:"hidden", name:"bal_amt", 		value:(row.bal_amt).replace(/,/gi,"")}).appendTo(itemFrm);
			$("<input></input>").attr({type:"hidden", name:"supply_net", 	value:(row.supply_net).replace(/,/gi,"")}).appendTo(itemFrm);
			$("<input></input>").attr({type:"hidden", name:"supply_vat", 	value:(row.supply_vat).replace(/,/gi,"")}).appendTo(itemFrm);
			$("<input></input>").attr({type:"hidden", name:"percent", 		value:row.percent}).appendTo(itemFrm);
			$("<input></input>").attr({type:"hidden", name:"dc_amt", 		value:row.dc_amt}).appendTo(itemFrm);
			$("<input></input>").attr({type:"hidden", name:"dc_danga", 		value:row.dc_danga}).appendTo(itemFrm);
			$("<input></input>").attr({type:"hidden", name:"saupjang_cd", 	value:row.saupjang_cd}).appendTo(itemFrm);
			
			var ld_req_qty = row.qty;
			if($.trim(ld_req_qty) == "" || isNaN(ld_req_qty)){
				chkQtyCnt++;
				if(firstItemId == ""){
					firstItemId = row.item_id;
				}
			}	
		}
		
		// 수량이 null 들어가는 케이스가 있어서 저장전에 수량 체크한다.
		if(chkQtyCnt > 0){
			
			if(firstItemId != ""){
				$("#grid_list").jqGrid('setSelection', firstItemId);
			}
			
			alert("수량 정보가 제대로 입력되지 않은 항목이 있습니다.");
			$('#itemFrm').empty();
			inserYn=0;
			return;
		}

		$.ajax({
			type:"POST",
			data: itemFrm.serialize(),
			url:"<%=ONLINE_ROOT%>/order/insertOrderAjax.do",
			dataType:"json",
			async: false ,
			success:function(data){
				
				if(data.result=='Y'){
					alert("주문요청 자료를 등록 하였습니다.");
					var rem_dambo = $('#rem_dambo').val().replace(/,/gi,"");
					$('#ld_rem_dambo').val(rem_dambo);
					
					
				}else{
					alert("주문요청 자료 등록이 실패하였습니다.");
				}
				
			},error:function(err){
				alert("주문등록 데이터베이스 관련 오류 발생 "+err.status+'error');
			},complete:function(){
				
				//저장후 제품 테이블 다시 reload
				var param = "rcust_id=" + $('#rcust_id').val() ;
				// 제품 jqgrid 호출
				$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/order/itemGridList.do?" + param}).trigger("reloadGrid");
				lastSelection=0;
				itemIdArray='';
				$("#ls_limit_yn").val('');
				$("#ls_pro_date").val('');
				$("#ls_pro_bigo").val('');
				$('#itemFrm').empty();
				$("#mavg_qty").val('');
				$("#psb_qty").val('');
				$("#amt").val('');
				$("#vat").val('');
				$("#tot_amt").val('');
				inserYn=0;
			}
		});
		
	}

	/**
	 * 	인쇄를 하기위해 grid 재생성 	
	 */
	function gridPrint(){
		$('#orderInsert').html('<table id=\"grid_list_clone\"></table>');
		
		var param = "rcust_id=" + $('#rcust_id').val() ;
		$("#grid_list_clone").jqGrid({
			url: "<%=ONLINE_ROOT%>/order/itemGridList.do?" + param,
			// 요청방식
			mtype:"post",
			// 결과물 받을 데이터 타입
			datatype:"json",
			// 컬럼명
			colNames:["제품코드","제품명","규격","수량","단가","공급가액","세액","합계금액"],
			// 컬럼 데이터(추가, 삭제, 수정이 가능하게 하려면 autoincrement컬럼을 제외한 모든 컬럼을 editable:true로 지정)
			// edittyped은 text, password, ... input type명을 사용 
			colModel:[
						{name:"item_id",	index:"item_id",	align:"center",	width:120,	key:true},							//제품코드
						{name:"item_nm",	index:"item_nm ",	align:"left",	width:300,	formatter:Order.saupjangFormatter},	//제품명
						{name:"standard",	index:"standard", 	align:"center",	width:120},										//규격
						{name:"qty",		index:"qty", 		align:"center", width:100,	editable: true, editype : "text", formatter:"textbox"},			//수량
						{name:"bal_amt",	index:"bal_amt", 	align:"right", 	width:100,	formatter:Order.amountFormatter},	//단가
						{name:"supply_net",	index:"supply_net", align:"right",  width:120,	formatter:Order.amountFormatter},	//공급가액
						{name:"supply_vat",	index:"supply_vat", align:"right",	width:120,	formatter:Order.amountFormatter},	//세액
						{name:"tot",		index:"tot",		align:"right",	width:150,	formatter:Order.amountFormatter}	//합계금액
					],
			//-1로 하면 전체 갯수 
			rowNum: -1,
			//수정할수 있는 폼으로 바뀜
			onSelectRow: editRow,
			height:580,
			loadComplete: function(data){
				Commons.extraAction(saleActionFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', 'orderInsert');
			}
   		});		
	}
	
	function initdamboCheck(){
		var grid = $("#grid_list");
		grid.editRow(grid.getGridParam("selrow"),true);
		inserYn=0;
		return;
	}
	
	/* 회전일, 주문제어 회전일 비교 */
	/* function rateDayCheck(){
		var control_rate_day = $("#control_rate_day").val();
		var rate_day = $("#rate_day").val();
		
		rate_day = "150";
		var result = "Y";
		if (Number(rate_day) > Number(control_rate_day)) {
			alert("회전일이 기준 회전일(" + control_rate_day + "일) 보다 초과되었습니다.");
			result = "N";
		}
		
		return result;
	} */
	
	/* 거래처 팝업 열기 */
	function openCustomerPopup() {		
		//CHOE 2017.02.22 관리부 이준 요청 : 선입금 처가 아닌 경우에만 회전일 관리를 확인한다. (선입금처 회전일 무시하고 주문)
		if('<%=orderInit.getPre_deposit()%>' == 'N'){			
			if (("" != $("#control_rate_day").val() && 0 < Number($("#control_rate_day").val())) && Number($("#rate_day").val()) > Number($("#control_rate_day").val())) {
				alert("회전일이 기준 회전일(" + $("#control_rate_day").val() + "일) 보다 초과되었습니다. pop");
				return false;
			}
		}
		Commons.popupOpen('rcust_id', '<%=ONLINE_ROOT%>/order/common/storeListPopup.do', '600', '655');
	}
	
	</script>
	
</head>
<body>
	<form id="frm" name="frm">
	
		<!-- temp01 -->
		<input type="hidden" id="ld_credit_limit_amt" name="ld_credit_limit_amt" value="<%=(long)orderInit.getLd_credit_limit_amt()%>" />
		<!-- temp02 -->
		<input type="hidden" id="ld_sale_tot_credit" name="ld_sale_tot_credit" value="<%=(long)orderInit.getLd_sale_tot_credit()%>" />
		<input type="hidden" id="m_qty" name="m_qty" value="" />
		<input type="hidden" id="item_seq" name="item_seq" value="" /> 
		<input type="hidden" id="credit_dambo" name="credit_dambo" value="<%=(long)orderInit.getCredit_dambo()%>" />
		<input type="hidden" id="ld_rem_dambo" name="ld_rem_dambo" value="<%=(long)orderInit.getLd_rem_dambo()%>" />
		<input type="hidden" id="rsawon_id" name="rsawon_id" value="" />
		<input type="hidden" id="ls_pro_date" name="ls_pro_date" value="" />
		<input type="hidden" id="ls_pro_bigo" name="ls_pro_bigo" value="" />
		<input type="hidden" id="ls_limit_yn" name="ls_limit_yn" value="" />
		
		<input type="hidden" id="control_rate_day" name="control_rate_day" value="" />
		<input type="hidden" id="rate_day" name="rate_day" value="" />
	
		<!-- ##### content start ##### -->	
		<div class="wrap_search">
			<div class="search">
				<div class="float_l">
					<label class="w70">주문요청일</label>
					<p class="wrap_date">
						<input type="text" id="req_date" name="req_date" maxlength="10" onchange="javascript:Commons.setDate('','req_date');"  readonly class="ipt_disable" />
						<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
					</p><br />
					
					<label class="w70">판매처</label>
					<input type="text" class="w140" id="rcust_id" name="rcust_id"  onchange="javascript:storeChkAjax(this.value);"/>
					<button type="button" class="btn_search" onclick="javascript:openCustomerPopup();"><span class="blind">찾기</span></button>
					<input type="text" class="w265 ipt_disable" id="rcust_id_name" name="rcust_id_name" readonly/><br />
					
					<label class="w70">요청사항</label>
					<input type="text" class="w435" id="bigo" name="bigo" value=""/>
				</div>
				<div class="float_r">
					<label class="w70">도매담당자</label>
					<input type="text" class="w350 ipt_disable" id="sawon_info" name="sawon_info" value="<%=ls_sawon_info.trim() %>" readonly/><br />
					<label class="w70">병원담당자</label>
					<input type="text" class="w350 ipt_disable" id="rsawon_info" name="rsawon_info" value="" readonly/><br />
					<label class="w70">총담보</label>
					<input type="text" class="w120 ta_right ipt_disable" id="tot_dambo" name="tot_dambo" value="" readonly/>
					<label class="w70 ml10">주문가능액</label>
					<input type="text" class="w140 ta_right ipt_disable" id="rem_dambo" name="rem_dambo" value="" readonly/>
				</div>
			</div>
		</div>
		
		<div class="wrap_btn_group">
			<div class="btn_group">
				<div class="float_l">
					[설명] (평균수량=직전3개월평균 * <%=(long)orderInit.getLs_jumun_limit() %>%) (주문한도=평균수량-해당월)
				</div>
				<div class="float_r ta_r">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
				</div>
			</div>
		</div>
		<div class="inner_cont2">
			<div class="wrap_result_group">
				<table id="grid_list"></table>
			</div>
			<div class="wrap_result_group">
				<div class="result_group">
					<div class="float_l">
						<label class="point">평균수량</label>
						<input type="text" class="w100 ta_right ipt_disable" id="mavg_qty" readonly/>
						<label class="point ml10">주문한도</label>
						<input type="text" class="w100 ta_right ipt_disable" id="psb_qty" readonly/>
					</div>
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
	<div id="orderInsert" style="display:none;"><table id="grid_list_clone"></table></div>
	<form id="itemFrm" name="itemFrm">
	</form>
	<%@include file="/include/footer.jsp"%>
</body>
</html>