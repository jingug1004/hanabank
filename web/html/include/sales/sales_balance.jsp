<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../../include/head.jsp"%>
</head>
<body>
	<%@include file="../../include/header.jsp"%>
	
	<div class="wrap_con easyui-tabs">
		<div class="content" title="Main">
		
		<!-- ##### content start ##### -->
		<div class="wrap_search mbn">
				<div class="search">
				<label>거래처</label>
				<input class="w140" type="text">
				<button class="btn_search"><span class="blind">찾기</span></button>
				<input class="w265" type="text">
				
				<label class="ml10">거래구분</label>
				<input type="radio" name="cle" id="cle1" /><label for="cle1">담보거래처</label>
				<input type="radio" name="cle" id="cle2" /><label for="cle2">전체거래처</label>
				
				<div class="wrap_search_btn">
					<button class="btn_type01">검색</button>
				</div>
			</div>
		</div>	
		<div class="inner_cont sb_cont">		
			<div class="wrap_btn_group mb10">
				<div class="btn_group ta_r">
					<button>조회</button>
					<button>인쇄</button>
					<button>엑셀</button>
					<button>닫기</button>
				</div>
			</div>		
			<div class="wrap_customer01">
				<div class="wrap_search">
					<div class="box_type01 float_l">
					
					</div>
					<div class="float_r">
						<div class="search">
							<div class="search_type02">
								<label class="w46 ta_r">전월잔고</label>
								<input type="text" class="w140 ta_right ipt_disable" id="before_amt" readonly="">
								
								<label class="w70 ta_r">미도래(자수)</label>
								<input type="text" class="w140 ta_right ipt_disable" id="jasu_amt" readonly="">
								
								<label class="w70 ta_r">현잔고</label>
								<input type="text" class="w140 ta_right ipt_disable" id="cur_amt" readonly="">
								
								<label class="w70 ta_r">총여신</label>
								<input type="text" class="w140 ta_right ipt_disable" id="sum_cur_jasu" readonly=""><br>
								
								<label class="w46 ta_r">금월판매</label>
								<input type="text" class="w140 ta_right ipt_disable" id="sale_amt" readonly="">
								
								<label class="w70 ta_r">미도래(타수)</label>
								<input type="text" class="w140 ta_right ipt_disable" id="tasu_amt" readonly="">
								
								<label class="w70 ta_r">담보확보액</label>
								<input type="text" class="w140 ta_right ipt_disable" id="sale_dambo_amt" readonly="">
								
								<label class="w70 ta_r">담보확보율</label>
								<input type="text" class="w140 ta_right ipt_disable" id="rate_dmbo_cur" readonly="">
							</div>	
						</div>
						<div class="box_type02 h372 mt15">
										
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- ##### content end ##### -->
		
		</div>
	</div>
	
	<%@include file="../../include/footer.jsp"%>
</body>
</html>