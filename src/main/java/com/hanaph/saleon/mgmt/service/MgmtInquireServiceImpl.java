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

import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.mgmt.dao.MgmtInquireDAO;
import com.hanaph.saleon.mgmt.vo.MgmtInquireVO;

/**
 * <pre>
 * Class Name : MgmtInquireService.java
 * 설명 : MANAGER 권한조회 ServiceImpl class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 4.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2014. 12. 4.
 */
@Service(value="mgmtInquireService")
public class MgmtInquireServiceImpl implements MgmtInquireService{
	
	@Autowired
	private MgmtInquireDAO mgmtInquireDao;
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtInquireService#getUserPgmList(java.util.Map)
	 */
	@Override
	public List<MgmtInquireVO> getUserPgmList(Map<String, String> paramMap) {
		return mgmtInquireDao.getUserPgmList(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtInquireService#getUserRoleList(java.util.Map)
	 */
	@Override
	public List<MgmtInquireVO> getUserRoleList(Map<String, String> paramMap) {
		return mgmtInquireDao.getUserRoleList(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtInquireService#getEmpListByPgmno(java.util.Map)
	 */
	@Override
	public List<MgmtInquireVO> getEmpListByPgmno(Map<String, String> paramMap) {
		return mgmtInquireDao.getEmpListByPgmno(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtInquireService#getRoleListByPgmno(java.util.Map)
	 */
	@Override
	public List<MgmtInquireVO> getRoleListByPgmno(Map<String, String> paramMap) {
		return mgmtInquireDao.getRoleListByPgmno(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.mgmt.service.MgmtInquireService#insertUserRoleCopy(java.util.Map)
	 */
	@Override
	public int insertUserRoleCopy(MgmtInquireVO mgmtInquireVO) {
		// TODO Auto-generated method stub
		
		int result = 0;			// 결과 기본 값
		int roleCount = 0;		// role 갯수 기본 값
		String roleNo = mgmtInquireVO.getRole_no();		// 파라메터로 받은 role을 변수에 셋팅.
		String empCode = mgmtInquireVO.getEmp_code();	// 파라메터로 받은 사원코드를 변수에 담음.
		
		// map 객체 생성
		Map<String, String> paramMap = new HashMap<String, String>();
		
		/*
		 *  role no가 무조건 존재 하며 ,,,데이터로 들어온 경우
		 */
		if(!"".equals(roleNo) && roleNo.indexOf(",") > -1){
			String roleNos[] = roleNo.split(",");	// ,값으로 배열에 셋팅
			
			/*
			 * 셋팅 된 배열의 수 만큼 for
			 */
			for(int i= 0; i<roleNos.length; i++){
				mgmtInquireVO.setRole_no(roleNos[i]);
				
				/*
				 * 사원코드가 무조건 존재 하며 사원코드 데이터가 ,,, 데이터로 들어온 경우
				 */
				if(!"".equals(empCode) && empCode.indexOf(",") > -1){
					String empCodes[] = empCode.split(",");	// ,값으로 자른 뒤 배열에 셋팅
					/*
					 * 셋팅 된 배열의 수 만큼 for
					 */
					for(int j= 0; j<empCodes.length; j++){
						
						mgmtInquireVO.setEmp_code(empCodes[j]);	// 사원코드를 하나씩 mgmtInquireVO 오브젝트에 셋팅
						/*
						 *  사원코드를 맵에 셋팅
						 */
						paramMap.put("empCode", empCodes[j]);
						paramMap.put("roleNo", mgmtInquireVO.getRole_no());
						
						/*
						 * 해당 role 데이터가 있는지 셋팅
						 */
						roleCount = mgmtInquireDao.getRoleUserCount(paramMap);
						
						/*
						 * role count가 없을 경우에만 role등록
						 */
						if(roleCount == 0){
							mgmtInquireDao.insertUserRoleCopy(mgmtInquireVO);
							result++;	// 반환 값
						}
						
					}
				/*
				 * 사원코드가 ,,,값이 아닌 한개의 값으로 들어온 경우	
				 */
				}else{
					
					/*
					 * role no와 사원코드를 맵에 셋팅
					 */
					paramMap.put("empCode", StringUtil.nvl(mgmtInquireVO.getEmp_code()));
					paramMap.put("roleNo", StringUtil.nvl(mgmtInquireVO.getRole_no()));
					
					/*
					 * 해당 role 데이터가 있는지 셋팅
					 */
					roleCount = mgmtInquireDao.getRoleUserCount(paramMap);
					
					/*
					 * role count가 없을 경우에만 role등록
					 */
					if(roleCount == 0){
						mgmtInquireVO.setEmp_code(empCode);
						result = mgmtInquireDao.insertUserRoleCopy(mgmtInquireVO);
					}
					
				}
			}
		/*
		 * role no가 ,,,값이 아닌 한개의 값으로 들어온 경우	
		 */	
		}else{
			
			/*
			 * 사원코드가 무조건 존재 하며 사원코드 데이터가 ,,, 데이터로 들어온 경우
			 */
			if(!"".equals(empCode) && empCode.indexOf(",") > -1){
				String empCodes[] = empCode.split(",");		// ,값으로 자른 뒤 배열에 셋팅
				
				/*
				 * 셋팅 된 배열의 수 만큼 for
				 */
				for(int j= 0; j<empCodes.length; j++){	
					mgmtInquireVO.setEmp_code(empCodes[j]);		// 사원코드를 하나씩 mgmtInquireVO 오브젝트에 셋팅
					
					/*
					 *  사원코드를 맵에 셋팅
					 */
					paramMap.put("empCode", empCodes[j]);
					paramMap.put("roleNo", mgmtInquireVO.getRole_no());
					
					/*
					 * 해당 role 데이터가 있는지 셋팅
					 */
					roleCount = mgmtInquireDao.getRoleUserCount(paramMap);
					
					/*
					 * role count가 없을 경우에만 role등록
					 */
					if(roleCount == 0){
						mgmtInquireDao.insertUserRoleCopy(mgmtInquireVO);
						result++;
					}
					
				}
			/*
			 * 사원코드가 ,,,값이 아닌 한개의 값으로 들어온 경우	
			 */
			}else{
				
				/*
				 * role no와 사원코드를 맵에 셋팅
				 */
				paramMap.put("empCode", StringUtil.nvl(mgmtInquireVO.getEmp_code()));
				paramMap.put("roleNo", StringUtil.nvl(mgmtInquireVO.getRole_no()));
				
				/*
				 * 해당 role 데이터가 있는지 셋팅
				 */
				roleCount = mgmtInquireDao.getRoleUserCount(paramMap);
				
				/*
				 * role count가 없을 경우에만 role등록
				 */
				if(roleCount == 0){
					mgmtInquireVO.setEmp_code(empCode);
					result = mgmtInquireDao.insertUserRoleCopy(mgmtInquireVO);
				}
				
			}
		}
		
		return result;		// 결과 값 반환
	}

}
