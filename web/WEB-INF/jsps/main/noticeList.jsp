<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : noticeList.jsp
 * @메뉴명 : 공지사항 목록
 * @최초작성일 : 2014/10/29            
 * @author : 장일영                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="/include/head.jsp"%>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/formCheck.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/business.js"></script>
	<script type="text/javascript">
	
	/* 화면의 Dom객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		
		/*
		*	엔터키를 눌렀을 경우 공지사항 조회
		*/
		$('body').on('keydown', function(e){
			if(event.keyCode==13){
				getNoticeListAjax();
			}
		});
		
		/*
		*	검색 버튼을 클릭했을 경우 공지사항 조회
		*/
		$('#btnSearch').on('click', function(e){
			getNoticeListAjax();
		});
		
		/*
		*	조회 기간 설정
		*/
		Commons.setDate("sttDate", "endDate");
		
		/*
		*	공지사랑 리스트 jqGrid 초기화
		*/
		$("#grid_notice_list").jqGrid({
			mtype:"post",
			datatype:"json",
			colNames:["제목","작성일시","작성자", "notice_id"],
			colModel:[
						{name:"notice_title",	index:"notice_title", 	align:"left"},
						{name:"input_date",	index:"input_date", 	align:"center",	width:"50px"},
						{name:"sawon_nm",	index:"sawon_nm", 	align:"center",	width:"30px"},
						{name:"notice_id",	index:"notice_id",	hidden:true}
					],
			height : 460,
			rownumWidth : 30,
			page: 1,
			rowNum: 20,
			rownumbers: true, 
			rowList: [20,30,40],
			autowidth:true,
			loadComplete: function(data){
				if (data.records <= 0) {
					alert("조회 결과가 없습니다.");
					//Commons.resizeJqGrid();
				} else {
					//Commons.resizeJqGrid();
				}
			},
			onSelectRow: function(id){
				var noticeId = $("#grid_notice_list").getRowData(id).notice_id;
				Commons.popupOpen('noticePop', '<%=ONLINE_ROOT %>/getNotice.do?notice_id='+noticeId, 618, 443);
			},
			pager: "#grid_Pager_notice_list"
   		});
		
		

	});
	
	/**
	*	공지사랑 목록 조회
	*/
	var getNoticeListAjax = function(){
		$("#grid_notice_list").clearGridData(); 					// 이전 그리드 삭제
		
		var postData = { 											//파라메터 셋팅
			searchType:$("#searchType option:selected").val(), 		//검색 타입
			keyword:$("#keyword").val(),							//검색어
			sttDate:$("#sttDate").val(),							//검색 시작 일시
			endDate:$("#endDate").val()								//검색 완료 일시
			};
		
		$("#grid_notice_list").jqGrid('setGridParam',{url:"<%=ONLINE_ROOT%>/getNoticeListAjax.do", postData:postData}).trigger("reloadGrid");	//jqGrid 조회
	};
	</script>
</head>
<body>
		<!-- ##### content start ##### -->
		<div class="inner_cont" style="padding :0">
			<div class="wrap_search02 w967 m0auto">
				<label>조회조건</label>
				<select class="w100" id="searchType" id="searchType">
					<option value="none">None</option>
					<option value="title">제목</option>
					<option value="desc">내용</option>
					<option value="sawonNm">등록자</option>
					<option value="titleDesc">제목 + 내용</option>
				</select>
				<input type="text" class="w150" id="keyword" name="keyword"/>
				<label>등록일자</label>
				<p class="wrap_date">
					<input type="text" id="sttDate" name="sttDate">
					<button class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				~
				<p class="wrap_date">
					<input type="text"  id="endDate" name="endDate">
					<button class="btn_date"><span class="blind">달력보기</span></button>
				</p>
				<button type="button" id="btnSearch">검색</button>
			</div>
			<div class="box_type01 w967 m0auto">
				<table id="grid_notice_list" class="w967"></table>
				<div id="grid_Pager_notice_list" class="w967"></div>
			</div>
		</div>
		<!-- ##### content end ##### -->
		<%@include file="/include/footer.jsp"%>
</body>
</html>