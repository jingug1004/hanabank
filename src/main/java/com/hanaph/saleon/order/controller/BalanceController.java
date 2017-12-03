/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.order.controller;

import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;
import com.hanaph.saleon.order.service.BalanceService;
import com.hanaph.saleon.order.vo.BalanceJsonVO;
import com.hanaph.saleon.order.vo.BalanceVO;
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
 * Class Name : BalanceController.java
 * 설명 : 잔고/담보 관련 컨트롤러 클래스
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 28.  jung a Woo
 * </pre>
 *
 * @author : jung a Woo(wja@irush.co.kr)
 * @version :
 * @since : 2014. 10. 28.
 */
@Controller
public class BalanceController {

    /**
     * BalanceService
     */
    @Autowired
    private BalanceService balanceService;

    /**
     * <pre>
     * 1. 개요     : 잔고/담보 조회 화면 호출
     * 2. 처리내용 : 잔고/담보 조회 화면 호출
     * </pre>
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     * @Method Name : balanceList
     */
    @RequestMapping("/order/balanceList.do")
    public ModelAndView balanceList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("order/balanceList");
        return mav;
    }


    /**
     * <pre>
     * 1. 개요     : 잔고/담보 jqgrid 목록 조회
     * 2. 처리내용 : 잔고/담보 jqgrid 목록 조회
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : saleList
     */
    @RequestMapping("/order/balanceGridList.do")
    public void balanceGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/*
         *  세션에서 사원 정보를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String cust_id = StringUtil.nvl(loginUserVO.getEmp_code());                                //cust_id

		/*
         * parameter
		 */
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "");                            //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "");                            //오름(asc), 내림(desc) 차순
		
		/*
		 * parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cust_id", cust_id);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*
		 * 잔고/담보 jqgrid 목록 조회
		 */
        List<BalanceVO> balanceList = balanceService.getBalanceGridList(paramMap);
        int records = balanceService.getBalanceCount(paramMap).getTotalCnt();    //list 총 row 수
        int total = (int) Math.ceil((double) records / (double) perPageRow);            //list 총 page 수
		
		/*
		 * 화면 상단 금액 
		 */
        BalanceVO sumBalance = balanceService.getSumBalance(paramMap);
		
		
		/*
		 * json으로 변환하기 위해 VO를 생성하고 값을 세팅함
		 */
        BalanceJsonVO retrunVO = new BalanceJsonVO();
        retrunVO.setTotal(total);                                //page 수
        retrunVO.setRecords(records);                            //row 총갯수
        retrunVO.setPage(page);                                    //현재 page
        retrunVO.setRows(balanceList);                            //grid 값
        retrunVO.setSumBalance(sumBalance);                        //원장조회 하단의 총액수
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, retrunVO);
    }


    /**
     * <pre>
     * 1. 개요     : 잔고/담보 현황 엑셀 다운로드
     * 2. 처리내용 : 잔고/담보 현황 grid를 엑셀 파일로 생성
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception
     * @Method Name : ledgerGridListExcelDown
     */
    @RequestMapping("/order/balanceGridListExcelDown.do")
    public void ledgerGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String cust_id = StringUtil.nvl(loginUserVO.getEmp_code());                                //cust_id
		
		/*
		 * parameter
		 */
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "");                            //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "");                            //오름(asc), 내림(desc) 차순
		
		/*
		 * parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cust_id", cust_id);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
        paramMap.put("page", null);
        paramMap.put("perPageRow", null);
		
		/*
		 * 잔고/담보 현황 엑셀 다운로드
		 */
        List<BalanceVO> balanceList = balanceService.getBalanceGridList(paramMap);
		
		/*
		 *  excel다운용
		 */
        @SuppressWarnings("rawtypes")
        List<Map> excelMap = new ArrayList<Map>();
		
		/*
		 *  excel다운용 엑셀에 출력 할 항목들의 vo를 map에 담아줍니다.
		 */
        for (int i = 0; i < balanceList.size(); i++) {
            Map<String, String> mapA1 = new HashMap<String, String>();

            BalanceVO balanceVO = new BalanceVO();
            balanceVO = balanceList.get(i);

            mapA1.put("1", StringUtil.nvl(balanceVO.getBill_gb()));
            mapA1.put("2", StringUtil.nvl(balanceVO.getSale_dambo_amt()));
            mapA1.put("3", StringUtil.nvl(balanceVO.getEnd_ymd()));
            mapA1.put("4", StringUtil.nvl(balanceVO.getBill_no()));
            mapA1.put("5", StringUtil.nvl(balanceVO.getBalhang()));
            mapA1.put("6", StringUtil.nvl(balanceVO.getJigeub()));
            mapA1.put("7", StringUtil.nvl(balanceVO.getStart_ymd()));
            mapA1.put("8", StringUtil.nvl(balanceVO.getChulgo_ymd()));

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
