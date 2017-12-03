/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.mgmt.controller;

import com.hanaph.saleon.common.utils.ExcelDownManager;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.RequestFilterUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.main.controller.MainController;
import com.hanaph.saleon.mgmt.service.MgmtUserService;
import com.hanaph.saleon.mgmt.vo.MgmtUserJsonVO;
import com.hanaph.saleon.mgmt.vo.MgmtUserVO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : MgmtUserController.java
 * 설명 : MANAGER 사용자 관리 Controller class
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
public class MgmtUserController {

    /**
     * log4j
     */
    private static final Logger logger = Logger.getLogger(MainController.class);

    /**
     * MgmtUserService
     */
    @Autowired
    private MgmtUserService mgmtUserService;


    /**
     * <pre>
     * 1. 개요     : MANAGER > 사용자관리 > 사용자 리스트
     * 2. 처리내용 : 사용자 리스트를 조회하는 jsp view page
     * </pre>
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @Method Name : userMgmtMain
     */
    @RequestMapping("/mgmt/userMgmtMain.do")
    public ModelAndView userMgmtMain(HttpServletRequest request, HttpServletResponse response) {

		/*
         * ModelAndView객체 생성 해당 jsp page 호출
		 */
        ModelAndView mav = new ModelAndView("/mgmt/userMgmtMain");

        return mav;
    }

    /**
     * <pre>
     * 1. 개요     : MANAGER > 사용자관리 > 사용자 리스트
     * 2. 처리내용 : 사용자 리스트를 조회하는 ajax
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @Method Name : userMgmtMainAjax
     */
    @RequestMapping("/mgmt/userMgmtMainAjax.do")
    public void userMgmtMainAjax(HttpServletRequest request, HttpServletResponse response) {


        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);        // request XSS 필터링

		/*
         *  parameter를 map에 setting
		 */
        paramMap.put("sidx", StringUtil.nvl(paramMap.get("sidx"), "emp_name"));        // 거래처 이름
        paramMap.put("sord", StringUtil.nvl(paramMap.get("sord"), "asc"));            // jqgrid 정렬,오름차순 내림차순 기본 내림차순
        paramMap.put("searchText", StringUtil.nvl(paramMap.get("searchText"), ""));    // 검색어 값
        paramMap.put("empGubun", StringUtil.nvl(paramMap.get("empGubun"), "1"));        // 거래처 구분 1 고정 1:거래처
        paramMap.put("useYn", StringUtil.nvl(paramMap.get("useYn"), "Y"));            // 삭제 여부 Y:정상 N:삭제
		
		/*
		 * 조회 버튼 클릭시 사용자의 리스트를 호출하는 서비스
		 */
        List<MgmtUserVO> userList = mgmtUserService.getUserMgmtMain(paramMap);
		
		/*
		 * response body로 뿌려질 list를 object에 담는다.
		 */
        MgmtUserJsonVO returnMgmtUserVO = new MgmtUserJsonVO();

        returnMgmtUserVO.setRows(userList);                    // grid 값
        returnMgmtUserVO.setRecords(userList.size());        // 전체 레코드(row)수

        try {
            MarshallerUtil.marshalling("json", response, returnMgmtUserVO);        // 공통 marshallerUtil
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

    }

    /**
     * <pre>
     * 1. 개요     : MANAGER > 사용자관리 > 사용자 리스트
     * 2. 처리내용 : 사용자 리스트 엑셀 다운로드
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Method Name : userMgmtMainAjaxExcelDown
     */
    @RequestMapping("/mgmt/userMgmtMainAjaxExcelDown.do")
    public void userMgmtMainAjaxExcelDown(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);        // request XSS 필터링
		
		/*
		 *  parameter를 map에 setting
		 */
        paramMap.put("sidx", StringUtil.nvl(paramMap.get("sidx"), "emp_name"));        // 거래처 이름
        paramMap.put("sord", StringUtil.nvl(paramMap.get("sord"), "asc"));            // jqgrid 정렬,오름차순 내림차순 기본 내림차순
        paramMap.put("searchText", StringUtil.nvl(paramMap.get("searchText"), ""));    // 검색어 값
        paramMap.put("empGubun", StringUtil.nvl(paramMap.get("empGubun"), "1"));        // 거래처 구분 1 고정 1:거래처
        paramMap.put("useYn", StringUtil.nvl(paramMap.get("useYn"), "Y"));            // 삭제 여부 Y:정상 N:삭제
		
