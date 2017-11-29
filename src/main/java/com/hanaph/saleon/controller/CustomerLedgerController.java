package com.hanaph.saleon.controller;

import com.hanaph.saleon.business.service.BusinessService;
import com.hanaph.saleon.business.service.CustomerLedgerService;
import com.hanaph.saleon.business.vo.BusinessVO;
import com.hanaph.saleon.business.vo.CustomerLedgerJsonVO;
import com.hanaph.saleon.business.vo.CustomerLedgerVO;
import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;
import com.hanaph.saleon.order.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * <pre>
 * Class Name : CustomerLedgerController.java
 * 설명 : 거래처원장조회 Controller
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 14.      Beomjin
 * </pre>
 *
 * @author : Beomjin(@irush.co.kr)
 * @version :
 * @since : 2014. 11. 14.
 */
@Controller
public class CustomerLedgerController {

    @Autowired
    private CustomerLedgerService customerLedgerService;

    @Autowired
    private BusinessService businessService;

    @Autowired
    private CompanyService companyService;

    /**
     * <pre>
     * 1. 개요     : 거래처원장조회 메인
     * 2. 처리내용 : 거래처원장조회 페이지를 반환한다.
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : customerLedger
     */
    @RequestMapping("/business/customerLedgerList.do")
    public ModelAndView customerLedger(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/customerLedgerList");
        Map<String, String> paramMap = new HashMap<String, String>();

		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션 생성
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
     * 1. 개요     : 거래처/간납처 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 거래처/간납처 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : customerGridList
     */
    @RequestMapping("/business/customerGridList.do")
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
        String selectType = StringUtil.nvl(request.getParameter("selectType"), "customer"); // 조회타입
        String ad_fr_date = StringUtil.nvl(request.getParameter("ad_fr_date")); // 조회기간 fr
        String ad_to_date = StringUtil.nvl(request.getParameter("ad_to_date")); // 조회기간 to
        String as_fr_cust = StringUtil.nvl(request.getParameter("as_fr_cust"), "");  // 거래처 코드
        String as_dept_cd = StringUtil.nvl(request.getParameter("as_dept_cd")); // 부서코드
        String as_pda_auth = StringUtil.nvl(request.getParameter("as_pda_auth")); // pda 권한
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "cust_id"); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순
		
		/*parameter를 map에 setting*/
        paramMap.put("as_emp_cd", as_emp_cd);
        paramMap.put("selectType", selectType);
        paramMap.put("ad_fr_date", ad_fr_date);
        paramMap.put("ad_to_date", ad_to_date);
        paramMap.put("as_fr_cust", as_fr_cust);
        paramMap.put("as_dept_cd", as_dept_cd);
        paramMap.put("as_pda_auth", as_pda_auth);
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
        paramMap.put("as_assgnCode", as_assgnCode); //직책코드
		
		/*거래처/간납처 목록*/
        List<CustomerLedgerVO> customerList = customerLedgerService.getCustomerGridList(paramMap);
		
		/*거래처/간납처 목록 총 수*/
        CustomerLedgerVO totalCountInfo = customerLedgerService.getCustomerGridTotalCount(paramMap);
		
		/*paging 연산*/
        int records = totalCountInfo.getTotal_cnt();
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        CustomerLedgerJsonVO customerLedgerJsonVO = new CustomerLedgerJsonVO();

        customerLedgerJsonVO.setTotal(total);        //page 수
        customerLedgerJsonVO.setPage(page);            //현재 page
        customerLedgerJsonVO.setRecords(records); // 전체 수
        customerLedgerJsonVO.setRows(customerList); // list
        customerLedgerJsonVO.setTotalCountInfo(totalCountInfo); // 합계정보

