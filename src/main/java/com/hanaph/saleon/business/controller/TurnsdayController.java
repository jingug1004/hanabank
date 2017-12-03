/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hanaph.saleon.business.service.BusinessService;
import com.hanaph.saleon.business.service.TurnsdayService;
import com.hanaph.saleon.business.vo.BusinessVO;
import com.hanaph.saleon.business.vo.TurnsdayVO;
import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;

/**
 * <pre>
 * Class Name : SaleController.java
 * 설명 : 회전일현황 Controller
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 22.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 10. 22.
 */
@Controller
public class TurnsdayController {
	
	@Autowired
	private TurnsdayService turnsdayService;
	
	@Autowired
	private BusinessService businessService;
	
	/**
	 * <pre>
	 * 1. 개요     : 회전일 현황 메인
	 * 2. 처리내용 : 회전일현황 페이지를 반환한다.
	 * </pre>
	 * @Method Name : turnsdayList
	 * @param request
	 * @return
	 */		
	@RequestMapping("/business/turnsdayList.do")
	public ModelAndView turnsdayList(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("business/turnsdayList");
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*세션에서 emp_code를 가져온다.*/
		HttpSession session = request.getSession(); // 세션생성
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());
		
		/*parameter를 map에 setting*/
		paramMap.put("gs_empCode", gs_empCode);
		
		/*부서 코드와 pda 권한을 가져온다.*/
		BusinessVO commonEmpInfo = businessService.getCommonEmpInfo(paramMap);
		
		mav.addObject("commonEmpInfo", commonEmpInfo);
				
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 1. 개요     : 회전일 현황 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 회전일현황 목록을 json 형태로 반환한다.
	 * </pre>
	 * @Method Name : turnsdayGridList
	 * @param request
	 * @param response
	 * @throws IOException
	 */		
	@RequestMapping("/business/turnsdayGridList.do")
	public void turnsdayGridList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*세션에서 emp_code를 가져온다.*/
		HttpSession session = request.getSession(); // 세션 생성
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
		
		/*parameter*/
		String ad_fr_date = StringUtil.nvl(request.getParameter("ad_fr_date")).replaceAll("\\-", ""); // 조회기간 fr
		String ad_to_date = StringUtil.nvl(request.getParameter("ad_to_date")).replaceAll("\\-", ""); // 조회기간 to
		String as_fr_cust = StringUtil.nvl(request.getParameter("as_fr_cust"), ""); // 거래처 코드
		String as_fr_rcust = StringUtil.nvl(request.getParameter("as_fr_rcust"), ""); // 매출처 코드
		String as_dept_cd = StringUtil.nvl(request.getParameter("as_dept_cd")); // 부서코드
		String as_pda_auth = StringUtil.nvl(request.getParameter("as_pda_auth")); // pda 권한
		String dc_en_yn = StringUtil.nvl(request.getParameter("dc_en_yn"), "%"); //할인정리여부
		String gc_en_yn = StringUtil.nvl(request.getParameter("gc_en_yn"), "%"); //보상정리여부(Y:정리, N:미정리)
		String dw_gb = StringUtil.nvl(request.getParameter("dw_gb"), "2"); //조회타입, 2:이력
		String sidx = StringUtil.nvl(request.getParameter("sidx"),"ymd, cust_id, rcust_id"); // 정렬
		String sord = StringUtil.nvl(request.getParameter("sord"),""); // 정렬순
		
		/*parameter를 map에 setting*/
		paramMap.put("as_emp_cd", as_emp_cd); // 사원코드
		paramMap.put("ad_fr_date", ad_fr_date); // 조회기간 fr
		paramMap.put("ad_to_date", ad_to_date); // 조회기간 to
		paramMap.put("as_fr_cust", as_fr_cust); // 거래처 코드
		paramMap.put("as_dept_cd", as_dept_cd); // 부서코드
		paramMap.put("as_pda_auth", as_pda_auth); // pda 권한
		paramMap.put("as_fr_rcust", as_fr_rcust); // 매출처 코드
		paramMap.put("dc_en_yn", dc_en_yn); //할인정리여부
		paramMap.put("gc_en_yn", gc_en_yn); //보상정리여부(Y:정리, N:미정리)
		paramMap.put("dw_gb", dw_gb); //조회타입, 2:이력
		paramMap.put("sidx", sidx); // 정렬
		paramMap.put("sord", sord); // 정렬순
		
