<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : roleDetailPopup.jsp      
 * @메뉴명 : Role 상세 팝업      
 * @최초작성일 : 2015/01/15            
 * @author : 김재갑                  
 * @수정내역 : 
--%>
<%@ page import="com.hanaph.saleon.mgmt.vo.MgmtRoleVO"%>
<%@ page import="com.hanaph.saleon.common.utils.StringUtil" %>
<%@ include file ="/common/path.jsp" %>
<%
	MgmtRoleVO roleDetail = (MgmtRoleVO)request.getAttribute("roleDetail");		// Role상세정보를 담은 VO
	String pgmNo = StringUtil.nvl(request.getParameter("pgmNo"));				// 프로그램 no
	String roleNo = StringUtil.nvl(request.getParameter("roleNo"));				// role no
	String useBtn = "";															// 버튼 string 변수 셋팅
	
	// Role상세정보가 있는 경우 버튼 string 변수에 셋팅 해준다. 
	if(roleDetail !=null){									
		useBtn = StringUtil.nvl(roleDetail.getUse_btn());	
	}
	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript">
		
		/**
		 * window dom load시 최초 호출되는 jqgrid
		 */
		$(document).ready(function(){
			// 버튼 리스트의 jqgrid부분
			jsonReader : {
			    repeatitems: false;
			}
			
			$("#btn_list").jqGrid({
				
				url : "<%=ONLINE_ROOT%>/mgmt/buttonListAjax.do?pgmNo=<%=pgmNo%>&useYn=Y",
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
							{name:"btn_id",		index:"btn_id",			align:"left",	width:100, type:"text", formatter: 'textbox'},		// 버튼 id								// 버튼 ID
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
				sortname :'btn_id',
				sortorder:'asc',
				autowidth:true,
				
				/**
				 * 버튼 jqgrid가 호출 되고 실행 되는 함수 	
				 * @param data
				 */
				loadComplete: function(data){
					var useBtn = "<%=useBtn%>";
					if (data.records > 0) {										// 버튼 list가 1건이라도 있는 경우
						for(var idx=0; idx<data.records; idx++){				// 버튼 list만큼 for
							var trID = idx+1;									// idx가 0부터 시작 하므로 +1
							if(useBtn.indexOf(data.rows[idx].btn_id) == -1){  	// 버튼 string 문자열 중 버튼 list가 없는 경우
								$("tr#"+trID+" > td >").removeAttr('checked');	// 버튼 list에 체크 박스를 해제
								$("tr#"+trID+" > td >").val('N');				// 버튼 list 체크 박스 N값 설정
							}else{												// 버튼 string 문자열 중 버튼 list가 있는 경우
								$("tr#"+trID+" > td >").attr('checked',true);	// 버튼 list에 체크 박스를 설정
								$("tr#"+trID+" > td >").val('Y');				// 버튼 list 체크 박스 Y값 설정
							}
							
						}						
					}
				},
			});
			
			/**
			 * 저장 버튼 클릭 : 버튼 권한 textarea 데이터를 DB UPDATE	
			 * @param pgmNo			프로그램 no
			 * @param roleNo		role no
			 * @param useBtn		버튼권한 textarea
			 * @returns int			resultCode
			 */
			$("#uc_save").bind("click",function(){
				
				var pgmNo = $("#pgmNo").val();		// 프로그램 no
				var roleNo = $("#roleNo").val();	// role no
				var useBtn = $("#useBtn").val();	// 버튼권한 textarea
				
				$.ajax({
					type : "POST"
					       	, async : true
					    	, url : "<%=ONLINE_ROOT %>/mgmt/updateRoleDetailAjax.do"
					    	, dataType : "json"
							, data : {pgmNo:pgmNo,roleNo:roleNo,useBtn:useBtn}
					, success : function(data) {
						if(data.resultCode > 0){
							alert("저장되었습니다.");
							opener.reloadRoleProgram();
						}else{
			    			alert("처리 중 장애가 발생 하였습니다.");
			    		}
						self.close();
					}
					, error : function(request, status, error) {
						alert("code : " + request.status + "\r\nmessage : " + request.reponseText);
					}
				});
			});
			
		});
		
	 	/**
		 * window창 크기 조절
		 */
		$(window).resize(function(){
			$("#deptList").setGridWidth($('.box_type01 h200').width() , false);
			
		});
		
		/**
		 * 프로그램 버튼 List 행 선택 : 선택 된 행의 id 데이터를 읽어 	버튼 권한 textarea에 등록.
		 * @param $("#btn_list").getGridParam("selrow")		선택 된 행의 데이터
		 */
		function programButtonClick(){
			var rowId = $("#btn_list").getGridParam("selrow");  // 선택 된 행의 데이터
			var selectSel = "";
			
	  		if(rowId && rowId!==selectSel){						
				selectSel=rowId;
		    	var selectedCheck = $("#btn_list").getRowData(selectSel);		// 선택 된 행의 데이터
				var textareaValue = $("textarea#useBtn").val();					// 버튼 string 값
				var selectedCheckBtnId = selectedCheck.btn_id;					// 선택 된 버튼 id
				var selectedBtnUseYn = selectedCheck.btn_use_yn;				// 선택 된 버튼 사용 여부
				
				if(selectedBtnUseYn == "N"){									// 버튼 사용여부가 N인경우
					textareaValue = $("textarea#useBtn").val()+",";				// 버튼 string 값 + 문자열 ,
					selectedCheckBtnId = selectedCheck.btn_id+",";				// 선택 된 버튼 ID + ,
					
					if(textareaValue.indexOf(selectedCheckBtnId) > -1){			// 	버튼 string 값이 버튼 id에 있는 경우
						textareaValue = textareaValue.replace(selectedCheckBtnId,"");	 // replace하여 버튼 str을 지워 준다
						textareaValue = textareaValue.slice(0,-1);					     // 마지막 , 값 삭제
						
						$("textarea#useBtn").val(textareaValue);				// textarea 값 셋팅
						$("#textUsebtn").text(textareaValue);					// text 영역에 값 셋팅
					}
				}else{
					if(textareaValue.indexOf(selectedCheckBtnId) == -1){		// 버튼 사용여부가 Y인 경우
						if(textareaValue == ""){								// textareaValue값이 없는 경우
							$("textarea#useBtn").val(selectedCheck.btn_id);			// textarea 값 셋팅
							$("#textUsebtn").text(selectedCheck.btn_id);			// text 영역에 값 셋팅
						}else{														// textareaValue값이 있는 경우
							$("textarea#useBtn").val(textareaValue+","+selectedCheck.btn_id);	// textarea영역에 기존값 + ,문자열 + 선택 된 값 셋팅
							$("#textUsebtn").text(textareaValue+","+selectedCheck.btn_id);		// text영역에 기존값 + ,문자열 + 선택 된 값 셋팅
						}
					}
				}
			}
		}
        
	</script>
