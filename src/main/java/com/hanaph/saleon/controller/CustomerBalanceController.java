/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.controller;

import com.hanaph.saleon.business.service.CustomerBalanceService;
import com.hanaph.saleon.business.vo.CustomerBalanceJsonVO;
import com.hanaph.saleon.business.vo.CustomerBalanceVO;
import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : CustomerBalanceController.java
 * 설명 : 영업관리 > 잔고 담보현황 Controller class
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015. 2. 2.      slamwin
 * </pre>
 *
 * @author : slamwin(@irush.co.kr)
 * @version :
 * @since : 2015. 2. 2.
 */
@Controller
public class CustomerBalanceController {

    /**
     * log4j
     */
    //private static final Logger logger = Logger.getLogger(CustomerBalanceController.class);

    /**
     * MgmtInquireService
     */
    @Autowired
    private CustomerBalanceService customerBalanceService;


    /**
     * <pre>
     * 1. 개요     : 영업관리 잔고/담보현황 리스트 출력
     * 2. 처리내용 : 영업관리 잔고/담보현황 리스트 출력
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : balanceList
     */
    @RequestMapping("/business/customerBalanceList.do")
    public ModelAndView getCustomerBalanceList(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/customerBalanceList");

        return mav;
    }


    /**
     * <pre>
     * 1. 개요     : 영업관리 잔고/담보현황 거래처 jqgrid 목록
     * 2. 처리내용 : 거래처코드 검색 결과에 따라 grid list를 호출 한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : getCustomerBalanceGridList
     */
    @RequestMapping("/business/customerBalanceGridList.do")
    public void getCustomerBalanceGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();

