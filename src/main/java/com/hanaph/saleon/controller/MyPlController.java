/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hanaph.saleon.business.service.MyPlService;
import com.hanaph.saleon.business.vo.MyPlJsonVO;
import com.hanaph.saleon.business.vo.MyPlVO;
import com.hanaph.saleon.common.utils.MarshallerUtil;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.member.vo.LoginUserVO;

/**
 * <pre>
 * Class Name : MyPlController.java
 * 설명 : MY P/L 관련 Controller
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 4.      jung a Woo
 * </pre>
 * 
 * @version : 
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 12. 4.
 */
@Controller
public class MyPlController {
	
	/**
	 * log
	 */
	private static final Logger logger = Logger.getLogger(MyPlController.class);
	
	/**
	 * MyPlService
	 */
	@Autowired
	private MyPlService myPlService;
	
	/**
	 * <pre>
	 * 1. 개요     : My P/L 화면 호출
	 * 2. 처리내용 : 제품타입정보를 조회하면서 My P/L화면을 호출한다.
	 * </pre>
	 * @Method Name : myPlList
	 * @param request	HttpServletRequest
	 * @return	ModelAndView
	 */		
	@RequestMapping("/business/myPlList.do")
	public ModelAndView myPlList(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("business/myPlList");	//뷰단에 리턴한 ModelAndView객체를 생성하면서 뷰 네임(jsp) 설정
		mav.addObject("itemTypeList",myPlService.getItemTypeList());	//제품 타입 코드를 조회해서 ModelAndView에 저장
		return mav;
	}
	
	
	/**
	 * <pre>
	 * 1. 개요     : P/L 그룹 목록 조회
	 * 2. 처리내용 :	P/L 그룹 목록 조회
	 * </pre>
	 * @Method Name : myplGroupGridList
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException	
	 */		
	@RequestMapping("/business/myplGroupGridList.do")
	public void myplGroupGridList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/* 
		 * 세션에 저장된 사원코드를 가져온다 
		 * */
		HttpSession session = request.getSession();
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String cust_id = StringUtil.nvl(loginUserVO.getEmp_code());								//사원코드
		
		/* 
		 * parameter 값을 받아서 
		 * */
		int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"),"20"));	//페이지 size
		String sidx = StringUtil.nvl(request.getParameter("sidx"),"sort_seq");					//sort할 컬럼명
		String sord = StringUtil.nvl(request.getParameter("sord"),"");							//오름(asc), 내림(desc) 차순
		
		/*
		 * paramMap에 담는다. 
		 */
		Map<String, String> paramMap = new HashMap<String, String>();		//parameter를 담는 Map 객체 생성
		paramMap.put("as_sawon_id", cust_id);
		paramMap.put("sidx", sidx);
		paramMap.put("sord", sord);
		
		/*
		 * P/L 그룹 목록 조회한 후 json형태로 리턴
		 */
		List<MyPlVO> myPlList = myPlService.getMyplGroupGridList(paramMap);	//P/L 그룹 목록 조회
		
		int records = myPlList.size();										//row 총갯수
		int total = (int)Math.ceil((double)records/(double)perPageRow);		//page 수 계산
		
		MyPlJsonVO myPlJsonVO = new MyPlJsonVO();							//json형태로 만들기 위한 VO를 생성					
		myPlJsonVO.setTotal(total);											
		myPlJsonVO.setRecords(records);										
		myPlJsonVO.setRows(myPlList);										
		
