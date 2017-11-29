<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : roleProgramPopup.jsp
 * @메뉴명 : Role 프로그램 선택 팝업
 * @최초작성일 : 2015/01/16            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%
	String roleNo = StringUtil.nvl(request.getParameter("roleNo"));		// role no
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="StyleSheet" href="<%=ONLINE_WEB_ROOT%>/css/dtree.css" type="text/css" />
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/dtree.js"></script>	
	<script type="text/javascript" >
		
		/**
		 * 전역 변수 셋팅 부분
		 * roleNo  = role no
		 * param   = url의 get방식으로 넘길 role no
		 * selectedPgmNo		= 선택 된 프로그램 no
		 * selectedUseBtn		= 선택 된 버튼
		 * lastsel2				= 선택 된 row id
		 * selectedPgmNoArray	= 선택 된 프로그램 no를 담기 위한 배열
		 */
		var roleNo = '<%=roleNo%>';		  	
		var param = "?roleNo=<%=roleNo%>";
		var selectedPgmNo = "";
		var selectedUseBtn = "";
		var lastsel2 ="";
		var selectedPgmNoArray = [];
		
		/**
		 * window dom load시 최초 호출되는 jqgrid
		 */
		$(document).ready(function(){
			
			$("#btn_list").jqGrid({
				
				url : "<%=ONLINE_ROOT%>/mgmt/buttonListAjax.do?pgmNo="+selectedPgmNo+"&useYn=Y",
				// 요청방식
				mtype:"post",
				// 결과물 받을 데이터 타입
				datatype:"json",
			
				// 컬럼명
				colNames:["","버튼 ID","버튼명"],
				
				colModel:[	
							{name:"btn_use_yn",	index:"btn_use_yn", width:20, align:"center",	type:"checkbox", formatter: 'checkbox'
							 ,formatoptions:{disabled: false}, editable:true, edittype:"checkbox"
							 ,editoptions:{value:'Y:N' 
									  	   ,dataEvents:[{type:'click', fn: function(e){
									  		programButtonClick();
									  	   }}]
										}  
							},		// 사용 여부
							{name:"btn_id",		index:"btn_id",			align:"left",	width:100, type:"text", formatter: 'textbox'},									// 버튼 ID
							{name:"btn_nm",		index:"btn_nm", 		align:"left",	width:50, type:"text", formatter: 'textbox'}		// 버튼 명
						],
				
				// 선택된 행의 데이터 가져오기.
			    beforeSelectRow: function(rowid, e) {     //사용자가 row를 클릭하는 순간 발생 (return값 false시 선택안한걸로 간주함)
			    	$("#btn_list").jqGrid('setSelection', rowid);
			    },
			    
			 	// 선택된 행의 데이터 가져오기.
				onSelectRow: function(id){
					$('#btn_list').editRow(id,true);
					programButtonClick();
				},
				
				// 그리드 캡션
				height:176,
				rowNum : -1, /* 페이지당 레코드수 초기값 */
				//기본 정렬 값 -> 매개변수이름은 sidx(컬럼), sord(차순)로 요청된다
				sortname :'btn_id',
				sortorder:'asc',
				autowidth:true,
				
				loadComplete: function(data){
					var useBtn = selectedUseBtn;
					if (data.records > 0) {			// 버튼 리스트가 1건이상 존재 하는경우
						for(var idx=0; idx<data.records; idx++){	// 버튼 리스트 갯수 만큼 for
							var trID = idx+1;
							
							if(useBtn.indexOf(data.rows[idx].btn_id) == -1){	// 버튼 리스트에 버튼 string값이 존재 하지 않는 경우
								$("tr#"+trID+" > td >").removeAttr('checked');	// 체크박스 체크 해재
								$("tr#"+trID+" > td >").val('N');				// 체크박스 체크 해재 값
							}else{												// 버튼 리스트에 버튼 string값이 존재 하는 경우
								$("tr#"+trID+" > td >").attr('checked',true);	// 체크박스 체크
								$("tr#"+trID+" > td >").val('Y');				// 체크박스 체크 해재
							}
						}						
					}
				},
			});
			
			$("#roleProgramList").clearGridData(); 	// 이전 그리드 삭제
			$("#roleProgramList").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/mgmt/regPgmListAjax.do" + param}).trigger("reloadGrid");
			$("#roleProgramList").jqGrid({
				
				url : "<%=ONLINE_ROOT%>/mgmt/regPgmListAjax.do?roleNo="+roleNo,
				// 요청방식
				mtype:"post",
				// 결과물 받을 데이터 타입
				datatype:"json",
				// 컬럼명
				colNames:["PGM No.","프로그램 명","프로그램 ID","버튼","Role No","PgmKindCode"],
				colModel:[
							{name:"pgm_no",			index:"pgm_no",				align:"left", width:45, key:true},		// 프로그램 no
							{name:"pgm_name",		index:"pgm_name",			align:"left"},							// 프로그램 명
							{name:"pgm_id",			index:"pgm_id",				align:"left"},							// 프로그램 id
							{name:"use_btn",		index:"use_btn",			align:"left"},							// 버튼 ID
							{name:"role_no",		index:"role_no",			align:"left", hidden:true},				// role no
							{name:"pgm_kind_code",	index:"pgm_kind_code",		align:"left", hidden:true},				// 프로그램 메뉴 구분
				
						 ],
				height:236,
				rowNum: -1,		  			// 페이지 출력 글 수
				sortname :'pgm_no',			//기본 정렬 값 -> 매개변수이름은 sidx(컬럼), sord(차순)로 요청된다
				sortorder:'asc',
				autowidth:true,				// 자동 가로 사이즈
				onSelectRow: function(id){	// 선택된 행의 데이터 가져오기.
				
					if(id && id!==lastsel2){						// 기존 전역 변수와 현재 선택된 행의 번호 가 다를 경우
						
						$('#roleProgramList').restoreRow(lastsel2);	// 선택 한 이전 jqgrid상태를 복원
				    	lastsel2=id;								// 기존전역 변수 현재 선택된 행으로 셋팅
				    	
				    	var selectedList = $("#roleProgramList").getRowData(lastsel2);	// 선택 된 행의 데이터 가져오기
				    	
				    	selectedPgmNo = selectedList.pgm_no;		// 전역변수에 선택된 프로그램 no 셋팅
				    	selectedUseBtn = selectedList.use_btn;		// 전역변수에 선택된 버튼 string 셋팅
				    	
				    	buttonList(selectedPgmNo);					// 버튼 리스트 jqgrid호출
				    	
					}
				
				},
				
				/**
				 * 프로그램 리스트 출력이 완료 된 후 
				 */
				loadComplete: function(data){
					if (data.records > 0) {						// 프로그램 리스트가 1건이상인 경우
						var topPgmNo = data.rows[0].pgm_no;		// 프로그램 no를 버튼의 최초건수 프로그램 no로 셋팅
						selectedUseBtn = data.rows[0].use_btn;	// 버튼 string을 버튼의 최초건수 버튼 string으로 셋팅
						$("#roleProgramList").jqGrid('setSelection', data.rows[0].pgm_no);	// 최상단 건수 선택 된 값으로 셋팅
						buttonList(topPgmNo);					// 버튼 jqgrid호출
					}
				}
			});
			
			/**
			 * window창 크기 조절
			 */
			$(window).resize(function(){
				$("#btn_list").setGridWidth($('.box_type01 w420 h200').width() , false);
			});
			
			/**
			 * 저장 버튼 클릭 : role insert DB insert	
			 * @param jqgrid			getRowData
			 */
			$("#uc_save").bind("click",function(){
				
				var data = "";													// 선택 된 행의 데이터 가져오기
				var dataUseBtn = "";											// 버튼 string 데이터를 담을 변수
				var selectedUseBtnArray = [];									// 버튼 string을 담을 배열
				var selectedPgmNoArrayJoin = selectedPgmNoArray.join("$");		// 프로그램 no를 담은 배열을 $형태로 조인
				var selectedUseBtnArrayJoin = "";								// 버튼 string을 담은 배열의 $조인할 변수
				
				for(var saveIdx=0; saveIdx<selectedPgmNoArray.length; saveIdx++){			// 선택 된 프로그램 배열 만큼 for
					data = $("#roleProgramList").getRowData(selectedPgmNoArray[saveIdx]);	// 선택 된 프로그램의 데이터 가져오기
					dataUseBtn = data.use_btn;				// 버튼 string set
					selectedUseBtnArray.push(dataUseBtn);	// 버튼 string 배열 담기 
				}
				
				selectedUseBtnArrayJoin = selectedUseBtnArray.join("$");	// 버튼 배열 $문자열 합치기
				
				$.ajax({
					type : "POST"
					       	, async : true
					    	, url : "<%=ONLINE_ROOT %>/mgmt/updateRoleProgramAjax.do"
					    	, dataType : "json"
					    	, data : {roleNo:roleNo,pgmNo:selectedPgmNoArrayJoin,useBtn:selectedUseBtnArrayJoin}
					    	, success : function(data) {
					    		if(data.resultCode > 0){
					    			alert("저장 되었습니다.");
									Commons.treeMenuRightAjax("ROLE_MENU",param);	// 저장 후 메뉴 tree호출
									$("#roleProgramList").trigger("reloadGrid");	// jqgrid reload
									opener.reloadRoleProgram();						// 상위 창 프로그램 목록 repload
					    		}
					    }
					    , error : function(request, status, error) {
						     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
					    }
				});
				
			});
			
			/**
			 * 삭제 버튼 클릭 : role insert DB insert	
			 * @param jqgrid			getRowData
			 */
			$("#uc_delete").bind("click",function(){
				var message = "";
				var selectedList = $("#roleProgramList").getRowData(lastsel2);
		    	selectedPgmNo = selectedList.pgm_no;
		    	
		    	if(selectedPgmNo == ""){
		    		alert("삭제 할 행을 선택 해주세요.");
		    		return;
		    	}
		    	
		    	if(selectedPgmNo == "00000"){
		    		message = "최상위 메뉴를 삭제 하시면 메뉴 전체가 삭제 됩니다.\n진행 하시겠습니까?";
		    	}else{
		    		message = "선택된 Role 프로그램을 삭제 하시겠습니까?";
		    	}
		    	
	    		if(confirm(message)){
			    	$.ajax({
						type : "POST"
						       	, async : true
						    	, url : "<%=ONLINE_ROOT %>/mgmt/deleteRoleProgramAjax.do"
						    	, dataType : "json"
						    	, data : {roleNo:roleNo,pgmNo:selectedPgmNo}
						    	, success : function(data) {
						    		if(data.resultCode > 0){
						    			alert("삭제 되었습니다.");
						    			if(selectedPgmNo == "00000"){
						    				//treeRight.innerHTML = "";
						    				$("#treeRight").html("");
						    			}
						    			
										Commons.treeMenuRightAjax("ROLE_MENU",param);
										$("#roleProgramList").trigger("reloadGrid");
										opener.reloadRoleProgram();
						    		}
						    }
						    , error : function(request, status, error) {
							     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
						    }
					});
				}
		    	
			});
				
		});
		
		/**
		 * window load 왼쪽 tree와 오른쪽 메뉴 트리를 호출 하고 팝업 호출 전 부모창의 role title을 가지고 온다.
		 */
		$(window).load(function() {
			Commons.treeMenuLeftAjax("IN_USE");
			Commons.treeMenuRightAjax("ROLE_MENU",param);
			$("#roleTitle").text($("#roleTitle",opener.document).text());
		});
		
		/**
		 * 왼쪽 tree menu 호출
		 * @param jqgrid			getRowData
		 */
		function leftTreeComplete(d){
			treeLeft.innerHTML = d;
		}
		
		/**
		 * 오른쪽 tree menu 호출
		 * @param jqgrid			getRowData
		 */
		function rightTreeComplete(d){
			treeRight.innerHTML = d;
		}
		
		/**
		 * 선택 된 프로그램의 버튼 list
		 * @param jqgrid			getRowData
		 */
		function buttonList(programNumber){
			var pgmNo = Commons.leadingZeros(programNumber,5);
			
			if(pgmNo != ""){
				// 파라미터 셋팅
				var param = "?pgmNo="+pgmNo;
				$("#btn_list").clearGridData(); 	// 이전 그리드 삭제
				$("#btn_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/mgmt/buttonListAjax.do" + param}).trigger("reloadGrid");
			}
		}
		
		/**
		 * 저장 버튼 클릭 : role insert DB insert	
		 * @param jqgrid			getRowData
		 */
		function programButtonClick(){
			
			var rowId = $("#roleProgramList").getGridParam("selrow");		// role no
			var programButton = $("#roleProgramList").getRowData(rowId);	// 선택 된 데이터 가져오기
			var buttonRowId = $("#btn_list").getGridParam("selrow");		// 체크 된 버튼 row id가져오기
			var selectedCheck = $("#btn_list").getRowData(buttonRowId);		// 체크 된 버튼 row id의 데이터 가져오기
			var textValue = programButton.use_btn;							// 선택 된 데이터의 버튼 string 가져오기
			var selectedCheckBtnId = selectedCheck.btn_id;					// 가져온 버튼 string을 변수에 담음
			var selectedBtnUseYn = selectedCheck.btn_use_yn;				// 가져온 버튼 사용여부를 변수에 담음
				
			if(selectedBtnUseYn == "N"){				// 버튼의 사용 여부가 N : 미사용일때
				
				textValue = programButton.use_btn+",";			// 선택된 버튼 string의 뒤에 ,문자열을 붙인다
				selectedCheckBtnId = selectedCheck.btn_id+",";	// 선택된 버튼 id의 뒤에 ,문자열을 붙인다
				
				if(textValue.indexOf(selectedCheckBtnId) > -1){ // 기존 프로그램 버튼 string에 체크가 안된 버튼 string이 있을 경우 
					
					textValue = textValue.replace(selectedCheckBtnId,"");	// 해당 버튼 string을 제외 해준다.
					textValue = textValue.slice(0,-1);						// 마지막 , 삭제
					
					if(textValue == ""){		// textValue 값이 빈 값인 경우
						textValue = " ";		// 공백 값 추가
					}
					
					$("#roleProgramList").setCell(rowId,"use_btn",textValue);
					
				}
			}else{									// 버튼의 사용 여부가 Y : 사용일때
				if(textValue.indexOf(selectedCheckBtnId) == -1){	// 현재 버튼 string에 버튼이 없는 경우
					if(textValue.trim() == ""){						// 버튼 string이 아무 값이 없는 경우
						textValue = selectedCheck.btn_id;			// 버튼 string 추가
					}else{											// 버튼 string에 값이 있는 경우
						textValue = textValue+","+selectedCheck.btn_id;		//  기존 값 + 선택 한 버튼 string 추가
					}
					$("#roleProgramList").setCell(rowId,"use_btn",textValue);
				}
			}
			
			if(programButton.pgm_kind_code == "P"){	// 메뉴 폴더가 아닌경우
				if(selectedPgmNoArray.join().indexOf(programButton.pgm_no) == -1){
					selectedPgmNoArray.push(selectedPgmNo);
				}
	    	}
			
		}
		
		/**
		 * 왼쪽 tree menu의 프로그램 선택 시 오른쪽 tree의 메뉴 추가 기능
		 * @param jqgrid			getRowData
		 */
		function selectedMenu(programNumber,type){
			
			var pgmNo = Commons.leadingZeros(programNumber,5);		// 숫자 형태의 변수를 앞자리 0을 붙여 문자 형태로 변환
			
			if(type=="left"){										// left tree일 경우
				if(confirm("선택 된 메뉴를 추가 하시겠습니까?")){
					$.ajax({
						type : "POST"
						       	, async : true
						    	, url : "<%=ONLINE_ROOT %>/mgmt/insertRoleProgramAjax.do"
						    	, dataType : "json"
						    	, data : {roleNo:roleNo,pgmNo:pgmNo}
						    	, success : function(data) {
						    		if(data.resultCode > 0){
										alert("추가 되었습니다.");
										$("#roleProgramList").trigger("reloadGrid");
										Commons.treeMenuRightAjax("ROLE_MENU",param);
										opener.reloadRoleProgram();
						    		}else{
						    			alert("이미 등록된 프로그램 입니다.");
						    			return;
						    		}
						    		
						    }
						    , error : function(request, status, error) {
							     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
						    }
					});
				}
			}else{
				$("#roleProgramList").jqGrid('setSelection', pgmNo);
			}
		}
		
		/**
		 * 정렬순서를 기본 셋팅하여 주는 함수 
		 * @param programNumber			선택 된 프로그램 no
		 */
		function sortSetting(programNumber){}
		
	</script>