		/*회전일현황 목록 조회 후 jsos 형태로 리턴 */
		MarshallerUtil.marshalling("json", response, turnsdayService.getTurnsdayGridList(paramMap));
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 회전일 현황 excel 다운로드
	 * 2. 처리내용 : 검색조건에 따른 회전일현황 목록을 excel 파일로 반환한다.
	 * </pre>
	 * @Method Name : turnsdayGridListExcelDown
	 * @param request
	 * @param response
	 * @throws Exception
	 */		
	@SuppressWarnings("rawtypes")
	@RequestMapping("/business/turnsdayGridListExcelDown.do")
	public void turnsdayGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*세션에서 emp_code를 가져온다.*/
		HttpSession session = request.getSession(); // 세션 생성
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
		
		/*parameter*/
		String ad_fr_date = StringUtil.nvl(request.getParameter("ad_fr_date")).replaceAll("\\-", ""); // 조회기간 fr
		String ad_to_date = StringUtil.nvl(request.getParameter("ad_to_date")).replaceAll("\\-", ""); // 조회기간 to
		String as_fr_cust = StringUtil.nvl(request.getParameter("as_fr_cust"), ""); // 거래처 코드
		String as_fr_rcust = StringUtil.nvl(request.getParameter("as_fr_rcust"), ""); // 매출처 코드
		String as_dept_cd = StringUtil.nvl(request.getParameter("as_dept_cd")); // 부서코드
		String as_pda_auth = StringUtil.nvl(request.getParameter("as_pda_auth")); // pda 권한
		String dc_en_yn = StringUtil.nvl(request.getParameter("dc_en_yn"), "%"); //할인정리여부
		String gc_en_yn = StringUtil.nvl(request.getParameter("gc_en_yn"), "%"); //보상정리여부(Y:정리, N:미정리)
		String dw_gb = StringUtil.nvl(request.getParameter("dw_gb"), "2"); //조회타입, 2:이력
		String sidx = StringUtil.nvl(request.getParameter("sidx"),"ymd, cust_id, rcust_id"); // 정렬
		String sord = StringUtil.nvl(request.getParameter("sord"),""); // 정렬순
		
		/*parameter를 map에 setting*/
		paramMap.put("as_emp_cd", as_emp_cd); // 사원코드
		paramMap.put("ad_fr_date", ad_fr_date); // 조회기간 fr
		paramMap.put("ad_to_date", ad_to_date); // 조회기간 to
		paramMap.put("as_fr_cust", as_fr_cust); // 거래처 코드
		paramMap.put("as_dept_cd", as_dept_cd); // 부서코드
		paramMap.put("as_pda_auth", as_pda_auth); // pda 권한
		paramMap.put("as_fr_rcust", as_fr_rcust); // 매출처 코드
		paramMap.put("dc_en_yn", dc_en_yn); //할인정리여부
		paramMap.put("gc_en_yn", gc_en_yn); //보상정리여부(Y:정리, N:미정리)
		paramMap.put("dw_gb", dw_gb); //조회타입, 2:이력
		paramMap.put("sidx", sidx); // 정렬
		paramMap.put("sord", sord); // 정렬순
		
