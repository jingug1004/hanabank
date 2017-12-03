/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.mgmt.controller;

import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.RequestFilterUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.main.controller.MainController;
import com.hanaph.saleon.mgmt.service.MgmtInquireService;
import com.hanaph.saleon.mgmt.vo.MgmtInquireJsonVO;
import com.hanaph.saleon.mgmt.vo.MgmtInquireVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : MgmtInquireRoleController.java
 * 설명 : MANAGER 권한조회 Controller class
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 27.      slamwin
 * </pre>
 *
 * @author : slamwin(@irush.co.kr)
 * @version :
 * @since : 2014. 10. 27.
 */
@Controller
public class MgmtInquireController {

    /**
     * log4j
     */
    private static final Logger logger = Logger.getLogger(MainController.class);

    /**
     * MgmtInquireService
     */
    @Autowired
    private MgmtInquireService mgmtInquireService;

    /**
     * <pre>
     * 1. 개요     : 권한조회 메인 화면 컨트롤러
     * 2. 처리내용 : 권한조회 메인 화면 jsp 노출
     * </pre>
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @Method Name : inquireRole
     */
    @RequestMapping("/mgmt/inquireRole.do")
    public ModelAndView inquireRole(HttpServletRequest request, HttpServletResponse response) {

        ModelAndView mav = new ModelAndView("/mgmt/inquireRole");

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 권한조회 사용 프로그램
     * 2. 처리내용 : 사용자별 사용 프로그램 조회 ajax
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : usePgmByEmpCodeAjax
     */
    @RequestMapping("/mgmt/getUserPgmListAjax.do")
    public void usePgmByEmpCodeAjax(HttpServletRequest request, HttpServletResponse response) {

		/*
         *  parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request); // request XSS 필터링

        paramMap.put("empCode", StringUtil.nvl(paramMap.get("emp_code")));    //	사원코드
        paramMap.put("topCode", "ZZZZZ");    // 최상위 depth부터

		/*
         * 메뉴 트리를 구성할 list를 가지고 온다.
		 */
        List<MgmtInquireVO> pgmList = mgmtInquireService.getUserPgmList(paramMap);
		
		/*
		 * response body로 뿌려질 list를 object에 담는다.
		 */
        MgmtInquireVO mgmtInquireVO = new MgmtInquireVO();
        mgmtInquireVO.setMenuList(pgmList);

        try {
            MarshallerUtil.marshalling("json", response, mgmtInquireVO);  // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : 권한조회 사용권한
     * 2. 처리내용 : 사용자별 사용 권한 list ajax
     * </pre>
     *
     * @param request
     * @param response
     * @Method Name : getUserRoleListAjax
     */
    @RequestMapping("/mgmt/getUserRoleListAjax.do")
    public void getUserRoleListAjax(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 *  parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);

        paramMap.put("sidx", StringUtil.nvl(paramMap.get("sidx"), "role_no"));  // role no
        paramMap.put("sord", StringUtil.nvl(paramMap.get("sord"), "asc"));      // jqgrid 정렬,오름차순 내림차순 기본 내림차순
        paramMap.put("empCode", StringUtil.nvl(paramMap.get("emp_code"), "")); // 사원 코드
		
		/*
		 * 사용자 role 목록
		 */
        List<MgmtInquireVO> roleList = mgmtInquireService.getUserRoleList(paramMap);
		
		/*
		 * response body로 뿌려질 list를 object에 담는다.
		 */
        MgmtInquireJsonVO returnMgmtInquireJonVO = new MgmtInquireJsonVO();
        returnMgmtInquireJonVO.setRows(roleList);                    // grid 값
        returnMgmtInquireJonVO.setRecords(roleList.size());            // 전체 레코드(row)수

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtInquireJonVO);    // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : 권한 조회 사용자 list
     * 2. 처리내용 : 사용자 list ajax
     * </pre>
     *
     * @param request
     * @param response
     * @Method Name : getEmpListByPgmnoAjax
     */
    @RequestMapping("/mgmt/getEmpListByPgmnoAjax.do")
    public void getEmpListByPgmnoAjax(HttpServletRequest request, HttpServletResponse response) {


        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);        // request XSS 필터링
		
		/*
		 *  parameter를 map에 setting
		 */
        paramMap.put("sidx", StringUtil.nvl(paramMap.get("sidx"), "dept_code"));        // 부서코드
        paramMap.put("sord", StringUtil.nvl(paramMap.get("sord"), "asc"));            // jqgrid 정렬,오름차순 내림차순 기본 내림차순
        paramMap.put("pgm_no", StringUtil.nvl(paramMap.get("pgm_no"), ""));
		
		/*
		 * 사용자 프로그램 목록
		 */
        List<MgmtInquireVO> roleList = mgmtInquireService.getEmpListByPgmno(paramMap);
		
		/*
		 * response body로 뿌려질 list를 object에 담는다.
		 */
        MgmtInquireJsonVO returnMgmtInquireJonVO = new MgmtInquireJsonVO();
        returnMgmtInquireJonVO.setRows(roleList);                    // grid 값
        returnMgmtInquireJonVO.setRecords(roleList.size());            // 전체 레코드(row)수

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtInquireJonVO);    // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : 권한 조회 프로그램 tab
     * 2. 처리내용 : 권한 조회 프로그램 tab 사용권한 list ajax
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : getRoleListByPgmnoAjax
     */
    @RequestMapping("/mgmt/getRoleListByPgmnoAjax.do")
    public void getRoleListByPgmnoAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);        // request XSS 필터링
		
