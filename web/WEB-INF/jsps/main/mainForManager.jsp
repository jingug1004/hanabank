<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : mainForManager.jsp
 * @메뉴명 : 임원진/관리자 대시 보드 화면  
 * @최초작성일 : 2014/10/29            
 * @author : 장일영                  
 * @수정내역 : 
--%>
<%@ page import="com.hanaph.saleon.main.vo.EmpDashboardVO, java.util.List, org.apache.commons.lang.time.DateFormatUtils, java.util.Date" %>
<%@ include file ="/common/path.jsp" %>
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
		
		$('#comBtnSearch').on('click', function(e){getCompanyResultYearAjax();});
		$('#partBtnSearch').on('click', function(e){getCompanyResultMonthByPartAjax();});
		
		getCompanyResultYearAjax(false);
		getCompanyResultMonthByPartAjax(false);
	});
	
	/**
	 * 회사 전체 연실적 조회
	 * @param alertYn	조회결과 alert 여부
	 */
	var getCompanyResultYearAjax = function(alertYn){
		
		if(typeof alertYn == 'undefined'){
			alertYn = true;
		}
		
		$.ajax({
			type : "POST"
			       	, async : true
			    	, url : ONLINE_ROOT+"/getCompanyResultYearAjax.do"
			    	, dataType : "json"
			    	, data : {year:$('#comReqYear').val()}
			    	, success : function(data) {
			    		if(typeof data != "undefined" && typeof data.length != "undefined" ){
			    			fillGrid('com', data);
			    			drowBarChart2D('com', data);
			    			if(data.length == 0 && alertYn == true){
			    				alert($('#comReqYear').val() + '년도 년간 실적 조회 결과가 없습니다.');
			    			}
				    	}
			    	}
			});
	};
	
	/**
	* 파트별 전체 월실적 조회
	* @param alertYn	조회결과 alert 여부
	*/
	var getCompanyResultMonthByPartAjax = function(alertYn){
		
		if(typeof alertYn == 'undefined'){
			alertYn = true;
		}
		
		var regExp = /^[0-9]{4}-[0-9]{2}$/;
		if (!regExp.test($('#partReqYear').val())) {
			alert('조회하려는 년월의 입력 방식을 년-월 형식으로 입력해주세요. \n(예) 2015-01');
			$('#partReqYear').focus();
			return;
		}
		
		$.ajax({
			type : "POST"
			       	, async : true
			    	, url : ONLINE_ROOT+"/getCompanyResultMonthByPartAjax.do"
			    	, dataType : "json"
			    	, data : {year:$('#partReqYear').val()}
			    	, success : function(data) {
			    		if(typeof data != "undefined" && typeof data.length != "undefined" ){
			    			fillGrid('part', data);
			    			drowBarChart2D('part', data);
			    			if(data.length == 0 && alertYn == true){
			    				alert('파트별 이 달의 실적('+$('#partReqYear').val()+') 조회 결과가 없습니다.');
			    			}
				    	}
			    	}
			});
	};
	
	/**
	* 파트별 전체 월실적 그리드 작성
	* @param type	'com' : 회사, 'part' : 파트
	* @param json : 실적 조회 결과(json)
	*/
	var fillGrid = function(type, json){
		
		$('#'+type+"Grid").find('thead th:first').html($('#'+type+"ReqYear").val());	//조회 년도 셋팅
		$('#'+type+"Grid").find('tbody td').html('');									//기존 그리드값 초기화
		
		var sumSaleGoalAmt = 0;															//판매 목표 합계
		var sumSaleResultAmt = 0;														//판매 실적 합계
		var sumInSaleGoalAmt = 0;														//수금 목표 합계
		var sumInSaleResultAmt = 0;														//수금 실적 합계
		
		/*
		*	json을 loop를 돌면서 합계 변수에 값을 더하고, 그리드에 수치를 입력한다.
		*/
		if(json.length && json.length > 0){
			
			$.each(json, function(idx){
				sumSaleGoalAmt = sumSaleGoalAmt + parseInt(json[idx].sale_goal_amt, 10);
				sumSaleResultAmt = sumSaleResultAmt + parseInt(json[idx].sale_result_amt, 10);
				sumInSaleGoalAmt = sumInSaleGoalAmt + parseInt(json[idx].in_sale_goal_amt, 10);
				sumInSaleResultAmt = sumInSaleResultAmt + parseInt(json[idx].in_sale_result_amt, 10);
				
				if(type == 'com'){
					$('#'+type+"Tr"+json[idx].month).find('td:eq(0)').html(Commons.addComma(json[idx].sale_goal_amt));
					$('#'+type+"Tr"+json[idx].month).find('td:eq(1)').html(Commons.addComma(json[idx].sale_result_amt));
					$('#'+type+"Tr"+json[idx].month).find('td:eq(2)').html(Commons.addComma(json[idx].in_sale_goal_amt));
					$('#'+type+"Tr"+json[idx].month).find('td:eq(3)').html(Commons.addComma(json[idx].in_sale_result_amt));
				} else {
					$('#'+type+"Tr"+json[idx].part_gb).find('td:eq(0)').html(Commons.addComma(json[idx].sale_goal_amt));
					$('#'+type+"Tr"+json[idx].part_gb).find('td:eq(1)').html(Commons.addComma(json[idx].sale_result_amt));
					$('#'+type+"Tr"+json[idx].part_gb).find('td:eq(2)').html(Commons.addComma(json[idx].in_sale_goal_amt));
					$('#'+type+"Tr"+json[idx].part_gb).find('td:eq(3)').html(Commons.addComma(json[idx].in_sale_result_amt));
				}
				
				
			});
		}
		
		/*
		*	합계를 그리드에 표시한다.
		*/
		$('#'+type+"Grid").find('tfoot td:eq(0)').html(Commons.addComma(sumSaleGoalAmt));
		$('#'+type+"Grid").find('tfoot td:eq(1)').html(Commons.addComma(sumSaleResultAmt));
		$('#'+type+"Grid").find('tfoot td:eq(2)').html(Commons.addComma(sumInSaleGoalAmt));
		$('#'+type+"Grid").find('tfoot td:eq(3)').html(Commons.addComma(sumInSaleResultAmt));
	};
	
	
	/**
	*	차트 그리기
	*	@param	type	'com' : 회사, 'part' : 지점
	* 	@param	json	실적 조회 결과(json)
	*/
	var drowBarChart2D = function(type, json){
		var arrySaleGoalAmt = [];				//판매 목표액 배열
		var arrySaleResultAmt = [];             //판매 실적액 배열
		var arryInSaleGoalAmt = [];             //수금 목표액 배열
		var arryInSaleResultAmt = [];           //수금 목표액 배열
		
		/*
		*	label 배열. 회사일 경우에는 월별, 파트일경우에는 파트명
		*/
		var arrLabels = ["도매","종별","세미","로컬","도매2","서울준종별"];
		if(type == 'com'){
			arrLabels = ["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"];
		}
		
		/*
		*	그래프 데이터 생성
		*/
		if(json.length && json.length > 0){
			$.each(json, function(idx){
				if(type == 'com'){		//회사일 경우에는 월별 실적 셋팅
					arrySaleGoalAmt[parseInt(json[idx].month, 10)-1] = json[idx].sale_goal_amt;
					arrySaleResultAmt[parseInt(json[idx].month, 10)-1] = json[idx].sale_result_amt;
					arryInSaleGoalAmt[parseInt(json[idx].month, 10)-1] = json[idx].in_sale_goal_amt;
					arryInSaleResultAmt[parseInt(json[idx].month, 10)-1] = json[idx].in_sale_result_amt;
				} else {				//파트일 경우에는 파트 코드 매핑
					var arrIdx = -1;
					if(json[idx].part_gb == '01'){
						arrIdx = 0;
					} else if(json[idx].part_gb == '02'){
						arrIdx = 1;
					} else if(json[idx].part_gb == '03'){
						arrIdx = 2;
					} else if(json[idx].part_gb == '04'){
						arrIdx = 3;
					} else if(json[idx].part_gb == '14'){
						arrIdx = 4;
					} else if(json[idx].part_gb == '15'){
						arrIdx = 5;
					}
					
					if(arrIdx > -1){
						arrySaleGoalAmt[arrIdx] = json[idx].sale_goal_amt;
						arrySaleResultAmt[arrIdx] = json[idx].sale_result_amt;
						arryInSaleGoalAmt[arrIdx] = json[idx].in_sale_goal_amt;
						arryInSaleResultAmt[arrIdx] = json[idx].in_sale_result_amt;
					}
				}
			});
		}
		
		/*
		*	데이터 유효성 검증
		*/
		Commons.rep0UndefInArr(arrySaleGoalAmt);
		Commons.rep0UndefInArr(arrySaleResultAmt);
		Commons.rep0UndefInArr(arryInSaleGoalAmt);
		Commons.rep0UndefInArr(arryInSaleResultAmt);
		
		/*
		*	차트를 그린 canvas 객체 선언
		*/
		var ctxSale = document.getElementById(type+"SaleChart").getContext("2d");
		var ctxInSale = document.getElementById(type+"InSaleChart").getContext("2d");
		
		/*
		*	ie8같은 경우 html5 태그인 canvas를 지원하지 않아 구글에서 만든 excanvas.js를 임포트 했기 때문에 animation 기능을 끈다.
		*/
		var chartAnimate = true;
		try{ if((+navigator.userAgent.match(/MSIE ([\d.]+)?/)[1]) < 9){chartAnimate = false;} }catch(e){}
		
		/*
		*	판매 차트 옵션 생성
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
		
		/*
		*	기존에 그려진 판매 차트를 파괴한다!!!!!
		*/
		if(window[type+'myBar_ctxSale']){
			window[type+'myBar_ctxSale'].destroy();	
		}
		
		/*
		*	판매 차트 데이터 셋 생성
		*/
		var chartDataSale = {
							    labels: arrLabels,
							    datasets: [
							        {
							        	fillColor : "rgba(31,183,235,0.5)",
										strokeColor : "rgba(31,183,235,0.8)",
										highlightFill : "rgba(31,183,235,0.75)",
										highlightStroke : "rgba(31,183,235,1)",
										tooltipTitleFontStyle: "bold",
										data : arrySaleGoalAmt,
										label : '목표'
									},
									{
										fillColor : "rgba(255,102,0,0.5)",
										strokeColor : "rgba(255,102,0,0.8)",
										highlightFill: "rgba(255,102,0,0.75)",
										highlightStroke: "rgba(255,102,0,1)",
										data : arrySaleResultAmt,
										label : '실적'
									}
							    ]
							};
		
		/*
		*	판매 차트 생성.
		*/
		window[type+'myBar_ctxSale'] = new Chart(ctxSale).Bar(chartDataSale, chartOption);
		
		/*
		*	기존에 그려진 수금 차트를 파괴한다!!!!!
		*/
		if(window[type+'myBar_ctxInSale']){
			window[type+'myBar_ctxInSale'].destroy();
		}
		
		/*
		*	수금 차트 데이터 셋 생성
		*/
		var chartDataInSale = {
								    labels: arrLabels,
								    datasets: [
										{
								        	fillColor : "rgba(31,183,235,0.5)",
											strokeColor : "rgba(31,183,235,0.8)",
											highlightFill : "rgba(31,183,235,0.75)",
											highlightStroke : "rgba(31,183,235,1)",
											tooltipTitleFontStyle: "bold",
											data : arryInSaleGoalAmt,
											label : '목표'
										},
										{
											fillColor : "rgba(255,102,0,0.5)",
											strokeColor : "rgba(255,102,0,0.8)",
											highlightFill: "rgba(255,102,0,0.75)",
											highlightStroke: "rgba(255,102,0,1)",
											data : arryInSaleResultAmt,
											label : '실적'
										}
								    ]
								};
		
		/*
		*	수금 그래프를 새로 그린다.
		*/
		window[type+'myBar_ctxInSale'] = new Chart(ctxInSale).Bar(chartDataInSale, chartOption);
		
		/*
		*	차트 범례 생성
		*/
		Commons.legend(document.getElementById(type+"SaleChartLegend"), chartDataSale);
		Commons.legend(document.getElementById(type+"InSaleChartLegend"), chartDataInSale);
		
	};
	
	</script>
