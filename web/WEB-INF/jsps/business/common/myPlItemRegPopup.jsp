<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : myPlItemRegPopup.jsp
 * @메뉴명 : 영업관리 > myP/L > P/L제품 등록 팝업
 * @최초작성일 : 2014/10/29            
 * @author : 우정아                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%@ page import="java.util.List" %>
<%@ page import="com.hanaph.saleon.business.vo.MyPlVO" %>
<%
	/* 제품 타입 코드 리스트 */
	List<MyPlVO> itemTypeList = (List<MyPlVO>)request.getAttribute("itemTypeList");
%>
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
	
	var proc = "update";	//저장버튼 클릭시 insert, update 구분 flag
	
	var selItemId = '';		//선택한 아이템의 jqgrid id
	
	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리. */
	    jsonReader : {
            repeatitems: false;
    	}
	
		$("#grid_list").jqGrid({
			url:"<%=ONLINE_ROOT%>/business/plItemListGridList.do",
			mtype:"get",
			datatype:"json",
			colNames:["구분1","구분2","제품코드","제품명","KD","보험약가","주성분.함량","적응증","용법.용량","포장단위","글크기_적응증","글크기_용법용량"],
			colModel:[
				{name:"item_kind1",			index:"item_kind1", 		align:"center",		width:52			},		//구분1
				{name:"item_kind_nm",		index:"item_kind_nm", 		align:"center", 	width:100			},		//구분2
				{name:"item_id",			index:"item_id",			align:"center",		width:60,	key:true},		//제품코드
				{name:"item_nm",			index:"item_nm", 			align:"left",		width:100			},		//제품명
				{name:"item_kd_no",			index:"item_kd_no", 		align:"center", 	width:100			},		//KD
				{name:"item_out_danga",		index:"item_out_danga",		align:"right",		width:70			},		//보험약가
				{name:"item_main_source",	index:"item_main_source", 	align:"left",	 	width:100			},		//주성분.함량
				{name:"item_effect",		index:"item_effect", 		align:"left", 	 	width:150			},		//적응증
				{name:"item_use_does",		index:"item_use_does",		align:"left",		width:150			},		//용법.용량
				{name:"item_pojang_unit",	index:"item_pojang_unit", 	align:"center",		width:100			},		//포장단위
				{name:"fontsize_effect",	index:"fontsize_effect", 	align:"center", 	width:100			},		//글크기_적응증
				{name:"fontsize_use_does",	index:"fontsize_use_does",	align:"center",		width:100			}		//글크기용법용량
			
			],
			page: 1,
			rowNum: 20,
			rowList: [20,30,40],
			height:400,
			width:428,
			shrinkToFit: false,
			onSelectRow: selPlItemRow,
			loadComplete: function(data){	//조회 완료시 호출되는 function
				if(data.records != 0){		//조회 결과가 있을 경우
					$('.total').html("결과 총 "+data.records+"건");
					selItemId = selItemId == '' ? data.rows[0].item_id : selItemId;
					$("#grid_list").setSelection(selItemId);
					proc = "update";
				}
				
			},
			pager: "#grid_Pager"
			
   		});
	});
	
	/**
	*	P/L제품 목록 조회
	*/
	function getGridList(){
		var param = "search_item_kind1=" + encodeURI(encodeURIComponent($("#search_item_kind1").val())) +"&search_item_kind2=" + $("#search_item_kind2").val() + "&search_item_nm="+ encodeURI(encodeURIComponent($("#search_item_nm").val()));
		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/plItemListGridList.do?"+param}).trigger("reloadGrid");
	}
	
	/**
	*	제품리스트에서 row 클릭시
	*/
	function selPlItemRow(){
		
		$.ajax({
			type:"POST",
			data: {item_id: $('#grid_list').getGridParam("selrow")},
			url:"<%=ONLINE_ROOT%>/business/plItemInfoAjax.do",
			dataType:"json",
			success:function(data){

				if(data != null){
					proc = "update";
					
					selItemId = data.item_id;
					
					$("#item_kind1").val(data.item_kind1);
					$("#item_kind2").val(data.item_kind2);
					$("#item_id").val(data.item_id);
					$("#orgn_item_id").val(data.item_id);
					$("#item_id_name").val(data.item_nm);
					$("#item_kd_no").val(data.item_kd_no);
					$("#item_out_danga").val(data.item_out_danga);
					$("#item_main_source").val(data.item_main_source);
					$("#item_pojang_unit").val(data.item_pojang_unit);
					$("#item_effect").val(data.item_effect);
					$("#item_use_does").val(data.item_use_does);
					$("#fontsize_effect").val(data.fontsize_effect);
					$("#fontsize_use_does").val(data.fontsize_use_does);
					$("#itemPhoto_name").val('');
					
					fontSizeEdit('fontsize_effect');	
					fontSizeEdit('fontsize_use_does');
					
					$("#photoSaveBtn").removeAttr("disabled");
					$("#photoDelBtn").removeAttr("disabled");
					$("#photoSelectBtn").removeAttr("disabled");
					
					$("#itemPhoto").val("");
					if(data.item_photo.length > 0){
						var d = new Date();
						$("#item_img").attr('src', '<%=ONLINE_ROOT %>/business/getPlItemPhoto.do?item_id=' + data.item_id + '&dummy=' + d.getTime());
					} else {
						$("#item_img").attr('src', '');
					}
					
					$("input:radio[name='use_yn']").removeAttr("checked");
					if(data.use_yn=="Y"){
						$("input:radio[id='use_y']").prop("checked",true);
					}else{
						$("input:radio[id='use_n']").prop("checked",true);
					}
				}
			}
		});
		
	}
	
	
	/**
	*	입력 버튼 클릭시
	*/
	function initForm(){
		
		$('#grid_list').resetSelection();
		
		$("#item_kind1 > option:eq(0)").attr("selected","selected");
		$("#item_kind2").val("");
		$("#item_id").val("");
		$("#orgn_item_id").val("");
		$("#item_id_name").val("");
		$("#item_kd_no").val("");
		$("#item_out_danga").val("");
		$("#item_main_source").val("");
		$("#item_pojang_unit").val("");
		$("#item_effect").val("");
		$("#item_use_does").val("");
		$("#fontsize_effect").val("50");
		$("#fontsize_use_does").val("50");
		$("#itemPhoto_name").val('');
		$("input:radio[id='use_y']").prop("checked",true);
		$("#item_img").attr('src', '');
		$("#photoSaveBtn").attr("disabled","disabled");
		$("#photoSelectBtn").attr("disabled", "disabled");
		$("#photoDelBtn").attr("disabled","disabled");
		
		proc = "insert";
		
	}
	
	
	/**
	*	저장 버튼 클릭시
	*/
	function insertPlItem(){
		
		$("#p_insert").attr("disabled",true);
		
		var item_kind2 = $("#item_kind2")
		if(formCheck.isNull(item_kind2.val())){
			alert("제품구분을 선택해주세요");
			item_kind2.focus();
			$("#p_insert").attr("disabled",false);
			return;
		}
		
		var item_id_name = $("#item_id_name").val();
		if(formCheck.isNull(item_id_name)){
			alert("제품을 선택해주세요");
			$("#p_insert").attr("disabled",false);
			return;
		}
		
		if(proc=="insert"){
			
			selItemId = $("#item_id").val();
			
			$.ajax({
				type:"POST",
				data: $("#frm").serialize(),
				url:"<%=ONLINE_ROOT%>/business/insertPlItem.do",
				dataType:"json",
				success:function(data){
					
					if(data.result=="Y"){
						alert("저장되었습니다.");	
						getGridList(); // 그리드 호출
						
					}else{
						alert("저장되지 않았습니다. 다시 시도하여 주세요.");
					}
					
				},complete:function(){
					getGridList(); // 그리드 호출
					$("#p_insert").attr("disabled",false);
				}
			});
			
		}else if(proc=="update"){
			updatePlItem();
		}
		
	}
	
	
	/**
	*	수정 버튼 클릭시
	*/
	function updatePlItem(){
		
		$("#p_insert").attr("disabled",true);
		
		if(!$('#grid_list').getGridParam("selrow")){
			alert("수정할 제품을 선택해주세요.");
			$("#p_insert").attr("disabled",false);
			return;
		}
		
		var item_id_name = $("#item_id_name").val();
		if(formCheck.isNull(item_id_name)){
			alert("제품을 선택해주세요");
			$("#p_insert").attr("disabled",false);
			return;
		}
		
		$.ajax({
			type:"POST",
			data: $("#frm").serialize(),
			url:"<%=ONLINE_ROOT%>/business/updatePlItem.do",
			dataType:"json",
			success:function(data){
				
				if(data.result=="Y"){
					alert("수정되었습니다.");	
					opener.getGridList();
				}else{
					alert("수정되지 않았습니다. 다시 시도하여 주세요.");
				}
				
			},complete:function(){
				getGridList(); // 그리드 호출
				$("#p_insert").attr("disabled",false);
			}
		});
		
	}
	
	/**
	*	삭제 버튼 클릭시
	*/
	function deletePlItem(){
		
		if(!$('#grid_list').getGridParam("selrow")){
			alert("삭제할 제품을 선택해주세요.");
			return;
		}else{
			if(confirm("제품을 삭제하시겠습니까?")){
	 			$.ajax({
					type:"POST",
					data: {item_id: $('#item_id').val()},
					url:"<%=ONLINE_ROOT%>/business/deletePlItem.do",
					dataType:"json",
					success:function(data){
						if(data.result=="Y"){
							alert("삭제되었습니다.");	
						}else{
							alert("삭제되지 않았습니다. 다시 시도하여 주세요.");
						}
						
					},complete:function(){
						getGridList();
						selItemId = '';
					}
				});
			}
		}
	}
	
	/**
	*	사진저장 버튼 클릭시
	*/
	function itemPhotoSave(){
		
		$("#photoSaveBtn").attr("disabled","disabled");
		$("#photoSelectBtn").attr("disabled","disabled");
		
		if($("#itemPhoto").val()!= ""){
			
			var frm = document.frm;
			frm.target = "ifrm";
			frm.action = "<%=ONLINE_ROOT%>/business/itemUploadPhoto.do";
			frm.submit();
		}else{
			$("#photoSaveBtn").removeAttr("disabled");
			$("#photoSelectBtn").removeAttr("disabled");
			alert("사진을 선택해 주세요.");
		}
		
		
	}
	
	
	/**
	*	사진삭제 버튼 클릭시
	*/
	function itemPhotoDelete(){
		
		
		if($('#item_img').length > 0 ){
			if(confirm("사진을 삭제하시겠습니까?")){
	 			$.ajax({
					type:"POST",
					data: {item_id: $('#item_id').val()},
					url:"<%=ONLINE_ROOT%>/business/itemDeletePhoto.do",
					dataType:"json",
					success:function(data){
						if(data.result=="Y"){
							alert("삭제되었습니다.");	
							$("#item_img").attr('src', '');
						}else{
							alert("삭제되지 않았습니다. 다시 시도하여 주세요.");
						}
						
					},complete:function(){
						getGridList();
					}
				});
			}
		}else{
			alert("제품 사진이 존재하지 않습니다.");
			return;
		}
	}
	
	
	/**
	 *	사진 저장이 완료되었을 때 호출되는 function	
	 * @param result	사진 저장 결과값
	 */
	function callbackPhoto(result){

		$("#photoSaveBtn").removeAttr("disabled");
		$("#photoSelectBtn").removeAttr("disabled");
		
		if(result==1){
			alert("사진이 저장되었습니다.");
			getGridList();
		}else if(result==3){
			alert("사진은 300Kbyte 이하만 첨부 가능합니다.");
		}else{
			alert("등록되지 않았습니다. 다시 시도하여 주세요.");
		}
	}
	
	/**
	 *	출력글자크기 숫자에 따른 폰트크기 변화
	 * @param id	출력글자크기 숫자가 입력된 element id
	 */
	function fontSizeEdit(id){

		var num = parseInt($("#"+id).val());
		if(id=="fontsize_effect"){
			$("#item_effect").css("font-size", (num+70)+"%");
		}else if(id=="fontsize_use_does"){
			$("#item_use_does").css("font-size", (num+70)+"%");
		}
		
	}
	
	/**
	 * 화살표 up, down 클릭시 출력글자크기 변화
	 * @param id	출력글자크기 숫자가 입력된 element id
	 * @param type	up : 크기 키우기, down : 크기 작게
	 */
	function fontNumEdit(id, type){
		
		var num = parseInt($("#"+id).val());
		
		if(type=="up"){
			if(num <50)
			$("#"+id).val(num+1);
		}else if(type=="down"){
			if(num>30)
			$("#"+id).val(num-1);
		}
		fontSizeEdit(id);
	}
	
	/**
	 * 사진 선택시 사진의 파일명만 추출해서 파일명 element에 보여주기
	 * @param input	function을 호출하는 element 자신.
	 */
	function photoNameInput(input) {
		var fName = $("#itemPhoto").val().split("\\");
		$("#itemPhoto_name").val(fName[fName.length-1]);
	}
	
	</script>