        String customerId = StringUtil.nvl(request.getParameter("customerId"), "");        // 거래처코드
        String gubun = StringUtil.nvl(request.getParameter("gubun"), "0");                // 거래구분
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "CUST_NM");            // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), "ASC");                // 정렬순

		/*parameter를 map에 setting*/
        paramMap.put("customerId", customerId); // 거래처코드
        paramMap.put("gubun", gubun);            // 거래구분
        paramMap.put("sidx", sidx);            // 정렬
        paramMap.put("sord", sord);            // 정렬순

		/*거래처 목록*/
        List<CustomerBalanceVO> customerBalanceList = customerBalanceService.getCustomerBalanceGridList(paramMap);

		/*
         * response body로 뿌려질 결과 list를 object에 담는다.
		 */
        CustomerBalanceJsonVO returnCustomerBalanceJsonVO = new CustomerBalanceJsonVO();

        returnCustomerBalanceJsonVO.setRows(customerBalanceList);                    // grid 값
        returnCustomerBalanceJsonVO.setRecords(customerBalanceList.size());            // 전체 레코드(row)수

        MarshallerUtil.marshalling("json", response, returnCustomerBalanceJsonVO);

    }

    /**
     * <pre>
     * 1. 개요     : 영업관리 잔고/담보현황 거래처 jqgrid 목록
     * 2. 처리내용 : 거래처코드 검색 결과에 따라 grid list를 호출 한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : getCustomerBalanceGridList
     */
    @RequestMapping("/business/customerBalanceGridDetail.do")
    public void getCustomerBalanceGridDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();

        String customerId = StringUtil.nvl(request.getParameter("customerId"), "");                // 거래처코드

		/* parameter를 map에 setting */
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "");                            //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "");                            //오름(asc), 내림(desc) 차순

        paramMap.put("customerId", customerId); // 거래처코드
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/* 거래처 잔고/담보 현황 목록 */
        List<CustomerBalanceVO> customerBalanceDetailList = customerBalanceService.getCustomerBalanceGridDetail(paramMap);
        int records = customerBalanceService.getCustomerBalanceDetailCount(paramMap);                //list 총 row 수
        int total = (int) Math.ceil((double) records / (double) perPageRow);                                //list 총 page 수
		
		/*
		 * 화면 상단 금액 
		 */
        CustomerBalanceVO sumBalance = customerBalanceService.getCustomerSumBalance(paramMap);
		
		/*
		 * response body로 뿌려질 결과 list를 object에 담는다.
		 */
        CustomerBalanceJsonVO returnCustomerBalanceJsonVO = new CustomerBalanceJsonVO();

        returnCustomerBalanceJsonVO.setTotal(total);                    // page 수
        returnCustomerBalanceJsonVO.setRecords(records);                // row 총갯수
        returnCustomerBalanceJsonVO.setPage(page);                        // 현재 page
        returnCustomerBalanceJsonVO.setRows(customerBalanceDetailList);    // grid 값
        returnCustomerBalanceJsonVO.setSumBalance(sumBalance);        // 원장조회 상단의 금액

        MarshallerUtil.marshalling("json", response, returnCustomerBalanceJsonVO);

    }

    /**
     * <pre>
     * 1. 개요     : 영업관리 잔고/담보현황 거래처 jqgrid 엑셀 다운로드
     * 2. 처리내용 : 거래처코드 검색 결과에 따라 전체 페이지의 엑셀을 다운 받는다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     * @Method Name : customerBalanceDetailExcel
     */
    @RequestMapping("/business/customerBalanceDetailExcel.do")
    public void getCustomerBalanceDetailExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> paramMap = new HashMap<String, String>();

        String customerId = StringUtil.nvl(request.getParameter("customerId"), "");                // 거래처코드

        String sidx = StringUtil.nvl(request.getParameter("sidx"), "");                            //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "");                            //오름(asc), 내림(desc) 차순

        paramMap.put("customerId", customerId); // 거래처코드
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
        paramMap.put("page", null);
        paramMap.put("perPageRow", null);
		
		/* 거래처 잔고/담보 현황 목록 */
        List<CustomerBalanceVO> customerBalanceDetailList = customerBalanceService.getCustomerBalanceGridDetail(paramMap);
		
		/*
		 *  excel다운용
		 */
        @SuppressWarnings("rawtypes")
        List<Map> excelMap = new ArrayList<Map>();
		
		/*
		 *  excel다운용 엑셀에 출력 할 항목들의 vo를 map에 담아줍니다.
		 */
        for (int i = 0; i < customerBalanceDetailList.size(); i++) {
            Map<String, String> mapA1 = new HashMap<String, String>();

            CustomerBalanceVO customerBalanceVO = new CustomerBalanceVO();
            customerBalanceVO = customerBalanceDetailList.get(i);

            mapA1.put("1", StringUtil.nvl(customerBalanceVO.getBill_gb()));
            mapA1.put("2", StringUtil.nvl(customerBalanceVO.getSale_dambo_amt()));
            mapA1.put("3", StringUtil.nvl(customerBalanceVO.getEnd_ymd()));
            mapA1.put("4", StringUtil.nvl(customerBalanceVO.getBill_no()));
            mapA1.put("5", StringUtil.nvl(customerBalanceVO.getBalhang()));
            mapA1.put("6", StringUtil.nvl(customerBalanceVO.getJigeub()));
            mapA1.put("7", StringUtil.nvl(customerBalanceVO.getStart_ymd()));
            mapA1.put("8", StringUtil.nvl(customerBalanceVO.getChulgo_ymd()));

            excelMap.add(mapA1);
        }

        String[] header = {"담보종류", "매출담보금액", "만기일", "어음번호", "발행처", "지급처", "발행일", "출고일"};        // excel다운용 엑셀 헤더 값
        String[] content = {"1", "2", "3", "4", "5", "6", "7", "8"};                                                // excel다운용 mapA1에 담은 mapA1 이름과 동일 해야 합니다.
		
		/*
		 * 실제로 excel 파일을 생성해서 다운로드 받을 수 있게 하는 공통 모듈 호출
		 */
        ExcelDownManager.ExcelDown("잔고_담보현황", header, content, excelMap, response);

    }

}
