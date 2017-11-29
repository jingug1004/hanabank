<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : myplGroupPopup.jsp
 * @메뉴명 : 영업관리 > myP/L > P/L그룹 관리 팝업
 * @최초작성일 : 2014/10/29            
 * @author : 우정아                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/business.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/formCheck.js"></script>
	<script type="text/javascript">
	
	var rowIdArray = [];		//P/L그룹 배열
	
	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리. */
	    jsonReader : {
            repeatitems: false;
    	}
		
		/* P/L그룹 목록 */
		$("#grid_list").jqGrid({
			url:"<%=ONLINE_ROOT%>/business/myplGroupGridList.do",
			mtype:"get",
			datatype:"json",
			colNames:["PL그룹명","설명","정렬순서","plgrp_nm"],
			colModel:[
				{name:"plgrp_nm",	index:"plgrp_nm", 	align:"left",		width:120,		formatter:plgrpNmFormatter},		//PL그룹명
				{name:"comments",	index:"comments", 	align:"center", 	width:200,		formatter:commentsFormatter},		//설명
				{name:"sort_seq",	index:"sort_seq",	align:"center",		width:70,		formatter:sortSeqFormatter},		//정렬순서
				{name:"plgrp_no",	index:"plgrp_no", 	hidden:true,		key:true	}		//plgrp_nm
			],
			height:415,
			rownumWidth : 30,
			autowidth:true,
			loadComplete: function(data){	//조회 완료시 호출되는 function
				if(data.records != 0){		//조회 결과가 있을 경우
					$('.total').html("결과 총 "+data.records+"건");
					changeModifyForm(data.rows[0].plgrp_no, "plgrp_nm");		//첫 로우를 수정폼으로 변경
				}
				
			}
			
   		});
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
	$(window).resize(function(){
		$("#grid_list").setGridWidth($(".box_type01").width() , true);
	});
	
	/**
	*	P/L그룹 목록 조회
	*/
	function getGridList(){
		// 호출
		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/myplGroupGridList.do"}).trigger("reloadGrid");
	}
	
	/**
	 * 	추가 버튼 클릭시
	 */
	function insertPlGroup(){
		
		$("#p_insert").attr("disabled",true);
		
		if($("#plgrp_nm").val()==''){
			alert("PL그룹명을 먼저 입력해주십시오.");
			$("#p_insert").attr("disabled",false);
			$("#plgrp_nm").focus();
			return;
		}
		
		if (formCheck.getByteLength($("#plgrp_nm").val())>100){
			alert('PL그룹명은 한글 50자, 영문 100자로 입력해주세요(100byte)');
			$("#p_insert").attr("disabled",false);
			$("#plgrp_nm").focus();
			return;
		}
		
		if (formCheck.getByteLength($("#comments").val())>100){
			alert('설명은 한글 50자, 영문 100자로 입력해주세요(100byte)');
			$("#p_insert").attr("disabled",false);
			$("#comments").focus();
			return;
		}
		
		if(formCheck.isNumer($("#sort_seq").val())){
			alert("정렬순서에는 숫자만 입력가능합니다.");
			$("#sort_seq").focus();
			$("#p_insert").attr("disabled",false);
			return;
		}
		
		$.ajax({
			type:"POST",
			async: false,
			url:"<%=ONLINE_ROOT%>/business/insertPlGroupAjax.do",
			data:$("#frm").serialize(),
			dataType:"json",
			success:function(data){
				
				if(data.result=="Y"){
					//입력한값 empty
					$('#plgrp_nm').val("");
					$('#comments').val("");
					$('#sort_seq').val("");
					
					alert("저장하였습니다.");
					opener.getGridList();
				}else{
					alert("저장되지 않았습니다. 다시 시도하여 주세요.");
				}
				
			},complete:function(){
				//추가버튼 다시 사용
				$("#p_insert").attr("disabled",false);
				//grid reload
				getGridList();
			}
		});		
		
	}
	
	/**
	 * P/L그룹 목록 그리드. P/L그룹명 컬럼 클릭시 수정폼으로 변경
	 * @param cellvalue	현재 컬럼 값
	 * @param rowObject	현재 row 데이터
	 * @returns {String}	수정폼으로 변경하게 하는 html
	 */
	function plgrpNmFormatter(cellvalue, rowObject){
		var returnValue = "<div id='plgrpNmLayer_"+rowObject.rowId+"' onclick='javascript:changeModifyForm("+rowObject.rowId+",\"plgrp_nm\");'>"+cellvalue+"&nbsp;</div>";
		returnValue += "<div id='plgrpNmInputLayer_"+rowObject.rowId+"' style='display:none;'>";
		returnValue += "<input type='text' name='plgrp_nm' id='plgrp_nm_"+rowObject.rowId+"' value='"+cellvalue+"' onKeyDown='if(event.keyCode==13 || event.keyCode==9){removeOnblur("+rowObject.rowId+",\"plgrp_nm\");  return false;}' onblur='javascript:changeModifyForm("+rowObject.rowId+",\"plgrp_nm\");' />";
		returnValue += "</div>";
		
		return returnValue;
	}
	
	/**
	 *	P/L그룹 목록 그리드. P/L그룹 코멘트 컬럼 클릭시 수정폼으로 변경
	 * @param cellvalue	현재 컬럼 값
	 * @param rowObject	현재 row 데이터
	 * @returns {String}		수정폼으로 변경하게 하는 html
	 */
	function commentsFormatter(cellvalue, rowObject){
		var returnValue = "<div id='commentsLayer_"+rowObject.rowId+"' onclick='javascript:changeModifyForm("+rowObject.rowId+",\"comments\");'>"+cellvalue+"&nbsp;</div>";
		returnValue += "<div id='commentsInputLayer_"+rowObject.rowId+"' style='display:none;'>";
		returnValue += "<input type='text' name='comments' id='comments_"+rowObject.rowId+"' value='"+cellvalue+"' onKeyDown='if(event.keyCode==13 || event.keyCode==9){removeOnblur("+rowObject.rowId+",\"comments\");  return false;} ' onblur='javascript:changeModifyForm("+rowObject.rowId+",\"comments\");' />";
		returnValue += "</div>";
		
		return returnValue;
	}
	
	/**
	 *	P/L그룹 목록 그리드. P/L그룹 정렬순서 컬럼 클릭시 수정폼으로 변경
	 * @param cellvalue	현재 컬럼 값
	 * @param rowObject	현재 row 데이터
	 * @returns {String}	수정폼으로 변경하게 하는 html
	 */
	function sortSeqFormatter(cellvalue, rowObject){
		// 사용내역 포맷
		var returnValue = "<div id='sortSeqLayer_"+rowObject.rowId+"' onclick='javascript:changeModifyForm("+rowObject.rowId+",\"sort_seq\");'>"+cellvalue+"&nbsp;</div>";
		returnValue += "<div id='sortSeqInputLayer_"+rowObject.rowId+"' style='display:none;'>";
		returnValue += "<input type='text' name='sort_seq' id='sort_seq_"+rowObject.rowId+"' value='"+cellvalue+"' onKeyDown='if(event.keyCode==13 || event.keyCode==9){removeOnblur("+rowObject.rowId+",\"sort_seq\");  return false;}' onblur='javascript:changeModifyForm("+rowObject.rowId+",\"sort_seq\");' />";
		returnValue += "</div>";
		
		return returnValue;
	}
	
	/**
	 * 탭/엔터 입력 시 onblur 이벤트 제거 및 다음 수정폼으로 이동
	 * @param rowId	현재 row id
	 * @param type	컬럼 type
	 */
	function removeOnblur(rowId, type){
		
		if ("plgrp_nm" == type) {
			//$("#plgrp_nm_"+rowId).attr("onblur", "");
			/**
			 * @param rowId
			 * @param type
			 * @returns {Boolean}
			 */
			type = "comments";
		} else if ("comments" == type) {
			//$("#comments_"+rowId).attr("onblur", "");
			type = "sort_seq";
		} else if ("sort_seq" == type) {
			//$("#sort_seq_"+rowId).attr("onblur", "");
			rowId = Commons.jqGridGetNextRowId('grid_list', rowId);	//다음 로우 id 구함
			type = "plgrp_nm";
		}
		
		changeModifyForm(rowId, type);
	}
	
	
	/**
	*	text 입력 폼 변경
	*/
	function changeModifyForm(rowId, type){
		var divForm = "";
		var textForm = "";
		var inputLayer = "";
		
		
		if ("plgrp_nm" == type) { // type에 따른 target 설정
			divForm = $("#plgrpNmLayer_"+rowId);
			textForm = $("#plgrp_nm_"+rowId);
			inputLayer = $("#plgrpNmInputLayer_"+rowId);
		} else if ("comments" == type) {
			divForm = $("#commentsLayer_"+rowId);
			textForm = $("#comments_"+rowId);
			inputLayer = $("#commentsInputLayer_"+rowId);
		} else if ("sort_seq" == type) {
			divForm = $("#sortSeqLayer_"+rowId);
			textForm = $("#sort_seq_"+rowId);
			inputLayer = $("#sortSeqInputLayer_"+rowId);
		}
		
		if (divForm.css("display") == "none") { // 입력폼 감추고 영역 보이기  
			
			if ("plgrp_nm" == type) {
				if (formCheck.getByteLength($("#plgrp_nm_"+rowId).val())>100){
					alert('PL그룹명은 한글 50자, 영문 100자로 입력해주세요(100byte)');
					$("#plgrp_nm_"+rowId).focus();
					textForm.attr("onblur", "changeModifyForm('"+rowId+"', '"+type+"');"); // onblur 이벤트 생성
					return;
				}
				$("#plgrpNmLayer_"+rowId).text(textForm.val() + " "); // value 셋팅
			} else if ("comments" == type) {
				if (formCheck.getByteLength($("#comments_"+rowId).val())>100){
					alert('설명은 한글 50자, 영문 100자로 입력해주세요(100byte)');
					$("#comments_"+rowId).focus();
					textForm.attr("onblur", "changeModifyForm('"+rowId+"', '"+type+"');"); // onblur 이벤트 생성
					return;
				}
				$("#commentsLayer_"+rowId).text(textForm.val() + " "); // value 셋팅
			} else if ("sort_seq" == type) {
				if(formCheck.isNumer($("#sort_seq_"+rowId).val())){
					alert("정렬순서에는 숫자만 입력가능합니다.");
					$("#sort_seq_"+rowId).focus();
					textForm.attr("onblur", "changeModifyForm('"+rowId+"', '"+type+"');"); // onblur 이벤트 생성
					return false;
				}
				$("#sortSeqLayer_"+rowId).text(textForm.val() + " "); // value 셋팅
			}
			addRowIdArray(rowId); // 배열에 rowId 담기
			inputLayer.hide(); // 입력폼 감추기
			divForm.show(); // 영역 보이기
			
		} else { // 영역 감추고 입력폼 보이기
			textForm.width(textForm.parents("td").width() - 1); // input size 변경
			textForm.val($.trim(divForm.text())); // value 셋팅
			textForm.attr("onblur", "changeModifyForm('"+rowId+"', '"+type+"');"); // onblur 이벤트 생성
			divForm.hide(); // 영역 감추기
			inputLayer.show(); // 입력폼 보이기
			textForm.focus();
		}
	}
	
	
	/**
	 *	배열에 요소를 담는다.
	 * @param rowId	현재 row id
	 */
	function addRowIdArray(rowId){
		
		// 배열에 같은 값 존재여부 검색
		var isExist = false; // flag
		for (var i = 0; i < rowIdArray.length; i++) { // rowId 위치 검색
			if (rowIdArray[i] == rowId) {
				isExist = true;
				break;
			}
		}
		
		if (!isExist) { // 없을 경우
			rowIdArray.push(rowId); // 배열에 현재 rowId 더함	
		}
	}
	
	/**
	 * 수정 버튼 클릭시
	 */
	function updatePlGroup(){
		
		if (rowIdArray.length > 0) { // 배열에 담긴 rowId를 기준으로 data들을 form에 담아 넘긴다
			var frm = $("#updateFrm");
			
			for (var i = 0; i < rowIdArray.length; i++) { // form에 data 동적 생성
				$("<input></input>").attr({type:"hidden", name:"plgrp_no", value: rowIdArray[i]}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"plgrp_nm", value: $("#plgrp_nm_" + rowIdArray[i]).val()}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"comments", value: $("#comments_" + rowIdArray[i]).val()}).appendTo(frm);
				$("<input></input>").attr({type:"hidden", name:"sort_seq", value: $("#sort_seq_" + rowIdArray[i]).val()}).appendTo(frm);
			}
			
			$.ajax({
				type:"POST",
				data: frm.serialize(),
				url:"<%=ONLINE_ROOT%>/business/updatePlGroup.do",
				dataType:"json",
				success:function(data){
					
					if(data.result=="Y"){
						alert("수정되었습니다.");	
						opener.getGridList();
					}else{
						alert("수정되지 않았습니다. 다시 시도하여 주세요.");
					}
					
				},complete:function(){
					rowIdArray = []; // 배열 초기화
					frm.html(""); // 폼 초기화
					getGridList(); // 그리드 호출
				}
			});
			
		} else {
			alert("변경 사항이 없습니다.");
		}
		
	}
	
	/**
	 * 	삭제 버튼 클릭시
	 */
	function deletePlGroup(){
		
		if(!$('#grid_list').getGridParam("selrow")){
			alert("삭제할 P/L그룹을 선택해주세요.");
			return;
		}else{
			$.ajax({
				type:"POST",
				data: {plgrp_no: $('#grid_list').getGridParam("selrow")},
				url:"<%=ONLINE_ROOT%>/business/deletePlGroup.do",
				dataType:"json",
				success:function(data){
					if(data.result=="Y"){
						alert("삭제되었습니다.");	
					}else{
						alert("삭제되지 않았습니다. 다시 시도하여 주세요.");
					}
					
				},complete:function(){
					getGridList();
				}
			});
			
		}
		
	}
	
	</script>
