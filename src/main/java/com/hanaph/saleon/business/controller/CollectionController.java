/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.business.controller;

import com.hanaph.saleon.business.service.BusinessService;
import com.hanaph.saleon.business.service.CollectionService;
import com.hanaph.saleon.business.vo.BusinessVO;
import com.hanaph.saleon.business.vo.CollectionJsonVO;
import com.hanaph.saleon.business.vo.CollectionVO;
import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;
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
 * Class Name : CollectionController.java
 * 설명 : 수금현황 Controller
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 28.      Beomjin
 * </pre>
 *
 * @author : Beomjin(@irush.co.kr)
 * @version :
 * @since : 2014. 10. 28.
 */
@Controller
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private BusinessService businessService;

    /**
     * <pre>
     * 1. 개요     : 수금현황 메인
     * 2. 처리내용 : 수금현황 페이지를 반환한다.
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : collectionMain
     */
    @RequestMapping("/business/collectionList.do")
    public ModelAndView collectionList(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/collectionList");
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
     * 1. 개요     : 수금현황 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 수금현황 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : collectionGridList
     */
    @RequestMapping("/business/collectionGridList.do")
    public void collectionGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();

		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션 생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
        String as_assgnCode = StringUtil.nvl(loginUserVO.getAssgn_cd());

		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String ad_fr_date = StringUtil.nvl(request.getParameter("ad_fr_date")); // 조회기간 fr
        String ad_to_date = StringUtil.nvl(request.getParameter("ad_to_date")); // 조회기간 to
        String as_fr_cust = StringUtil.nvl(request.getParameter("as_fr_cust"), ""); // 거래처 코드
        String as_dept_cd = StringUtil.nvl(request.getParameter("as_dept_cd")); // 부서코드
        String as_pda_auth = StringUtil.nvl(request.getParameter("as_pda_auth")); // pda 권한
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "ymd, cust_id"); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순

		/*parameter를 map에 setting*/
        paramMap.put("as_emp_cd", as_emp_cd); // 사원 코드
        paramMap.put("ad_fr_date", ad_fr_date); // 조회기간 fr
        paramMap.put("ad_to_date", ad_to_date); // 조회기간 to
        paramMap.put("as_fr_cust", as_fr_cust); // 거래처 코드
        paramMap.put("as_dept_cd", as_dept_cd); // 부서코드
        paramMap.put("as_pda_auth", as_pda_auth); // pda 권한
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
        paramMap.put("as_assgnCode", as_assgnCode); //직책코드
		
		/*수금현황 목록*/
        List<CollectionVO> collectionList = collectionService.getCollectionGridList(paramMap);
		
		/*수금현황 목록 총 수*/
        CollectionVO totalCountInfo = collectionService.getCollectionGridTotalCount(paramMap);
		
		/*paging 연산*/
        int records = totalCountInfo.getTotal_cnt();
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        CollectionJsonVO collectionJsonVO = new CollectionJsonVO();

        collectionJsonVO.setTotal(total); //page 수
        collectionJsonVO.setPage(page); //현재 page
        collectionJsonVO.setRecords(records); // 전체 수
        collectionJsonVO.setRows(collectionList); // list
        collectionJsonVO.setTotalCountInfo(totalCountInfo); // 합계정보

        MarshallerUtil.marshalling("json", response, collectionJsonVO);
    }

    /**
     * <pre>
     * 1. 개요     : 수금현황 excel 다운로드
     * 2. 처리내용 : 검색조건에 따른 수금현황 목록을 excel 파일로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     * @Method Name : collectionGridListExcelDown
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/business/collectionGridListExcelDown.do")
    public void collectionGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션 생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
		
		/*parameter*/
        String ad_fr_date = StringUtil.nvl(request.getParameter("ad_fr_date")); // 조회기간 fr
        String ad_to_date = StringUtil.nvl(request.getParameter("ad_to_date")); // 조회기간 to
        String as_fr_cust = StringUtil.nvl(request.getParameter("as_fr_cust"), ""); // 거래처 코드
        String as_dept_cd = StringUtil.nvl(request.getParameter("as_dept_cd")); // 부서코드
        String as_pda_auth = StringUtil.nvl(request.getParameter("as_pda_auth")); // pda 권한
        String as_assgnCode = StringUtil.nvl(loginUserVO.getAssgn_cd());
		
		/*parameter를 map에 setting*/
        paramMap.put("as_emp_cd", as_emp_cd); // 사원코드
        paramMap.put("ad_fr_date", ad_fr_date); // 조회기간 fr
        paramMap.put("ad_to_date", ad_to_date); // 조회기간 to
        paramMap.put("as_fr_cust", as_fr_cust); // 거래처 코드
        paramMap.put("as_dept_cd", as_dept_cd); // 부서코드
        paramMap.put("as_pda_auth", as_pda_auth); // pda 권한
        paramMap.put("sidx", "ymd, cust_id"); // 정렬
        paramMap.put("sord", ""); // 정렬순
        paramMap.put("page", null);
        paramMap.put("perPageRow", null);
        paramMap.put("as_assgnCode", as_assgnCode); //직책코드
		
		/*수금현황 목록*/
        List<CollectionVO> collectionList = collectionService.getCollectionGridList(paramMap);
		
		/*수금현황 목록을 map에 담는다.*/
        List<Map> excelMap = new ArrayList<Map>();

        for (int i = 0; i < collectionList.size(); i++) {

            Map<String, String> mapA1 = new HashMap<String, String>();

            CollectionVO collectionVO = new CollectionVO();
            collectionVO = collectionList.get(i);

            mapA1.put("1", collectionVO.getYmd());
            mapA1.put("2", collectionVO.getCust_id());
            mapA1.put("3", collectionVO.getCust_nm());
            mapA1.put("4", collectionVO.getRcust_id());
            mapA1.put("5", collectionVO.getRcust_nm());
            mapA1.put("6", collectionVO.getItem_id());
            mapA1.put("7", collectionVO.getSukum());
            mapA1.put("8", collectionVO.getEnd_ymd());
            mapA1.put("9", collectionVO.getBill_no());
            mapA1.put("10", collectionVO.getBalhang());
            mapA1.put("11", collectionVO.getSawon_id());
            mapA1.put("12", collectionVO.getSawon_nm());

            excelMap.add(mapA1);
        }

        String[] header = {"거래일자", "거래처코드", "거래처명", "납품처코드", "납품처명", "구분", "수금액", "만기일", "어음번호", "발행처", "사원코드", "담당사원"}; // excel header
        String[] content = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"}; // excel content

        ExcelDownManager.ExcelDown("수금현황", header, content, excelMap, response);

    }
}
