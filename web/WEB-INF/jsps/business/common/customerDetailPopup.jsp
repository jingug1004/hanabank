<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : partListPopup.jsp
 * @메뉴명 : 영업관리 > 파트 검색 팝업
 * @최초작성일 : 2014/12/19          
 * @author : 윤범진                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%@ page import="com.hanaph.saleon.business.vo.CustomerInfoVO" %>
<%@ page import="com.hanaph.saleon.common.utils.StringUtil" %>
<%
	/* 거래처 상세 정보 */
	CustomerInfoVO customerDetail = new CustomerInfoVO();
	customerDetail = (CustomerInfoVO)request.getAttribute("customerDetail");
	
	String cust_gb1 = StringUtil.nvl(customerDetail.getCust_gb1()); // 거래처 구분
	String uptae = StringUtil.nvl(customerDetail.getUptae()); // 업태
	String tel = StringUtil.nvl(customerDetail.getTel()); // 전화번호
	String cust_id = StringUtil.nvl(customerDetail.getCust_id()); // 거래처 코드
	String jongmok = StringUtil.nvl(customerDetail.getJongmok()); // 종목
	String hp = StringUtil.nvl(customerDetail.getHp()); // 핸드폰 번호
	String cust_nm = StringUtil.nvl(customerDetail.getCust_nm()); // 거래처명
	String open_date = StringUtil.nvl(customerDetail.getOpen_date()); // 개업일
	String fax = StringUtil.nvl(customerDetail.getFax()); // 팩스번호
	String president = StringUtil.nvl(customerDetail.getPresident()); // 대표자명
	String start_ymd = StringUtil.nvl(customerDetail.getStart_ymd()); // 거래개시일
	String room_cnt = StringUtil.nvl(customerDetail.getRoom_cnt()); // 병실수
	String bupin_no = StringUtil.nvl(customerDetail.getBupin_no()); // 법인번호
	String submit_date = StringUtil.nvl(customerDetail.getSubmit_date()); // 결제일
	String bedno = StringUtil.nvl(customerDetail.getBedno()); // bed 수
	String vou_no = StringUtil.nvl(customerDetail.getVou_no()); // 사업자번호
	String use_ymd = StringUtil.nvl(customerDetail.getUse_ymd()); // 중지일지
	String email = StringUtil.nvl(customerDetail.getEmail()); // email
	String zip = StringUtil.nvl(customerDetail.getZip()); // 우편번호
	String addr1 = StringUtil.nvl(customerDetail.getAddr1()); // 주소
	String addr2 = StringUtil.nvl(customerDetail.getAddr2()); // 상세주소
	
