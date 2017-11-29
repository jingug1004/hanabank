/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.controller;

import com.hanaph.saleon.business.service.BusinessService;
import com.hanaph.saleon.business.vo.BusinessJsonVO;
import com.hanaph.saleon.business.vo.BusinessVO;
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
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : BusinessController.java
 * 설명 : 영업관리 공통 Controller
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 24.      Beomjin
 * </pre>
 *
 * @author : Beomjin(@irush.co.kr)
 * @version :
 * @since : 2014. 10. 24.
 */
@Controller
public class BusinessController {

    @Autowired
    private BusinessService businessService;

    /**
     * <pre>
     * 1. 개요     : 거래처 검색 팝업 메인
     * 2. 처리내용 : 거래처 검색 팝업 페이지를 반환한다.
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : customerList
     */
    @RequestMapping("/business/common/customerList.do")
    public ModelAndView customerList(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/common/customerListPopup");

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 거래처 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 거래처 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : customerGridList
     */
    @RequestMapping("/business/common/customerGridList.do")
    public void customerGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();

		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션 생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
        String as_assgnCode = StringUtil.nvl(loginUserVO.getAssgn_cd());

		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String searchKeyword = URLDecoder.decode(StringUtil.nvl(request.getParameter("searchKeyword")), "UTF-8"); // 검색어
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "cust_nm"); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순

