<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : companyCardMgmtList.jsp
 * @메뉴명 : 영업관리 > 법인카드 IBK 관리
 * @최초작성일 : 2014/11/24            
 * @author : 윤범진                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%@ page import="com.hanaph.saleon.business.vo.CompanyCardMgmtVO" %>
<%@ page import="java.util.List" %>
<%
	/* 사용자 정보 셋팅(부서, 사원, 직책) */
	CompanyCardMgmtVO companyCardMgmtInit = new CompanyCardMgmtVO();
	companyCardMgmtInit = (CompanyCardMgmtVO)request.getAttribute("companyCardMgmtInit");
	
	/* 계정목록 */
	@SuppressWarnings("unchecked")
	List<CompanyCardMgmtVO> gaejungCodeList = (List<CompanyCardMgmtVO>)request.getAttribute("gaejungCodeList");
	
	String ls_sawon_id = companyCardMgmtInit.getLs_sawon_id(); // 사원코드
	String ls_sawon_nm = companyCardMgmtInit.getLs_sawon_nm(); // 사원명
	String ls_dept_cd = companyCardMgmtInit.getLs_dept_cd(); // 부서코드
	String ls_dept_nm = companyCardMgmtInit.getLs_dept_nm(); // 부서명
	String is_assgn_cd = companyCardMgmtInit.getIs_assgn_cd(); // 직책코드
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
	
	var mgmtActionFlag = false; // 법인카드관리 부가기능(인쇄, 엑셀다운로드) 사용 가능 여부 flag
	var rowIdArray = []; // 수정 데이터를 담을 배열
	var ls_sawon_id = "<%=ls_sawon_id%>"; // 사원코드
	var ls_sawon_nm = "<%=ls_sawon_nm%>"; // 사원명
	var is_assgn_cd = "<%=is_assgn_cd%>"; // 직책코드
	var lastsel = "";
	
	$(document).ready(function(){
		
		/* 로그인 사원 정보 존재 여부 */
		if ("" == ls_sawon_id) {
			alert("로그인 사원의 기본정보를 가져오는데 실패하였습니다.");
		}
		
		/* 로그인 사원의 직책에 따른 본인 자료만 보기 셋팅 */
		if ("" == is_assgn_cd) {
			alert("로그인 사원의 직책을 가져오는데 실패하였습니다.");
		} else if ("27040" == is_assgn_cd) {
			$("#ls_sawon_id").val(ls_sawon_id);
			$("#ls_sawon_nm").val(ls_sawon_nm);
			$("#as_teammember").attr("checked", true);
		}
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리.  */
	    jsonReader : {
            repeatitems: false;
    	}
		
		/* 조회일자 셋팅(이번 달 1일 ~ 오늘) */
		Commons.setDate("as_use_dt_fr", "as_use_dt_to");
		
		/* 파라미터 셋팅 */
		var as_sawon_id = $("#as_sawon_id").val();
		var as_use_dt_fr = $("#as_use_dt_fr").val();
		var as_use_dt_to = $("#as_use_dt_to").val();
		var as_teammember = "";
		
		if ($("#as_teammember").is(":checked")) {
			as_teammember = "Y";
		} else {
			as_teammember = "N";
		}
		
		var params = {
			as_sawon_id : as_sawon_id,
			as_use_dt_fr : as_use_dt_fr,
			as_use_dt_to : as_use_dt_to,
			as_teammember : as_teammember
		};
		
		var paramStr = $.param(params);
		
		/* 법인카드 사용내역 */ 
		$("#grid_list").jqGrid({
			url:"<%=ONLINE_ROOT%>/business/companyCardHistoryGridList.do?" + paramStr,
			editurl: ONLINE_ROOT + "/business/customerDetailEtcGridList.do?" + paramStr,
			mtype:"get",
			datatype:"json",
			colNames:["사용일자","사용시간","카드번호","승인번호","사원","부서","사용금액","가맹점명","사업자번호","과세구분","부가세공제여부","구매(사용)내역 상세히기술","계정과목","계정과목코드","관리부승인", "승인자", "입력완료", "입력자", "적요", "선택", "전표결과", "전표번호", "전표생성시각", "전표승인", "전표처리상세내역"],
			colModel:[
						{name:"use_dt",					index:"use_dt",					align:"center", width:80},
						{name:"use_tm",					index:"use_tm", 				align:"center", width:60},
						{name:"card_no",				index:"card_no", 				align:"center", width:130},
						{name:"card_ok_no",				index:"card_ok_no", 			align:"center", width:70},
						{name:"sawon_nm",				index:"sawon_nm", 				align:"left", width:50},
						{name:"dept_nm",				index:"dept_nm", 				align:"left", width:100},
						{name:"use_amt",				index:"use_amt", 				align:"right", width:100,	formatter:"integer"},
						{name:"saupjang_nm",			index:"saupjang_nm", 			align:"left", width:200},
						{name:"saup_no",				index:"saup_no",				align:"center", width:90,	formatter:vouNoFormatter},
						{name:"tax_gb",					index:"tax_gb",					align:"center", width:65, formatter:taxFlagFormatter},
						{name:"gongjae_yn",				index:"gongjae_yn",				align:"center", width:80,	formatter:surtaxDeductFormatter},
						{name:"use_detail",				index:"use_detail", 			align:"left", width:200,	editable:true, edittype:"text"},
						{name:"gaejung_nm",				index:"gaejung_nm",				align:"center", width:95,	formatter:gaejungNameFormatter},
						{name:"gaejung_cd",				index:"gaejung_cd", 			hidden:true,	formatter:gaejungCodeFormatter},
						{name:"salecamp_conf_yn",		index:"salecamp_conf_yn",		hidden:true},
						{name:"salecamp_conf_sabun_nm",	index:"salecamp_conf_sabun_nm",	hidden:true},
						{name:"teamjang_conf_yn",		index:"teamjang_conf_yn",		align:"center", width:100, formatter:confirmFlagFormatter},
						{name:"teamjang_conf_sabun_nm",	index:"teamjang_conf_sabun_nm",	align:"center", width:50},
						{name:"jukyo",					index:"jukyo", 					align:"left", width:200,	editable:true, edittype:"text", editoptions:Commons.jqgridEditOptions("grid_list", "Y")},
						{name:"sel_yn",					index:"sel_yn",					hidden:true},
						{name:"junpyo_yn",				index:"junpyo_yn",				hidden:true},
						{name:"junpyo_no",				index:"junpyo_no",				hidden:true},
						{name:"junpyo_crtdt",			index:"junpyo_crtdt",			hidden:true},
						{name:"seungin_yn",				index:"seungin_yn",				hidden:true},
						{name:"junpyo_result",			index:"junpyo_result",			hidden:true}
					],
					
			formatter : {
				integer : {thousandsSeparator: ",", defaultValue: '0'} // 천단위 마다 콤마
	        },
			height:484,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* 1 row 생성 될 때 마다 호출 */
			afterInsertRow: function(rowId) {
				var salecamp_conf_yn = $("#grid_list").getCell(rowId, "salecamp_conf_yn");
				
				if (salecamp_conf_yn == 2) {
					$("#teamjang_conf_y_"+rowId).prop("disabled", true);
					$("#teamjang_conf_n_"+rowId).prop("disabled", true);
				}
			},
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if (data.records > 0) {
					$("td[aria-describedby='grid_list_gaejung_nm']").css("white-space", "nowrap");
					$("#total_cnt").val(data.totalCountInfo.total_cnt); // 목록 총 수
					$("#total_use_amt").val(Commons.addComma(data.totalCountInfo.total_use_amt)); // 총 사용금액
					mgmtActionFlag = true; // 인쇄, 엑셀 사용 가능하도록
				} else {
					alert("해당 사용내역이 없습니다.");
					mgmtActionFlag = false;					
				}
			},
			
			/* row 선택 시 */
			onSelectRow: function(id){
				if(id){
					$("#grid_list").jqGrid("saveRow",lastsel); // 편집중이던 row 저장
					$("#grid_list").jqGrid("restoreRow",lastsel); // 편집화면을 일반화면으로 변경
					
					if ("N" == $(":radio[name='teamjang_conf_yn_"+id+"']:checked").val()) { // 진행중일 경우만 작동
						$("#grid_list").jqGrid("editRow",id,true);
						lastsel=id;
						
						Business.addArray(rowIdArray, id); // update 배열에 현재 id를 넣는다.	
					}
				}
			},
			
			pager: "#grid_Pager"
   		});
		
		/* 조회 버튼 클릭 */
		$("#ct_retrieve_btn_type01").on("click",function(){
			getGridList();
		});
		
		/* 저장 버튼 클릭 */
		$("#p_save").on("click",function(){
			updateDetail();
		});
		
		/* 인쇄 버튼 클릭 */
		$("#p_print").on("click",function(){
			$(".gaejungCodeLayer").remove();
			Commons.extraAction(mgmtActionFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', 'companyCardMgmt');
		});
		
		/* 엑셀 버튼 클릭 */
		$("#p_excel").on("click",function(){
			Commons.extraAction(mgmtActionFlag, 'excel', '<%=ONLINE_ROOT%>/business/companyCardHistoryGridListExcelDown.do', '');
		});
		
		/* 닫기 버튼 클릭 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('법인카드관리IBK');
		});
		
		/* grid 영역 넓이 셋팅 */
		$("#grid_list").setGridWidth($("#companyCardMgmt").width() , false);
		
		/* 엔터키 눌렸을 경우 조회되도록. */
		$("body").on("keydown", function(event){
			if($("#ct_retrieve_btn_type01").prop('disabled') == false){
				if(event.keyCode==13 && event.target.name!="grid_count"){
					getGridList();
				return false;
				}
			}
		});
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
	$(window).resize(function(){
		$("#grid_list").setGridWidth($("#companyCardMgmt").width() , false);
		$(".gaejungCodeLayer").remove();
	});
	
	/* 사업자번호 포맷 */
	function vouNoFormatter(cellvalue, rowObject){
		var returnValue = "";
		
		if ("" != cellvalue) {
			returnValue = cellvalue.substring("0", "3") + "-" + cellvalue.substring("3", "5") + "-" + cellvalue.substring("5", "10");
		}
		returnValue += "<input type='hidden' name='saup_no_"+rowObject.rowId+"' id='saup_no_"+rowObject.rowId+"' value='"+cellvalue+"'>";
		
		return returnValue;
	}
	
	/* 과세구분 포맷 */
	function taxFlagFormatter(cellvalue, rowObject){
		var returnValue = "<select name='tax_gb_"+rowObject.rowId+"' id='tax_gb_"+rowObject.rowId+"' onchange='javascript:Business.addArray(rowIdArray, "+rowObject.rowId+");' >";
		returnValue += "<option value='1' " + (cellvalue == "1" ? "selected" : "") + ">일반</option>";
		returnValue += "<option value='2' " + (cellvalue == "2" ? "selected" : "") + ">간이</option>";
		returnValue += "<option value='3' " + (cellvalue == "3" ? "selected" : "") + ">면세</option>";
		returnValue += "<option value='4' " + (cellvalue == "4" ? "selected" : "") + ">비영리</option>";
		returnValue += "<option value='9' " + (cellvalue == "9" ? "selected" : "") + ">휴업</option>";
		returnValue += "<option value='10' " + (cellvalue == "10" ? "selected" : "") + ">폐업</option>";
		returnValue += "<option value='11' " + (cellvalue == "11" ? "selected" : "") + ">해외</option>";
		returnValue += "<option value='12' " + (cellvalue == "12" ? "selected" : "") + ">비등록</option>";
		returnValue += "</select>";
		
		return returnValue;
	}
	
	/* 부가세 공제 여부 포맷 */
	function surtaxDeductFormatter(cellvalue, rowObject){
		var returnValue = "<select name='gongjae_yn_"+rowObject.rowId+"' id='gongjae_yn_"+rowObject.rowId+"' onchange='javascript:Business.addArray(rowIdArray, "+rowObject.rowId+");' >";
		returnValue += "<option value='Y' "+(cellvalue == "Y" ? "selected" : "")+">공제가능</option>";
		returnValue += "<option value='N' "+(cellvalue == "N" ? "selected" : "")+">공제불가</option>";
		returnValue += "</select>";
		
		return returnValue;
	}
	
	/* 입력완료 포맷 */
	function confirmFlagFormatter(cellvalue, rowObject){
		var returnValue = "<input type='radio' name='teamjang_conf_yn_"+rowObject.rowId+"' id='teamjang_conf_n_"+rowObject.rowId+"' value='N' onclick='javascript:setRowId(this, "+rowObject.rowId+");' "+(cellvalue == "N" ? "checked" : "")+" /><label for='teamjang_conf_n_"+rowObject.rowId+"'> 진행 </label>";
		returnValue += "<input type='radio' name='teamjang_conf_yn_"+rowObject.rowId+"' id='teamjang_conf_y_"+rowObject.rowId+"' value='Y' onclick='javascript:setRowId(this, "+rowObject.rowId+");' "+(cellvalue == "Y" ? "checked" : "")+" /><label for='teamjang_conf_y_"+rowObject.rowId+"'> 완료 </label>";
		returnValue += "<input type='hidden' id='teamjang_conf_yn_"+rowObject.rowId+"' value='"+cellvalue+"'>";
		
		return returnValue;
	}
	
	/* 계정과목 포맷 */
	function gaejungNameFormatter(cellvalue, rowObject){
		var returnValue = "<div onclick='javascript:openLayer("+rowObject.rowId+");'>";
		returnValue += "<input type='text' name='gaejung_nm_"+rowObject.rowId+"' id='gaejung_nm_"+rowObject.rowId+"'value='"+cellvalue+"' style='width: 60px;' class='ipt_disable' readonly />";
		returnValue += "<button type='button' style='height:16px;'>▼</button>";
		returnValue += "</div>";
		
		return returnValue;
	}
	
	/* 계정과목 코드 포맷 */
	function gaejungCodeFormatter(cellvalue, rowObject){
		return cellvalue + "<input type='hidden' name='gaejung_cd_"+rowObject.rowId+"' id='gaejung_cd_"+rowObject.rowId+"' value='"+cellvalue+"'>";
	}
	
	/* 계정과목 목록 레이어 열기 */
	function openLayer(rowId){
		if ("N" == $(":radio[name='teamjang_conf_yn_"+rowId+"']:checked").val()) { // 진행중일 경우만 작동
			if ($("#rowId_" + rowId).length > 0) { // 레이어가 존재할 경우 삭제
				closeLayer(rowId);
			} else {
				$(".gaejungCodeLayer").remove(); // layer 전체 삭제
				
				var cloneLayer = $("#gaejungCodeLayer").clone(); // div 복제
				cloneLayer.addClass("gaejungCodeLayer");
				cloneLayer.attr("id", "rowId_" + rowId);
				$("#gaejung_nm_"+rowId).after(cloneLayer);
				cloneLayer.show();
			}			
		}
	}
	
	/* 계정과목 목록 레이어 닫기 */
	function closeLayer(rowId){
		$("#rowId_" + rowId).remove();
	}
	
	/* 계정과목 선택 */
	function selectGaejungCd(obj, gaejungCd, gaejungNm){
		var rowId = $(obj).parents("div").attr("id").replace("rowId_", ""); // rowId 가져오기
		$("#gaejung_cd_" + rowId).val(gaejungCd); // 계정과목 셋팅
		$("#gaejung_nm_" + rowId).val(gaejungNm);
		Business.addArray(rowIdArray, rowId); // update 배열에 현재 id를 넣는다.
		$("#rowId_" + rowId).hide(); // 레이어 닫기
	}
	
	/* 완료 처리 시 rowId 배열에 담기 */
	function setRowId(obj, rowId){
		var teamjang_conf_n = $("#teamjang_conf_n_"+rowId);
		var ret = $("#grid_list").getRowData(rowId);
	
		/* 유효성 체크 */
		if (formCheck.isEmpty($("#saup_no_"+rowId).val())) {
			alert("사업자번호가 입력되어야 완료처리가 가능합니다.");
			teamjang_conf_n.attr("checked", true);
			return false;
		}
	
		if (formCheck.isEmpty($("#gongjae_yn_"+rowId).val())) {
			alert("부가세공제여부가 입력되어야 완료처리가 가능합니다.");
			teamjang_conf_n.prop("checked", true);
			return false;
		}
		
		if (formCheck.isEmpty(ret.use_detail)) {
			alert("사용내역상세가 입력되어야 완료처리가 가능합니다.");
			teamjang_conf_n.prop("checked", true);
			return false;
		}
		
		if (formCheck.isEmpty($("#gaejung_cd_"+rowId).val())) {
			alert("계정과목이 입력되어야 완료처리가 가능합니다.");
			teamjang_conf_n.prop("checked", true);
			return false;
		}
		
		Business.addArray(rowIdArray, rowId); // update 배열에 현재 id를 넣는다.
	}
	
	/* 조회 */
	function getGridList(){
		rowIdArray = []; // 배열 초기화
		$("#insertFrm").html(""); // 폼 초기화
		
		var as_sawon_id = $("#as_sawon_id").val();
		var as_use_dt_fr = $("#as_use_dt_fr").val();
		var as_use_dt_to = $("#as_use_dt_to").val();
		var as_teammember = "";
		
		if ($("#as_teammember").is(":checked")) {
			as_teammember = "Y";
		} else {
			as_teammember = "N";
		}
		
		/* 유효성 체크 */
		if (!formCheck.isDateFormat(as_use_dt_fr)) {
			alert("올바른 조회 기간을 입력해주세요.");
			$("#ad_fr_date").focus();
			return false;
		}
		
		if (!formCheck.isDateFormat(as_use_dt_to)) {
			alert("올바른 조회 기간을 입력해주세요.");
			$("#as_use_dt_to").focus();
			return false;
		}
		
		if (formCheck.dateChk(as_use_dt_fr, as_use_dt_to) < 0) {
			alert("조회 기간을 확인하세요!");
			return false;
		}
		
		/* 파라미터 셋팅 */
		var params = {
			as_sawon_id : as_sawon_id,
			as_use_dt_fr : as_use_dt_fr,
			as_use_dt_to : as_use_dt_to,
			as_teammember : as_teammember
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/companyCardHistoryGridList.do?" + paramStr}).trigger("reloadGrid", [{page:1}]);
	}
	
	/* 본인 자료만 보기 */
	function viewMyData(){
		if ($("#as_teammember").is(":checked")) {
			$("#ls_sawon_id").val(ls_sawon_id);
			$("#ls_sawon_nm").val(ls_sawon_nm);
		} else {
			$("#ls_sawon_id").val("");
			$("#ls_sawon_nm").val("");
		}
	}
	
	/* 저장 */
	function updateDetail(){
		$("#grid_list").jqGrid("saveRow",lastsel);
		$("#grid_list").jqGrid("restoreRow",lastsel);
		
		if (rowIdArray.length > 0) { // 배열에 담긴 rowId를 기준으로 data들을 form에 담아 넘긴다
			
			/* 유효성 체크 */
			for (var i = 0; i < rowIdArray.length; i++) {
				var ret = $("#grid_list").getRowData(rowIdArray[i]);
				
				if (formCheck.getByteLength(ret.use_detail) > 500) {
					alert("사용 내역은 한글 250자, 영문 500자로 입력해주세요.(500byte)");
					$("#grid_list").jqGrid("editRow",rowIdArray[i],true);
					return;
				}
				
				/* 적요가 입력되어있지 않을 경우 사용 내역으로 대체 */
				if (formCheck.isNull(ret.jukyo)) {
					$("#grid_list").jqGrid("setCell",rowIdArray[i],"jukyo",ret.use_detail);
				}
				
				if (formCheck.getByteLength($("#grid_list").getCell(rowIdArray[i], "jukyo")) > 60) {
					alert("적요는 한글 30자, 영문 60자로 입력해주세요.(60byte)");
					$("#grid_list").jqGrid("editRow",rowIdArray[i],true);
					return;
				}
				
			}
			
			var frm = $("#insertFrm");
			
			for (var i = 0; i < rowIdArray.length; i++) { // form에 data 동적 생성
				var ret = $("#grid_list").getRowData(rowIdArray[i]);
			
				$("<input></input>").attr({type:"hidden", name:"use_dt", value: ret.use_dt}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"use_tm", value: ret.use_tm}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"card_no", value: ret.card_no}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"card_ok_no", value: ret.card_ok_no}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"tax_gb", value: $("#tax_gb_" + rowIdArray[i]).val()}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"gongjae_yn", value: $("#gongjae_yn_" + rowIdArray[i]).val()}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"use_detail", value: ret.use_detail}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"gaejung_cd", value: $("#gaejung_cd_" + rowIdArray[i]).val()}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"teamjang_conf_yn", value: $(":radio[name='teamjang_conf_yn_"+rowIdArray[i]+"']:checked").val()}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"jukyo", value: ret.jukyo}).appendTo(frm);
			}
			
			/* 저장 프로세스 */
			$.ajax({
				type:"POST",
				data: frm.serialize(),
				url:"<%=ONLINE_ROOT%>/business/updateCardUseDetail.do",
				dataType:"json",
				success:function(data){
					if ("Y" == data.message) {
						alert("저장되었습니다.");	
					} else {
						alert("저장 실패");
					}
				},complete:function(){
					getGridList(); // 그리드 호출
				}
			});
			
		} else {
			alert("변경 사항이 없습니다.");
		}
	}
	
	</script>