</head>


<body>
	
		<div class="popup" >
		
		<!-- ##### content start ##### -->
		<!-- window size : 800 * 675 -->
			<h1 class="tit">P/L용 제품리스트 관리</h1>
			
			<div class="wrap_pop_search">
				<div class="float_l">
					<label>구분1</label>
					<select class="w100" id="search_item_kind1" name="search_item_kind1">
						<option value="">전체</option>
						<option>전문</option>
						<option>일반</option>
					</select>
					
					<label class="ml10">구분2</label>
					<select class="w100" id="search_item_kind2" name="search_item_kind2">
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
					
					<label class="ml10">제품명</label>
					<input type="text" class="w150" id="search_item_nm" name="search_item_nm"/>
					
				</div>
				
				<div class="float_r">
					<button type="button" onclick="javascript:getGridList();">조회</button>
				</div>
			</div>
			<div class="ta_r mt10">
				<button type="button" onclick="javascript:initForm();">입력</button>
				<button type="button" id="p_insert" onclick="javascript:insertPlItem();">저장</button>
				<button type="button" onclick="javascript:deletePlItem();">삭제</button>
			</div>
			<p class="total">결과 총 00건</p>
			<div class="wrap_pl_list">
				<div class="box_type01 float_l w430 h451">
					<table id="grid_list"></table>
					<div id="grid_Pager"></div>
				</div>
				<form id="frm" name="frm" method="post" enctype="multipart/form-data">
				<div class="float_r wrap_tbl">
					<div class="box_type01">
						<table class="type01">
							<tr>
								<th class="no_border_t no_border_l">분류</th>
								<td class="no_border_t no_border_r">
									<select class="w80" id="item_kind1" name="item_kind1">
										<option>전문</option>
										<option>일반</option>
									</select>
									<select class="w100" id="item_kind2" name="item_kind2">
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
								</td>
							</tr>
							<tr>
								<th class="no_border_l">제품</th>
								<td class="no_border_r">
									<input type="hidden" id="item_id" name="item_id"  />
									<input type="hidden" id="orgn_item_id" name="orgn_item_id"/>
									<input type="text" id="item_id_name" name="item_id_name" class="w150" />
									<button type="button" onclick="javascript:Commons.popupOpen('item_id', '<%=ONLINE_ROOT%>/business/common/itemListPopup.do', '600', '655');">찾기</button>
								</td>
							</tr>
							<tr>
								<th class="no_border_l">사진</th>
								<td class="no_border_r">
									<div class="wrap_photo">
										<img id="item_img" src="" />
										<div class="wrap_photo_btn" id="photoBtnDiv">
											<%/*
											기존 파워빌더 로직
											 - 사진파일명을 제품코드로 만들어야 하고, 해당 사진이 있는 폴더를 선택해서 이미지보기로 이미지를 확인 후 저장. 
											WEB 적용 로직
											 - WEB에서는 해당 사진을 선택한 후 저장. 그래서 사진폴더, 사진보기, 도움말 버튼이 필요없음
											*/%> 
											<button type="button" id="photoSaveBtn" onclick="javascript:itemPhotoSave();" >사진저장</button><br />
											<button type="button" id="photoDelBtn" onclick="javascript:itemPhotoDelete();">사진삭제</button>
										</div>
									
									</div>
								</td>
							</tr>
							<tr>
								<td colspan="2" class="no_border_l no_border_r no_border_b pdrn">
									<input type="file" id="itemPhoto" name="itemPhoto" class="blind" onchange="photoNameInput(this)" />
									<input type="text" id="itemPhoto_name" name="itemPhoto_name" class="w200" />
									<button type="button" id="photoSelectBtn" class="pl_photo_btn">사진선택</button>
								</td>
							</tr>
						</table>
					</div>
					<div class="box_type01">
						<table class="type01">
							<colgroup>
								<col width="25%"/>
								<col width="25%"/>
								<col width="25%"/>
								<col width="25%"/>
							</colgroup>
							<tr>
								<th class="no_border_t no_border_l">KD</th>
								<th class="no_border_t">보험약가</th>
								<th class="no_border_t">주성분·함량</th>
								<th class="no_border_t no_border_r">포장단위</th>
							</tr>
							<tr>
								<td class="no_border_b no_border_l">
									<textarea class="w68 ta_l" id="item_kd_no" name="item_kd_no"></textarea>
								</td>
								<td class="no_border_b">
									<textarea class="w68 ta_l" id="item_out_danga" name="item_out_danga"></textarea>
								</td>
								<td class="no_border_b">
									<textarea class="w68 ta_l" id="item_main_source" name="item_main_source"></textarea>
								</td>
								<td class="no_border_b no_border_r">
									<textarea class="w68 ta_l" id="item_pojang_unit" name="item_pojang_unit"></textarea>
								</td>
							</tr>
						</table>
					</div>
					<div class="wrap_pl_box">
						<div class="box_type01 float_l">
							<table class="type01">
								<colgroup>
									<col style="width:78px;" />
									<col style="width:;" />
									<col style="width:21px;" />
								</colgroup>
								<tr>
									<th colspan="3" class="no_border_t no_border_l no_border_r">적응중</th>
								</tr>
								<tr>
									<td colspan="3" class="no_border_l no_border_r">
										<textarea class="w143 h50" id="item_effect" name="item_effect"></textarea>
									</td>
								</tr>
								<tr>
									<th class="no_border_b no_border_l">출력글자크기</th>
									<td class="no_border_b">
										<input type="text" class="w25" id="fontsize_effect" name="fontsize_effect" onchange="fontSizeEdit('fontsize_effect');"/>
									</td>
									<td class="no_border_b no_border_r pd0">
										<button type="button" class="btn_up" onclick="javascript:fontNumEdit('fontsize_effect','up')"><span class="blind"></span></button><br />
										<button type="button" class="btn_down" onclick="javascript:fontNumEdit('fontsize_effect','down')"><span class="blind"></span></button>
									</td>
								</tr>
							</table>
						</div>
						<div class="box_type01 float_r ml10">
							<table class="type01">
								<colgroup>
									<col style="width:78px;" />
									<col style="width:;" />
									<col style="width:21px;" />
								</colgroup>
								<tr>
									<th colspan="3" class="no_border_t no_border_l no_border_r">용법·용량</th>
								</tr>
								<tr>
									<td colspan="3" class="no_border_l no_border_r">
										<textarea class="w143 h50" id="item_use_does" name="item_use_does"></textarea>
									</td>
								</tr>
								<tr>
									<th class="no_border_b no_border_l">출력글자크기</th>
									<td class="no_border_b">
										<input type="text" class="w25" id="fontsize_use_does" name="fontsize_use_does" onchange="fontSizeEdit('fontsize_use_does');"/>
									</td>
									<td class="no_border_b no_border_r pd0">
										<button type="button" class="btn_up" onclick="javascript:fontNumEdit('fontsize_use_does','up')"><span class="blind"></span></button><br />
										<button type="button" class="btn_down" onclick="javascript:fontNumEdit('fontsize_use_does','down')"><span class="blind"></span></button>
									</td>
								</tr>
							</table>
						</div>
					</div>
					
					<table class="type01 mt10">
						<tr>
							<th>사용여부</th>
							<td>
								<input type="radio" id="use_y" name="use_yn" value="Y"/>
								<label>사용함</label>
								<input type="radio" id="use_n" name="use_yn" value="N"/>
								<label>사용안함</label>
							</td>
						</tr>
					</table>
					
				</div>
				</form>
			</div>

			<button class="close"><span class="blind">닫기</span></button>
		
		<!-- ##### content end ##### -->
	
		</div>
	<iframe id="ifrm" name="ifrm" style="display:none;"></iframe>
	<%@ include file ="/include/footer_pop.jsp" %>
</body>

</html>