</head>

<body>
	<!-- ##### content start ##### -->
	<div class="inner_cont" style="padding:0">
		<div class="main w967 m0auto paddingTB30">
			<h2>년간 실적</h2>
			<div class="search mt10">
				<label>년도</label>
				<input type="text" class="w100 ta_r" id="comReqYear" value="<%=DateFormatUtils.format(new Date(), "yyyy")%>"  maxlength='4' onkeyup="this.value=Commons.numberFilter(this.value);" />
				<button id="comBtnSearch">검색</button>
			</div>
			<table id="comGrid" class="type01 ta_c mt10">
				<colgroup>
					<col />
					<col style="width:210px;" />
					<col style="width:210px;" />
					<col style="width:210px;" />
					<col style="width:210px;" />
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2">2014</th>
						<th colspan="2">판매</th>
						<th colspan="2">수금</th>
					</tr>
					<tr>
						<th>목표</th>
						<th>실적</th>
						<th>목표</th>
						<th>실적</th>
					</tr>
				</thead>
				<tbody>
					<%for(int i = 1; i <= 12; i++){ %>
					<tr id="comTr<%=(i<10? "0"+i : ""+i)%>">
						<th><%=i %>月</th>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
					</tr>
					<%} %>
				</tbody>
				<tfoot>
					<tr>
						<th>합계</th>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
					</tr>
				</tfoot>
			</table>
			
			<div class="wrap_chart">
				<div class="float_l">
					<p class="ta_c">&lt; 판 매 &gt;</p>
					<div class="chart" style="position: relative;">
						<canvas id="comSaleChart" style='position: absolute;bottom: 0;'></canvas>
						<div id="comSaleChartLegend"></div>
					</div>
				</div>
				<div class="float_r">
					<p class="ta_c">&lt; 수 금 &gt;</p>
					<div class="chart" style="position: relative;">
						<canvas id="comInSaleChart" style='position: absolute;bottom: 0;'></canvas>
						<div id="comInSaleChartLegend"></div>
					</div>
				</div>
			</div>
			
			<h2 class="mt30">파트별 이 달의 실적</h2>
			<div class="search mt10">
				<label>년월</label>
				<input type="text" class="w100 ta_r" id="partReqYear" value="<%=DateFormatUtils.format(new Date(), "yyyy-MM")%>"  maxlength='7'/>
				<button id="partBtnSearch">검색</button>
			</div>
			<table id="partGrid"  class="type01 ta_c mt10">
				<colgroup>
					<col />
					<col style="width:210px;" />
					<col style="width:210px;" />
					<col style="width:210px;" />
					<col style="width:210px;" />
				</colgroup>
				<thead>
					<tr>
						<th rowspan="2">2014</th>
						<th colspan="2">판매</th>
						<th colspan="2">수금</th>
					</tr>
					<tr>
						<th>목표</th>
						<th>실적</th>
						<th>목표</th>
						<th>실적</th>
					</tr>
				</thead>
				<tbody>
					<tr id="partTr01">
						<th>도매</th>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
					</tr>
					<tr id="partTr02">
						<th>종병</th>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
					</tr>
					<tr id="partTr03">
						<th>세미</th>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
					</tr>
					<tr id="partTr04">
						<th>로컬</th>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
					</tr>
					<tr id="partTr14">
						<th>도매2</th>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
					</tr>
					<tr id="partTr15">
						<th>서울준종병</th>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
					</tr>
				</tbody>
				<tfoot>
					<tr>
						<th>합계</th>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
						<td class="ta_r"></td>
					</tr>
				</tfoot>
			</table>
			
			<div class="wrap_chart">
				<div class="float_l">
					<p class="ta_c">&lt; 판 매 &gt;</p>
					<div class="chart" style="position: relative;">
						<canvas id="partSaleChart" style='position: absolute;bottom: 0;'></canvas>
						<div id="partSaleChartLegend"></div>
					</div>
				</div>
				<div class="float_r">
					<p class="ta_c">&lt; 수 금 &gt;</p>
					<div class="chart" style="position: relative;">
						<canvas id="partInSaleChart" style='position: absolute;bottom: 0;'></canvas>
						<div id="partInSaleChartLegend"></div>
					</div>
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