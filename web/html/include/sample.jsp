<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
	<%@include file="../include/head.jsp"%>
</head>
<body>
	<%@include file="../include/header.jsp"%>
	
	<div class="wrap_con easyui-tabs">
		<div class="content" title="Main">
		
		<!-- ##### content start ##### -->
		
			<div class="wrap_search">
				<div class="search">
					<label>조회기간</label>
					<p class="wrap_date">
						<input type="text" />
						<button class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					~
					<p class="wrap_date">
						<input type="text" />
						<button class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					
					<label>거래처</label>
					<input type="text" class="w120" />
					<button class="btn_search"><span class="blind">찾기</span></button>
					<input type="text" class="w140" />
					~
					<input type="text" class="w120" />
					<button class="btn_search"><span class="blind">찾기</span></button>
					<input type="text" class="w140" />
					
					<div class="wrap_search_btn">
						<button class="btn_type01">조회</button>
					</div>
				</div>
			</div>
			
			<div class="wrap_search">
				<div class="search">
					<div class="float_l search_type02">
						<label class="w70 ta_r">전월잔고</label>
						<input type="text" class="w140" />
						
						<label class="w70 ta_r">미도래(자수)</label>
						<input type="text" class="w140" />
						
						<label class="w70 ta_r">현잔고</label>
						<input type="text" class="w140" />
						
						<label class="w70 ta_r">총여신</label>
						<input type="text" class="w140" /><br />
						
						<label class="w70 ta_r">금월판매</label>
						<input type="text" class="w140" />
						
						<label class="w70 ta_r">미도래(타수)</label>
						<input type="text" class="w140" />
						
						<label class="w70 ta_r">담보확보액</label>
						<input type="text" class="w140" />
						
						<label class="w70 ta_r">담보확보율</label>
						<input type="text" class="w140" />
					</div>
					<div class="wrap_search_btn">
						<button class="btn_type02">조회</button>
					</div>
				</div>
			</div>
			
			<div class="wrap_search">
				<div class="search">
					<div class="float_l">
						<label class="w70">주문요청일</label>
						<p class="wrap_date">
							<input type="text" />
							<button class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						~
						<p class="wrap_date">
							<input type="text" />
							<button class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						
						<label>약품구분</label>
						<input type="radio" name="select_drug" />
						<span>일반약품</span>
						<input type="radio" name="select_drug" />
						<span>항정신성약품</span><br />
						
						<label class="w70">판매처</label>
						<input type="text" class="w140" />
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input type="text" class="w265" /><br />
						
						<label class="w70">요청사항</label>
						<input type="text" class="w435"/>
					</div>
					<div class="float_r">
						<label class="w70">도매담당자</label>
						<input type="text" class="w350" /><br />
						<label class="w70">병원담당자</label>
						<input type="text" class="w350" /><br />
						<label class="w70">총담보</label>
						<input type="text" class="w120" />
						<label class="w70 ml10">주문가능액</label>
						<input type="text" class="w140" />
					</div>
				</div>
			</div>
			
			<div class="wrap_search">
				<div class="search">
					<div class="float_l">
						<label class="w70">조회유형</label>
						<input type="radio" name="select_type" />
						<span>파트별</span>
						<input type="radio" name="select_type" />
						<span>영업지점별</span>
						<input type="radio" name="select_type" />
						<span>사원별</span><br />
						
						<label class="w70">파트</label>
						<input type="text" class="w140" />
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input type="text" class="w265" /><br />
						
						<label class="w70">부서</label>
						<input type="text" class="w140" />
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input type="text" class="w265" /><br />
						
						<label class="w70">사원</label>
						<input type="text" class="w140" />
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input type="text" class="w265" /><br />
					</div>
					<div class="float_l ml10">
						<label class="w70">실적년월</label>
						<input type="text" class="w280" />
						<span class="result_txt">실적율 (원내 / 원외 / 병원)</span>
						
						<label class="w70">판매</label>
						<input type="text" class="w70" /> % 
						<input type="text" class="w70" /> % 
						<input type="text" class="w70" /> % 
						<br />
						<label class="w70">수금</label>
						<input type="text" class="w70" /> % 
						<input type="text" class="w70" /> % 
						<input type="text" class="w70" /> % 
					</div>
					<div class="wrap_search_btn">
						<button class="btn_type04">조회</button>
					</div>
				</div>
			</div>
			
			<div class="wrap_search">
				<div class="search">
					<div class="float_l">
						<label class="w70">주문요청일</label>
						<p class="wrap_date">
							<input type="text" />
							<button class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						~
						<p class="wrap_date">
							<input type="text" />
							<button class="btn_date"><span class="blind">달력보기</span></button>
						</p><br />
						
						<label class="w70">판매처</label>
						<input type="text" class="w140" />
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input type="text" class="w230" />
					</div>
					<div class="float_l ml10">
						<label class="w70">도매담당자</label>
						<input type="text" class="w350" /><br />
						<label class="w70">총담보</label>
						<input type="text" class="w120" />
						<label class="w70 ml10">주문가능액</label>
						<input type="text" class="w140" />
					</div>
					<div class="wrap_search_btn">
						<button class="btn_type02">조회</button>
					</div>
				</div>
			</div>
			
			<div class="wrap_search">
				<div class="search">
					<label class="w70">주문요청일</label>
					<p class="wrap_date">
						<input type="text" />
						<button class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					~
					<p class="wrap_date">
						<input type="text" />
						<button class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					
					<label>부서명</label>
					<input type="text" class="w100" />
					<input type="text" class="w100" />
					
					<label>사원명</label>
					<input type="text" class="w100" />
					<input type="text" class="w100" />
					
					<input type="checkbox" />
					<label>본인자료만 보기</label>
					
					<div class="wrap_search_btn">
						<button class="btn_type01">조회</button>
					</div>
				</div>
			</div>
			
			<div class="wrap_search">
				<div class="search">
					<div class="float_l">
						<label class="w70">주문요청일</label>
						<p class="wrap_date">
							<input type="text" />
							<button class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						~
						<p class="wrap_date">
							<input type="text" />
							<button class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						
						<label class="ml10">조회구분</label>
						<select class="w100">
							<option>거래처</option>
						</select><br />
						
						<label class="w70">판매처</label>
						<input type="text" class="w140" />
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input type="text" class="w230" />
						~
						<input type="text" class="w140" />
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input type="text" class="w230" />
					</div>
					
					<div class="wrap_search_btn">
						<button class="btn_type02">조회</button>
					</div>
				</div>
			</div>
			
			<div class="wrap_search">
				<div class="search">
					<label class="w70">주문요청일</label>
					<p class="wrap_date">
						<input type="text" />
						<button class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					~
					<p class="wrap_date">
						<input type="text" />
						<button class="btn_date"><span class="blind">달력보기</span></button>
					</p>
					
					<label class="ml10">주문상태</label>
					<input type="checkbox" />
					<span>접수</span>
					<input type="checkbox" />
					<span>승인</span>
					<input type="checkbox" />
					<span>반려</span>
				</div>
			</div>
			
			<div class="wrap_search">
				<div class="search">
					<div class="float_l">
						<label class="w70">주문일</label>
						<p class="wrap_date">
							<input type="text" />
							<button class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						~
						<p class="wrap_date">
							<input type="text" />
							<button class="btn_date"><span class="blind">달력보기</span></button>
						</p><br />
						
						<label class="w70">주문구분</label>
						<input type="radio" name="select_order" />
						<span>온라인주문</span>
						<input type="radio" name="select_order" />
						<span>PDA주문</span>
					</div>
					<div class="float_l ml10">
						<label class="w50">주문상태</label>
						<input type="checkbox" />
						<span>접수</span>
						<input type="checkbox" />
						<span>승인</span>
						<input type="checkbox" />
						<span>반려</span>
						
						<label class="ml10">여신규정</label>
						<input type="checkbox" />
						<span>이내</span>
						<input type="checkbox" />
						<span>초과</span>
						<input type="checkbox" />
						<span>예외</span>
						<input type="checkbox" />
						<span>전체</span><br />
						
						<label class="w50">거래처</label>
						<input type="text" class="w140" />
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input type="text" class="w230" />
					</div>
					<div class="float_l ml10">
						<label>주문위반</label>
						<select class="w100">
							<option>전체</option>
						</select><br />
						
						<label>팀장반려</label>
						<select class="w100">
							<option>전체</option>
						</select>
					</div>
					<div class="wrap_search_btn">
						<button class="btn_type02">조회</button>
					</div>
				</div>
			</div>
			
			<div class="wrap_search">
				<div class="search">
					<div class="float_l">
						<label class="w70">주문일</label>
						<p class="wrap_date">
							<input type="text" />
							<button class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						<select class="w50">
							<option>9</option>
						</select>
						<select class="w50">
							<option>30</option>
						</select>
						~
						<p class="wrap_date">
							<input type="text" />
							<button class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						<select class="w50">
							<option>9</option>
						</select>
						<select class="w50">
							<option>30</option>
						</select>
						
						<label class="ml10">승인일</label>
						<p class="wrap_date">
							<input type="text" />
							<button class="btn_date"><span class="blind">달력보기</span></button>
						</p>
						
						<label class="ml10">월평균주문월</label>
						<input type="text" class="w80" /><br />
						
						<label class="w70">수량한도</label>
						<select class="w125">
							<option>이내(승인가)</option>
						</select>
						
						<label class="ml10">승인구분</label>
						<select class="w125">
							<option>대기</option>
						</select>
						
						<label class="ml10">주문구분</label>
						<select class="w125">
							<option>온라인주문</option>
						</select>
						
						<label class="ml10">여신규정</label>
						<select class="w125">
							<option>대기</option>
						</select>
						
						<input type="checkbox" />
						<label>선입금거래처</label><br />
						
						<label class="w70">거래처</label>
						<input type="text" class="w140" />
						<button class="btn_search"><span class="blind">찾기</span></button>
						<input type="text" class="w435" />
						
						<label class="ml10">간납구분</label>
						<select class="w125">
							<option>일반</option>
						</select>
					</div>
					<div class="wrap_search_btn">
						<button class="btn_type03">조회</button>
					</div>
				</div>
			</div>
			
			<div class="wrap_search">
				<div class="search">
					<label>거래처</label>
					<input type="text" class="w80" />
					<span>(약)원광메디칼써플라이어</span>
					
					<label class="ml10">거래구분</label>
					<select class="w100">
						<option>거래</option>
					</select>
					
					<div class="wrap_search_btn">
						<button class="btn_type01">검색</button>
					</div>
				</div>
			</div>
			
			<div class="wrap_btn_group">
				<div class="btn_group">
					<button disabled="disabled">조회</button>
					<button>입력</button>
					<button>저장</button>
					<button>삭제</button>
					<button>인쇄</button>
					<button>엑셀</button>
					<button>닫기</button>
				</div>
			</div>
			
			<div class="wrap_btn_group">
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
			
			<div class="wrap_btn_group">
				<div class="btn_group">
					<div class="float_l">
						<button>이전화면</button>
						<button>다음화면</button>
					</div>
					<div class="float_r ta_r">
						<button disabled="disabled">조회</button>
						<button>입력</button>
						<button>저장</button>
						<button>삭제</button>
						<button>인쇄</button>
						<button>엑셀</button>
						<button>닫기</button>
					</div>
				</div>
			</div>
			
			<div class="wrap_result_group">
				<div class="result_group">
					<label class="point">평균수량</label>
					<input type="text" class="w100" />
					<label class="point ml10">주문한도</label>
					<input type="text" class="w100" />
				</div>
			</div>
			
			<div class="wrap_result_group">
				<div class="result_group">
					<div class="float_r">
						<label class="point">공급가액</label>
						<input type="text" class="w100" />
						<label class="point ml10">세액</label>
						<input type="text" class="w100" />
						<label class="point ml10">공급총액</label>
						<input type="text" class="w100" />
					</div>
				</div>
			</div>
			
			<div class="wrap_result_group">
				<div class="result_group">
					<div class="float_l">
						<label class="point">평균수량</label>
						<input type="text" class="w100" />
						<label class="point ml10">주문한도</label>
						<input type="text" class="w100" />
					</div>
					<div class="float_r">
						<label class="point">공급가액</label>
						<input type="text" class="w100" />
						<label class="point ml10">세액</label>
						<input type="text" class="w100" />
						<label class="point ml10">공급총액</label>
						<input type="text" class="w100" />
					</div>
				</div>
			</div>
		
		<!-- ##### content end ##### -->
		
		</div>
	</div>
	
	<%@include file="../include/footer.jsp"%>
</body>
</html>