		/*판매현황 목록*/
		List<TurnsdayVO> turnsdayList = turnsdayService.getTurnsdayGridList(paramMap);
		/*판매현황 목록을 map에 담는다.*/
		List<Map> excelMap = new ArrayList<Map>();
		long sumAmt = 0;
		for (int i = 0; i < turnsdayList.size(); i++) {
			
			Map<String, String> mapA1 = new HashMap<String, String>();
			
			TurnsdayVO turnsdayVO = new TurnsdayVO();
			turnsdayVO = turnsdayList.get(i);
			
			mapA1.put("1", turnsdayVO.getYmd());
			mapA1.put("2", turnsdayVO.getCust_id());
			mapA1.put("3", turnsdayVO.getCust_nm());
			mapA1.put("4", turnsdayVO.getRcust_id());
			mapA1.put("5", turnsdayVO.getRcust_nm());
			mapA1.put("6", turnsdayVO.getItem_id());
			mapA1.put("7", turnsdayVO.getItem_nm());
			mapA1.put("8", turnsdayVO.getStandard());
			mapA1.put("9",  StringUtil.makeMoneyType(turnsdayVO.getQty()));
			mapA1.put("10", StringUtil.makeMoneyType(turnsdayVO.getDc_qty()));
			mapA1.put("11", StringUtil.makeMoneyType(turnsdayVO.getBal_amt()));
			mapA1.put("12", StringUtil.makePercentType(turnsdayVO.getQty_yul()));
			mapA1.put("13", StringUtil.makeMoneyType(turnsdayVO.getAmt()));
			mapA1.put("14", StringUtil.makeMoneyType(turnsdayVO.getYak_amt()));
			mapA1.put("15", StringUtil.makePercentType(turnsdayVO.getAmt_yul()));
			mapA1.put("16", StringUtil.makeMoneyType(turnsdayVO.getAct_sale()));
			mapA1.put("17", StringUtil.makeMoneyType(turnsdayVO.getDanga_cha()));
			mapA1.put("18", StringUtil.makeMoneyType(turnsdayVO.getHal_amt()));
			mapA1.put("19", StringUtil.makeMoneyType(turnsdayVO.getBosang_amt()));
			mapA1.put("20", StringUtil.makePercentType(turnsdayVO.getBosang_yul()));
			mapA1.put("21", StringUtil.makeMoneyType(turnsdayVO.getC_amt()));
			mapA1.put("22", StringUtil.makePercentType(turnsdayVO.getC()));
			mapA1.put("23", StringUtil.makeMoneyType(turnsdayVO.getGc_amt()));
			mapA1.put("24", StringUtil.makePercentType(turnsdayVO.getGc()));
			mapA1.put("25", StringUtil.makeMoneyType(turnsdayVO.getEtc_amt()));
			mapA1.put("26", StringUtil.makePercentType(turnsdayVO.getEtc()));
			mapA1.put("27", turnsdayVO.getDc_en_yn());
			mapA1.put("28", turnsdayVO.getGc_en_yn());
			mapA1.put("29", turnsdayVO.getBigo());
			excelMap.add(mapA1);
			
			// 매출액 합계 구함
			try{
				sumAmt += Long.parseLong(StringUtil.nvl2(turnsdayVO.getAmt(), "0"));
			}catch(Exception e){
				e.getMessage();
			}
		}
		
		//매출액 합계 셋팅 
		Map<String, String> mapA2 = new HashMap<String, String>();
		mapA2.put("13", StringUtil.makeMoneyType(Long.toString(sumAmt)));
		excelMap.add(mapA2);
		
		String[] header = {"*일자*","거래처코드","거래처명","매출처코드","매출처명","제품코드","제품명","규격","수량","할증"
				,"발행단가","할인율","매출액","약정단가","할인율","실매출액","단가차액", "할인금액","보상금액","보상율"
				,"C 금액","C율","GC 금액","GC율","별도금액","별도율","할인정리","보상정리", "비고"}; // excel header
		String[] content = {"1","2","3","4","5","6","7","8","9","10"
				,"11","12","13","14","15","16","17","18","19","20"
				,"21","22","23","24","25","26","27","28","29"}; // excel content
		ExcelDownManager.ExcelDown("회전일현황", header, content, excelMap, response);
		
	}
}
