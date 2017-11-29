<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : regManagement.jsp
 * @메뉴명 : MANAGER > 프로그램 등록관리      
 * @최초작성일 : 2015/01/16            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="StyleSheet" href="<%=ONLINE_WEB_ROOT%>/css/dtree.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/dtree.js"></script>	
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/formCheck.js"></script>	
	<script type="text/javascript">
		
		var oriDataRecode = 0;		// 총 데이터 건수
		var addDataRecode = 0;		// 추가 될 데이터 행 번호
		var lastsel2 = "";			// 선택 된 행
		var submitted = false;		// 저장 중복방지 변수
		
		/**
		 * window dom load시 최초 호출되는 role목록 jqgrid
		 */
		$(document).ready(function(){
			// 버튼 리스트의 jqgrid부분
			jsonReader : {
			    repeatitems: false;
			}
			
			$("#btn_list").jqGrid({
				// 요청방식
				mtype:"post",
				// 결과물 받을 데이터 타입
				datatype:"json",
				// 컬럼명
				colNames:["PGM No","버튼 ID","사용","버튼명"],
				colModel:[
							{name:"pgm_no",		index:"pgm_no",			align:"left", width:50},						// 프로그램 ID
							{name:"btn_id",		index:"btn_id",			align:"left", width:200, type:"text", editable: true, edittype:"text",formatter: 'textbox', editoptions:{size:50, maxlength: 50}},		// 버튼 ID
							{name:"btn_use_yn",	index:"btn_use_yn", 	align:"center", width:100,	type:"checkbox", formatter: 'checkbox', width:30
									,formatoptions:{disabled: true}, editable:true, edittype:"checkbox"
									,editoptions:{
										  	value:'Y:N', 
										  	dataEvents:[{type:'click', fn: function(e){
										  		var rowId = $("#btn_list").getGridParam("selrow");  
										  		var selectSel = "";
												
												if(rowId && rowId!==selectSel){
											    	
													selectSel=rowId;
											    	var selectedCheck = $("#btn_list").getRowData(selectSel);
											    	
													$("#buttonUseYn").empty();
													$("#buttonUseYn").val(selectedCheck.btn_use_yn);
												}
										  		
										        }}]
									}  
							},		// 사용 여부
							{name:"btn_nm", index:"btn_nm", align:"left", type:"text", width:152, editable: true, edittype:"text", editrules:{required:true}, formatter: 'textbox', editoptions :  Commons.jqgridEditOptions('btn_list')}		// 버튼 명
						],
				
				// 선택된 행의 데이터 가져오기.
				onSelectRow: function(id){
					
					$('#btn_list').editRow(id,true);
					
					if(id && id!==lastsel2){
						$('#btn_list').restoreRow(lastsel2);
					}
					
					lastsel2=id;
			    	
			    	var selectedList = $("#btn_list").getRowData(lastsel2);
			    	
			    	$("#rowids").empty();			// row id 빈 값 셋팅
			    	$("#buttonPgmNo").empty();		// 버튼 프로그램 no 빈 값 셋팅
					$("#buttonId").empty();			// 버튼 id 빈 값 셋팅
					$("#buttonOriId").empty();		// 버튼 원래 id 빈 값 셋팅
					$("#buttonUseYn").empty();		// 버튼 사용여부 빈 값 셋팅
					$("#buttonName").empty();		// 버튼명 빈 값 셋팅
					
					$("#rowids").val(lastsel2);								// row id 선택 된  값 셋팅
			    	$("#buttonPgmNo").val(selectedList.pgm_no);				// 버튼 프로그램 no 선택 된  값 셋팅
					$("#buttonId").val($("#"+lastsel2+"_btn_id").val());	// 버튼 id 선택 된  값 셋팅
					$("#buttonOriId").val($("#"+lastsel2+"_btn_id").val());	// 버튼 원래 id 선택 된 값 셋팅
					$("#buttonUseYn").val(selectedList.btn_use_yn);			// 버튼 사용여부 선택 된 값 셋팅
					$("#buttonName").val($("#"+lastsel2+"_btn_nm").val());	// 버튼명 선택 된 값 셋팅
					
				},
				
				// 그리드 캡션
				height:167,
				//autowidth:true,
				
				loadComplete: function(data){
					if (data.records > 0) {
						oriDataRecode = data.records;
						addDataRecode = data.records +1;
						$("#buttonPgmNo").val(data.rows[0].pgm_no);
					}else{
						$("#buttonPgmNo").val("");
					}
				},
				
			});
			
			// 
			/**
		 	 * 프로그램 추가 버튼 클릭
		 	 */
			$("#cm_add").on("click",function(){
				
				if($("#parentPgm").val() == ""){
					alert("상위 메뉴 선택 후 메뉴를 추가 해주세요.");
					return;
				}
				
				if($("#pgmNo").val() != ""){
					alert("PGM No.가 등록 되어 있습니다.\n상위 메뉴 선택 후 메뉴를 추가 해주세요.");
					return;
				}
				
				if($("#pgmId").val() == ""){
					alert("PGM URL을 입력 해주세요.");
					$("#pgmId").focus();
					return;
				}
				
				if($("#pgmName").val() == ""){
					alert("PGM Name을 입력 해주세요.");
					$("#pgmName").focus();
					return;
				}
				
				procProgram("insert");
			}); 
			
			/**
		 	 * 프로그램 삭제시 호출되는 함수
		 	 */
			$("#cm_delete").on("click",function(){   
				
				if($("#pgmNo").val() == ""){
					alert("PGM No.가 없습니다.\n메뉴 선택 후 삭제 해주세요.");
					return;
				}
				
				if(confirm("시스템에 영향을 줄수 있습니다.\삭제 하시겠습니까?")){
					procProgram("delete");
				}
			});
			
			/**
		 	 * 프로그램 저장시 호출되는 함수
		 	 */
			$("#cm_save").on("click",function(){
				
				if($("#parentPgm").val() == ""){
					alert("상위 메뉴 code가 존재 하지 않습니다.");
					return;
				}
				
				if($("#pgmId").val() == ""){
					alert("PGM URL을 입력 해주세요.");
					$("#pgmId").focus();
					return;
				}
				
				if($("#pgmName").val() == ""){
					alert("PGM Name을 입력 해주세요.");
					$("#pgmName").focus();
					return;
				}
				procProgram("save");
			});
			
			/**
		 	 * 버튼 추가 버튼 클릭시 호출되는 함수
		 	 */
			$("#cb_add").on("click",function(){
				
				if($("#pgmNo").val() == ""){
					alert("프로그램 추가 후 등록 가능 합니다.");
					return;
				}
				
				if(addDataRecode == 0){
					addDataRecode = 1;
				}
				
				if(oriDataRecode+1 == addDataRecode){
					$("#rowids").val(addDataRecode);
					$("#btn_list").jqGrid('addRow', {
			           rowID : addDataRecode,          		// 중복되지 않게 rowid설정
			           initdata : {pgm_no:"", btn_id:"",btn_use_yn:"", btn_nm:""},
			           position :"last",           			// first, last
			           useDefValues : false,
			           useFormatter : false,
			           addRowParams : {extraparam:{}}
					});
					
					addDataRecode = addDataRecode+1;
				}
	
			}); 
			
			/**
		 	 * 삭제버튼 클릭시 호출되는 함수
		 	 */
			$("#cb_delete").on("click",function(){ 
				var rowid = $("#rowids").val();
				
				if(rowid == addDataRecode){
					//행삭제
					$("#btn_list").delRowData(rowid);
					$("#rowids").val("");
					addDataRecode = addDataRecode-1;
					
				}else{
					
					if(rowid == ""){
						alert("버튼을 선택 해주세요.");
						return;
					}
					
					if(confirm("해당 버튼을 삭제 하시겠습니까?")){
						//행삭제
						$("#btn_list").delRowData(rowid);
						$("#rowids").val("");
						addDataRecode = addDataRecode-1;
						oriDataRecode = oriDataRecode-1;
						
						// 데이터 삭제
						procButton("delete");
						
					}	
				}
				
			});
			
			/**
		 	 * 추가버튼 클릭시 최초 호출되는 함수
		 	 */
			$("#cb_save").on("click",function(){
				
				var buttonId = $("#buttonOriId").val();
				var saveRowId = $("#rowids").val();
				var type = "";
				
				if($("input:checkbox[id='"+saveRowId+"_btn_use_yn']").is(":checked") == true){
					$("#buttonUseYn").val("Y");
				}else{
					$("#buttonUseYn").val("N");
				}
				
				$("#buttonId").val($("#"+saveRowId+"_btn_id").val());
				$("#buttonName").val($("#"+saveRowId+"_btn_nm").val());
				
				if($("#buttonPgmNo").val() == ""){
					
					type = "insert";
					
					if($("#"+saveRowId+"_btn_id").val() == ""){
						alert("버튼 ID를 입력 해주세요.");
						return;
					}
					
				}else{
					
					type = "save";
					
					if(buttonId == ""){
						alert("버튼 ID를 입력 해주세요.");
						return;
					}
					
				}
				// 데이터 추가
				procButton(type);
				
			});
		});
		
		/**
	 	 * 프로그램 추가,저장,삭제를 처리하는 함수
	 	 * @param buttonType		프로그램 버튼 type
	 	 * @return 					resultCode
	 	 */
		function procProgram(buttonType){
			
			if(submitted == true) {return;}
			
			submitted = true;
			
			$("#procType").val(buttonType);
			var parentPgm = $("#parentPgm").val();
			var formData = $("form[name=programFrm]").serialize();
			var pgmId = $("#pgmId").val();
			var pgmName = $("#pgmName").val();
			var sortOrder = $("#sortOrder").val();
			var customPgmId = $("#pgmId").val().split(".").join("");
			
			customPgmId = customPgmId.split("_").join("");
			customPgmId = customPgmId.split("/").join("");
			
			if(buttonType == "insert" || buttonType == "save"){
				if(parentPgm == ""){
					alert("상위 메뉴가 없습니다.");
					submitted = false;
					return;
				}
				
				if(formCheck.getByteLength(pgmId) > 200){
					alert("PGM URL은 200byte이하로 입력 해주세요.");
					$("#pgmId").focus();
					submitted = false;
					return;
				}
				
				if(formCheck.isAlphaNum(customPgmId)){
					alert("PGM URL은 숫자 또는 영문, /, _만 가능합니다.");
					$("#pgmId").focus();
					submitted = false;
					return;
				}
				
				if(formCheck.getByteLength(pgmName) > 100){
					alert("PGM Name은 100byte이하로 입력 해주세요.");
					$("#pgmName").focus();
					submitted = false;
					return;
				}
				
				if(formCheck.isNumer(sortOrder)){
					alert("정렬 순서는 숫자만 가능합니다.");
					$("#sortOrder").focus();
					submitted = false;
					return;
				}
			}
			
			$.ajax({
				type : "POST"
				       	, async : true
				    	, url : "<%=ONLINE_ROOT %>/mgmt/procProgramAjax.do"
				    	, dataType : "json"
				    	, data : formData
				    	, success : function(data) {
				    		if(data.resultCode > 0){
				    			if(data.resultMsg == "insert"){
				    				alert("추가 되었습니다.");
				    				
				    				if($("input:checkbox[id='pgmUseYn']").is(":checked") == true){
				    					$("input:checkbox[id='pgmUseYn']").prop("checked", true);	
				    				}else{
				    					$("input:checkbox[id='pgmUseYn']").prop("checked", false);	
				    				}
				    				
									if($("input:checkbox[id='menuUseYn']").is(":checked") == true){
										$("input:checkbox[id='menuUseYn']").prop("checked", true);	
				    				}else{
				    					$("input:checkbox[id='menuUseYn']").prop("checked", false);
				    				}
									
				    			}else if(data.resultMsg == "save"){
				    				alert("저장 되었습니다.");
				    			}else{
				    				alert("삭제 되었습니다.");
				    				$('#programFrm')[0].reset();
				    				$("#btn_list").trigger("reloadGrid");
									$("input:checkbox[id='pgmUseYn']").prop("checked", false);
									$("input:checkbox[id='menuUseYn']").prop("checked", false);
				    			}
				    			
				    			addDataRecode = 0;
				    			
					    		$("#treeLeft").empty();
					    		$("#treeRiget").empty();
					    		Commons.treeMenuLeftAjax("ALL");
					    		Commons.treeMenuRightAjax("IN_USE");
				    		
				    		}else{
				    			alert("처리 중 장애가 발생 하였습니다.");
				    		}
				    }
				    , error : function(request, status, error) {
					     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
				    }
				    ,
		 	           complete : function(data) {
		 	        		submitted = false;   
	          		}
				    
			});
			
		}
		
		/**
	 	 * 왼쪽 메뉴 tree프로그램 선택시 호출 되는 함수
	 	 * @param  programNumber 프로그램 no
	 	 * @return data
	 	 */
		function selectedMenu(programNumber){
			
			addDataRecode = 0;
			oriDataRecode = 0;
			
			$("#rowids").val("");
			
			if(programNumber != ""){
				
				var pgmNo = Commons.leadingZeros(programNumber,5);
			
				$.ajax({
				    type : "POST"
				    , async : true
				    , url : "<%=ONLINE_ROOT %>/mgmt/programDetailAjax.do" 
				    , dataType : "json"
				    , data : {"pgmNo":pgmNo}
				    , success : function(data) {
				    	
				    	// 값 초기화
				    	formReset(); 
						
						// 값이 있는경우 셋팅
						$("#pgmNo").val(data.pgm_no);
						$("#parentPgm").val(data.parent_pgm);
						$("#pgmNumber").val(data.pgm_no);
						$("#pgmId").val(data.pgm_id);
						$("#pgmName").val(data.pgm_name);
						$("#pgmKindCode").val(data.pgm_kind_code).prop("selected", "selected");
						$("#picture").val(data.picture).prop("selected", "selected");
						$("#selectPicture").val(data.select_picture).prop("selected", "selected");
						
						if(data.pgm_use_yn == "Y"){
							$("input:checkbox[id='pgmUseYn']").prop("checked", true);	
						}else{
							$("input:checkbox[id='pgmUseYn']").prop("checked", false);
						}
						 
						if(data.menu_use_yn == "Y"){
							$("input:checkbox[id='menuUseYn']").prop("checked", true); 
						}else{
							$("input:checkbox[id='menuUseYn']").prop("checked", false);
						}
						
						$("#parentPgmNumber").val(data.parent_pgm);
						$("#sortOrder").val(data.sort_order);
						
				    }, complete : function(data) {
				    	buttonList(programNumber);
				    }, error : function(request, status, error) {
					     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
				    }
				    
				});	
			}
		}
		
		/**
	 	 * 왼쪽 메뉴 tree프로그램 선택 후 추가 될 정렬 순서로 셋팅 하는 함수
	 	 */
		function sortSetting(programNumber){
			
			formReset();	// form값을 리셋한다.
			
			$("input:checkbox[id='pgmUseYn']").prop("checked", false);		// 프로그램 사용여부 체크박스 해재
			$("input:checkbox[id='menuUseYn']").prop("checked", false);		// 메뉴 체크박스 해재
			
			addDataRecode = 0;
			$("#btn_list").clearGridData();
			
			var pgmNo = Commons.leadingZeros(programNumber,5);
		
			if(pgmNo != ""){
				$.ajax({
				    type : "POST"
				    , async : true
				    , url : "<%=ONLINE_ROOT %>/mgmt/programSortNumAjax.do" 
				    , dataType : "json"
				    , data : {"pgmNo":pgmNo}
				    , success : function(data) {
						if(data.resultCode > 0){
							$("#sortOrder").val(data.max_sort);
							$("#parentPgmNumber").val(pgmNo);
							$("#parentPgm").val(pgmNo);
						}else{
			    			alert("처리 중 장애가 발생 하였습니다.");
			    		}
				    }, error : function(request, status, error) {
					     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
				    }
				    
				});
			}
		}
		
		/**
	 	 * 버튼 list를 호출하는 jqgrif 함수
	 	 * @param programNumber		프로그램 no
	 	 * @return 					버튼list jqgrid
	 	 */
		function buttonList(programNumber){
			
			var pgmNo = Commons.leadingZeros(programNumber,5);	// 프로그램 no가 숫자 형태로 넘어와 숫자 앞에 0을 채워주는 함수로 결과 값 return받음.
			
			$("#buttonInsertPgmNo").val(pgmNo);		// 프로그램 no셋팅
			
			if(pgmNo != ""){
				// 파라미터 셋팅
				var param = "pgmNo="+pgmNo;
				$("#btn_list").clearGridData(); 	// 이전 그리드 삭제
				$("#btn_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/mgmt/buttonListAjax.do?" + param}).trigger("reloadGrid");
			}
		}
		
		/**
	 	 * FORM RESET : hidden값과 form값을 리셋 해준다.
	 	 */
		function formReset(){
			
			$("#pgmNo").val("");
			$("#parentPgm").val("");
			
			$("form").each(function() {  
				this.reset();  
			});	
		}
		
		/**
	 	 * 버튼 추가,저장,삭제시 호출되는 함수
	 	 * @param buttonType		버튼 type
	 	 * @return resultCode 		결과 값
	 	 */
		function procButton(buttonType){
			
			$("#procButtonType").val(buttonType);	//  버튼 type셋팅
			
			var formData = $("form[name=buttonFrm]").serialize();
			
			$.ajax({
				type : "POST"
				       	, async : true
				    	, url : "<%=ONLINE_ROOT %>/mgmt/procButtonAjax.do"
				    	, dataType : "json"
				    	, data : formData
				    	, success : function(data) {
				    		if(data.resultCode > 0){
				    			if(data.message == "insert"){
				    				if(data.resultCode == 3){
				    					alert("이미 등록된 버튼 ID 입니다.");
				    					return;
				    				}else{
				    					alert("저장 되었습니다.");
				    					$("#rowids").val("");
				    					lastsel2 = "";
				    				}
				    			}else if(data.message == "save"){
				    				alert("저장 되었습니다.");
				    				$("#rowids").val();
				    				lastsel2 = "";
				    			}else{
				    				alert("삭제 되었습니다.");
				    				$("#rowids").val("");
				    				lastsel2 = "";
				    			}
				    		}else{
				    			alert("처리 중 장애가 발생 하였습니다.");
				    		}
				    		
				    		$("#btn_list").trigger("reloadGrid");
				    	}
				    	, error : function(request, status, error) {
					     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
				    	}
				});
		}
		
		/**
	 	 * window resize 함수
	 	 */
		$(window).resize(function(){
			$("#btn_list").setGridWidth($(".botton_list.box_type01").width() , false);
		});
		
		/**
	 	 * window oad시 왼쪽 메뉴 tree와 오른쪽 menu tree를 호출 하는 함수
	 	 */
		$(window).load(function() {
			Commons.treeMenuLeftAjax("ALL");
			Commons.treeMenuRightAjax("IN_USE");
		});
		
		/**
	 	 * 왼쪽 메뉴 tree 호출 함수
	 	 */
		function leftTreeComplete(d){
			treeLeft.innerHTML = d;
		}
		
		/**
	 	 *  오른쪽 menu tree 호출 함수
	 	 */
		function rightTreeComplete(d){
			treeRight.innerHTML = d;
		}
		
	</script>
