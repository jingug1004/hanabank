/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.mgmt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.RequestFilterUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.main.controller.MainController;
import com.hanaph.saleon.mgmt.service.MgmtRoleService;
import com.hanaph.saleon.mgmt.vo.MgmtRoleJsonVO;
import com.hanaph.saleon.mgmt.vo.MgmtRoleVO;

/**
 * <pre>
 * Class Name : MgmtRoleController.java
 * 설명 : MANAGER 권한 등록관리 Controller class
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
public class MgmtRoleController {

    /**
     * log4j
     */
    private static final Logger logger = Logger.getLogger(MainController.class);

    /**
     * MgmtRoleService
     */
    @Autowired
    private MgmtRoleService mgmtRoleService;


    /**
     * <pre>
     * 1. 개요     : 권한 등록관리 화면
     * 2. 처리내용 : jsp view page
     * </pre>
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @Method Name : getRegAuthority
     */
    @RequestMapping("/mgmt/regAuthority.do")
    public ModelAndView regAuthority(HttpServletRequest request, HttpServletResponse response) {

		/*
         * ModelAndView 객체 생성 후 jsp view page 호출
		 */
        ModelAndView mav = new ModelAndView("/mgmt/regAuthority");

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : 권한 등록관리 화면
     * 2. 처리내용 : 권한 등록관리 화면의 role list ajax
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : regRoleListAjax
     */
    @RequestMapping("/mgmt/regRoleListAjax.do")
    public void regRoleListAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);                        // request XSS 필터링

		/*
		 *  parameter를 map에 setting
		 */
        paramMap.put("sidx", StringUtil.nvl(paramMap.get("sidx"), "role_no"));    // 정렬 될 컬럼 명
        paramMap.put("sord", StringUtil.nvl(paramMap.get("sord"), "desc"));        // jqgrid 정렬,오름차순 내림차순 기본 내림차순
		
		/*
		 * response body로 뿌려질 결과 list를 object에 담는다.
		 */
        MgmtRoleJsonVO returnMgmtJsonVO = new MgmtRoleJsonVO();
		
		/*
		 * role 목록을 가져오는 서비스 호출
		 */
        List<MgmtRoleVO> roleList = mgmtRoleService.getRegAuthority(paramMap);

        returnMgmtJsonVO.setRows(roleList);                    // grid 값
        returnMgmtJsonVO.setRecords(roleList.size());        // 전체 레코드(row)수

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtJsonVO);        // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }


    /**
     * <pre>
     * 1. 개요     : 권한 등록관리 화면
     * 2. 처리내용 : 권한 등록관리 화면의 role 사용자 등록 list ajax
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : regUserListAjax
     */
    @RequestMapping("/mgmt/regUserListAjax.do")
    public void regUserListAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);        // request XSS 필터링
		
		/*
		 *  parameter를 map에 setting
		 */
        paramMap.put("sidx", StringUtil.nvl(paramMap.get("sidx"), "emp_name"));        // 정렬 될 컬럼 명
        paramMap.put("sord", StringUtil.nvl(paramMap.get("sord"), "asc"));            // jqgrid 정렬,오름차순 내림차순 기본 내림차순
        paramMap.put("roleNo", StringUtil.nvl(paramMap.get("roleNo"), ""));            // role no
		
		/*
		 * 반환 될 결과 값을 담을 오브젝트 생성
		 */
        MgmtRoleJsonVO returnMgmtJsonVO = new MgmtRoleJsonVO();
		
		/*
		 * response body로 뿌려질 결과 list를 object에 담는다.
		 */
        List<MgmtRoleVO> rollUserJsonList = mgmtRoleService.getRoleUserList(paramMap);

        returnMgmtJsonVO.setRows(rollUserJsonList);                    // grid 값
        returnMgmtJsonVO.setRecords(rollUserJsonList.size());        // 전체 레코드(row)수


        try {
            MarshallerUtil.marshalling("json", response, returnMgmtJsonVO);        // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <pre>
     * 1. 개요     : 권한 등록관리 화면
     * 2. 처리내용 : 권한 등록관리 화면의 role 프로그램 등록 list ajax
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : regPgmListAjax
     */
    @RequestMapping("/mgmt/regPgmListAjax.do")
    public void regPgmListAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);        // request XSS 필터링
		
		/*
		 *  parameter를 map에 setting
		 */
        paramMap.put("sidx", StringUtil.nvl(paramMap.get("sidx"), ""));        // 정렬 될 컬럼 명
        paramMap.put("sord", StringUtil.nvl(paramMap.get("sord"), ""));        // jqgrid 정렬,오름차순 내림차순 기본 내림차순
        paramMap.put("pgmUseYn", "Y");
        paramMap.put("roleNo", StringUtil.nvl(paramMap.get("roleNo"), ""));    // role no
		
		/*
		 * response body로 뿌려질 결과 값을 object에 담는다.
		 */
        List<MgmtRoleVO> rollProgramJsonList = mgmtRoleService.getRoleProgramList(paramMap);
		
		/*
		 * response body로 뿌려질 결과 값을 object에 담는다.
		 */
        MgmtRoleJsonVO returnMgmtJsonVO = new MgmtRoleJsonVO();

        returnMgmtJsonVO.setRows(rollProgramJsonList);                    // grid 값
        returnMgmtJsonVO.setRecords(rollProgramJsonList.size());        // 전체 레코드(row)수

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtJsonVO);        // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * <pre>
     * 1. 개요     : 권한 등록관리 화면 role 처리
     * 2. 처리내용 : 권한 등록관리 화면 추가,저장 처리
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : procRoleAjax
     */
    @RequestMapping("/mgmt/procRoleAjax.do")
    public void insertRoleAjax(HttpServletRequest request, HttpServletResponse response) {


        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);        // request XSS 필터링

        String roleNo = StringUtil.nvl(paramMap.get("roleNo"), "");                // role no
        String roleCatCode = StringUtil.nvl(paramMap.get("roleCatCode"), "");    // role 카테고리 코드
        String roleName = StringUtil.nvl(paramMap.get("roleName"), "");            // role명
        String deptCode = StringUtil.nvl(paramMap.get("deptCode"), "");            // 부서코드
        String deptName = StringUtil.nvl(paramMap.get("deptName"), "");            // 부서 명
        String empCode = StringUtil.nvl(paramMap.get("empCode"), "");            // 사원 코드
        String empName = StringUtil.nvl(paramMap.get("empName"), "");            // 사원 명
        String type = StringUtil.nvl(paramMap.get("type"), "");                    // 처리 구분
		
		/*
		 * 파라메터로 받은 값을 셋팅 하기 위한 오브젝트 생성
		 */
        MgmtRoleVO paramMgmtRoleVO = new MgmtRoleVO();

        int result = 0;        // 기본 값 설정
		
		/*
		 * 파라메터 셋팅
		 */
        paramMgmtRoleVO.setRole_no(roleNo);
        paramMgmtRoleVO.setRole_cat_code(roleCatCode);
        paramMgmtRoleVO.setRole_name(roleName);
        paramMgmtRoleVO.setDept_code(deptCode);
        paramMgmtRoleVO.setDept_name(deptName);
        ;
        paramMgmtRoleVO.setEmp_code(empCode);
        paramMgmtRoleVO.setEmp_name(empName);
        paramMgmtRoleVO.setType(type);
		
		/*
		 * 처리 구분이 저장인경우
		 */
        if (type.equals("insert")) {
			/*
			 * role저장 서비스 호출
			 */
            result = mgmtRoleService.insertRole(paramMgmtRoleVO);
        } else {
			/*
			 * role 수정 서비스 호출
			 */
            result = mgmtRoleService.updateRole(paramMgmtRoleVO);
        }
		
		/*
		 * response body로 뿌려질 결과 값을 object에 담는다.
		 */
        MgmtRoleVO returnMgmtRoleVO = new MgmtRoleVO();

        returnMgmtRoleVO.setResultCode(result);
        returnMgmtRoleVO.setType(type);

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtRoleVO);        // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : 권한 등록관리 화면 role 처리
     * 2. 처리내용 : 권한 등록관리 role 복사
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : insertRoleCopyAjax
     */
    @RequestMapping("/mgmt/insertRoleCopyAjax.do")
    public void insertRoleCopyAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);                        // request XSS 필터링

        String roleNo = StringUtil.nvl(paramMap.get("roleNo"), "");                // role no
        String roleCatCode = StringUtil.nvl(paramMap.get("roleCatCode"), "");    // role 카테고리 코드
        String roleName = StringUtil.nvl(paramMap.get("roleName"), "");            // role명
        String deptCode = StringUtil.nvl(paramMap.get("deptCode"), "");            // 부서코드
        String deptName = StringUtil.nvl(paramMap.get("deptName"), "");            // 부서 명
        String empCode = StringUtil.nvl(paramMap.get("empCode"), "");            // 사원 코드
        String empName = StringUtil.nvl(paramMap.get("empName"), "");            // 사원 명
        String type = StringUtil.nvl(paramMap.get("type"), "");                    // 처리 구분

        int result = 0;        // 기본 결과 값 셋팅
		
		/*
		 * 파라메터로 받은 값을 셋팅 하기 위한 오브젝트 생성
		 */
        MgmtRoleVO paramMgmtRoleVO = new MgmtRoleVO();

        paramMgmtRoleVO.setRole_no(roleNo);
        paramMgmtRoleVO.setRole_cat_code(roleCatCode);
        paramMgmtRoleVO.setRole_name(roleName);
        paramMgmtRoleVO.setDept_code(deptCode);
        paramMgmtRoleVO.setDept_name(deptName);
        ;
        paramMgmtRoleVO.setEmp_code(empCode);
        paramMgmtRoleVO.setEmp_name(empName);
        paramMgmtRoleVO.setType(type);
		
		/*
		 * role복사를 서비스 호출
		 */
        result = mgmtRoleService.insertRoleCopy(paramMgmtRoleVO);
		
		/*
		 * response body로 뿌려질 결과 값을 object에 담는다.
		 */
        MgmtRoleVO returnMgmtRoleVO = new MgmtRoleVO();
        returnMgmtRoleVO.setResultCode(result);

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtRoleVO);            // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : 권한 등록관리 화면 role 처리
     * 2. 처리내용 : 권한 등록관리 role 삭제
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : deleteRoleAjax
     */
    @RequestMapping("/mgmt/deleteRoleAjax.do")
    public void deleteRoleAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);

        paramMap.put("roleNo", StringUtil.nvl(paramMap.get("roleNo")));        // role no

        MgmtRoleVO paramMgmtRoleVO = new MgmtRoleVO();        // parameter object생성
        MgmtRoleVO returnMgmtRoleVO = new MgmtRoleVO();        // return object생성

        int result = 0;        // 결과 기본 값 셋팅

        paramMgmtRoleVO.setRole_no(paramMap.get("roleNo"));        // role no셋팅
		
		/*
		 * role 삭제 서비스 호출
		 */
        result = mgmtRoleService.deleteRole(paramMgmtRoleVO);
		
		/*
		 * role삭제 결과 값 셋팅
		 */
        returnMgmtRoleVO.setResultCode(result);

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtRoleVO);    // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : role등록 사원 리스트 팝업
     * 2. 처리내용 : role등록 사원 리스트 팝업 jsp 출력
     * </pre>
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @Method Name : userPopup
     */
    @RequestMapping("/mgmt/common/userPopup.do")
    public ModelAndView userPopup(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * ModelAndView 객체 생성 및 jsp page 호출
		 */
        ModelAndView mav = new ModelAndView("/mgmt/common/userPopup");

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : role등록 사원 리스트 팝업 ajax
     * 2. 처리내용 : role등록 사원 리스트 팝업 사원 list ajax
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : userPopupGridList
     */
    @RequestMapping("/mgmt/common/userPopupGridList.do")
    public void userPopupGridList(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);        // request XSS 필터링
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("sidx", StringUtil.nvl(paramMap.get("sidx"), ""));            // 정렬 될 컬럼 명
        paramMap.put("sord", StringUtil.nvl(paramMap.get("sord"), ""));            // jqgrid 정렬,오름차순 내림차순 기본 내림차순
        paramMap.put("code", StringUtil.nvl(paramMap.get("code"), ""));            // 사원 코드
        paramMap.put("name", StringUtil.nvl(paramMap.get("name"), ""));            // 사원 명
        paramMap.put("pageType", StringUtil.nvl(paramMap.get("pageType"), ""));    // 팝업 page type
        paramMap.put("selectedType", StringUtil.nvl(paramMap.get("selectedType"), ""));    // 검색어 type
		
		
		/*
		 * 검색어 type이 부서인 경우
		 */
        if (paramMap.get("selectedType").equals("dept")) {
            paramMap.put("deptCode", StringUtil.nvl(paramMap.get("code"), ""));
            paramMap.put("deptName", StringUtil.nvl(paramMap.get("name"), ""));
            paramMap.put("empCode", "");
            paramMap.put("empName", "");
        } else {
            paramMap.put("empCode", StringUtil.nvl(paramMap.get("code"), ""));
            paramMap.put("empName", StringUtil.nvl(paramMap.get("name"), ""));
            paramMap.put("deptCode", "");
            paramMap.put("deptName", "");
        }
		
		/*
		 * response body로 뿌려질 결과 list를 object에 담는다.
		 */
        List<MgmtRoleVO> userList = mgmtRoleService.getUserList(paramMap);

        MgmtRoleJsonVO returnMgmtJsonVO = new MgmtRoleJsonVO();

        returnMgmtJsonVO.setRows(userList);                    // grid 값
        returnMgmtJsonVO.setRecords(userList.size());        // 전체 레코드(row)수

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtJsonVO);        // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : role등록 부서 리스트 팝업
     * 2. 처리내용 : role등록 부서 리스트 팝업 jsp 출력
     * </pre>
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @Method Name : deptListPopup
     */
    @RequestMapping("/mgmt/common/deptListPopup.do")
    public ModelAndView deptListPopup(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * ModelAndView 객체 생성 및 jsp page 호출
		 */
        ModelAndView mav = new ModelAndView("/mgmt/common/deptListPopup");

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : role등록 부서 리스트 팝업 ajax
     * 2. 처리내용 : role등록 부서 리스트 팝업 사원 list ajax
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : deptPopupGridList
     */
    @RequestMapping("/mgmt/common/deptPopupGridList.do")
    public void deptPopupGridList(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);

        paramMap.put("sidx", StringUtil.nvl(paramMap.get("sidx"), ""));            // 정렬 될 컬럼 명
        paramMap.put("sord", StringUtil.nvl(paramMap.get("sord"), ""));            // jqgrid 정렬,오름차순 내림차순 기본 내림차순
        paramMap.put("deptCode", StringUtil.nvl(paramMap.get("code"), ""));        // 부서 코드
        paramMap.put("deptName", StringUtil.nvl(paramMap.get("name"), ""));        // 부서 명
		
		/*
		 * response body로 뿌려질 결과 list를 object에 담는다.
		 */
        List<MgmtRoleVO> userList = mgmtRoleService.getDeptList(paramMap);

        MgmtRoleJsonVO returnMgmtJsonVO = new MgmtRoleJsonVO();

        returnMgmtJsonVO.setRows(userList);                    // grid 값
        returnMgmtJsonVO.setRecords(userList.size());        // 전체 레코드(row)수

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtJsonVO);    // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : role 사용자 추가
     * 2. 처리내용 : role등록 사원 리스트 팝업에서 사용자 선택 후 추가
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : insertUserRoleAjax
     */
    @RequestMapping("/mgmt/insertUserRoleAjax.do")
    public void insertUserRoleAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);

        String roleNo = StringUtil.nvl(paramMap.get("roleNo"), "");            // role no
        String empCode = StringUtil.nvl(request.getParameter("empCode"));    // 사용자 코드

        MgmtRoleVO paramMgmtRoleVO = new MgmtRoleVO();

        int result = 0;        // 결과 기본 값 셋팅

        paramMgmtRoleVO.setRole_no(roleNo);
        paramMgmtRoleVO.setEmp_code(empCode);
		
		/*
		 * 사용자 추가 role 서비스 호출
		 */
        result = mgmtRoleService.insertUserRole(paramMgmtRoleVO);
		
		/*
		 * response body로 뿌려질 결과 값을 object에 담는다.
		 */
        MgmtRoleVO returnMgmtRoleVO = new MgmtRoleVO();

        returnMgmtRoleVO.setResultCode(result);

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtRoleVO);        // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : role 사용자 삭제
     * 2. 처리내용 : role 사용자 리스트에서 사용자 선택 후 삭제
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : deleteUserRoleAjax
     */
    @RequestMapping("/mgmt/deleteUserRoleAjax.do")
    public void deleteUserRoleAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);

        String roleNo = StringUtil.nvl(paramMap.get("roleNo"), "");        // role no
        String empCode = StringUtil.nvl(paramMap.get("empCode"), "");    // 사원 코드

        MgmtRoleVO paramMgmtRoleVO = new MgmtRoleVO();        // parameter object생성
        MgmtRoleVO returnMgmtRoleVO = new MgmtRoleVO();        // return object생성

        int result = 0;        // 결과 기본 값 셋팅

        paramMgmtRoleVO.setRole_no(roleNo);
        paramMgmtRoleVO.setEmp_code(empCode);
		
		/*
		 * 삭제 될 사용자 role 서비스 호출
		 */
        result = mgmtRoleService.deleteUserRole(paramMgmtRoleVO);

        returnMgmtRoleVO.setResultCode(result);

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtRoleVO);        // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : role 프로그램 등록 팝업
     * 2. 처리내용 : role 프로그램 리스트에서 프로그램 선택 시 보여지는 팝업
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : rollDetailPopup
     */
    @RequestMapping("/mgmt/common/roleDetailPopup.do")
    public ModelAndView rollDetailPopup(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * ModelAndView 객체 생성 후 jsp view page 호출
		 */
        ModelAndView mav = new ModelAndView("/mgmt/common/roleDetailPopup");

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);

        String pgmNo = StringUtil.nvl(paramMap.get("pgmNo"), "");        // 프로그램 no
        String roleNo = StringUtil.nvl(paramMap.get("roleNo"), "");        // role no
		
		/*
		 * parameter를 map에 setting
		 */
        paramMap.put("pgmNo", pgmNo);        // 프로그램 no
        paramMap.put("pgmUseYn", "Y");        // 프로그램 사용 여부
        paramMap.put("roleNo", roleNo);        // role no

        MgmtRoleVO roleDetail = new MgmtRoleVO();
		
		/*
		 * 선택 된 프로그램의 role 상세 정보 팝업 서비스 호출
		 */
        roleDetail = mgmtRoleService.getRoleDetail(paramMap);

        mav.addObject("roleDetail", roleDetail);

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : role 프로그램 등록 팝업
     * 2. 처리내용 : role 프로그램 리스트에서 프로그램 선택 시 보여지는 팝업 버튼 수정
     * </pre>
     *
     * @param request
     * @return jsonObject
     * @Method Name : updateRoleDetailAjax
     */
    @RequestMapping("/mgmt/updateRoleDetailAjax.do")
    public void updateRoleDetailAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);

        String roleNo = StringUtil.nvl(paramMap.get("roleNo"));        // role no
        String pgmNo = StringUtil.nvl(paramMap.get("pgmNo"));        // 프로그램 no
        String useBtn = StringUtil.nvl(paramMap.get("useBtn"));        // 사용 중인 버튼 str

        MgmtRoleVO paramMgmtRoleVO = new MgmtRoleVO();
        MgmtRoleVO returnMgmtRoleVO = new MgmtRoleVO();

        int result = 0;        // 결과 값 기본 셋팅

        paramMgmtRoleVO.setRole_no(roleNo);        // role no
        paramMgmtRoleVO.setPgm_no(pgmNo);        // 프로그램 no
        paramMgmtRoleVO.setUse_btn(useBtn);        // 사용 중인 버튼 str
		
		/*
		 * 사용 중인 버튼 str update서비스 호출
		 */
        result = mgmtRoleService.updateRoleDetail(paramMgmtRoleVO);

        returnMgmtRoleVO.setResultCode(result);

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtRoleVO);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : 권한 등록관리 role 등록관리 팝업
     * 2. 처리내용 : 권한 등록관리 role 등록관리 버튼 클릭 시 보여지는 팝업 jsp view
     * </pre>
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @Method Name : roleProgramPopup
     */
    @RequestMapping("/mgmt/common/roleProgramPopup.do")
    public ModelAndView roleProgramPopup(HttpServletRequest request, HttpServletResponse response) {
		
		/*
		 * ModelAndView 객체 생성 후 jsp view page 호출
		 */
        ModelAndView mav = new ModelAndView("/mgmt/common/roleProgramPopup");

        return mav;

    }

    /**
     * <pre>
     * 1. 개요     : 권한 등록관리 role 등록관리 팝업
     * 2. 처리내용 : role추가
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : insertProgramAjax
     */
    @RequestMapping("/mgmt/insertRoleProgramAjax.do")
    public void insertRoleProgramAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);

        String pgmNo = StringUtil.nvl(paramMap.get("pgmNo"), "");        // 프로그램 no
        String roleNo = StringUtil.nvl(paramMap.get("roleNo"), "");        // role no

        MgmtRoleVO paramMgmtRoleVO = new MgmtRoleVO();        // parameter object생성
        MgmtRoleVO returnMgmtRoleVO = new MgmtRoleVO();        // return object생성

        int result = 0;        // 결과 값 기본 셋팅

        paramMgmtRoleVO.setPgm_no(pgmNo);        // 프로그램 no
        paramMgmtRoleVO.setRole_no(roleNo);        // role no
        paramMgmtRoleVO.setMenu_use_yn("Y");    // 메뉴 사용 여부
		
		/*
		 * 선택된 프로그램을 추가 하는 서비스 호출
		 */
        result = mgmtRoleService.insertRoleProgram(paramMgmtRoleVO);
		
		/*
		 * 결과 값 셋팅
		 */
        returnMgmtRoleVO.setResultCode(result);

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtRoleVO);        // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : 권한 등록관리 role 등록관리 팝업
     * 2. 처리내용 : role수정
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : updateRoleProgramAjax
     */
    @RequestMapping("/mgmt/updateRoleProgramAjax.do")
    public void updateRoleProgramAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);

        String pgmNo = StringUtil.nvl(paramMap.get("pgmNo"), "");        // 프로그램 no
        String roleNo = StringUtil.nvl(paramMap.get("roleNo"), "");        // role no
        String useBtn = StringUtil.nvl(paramMap.get("useBtn"), "");        // 사용 중인 버튼 str

        int result = 0;        // 결과 값 기본 셋팅

        MgmtRoleVO paramMgmtRoleVO = new MgmtRoleVO();        // parameter object생성
        MgmtRoleVO returnMgmtRoleVO = new MgmtRoleVO();        // return object생성
		
		/*
		 * $기호를 체크하여 수정 할 버튼이 1개 이상인 경우 체크
		 */
        if (!"".equals(pgmNo) && pgmNo.indexOf("$") > -1) {
            String pgmNos[] = pgmNo.split("\\$");        // 프로그램 no 배열 셋팅
            String useBtns[] = useBtn.split("\\$");        // 버튼 string 배열 셋팅

            for (int i = 0; i < pgmNos.length; i++) {        // 프로그램의 길이 만큼 for

                paramMgmtRoleVO = new MgmtRoleVO();        // for문 만큼 셋팅 할 object생성

                paramMgmtRoleVO.setRole_no(roleNo);        // role no
                paramMgmtRoleVO.setPgm_no(pgmNos[i]);    // 프로그램 no
                paramMgmtRoleVO.setUse_btn(useBtns[i]);    // 버튼 string

                // 권한 등록 관리 Role 프로그램 등록 팝업의 버튼 update를 같이 사용
                result = mgmtRoleService.updateRoleDetail(paramMgmtRoleVO);
                result++;
            }
		/*
		 * 수정 할 버튼이 단건인 경우	
		 */
        } else {

            paramMgmtRoleVO.setRole_no(roleNo);    // role no
            paramMgmtRoleVO.setPgm_no(pgmNo);    // 프로그램 no
            paramMgmtRoleVO.setUse_btn(useBtn);    // 버튼 string

            // 권한 등록 관리 Role 프로그램 등록 팝업의 버튼 update를 같이 사용
            result += mgmtRoleService.updateRoleDetail(paramMgmtRoleVO);
        }
		
		/*
		 * 결과 int 값을 return object에 셋팅
		 */
        returnMgmtRoleVO.setResultCode(result);

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtRoleVO);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : 권한 등록관리 role 등록관리 팝업
     * 2. 처리내용 : role삭제
     * </pre>
     *
     * @param request
     * @param response
     * @return jsonObject
     * @Method Name : deleteRoleProgramAjax
     */
    @RequestMapping("/mgmt/deleteRoleProgramAjax.do")
    public void deleteRoleProgramAjax(HttpServletRequest request, HttpServletResponse response) {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);

        String pgmNo = StringUtil.nvl(paramMap.get("pgmNo"), "");        // 프로그램 no
        String roleNo = StringUtil.nvl(paramMap.get("roleNo"), "");        // role no

        MgmtRoleVO paramMgmtRoleVO = new MgmtRoleVO();        // parameter object생성
        MgmtRoleVO returnMgmtRoleVO = new MgmtRoleVO();        // return object생성

        int result = 0;        // 결과 값 기본 셋팅

        paramMgmtRoleVO.setPgm_no(pgmNo);        // 프로그램 no
        paramMgmtRoleVO.setRole_no(roleNo);        // role no
        paramMgmtRoleVO.setMenu_use_yn("Y");    // 메뉴 사용여부
		
		/*
		 * role 삭제 서비스 호출
		 */
        result = mgmtRoleService.deleteRoleProgram(paramMgmtRoleVO);
		
		/*
		 * 결과 int 값을 return object에 셋팅
		 */
        returnMgmtRoleVO.setResultCode(result);

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtRoleVO);        // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

}
