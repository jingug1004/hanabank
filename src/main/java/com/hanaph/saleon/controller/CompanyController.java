/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.controller;

import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;
import com.hanaph.saleon.order.service.CompanyService;
import com.hanaph.saleon.order.vo.CompanyVO;
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
 * Class Name : CompanyController.java
 * 설명 : 회사정보 관련 컨트롤러 클래스
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 5.      jung a Woo
 * </pre>
 *
 * @author : jung a Woo(wja@irush.co.kr)
 * @version :
 * @since : 2014. 12. 5.
 */
@Controller
public class CompanyController {

    /**
     * CompanyService
     */
    @Autowired
    private CompanyService companyService;

    /**
     * <pre>
     * 1. 개요     : 회사정보 화면 호출
     * 2. 처리내용 : 회사정보 화면 jsp 노출
     * </pre>
     *
     * @param request HttpServletRequest
     * @return ModelAndView
     * @Method Name : companyInfo
     */
    @RequestMapping("/order/companyInfo.do")
    public ModelAndView companyInfo(HttpServletRequest request) {
        ModelAndView mav = new ModelAndView("order/companyInfo");
        return mav;
    }


    /**
     * <pre>
     * 1. 개요     : 회사정보를 조회
     * 2. 처리내용 : 회사정보를 조회해서 json형태로 리턴하는 ajax
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws IOException
     * @Method Name : companyInfoAjax
     */
    @RequestMapping("/order/companyInfoAjax.do")
    public void companyInfoAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
        /*
         * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String cust_id = StringUtil.nvl(loginUserVO.getEmp_code());        //cust_id

		/*
         * parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cust_id", cust_id);

		/*
         * 회사정보 조회
		 */
        CompanyVO companyVO = companyService.getCompanyInfo(paramMap);

		/*
         * VO형태를 json형태로 마샬
		 */
        MarshallerUtil.marshalling("json", response, companyVO);

    }


    /**
     * <pre>
     * 1. 개요     : 회사정보 수정
     * 2. 처리내용 : 회사정보를 수정하는 ajax
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @Method Name : updateCompanyAjax
     */
    @RequestMapping("/order/updateCompanyAjax.do")
    public void updateCompanyAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String cust_id = StringUtil.nvl(loginUserVO.getEmp_code());        //cust_id

		/*
		 * parameter
		 */
        String tel = StringUtil.nvl(request.getParameter("tel"));        //전화번호
        String hp = StringUtil.nvl(request.getParameter("hp"));            //핸드폰번호
        String fax = StringUtil.nvl(request.getParameter("fax"));        //팩스번호
        String email = StringUtil.nvl(request.getParameter("email"));    //email
		
		/*
		 * parameter를 map에 setting
		 */
        CompanyVO companyVO = new CompanyVO();
        companyVO.setCust_id(cust_id);
        companyVO.setTel(tel);
        companyVO.setHp(hp);
        companyVO.setFax(fax);
        companyVO.setEmail(email);
		
		/*
		 * 회사정보 수정한 후 그 결과를 json을 리턴
		 */
        int result = companyService.updateCompany(companyVO);
        if (result == 1) {
            companyVO.setResult("Y");    // 수정 성공
        } else {
            companyVO.setResult("N");    // 수정 실패
        }

        MarshallerUtil.marshalling("json", response, companyVO);    //VO형태를 json형태로 마샬
    }


    /**
     * <pre>
     * 1. 개요     : 회사정보 엑셀 다운로드
     * 2. 처리내용 : 회사정보를 엑셀 파일로 생성
     * </pre>
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @throws Exception
     * @Method Name : companyInfoExcelDown
     */
    @RequestMapping("/order/companyInfoExcelDown.do")
    public void companyInfoExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
        HttpSession session = request.getSession();
        LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
        String cust_id = StringUtil.nvl(loginUserVO.getEmp_code());        //cust_id
		
		/*
		 * parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("cust_id", cust_id);
				
		/*
		 * 회사정보 조회한 후 엑셀 파일로 생성한 다운로드하게 한다.
		 */
        CompanyVO companyInfo = companyService.getCompanyInfo(paramMap);

        Map<String, String> mapA1 = new HashMap<String, String>();        //excel생성시 필요한 데이터를 셋팅하기 위한 Map객체 생성. 밑의 header의 컬럼순서대로 데이터 셋팅
        mapA1.put("1", StringUtil.nvl(companyInfo.getCust_id()));
        mapA1.put("2", StringUtil.nvl(companyInfo.getCust_nm()));
        mapA1.put("3", StringUtil.nvl(companyInfo.getVou_no()));
        mapA1.put("4", StringUtil.nvl(companyInfo.getPresident()));
        mapA1.put("5", StringUtil.nvl(companyInfo.getBupin_nov()));
        mapA1.put("6", StringUtil.nvl(companyInfo.getUptae()));
        mapA1.put("7", StringUtil.nvl(companyInfo.getJongmok()));
        mapA1.put("8", StringUtil.nvl(companyInfo.getZip()));
        mapA1.put("9", StringUtil.nvl(companyInfo.getAddr()));
        mapA1.put("10", StringUtil.nvl(companyInfo.getTel()));
        mapA1.put("11", StringUtil.nvl(companyInfo.getHp()));
        mapA1.put("12", StringUtil.nvl(companyInfo.getFax()));
        mapA1.put("13", StringUtil.nvl(companyInfo.getEmail()));
        mapA1.put("14", StringUtil.nvl(companyInfo.getVirtual_no()));

        @SuppressWarnings("rawtypes")
        List<Map> excelMap = new ArrayList<Map>();    // excel다운용
        excelMap.add(mapA1);

        String[] header = {"회사번호", "회사명", "사업자번호", "대표자명", "주민(법인)번호", "업태", "종목", "우편번호", "주소", "전화번호", "핸드폰번호", "팩스번호", "이메일주소", "온라인입금전용계좌번호"}; // excel다운용 엑셀 헤더 값
        String[] content = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}; // excel다운용 mapA1에 담은 mapA1 이름과 동일 해야 합니다.

        ExcelDownManager.ExcelDown("회사정보", header, content, excelMap, response); // 실제로 excel 파일을 생성해서 다운로드 받을 수 있게 하는 공통 모듈 호출

    }

}
