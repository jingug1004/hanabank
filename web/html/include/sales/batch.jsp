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
			<div class="wrap_search">
				<div class="search">
					<div class="float_l">
						<label class="w70">조회유형</label>
						<input name="select_type" type="radio">
						<span>파트별</span>
						<input name="select_type" type="radio">
						<span>영업지점별</span>
						<input name="select_type" type="radio">
						<span>사원별</span><br>
						
						<label class="w70">파트</label>
						<input class="w140" type="text">
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input class="w265" type="text"><br>
						
						<label class="w70">부서</label>
						<input class="w140" type="text">
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input class="w265" type="text"><br>
						
						<label class="w70">사원</label>
						<input class="w140" type="text">
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input class="w265" type="text"><br>
					</div>
					<div class="float_l ml10">
						<label class="w70">실적년월</label>
						<input class="w280" type="text">
						<span class="result_txt">실적율 (원내 / 원외 / 병원)</span>
						
						<label class="w70">판매</label>
						<input class="w70" type="text"> % 
						<input class="w70" type="text"> % 
						<input class="w70" type="text"> % 
						<br>
						<label class="w70">수금</label>
						<input class="w70" type="text"> % 
						<input class="w70" type="text"> % 
						<input class="w70" type="text"> % 
					</div>
					<div class="wrap_search_btn">
						<button class="btn_type04">조회</button>
					</div>
				</div>
			</div>
			
			<div class="wrap_btn_group reloc2">
				<div class="btn_group">
					<div class="float_l">
						<button>이전화면</button>
						<button>다음화면</button>
					</div>
					<div class="float_r ta_r">
						<button>인쇄</button>
						<button>엑셀</button>
						<button>닫기</button>
					</div>
				</div>
			</div>
		<!-- ##### content start ##### -->
		<div class="inner_cont">
			<p>Batch Data 생성시각 : 2014/09/10 13:34:52</p>
			
			<div class="box_type01 h380">
				
			</div>
		</div>
		<!-- ##### content end ##### -->
		
		</div>
	</div>
	
	<%@include file="../../include/footer.jsp"%>
</body>
</html>