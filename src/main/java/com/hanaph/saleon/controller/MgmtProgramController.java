/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.controller;

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
import com.hanaph.saleon.mgmt.service.MgmtProgramService;
import com.hanaph.saleon.mgmt.vo.MgmtButtonJsonVO;
import com.hanaph.saleon.mgmt.vo.MgmtButtonVO;
import com.hanaph.saleon.mgmt.vo.MgmtProgramVO;

/**
 * <pre>
 * Class Name : MgmtProgramController.java
 * 설명 : MANAGER 프로그램 등록 관리 Controller class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 27.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2014. 10. 27.
 */
@Controller
public class MgmtProgramController {
	
	/**
	 * log4j
	 */
	private static final Logger logger = Logger.getLogger(MainController.class);
	
	/**
	 * MgmtProgramService
	 */
	@Autowired
	private MgmtProgramService mgmtProgramService;
	
	
	/**
	 * <pre>
	 * 1. 개요     : MANAGER 프로그램 등록 관리 JSP page
	 * 2. 처리내용 : MANAGER 프로그램 등록 관리의 jap view page
	 * </pre>
	 * @Method Name : getRegManagement
	 * @param request
	 * @return ModelAndView
	 */		
	@RequestMapping("/mgmt/regManagement.do")
	public ModelAndView getRegManagement(HttpServletRequest request){
		
		/*
		 * ModelAndView 객체 생성 후 jsp page호출
		 */
		ModelAndView mav = new ModelAndView("/mgmt/regManagement");
		
		return mav;
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : MANAGER의 메뉴 tree list를 노출 하는 ajax
	 * 2. 처리내용 : MANAGER메뉴 상의 메뉴 tree구조 공통 ajax 
	 * </pre>
	 * @Method Name : getTreeMenuAjax
	 * @param request
	 * @param response
	 * @return jsonObject
	 */		
	@RequestMapping("/mgmt/common/treeMenuAjax.do")
	public void treeMenuAjax(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap = RequestFilterUtil.getReqData(request);		// request XSS 필터링
		
		/*
		 *  parameter를 map에 setting
		 */
		paramMap.put("type", StringUtil.nvl(paramMap.get("type"),"ALL"));			// 전체 프로그램 값 등록 프로그램 값 : MENU
		paramMap.put("roleNo", StringUtil.nvl(paramMap.get("roleNo"),""));			// role no
		paramMap.put("pgmUseYn", StringUtil.nvl(paramMap.get("pgmUseYn"),"Y"));		// 프로그램 사용여부
		paramMap.put("menuUseYn", StringUtil.nvl(paramMap.get("menuUseYn"),"Y"));	// 메뉴 사용여부
		paramMap.put("topCode", StringUtil.nvl(paramMap.get("topCode"),"00000"));	// 최상위 코드 고정 
		
		/*
		 * 메뉴 tree를 구성 하는 서비스 호출
		 */
		List<MgmtProgramVO> menuList = mgmtProgramService.getMenuList(paramMap); 
		
		/*
		 * response body로 뿌려질 결과 list를 object에 담는다.
		 */
		MgmtProgramVO mgmtVO = new MgmtProgramVO();
		
		mgmtVO.setMenuList(menuList);
		
		try {
			MarshallerUtil.marshalling("json", response, mgmtVO);		// 공통 marshallerUtil
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 프로그램 등록 관리의 프로그램 상세정보
	 * 2. 처리내용 : 메뉴트리에서 선택 된 프로그램의 상세정보를 가져오는 ajax
	 * </pre>
	 * @Method Name : programDetailAjax
	 * @param request
	 * @param response
	 * @return jsonObject
	 */		
	@RequestMapping("/mgmt/programDetailAjax.do")
	public void programDetailAjax(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap = RequestFilterUtil.getReqData(request);			// request XSS 필터링	
		
		/*
		 * response body로 뿌려질 결과 list를 object에 담는다.
		 */
		MgmtProgramVO mgmtVO = new MgmtProgramVO();
		
		/*
		 * 프로그램 상세 정보 값을 가져오는 서비스 호출
		 */
		mgmtVO = mgmtProgramService.detailProgramInfo(paramMap);
		
		try {
			MarshallerUtil.marshalling("json", response, mgmtVO);		// 공통 marshallerUtil
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 프로그램 등록 관리의 신규 프로그램 sorting number
	 * 2. 처리내용 : 메뉴트리의 메뉴 클릭 시 신규 프로그램을 추가 할때 가지고 오는 신규 순위
	 * </pre>
	 * @Method Name : getProgramSortNum
	 * @param request
	 * @param response
	 * @return jsonObject
	 */		
	@RequestMapping("/mgmt/programSortNumAjax.do")
	public void programSortNumAjax(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap = RequestFilterUtil.getReqData(request);		// request XSS 필터링
		
		int maxSortNum = 0;		// 결과 값 기본 셋팅	
		
		/*
		 * 프로그램 정렬 순서의 코드를 담기위한 오브젝트 생성
		 */
		MgmtProgramVO returnMgmtVO = new MgmtProgramVO();
		
		/*
		 * 프로그램 정렬 순서의 코드를 가지고 오는 서비스 호출
		 */
		returnMgmtVO = mgmtProgramService.getProgramSortNum(paramMap);
		
		maxSortNum = returnMgmtVO.getMax_sort(); 	// 정렬 순서 셋팅
		
		if(maxSortNum > 0){
			returnMgmtVO.setResultCode(1);	// 정렬 순서 코드가 1이상이면 성공
		}else{
			returnMgmtVO.setResultCode(0);	// 정렬 순서 코드가 1이 아닌경우면 실패
		}
		
		try {
			MarshallerUtil.marshalling("json", response, returnMgmtVO);		// 공통 marshallerUtil		
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 프로그램 등록 관리 프로그램 추가,저장,삭제 
	 * 2. 처리내용 : 프로그램 등록 관리 추가,저장,삭제 처리
	 * </pre>
	 * @Method Name : procProgram
	 * @param request
	 * @param response
	 * @return jsonObject
	 */		
	@RequestMapping("/mgmt/procProgramAjax.do")
	public void procProgramAjax(HttpServletRequest request, HttpServletResponse response){
		
		String procType = StringUtil.nvl(request.getParameter("procType"),"insert");		// 버튼 처리 타입
		String pgmNo = StringUtil.nvl(request.getParameter("pgmNo"),"");					// 프로그램 no
		String pgmId = StringUtil.nvl(request.getParameter("pgmId"),"");					// 프로그램 id
		String pgmName = StringUtil.nvl(request.getParameter("pgmName"),"");				// 프로그램 명
		String pgmKindCode = StringUtil.nvl(request.getParameter("pgmKindCode"),"");		//
		String pgmUseYn = StringUtil.nvl(request.getParameter("pgmUseYn"),"N");				// 프로그램 사용 여부
		String menuUseYn = StringUtil.nvl(request.getParameter("menuUseYn"),"N");			// 메뉴 사용여부
		String parentPgm = StringUtil.nvl(request.getParameter("parentPgm"),"00000");		// 상위코드 상위코드가 없을 시 최상위 코드 셋팅
		String sortOrder = StringUtil.nvl(request.getParameter("sortOrder"),"1");			// 정렬 순서 기본 값은 1
		String picture = StringUtil.nvl(request.getParameter("picture"),"MENU");			// tree icon
		String selectPicture = StringUtil.nvl(request.getParameter("selectPicture"),"MENU");// 선택된 tree icon
		
		/*
		 * request parameter를 담을 오브젝트
		 */
		MgmtProgramVO paramMgmtVO = new MgmtProgramVO();
		
		/*
		 * 결과 반환 값을 담을 오브젝트
		 */
		MgmtProgramVO returnMgmtVO = new MgmtProgramVO();
		
		/*
		 *  처리 type이 등록 인 경우
		 */
		if("insert".equals(procType)){
			
			/*
			 * 프로그램 아이디는 기본 생성 되므로 없을 경우만 실행
			 */
			if("".equals(pgmNo)){
				paramMgmtVO.setPgm_no(pgmNo);								// 프로그램 no
				paramMgmtVO.setPgm_id(pgmId);								// 프로그램 id
				paramMgmtVO.setPgm_name(pgmName);							// 프로그램 명
				paramMgmtVO.setPgm_kind_code(pgmKindCode);					// 프로그램 메뉴 구분
				paramMgmtVO.setPgm_use_yn(pgmUseYn);						// 프로그램 사용 여부
				paramMgmtVO.setMenu_use_yn(menuUseYn);						// 메뉴 사용 여부
				paramMgmtVO.setParent_pgm(parentPgm);						// 상위 프로그램 id
				paramMgmtVO.setSort_order(Integer.parseInt(sortOrder));		// 정렬 순서
				paramMgmtVO.setPicture(picture);							// tree icon		
				paramMgmtVO.setSelect_picture(selectPicture);				// 선택된 tree icon
			}
			
			/*
			 * 프로그램 등록 서비스 호출 후 결과 값 반환
			 */
			returnMgmtVO.setResultCode(mgmtProgramService.insertProgram(paramMgmtVO)); 
		
		/*
		 *  처리 type이 저장 인 경우
		 */
		}else if("save".equals(procType)){
			
			paramMgmtVO.setPgm_no(pgmNo);							// 프로그램 no
			paramMgmtVO.setPgm_id(pgmId);							// 프로그램 id
			paramMgmtVO.setPgm_name(pgmName);						// 프로그램 명
			paramMgmtVO.setPgm_kind_code(pgmKindCode);				// 프로그램 메뉴 구분
			paramMgmtVO.setPgm_use_yn(pgmUseYn);					// 프로그램 사용 여부
			paramMgmtVO.setMenu_use_yn(menuUseYn);					// 메뉴 사용 여부
			paramMgmtVO.setParent_pgm(parentPgm);					// 상위 프로그램 id
			paramMgmtVO.setSort_order(Integer.parseInt(sortOrder));	// 정렬 순서
			paramMgmtVO.setPicture(picture);						// tree icon	
			paramMgmtVO.setSelect_picture(selectPicture);			// 선택된 tree icon
			
			/*
			 * 프로그램 아이디가 없는 경우 등록으로 간주
			 */
			if("".equals(pgmNo)){
				returnMgmtVO.setResultCode(mgmtProgramService.insertProgram(paramMgmtVO)); 
			/*
			 * 프로그램 아이디가 있는 경우 수정으로 간주
			 */
			}else{
				returnMgmtVO.setResultCode(mgmtProgramService.updateProgram(paramMgmtVO)); 
			}
			
		/*
		 *  처리 type이 삭제 인 경우
		 */	
		}else{
			
			/*
			 * 프로그램 id가 무조건 존재 해야 로직 실행
			 */
			if(!"".equals(pgmNo)){
				/*
				 * 프로그램 id셋팅 후 프로그램 삭제
				 */
				paramMgmtVO.setPgm_no(pgmNo);
				returnMgmtVO.setResultCode(mgmtProgramService.deleteProgram(paramMgmtVO));
			}
			
		}
		
		/*
		 * 등록,수정,삭제에 대한 type 반환
		 */
		returnMgmtVO.setResultMsg(procType);
		
		try {
			MarshallerUtil.marshalling("json", response, returnMgmtVO);		// 공통 marshallerUtil	
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 버튼 리스트를 출력 하는 ajax
	 * 2. 처리내용 : PF_PGM_BTN테이블의 버튼 리스트 출력
	 * </pre>
	 * @Method Name : buttonListAjax
	 * @param request
	 * @param response
	 * @return jsonObject
	 */		
	@RequestMapping("/mgmt/buttonListAjax.do")
	public void buttonListAjax(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap = RequestFilterUtil.getReqData(request);				// request XSS 필터링
		
		String sidx = StringUtil.nvl(paramMap.get("sidx"),"btn_id");	// 버튼 id
		String sord = StringUtil.nvl(paramMap.get("sord"),"asc");		// 정렬 될 컬럼 명 
		String useYn = StringUtil.nvl(paramMap.get("useYn"),"");		// 버튼 사용 여부
		
		/*
		 *  parameter를 map에 setting
		 */
		paramMap.put("sidx", sidx);
		paramMap.put("sord", sord);
		paramMap.put("useYn", useYn);
		
		/*
		 * 버튼 list를 가지고 오는 서비스 호출
		 */
		List<MgmtButtonVO> buttonList = mgmtProgramService.getButtonList(paramMap); 
		
		/*
		 * response body로 뿌려질 결과 list를 object에 담는다.
		 */
		MgmtButtonJsonVO returnMgmtButtonJsonVO = new MgmtButtonJsonVO();
		
		returnMgmtButtonJsonVO.setRows(buttonList);					// grid 값
		returnMgmtButtonJsonVO.setRecords(buttonList.size());		// 전체 레코드(row)수
		
		try {
			MarshallerUtil.marshalling("json", response, returnMgmtButtonJsonVO);	// 공통 marshallerUtil	
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 버튼 리스트의 체크 된 버튼 처리
	 * 2. 처리내용 : 버튼 추가,삭제 처리
	 * </pre>
	 * @Method Name : procButton
	 * @param request
	 * @param response
	 */		
	@RequestMapping("/mgmt/procButtonAjax.do")
	public void procButtonAjax(HttpServletRequest request, HttpServletResponse response){
		
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap = RequestFilterUtil.getReqData(request);				// request XSS 필터링
		
		String procButtonType = StringUtil.nvl(paramMap.get("procButtonType"),"save");	// 버튼 처리 type 기본 저장 값 설정
		String insertPgmNo = StringUtil.nvl(paramMap.get("buttonInsertPgmNo"),"");		// 버튼 등록 프로그램 id
		String pgmNo = StringUtil.nvl(paramMap.get("buttonPgmNo"),"");					// 버튼 프로그램 id
		String btnId = StringUtil.nvl(paramMap.get("buttonId"),"");						// 버튼 id
		String buttonOriId = StringUtil.nvl(paramMap.get("buttonOriId"),"");			// 기존 버튼 id
		String btnUseYn = StringUtil.nvl(paramMap.get("buttonUseYn"),"N");				// 버튼 사용 여부
		String btnNm = StringUtil.nvl(paramMap.get("buttonName"),"");					// 버튼 명
		
		/*
		 * request parameter를 담을 오브젝트
		 */
		MgmtButtonVO paramMgmtButtonVO = new MgmtButtonVO();
		
		/*
		 * 결과 값을 담을 오브젝트
		 */
		MgmtButtonJsonVO returnMgmtButtonJsonVO = new MgmtButtonJsonVO();
		
		/*
		 * 버튼 처리 type이 등록인 경우
		 */
		if("insert".equals(procButtonType)){
			paramMgmtButtonVO.setPgm_no(insertPgmNo);		// 프로그램 no
		    paramMgmtButtonVO.setBtn_id(btnId);				// 버튼 id
		    paramMgmtButtonVO.setBtn_use_yn(btnUseYn);		// 버튼 사용 여부
		    paramMgmtButtonVO.setBtn_nm(btnNm);				// 버튼 명
			
		    /*
		     * 버튼 등록 후 등록 type셋팅
		     */
		    returnMgmtButtonJsonVO.setResultCode(mgmtProgramService.insertButton(paramMgmtButtonVO));
			returnMgmtButtonJsonVO.setMessage("insert");
			
		/*
		 * 버튼 처리 type이 수정인 경우
		 */
		}else if("save".equals(procButtonType)){
			paramMgmtButtonVO.setPgm_no(pgmNo);				// 프로그램 no
		    paramMgmtButtonVO.setBtn_id(btnId);				// 버튼 id
		    paramMgmtButtonVO.setBtn_ori_id(buttonOriId);	// 기존 버튼 id
		    paramMgmtButtonVO.setBtn_use_yn(btnUseYn);		// 버튼 사용 여부
		    paramMgmtButtonVO.setBtn_nm(btnNm);				// 버튼 명
			
		    /*
		     * 버튼 수정 후 수정 type셋팅
		     */
		    returnMgmtButtonJsonVO.setResultCode(mgmtProgramService.updateButton(paramMgmtButtonVO));
			returnMgmtButtonJsonVO.setMessage("save");
		
		/*
		 * 버튼 처리 type이 삭제인 경우
		 */
		}else{
			paramMgmtButtonVO.setPgm_no(pgmNo);				// 프로그램 no
		    paramMgmtButtonVO.setBtn_id(btnId);				// 버튼 id
		    paramMgmtButtonVO.setBtn_ori_id(buttonOriId);	// 기존 버튼 id	
		    paramMgmtButtonVO.setBtn_use_yn(btnUseYn);		// 버튼 사용 여부
		    paramMgmtButtonVO.setBtn_nm(btnNm);				// 버튼 명
			
		    /*
		     * 버튼 삭제 후 삭제 type셋팅
		     */
		    returnMgmtButtonJsonVO.setResultCode(mgmtProgramService.deleteButton(paramMgmtButtonVO));
			returnMgmtButtonJsonVO.setMessage("delete");
		}
		
		try {
			MarshallerUtil.marshalling("json", response, returnMgmtButtonJsonVO);		// 공통 marshallerUtil
		} catch (IOException e) {
			logger.error(e.getMessage());
		}
		
	}
	
}
