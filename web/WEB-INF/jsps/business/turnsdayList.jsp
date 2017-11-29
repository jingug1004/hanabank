<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : turnsdayList.jsp
 * @메뉴명 : 영업관리 > 회전일현황
 * @최초작성일 : 2014/10/22            
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
	
	/* 전역 변수 */
	var saleActionFlag = false; // 판매현황 부가기능(인쇄, 엑셀다운로드) 사용 가능 여부 flag
	
	$(document).ready(function(){
		
		/* 사원 정보 존재 여부 */
		if (<%=isNull%>) {
			alert("사원정보 자료가 없습니다.");
		}

		/* jqGrid의 jsonReader 옵션 전역 설정. row 반복 형태의 json 처리.  */
	    jsonReader : {
            repeatitems: false;
    	}
		
		
		 
	
		/* 판매현황 grid */
		$("#grid_list").jqGrid({
			mtype:"get",
			datatype:"json",
			colNames:["*일자*", "거래처코드", "거래처명", "매출처코드", "매출처명", "제품코드", "제품명", "규격"],
			colModel:[
						{name:"ymd",		index:"ymd",		align:"center", width:80},
						{name:"cust_id",	index:"cust_id", 	align:"center", width:60},
						{name:"cust_nm",	index:"cust_nm", 	align:"center", width:150},
						{name:"rcust_id",	index:"rcust_id", 	align:"center", width:60},
						{name:"rcust_nm",	index:"rcust_nm", 	align:"center", width:150},
						{name:"item_id",	index:"item_id", 	align:"center", width:50},
						{name:"item_nm",	index:"item_nm", 	align:"left", width:150},
						{name:"standard",	index:"standard", 	align:"left", width:60}
					],
			formatter : {
				integer : {thousandsSeparator: ",", defaultValue: '0'} // 천단위 마다 콤마
	        },
	        rowNum: -1,
			height:484,
			autowidth:true,
			loadonce : true,							//처음만 DB 조회, 그 후에는 그 조회된 테이터로 정렬함. 페이징처리 안 된 그리드에서 사용
			/* 조회 완료 시 호출 */
			loadComplete: function(data){
				if(data.length && data.length > 0){
					$("#grid_list").jqGrid('setGridParam',{rowNum : data.length});
				}
				
				if(!saleActionFlag && data.length && data.length > 0) {
					saleActionFlag = true; // 인쇄, 엑셀 사용 가능하도록
				} else if(!saleActionFlag && (!data.length || data.length < 1)){
					alert("해당 자료가 없습니다.");
					saleActionFlag = false;					
				}
			}
   		});
		
		/* 조회 버튼 클릭 */
		$("#ct_retrieve_btn_type01").on("click",function(){
			getGridList();
		});
		
		/* 엑셀 버튼 클릭 */
		$("#p_excel").on("click",function(){
			Commons.extraAction(saleActionFlag, 'excel', '<%=ONLINE_ROOT%>/business/turnsdayGridListExcelDown.do', '');
		});
		
		/* 닫기 버튼 클릭 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('회전일현황');
		});
		
		/* 조회일자 셋팅(이번 달 1일 ~ 오늘) */
		Commons.setDate("ad_fr_date", "ad_to_date");
		
		/* grid 영역 넓이 셋팅 */
		$("#grid_list").setGridWidth($("#sale").width() , false);
		
		/* 엔터키 눌렸을 경우 조회되도록. */
		$("body").on("keydown", function(event){
			if($("#ct_retrieve_btn_type01").prop('disabled') == false){
				if(event.keyCode==13 && event.target.name!="grid_count"){
					getGridList();
				return false;
				}
			}
		});
	});
	
	/* 브라우저 창의 사이즈가 변경될 때 */
	$(window).resize(function(){
		$("#grid_list").setGridWidth($("#sale").width() , false);
	});
	
	/* 조회 */
	function getGridList(){
		var ad_fr_date = $("#ad_fr_date").val();
		var ad_to_date = $("#ad_to_date").val();
		var as_fr_cust = $("#as_fr_cust").val();
		var as_fr_rcust = $("#as_fr_rcust").val();
		var as_dept_cd = $("#as_dept_cd").val();
		var as_pda_auth = $("#as_pda_auth").val();
		var dc_en_yn = $("#dc_en_yn").val();
		var gc_en_yn = $("#gc_en_yn").val();
		var dw_gb = $("#dw_gb").val();

		
		/* 유효성 체크 */
		if (!formCheck.isDateFormat(ad_fr_date)) {
			alert("올바른 조회 기간을 입력해주세요.");
			$("#ad_fr_date").focus();
			return false;
		}
		
		if (!formCheck.isDateFormat(ad_to_date)) {
			alert("올바른 조회 기간을 입력해주세요.");
			$("#ad_to_date").focus();
			return false;
		}
		
		if (formCheck.dateChk(ad_fr_date, ad_to_date) < 0) {
			alert("조회 기간을 확인하세요!");
			return false;
		}
		
		/* 파라미터 셋팅 */
		var params = {
			ad_fr_date : ad_fr_date,
			ad_to_date : ad_to_date,
			as_fr_cust : as_fr_cust,
			as_fr_rcust : as_fr_rcust,
			as_dept_cd : as_dept_cd,
			as_pda_auth : as_pda_auth,
			dc_en_yn : dc_en_yn,
			gc_en_yn : gc_en_yn,
			dw_gb : dw_gb
		};
		
		var paramStr = $.param(params);
		
		/* 호출 */
		$("#grid_list").jqGrid('clearGridData');
		$("#grid_list").jqGrid('setGridParam',{rowNum : -1});
		$("#grid_list").jqGrid('setGridParam',{loadonce : false});		//조회 버튼 클릭시에는 DB조회되도록
		$("#grid_list").jqGrid('setGridParam',{loadonce : false, mtype:"get", datatype:"json", url:"<%=ONLINE_ROOT%>/business/turnsdayGridList.do?" + paramStr}).trigger("reloadGrid");	//조회 버튼 클릭시 DB조회 되도록
		$("#grid_list").jqGrid('setGridParam',{loadonce : true});			//조회 한 후에는 다시  loadonce를 true로 변경
		saleActionFlag = false;
	}
	
	</script>
