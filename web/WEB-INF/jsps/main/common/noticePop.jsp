<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : noticePop.jsp
 * @메뉴명 : 공지사항 팝업         
 * @최초작성일 : 2014/10/29            
 * @author : 장일영                  
 * @수정내역 : 
--%>
<%@ page import="com.hanaph.saleon.main.vo.NoticeVO, java.util.List" %>
<%@ include file ="/common/path.jsp" %>
<%
	/*
	*	공지사항 리스트
	*/
	List<NoticeVO> noticeList = (List<NoticeVO>) request.getAttribute("notice");
	NoticeVO noticeVO = new NoticeVO();
	if(noticeList != null && !noticeList.isEmpty()){
		noticeVO = noticeList.get(0);
	}
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="/include/head.jsp"%>
	<script>
	
	<%if(noticeList == null || noticeList.isEmpty()){%>
	alert("공지사항 정보가 없습니다.");
	self.close();
	<%}%>
	
	/* 화면의 Dom객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		/*
		* 닫기 버튼 클릭했을 때 chkReadYn(체크박스)가 체크되었을 경우 cookie에 sale_on_noticeReadDate을 오늘 일자로 셋팅해준다.
		*/
		$('#btnClose').on('click', function(e){
			if($('#chkReadYn').is(":checked")){
				var d = new Date();
				var today = '' + d.getFullYear() + Commons.leadingZeros((d.getMonth()+1), 2) + Commons.leadingZeros(d.getDate(), 2);
				Commons.setCookie('sale_on_noticeReadDate', today, 365);
			}
			
			self.close();
		});
	});
		
	</script>
</head>
<body onload="window.focus();">
	<div class="popup notice_bg" title="Main">
		
		<!-- ##### content start ##### -->
		<!-- window size : 600 * 420 -->
			<div class="wrap_notice">
				<h1 class="mt10 ta_c">공지사항</h1>
				
				<div class="box_type01">
					<table class="type03">
						<colgroup>
							<col style="width:80px;" />
							<col style="width:70px;" />
							<col style="width:75px;" />
							<col style="width:130px;" />
							<col style="width:70px;" />
							<col style="width:;" />
						</colgroup>
						<tr>
							<th class="no_border_t no_border_l">공지번호</th>
							<td class="no_border_t"><%=StringUtil.nvl(noticeVO.getNotice_id()) %></td>
							<th class="no_border_t">작성일시</th>
							<td class="no_border_t"><%=StringUtil.nvl(noticeVO.getInput_date()) %></td>
							<th class="no_border_t">유효기간</th>
							<td class="no_border_t no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">작성자</th>
							<td><%=StringUtil.nvl(noticeVO.getSawon_nm()) %></td>
							<th>E-Mail</th>
							<td colspan="3" class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l">제목</th>
							<td colspan="5" class="no_border_r"><%=StringUtil.nvl(noticeVO.getNotice_title(), "[제목없음]") %></td>
						</tr>
						<tr>
							<td colspan="6" class="no_border_l no_border_b no_border_r h200 va_t">
								<div style="overflow:auto; height:200px; width:100%">
								<%=StringUtil.nl2br(noticeVO.getNotice_desc()) %>
								</div>
							</td>
						</tr>
					</table>
				</div>
				
				<div class="wrap_confirmed ta_r mt10">
					<input type="checkbox" id="chkReadYn" />
					<label for="chkReadYn">이 공지내용을 확인하였습니다.</label>
					<button id= "btnClose" class="closed"><span class="blind">닫기</span></button>
				</div>
			</div>
		
		<!-- ##### content end ##### -->
	
	</div>
</body>
</html>