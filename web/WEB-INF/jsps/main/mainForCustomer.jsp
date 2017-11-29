<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : mainForCustomer.jsp
 * @메뉴명 : 거래처 대시 보드 화면  
 * @최초작성일 : 2014/10/29            
 * @author : 장일영                  
 * @수정내역 : 
--%>
<%@ page import="com.hanaph.saleon.main.vo.CustDashboardVO, java.util.List, org.apache.commons.lang.time.DateFormatUtils, java.util.Date" %>
<%@ include file ="/common/path.jsp" %>
<%
	/*거래처 정보*/
	CustDashboardVO custInfo = request.getAttribute("custInfo") != null ? (CustDashboardVO) request.getAttribute("custInfo") : new CustDashboardVO();
	
	/*거래처 여신현황*/
	CustDashboardVO custLoanPresentCondition = request.getAttribute("custLoanPresentCondition") != null ? (CustDashboardVO) request.getAttribute("custLoanPresentCondition") : new CustDashboardVO();

	String reqYear = request.getParameter("reqYear") == null ? "" : (String) request.getParameter("reqYear");
	
%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<!--[if lt IE 9]>
    <script src="<%=ONLINE_WEB_ROOT%>/js/excanvas.js"></script>
	<![endif]-->
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/Chart.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=ONLINE_WEB_ROOT%>/css/chart.js.legend.css" />
	<script type="text/javascript">
	
	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		
		/* 엔터키입력 막기 */
		$("body").on("keydown", function (e) {
			if (e.keyCode === 13) {
				e.preventDefault();
			}
		});
		
		var setReqYear = "<%=reqYear%>";
		if(setReqYear != ""){
			$('#reqYear').val(setReqYear);
		}
		$('#btnSearch').on('click', function(e){getCustOrderPresentConditionAjax();});		//검색 버튼 클릭 이벤트를 걸어준다.
		getCustOrderPresentConditionAjax(false);											//년간 주문 현황 조회 

	});
	
	/**
	*	년간 주문 현황 조회
	*	@param	alertYn	조회결과 alert 여부
	*/
	var getCustOrderPresentConditionAjax = function(alertYn){
		
		if(typeof alertYn == 'undefined'){
			alertYn = true;
		}
		
		$.ajax({
			type : "POST"
			       	, async : true
			    	, url : ONLINE_ROOT+"/getCustOrderPresentConditionAjax.do"
			    	, dataType : "json"
			    	, data : {reqYear:$('#reqYear').val()}
			    	, success : function(data) {
			    		if(typeof data != "undefined" && typeof data.length != "undefined" ){
			    			fillGrid(data);
			    			drowLineChart2D(data);
			    			if(data.length == 0 && alertYn == true){
			    				alert($('#reqYear').val() + '년도 년간 주문 현황 조회 결과가 없습니다.');
			    			}
				    	}
			    	}
			});
	};
	
	/**
	*	년간 주문 현황 표채우기
	*	@param	json	년간 주문 현황 json
	*/
	var fillGrid = function(json){
		$('div.year_table tbody td.no_border_r').html('');
		if(json.length && json.length > 0){
			var year = '';
			$.each(json, function(idx){
				if(parseInt(json[idx].month, 10) <= 12){
					$('#tdAmt'+json[idx].month).html(Commons.addComma(json[idx].month_amt));
				} else {
					$('#tdAmt'+json[idx].month).html(Commons.addComma(json[idx].total_amt));
				}
				year = json[idx].ymd;
			});
			
			$('#reqYear').val(year.substring(0, 4));
		}
	};
	
	/**
	*	년간 주문 현황 차트 그리기
	*	@param	json	년간 주문 현황 json
	*/
	var drowLineChart2D = function(json){
		/*
		*	전달받은 json객체를 루프돌면서 배열에 값을 셋팅한다.
		*/
		var arryData = [];		//실제 값이 들어갈 배열. index순이 월순이다.
		if(json.length && json.length > 0){
			$.each(json, function(idx){
				if(parseInt(json[idx].month, 10) <= 12){
					arryData[parseInt(json[idx].month, 10)-1] = json[idx].month_amt;
				} 
			});
		}
		
		Commons.rep0UndefInArr(arryData);	//데이터 유효성 검증
		
		/*
		* ie8같은 경우 html5 태그인 canvas를 지원하지 않아 구글에서 만든 excanvas.js를 임포트 했기 때문에 animation 기능을 끈다.
		*/
		var chartAnimate = true;
		try{ if((+navigator.userAgent.match(/MSIE ([\d.]+)?/)[1]) < 9){chartAnimate = false;} }catch(e){}
		
		var ctx = document.getElementById("canvas1").getContext("2d");	//차트 그릴 canvas 지정
		
		if(window.myBar){
			window.myBar.destroy();		// 기존에 그려진 차트를 파괴한다!!!!!
		}
		
		/*
		* 차트 데이터 생성
		*/
		var chartData = {
			    labels: ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],
			    datasets: [
			        {
			            fillColor: "rgba(31,183,235,0.2)",
			            strokeColor: "rgba(31,183,235,1)",
			            pointColor: "rgba(31,183,235,1)",
			            pointStrokeColor: "#fff",
			            pointHighlightFill: "#fff",
			            pointHighlightStroke: "rgba(31,183,235,1)",
			            data: arryData,
			            label : "금액(원)"
			        }
			    ]
			}; 
		
		/*
		*	차트 옵션 설정
		*/
		var chartOption = {
					animation: chartAnimate,
					responsive : true,	// Y축 값을 설정하겠냐
					scaleOverride: false,	// ** Required if scaleOverride is true **
					scaleSteps: 5,	// 몇개까지 보여줄꺼냐
					scaleStepWidth: 1000000,	// 어떤 단위로 점프할꺼냐
					scaleStartValue: 0,		// 시작되는 값은 무엇이냐
					scaleBeginAtZero : true
				};
		
		window.myBar = new Chart(ctx).Line(chartData, chartOption);		//새로 차트를 그린다.
		
		Commons.legend(document.getElementById("legend1"), chartData);	//차트 범례 생성
	};
	
	function mainForCustomerReload(){
		var reqYear = $('#reqYear').val();
		location.href="<%=ONLINE_ROOT%>/mainForCustomer.do?reqYear="+reqYear;
	}
	
	</script>