</head>

<body>
	<form name="frm" id="frm">
		<input type="hidden" name="as_dept_cd" id="as_dept_cd" value="<%=dept_cd %>" />
		<input type="hidden" name="as_pda_auth" id="as_pda_auth" value="<%=pda_auth %>" />
		<input type="hidden" name="dc_en_yn" id="dc_en_yn" value="%" />
		<input type="hidden" name="gc_en_yn" id="gc_en_yn" value="%" />
		<input type="hidden" name="dw_gb" id="dw_gb" value="2" />
		
		<div class="wrap_search">
			<div class="search">
				<label>조회기간</label>
				<p class="wrap_date">
					<input type="text" maxlength="10" id="ad_fr_date" name="ad_fr_date" />
					<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				~
				<p class="wrap_date">
					<input type="text" maxlength="10" id="ad_to_date" name="ad_to_date" />
					<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				&nbsp;
				<label>거래처</label>
				<input type="text" class="w80" maxlength="10" id="as_fr_cust" name="as_fr_cust" onblur="javascript:Business.getCustomerName(this.id);"/>
				<button type="button" class="btn_search" onclick="javascript:Commons.popupOpen('as_fr_cust', '<%=ONLINE_ROOT%>/business/common/customerList.do', '600', '655');" ><span class="blind">찾기</span></button>
				<input type="text" class="w140 ipt_disable" id="as_fr_cust_name" name="as_fr_cust_name" readonly />
				&nbsp;
				<label>매출처</label>
				<input type="text" class="w80" maxlength="10" id="as_fr_rcust" name="as_fr_rcust" onblur="javascript:Business.getCustomerName(this.id);"/>
				<button type="button" class="btn_search" onclick="javascript:Commons.popupOpen('as_fr_rcust', '<%=ONLINE_ROOT%>/business/common/customerList.do', '600', '655');" ><span class="blind">찾기</span></button>
				<input type="text" class="w140 ipt_disable" id="as_fr_rcust_name" name="as_fr_rcust_name" readonly />
				
				<div class="wrap_search_btn">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "ct_")%>
				</div>
			</div>
		</div>
		
		<div class="wrap_btn_group">
			<div class="btn_group">
				<div class="float_l" style="color:#ee3333">
					※ 회전일 정보는 엑셀출력시에만 보입니다.
				</div>
				<div class="float_r ta_r">
					<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
				</div>
			</div>
		</div>
		
		<div class="inner_cont2">
			<div class="wrap_result_group" id="sale" >
				<table id="grid_list"></table>
			</div>
		</div>		
	</form>
	<%@include file="/include/footer.jsp"%>
</body>
</html>