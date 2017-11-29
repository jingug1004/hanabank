<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : performanceList.jsp
 * @메뉴명 : 영업관리 > 실적현황 Batch
 * @최초작성일 : 2014/10/31            
 * @author : 윤범진                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%@ page import="com.hanaph.saleon.common.utils.StringUtil" %>
<%
	/*
	 * 자료를 볼수 있는 권한을 인사의 직책코드에 의해서 처리함.
	   직책은 영업사원등록에 PDA권한이 전체인경우 '27000' 으로 셋팅함 예)전산팀,영업관리
	 */
		 
	String assgn_cd = StringUtil.nvl(loginUserVO.getAssgn_cd());
	String part_gb = "";
	String part_name = "";
	String dept_cd = "";
	String dept_name = "";
	String emp_cd = "";
	String emp_name = "";
	
	String part_ipt_disable = "";
	String dept_ipt_disable = "";
	String emp_ipt_disable = "";
	String part_disabled = "";
	String dept_disabled = "";
	String emp_disabled = "";
	String part_readonly = "";
	String dept_readonly = "";
	String emp_readonly = "";
	 
	if ("27040".equals(assgn_cd) || "27050".equals(assgn_cd)) { // 팀원,차석 은 부서 정보 셋팅 및 변경 불가 처리
		part_ipt_disable = "ipt_disable";
		dept_ipt_disable = "ipt_disable";
		emp_ipt_disable = "ipt_disable";
		part_disabled = "disabled";
		dept_disabled = "disabled";
		emp_disabled = "disabled";
		part_readonly = "readonly";
		dept_readonly = "readonly";
		emp_readonly = "readonly";
		
		part_gb = StringUtil.nvl(loginUserVO.getPart_gb()); // 파트 코드
		part_name = StringUtil.nvl(loginUserVO.getPart_name()); // 파트 명
		emp_cd = StringUtil.nvl(loginUserVO.getEmp_code()); // 사원 코드
		emp_name = StringUtil.nvl(loginUserVO.getEmp_name()); // 사원 명
	}else if ("27030".equals(assgn_cd))  { // 팀장은 파트,부서막음 
		part_ipt_disable = "ipt_disable";
		dept_ipt_disable = "ipt_disable";
		part_disabled = "disabled";
		dept_disabled = "disabled";
		part_readonly = "readonly";
		dept_readonly = "readonly";
		
		part_gb = StringUtil.nvl(loginUserVO.getPart_gb()); // 파트 코드
		part_name = StringUtil.nvl(loginUserVO.getPart_name()); // 파트 명
		dept_cd = StringUtil.nvl(loginUserVO.getDept_code()); // 부서 코드
		dept_name = StringUtil.nvl(loginUserVO.getDept_name()); // 부서 명
	}else if ("27020".equals(assgn_cd) || "27025".equals(assgn_cd)) { // 본부장,총괄지점장인경우 파트만 막음
		part_ipt_disable = "ipt_disable";
		part_disabled = "disabled";
		part_readonly = "readonly";
		
		part_gb = StringUtil.nvl(loginUserVO.getPart_gb()); // 파트 코드
		part_name = StringUtil.nvl(loginUserVO.getPart_name()); // 파트 명  
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
	
	/* 전역 변수 */
	var partActionFlag = false; // 파트별 실적현황 부가기능(인쇄, 엑셀다운로드) 사용 가능 여부 flag
	var teamActionFlag = false; // 영업지점별 실적현황 부가기능(인쇄, 엑셀다운로드) 사용 가능 여부 flag
	var empActionFlag = false; // 사원별 실적현황 부가기능(인쇄, 엑셀다운로드) 사용 가능 여부 flag
	
	$(document).ready(function(){
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리.  */
	    jsonReader : {
            repeatitems: false;
    	}
	
		/* 파트별 실적현황 grid */
		$("#grid_list_part").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["파트","판매목표", "종합병원", "준종합병원", "T", "입찰", "일반간납", "도매로컬", "의원", "약국", "기타", "반품", "매출할인외", "계", "달성율(%)", "수금목표", "종합병원", "준종합병원", "도매", "의원", "약국", "기타", "계", "달성율(%)"],
			colModel:[
						{name:"col4",						index:"col4",						align:"left", width:85},
						{name:"sale_amt",					index:"sale_amt",					align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_byung",		index:"sale_amt_siljuk_byung",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_mbyung",		index:"sale_amt_siljuk_mbyung",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_03",		index:"sale_amt_siljuk_in_03",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_04",		index:"sale_amt_siljuk_in_04", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_02",		index:"sale_amt_siljuk_in_02",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_local",	index:"sale_amt_siljuk_in_local", 	align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_01",		index:"sale_amt_siljuk_in_01",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_out",		index:"sale_amt_siljuk_out", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_05",		index:"sale_amt_siljuk_in_05",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_banpum",			index:"sale_amt_banpum",			align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_halin",				index:"sale_amt_halin",				align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk",			index:"sale_amt_siljuk", 			align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_percent",				index:"sale_percent",				align:"right",	width:75, formatter:percentFormatter},
						{name:"in_amt",						index:"in_amt",					 	align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_byung",		index:"in_amt_siljuk_byung",		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_mbyung",		index:"in_amt_siljuk_mbyung",	 	align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_in_local",		index:"in_amt_siljuk_in_local",		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_in_01",		index:"in_amt_siljuk_in_01", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_out",			index:"in_amt_siljuk_out",			align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_in_05",		index:"in_amt_siljuk_in_05", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk",				index:"in_amt_siljuk",				align:"right",	width:100, formatter:amountFormatter},
						{name:"in_percent",					index:"in_percent",					align:"right",	width:75, formatter:percentFormatter}
					],
			height:484,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* row가 추가 될대마다 호출 */
			afterInsertRow:function(rowid, rowdata , rowelem){ // fonr 색상 변경 및 bold 처리
				$("#grid_list_part").jqGrid("setCell",rowid,"col4","",{color:"#0000FF"}); // 파트명
				$("#grid_list_part").jqGrid("setCell",rowid,"sale_amt","",{color:"#DF01D7"}); // 판매목표
				$("#grid_list_part").jqGrid("setCell",rowid,"sale_amt_siljuk","",{"color":"#0000FF", "font-weight": "bold"}); // 판매 계
				$("#grid_list_part").jqGrid("setCell",rowid,"in_amt","",{color:"#DF01D7"}); // 수금목표
				$("#grid_list_part").jqGrid("setCell",rowid,"in_amt_siljuk","",{"color":"#0000FF", "font-weight": "bold"}); // 수금 계
			},
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if(data.records > 0){
					if(data.page == data.total){
						
						/* 합계정보 */
						var total_sale_amt = data.totalCountInfo.total_sale_amt;
						var total_sale_amt_siljuk = data.totalCountInfo.total_sale_amt_siljuk;
						var total_sale_percent = (total_sale_amt == 0 ? "0" : ((Number(total_sale_amt_siljuk) * 100) / Number(total_sale_amt)).toFixed(2));
						var total_in_amt = data.totalCountInfo.total_in_amt;
						var total_in_amt_siljuk = data.totalCountInfo.total_in_amt_siljuk;
						var total_in_percent = (total_in_amt == 0 ? "0" : ((Number(total_in_amt_siljuk) * 100) / Number(total_in_amt)).toFixed(2));
						
						var datarow = {
								col4 : "<font color='#FF0000'>영업부 - 합계</font>",
								sale_amt : total_sale_amt,
								sale_amt_siljuk_byung : data.totalCountInfo.total_sale_amt_siljuk_byung,
								sale_amt_siljuk_mbyung : data.totalCountInfo.total_sale_amt_siljuk_mbyung,
								sale_amt_siljuk_in_03 : data.totalCountInfo.total_sale_amt_siljuk_in_03,
								sale_amt_siljuk_in_04 : data.totalCountInfo.total_sale_amt_siljuk_in_04,
								sale_amt_siljuk_in_02 : data.totalCountInfo.total_sale_amt_siljuk_in_02,
								sale_amt_siljuk_in_local : data.totalCountInfo.total_sale_amt_siljuk_in_local,
								sale_amt_siljuk_in_01 : data.totalCountInfo.total_sale_amt_siljuk_in_01,
								sale_amt_siljuk_out : data.totalCountInfo.total_sale_amt_siljuk_out,
								sale_amt_siljuk_in_05 : data.totalCountInfo.total_sale_amt_siljuk_in_05,
								sale_amt_banpum : data.totalCountInfo.total_sale_amt_banpum,
								sale_amt_halin : data.totalCountInfo.total_sale_amt_halin,
								sale_amt_siljuk : total_sale_amt_siljuk,
								sale_percent : total_sale_percent,
								in_amt : total_in_amt,
								in_amt_siljuk_byung : data.totalCountInfo.total_in_amt_siljuk_byung,
								in_amt_siljuk_mbyung : data.totalCountInfo.total_in_amt_siljuk_mbyung,
								in_amt_siljuk_in_local : data.totalCountInfo.total_in_amt_siljuk_in_local,
								in_amt_siljuk_in_01 : data.totalCountInfo.total_in_amt_siljuk_in_01,
								in_amt_siljuk_out : data.totalCountInfo.total_in_amt_siljuk_out,
								in_amt_siljuk_in_05 : data.totalCountInfo.total_in_amt_siljuk_in_05,
								in_amt_siljuk : total_in_amt_siljuk,
								in_percent : total_in_percent
						};
						$("#grid_list_part").addRowData(0, datarow, "last"); // 마지막 row에 추가
					}

					var proc_date = data.rows[0].proc_date; // batch 생성 시간
					$("#proc_date").text(proc_date.substr(0, proc_date.indexOf(".")));
					
					partActionFlag = true;  // 인쇄, 엑셀 사용 가능하도록
				}else{
					alert("조회 결과가 없습니다.");
					partActionFlag = false;
				}
			},
			
			pager: "#grid_Pager_part"
   		});
		
		/* 영업지점별 실적현황 grid */
		$("#grid_list_team").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["파트","지점/팀", "판매목표", "종합병원", "준종합병원", "T", "입찰", "일반간납", "도매로컬", "의원", "약국", "기타", "반품", "매출할인외", "계", "달성율(%)", "수금목표", "종합병원", "준종합병원", "도매", "의원", "약국", "기타", "계", "달성율(%)"],
			
			colModel:[
						{name:"col2",						index:"col2",						align:"left", width:75},
						{name:"col4",						index:"col4",						align:"left", width:90},
						{name:"sale_amt",					index:"sale_amt",					align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_byung",		index:"sale_amt_siljuk_byung",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_mbyung",		index:"sale_amt_siljuk_mbyung",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_03",		index:"sale_amt_siljuk_in_03",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_04",		index:"sale_amt_siljuk_in_04", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_02",		index:"sale_amt_siljuk_in_02",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_local",	index:"sale_amt_siljuk_in_local", 	align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_01",		index:"sale_amt_siljuk_in_01",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_out",		index:"sale_amt_siljuk_out", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_05",		index:"sale_amt_siljuk_in_05",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_banpum",			index:"sale_amt_banpum",			align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_halin",				index:"sale_amt_halin",				align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk",			index:"sale_amt_siljuk", 			align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_percent",				index:"sale_percent",				align:"right",	width:75, formatter:percentFormatter},
						{name:"in_amt",						index:"in_amt",					 	align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_byung",		index:"in_amt_siljuk_byung",		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_mbyung",		index:"in_amt_siljuk_mbyung",	 	align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_in_local",		index:"in_amt_siljuk_in_local",		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_in_01",		index:"in_amt_siljuk_in_01", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_out",			index:"in_amt_siljuk_out",			align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_in_05",		index:"in_amt_siljuk_in_05", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk",				index:"in_amt_siljuk",				align:"right",	width:100, formatter:amountFormatter},
						{name:"in_percent",					index:"in_percent",					align:"right",	width:75, formatter:percentFormatter}
					],
			height:484,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* row가 추가 될대마다 호출 */
			afterInsertRow:function(rowid, rowdata , rowelem){ // fonr 색상 변경 및 bold 처리
				$("#grid_list_team").jqGrid("setCell",rowid,"col2","",{color:"#0000FF"}); // 파트
				$("#grid_list_team").jqGrid("setCell",rowid,"col4","",{color:"#8A0808"}); // 지점/팀
				$("#grid_list_team").jqGrid("setCell",rowid,"sale_amt","",{color:"#DF01D7"}); // 판매목표
				$("#grid_list_team").jqGrid("setCell",rowid,"sale_amt_siljuk","",{"color":"#0000FF", "font-weight": "bold"}); // 판매 계
				$("#grid_list_team").jqGrid("setCell",rowid,"in_amt","",{color:"#DF01D7"}); // 수금목표
				$("#grid_list_team").jqGrid("setCell",rowid,"in_amt_siljuk","",{"color":"#0000FF", "font-weight": "bold"}); // 수금 계
			},
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if(data.records > 0){
					if(data.page == data.total){
						
						/* 합계정보 */
						var total_sale_amt = data.totalCountInfo.total_sale_amt;
						var total_sale_amt_siljuk = data.totalCountInfo.total_sale_amt_siljuk;
						var total_sale_percent = (total_sale_amt == 0 ? "0" : ((Number(total_sale_amt_siljuk) * 100) / Number(total_sale_amt)).toFixed(2));
						var total_in_amt = data.totalCountInfo.total_in_amt;
						var total_in_amt_siljuk = data.totalCountInfo.total_in_amt_siljuk;
						var total_in_percent = (total_in_amt == 0 ? "0" : ((Number(total_in_amt_siljuk) * 100) / Number(total_in_amt)).toFixed(2));
						
						var datarow = {
								col2 : "",
								col4 : "영업부 - 합계",
								sale_amt : total_sale_amt,
								sale_amt_siljuk_byung : data.totalCountInfo.total_sale_amt_siljuk_byung,
								sale_amt_siljuk_mbyung : data.totalCountInfo.total_sale_amt_siljuk_mbyung,
								sale_amt_siljuk_in_03 : data.totalCountInfo.total_sale_amt_siljuk_in_03,
								sale_amt_siljuk_in_04 : data.totalCountInfo.total_sale_amt_siljuk_in_04,
								sale_amt_siljuk_in_02 : data.totalCountInfo.total_sale_amt_siljuk_in_02,
								sale_amt_siljuk_in_local : data.totalCountInfo.total_sale_amt_siljuk_in_local,
								sale_amt_siljuk_in_01 : data.totalCountInfo.total_sale_amt_siljuk_in_01,
								sale_amt_siljuk_out : data.totalCountInfo.total_sale_amt_siljuk_out,
								sale_amt_siljuk_in_05 : data.totalCountInfo.total_sale_amt_siljuk_in_05,
								sale_amt_banpum : data.totalCountInfo.total_sale_amt_banpum,
								sale_amt_halin : data.totalCountInfo.total_sale_amt_halin,
								sale_amt_siljuk : total_sale_amt_siljuk,
								sale_percent : total_sale_percent,
								in_amt : total_in_amt,
								in_amt_siljuk_byung : data.totalCountInfo.total_in_amt_siljuk_byung,
								in_amt_siljuk_mbyung : data.totalCountInfo.total_in_amt_siljuk_mbyung,
								in_amt_siljuk_in_local : data.totalCountInfo.total_in_amt_siljuk_in_local,
								in_amt_siljuk_in_01 : data.totalCountInfo.total_in_amt_siljuk_in_01,
								in_amt_siljuk_out : data.totalCountInfo.total_in_amt_siljuk_out,
								in_amt_siljuk_in_05 : data.totalCountInfo.total_in_amt_siljuk_in_05,
								in_amt_siljuk : total_in_amt_siljuk,
								in_percent : total_in_percent
						};
						$("#grid_list_team").addRowData(0, datarow, "last"); // 마지막 row에 추가
					}
					
					var proc_date = data.rows[0].proc_date; // batch 생성시간
					$("#proc_date").text(proc_date.substr(0, proc_date.indexOf(".")));
					
					teamActionFlag = true; // 인쇄, 엑셀 사용 가능하도록
				}else{
					alert("조회 결과가 없습니다.");
					teamActionFlag = false;
				}
			},
			
			pager: "#grid_Pager_team"
   		});
		
		/* 사원별 실적현황 grid */
		$("#grid_list_emp").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["지점/팀","담당사원", "판매목표", "종합병원", "준종합병원", "T", "입찰", "일반간납", "도매로컬", "의원", "약국", "기타", "반품", "매출할인외", "계", "달성율(%)", "수금목표", "종합병원", "준종합병원", "도매", "의원", "약국", "기타", "계", "달성율(%)"],
			colModel:[
						{name:"col2",						index:"col2",						align:"left", width:90},
						{name:"col4",						index:"col4",						align:"left", width:75},
						{name:"sale_amt",					index:"sale_amt",					align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_byung",		index:"sale_amt_siljuk_byung",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_mbyung",		index:"sale_amt_siljuk_mbyung",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_03",		index:"sale_amt_siljuk_in_03",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_04",		index:"sale_amt_siljuk_in_04", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_02",		index:"sale_amt_siljuk_in_02",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_local",	index:"sale_amt_siljuk_in_local", 	align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_01",		index:"sale_amt_siljuk_in_01",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_out",		index:"sale_amt_siljuk_out", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk_in_05",		index:"sale_amt_siljuk_in_05",		align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_banpum",			index:"sale_amt_banpum",			align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_halin",				index:"sale_amt_halin",				align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_amt_siljuk",			index:"sale_amt_siljuk", 			align:"right",	width:100, formatter:amountFormatter},
						{name:"sale_percent",				index:"sale_percent",				align:"right",	width:75, formatter:percentFormatter},
						{name:"in_amt",						index:"in_amt",					 	align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_byung",		index:"in_amt_siljuk_byung",		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_mbyung",		index:"in_amt_siljuk_mbyung",	 	align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_in_local",		index:"in_amt_siljuk_in_local",		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_in_01",		index:"in_amt_siljuk_in_01", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_out",			index:"in_amt_siljuk_out",			align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk_in_05",		index:"in_amt_siljuk_in_05", 		align:"right",	width:100, formatter:amountFormatter},
						{name:"in_amt_siljuk",				index:"in_amt_siljuk",				align:"right",	width:100, formatter:amountFormatter},
						{name:"in_percent",					index:"in_percent",					align:"right",	width:75, formatter:percentFormatter}
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
			
			/* row가 추가 될대마다 호출 */
			afterInsertRow:function(rowid, rowdata , rowelem){ // fonr 색상 변경 및 bold 처리
				$("#grid_list_emp").jqGrid("setCell",rowid,"col2","",{color:"#0000FF"}); // 지점/팀
				$("#grid_list_emp").jqGrid("setCell",rowid,"col4","",{color:"#0000FF"}); // 담당사원
				$("#grid_list_emp").jqGrid("setCell",rowid,"sale_amt","",{color:"#DF01D7"}); // 판매목표
				$("#grid_list_emp").jqGrid("setCell",rowid,"sale_amt_siljuk","",{"color":"#0000FF", "font-weight": "bold"}); // 판매 계
				$("#grid_list_emp").jqGrid("setCell",rowid,"in_amt","",{color:"#DF01D7"}); // 수금목표
				$("#grid_list_emp").jqGrid("setCell",rowid,"in_amt_siljuk","",{"color":"#0000FF", "font-weight": "bold"}); // 수금 계
			},
			
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if(data.records > 0){
					if(data.page == data.total){
						
						/* 합계정보 */
						var total_sale_amt = data.totalCountInfo.total_sale_amt;
						var total_sale_amt_siljuk = data.totalCountInfo.total_sale_amt_siljuk;
						var total_sale_percent = (total_sale_amt == 0 ? "0" : ((Number(total_sale_amt_siljuk) * 100) / Number(total_sale_amt)).toFixed(2));
						var total_in_amt = data.totalCountInfo.total_in_amt;
						var total_in_amt_siljuk = data.totalCountInfo.total_in_amt_siljuk;
						var total_in_percent = (total_in_amt == 0 ? "0" : ((Number(total_in_amt_siljuk) * 100) / Number(total_in_amt)).toFixed(2));
						
						var datarow = {
								col2 : "영업부 - 합계",
								col4 : "",
								sale_amt : total_sale_amt,
								sale_amt_siljuk_byung : data.totalCountInfo.total_sale_amt_siljuk_byung,
								sale_amt_siljuk_mbyung : data.totalCountInfo.total_sale_amt_siljuk_mbyung,
								sale_amt_siljuk_in_03 : data.totalCountInfo.total_sale_amt_siljuk_in_03,
								sale_amt_siljuk_in_04 : data.totalCountInfo.total_sale_amt_siljuk_in_04,
								sale_amt_siljuk_in_02 : data.totalCountInfo.total_sale_amt_siljuk_in_02,
								sale_amt_siljuk_in_local : data.totalCountInfo.total_sale_amt_siljuk_in_local,
								sale_amt_siljuk_in_01 : data.totalCountInfo.total_sale_amt_siljuk_in_01,
								sale_amt_siljuk_out : data.totalCountInfo.total_sale_amt_siljuk_out,
								sale_amt_siljuk_in_05 : data.totalCountInfo.total_sale_amt_siljuk_in_05,
								sale_amt_banpum : data.totalCountInfo.total_sale_amt_banpum,
								sale_amt_halin : data.totalCountInfo.total_sale_amt_halin,
								sale_amt_siljuk : total_sale_amt_siljuk,
								sale_percent : total_sale_percent ,
								in_amt : total_in_amt,
								in_amt_siljuk_byung : data.totalCountInfo.total_in_amt_siljuk_byung,
								in_amt_siljuk_mbyung : data.totalCountInfo.total_in_amt_siljuk_mbyung,
								in_amt_siljuk_in_local : data.totalCountInfo.total_in_amt_siljuk_in_local,
								in_amt_siljuk_in_01 : data.totalCountInfo.total_in_amt_siljuk_in_01,
								in_amt_siljuk_out : data.totalCountInfo.total_in_amt_siljuk_out,
								in_amt_siljuk_in_05 : data.totalCountInfo.total_in_amt_siljuk_in_05,
								in_amt_siljuk : total_in_amt_siljuk,
								in_percent : total_in_percent
						};
						$("#grid_list_emp").addRowData(0, datarow, "last"); // 마지막 row에 추가
					}
					
					var proc_date = data.rows[0].proc_date; // batch 생성시간
					$("#proc_date").text(proc_date.substr(0, proc_date.indexOf(".")));
					
					empActionFlag = true; // 인쇄, 엑셀 사용 가능하도록
				}else{
					alert("조회 결과가 없습니다.");
					empActionFlag = false;
				}
			},
			
			pager: "#grid_Pager_emp"
   		});
		
		/* 조회 버튼 클릭 */
		$("#ct_retrieve_btn_type04").on("click",function(){
			getGridList();
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
			parent.Commons.closeTab('실적현황_Batch');
		});
		
		/* 이전화면 버튼 클릭 */
		$("#lt_preview").on("click",function(){
			changePage('prev');
		});
		
		/* 다음화면 버튼 클릭 */
		$("#lt_next").on("click",function(){
			changePage('next');
		});
		
		/* 조회일자 셋팅(이번 달) */
		Business.setThisMonth("as_date_time");
		
		/* grid 영역 넓이 셋팅 */
		var selectType = $(":radio[name='selectType']:checked").val();
		$("#grid_list_part").setGridWidth($("#" + selectType).width() , false);
		$("#grid_list_team").setGridWidth($("#" + selectType).width() , false);
		$("#grid_list_emp").setGridWidth($("#" + selectType).width() , false);
		
		/* 엔터키 눌렸을 경우 조회되도록. */
		$("body").on("keydown", function(event){
			if(event.keyCode==13 && event.target.name!="grid_count"){
				getGridList();
				return false;
			}
		});
		
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
	$(window).resize(function(){
		var selectType = $(":radio[name='selectType']:checked").val();
		$("#grid_list_part").setGridWidth($("#" + selectType).width() , false);
		$("#grid_list_team").setGridWidth($("#" + selectType).width() , false);
		$("#grid_list_emp").setGridWidth($("#" + selectType).width() , false);
	});
	
	/* 조회 */
	function getGridList(){
		var as_part_cd = $("#as_part_cd").val();
		var as_team_cd = $("#as_team_cd").val();
		var as_emp_no = $("#as_emp_no").val();
		var selectType = $(":radio[name='selectType']:checked").val();
		var as_date_time = $("#as_date_time").val();
		var as_siljukyul_in = $("#as_siljukyul_in").val();
		var as_siljukyul_out = $("#as_siljukyul_out").val();
		var as_siljukyul_byung = $("#as_siljukyul_byung").val();
		var as_siljukyul_in_su = $("#as_siljukyul_in_su").val();
		var as_siljukyul_out_su = $("#as_siljukyul_out_su").val();
		var as_siljukyul_byung_su = $("#as_siljukyul_byung_su").val(); 
		var as_assgn_cd = $("#as_assgn_cd").val(); 
		
		/* 실적년월 유효성 체크 */
		if ("" == as_date_time) {
			Business.setThisMonth("as_date_time");
			as_date_time = $("#as_date_time").val();
		}
		
		if(!formCheck.isMonthFormat(as_date_time)) {
			alert("올바른 실적년월을 입력해 주세요.");
			return false;
		}    
		
		if ("27020" == as_assgn_cd || "27025" == as_assgn_cd) {
			if (formCheck.isEmpty(as_team_cd) && formCheck.isEmpty(as_emp_no) ){
				alert("부서 또는 사원중 한개의 조건은 입력해야 합니다.");
				return false;
			}	 
		}
		
		/* 실적율 유효성 체크 */
		if (formCheck.isEmpty(as_siljukyul_in) || formCheck.isNumPeriod(as_siljukyul_in) || !formCheck.isPercent(as_siljukyul_in)) {
			$("#as_siljukyul_in").val("100.000");
			return false;
		}
		
		if (formCheck.isEmpty(as_siljukyul_out) || formCheck.isNumPeriod(as_siljukyul_out) || !formCheck.isPercent(as_siljukyul_out)) {
			$("#as_siljukyul_out").val("100.000");
			return false;
		}
		
		if (formCheck.isEmpty(as_siljukyul_byung) || formCheck.isNumPeriod(as_siljukyul_byung) || !formCheck.isPercent(as_siljukyul_byung)) {
			$("#as_siljukyul_byung").val("100.000");
			return false;
		}
		
		if (formCheck.isEmpty(as_siljukyul_in_su) || formCheck.isNumPeriod(as_siljukyul_in_su) || !formCheck.isPercent(as_siljukyul_in_su)) {
			$("#as_siljukyul_in_su").val("100.000");
			return false;
		}
		
		if (formCheck.isEmpty(as_siljukyul_out_su) || formCheck.isNumPeriod(as_siljukyul_out_su) || !formCheck.isPercent(as_siljukyul_out_su)) {
			$("#as_siljukyul_out_su").val("100.000");
			return false;
		}
		
		if (formCheck.isEmpty(as_siljukyul_byung_su) || formCheck.isNumPeriod(as_siljukyul_byung_su) || !formCheck.isPercent(as_siljukyul_byung_su)) {
			$("#as_siljukyul_byung_su").val("100.000");
			return false;
		}
		
		/* 파라미터 셋팅 */
		var params = {
			as_part_cd : as_part_cd,
			as_team_cd : as_team_cd,
			as_emp_no : as_emp_no,
			selectType : selectType,
			as_date_time : as_date_time,
			as_siljukyul_in : as_siljukyul_in,
			as_siljukyul_out : as_siljukyul_out,
			as_siljukyul_byung : as_siljukyul_byung,
			as_siljukyul_in_su : as_siljukyul_in_su,
			as_siljukyul_out_su : as_siljukyul_out_su,
			as_siljukyul_byung_su : as_siljukyul_byung_su
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list_" + selectType).jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/performanceGridList.do?" + paramStr}).trigger("reloadGrid", [{page:1}]);
	}
	
	/* 파트명, 부서명, 사원명 가져오기 */
	function getPerformanceName(target, searchType){
		
		var searchKeyword = "";
		
		if ("part" == searchType) { // 조회 타입에 따라 검색어 변경
			searchKeyword = $("#as_part_cd").val();
		} else if ("team" == searchType) {
			searchKeyword = $("#as_team_cd").val();
		} else if ("emp" == searchType) {
			searchKeyword = $("#as_emp_no").val();
		}
		
		if ("" != searchKeyword) { // 검색어가 있다면 수행
			$.ajax({
				type:"POST",
				url:"<%=ONLINE_ROOT%>/business/common/performanceNameAjax.do",
				data:{ searchKeyword : searchKeyword, searchType : searchType },
				dataType:"json",
				success:function(data){
					if ("" != data.result_nm) {
						$("#" + target + "_name").val(data.result_nm);	
					} else {
						alert("등록된 코드가 아닙니다.");
						$("#" + target + "_name").val("");
						$("#" + target).val("");
						$("#" + target).focus();
					}
				}
			});
		} else {
			$("#" + target + "_name").val("");
		}
	}
	
	/* 조회유형에 따른 grid 영역 교체 */
	function changeType(value) {
		$(".wrap_result_group").hide();
		$("#" + value).show();
		getGridList();	
	}
	
	/* 이전, 다음 화면 */
	function changePage(value) {
		var currentPage = $(":radio[name='selectType']:checked").val();
		var targetPage = "";
		
		if ("prev" == value) { // 이전, 다음 버튼과 현재 페이지에 따라 이동할 페이지 셋팅
			if ("team" == currentPage) {
				targetPage = "part";
			} else if ("emp" == currentPage) {
				targetPage = "team";
			}
		} else if ("next" == value) {
			if ("part" == currentPage) {
				targetPage = "team";
			} else if ("team" == currentPage) {
				targetPage = "emp";
			}
		}
		
		if ("" != targetPage) {
			$(":radio[name='selectType']:radio[value='" + targetPage + "']").prop("checked", true);
			changeType(targetPage);			
		}
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
	
	/* 퍼센트 커스텀 포맷 */
	function percentFormatter(cellvalue){
		
		if (cellvalue.indexOf(".") < 0 && "" != cellvalue) { // 소수점이 없을 경우 .00을 붙여준다.
			cellvalue = cellvalue + ".00";
		} else if (cellvalue.substring(cellvalue.indexOf(".")+1).length == 1) { // 소수점이 있으나 소수점 밑 1자리만 있을 경우 0을 붙여준다.
			cellvalue = cellvalue + "0";
		}
		
		return cellvalue;
	}
	
	/* 인쇄, 엑셀 실행 */
	function goExtraAction(type) {
		var selectType = $(":radio[name='selectType']:checked").val();
		var selectFlag = false;
		var url = "";
		
		if ("part" == selectType) {
			selectFlag = partActionFlag; 
		} else if ("team" == selectType) {
			selectFlag = teamActionFlag;
		} else if ("emp" == selectType) {
			selectFlag = empActionFlag;
		}
		
		if ("print" == type) {
			url = "<%=ONLINE_ROOT%>/common/commonPrint.do";
		} else if ("excel" == type) {
			url = "<%=ONLINE_ROOT%>/business/performanceGridListExcelDown.do";
		}
		
		Commons.extraAction(selectFlag, type, url, selectType);
	}
	
	</script>
</head>

<body>
	<form name="frm" id="frm">
		<div class="wrap_search">
			<div class="search">
				<div class="float_l">
					<label class="w70">조회유형</label>
					<input type="radio" name="selectType" id="selectType_part" value="part" onclick="javascript:changeType(this.value);" checked />
					<span>파트별</span>
					<input type="radio" name="selectType" id="selectType_team" value="team" onclick="javascript:changeType(this.value);" />
					<span>영업지점별</span>
					<input type="radio" name="selectType" id="selectType_emp" value="emp" onclick="javascript:changeType(this.value);" />
					<span>사원별</span> 
					<input type="text" class="w50 ipt_disable" id="as_assgn_cd" name="as_assgn_cd" value="<%=assgn_cd %>"  readonly hidden /><br />
				    
					<label class="w70">파 트</label>
					<input type="text" class="w140 <%=part_ipt_disable %>" id="as_part_cd" name="as_part_cd" onblur="javascript:getPerformanceName(this.id, 'part');" value="<%=part_gb %>" <%=part_readonly %>/>
					<button type="button" class="btn_search" onclick="javascript:Commons.popupOpen('as_part_cd', '<%=ONLINE_ROOT%>/business/common/partList.do', '600', '655');" <%=part_disabled %>><span class="blind">찾기</span></button>
					<input type="text" class="w265 ipt_disable" id="as_part_cd_name" name="as_part_cd_name" value="<%=part_name %>" readonly /><br />
				
					<label class="w70">부 서</label>
					<input type="text" class="w140 <%=dept_ipt_disable %>" id="as_team_cd" name="as_team_cd" onblur="javascript:getPerformanceName(this.id, 'team');" value="<%=dept_cd %>" <%=dept_readonly %>/>
					<button type="button" class="btn_search" onclick="javascript:Commons.popupOpen('as_team_cd', '<%=ONLINE_ROOT%>/business/common/teamList.do', '600', '655');" <%=dept_disabled %>><span class="blind">찾기</span></button>
					<input type="text" class="w265 ipt_disable" id="as_team_cd_name" name="as_team_cd_name" value="<%=dept_name %>" readonly /><br />
				
					<label class="w70">사 원</label>
					<input type="text" class="w140 <%=emp_ipt_disable %>" id="as_emp_no" name="as_emp_no" onblur="javascript:getPerformanceName(this.id, 'emp');" value="<%=emp_cd %>" <%=emp_readonly %>/>
					<button type="button" class="btn_search" onclick="javascript:Commons.popupOpen('as_emp_no', '<%=ONLINE_ROOT%>/business/common/empList.do', '600', '655');" <%=emp_disabled %>><span class="blind">찾기</span></button>
					<input type="text" class="w265 ipt_disable" id="as_emp_no_name" name="as_emp_no_name" value="<%=emp_name %>"  readonly /><br />
				</div>
				
				<div class="float_l ml10">
					<label class="w70">실적년월</label>
					<input type="text" class="w280" maxlength="7" id="as_date_time" name="as_date_time" /><br />
					<span class="result_txt">실적율 (원내 / 원외 / 병원)</span>
				
					<label class="w70">판매</label>
					<input type="text" class="w70" maxlength="7" id="as_siljukyul_in" name="as_siljukyul_in" value="90.909" /> %
					<input type="text" class="w70" maxlength="7" id="as_siljukyul_out" name="as_siljukyul_out" value="90.909" /> %
					<input type="text" class="w70" maxlength="7" id="as_siljukyul_byung" name="as_siljukyul_byung" value="90.909" /> %
					<br />
					<label class="w70">수금</label>
					<input type="text" class="w70" maxlength="7" id="as_siljukyul_in_su" name="as_siljukyul_in_su" value="90.909" /> %
					<input type="text" class="w70" maxlength="7" id="as_siljukyul_out_su" name="as_siljukyul_out_su" value="90.909" /> %
					<input type="text" class="w70" maxlength="7" id="as_siljukyul_byung_su" name="as_siljukyul_byung_su" value="90.909" /> %
				</div>
				
				<div class="wrap_search_btn">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "ct_")%>
				</div>
			</div>
		</div>
		
		<div class="wrap_btn_group">
			<div class="btn_group">
				<div class="float_l">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "lt_")%>
				</div>
				
				<div class="float_r ta_r">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
				</div>
			</div>
		</div>
		
		<div class="inner_cont">
			<p>Batch Data 생성시각 : <span id="proc_date"></span></p>
			
			<!-- 파트별 -->
			<div class="wrap_result_group" id="part" style="display:block;">
				<table id="grid_list_part"></table>
				<div id="grid_Pager_part" class="part"></div>
			</div>
			
			<!-- 영업지점별 -->
			<div class="wrap_result_group" id="team" style="display:none;">
				<table id="grid_list_team"></table>
				<div id="grid_Pager_team" class="team"></div>
			</div>
			
			<!-- 사원별 -->
			<div class="wrap_result_group" id="emp" style="display:none;">
				<table id="grid_list_emp"></table>
				<div id="grid_Pager_emp" class="emp"></div>
			</div>
		</div>
	</form>
	<%@include file="/include/footer.jsp"%>
</body>
</html>