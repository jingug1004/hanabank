/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanaph.saleon.business.dao.CompanyCardMgmtDAO;
import com.hanaph.saleon.business.vo.CompanyCardMgmtVO;
import com.hanaph.saleon.common.utils.StringUtil;

@Service(value="companyCardMgmtService")
public class CompanyCardMgmtServiceImpl implements CompanyCardMgmtService{

	@Autowired
	private CompanyCardMgmtDAO companyCardMgmtDAO;
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CompanyCardMgmtService#getCompanyCardMgmtInit(java.util.Map)
	 */
	@Override
	public CompanyCardMgmtVO getCompanyCardMgmtInit(Map<String, String> paramMap) {
		
		CompanyCardMgmtVO returnVO = new CompanyCardMgmtVO();
		
		/*부서가 바뀌면 퇴직처리하는데 이전부서에서 했던 법인카드 정리를 위해서는 퇴직된 사번으로 로그인 되어야 함.*/
		CompanyCardMgmtVO empInfoVO = companyCardMgmtDAO.getEmpInfo(paramMap); //로그인 사원의 사번,사원명
		
		String ls_sawon_id = ""; // 사번
		String ls_sawon_nm = ""; // 사원명
		String ls_dept_cd = ""; // 부서코드
		String ls_dept_nm = ""; // 부서명
		
		/*조회결과가 있을 경우 return*/
		if (empInfoVO != null) {
			ls_sawon_id = StringUtil.nvl(empInfoVO.getLs_sawon_id());
			ls_sawon_nm = StringUtil.nvl(empInfoVO.getLs_sawon_nm());
			ls_dept_cd = StringUtil.nvl(empInfoVO.getLs_dept_cd());
			ls_dept_nm = StringUtil.nvl(empInfoVO.getLs_dept_nm());
		}
		
		returnVO.setLs_sawon_id(ls_sawon_id);
		returnVO.setLs_sawon_nm(ls_sawon_nm);
		returnVO.setLs_dept_cd(ls_dept_cd);
		returnVO.setLs_dept_nm(ls_dept_nm);
		
		/*로그인사원의 직책(보직)을 인사시스템에서 가져온다.*/
		String is_assgn_cd = StringUtil.nvl(companyCardMgmtDAO.getAssgnCd(paramMap));
		
		returnVO.setIs_assgn_cd(is_assgn_cd);
		
		return returnVO;
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CompanyCardMgmtService#getGaejungCodeList()
	 */
	@Override
	public List<CompanyCardMgmtVO> getGaejungCodeList() {
		return companyCardMgmtDAO.getGaejungCodeList(); // 계정과목 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CompanyCardMgmtService#getCompanyCardHistoryGridList(java.util.Map)
	 */
	@Override
	public List<CompanyCardMgmtVO> getCompanyCardHistoryGridList(Map<String, String> paramMap) {
		return companyCardMgmtDAO.getCompanyCardHistoryGridList(paramMap); // 법인카드 사용 내역 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CompanyCardMgmtService#getCompanyCardHistoryGridTotalCount(java.util.Map)
	 */
	@Override
	public CompanyCardMgmtVO getCompanyCardHistoryGridTotalCount(Map<String, String> paramMap) {
		return companyCardMgmtDAO.getCompanyCardHistoryGridTotalCount(paramMap); // 법인카드 사용 내역 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CompanyCardMgmtService#updateCardUseDetail(java.util.Map)
	 */
	@Transactional
	@Override
	public String updateCardUseDetail(Map<String, Object> paramMap) {
		String result = "N"; // 수행 결과
		
		/*parameter*/
		String teamjang_conf_sabun = (String) paramMap.get("teamjang_conf_sabun"); // 승인자
		String[] use_dt = (String[]) paramMap.get("use_dt"); // 사용 일자
		String[] use_tm = (String[]) paramMap.get("use_tm"); // 사용 시간
		String[] card_no = (String[]) paramMap.get("card_no"); // 카드 번호
		String[] card_ok_no = (String[]) paramMap.get("card_ok_no"); // 승인번호
		String[] tax_gb = (String[]) paramMap.get("tax_gb"); // 과세구분
		String[] gongjae_yn = (String[]) paramMap.get("gongjae_yn"); // 부가세 공제여부
		String[] use_detail = (String[]) paramMap.get("use_detail"); // 사용내역
		String[] gaejung_cd = (String[]) paramMap.get("gaejung_cd"); // 계정과목
		String[] teamjang_conf_yn = (String[]) paramMap.get("teamjang_conf_yn"); // 입력완료 여부
		String[] jukyo = (String[]) paramMap.get("jukyo"); // 적요
		
		if (card_no != null) {
			
			/*수정된 내역들에 대해서만 프로세스 수행*/
			for (int i = 0; i < card_no.length; i++) {
				CompanyCardMgmtVO companyCardMgmtVO = new CompanyCardMgmtVO();
				
				companyCardMgmtVO.setUse_dt(use_dt[i].replace("-",""));
				companyCardMgmtVO.setUse_tm(use_tm[i].replace(":",""));
				companyCardMgmtVO.setCard_no(card_no[i]);
				companyCardMgmtVO.setCard_ok_no(card_ok_no[i]);
				companyCardMgmtVO.setTax_gb(tax_gb[i]);
				companyCardMgmtVO.setGongjae_yn(gongjae_yn[i]);
				companyCardMgmtVO.setUse_detail(use_detail[i]);
				companyCardMgmtVO.setGaejung_cd(gaejung_cd[i]);
				companyCardMgmtVO.setTeamjang_conf_yn(teamjang_conf_yn[i]);
				companyCardMgmtVO.setTeamjang_conf_sabun("N".equals(teamjang_conf_yn[i]) ? "" : teamjang_conf_sabun); // 완료처리 되었을 경우에만 승인자 입력
				companyCardMgmtVO.setJukyo(jukyo[i]);
				
				companyCardMgmtDAO.updateCardUseDetail(companyCardMgmtVO); // 법인카드 사용 내역 수정
			}
		}
		
		result = "Y";
		
		return result;
	}
}
