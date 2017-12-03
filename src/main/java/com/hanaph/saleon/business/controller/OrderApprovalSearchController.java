/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.business.controller;

import com.hanaph.saleon.business.service.BusinessService;
import com.hanaph.saleon.business.service.OrderApprovalSearchService;
import com.hanaph.saleon.business.vo.BusinessVO;
import com.hanaph.saleon.business.vo.OrderApprovalSearchJsonVO;
import com.hanaph.saleon.business.vo.OrderApprovalSearchVO;
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
 * Class Name : OrderApprovalSearchController.java
 * 설명 : 주문/승인 조회 Controller
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 26.      Beomjin
 * </pre>
 *
 * @author : Beomjin(@irush.co.kr)
 * @version :
 * @since : 2014. 12. 26.
 */
@Controller
public class OrderApprovalSearchController {

    @Autowired
    private BusinessService businessService;

    @Autowired
    private OrderApprovalSearchService orderApprovalSearchService;

    /**
     * <pre>
     * 1. 개요     : 주문/승인 조회
     * 2. 처리내용 : 주문/승인 조회 페이지를 반환한다.
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : orderApprovaSearch
     */
    @RequestMapping("/business/orderApprovalSearch.do")
    public ModelAndView orderApprovalSearch(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/orderApprovalSearch");
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
     * 1. 개요     : 주문/승인 내역 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 주문/승인 내역 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : orderApprovalSearchGridList
     */
    @RequestMapping("/business/orderApprovalSearchGridList.do")
    public void orderApprovalSearchGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();

		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션 생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());

		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String adt_fr_date = StringUtil.nvl(request.getParameter("adt_fr_date").replace("-", "")); // 조회기간 fr
        String adt_to_date = StringUtil.nvl(request.getParameter("adt_to_date").replace("-", "")); // 조회기간 to
        String selectType = StringUtil.nvl(request.getParameter("selectType")); // 조회타입
        String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
        String as_wiban_order_req_yn = StringUtil.nvl(request.getParameter("as_wiban_order_req_yn"), "%"); // 주문위반
        String as_wiban_order_conf_yn = StringUtil.nvl(request.getParameter("as_wiban_order_conf_yn"), "%"); // 팀장반려
        String as_slip_gb = StringUtil.nvl(request.getParameter("as_slip_gb")); // 주문구분
        String as_limit_yn = StringUtil.nvl(request.getParameter("as_limit_yn"), "A"); // 여신규정
        String as_receipt_gb = StringUtil.nvl(request.getParameter("as_receipt_gb"), "0"); // 주문상태
        String as_receipt_gb2 = StringUtil.nvl(request.getParameter("as_receipt_gb2"), "0"); // 승인구분
        String as_dept_cd = StringUtil.nvl(request.getParameter("as_dept_cd")); // 부서코드
        String as_pda_auth = StringUtil.nvl(request.getParameter("as_pda_auth")); // pda 권한
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "order".equals(selectType) ? "ymd, req_time" : "app_time"); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순

		/*주문상태 전체 조회에 따른 검색조건 변경*/
        if ("0".equals(as_receipt_gb)) {
            as_receipt_gb = "'1','2','3'";
        } else {
            as_receipt_gb = "'" + as_receipt_gb + "'";
        }

		/*승인구분 전체 조회에 따른 검색조건 변경*/
        if ("0".equals(as_receipt_gb2)) {
            as_receipt_gb2 = "'2','3'";
        } else {
            as_receipt_gb2 = "'" + as_receipt_gb2 + "'";
        }

		/*여신규정 전체 조회에 따른 검색조건 변경*/
        if ("A".equals(as_limit_yn)) {
            as_limit_yn = "'Y','N','E'";
        } else {
            as_limit_yn = "'" + as_limit_yn + "'";
        }

		/*parameter를 map에 setting*/
        paramMap.put("as_emp_cd", as_emp_cd); // 사원코드
        paramMap.put("adt_fr_date", adt_fr_date); // 조회기간 fr
        paramMap.put("adt_to_date", adt_to_date); // 조회기간 to
        paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
        paramMap.put("selectType", selectType); // 조회타입
        paramMap.put("as_wiban_order_req_yn", as_wiban_order_req_yn); // 주문위반
        paramMap.put("as_wiban_order_conf_yn", as_wiban_order_conf_yn); // 팀장반려
        paramMap.put("as_slip_gb", as_slip_gb); // 주문구분
        paramMap.put("as_limit_yn", as_limit_yn); // 여신규정
        paramMap.put("as_receipt_gb", as_receipt_gb); // 주문상태
        paramMap.put("as_receipt_gb2", as_receipt_gb2); // 승인구분
        paramMap.put("as_dept_cd", as_dept_cd); // 부서코드
        paramMap.put("as_pda_auth", as_pda_auth); // pda 권한
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*주문/승인 목록*/
        List<OrderApprovalSearchVO> orderApprovaSearchList = orderApprovalSearchService.getOrderApprovalSearchGridList(paramMap);
		
		/*주문/승인 목록 총 수*/
        OrderApprovalSearchVO totalCountInfo = orderApprovalSearchService.getOrderApprovalSearchGridTotalCount(paramMap);
		
		/*paging 연산*/
        int records = totalCountInfo.getTotal_cnt();
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        OrderApprovalSearchJsonVO orderApprovalSearchJsonVO = new OrderApprovalSearchJsonVO();

        orderApprovalSearchJsonVO.setTotal(total);        //page 수
        orderApprovalSearchJsonVO.setPage(page);            //현재 page
        orderApprovalSearchJsonVO.setRecords(records); // 전체 수
        orderApprovalSearchJsonVO.setRows(orderApprovaSearchList); // list
        orderApprovalSearchJsonVO.setTotalCountInfo(totalCountInfo); // 합계정보

        MarshallerUtil.marshalling("json", response, orderApprovalSearchJsonVO);

    }

    /**
     * <pre>
     * 1. 개요     : 주문 내역 상세 목록
     * 2. 처리내용 : 선택한 주문 내역의 상세 목록을 json형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : orderApprovalDetailGridList
     */
    @RequestMapping("/business/orderApprovalDetailGridList.do")
    public void orderApprovalDetailGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String ymd = StringUtil.nvl(request.getParameter("ymd").replace("-", "")); // 주문일/승인일
        String gumae_no = StringUtil.nvl(request.getParameter("gumae_no").replace("-", "")); // 구매번호
        String selectType = StringUtil.nvl(request.getParameter("selectType")); // 조회타입
        String sidx = StringUtil.nvl(request.getParameter("sidx"), ""); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순
		
		/*parameter를 map에 setting*/
        paramMap.put("ymd", ymd); // 주문일/승인일
        paramMap.put("gumae_no", gumae_no); // 구매번호
        paramMap.put("selectType", selectType); // 조회타입
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*주문 내역 상세 목록*/
        List<OrderApprovalSearchVO> orderApprovaSearchDetailList = orderApprovalSearchService.getOrderApprovalDetailGridList(paramMap);
		
		/*주문 내역 상세 목록 총 수*/
        OrderApprovalSearchVO totalCountInfo = orderApprovalSearchService.getOrderApprovalDetailGridTotalCount(paramMap);
		
		/*paging 연산*/
        int records = totalCountInfo.getTotal_cnt();
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        OrderApprovalSearchJsonVO orderApprovalSearchJsonVO = new OrderApprovalSearchJsonVO();

        orderApprovalSearchJsonVO.setTotal(total);        //page 수
        orderApprovalSearchJsonVO.setPage(page);            //현재 page
        orderApprovalSearchJsonVO.setRecords(records); // 전체 수
        orderApprovalSearchJsonVO.setRows(orderApprovaSearchDetailList); // list
        orderApprovalSearchJsonVO.setTotalCountInfo(totalCountInfo); // 합계 정보

        MarshallerUtil.marshalling("json", response, orderApprovalSearchJsonVO);

    }

    /**
     * <pre>
     * 1. 개요     : 주문/승인 excel 다운로드
     * 2. 처리내용 : 검색조건에 따른 주문/승인 목록을 excel 파일로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     * @Method Name : orderApprovalSearchGridListExcelDown
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/business/orderApprovalSearchGridListExcelDown.do")
    public void orderApprovalSearchGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
		
		/*parameter*/
        String adt_fr_date = StringUtil.nvl(request.getParameter("adt_fr_date").replace("-", "")); // 조회기간 fr
        String adt_to_date = StringUtil.nvl(request.getParameter("adt_to_date").replace("-", "")); // 조회기간 to
        String selectType = StringUtil.nvl(request.getParameter("selectType")); // 조회타입
        String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
        String as_wiban_order_req_yn = StringUtil.nvl(request.getParameter("as_wiban_order_req_yn"), "%"); // 주문위반
        String as_wiban_order_conf_yn = StringUtil.nvl(request.getParameter("as_wiban_order_conf_yn"), "%"); // 팀장반려
        String as_slip_gb = StringUtil.nvl(request.getParameter("as_slip_gb")); // 주문구분
        String as_limit_yn = StringUtil.nvl(request.getParameter("as_limit_yn"), "A"); // 여신규정
        String as_receipt_gb = StringUtil.nvl(request.getParameter("as_receipt_gb"), "0"); // 주문상태
        String as_receipt_gb2 = StringUtil.nvl(request.getParameter("as_receipt_gb2"), "0"); // 승인구분
        String as_dept_cd = StringUtil.nvl(request.getParameter("as_dept_cd")); // 부서코드
        String as_pda_auth = StringUtil.nvl(request.getParameter("as_pda_auth")); // pda 권한
		
		/*주문상태 전체 조회에 따른 검색조건 변경*/
        if ("0".equals(as_receipt_gb)) {
            as_receipt_gb = "'1','2','3'";
        } else {
            as_receipt_gb = "'" + as_receipt_gb + "'";
        }
		
		/*승인구분 전체 조회에 따른 검색조건 변경*/
        if ("0".equals(as_receipt_gb2)) {
            as_receipt_gb2 = "'2','3'";
        } else {
            as_receipt_gb2 = "'" + as_receipt_gb2 + "'";
        }
		
		/*여신규정 전체 조회에 따른 검색조건 변경*/
        if ("A".equals(as_limit_yn)) {
            as_limit_yn = "'Y','N','E'";
        } else {
            as_limit_yn = "'" + as_limit_yn + "'";
        }
		
		/*parameter를 map에 setting*/
        paramMap.put("as_emp_cd", as_emp_cd); // 사원코드
        paramMap.put("adt_fr_date", adt_fr_date); // 조회기간 fr
        paramMap.put("adt_to_date", adt_to_date); // 조회기간 to
        paramMap.put("as_cust_id", as_cust_id); // 조회타입
        paramMap.put("selectType", selectType); // 거래처 코드
        paramMap.put("as_wiban_order_req_yn", as_wiban_order_req_yn); // 주문위반
        paramMap.put("as_wiban_order_conf_yn", as_wiban_order_conf_yn); // 팀장반려
        paramMap.put("as_slip_gb", as_slip_gb); // 주문구분
        paramMap.put("as_limit_yn", as_limit_yn); // 여신규정
        paramMap.put("as_receipt_gb", as_receipt_gb); // 주문상태
        paramMap.put("as_receipt_gb2", as_receipt_gb2); // 승인구분
        paramMap.put("as_dept_cd", as_dept_cd); // 부서코드
        paramMap.put("as_pda_auth", as_pda_auth); // pda 권한
        paramMap.put("sidx", "order".equals(selectType) ? "ymd, req_time" : "app_time"); // 정렬
        paramMap.put("sord", ""); // 정렬순
        paramMap.put("page", null);
        paramMap.put("perPageRow", null);
		
		/*주문/승인 목록*/
        List<OrderApprovalSearchVO> orderApprovaSearchList = orderApprovalSearchService.getOrderApprovalSearchGridList(paramMap);
		
		/*주문/승인 목록을 map에 담는다.*/
        List<Map> excelMap = new ArrayList<Map>();
        String title = "";
        String[] header = null;
        String[] content = null;

        for (int i = 0; i < orderApprovaSearchList.size(); i++) {

            Map<String, String> mapA1 = new HashMap<String, String>();

            OrderApprovalSearchVO orderApprovalSearchVO = new OrderApprovalSearchVO();
            orderApprovalSearchVO = orderApprovaSearchList.get(i);
			
			/*조회타입에 따라 excel content 분기*/
            if ("order".equals(selectType)) {

                mapA1.put("1", orderApprovalSearchVO.getYmd());
                mapA1.put("2", orderApprovalSearchVO.getCust_id());
                mapA1.put("3", orderApprovalSearchVO.getCust_nm());
                mapA1.put("4", orderApprovalSearchVO.getRcust_id());
                mapA1.put("5", orderApprovalSearchVO.getRcust_nm());
                mapA1.put("6", orderApprovalSearchVO.getReq_time());
                mapA1.put("7", orderApprovalSearchVO.getGumae_no());
                mapA1.put("8", orderApprovalSearchVO.getWiban_order_req_yn());
                mapA1.put("9", orderApprovalSearchVO.getWiban_order_conf_yn());
                mapA1.put("10", orderApprovalSearchVO.getStatus());
                mapA1.put("11", orderApprovalSearchVO.getGumae_gb());
                mapA1.put("12", orderApprovalSearchVO.getReturn_desc());

            } else if ("approval".equals(selectType)) {

                mapA1.put("1", orderApprovalSearchVO.getApp_ymd());
                mapA1.put("2", orderApprovalSearchVO.getApp_time());
                mapA1.put("3", orderApprovalSearchVO.getCust_id());
                mapA1.put("4", orderApprovalSearchVO.getCust_nm());
                mapA1.put("5", orderApprovalSearchVO.getRcust_id());
                mapA1.put("6", orderApprovalSearchVO.getRcust_nm());
                mapA1.put("7", orderApprovalSearchVO.getApp_no());
                mapA1.put("8", orderApprovalSearchVO.getStatus());
                mapA1.put("9", orderApprovalSearchVO.getAsawon_id());
                mapA1.put("10", orderApprovalSearchVO.getAsawon_nm());
                mapA1.put("11", orderApprovalSearchVO.getYmd());
                mapA1.put("12", orderApprovalSearchVO.getGumae_no());
                mapA1.put("13", orderApprovalSearchVO.getGumae_gb());
                mapA1.put("14", orderApprovalSearchVO.getBigo());
                mapA1.put("15", orderApprovalSearchVO.getReturn_desc());

            }

            excelMap.add(mapA1);
        }
		
		/*조회타입에 따라 excel 내용 분기*/
        if ("order".equals(selectType)) {
            title = "주문 조회";
            header = new String[]{"주문요청일", "거래처", "거래처명", "판매처", "판매처명", "주문시간", "접수번호", "위반주문", "팀장승인", "주문상태", "주문종류", "비고"};
            content = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        } else if ("approval".equals(selectType)) {
            title = "승인 조회";
            header = new String[]{"주문승인일", "승인일시", "거래처", "거래처명", "판매처", "판매처명", "승인번호", "승인구분", "승인사번", "승인사원명", "주문요청일", "접수번호", "주문종류", "요청사항", "승인/반려사유"};
            content = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15"};
        }

        ExcelDownManager.ExcelDown(title, header, content, excelMap, response);

    }
}
