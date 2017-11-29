<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%--
 * @파일명 : companyInfo.jsp   
 * @메뉴명 : 온라인발주 > 회사정보            
 * @최초작성일 : 2014/10/29            
 * @author : 우정아                  
 * @수정내역 : 
--%>
<%@ include file ="/common/path.jsp" %>
<%@ page import="com.hanaph.saleon.order.vo.CompanyVO" %>
<%@ page import="com.hanaph.saleon.common.utils.StringUtil" %>

<!DOCTYPE html>
<html lang="ko">
<head>
	<%@ include file ="/include/head.jsp" %>
	<script type="text/javascript" src="<%=ONLINE_WEB_ROOT%>/js/formCheck.js"></script>
	<script type="text/javascript">
	
	var saleActionFlag = true;		//기능(엑셀, 인쇄) 제어를 위한 전역변수
	var submit = false;				//중복 저장 방지
	
	/* 화면의 Dom 객체가 모두 준비되었을 때 */
	$(document).ready(function(){
		
		getCompany();
		
		/* 조회 버튼 클릭 */
		$("#p_retrieve").on("click",function(){
			getCompany();
		});
		
		/* 저장 버튼 클릭 */
		$("#p_save").on("click",function(){
			updateCompany();
		});
		
		/* 인쇄 버튼 클릭 */
		$("#p_print").on("click",function(){
			Commons.extraAction(saleActionFlag, 'print', '<%=ONLINE_ROOT%>/common/commonPrint.do', 'companyInfo_print');
		});
		
		/* 엑셀 버튼 클릭 */
		$("#p_excel").on("click",function(){
			Commons.extraAction(saleActionFlag, 'excel', '<%=ONLINE_ROOT%>/order/companyInfoExcelDown.do', '');
		});
		
		/* 닫기 버튼 클릭 */
		$("#p_close").on("click",function(){
			parent.Commons.closeTab('회사정보');
		});

	});
	
	/* 윈도우의 모든 객체/파일이 로드 완료되었을 때 */
	$(window).load(function(){
		/* 
		* 엔터키를 조회버튼이 비활성화 되지 않고, 엔터키 눌린 곳의 name속성값이 grid_count 아닐때  눌렀을 때 회사정보 조회 
		*/
		$("body").on("keydown", function(event){
			if($("#p_retrieve").prop('disabled') == false){
				if(event.keyCode==13 && event.target.name!="grid_count"){
					getCompany();
				return false;
				}
			}
		});
	});
	
	/** 
	*	저장 버튼 클릭시 회사정보 수정
	*/
	function updateCompany(){
		
		if ($('#tel').val()!='' && !formCheck.isValidPhone($('#tel').val())){
			alert("올바른 전화 번호가 아닙니다.");
			$('#tel').focus();
			return;
		}
		if ($('#hp').val()!='' && !formCheck.isValidPhone($('#hp').val())){
			alert("올바른 핸드폰 번호가 아닙니다.");
			$('#hp').focus();
			return;
		}
		if ($('#fax').val()!='' && !formCheck.isValidPhone($('#fax').val())){
			alert("올바른 팩스 번호가 아닙니다.");
			$('#fax').focus();
			return;
		}
		
		if ($('#email').val()!='' && !formCheck.isValidEmail($('#email').val())){
			alert("올바른 이메일 주소가 아닙니다.");
			$('#email').focus();
			return;
		}
		
		
		if(!submit){

			$.ajax({
				type:"POST",
				data:$("#frm").serialize(),
				url:"<%=ONLINE_ROOT%>/order/updateCompanyAjax.do",
				dataType:"json",
				success:function(data){
					if(data.result =="Y"){
						alert("수정하였습니다.");
						
					}else{
						alert("수정되지 않았습니다. 다시 시도해 주세요.");
					}
				},complete:function(){
					getCompany();					
				}
			});
		}else if(submit){
			alert("저장중입니다.");
		}
		
	}
	
	/* 
	* 회사정보 불러오기 
	*/
	function getCompany(){
		$.ajax({
			type:"POST",
			url:"<%=ONLINE_ROOT%>/order/companyInfoAjax.do",
			dataType:"json",
			success:function(data){
				if(data != null){

					$("#cust_id").val(data.cust_id);
					$("#cust_id2").html(data.cust_id);
					$("#cust_nm").html(data.cust_nm);
					$("#vou_no").html(data.vou_no);
					$("#president").html(data.president);
					$("#bupin_nov").html(data.bupin_nov);
					$("#uptae").html(data.uptae);
					$("#jongmok").html(data.jongmok);
					$("#zip").html(data.zip);
					$("#addr").html(data.addr);
					$("#tel").val(data.tel);
					$("#hp").val(data.hp);
					$("#fax").val(data.fax);
					$("#email").val(data.email);
					
					//print전용 영역에 값 세팅
					$("#cust_id_print").val(data.cust_id);
					$("#cust_id2_print").html(data.cust_id);
					$("#cust_nm_print").html(data.cust_nm);
					$("#vou_no_print").html(data.vou_no);
					$("#president_print").html(data.president);
					$("#bupin_nov_print").html(data.bupin_nov);
					$("#uptae_print").html(data.uptae);
					$("#jongmok_print").html(data.jongmok);
					$("#zip_print").html(data.zip);
					$("#addr_print").html(data.addr);
					$("#tel_print").html(data.tel);
					$("#hp_print").html(data.hp);
					$("#fax_print").html(data.fax);
					$("#email_print").html(data.email);

				}else{

					$("#tel").remove();
					$("#hp").remove();
					$("#fax").remove();
					$("#email").remove();
					
					alert("회사정보가 없습니다.");
					return;
				}
			}
		});
	}
	
	/* 
	*	엑셀 다운로드 
	*/
	function excelDownload(){
		Commons.goExcelDownload('<%=ONLINE_ROOT%>/order/companyInfoExcelDown.do');

	}
	</script>
	