		/*
		 *  parameter를 map에 setting
		 */
        paramMap.put("sidx", StringUtil.nvl(paramMap.get("sidx"), "role_no"));    // role no
        paramMap.put("sord", StringUtil.nvl(paramMap.get("sord"), "asc"));        // jqgrid 정렬,오름차순 내림차순 기본 내림차순
        paramMap.put("pgm_no", StringUtil.nvl(paramMap.get("pgm_no"), ""));     // 프로그램 no
		
		/*
		 * 사용권한 list 서비스 호출
		 */
        List<MgmtInquireVO> roleList = mgmtInquireService.getRoleListByPgmno(paramMap);
		
		/*
		 * response body로 뿌려질 list를 object에 담는다.
		 */
        MgmtInquireJsonVO returnMgmtInquireJsonVO = new MgmtInquireJsonVO();
        returnMgmtInquireJsonVO.setRows(roleList);                    // grid 값
        returnMgmtInquireJsonVO.setRecords(roleList.size());        // 전체 레코드(row)수

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtInquireJsonVO);    // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : 권한 조회 사용자 role 복사
     * 2. 처리내용 : 권한 조회 사용자 list 더블 클릭 시 팝업에서 사용자 조회 후 role 복사
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : userRoleCopyAjax
     */
    @RequestMapping("/mgmt/userRoleCopyAjax.do")
    public void userRoleCopyAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);
		
		/*
		 *  parameter를 MgmtInquireVO에 setting
		 */
        MgmtInquireVO mgmtInquireVO = new MgmtInquireVO();
        mgmtInquireVO.setRole_no(StringUtil.nvl(paramMap.get("roleNo"), ""));        // role no
        mgmtInquireVO.setEmp_code(StringUtil.nvl(paramMap.get("empCode"), ""));        // 사원코드

        // 결과 값
        int result = 0;

        // role no값이 필수인경우만
        if (!mgmtInquireVO.getRole_no().equals("")) {
			/*
			 * role no 값이 필수인 경우만 선택된 사용자의 role을 복사 하는 서비스 호출
			 */
            result = mgmtInquireService.insertUserRoleCopy(mgmtInquireVO);
        }
		
		/*
		 * response body로 뿌려질 code를 object에 담는다.
		 */
        MgmtInquireJsonVO returnMgmtInquireJsonVO = new MgmtInquireJsonVO();
        returnMgmtInquireJsonVO.setResultCode(result);

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtInquireJsonVO);    // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }
}
