/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.order.controller;

import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;
import com.hanaph.saleon.order.service.OrderService;
import com.hanaph.saleon.order.vo.ItemJsonVO;
import com.hanaph.saleon.order.vo.ItemVO;
import com.hanaph.saleon.order.vo.OrderJsonVO;
import com.hanaph.saleon.order.vo.OrderVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.*;

/**
 * <pre>
 * Class Name : OrderController.java
 * 설명 : 온라인발주 주문 관련 컨트롤러 클래스
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 30.      jung a Woo
 * </pre>
 *
 * @author : jung a Woo(wja@irush.co.kr)
 * @version :
 * @since : 2014. 10. 30.
 */
@Controller
public class OrderController {

    /**
     * log
     */
    private static final Logger logger = Logger.getLogger(OrderController.class);

    /**
     * OrderService
     */
    @Autowired
    private OrderService orderService;

    /**
     * <pre>
     * 1. 개요     : 온라인발주 주문등록 화면
     * 2. 처리내용 : 온라인발주 주문등록 jsp 노출 및 등록에 필요한 기본값 setting
     * </pre>
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     * @Method Name : orderInsertForm
     */
    @RequestMapping("/order/orderInsertForm.do")
    public ModelAndView orderInsertForm(HttpServletRequest request) {
        /*
         *	ModelAndView 객체 생성.
		 */
        ModelAndView mav = new ModelAndView("order/orderInsertForm");

		/*
         * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                    //cust_id

		/*
         * parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("gs_empCode", gs_empCode);
		
		/*
		 * DB에서 조회한 등록에 필요한 기본값 ModelAndView에 저장
		 */
        mav.addObject("orderInit", orderService.getOrderInit(paramMap));

        return mav;
    }


    /**
     * <pre>
     * 1. 개요     : 온라인발주 주문수정 화면
     * 2. 처리내용 : 온라인발주 주문수정 jsp 노출 및 수정에 필요한 기본값 setting
     * </pre>
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     * @Method Name : orderUpdateForm
     */
    @RequestMapping("/order/orderUpdateForm.do")
    public ModelAndView orderUpdateForm(HttpServletRequest request) {
		
		/*
		 * 객체 생성
		 */
        ModelAndView mav = new ModelAndView("order/orderUpdateForm");
        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                //cust_id

		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("gs_empCode", gs_empCode);
		
		/*
		 * DB에서 조회한 등록에 필요한 기본값 ModelAndView에 저장
		 */
        mav.addObject("orderInit", orderService.getOrderUpInit(paramMap));

        return mav;
    }


    /**
     * <pre>
     * 1. 개요     : 판매처 선택 popup 화면
     * 2. 처리내용 : 판매처 선택 popup jsp 노출
     * </pre>
     *
     * @param request
     * @return ModelAndView
     * @Method Name : storeListPopup
     */
    @RequestMapping("/order/common/storeListPopup.do")
    public ModelAndView storeListPopup(HttpServletRequest request) {

		/*
		 * 객체생성
		 */
        ModelAndView mav = new ModelAndView("order/common/storeListPopup");
        Map<String, String> paramMap = new HashMap<String, String>();
		
		
		/*
		 * 세션생성 세션에서 cust_id를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String cust_id = StringUtil.nvl(loginUserVO.getEmp_code());        //cust_id
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("cust_id", cust_id);


        return mav;
    }


    /**
     * <pre>
     * 1. 개요     : 판매처검색 grid 목록
     * 2. 처리내용 : 판매처검색 grid 목록 ajax
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : storeGridList
     */
    @RequestMapping("/order/common/storeGridList.do")
    public void storeGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		/*
		 * 객체생성
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        OrderJsonVO returnVO = new OrderJsonVO();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String as_cust_id = StringUtil.nvl(loginUserVO.getEmp_code());                                                //cust_id
		
		/*
		 * parameter
		 */
        String searchKeyword = URLDecoder.decode(StringUtil.nvl(request.getParameter("searchKeyword")), "UTF-8");    //키워드
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "cust_nm");                                        //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "asc");                                            //오름(asc), 내림(desc) 차순
		
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("as_cust_id", as_cust_id);
        paramMap.put("searchKeyword", searchKeyword);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
		
		/*
		 * 페이징 list
		 */
        List<OrderVO> orderVO = orderService.getStoreGridList(paramMap);
		
