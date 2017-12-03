/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.main.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hanaph.saleon.common.utils.Environment;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.main.service.MainDashBoardService;
import com.hanaph.saleon.main.vo.NoticeJsonVO;
import com.hanaph.saleon.main.vo.NoticeVO;
import com.hanaph.saleon.member.vo.LoginUserVO;

/**
 * <pre>
 * Class Name : MainController.java
 * 설명 : Main화면과 관련된 컨트롤러 클래스
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 16.      slamwin
 * </pre>
 *
 * @author : slamwin(@irush.co.kr)
 * @version : 1.0
 * @since : 2014. 10. 16.
 */

@Controller
public class MainController {

    /**
     * 로그
     */
    private static final Logger logger = Logger.getLogger(MainController.class);

    /**
     * 환경변수
     */
    private Environment env = new Environment();

    /**
     * mainDashBoardService
     */
    @Autowired
    MainDashBoardService mainDashBoardService;

    /**
     * <pre>
     * 1. 개요     : 화면의 top frame 호출
     * 2. 처리내용 : 화면의 top frame 호출
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @Method Name : topFrame
     */
    @RequestMapping("/main.do")
    public ModelAndView topFrame(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/main/main");

		/*
         * 세션에서 사원 정보를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");

		
		/*
		 *  영업사원인데 비번이 초기비번 1111 일경우 비번변경 팝업을 띄운다.
		 *  2015-06-08 로그인 시 세션에 해당 내용을 담도록 수정
		 */
        logger.debug("loginUserVO.getEmp_gb() " + loginUserVO.getEmp_gb() + "//loginUserVO.getPassword() " + loginUserVO.getPassword());
		/*if(!("0".equals(loginUserVO.getEmp_gb()) || "1".equals(loginUserVO))
				&& "1111".equals(loginUserVO.getPassword())
				){
			mav.addObject("reqChgPassword", "true");		//비밀번호 변경 팝업을 띄우게 설정
		}*/
		
		/*
		 * 유저의 구분을 기준으로 main 화면 url 결정
		 */
        String dashboardUrl = "";                                //회원 구분별 호출할 url
        String ONLINE_ROOT = env.getValue("root_dir.url");        // 환경 변수에 저장된 url(프로토콜, 호스트정보, 컨텍스트정보)
        if ("admin".equals(loginUserVO.getEmp_code()) || "000".equals(loginUserVO.getEmp_gb()) || "001".equals(loginUserVO.getEmp_gb())) {    //관리자, 임원진
            dashboardUrl = ONLINE_ROOT + "/mainForManager.do";
        } else if ("1".equals(loginUserVO.getEmp_gb())) {            // 거래처
            dashboardUrl = ONLINE_ROOT + "/mainForCustomer.do";
        } else {                                                //영업사원
            dashboardUrl = ONLINE_ROOT + "/mainForEmployee.do";
        }

        mav.addObject("dashboardUrl", dashboardUrl);

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 임원진/관리자용 메인 화면(대쉬보드) 호출
     * 2. 처리내용 :	임원진/관리자용 메인 화면(대쉬보드) 호출
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @Method Name : mainForManager
     */
    @RequestMapping("/mainForManager.do")
    public ModelAndView mainForManager(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/main/mainForManager");
		
		/*
		 *  공지사항 조회
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("rownum", "5");                                                    //메인 화면에서는 5 row만 보이도록
        mav.addObject("noticeList", mainDashBoardService.getNoticeList(paramMap));        //공지사항 목록 조회

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 거래처용 메인 화면(대쉬보드) 호출
     * 2. 처리내용 :	거래처용 메인 화면(대쉬보드) 호출
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @Method Name : mainForCustomer
     */
    @RequestMapping("/mainForCustomer.do")
    public ModelAndView mainForCustomer(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/main/mainForCustomer");
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		
		/*
		 *  거래처 정보 조회
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("custId", loginUserVO.getEmp_code());
        mav.addObject("custInfo", mainDashBoardService.getCustInfo(paramMap));
        mav.addObject("custLoanPresentCondition", mainDashBoardService.getCustLoanPresentCondition(paramMap));
		
		/*
		 *  공지사항 조회
		 */
        paramMap.clear();
        paramMap.put("rownum", "5");
        mav.addObject("noticeList", mainDashBoardService.getNoticeList(paramMap));

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 영업사원용 메인 화면(대쉬보드) 호출
     * 2. 처리내용 :	영업사원용 메인 화면(대쉬보드) 호출
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return ModelAndView
     * @Method Name : mainForEmployee
     */
    @RequestMapping("/mainForEmployee.do")
    public ModelAndView mainForEmployee(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/main/mainForEmployee");
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		
		/*
		 *  영업사원 정보
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("empId", loginUserVO.getEmp_code());
        mav.addObject("empInfo", mainDashBoardService.getEmpInfo(paramMap));
		
		/*
		 *  공지사항 조회
		 */
        paramMap.clear();
        paramMap.put("rownum", "5");
        mav.addObject("noticeList", mainDashBoardService.getNoticeList(paramMap));

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 로그인시 뜨는 공지사항 팝업/상세화면 호출
     * 2. 처리내용 :	로그인시 뜨는 공지사항 팝업/상세화면 호출
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @Method Name : getNotice
     */
    @RequestMapping("/getNotice.do")
    public ModelAndView getNoticePopup(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/main/common/noticePop");
		
		/*
		 * 공지사항 조회
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("notice_id", StringUtil.nvl(request.getParameter("notice_id"), "0"));
        mav.addObject("notice", mainDashBoardService.getNoticeList(paramMap));

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 로그인시 뜨는 공지사항 이미지 팝업
     * 2. 처리내용 :	로그인시 뜨는 공지사항 이미지 팝업
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @Method Name : getNoticeImg
     */
    @RequestMapping("/getNoticeImg.do")
    public ModelAndView getNoticeImgPopup(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/main/common/noticeImgPop");

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 공지사항 목록 페이지 호출
     * 2. 처리내용 :	공지사항 목록 페이지 호출
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @Method Name : noticeList
     */
    @RequestMapping("/noticeList.do")
    public ModelAndView noticeList(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/main/noticeList");
        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 공지사항 목록 조회 ajax
     * 2. 처리내용 :	공지사항 목록 조회 ajax
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : getNoticeListAjax
     */
    @RequestMapping("/getNoticeListAjax.do")
    public void getNoticeListAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/*
		 *  검색 조건 
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("searchType", StringUtil.nvl(request.getParameter("searchType")));                    // 검색 타입
        paramMap.put("keyword", StringUtil.nvl(request.getParameter("keyword")));                        // 검색어
        paramMap.put("sttDate", StringUtil.nvl(request.getParameter("sttDate")).replaceAll("\\-", ""));    // 검색 기간. 시작 날짜
        paramMap.put("endDate", StringUtil.nvl(request.getParameter("endDate")).replaceAll("\\-", ""));    // 검색 기간. 끝 날짜
        paramMap.put("totalCountYn", "Y");                                                                // 전체 로우수 조회 여부
		
		/*
		 *  조회 결과 카운트
		 */
        NoticeVO totalCountInfo = mainDashBoardService.getNoticeList(paramMap).get(0);
		
		/*
		 *  paging 관련 변수
		 */
        int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"), "1"));            //현재 page
        int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"), "20"));    //페이지 size
        String sidx = StringUtil.nvl(request.getParameter("sidx"));                                //jqgrid 정렬 컬럼 index
        String sord = StringUtil.nvl(request.getParameter("sord"));                                //jqgrid 정렬 컬럼 order
        paramMap.put("sidx", sidx);
        paramMap.put("sord", sord);
        paramMap.put("page", String.valueOf(page));
        paramMap.put("perPageRow", String.valueOf(perPageRow));
        paramMap.remove("totalCountYn");                                                        // 리스트 조회하기 위해 전체 로우수 조회 여부 값 삭제
		
		/*
		 *  조회 결과 
		 */
        List<NoticeVO> noticeList = mainDashBoardService.getNoticeList(paramMap);
		
		/*
		 * jqGrid 페이징 설정 및 json
		 */
        int records = totalCountInfo.getTotal_cnt();                        //전체 로우수
        int total = (int) Math.ceil((double) records / (double) perPageRow);        //전체 page수
		
		/*
		 * 조회결과를 json 형태로 리턴하기 위해 VO에 셋팅
		 */
        NoticeJsonVO json = new NoticeJsonVO();
        json.setTotal(total);        //page 수
        json.setPage(page);            //현재 page
        json.setRecords(records);
        json.setRows(noticeList);
        json.setTotalCountInfo(totalCountInfo);

        MarshallerUtil.marshalling("json", response, json);

    }

    /**
     * <pre>
     * 1. 개요     : 거래처 주문현황 조회 ajax
     * 2. 처리내용 :	거래처 주문현황 조회 ajax
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException MarshallerUtil.marshalling()
     * @Method Name : getCustOrderPresentConditionAjax
     */
    @RequestMapping("/getCustOrderPresentConditionAjax.do")
    public void getCustOrderPresentConditionAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 세션에서 사원 정보 가져오기
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		
		/*
		 * 파라메터 처리
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("custId", loginUserVO.getEmp_code());            //사원코드
        paramMap.put("reqYear", StringUtil.nvl(request.getParameter("reqYear"), DateFormatUtils.format(new Date(), "yyyy")));    //조회 년도
        paramMap.put("AS_SILJUKYUL_IN", "90.909");        //판매실적률
        paramMap.put("AS_SILJUKYUL_IN_SU", "90.909");        //수금실적률

        /**
         * 거래처 주문현황 조회 데이터를 json형태로 리턴
         */
        MarshallerUtil.marshalling("json", response, mainDashBoardService.getCustOrderPresentCondition(paramMap));
    }

    /**
     * <pre>
     * 1. 개요     : 영업사업 년간 실적 조회 ajax
     * 2. 처리내용 :	영업사업 년간 실적 조회 ajax
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException MarshallerUtil.marshalling()
     * @Method Name : getEmpResultYearAjax
     */
    @RequestMapping("/getEmpResultYearAjax.do")
    public void getEmpResultYearAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 세션에서 사원 정보 가져오기
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		
		/*
		 * 파라메터 처리
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("empId", loginUserVO.getEmp_code());            //사원코드
        paramMap.put("year", StringUtil.nvl(request.getParameter("year"), DateFormatUtils.format(new Date(), "yyyy")));    //조회 년도
        paramMap.put("AS_SILJUKYUL_IN", "90.909");        //판매실적률
        paramMap.put("AS_SILJUKYUL_IN_SU", "90.909");        //수금실적률

        /**
         * 영업사업 년간 실적 조회 결과를 json 형태로 리턴
         */
        MarshallerUtil.marshalling("json", response, mainDashBoardService.getEmpResultYear(paramMap));
    }

    /**
     * <pre>
     * 1. 개요     :	파트 년간 실적 조회 ajax
     * 2. 처리내용 :	파트 년간 실적 조회 ajax
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException MarshallerUtil.marshalling()
     * @Method Name : getPartResultYearAjax
     */
    @RequestMapping("/getPartResultYearAjax.do")
    public void getPartResultYearAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 파라메터 처리
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("partCd", StringUtil.nvl(request.getParameter("partCd")));        //파트 코드
        paramMap.put("year", StringUtil.nvl(request.getParameter("year"), DateFormatUtils.format(new Date(), "yyyy")));    //조회 년도
        paramMap.put("AS_SILJUKYUL_IN", "90.909");        //판매실적률
        paramMap.put("AS_SILJUKYUL_IN_SU", "90.909");        //수금실적률

        /**
         * 파트 년간 실적 데이터를 json형태로 리턴
         */
        MarshallerUtil.marshalling("json", response, mainDashBoardService.getPartResultYear(paramMap));
    }

    /**
     * <pre>
     * 1. 개요     : 회사 년간 실적 조회 ajax
     * 2. 처리내용 :	회사 년간 실적 조회 ajax
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException MarshallerUtil.marshalling()
     * @Method Name : getCompanyResultYearAjax
     */
    @RequestMapping("/getCompanyResultYearAjax.do")
    public void getCompanyResultYearAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 파라메터 처리
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("year", StringUtil.nvl(request.getParameter("year"), DateFormatUtils.format(new Date(), "yyyy")));    //조회 년도
        paramMap.put("AS_SILJUKYUL_IN", "90.909");        //판매실적률
        paramMap.put("AS_SILJUKYUL_IN_SU", "90.909");        //수금실적률
		
		/*
		 *	회사 년간 실적을 파라메터로 조회 
		 */
        MarshallerUtil.marshalling("json", response, mainDashBoardService.getCompanyResultYear(paramMap));
    }

    /**
     * <pre>
     * 1. 개요     : 파트별 월별 실적 조회 ajax
     * 2. 처리내용 :	파트별 월별 실적 조회 ajax
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException MarshallerUtil.marshalling()
     * @Method Name : getCompanyResultMonthByPartAjax
     */
    @RequestMapping("/getCompanyResultMonthByPartAjax.do")
    public void getCompanyResultMonthByPartAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 파라메터 처리
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("year", StringUtil.nvl(request.getParameter("year"), DateFormatUtils.format(new Date(), "yyyy-MM")).replaceAll("\\-", ""));        //조회 년도
        paramMap.put("AS_SILJUKYUL_IN", "90.909");        //판매실적률
        paramMap.put("AS_SILJUKYUL_IN_SU", "90.909");        //수금실적률
		
		/*
		 * 파트별 월별 실적 데이터를 json 형태로 리턴
		 */
        MarshallerUtil.marshalling("json", response, mainDashBoardService.getCompanyResultMonthByPart(paramMap));
    }

    /**
     * <pre>
     * 1. 개요     : 부서 년간 실적 조회 ajax
     * 2. 처리내용 : 부서 년간 실적 조회 ajax
     * </pre>
     *
     * @param request
     * @param response
     * @throws IOException
     * @Method Name : getTeamResultYearAjax
     */
    @RequestMapping("/getTeamResultYearAjax.do")
    public void getTeamResultYearAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		/*
		 * 파라메터 처리
		 */
		
		/*세션에서 emp_code를 가져온다.*/
        HttpSession session = request.getSession(); // 세션생성
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String teamCd = StringUtil.nvl(loginUserVO.getDept_code());

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("partCd", StringUtil.nvl(request.getParameter("partCd")));        //파트 코드
        paramMap.put("teamCd", StringUtil.nvl(teamCd));        //부서 코드
        paramMap.put("year", StringUtil.nvl(request.getParameter("year"), DateFormatUtils.format(new Date(), "yyyy")));    //조회 년도
        paramMap.put("AS_SILJUKYUL_IN", "90.909");        //판매실적률
        paramMap.put("AS_SILJUKYUL_IN_SU", "90.909");        //수금실적률

        /**
         * 파트 년간 실적 데이터를 json형태로 리턴
         */
        MarshallerUtil.marshalling("json", response, mainDashBoardService.getTeamResultYear(paramMap));
    }

}
