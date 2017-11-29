<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : customerInfoList.jsp
 * @메뉴명 : 영업관리 > 고객등록
 * @최초작성일 : 2014/12/17
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
	
	var customerRegistActionFlag = false; // 고객등록 부가기능(인쇄, 엑셀다운로드) 사용 가능 여부 flag
	var saveType = ""; // 저장 타입
	var currentTab = 1; // 현재 탭
	var insertDataArr = []; // insert용 배열
	var updateDataArr = []; // update용 배열
	var lastsel = ""; // 마지막 선택한 id
	
	$(document).ready(function(){
		
		/* 사원 정보 존재 여부 */
		if (<%=isNull%>) {
			alert("사원정보 자료가 없습니다.");
		}
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리.  */
		jsonReader : {
            repeatitems: false;
    	}
		
		/* 기타사항 목록 grid */
		$("#grid_list_detail6").jqGrid({
			editurl: ONLINE_ROOT + "/business/customerDetailEtcGridList.do",
			mtype:"get",
			datatype:"json",
			colNames:["기타사항","거래처코드","고객코드","일련번호"],
			colModel:[
						{name:"gita",			index:"gita",			align:"left", editable:true, edittype:"text", editoptions:Commons.jqgridEditOptions("grid_list_detail6", "Y")},
						{name:"cust_id",		index:"cust_id",		hidden:true},
						{name:"customer_id",	index:"customer_id",	hidden:true},
						{name:"seq",			index:"seq",			hidden:true}
					],
			height:434,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			autowidth:true,
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				if(id){
					$("#grid_list_detail6").jqGrid("saveRow",lastsel); // 현재 수정폼을 저장
					$("#grid_list_detail6").jqGrid("restoreRow",lastsel); // 현재 수정폼을 복구
					$("#grid_list_detail6").jqGrid("editRow",id,true); // 선택한 row를 수정폼으로
					lastsel=id; // 마지막 선택한 row는 현재 row가 된다
					
					/* 현재 선택한 row가 입력버튼을 통해 추가된 row인지 확인 */
					var isExist = false; // flag
					for (var i = 0; i < insertDataArr.length; i++) { // value 위치 검색
						if (insertDataArr[i] == id) {
							isExist = true;
							break;
						}
					}
					
					if (!isExist) { // 없을 경우
						Business.addArray(updateDataArr, id); // update 배열에 현재 id를 넣는다.
					}
					
				}
			},
			
			pager: "#grid_Pager_detail6"
   		});
		
		/* 교우관계 목록 grid */
		$("#grid_list_detail5").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["병원명/기관명/제약회사명","성명","관계","관심도","전문과","거래처코드","고객코드","일련번호"],
			colModel:[
						{name:"corp_nm",	index:"corp_nm",	align:"left", width:150, editable:true, edittype:"text"},
						{name:"name",		index:"name",		align:"left", width:100, editable:true, edittype:"text"},
						{name:"friendship",	index:"friendship",	align:"left", width:150, editable:true, edittype:"text"},
						{name:"gwansimdo",	index:"gwansimdo",	align:"center", width:100, editable:true, edittype:"select", editoptions:{value:"1:상;2:중;3:하"}, formatter:gwansimdoFormatter},
						{name:"lesson",		index:"lesson",		align:"left", width:150, editable:true, edittype:"text", editoptions:Commons.jqgridEditOptions("grid_list_detail5", "Y")},
						{name:"cust_id",		index:"cust_id",		hidden:true},
						{name:"customer_id",	index:"customer_id",	hidden:true},
						{name:"seq",			index:"seq",			hidden:true}
					],
			height:434,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				if(id){
					$("#grid_list_detail5").jqGrid("saveRow",lastsel); // 현재 수정폼을 저장
					$("#grid_list_detail5").jqGrid("restoreRow",lastsel); // 현재 수정폼을 복구
					$("#grid_list_detail5").jqGrid("editRow",id,true); // 선택한 row를 수정폼으로
					lastsel=id; // 마지막 선택한 row는 현재 row가 된다
					
					/* 현재 선택한 row가 입력을 통해 추가된 row인지 확인 */
					var isExist = false; // flag
					for (var i = 0; i < insertDataArr.length; i++) { // value 위치 검색
						if (insertDataArr[i] == id) {
							isExist = true;
							break;
						}
					}
					
					if (!isExist) { // 없을 경우
						Business.addArray(updateDataArr, id); // update 배열에 현재 id를 넣는다.
					}
					
				}
			},
			
			pager: "#grid_Pager_detail5"
   		});
		
		/* 기념일 목록 grid */
		$("#grid_list_detail4").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["날짜","종류","상세내역","거래처코드","고객코드","일련번호"],
			colModel:[
						{name:"sdate",	index:"sdate",	align:"center", width:100, editable:true, edittype:"text"},
						{name:"kind",	index:"kind",	align:"left", width:150, editable:true, edittype:"text"},
						{name:"bigo",	index:"bigo",	align:"left", width:600, editable:true, edittype:"text", editoptions:Commons.jqgridEditOptions("grid_list_detail4", "Y")},
						{name:"cust_id",		index:"cust_id",		hidden:true},
						{name:"customer_id",	index:"customer_id",	hidden:true},
						{name:"seq",			index:"seq",			hidden:true}
					],
			height:434,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],

			/* row 선택 시 실행 */
			onSelectRow: function(id){
				if(id){
					$("#grid_list_detail4").jqGrid("saveRow",lastsel); // 현재 수정폼을 저장
					$("#grid_list_detail4").jqGrid("restoreRow",lastsel); // 현재 수정폼을 복구
					$("#grid_list_detail4").jqGrid("editRow",id,true); // 선택한 row를 수정폼으로
					lastsel=id; // 마지막 선택한 row는 현재 row가 된다
					
					/* 현재 선택한 row가 입력을 통해 추가된 row인지 확인 */
					var isExist = false; // flag
					for (var i = 0; i < insertDataArr.length; i++) { // value 위치 검색
						if (insertDataArr[i] == id) {
							isExist = true;
							break;
						}
					}
					
					if (!isExist) { // 없을 경우
						Business.addArray(updateDataArr, id); // update 배열에 현재 id를 넣는다.
					}
					
				}
			},
			
			pager: "#grid_Pager_detail4"
   		});
		
		/* 가족관계 목록 grid */
		$("#grid_list_detail3").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["관계","성명","생년월일","직업","기타사항","거래처코드","고객코드","일련번호"],
			colModel:[
						{name:"relation",	index:"relation",	align:"center", width:100, editable:true, edittype:"select", editoptions:{value:"01:본인;02:부;03:모;04:처;05:자;06:녀"}, formatter:relationFormatter},
						{name:"name",		index:"name",		align:"left", width:100, editable:true, edittype:"text"},
						{name:"birthday",	index:"birthday",	align:"center", width:100, editable:true, edittype:"text"},
						{name:"job",		index:"job",		align:"left", width:100, editable:true, edittype:"text"},
						{name:"gita",		index:"gita",		align:"left", width:500, editable:true, edittype:"text", editoptions:Commons.jqgridEditOptions("grid_list_detail3", "Y")},
						{name:"cust_id",		index:"cust_id",		hidden:true},
						{name:"customer_id",	index:"customer_id",	hidden:true},
						{name:"seq",			index:"seq",			hidden:true}
					],
			height:434,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				if(id){
					$("#grid_list_detail3").jqGrid("saveRow",lastsel); // 현재 수정폼을 저장
					$("#grid_list_detail3").jqGrid("restoreRow",lastsel); // 현재 수정폼을 복구
					$("#grid_list_detail3").jqGrid("editRow",id,true); // 선택한 row를 수정폼으로
					lastsel=id; // 마지막 선택한 row는 현재 row가 된다
					
					/* 현재 선택한 row가 입력을 통해 추가된 row인지 확인 */
					var isExist = false; // flag
					for (var i = 0; i < insertDataArr.length; i++) { // value 위치 검색
						if (insertDataArr[i] == id) {
							isExist = true;
							break;
						}
					}
					
					if (!isExist) { // 없을 경우
						Business.addArray(updateDataArr, id); // update 배열에 현재 id를 넣는다.
					}
					
				}
			},
			
			pager: "#grid_Pager_detail3"
   		});
		
		/* 소속학회 목록 grid */
		$("#grid_list_detail2").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["학회명","기간(fr)","기간(to)","학회내직위","관심도","기타사항","거래처코드","고객코드","일련번호"],
			colModel:[
						{name:"hakheo_nm",	index:"hakheo_nm",	align:"left", width:150, editable:true, edittype:"text"},
						{name:"datef",		index:"datef",		align:"center", width:100, editable:true, edittype:"text"},
						{name:"datet",		index:"datet",		align:"center", width:100, editable:true, edittype:"text"},
						{name:"jiwi",		index:"jiwi",		align:"left", width:150, editable:true, edittype:"text"},
						{name:"gwansimdo",	index:"gwansimdo",	align:"center", width:100, editable:true, edittype:"select", editoptions:{value:"1:상;2:중;3:하"}, formatter:gwansimdoFormatter}, 
						{name:"gita",		index:"gita",		align:"left", width:400, editable:true, edittype:"text", editoptions:Commons.jqgridEditOptions("grid_list_detail2", "Y")},
						{name:"cust_id",		index:"cust_id",		hidden:true},
						{name:"customer_id",	index:"customer_id",	hidden:true},
						{name:"seq",			index:"seq",			hidden:true}
					],
			height:434,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				if(id){
					$("#grid_list_detail2").jqGrid("saveRow",lastsel); // 현재 수정폼을 저장
					$("#grid_list_detail2").jqGrid("restoreRow",lastsel); // 현재 수정폼을 복구
					$("#grid_list_detail2").jqGrid("editRow",id,true); // 선택한 row를 수정폼으로
					lastsel=id; // 마지막 선택한 row는 현재 row가 된다
					
					/* 현재 선택한 row가 입력을 통해 추가된 row인지 확인 */
					var isExist = false; // flag
					for (var i = 0; i < insertDataArr.length; i++) { // value 위치 검색
						if (insertDataArr[i] == id) {
							isExist = true;
							break;
						}
					}
					
					if (!isExist) { // 없을 경우
						Business.addArray(updateDataArr, id); // update 배열에 현재 id를 넣는다.
					}
					
				}
			},
			
			pager: "#grid_Pager_detail2"
   		});
		
		/* 기타사항 고객목록 grid */
		$("#grid_list_client6").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["고객코드","고객명"],
			colModel:[
						{name:"customer_id",		index:"customer_id",		align:"center"},
						{name:"customer_nm",		index:"customer_nm",		align:"left"}
					],
			height:434,
			width:260,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* 조회 완료 시 */
			loadComplete: function(data){
				if (data.records > 0) {
					$("#grid_list_client6").setSelection(1, true); // 첫번째 row 선택
				}
			},
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				var ret = $("#grid_list_client6").getRowData(id);
				$("#customer_id").val(ret.customer_id);
				
				/* 기타사항 목록 호출 */
				getClientDetailGridList(ret.customer_id);
			},
			
			pager: "#grid_Pager_client6"
   		});
		
		/* 교우관계 고객목록 grid */
		$("#grid_list_client5").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["고객코드","고객명"],
			colModel:[
						{name:"customer_id",		index:"customer_id",		align:"center"},
						{name:"customer_nm",		index:"customer_nm",		align:"left"}
					],
			height:434,
			width:260,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* 조회 완료 시 */
			loadComplete: function(data){
				if (data.records > 0) {
					$("#grid_list_client5").setSelection(1, true); // 첫번째 row 선택
				}
			},
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				var ret = $("#grid_list_client5").getRowData(id);
				$("#customer_id").val(ret.customer_id);
				
				/* 교우관계 목록 호출 */
				getClientDetailGridList(ret.customer_id);
			},
			
			pager: "#grid_Pager_client5"
   		});
		
		/* 기념일 고객목록 grid */
		$("#grid_list_client4").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["고객코드","고객명"],
			colModel:[
						{name:"customer_id",		index:"customer_id",		align:"center"},
						{name:"customer_nm",		index:"customer_nm",		align:"left"}
					],
			height:434,
			width:260,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* 조회 완료 시 */
			loadComplete: function(data){
				if (data.records > 0) {
					$("#grid_list_client4").setSelection(1, true); // 첫번째 row 선택
				}
			},
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				var ret = $("#grid_list_client4").getRowData(id);
				$("#customer_id").val(ret.customer_id);
				
				/* 기념일 목록 호출 */
				getClientDetailGridList(ret.customer_id);
			},
			
			pager: "#grid_Pager_client4"
   		});
		
		/* 가족관계 고객목록 grid */
		$("#grid_list_client3").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["고객코드","고객명"],
			colModel:[
						{name:"customer_id",		index:"customer_id",		align:"center"},
						{name:"customer_nm",		index:"customer_nm",		align:"left"}
					],
			height:434,
			width:260,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* 조회 완료 시 */
			loadComplete: function(data){
				if (data.records > 0) {
					$("#grid_list_client3").setSelection(1, true); // 첫번째 row 선택
				}
			},
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				var ret = $("#grid_list_client3").getRowData(id);
				$("#customer_id").val(ret.customer_id);
				
				/* 가족관계 목록 호출 */
				getClientDetailGridList(ret.customer_id);
			},
			
			pager: "#grid_Pager_client3"
   		});
		
		/* 소속학회 고객목록 grid */
		$("#grid_list_client2").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["고객코드","고객명"],
			colModel:[
						{name:"customer_id",		index:"customer_id",		align:"center"},
						{name:"customer_nm",		index:"customer_nm",		align:"left"}
					],
			height:434,
			width:260,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* 조회 완료 시 */
			loadComplete: function(data){
				if (data.records > 0) {
					$("#grid_list_client2").setSelection(1, true); // 첫번째 row 선택
				}
			},
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				var ret = $("#grid_list_client2").getRowData(id);
				$("#customer_id").val(ret.customer_id);
				
				/* 소속학회 목록 호출 */
				getClientDetailGridList(ret.customer_id);
			},
			
			pager: "#grid_Pager_client2"
   		});
		
		/* 개인정보 목록 grid */
		$("#grid_list_client1").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["고객코드","고객명","성별","생년월일","실제생일","결혼여부","결혼기념일","성향","종교","출신고교","출신대학","전화번호","핸드폰번호","팩스번호","메일주소","차량번호","차종/색상","해외연수여부","직위","전문과","자녀관계","수련병원","전공","주거래도매처","대인관계","취미","금기사항","우편번호","주소","상세주소","기타사항"],
			colModel:[
						{name:"customer_id",		index:"customer_id",		width:80,	align:"center"},
						{name:"customer_nm",		index:"customer_nm",		width:50,	align:"left"},
						{name:"sex",				index:"sex",				width:40,	align:"center"},
						{name:"birth_day",			index:"birth_day",			width:90,	align:"center"},
						{name:"act_birth_day",		index:"act_birth_day",		width:90,	align:"center"},
						{name:"marry_yn",			index:"marry_yn",			width:55,	align:"center"},
						{name:"marry_day",			index:"marry_day",			width:90,	align:"center"},
						{name:"disposition",		index:"disposition",		width:150,	align:"left"},
						{name:"religion",			index:"religion",			width:55,	align:"left"},
						{name:"highschool",			index:"highschool",			width:65,	align:"left"},
						{name:"university",			index:"university",			width:65,	align:"left"},
						{name:"tel",				index:"tel",				width:90,	align:"left"},
						{name:"mobile",				index:"mobile",				width:90,	align:"left"},
						{name:"fax",				index:"fax",				width:90,	align:"left"},
						{name:"email",				index:"email",				width:100,	align:"left"},
						{name:"car_no",				index:"car_no", 			width:90,	align:"left"},
						{name:"car_color",			index:"car_color",			width:100,	align:"left"},
						{name:"foreign_study_yn",	index:"foreign_study_yn",	width:90,	align:"center"},
						{name:"rank",				index:"rank",				width:55,	align:"left"},
						{name:"lesson",				index:"lesson",				width:65,	align:"left"},
						{name:"child_kind",			index:"child_kind",			width:65,	align:"left"},
						{name:"hospital",			index:"hospital",			width:80,	align:"left"},
						{name:"major",				index:"major",				width:65,	align:"left"},
						{name:"main_buying",		index:"main_buying",		width:80,	align:"left"},
						{name:"human_rel",			index:"human_rel",			width:65,	align:"left"},
						{name:"hobby",				index:"hobby",				width:65,	align:"left"},
						{name:"taboo_list",			index:"taboo_list",			width:80,	align:"left"},
						{name:"zip",				index:"zip",				width:80,	align:"center"},
						{name:"address1",			index:"address1",			width:150,	align:"left"},
						{name:"address2",			index:"address2",			width:150,	align:"left"},
						{name:"gita",				index:"gita",				width:150,	align:"left"}
					],
			height:109,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			
			/* 조회 완료 시 */
			loadComplete: function(data){
				if (data.records > 0) {
					$("#grid_list_client1").setSelection(1, true); // 첫번째 row 선택
				}
			},
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				setPersonalInfo(id); // 고객정보 수정폼 셋팅
			},
			
			pager: "#grid_Pager_client1"
   		});
	
		/* 거래처 목록 grid */
		$("#grid_list_customer").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["거래처코드","거래처명","","사원코드","사원명"],
			colModel:[
						{name:"cust_id",	index:"cust_id",	align:"center"},
						{name:"cust_nm",	index:"cust_nm", 	align:"left"},
						{name:"cust_id2",	index:"cust_id2", 	align:"center", sortable:false, width:25, fixed:true, formatter:detailPopupButton},
						{name:"sawon_id",	index:"sawon_id", 	align:"center"},
						{name:"sawon_nm",	index:"sawon_nm", 	align:"left"}
					],
			formatter : {
				integer : {thousandsSeparator: ",", defaultValue: '0'} // 천단위 마다 콤마
	        },
			height:109,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			autowidth:true,
			
			/* 조회 완료 시 */
			loadComplete: function(data){
				if (data.records > 0) {
					$("#total_cnt").text(data.records);
					$("#grid_list_customer").setSelection(1, true); // 첫번째 row 선택
					
					customerRegistActionFlag = true; // 인쇄, 엑셀 사용 가능하도록
				} else {
					alert("조회 결과가 없습니다.");
					customerRegistActionFlag = false;
				}
			},
			
			/* row 선택 시 실행 */
			onSelectRow: function(id){
				var ret = $("#grid_list_customer").getRowData(id);
				$("#cust_id").val(ret.cust_id);
				
				getClientGridList(ret.cust_id); // 고객 목록 호출
			},
			
			pager: "#grid_Pager_customer"
   		});
		
		/* 조회 버튼 */
		$("#ct_retrieve_btn_type01").on("click",function(){
			getCustomerInfoGridList();
		});
		
		/* 입력 버튼 */
		$("#p_insert").on("click",function(){
			setInsertForm();
		});
		
		/* 저장 버튼 */
		$("#p_save").on("click",function(){
			saveCustomerInfo();
		});
		
		/* 삭제 버튼 */
		$("#p_delete").on("click",function(){
			deleteCustomerInfo();
		});
		
		/* 인쇄 버튼 */
		$("#p_print").on("click",function(){
			Commons.extraAction(customerRegistActionFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', 'customerRegist');
		});
		
		/* 엑셀 버튼 */
		$("#p_excel").on("click",function(){
			Commons.extraAction(customerRegistActionFlag, 'excel', '<%=ONLINE_ROOT%>/business/customerInfoGridListExcelDown.do', '');
		});
		
		/* 닫기 버튼 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('고객등록');
		});

		/* 최초 grid 넓이값 셋팅 */
		var width = $("#customerRegist").width() - 2;
		$("#grid_list_customer").setGridWidth(width, true);
		$("#grid_list_client1").setGridWidth(width, false);
		
		var width2 = width - $("#grid_list_client2").parents("div.left_box").width() - $("#grid_list_detail2").parents("div.right_box").css("margin-left").replace("px", "") - 2;
		$("#grid_list_detail2").setGridWidth(width2, false);
		$("#grid_list_detail3").setGridWidth(width2, false);
		$("#grid_list_detail4").setGridWidth(width2, false);
		$("#grid_list_detail5").setGridWidth(width2, false);
		$("#grid_list_detail6").setGridWidth(width2, true);
		
		/* 엔터키 눌렸을 경우 조회되도록. */
		$("body").on("keydown", function(event){
			if(event.keyCode==13 && event.target.name!="grid_count"){
				getCustomerInfoGridList();
				return false;
			}
		});
		
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
	$(window).resize(function(){
		var width = $("#customerRegist").width() - 2;
		$("#grid_list_customer").setGridWidth(width, true);
		$("#grid_list_client1").setGridWidth(width, false);
		
		var width2 = width - $("#grid_list_client2").parents("div.left_box").width() - $("#grid_list_detail2").parents("div.right_box").css("margin-left").replace("px", "") - 2;
		$("#grid_list_detail2").setGridWidth(width2, false);
		$("#grid_list_detail3").setGridWidth(width2, false);
		$("#grid_list_detail4").setGridWidth(width2, false);
		$("#grid_list_detail5").setGridWidth(width2, false);
		$("#grid_list_detail6").setGridWidth(width2, true);
	});
	
	/* 거래처 목록 내 팝업 버튼 포맷 */
	function detailPopupButton(cellvalue){
		return "<button type='button' style='width:18px; height:18px; border:0; no-repeat 0 0; padding:0; vertical-align:middle;' onclick='javascript:openCustomerDetailPopup(\""+cellvalue+"\");'><img src='"+ONLINE_ROOT+"/asset/img/ico_search.gif'/></button>";
	}
	
	/* 관심도 포맷 */
	function gwansimdoFormatter(cellvalue){
		var returnValue = "";
		
		if ("1" == cellvalue || "상" == cellvalue) {
			returnValue = "상";
		} else if ("2" == cellvalue || "중" == cellvalue) {
			returnValue = "중";
		} else if ("3" == cellvalue || "하" == cellvalue) {
			returnValue = "하";
		}
		
		return returnValue;
	}
	
	/* 가족관계 포맷 */
	function relationFormatter(cellvalue){
		var returnValue = "";
		
		if ("01" == cellvalue || "본인" == cellvalue) {
			returnValue = "본인";
		} else if ("02" == cellvalue || "부" == cellvalue) {
			returnValue = "부";
		} else if ("03" == cellvalue || "모" == cellvalue) {
			returnValue = "모";
		} else if ("04" == cellvalue || "처" == cellvalue) {
			returnValue = "처";
		} else if ("05" == cellvalue || "자" == cellvalue) {
			returnValue = "자";
		} else if ("06" == cellvalue || "녀" == cellvalue) {
			returnValue = "녀";
		}
		
		return returnValue;
	}
	
	/* 거래처 정보 팝업 */
	function openCustomerDetailPopup(cust_id){
		/* 파라미터 셋팅 */
		var params = {
				cust_id : cust_id
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		Commons.popupOpen('customerDetail', '<%=ONLINE_ROOT%>/business/customerDetailPopup.do?' + paramStr, '1000', '650');
	}
	
	/* 거래처 목록 */
	function getCustomerInfoGridList(){
		/* 파라미터 셋팅 */
		var paramStr = $("#frm").serialize();
		
		/* 호출 */
		$("#grid_list_customer").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/customerInfoGridList.do?" + paramStr}).trigger("reloadGrid", [{page:1}]);
	}
	
	/* 고객 목록 */
	function getClientGridList(cust_id){
		/* 파라미터 셋팅 */
		var params = {
			as_cust_id : cust_id
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list_client" + currentTab).jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/clientGridList.do?" + paramStr}).trigger("reloadGrid", [{page:1}]);
	}
	
	/* 소속학회, 가족관계, 기념일, 교우관계, 기타사항 목록 */
	function getClientDetailGridList(customer_id){
		initVar(); // 전역변수 초기화
		
		var cust_id = $("#cust_id").val();
		
		/* 파라미터 셋팅 */
		var params = {
			as_cust_id : cust_id,
			as_customer_id : customer_id,
			currentTab : currentTab
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list_detail" + currentTab).jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/clientDetailGridList.do?" + paramStr}).trigger("reloadGrid", [{page:1}]);
	}
	
	/* 개인정보 폼 셋팅 */
	function setPersonalInfo(id) {
		/* radio 초기화 */
		$("input:radio[name='sex']").prop("checked", false);
		$("input:radio[name='marry_yn']").prop("checked", false);
		$("input:radio[name='foreign_study_yn']").prop("checked", false);
		
		var ret = $("#grid_list_client1").getRowData(id);
		
		$("#as_customer_id").val(ret.customer_id);
		$("#customer_id").val(ret.customer_id);
		$("#customer_nm").val(ret.customer_nm);
		$("#birth_day").val(ret.birth_day);
		$("#act_birth_day").val(ret.act_birth_day);
		$("#marry_day").val(ret.marry_day);
		$("#disposition").val(ret.disposition);
		$("#religion").val(ret.religion);
		$("#highschool").val(ret.highschool);
		$("#university").val(ret.university);
		$("#tel").val(ret.tel);
		$("#mobile").val(ret.mobile);
		$("#fax").val(ret.fax);
		$("#email").val(ret.email);
		$("#car_no").val(ret.car_no);
		$("#car_color").val(ret.car_color);
		$("#rank").val(ret.rank);
		$("#lesson").val(ret.lesson);
		$("#child_kind").val(ret.child_kind);
		$("#hospital").val(ret.hospital);
		$("#major").val(ret.major);
		$("#main_buying").val(ret.main_buying);
		$("#human_rel").val(ret.human_rel);
		$("#hobby").val(ret.hobby);
		$("#taboo_list").val(ret.taboo_list);
		$("#zip").val(ret.zip);
		$("#address1").val(ret.address1);
		$("#address2").val(ret.address2);
		$("#gita").val(ret.gita);
		
		if (id > 0) { // 수정폼일 경우 radio 셋팅
			saveType = "update"; // 저장 타입 update로 셋팅
			
			$("#sex_"+ret.sex).prop("checked", true);
			$("#marry_"+ret.marry_yn).prop("checked", true);
			$("#foreign_study_"+ret.foreign_study_yn).prop("checked", true);	
		}
	}
	
	// 입력폼 셋팅, grid row 추가
	function setInsertForm(){
		/* 조회하지 않았을 경우 */
		if ($("#grid_list_customer").getGridParam("reccount") == 0) {
			alert("거래처를 먼저 조회하세요.");
			return false;
		}
		
		/* 선택한 탭에 따른 분기처리 */
		if (currentTab == 1) { // 개인정보일 경우
			saveType = "insert"; // 저장 타입 insert로 셋팅
			setPersonalInfo(0); // 개인정보 폼 초기화	
			
		} else { // 그 외 : grid data
			var targetRowId = $("#grid_list_detail" + currentTab).getGridParam("reccount") + 1; // 총 row 수 + 1을 id로 한다.
			var data = {}; // grid data 형태
			
			/* 선택한 탭에 따라 추가되는 grid row의 형태 */
			switch (currentTab) {
			    case 2 :
			    	data = {hakheo_nm:"", datef:"", datet:"", jiwi:"", gwansimdo:"", gita:""};
			    	break;
			    case 3 :
			    	data = {relation:"", name:"", birthday:"", job:"", gita:""};
			    	break;
			    case 4 :
			    	data = {sdate:"", kind:"", bigo:""};
			    	break;
			    case 5 :
			    	data = {corp_nm:"", name:"", relation:"", lesson:"", gwansimdo:""};
			    	break;
			    case 6 :
			    	data = {gita:""};
			    	break;
			} 
			
			$("#grid_list_detail" + currentTab).addRowData(targetRowId, data); // grid에 row 추가
			
			Business.addArray(insertDataArr, targetRowId); // insert 배열에 현재 id 셋팅
		}
	}
	
	/* 저장 */
	function saveCustomerInfo(){
		if ($("#grid_list_customer").getGridParam("reccount") == 0) {
			alert("거래처를 먼저 조회하세요.");
			return false;
		}
		
		var frm = $("#personalInfoFrm");
		var url = ONLINE_ROOT + "/business/procCustomerInfo.do";
		
		/* 선택한 탭에 따른 분기처리 */
		if (currentTab == 1) { // 개인정보일 경우
			$("#saveType").val(saveType);
		
			if ("insert" == saveType || "update" == saveType) { // 등록, 수정 시에만 유효성 체크
				
				if (formCheck.getByteLength($("#customer_nm").val()) > 30) { // 이름
					alert("이름은 한글 15자 혹은 영문 30자 이내로 입력해주세요.(30byte)");
					$("#customer_nm").focus();
					return;
				}
			
				if (!formCheck.isNull($("#birth_day").val()) && !formCheck.isDateFormat($("#birth_day").val())) { // 생년월일
					alert("올바른 날짜를 입력해주세요.");
					$("#birth_day").focus();
					return false;
				}
				
				if (!formCheck.isNull($("#act_birth_day").val()) && !formCheck.isDateFormat($("#act_birth_day").val())) { // 실제생일
					alert("올바른 날짜를 입력해주세요.");
					$("#act_birth_day").focus();
					return false;
				}
			
				if (formCheck.getByteLength($("#religion").val()) > 20) { // 종교
					alert("종교는 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
					$("#religion").focus();
					return;
				}
				
				if (!formCheck.isNull($("#marry_day").val()) && !formCheck.isDateFormat($("#marry_day").val())) { // 결혼기념일
					alert("올바른 날짜를 입력해주세요.");
					$("#marry_day").focus();
					return false;
				}
				
				if (formCheck.getByteLength($("#child_kind").val()) > 20) { // 자녀관계
					alert("자녀관계는 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
					$("#child_kind").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#disposition").val()) > 20) { // 성향
					alert("성향은 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
					$("#disposition").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#hobby").val()) > 20) { // 취미
					alert("취미는 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
					$("#hobby").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#highschool").val()) > 30) { // 출신고교
					alert("출신고교는 한글 15자 혹은 영문 30자 이내로 입력해주세요.(30byte)");
					$("#highschool").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#university").val()) > 30) { // 출신대학
					alert("출신대학은 한글 15자 혹은 영문 30자 이내로 입력해주세요.(30byte)");
					$("#university").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#taboo_list").val()) > 40) { // 금기사항
					alert("금기사항은 한글 20자 혹은 영문 40자 이내로 입력해주세요.(40byte)");
					$("#taboo_list").focus();
					return;
				}
				
				if (formCheck.isNumDash($("#tel").val())) { // 전화번호
					alert("전화번호는 숫자 혹은 - 만 입력 가능합니다.");
					$("#tel").focus();
					return;
				}
				
				if (formCheck.isNumDash($("#mobile").val())) { // 핸드폰번호
					alert("핸드폰번호는 숫자 혹은 - 만 입력 가능합니다.");
					$("#mobile").focus();
					return;
				}
				
				if (formCheck.isNumDash($("#fax").val())) { // 팩스번호
					alert("팩스번호는 숫자 혹은 - 만 입력 가능합니다.");
					$("#fax").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#rank").val()) > 10) { // 직위
					alert("직위는 한글 5자 혹은 영문 10자 이내로 입력해주세요.(10byte)");
					$("#rank").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#lesson").val()) > 20) { // 전문과
					alert("전문과는 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
					$("#lesson").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#hospital").val()) > 20) { // 수련병원
					alert("학회명은 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
					$("#hospital").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#major").val()) > 30) { // 전공
					alert("전공은 한글 15자 혹은 영문 30자 이내로 입력해주세요.(30byte)");
					$("#major").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#human_rel").val()) > 20) { // 대인관계
					alert("대인관계는 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
					$("#human_rel").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#car_no").val()) > 20) { // 차량번호
					alert("차량번호는 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
					$("#car_no").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#car_color").val()) > 20) { // 차종/색상
					alert("차종/색상은 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
					$("#car_color").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#gita").val()) > 600) { // 기타사항
					alert("기타사항은 한글 300자 혹은 영문 600자 이내로 입력해주세요.(600byte)");
					$("#gita").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#main_buying").val()) > 30) { // 주거래도매
					alert("주거래도매는 한글 15자 혹은 영문 30자 이내로 입력해주세요.(30byte)");
					$("#main_buying").focus();
					return;
				}
				
				if (!formCheck.isNull($("#email").val()) && !formCheck.isValidEmail($("#email").val())) { // 메일주소
					alert("올바른 메일주소가 아닙니다.");
					$("#email").focus();
					return;
				}
				
				if (formCheck.isNumer($("#zip").val())) { // 우편번호
					alert("우편번호는 숫자 6자 이내로 입력해주세요.(6byte)");
					$("#zip").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#address1").val()) > 100) { // 주소
					alert("주소는 한글 50자 혹은 영문 100자 이내로 입력해주세요.(100byte)");
					$("#address1").focus();
					return;
				}
				
				if (formCheck.getByteLength($("#address2").val()) > 50) { // 상세주소
					alert("상세주소는 한글 25자 혹은 영문 50자 이내로 입력해주세요.(50byte)");
					$("#address2").focus();
					return;
				}
			}
		
		} else { //  그 외 : grid data 동적 생성
			frm = $("#clientDetailFrm"); // 실제 submit form
			url = ONLINE_ROOT + "/business/procClientDetail.do";
			
			// 수정폼 복구
			$("#grid_list_detail"+currentTab).jqGrid("saveRow",lastsel);
			$("#grid_list_detail"+currentTab).jqGrid("restoreRow",lastsel);
			
			// 유효성 체크
			for (var i = 0; i < $("#grid_list_detail"+currentTab).getGridParam("reccount"); i++) {
				var ret = $("#grid_list_detail"+currentTab).getRowData((i+1));
				
				if ("2" == currentTab) { // 소속학회
					
					var hakheo_nm = ret.hakheo_nm;
					var datef = ret.datef;
					var datet = ret.datet;
					var jiwi = ret.jiwi;
					var gita = ret.gita;
					
					if (formCheck.getByteLength(hakheo_nm) > 30) { // 학회명
						alert("학회명은 한글 15자 혹은 영문 30자 이내로 입력해주세요.(30byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (!formCheck.isNull(datef) && (formCheck.isNumer(datef) || formCheck.getByteLength(datef) > 8)) { // 기간 fr
						alert("기간은 숫자 8자 이내로 입력해주세요.(8byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (!formCheck.isNull(datet) && (formCheck.isNumer(datet) || formCheck.getByteLength(datet) > 8)) { // 기간 fr
						alert("기간은 숫자 8자 이내로 입력해주세요.(8byte))");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (formCheck.getByteLength(jiwi) > 20) { // 학회 내 직위
						alert("학회 내 직위는 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (formCheck.getByteLength(gita) > 60) { // 기타사항
						alert("기타사항은 한글 30자 혹은 영문 60자 이내로 입력해주세요.(60byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
				} else if ("3" == currentTab) { // 가족관계
					
					var name = ret.name;
					var birthday = ret.birthdat;
					var job = ret.job;
					var gita = ret.gita;
					
					if (formCheck.getByteLength(name) > 20) { // 성명
						alert("성명은 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (!formCheck.isNull(birthday) && (formCheck.isNumer(birthday) || formCheck.getByteLength(birthday) > 8)) { // 생일
						alert("생일은 숫자 8자 이내로 입력해주세요.(8byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (formCheck.getByteLength(job) > 20) { // 직업
						alert("직업은 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (formCheck.getByteLength(gita) > 60) { // 기타사항
						alert("기타사항은 한글 30자 혹은 영문 60자 이내로 입력해주세요.(60byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
				} else if ("4" == currentTab) { // 기념일
					
					var sdate = ret.sdate;
					var kind = ret.kind;
					var bigo = ret.bigo;
					
					if (!formCheck.isNull(sdate) && (formCheck.isNumer(sdate) || formCheck.getByteLength(sdate) > 8)) { // 날짜
						alert("날짜는 숫자 8자 이내로 입력해주세요.(8byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (formCheck.getByteLength(kind) > 30) { // 종류
						alert("종류는 한글 15자 혹은 영문 30자 이내로 입력해주세요.(30byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (formCheck.getByteLength(bigo) > 40) { // 상세내역
						alert("상세내역은 한글 20자 혹은 영문 40자 이내로 입력해주세요.(40byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
				} else if ("5" == currentTab) { // 교우관계
					
					var corp_nm = ret.corp_nm;
					var name = ret.name;
					var friendship = ret.friendship;
					var lesson = ret.lesson;
					
					if (formCheck.getByteLength(corp_nm) > 20) { // 병원명/기관명/제약회사명
						alert("병원명/기관명/제약회사명은 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (formCheck.getByteLength(name) > 20) { // 성명
						alert("성명은 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (formCheck.getByteLength(friendship) > 20) { // 관계
						alert("관계는 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
					if (formCheck.getByteLength(lesson) > 20) { // 전문과
						alert("전문과는 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
					
				} else if ("6" == currentTab) {
					
					var gita = ret.gita;
					
					if (formCheck.getByteLength(gita) > 1000) { // 기타사항
						alert("기타사항은 한글 500자 혹은 영문 1000자 이내로 입력해주세요.(1000byte)");
						$("#grid_list_detail"+currentTab).jqGrid("editRow",(i+1),true);
						return;
					}
				}
			}
			
			/* 기본 정보 */
			$("<input></input>").attr({type:"hidden", name:"cust_id", value: $("#cust_id").val()}).appendTo(frm);
			$("<input></input>").attr({type:"hidden", name:"customer_id", value: $("#customer_id").val()}).appendTo(frm);
			$("<input></input>").attr({type:"hidden", name:"currentTab", value: currentTab}).appendTo(frm);
			
			/* 등록 정보 */
			for (var i = 0; i < insertDataArr.length; i++) { // form에 data 동적 생성
				var ret = $("#grid_list_detail"+currentTab).getRowData(insertDataArr[i]);
				var gwansimdo = "";
				
				if ("상" == ret.gwansimdo) {
					gwansimdo = "1";
				} else if ("중" == ret.gwansimdo) {
					gwansimdo = "2";
				} else if ("하" == ret.gwansimdo) {
					gwansimdo = "3";
				}
				
				if ("본인" == ret.relation) {
					relation = "01";
				} else if ("부" == ret.relation) {
					relation = "02";
				} else if ("모" == ret.relation) {
					relation = "03";
				} else if ("처" == ret.relation) {
					relation = "04";
				} else if ("자" == ret.relation) {
					relation = "05";
				} else if ("녀" == ret.relation) {
					relation = "06";
				}
			
				$("<input></input>").attr({type:"hidden", name:"insert_hakheo_nm", value: ret.hakheo_nm}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_datef", value: ret.datef}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_datet", value: ret.datet}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_jiwi", value: ret.jiwi}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_gwansimdo", value: gwansimdo}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_gita", value: ret.gita}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_relation", value: relation}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_name", value: ret.name}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_birthday", value: ret.birthday}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_job", value: ret.job}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_sdate", value: ret.sdate}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_kind", value: ret.kind}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_bigo", value: ret.bigo}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_corp_nm", value: ret.corp_nm}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_lesson", value: ret.lesson}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"insert_friendship", value: ret.friendship}).appendTo(frm);
				
			}
			
			/* 수정 정보 */
			for (var i = 0; i < updateDataArr.length; i++) { // form에 data 동적 생성
				var ret = $("#grid_list_detail"+currentTab).getRowData(updateDataArr[i]);
				var gwansimdo = "";
				var relation = "";
				
				if ("상" == ret.gwansimdo) {
					gwansimdo = "1";
				} else if ("중" == ret.gwansimdo) {
					gwansimdo = "2";
				} else if ("하" == ret.gwansimdo) {
					gwansimdo = "3";
				}
				
				if ("본인" == ret.relation) {
					relation = "01";
				} else if ("부" == ret.relation) {
					relation = "02";
				} else if ("모" == ret.relation) {
					relation = "03";
				} else if ("처" == ret.relation) {
					relation = "04";
				} else if ("자" == ret.relation) {
					relation = "05";
				} else if ("녀" == ret.relation) {
					relation = "06";
				}
				
				$("<input></input>").attr({type:"hidden", name:"update_seq", value: ret.seq}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_hakheo_nm", value: ret.hakheo_nm}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_datef", value: ret.datef}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_datet", value: ret.datet}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_jiwi", value: ret.jiwi}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_gwansimdo", value: gwansimdo}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_gita", value: ret.gita}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_relation", value: relation}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_name", value: ret.name}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_birthday", value: ret.birthday}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_job", value: ret.job}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_sdate", value: ret.sdate}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_kind", value: ret.kind}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_bigo", value: ret.bigo}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_corp_nm", value: ret.corp_nm}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_lesson", value: ret.lesson}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"update_friendship", value: ret.friendship}).appendTo(frm);
				
			}
		}
		
		$.ajax({
			type:"POST",
			data: frm.serialize(),
			url: url,
			dataType:"json",
			success:function(data){
				if (data.resultCount > 0 || data.message == "Y") {
					alert("완료되었습니다.");
				} else {
					alert("실패");
				}
			},
			complete:function(){
				if (currentTab == 1) { // 개인정보일 경우
					getClientGridList($("#cust_id").val());
				} else {
					getClientDetailGridList($("#customer_id").val());
				}
			}
		});
	}
	
	/* 삭제 */
	function deleteCustomerInfo(){
		/* 선택한 탭에 따른 분기처리 */
		if (currentTab == 1) { // 개인정보일 경우
			if ("update" != saveType) {
				alert("삭제할 고객을 선택해주세요.");
			} else {
				saveType = "delete";
				saveCustomerInfo();
			}
		} else { // 그 외
			if (insertDataArr.length > 0) {
				alert("저장이 완료되지 않은 항목이 있습니다.");
				return false;
			}
		
			var id = $("#grid_list_detail"+currentTab).jqGrid("getGridParam", "selrow");
			var ret = $("#grid_list_detail"+currentTab).getRowData(id);
			
			if (null == id) {
				alert("삭제할 항목을 선택해주세요.");
				return false;
			}
			
			/* 파라미터 셋팅 */
			var params = {
				cust_id : $("#cust_id").val(),
				customer_id : $("#customer_id").val(),
				currentTab : currentTab,
				seq : ret.seq
			};
			
			var paramStr = $.param(params);
			
			$.ajax({
				type:"POST",
				data: paramStr,
				url:"<%=ONLINE_ROOT%>/business/deleteClientDetail.do",
				dataType:"json",
				success:function(data){
					if (data.resultCount > 0) {
						alert("완료되었습니다.");
					} else {
						alert("실패");
						
					}
				},complete:function(){
					getClientDetailGridList($("#customer_id").val());
				}
			});
		}
		
	}
	
	/* 탭 변경 */
	function changeTab(tab){
		currentTab = tab; // 현재 선택한 탭 설정
		initVar(); // 전역변수 초기화
		
		if ($("#grid_list_customer").getGridParam("reccount") > 0) { // 거래처 목록의 결과가 있다면
			var id = $("#grid_list_customer").jqGrid("getGridParam", "selrow");
			var ret = $("#grid_list_customer").getRowData(id);
			
			getClientGridList(ret.cust_id); // 선택한 탭에 고객 목록을 뿌려줌
		}
	}
	
	/* 전역변수 초기화, 탭이 바뀔때마다 수정, 저장되어야 하는 대상 리셋해줘야함 */
	function initVar(){
		insertDataArr = [];
		updateDataArr = [];
		lastsel = "";
		$("#clientDetailFrm").html("");
	}
	
	</script>
</head>

<body>
	<form name="frm" id="frm">
		<input type="hidden" name="as_dept_cd" id="as_dept_cd" value="<%=dept_cd %>" />
		<input type="hidden" name="as_pda_auth" id="as_pda_auth" value="<%=pda_auth %>" />
		
		<div class="wrap_search">
			<div class="search">
				<label class="w70">거래처</label>
				<input type="text" class="w140" name="as_cust_id" id="as_cust_id" onblur="javascript:Business.getCustomerName(this.id);"/>
				<button type="button" class="btn_search" onclick="javascript:Commons.popupOpen('as_cust_id', '<%=ONLINE_ROOT%>/business/common/customerList.do', '600', '655');" ><span class="blind">찾기</span></button>
				<input type="text" class="w435 ipt_disable" name="as_cust_nm" id="as_cust_id_name" readonly/>
				
				<label class="ml10">거래구분</label>
				<select class="w100" id="as_use_yn" name="as_use_yn">
					<option value="%" selected>전체</option>
					<option value="Y">거래</option>
					<option value="N">중지</option>
				</select>
				
				<div class="wrap_search_btn">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "ct_")%>
				</div>
			</div>
		</div>
	</form>
	<div class="inner_cont2">		
		<div class="wrap_btn_group">
			<div class="btn_group ta_r">
				<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
			</div>
		</div>		
		<div class="wrap_customer01">
			<p class="customer_num">결과 총 <span id="total_cnt">0</span>건</p>
			<div class="customer_box01 box_type01">
				<div id="customerRegist">
					<table id="grid_list_customer"></table>
					<div id="grid_Pager_customer" class="customerRegist"></div>
				</div>
			</div>
		</div>
		<ul class="customer_teb">
			<li><a href="#tab_customer01" class="active" onclick="javascript:changeTab(1);" >개인정보</a></li>
			<li><a href="#tab_customer02" onclick="javascript:changeTab(2);" >소속학회</a></li>
			<li><a href="#tab_customer03" onclick="javascript:changeTab(3);" >가족관계</a></li>
			<li><a href="#tab_customer04" onclick="javascript:changeTab(4);" >기념일</a></li>
			<li><a href="#tab_customer05" onclick="javascript:changeTab(5);" >교우관계</a></li>
			<li class="last_menu"><a href="#tab_customer06" onclick="javascript:changeTab(6);" >기타사항</a></li>
		</ul>
		<div class="tab_all tab_customer01" id="tab_customer01">
			<div class="customer_box01 box_type01">
				<table id="grid_list_client1"></table>
				<div id="grid_Pager_client1"></div>
			</div>
			<div class="re_style06 box_type01">
				<form id="personalInfoFrm" name="personalInfoFrm">
					<input type="hidden" name="saveType" id="saveType" />
					<input type="hidden" name="cust_id" id="cust_id" />
					<input type="hidden" name="customer_id" id="customer_id" />
					
					<table class="table_info type01">
						<colgroup>
							<col style="width:9%" />
							<col style="width:16%" />
							<col style="width:9%" />
							<col style="width:16%" />
							<col style="width:9%" />
							<col style="width:16%" />
							<col style="width:9%" />
							<col style="width:16%" />
						</colgroup>
						<tbody>
							<tr>
								<th class="no_border_l no_border_t">고객</th>
								<td class="customer no_border_t">
									<input type="text" class="ctm01 ipt_disable" id="as_customer_id" name="as_customer_id" readonly/>
									<input type="text" class="ctm02" id="customer_nm" name="customer_nm" />
								</td>
								<th class="no_border_t">성별</th>
								<td class="no_border_t">
									<input type="radio" id="sex_M" name="sex" value="M"/>
									<label for="man">남자</label>
									<input type="radio" id="sex_F" name="sex" value="F"/>
									<label for="woman">여자</label>
								</td>
								<th class="no_border_t">생년월일</th>
								<td class="input_full no_border_t">
									<p class="wrap_date">
										<input type="text" id="birth_day" name="birth_day"/>
										<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
									</p>
								</td>
								<th class="no_border_t">실제생일</th>
								<td class="input_full no_border_r no_border_t">
									<p class="wrap_date">
										<input type="text" id="act_birth_day" name="act_birth_day"/>
										<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
									</p>
								</td>
							</tr>
							<tr>
								<th class="no_border_l">종교</th>
								<td class="input_full">
									<input type="text" id="religion" name="religion"/>
								</td>
								<th>결혼여부</th>
								<td>
									<input type="radio" id="marry_Y" name="marry_yn" value="Y"/>
									<label for="man">기혼</label>
									<input type="radio" id="marry_N" name="marry_yn" value="N"/>
									<label for="woman">미혼</label>
								</td>
								<th>결혼기념일</th>
								<td class="input_full">
									<p class="wrap_date">
										<input type="text" id="marry_day" name="marry_day"/>
										<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
									</p>
								</td>
								<th>자녀관계</th>
								<td class="input_full01 no_border_r">
									<input type="text" id="child_kind" name="child_kind"/>
								</td>
							</tr>
							<tr>
								<th class="no_border_l">성향</th>
								<td class="input_full">
									<input type="text" id="disposition" name="disposition"/>
								</td>
								<th>취미</th>
								<td class="input_full">
									<input type="text" id="hobby" name="hobby"/>
								</td>
								<th>출신고교</th>
								<td class="input_full01">
									<input type="text" id="highschool" name="highschool" />
								</td>
								<th>출신대학</th>
								<td class="input_full01 no_border_r">
									<input type="text" id="university" name="university" />
								</td>
							</tr>
							<tr>
								<th class="no_border_l">금기사항</th>
								<td class="input_full">
									<input type="text" id="taboo_list" name="taboo_list" />
								</td>
								<th>전화번호</th>
								<td class="input_full">
									<input type="text" id="tel" name="tel" maxlength="20"/>
								</td>
								<th>핸드폰번호</th>
								<td class="input_full01">
									<input type="text" id="mobile" name="mobile" maxlength="20"/>
								</td>
								<th>팩스번호</th>
								<td class="input_full01 no_border_r">
									<input type="text" id="fax" name="fax" maxlength="20"/>
								</td>
							</tr>
							<tr>
								<th class="no_border_l">직위</th>
								<td class="input_full">
									<input type="text" id="rank" name="rank"/>
								</td>
								<th>전문과</th>
								<td class="input_full">
									<input type="text" id="lesson" name="lesson"/>
								</td>
								<th>수련병원</th>
								<td class="input_full01">
									<input type="text" id="hospital" name="hospital"/>
								</td>
								<th>전공</th>
								<td class="input_full01 no_border_r">
									<input type="text" id="major" name="major"/>
								</td>
							</tr>
							<tr>
								<th class="no_border_l">해외연수</th>
								<td>
									<input type="radio" id="foreign_study_Y" name="foreign_study_yn" value="Y"/>
									<label for="man">연수</label>
									<input type="radio" id="foreign_study_N" name="foreign_study_yn" value="N"/>
									<label for="woman">미연수</label>
								</td>
								<th>대인관계</th>
								<td class="input_full">
									<input type="text" id="human_rel" name="human_rel"/>
								</td>
								<th>차량번호</th>
								<td class="input_full01">
									<input type="text" id="car_no" name="car_no" />
								</td>
								<th>차종/색상</th>
								<td class="input_full01 no_border_r">
									<input type="text" id="car_color" name="car_color" />
								</td>
							</tr>
							<tr>
								<th class="no_border_l">기타사항</th>
								<td class="input_full">
									<input type="text" id="gita" name="gita" />
								</td>
								<th>주거래도매</th>
								<td class="input_full">
									<input type="text" id="main_buying" name="main_buying"/>
								</td>
								<th>메일주소</th>
								<td class="input_full02 no_border_r" colspan="3">
									<input type="text" id="email" name="email" maxlength="128"/>
								</td>
							</tr>
							<tr>
								<th class="no_border_l no_border_b">우편번호</th>
								<td class="input_full no_border_b">
									<input type="text" id="zip" name="zip" maxlength="6"/>
								</td>
								<th class="no_border_b">주소</th>
								<td class="input_full no_border_b">
									<input type="text" id="address1" name="address1" />
								</td>
								<th class="no_border_b">상세주소</th>
								<td class="input_full02 no_border_r no_border_b" colspan="3">
									<input type="text" id="address2" name="address2" />
								</td>
							</tr>							
						</tbody>
					</table>
				</form>
			</div>
		</div>
		
		<!-- 소속학회 -->
		<div class="tab_all tab_customer02" id="tab_customer02">
			<div class="custom_box box_type01">
				<table>
					<colgroup>
						<col style="width:260px" />
						<col style="width:100%;" />
					</colgroup>
					<tbody>
						<tr>
							<td class="left_table">
								<div class="left_box">
									<table id="grid_list_client2"></table>
									<div id="grid_Pager_client2"></div>
								</div>
							</td>
							<td class="right_table">
								<div class="right_box">
									<table id="grid_list_detail2"></table>
									<div id="grid_Pager_detail2"></div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<!-- 가족관계 -->
		<div class="tab_all tab_customer03" id="tab_customer03">
			<div class="custom_box box_type01">
				<table>
					<colgroup>
						<col style="width:260px" />
						<col style="width:100%;" />
					</colgroup>
					<tbody>
						<tr>
							<td class="left_table">
								<div class="left_box">
									<table id="grid_list_client3"></table>
									<div id="grid_Pager_client3"></div>
								</div>
							</td>
							<td class="right_table">
								<div class="right_box">
									<table id="grid_list_detail3"></table>
									<div id="grid_Pager_detail3"></div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<!-- 기념일 -->
		<div class="tab_all tab_customer04" id="tab_customer04">
			<div class="custom_box box_type01">
				<table>
					<colgroup>
						<col style="width:260px" />
						<col style="width:100%;" />
					</colgroup>
					<tbody>
						<tr>
							<td class="left_table">
								<div class="left_box">
									<table id="grid_list_client4"></table>
									<div id="grid_Pager_client4"></div>
								</div>
							</td>
							<td class="right_table">
								<div class="right_box">
									<table id="grid_list_detail4"></table>
									<div id="grid_Pager_detail4"></div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<!-- 교우관계 -->
		<div class="tab_all tab_customer05" id="tab_customer05">
			<div class="custom_box box_type01">
				<table>
					<colgroup>
						<col style="width:260px" />
						<col style="width:100%;" />
					</colgroup>
					<tbody>
						<tr>
							<td class="left_table">
								<div class="left_box">
									<table id="grid_list_client5"></table>
									<div id="grid_Pager_client5"></div>
								</div>
							</td>
							<td class="right_table">
								<div class="right_box">
									<table id="grid_list_detail5"></table>
									<div id="grid_Pager_detail5"></div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		
		<!-- 기타사항 -->
		<div class="tab_all tab_customer06" id="tab_customer06">
			<div class="custom_box box_type01">
				<table>
					<colgroup>
						<col style="width:260px" />
						<col style="width:100%;" />
					</colgroup>
					<tbody>
						<tr>
							<td class="left_table">
								<div class="left_box">
									<table id="grid_list_client6"></table>
									<div id="grid_Pager_client6"></div>
								</div>
							</td>
							<td class="right_table">
								<div class="right_box">
									<table id="grid_list_detail6"></table>
									<div id="grid_Pager_detail6"></div>
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</div>
		<form id="clientDetailFrm" name="clientDetailFrm"></form>
	</div>
	<%@include file="/include/footer.jsp"%>
</body>
</html>