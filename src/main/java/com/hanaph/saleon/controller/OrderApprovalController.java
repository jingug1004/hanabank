/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hanaph.saleon.business.service.OrderApprovalService;
import com.hanaph.saleon.business.vo.OrderApprovalJsonVO;
import com.hanaph.saleon.business.vo.OrderApprovalVO;
import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;

/**
 * <pre>
 * Class Name : OrderApprovalController.java
 * 설명 : 주문 승인 Controller
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 5.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 12. 5.
 */
@Controller
public class OrderApprovalController {
	
	private static final Logger logger = Logger.getLogger(CompanyCardMgmtContoller.class);
	
	@Autowired
	private OrderApprovalService orderApprovalService;
	
	/**
	 * <pre>
	 * 1. 개요     : 주문승인 메인
	 * 2. 처리내용 : 주문승인 메인 페이지를 반환한다.
	 * </pre>
	 * @Method Name : orderApprovalList
	 * @param request
	 * @return
	 */		
	@RequestMapping("/business/orderApprovalList.do")
	public ModelAndView orderApprovalList(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("business/orderApprovalList");
				
		return mav;
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 주문내역 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 주문내역 목록을 json 형태로 반환한다.
	 * </pre>
	 * @Method Name : orderApprovalGridList
	 * @param request
	 * @param response
	 * @throws IOException
	 */		
	@RequestMapping("/business/orderApprovalGridList.do")
	public void orderApprovalGridList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
		String searchType = StringUtil.nvl(request.getParameter("searchType"), "order"); // 조회기준 order : 주문일 / approval : 승인일
		String adt_fr_date = StringUtil.nvl(request.getParameter("adt_fr_date")); // 조회기간 fr
		String adt_fr_hour = StringUtil.nvl(request.getParameter("adt_fr_hour")); // fr 시간
		String adt_fr_minute = StringUtil.nvl(request.getParameter("adt_fr_minute")); // fr 분
		String adt_to_date = StringUtil.nvl(request.getParameter("adt_to_date").replace("-", "")); // 조회기간 to
		String adt_to_hour = StringUtil.nvl(request.getParameter("adt_to_hour")); // to 시간
		String adt_to_minute = StringUtil.nvl(request.getParameter("adt_to_minute")); // to 분
		String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
		String as_slip_gb = StringUtil.nvl(request.getParameter("as_slip_gb")); // 주문구분
		String as_limit_yn = StringUtil.nvl(request.getParameter("as_limit_yn")); // 여신규정
		String as_avg_month = StringUtil.nvl(request.getParameter("as_avg_month").replace("-", "")); // 월평균주문월
		String as_wiban_kind = StringUtil.nvl(request.getParameter("as_wiban_kind"), "%"); // 위반종류
		String as_pre_deposit = StringUtil.nvl(request.getParameter("as_pre_deposit"), "N"); // 선입금거래처
		String as_psb_gb = StringUtil.nvl(request.getParameter("as_psb_gb")); // 수량한도
		String as_part_gb = StringUtil.nvl(request.getParameter("as_part_gb"), "2"); // 간납구분
		String as_receipt_gb = StringUtil.nvl(request.getParameter("as_receipt_gb"), "0"); // 승인구분
		String sidx = StringUtil.nvl(request.getParameter("sidx"),"req_time"); // 정렬
		String sord = StringUtil.nvl(request.getParameter("sord"),""); // 정렬순 
		
		/*승인구분 전체 조회에 따른 검색조건 변경*/
		if("0".equals(as_receipt_gb)){
			as_receipt_gb = "'1','2','3'";
		} else {
			as_receipt_gb = "'"+as_receipt_gb+"'";
		}
		
		/*여신규정 전체 조회에 따른 검색조건 변경*/
		if("A".equals(as_limit_yn)){
			as_limit_yn = "'Y','N','E'";
		} else {
			as_limit_yn = "'"+as_limit_yn+"'";
		}
		
		/*parameter를 map에 setting*/
		paramMap.put("adt_fr_datetime", adt_fr_date.replace("-", "") + adt_fr_hour + adt_fr_minute); // 조회기간 fr
		paramMap.put("adt_fr_date", adt_fr_date); // 조회기간 fr
		paramMap.put("adt_to_datetime", adt_to_date.replace("-", "") + adt_to_hour + adt_to_minute); // 조회기간 to
		paramMap.put("adt_to_date", adt_to_date); // 조회기간 to
		paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
		paramMap.put("as_slip_gb", as_slip_gb); // 주문구분
		paramMap.put("as_limit_yn", as_limit_yn); // 여신규정
		paramMap.put("as_avg_month", as_avg_month); // 월평균주문월
		paramMap.put("as_wiban_kind", as_wiban_kind); // 위반종류
		paramMap.put("as_pre_deposit", as_pre_deposit); // 선입금거래처
		paramMap.put("as_psb_gb", as_psb_gb); // 수량한도
		paramMap.put("as_part_gb", as_part_gb); // 간납구분
		paramMap.put("as_receipt_gb", as_receipt_gb); // 승인구분 
		paramMap.put("searchType", searchType); // 조회구분
		paramMap.put("sidx", sidx); // 정렬
		paramMap.put("sord", sord); // 정렬순
		
		/*주문목록*/
		List<OrderApprovalVO> orderApprovalList = orderApprovalService.getOrderApprovalGridList(paramMap);
		
		/*주문목록 총 수*/
		OrderApprovalVO totalCountInfo = orderApprovalService.getOrderApprovalGridTotalCount(paramMap);
		
		if (orderApprovalList != null && orderApprovalList.size() > 0) {
			totalCountInfo.setTotal_cnt(orderApprovalList.get(0).getTotal_cnt());
		} else {
			totalCountInfo.setTotal_cnt(0);
		} 
		
		/*paging 연산*/
		int records = totalCountInfo.getTotal_cnt();
		
		/*returnVO*/
		OrderApprovalJsonVO orderApprovalJsonVO = new OrderApprovalJsonVO();
		
		orderApprovalJsonVO.setRecords(records); // 전체 수
		orderApprovalJsonVO.setRows(orderApprovalList); // list
		orderApprovalJsonVO.setTotalCountInfo(totalCountInfo); // 합계정보
		
		MarshallerUtil.marshalling("json", response, orderApprovalJsonVO);
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 주문세부내역 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 주문세부내역 목록을 json 형태로 반환한다.
	 * </pre>
	 * @Method Name : orderDetailGridList
	 * @param request
	 * @param response
	 * @throws IOException
	 */		
	@RequestMapping("/business/orderDetail.do")
	public void orderDetail(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
		String adt_fr_date = StringUtil.nvl(request.getParameter("adt_fr_date").replace("-", "")); // 조회기간 fr
		String adt_fr_hour = StringUtil.nvl(request.getParameter("adt_fr_hour")); // fr 시간
		String adt_fr_minute = StringUtil.nvl(request.getParameter("adt_fr_minute")); // fr 분
		String adt_to_date = StringUtil.nvl(request.getParameter("adt_to_date").replace("-", "")); // 조회기간 to
		String adt_to_hour = StringUtil.nvl(request.getParameter("adt_to_hour")); // to 시간
		String adt_to_minute = StringUtil.nvl(request.getParameter("adt_to_minute")); // to 분
		String ls_ymd = StringUtil.nvl(request.getParameter("ls_ymd").replace("-", "")); // 주문요청일
		String as_avg_month = StringUtil.nvl(request.getParameter("as_avg_month").replace("-", "")); // 월평균주문월
		String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
		String as_rcust_id = StringUtil.nvl(request.getParameter("as_rcust_id")); // 판매처 코드
		String as_gumae_no = StringUtil.nvl(request.getParameter("as_gumae_no").replace("-", "")); // 접수번호
		String as_slip_gb = StringUtil.nvl(request.getParameter("as_slip_gb")); // 주문구분
		String sidx = StringUtil.nvl(request.getParameter("sidx"),"item_id"); // 정렬
		String sord = StringUtil.nvl(request.getParameter("sord"),"asc"); // 정렬순
		
		/*parameter를 map에 setting*/
		paramMap.put("adt_fr_datetime", adt_fr_date + adt_fr_hour + adt_fr_minute); // 조회기간 fr
		paramMap.put("adt_fr_date", adt_fr_date); // 조회기간 fr
		paramMap.put("adt_to_datetime", adt_to_date + adt_to_hour + adt_to_minute); // 조회기간 to
		paramMap.put("adt_to_date", adt_to_date); // 조회기간 to
		paramMap.put("ls_ymd", ls_ymd); // 주문요청일
		paramMap.put("as_avg_month", as_avg_month); // 월평균주문월
		paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
		paramMap.put("as_rcust_id", as_rcust_id); // 판매처 코드
		paramMap.put("as_gumae_no", as_gumae_no); // 접수번호
		paramMap.put("as_slip_gb", as_slip_gb); // 주문구분
		paramMap.put("sidx", sidx); // 정렬
		paramMap.put("sord", sord); // 정렬순
		
		/*주문 상세 목록*/
		List<OrderApprovalVO> orderDetailList = orderApprovalService.getOrderDetailGridList(paramMap);
		
		/*주문 상세 목록 총 수*/
		OrderApprovalVO totalCountInfo = orderApprovalService.getOrderDetailGridTotalCount(paramMap);
		
		/*거래처 정보*/
		OrderApprovalVO customerInfo = orderApprovalService.getCustomerInfo(paramMap);
		
		/*승인 정보*/
		OrderApprovalVO approvalInfo = orderApprovalService.getApprovalInfo(paramMap);
		
		/*returnVO*/
		OrderApprovalJsonVO orderApprovalJsonVO = new OrderApprovalJsonVO();
		
		orderApprovalJsonVO.setRecords(totalCountInfo.getTotal_cnt());
		orderApprovalJsonVO.setRows(orderDetailList);
		orderApprovalJsonVO.setTotalCountInfo(totalCountInfo);
		orderApprovalJsonVO.setCustomerInfo(customerInfo);
		orderApprovalJsonVO.setApprovalInfo(approvalInfo);
		
		MarshallerUtil.marshalling("json", response, orderApprovalJsonVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 담보약속 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 담보약속 목록을 json 형태로 반환한다.
	 * </pre>
	 * @Method Name : promiseGridList
	 * @param request
	 * @param response
	 * @throws IOException
	 */		
	@RequestMapping("/business/promiseGridList.do")
	public void promiseGridList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
		String adt_fr_date = StringUtil.nvl(request.getParameter("adt_fr_date").replace("-", "")); // 조회기간 fr
		String adt_fr_hour = StringUtil.nvl(request.getParameter("adt_fr_hour")); // fr 시간
		String adt_fr_minute = StringUtil.nvl(request.getParameter("adt_fr_minute")); // fr 분
		String adt_to_date = StringUtil.nvl(request.getParameter("adt_to_date").replace("-", "")); // 조회기간 to
		String adt_to_hour = StringUtil.nvl(request.getParameter("adt_to_hour")); // to 시간
		String adt_to_minute = StringUtil.nvl(request.getParameter("adt_to_minute")); // to 분
		String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
		String sidx = StringUtil.nvl(request.getParameter("sidx"),""); // 정렬
		String sord = StringUtil.nvl(request.getParameter("sord"),"");  // 정렬순
		
		if ("".equals(sidx)) {
			sidx = "input_ymd";
			sord = "desc";
		}
		
		/*parameter를 map에 setting*/
		paramMap.put("adt_fr_date", adt_fr_date); // 조회기간 fr
		paramMap.put("adt_fr_datetime", adt_fr_date + adt_fr_hour + adt_fr_minute); // 조회기간 fr
		paramMap.put("adt_to_date", adt_to_date); // 조회기간 to
		paramMap.put("adt_to_datetime", adt_to_date + adt_to_hour + adt_to_minute); // 조회기간 to
		paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
		paramMap.put("sidx", sidx); // 정렬
		paramMap.put("sord", sord); // 정렬순
		
		/*담보약속 목록*/
		List<OrderApprovalVO> promiseList = orderApprovalService.getPromiseGridList(paramMap);
		
		/*returnVO*/
		OrderApprovalJsonVO orderApprovalJsonVO = new OrderApprovalJsonVO();
		
		orderApprovalJsonVO.setRows(promiseList);
		
		MarshallerUtil.marshalling("json", response, orderApprovalJsonVO);
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 승인 내용 저장
	 * 2. 처리내용 : 승인 내용을 저장한다.
	 * </pre>
	 * @Method Name : updateOrderApproval
	 * @param request
	 * @param response
	 * @throws IOException
	 */		
	@RequestMapping("/business/updateOrderApproval.do")
	public void updateOrderApproval(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, Object> paramMap = new HashMap<String, Object>();
		
		/*세션생성 세션에서 emp_code를 가져온다.*/
		HttpSession session = request.getSession(); // 세선 생성
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String emp_code = StringUtil.nvl(loginUserVO.getEmp_code());
		
		/*parameter*/
		String app_date = request.getParameter("app_date"); // 승인일
		String receipt_gb = request.getParameter("receipt_gb"); // 승인구분
		String return_desc = StringUtil.nvl(request.getParameter("return_desc")); // 반려사유
		String this_order_amt = StringUtil.nvl(request.getParameter("this_order_amt"),"0").replaceAll(",",""); // 현주문의공급총액
		String[] gumae_no = request.getParameterValues("gumae_no"); // 접수번호
		String[] app_no = request.getParameterValues("app_no"); // 승인번호
		String[] req_date = request.getParameterValues("req_date"); // 주문요청일
		String[] cust_id = request.getParameterValues("cust_id"); // 거래처 코드
		String[] cust_nm = request.getParameterValues("cust_nm"); // 거래처명
		String[] rcust_id = request.getParameterValues("rcust_id"); // 판매처 코드
		String[] rcust_nm = request.getParameterValues("rcust_nm"); // 판매처명
		String[] slip_gb = request.getParameterValues("slip_gb"); // 주문구분
		
		/*승인수량 수정을 위한 parameter*/
		String[] ymd = request.getParameterValues("ymd"); // 요청일
		String[] detail_gumae_no = request.getParameterValues("detail_gumae_no"); // 접수번호
		String[] input_seq = request.getParameterValues("input_seq"); // 등록번호
		String[] qty = request.getParameterValues("qty"); // 승인수량
		String[] amt = request.getParameterValues("amt"); // 공급가액
		String[] vat = request.getParameterValues("vat"); // 세액
		String[] dc_amt = request.getParameterValues("dc_amt"); // 세액
		
		/*parameter를 map에 setting*/
		paramMap.put("emp_code", emp_code); // 승인자 코드
		paramMap.put("app_date", app_date); // 승인일
		paramMap.put("receipt_gb", receipt_gb); // 승인구분
		paramMap.put("return_desc", return_desc); // 반려사유
		paramMap.put("thisorder_amt", this_order_amt); // 현주문의공급총액
		paramMap.put("gumae_no", gumae_no); // 접수번호
		paramMap.put("app_no", app_no); // 승인번호
		paramMap.put("req_date", req_date); // 주문요청일
		paramMap.put("cust_id", cust_id); // 거래처 코드
		paramMap.put("cust_nm", cust_nm); // 거래처명
		paramMap.put("rcust_id", rcust_id); // 판매처 코드
		paramMap.put("rcust_nm", rcust_nm); // 판매처명
		paramMap.put("slip_gb", slip_gb); // 주문구분
		paramMap.put("ymd", ymd); // 요청일
		paramMap.put("detail_gumae_no", detail_gumae_no); // 접수번호
		paramMap.put("input_seq", input_seq); // 등록번호
		paramMap.put("qty", qty); // 승인수량
		paramMap.put("amt", amt); // 공급가액
		paramMap.put("vat", vat); // 세액
		paramMap.put("dc_amt", dc_amt); // 세액
		
		/*returnVO*/
		OrderApprovalVO orderApprovalVO = new OrderApprovalVO(); // 수행 결과
		OrderApprovalJsonVO returnVO = new OrderApprovalJsonVO(); // returnVO
		
		/*이월작업 체크*/
		int storeLocCount = orderApprovalService.getStoreLocCount();
		
		/*이월작업 여부에 따라 수행*/
		if (0 == storeLocCount && !"cancel_order".equals(receipt_gb)) { // 이월작업이 있을 경우
			
			String[] resultCodeArr = {"F_002"}; // 에러 코드
			String[] resultMessageArr = {"재고 이월작업을 먼저 하셔야 합니다."}; // 에러 메세지
			
			returnVO.setUpdateResult(false); // 프로세스 결과
			returnVO.setResultCodeArr(resultCodeArr); // 에러 코드
			returnVO.setResultMessageArr(resultMessageArr); // 에러 메세지
			
		} else { // 이월작업이 없을 경우
			try {
				
				/*승인구분에 따라 수행*/
				if ("cancel_order".equals(receipt_gb)) { // 취소일 경우
					orderApprovalVO = orderApprovalService.cancelOrderApproval(paramMap);
				} else { // 승인, 반려, 추가승인일 경우
					orderApprovalVO = orderApprovalService.updateOrderApproval(paramMap);
				}
				
				returnVO.setUpdateResult(true); // 전체 프로세스 결과
				returnVO.setRowResultArr(orderApprovalVO.getRowResultArr()); // 각 row 별 프로세스 결과
				returnVO.setResultCodeArr(orderApprovalVO.getResultCodeArr()); // 각 row 별 결과 코드
				returnVO.setResultMessageArr(orderApprovalVO.getResultMessageArr()); // 각 row 별 결과 메세지
				
			} catch (Exception e) {
				logger.error(e.getMessage());
				e.printStackTrace();
				
				String[] resultCodeArr = {"F_001"}; // 에러 코드
				String[] resultMessageArr = {"오류 발생 : " + e}; // 에러 메세지
				
				returnVO.setUpdateResult(false); // 프로세스 결과
				returnVO.setResultCodeArr(resultCodeArr); // 에러 코드
				returnVO.setResultMessageArr(resultMessageArr); // 에러 메세지
			}
		}
		
		MarshallerUtil.marshalling("json", response, returnVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 주문내역 excel 다운로드
	 * 2. 처리내용 : 검색조건에 따른 주문내역 목록을 excel 파일로 반환한다.
	 * </pre>
	 * @Method Name : orderApprovalGridListExcelDown
	 * @param request
	 * @param response
	 * @throws Exception
	 */		
	@SuppressWarnings("rawtypes")
	@RequestMapping("/business/orderApprovalGridListExcelDown.do")
	public void orderApprovalGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
		String searchType = StringUtil.nvl(request.getParameter("searchType"), "order"); // 조회기준 order : 주문일 / approval : 승인일
		String adt_fr_date = StringUtil.nvl(request.getParameter("adt_fr_date").replace("-", "")); // 조회기간 fr
		String adt_fr_hour = StringUtil.nvl(request.getParameter("adt_fr_hour")); // fr 시간
		String adt_fr_minute = StringUtil.nvl(request.getParameter("adt_fr_minute")); // fr 분
		String adt_to_date = StringUtil.nvl(request.getParameter("adt_to_date").replace("-", "")); // 조회기간 to
		String adt_to_hour = StringUtil.nvl(request.getParameter("adt_to_hour")); // to 시간
		String adt_to_minute = StringUtil.nvl(request.getParameter("adt_to_minute")); // to 분
		String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
		String as_slip_gb = StringUtil.nvl(request.getParameter("as_slip_gb")); // 주문구분
		String as_limit_yn = StringUtil.nvl(request.getParameter("as_limit_yn")); // 여신규정
		String as_avg_month = StringUtil.nvl(request.getParameter("as_avg_month").replace("-", "")); // 월평균주문월
		String as_wiban_kind = StringUtil.nvl(request.getParameter("as_wiban_kind"), "%"); // 위반종류
		String as_pre_deposit = StringUtil.nvl(request.getParameter("as_pre_deposit"), "N"); // 선입금거래처
		String as_psb_gb = StringUtil.nvl(request.getParameter("as_psb_gb")); // 수량한도
		String as_part_gb = StringUtil.nvl(request.getParameter("as_part_gb"), "2"); // 간납구분
		String as_receipt_gb = StringUtil.nvl(request.getParameter("as_receipt_gb"), "0"); // 승인구분  
		
		/*승인구분 전체 조회에 따른 검색조건 변경*/
		if("0".equals(as_receipt_gb)){
			as_receipt_gb = "'1','2','3'";
		} else {
			as_receipt_gb = "'"+as_receipt_gb+"'";
		}
				
		/*여신규정 전체 조회에 따른 검색조건 변경*/
		if("A".equals(as_limit_yn)){
			as_limit_yn = "'Y','N','E'";
		} else {
			as_limit_yn = "'"+as_limit_yn+"'";
		}
		
		/*parameter를 map에 setting*/
		paramMap.put("adt_fr_date", adt_fr_date); // 조회기간 fr
		paramMap.put("adt_fr_datetime", adt_fr_date + adt_fr_hour + adt_fr_minute); // 조회기간 fr
		paramMap.put("adt_to_date", adt_to_date); // 조회기간 to
		paramMap.put("adt_to_datetime", adt_to_date + adt_to_hour + adt_to_minute); // 조회기간 to
		paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
		paramMap.put("as_slip_gb", as_slip_gb); // 주문구분
		paramMap.put("as_limit_yn", as_limit_yn); // 여신규정
		paramMap.put("as_avg_month", as_avg_month); // 월평균주문월
		paramMap.put("as_wiban_kind", as_wiban_kind); // 위반종류
		paramMap.put("as_pre_deposit", as_pre_deposit); // 선입금거래처
		paramMap.put("as_psb_gb", as_psb_gb); // 수량한도
		paramMap.put("as_part_gb", as_part_gb); // 간납구분
		paramMap.put("as_receipt_gb", as_receipt_gb); // 승인구분 
		paramMap.put("searchType", searchType); // 조회구분
		paramMap.put("sidx", "req_time"); // 정렬
		paramMap.put("sord", ""); // 정렬순
		paramMap.put("page", null);
		paramMap.put("perPageRow", null);
		
		/*주문 목록*/
		List<OrderApprovalVO> orderApprovalList = orderApprovalService.getOrderApprovalGridListExcel(paramMap);
		
		/*주문 목록을 map에 담는다.*/
		List<Map> excelMap = new ArrayList<Map>();
		
		for (int i = 0; i < orderApprovalList.size(); i++) {
			
			Map<String, String> mapA1 = new HashMap<String, String>();
			
			OrderApprovalVO orderApprovalVO = new OrderApprovalVO();
			orderApprovalVO = orderApprovalList.get(i);
			
			mapA1.put("1",  orderApprovalVO.getReceipt_gb());
			mapA1.put("2",  orderApprovalVO.getYmd());
			mapA1.put("3",  orderApprovalVO.getCust_id());
			mapA1.put("4",  orderApprovalVO.getCust_nm());
			mapA1.put("5",  orderApprovalVO.getRcust_id());
			mapA1.put("6",  orderApprovalVO.getRcust_nm());
			mapA1.put("7",  orderApprovalVO.getGumae_no());
			mapA1.put("8",  orderApprovalVO.getRcust_gb1());
			mapA1.put("9",  orderApprovalVO.getSawon_nm());
			mapA1.put("10", orderApprovalVO.getDept_nm());
			mapA1.put("11", orderApprovalVO.getCur_amt());
			mapA1.put("12", orderApprovalVO.getRate_day());
			mapA1.put("13", orderApprovalVO.getPbigo());
			mapA1.put("14", orderApprovalVO.getWiban_kind());
			mapA1.put("15", orderApprovalVO.getWiban_order_req_yn());
			mapA1.put("16", orderApprovalVO.getWiban_order_conf_yn());  
			
			excelMap.add(mapA1);
		}
		
		String[] header = {"주문구분","주문요청일","거래처","거래처명","판매처","판매처명","접수번호","전납구분","담당자","팀명","현잔고","회전일","담보약속내용","위반유형","위반요청","팀장승인"}; // excel header
		String[] content = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16"}; // excel content
		
		ExcelDownManager.ExcelDown("주문승인", header, content, excelMap, response);
	}
}
