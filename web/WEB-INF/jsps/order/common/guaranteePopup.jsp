<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : guaranteePopup.jsp
 * @메뉴명 : 담보 약속사항 팝업    
 * @최초작성일 : 2014/10/29            
 * @author : 우정아                 
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<title>판매처 검색</title>
	<%@ include file ="/include/head.jsp" %>
	<!--[if lte IE 9]><script src="asset/js/IE9.js"></script><![endif]-->
	<!--[if lte IE 9]><script src="asset/js/html5.js"></script><![endif]-->
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/ui.jqgrid.css">
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/grid.locale-kr.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery.jqGrid.min.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/formCheck.js"></script>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/order.js"></script>
	<script type="text/javascript">
	
	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		//담보제공 기일 오늘날짜로 set
		Commons.setDate('','ls_pro_date');
	});
	
	/**
	 * 담보 약속 사항을 opener쪽에 셋팅
	 */
	function sendValue(){
		
		//현재날짜구하기
		var now = new Date();
		var year= now.getFullYear();
		var mon = (now.getMonth()+1)>9 ? ""+(now.getMonth()+1) : "0"+(now.getMonth()+1);
		var day = now.getDate()>9 ? ""+now.getDate() : "0"+now.getDate();
		var todate = year + "-" + mon + "-" + day;
		
		var ls_pro_date = $('#ls_pro_date').val();
		var ls_pro_bigo = $('#ls_pro_bigo').val();
		
		
		if(!formCheck.isDateFormat(ls_pro_date)){
			alert('약속기일 형식(YYYY-MM-DD)을 확인해주세요.');
			return;
		}
		
		if (formCheck.dateChk(todate,ls_pro_date)<0) {
			alert('약속기일이 현재일보다 이전일자입니다.');
			return;
		}
	
		if (formCheck.isNull(ls_pro_bigo)){
			alert('약속사항을 입력하여 주시기 바랍니다.');
			return;
		}
		
		// DB 80byte 길이 제한
		if (formCheck.getByteLength(ls_pro_bigo)>80){
			alert('약속사항의 내용은 한글 40자, 영문 80자로 입력해주세요(80byte)');
			return;
		}
		
		self.close();		// 팝업창닫기
		opener.damboValue(ls_pro_date.replace(/-/gi,""), ls_pro_bigo);		//opener쪽에 입력받은 값 셋팅
		
	}
	
	$(window).unload(function(){
		opener.initdamboCheck();	 
	});
	
	</script>
</head>


<body>
<div class="popup">
		
<!-- ##### content start ##### -->
<!-- window size : 480 * 391 -->
	<h1 class="tit">담보제공 약속이 필요합니다</h1>
	
	<div class="box_type01">
		<table class="type01">
			<colgroup>
				<col style="width:100px;" />
				<col />
			</colgroup>
			<tr>
				<th class="no_border_l no_border_t">약속기일</th>
				<td class="no_border_r no_border_t">
					<p class="wrap_date">
						<input type="text" class="ls_pro_date" id="ls_pro_date" name="ls_pro_date" value=""/>
						<button type="button" class="btn_date"><span class="blind">달력보기</span></button>
					</p>
				</td>
			</tr>
			<tr>
				<th class="no_border_l no_border_b">약속사항</th>
				<td class="no_border_r no_border_b">
					<textarea id="ls_pro_bigo" class="w300 h150"></textarea>
				</td>
			</tr>
		</table>
	</div>
	<div class="wrap_confirm">
		<button type="button" onclick="javascript:sendValue();">확인</button>
		<button type="button" onclick="javascript:opener.initdamboCheck();self.close();">취소</button>
	</div>
	
	<button type="button" class="close" onclick="javascript:opener.initdamboCheck();"><span class="blind">닫기</span></button>

	<!-- ##### content end ##### -->

	</div>

	<%@ include file ="/include/footer_pop.jsp" %>
</body>

</html>