		MarshallerUtil.marshalling("json", response, myPlJsonVO);			//VO형태를 json형태로 마샬
	}
	
	
	/**
	 * <pre>
	 * 1. 개요     : P/L 그룹에 등록된 제품 목록 조회
	 * 2. 처리내용 :	P/L 그룹에 등록된 제품 목록 조회
	 * </pre>
	 * @Method Name : myPlItemGridList
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/myPlItemGridList.do")
	public void myGList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/* 
		 * 세션에 저장된 사원코드를 가져온다 
		 * */
		HttpSession session = request.getSession();
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String sawon_id = StringUtil.nvl(loginUserVO.getEmp_code());						//사원코드
		
		/* 
		 * parameter 값을 받아서 paramMap에 담는다. 
		 * */
		Map<String, String> paramMap = new HashMap<String, String>();						//parameter를 담는 Map 객체 생성
		paramMap.put("as_sawon_id", sawon_id);												//사원코드
		paramMap.put("as_plgrp_no", StringUtil.nvl(request.getParameter("plgrp_no"),""));	//P/L group no
		paramMap.put("sidx", StringUtil.nvl(request.getParameter("sidx"),"sort_seq"));		//sort할 컬럼명
		paramMap.put("sord", StringUtil.nvl(request.getParameter("sord"),""));				//오름(asc), 내림(desc) 차순
		
		/* 
		 * P/L 그룹에 해당하는 제품 목록 조회 json으로 변환하여 뷰단에 리턴한다.  
		 * */
		List<MyPlVO> myPlList = myPlService.getMyPlItemList(paramMap);						//P/L Group Item List
		int records = myPlList.size();														//list 총 count수
		MyPlJsonVO myPlJsonVO = new MyPlJsonVO();											//json 변환용 VO 생성 
		myPlJsonVO.setRecords(records);														//row 총갯수
		myPlJsonVO.setRows(myPlList);														//grid 값
		
		MarshallerUtil.marshalling("json", response, myPlJsonVO);							//VO객체를 json형태로 변환
	}
	
	
	/**
	 * <pre>
	 * 1. 개요     : 현재 선택된 P/L그룹에 등록되지 않은 P/L 제품 목록 조회
	 * 2. 처리내용 :	현재 선택된 P/L그룹에 등록되지 않은 P/L 제품 목록 조회
	 * </pre>
	 * @Method Name : itemGridList
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/itemGridList.do")
	public void itemGridList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
		HttpSession session = request.getSession();
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String sawon_id = StringUtil.nvl(loginUserVO.getEmp_code());					//sawon_id
		
		/*
		 * parameter
		 */
		String plgrp_no = StringUtil.nvl(request.getParameter("plgrp_no"),"");			//P/L group no
		String item_nm = StringUtil.nvl(request.getParameter("item_nm"),"");			//제품명
		item_nm = URLDecoder.decode(item_nm, "utf-8");
		String item_kind = StringUtil.nvl(request.getParameter("item_kind"),"");		//제품종료
		String sidx = StringUtil.nvl(request.getParameter("sidx"),"");					//sort할 컬럼명
		String sord = StringUtil.nvl(request.getParameter("sord"),"");					//오름(asc), 내림(desc) 차순
		
		/*
		 * parameter를 map에 setting
		 */
		Map<String, String> paramMap = new HashMap<String, String>();					//parameter를 담은 Map객체 생성
		paramMap.put("as_sawon_id", sawon_id);
		paramMap.put("as_plgrp_no", plgrp_no);
		paramMap.put("as_item_nm", item_nm);
		paramMap.put("as_item_kind", item_kind);
		paramMap.put("sidx", sidx);
		paramMap.put("sord", sord);
		
		/*
		 * 현재 선택된 P/L그룹에 등록되지 않은 P/L 제품 목록 조회한 후 json형태로 변환해서 리턴
		 */
		List<MyPlVO> myPlList = myPlService.getItemList(paramMap);						//현재 선택된 P/L그룹에 등록되지 않은 P/L 제품 목록 조회
		MyPlJsonVO myPlJsonVO = new MyPlJsonVO();										// json으로 변환하기 위한 VO 생성
		myPlJsonVO.setRecords(myPlList.size());											//row 총갯수
		myPlJsonVO.setRows(myPlList);													//grid 값
		
		MarshallerUtil.marshalling("json", response, myPlJsonVO);						//VO형태를 json형태로 마샬
	}
	
	
	
	/**
	 * <pre>
	 * 1. 개요     : P/L그룹 관린 팝업 호출
	 * 2. 처리내용 :	P/L그룹 관린 팝업 호출
	 * </pre>
	 * @Method Name : myplGroupPopup
	 * @param request	HttpServletRequest
	 * @return	ModelAndView
	 */		
	@RequestMapping("/business/common/myplGroupPopup.do")
	public ModelAndView myplGroupPopup(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("business/common/myplGroupPopup");
		return mav;
	}
	
	
	
	/**
	 * <pre>
	 * 1. 개요     : P/L그룹 추가
	 * 2. 처리내용 :	P/L그룹 추가
	 * </pre>
	 * @Method Name : insertPlGroupAjax
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/insertPlGroupAjax.do")
	public void insertPlGroupAjax(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
		HttpSession session = request.getSession();
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String gs_empCode = StringUtil.nvl(loginUserVO.getEmp_code());						//sawon_id
		
		/*
		 * parameter
		 */
		String plgrp_nm = StringUtil.nvl(request.getParameter("pl_nm"),"");					//P/L그룹명
		String comments = StringUtil.nvl(request.getParameter("pl_comments"),"");			//설명
		String sort_seq = StringUtil.nvl(request.getParameter("pl_sort_seq"),"");			//정렬순서
		
		/*
		 * parameter를 map에 setting
		 */
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("gs_empCode", gs_empCode);
		paramMap.put("plgrp_nm", plgrp_nm);
		paramMap.put("comments", comments);
		paramMap.put("sort_seq", sort_seq);
		
		/*
		 *	P/L그룹 인서트하고 그 결과를 json으로 리턴
		 */
		boolean result = false;										//인서트 성공 여부
		MyPlVO returnVO = new MyPlVO();								//json으로 변환하기 위한 VO 생성
		result = myPlService.insertPlGroup(paramMap);				//P/L insert
		if(result){
			returnVO.setResult("Y");								//Y이면 정상 update
		}else{
			returnVO.setResult("N");								//N이면 update error
		}
			
		MarshallerUtil.marshalling("json", response, returnVO);		//VO형태를 json형태로 마샬
	}
	
	
	/**
	 * <pre>
	 * 1. 개요     : P/L그룹 정보 수정
	 * 2. 처리내용 : P/L그룹 정보 수정
	 * </pre>
	 * @Method Name : updateOrderAjax
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/updatePlGroup.do")
	public void updateOrderAjax(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/*
		 * parameter
		 */
		String[] plgrp_no = request.getParameterValues("plgrp_no");		//P/L그룹 코드
		String[] plgrp_nm = request.getParameterValues("plgrp_nm");		//P/L그룹 이름
		String[] comments = request.getParameterValues("comments");		//P/L그룹 설명
		String[] sort_seq = request.getParameterValues("sort_seq");		//P/L그룹 정렬 순서
		
		/*
		 * parameter를 map에 setting
		 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("plgrp_no", plgrp_no);
		paramMap.put("plgrp_nm", plgrp_nm);
		paramMap.put("comments", comments);
		paramMap.put("sort_seq", sort_seq);
		
		/*
		 *	P/L그룹 정보 수정 후 결과를 json 형태로 리턴
		 */
		boolean result = false;										//성공 여부
		MyPlVO returnVO = new MyPlVO(); 							//json변환용 VO 생성
		try{
			result = myPlService.updatePlGroup(paramMap);		 	// P/L그룹 정보 수정
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		
		if(result){
			returnVO.setResult("Y");								//Y이면 정상 update
		}else{
			returnVO.setResult("N");								//N이면 update error
		}
		
		MarshallerUtil.marshalling("json", response, returnVO);		//VO형태를 json형태로 마샬
		
	}
	
	
	/**
	 * <pre>
	 * 1. 개요     : P/L그룹 삭제
	 * 2. 처리내용 : P/L그룹 삭제
	 * </pre>
	 * @Method Name : deletePlGroup
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/deletePlGroup.do")
	public void deletePlGroup(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
		HttpSession session = request.getSession();
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String sawon_id = StringUtil.nvl(loginUserVO.getEmp_code());						//sawon_id
				
		/*
		 * parameter
		 */
		String plgrp_no = StringUtil.nvl(request.getParameter("plgrp_no"),"");				//P/L 그룹 코드
		
		/*
		 * parameter를 map에 setting
		 */
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("sawon_id", sawon_id);
		paramMap.put("plgrp_no", plgrp_no);
		
		/*
		 * P/L그룹 삭제후 그 결과를 json으로 리턴 
		 */
		boolean result = false;										//삭제 결과
		MyPlVO retrunVO = new MyPlVO(); 							//json변환용 VO 생성
		try{
			result = myPlService.deletePlGroup(paramMap);	
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		if(result){
			retrunVO.setResult("Y");								//Y이면 정상 update
		}else{
			retrunVO.setResult("N");								//N이면 update error
		}
		
		MarshallerUtil.marshalling("json", response, retrunVO);		//VO형태를 json형태로 마샬
		
	}
	
	
	
	/**
	 * <pre>
	 * 1. 개요     : P/L그룹에 제품 목록 저장 
	 * 2. 처리내용 : P/L그룹에 제품 목록 저장 
	 * </pre>
	 * @Method Name : insertMyPlList
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/insertMyPlList.do")
	public void insertMyPlList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/*
		 * 세션생성 세션에서 emp_code를 가져온다.
		 */
		HttpSession session = request.getSession();
		LoginUserVO loginUserVO = (LoginUserVO) session.getAttribute("onlineUser");
		String sawon_id = StringUtil.nvl(loginUserVO.getEmp_code());						// sawon_id
		
		/*
		 * parameter
		 */
		String plgrp_no = StringUtil.nvl(request.getParameter("plgrp_no"),"");				// P/L그룹 코드
		String[] item_id = request.getParameterValues("item_id");							// 제품 코드
		String[] sort_seq = request.getParameterValues("sort_seq");							// 정렬 순서
		
		/*
		 * parameter를 map에 setting
		 */
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("sawon_id", sawon_id);
		paramMap.put("plgrp_no", plgrp_no);
		paramMap.put("item_id", item_id);
		paramMap.put("sort_seq", sort_seq);
		
		/*
		 *	P/L그룹에 선택된 제품들을 등록한 후 그 결과를 json으로 리턴한다.
		 */
		boolean result = false;		//등록 성공 여부
		MyPlVO retrunVO = new MyPlVO(); 
		try{
			result = myPlService.insertMyPlList(paramMap);	
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		if(result){
			retrunVO.setResult("Y");	//Y이면 정상 update
		}else{
			retrunVO.setResult("N");	//N이면 update error
		}
		
		MarshallerUtil.marshalling("json", response, retrunVO);		// VO형태를 json형태로 마샬
		
	}
	
	
	
	/**
	 * <pre>
	 * 1. 개요     : P/L 제품 리스트 팝업 호출
	 * 2. 처리내용 :	 P/L 제품 리스트 팝업 호출
	 * </pre>
	 * @Method Name : myPlItemRegForm
	 * @param request	HttpServletRequest
	 * @return	ModelAndView
	 */		
	@RequestMapping("/business/common/myPlItemRegPopup.do")
	public ModelAndView myPlItemRegForm(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("business/common/myPlItemRegPopup");
		mav.addObject("itemTypeList",myPlService.getItemTypeList());	// 제품 타입 공통 코드 조회
		return mav;
	}
	
	
	
	/**
	 * <pre>
	 * 1. 개요     : P/L용 제품 리스트 조회
	 * 2. 처리내용 : P/L용 제품 리스트 조회
	 * </pre>
	 * @Method Name : plItemListGridList
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/plItemListGridList.do")
	public void plItemListGridList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/*
		 * parameter
		 */
		int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"),"1"));											//현재 page
		int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"),"20"));									//페이지 size
		String sidx = StringUtil.nvl(request.getParameter("sidx"),"");															//sort할 컬럼명
		String sord = StringUtil.nvl(request.getParameter("sord"),"");															//오름(asc), 내림(desc) 차순
		String as_item_kind1 = URLDecoder.decode(StringUtil.nvl(request.getParameter("search_item_kind1"),""), "UTF-8");		//구분1
		String as_item_kind2 = StringUtil.nvl(request.getParameter("search_item_kind2"),"");									//구분2
		String as_item_name = URLDecoder.decode(StringUtil.nvl(request.getParameter("search_item_nm"),""), "UTF-8");			//제품명
		
		/*
		 * parameter를 map에 setting
		 */
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("as_item_kind1", as_item_kind1);
		paramMap.put("as_item_kind2", as_item_kind2);
		paramMap.put("as_item_name", as_item_name);
		paramMap.put("sidx", sidx);
		paramMap.put("sord", sord);
		paramMap.put("page", String.valueOf(page));
		paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*
		 * 페이징 list
		 */
		List<MyPlVO> plItemList = myPlService.getPlItemList(paramMap);		// 목록 조회
		int records = myPlService.getplItemCnt(paramMap).getCnt();			// 전체 목록 수
		int total = (int)Math.ceil((double)records/(double)perPageRow);		// 전체 페이지수
		
		//paging parameter 
		MyPlJsonVO returnVO = new MyPlJsonVO();
		returnVO.setTotal(total);			//page 수
		returnVO.setRecords(records);		//row 총갯수
		returnVO.setPage(page);				//현재 page
		returnVO.setRows(plItemList);		//grid 값
		
		//VO형태를 json형태로 마샬
		MarshallerUtil.marshalling("json", response, returnVO);
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 제품 찾기 팝업 호출
	 * 2. 처리내용 : 제품 찾기 팝업 호출
	 * </pre>
	 * @Method Name : itemListPopup
	 * @param request	HttpServletRequest
	 * @return
	 */		
	@RequestMapping("/business/common/itemListPopup.do")
	public ModelAndView itemListPopup(HttpServletRequest request){
		ModelAndView mav = new ModelAndView("business/common/itemListPopup");
		return mav;
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 모든 제품 목록 조회
	 * 2. 처리내용 : 모든 제품 목록 조회
	 * </pre>
	 * @Method Name : itemAllGridList
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/order/common/itemAllGridList.do")
	public void itemAllGridList(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/*
		 * parameter
		 */
		int page = Integer.parseInt(StringUtil.nvl(request.getParameter("page"),"1"));									//현재 page
		int perPageRow = Integer.parseInt(StringUtil.nvl(request.getParameter("rows"),"20"));							//페이지 size
		String sidx = StringUtil.nvl(request.getParameter("sidx"),"");													//sort할 컬럼명
		String sord = StringUtil.nvl(request.getParameter("sord"),"");													//오름(asc), 내림(desc) 차순
		String searchKeyword = URLDecoder.decode(StringUtil.nvl(request.getParameter("searchKeyword")), "UTF-8");		//키워드
		
		/*
		 * parameter를 map에 setting
		 */
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchKeyword", searchKeyword);
		paramMap.put("sidx", sidx);
		paramMap.put("sord", sord);
		paramMap.put("page", String.valueOf(page));
		paramMap.put("perPageRow", String.valueOf(perPageRow));
		
		/*
		 * 모든 제품 목록 조회
		 */
		List<MyPlVO> myPlVO = myPlService.getItemAllGridList(paramMap);		// 모든 제품 목록 조회
		int records = myPlService.getItemAllCnt(paramMap).getCnt();			// 전체 제품 수
		int total = (int)Math.ceil((double)records/(double)perPageRow);		// 전체 페이지 수
		
		/*
		 * page의 리턴시킬 값
		 */
		MyPlJsonVO returnVO = new MyPlJsonVO();
		returnVO.setTotal(total);			//page 수
		returnVO.setPage(page);				//현재 page
		returnVO.setRecords(records);		//전체 제품 수
		returnVO.setRows(myPlVO);			//실제 데이터 셋팅				
		
		
		/*
		 * VO형태를 json형태로 마샬
		 */
		MarshallerUtil.marshalling("json", response, returnVO);
	}
	
	
	/**
	 * <pre>
	 * 1. 개요     : 제품 상세 정보 조회
	 * 2. 처리내용 : 제품 상세 정보 조회
	 * </pre>
	 * @Method Name : plItemInfoAjax
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/plItemInfoAjax.do")
	public void plItemInfoAjax(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/*
		 * parameter를 map에 setting
		 */
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("item_id", StringUtil.nvl(request.getParameter("item_id"),""));		//제품명
		
		/*
		 * 제품 상세 정보 조회
		 */
		MyPlVO returnVO = myPlService.getPlItemInfo(paramMap);
		
		/*
		 * VO형태를 json형태로 마샬
		 */
		MarshallerUtil.marshalling("json", response, returnVO);
		
	}
	
	
	/**
	 * <pre>
	 * 1. 개요     : P/L용 제품 등록
	 * 2. 처리내용 : P/L용 제품 등록
	 * </pre>
	 * @Method Name : insertPlItem
	 * @param request	HttpServletRequest	HttpServletRequest
	 * @param response	HttpServletResponse	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/insertPlItem.do")
	public void insertPlItem(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/*
		 * parameter
		 */
		String item_id = StringUtil.nvl(request.getParameter("item_id"),"");						//제품코드
		String item_nm = StringUtil.nvl(request.getParameter("item_id_name"),"");					//제품명
		String item_kind1 = StringUtil.nvl(request.getParameter("item_kind1"),"");					//제품분류1
		String item_kind2 = StringUtil.nvl(request.getParameter("item_kind2"),"");					//제품분류2
		String item_kd_no = StringUtil.nvl(request.getParameter("item_kd_no"),"");					//제품 KD
		String item_out_danga = StringUtil.nvl(request.getParameter("item_out_danga"),"");			//보험약가
		String item_main_source = StringUtil.nvl(request.getParameter("item_main_source"),"");		//주성분, 함량
		String item_pojang_unit = StringUtil.nvl(request.getParameter("item_pojang_unit"),"");		//포장단위
		String item_effect = StringUtil.nvl(request.getParameter("item_effect"),"");				//적응증
		String item_use_does = StringUtil.nvl(request.getParameter("item_use_does"),"");			//용법, 용량
		String fontsize_effect = StringUtil.nvl(request.getParameter("fontsize_effect"),"");		//글크기_적응증
		String fontsize_use_does = StringUtil.nvl(request.getParameter("fontsize_use_does"),"");	//글크기_용법용량
		String use_yn = StringUtil.nvl(request.getParameter("use_yn"),"");							//사용여부
		
		/*
		 * parameter를 vo에 setting
		 */
		MyPlVO myPlVO = new MyPlVO();
		myPlVO.setItem_id(item_id);
		myPlVO.setItem_nm(item_nm);
		myPlVO.setItem_kind1(item_kind1);
		myPlVO.setItem_kind2(item_kind2);
		myPlVO.setItem_kd_no(item_kd_no);
		myPlVO.setItem_out_danga(item_out_danga);
		myPlVO.setItem_main_source(item_main_source);
		myPlVO.setItem_pojang_unit(item_pojang_unit);
		myPlVO.setItem_effect(item_effect);
		myPlVO.setItem_use_does(item_use_does);
		myPlVO.setFontsize_effect(fontsize_effect);
		myPlVO.setFontsize_use_does(fontsize_use_does);
		myPlVO.setUse_yn(use_yn);
		
		/*
		 * P/L용 제품 등록
		 */
		boolean result = false;		//등록 성공 여부
		try{
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("item_id", item_id);
			if(myPlService.getItemOverlapCheck(paramMap).getCnt() == 0){	//선택한 제품의 P/L용 제품 기등록 여부 조회해서 없을 경우
				result = myPlService.insertPlItem(myPlVO);		// P/L용 제품 등록
			}
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		if(result){
			myPlVO.setResult("Y");	//Y이면 정상 update
		}else{
			myPlVO.setResult("N");	//N이면 update error
		}
		
		/*
		 * VO형태를 json형태로 마샬
		 */
		MarshallerUtil.marshalling("json", response, myPlVO);
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : P/L용 제품 수정
	 * 2. 처리내용 :	P/L용 제품 수정
	 * </pre>
	 * @Method Name : updatePlItem
	 * @param request	HttpServletRequest	
	 * @param response	HttpServletResponse	
	 * @throws IOException
	 */		
	@RequestMapping("/business/updatePlItem.do")
	public void updatePlItem(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/*
		 * 파라메터 셋팅
		 */
		String item_id = StringUtil.nvl(request.getParameter("item_id"),"");						//제품코드
		String orgn_item_id = StringUtil.nvl(request.getParameter("orgn_item_id"),"");				//현재 선택된 제품코드
		String item_nm = StringUtil.nvl(request.getParameter("item_id_name"),"");					//제품명
		String item_kind1 = StringUtil.nvl(request.getParameter("item_kind1"),"");					//제품분류1
		String item_kind2 = StringUtil.nvl(request.getParameter("item_kind2"),"");					//제품분류2
		String item_kd_no = StringUtil.nvl(request.getParameter("item_kd_no"),"");					//제품 KD
		String item_out_danga = StringUtil.nvl(request.getParameter("item_out_danga"),"");			//보험약가
		String item_main_source = StringUtil.nvl(request.getParameter("item_main_source"),"");		//주성분, 함량
		String item_pojang_unit = StringUtil.nvl(request.getParameter("item_pojang_unit"),"");		//포장단위
		String item_effect = StringUtil.nvl(request.getParameter("item_effect"),"");				//적응증
		String item_use_does = StringUtil.nvl(request.getParameter("item_use_does"),"");			//용법, 용량
		String fontsize_effect = StringUtil.nvl(request.getParameter("fontsize_effect"),"");		//글크기_적응증
		String fontsize_use_does = StringUtil.nvl(request.getParameter("fontsize_use_does"),"");	//글크기_용법용량
		String use_yn = StringUtil.nvl(request.getParameter("use_yn"),"");							//사용여부
		
		/*  
		 * parameter를 vo에 setting
		 */
		MyPlVO myPlVO = new MyPlVO();
		myPlVO.setItem_id(item_id);
		myPlVO.setItem_nm(item_nm);
		myPlVO.setOrgn_item_id(orgn_item_id);
		myPlVO.setItem_kind1(item_kind1);
		myPlVO.setItem_kind2(item_kind2);
		myPlVO.setItem_kd_no(item_kd_no);
		myPlVO.setItem_out_danga(item_out_danga);
		myPlVO.setItem_main_source(item_main_source);
		myPlVO.setItem_pojang_unit(item_pojang_unit);
		myPlVO.setItem_effect(item_effect);
		myPlVO.setItem_use_does(item_use_does);
		myPlVO.setFontsize_effect(fontsize_effect);
		myPlVO.setFontsize_use_does(fontsize_use_does);
		myPlVO.setUse_yn(use_yn);
		
		/*
		 * P/L용 제품 수정
		 */
		boolean result = false;		//수정 성공 여부
		try{
			Map<String, String> paramMap = new HashMap<String, String>();
			paramMap.put("item_id", item_id);
			if(myPlService.getItemOverlapCheck(paramMap).getCnt() == 0){	//선택한 제품의 P/L용 제품 기등록 여부 조회해서 없을 경우
				result = myPlService.updatePlItem(myPlVO);	// P/L용 제품 수정
			}
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		if(result){
			myPlVO.setResult("Y");	//Y이면 정상 update
		}else{
			myPlVO.setResult("N");	//N이면 update error
		}
		
		/*
		 * VO형태를 json형태로 마샬
		 */
		MarshallerUtil.marshalling("json", response, myPlVO);
		
	}
	
	
	/**
	 * <pre>
	 * 1. 개요     : P/L용 제품 삭제
	 * 2. 처리내용 : P/L용 제품 삭제
	 * </pre>
	 * @Method Name : deletePlItem
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/deletePlItem.do")
	public void deletePlItem(HttpServletRequest request, HttpServletResponse response) throws IOException{
		/*
		 * parameter를 vo에 setting
		 */
		MyPlVO myPlVO = new MyPlVO();
		myPlVO.setItem_id(StringUtil.nvl(request.getParameter("item_id"),""));		//제품코드
		
		/*
		 * P/L용 제품 삭제
		 */
		boolean result = false;		//삭제 성공 여부
		try{
			result = myPlService.deletePlItem(myPlVO);		// P/L용 제품 삭제
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		if(result){
			myPlVO.setResult("Y");	//Y이면 정상 update
		}else{
			myPlVO.setResult("N");	//N이면 update error
		}
		
		/*
		 * VO형태를 json형태로 마샬
		 */
		MarshallerUtil.marshalling("json", response, myPlVO);
		
	}
	
	/**
	 * <pre>
	 * 1. 개요     : 선택한 제품의 P/L용 제품 기등록 여부
	 * 2. 처리내용 : 선택한 제품의 P/L용 제품 기등록 여부
	 * </pre>
	 * @Method Name : itemOverlapCheckAjax
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/itemOverlapCheckAjax.do")
	public void itemOverlapCheckAjax(HttpServletRequest request, HttpServletResponse response) throws IOException{
		
		/*
		 * parameter
		 */
		String item_id = StringUtil.nvl(request.getParameter("item_id"),"");						//제품코드
		
		/*
		 * parameter를 map에 setting
		 */
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("item_id", item_id);
		
		/*
		 * 선택한 제품의 P/L용 제품 기등록 여부
		 */
		MyPlVO returnVO = myPlService.getItemOverlapCheck(paramMap);	//선택한 제품의 P/L용 제품 기등록 여부 조회
		if(returnVO.getCnt()!=0){
			returnVO.setResult("N");	//선택한 제품의 P/L용 제품 기등록됨
		}else{
			returnVO.setResult("Y");	//선택한 제품의 P/L용 제품 기등록되지 않음
		}
		
		/*
		 * VO형태를 json형태로 마샬
		 */
		MarshallerUtil.marshalling("json", response, returnVO);
		
	}
	
	
	/**
	 * <pre>
	 * 1. 개요     : 제품 이미지 보여주기
	 * 2. 처리내용 : 제품 이미지 보여주기
	 * </pre>
	 * @Method Name : getPlItemPhoto
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 * @throws SerialException
	 * @throws SQLException
	 */		
	@RequestMapping("/business/getPlItemPhoto.do")
	public void getPlItemPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException, SerialException, SQLException {
		
		/*
		 * 객체생성
		 */
		Map<String, String> paramMap = new HashMap<String, String>(); 
		OutputStream outputStream;		// 이미지를 뿌려줄 OutputStream 생성
		
		/*
		 * parameter
		 */
		String item_id = StringUtil.nvl(request.getParameter("item_id"),"");						//제품코드
		
		/*
		 *	 parameter를 map에 setting
		 */
		paramMap.put("item_id", item_id);
		
		/*
		 * BLOB 컬럼에 저장되어 있는 사진 data를 조회해서 사진 파일로 생성한 후 
		 * 응답(response)한다.
		 */
		MyPlVO myPlVO = myPlService.getPlItemPhoto(paramMap);		//사진 데이터 조회
		if(myPlVO.getItem_photo() != null){
			response.setHeader("Content-Disposition", "inline;filename=\"" + item_id + "\""); 		//응답 헤더값중 Content-Disposition에 파일명을 제품코드로 설정
			outputStream = response.getOutputStream(); 												//응답 객체에서 OutputStream을 로드한 후
			response.setContentType("image/jpeg"); 													//응답 content type을 image/jpeg로 설정
	
			SerialBlob blob = new SerialBlob(myPlVO.getItem_photo()); 								//BLOB 컬럼 타입에 대응되는 자바 Object
			IOUtils.copy(blob.getBinaryStream(), outputStream); 									//IOUtils.copy 메소드를 이용해서 outputStream에 BLOB의 data를 복사한다.
	
			outputStream.flush(); 																	//outputStream 데이터를 출력				
			outputStream.close();																	//outputStream 객체 닫기
		}
	}
	
	
	
	
	/**
	 * <pre>
	 * 1. 개요     : 제품 이미지 업로드
	 * 2. 처리내용 :	 제품 이미지 업로드
	 * </pre>
	 * @Method Name : itemUploadPhoto
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/itemUploadPhoto.do")
	public void itemUploadPhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		MyPlVO myPlVO = new MyPlVO();	//객체 생성
		
		MultipartHttpServletRequest multipartHttpServletRequest = (MultipartHttpServletRequest) request;	//parameter
		MultipartFile multipartFile = multipartHttpServletRequest.getFile("itemPhoto");						//제품이미지
		String item_id = StringUtil.nvl(request.getParameter("item_id"),"");								//제품코드

        int result = 0;		//업로드 결과
        
        logger.debug("multipartFile.item_id() : " + item_id);
        logger.debug("multipartFile.getContentType() : " + multipartFile.getContentType());
        logger.debug("multipartFile.getName() : " + multipartFile.getName());
        logger.debug("multipartFile.getOriginalFilename() : " + multipartFile.getOriginalFilename());
        logger.debug("multipartFile.getSize() : " + multipartFile.getSize());

        
        if(multipartFile.getSize()<307200){	//300kb이상 파일은 업로드 못하게
        	myPlVO.setItem_id(item_id);
            myPlVO.setItem_photo(multipartFile.getBytes());		//업로드된 파일의 사이즈
            
            try{
    			result = myPlService.updateItemPhoto(myPlVO);	//파일 데이터 DB에 저장
    		}catch(Exception ex){
    			logger.error(ex.getMessage());
    		}	
        }else{
        	result=3;		// 300kb이상은 에러코드 반환
        }
		
		/*
		 * VO형태를 json형태로 마샬
		 */
		MarshallerUtil.marshalling("txt", response, "<script>parent.callbackPhoto('"+result+"');</script>");
	}
	
	
	/**
	 * <pre>
	 * 1. 개요     : 제품 이미지 삭제
	 * 2. 처리내용 : 제품 이미지 삭제
	 * </pre>
	 * @Method Name : itemDeletePhoto
	 * @param request	HttpServletRequest
	 * @param response	HttpServletResponse
	 * @throws IOException
	 */		
	@RequestMapping("/business/itemDeletePhoto.do")
	public void itemDeletePhoto(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		/*
		 * parameter
		 */
		MyPlVO myPlVO = new MyPlVO();	//객체 생성
		myPlVO.setItem_id(StringUtil.nvl(request.getParameter("item_id"),""));		//제품 코드
		
		/*
		 *	제품 이미지 삭제 
		 */
        boolean result = false;		//삭제 성공 여부
        try{
			result = myPlService.deleteItemPhoto(myPlVO);
		}catch(Exception ex){
			logger.error(ex.getMessage());
		}
		if(result){
			myPlVO.setResult("Y");	//Y이면 정상 update
		}else{
			myPlVO.setResult("N");	//N이면 update error
		}
		
		/*
		 * VO형태를 json형태로 마샬
		 */
		MarshallerUtil.marshalling("json", response, myPlVO);
	}
}
