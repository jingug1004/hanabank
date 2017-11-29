package com.hanaph.saleon.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hanaph.saleon.business.service.BusinessService;
import com.hanaph.saleon.business.service.CustomerInfoService;
import com.hanaph.saleon.business.vo.BusinessVO;
import com.hanaph.saleon.business.vo.CustomerInfoJsonVO;
import com.hanaph.saleon.business.vo.CustomerInfoVO;
import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;

/**
 * <pre>
 * Class Name : CustomerInfoController.java
 * 설명 : 고객 등록 Controller
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 17.      Beomjin
 * </pre>
 *
 * @author : Beomjin(@irush.co.kr)
 * @version :
 * @since : 2014. 12. 17.
 */
@Controller
public class CustomerInfoController {

    private static final Logger logger = Logger.getLogger(CompanyCardMgmtContoller.class);

    @Autowired
    private CustomerInfoService customerInfoService;

    @Autowired
    private BusinessService businessService;

    /**
     * <pre>
     * 1. 개요     : 고객등록 메인
     * 2. 처리내용 : 고객등록 페이지를 반환한다.
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : customerInfoList
     */
    @RequestMapping("/business/customerInfoList.do")
    public ModelAndView customerInfoList(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/customerInfoList");
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
     * 1. 개요     : 거래처 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 거래처 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : customerInfoGridList
     */
    @RequestMapping("/business/customerInfoGridList.do")
    public void customerInfoGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션 생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
		
		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
        String as_use_yn = StringUtil.nvl(request.getParameter("as_use_yn"), "%"); // 거래 구분
        String as_dept_cd = StringUtil.nvl(request.getParameter("as_dept_cd")); // 부서 코드
        String as_pda_auth = StringUtil.nvl(request.getParameter("as_pda_auth")); // pda 권한
        String sidx = StringUtil.nvl(request.getParameter("sidx"), ""); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순
		
		/*parameter를 map에 setting*/
        paramMap.put("as_emp_cd", as_emp_cd); // 사원 코드
        paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
        paramMap.put("as_use_yn", as_use_yn); // 거래 구분
        paramMap.put("as_dept_cd", as_dept_cd); // 부서 코드
        paramMap.put("as_pda_auth", as_pda_auth); // pda 권한
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*거래처 목록*/
        List<CustomerInfoVO> customerInfoList = customerInfoService.getCustomerInfoGridList(paramMap);
		
		/*거래처 목록 총 수*/
        CustomerInfoVO totalCountInfo = customerInfoService.getCustomerInfoGridTotalCount(paramMap);
		
		/*paging 연산*/
        int records = totalCountInfo.getTotal_cnt();
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        CustomerInfoJsonVO customerInfoJsonVO = new CustomerInfoJsonVO();

        customerInfoJsonVO.setTotal(total);        //page 수
        customerInfoJsonVO.setPage(page);            //현재 page
        customerInfoJsonVO.setRecords(records); // 전체 수
        customerInfoJsonVO.setRows(customerInfoList); // list
        customerInfoJsonVO.setTotalCountInfo(totalCountInfo); // 합계정보

        MarshallerUtil.marshalling("json", response, customerInfoJsonVO);
    }

    /**
     * <pre>
     * 1. 개요     : 고객 jqgrid 목록
     * 2. 처리내용 : 거래처 코드 하위의 고객 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : clientGridList
     */
    @RequestMapping("/business/clientGridList.do")
    public void clientGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
        String sidx = StringUtil.nvl(request.getParameter("sidx"), ""); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순
		
		/*parameter를 map에 setting*/
        paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*고객 목록*/
        List<CustomerInfoVO> clientList = customerInfoService.getClientGridList(paramMap);
		
		/*고객 목록 총 수*/
        CustomerInfoVO totalCountInfo = customerInfoService.getClientGridTotalCount(paramMap);
		
		/*paging 연산*/
        int records = totalCountInfo.getTotal_cnt();
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        CustomerInfoJsonVO customerInfoJsonVO = new CustomerInfoJsonVO();