</head>

<body>
	<form name="frm" id="frm">
		<input type="hidden" name="as_sawon_id" id="as_sawon_id" value="<%=ls_sawon_id %>" />
		
		<div class="wrap_search">
			<div class="search">
				<label class="w70">사용일자</label>
				<p class="wrap_date">
					<input type="text" maxlength="10" class="crd" id="as_use_dt_fr" name="as_use_dt_fr" />
					<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				~
				<p class="wrap_date">
					<input type="text" maxlength="10" class="crd" id="as_use_dt_to" name="as_use_dt_to" />
					<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				
				<label>부서명</label>
				<input type="text" class="w100 ipt_disable" value="<%=ls_dept_cd %>" readonly />
				<input type="text" class="w100 ipt_disable" value="<%=ls_dept_nm %>" readonly />
				
				<label>사원명</label>
				<input type="text" class="w100 ipt_disable" name="ls_sawon_id" id="ls_sawon_id" readonly />
				<input type="text" class="w100 ipt_disable" name="ls_sawon_nm" id="ls_sawon_nm" readonly />
				
				<input type="checkbox" name="as_teammember" id="as_teammember" value="Y" onclick="javascript:viewMyData();"/>
				<label>본인 자료만 보기</label>
				
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
			<div class="wrap_result_group" id="companyCardMgmt" >
				<table id="grid_list"></table>
				<div id="grid_Pager" class="companyCardMgmt"></div>
			</div>
			
			<div class="wrap_result_group">
				<div class="result_group">
					<div class="float_r">
						<label class="point">결과</label>
						<input type="text" class="w100 ta_right ipt_disable" id="total_cnt" readonly />
						<label class="point ml10">총 사용금액</label>
						<input type="text" class="w100 ta_right ipt_disable" id="total_use_amt" readonly />
					</div>
				</div>
			</div>
		</div>
	</form>
	<div id="gaejungCodeLayer" style="z-index:9999;padding:3px;position:absolute;display:none;border:1px solid #ccc;background-color:#ffffff;">
		<table class="ui-jqgrid-htable">
			<thead>
				<tr class="ui-jqgrid-labels">
					<th class="ui-state-default" >계정과목코드</th>
					<th class="ui-state-default" >계정과목명</th>
					<th class="ui-state-default" >적용내용</th>
				</tr>
			</thead>
			<tbody>
			<%
			for(int i=0; i < gaejungCodeList.size(); i++){
				CompanyCardMgmtVO gaejungCode = gaejungCodeList.get(i);
			%>
				<tr class="ui-widget-content" onclick="javascript:selectGaejungCd(this, '<%=gaejungCode.getGaejung_cd()%>', '<%=gaejungCode.getGaejung_nm()%>')">
					<td style="text-align:center;"><%=gaejungCode.getGaejung_cd()%></td>
					<td style="text-align:center;"><%=gaejungCode.getGaejung_nm()%></td>
					<td style="text-align:left;"><%=gaejungCode.getBigo()%></td>
				</tr>
			<%} %>
			</tbody>
		</table>
	</div>
	<form id="insertFrm" name="insertFrm"></form>
	<%@include file="/include/footer.jsp"%>
</body>
</html>