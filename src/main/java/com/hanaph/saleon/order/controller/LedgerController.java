/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.order.controller;

import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;
import com.hanaph.saleon.order.service.LedgerService;
import com.hanaph.saleon.order.vo.LedgerJsonVO;
import com.hanaph.saleon.order.vo.LedgerVO;
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
 * Class Name : LedgerController.java
 * 설명 : 원장조회 관련 컨트롤러 클래스
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 27.  jung a Woo
 * </pre>
 *
 * @author : jung a Woo(wja@irush.co.kr)
 * @version :
 * @since : 2014. 10. 27.
 */
@Controller
public class LedgerController {

    /**
     * LedgerService
     */
    @Autowired
    private LedgerService ledgerService;

    /**
     * <pre>
     * 1. 개요     : 원장조회 화면 호출
     * 2. 처리내용 : 원장조회 화면 jsp 노출
     * </pre>
     *
     * @param request HttpServletRequest
     * @return
     * @Method Name : ledgerMain
     */
    @RequestMapping("/order/ledgerList.do")
    public ModelAndView ledgerMain(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("order/ledgerList");
        return mav;
    }


    /**
     * <pre>
     * 1. 개요     : 원장 jqgrid 목록 조회
     * 2. 처리내용 : 원장 jqgrid 목록 ajax
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : ledgerGridList
     */
    @RequestMapping("/order/ledgerGridList.do")
    public void ledgerGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

		/*
         * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String cust_id = StringUtil.nvl(loginUserVO.getEmp_code());                                //cust_id

		/*
         * parameter
		 */
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String adt_from = StringUtil.nvl(request.getParameter("adt_from"));                        //조회시작날짜
        String adt_to = StringUtil.nvl(request.getParameter("adt_to"));                            //조회종료날짜
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "");                            //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "");                            //오름(asc), 내림(desc) 차순

		/*
         * parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("adt_from", adt_from);
        paramMap.put("adt_to", adt_to);
        paramMap.put("cust_id", cust_id);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));

		/*
		 * 원장 jqgrid 목록 조회
		 */
        List<LedgerVO> ledgerList = ledgerService.getLedgerGridList(paramMap);
		
		/*
		 * 화면 하단 총액 조회
		 */
        LedgerVO sumLedger = ledgerService.getSumLedger(paramMap);
		
		/*
		 * json으로 변환하기 위해 VO를 생성하고 값을 세팅함
		 */
        int records = sumLedger.getTotalCnt();                                //list 총 count 수
        int total = (int) Math.ceil((double) records / (double) perPageRow);        //list 총 page  수

        LedgerJsonVO retrunLedgerJsonVO = new LedgerJsonVO();
        retrunLedgerJsonVO.setTotal(total);                                    //page 수
        retrunLedgerJsonVO.setRecords(records);                                //row 총갯수
        retrunLedgerJsonVO.setPage(page);                                    //현재 page
        retrunLedgerJsonVO.setRows(ledgerList);                                //grid 값
        retrunLedgerJsonVO.setSumLedger(sumLedger);                            //원장조회 하단의 총액수
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, retrunLedgerJsonVO);
    }

    /**
     * <pre>
     * 1. 개요     : 원장 jqgrid 엑셀 다운로드
     * 2. 처리내용 : 원장 jqgrid를 엑셀 파일로 생성
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     * @Method Name : ledgerGridListExcelDown
     */
    @RequestMapping("/order/ledgerGridListExcelDown.do")
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
        String adt_from = StringUtil.nvl(request.getParameter("adt_from"));                        //조회시작날짜
        String adt_to = StringUtil.nvl(request.getParameter("adt_to"));                            //조회종료날짜
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "");                            //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "");                            //오름(asc), 내림(desc) 차순
		
		/*
		 * parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("adt_from", adt_from);
        paramMap.put("adt_to", adt_to);
        paramMap.put("cust_id", cust_id);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
        paramMap.put("page", null);            // excel다운용 전체 page처리를 위해 page null값
        paramMap.put("perPageRow", null);    // excel다운용 전체 page처리를 위해 page perPageRow값
		
		/*
		 * 원장 jqgrid 목록
		 */
        List<LedgerVO> ledgerList = ledgerService.getLedgerGridList(paramMap);
		
		/*
		 *  excel다운용
		 */
        @SuppressWarnings("rawtypes")
        List<Map> excelMap = new ArrayList<Map>();
		
		/*
		 *  excel다운용 엑셀에 출력 할 항목들의 vo를 map에 담아줍니다.
		 *  밑의 header의 들어가는 컬럼과 같은 순서로 데이터를 셋팅해줘야 함.
		 */
        LedgerVO ledgerVO = null;
        for (int i = 0; i < ledgerList.size(); i++) {
            Map<String, String> mapA1 = new HashMap<String, String>();    // excel row data
            ledgerVO = ledgerList.get(i);                                // 조회한 데이터의 row data

            mapA1.put("1", StringUtil.nvl(ledgerVO.getYmd()));
            mapA1.put("2", StringUtil.nvl(ledgerVO.getRcust_nm()));
            mapA1.put("3", StringUtil.nvl(ledgerVO.getItem_nm()));
            mapA1.put("4", StringUtil.nvl(ledgerVO.getStandard()));
            mapA1.put("5", StringUtil.nvl(ledgerVO.getQty()));
            mapA1.put("6", StringUtil.nvl(ledgerVO.getDanga()));
            mapA1.put("7", StringUtil.nvl(ledgerVO.getAmt()));
            mapA1.put("8", StringUtil.nvl(ledgerVO.getVat()));
            mapA1.put("9", StringUtil.nvl(ledgerVO.getTot()));
            mapA1.put("10", StringUtil.nvl(ledgerVO.getSukum()));
            mapA1.put("11", StringUtil.nvl(ledgerVO.getRem_amt()));

            excelMap.add(mapA1);
        }
		
		/*
		 * excel의 header 정보 및 mapping 정보 셋팅
		 */
        String[] header = {"일자", "납품처", "품목", "단위", "수량", "단가", "공급가액", "세액", "합계금액", "수금", "잔액"};    // excel다운용 엑셀 헤더 값
        String[] content = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11"};                                    // excel다운용 mapA1에 담은 mapA1 이름과 동일 해야 합니다.
		
		/*
		 *  실제로 excel 파일을 생성해서 다운로드 받을 수 있게 하는 공통 모듈 호출
		 */
        ExcelDownManager.ExcelDown("원장조회", header, content, excelMap, response);

    }
}
