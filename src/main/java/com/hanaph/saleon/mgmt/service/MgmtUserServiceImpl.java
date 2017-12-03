/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.mgmt.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanaph.saleon.mgmt.dao.MgmtUserDAO;
import com.hanaph.saleon.mgmt.vo.MgmtUserVO;

/**
 * <pre>
 * Class Name : MgmtUserServiceImpl.java
 * 설명 : MANAGER 사용자 관리 ServiceImpl class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 28.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2014. 11. 28.
 */
@Service(value="mgmtUserService")
public class MgmtUserServiceImpl implements MgmtUserService{
	
	@Autowired
	private MgmtUserDAO mgmtUserDao;
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtUserService#getUserMgmtMain(java.util.Map)
	 */
	@Override
	public List<MgmtUserVO> getUserMgmtMain(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return mgmtUserDao.getUserMgmtMain(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtUserService#updateMember(com.hanaph.saleon.mgmt.vo.MgmtUserVO)
	 */
	@Override
	public int updateMember(MgmtUserVO paramMgmtUserVO) {
		// TODO Auto-generated method stub
		
		int result = 0;		// 결과 값 기본 셋팅
		
		String[] empCodes = paramMgmtUserVO.getEmpCodes();			// 거래처 코드
		String[] empNames = paramMgmtUserVO.getEmpNames();			// 거래처 이름
		String[] empPasswords = paramMgmtUserVO.getEmpPasswords();	// 비밀번호
		
		// 선택 된 거래처의 수 만큼 for
		for(int i = 0; i<empCodes.length; i++){
			
			// 단일 셋팅을 위한 object생성
			MgmtUserVO setMgmtUserVO = new MgmtUserVO();
			
			setMgmtUserVO.setEmp_code(empCodes[i]);		// 거래처 코드
			setMgmtUserVO.setEmp_name(empNames[i]);		// 거래처 이름
			setMgmtUserVO.setPassword(empPasswords[i]);	// 비밀번호
			setMgmtUserVO.setDept_code("");				// 부서 코드 
			setMgmtUserVO.setEmp_gb("1");				// 사원 구분 
			setMgmtUserVO.setUse_yn("Y");				// 삭제 여부
			
			// 수정사항 db update
			result += mgmtUserDao.updateMember(setMgmtUserVO);
		}
		
		return result;		// 결과 값 반환
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtUserService#deleteMember(com.hanaph.saleon.mgmt.vo.MgmtUserVO)
	 */
	@Override
	public int deleteMember(MgmtUserVO paramMgmtUserVO) {
		// TODO Auto-generated method stub
		return mgmtUserDao.deleteMember(paramMgmtUserVO);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtUserService#getRoleList(java.util.Map)
	 */
	@Override
	public List<MgmtUserVO> getRoleList(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return mgmtUserDao.getRoleList(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtUserService#insertUserRole(com.hanaph.saleon.mgmt.vo.MgmtUserVO)
	 */
	@Override
	@Transactional
	public int insertUserRole(MgmtUserVO paramMgmtUserVO) {
		// TODO Auto-generated method stub
		
		int result =0;		// 결과 값 기본 셋팅
		
		String empCode = paramMgmtUserVO.getEmp_code();		// 거래처 코드
		String roleNo = paramMgmtUserVO.getRole_no();		// role no
		
		/*
		 * check된 role no를 담기 위한 맵 생성
		 */
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*
		 *  체크된 role
		 */
		if(!"".equals(roleNo) && roleNo.indexOf(",") > -1){
			String roleNos[] = roleNo.split("\\,");
			
			/*
			 * role no만큼 for
			 */
			for(int i = 0; i<roleNos.length; i++){
				
				paramMap.put("empCode", empCode);		// 1개씩 맵에 거래처 코드 셋팅
				paramMap.put("roleNo", roleNos[i]);		// 1개씩 맵에 role no 셋팅
				
				int count = 0;		// 사용자 role 갯수 기본 셋팅
				
				/*
				 *	사용자 role 갯수 체크  
				 */
				count = mgmtUserDao.getRoleUserCount(paramMap);
				
				/*
				 * 기존 role이 없는 경우만 체크하여 role등록
				 */
				if(count == 0){
					
					paramMgmtUserVO = new MgmtUserVO();
					
					paramMgmtUserVO.setRole_no(roleNos[i]);		// role no
					paramMgmtUserVO.setEmp_code(empCode);		// 거래처 코드
					
					// 기존 role이 없는 경우만 role등록
					result = mgmtUserDao.insertUserRole(paramMgmtUserVO);
					result++;	// 결과 값 반환
				}
			}
			
			/*
			 * 현재 체크되어 들어온 role no 값을 제외 한 role 전부 삭제
			 */
			paramMgmtUserVO.setRole_no(roleNo);
			mgmtUserDao.deleteUserRole(paramMgmtUserVO);
		/*
		 * 체크된 role이 한개 일 경우	
		 */
		}else{
			
			/*
			 *  empCode,roleNo를 map에 setting
			 */
			paramMap.put("empCode", empCode);	// 사원코드
			paramMap.put("roleNo", roleNo);		// role no
			
			int count = 0;		// 사용자 role 갯수 기본 셋팅
			/*
			 *	사용자 role 갯수 체크  
			 */
			count = mgmtUserDao.getRoleUserCount(paramMap);
			
			/*
			 * 기존 role이 없는 경우만 체크하여 role등록
			 */
			if(count == 0){
				
				paramMgmtUserVO.setRole_no(roleNo);			// role no
				paramMgmtUserVO.setEmp_code(empCode);		// 거래처 코드
				
				/*
				 *  기존 role이 없는 경우만 role등록
				 */
				result = mgmtUserDao.insertUserRole(paramMgmtUserVO);
			}
			
			/*
			 * 현재 체크되어 들어온 role no 값을 제외 한 role 전부 삭제
			 */
			mgmtUserDao.deleteUserRole(paramMgmtUserVO);
		}
		
		return result;		// 결과 값 반환
	}

}
