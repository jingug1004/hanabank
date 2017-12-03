/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.mgmt.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanaph.saleon.mgmt.dao.MgmtProgramDAO;
import com.hanaph.saleon.mgmt.vo.MgmtButtonVO;
import com.hanaph.saleon.mgmt.vo.MgmtProgramVO;

/**
 * <pre>
 * Class Name : MgmtServiceImpl.java
 * 설명 : MANAGER 프로그램 등록 관리 ServiceImpl class
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
@Service(value="mgmtProgramService")
public class MgmtProgramServiceImpl implements MgmtProgramService{
	
	@Autowired
	private MgmtProgramDAO mgmtProgramDao;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtService#getMenuList(java.util.Map)
	 */
	@Override
	public List<MgmtProgramVO> getMenuList(Map<String, String> paramMap) {
		
		/*
		 * menu type
		 */
		String type = paramMap.get("type");
		
		/*
		 * 프로그램 등록 관리 팝업인 경우와 아닌 경우
		 */
		if(type.equals("ROLE_MENU")){
			return mgmtProgramDao.getRoleMenuList(paramMap);	// 프로그램 등록 관리 팝업인 경우
		}else{
			return mgmtProgramDao.getMenuList(paramMap);
		}
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtService#detailProgramInfo(java.util.Map)
	 */
	@Override
	public MgmtProgramVO detailProgramInfo(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return mgmtProgramDao.detailProgramInfo(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtService#getProgramSortNum(java.util.Map)
	 */
	@Override
	public MgmtProgramVO getProgramSortNum(Map<String, String> paramMap) {
		return mgmtProgramDao.getProgramSortNum(paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtService#insertProgram(com.hanaph.saleon.mgmt.vo.MgmtVO)
	 */
	@Override
	public int insertProgram(MgmtProgramVO paramMgmtVO) {
		// TODO Auto-generated method stub
		return mgmtProgramDao.insertProgram(paramMgmtVO);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtService#updateProgram(com.hanaph.saleon.mgmt.vo.MgmtVO)
	 */
	@Override
	public int updateProgram(MgmtProgramVO paramMgmtVO) {
		// TODO Auto-generated method stub
		return mgmtProgramDao.updateProgram(paramMgmtVO);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtService#deleteProgram(com.hanaph.saleon.mgmt.vo.MgmtVO)
	 */
	@Override
	@Transactional
	public int deleteProgram(MgmtProgramVO paramMgmtVO) {
		// TODO Auto-generated method stub
		
		int result = 0;		// 결과 값 기본 셋팅
		
		/*
		 * 삭제 한 후 행 반환
		 */
		result = mgmtProgramDao.deleteProgram(paramMgmtVO);
		
		/*
		 * 삭제 한 행이 1개이상일시
		 */
		if(result > 0){
			
			/*
			 * 버튼 삭제 오브젝트 생성
			 */
			MgmtButtonVO paramButtonMgmtProgramVO = new MgmtButtonVO();
			
			paramButtonMgmtProgramVO.setPgm_no(paramMgmtVO.getPgm_no());	// 프로그램 no
			paramButtonMgmtProgramVO.setType("PROGRAM");					// 삭제 한 type 프로그램으로 셋팅
			
			/*
			 * 버튼 삭제 dao호출
			 */
			mgmtProgramDao.deleteButton(paramButtonMgmtProgramVO);
			
		}
		
		return result;		// 결과 값 반환
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtService#getButtonList(java.util.Map)
	 */
	@Override
	public List<MgmtButtonVO> getButtonList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return mgmtProgramDao.getButtonList(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtService#insertButton(com.hanaph.saleon.mgmt.vo.MgmtButtonVO)
	 */
	@Override
	public int insertButton(MgmtButtonVO paramMgmtButtonVO) {
		// TODO Auto-generated method stub
		
		int result = 0;		// 결과 값 기본 셋팅
		
		/*
		 * 기존 버튼이 등록 되어 있는지 체크
		 */
		result = mgmtProgramDao.getButton(paramMgmtButtonVO);
		
		/*
		 * 입력한 버튼이 존재 하지 않는 경우
		 */
		if(result == 0){
			/*
			 * 버튼 등록 dao 호출
			 */
			result = mgmtProgramDao.insertButton(paramMgmtButtonVO);
		}else{
			result = 3; 	//이미 등록된 버튼
		}
		
		return result;		// 결과 값 반환
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtService#updateButton(com.hanaph.saleon.mgmt.vo.MgmtButtonVO)
	 */
	@Override
	@Transactional
	public int updateButton(MgmtButtonVO paramMgmtButtonVO) {
		// TODO Auto-generated method stub
		
		int result = 0;		// 결과 값 기본 셋팅
		
		/*
		 * 버튼 수정 
		 */
		result = mgmtProgramDao.updateButton(paramMgmtButtonVO);
		
		/*
		 * 프로그램 마스터 테이블의 use_btn컬럼 버튼 이름 수정
		 */
		result += mgmtProgramDao.updateRoleButton(paramMgmtButtonVO);	// 결과 값 반환
		
		return result;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtService#deleteButton(com.hanaph.saleon.mgmt.vo.MgmtButtonVO)
	 */
	@Override
	public int deleteButton(MgmtButtonVO paramMgmtButtonVO) {
		// TODO Auto-generated method stub
		int result = 0;		// 결과 값 기본 셋팅
		
		/*
		 * 버튼 삭제 dao호출
		 */
		result = mgmtProgramDao.deleteButton(paramMgmtButtonVO);
		
		/*
		 * 프로그램 마스터 테이블의 use_btn컬럼 버튼 이름 삭제
		 */
		result += mgmtProgramDao.deleteRoleButton(paramMgmtButtonVO);
		
		return result;		// 결과 값 반환
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtProgramService#getUserPgmList(com.hanaph.saleon.mgmt.vo.MgmtProgramVO)
	 */
	@Override
	public List<MgmtProgramVO> getUserPgmList(Map<String, String> paramMap) {
		return mgmtProgramDao.getUserPgmList(paramMap);
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtProgramService#getMenuAuthByUser(java.lang.String)
	 */
	@Override
	public List<MgmtProgramVO> getMenuAuthByUser(String empCode) {
		return mgmtProgramDao.getMenuAuthByUser(empCode);
	}

}
