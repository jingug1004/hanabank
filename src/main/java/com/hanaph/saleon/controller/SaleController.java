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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hanaph.saleon.business.service.BusinessService;
import com.hanaph.saleon.business.service.SaleService;
import com.hanaph.saleon.business.vo.BusinessVO;
import com.hanaph.saleon.business.vo.SaleJsonVO;
import com.hanaph.saleon.business.vo.SaleVO;
import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;

/**
 * <pre>
 * Class Name : SaleController.java
 * 설명 : 판매현황 Controller
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
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private BusinessService businessService;
	
	/**
	 * <pre>
	 * 1. 개요     : 판매현황 메인
	 * 2. 처리내용 : 판매현황 페이지를 반환한다.
	 * </pre>
	 * @Method Name : saleMain
	 * @param request
	 * @return
	 */		
	@RequestMapping("/business/saleList.do")
	public ModelAndView saleList(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("business/saleList");
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
	 * 1. 개요     : 판매현황 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 판매현황 목록을 json 형태로 반환한다.
	 * </pre>
	 * @Method Name : saleList
	 * @param request
	 * @param response
	 * @throws IOException
	 */		
	@RequestMapping("/business/saleGridList.do")
	public void saleGridList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*세션에서 emp_code를 가져온다.*/
		HttpSession session = request.getSession(); // 세션 생성
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
		String as_assgnCode = StringUtil.nvl(loginUserVO.getAssgn_cd());
		
		/*parameter*/
		int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"),"1"));			//현재 page
		int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"),"20"));	//페이지 size
		String ad_fr_date = StringUtil.nvl(request.getParameter("ad_fr_date")); // 조회기간 fr
		String ad_to_date = StringUtil.nvl(request.getParameter("ad_to_date")); // 조회기간 to
		String as_fr_cust = StringUtil.nvl(request.getParameter("as_fr_cust"), ""); // 거래처 코드
		String as_dept_cd = StringUtil.nvl(request.getParameter("as_dept_cd")); // 부서코드
		String as_pda_auth = StringUtil.nvl(request.getParameter("as_pda_auth")); // pda 권한
		String sidx = StringUtil.nvl(request.getParameter("sidx"),"ymd, cust_id, rcust_id"); // 정렬
		String sord = StringUtil.nvl(request.getParameter("sord"),""); // 정렬순
		
		/*parameter를 map에 setting*/
		paramMap.put("as_emp_cd", as_emp_cd); // 사원코드
		paramMap.put("as_assgnCode", as_assgnCode); //직책코드
		paramMap.put("ad_fr_date", ad_fr_date); // 조회기간 fr
		paramMap.put("ad_to_date", ad_to_date); // 조회기간 to
		paramMap.put("as_fr_cust", as_fr_cust); // 거래처 코드
		paramMap.put("as_dept_cd", as_dept_cd); // 부서코드
		paramMap.put("as_pda_auth", as_pda_auth); // pda 권한
		paramMap.put("sidx", sidx); // 정렬
		paramMap.put("sord", sord); // 정렬순
		paramMap.put("page", String.valueOf(page));
		paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*판매현황 목록*/
		List<SaleVO> saleList = saleService.getSaleGridList(paramMap);
		
		/*판매현황 목록 총 수*/
		SaleVO totalCountInfo = saleService.getSaleGridTotalCount(paramMap);
		
		/*paging 연산*/
		int records = totalCountInfo.getTotal_cnt();
		int total = (int)Math.ceil((double)records/(double)perPageRow);
		
		/*returnVO*/
		SaleJsonVO saleJsonVO = new SaleJsonVO();
		
		saleJsonVO.setTotal(total);		//page 수
		saleJsonVO.setPage(page);			//현재 page
		saleJsonVO.setRecords(records); // 전체 수
		saleJsonVO.setRows(saleList); // list
		saleJsonVO.setTotalCountInfo(totalCountInfo); // 합계정보
		
		MarshallerUtil.marshalling("json", response, saleJsonVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 판매현황 excel 다운로드
	 * 2. 처리내용 : 검색조건에 따른 판매현황 목록을 excel 파일로 반환한다.
	 * </pre>
	 * @Method Name : saleGridListExcelDown
	 * @param request
	 * @param response
	 * @throws Exception
	 */		
	@SuppressWarnings("rawtypes")
	@RequestMapping("/business/saleGridListExcelDown.do")
	public void saleGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*세션에서 emp_code를 가져온다.*/
		HttpSession session = request.getSession(); // 세션 생성
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
		String as_assgnCode = StringUtil.nvl(loginUserVO.getAssgn_cd());
		
		/*parameter*/
		String ad_fr_date = StringUtil.nvl(request.getParameter("ad_fr_date")); // 조회기간 fr
		String ad_to_date = StringUtil.nvl(request.getParameter("ad_to_date")); // 조회기간 to
		String as_fr_cust = StringUtil.nvl(request.getParameter("as_fr_cust"), ""); // 거래처 코드
		String as_dept_cd = StringUtil.nvl(request.getParameter("as_dept_cd")); // 부서코드
		String as_pda_auth = StringUtil.nvl(request.getParameter("as_pda_auth")); // pda 권한
		
		/*parameter를 map에 setting*/
		paramMap.put("as_emp_cd", as_emp_cd); // 사원코드
		paramMap.put("as_assgnCode", as_assgnCode); //직책코드
		paramMap.put("ad_fr_date", ad_fr_date); // 조회기간 fr
		paramMap.put("ad_to_date", ad_to_date); // 조회기간 to
		paramMap.put("as_fr_cust", as_fr_cust); // 거래처 코드
		paramMap.put("as_dept_cd", as_dept_cd); // 부서코드
		paramMap.put("as_pda_auth", as_pda_auth); // pda 권한
		paramMap.put("sidx", "ymd, cust_id, rcust_id"); // 정렬
		paramMap.put("sord", ""); // 정렬순
		paramMap.put("page", null);
		paramMap.put("perPageRow", null);
		
		/*판매현황 목록*/
		List<SaleVO> saleList = saleService.getSaleGridList(paramMap);
		
		/*판매현황 목록을 map에 담는다.*/
		List<Map> excelMap = new ArrayList<Map>();
		
		for (int i = 0; i < saleList.size(); i++) {
			
			Map<String, String> mapA1 = new HashMap<String, String>();
			
			SaleVO saleVO = new SaleVO();
			saleVO = saleList.get(i);
			
			mapA1.put("1", saleVO.getYmd());
			mapA1.put("2", saleVO.getCust_id());
			mapA1.put("3", saleVO.getCust_nm());
			mapA1.put("4", saleVO.getRcust_id());
			mapA1.put("5", saleVO.getRcust_nm());
			mapA1.put("6", saleVO.getItem_id());
			mapA1.put("7", saleVO.getItem_nm());
			mapA1.put("8", saleVO.getStandard());
			mapA1.put("9", saleVO.getQty());
			mapA1.put("10", saleVO.getDanga());
			mapA1.put("11", saleVO.getAmt());
			mapA1.put("12", saleVO.getVat());
			mapA1.put("13", saleVO.getTot());
			mapA1.put("14", saleVO.getDc_amt());
			
			excelMap.add(mapA1);
		}
		
		String[] header = {"거래일자","거래처코드","거래처명","납품처코드","납품처명","제품코드","제품명","단위","수량","단가","공급가액","세액","합계금액","할인액"}; // excel header
		String[] content = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14"}; // excel content
		
		ExcelDownManager.ExcelDown("판매현황", header, content, excelMap, response);
	}
}