		/*
		 * page의 리턴시킬 값
		 */
        returnVO.setRows(orderVO);
        returnVO.setRecords(orderVO.size());
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, returnVO);
    }


    /**
     * <pre>
     * 1. 개요     : 출하중지처 여부와 등록된 판매처코드 여부와, 병원담당자 정보
     * 2. 처리내용 : 출하중지처 여부와 등록된 판매처코드 여부와, 병원담당자 정보를 가져온다.
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : storeChkAjax
     */
    @RequestMapping("/order/common/storeChkAjax.do")
    public void storeChkAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		/*
		 * 객체생성
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        OrderVO returnVO = new OrderVO();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                    //cust_id
		
		/*
		 * parameter
		 */
        String rcust_id = StringUtil.nvl(request.getParameter("rcust_id"), "");            //sort할 컬럼명
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("gs_empCode", gs_empCode);
        paramMap.put("rcust_id", rcust_id);
		
		/*
		 * 출하중지처 여부와 등록된 판매처코드 여부와, 병원담당자 정보를 가져온다.
		 */
        returnVO = orderService.getstoreChk(paramMap);
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, returnVO);
    }

    /**
     * <pre>
     * 1. 개요     : 판매처별 제품 jqgrid 목록
     * 2. 처리내용 : 판매처별 제품 jqgrid 목록 ajax
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : itemGridList
     */
    @RequestMapping("/order/itemGridList.do")
    public void itemGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		/*
		 * 객체생성
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        ItemJsonVO itemJsonVO = new ItemJsonVO();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                    //사원 코드
		
		/*
		 * parameter
		 */
        String rcust_id = StringUtil.nvl(request.getParameter("rcust_id"), "");            //판매처 id
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "item_nm");            //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "");                    //오름(asc), 내림(desc) 차순
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("gs_empCode", gs_empCode);
        paramMap.put("rcust_id", rcust_id);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
		
		/*
		 * 페이징 list
		 */
        List<ItemVO> itemList = orderService.getItemGridList(paramMap);
        itemJsonVO.setRecords(itemList.size());        //row 총갯수
        itemJsonVO.setRows(itemList);                //grid 값
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, itemJsonVO);

    }

    /**
     * <pre>
     * 1. 개요     : 온라인 주문등록 엑셀 다운로드
     * 2. 처리내용 : 온라인 주문등록 grid를 엑셀 파일로 생성
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception
     * @Method Name : onderInsertGridListExcelDown
     */
    @RequestMapping("/order/onderInsertGridListExcelDown.do")
    public void onderInsertGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		 * 객체생성
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                    //사원코드
		
		/*
		 * parameter
		 */
        String rcust_id = StringUtil.nvl(request.getParameter("rcust_id"), "");            //판매처 id
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "");                    //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "");                    //오름(asc), 내림(desc) 차순
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("gs_empCode", gs_empCode);
        paramMap.put("rcust_id", rcust_id);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
        paramMap.put("page", null);            // excel다운용 전체 page처리를 위해 page null값
        paramMap.put("perPageRow", null);    // excel다운용 전체 page처리를 위해 page perPageRow값
		
		/*
		 * 페이징 list
		 */
        List<ItemVO> itemList = orderService.getItemGridList(paramMap);
		
		/*
		 *  excel다운용
		 */
        @SuppressWarnings("rawtypes")
        List<Map> excelMap = new ArrayList<Map>();
		
		/*
		 *  excel다운용 엑셀에 출력 할 항목들의 vo를 map에 담아줍니다.
		 *  밑의 header의 들어가는 컬럼과 같은 순서로 데이터를 셋팅해줘야 함.
		 */
        for (int i = 0; i < itemList.size(); i++) {
            Map<String, String> mapA1 = new HashMap<String, String>();

            ItemVO itemVO = new ItemVO();
            itemVO = itemList.get(i);

            mapA1.put("1", StringUtil.nvl(itemVO.getItem_id()));
            mapA1.put("2", StringUtil.nvl(itemVO.getItem_nm()));
            mapA1.put("3", StringUtil.nvl(itemVO.getStandard()));
            mapA1.put("4", StringUtil.nvl(String.valueOf(itemVO.getQty())));
            mapA1.put("5", StringUtil.nvl(String.format("%.0f", itemVO.getBal_amt())));
            mapA1.put("6", StringUtil.nvl(String.format("%.0f", itemVO.getSupply_net())));
            mapA1.put("7", StringUtil.nvl(String.format("%.0f", itemVO.getSupply_vat())));
            mapA1.put("8", StringUtil.nvl(String.format("%.0f", itemVO.getTot_amt())));

            excelMap.add(mapA1);
        }
		
		/*
		 * excel의 header 정보 및 mapping 정보 셋팅
		 */
        String[] header = {"제품코드", "제품명", "규격", "수량", "단가", "공급가액", "세액", "합계금액"}; // excel다운용 엑셀 헤더 값
        String[] content = {"1", "2", "3", "4", "5", "6", "7", "8"}; // excel다운용 mapA1에 담은 mapA1 이름과 동일 해야 합니다.
		
		/*
		 *  실제로 excel 파일을 생성해서 다운로드 받을 수 있게 하는 공통 모듈 호출
		 */
        ExcelDownManager.ExcelDown("주문등록 제품목록", header, content, excelMap, response); // excel다운용

    }


    /**
     * <pre>
     * 1. 개요     : 해당제품이 마약,향정일때 납품처의 향정마약여부 체크 ajax
     * 2. 처리내용 :  해당제품이 마약,향정일때 납품처의 향정마약여부 체크한다.
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : itemGbYnAjax
     */
    @RequestMapping("/order/common/itemGbYnAjax.do")
    public void itemGbYnAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/*
		 * 객체생성
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
				
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                    //사원 코드
		
		/*
		 * parameter
		 */
        String rcust_id = StringUtil.nvl(request.getParameter("rcust_id"), "");            //판매처 id
        String ls_item_id = StringUtil.nvl(request.getParameter("ls_item_id"), "");        //제품 id
        String saupjang_cd = StringUtil.nvl(request.getParameter("saupjang_cd"), "");    //제품 id
        String ls_ymd = StringUtil.nvl(request.getParameter("ls_ymd"), "");                //주문요청일
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("gs_empCode", gs_empCode);
        paramMap.put("rcust_id", rcust_id);
        paramMap.put("ls_item_id", ls_item_id);
        paramMap.put("saupjang_cd", saupjang_cd);
        paramMap.put("ls_ymd", ls_ymd);
		
		/*
		 * 향정마약여부 체크, 주문 가능 수량
		 */
        ItemVO itemVO = orderService.getItemGbYn(paramMap);
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, itemVO);

    }

    /**
     * <pre>
     * 1. 개요     : 제품체크, 창고재고 ajax
     * 2. 처리내용 : 주문등록 제품 재고 수량 및 체크를 한다.
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : itemChkRegAjax
     */
    @RequestMapping("/order/itemChkRegAjax.do")
    public void itemChkRegAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/*
		 * 객체생성
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                    //사원 코드
		
		/*
		 * parameter
		 */
        String ls_item_cd = StringUtil.nvl(request.getParameter("ls_item_cd"), "");        //제품 id
        String ldt_jaego = StringUtil.nvl(request.getParameter("ldt_jaego"), "");        //ldt_jaego
        String ls_rcust_id = StringUtil.nvl(request.getParameter("ls_rcust_id"), "");    //판매처
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("ls_item_cd", ls_item_cd);
        paramMap.put("ldt_jaego", ldt_jaego);
        paramMap.put("ls_rcust_id", ls_rcust_id);
        paramMap.put("cust_id", gs_empCode);
		
		/*
		 * 제품 체크, 창고 재고
		 */
        ItemVO itemVO = orderService.getItemChkReg(paramMap);
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, itemVO);

    }


    /**
     * <pre>
     * 1. 개요     : 주문등록 ajax
     * 2. 처리내용 : parameter로 넘겨받은 값을 db에 저장한다.
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : insertOrderAjax
     */
    @RequestMapping("/order/insertOrderAjax.do")
    public void insertOrderAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/*
		 * 객체생성
		 */
        ItemVO retrunVO = new ItemVO();
        Map<String, Object> paramMap = new HashMap<String, Object>();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                                //사원 코드
		
		/*
		 * parameter
		 */
        String ldt_req_date = StringUtil.nvl(request.getParameter("ldt_req_date"), "");                //주문요청일
        String[] item_id = request.getParameterValues("item_id");                                    //제품코드
        String[] qty = request.getParameterValues("qty");                                            //수량
        String[] bal_amt = request.getParameterValues("bal_amt");                                    //발행금액
        String[] supply_net = request.getParameterValues("supply_net");                                //supply_net
        String[] supply_vat = request.getParameterValues("supply_vat");                                //supply_vat
        String[] percent = request.getParameterValues("percent");                                    //percent
        String[] dc_amt = request.getParameterValues("dc_amt");                                        //할인금액
        String[] dc_danga = request.getParameterValues("dc_danga");                                    //할증(할인증정)수량
        String[] saupjang_cd = request.getParameterValues("saupjang_cd");                            //회계사업장코드

        String ls_sawon_id = StringUtil.nvl(request.getParameter("ls_sawon_id"), "");                //사원코드
        String ls_rsawon_id = StringUtil.nvl(request.getParameter("ls_rsawon_id"), "");                //실거래처 사원코드
        String ls_rcust_cd = StringUtil.nvl(request.getParameter("ls_rcust_cd"), "");                //실거래처 코드
        String ld_supply_net_sum = StringUtil.nvl(request.getParameter("ld_supply_net_sum"), "");    //총금액
        String ld_supply_vat_sum = StringUtil.nvl(request.getParameter("ld_supply_vat_sum"), "");    //총부가세

        String ls_bigo = StringUtil.nvl(request.getParameter("bigo"), "");                            //비고
        String ls_limit_yn = StringUtil.nvl(request.getParameter("ls_limit_yn"), "");                //제한여부
        String ls_pro_date = StringUtil.nvl(request.getParameter("ls_pro_date"), "");                //거래처약속일자
        String ls_pro_bigo = StringUtil.nvl(request.getParameter("ls_pro_bigo"), "");                //거래처약속내용
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("tableType", "SALE0203");
        paramMap.put("ldt_req_date", ldt_req_date);
        paramMap.put("gs_empCode", gs_empCode);

		/*
		 * 주문 master
		 */
        paramMap.put("ls_sawon_id", ls_sawon_id);
        paramMap.put("ls_rsawon_id", ls_rsawon_id);
        paramMap.put("ls_rcust_cd", ls_rcust_cd);
        paramMap.put("ld_supply_net_sum", ld_supply_net_sum);
        paramMap.put("ld_supply_vat_sum", ld_supply_vat_sum);
        paramMap.put("ls_bigo", ls_bigo);
        paramMap.put("ls_limit_yn", ls_limit_yn);
        paramMap.put("ls_pro_date", ls_pro_date);
        paramMap.put("ls_pro_bigo", ls_pro_bigo);
		
		/*
		 * 주문 detail 
		 */
        paramMap.put("item_id", item_id);
        paramMap.put("qty", qty);
        paramMap.put("bal_amt", bal_amt);
        paramMap.put("supply_net", supply_net);
        paramMap.put("supply_vat", supply_vat);
        paramMap.put("percent", percent);
        paramMap.put("dc_amt", dc_amt);
        paramMap.put("dc_danga", dc_danga);
        paramMap.put("saupjang_cd", saupjang_cd);
		
		/*
		 * 주문 등록을 진행하고 그 결과를 json으로 리턴한다. 
		 */
        boolean result = false;            //등록 결과

        try {
            result = orderService.insertOrder(paramMap);
        } catch (Exception ex) {
            logger.error("주문 등록 에러 ::: [custId:" + gs_empCode + "][rcustId:" + ls_rcust_cd + "][reqDate:" + ldt_req_date + "] ");
            logger.error("item_id : " + Arrays.toString(item_id));
            logger.error("qty : " + Arrays.toString(qty));
            logger.error("bal_amt : " + Arrays.toString(bal_amt));
            logger.error("supply_net : " + Arrays.toString(supply_net));
            logger.error("supply_vat : " + Arrays.toString(supply_vat));
            ex.printStackTrace();
        }

        if (result) {
            retrunVO.setResult("Y");    //Y이면 정상 update
        } else {
            retrunVO.setResult("N");    //N이면 update error
        }

        MarshallerUtil.marshalling("json", response, retrunVO);    //VO형태를 json형태로 마샬

    }


    /**
     * <pre>
     * 1. 개요     : 온라인 주문 담보 제공 popup 화면
     * 2. 처리내용 : 온라인 주문 담보 제공 popup jsp 노출
     * </pre>
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     * @Method Name : guaranteePopup
     */
    @RequestMapping("/order/common/guaranteePopup.do")
    public ModelAndView guaranteePopup(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("order/common/guaranteePopup");
        return mav;
    }


    /**
     * <pre>
     * 1. 개요     : 주문 Master Grid List
     * 2. 처리내용 : 주문 Master 목록을 가져온다.
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : masterGridList
     */
    @RequestMapping("/order/masterGridList.do")
    public void masterGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		/*
		 * 객체생성
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        ItemJsonVO returnVO = new ItemJsonVO();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                    //cust_id
		
		/*
		 * parameter
		 */
        String fr_date = StringUtil.nvl(request.getParameter("fr_date"), "");            //조회 시작일
        String to_date = StringUtil.nvl(request.getParameter("to_date"), "");            //조회 종료일
        String rcust_id = StringUtil.nvl(request.getParameter("rcust_id"), "");        //판매처 id
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "");                    //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "asc");                //오름(asc), 내림(desc) 차순
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("gs_empCode", gs_empCode);
        paramMap.put("fr_date", fr_date);
        paramMap.put("to_date", to_date);
        paramMap.put("rcust_id", rcust_id);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
		
		/*
		 * 페이징 list
		 */
        List<ItemVO> itemVO = orderService.getMasterGridList(paramMap);
		
		/*
		 * page의 리턴시킬 값
		 */
        returnVO.setRows(itemVO);
        returnVO.setRecords(itemVO.size());
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, returnVO);
    }


    /**
     * <pre>
     * 1. 개요     : 주문 Detail Grid List
     * 2. 처리내용 : 주문 Detail 목록을 가져온다.
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : detailGridList
     */
    @RequestMapping("/order/detailGridList.do")
    public void detailGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		/*
		 * 객체생성
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        ItemJsonVO returnVO = new ItemJsonVO();
		
		/*
		 * parameter
		 */
        String ymd = StringUtil.nvl(request.getParameter("ymd"), "");                //주문요청일
        String gumae_no = StringUtil.nvl(request.getParameter("gumae_no"), "");        //주문번호
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "item_nm");                //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "asc");            //오름(asc), 내림(desc) 차순
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("ymd", ymd);
        paramMap.put("gumae_no", gumae_no);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
		
		/*
		 * 페이징 list
		 */
        List<ItemVO> itemVO = orderService.getDetailGridList(paramMap);
		
		/*
		 * page의 리턴시킬 값
		 */
        returnVO.setRows(itemVO);
        returnVO.setRecords(itemVO.size());
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, returnVO);
    }


    /**
     * <pre>
     * 1. 개요     : 제품별 bas_amt값 ajax
     * 2. 처리내용 : 제품별 bas_amt값을 가져온다
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : basDangaAjax
     */
    @RequestMapping("/order/basDangaAjax.do")
    public void basDangaAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 객체 생성
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*
		 *	세션생성 세션에서 emp_code를 가져온다. 
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                            //emp_code

		/*
		 * parameter
		 */
        String ls_rcust_id = StringUtil.nvl(request.getParameter("ls_rcust_id"), "");            //판매처 id
        String ls_item_id = StringUtil.nvl(request.getParameter("ls_item_id"), "");                //제품 id
		
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("gs_empCode", gs_empCode);
        paramMap.put("ls_rcust_id", ls_rcust_id);
        paramMap.put("ls_item_id", ls_item_id);
		
		/*
		 * result 
		 */
        ItemVO itemVO = orderService.getBasDanga(paramMap);
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, itemVO);
    }


    /**
     * <pre>
     * 1. 개요     : 제품 수량 입력시 제품 체크 ajax
     * 2. 처리내용 : 제품 수량 입력시 제품 체크값을 가져온다
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : itemChkEditAjax
     */
    @RequestMapping("/order/itemChkEditAjax.do")
    public void itemChkEditAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		
		/*
		 * 객체 생성
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                            //emp_code

		/*
		 * parameter
		 */
        String ls_rcust_id = StringUtil.nvl(request.getParameter("ls_rcust_id"), "");            //판매처 id
        String ls_item_cd = StringUtil.nvl(request.getParameter("ls_item_cd"), "");                //제품 id
        String ldt_jaego = StringUtil.nvl(request.getParameter("ldt_jaego"), "");                //재고날짜
        String ls_gumae_no = StringUtil.nvl(request.getParameter("ls_gumae_no"), "");            //주문번호
		
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("gs_empCode", gs_empCode);
        paramMap.put("ls_rcust_id", ls_rcust_id);
        paramMap.put("ls_item_cd", ls_item_cd);
        paramMap.put("ldt_jaego", ldt_jaego);
        paramMap.put("ls_gumae_no", ls_gumae_no);
		
		/*
		 * result 
		 */
        ItemVO itemVO = orderService.getItemChkEdit(paramMap);

        //VO형태를 json형태로 마샬
        MarshallerUtil.marshalling("json", response, itemVO);
    }


    /**
     * <pre>
     * 1. 개요     : 주문 승인체크 ajax
     * 2. 처리내용 : 주문 승인여부를 가져온다.
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : orderApprovalAjax
     */
    @RequestMapping("/order/orderApprovalAjax.do")
    public void orderApprovalAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/*
		 * 객체 생성
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("ls_gumae_no", StringUtil.nvl(request.getParameter("ls_gumae_no"), ""));        //주문번호
		
		/*
		 * result 
		 */
        ItemVO itemVO = orderService.getReceiptChk(paramMap);
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, itemVO);
    }

    /**
     * <pre>
     * 1. 개요     : 주문 수정 ajax
     * 2. 처리내용 : parameter로 넘겨받은 값을 db에 수정한다.
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : updateOrderAjax
     */
    @RequestMapping("/order/updateOrderAjax.do")
    public void updateOrderAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/*
		 * 객체생성
		 */
        ItemVO retrunVO = new ItemVO();
        Map<String, Object> paramMap = new HashMap<String, Object>();
		
		/*
		 * parameter
		 */
        String ldt_req_date = StringUtil.nvl(request.getParameter("ldt_req_date"), "");    //주문요청일
        String ls_gumae_no = StringUtil.nvl(request.getParameter("gumae_no"), "");        //구매번호
        String ls_bigo = StringUtil.nvl(request.getParameter("bigo"), "");                //비고
        String ld_amt_sum = StringUtil.nvl(request.getParameter("ld_amt_sum"), "");        //총금액
        String ld_vat_sum = StringUtil.nvl(request.getParameter("ld_vat_sum"), "");        //총부가세
        String ls_limit_yn = StringUtil.nvl(request.getParameter("ls_limit_yn"), "");    //제한여부

        String[] ld_item_id = request.getParameterValues("item_id");                    //제품코드
        String[] input_seq = request.getParameterValues("input_seq");                    //입력순서
        String[] qty = request.getParameterValues("qty");                                //수량
        String[] amend_qty = request.getParameterValues("amend_qty");                    //변경 수량
        String[] amend_amt = request.getParameterValues("amend_amt");                    //변경 금액
        String[] amend_vat = request.getParameterValues("amend_vat");                    //변경 부가세
        String[] dc_amt = request.getParameterValues("dc_amt");                            //할인 금액
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("ldt_req_date", ldt_req_date);
        paramMap.put("ls_gumae_no", ls_gumae_no);
        paramMap.put("ls_bigo", ls_bigo);
        paramMap.put("ld_amt_sum", ld_amt_sum);
        paramMap.put("ld_vat_sum", ld_vat_sum);
        paramMap.put("ls_limit_yn", ls_limit_yn);
		
		/*
		 * 주문 master
		 */
        paramMap.put("ld_item_id", ld_item_id);
        paramMap.put("input_seq", input_seq);
        paramMap.put("qty", qty);
        paramMap.put("amend_qty", amend_qty);
        paramMap.put("amend_amt", amend_amt);
        paramMap.put("amend_vat", amend_vat);
        paramMap.put("dc_amt", dc_amt);
		
		/*
		 * 주문 수정을 진행하고 그 결과를 json으로 리턴
		 */
        boolean result = false;                                    //주문 수정 결과

        try {
            result = orderService.updateOrder(paramMap);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        if (result) {
            retrunVO.setResult("Y");                            //Y이면 정상 update
        } else {
            retrunVO.setResult("N");                            //N이면 update error
        }

        MarshallerUtil.marshalling("json", response, retrunVO);    //VO형태를 json형태로 마샬

    }


    /**
     * <pre>
     * 1. 개요     : 온라인 주문 master grid 엑셀 다운로드
     * 2. 처리내용 : 온라인 주문 master grid를 엑셀 파일로 생성
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception
     * @Method Name : onderUpdateGridListExcelDown
     */
    @RequestMapping("/order/onderUpdateGridListExcelDown.do")
    public void onderUpdateGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                    //emp_code
		
		/*
		 * parameter
		 */
        String fr_date = StringUtil.nvl(request.getParameter("fr_date"), "");            //조회 시작일
        String to_date = StringUtil.nvl(request.getParameter("to_date"), "");            //조회 종료일
        String rcust_id = StringUtil.nvl(request.getParameter("rcust_id"), "");            //판매처 id
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "");                    //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "asc");                //오름(asc), 내림(desc) 차순
		
		/*
		 * parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("gs_empCode", gs_empCode);
        paramMap.put("fr_date", fr_date);
        paramMap.put("to_date", to_date);
        paramMap.put("rcust_id", rcust_id);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
		
		/*
		 * 페이징 list
		 */
        List<ItemVO> itemList = orderService.getMasterGridList(paramMap);
			
		/*
		 *  excel다운용
		 */
        @SuppressWarnings("rawtypes")
        List<Map> excelMap = new ArrayList<Map>();
		
		/*
		 *  excel다운용 엑셀에 출력 할 항목들의 vo를 map에 담아줍니다.
		 *  밑의 header의 들어가는 컬럼과 같은 순서로 데이터를 셋팅해줘야 함.
		 */
        for (int i = 0; i < itemList.size(); i++) {
            Map<String, String> mapA1 = new HashMap<String, String>();        // excel row data

            ItemVO itemVO = new ItemVO();                                    // 조회한 데이터의 row data
            itemVO = itemList.get(i);

            mapA1.put("1", StringUtil.nvl(itemVO.getYmd()));
            mapA1.put("2", StringUtil.nvl(itemVO.getRcust_id()));
            mapA1.put("3", StringUtil.nvl(itemVO.getRcust_nm()));
            mapA1.put("4", StringUtil.nvl(itemVO.getGumae_no()));
            mapA1.put("5", StringUtil.nvl(itemVO.getStatus()));
            mapA1.put("6", StringUtil.nvl(itemVO.getGumae_gb()));
            mapA1.put("7", StringUtil.nvl(itemVO.getBigo()));

            excelMap.add(mapA1);
        }
		/*
		 * excel의 header 정보 및 mapping 정보 셋팅
		 */
        String[] header = {"주문요청일", "판매처", "판매처명", "접수번호", "주문상태", "주문종류", "요청사항"}; // excel다운용 엑셀 헤더 값
        String[] content = {"1", "2", "3", "4", "5", "6", "7"}; // excel다운용 mapA1에 담은 mapA1 이름과 동일 해야 합니다.
		
		/*
		 *  실제로 excel 파일을 생성해서 다운로드 받을 수 있게 하는 공통 모듈 호출
		 */
        ExcelDownManager.ExcelDown("주문목록", header, content, excelMap, response); // excel다운용
    }

    /**
     * <pre>
     * 1. 개요     : 온라인발주 주문삭제 화면
     * 2. 처리내용 : 온라인발주 주문삭제 jsp 노출
     * </pre>
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     * @Method Name : orderDeleteForm
     */
    @RequestMapping("/order/orderDeleteForm.do")
    public ModelAndView orderDeleteForm(HttpServletRequest request) {
		
		/*
		 * 객체 생성
		 */
        ModelAndView mav = new ModelAndView("order/orderDeleteForm");
        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                //cust_id

		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("gs_empCode", gs_empCode);
		
		/*
		 * 온라인발주 주문 삭제 화면에서 기본값을 조회해서 뷰단으로 전달
		 */
        mav.addObject("orderInit", orderService.getOrderUpInit(paramMap));

        return mav;
    }


    /**
     * <pre>
     * 1. 개요     : 주문삭제 ajax
     * 2. 처리내용 : parameter로 넘겨받은 값을 db에 삭제한다.
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : deleteOrderAjax
     */
    @RequestMapping("/order/deleteOrderAjax.do")
    public void deleteOrderAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/*
		 * 객체생성
		 */
        ItemVO retrunVO = new ItemVO();
        Map<String, Object> paramMap = new HashMap<String, Object>();
		
		/*
		 * parameter
		 */
        String ldt_req_date = StringUtil.nvl(request.getParameter("ldt_req_date"), "");        //주문요청일
        String ls_gumae_no = StringUtil.nvl(request.getParameter("gumae_no"), "");            //구매 번호
        String masterDelYn = StringUtil.nvl(request.getParameter("masterDelYn"), "");        //주문 마스터 삭제 여부

        String[] ld_item_id = request.getParameterValues("item_id");                        //제품코드
        String[] input_seq = request.getParameterValues("input_seq");                        //입력 순서
        String[] qty = request.getParameterValues("qty");                                    //수량
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("ldt_req_date", ldt_req_date);
        paramMap.put("ls_gumae_no", ls_gumae_no);
        paramMap.put("masterDelYn", masterDelYn);
		
		/*
		 * 주문 detail
		 */
        paramMap.put("ld_item_id", ld_item_id);
        paramMap.put("input_seq", input_seq);
        paramMap.put("qty", qty);
		
		/*
		 * 주문 삭제를 진행하고 결과를 json으로 리턴
		 */
        boolean result = false;            //주문 삭제 성공 여부

        try {
            result = orderService.deleteOrder(paramMap);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
        }

        if (result) {
            retrunVO.setResult("Y");        //Y이면 정상 delete
        } else {
            retrunVO.setResult("N");        //N이면 delete error
        }

        MarshallerUtil.marshalling("json", response, retrunVO);    //VO형태를 json형태로 마샬

    }


    /**
     * <pre>
     * 1. 개요     : 온라인발주 주문검색 화면
     * 2. 처리내용 : 온라인발주 주문검색 jsp 노출
     * </pre>
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     * @Method Name : orderList
     */
    @RequestMapping("/order/orderList.do")
    public ModelAndView orderList(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("order/orderList");
        return mav;
    }


    /**
     * <pre>
     * 1. 개요     : 주문 Master status grid List
     * 2. 처리내용 : 주문 Master status 목록을 가져온다.
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : orderGridList
     */
    @RequestMapping("/order/orderGridList.do")
    public void orderGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/*
		 * 객체생성
		 */
        Map<String, Object> paramMap = new HashMap<String, Object>();
        ItemJsonVO returnVO = new ItemJsonVO();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                    //cust_id
		
		/*
		 * parameter
		 */
        String fr_date = StringUtil.nvl(request.getParameter("fr_date"), "");                //조회 시작일
        String to_date = StringUtil.nvl(request.getParameter("to_date"), "");                //조회 종료일
        String[] receipt_gb = request.getParameterValues("receipt_gb");                        //주문 상태
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "gumae_no");                        //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "asc");                    //오름(asc), 내림(desc) 차순
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("gs_empCode", gs_empCode);
        paramMap.put("fr_date", fr_date);
        paramMap.put("to_date", to_date);
        paramMap.put("receipt_gb", receipt_gb);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
		
		/*
		 * 주문상태 아무것도 체크안했을 경우 grid검색 안함
		 */
        if (receipt_gb == null) {
            returnVO.setRecords(0);
        } else {
			
			/*
			 * 페이징 list
			 */
            List<ItemVO> itemVO = orderService.getOrderStatusGridList(paramMap);
			
			/*
			 * page의 리턴시킬 값
			 */
            returnVO.setRows(itemVO);
            returnVO.setRecords(itemVO.size());
        }
		
		/*
		 * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, returnVO);
    }


    /**
     * <pre>
     * 1. 개요     : 온라인 주문검색 grid 엑셀 다운로드
     * 2. 처리내용 : 온라인 주문검색 grid 엑셀 엑셀 파일로 생성
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception
     * @Method Name : orderGridListExcelDown
     */
    @RequestMapping("/order/orderGridListExcelDown.do")
    public void orderGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		 * 객체생성
		 */
        Map<String, Object> paramMap = new HashMap<String, Object>();
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());                    //cust_id

        //parameter
        String fr_date = StringUtil.nvl(request.getParameter("fr_date"), "");                //조회 시작일
        String to_date = StringUtil.nvl(request.getParameter("to_date"), "");                //조회 종료일
        String[] receipt_gb = request.getParameterValues("receipt_gb");                        //주문 상태
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "");                        //sort할 컬럼명
        String sord = StringUtil.nvl(request.getParameter("sord"), "asc");                    //오름(asc), 내림(desc) 차순

        if (receipt_gb == null) {
            receipt_gb = new String[]{"1", "2", "3"};
        }
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("gs_empCode", gs_empCode);
        paramMap.put("fr_date", fr_date);
        paramMap.put("to_date", to_date);
        paramMap.put("receipt_gb", receipt_gb);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);

		/*
		 * 페이징 list
		 */
        List<ItemVO> itemList = orderService.getOrderStatusGridList(paramMap);
			
		/*
		 *  excel다운용
		 */
        @SuppressWarnings("rawtypes")
        List<Map> excelMap = new ArrayList<Map>();
		
		/*
		 *  excel다운용 엑셀에 출력 할 항목들의 vo를 map에 담아줍니다.
		 *  밑의 header의 들어가는 컬럼과 같은 순서로 데이터를 셋팅해줘야 함.
		 */
        for (int i = 0; i < itemList.size(); i++) {
            Map<String, String> mapA1 = new HashMap<String, String>();    // excel row data

            ItemVO itemVO = new ItemVO();                                // 조회한 데이터의 row data
            itemVO = itemList.get(i);


            mapA1.put("1", StringUtil.nvl(itemVO.getRcust_id()));
            mapA1.put("2", StringUtil.nvl(itemVO.getRcust_nm()));
            mapA1.put("3", StringUtil.nvl(itemVO.getGumae_no()));
            mapA1.put("4", StringUtil.nvl(itemVO.getYmd()));
            mapA1.put("5", StringUtil.nvl(itemVO.getStatus()));
            mapA1.put("6", StringUtil.nvl(itemVO.getGumae_gb()));
            mapA1.put("7", StringUtil.nvl(itemVO.getApp_ymd()));
            mapA1.put("8", StringUtil.nvl(itemVO.getApp_no()));
            mapA1.put("9", StringUtil.nvl(itemVO.getBigo()));

            excelMap.add(mapA1);
        }
		
		/*
		 * excel의 header 정보 및 mapping 정보 셋팅
		 */
        String[] header = {"판매처", "판매처명", "접수번호", "주문요청일", "주문상태", "주문종류", "주문승인일", "승인번호", "비고"}; // excel다운용 엑셀 헤더 값
        String[] content = {"1", "2", "3", "4", "5", "6", "7", "8", "9"}; // excel다운용 mapA1에 담은 mapA1 이름과 동일 해야 합니다.
		
		/*
		 *  실제로 excel 파일을 생성해서 다운로드 받을 수 있게 하는 공통 모듈 호출
		 */
        ExcelDownManager.ExcelDown("주문검색", header, content, excelMap, response); // excel다운용
    }

}