</head>


<body onKeyDown="if(event.keyCode==13 && event.target.name!='grid_count' && event.target.name!='plgrp_nm' && event.target.name!='comments' && event.target.name!='sort_seq'){getGridList();return false;}">
	
		<div class="popup">
		
		<!-- ##### content start ##### -->
		<!-- window size : 560 * 700 -->
			<h1 class="tit">출력 제품리스트<span class="subtitle">출력할 제품리스트의 그룹을 만들어주십시오.</span></h1>
			<form id="frm" name="frm">
				<div class="wrap_pop_search">
					<label>P/L그룹 추가</label>
					<input type="text" class="w100" placeholder="그룹명" id="plgrp_nm" name="pl_nm" />
					<input type="text" class="w120" placeholder="그룹설명" id="comments" name="pl_comments" />
					<input type="text" class="w120" placeholder="정렬순서" id="sort_seq" name="pl_sort_seq" />
					<div class="wrap_pop_search_btn">
						<button type="button" id="p_insert" onclick="javascript:insertPlGroup();">추가</button>
	
					</div>
				</div>
			</form>			
			<div class="ta_r mt10">
				<button type="button" onclick="javascript:getGridList();">조회</button>
				<button type="button" onclick="javascript:updatePlGroup();">수정</button>
				<button type="button" onclick="javascript:deletePlGroup();">삭제</button>
			</div>
			
			<p class="total">결과 총 00건</p>
			<div class="box_type01 h440">
				<table id="grid_list"></table>
			</div>
			
			<button class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
		</div>
	
	<form id="updateFrm" name="updateFrm" ></form>
	<%@ include file ="/include/footer_pop.jsp" %>
</body>

</html>
