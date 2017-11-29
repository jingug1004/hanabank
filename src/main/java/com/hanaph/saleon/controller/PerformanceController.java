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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hanaph.saleon.business.service.PerformanceService;
import com.hanaph.saleon.business.vo.PerformanceJsonVO;
import com.hanaph.saleon.business.vo.PerformanceVO;
import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;

/**
 * <pre>
 * Class Name : PerformanceController.java
 * 설명 : 실적현황 Controller
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 31.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 10. 31.
 */
@Controller
public class PerformanceController {
	
	@Autowired
	private PerformanceService performanceService;
	
	/**
	 * <pre>
	 * 1. 개요     : 실적현황 메인
	 * 2. 처리내용 : 실적현황 페이지를 반환한다.
	 * </pre>
	 * @Method Name : performanceListMain
	 * @param request
	 * @return
	 */		
	@RequestMapping("/business/performanceList.do")
	public ModelAndView performanceList(HttpServletRequest request){
		
		ModelAndView mav = new ModelAndView("business/performanceList");
				
		return mav;
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 실적현황 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 실적현황 목록을 json 형태로 반환한다.
	 * </pre>
	 * @Method Name : performanceGridList
	 * @param request
	 * @param response
	 * @throws IOException
	 */		
	@RequestMapping("/business/performanceGridList.do")
	public void performanceGridList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"),"1"));			//현재 page
		int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"),"20"));	//페이지 size
		String selectType = StringUtil.nvl(request.getParameter("selectType"), "part"); // 조회 타입
		String as_date_time = StringUtil.nvl(request.getParameter("as_date_time")).replace("-", ""); // 조회년월
		String as_siljukyul_in = StringUtil.nvl(request.getParameter("as_siljukyul_in")); // 원내 판매 실적률
		String as_siljukyul_out = StringUtil.nvl(request.getParameter("as_siljukyul_out")); // 원외 판매 실적률
		String as_siljukyul_byung = StringUtil.nvl(request.getParameter("as_siljukyul_byung")); // 병원 판매 실적률
		String as_siljukyul_in_su = StringUtil.nvl(request.getParameter("as_siljukyul_in_su")); // 원내 수금 실적률
		String as_siljukyul_out_su = StringUtil.nvl(request.getParameter("as_siljukyul_out_su")); // 원외 수금 실적률
		String as_siljukyul_byung_su = StringUtil.nvl(request.getParameter("as_siljukyul_byung_su")); // 병원 수금 실적률
		String as_part_cd = StringUtil.nvl(request.getParameter("as_part_cd")); // 파트 코드
		String as_team_cd = StringUtil.nvl(request.getParameter("as_team_cd")); // 부서 코드
		String as_emp_no = StringUtil.nvl(request.getParameter("as_emp_no")); // 사원 코드
		String defaultSidx = ""; // 기본 정렬
		
		/*조회 타입에 따라 정렬기준 분기처리*/
		if ("part".equals(selectType)) {
			defaultSidx = "col4";
		} else {
			defaultSidx = "rorder";
		}
		
		String sidx = StringUtil.nvl(request.getParameter("sidx"), defaultSidx); // 정렬
		String sord = StringUtil.nvl(request.getParameter("sord"),""); // 정렬순
		
		paramMap.put("selectType", selectType);  // 조회 타입
		paramMap.put("as_date_time", as_date_time);  // 조회년월
		paramMap.put("as_siljukyul_in", as_siljukyul_in); // 원내 판매 실적률
		paramMap.put("as_siljukyul_out", as_siljukyul_out); // 원외 판매 실적률
		paramMap.put("as_siljukyul_byung", as_siljukyul_byung); // 병원 판매 실적률
		paramMap.put("as_siljukyul_in_su", as_siljukyul_in_su); // 원내 수금 실적률
		paramMap.put("as_siljukyul_out_su", as_siljukyul_out_su); // 원외 수금 실적률
		paramMap.put("as_siljukyul_byung_su", as_siljukyul_byung_su); // 병원 수금 실적률
		paramMap.put("as_part_cd", as_part_cd); // 파트 코드
		paramMap.put("as_team_cd", as_team_cd); // 부서 코드
		paramMap.put("as_emp_no", as_emp_no); // 사원 코드
		paramMap.put("sidx", sidx); // 정렬
		paramMap.put("sord", sord); // 정렬순
		paramMap.put("page", String.valueOf(page)); // 현재 page
		paramMap.put("perPageRow", String.valueOf(perPageRow)); // 페이지 size
		
		/*실적현황 목록*/
		List<PerformanceVO> performanceList = performanceService.getPerformanceGridList(paramMap);
		
		/*실적현황 목록 총 수*/
		PerformanceVO totalCountInfo = performanceService.getPerformanceGridTotalCount(paramMap);
		
		/*paging 연산*/
		int records = totalCountInfo.getTotal_cnt();
		int total = (int)Math.ceil((double)records/(double)perPageRow);
		
		/*returnVO*/
		PerformanceJsonVO performanceJsonVO = new PerformanceJsonVO();
		
		performanceJsonVO.setTotal(total);
		performanceJsonVO.setPage(page);
		performanceJsonVO.setRecords(records);
		performanceJsonVO.setRows(performanceList);
		performanceJsonVO.setTotalCountInfo(totalCountInfo);
		
		MarshallerUtil.marshalling("json", response, performanceJsonVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 실적현황 excel 다운로드
	 * 2. 처리내용 : 검색조건에 따른 실적현황 목록을 excel 파일로 반환한다.
	 * </pre>
	 * @Method Name : performanceGridListExcelDown
	 * @param request
	 * @param response
	 * @throws Exception
	 */		
	@SuppressWarnings("rawtypes")
	@RequestMapping("/business/performanceGridListExcelDown.do")
	public void performanceGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
		String selectType = StringUtil.nvl(request.getParameter("selectType"), "part"); // 조회 타입
		String as_date_time = StringUtil.nvl(request.getParameter("as_date_time")).replace("-", ""); // 조회년월
		String as_siljukyul_in = StringUtil.nvl(request.getParameter("as_siljukyul_in")); // 원내 판매 실적률
		String as_siljukyul_out = StringUtil.nvl(request.getParameter("as_siljukyul_out")); // 원외 판매 실적률
		String as_siljukyul_byung = StringUtil.nvl(request.getParameter("as_siljukyul_byung")); // 병원 판매 실적률
		String as_siljukyul_in_su = StringUtil.nvl(request.getParameter("as_siljukyul_in_su")); // 원내 수금 실적률
		String as_siljukyul_out_su = StringUtil.nvl(request.getParameter("as_siljukyul_out_su")); // 원외 수금 실적률
		String as_siljukyul_byung_su = StringUtil.nvl(request.getParameter("as_siljukyul_byung_su")); // 병원 수금 실적률
		String as_part_cd = StringUtil.nvl(request.getParameter("as_part_cd")); // 파트 코드
		String as_team_cd = StringUtil.nvl(request.getParameter("as_team_cd")); // 부서 코드
		String as_emp_no = StringUtil.nvl(request.getParameter("as_emp_no")); // 사원 코드
		String defaultSidx = ""; // 기본 정렬
		
		/*조회 타입에 따라 정렬기준 분기처리*/
		if ("part".equals(selectType)) {
			defaultSidx = "col4";
		} else {
			defaultSidx = "rorder";
		}
		
		/*parameter를 map에 setting*/
		paramMap.put("selectType", selectType);  // 조회 타입
		paramMap.put("as_date_time", as_date_time);  // 조회년월
		paramMap.put("as_siljukyul_in", as_siljukyul_in);  // 원내 판매 실적률
		paramMap.put("as_siljukyul_out", as_siljukyul_out);  // 원외 판매 실적률
		paramMap.put("as_siljukyul_byung", as_siljukyul_byung);  // 병원 판매 실적률
		paramMap.put("as_siljukyul_in_su", as_siljukyul_in_su);  // 원내 수금 실적률
		paramMap.put("as_siljukyul_out_su", as_siljukyul_out_su); // 원외 수금 실적률
		paramMap.put("as_siljukyul_byung_su", as_siljukyul_byung_su); // 병원 수금 실적률
		paramMap.put("as_part_cd", as_part_cd); // 파트 코드
		paramMap.put("as_team_cd", as_team_cd); // 부서 코드
		paramMap.put("as_emp_no", as_emp_no); // 사원 코드
		paramMap.put("sidx", defaultSidx); // 정렬
		paramMap.put("sord", ""); // 정렬순
		paramMap.put("page", null);
		paramMap.put("perPageRow", null);
		
		/*실적현황 목록*/
		List<PerformanceVO> performanceList = performanceService.getPerformanceGridList(paramMap);
		
		/*실적현황 목록을 map에 담는다.*/
		List<Map> excelMap = new ArrayList<Map>();
		String title = "";
		String[] header = null;
		String[] content = null;
		
		for (int i = 0; i < performanceList.size(); i++) {
			
			Map<String, String> mapA1 = new HashMap<String, String>();
			
			PerformanceVO performanceVO = new PerformanceVO();
			performanceVO = performanceList.get(i);
			
			/*조회 타입에 따라 excel column 분기처리*/
			if ("part".equals(selectType)) {
				
				mapA1.put("1", performanceVO.getCol4());
				mapA1.put("2", performanceVO.getSale_amt());
				mapA1.put("3", performanceVO.getSale_amt_siljuk_byung());
				mapA1.put("4", performanceVO.getSale_amt_siljuk_mbyung());
				mapA1.put("5", performanceVO.getSale_amt_siljuk_in_03());
				mapA1.put("6", performanceVO.getSale_amt_siljuk_in_04());
				mapA1.put("7", performanceVO.getSale_amt_siljuk_in_02());
				mapA1.put("8", performanceVO.getSale_amt_siljuk_in_local());
				mapA1.put("9", performanceVO.getSale_amt_siljuk_in_01());
				mapA1.put("10", performanceVO.getSale_amt_siljuk_out());
				mapA1.put("11", performanceVO.getSale_amt_siljuk_in_05());
				mapA1.put("12", performanceVO.getSale_amt_banpum());
				mapA1.put("13", performanceVO.getSale_amt_halin());
				mapA1.put("14", performanceVO.getSale_amt_siljuk());
				mapA1.put("15", performanceVO.getSale_percent());
				mapA1.put("16", performanceVO.getIn_amt());
				mapA1.put("17", performanceVO.getIn_amt_siljuk_byung());
				mapA1.put("18", performanceVO.getIn_amt_siljuk_mbyung());
				mapA1.put("19", performanceVO.getIn_amt_siljuk_in_local());
				mapA1.put("20", performanceVO.getIn_amt_siljuk_in_01());
				mapA1.put("21", performanceVO.getIn_amt_siljuk_out());
				mapA1.put("22", performanceVO.getIn_amt_siljuk_in_05());
				mapA1.put("23", performanceVO.getIn_amt_siljuk());
				mapA1.put("24", performanceVO.getIn_percent());
				
			} else {
				
				mapA1.put("1", performanceVO.getCol2());
				mapA1.put("2", performanceVO.getCol4());
				mapA1.put("3", performanceVO.getSale_amt());
				mapA1.put("4", performanceVO.getSale_amt_siljuk_byung());
				mapA1.put("5", performanceVO.getSale_amt_siljuk_mbyung());
				mapA1.put("6", performanceVO.getSale_amt_siljuk_in_03());
				mapA1.put("7", performanceVO.getSale_amt_siljuk_in_04());
				mapA1.put("8", performanceVO.getSale_amt_siljuk_in_02());
				mapA1.put("9", performanceVO.getSale_amt_siljuk_in_local());
				mapA1.put("10", performanceVO.getSale_amt_siljuk_in_01());
				mapA1.put("11", performanceVO.getSale_amt_siljuk_out());
				mapA1.put("12", performanceVO.getSale_amt_siljuk_in_05());
				mapA1.put("13", performanceVO.getSale_amt_siljuk());
				mapA1.put("14", performanceVO.getSale_percent());
				mapA1.put("15", performanceVO.getIn_amt());
				mapA1.put("16", performanceVO.getIn_amt_siljuk_byung());
				mapA1.put("17", performanceVO.getIn_amt_siljuk_mbyung());
				mapA1.put("18", performanceVO.getIn_amt_siljuk_in_local());
				mapA1.put("19", performanceVO.getIn_amt_siljuk_in_01());
				mapA1.put("20", performanceVO.getIn_amt_siljuk_out());
				mapA1.put("21", performanceVO.getIn_amt_siljuk_in_05());
				mapA1.put("22", performanceVO.getIn_amt_siljuk());
				mapA1.put("23", performanceVO.getIn_percent());
				
			}
			
			excelMap.add(mapA1);
		}
		
		/*조회 타입에 따라 excel column 분기처리*/
		if ("part".equals(selectType)) {
			title = "파트별 실적현황";
			header = new String[]{"파트","판매목표", "종합병원", "준종합병원", "T", "입찰", "일반간납", "도매로컬", "의원", "약국", "기타", "반품", "매출할인외", "계", "달성율(%)", "수금목표", "종합병원", "준종합병원", "도매", "의원", "약국", "기타", "계", "달성율(%)"};
			content = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24"};
		} else if ("team".equals(selectType)) {
			title = "영업지점별 실적현황";
			header = new String[]{"파트","지점/팀", "판매목표", "종합병원", "준종합병원", "T", "입찰", "일반간납", "도매로컬", "의원", "약국", "기타", "계", "달성율(%)", "수금목표", "종합병원", "준종합병원", "도매", "의원", "약국", "기타", "계", "달성율(%)"};
			content = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
		} else if ("emp".equals(selectType)) {
			title = "사원별 실적현황";
			header = new String[]{"지점/팀","담당사원", "판매목표", "종합병원", "준종합병원", "T", "입찰", "일반간납", "도매로컬", "의원", "약국", "기타", "계", "달성율(%)", "수금목표", "종합병원", "준종합병원", "도매", "의원", "약국", "기타", "계", "달성율(%)"};
			content = new String[]{"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23"};
		}
		
		ExcelDownManager.ExcelDown(title, header, content, excelMap, response);
		
	}
}
