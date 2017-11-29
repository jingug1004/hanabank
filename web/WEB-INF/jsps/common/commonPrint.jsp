<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : commonPrint.jsp
 * @메뉴명 : 공통 > 인쇄 팝업창       
 * @최초작성일 : 2014/11/7            
 * @author : 윤범진                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<!DOCTYPE html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>하나제약 온라인 주문</title>
	<style>
		/*a4용지*/
		@page a4sheet { size: 21.0cm 29.7cm } 
		.a4 { page: a4sheet; /* page-break-after: always */ } 
	</style>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript">
	
		try {
			// css 복제
			$("link", opener.document).each(function() {
				var css = document.createElement("link");
				css.setAttribute("rel", $(this).attr("rel"));
				css.setAttribute("type", $(this).attr("type"));
				css.setAttribute("href", $(this).attr("href"));
				document.getElementsByTagName("head")[0].appendChild(css);
			});	
		} catch (e) {
			// 잘못된 접근일 경우 메인으로 redirect
			location.href = "<%=ONLINE_ROOT%>/main.do";
		}
		
		// 인쇄
		function printPage(){
			$("#buttonArea").hide();
			if(confirm("인쇄하시겠습니까?")) {
				window.print();
			}
			$("#buttonArea").show();
		}
	
		$(document).ready(function(){
			try {
				
				// 인쇄영역 복제
				var originArea = opener.document.getElementById(self.name);
				
				var flagDispNone = false;	// 인쇄영역의 display 값이 none인지?
				if(originArea.style.display == 'none'){	
					originArea.style.display = 'block';	// 정확한 복제를 위해서 일시적으로 display값을 변경
					flagDispNone = true;
				}
				var printArea = document.createElement("div");
				printArea.className = originArea.className + " a4";
				printArea.setAttribute("id", self.name);
				printArea.innerHTML = originArea.innerHTML;
				document.body.appendChild(printArea);
				
				// 불필요한 div 삭제
				$("." + self.name).remove();
				
				// grid div 넓이를 table 기준으로 맞춰서 scroll 사라지도록 함 - 2014-12-31 수정
				var width = $("table.ui-jqgrid-htable").width();
				
				$("div.ui-jqgrid.ui-widget.ui-widget-content").width(width);
				$("div.ui-jqgrid-view").width(width);
				$("div.ui-state-default.ui-jqgrid-hdiv").width(width);
				$("div.ui-jqgrid-bdiv").width(width);
				
				$("div.ui-jqgrid-bdiv").css("overflow", "hidden");
				
				// grid 영역 높이 맞춤 ((tr 갯수 * 23px) - 1)
				//var count = ($(".ui-jqgrid-btable tr").size() - 1);
				//$(".ui-jqgrid-bdiv").height((count * 23) + 1);
				$(".ui-jqgrid-bdiv").height($('.ui-jqgrid-bdiv table').innerHeight());	//1010. 한 로우가 23px이 아닌 경우가 있어서.
				
				// grid row 선택 해제 
				$(".ui-state-highlight").removeClass("ui-state-highlight");
				
				if(flagDispNone){	
					originArea.style.display = 'none';	// 원래대로 display속성을 none을 복원함. 
					$(".ui-jqgrid-bdiv table td").css({wordBreak:"break-all",wordWrap:"break-word"});
					setTimeout(function(){ $(".ui-jqgrid-bdiv").height($('.ui-jqgrid-bdiv table').innerHeight()); }, 1000);	//제대로된 div의 height을 위해 1초뒤에 다시한번 table의 height를 적용함.
				}
			} catch (e) {
				// 잘못된 접근일 경우 메인으로 redirect
				location.href = "<%=ONLINE_ROOT%>/main.do";
			}
		});
	</script>
</head>
<body>
<div id="buttonArea" class="wrap_btn_group">
	<div class="btn_group ta_r">
		<button type="button" onclick="javascript:printPage();" >출력</button>
		<button type="button" onclick="javascript:self.close();" >닫기</button>
	</div>
</div>
</body>
</html>