</head>
<body>
	<form id="frm" name="frm">
	<input type="hidden" id="cust_id" name="cust_id" value="" />
	<!-- ##### content start ##### -->
	<div class="inner_cont">
		<div class="wrap_btn_group">
			<div class="btn_group ta_r">
				<%=WebUtil.createButtonArea(currPgmNoByUri, "p_")%>
			</div>
		</div>
		<div class="wrap_company box_type01 w967  m0auto mt25" id="companyInfo">		
		
			<table class="type01 re_style03">
				<colgroup>
					<col style="width:15%" />
					<col style="width:85%" />
				</colgroup>
				<tbody>
					<tr>
						<th class="no_border_l no_border_t">회사번호</th>
						<td class="no_border_r no_border_t" id="cust_id2"></td>
					</tr>
					<tr>
						<th class="no_border_l">회사명</th>
						<td class="no_border_r" id="cust_nm"></td>
					</tr>
					<tr>
						<th class="no_border_l">사업자번호</th>
						<td class="no_border_r" id="vou_no"></td>
					</tr>
					<tr>
						<th class="no_border_l">대표자명</th>
						<td class="no_border_r" id="president"></td>
					</tr>
					<tr>
						<th class="no_border_l">주민(법인)번호</th>
						<td class="no_border_r" id="bupin_nov"></td>
					</tr>
					<tr>
						<th class="no_border_l">업태</th>
						<td class="no_border_r" id="uptae"></td>
					</tr>
					<tr>
						<th class="no_border_l">종목</th>
						<td class="no_border_r" id="jongmok"></td>
					</tr>
					<tr>
						<th class="no_border_l">우편번호</th>
						<td class="no_border_r" id="zip"></td>
					</tr>
					<tr>
						<th class="no_border_l">주소</th>
						<td class="no_border_r" id="addr"></td>
					</tr>
					<tr>
						<th class="no_border_l">전화번호</th>
						<td class="no_border_r">
							<input type="text" class="input_size" id="tel" name="tel" value="" />
						</td>
					</tr>
					<tr>
						<th class="no_border_l">핸드폰번호</th>
						<td class="no_border_r">
							<input type="text" class="input_size" id="hp" name="hp" value="" />
						</td>
					</tr>
					<tr>
						<th class="no_border_l">팩스번호</th>
						<td class="no_border_r">
							<input type="text" class="input_size" id="fax" name="fax" value="" />
						</td>
					</tr>
					<tr>
						<th class="no_border_l">이메일주소</th>
						<td class="no_border_r">
							<input type="text" class="input_size" id="email" name="email" value="" />
						</td>
					</tr>
					<tr>
						<th class="no_border_l no_border_b">온라인입금 전용계좌</th>
						<td class="no_border_r no_border_b online_num">
							<span class="name_tit">* 예금주 : <span class="name_txt">하나제약</span></span>
							<span class="name_tit">* 입금은행 : <span class="name_txt">기업은행</span></span>
						</td>
					</tr>
				</tbody>			
			</table>
		</div>
		<p class="company_txt w967 mt30">※  전화번호 및 핸드폰, 팩스번호, 메일주소 등은 입력/수정하여 주시기 바라며, 이외 다른 변경사항은 담당자와 통화 후 관련서류를 제출하여 주시기 바랍니다.</p>
	</div>
	<!-- ##### content end ##### -->
	</form>
	
	<!-- ##### 인쇄전용 content start ##### -->
	<div class="wrap_company box_type01 w967  m0auto mt25" id="companyInfo_print" style="display:none">		
		
			<table class="type01 re_style03">
				<colgroup>
					<col style="width:15%" />
					<col style="width:85%" />
				</colgroup>
				<tbody>
					<tr>
						<th class="no_border_l no_border_t">회사번호</th>
						<td class="no_border_r no_border_t" id="cust_id2_print"></td>
					</tr>
					<tr>
						<th class="no_border_l">회사명</th>
						<td class="no_border_r" id="cust_nm_print"></td>
					</tr>
					<tr>
						<th class="no_border_l">사업자번호</th>
						<td class="no_border_r" id="vou_no_print"></td>
					</tr>
					<tr>
						<th class="no_border_l">대표자명</th>
						<td class="no_border_r" id="president_print"></td>
					</tr>
					<tr>
						<th class="no_border_l">주민(법인)번호</th>
						<td class="no_border_r" id="bupin_nov_print"></td>
					</tr>
					<tr>
						<th class="no_border_l">업태</th>
						<td class="no_border_r" id="uptae_print"></td>
					</tr>
					<tr>
						<th class="no_border_l">종목</th>
						<td class="no_border_r" id="jongmok_print"></td>
					</tr>
					<tr>
						<th class="no_border_l">우편번호</th>
						<td class="no_border_r" id="zip"></td>
					</tr>
					<tr>
						<th class="no_border_l">주소</th>
						<td class="no_border_r" id="addr_print"></td>
					</tr>
					<tr>
						<th class="no_border_l">전화번호</th>
						<td class="no_border_r" id="tel_print">
							<input type="text" class="input_size" id="tel" name="tel" value="" />
						</td>
					</tr>
					<tr>
						<th class="no_border_l">핸드폰번호</th>
						<td class="no_border_r" id="hp_print">
						</td>
					</tr>
					<tr>
						<th class="no_border_l">팩스번호</th>
						<td class="no_border_r" id="fax_print">
						</td>
					</tr>
					<tr>
						<th class="no_border_l">이메일주소</th>
						<td class="no_border_r" id="email_print">
						</td>
					</tr>
					<tr>
						<th class="no_border_l no_border_b">온라인입금 전용계좌</th>
						<td class="no_border_r no_border_b online_num">
							<span class="name_tit">* 예금주 : <span class="name_txt">하나제약</span></span>
							<span class="name_tit">* 입금은행 : <span class="name_txt">기업은행</span></span>
						</td>
					</tr>
				</tbody>			
			</table>
		</div>
	<!-- ##### 인쇄전용 content end ##### -->
	
	<%@include file="/include/footer.jsp"%>
</body>
</html>