        customerInfoJsonVO.setTotal(total);        //page 수
        customerInfoJsonVO.setPage(page);            //현재 page
        customerInfoJsonVO.setRecords(records); // 전체 수
        customerInfoJsonVO.setRows(clientList); // list
        customerInfoJsonVO.setTotalCountInfo(totalCountInfo); // 합계정보

        MarshallerUtil.marshalling("json", response, customerInfoJsonVO);
    }

    /**
     * <pre>
     * 1. 개요     : 거래처 excel 목록
     * 2. 처리내용 : 검색조건에 따른 거래처 목록을 excel 파일로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws Exception
     * @Method Name : customerInfoGridListExcelDown
     */
    @SuppressWarnings("rawtypes")
    @RequestMapping("/business/customerInfoGridListExcelDown.do")
    public void customerInfoGridListExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션 생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String as_emp_cd = StringUtil.nvl(loginUserVO.getEmp_code());
		
		/*parameter*/
        String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id"));
        String as_use_yn = StringUtil.nvl(request.getParameter("as_use_yn"), "%");
        String as_dept_cd = StringUtil.nvl(request.getParameter("as_dept_cd"));
        String as_pda_auth = StringUtil.nvl(request.getParameter("as_pda_auth"));
		
		/*parameter를 map에 setting*/
        paramMap.put("as_emp_cd", as_emp_cd);
        paramMap.put("as_cust_id", as_cust_id);
        paramMap.put("as_use_yn", as_use_yn);
        paramMap.put("as_dept_cd", as_dept_cd);
        paramMap.put("as_pda_auth", as_pda_auth);
        paramMap.put("sidx", "");
        paramMap.put("sord", "");
        paramMap.put("page", null);
        paramMap.put("perPageRow", null);
		
		/*거래처 목록*/
        List<CustomerInfoVO> customerInfoList = customerInfoService.getCustomerInfoGridList(paramMap);
		
		/*판매현황 목록을 map에 담는다.*/
        List<Map> excelMap = new ArrayList<Map>();

        for (int i = 0; i < customerInfoList.size(); i++) {

            Map<String, String> mapA1 = new HashMap<String, String>();

            CustomerInfoVO customerInfoVO = new CustomerInfoVO();
            customerInfoVO = customerInfoList.get(i);

            mapA1.put("1", customerInfoVO.getCust_id());
            mapA1.put("2", customerInfoVO.getCust_nm());
            mapA1.put("3", customerInfoVO.getSawon_id());
            mapA1.put("4", customerInfoVO.getSawon_nm());

            excelMap.add(mapA1);
        }

        String[] header = {"거래처코드", "거래처명", "사원코드", "사원명"}; // excel header
        String[] content = {"1", "2", "3", "4"}; // excel content

        ExcelDownManager.ExcelDown("고객등록", header, content, excelMap, response);
    }

    /**
     * <pre>
     * 1. 개요     : 고객 정보 등록/수정
     * 2. 처리내용 : 고객 정보를 등록/수정 한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : procCustomerInfo
     */
    @RequestMapping("/business/procCustomerInfo.do")
    public void procCustomerInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        String cust_id = StringUtil.nvl(request.getParameter("cust_id")); // 거래처 코드
        String customer_id = StringUtil.nvl(request.getParameter("customer_id")); // 고객 코드
        String customer_nm = StringUtil.nvl(request.getParameter("customer_nm")); // 고객명
        String sex = StringUtil.nvl(request.getParameter("sex")); // 성별
        String birth_day = StringUtil.nvl(request.getParameter("birth_day").replace("-", "")); // 생일
        String act_birth_day = StringUtil.nvl(request.getParameter("act_birth_day").replace("-", "")); // 실제 생일
        String religion = StringUtil.nvl(request.getParameter("religion")); // 종교
        String marry_yn = StringUtil.nvl(request.getParameter("marry_yn")); // 결혼여부
        String marry_day = StringUtil.nvl(request.getParameter("marry_day").replace("-", "")); // 결혼기념일
        String child_kind = StringUtil.nvl(request.getParameter("child_kind")); // 자녀관계
        String disposition = StringUtil.nvl(request.getParameter("disposition")); // 성향
        String hobby = StringUtil.nvl(request.getParameter("hobby")); // 취미
        String highschool = StringUtil.nvl(request.getParameter("highschool")); // 출신고등학교
        String university = StringUtil.nvl(request.getParameter("university")); // 출신대학교
        String taboo_list = StringUtil.nvl(request.getParameter("taboo_list")); // 금기사항
        String tel = StringUtil.nvl(request.getParameter("tel")); // 전화번호
        String mobile = StringUtil.nvl(request.getParameter("mobile")); // 핸드폰 번호
        String fax = StringUtil.nvl(request.getParameter("fax")); // fax 번호
        String rank = StringUtil.nvl(request.getParameter("rank")); // 직위
        String lesson = StringUtil.nvl(request.getParameter("lesson")); // 전문과
        String hospital = StringUtil.nvl(request.getParameter("hospital")); // 수련병원
        String major = StringUtil.nvl(request.getParameter("major")); // 전공
        String foreign_study_yn = StringUtil.nvl(request.getParameter("foreign_study_yn")); // 해외연수
        String human_rel = StringUtil.nvl(request.getParameter("human_rel")); // 대인관계
        String car_no = StringUtil.nvl(request.getParameter("car_no")); // 차량번호
        String car_color = StringUtil.nvl(request.getParameter("car_color")); // 차량종류/색상
        String gita = StringUtil.nvl(request.getParameter("gita")); // 기타사항
        String main_buying = StringUtil.nvl(request.getParameter("main_buying")); // 주거래도매
        String email = StringUtil.nvl(request.getParameter("email")); // email
        String zip = StringUtil.nvl(request.getParameter("zip")); // 우편번호
        String address1 = StringUtil.nvl(request.getParameter("address1")); // 주소
        String address2 = StringUtil.nvl(request.getParameter("address2")); // 상세주소
        String saveType = StringUtil.nvl(request.getParameter("saveType")); // 저장종류
		
		/*parameter를 map에 setting*/
        paramMap.put("cust_id", cust_id); // 거래처 코드
        paramMap.put("customer_id", customer_id); // 고객 코드
        paramMap.put("customer_nm", customer_nm); // 고객명
        paramMap.put("sex", sex); // 성별
        paramMap.put("birth_day", birth_day); // 생일
        paramMap.put("act_birth_day", act_birth_day); // 실제 생일
        paramMap.put("religion", religion); // 종교
        paramMap.put("marry_yn", marry_yn); // 결혼여부
        paramMap.put("marry_day", marry_day); // 결혼기념일
        paramMap.put("child_kind", child_kind); // 자녀관계
        paramMap.put("disposition", disposition); // 성향
        paramMap.put("hobby", hobby); // 취미
        paramMap.put("highschool", highschool); // 출신고등학교
        paramMap.put("university", university); // 출신대학교
        paramMap.put("taboo_list", taboo_list); // 금기사항
        paramMap.put("tel", tel); // 전화번호
        paramMap.put("mobile", mobile); // 핸드폰 번호
        paramMap.put("fax", fax); // fax 번호
        paramMap.put("rank", rank); // 직위
        paramMap.put("lesson", lesson); // 전문과
        paramMap.put("hospital", hospital); // 수련병원
        paramMap.put("major", major); // 전공
        paramMap.put("foreign_study_yn", foreign_study_yn); // 해외연수
        paramMap.put("human_rel", human_rel); // 대인관계
        paramMap.put("car_no", car_no); // 차량번호
        paramMap.put("car_color", car_color); // 차량종류/색상
        paramMap.put("gita", gita); // 기타사항
        paramMap.put("main_buying", main_buying); // 주거래도매
        paramMap.put("email", email); // email
        paramMap.put("zip", zip); // 우편번호
        paramMap.put("address1", address1); // 주소
        paramMap.put("address2", address2); // 상세주소
        paramMap.put("saveType", saveType); // 저장종류
		
		/*고객 등록 / 수정 / 삭제*/
        int resultCount = customerInfoService.procCustomerInfo(paramMap);
		
		/*returnVO*/
        CustomerInfoJsonVO returnVO = new CustomerInfoJsonVO();

        returnVO.setResultCount(resultCount);

        MarshallerUtil.marshalling("json", response, returnVO);
    }

    /**
     * <pre>
     * 1. 개요     : 거래처 상세 정보 팝업
     * 2. 처리내용 : 거래처 상세 정보를 popup 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @return
     * @Method Name : customerDetail
     */
    @RequestMapping("/business/customerDetailPopup.do")
    public ModelAndView customerDetailPopup(HttpServletRequest request) {

        ModelAndView mav = new ModelAndView("business/common/customerDetailPopup");
        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        String cust_id = StringUtil.nvl(request.getParameter("cust_id")); // 거래처 코드
		
		/*parameter를 map에 setting*/
        paramMap.put("cust_id", cust_id); // 거래처 코드
		
		/*거래처 상세 정보*/
        CustomerInfoVO customerDetailVO = customerInfoService.getCustomerDetail(paramMap);

        mav.addObject("customerDetail", customerDetailVO);

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 거래처 상세정보(기타사항) jqgrid 목록
     * 2. 처리내용 : 거래처 상세정보(기타사항) 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : customerDetailPopupGridList
     */
    @RequestMapping("/business/customerDetailEtcGridList.do")
    public void customerDetailEtcGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String cust_id = StringUtil.nvl(request.getParameter("cust_id")); // 거래처 코드
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "cust_id, seq"); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순
		
		/*parameter를 map에 setting*/
        paramMap.put("cust_id", cust_id); // 거래처 코드
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*거래처 기타사항 목록*/
        List<CustomerInfoVO> customerDetailEtcList = customerInfoService.getCustomerDetailEtcGridList(paramMap);
		
		/*거래처 기타사항 목록 총 수*/
        CustomerInfoVO totalCountInfo = customerInfoService.getCustomerDetailEtcGridTotalCount(paramMap);
		
		/*paging 연산*/
        int records = totalCountInfo.getTotal_cnt();
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        CustomerInfoJsonVO customerInfoJsonVO = new CustomerInfoJsonVO();

        customerInfoJsonVO.setTotal(total);        //page 수
        customerInfoJsonVO.setPage(page);            //현재 page
        customerInfoJsonVO.setRecords(records); // 전체 수
        customerInfoJsonVO.setRows(customerDetailEtcList); // list
        customerInfoJsonVO.setTotalCountInfo(totalCountInfo); // 합계정보

        MarshallerUtil.marshalling("json", response, customerInfoJsonVO);
    }

    /**
     * <pre>
     * 1. 개요     : 거래처 상세 정보 수정 및 기타사항 등록/수정
     * 2. 처리내용 : 거래처 상세 정보 및 기타사항을 등록/수정한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : procCustomerDetail
     */
    @RequestMapping("/business/procCustomerDetail.do")
    public void procCustomerDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> detailParamMap = new HashMap<String, String>(); // 상세 수정 파라미터
        Map<String, Object> gitaParamMap = new HashMap<String, Object>(); // 기타사항 등록/수정 파라미터
		
		/*parameter*/
        String cust_id = StringUtil.nvl(request.getParameter("cust_id")); // 거래처 코드
        String tel = StringUtil.nvl(request.getParameter("tel")); // 전화번호
        String hp = StringUtil.nvl(request.getParameter("hp")); // 핸드폰 번호
        String open_date = StringUtil.nvl(request.getParameter("open_date").replace("-", "")); // 개업일
        String fax = StringUtil.nvl(request.getParameter("fax")); // fax 번호
        String room_cnt = StringUtil.nvl(request.getParameter("room_cnt")); // 병실 수
        String submit_date = StringUtil.nvl(request.getParameter("submit_date").replace("-", "")); // 결재일
        String bedno = StringUtil.nvl(request.getParameter("bedno")); // bed수
        String email = StringUtil.nvl(request.getParameter("email")); // email
        String[] insert_gita = request.getParameterValues("insert_gita"); // 기타사항 등록
        String[] update_gita = request.getParameterValues("update_gita"); // 기타사항 수정
        String[] update_seq = request.getParameterValues("update_seq"); // 기타사항 seq
		
		/*parameter를 map에 setting*/
        detailParamMap.put("cust_id", cust_id); // 거래처 코드
        detailParamMap.put("tel", tel); // 전화번호
        detailParamMap.put("hp", hp); // 핸드폰 번호
        detailParamMap.put("open_date", open_date); // 개업일
        detailParamMap.put("fax", fax); // fax 번호
        detailParamMap.put("room_cnt", room_cnt); // 병실 수
        detailParamMap.put("submit_date", submit_date); // 결재일
        detailParamMap.put("bedno", bedno); // bed수
        detailParamMap.put("email", email); // email

        gitaParamMap.put("cust_id", cust_id); // 거래처 코드
        gitaParamMap.put("insert_gita", insert_gita); // 기타사항 등록
        gitaParamMap.put("update_gita", update_gita); // 기타사항 수정
        gitaParamMap.put("update_seq", update_seq); // 기타사항 seq
		
		/*거래처 상세 수정*/
        int resultCount = customerInfoService.updateCustomerDetail(detailParamMap);
        String result = "N"; // 기타사항 등록/수정 결과

        try {
			/*기타사항 등록/수정*/
            result = customerInfoService.procCustomerDetailEtc(gitaParamMap);
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
		
		/*returnVO*/
        CustomerInfoJsonVO returnVO = new CustomerInfoJsonVO();

        returnVO.setResultCount(resultCount); // 상세 수정 결과
        returnVO.setMessage(result); // 기타사항 등록/수정 결과

        MarshallerUtil.marshalling("json", response, returnVO);
    }

    /**
     * <pre>
     * 1. 개요     : 거래처 기타정보 삭제
     * 2. 처리내용 : 거래처 기타정보를 삭제한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : deleteCustomerDetailEtc
     */
    @RequestMapping("/business/deleteCustomerDetailEtc.do")
    public void deleteCustomerDetailEtc(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        String cust_id = StringUtil.nvl(request.getParameter("cust_id")); // 거래처 코드
        String seq = StringUtil.nvl(request.getParameter("seq")); // 기타사항 seq
		
		/*parameter를 map에 setting*/
        paramMap.put("cust_id", cust_id); // 거래처 코드
        paramMap.put("seq", seq); // 기타사항 seq
		
		/*기타사항 삭제*/
        int resultCount = customerInfoService.deleteCustomerDetailEtc(paramMap);
		
		/*returnVO*/
        CustomerInfoJsonVO returnVO = new CustomerInfoJsonVO();

        returnVO.setResultCount(resultCount); // 기타사항 삭제 결과

        MarshallerUtil.marshalling("json", response, returnVO);
    }

    /**
     * <pre>
     * 1. 개요     : 고객 기타정보 jqgrid 목록
     * 2. 처리내용 : 검색조건에 따른 고객 기타정보 목록을 json 형태로 반환한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : instituteGridList
     */
    @RequestMapping("/business/clientDetailGridList.do")
    public void clientDetailGridList(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String as_cust_id = StringUtil.nvl(request.getParameter("as_cust_id")); // 거래처 코드
        String as_customer_id = StringUtil.nvl(request.getParameter("as_customer_id")); // 고객 코드
        String currentTab = StringUtil.nvl(request.getParameter("currentTab")); // 현재 탭
        String sidx = StringUtil.nvl(request.getParameter("sidx"), "seq"); // 정렬
        String sord = StringUtil.nvl(request.getParameter("sord"), ""); // 정렬순
		
		/*parameter를 map에 setting*/
        paramMap.put("as_cust_id", as_cust_id); // 거래처 코드
        paramMap.put("as_customer_id", as_customer_id); // 고객 코드
        paramMap.put("currentTab", currentTab); // 현재 탭
        paramMap.put("sidx", sidx); // 정렬
        paramMap.put("sord", sord); // 정렬순
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*고객 기타정보 목록*/
        List<CustomerInfoVO> clientDetailList = customerInfoService.getClientDetailGridList(paramMap);
		
		/*고객 기타정보 목록 총 수*/
        CustomerInfoVO totalCountInfo = customerInfoService.getClientDetailGridTotalCount(paramMap);
		
		/*paging 연산*/
        int records = totalCountInfo.getTotal_cnt();
        int total = (int) Math.ceil((double) records / (double) perPageRow);
		
		/*returnVO*/
        CustomerInfoJsonVO customerInfoJsonVO = new CustomerInfoJsonVO();

        customerInfoJsonVO.setTotal(total);        //page 수
        customerInfoJsonVO.setPage(page);            //현재 page
        customerInfoJsonVO.setRecords(records); // 전체 수
        customerInfoJsonVO.setRows(clientDetailList); // list
        customerInfoJsonVO.setTotalCountInfo(totalCountInfo); // 합계정보

        MarshallerUtil.marshalling("json", response, customerInfoJsonVO);
    }

    /**
     * <pre>
     * 1. 개요     : 고객 기타정보 등록/수정
     * 2. 처리내용 : 고객 기타정보를 등록/수정한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : procClientDetail
     */
    @RequestMapping("/business/procClientDetail.do")
    public void procClientDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, Object> paramMap = new HashMap<String, Object>();
		
		/*parameter*/
		/*기본 정보*/
        String cust_id = request.getParameter("cust_id"); // 거래처 코드
        String customer_id = request.getParameter("customer_id"); // 고객 코드
        String currentTab = request.getParameter("currentTab"); // 현재 탭
		
		/*등록용*/
        String[] insert_hakheo_nm = request.getParameterValues("insert_hakheo_nm"); // 학회명
        String[] insert_datef = request.getParameterValues("insert_datef"); // 기간 fr
        String[] insert_datet = request.getParameterValues("insert_datet"); // 기간 to
        String[] insert_jiwi = request.getParameterValues("insert_jiwi"); // 직위
        String[] insert_gwansimdo = request.getParameterValues("insert_gwansimdo"); // 관심도
        String[] insert_gita = request.getParameterValues("insert_gita"); // 기타사항
        String[] insert_relation = request.getParameterValues("insert_relation"); // 관계
        String[] insert_name = request.getParameterValues("insert_name"); // 성명
        String[] insert_birthday = request.getParameterValues("insert_birthday"); // 생일
        String[] insert_job = request.getParameterValues("insert_job"); // 직업
        String[] insert_sdate = request.getParameterValues("insert_sdate"); // 날짜
        String[] insert_kind = request.getParameterValues("insert_kind"); // 종류
        String[] insert_bigo = request.getParameterValues("insert_bigo"); // 상세내역
        String[] insert_corp_nm = request.getParameterValues("insert_corp_nm"); // 병원명/기관명/제약회사명
        String[] insert_lesson = request.getParameterValues("insert_lesson"); // 전문과
        String[] insert_friendship = request.getParameterValues("insert_friendship"); // 교우관계
		
		/*수정용*/
        String[] update_seq = request.getParameterValues("update_seq"); // 기타정보 seq
        String[] update_hakheo_nm = request.getParameterValues("update_hakheo_nm"); // 학회명
        String[] update_datef = request.getParameterValues("update_datef"); // 기간 fr
        String[] update_datet = request.getParameterValues("update_datet"); // 기간 to
        String[] update_jiwi = request.getParameterValues("update_jiwi"); // 직위
        String[] update_gwansimdo = request.getParameterValues("update_gwansimdo"); // 관심도
        String[] update_gita = request.getParameterValues("update_gita"); // 기타사항
        String[] update_relation = request.getParameterValues("update_relation"); // 관계
        String[] update_name = request.getParameterValues("update_name"); // 성명
        String[] update_birthday = request.getParameterValues("update_birthday"); // 생일
        String[] update_job = request.getParameterValues("update_job"); // 직업
        String[] update_sdate = request.getParameterValues("update_sdate"); // 날짜
        String[] update_kind = request.getParameterValues("update_kind"); // 종류
        String[] update_bigo = request.getParameterValues("update_bigo"); // 상세내역
        String[] update_corp_nm = request.getParameterValues("update_corp_nm"); // 병원명/기관명/제약회사명
        String[] update_lesson = request.getParameterValues("update_lesson"); // 전문과
        String[] update_friendship = request.getParameterValues("update_friendship"); // 교우관계

        //parameter를 map에 setting
        paramMap.put("cust_id", cust_id); // 거래처 코드
        paramMap.put("customer_id", customer_id); // 고객 코드
        paramMap.put("currentTab", currentTab); // 현재 탭
        paramMap.put("insert_hakheo_nm", insert_hakheo_nm); // 학회명
        paramMap.put("insert_datef", insert_datef); // 기간 fr
        paramMap.put("insert_datet", insert_datet); // 기간 to
        paramMap.put("insert_jiwi", insert_jiwi); // 직위
        paramMap.put("insert_gwansimdo", insert_gwansimdo); // 관심도
        paramMap.put("insert_gita", insert_gita); // 기타사항
        paramMap.put("insert_relation", insert_relation); // 관계
        paramMap.put("insert_name", insert_name); // 성명
        paramMap.put("insert_birthday", insert_birthday); // 생일
        paramMap.put("insert_job", insert_job); // 직업
        paramMap.put("insert_sdate", insert_sdate); // 날짜
        paramMap.put("insert_kind", insert_kind); // 종류
        paramMap.put("insert_bigo", insert_bigo); // 상세내역
        paramMap.put("insert_corp_nm", insert_corp_nm); // 병원명/기관명/제약회사명
        paramMap.put("insert_lesson", insert_lesson); // 전문과
        paramMap.put("insert_friendship", insert_friendship); // 교우관계
        paramMap.put("update_seq", update_seq); // 기타정보 seq
        paramMap.put("update_hakheo_nm", update_hakheo_nm); // 학회명
        paramMap.put("update_datef", update_datef); // 기간 fr
        paramMap.put("update_datet", update_datet); // 기간 to
        paramMap.put("update_jiwi", update_jiwi); // 직위
        paramMap.put("update_gwansimdo", update_gwansimdo); // 관심도
        paramMap.put("update_gita", update_gita); // 기타사항
        paramMap.put("update_relation", update_relation); // 관계
        paramMap.put("update_name", update_name); // 성명
        paramMap.put("update_birthday", update_birthday); // 생일
        paramMap.put("update_job", update_job); // 직업
        paramMap.put("update_sdate", update_sdate); // 날짜
        paramMap.put("update_kind", update_kind); // 종류
        paramMap.put("update_bigo", update_bigo); // 상세내역
        paramMap.put("update_corp_nm", update_corp_nm); // 병원명/기관명/제약회사명
        paramMap.put("update_lesson", update_lesson); // 전문과
        paramMap.put("update_friendship", update_friendship); // 교우관계

        String result = "N"; // 최종 수행 결과

        try {
            result = customerInfoService.procClientDetail(paramMap); // 기타정보 등록/수정
        } catch (Exception e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
		
		/*returnVO*/
        CustomerInfoJsonVO returnVO = new CustomerInfoJsonVO();

        returnVO.setMessage(result); // 수행 결과

        MarshallerUtil.marshalling("json", response, returnVO);
    }

    /**
     * <pre>
     * 1. 개요     : 고객 기타정보 삭제
     * 2. 처리내용 : 고객 기타정보를 삭제한다.
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : deleteClientDetail
     */
    @RequestMapping("/business/deleteClientDetail.do")
    public void deleteClientDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Map<String, String> paramMap = new HashMap<String, String>();
		
		/*parameter*/
        String cust_id = StringUtil.nvl(request.getParameter("cust_id")); // 거래처 코드
        String customer_id = StringUtil.nvl(request.getParameter("customer_id")); // 고객 코드
        String currentTab = request.getParameter("currentTab"); // 현재 탭
        String seq = StringUtil.nvl(request.getParameter("seq")); // 기타정보 seq
		
		/*parameter를 map에 setting*/
        paramMap.put("cust_id", cust_id); // 거래처 코드
        paramMap.put("customer_id", customer_id); // 고객 코드
        paramMap.put("currentTab", currentTab); // 현재 탭
        paramMap.put("seq", seq); // 기타정보 seq
		
		/*고객 기타정보 삭제*/
        int resultCount = customerInfoService.deleteClientDetail(paramMap);
		
		/*returnVO*/
        CustomerInfoJsonVO returnVO = new CustomerInfoJsonVO();

        returnVO.setResultCount(resultCount); // 수행 결과

        MarshallerUtil.marshalling("json", response, returnVO);
    }
}
