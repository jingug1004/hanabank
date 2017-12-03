<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="include/head.jsp"%>
</head>
<body>
	<%@include file="include/header.jsp"%>
	
	<div class="wrap_con easyui-tabs">
		<div class="content" title="Main">
 			<%//@include file="include/search.jsp"%>
 			<%//@include file="include/searchResult.jsp"%>
 			<%//@include file="include/detailView.jsp"%>
			
			<%//@include file="include/manager/program_insert.jsp"%>

 			<%@include file="include/sales/sales_charts.jsp"%>
 			<%@include file="include/order/order_register.jsp"%>

 			<%//@include file="include/sales/record_status_batch.jsp"%>
 			<%//@include file="include/order/account_status.jsp"%>

		</div>
	</div>
	
	<%@include file="include/footer.jsp"%>
</body>
</html>