		/*parameter를 map에 setting*/
        paramMap.put("searchKeyword", searchKeyword); // 검색어
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));

        paramMap.put("as_emp_cd", as_emp_cd); // 사원코드
        paramMap.put("as_assgnCode", as_assgnCode); //직책코드
		
		/*거래처 목록*/
        List<BusinessVO> customerList = businessService.getCustomerGridList(paramMap);
		
		/*paging 연산*/
        int records = businessService.getCustomerGridTotalCount(paramMap); // 거래처 총 수
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        BusinessJsonVO businessJsonVO = new BusinessJsonVO();

        businessJsonVO.setTotal(total); // page 수
        businessJsonVO.setPage(page); // 현재 page
        businessJsonVO.setRecords(records); // 전체 수
        businessJsonVO.setRows(customerList); // list

        MarshallerUtil.marshalling("json", response, businessJsonVO);

    }

    /**
     * <pre>
     * 1. 개요     : 거래처 명 검색 ajax
     * 2. 처리내용 : 거래처 코드에 따른 거래처 명을 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : customerNameAjax
     */
    @RequestMapping("/business/common/customerNameAjax.do")
    public void customerNameAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        String cust_id = StringUtil.nvl(request.getParameter("cust_id")); // 거래처 코드
		
		/*parameter를 map에 setting*/
        paramMap.put("cust_id", cust_id);
		
		/*거래처 명 조회*/
        String cust_nm = StringUtil.nvl(businessService.getCustomerName(paramMap));
		
		/*returnVO*/
        BusinessVO businessVO = new BusinessVO();

        businessVO.setCust_nm(cust_nm); // 거래처 명

        MarshallerUtil.marshalling("json", response, businessVO);

    }

    /**
     * <pre>
     * 1. 개요     : 상세 거래처 명 검색 ajax
     * 2. 처리내용 : 거래처 코드에 따른 상세 거래처 명을 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : customerTypeAjax
     */
    @RequestMapping("/business/common/customerTypeAjax.do")
    public void customerTypeAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        String cust_id = StringUtil.nvl(request.getParameter("cust_id")); // 거래처 코드
		
		/*parameter를 map에 setting*/
        paramMap.put("cust_id", cust_id);
		
		/*returnVO*/
        BusinessVO businessVO = businessService.getCustomerType(paramMap);

        MarshallerUtil.marshalling("json", response, businessVO);

    }

    /**
     * <pre>
     * 1. 개요     : 파트 검색 팝업 메인
     * 2. 처리내용 : 파트 검색 팝업 페이지를 반환한다.
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : partListPopupMain
     */
    @RequestMapping("/business/common/partList.do")
    public ModelAndView partList(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/common/partListPopup");

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 파트 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 파트 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : partGridList
     */
    @RequestMapping("/business/common/partGridList.do")
    public void partGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String searchKeyword = URLDecoder.decode(StringUtil.nvl(request.getParameter("searchKeyword")), "UTF-8"); // 검색어
        String sidx = StringUtil.nvl(request.getParameter("sidx"), ""); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순
		
		/*parameter를 map에 setting*/
        paramMap.put("searchKeyword", searchKeyword); // 검색어
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*파트 목록*/
        List<BusinessVO> partList = businessService.getPartGridList(paramMap);
		
		/*paging 연산*/
        int records = businessService.getPartGridTotalCount(paramMap); // 파트 총 수
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        BusinessJsonVO businessJsonVO = new BusinessJsonVO();

        businessJsonVO.setTotal(total); // page 수
        businessJsonVO.setPage(page); // 현재 page
        businessJsonVO.setRecords(records); // 전체 수
        businessJsonVO.setRows(partList); // list

        MarshallerUtil.marshalling("json", response, businessJsonVO);

    }

    /**
     * <pre>
     * 1. 개요     : 부서 검색 팝업 메인
     * 2. 처리내용 : 부서 검색 팝업 페이지를 반환한다.
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : teamListPopupMain
     */
    @RequestMapping("/business/common/teamList.do")
    public ModelAndView teamList(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/common/teamListPopup");

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 부서 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 부서 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : teamGridList
     */
    @RequestMapping("/business/common/teamGridList.do")
    public void teamGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
				
		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션 생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
        String as_assgnCode = StringUtil.nvl(loginUserVO.getAssgn_cd());
		
		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String searchKeyword = URLDecoder.decode(StringUtil.nvl(request.getParameter("searchKeyword")), "UTF-8"); // 검색어
        String sidx = StringUtil.nvl(request.getParameter("sidx"), ""); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순
		
		/*parameter를 map에 setting*/
        paramMap.put("searchKeyword", searchKeyword); // 검색어
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));

        paramMap.put("as_emp_cd", as_emp_cd); // 사원코드
        paramMap.put("as_assgnCode", as_assgnCode); //직책코드
		
		/*부서 목록*/
        List<BusinessVO> teamList = businessService.getTeamGridList(paramMap);
		
		/*paging 연산*/
        int records = businessService.getTeamGridTotalCount(paramMap); // 부서 목록 총 수
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        BusinessJsonVO businessJsonVO = new BusinessJsonVO();

        businessJsonVO.setTotal(total); // page 수
        businessJsonVO.setPage(page); // 현재 page
        businessJsonVO.setRecords(records); // 전체 수
        businessJsonVO.setRows(teamList); // list

        MarshallerUtil.marshalling("json", response, businessJsonVO);

    }

    /**
     * <pre>
     * 1. 개요     : 사원 검색 팝업 메인
     * 2. 처리내용 : 사원 검색 팝업 페이지를 반환한다.
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : empListPopupMain
     */
    @RequestMapping("/business/common/empList.do")
    public ModelAndView empList(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/common/empListPopup");

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 사원 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 사원 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : empGridList
     */
    @RequestMapping("/business/common/empGridList.do")
    public void empGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션 생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
        String as_assgnCode = StringUtil.nvl(loginUserVO.getAssgn_cd());
        System.out.println("as_assgnCode:>" + as_assgnCode);
		
		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String searchKeyword = URLDecoder.decode(StringUtil.nvl(request.getParameter("searchKeyword")), "UTF-8"); // 검색어
        String dept_cd = StringUtil.nvl(request.getParameter("dept_cd"), ""); // 부서코드
        String sidx = StringUtil.nvl(request.getParameter("sidx"), ""); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순
		
		/*parameter를 map에 setting*/
        paramMap.put("searchKeyword", searchKeyword); // 검색어
        paramMap.put("dept_cd", dept_cd); // 부서코드
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));

        paramMap.put("as_emp_cd", as_emp_cd); // 사원코드
        paramMap.put("as_assgnCode", as_assgnCode); //직책코드

        System.out.println("as_assgnCode:>" + paramMap.get("as_assgnCode"));
		
		/*사원목록*/
        List<BusinessVO> empList = businessService.getEmpGridList(paramMap);
		
		/*paging 연산*/
        int records = businessService.getEmpGridTotalCount(paramMap); // 사원목록
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        BusinessJsonVO businessJsonVO = new BusinessJsonVO();

        businessJsonVO.setTotal(total); // page 수
        businessJsonVO.setPage(page); // 현재 page
        businessJsonVO.setRecords(records); // 전체 수
        businessJsonVO.setRows(empList); // list

        MarshallerUtil.marshalling("json", response, businessJsonVO);

    }

    /**
     * <pre>
     * 1. 개요     : 파트/부서/사원 명 검색 ajax
     * 2. 처리내용 : 검색조건에 따른 파트/부서/사원 명을 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : performanceNameAjax
     */
    @RequestMapping("/business/common/performanceNameAjax.do")
    public void performanceNameAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        String searchKeyword = StringUtil.nvl(request.getParameter("searchKeyword")); // 검색어
        String searchType = StringUtil.nvl(request.getParameter("searchType")); // 조회타입
		
		/*parameter를 map에 setting*/
        paramMap.put("searchKeyword", searchKeyword);
        paramMap.put("searchType", searchType);
		
		/*returnVO*/
        String resultName = StringUtil.nvl(businessService.getPerformanceName(paramMap)); // 검색 결과

        BusinessVO businessVO = new BusinessVO();
        businessVO.setResult_nm(resultName); // 파트/부서/사원 명

        MarshallerUtil.marshalling("json", response, businessVO);

    }

    /**
     * <pre>
     * 1. 개요     : 담보약속 팝업 메인
     * 2. 처리내용 : 담보약속 팝업 페이지를 반환한다.
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : promiseList
     */
    @RequestMapping("/business/common/promiseList.do")
    public ModelAndView promiseList(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/common/promiseListPopup");

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 담보약속 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 담보약속 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : promiseGridList
     */
    @RequestMapping("/business/common/promiseGridList.do")
    public void promiseGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String adt_fr_date = StringUtil.nvl(request.getParameter("adt_fr_date").replace("-", "")); // 조회기간 fr
        String adt_to_date = StringUtil.nvl(request.getParameter("adt_to_date").replace("-", "")); // 조회기간 to
        String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
        String sidx = StringUtil.nvl(request.getParameter("sidx"), ""); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순

        if ("".equals(sidx)) {
            sidx = "input_ymd";
            sord = "desc";
        }
		
		/*parameter를 map에 setting*/
        paramMap.put("adt_fr_date", adt_fr_date); // 조회기간 fr
        paramMap.put("adt_to_date", adt_to_date); // 조회기간 to
        paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*담보약속 목록*/
        List<BusinessVO> promiseList = businessService.getPromiseGridList(paramMap);
		
		/*paging 연산*/
        int records = businessService.getPromiseGridTotalCount(paramMap); // 담보약속 목록 총 수
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        BusinessJsonVO businessJsonVO = new BusinessJsonVO();

        businessJsonVO.setTotal(total); // page 수
        businessJsonVO.setPage(page); // 현재 page
        businessJsonVO.setRecords(records); // 전체 수
        businessJsonVO.setRows(promiseList); // list

        MarshallerUtil.marshalling("json", response, businessJsonVO);

    }
}