</head>
<body>
	<div class="inner_cont">
		<div class="wrap_program_admin">
			<div class="float_l mr10">
				<h2>전체 프로그램 메뉴</h2>
					<div class="program_box box_type01">
						<div class="list_button mtn" style="width:92px; margin:10px auto">
							<button class="type_01" onclick="javascript: d.openAll();">펼치기</button>
							<button class="type_01" onclick="javascript: d.closeAll();">접기</button>
						</div>
						<div id="treeLeft" class="mt10"></div>
					</div>
			</div>
			<div class="float_l mr10">
				<div>
					<h2>Program</h2>
					<div class="program_detail box_type01">
					<form name="programFrm" id="programFrm" method="post">
						<input type="hidden" name="pgmNo" id="pgmNo" value="" />
						<input type="hidden" name="parentPgm" id="parentPgm" value="" />
						<input type="hidden" name="procType" id="procType" value="" />
						<table class="type01 re_style">
							<colgroup>
									<col style="width:30%" />
									<col style="width:70%" />
							</colgroup>
							<tbody>
								<tr>
									<th class="no_border_l">PGM NO.</th>
									<td class="no_border_r">
										<input type="text" name="pgmNumber" id="pgmNumber" disabled class="ipt_disable" />
									</td>
								</tr>
								<tr>
									<th class="no_border_l">PGM URL</th>
									<td class="no_border_r">
										<input type="text" name="pgmId" id="pgmId" maxlength="50" />
									</td>
								</tr>
								<tr>
									<th class="no_border_l">PGM Name</th>
									<td class="no_border_r">
										<input type="text" name="pgmName" id="pgmName" maxlength="100" />
									</td>
								</tr>
								<tr>
									<th class="no_border_l">PGM 구분</th>
									<td class="no_border_r">
										<select name="pgmKindCode" id="pgmKindCode">
											<option value="P" selected>Program</option>
											<option value="M">Menu</option>
										</select>
									</td>
								</tr>
								<tr>
									<th class="no_border_l">사용여부</th>
									<td class="no_border_r">
										<input type="checkbox" name="pgmUseYn" id="pgmUseYn" value="Y" />
										<label for="check_pro">프로그램</label>
										<input type="checkbox" name="menuUseYn" id="menuUseYn" type="checkbox" value="Y" />
										<label for="check_menu">메뉴</label>
									</td>
								</tr>
								<tr>
									<th class="no_border_l">상위메뉴</th>
									<td class="no_border_r">
										<input type="text" name="parentPgmNumber" id="parentPgmNumber" disabled class="ipt_disable" />
									</td>
								</tr>
								<tr>
									<th class="no_border_l">정렬순서</th>
									<td class="no_border_r">
										<input type="text" name="sortOrder" id="sortOrder" maxlength="3" />
									</td>
								</tr>
								<tr>
									<th class="no_border_l">아이콘</th>
									<td class="no_border_r">
										<select name="picture" id="picture">
											<option value="tree_icon_window.gif">PROGRAM</option>
											<option value="tree_icon_folder.gif">MENU</option>
										</select>
									</td>
								</tr>
								<tr>
									<th class="no_border_l no_border_b">선택 아이콘</th>
									<td class="no_border_r no_border_b">
										<select name="selectPicture" id="selectPicture">
											<option value="tree_icon_window.gif">PROGRAM</option>
											<option value="tree_icon_folder.gif">MENU</option>
										</select>
									</td>
								</tr>
							</tbody>
						</table>
					</form>
				</div>
				<div class="manager_btn_group">
					<div class="ta_r">
						<%=WebUtil.createButtonArea(currPgmNoByUri, "cm_")%>
					</div>
				</div>	
			</div>			
				<div class="mt8">
						<h2>Button List</h2>
					<div class="botton_list box_type01">
						<form name="buttonFrm" id="buttonFrm">
							<input type="hidden" name="procButtonType" id="procButtonType" value="" />
							<input type="hidden" name="buttonInsertPgmNo" id="buttonInsertPgmNo" value="" />
							<input type="hidden" name="buttonPgmNo" id="buttonPgmNo" value="" />
							<input type="hidden" name="buttonId" id="buttonId" value="" />
							<input type="hidden" name="buttonOriId" id="buttonOriId" value="" />
							<input type="hidden" name="buttonUseYn" id="buttonUseYn" value="" />
							<input type="hidden" name="buttonName" id="buttonName" value="" />
							<input type="hidden" name="rowids" id="rowids" value="" />	
						</form>
						<table id="btn_list"></table>	
					</div>
					<div class="manager_btn_group">
						<div class="ta_r">
							<%=WebUtil.createButtonArea(currPgmNoByUri, "cb_")%>
						</div>
					</div>
				</div>
			</div>
			<div class="float_l">
				<h2>사용 프로그램 미리보기</h2>
				<div class="program_box box_type01">
					<div class="list_button mtn" style="width:92px; margin:10px auto">
							<button class="type_01" onclick="javascript: d2.openAll();">펼치기</button>
							<button class="type_01" onclick="javascript: d2.closeAll();">접기</button>
						</div>
					<div id="treeRight" class="mt10"></div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="/include/footer.jsp"%>
</body>
</html>