</head>
<body>
<form name="frm" id="frm">
	<input id="pgmNo" name="pgmNo" type="hidden" value="<%=pgmNo%>">
	<input id="roleNo" name="roleNo" type="hidden" value="<%=roleNo%>">
	<div class="popup" title="Main">
		
		<!-- ##### content start ##### -->
		<!-- window size : 520 * 747 -->
			<h1 class="tit">Role 프로그램 선택</h1>
			
			<div class="wrap_program_role mt30">
				<h2 class="tit">Role 등록 프로그램</h2>
				<div class="box_type01">
					<table class="type02">
						<colgroup>
							<col style="width:150px;" />
							<col />
						</colgroup>
						<tr>
							<th class="no_border_t no_border_l">PGM NO.</th>
							<td class="no_border_t no_border_r"><%=StringUtil.nvl(roleDetail.getPgm_no()) %></td>
						</tr>
						<tr>
							<th class="no_border_l">프로그램 명</th>
							<td class="no_border_r"><%=StringUtil.nvl(roleDetail.getPgm_name()) %></td>
						</tr>
						<tr>
							<th class="no_border_l" rowspan="2">프로그램 URL</th>
							<td class="no_border_r"><%=StringUtil.nvl(roleDetail.getPgm_id()) %></td>
						</tr>
						<tr>
							<td class="no_border_r" id="textUsebtn"><%=StringUtil.nvl(roleDetail.getUse_btn()) %></td>
						</tr>
						<tr>
							<th class="no_border_b no_border_l">버튼 권한</th>
							<td class="no_border_b no_border_r">
								<textarea id="useBtn" disabled="disabled" class="w340 h100"><%=StringUtil.nvl(roleDetail.getUse_btn()) %></textarea>
							</td>
						</tr>
					</table>
				</div>
				
				<h2 class="tit mt30">프로그램 버튼 List</h2>
				<div class="box_type01 h200">
					<table id="btn_list"></table>
				</div>
				
				<div class="ta_r mt10">
					<button type="button" id="uc_save">저장</button>
					<button type="button" id="uc_close" onclick="self.close();">닫기</button>
				</div>
				
				<button class="close"><span class="blind">닫기</span></button>
				
			</div>
		<!-- ##### content end ##### -->
		<%@include file="/include/footer_pop.jsp"%>
	</div>
</form>
</body>
</html>