/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.controller;

import com.hanaph.saleon.business.service.CompanyCardMgmtService;
import com.hanaph.saleon.business.vo.CompanyCardMgmtJsonVO;
import com.hanaph.saleon.business.vo.CompanyCardMgmtVO;
import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : CompanyCardMgmtController.java
 * 설명 : 법인카드관리 IBK Controller
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 24.      Beomjin
 * </pre>
 *
 * @author : Beomjin(@irush.co.kr)
 * @version :
 * @since : 2014. 11. 24.
 */
@Controller
public class CompanyCardMgmtContoller {

    private static final Logger logger = Logger.getLogger(CompanyCardMgmtContoller.class);

    @Autowired
    private CompanyCardMgmtService companyCardMgmtService;

    /**
     * <pre>
     * 1. 개요     : 법인카드관리 메인
     * 2. 처리내용 : 법인카드관리 페이지를 반환한다.
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : companyCardMgmtList
     */
    @RequestMapping("/business/companyCardMgmtList.do")
    public ModelAndView companyCardMgmtList(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/companyCardMgmtList");
        Map<String, String> paramMap = new HashMap<String, String>();

		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());

		/*parameter를 map에 setting*/
        paramMap.put("gs_empCode", gs_empCode);

		/*현재 사용자의 직책(보직)정보*/
        CompanyCardMgmtVO companyCardMgmtInit = companyCardMgmtService.getCompanyCardMgmtInit(paramMap);

		/*계정과목 목록*/
        List<CompanyCardMgmtVO> gaejungCodeList = companyCardMgmtService.getGaejungCodeList();