</head>

<body>
	<!-- ##### content start ##### -->
		<div class="inner_cont" style="padding:0">
			<div class="main w967 m0auto paddingTB30">
				<h2>기본사항</h2>
				<div class="box_type02">
					<table class="type01 ta_c">
						<thead>
							<tr>
								<th class="no_border_l no_border_t">거래처코드</th>
								<th class="no_border_t">거래처명</th>
								<th class="no_border_t">사업자 번호</th>
								<th class="no_border_t">주소</th>
								<th class="no_border_t">대표자명</th>
								<th class="no_border_r no_border_t">계산서 이메일</th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td class="no_border_l no_border_b"><%=StringUtil.nvl(custInfo.getCust_id()) %></td>
								<td class="no_border_b"><%=StringUtil.nvl(custInfo.getCust_nm()) %></td>
								<%
									/*
									*	MaskFormatter. format => A:Any character or number
									*	123-45-67890 형태로 보여주는 포맷 설정
									*/
									javax.swing.text.MaskFormatter mf = new javax.swing.text.MaskFormatter("AAA-AA-AAAAA");
									mf.setValueContainsLiteralCharacters(false);
									String vouNo = StringUtil.nvl(custInfo.getVou_no());
									try{
										vouNo = mf.valueToString(vouNo);
									}catch(Exception e){}
								%>
								<td class="no_border_b"><%=vouNo%></td>
								<td class="no_border_b"><%=StringUtil.nvl(custInfo.getAddr()) %></td>
								<td class="no_border_b"><%=StringUtil.nvl(custInfo.getPresident()) %></td>
								<td class="no_border_r no_border_b"><%=StringUtil.nvl(custInfo.getEmail()) %></td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<h2 class="mt30">여신현황<button type="button" onclick="javascript:mainForCustomerReload();">현재현황보기</button></h2>
				<div class="box_type02">
					<table class="type01 ta_c" style="table-layout: fixed">
						<colgroup>
							<col style="width:160px;" />
							<col />
							<col style="width:160px;" />
							<col />
							<col style="width:160px;" />
							<col />
						</colgroup>
						<tr>
							<th class="no_border_l no_border_t">전월잔고</th>
							<td class="no_border_t ta_r"><%=StringUtil.makeMoneyType(custLoanPresentCondition.getBefore_amt()) %></td>
							<th class="no_border_t">총여신</th>
							<td class="no_border_t ta_r"><%=StringUtil.makeMoneyType(custLoanPresentCondition.getTotal_amt()) %></td>
							<th class="no_border_t">미정리매출</th>
							<td class="no_border_r ta_r no_border_t"><%=StringUtil.makeMoneyType(custLoanPresentCondition.getPending_sales()) %></td>
						</tr>
						<tr>
							<th class="no_border_l">금월판매</th>
							<td class="ta_r"><%=StringUtil.makeMoneyType(custLoanPresentCondition.getSale_amt()) %></td>
							<th>연대보증인</th>
							<td><%=StringUtil.nvl(custLoanPresentCondition.getDambo()) %></td>
							<th>회전일</th>
							<td class="no_border_r"><%=StringUtil.nvl(custLoanPresentCondition.getRate_day()) %></td>
						</tr>
						<tr>
							<th class="no_border_l">금월수금</th>
							<td class="ta_r"><%=StringUtil.makeMoneyType(custLoanPresentCondition.getCash_amt()) %></td>
							<th>담보확보액</th>
							<td class="ta_r"><%=StringUtil.makeMoneyType(custLoanPresentCondition.getSale_dambo_amt()) %></td>
							<th>거래처 담당자</th>
							<td class="no_border_r"><%=StringUtil.nvl(custLoanPresentCondition.getSawon_nm()) %></td>
						</tr>
						<tr>
							<th class="no_border_l">현잔고</th>
							<td class="ta_r"><%=StringUtil.makeMoneyType(custLoanPresentCondition.getCur_amt()) %></td>
							<th>담보확보율</th>
							<td><%=StringUtil.nvl(custLoanPresentCondition.getDambo_per()) %></td>
							<th>판매처 담당자</th>
							<td class="no_border_r"><%=StringUtil.nvl(custLoanPresentCondition.getCust_sawon_nm()) %></td>
						</tr>
						<tr>
							<th class="no_border_l">미도래(자수)</th>
							<td class="ta_r"><%=StringUtil.makeMoneyType(custLoanPresentCondition.getJasu_amt()) %></td>
							<th>담보종류</th>
							<td><%=StringUtil.nvl(custLoanPresentCondition.getDambo_kind()) %></td>
							<th></th>
							<td class="no_border_r"></td>
						</tr>
						<tr>
							<th class="no_border_l no_border_b">미도래(타수)</th>
							<td class="no_border_b ta_r"><%=StringUtil.makeMoneyType(custLoanPresentCondition.getTasu_amt()) %></td>
							<th class="no_border_b">여신한도액</th>
							<td class="no_border_b ta_r"><%=StringUtil.makeMoneyType(custLoanPresentCondition.getLoan_limit()) %></td>
							<th class="no_border_b"></th>
							<td class="no_border_r no_border_b"></td>
						</tr>
					</table>
				</div>
				
				<h2 class="mt30">년간 주문 현황</h2>
				<div class="wrap_year_order">
					<div class="wrap_graph">
						<div class="search mt10">
							<label>년도</label>
							<input type="text" class="w100 ta_r" id="reqYear" value="<%=DateFormatUtils.format(new Date(), "yyyy")%>" maxlength='4' onkeyup="this.value=Commons.numberFilter(this.value);" />
							<button id="btnSearch">검색</button>
						</div>
						
						<div class="graph" style="position: relative;">
							<canvas id="canvas1" style='position: absolute;bottom: 0;'></canvas>
							<div id="legend1"></div>
						</div>
					</div>
					<div class="year_table">
						<table class="type01 ta_c">
							<colgroup>
								<col style="width:50%;" />
								<col style="width:50%;" />
							</colgroup>
							<thead>
								<tr>
									<th class="no_border_l no_border_t">월</th>
									<th class="no_border_r no_border_t">금액 (원)</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td class="no_border_l">1月</td>
									<td class="no_border_r ta_r" id="tdAmt01"></td>										
								</tr>
								<tr>
									<td class="no_border_l">2月</td>
									<td class="no_border_r ta_r" id="tdAmt02"></td>										
								</tr>
								<tr>
									<td class="no_border_l">3月</td>
									<td class="no_border_r ta_r" id="tdAmt03"></td>										
								</tr>
								<tr>
									<td class="no_border_l">4月</td>
									<td class="no_border_r ta_r" id="tdAmt04"></td>										
								</tr>
								<tr>
									<td class="no_border_l">5月</td>
									<td class="no_border_r ta_r" id="tdAmt05"></td>										
								</tr>
								<tr>
									<td class="no_border_l">6月</td>
									<td class="no_border_r ta_r" id="tdAmt06"></td>										
								</tr>
								<tr>
									<td class="no_border_l">7月</td>
									<td class="no_border_r ta_r" id="tdAmt07"></td>										
								</tr>
								<tr>
									<td class="no_border_l">8月</td>
									<td class="no_border_r ta_r" id="tdAmt08"></td>										
								</tr>
								<tr>
									<td class="no_border_l">9月</td>
									<td class="no_border_r ta_r" id="tdAmt09"></td>										
								</tr>
								<tr>
									<td class="no_border_l">10月</td>
									<td class="no_border_r ta_r" id="tdAmt10"></td>										
								</tr>
								<tr>
									<td class="no_border_l">11月</td>
									<td class="no_border_r ta_r" id="tdAmt11"></td>										
								</tr>
								<tr>
									<td class="no_border_l">12月</td>
									<td class="no_border_r ta_r" id="tdAmt12"></td>										
								</tr>
							</tbody>
							<tfoot>
								<tr>
									<th class="no_border_l no_border_b">합계</th>
									<td class="no_border_r ta_r no_border_b" id="tdAmt99"></td>										
								</tr>
							</tfoot>
						</table>
					</div>
				</div>
				
				<!-- 공지사항 리스트 -->
				<%@ include file ="./common/noticeListInc.jsp" %>
				<!-- //공지사항 리스트 -->
			</div>
		</div>
		<!-- ##### content end ##### -->
		<%@include file="/include/footer.jsp"%>
</body>
</html>