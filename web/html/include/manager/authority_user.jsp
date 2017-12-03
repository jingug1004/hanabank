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
		<div class="inner_cont">
			<div class="wrap_btn_group reloc">
				<div class="btn_group ta_r">
					<button disabled="disabled">조회</button>
					<button>입력</button>
					<button>저장</button>
					<button>삭제</button>
					<button>인쇄</button>
					<button>엑셀</button>
					<button>닫기</button>
				</div>
			</div>
			<div class="wrap_search02 w967 m0auto">
				<label class="ml10">거래처코드/명</label>
				<input type="text" class="w350" />
			</div>
			<div class="box_type01 h460 w967 m0auto">
				
			</div>
		</div>
		<!-- ##### content end ##### -->
		
		</div>
	</div>
	
	<%@include file="../../include/footer.jsp"%>
</body>
</html>