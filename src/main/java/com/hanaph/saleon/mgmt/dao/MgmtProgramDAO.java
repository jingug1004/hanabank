package com.hanaph.saleon.mgmt.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.mgmt.vo.MgmtButtonVO;
import com.hanaph.saleon.mgmt.vo.MgmtProgramVO;

/**
 * <pre>
 * Class Name : MgmtDao.java
 * 설명 : MANAGER 프로그램 등록 관리 DAO class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 4.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2014. 11. 4.
 */
public interface MgmtProgramDAO{
	
	/**
	 * <pre>
	 * 1. 개요     : MANAGER의 메뉴 tree list를 노출 하는 ajax
	 * 2. 처리내용 : MANAGER메뉴 상의 메뉴 tree구조 공통 ajax 
	 * </pre>
	 * @Method Name : getMenuList
	 * @param paramMap
	 * @return list
	 */		
	public List<MgmtProgramVO> getMenuList(Map<String, String> paramMap);
	
			
	/**
	 * <pre>
	 * 1. 개요     : MANAGER의 메뉴 tree list를 노출 하는 ajax
	 * 2. 처리내용 : 현재 등록 된 role 프로그램만 노출
	 * </pre>
	 * @Method Name : getRoleMenuList
	 * @param paramMap
	 * @return list
	 */		
	public List<MgmtProgramVO> getRoleMenuList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 프로그램 등록 관리의 프로그램 상세정보
	 * 2. 처리내용 : 메뉴트리에서 선택 된 프로그램의 상세정보를 가져오는 ajax
	 * </pre>
	 * @Method Name : detailProgramInfo
	 * @param paramMap
	 * @return MgmtProgramVO
	 */			
	public MgmtProgramVO detailProgramInfo(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 프로그램 등록 관리의 신규 프로그램 sorting number
	 * 2. 처리내용 : 메뉴트리의 메뉴 클릭 시 신규 프로그램을 추가 할때 가지고 오는 신규 순위
	 * </pre>
	 * @Method Name : getProgramSortNum
	 * @param paramMap
	 * @return MgmtProgramVO
	 */		
	public MgmtProgramVO getProgramSortNum(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 프로그램 등록 관리 프로그램 처리
	 * 2. 처리내용 : 프로그램 등록
	 * </pre>
	 * @Method Name : insertProgram
	 * @param  MgmtProgramVO paramMgmtVO
	 * @return int
	 */			
	public int insertProgram(MgmtProgramVO paramMgmtProgramVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 프로그램 등록 관리 프로그램 처리
	 * 2. 처리내용 : 프로그램 수정
	 * </pre>
	 * @Method Name : updateProgram
	 * @param paramMgmtVO
	 * @return int
	 */			
	public int updateProgram(MgmtProgramVO paramMgmtProgramVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 프로그램 등록 관리 프로그램 처리
	 * 2. 처리내용 : 프로그램 삭제
	 * </pre>
	 * @Method Name : deleteProgram
	 * @param paramMgmtVO
	 * @return int
	 */		
	public int deleteProgram(MgmtProgramVO paramMgmtProgramVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 버튼 리스트를 출력 하는 ajax
	 * 2. 처리내용 : PF_PGM_BTN테이블의 버튼 리스트 출력
	 * </pre>
	 * @Method Name : getButtonList
	 * @param paramMap
	 * @return list
	 */		
	public List<MgmtButtonVO> getButtonList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 버튼 체크
	 * 2. 처리내용 : 버튼 추가 전 해당 버튼이 있는지 확인
	 * </pre>
	 * @Method Name : getButton
	 * @param paramButtonMgmtProgramVO
	 * @return int
	 */		
	public int getButton(MgmtButtonVO paramMgmtButtonVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 버튼 리스트의 체크 된 버튼 처리
	 * 2. 처리내용 : 버튼 추가
	 * </pre>
	 * @Method Name : insertButton
	 * @param paramButtonMgmtVO
	 * @return int
	 */		
	public int insertButton(MgmtButtonVO paramMgmtButtonVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : updateButton
	 * @param paramButtonMgmtProgramVO
	 * @return int
	 */		
	public int updateButton(MgmtButtonVO paramMgmtButtonVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 버튼 리스트의 체크 된 버튼 처리
	 * 2. 처리내용 : 버튼 삭제 처리
	 * </pre>
	 * @Method Name : deleteButton
	 * @param paramButtonMgmtVO
	 * @return int
	 */	
	public int deleteButton(MgmtButtonVO paramMgmtButtonVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 버튼 리스트의 체크 된 버튼 처리
	 * 2. 처리내용 : 버튼 삭제,수정 시 role에 등록된 버튼 내용을 업데이트 한다.
	 * </pre>
	 * @Method Name : updateRoleButton
	 * @param paramButtonMgmtVO
	 * @return int
	 */	
	public int updateRoleButton(MgmtButtonVO paramMgmtButtonVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 버튼 리스트의 체크 된 버튼 처리
	 * 2. 처리내용 : 버튼 삭제,수정 시 role에 등록된 버튼 내용을 업데이트 한다.
	 * </pre>
	 * @Method Name : deleteRoleButton
	 * @param paramButtonMgmtVO
	 * @return int
	 */	
	public int deleteRoleButton(MgmtButtonVO paramMgmtButtonVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 사원별 사용 프로그램 목록 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : getUserPgmList
	 * @param paramMgmtVO
	 * @return list
	 */		
	public List<MgmtProgramVO> getUserPgmList(Map<String, String> paramMap);
	
	/**
	 * <pre>
	 * 1. 개요     : 사용자별 메뉴권한 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : getMenuAuthByUser
	 * @param paramMap
	 * @return list
	 */		
	public List<MgmtProgramVO> getMenuAuthByUser(String empCode);
	
	/**
	 * <pre>
	 * 1. 개요     : 사용자별 화면별 버튼 권한 조회
	 * 2. 처리내용 :
	 * </pre>
	 * @Method Name : getBtnAuthInPgmByUser
	 * @param paramMap
	 * @return list
	 */		
	public List<MgmtButtonVO> getBtnAuthInPgmByUser(MgmtProgramVO paramVO);
	
}
