<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="com.hanaph.saleon.business.vo.CustomerLedgerVO" %>
<%@ page import="com.hanaph.saleon.common.utils.StringUtil" %>
<%@ page import="java.util.List" %>
<%@ include file ="/common/path.jsp" %>
<%

	/* 조회기간 */
	String ad_fr_date = ((String)request.getAttribute("ad_fr_date")).replace("-", "");
	String ad_to_date = ((String)request.getAttribute("ad_to_date")).replace("-", "");
	
	ad_fr_date = ad_fr_date.substring(0, 4) + "년 " + ad_fr_date.substring(4, 6) + "월 " + ad_fr_date.substring(6, 8) + "일";
	ad_to_date = ad_to_date.substring(0, 4) + "년 " + ad_to_date.substring(4, 6) + "월 " + ad_to_date.substring(6, 8) + "일";
	
	/* 원장 목록 */
	@SuppressWarnings("unchecked")
	List<CustomerLedgerVO> ledgerList = (List<CustomerLedgerVO>)request.getAttribute("ledgerList");
	
	/* 거래처 정보 */
	CustomerLedgerVO companyVO = (CustomerLedgerVO)ledgerList.get(0);

	String cust_nm = StringUtil.nvl(companyVO.getCust_nm()); // 거래처 명
	String cust_id = StringUtil.nvl(companyVO.getCust_id()); // 거래처 코드
	String president = StringUtil.nvl(companyVO.getPresident()); // 대표자명
	String sawon_nm = StringUtil.nvl(companyVO.getSawon_nm()); // 담당자명
	String vou_no = StringUtil.nvl(companyVO.getVou_no()); // 사업자번호
	String tel = StringUtil.nvl(companyVO.getTel()); // 전화번호
	
	if (!"".equals(vou_no) && vou_no.length() == 10) {
		vou_no = vou_no.substring(0, 3) + "-" + vou_no.substring(3, 5) + "-" + vou_no.substring(5, 10);
	}
	
	/* 합계정보 */
	CustomerLedgerVO sumLedger = (CustomerLedgerVO)request.getAttribute("sumLedger");
	
	double month_total_amt = 0;
	double month_total_vat = 0;
	double month_total_tot = 0;
	double month_total_sukum = 0;
	
	String total_amt = StringUtil.makeMoneyType(sumLedger.getTotal_amt()); // 공급가액
	String total_vat = StringUtil.makeMoneyType(sumLedger.getTotal_vat()); // 세액
	String total_tot = StringUtil.makeMoneyType(sumLedger.getTotal_tot()); // 합계금액
	String total_sukum = StringUtil.makeMoneyType(sumLedger.getTotal_sukum()); // 수금액
	String total_rem_amt = "0";

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>거래처원장</title>
<meta http-equiv="imagetoolbar" content="no" />
<style type="text/css">
/* html, body {height:100%;} */
body {margin:0; padding:0; font-family:Dotum, '돋움', verdana, sans-serif; color:#000; font-size:11px; /* background:url('logo.gif') no-repeat center 50%; */}
form, div, p, h1, h2, h3, h4, h5, h6, dl, dt, dd, ul, ol, li, blockquote, th, td {margin:0; padding:0; border:0 none;}
img {border:0 none;}
table {border-collapse:collapse; table-layout:fixed; margin:0; padding:0; border:0;}

table.header {clear:both; width:100%;}
table.header td {padding:4px 5px 2px;}
table.header td.subject {font-weight:bold; font-size:13px; text-decoration:underline; text-align:center;}
table.header td.center {text-align:center;}
table.header td.right {text-align:right;}
table.header td.bold {font-weight:bold;}

table.list {clear:both; margin:15px 0 30px; width:100%; border-bottom:3px solid #000;}
table.list th {padding:5px 5px 4px; border-top:3px solid #000; border-bottom:3px solid #000; background-color:#eee; border-right:2px solid #fff;}
table.list td {text-align:right; padding:4px 5px 2px; line-height:1.4; white-space:nowrap; /*word-wrap:break-word; word-break: break-all;*/ overflow:hidden; text-overflow:clip;}
table.list td span {display:inline-block; margin-right:8px;}
table.list td.center {text-align:center;}
table.list td.left {text-align:left;}
table.list td.bold {font-weight:bold;}
table.list tr.mtotal td {border-top:1px solid #000;}
table.list tr.mtotal td.noline {border-top:0 none;}

table.list th.last, 
table.list td.last {border-right:0 none;}
p.bg_logo {position:fixed; z-index:-1; text-align:center;}
p.bg_logo img {width:1020px; height:717px; text-align:center; margin:0 auto;}
.wrap_btn_group {position:absolute; top:15px; right:10px;}
.wrap_btn_group .btn_group button {border:1px solid #818181; background-color:#878787; color:#fff; font-size:11px;}
button {background-color:#FBFBFB; border-top:1px solid #E9E9E9; border-left:1px solid #E9E9E9; border-right:1px solid #C4C4C4; border-bottom:1px solid #C4C4C4; cursor:pointer; padding:0px 5px 0; height:20px; font-family:"NanumGothic"; vertical-align:middle;}
#wrap {margin-top:10px;}

@media print {
	@page {size: 29.7cm 21cm;}
	body {font-size:9px; font-family:Gulim, '굴림', verdana, sans-serif; margin:10px 0;}
	#wrap {page:a4sheet; /* page-break-after:always; */ margin-top:0;}
	table.header td.subject {font-size:13px;}
	table.list {margin:15px 0 0;}
	table.list th {font-size:10px; background-color:#eee;}
	.wrap_btn_group {display:none;}
	p.bg_logo img {width:976px; height:630px; text-align:center; margin:0 auto;}

}
</style>
<!-- script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/common.js" charset="utf-8"></script -->
</head>
<body>
<div id="wrap">
	<p class="bg_logo"><img src="<%=ONLINE_WEB_ROOT %>/img/bg_logo.gif" alt="하나제약주식회사" /></p>
	<!-- start header -->
	<table class="header">
	<colgroup>
		<col style="width:20%" />
		<col style="width:19%" />
		<col />
		<col style="width:21%" />
		<col style="width:15%" />
	</colgroup>
	<tr>
		<td class="subject" colspan="5">거 래 처 원 장</td>
	</tr>
	<tr>
		<td class="center bold" colspan="5">조회기간: <%=ad_fr_date %> ~ <%=ad_to_date %></td>
	</tr>
	<tr>
		<td colspan="4" class="bold"><%=cust_nm %></td>
		<td></td>
	</tr>
	<tr>
		<td>대표자 : <strong><%=president %></strong></td>
		<td>담당자 : <%=sawon_nm %></td>
		<td>사업자번호 : <%=vou_no %></td>
		<td>전화번호 : <%=tel %></td>
		<td>DATE : <%=(String)request.getAttribute("today") %></td>
	</tr>
	</table>
	<!-- end header -->

	<!-- start 일자별 목록 테이블 -->
	<table class="list">
	<colgroup>
		<col style="width:8%;" />
		<col style="width:7%;" />
		<col style="width:5%;" />
		<col style="width:11%;" />
		<col style="width:5%;" />
		<col style="width:4%;" />
		<col style="width:6%;" />
		<col style="width:7%;" />
		<col style="width:6%;" />
		<col style="width:8%;" />
		<col style="width:8%;" />
		<col style="width:8%;" />
		<col style="width:6%;" />
		<col />
	</colgroup>
	<thead>
	<tr>
		<th>일자</th>
		<th colspan="2">납품처</th>
		<th>품목</th>
		<th>단위</th>
		<th>수량</th>
		<th>단가</th>
		<th>공급가액</th>
		<th>세액</th>
		<th>합계금액</th>
		<th>수금</th>
		<th>잔액</th>
		<th>매출할인</th>
		<th class="last">비고</th>
	</tr>
	</thead>
	<tbody>
	<%
		for(int i = 0; i < ledgerList.size(); i++){
			
			CustomerLedgerVO ledgerVO = ledgerList.get(i);
			String ymd = StringUtil.nvl(ledgerVO.getYmd());
			String rcust_nm = StringUtil.nvl(ledgerVO.getRcust_nm());
			String item_nm = StringUtil.nvl(ledgerVO.getItem_nm());
			String standard = StringUtil.nvl(ledgerVO.getStandard());
			String qty = StringUtil.nvl(ledgerVO.getQty());
			String danga = StringUtil.makeMoneyTypeInt(ledgerVO.getDanga());
			String amt = StringUtil.makeMoneyTypeInt(ledgerVO.getAmt());
			String vat = StringUtil.makeMoneyTypeInt(ledgerVO.getVat());
			String tot = StringUtil.makeMoneyTypeInt(ledgerVO.getTot());
			String sukum = StringUtil.makeMoneyTypeInt(ledgerVO.getSukum());
			String rem_amt = StringUtil.makeMoneyTypeInt(ledgerVO.getRem_amt());
			String dc_amt = StringUtil.makeMoneyTypeInt(ledgerVO.getDc_amt()); 
			String bigo = StringUtil.nvl(ledgerVO.getBigo());
			
			month_total_amt = month_total_amt + Integer.parseInt(ledgerVO.getAmt());
			month_total_vat = month_total_vat + Integer.parseInt(ledgerVO.getVat());
			month_total_tot = month_total_tot + Integer.parseInt(ledgerVO.getTot());
			month_total_sukum = month_total_sukum + Integer.parseInt(ledgerVO.getSukum());
			
	%>
		<tr>
			<td class="center"><%=ymd %></td>
			<td colspan="2" class="left"><%=rcust_nm %></td>
			<td class="left"><%=item_nm %></td>
			<td class="center"><%=standard %></td>
			<td><%=qty %></td>
			<td><%=danga %></td>
			<td><%=amt %></td>
			<td><%=vat %></td>
			<td><%=tot %></td>
			<td><%=sukum %></td>
			<td><%=rem_amt %></td>
			<td><%=dc_amt %></td>
			<td class="last center"><%=bigo %></td>
		</tr>
	<%
			/* 다음 row 정보와 비교해서 조회년월이 다를 경우(년월이 바뀌었을 경우) 월 계 표시해준다. */
			
			boolean monthTotalFlag = false;
	
			/* 마지막 row일 경우 */
			if (i == ledgerList.size() - 1){
				total_rem_amt = rem_amt; // 합계 셋팅
				monthTotalFlag = true;
			} else {
				String currentYm = StringUtil.nvl(ledgerVO.getYm()); // 현재 row 년월
				String nextYm = StringUtil.nvl(ledgerList.get(i+1).getYm()); // 다음 row 년월
				
				if (!currentYm.equals(nextYm)) {
					monthTotalFlag = true;
				}
			}
			
			if (monthTotalFlag) {
	%>
		<!-- start 월 계 -->
		<tr class="mtotal">
			<td class="noline"></td>
			<td class="noline"></td>
			<td class="bold">월&nbsp;계</td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td><%=StringUtil.makeMoneyType(month_total_amt) %></td>
			<td><%=StringUtil.makeMoneyType(month_total_vat) %></td>
			<td><%=StringUtil.makeMoneyType(month_total_tot) %></td>
			<td><%=StringUtil.makeMoneyType(month_total_sukum) %></td>
			<td><%=rem_amt %></td>
			<td></td>
			<td class="last center"></td>
		</tr>
		<!-- end 월 계 -->
	<%
	
				/* 월 합계 정보 초기화 */
				month_total_amt = 0;
				month_total_vat = 0;
				month_total_tot = 0;
				month_total_sukum = 0;
				
			}
		}
	%>

	<!-- start 합 계 -->
	<tr class="mtotal">
		<td class="noline"></td>
		<td class="noline"></td>
		<td class="bold">합&nbsp;계</td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>
		<td><%=total_amt %></td>
		<td><%=total_vat %></td>
		<td><%=total_tot %></td>
		<td><%=total_sukum %></td>
		<td><%=total_rem_amt %></td>
		<td></td>
		<td class="last center"></td>
	</tr>
	<!-- end 합 계 -->

	</tbody>
	</table>
	<!-- end 일자별 목록 테이블 -->
</div>

<!-- 임시 인쇄하기 버튼 -->
<div id="buttonArea" class="wrap_btn_group">
	<div class="btn_group ta_r">
		<button type="button" onclick="javascript:window.print();" >출력</button>&nbsp;
		<button type="button" onclick="javascript:self.close();" >닫기</button>
	</div>
</div>
</body>
</html>