%>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/formCheck.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/business.js"></script>
	<script type="text/javascript">
	
	var insertDataArr = []; // 기타사항 등록용 배열
	var updateDataArr = []; // 기타사항 수정용 배열
	var lastsel = ""; // 마지막 선택한 row id
	
	$(document).ready(function(){
		
		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리.  */
	    jsonReader : {
            repeatitems: false;
    	}
	
		/* 파라미터 셋팅 */
		var params = {
			cust_id : "<%=cust_id%>",
		};
		
		var paramStr = $.param(params);
		
		/* 거래처 기타사항 목록 jqgrid */
		$("#grid_list").jqGrid({
			url: ONLINE_ROOT + "/business/customerDetailEtcGridList.do?" + paramStr,
			editurl: ONLINE_ROOT + "/business/customerDetailEtcGridList.do?" + paramStr,
			mtype:"get",
			datatype:"json",
			colNames:["기타사항","일련번호"],
			colModel:[
						{name:"gita",	index:"gita",	align:"left", editable:true, edittype:"text", editoptions:Commons.jqgridEditOptions("grid_list", "Y")},
						{name:"seq",	index:"seq",	hidden:true}
					],

			height:149,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			autowidth:true,
			
			/* 조회 완료 시 호출 */
			onSelectRow: function(id){
				if(id){
					/* 수정폼 제어 */
					$("#grid_list").jqGrid("saveRow",lastsel); // 수정폼 저장
					$("#grid_list").jqGrid("restoreRow",lastsel); // 수정폼 되돌리기
					$("#grid_list").jqGrid("editRow",id,true); // 수정폼으로 변환
					lastsel=id;
					
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
			
			pager: "#grid_Pager"
   		});
		
		/* 엔터키 눌렸을 경우 조회되도록. */
		$("body").on("keydown", function(event){
			if(event.keyCode==13 && event.target.name!="grid_count" && event.target.name!="gita"){
				getGridList();
				return false;
			}
		});
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
	$(window).resize(function(){
		$("#grid_list").setGridWidth($("#result").width() , true);
	});
	
	//* 거래처 기타사항 목록 조회 */
	function getGridList(){
		/* 폼 및 배열 초기화 */
		$("#gitaFrm").html(""); // grid 데이터 폼
		insertDataArr = [];
		updateDataArr = [];
		
		/* 파라미터 셋팅 */
		var params = {
			cust_id : $("#cust_id").val()
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/business/customerDetailEtcGridList.do?" + paramStr}).trigger("reloadGrid");
	}
	
	/* 저장폼 셋팅 */
	function setInsertForm(){
		var targetRowId = $("#grid_list").getGridParam("reccount") + 1; // 총 row 수 + 1을 id로 한다.
		var data = {gita:""}; // value 셋팅
		
		$("#grid_list").addRowData(targetRowId, data); // row 추가
		
		Business.addArray(insertDataArr, targetRowId); // insert 배열에 현재 id 셋팅
	}
	
	/* 저장 */
	function saveCustomerDetail(){
		var frm = $("#frm"); // 전체 폼
		var gitaFrm = $("#gitaFrm"); // 기타사항 데이터 용 div
		
		/* 기타사항 수정폼 복구 */
		$("#grid_list").jqGrid("saveRow",lastsel);
		$("#grid_list").jqGrid("restoreRow",lastsel);
		
		/* 유효성 체크 */
		if (formCheck.isNumDash($("#tel").val())) { // 전화번호
			alert("전화번호는 숫자 혹은 - 만 입력 가능합니다.");
			$("#tel").focus();
			return;
		}
		
		if (formCheck.isNumDash($("#hp").val())) { // 핸드폰번호
			alert("핸드폰번호는 숫자 혹은 - 만 입력 가능합니다.");
			$("#hp").focus();
			return;
		}
		
		if (!formCheck.isNull($("#open_date").val()) && !formCheck.isDateFormat($("#open_date").val())) { // 개업일
			alert("올바른 날짜를 입력해주세요.");
			$("#open_date").focus();
			return false;
		}
		
		if (formCheck.isNumDash($("#fax").val())) { // 팩스번호
			alert("팩스번호는 숫자 혹은 - 만 입력 가능합니다.");
			$("#fax").focus();
			return;
		}
		
		if (formCheck.getByteLength($("#room_cnt").val()) > 20) { // 병실수
			alert("병실수는 한글 10자 혹은 영문 20자 이내로 입력해주세요.(20byte)");
			$("#room_cnt").focus();
			return;
		}
		
		if (!formCheck.isNull($("#submit_date").val()) && !formCheck.isDateFormat($("#submit_date").val())) { // 결재일
			alert("올바른 날짜를 입력해주세요.");
			$("#submit_date").focus();
			return false;
		}
		
		if (formCheck.isNumer($("#bedno").val())) { // BED수
			alert("BED수는 숫자만 입력 가능합니다.");
			$("#bedno").focus();
			return;
		}
		
		if (!formCheck.isNull($("#email").val()) && !formCheck.isValidEmail($("#email").val())) { // 메일주소
			alert("올바른 메일주소가 아닙니다.");
			$("#email").focus();
			return;
		}
		
		/* grid 영역 */
		for (var i = 0; i < $("#grid_list").getGridParam("reccount"); i++) {
			var ret = $("#grid_list").getRowData((i+1));
				
			var gita = ret.gita;
			console.log(formCheck.getByteLength(gita));
			if (formCheck.getByteLength(gita) > 1000) { // 기타사항
				alert("기타사항은 한글 500자 혹은 영문 1000자 이내로 입력해주세요.(1000byte)");
				$("#grid_list").jqGrid("editRow",(i+1),true);
				return;
			}
		}
		
		
		/* 기타사항 등록 */
		for (var i = 0; i < insertDataArr.length; i++) { // form에 data 동적 생성
			var ret = $("#grid_list").getRowData(insertDataArr[i]);
			$("<input></input>").attr({type:"hidden", name:"insert_gita", value: ret.gita}).appendTo(gitaFrm);
		}
		
		/* 기타사항 수정 */
		for (var i = 0; i < updateDataArr.length; i++) { // form에 data 동적 생성
			var ret = $("#grid_list").getRowData(updateDataArr[i]);
			$("<input></input>").attr({type:"hidden", name:"update_gita", value: ret.gita}).appendTo(gitaFrm);
			$("<input></input>").attr({type:"hidden", name:"update_seq", value: ret.seq}).appendTo(gitaFrm);
		}
		
		/* 저장 프로세스 */
		$.ajax({
			type:"POST",
			data: frm.serialize(),
			url:"<%=ONLINE_ROOT%>/business/procCustomerDetail.do",
			dataType:"json",
			success:function(data){
				if (data.resultCount > 0 && "Y" == data.message) {
					alert("완료되었습니다.");
				} else {
					alert("실패");
					
				}
			},complete:function(){
				getGridList(); // 그리드 호출
			}
		});
	}
	
	/* 기타사항 삭제 */
	function deleteCustomerDetailEtc(){
		if (insertDataArr.length > 0) { // insert 배열에 값이 있을 경우
			alert("저장이 완료되지 않은 기타사항이 있습니다.");
			return false;
		}
		
		/* 현재 선택한 row의 정보 반환 */
		var id = $("#grid_list").jqGrid("getGridParam", "selrow");
		var ret = $("#grid_list").getRowData(id);
		
		/* 파라미터 셋팅 */
		var params = {
			cust_id : $("#cust_id").val(),
			seq : ret.seq
		};
		
		var paramStr = $.param(params);
		
		/* 삭제 프로세스 */
		$.ajax({
			type:"POST",
			data: paramStr,
			url:"<%=ONLINE_ROOT%>/business/deleteCustomerDetailEtc.do",
			dataType:"json",
			success:function(data){
				if (data.resultCount > 0) {
					alert("완료되었습니다.");
				} else {
					alert("실패");
					
				}
			},complete:function(){
				getGridList(); // 그리드 호출
			}
		});
	}
	
	</script>
</head>


<body>
	<div class="popup">
		<h1 class="tit">거래처 기타사항</h1>
			<form name="frm" id="frm">
				<input type="hidden" name="cust_id" id="cust_id" value="<%=cust_id %>" />
				<div id="gitaFrm"></div>
				
				<div class="box_type01 etc_customer">
					<table class="type01 ta_l">
						<colgroup>
							<col style="width:175px;" />
							<col />
							<col style="width:175px;" />
							<col />
							<col style="width:175px;" />
							<col />
						</colgroup>
						<tr>
							<th class="no_border_l no_border_t">구분</th>
							<td class="no_border_t"><input type="text" id="cust_gb1" name="cust_gb1" value="<%=cust_gb1 %>" readonly class="ipt_disable" /></td>
							<th class="no_border_t">업태</th>
							<td class="no_border_t"><input type="text" id="uptae" name="uptae" value="<%=uptae %>" readonly class="ipt_disable" /></td>
							<th class="no_border_t">전화번호</th>
							<td class="no_border_r no_border_t"><input type="text" id="tel" name="tel" maxlength="20" value="<%=tel %>"/></td>
						</tr>
						<tr>
							<th class="no_border_l">거래처 코드</th>
							<td><input type="text" id="cust_id" name="cust_id" value="<%=cust_id %>" readonly class="ipt_disable" /></td>
							<th>종목</th>
							<td><input type="text" id="jongmok" name="jongmok" value="<%=jongmok %>" readonly class="ipt_disable" /></td>
							<th>핸드폰번호</th>
							<td class="no_border_r"><input type="text" id="hp" name="hp" maxlength="15" value="<%=hp %>" /></td>
						</tr>
						<tr>
							<th class="no_border_l">거래처명</th>
							<td><input type="text" id="cust_nm" name="cust_nm" value="<%=cust_nm %>" readonly class="ipt_disable" /></td>
							<th>개업일</th>
							<td class="date_search">
								<div class="date_position posi2">
									<input type="text" id="open_date" name="open_date" maxlength="10" value="<%=open_date %>" />
									<button type="button" class="btn_date">달력보기</button>
								</div>
							</td>
							<th>팩스번호</th>
							<td class="no_border_r"><input type="text" id="fax" name="fax" maxlength="30" value="<%=fax %>" /></td>
						</tr>
						<tr>
							<th class="no_border_l">대표자</th>
							<td><input type="text" id="president" name="president" value="<%=president %>" readonly class="ipt_disable" /></td>
							<th>거래개시일</th>
							<td><input type="text" id="start_ymd" name="start_ymd" value="<%=start_ymd %>" readonly class="ipt_disable" /></td>
							<th>병실수</th>
							<td class="no_border_r"><input type="text" id="room_cnt" name="room_cnt" maxlength="20" value="<%=room_cnt %>" /></td>
						</tr>
						<tr>
							<th class="no_border_l">주민(법인)번호</th>
							<td><input type="text" id="bupin_no" name="bupin_no" value="<%=bupin_no %>" readonly class="ipt_disable" /></td>
							<th>결재일</th>
							<td class="date_search">
								<div class="date_position posi2">
									<input type="text" id="submit_date" name="submit_date" maxlength="10" value="<%=submit_date %>" />
									<button type="button" class="btn_date">날짜찾기</button>
								</div>
							</td>
							<th>BED수</th>
							<td class="no_border_r"><input type="text" id="bedno" name="bedno" value="<%=bedno %>" maxlength="20" /></td>
						</tr>
						<tr>
							<th class="no_border_l">사업자번호</th>
							<td><input type="text" id="vou_no" name="vou_no" value="<%=vou_no %>" readonly class="ipt_disable" /></td>
							<th>중지일자</th>
							<td><input type="text" id="use_ymd" name="use_ymd" value="<%=use_ymd %>" readonly class="ipt_disable" /></td>
							<th>이메일주소</th>
							<td class="no_border_r"><input type="text" id="email" name="email" maxlength="30" value="<%=email %>" /></td>
						</tr>
						<tr>
							<th class="no_border_l no_border_b">우편번호</th>
							<td class="no_border_b"><input type="text" id="zip" name="zip" value="<%=zip %>" readonly class="ipt_disable"/></td>
							<th class="no_border_b">주소</th>
							<td class="no_border_b"><input type="text" id="addr1" name="addr1" value="<%=addr1 %>" readonly class="ipt_disable" /></td>
							<th class="no_border_b">상세주소</th>
							<td class="no_border_r no_border_b"><input type="text" id="addr2" name="addr2" value="<%=addr2 %>" readonly class="ipt_disable" /></td>
						</tr>
					</table>
				</div>
			</form>
			<div class="box_type01 h200 mt30" id="customerDetail">
				<table id="grid_list"></table>
				<div id="grid_Pager" class="customerDetail"></div>
			</div>
			
			<div class="mt10 ta_r">
				<button type="button" onclick="javascript:getGridList();">조회</button>
				<button type="button" onclick="javascript:setInsertForm();">입력</button>
				<button type="button" onclick="javascript:saveCustomerDetail();">저장</button>
				<button type="button" onclick="javascript:deleteCustomerDetailEtc();">삭제</button>
				<button disabled="disabled">인쇄</button>
				<button disabled="disabled">엑셀</button>
				<button type="button" onclick="javascript:self.close();">닫기</button>
			</div>
			
			<button class="close"><span class="blind">닫기</span></button>
	</div>
	
	<%@include file="/include/footer_pop.jsp"%>
</body>

</html>
