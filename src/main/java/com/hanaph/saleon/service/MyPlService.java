/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.MyPlVO;

/**
 * <pre>
 * Class Name : MyPlService.java
 * 설명 :  MY P/L 관련 Service Interface.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 8.      jung a Woo          
 * </pre>
 * 
 * @version : 
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 12. 8.
 */
public interface MyPlService {

	/**
	 * <pre>
	 * 1. 개요     : P/L 그룹 목록 조회
	 * 2. 처리내용 :	P/L 그룹 목록 조회
	 * </pre>
	 * @Method Name : getMyplGroupGridList
	 * @param paramMap	Map<String, String>
	 * @return	List<MyPlVO>
	 */		
	public List<MyPlVO> getMyplGroupGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : P/L그룹 추가
	 * 2. 처리내용 :	먼저 P/L그룹 코드를 생성한 뒤에 P/L그룹 정보를 추가한다.
	 * </pre>
	 * @Method Name : insertPlGroup
	 * @param paramMap	Map<String, String>
	 * @return	boolean
	 */		
	public boolean insertPlGroup(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : P/L그룹 정보 수정
	 * 2. 처리내용 :	P/L그룹들의 정보를 array로 받은 뒤 루프를 돌면서 수정한다.
	 * </pre>
	 * @Method Name : updatePlGroup
	 * @param paramMap	Map<String, Object>
	 * @return	boolean
	 */		
	public boolean updatePlGroup(Map<String, Object> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : P/L그룹 삭제
	 * 2. 처리내용 :	P/L그룹 삭제한 후 삭제한 P/L그룹에 등록되어 있는 P/L용 제품들도 삭제한다.
	 * </pre>
	 * @Method Name : deletePlGroup
	 * @param paramMap	Map<String, String> 
	 * @return	boolean
	 */		
	public boolean deletePlGroup(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 제품 타입 코드 조회
	 * 2. 처리내용 :	제품 타입 코드 조회
	 * </pre>
	 * @Method Name : getItemTypeList
	 * @return	List<MyPlVO>
	 */		
	public List<MyPlVO> getItemTypeList();

	/**
	 * <pre>
	 * 1. 개요     : P/L 그룹에 등록된 제품 목록 조회
	 * 2. 처리내용 :	P/L 그룹에 등록된 제품 목록 조회
	 * </pre>
	 * @Method Name : getMyPlItemList
	 * @param paramMap	Map<String, String>
	 * @return	List<MyPlVO>
	 */		
	public List<MyPlVO> getMyPlItemList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 현재 선택된 P/L그룹에 등록되지 않은 P/L 제품 목록 조회
	 * 2. 처리내용 :	현재 선택된 P/L그룹에 등록되지 않은 P/L 제품 목록 조회
	 * </pre>
	 * @Method Name : getItemList
	 * @param paramMap	Map<String, String>
	 * @return	List<MyPlVO>
	 */		
	public List<MyPlVO> getItemList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 	P/L그룹에 선택된 제품들을 등록
	 * 2. 처리내용 :	기존에 등록된 제품 목록 삭제, 제품정보가 있을 경우 제품 갯수만큼 루프를 돌면서 인서트
	 * 
	 * </pre>
	 * @Method Name : insertMyPlList
	 * @param paramMap	Map<String, Object>
	 * @return	boolean
	 */		
	public boolean insertMyPlList(Map<String, Object> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : P/L용 제품 리스트 조회
	 * 2. 처리내용 :	P/L용 제품 리스트 조회
	 * </pre>
	 * @Method Name : getPlItemList
	 * @param paramMap	Map<String, String>
	 * @return	List<MyPlVO>
	 */		
	public List<MyPlVO> getPlItemList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : P/L용 제품 리스트 전체 row수 조회
	 * 2. 처리내용 :	P/L용 제품 리스트 전체 row수 조회
	 * </pre>
	 * @Method Name : getplItemCnt
	 * @param paramMap	Map<String, String>
	 * @return	MyPlVO
	 */		
	public MyPlVO getplItemCnt(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 모든 제품 목록 조회
	 * 2. 처리내용 :	모든 제품 목록 조회
	 * </pre>
	 * @Method Name : getItemAllGridList
	 * @param paramMap	Map<String, String>
	 * @return	List<MyPlVO>
	 */		
	public List<MyPlVO> getItemAllGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 모든 제품 목록 전체 건수
	 * 2. 처리내용 : 모든 제품 목록 전체 건수
	 * </pre>
	 * @Method Name : getItemAllCnt
	 * @param paramMap	Map<String, String>
	 * @return	MyPlVO
	 */		
	public MyPlVO getItemAllCnt(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 제품 상세 정보 조회
	 * 2. 처리내용 :	제품 상세 정보 조회
	 * </pre>
	 * @Method Name : getPlItemInfo
	 * @param paramMap	Map<String, String>
	 * @return	MyPlVO
	 */		
	public MyPlVO getPlItemInfo(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 선택한 제품의 P/L용 제품 기등록 여부
	 * 2. 처리내용 :	선택한 제품의 P/L용 제품 기등록 여부
	 * </pre>
	 * @Method Name : getItemOverlapCheck
	 * @param paramMap	Map<String, String>
	 * @return	MyPlVO
	 */		
	public MyPlVO getItemOverlapCheck(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 	P/L용 제품 등록
	 * 2. 처리내용 :	P/L용 제품 등록
	 * </pre>
	 * @Method Name : insertPlItem
	 * @param myPlVO	MyPlVO
	 * @return	boolean
	 */		
	public boolean insertPlItem(MyPlVO myPlVO);

	/**
	 * <pre>
	 * 1. 개요     : P/L용 제품 삭제
	 * 2. 처리내용 :	P/L용 제품 삭제
	 * </pre>
	 * @Method Name : deletePlItem
	 * @param myPlVO	MyPlVO
	 * @return	boolean
	 */		
	public boolean deletePlItem(MyPlVO myPlVO);

	/**
	 * <pre>
	 * 1. 개요     : P/L용 제품 수정
	 * 2. 처리내용 :	P/L용 제품 수정
	 * </pre>
	 * @Method Name : updatePlItem
	 * @param myPlVO	MyPlVO
	 * @return	boolean
	 */		
	public boolean updatePlItem(MyPlVO myPlVO);

	/**
	 * <pre>
	 * 1. 개요     : 제품 이미지 BLOB 데이터 조회
	 * 2. 처리내용 : 제품 이미지 BLOB 데이터 조회
	 * </pre>
	 * @Method Name : getPlItemPhoto
	 * @param paramMap	Map<String, String>
	 * @return	MyPlVO
	 */		
	public MyPlVO getPlItemPhoto(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 업로드 제품 이미지를 BLOB 데이터로 update
	 * 2. 처리내용 : 업로드 제품 이미지를 BLOB 데이터로 update
	 * </pre>
	 * @Method Name : updateItemPhoto
	 * @param myPlVO	MyPlVO	
	 * @return	int
	 */		
	public int updateItemPhoto(MyPlVO myPlVO);

	/**
	 * <pre>
	 * 1. 개요     : 제품 이미지 데이터 삭제
	 * 2. 처리내용 : 제품 이미지 데이터 삭제
	 * </pre>
	 * @Method Name : deleteItemPhoto
	 * @param myPlVO	MyPlVO
	 * @return	boolean
	 */		
	public boolean deleteItemPhoto(MyPlVO myPlVO);

}
