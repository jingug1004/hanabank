<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : noticeListInc.jsp   
 * @메뉴명 : main > 공지사항 목록 include           
 * @최초작성일 : 2014/10/29            
 * @author : 장일영                  
 * @수정내역 : 
--%>
<%@ page import="com.hanaph.saleon.main.vo.NoticeVO, java.util.List" %>
<%@ page import="java.util.Date"%>
<%@ page import="java.util.Calendar"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%
	// 공지사항 리스트
	List<NoticeVO> noticeList = (List<NoticeVO>) request.getAttribute("noticeList");
	
%>
			<div class="wrap_notice_list">
				<h2 class="mt30">공지사항</h2>
				<div class="box_type02">
					<table class="notice">
						<colgroup>
							<col style="width:730px;" />
							<col />
							<col />
						</colgroup>
						<%
							if(noticeList != null && !noticeList.isEmpty()){
								int idx = 0;
								for(NoticeVO item : noticeList){
						%>
						<tr>
							<td class="title <%=idx == 0 ? "no_border_t" : "" %>"><a href="javascript:Commons.popupOpen('noticePop', '<%=ONLINE_ROOT %>/getNotice.do?notice_id=<%=item.getNotice_id() %>', 618, 443);" class="fc_b"><%=StringUtil.nvl(item.getNotice_title(), "[제목없음]") %></a></td>
							<td class="<%=idx == 0 ? "no_border_t" : "" %>"><%=StringUtil.nvl(item.getInput_date()) %></td>
							<td class="<%=idx == 0 ? "no_border_t" : "" %>"><%=StringUtil.nvl(item.getSawon_nm()) %></td>
						</tr>
						<%
									idx++;
								}
							} else {
						%>
						<tr>
							<td class="title no_border_t" colspan="3">공지사항이 없습니다.</td>
						</tr>
						<%
							}
						%>
					</table>
				</div>
				<p class="more"><a href="javascript:parent.Commons.addTab('공지사항', '<%=ONLINE_ROOT %>/noticeList.do');">+ 더 보기</a></p>
			</div>
			<script type="text/javascript">
				/*
				*	cookie에 심어진 공지사항 마지막 읽은 날짜와 최신 공지사항의 등록일자를 비교해 등록일자가 더 최신이면 공지사항팝업을 보여줌.
				*/
				var popNotice = function(noticeId, inpDate){
					var sale_on_noticeReadDate = Commons.getCookie('sale_on_noticeReadDate');
					if(sale_on_noticeReadDate == "" || sale_on_noticeReadDate < inpDate){
						Commons.popupOpen('noticePop', '<%=ONLINE_ROOT %>/getNotice.do?notice_id='+noticeId, 618, 443);
					}
				};
				
				<%
					String dateString = "20150129";		// 셋팅 날짜 필수 이미지 이름과 동일 jpg형식으로 넣어 주세요.
					 
				    SimpleDateFormat formatter = new SimpleDateFormat ("yyyyMMdd"); // 년월일 형태 yyyyMMddHHmm 시분초까지 설정 가능
				    Date date = formatter.parse(dateString);						// 설정된 시간
				    Calendar calendar = Calendar.getInstance();						// 캘린더 객체를 얻는다.
				    calendar.setTime(date);
				    calendar.add(Calendar.MONTH,2);									// 설정 개월 수 2개월 셋팅
				    //int setDay = Integer.parseInt(formatter.format(calendar.getTime()));	// 셋팅된 날짜 String
				    int setDay = 20150228;	// 몇 개월 기간이 아닌 특정날짜로 셋팅 할 경우 몇개월간으로 셋팅 할 경우 위의 주석을 해지 해주세요.
				    
				    Date nowDate = new Date();												// 현재 시간
				    int sysdate = Integer.parseInt(formatter.format(nowDate));				// 현재 날짜 String
				    
				    
				    /*
				     * 현재 날짜가 공지 날짜에 포함 되면 설정 된 페이지 호출
				     */
				    if(setDay >= sysdate){
				%>
						var sale_on_noticeImgReadDate = Commons.getCookie('sale_on_noticeImgReadDate');
						if(sale_on_noticeImgReadDate == ""){ 
							Commons.popupOpen('noticeImgPop', '<%=ONLINE_ROOT %>/getNoticeImg.do?dateString=<%=dateString%>',700,700);
				    	}
				<%
				    }
				%>
			
				<%if(noticeList != null && !noticeList.isEmpty()){ %>
				popNotice('<%=noticeList.get(0).getNotice_id()%>', '<%=noticeList.get(0).getInput_date()%>');		//최신 공지 팝업
				<%}%>
			</script>