		/*
		 * 엑셀 버튼 클릭시 사용자의 리스트를 호출하는 서비스
		 */
        List<MgmtUserVO> userList = mgmtUserService.getUserMgmtMain(paramMap);
		
		/*
		 * 엑셀 맵 생성
		 */
        @SuppressWarnings("rawtypes")
        List<Map> excelMap = new ArrayList<Map>();
		
		/*
		 * 사용자 수 만큼 for
		 */
        for (int i = 0; i < userList.size(); i++) {

            Map<String, String> mapA1 = new HashMap<String, String>();

            MgmtUserVO mgmtUserVO = new MgmtUserVO();
            mgmtUserVO = userList.get(i);

            mapA1.put("1", mgmtUserVO.getEmp_code());    // 거래처코드
            mapA1.put("2", mgmtUserVO.getEmp_name());    // 거래처이름
            mapA1.put("3", mgmtUserVO.getPassword());    // 비밀번호
            mapA1.put("4", mgmtUserVO.getRole_no());    // role no

            excelMap.add(mapA1);
        }

        String[] header = {"사용자코드", "사용자명", "비밀번호", "ROLE명"};    // 엑셀 타이틀 항목
        String[] content = {"1", "2", "3", "4"};                            // contet와 타이틀 매칭

        ExcelDownManager.ExcelDown("사용자관리", header, content, excelMap, response);        // 공통 class 엑셀 메소드 호출

    }

    /**
     * <pre>
     * 1. 개요     : MANAGER > 사용자관리 > 사용자 리스트
     * 2. 처리내용 : 사용자 정보를 수정하는 메소드
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Method Name : updateMemberAjax
     */
    @RequestMapping("/mgmt/updateMemberAjax.do")
    public void updateMemberAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {


        String[] empCodes = request.getParameterValues("empCode");            // 거래처코드 배열
        String[] empNames = request.getParameterValues("empName");            // 거래처이름 배열
        String[] empPasswords = request.getParameterValues("empPassword");    // 비밀번호 배열

        int result = 0;        // 결과값 초기 셋팅
		
		/*
		 * requet로 받은 empCodes,empNames,empPasswords object에 셋팅
		 */
        MgmtUserVO paramMgmtUserVO = new MgmtUserVO();

        paramMgmtUserVO.setEmpCodes(empCodes);            // 거래처코드 배열
        paramMgmtUserVO.setEmpNames(empNames);            // 거래처이름 배열
        paramMgmtUserVO.setDept_code("");                // 부서코드 거래처는 없음으로 빈 값 셋팅
        paramMgmtUserVO.setEmpPasswords(empPasswords);    // 비밀번호 배열
        paramMgmtUserVO.setEmp_gb("1");                    // 사용자 구분은 to-be 거래처만 되는 상태로 셋팅
        paramMgmtUserVO.setUse_yn("Y");                    // 사용자 삭제 여부 Y:정상 N삭제
		
		/*
		 * 정보 수정한 사용자 db수정
		 */
        result = mgmtUserService.updateMember(paramMgmtUserVO);
		
		/*
		 * response body로 뿌려질 code를 object에 담는다.
		 */
        MgmtUserVO returnMgmtUserVO = new MgmtUserVO();
        returnMgmtUserVO.setResultCode(result);

        MarshallerUtil.marshalling("json", response, returnMgmtUserVO);        // 공통 marshallerUti

    }

    /**
     * <pre>
     * 1. 개요     : MANAGER > 사용자관리 > 사용자 리스트
     * 2. 처리내용 : 사용자 정보를 삭제하는 메소드
     * </pre>
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     * @Method Name : updateMemberAjax
     */
    @RequestMapping("/mgmt/deleteMemberAjax.do")
    public void deleteMemberAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);        // request XSS 필터링

        int result = 0;        // 결과 초기 값
		
		/*
		 * requet로 받은 empCodes object에 셋팅
		 */
        MgmtUserVO paramMgmtUserVO = new MgmtUserVO();

        paramMgmtUserVO.setEmp_code(paramMap.get("empCode"));        // 거래처 코드
        paramMgmtUserVO.setUse_yn("N");                                // 거래처 삭제 flag
		
		/*
		 * 사용자 삭제 서비스 호출
		 */
        result = mgmtUserService.deleteMember(paramMgmtUserVO);
		
		/*
		 * response body로 뿌려질 결과 값을 object에 담는다.
		 */
        MgmtUserVO returnMgmtUserVO = new MgmtUserVO();
        returnMgmtUserVO.setResultCode(result);

        MarshallerUtil.marshalling("json", response, returnMgmtUserVO);        // 공통 marshallerUtil

    }

    /**
     * <pre>
     * 1. 개요     : MANAGER > 사용자관리 > Role선택 팝업 page
     * 2. 처리내용 : 사용자 현재 적용된 role list jsp view page
     * </pre>
     *
     * @param request
     * @param response
     * @return ModelAndView
     * @Method Name : userMgmtMain
     */
    @RequestMapping("/mgmt/common/roleListPopup.do")
    public ModelAndView roleListPopup(HttpServletRequest request, HttpServletResponse response) {

        // ModelAndView 객체 생성
        ModelAndView mav = new ModelAndView("/mgmt/common/roleListPopup");
		
		/*
		 *  parameter를 map에 setting
		 */
        Map<String, String> paramMap = new HashMap<String, String>();

        paramMap = RequestFilterUtil.getReqData(request);
        paramMap.put("empCode", StringUtil.nvl(paramMap.get("empCode"), ""));

        return mav;        // ModelAndView return
    }

    /**
     * <pre>
     * 1. 개요     : MANAGER > 사용자관리 > Role선택 팝업 grid page
     * 2. 처리내용 : 사용자 현재 적용된 role list ajax
     * </pre>
     *
     * @param request
     * @param response
     * @return json
     * @throws Exception
     * @Method Name : rolePopupGridList
     */
    @RequestMapping("/mgmt/common/rolePopupGridList.do")
    public void rolePopupGridList(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);        // request XSS 필터링
		
		/*
		 *  parameter를 map에 setting
		 */
        paramMap.put("sidx", StringUtil.nvl(paramMap.get("sidx"), ""));            // 정렬 될 컬럼 명
        paramMap.put("sord", StringUtil.nvl(paramMap.get("sord"), ""));            // jqgrid 정렬,오름차순 내림차순 기본 내림차순
        paramMap.put("empCode", StringUtil.nvl(paramMap.get("empCode"), ""));    // 거래처코드
        paramMap.put("code", StringUtil.nvl(paramMap.get("code"), ""));            // role 코드 명
        paramMap.put("name", StringUtil.nvl(paramMap.get("name"), ""));            // role 명
		
		/*
		 * 사용자 현재 적용된 role list 서비스 호출
		 */
        List<MgmtUserVO> roleList = mgmtUserService.getRoleList(paramMap);
		
		/*
		 * response body로 뿌려질 결과 list를 object에 담는다.
		 */
        MgmtUserJsonVO returnMgmtUserVO = new MgmtUserJsonVO();

        returnMgmtUserVO.setRows(roleList);                    // grid 값
        returnMgmtUserVO.setRecords(roleList.size());        // 전체 레코드(row)수

        MarshallerUtil.marshalling("json", response, returnMgmtUserVO);        // 공통 marshallerUtil

    }

    /**
     * <pre>
     * 1. 개요     : MANAGER > 사용자관리 > Role insert ajax
     * 2. 처리내용 : 팝업에서 선택 된 role 추가
     * </pre>
     *
     * @param request
     * @param response
     * @return json
     * @throws Exception
     * @Method Name : insertRoleAjax
     */
    @RequestMapping("/mgmt/common/insertRoleAjax.do")
    public void insertRoleAjax(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap = RequestFilterUtil.getReqData(request);

        String roleNo = StringUtil.nvl(paramMap.get("roleNo"), "");
        String empCode = StringUtil.nvl(paramMap.get("empCode"), "");
		
		/*
		 *  roleNo,empCode를 paramMgmtUserVO에 setting
		 */
        MgmtUserVO paramMgmtUserVO = new MgmtUserVO();

        paramMgmtUserVO.setRole_no(roleNo);
        paramMgmtUserVO.setEmp_code(empCode);

        // 사용자 role 등록 후 결과 값 반환 서비스 호출
        int result = mgmtUserService.insertUserRole(paramMgmtUserVO);
		
		/*
		 * response body로 뿌려질 결과 값을 object에 담는다.
		 */
        MgmtUserJsonVO returnMgmtUserVO = new MgmtUserJsonVO();
        returnMgmtUserVO.setResultCode(result);

        MarshallerUtil.marshalling("json", response, returnMgmtUserVO);        // 공통 marshallerUtil

    }
}