        MarshallerUtil.marshalling("json", response, customerLedgerJsonVO);
    }

    /**
     * <pre>
     * 1. 개요     : 거래처/간납처 원장 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 거래처/간납처 원장 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : customerLedgerGridList
     */
    @RequestMapping("/business/customerLedgerGridList.do")
    public void customerLedgerGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String selectType = StringUtil.nvl(request.getParameter("selectType"), "customer"); // 조회타입
        String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
        String ad_fr_date = StringUtil.nvl(request.getParameter("ad_fr_date")); // 조회기간 fr
        String ad_to_date = StringUtil.nvl(request.getParameter("ad_to_date")); // 조회기간 to
        String sidx = StringUtil.nvl(request.getParameter("sidx"), ""); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순
		
		/*parameter를 map에 setting*/
        paramMap.put("selectType", selectType); // 조회타입
        paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
        paramMap.put("ad_fr_date", ad_fr_date); // 조회기간 fr
        paramMap.put("ad_to_date", ad_to_date); // 조회기간 to
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*거래처/간납처 원장 목록*/
        List<CustomerLedgerVO> customerLedgerList = customerLedgerService.getCustomerLedgerGridList(paramMap);
		
		/*거래처/간납처 원장 목록 총 수*/
        CustomerLedgerVO totalCountInfo = customerLedgerService.getCustomerLedgerGridTotalCount(paramMap);
		
		/*paging 연산*/
        int records = totalCountInfo.getTotal_cnt();
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        CustomerLedgerJsonVO customerLedgerJsonVO = new CustomerLedgerJsonVO();

        customerLedgerJsonVO.setTotal(total);        //page 수
        customerLedgerJsonVO.setPage(page);            //현재 page
        customerLedgerJsonVO.setRecords(records);
        customerLedgerJsonVO.setRows(customerLedgerList);
        customerLedgerJsonVO.setTotalCountInfo(totalCountInfo);

        MarshallerUtil.marshalling("json", response, customerLedgerJsonVO);
    }

    /**
     * <pre>
     * 1. 개요     : 거래처/간납처 원장 excel 다운로드
     * 2. 처리내용 : 검색조건에 따른 거래처/간납처 원장 목록을 excel 파일로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     * @Method Name : ledgerGridListExcelDown
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/business/ledgerGridListExcelDown.do")
    public void ledgerGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        String selectType = StringUtil.nvl(request.getParameter("selectType"), "customer"); // 조회타입
        String as_cust_id_customer = StringUtil.nvl(request.getParameter("as_cust_id_customer")); // 거래처 코드
        String as_cust_id_indirect = StringUtil.nvl(request.getParameter("as_cust_id_indirect")); // 간납처 코드
        String ad_fr_date = StringUtil.nvl(request.getParameter("ad_fr_date")); // 조회기간 fr
        String ad_to_date = StringUtil.nvl(request.getParameter("ad_to_date")); // 조회기간 to
        String as_cust_id = ""; // 조회할 거래처 코드
		
		/*조회 타입에 따라 조회할 거래처 코드를 셋팅*/
        if ("customer".equals(selectType)) {
            as_cust_id = as_cust_id_customer;
        } else if ("indirect".equals(selectType)) {
            as_cust_id = as_cust_id_indirect;
        }
		
		/*parameter를 map에 setting*/
        paramMap.put("selectType", selectType); // 조회타입
        paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
        paramMap.put("ad_fr_date", ad_fr_date); // 조회기간 fr
        paramMap.put("ad_to_date", ad_to_date); // 조회기간 to
        paramMap.put("sidx", "");
        paramMap.put("sord", "");
        paramMap.put("page", null);
        paramMap.put("perPageRow", null);
		
		/*거래처/간납처 원장 목록*/
        List<CustomerLedgerVO> customerLedgerList = customerLedgerService.getCustomerLedgerGridList(paramMap);
		
		/*거래처/간납처 원장 목록을 map에 담는다.*/
        List<Map> excelMap = new ArrayList<Map>();
        String[] header = null;

        for (int i = 0; i < customerLedgerList.size(); i++) {

            Map<String, String> mapA1 = new HashMap<String, String>();

            CustomerLedgerVO customerLedgerVO = new CustomerLedgerVO();
            customerLedgerVO = customerLedgerList.get(i);

            mapA1.put("1", customerLedgerVO.getYmd());
            mapA1.put("2", customerLedgerVO.getRcust_nm());
            mapA1.put("3", customerLedgerVO.getItem_nm());
            mapA1.put("4", customerLedgerVO.getStandard());
            mapA1.put("5", customerLedgerVO.getQty());
            mapA1.put("6", customerLedgerVO.getDanga());
            mapA1.put("7", customerLedgerVO.getAmt());
            mapA1.put("8", customerLedgerVO.getVat());
            mapA1.put("9", customerLedgerVO.getTot());
            mapA1.put("10", customerLedgerVO.getSukum());
            mapA1.put("11", customerLedgerVO.getRem_amt());
            mapA1.put("12", customerLedgerVO.getDc_amt());
            mapA1.put("13", customerLedgerVO.getBigo());

            excelMap.add(mapA1);
        }
		
		/*조회타입에 따라  excel header 분기*/
        if ("customer".equals(selectType)) {
            header = new String[]{"일자", "납품처", "품목", "단위", "수량", "단가", "공급가액", "세액", "합계금액", "수금", "잔액", "매출할인", "비고"};
        } else if ("indirect".equals(selectType)) {
            header = new String[]{"일자", "거래처", "품목", "단위", "수량", "단가", "공급가액", "세액", "합계금액", "수금", "잔액", "매출할인", "비고"};
        }

        String[] content = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13"}; // excel content

        ExcelDownManager.ExcelDown("거래처원장조회", header, content, excelMap, response);
    }

    /**
     * <pre>
     * 1. 개요     : 거래처원장 인쇄 팝업
     * 2. 처리내용 : 거래처원장 인쇄 팝업을 가져온다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @throws ParseException
     * @Method Name : ledgerListPopup
     */
    @RequestMapping("/business/ledgerListPrintPopup.do")
    public ModelAndView ledgerListPrintPopup(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {

        ModelAndView mav = new ModelAndView("business/common/ledgerListPrintPopup");
        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        String selectType = StringUtil.nvl(request.getParameter("selectType"), "customer"); // 조회타입
        String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
        String ad_fr_date = StringUtil.nvl(request.getParameter("ad_fr_date")); // 조회기간 fr
        String ad_to_date = StringUtil.nvl(request.getParameter("ad_to_date")); // 조회기간 to
		
		/*parameter를 map에 setting*/
        paramMap.put("selectType", selectType); // 조회타입
        paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
        paramMap.put("ad_fr_date", ad_fr_date); // 조회기간 fr
        paramMap.put("ad_to_date", ad_to_date); // 조회기간 to
        paramMap.put("sidx", "");
        paramMap.put("sord", "");
        paramMap.put("page", null);
        paramMap.put("perPageRow", null);
		
		/*거래처/간납처 원장 목록*/
        List<CustomerLedgerVO> ledgerList = customerLedgerService.getCustomerLedgerGridList(paramMap);
		
		/*거래처/간납처 원장 목록 총 수*/
        CustomerLedgerVO sumLedger = customerLedgerService.getCustomerLedgerGridTotalCount(paramMap);
		
		/*출력일자*/
        String today = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        mav.addObject("today", today);
        mav.addObject("ad_fr_date", ad_fr_date);
        mav.addObject("ad_to_date", ad_to_date);
        mav.addObject("ledgerList", ledgerList);
        mav.addObject("sumLedger", sumLedger);

        return mav;
    }
}