</head>
<body>
	<div class="popup" title="Main">
		<!-- ##### content start ##### -->
		<!-- window size : 1000 * 770 -->
			<h1 class="tit" id="roleTitle"></h1>
			<div class="wrap_program">
				<div class="float_l">
					<h2 class="tit">프로그램 사용 메뉴</h2>
					<div class="box_type01 w245 h550">
						<div class="list_button" style="width:92px; margin:10px auto">
							<button class="type_01" onclick="javascript: d.openAll();">펼치기</button>
							<button class="type_01" onclick="javascript: d.closeAll();">접기</button>
						</div>
						<div id="treeLeft" class="mt10"></div>
					</div>
				</div>
				<div class="float_l ml10">
					<h2 class="tit">Role 등록 프로그램</h2>
					<div class="box_type01 w420 h260">
						<table id="roleProgramList"></table>
					</div>
					
					<h2 class="tit mt30">프로그램 버튼 List</h2>
					<div class="box_type01 w420 h200">
						<table id="btn_list"></table>
					</div>
					<div class="ta_r mt10">
						<button id="uc_delete">삭제</button>
						<button id="uc_save">저장</button>
						<button id="uc_close" onclick="self.close();">닫기</button>
					</div>
				</div>
				<div class="float_l ml10">
					<h2 class="tit">Role 등록 미리보기</h2>
					<div class="box_type01 w245 h550" >
						<div class="list_button" style="width:92px; margin:10px auto">
							<button class="type_01" onclick="javascript: d2.openAll();">펼치기</button>
							<button class="type_01" onclick="javascript: d2.closeAll();">접기</button>
						</div>
						<div id="treeRight" class="mt10"></div>
					</div>
				</div>
			</div>
			
			<button class="close"><span class="blind">닫기</span></button>
		<!-- ##### content end ##### -->
	</div>
	<%@include file="/include/footer_pop.jsp"%>
</body>
</html>