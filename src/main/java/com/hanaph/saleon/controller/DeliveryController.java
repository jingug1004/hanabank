/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.controller;

import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;
import com.hanaph.saleon.order.service.DeliveryService;
import com.hanaph.saleon.order.vo.DeliveryJsonVO;
import com.hanaph.saleon.order.vo.DeliveryVO;
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
 * Class Name : DeliveryController.java
 * 설명 : 원장조회 관련 컨트롤러 클래스
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015.08.07   김태안           최초작성
 * </pre>
 *
 * @author : 김태안
 * @version :
 * @since : 2015. 08. 07.
 */
@Controller
public class DeliveryController {

    /**
     * DeliveryService
     */
    @Autowired
    private DeliveryService deliveryService;

    /**
     * <pre>
     * 1. 개요     : 원장조회 화면 호출
     * 2. 처리내용 : 원장조회 화면 jsp 노출
     * </pre>
     *
     * @param request HttpServletRequest
     * @return
     * @Method Name : deliveryMain
     */
    @RequestMapping("/order/deliveryList.do")
    public ModelAndView deliveryMain(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("order/deliveryList");
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
     * @Method Name : deliveryGridList
     */
    @RequestMapping("/order/deliveryGridList.do")
    public void deliveryGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

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
        List<DeliveryVO> deliveryList = deliveryService.getDeliveryGridList(paramMap);
		
		/*
		 * 화면 하단 총액 조회
		 */
        DeliveryVO sumDelivery = deliveryService.getSumDelivery(paramMap);
		
		/*
		 * json으로 변환하기 위해 VO를 생성하고 값을 세팅함
		 */
        int records = sumDelivery.getTotalCnt();                                //list 총 count 수
        int total = (int) Math.ceil((double) records / (double) perPageRow);        //list 총 page  수

        DeliveryJsonVO retrunDeliveryJsonVO = new DeliveryJsonVO();
        retrunDeliveryJsonVO.setTotal(total);                                    //page 수
        retrunDeliveryJsonVO.setRecords(records);                                //row 총갯수
        retrunDeliveryJsonVO.setPage(page);                                    //현재 page
        retrunDeliveryJsonVO.setRows(deliveryList);                                //grid 값
        retrunDeliveryJsonVO.setSumDelivery(sumDelivery);                            //원장조회 하단의 총액수
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, retrunDeliveryJsonVO);
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
     * @Method Name : deliveryGridListExcelDown
     */
    @RequestMapping("/order/deliveryGridListExcelDown.do")
    public void deliveryGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        List<DeliveryVO> deliveryList = deliveryService.getDeliveryGridList(paramMap);
		
		/*
		 *  excel다운용
		 */
        @SuppressWarnings("rawtypes")
        List<Map> excelMap = new ArrayList<Map>();
		
		/*
		 *  excel다운용 엑셀에 출력 할 항목들의 vo를 map에 담아줍니다.
		 *  밑의 header의 들어가는 컬럼과 같은 순서로 데이터를 셋팅해줘야 함.
		 */
        DeliveryVO deliveryVO = null;
        for (int i = 0; i < deliveryList.size(); i++) {
            Map<String, String> mapA1 = new HashMap<String, String>();    // excel row data
            deliveryVO = deliveryList.get(i);                                // 조회한 데이터의 row data

            mapA1.put("1", StringUtil.nvl(deliveryVO.getYmd()));
            mapA1.put("2", StringUtil.nvl(deliveryVO.getRcust_nm()));
            mapA1.put("3", StringUtil.nvl(deliveryVO.getItem_nm()));
            mapA1.put("4", StringUtil.nvl(deliveryVO.getStandard()));
            mapA1.put("5", StringUtil.nvl(deliveryVO.getQty()));
            mapA1.put("6", StringUtil.nvl(deliveryVO.getDanga()));
            mapA1.put("7", StringUtil.nvl(deliveryVO.getAmt()));
            mapA1.put("8", StringUtil.nvl(deliveryVO.getVat()));
            mapA1.put("9", StringUtil.nvl(deliveryVO.getTot()));
            mapA1.put("10", StringUtil.nvl(deliveryVO.getSukum()));
            mapA1.put("11", StringUtil.nvl(deliveryVO.getRem_amt()));

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
