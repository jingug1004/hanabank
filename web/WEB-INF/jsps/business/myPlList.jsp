<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : myPlList.jsp    
 * @메뉴명 : 영업관리 > myP/L
 * @최초작성일 : 2014/10/29            
 * @author : 우정아                  
 * @수정내역 : 
--%>
<%@ page import="java.util.List" %>
<%@ page import="com.hanaph.saleon.business.vo.MyPlVO" %>
<%@ include file ="/common/path.jsp" %>
<%
	/* 제품 타입 코드 */
	List<MyPlVO> itemTypeList = (List<MyPlVO>)request.getAttribute("itemTypeList");	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/formCheck.js"></script>
	<script type="text/javascript">
	
	var saleActionFlag = false;		//기능(엑셀, 인쇄) 제어를 위한 전역변수
	
	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		
		/*
		*	엔터키 눌렀을 때 INPUT 태그가 아닐 경우 목록 조회
		*/
		$('body').on('keydown', function(event){
			if(event.keyCode==13){
				if(event.target.nodeName != 'INPUT'){
					getGridList(); 
					return false;
				}
			}
		});
		
		/*
		*	제품명 텍스트박스에서 엔터키 눌렀을 경우 목록3 조회
		*/
		$('#item_nm').on('keydown', function(event){
			if(event.keyCode==13){
				getGrid3List(); 
				return false;
			}
		});

		Commons.setDate('fr_date','to_date');	//주문요청일 오늘날짜로 set
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리. */
	    jsonReader : {
            repeatitems: false;	// json 형태 설정. row 반복.
    	}
		
		/* 오른쪽 그리드. 현재 선택된 myPL그룹에 등록되지 않은 제품 목록 */
		$("#grid_list3").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["제품분류","제품명","제품ID","item_kind"],
			colModel:[
				{name:"item_kind_nm",	index:"item_kind_nm", 	align:"left",		width:100	},		//제품분류명
				{name:"item_nm",		index:"item_nm", 		align:"left",		width:190	},		//제품명
				{name:"item_id",		index:"item_id", 		hidden:true,		key:true	},		//제품id
				{name:"item_kind",		index:"item_kind", 		hidden:true						}		//제품분류코드
			],
			height:460,
			width:328,
			shrinkToFit: false,
			multiselect: true,
			multiboxonly:true
		});
		
		/* 중간 그리드. 현재 선택된 myPL그룹에 등록된 제품 목록. */
		$("#grid_list2").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["정렬순서","제품분류","제품명","제품ID","item_kind"],
			colModel:[
				{name:"sort_seq",		index:"sort_seq", 		align:"center",		width:50,		 formatter:sortSeqFormatter	},		//정렬순서
				{name:"item_kind_nm",	index:"item_kind_nm", 	align:"left",		width:100									},		//제품분류
				{name:"item_nm",		index:"item_nm", 		align:"left",		width:150									},		//제품명
				{name:"item_id",		index:"item_id", 		hidden:true,		key:true									},		//제품id
				{name:"item_kind",		index:"item_kind", 		hidden:true														}		//제품분류코드
			],
			height:460,
			width:328,
			multiselect: true,
			multiboxonly:true,
			onSelectCell: changeModifyForm,
			loadComplete: function(data){	//조회 완료시 호출되는 function
				if(data.records != 0){	//조회결과가 있을 경우
					saleActionFlag=true;
					changeModifyForm(data.rows[0].item_id);		//첫 로우를 수정폼으로 변경
				}
				getGrid3List();
				
			}		
		});
		
		/* 현재 선택된 myPL그룹에 등록된 제품 목록 인쇄 전용 그리드 */
		$("#grid_list2_print").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:[" ","분류","제품명","KD","보험약가","주성분/함량","적응증","용법/용량", "포장단위", "item_id","사진용량"],
			colModel:[
				{name:"item_kind1",		index:"item_kind1", 	align:"center",		width:50, 	formatter:trimPrintFormatter},		
				{name:"item_kind_nm",	index:"item_kind_nm", 	align:"center",		width:50, 	formatter:trimPrintFormatter},	
				{name:"item_nm",		index:"item_nm", 		align:"center",		width:200,	formatter:itemImgPrintFormatter	},
				{name:"item_kd_no",		index:"item_kd_no", 	align:"center",		width:50, 	formatter:trimPrintFormatter},	
				{name:"item_out_danga",		index:"item_out_danga", 	align:"right",		width:50, 	formatter:trimPrintFormatter},		
				{name:"item_main_source_size",		index:"item_main_source_size", 	align:"left",		width:200, 	formatter:trimPrintFormatter},	
				{name:"item_effect",		index:"item_effect", 	align:"left",		width:200, 	formatter:trimPrintFormatter},	
				{name:"item_use_does",		index:"item_use_does", 	align:"left",		width:200, 	formatter:trimPrintFormatter},	
				{name:"item_pojang_unit",		index:"item_pojang_unit", 	align:"center",		width:50, 	formatter:trimPrintFormatter},	
				{name:"item_id",		index:"item_id", 		hidden:true,		key:true									},
				{name:"cnt",		index:"cnt", 		hidden:true									}
			],
			loadComplete: function(data){
				if(data.records != 0){	//조회결과가 있을 경우
					Commons.extraAction(saleActionFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', 'myPlList_print');	//인쇄 팝업 호출
				} else {
					alert('조회 결과가 없습니다.');
				}
			}
		});
		
		/* 왼쪽 그리드. myPL그룹 목록 */
		$("#grid_list").jqGrid({
			url:"<%=ONLINE_ROOT%>/business/myplGroupGridList.do",
			mtype:"get",
			datatype:"json",
			colNames:["PL그룹명","plgrp_nm"],
			colModel:[
				{name:"plgrp_nm",	index:"plgrp_nm", 	align:"left",		width:113	},		//PL그룹명
				{name:"plgrp_no",	index:"plgrp_no", 	hidden:true,		key:true	}		//plgrp_nm
			],
			height:435,
			rownumWidth : 30,
			width:118,
			shrinkToFit: false,
			onSelectRow: selPlGroupRow,
			loadComplete: function(data){		//조회 완료시 호출되는 function
				
				$("#plgrp_no").val("");
				
				if(data.records != 0){			//조회결과가 있을 경우
					$("#plGroupCnt").html("P/L그룹 (총 "+data.records+"건)");
					$("#grid_list").setSelection(data.rows[0].plgrp_no);
					$("#plgrp_no").val(data.rows[0].plgrp_no);
					
				}
				
			}
   		});
		
		/* 조회 버튼 클릭 */
		$("#p_retrieve_btn").on("click",function(){
			getGridList();
		});
		
		/* 인쇄 버튼 클릭 */
		$("#p_print_btn").on("click",function(){
			print();
		});
		
		/* 저장 버튼 클릭 */
		$("#ct_save_btn").on("click",function(){
			insertMyPlList();
		});
		
		
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */ 
    $(window).resize(function(){
		$("#grid_list").setGridWidth($('.box_type01').width(), true);	//grid 영역의 넓이가 동적으로 조절
	});
    
 	/**
 	*	P/L그룹 목록
 	*/
	function getGridList(){
 		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/myplGroupGridList.do"}).trigger("reloadGrid");
	}
    
    /**
    *	P/L그룹 item list 조회
    */
	function getGrid2List(){
		var frm = $("#frm").serialize();
 		$("#grid_list2").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/myPlItemGridList.do?"+frm}).trigger("reloadGrid");
	}
 	
	/**
	*	item list 조회
	*/
	function getGrid3List(){
		var frm = $("#frm").serialize();
		$("#grid_list3").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/itemGridList.do?"+encodeURI(frm)}).trigger("reloadGrid");
	}

	/**
	 * 중간 그리드 정렬순서에 사용된 포매터. 클릭시 입력폼으로 변환되고 포커스아웃될때 div로 변경됨. 
	 * @param cellvalue	해당 컬럼의 값
	 * @param rowObject	해당 row의 데이터
	 * @returns {String}
	 */
	function sortSeqFormatter(cellvalue,rowObject){
		// 사용내역 포맷
		if(typeof(cellvalue)=="undefined"){
			cellvalue="";
		}
		var returnValue = "<div id='sortSeqLayer_"+rowObject.rowId+"' onclick='javascript:changeModifyForm("+rowObject.rowId+",\"sort_seq\");'>"+cellvalue+"&nbsp;</div>";
		returnValue += "<div id='sortSeqInputLayer_"+rowObject.rowId+"' style='display:none;'>";
		returnValue += "<input type='text' name='sort_seq' id='sort_seq_"+rowObject.rowId+"' value='"+cellvalue+"' onKeyDown='if(event.keyCode==13 || event.keyCode==9){moveNextEditCol("+rowObject.rowId+",\"sort_seq\"); return false;}' onblur='javascript:changeModifyForm("+rowObject.rowId+",\"sort_seq\");' />";
		returnValue += "</div>";
		return returnValue;
	}
	
	/**
	 * 다음 수정폼으로 이동
	 * @param prevRowId	이전 로우 id
	 */
	function moveNextEditCol(prevRowId){
		var nextRowId = Commons.jqGridGetNextRowId('grid_list2', prevRowId);	//다음 로우 id 구함
		//$("#sort_seq_"+prevRowId).attr("onblur", "");	//onblur 이벤트 제거
		changeModifyForm(nextRowId);		//다음 로우에 인풋폼 생성 
	}
	
	/**
	 * 다음 수정폼으로 이동
	 * @param rowId	현재 row id
	 * @returns {Boolean}	
	 */
	function changeModifyForm(rowId){
		var divForm = "";
		var textForm = "";
		var inputLayer = "";

		divForm = $("#sortSeqLayer_"+rowId);
		textForm = $("#sort_seq_"+rowId);
		inputLayer = $("#sortSeqInputLayer_"+rowId);
		
		if (divForm.css("display") == "none") { // 입력폼 감추고 영역 보이기  
			
			
			if(formCheck.isNumer($("#sort_seq_"+rowId).val())){
				alert("정렬순서에는 숫자만 입력가능합니다.");
				$("#sort_seq_"+rowId).focus();
				textForm.attr("onblur", "changeModifyForm('"+rowId+"');"); // onblur 이벤트 생성
				return false;
			}
			$("#sortSeqLayer_"+rowId).text(textForm.val() + " "); // value 셋팅
		
			inputLayer.hide(); // 입력폼 감추기
			divForm.show(); // 영역 보이기
		} else { // 영역 감추고 입력폼 보이기
			textForm.width(textForm.parents("td").width() - 1); // input size 변경
			textForm.val($.trim(divForm.text())); // value 셋팅
			textForm.attr("onblur", "changeModifyForm('"+rowId+"');"); // onblur 이벤트 생성
			divForm.hide(); // 영역 감추기
			inputLayer.show(); // 입력폼 보이기
			textForm.focus();
		}
	}
	
	/**
	 *	왼쪽 그리드에서 myPL그룹 선택시 중간 그리드 조회
	 * @param id P/L그룹 ID
	 */
	function selPlGroupRow(id){
		$("#item_kind > option:eq(0)").attr("selected","selected");
		$("#plgrp_no").val(id);
		getGrid2List();
		
	}
	
	/**
	*	myPL 그룹에 소속시키도록 오른쪽 그리드에서 중간 그리드로 이동시키기
	*/
	function myPlListSend(){
		var myPlGrid = $("#grid_list2");
		var itemGrid = $("#grid_list3");
		
		var rowKey = itemGrid.getGridParam("selrow");

        if (!rowKey){
            alert("선택된 제품이 없습니다.");
        	return;
        }else{
        	var itemsize =0;
        	var selectedIDs = itemGrid.getGridParam("selarrrow");
        	itemsize = selectedIDs.length;

        	for (var i = 0; i < itemsize; i++) {
        		//추가하려는 제품 row 정보
            	var addRow = itemGrid.getRowData(selectedIDs[i]);
            	
        		
        		//P/L목록에 추가
            	myPlGrid.addRowData(selectedIDs[i],addRow);
            }
        	
        	for (var j = itemsize-1; j >= 0; j--) {
        		//기존 제품 grid row 삭제
             	itemGrid.delRowData(selectedIDs[j]);
            }
        } 
		
	}
	
	/**
	*	myPL 그룹에서 빼도록 오른쪽 그리드로 이동시키기
	*/
	function itemListSend(){
		
		var myPlGrid = $("#grid_list2");
		var itemGrid = $("#grid_list3");
		
		var rowKey = myPlGrid.getGridParam("selrow");

        if (!rowKey){
            alert("선택된 제품이 없습니다.");
        	return;
        }else{
        	var itemsize =0;
        	var selectedIDs = myPlGrid.getGridParam("selarrrow");
        	itemsize = selectedIDs.length;
        	
        	for (var i = 0; i < selectedIDs.length; i++) {
            	
        		//추가하려는 제품 row 정보
            	var addRow = myPlGrid.getRowData(selectedIDs[i]);
            	//P/L목록에 추가
            	itemGrid.addRowData(selectedIDs[i],addRow);
            }

        	for (var j = itemsize-1; j >= 0; j--) {
        		//기존 제품 grid row 삭제
             	myPlGrid.delRowData(selectedIDs[j]);
            }
        } 
	}
	
	/**
	*	선택된 myPL그룹에 제품들 등록
	*/
	function insertMyPlList(){
		if($("#plgrp_no").val()==""){
			alert("PL그룹을 먼저 선택해야 합니다.\nPL그룹이 없으면 생성하시기 바랍니다.");
			return;
		}
		
		//전체 rowData 가져오기(배열)
		var data = $("#grid_list2").getRowData();
		var itemFrm = $('#itemFrm');
		
		if(itemFrm.find('input').size() > 0){
			$(itemFrm.find('input')).each(function(e){$(this).remove();});
		}
		
		$("<input></input>").attr({type:"hidden", name:"plgrp_no", 		value:$("#plgrp_no").val()}).appendTo(itemFrm);
		
		for(var i=0; i < data.length; i++ ){
			var row = data[i];
			$("<input></input>").attr({type:"hidden", name:"item_id", 		value:row.item_id}).appendTo(itemFrm);
			$("<input></input>").attr({type:"hidden", name:"sort_seq", 		value:$('#sort_seq_'+row.item_id).val()}).appendTo(itemFrm);
		}
		
		$.ajax({
			type:"POST",
			url:"<%=ONLINE_ROOT%>/business/insertMyPlList.do",
			data:$("#itemFrm").serialize(),
			dataType:"json",
			success:function(data){
				if (data.result=="Y") {
					alert("저장하였습니다.");
					 getGrid2List();
				} else {
					alert("저장되지 않았습니다. 다시 시도해 주세요.");
				}
			}
		});
		
	}
	
	/**
	*	P/L그룹 item list 인쇄
	*/
	function print(){
		var frm = $("#frm").serialize();
 		$("#grid_list2_print").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/myPlItemGridList.do?"+frm}).trigger("reloadGrid");
	}
	
	/**
	 * P/L그룹 item list 인쇄용 jqGrid 제품명/사진 Formatter
	 * @param cellvalue	컬럼값
	 * @param options	jqGrid옵션
	 * @param rowObject	row 데이터
	 * @returns
	 */
	function itemImgPrintFormatter(cellvalue, options, rowObject){
		var d = new Date();
		var returnValue = '<img id="item_img" style="width:140px; height:100px; visibility:hidden" src="">';
		if(rowObject.cnt > 0){
			returnValue = '<img id="item_img" style="width:140px; height:100px" src="<%=ONLINE_ROOT %>/business/getPlItemPhoto.do?item_id='+rowObject.item_id+'&amp;dummy='+d.getTime()+'">';
		}
		return returnValue.toString();
	}
	
	/**
	 * P/L그룹 item list 인쇄용 jqGrid trim Formatter
	 * @param cellvalue	컬럼값     
	 * @param options	jqGrid옵션
	 * @param rowObject	row 데이터 
	 * @returns
	 */
	function trimPrintFormatter(cellvalue, options, rowObject){
		var returnValue = $.trim(cellvalue);
		return returnValue.toString();
	}
	</script>
	