        mav.addObject("companyCardMgmtInit", companyCardMgmtInit);
        mav.addObject("gaejungCodeList", gaejungCodeList);

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 법인카드사용내역 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 법인카드사용내역 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : companyCardHistoryGridList
     */
    @RequestMapping("/business/companyCardHistoryGridList.do")
    public void companyCardHistoryGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();

		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String as_sawon_id = StringUtil.nvl(request.getParameter("as_sawon_id")); // 사원코드
        String as_use_dt_fr = StringUtil.nvl(request.getParameter("as_use_dt_fr")).replace("-", ""); // 조회기간 fr
        String as_use_dt_to = StringUtil.nvl(request.getParameter("as_use_dt_to")).replace("-", ""); // 조회기간 to
        String as_teammember = StringUtil.nvl(request.getParameter("as_teammember"), "N"); // 본인 자료만 보기
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "sawon_id, use_dt, use_tm, card_ok_no"); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순
		
		/*parameter를 map에 setting*/
        paramMap.put("as_sawon_id", as_sawon_id); // 사원코드
        paramMap.put("as_use_dt_fr", as_use_dt_fr); // 조회기간 fr
        paramMap.put("as_use_dt_to", as_use_dt_to); // 조회기간 to
        paramMap.put("as_teammember", as_teammember); // 본인 자료만 보기
        paramMap.put("sidx", sidx); // 정렬순
        paramMap.put("sord", sord); // 정렬
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*법인카드 사용내역 목록*/
        List<CompanyCardMgmtVO> companyCardHistoryList = companyCardMgmtService.getCompanyCardHistoryGridList(paramMap);
		
		/*법인카드 사용내역 목록 총 수*/
        CompanyCardMgmtVO totalCountInfo = companyCardMgmtService.getCompanyCardHistoryGridTotalCount(paramMap);
		
		/*paging 연산*/
        int records = totalCountInfo.getTotal_cnt();
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        CompanyCardMgmtJsonVO companyCardMgmtJsonVO = new CompanyCardMgmtJsonVO();

        companyCardMgmtJsonVO.setTotal(total);        //page 수
        companyCardMgmtJsonVO.setPage(page);            //현재 page
        companyCardMgmtJsonVO.setRecords(records); // 전체 수
        companyCardMgmtJsonVO.setRows(companyCardHistoryList); // list
        companyCardMgmtJsonVO.setTotalCountInfo(totalCountInfo); // 합계정보

        MarshallerUtil.marshalling("json", response, companyCardMgmtJsonVO);
    }

    /**
     * <pre>
     * 1. 개요     : 법인카드사용내역 수정
     * 2. 처리내용 : 법인카드사용내역의 추가정보를 수정한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : updateCardUseDetail
     */
    @RequestMapping("/business/updateCardUseDetail.do")
    public void updateCardUseDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
		
		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션 생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());
		
		/*parameter*/
        String[] use_dt = request.getParameterValues("use_dt"); // 사용일자
        String[] use_tm = request.getParameterValues("use_tm"); // 사용시간
        String[] card_no = request.getParameterValues("card_no"); // 카드번호
        String[] card_ok_no = request.getParameterValues("card_ok_no"); // 승인번호
        String[] tax_gb = request.getParameterValues("tax_gb"); // 과세구분
        String[] gongjae_yn = request.getParameterValues("gongjae_yn"); // 부가세공제여부
        String[] use_detail = request.getParameterValues("use_detail"); // 사용내역
        String[] gaejung_cd = request.getParameterValues("gaejung_cd"); // 계정과목
        String[] teamjang_conf_yn = request.getParameterValues("teamjang_conf_yn"); // 입력완료여부
        String[] jukyo = request.getParameterValues("jukyo"); // 적요
		
		/*parameter를 map에 setting*/
        paramMap.put("teamjang_conf_sabun", gs_empCode); // 승인자 코드
        paramMap.put("use_dt", use_dt); // 사용일자
        paramMap.put("use_tm", use_tm); // 사용시간
        paramMap.put("card_no", card_no); // 카드번호
        paramMap.put("card_ok_no", card_ok_no); // 승인번호
        paramMap.put("tax_gb", tax_gb); // 과세구분
        paramMap.put("gongjae_yn", gongjae_yn); // 부가세공제여부
        paramMap.put("use_detail", use_detail); // 사용내역
        paramMap.put("gaejung_cd", gaejung_cd); // 계정과목
        paramMap.put("teamjang_conf_yn", teamjang_conf_yn); // 입력완료여부
        paramMap.put("jukyo", jukyo); // 적요
		
		/*returnVO*/
        CompanyCardMgmtJsonVO returnVO = new CompanyCardMgmtJsonVO();
        String result = "N"; // 최종 수행 결과

        try {
			/*사용내역 수정*/
            result = companyCardMgmtService.updateCardUseDetail(paramMap);
        } catch (Exception e) {
            logger.error(e.getMessage());
        }

        returnVO.setMessage(result); // 수행 결과

        MarshallerUtil.marshalling("json", response, returnVO);
    }

    /**
     * <pre>
     * 1. 개요     : 법인카드사용내역 excel 다운로드
     * 2. 처리내용 : 검색조건에 따른 법인카드사용내역 목록을 excel 파일로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     * @Method Name : companyCardHistoryGridListExcelDown
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/business/companyCardHistoryGridListExcelDown.do")
    public void companyCardHistoryGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> paramMap = new HashMap<String, String>();

		/*parameter*/
        String as_sawon_id = StringUtil.nvl(request.getParameter("as_sawon_id")); // 사원코드
        String as_use_dt_fr = StringUtil.nvl(request.getParameter("as_use_dt_fr")).replace("-", ""); // 조회기간 fr
        String as_use_dt_to = StringUtil.nvl(request.getParameter("as_use_dt_to")).replace("-", ""); // 조회기간 to
        String as_teammember = StringUtil.nvl(request.getParameter("as_teammember"), "N"); // 본인 자료만 보기
		
		/*parameter를 map에 setting*/
        paramMap.put("as_sawon_id", as_sawon_id); // 사원코드
        paramMap.put("as_use_dt_fr", as_use_dt_fr); // 조회기간 fr
        paramMap.put("as_use_dt_to", as_use_dt_to); // 조회기간 to
        paramMap.put("as_teammember", as_teammember); // 본인자료만 보기
        paramMap.put("sidx", "sawon_id, use_dt, use_tm, card_ok_no"); // 정렬
        paramMap.put("sord", ""); // 정렬순
        paramMap.put("page", null);
        paramMap.put("perPageRow", null);
		
		/*법인카드 사용내역 목록*/
        List<CompanyCardMgmtVO> companyCardHistoryList = companyCardMgmtService.getCompanyCardHistoryGridList(paramMap);
		
		/*법인카드 사용내역 목록을 map에 담는다.*/
        List<Map> excelMap = new ArrayList<Map>();

        for (int i = 0; i < companyCardHistoryList.size(); i++) {

            Map<String, String> mapA1 = new HashMap<String, String>();

            CompanyCardMgmtVO companyCardMgmtVO = new CompanyCardMgmtVO();
            companyCardMgmtVO = companyCardHistoryList.get(i);

            mapA1.put("1", companyCardMgmtVO.getUse_dt());
            mapA1.put("2", companyCardMgmtVO.getUse_tm());
            mapA1.put("3", companyCardMgmtVO.getCard_no());
            mapA1.put("4", companyCardMgmtVO.getCard_ok_no());
            mapA1.put("5", companyCardMgmtVO.getSawon_nm());
            mapA1.put("6", companyCardMgmtVO.getDept_nm());
            mapA1.put("7", companyCardMgmtVO.getUse_amt());
            mapA1.put("8", companyCardMgmtVO.getSaupjang_nm());
            mapA1.put("9", companyCardMgmtVO.getSaup_no());
            mapA1.put("10", companyCardMgmtVO.getTax_gb());
            mapA1.put("11", companyCardMgmtVO.getGongjae_yn());
            mapA1.put("12", companyCardMgmtVO.getUse_detail());
            mapA1.put("13", companyCardMgmtVO.getGaejung_nm());
            mapA1.put("14", companyCardMgmtVO.getTeamjang_conf_yn());
            mapA1.put("15", companyCardMgmtVO.getTeamjang_conf_sabun_nm());
            mapA1.put("16", companyCardMgmtVO.getJukyo());

            excelMap.add(mapA1);
        }

        String[] header = {"사용일자", "사용시간", "카드번호", "승인번호", "사원", "부서", "사용금액", "가맹점명", "사업자번호", "과세구분", "부가세공제여부", "구매(사용)내역 상세히기술", "계정과목", "입력완료", "입력자", "적요"}; // excel header
        String[] content = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16"}; // excel content

        ExcelDownManager.ExcelDown("법인카드관리IBK", header, content, excelMap, response);
    }
}
