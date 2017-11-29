<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : userMgmtMain.jsp
 * @메뉴명 : MANAGER > 사용자관리      
 * @최초작성일 : 2015/01/16            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%@ page import="com.hanaph.saleon.mgmt.vo.MgmtRoleVO"%>
<%@ page import="com.hanaph.saleon.common.utils.StringUtil"%>
<%@ page import="java.util.List"%>
<%@ include file ="/common/path.jsp" %>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
<script type="text/javascript">
	
	var dataFlag = false;			// 사용자리스트의 건수가 있는지 없는지 담아두기 위한 변수 셋팅
	var selectedRowid = "";			// 선택된 rowid를 저장하기 위해 셋팅한 변수
	var lastsel = "";				// 선택된 rowid의 데이터를 저장하기 위한 변수
	var oriDataRecode = 0;			// 데이터의 총 건수를 셋팅 하기 위한 변수
	var addDataRecode = 0;			// 추가될 행의 row id를 저장하기 위한 변수
	var selectedEmpNoArray = [];	// 선택될 emp no를 셋팅하기 위한 배열
	var addDataFlag = false;		// 추가 버튼을 눌러 추가 여부를 확인 하기 위한 변수
	var selectedEmpCode = "";		// 선택된 사원코드를 저장하기 위한 변수
	
	/**
	 * window dom load시 최초 호출되는 사용자 목록 jqgrid
	 */
	$(document).ready(function(){
		// 버튼 리스트의 jqgrid부분
		jsonReader : {
		    repeatitems: false;
		}
		
		$("#userList").jqGrid({
			
			url : "<%=ONLINE_ROOT%>/mgmt/userMgmtMain.do",
			// 요청방식
			mtype:"post",
			// 결과물 받을 데이터 타입
			datatype:"json",
		
			// 컬럼명
			colNames:["사용자코드","copyCode","사용자명","비밀번호","사용 ROLE NO"],
			
			colModel:[	
						{name:"emp_code",		index:"emp_code",		align:"left",	width:60,  type:"text", formatter: 'textbox' ,editable: true},		// 사용자코드
						{name:"copy_code",		index:"copy_code", 		align:"left",	hidden:true},	// 사용자 copy코드
						{name:"emp_name",		index:"emp_name",		align:"left",	width:200, type:"text", formatter: 'textbox' ,editable: true, edittype:"text"},		// 사용자명
						{name:"password",		index:"password", 		align:"left",	width:140,  type:"text", formatter: 'textbox' ,editable: true, edittype:"text", editoptions :  Commons.jqgridEditOptions('userList')},
						{name:"role_no",		index:"role_no", 		align:"left",	width:545,  type:"text", formatter: 'textbox'}	// 사용 ROLE NO
					],
			
					
			// 선택된 행의 데이터 가져오기.
		    beforeSelectRow: function(rowid, e) {     //사용자가 row를 클릭하는 순간 발생 (return값 false시 선택안한걸로 간주함)
		    	$("#userList").jqGrid('setSelection', rowid);
		    },
		    
		 	// 선택된 행의 데이터 가져오기.
			onSelectRow: function(id){
				
				$('#userList').editRow(id,true);
				
				if(id && id!==lastsel){
					$('#userList').saveRow(lastsel);
					$('#userList').restoreRow(lastsel);
				}
				
				lastsel=id;
		    	selectedRowid = lastsel;
		    	
		    	var tempEmpNoSelect = $("#userList").getRowData(lastsel);
		    	selectedEmpCode = tempEmpNoSelect.copy_code;
		    	
		    	if(addDataRecode != selectedRowid){
		    		$("tr#"+lastsel+" > td:eq(0)").html("");
			    	$("tr#"+lastsel+" > td:eq(0)").text(selectedEmpCode);	// 셀 선택 시 수정일 경우 사원번호를 수정 못하게 셋팅
		    	}
		    	
		    	selectedEmpNoArray.push(selectedRowid);
		    	
			},
			
			ondblClickRow: function(id){
		    	
		    	if(addDataRecode != selectedRowid){
		    		var selectedList = $("#userList").getRowData(id);
			    	Commons.popupOpen("roleListPopup","<%=ONLINE_ROOT%>/mgmt/common/roleListPopup.do?empCode="+selectedList.emp_code,"530","675");
		    	}
		    	
			},
			
			// 그리드 캡션
			height:436,
			rowNum : -1, /* 페이지당 레코드수 초기값 */
			//autowidth:true,
			
			loadComplete: function(data){
				
				addDataRecode = 0;
				selectedRowid = 0;
				selectedEmpNoArray = [];
				
				if (data.records > 0) {
					dataFlag = true;
					oriDataRecode = data.records;
				}
				
			},
		});
		
		/**
		 * 조회 버튼
		 */
		$("#p_retrieve").on("click",function(){
			goSearch();
		});
		
		/**
		 * 추가 버튼 클릭
		 */ 
		$("#p_insert").on("click",function(){
			
			if(addDataRecode == 0){
				addDataRecode = oriDataRecode + 1;
			}

			if(oriDataRecode+1 == addDataRecode && addDataFlag == false){
				$("#userList").jqGrid('addRow', {
		           rowID : addDataRecode,          		// 중복되지 않게 rowid설정
		           initdata : {emp_code:"", emp_name:"",password:"", role_no :""},
		           position :"last",           			// first, last
		           useDefValues : true,
		           useFormatter : false,
		           addRowParams : {extraparam:{}}
				});
				selectedRowid = addDataRecode;
				addDataFlag = true;
			}
		});
		
		/**
		 * 저장 버튼 클릭
		 */
		$("#p_save").on("click",function(){
			
			var uniqueEmpCodeArray = [];
			
			$.each(selectedEmpNoArray, function(id, el){
				if($.inArray(el, uniqueEmpCodeArray) === -1) uniqueEmpCodeArray.push(el);
			});
			
			if(uniqueEmpCodeArray.length > 0){
				
				var $form = $("#frm");
				var data = "";
				
				for(var idx=0; idx<uniqueEmpCodeArray.length; idx++){
					
					data = $("#userList").getRowData(uniqueEmpCodeArray[idx]);
					
					var empCode = data.emp_code;
					var empName = data.emp_name;
					var password = data.password;
					
					if(idx+1 == uniqueEmpCodeArray.length){
						
 						if(uniqueEmpCodeArray[idx] == addDataRecode){
 							empCode = $("#"+uniqueEmpCodeArray[idx]+"_emp_code").val();	
 						}else{
 							empCode = data.emp_code;	
 						}
						
						empName = $("#"+uniqueEmpCodeArray[idx]+"_emp_name").val();
						password = $("#"+uniqueEmpCodeArray[idx]+"_password").val();
					}
					
					if(empCode != ""){
						$("<input></input>").attr({type:"hidden", name:"empCode", value:$.trim(empCode)}).appendTo($form);
						$("<input></input>").attr({type:"hidden", name:"empName", value:$.trim(empName)}).appendTo($form);
						$("<input></input>").attr({type:"hidden", name:"empPassword", value:$.trim(password)}).appendTo($form);
					}else{
						alert("사원코드가 존재 하지 않습니다.");
					}
					
				}
				
				$.ajax({
					type : "POST"
					       	, async : true
					    	, url : "<%=ONLINE_ROOT %>/mgmt/updateMemberAjax.do"
					    	, dataType : "json"
					    	, data : $form.serialize()
					    	, success : function(data) {
					    		if(data.resultCode > 0){
					    			alert("저장 되었습니다.");
					    		}else{
					    			alert("처리 중 장애가 발생 하였습니다.");
					    		}
					    		dataSet();
					    }
					    , error : function(request, status, error) {
						     alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
					    }
				});
			}
			
		});
		
		/**
		 * 삭제 버튼 클릭
		 */
		$("#p_delete").on("click",function(){
			
			var rowid = selectedRowid;
			
			if(rowid == "" || selectedEmpNoArray.lenght == 0){
				alert("선택 된 사용자가 없습니다.");
				return;
			}
			
			if(rowid == addDataRecode){
				//행삭제
				$("#userList").delRowData(rowid);
				addDataRecode = 0;
				addDataFlag = false;
			}else{
				
				if(selectedEmpNoArray.lenght == 0){
					alert("선택 된 사용자가 없습니다.");
					return;
				}
				
				if(confirm("선택된 사용자를 삭제 하시겠습니까?")){
					$.ajax({
						type : "POST"
						       	, async : true
						    	, url : "<%=ONLINE_ROOT %>/mgmt/deleteMemberAjax.do"
						    	, dataType : "json"
								, data : {empCode:selectedEmpCode}
						, success : function(data) {
							if(data.resultCode > 0){
								alert("삭제 되었습니다.");
							}else{
								alert("처리 중 장애가 발생 하였습니다.");
							}
							dataSet();
						}
						, error : function(request, status, error) {
							if(request.status == '403'){Commons.sessionOut('A');}
						}
					});
				}
			}
		
		});
		
		/**
		 * 인쇄 버튼 클릭
		 */
		$("#p_print").on("click",function(){
			Commons.extraAction(dataFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', 'printArea');
		});
		
		/**
		 * 엑셀 버튼 클릭
		 */
		$("#p_excel").on("click",function(){
			Commons.extraAction(dataFlag, 'excel', '<%=ONLINE_ROOT%>/mgmt/userMgmtMainAjaxExcelDown.do', '');
		});
		
		/**
		 * 닫기 버튼 클릭
		 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('사용자관리');
		});
		
	});
	
	/**
	 * window resize함수
	 */
	$(window).resize(function(){
		$("#userList").setGridWidth($("#printArea").width() , false);
	});
	
	/**
	 * 삭제 버튼 클릭
	 */
	$(window).load(function(){
		$("body").on("keydown", function(event){
			if($("#p_retrieve").prop('disabled') == false){
				if(event.keyCode==13 && event.target.name!="grid_count"){
					goSearch();
				return false;
				}
			}
		});
	});
	
	/**
	 * 조회 버튼 클릭 시 호출되는 사용자 목록 jqgrid함수
	 */
	function goSearch(){
		var searchText = $("#searchText").val();
		var postData = {searchText:searchText};
		$("#userList").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/mgmt/userMgmtMainAjax.do", postData:postData}).trigger("reloadGrid");
	}
	
	/**
	 *  전연변수 배열 값과 전역변수 값을 reset시키는 함수
	 */
	function dataSet(){
		
		dataFlag = false;
		selectedRowid = "";
		lastsel = "";
		oriDataRecode = 0;
		addDataRecode = 0;
		selectedEmpNoArray = [];
		addDataFlag = false;
		selectedEmpCode = "";
		
		$("#userList").trigger("reloadGrid");
		
	}
	
</script>
</head>
<body>
	<form name="frm" id="frm">
		<div class="inner_cont">
			<div class="wrap_btn_group">
				<div class="btn_group ta_r">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
				</div>
			</div>
			<div class="wrap_search02 w967 m0auto">
				<label class="ml10">사용자코드/명</label>
				<input type="text" class="w350" id="searchText" />
			</div>
			<div class="box_type01 h460 w967 m0auto" id="printArea">
				<table id="userList"></table>
			</div>
		</div>
	</form>	
	<%@include file="/include/footer.jsp"%>
</body>
</html>