</head>
<body>
	<form id="frm" name="frm" method="post">
		<input type="hidden" id="plgrp_no" name="plgrp_no"/>
		<!-- ##### content start ##### -->
		<div class="inner_cont">
			<div class="w967 m0auto">
				<h2>My P/L</h2>
				<p class="mt5">
					자신만의 제품리스트를 그룹별로 출력할 수 있는 화면입니다.
					<!-- 마케팅부, 전산부, admin계정만 버튼을 보여준다. -->
 					<%if(userDeptCd.equals("0138") || userDeptCd.equals("0007") || userEmpCode.equals("admin")){%>
 					<button type="button" class="btn" onclick="javascript:Commons.popupOpen('plItemReg', '<%=ONLINE_ROOT%>/business/common/myPlItemRegPopup.do', '850', '720');">P/L제품등록</button>
 					<%}%>
 					<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
				</p>
				
				<div class="wrap_mypl">
					<div class="box float_l">
						<p class="tit ta_c" id="plGroupCnt">
							P/L그룹 (총00건)
						</p>
						<div class="box_type01 w120 h460">
							<table id="grid_list"></table>
						</div>
						<div class="ta_c mt10">
							<button type="button" class="btn" onclick="javascript:Commons.popupOpen('groupReg', '<%=ONLINE_ROOT%>/business/common/myplGroupPopup.do', '560', '700');">P/L그룹 관리</button>
						</div>
					</div>
					<div class="box float_l ml37">
						<div class="wrap_tit">
							<p class="tit float_l">
								MY P/L 정렬순서 변경시 저장버튼을 사용하세요.
							</p>
							<div class="float_r">
								<%=WebUtil.createButtonArea(currPgmNoByUri, "ct_")%>
							</div>
						</div>
						<div class="box_type01 w330" id="myPlList">
							<table id="grid_list2"></table>
						</div>
					</div>
					<div class="change float_l">
						<button type="button" class="btn right" onclick="javascript:itemListSend();"><span class="blind">오른쪽으로 이동</span></button><br />
						<button type="button" class="btn left" onclick="javascript:myPlListSend();"><span class="blind">왼쪽으로 이동</span></button>
					</div>
					<div class="box float_l">
						<label>제품목록</label>
						<select class="w125" id="item_kind" name="item_kind">
							<option value="">전체</option>
							<% if(itemTypeList!=null){
								for(int i=0;itemTypeList.size()>i;i++){
									MyPlVO myPlVO = new MyPlVO();
									myPlVO=itemTypeList.get(i);
								%>
								<option value="<%=myPlVO.getItem_id()%>"><%=myPlVO.getItem_nm()%></option>
								<%}
							}%>
						</select>
						
						
						<input type="text" class="w95" id="item_nm" name="item_nm"/>
						<input type="text" style="display:none" name="block_submit_dummy"/>
						<button type="button" class="btn ml10" onclick="javascript:getGrid3List()">찾기</button>
						
						<div class="box_type01 w330 h485">
							<table id="grid_list3"></table>
						</div>
					</div>
				</div>
				
				<!-- 인쇄전용 jqGrid -->
				<div id="myPlList_print" style="display:none">
					<table id="grid_list2_print"></table>
				</div>
				
			</div>
		</div>
		<!-- ##### content end ##### -->
	</form>
	<form id="itemFrm"></form>
	<%@include file="/include/footer.jsp"%>
</body>
</html>