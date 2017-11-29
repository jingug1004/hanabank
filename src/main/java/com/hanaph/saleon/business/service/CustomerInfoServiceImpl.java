/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanaph.saleon.business.dao.CustomerInfoDAO;
import com.hanaph.saleon.business.vo.CustomerInfoVO;

@Service(value="customerInfoService")
public class CustomerInfoServiceImpl implements CustomerInfoService{
	
	@Autowired
	private CustomerInfoDAO customerInfoDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#getCustomerInfoGridList(java.util.Map)
	 */
	@Override
	public List<CustomerInfoVO> getCustomerInfoGridList(Map<String, String> paramMap) {
		return customerInfoDAO.getCustomerInfoGridList(paramMap); // 거래처 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#getCustomerInfoGridTotalCount(java.util.Map)
	 */
	@Override
	public CustomerInfoVO getCustomerInfoGridTotalCount(Map<String, String> paramMap) {
		return customerInfoDAO.getCustomerInfoGridTotalCount(paramMap); // 거래처 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#getClientGridList(java.util.Map)
	 */
	@Override
	public List<CustomerInfoVO> getClientGridList(Map<String, String> paramMap) {
		return customerInfoDAO.getClientGridList(paramMap); // 고객 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#getClientGridTotalCount(java.util.Map)
	 */
	@Override
	public CustomerInfoVO getClientGridTotalCount(Map<String, String> paramMap) {
		return customerInfoDAO.getClientGridTotalCount(paramMap); // 고객 목록 총 수
	}
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#procCustomerInfo(java.util.Map)
	 */
	@Override
	public int procCustomerInfo(Map<String, String> paramMap) {
		
		int resultCount = 0; // 수행 결과
		
		/*parameter 셋팅*/
		CustomerInfoVO paramVO = new CustomerInfoVO();
		
		paramVO.setCust_id((String) paramMap.get("cust_id")); // 거래처 코드
		paramVO.setCustomer_id((String) paramMap.get("customer_id")); // 고객 코드
		paramVO.setCustomer_nm((String) paramMap.get("customer_nm")); // 고객 명
		paramVO.setSex((String) paramMap.get("sex")); // 성별
		paramVO.setBirth_day((String) paramMap.get("birth_day")); // 생일
		paramVO.setAct_birth_day((String) paramMap.get("act_birth_day")); // 실제 생일
		paramVO.setReligion((String) paramMap.get("religion")); // 종교
		paramVO.setMarry_yn((String) paramMap.get("marry_yn")); // 결혼여부
		paramVO.setMarry_day((String) paramMap.get("marry_day")); // 결혼기념일
		paramVO.setChild_kind((String) paramMap.get("child_kind")); // 자녀관계
		paramVO.setDisposition((String) paramMap.get("disposition")); // 성향
		paramVO.setHobby((String) paramMap.get("hobby")); // 취미
		paramVO.setHighschool((String) paramMap.get("highschool")); // 출신고등학교
		paramVO.setUniversity((String) paramMap.get("university")); // 출신대학교
		paramVO.setTaboo_list((String) paramMap.get("taboo_list")); // 금기사항
		paramVO.setTel((String) paramMap.get("tel")); // 전화번호
		paramVO.setMobile((String) paramMap.get("mobile")); // 핸드폰번호
		paramVO.setFax((String) paramMap.get("fax")); // fax 번호
		paramVO.setRank((String) paramMap.get("rank")); // 직위 
		paramVO.setLesson((String) paramMap.get("lesson")); // 전문과
		paramVO.setHospital((String) paramMap.get("hospital")); // 수련병원
		paramVO.setMajor((String) paramMap.get("major")); // 전공
		paramVO.setForeign_study_yn((String) paramMap.get("foreign_study_yn")); // 해외연수
		paramVO.setHuman_rel((String) paramMap.get("human_rel")); // 대인관계
		paramVO.setCar_no((String) paramMap.get("car_no")); // 차량번호
		paramVO.setCar_color((String) paramMap.get("car_color")); // 차량종류 / 색상
		paramVO.setGita((String) paramMap.get("gita")); // 기타사항
		paramVO.setMain_buying((String) paramMap.get("main_buying")); // 주거래도매
		paramVO.setEmail((String) paramMap.get("email")); // email
		paramVO.setZip((String) paramMap.get("zip")); // 우편번호
		paramVO.setAddress1((String) paramMap.get("address1")); // 주소
		paramVO.setAddress2((String) paramMap.get("address2")); // 상세주소
		
		try {
			String saveType = (String) paramMap.get("saveType"); // 저장종류
			 
			/*저장 종류에 따라 프로세스 분기*/
			if ("update".equals(saveType)) { // 수정
				resultCount = customerInfoDAO.updateCustomerInfo(paramVO);
				
			} else if ("delete".equals(saveType)) { // 삭제
				resultCount = customerInfoDAO.deleteCustomerInfo(paramVO);
				
			} else if ("insert".equals(saveType)) { // 등록
				/*등록번호 생성*/
				paramVO.setTableType("CRM_MASTER");
				String max = customerInfoDAO.getProcedureCall(paramVO);
				paramVO.setCustomer_id(String.format("%07d", Integer.parseInt(max))); // 7자리가 될때까지 앞에 0 붙임
				
				/*등록*/
				resultCount = customerInfoDAO.insertCustomerInfo(paramVO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultCount;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#getCustomerDetail(java.util.Map)
	 */
	@Override
	public CustomerInfoVO getCustomerDetail(Map<String, String> paramMap) {
		return customerInfoDAO.getCustomerDetail(paramMap); // 거래처 상세 정보
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#getCustomerDetailEtcGridList(java.util.Map)
	 */
	@Override
	public List<CustomerInfoVO> getCustomerDetailEtcGridList(Map<String, String> paramMap) {
		return customerInfoDAO.getCustomerDetailEtcGridList(paramMap); // 거래처 상세 정보 : 기타사항 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#getCustomerDetailEtcGridTotalCount(java.util.Map)
	 */
	@Override
	public CustomerInfoVO getCustomerDetailEtcGridTotalCount(Map<String, String> paramMap) {
		return customerInfoDAO.getCustomerDetailEtcGridTotalCount(paramMap); // 거래처 상세 정보 : 기타사항 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#updateCustomerDetail(java.util.Map)
	 */
	@Transactional
	@Override
	public int updateCustomerDetail(Map<String, String> paramMap) {
		
		int resultCount = 0; // 수행 결과
		
		CustomerInfoVO paramVO = new CustomerInfoVO();
		
		paramVO.setCust_id((String) paramMap.get("cust_id")); // 거래처 코드
		paramVO.setTel((String) paramMap.get("tel")); // 전화번호
		paramVO.setHp((String) paramMap.get("hp")); // 핸드폰 번호
		paramVO.setOpen_date((String) paramMap.get("open_date")); // 개업일
		paramVO.setFax((String) paramMap.get("fax")); // fax 번호
		paramVO.setRoom_cnt((String) paramMap.get("room_cnt")); // 병실 수
		paramVO.setSubmit_date((String) paramMap.get("submit_date")); // 결재일
		paramVO.setBedno((String) paramMap.get("bedno")); // bed수
		paramVO.setEmail((String) paramMap.get("email")); // email
		
		try {
			/*거래처 상세정보 수정*/
			resultCount = customerInfoDAO.updateCustomerDetail(paramVO);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return resultCount;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#procCustomerDetailEtc(java.util.Map)
	 */
	@Transactional
	@Override
	public String procCustomerDetailEtc(Map<String, Object> gitaParamMap) {
		
		/*parameter*/
		String cust_id = (String) gitaParamMap.get("cust_id"); // 거래처 코드
		String[] insert_gita = (String[]) gitaParamMap.get("insert_gita"); // insert 기타사항
		String[] update_gita = (String[]) gitaParamMap.get("update_gita"); // update 기타사항
		String[] update_seq = (String[]) gitaParamMap.get("update_seq"); // 수정할 기타사항 seq
		
		/* 기타사항 등록*/
		if (null != insert_gita) {
			for (int i = 0; i < insert_gita.length; i++) {
				
				CustomerInfoVO insertParamVO = new CustomerInfoVO();
				
				/*등록번호 생성*/
				insertParamVO.setCust_id(cust_id);
				insertParamVO.setTableType("CRM_CUSTGITA");
				
				String max = customerInfoDAO.getProcedureCall(insertParamVO);
				
				insertParamVO.setSeq(String.format("%03d", Integer.parseInt(max))); // 3자리가 될때까지 0을 붙인다
				insertParamVO.setGita(insert_gita[i]);
				
				customerInfoDAO.insertCustomerDetailEtc(insertParamVO); // 기타사항 등록
			}
		}
		
		// 기타사항 수정
		if (null != update_seq) {
			for (int i = 0; i < update_seq.length; i++) {
				CustomerInfoVO updateParamVO = new CustomerInfoVO();
				
				updateParamVO.setCust_id(cust_id);
				updateParamVO.setSeq(update_seq[i]);
				updateParamVO.setGita(update_gita[i]);
				
				customerInfoDAO.updateCustomerDetailEtc(updateParamVO); // 기타사항 수정
			}
		}
		
		return "Y";
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#deleteCustomerDetailEtc(java.util.Map)
	 */
	@Transactional
	@Override
	public int deleteCustomerDetailEtc(Map<String, String> paramMap) {
		
		/*parameter*/
		String cust_id = (String)paramMap.get("cust_id"); // 거래처 코드
		String seq = (String)paramMap.get("seq"); // 기타사항 seq
		
		CustomerInfoVO paramVO = new CustomerInfoVO();
		
		paramVO.setCust_id(cust_id);
		paramVO.setSeq(seq);
		
		return customerInfoDAO.deleteCustomerDetailEtc(paramVO); // 기타사항 삭제
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#getClientDetailGridList(java.util.Map)
	 */
	@Override
	public List<CustomerInfoVO> getClientDetailGridList(Map<String, String> paramMap) {
		
		List<CustomerInfoVO> returnList = new ArrayList<CustomerInfoVO>();
		
		String currentTab = paramMap.get("currentTab"); // 현재 탭
		
		/*현재 탭에 따라 분기처리*/
		if ("2".equals(currentTab)) { 
			returnList = customerInfoDAO.getInstituteGridList(paramMap); // 소속학회 목록
		} else if ("3".equals(currentTab)) {
			returnList = customerInfoDAO.getFamilyRelationshipsGridList(paramMap); // 가족관계 목록
		} else if ("4".equals(currentTab)) {
			returnList = customerInfoDAO.getAnniversaryGridList(paramMap); // 기념일 목록
		} else if ("5".equals(currentTab)) {
			returnList = customerInfoDAO.getFriendRelationshipsGridList(paramMap); // 교우관계 목록
		} else if ("6".equals(currentTab)) {
			returnList = customerInfoDAO.getOtherDetailGridList(paramMap); // 기타사항 목록
		}
		
		return returnList;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#getClientDetailGridTotalCount(java.util.Map)
	 */
	@Override
	public CustomerInfoVO getClientDetailGridTotalCount(Map<String, String> paramMap) {
		
		CustomerInfoVO returnVO = new CustomerInfoVO();
		
		String currentTab = paramMap.get("currentTab"); // 현재 탭
		
		/*현재 탭에 따라 분기처리*/
		if ("2".equals(currentTab)) {
			returnVO = customerInfoDAO.getInstituteGridTotalCount(paramMap); // 소속학회 목록
		} else if ("3".equals(currentTab)) {
			returnVO = customerInfoDAO.getFamilyRelationshipsGridTotalCount(paramMap); // 가족관계 목록
		} else if ("4".equals(currentTab)) {
			returnVO = customerInfoDAO.getAnniversaryGridTotalCount(paramMap); // 기념일 목록
		} else if ("5".equals(currentTab)) {
			returnVO = customerInfoDAO.getFriendRelationshipsGridTotalCount(paramMap); // 교우관계 목록
		} else if ("6".equals(currentTab)) {
			returnVO = customerInfoDAO.getOtherDetailGridTotalCount(paramMap); // 기타사항 목록
		}
		
		return returnVO;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#procClientDetail(java.util.Map)
	 */
	@Override
	public String procClientDetail(Map<String, Object> paramMap) {
		
		/*기본 parameter*/
		String currentTab = (String) paramMap.get("currentTab"); // 현재 탭
		String tableType = ""; // 등록번호 조회를 위한 테이블
		String[] standardArr = null; // loop의 기준이 되는 컬럼 배열
		
		String cust_id = (String) paramMap.get("cust_id"); // 거래처 코드
		String customer_id = (String) paramMap.get("customer_id"); // 고객 코드
		String[] update_seq = (String[]) paramMap.get("update_seq"); // 수정할 기타정보 seq
		
		/*등록용 parameter*/
		String[] insert_hakheo_nm = (String[]) paramMap.get("insert_hakheo_nm"); // 학회명
		String[] insert_datef = (String[]) paramMap.get("insert_datef"); // 기간 fr
		String[] insert_datet = (String[]) paramMap.get("insert_datet"); // 기간 to
		String[] insert_jiwi = (String[]) paramMap.get("insert_jiwi"); // 직위
		String[] insert_gwansimdo = (String[]) paramMap.get("insert_gwansimdo"); // 관심도
		String[] insert_gita = (String[]) paramMap.get("insert_gita"); // 기타사항 
		String[] insert_relation = (String[]) paramMap.get("insert_relation"); // 관계
		String[] insert_name = (String[]) paramMap.get("insert_name"); // 성명
		String[] insert_birthday = (String[]) paramMap.get("insert_birthday"); // 생일
		String[] insert_job = (String[]) paramMap.get("insert_job"); // 직업
		String[] insert_sdate = (String[]) paramMap.get("insert_sdate"); // 날짜
		String[] insert_kind = (String[]) paramMap.get("insert_kind"); // 종류
		String[] insert_bigo = (String[]) paramMap.get("insert_bigo"); // 상세내역
		String[] insert_corp_nm = (String[]) paramMap.get("insert_corp_nm"); // 병원명/기관명/제약회사명
		String[] insert_lesson = (String[]) paramMap.get("insert_lesson"); // 전문과
		String[] insert_friendship = (String[]) paramMap.get("insert_friendship"); // 교우관계
		
		/*수정용 parameter*/
		String[] update_hakheo_nm = (String[]) paramMap.get("update_hakheo_nm"); // 학회명
		String[] update_datef = (String[]) paramMap.get("update_datef"); // 기간 fr
		String[] update_datet = (String[]) paramMap.get("update_datet"); // 기간 to
		String[] update_jiwi = (String[]) paramMap.get("update_jiwi"); // 직위
		String[] update_gwansimdo = (String[]) paramMap.get("update_gwansimdo"); // 관심도
		String[] update_gita = (String[]) paramMap.get("update_gita"); // 기타사항 
		String[] update_relation = (String[]) paramMap.get("update_relation"); // 관계
		String[] update_name = (String[]) paramMap.get("update_name"); // 성명
		String[] update_birthday = (String[]) paramMap.get("update_birthday"); // 생일
		String[] update_job = (String[]) paramMap.get("update_job"); // 직업
		String[] update_sdate = (String[]) paramMap.get("update_sdate"); // 날짜
		String[] update_kind = (String[]) paramMap.get("update_kind"); // 종류
		String[] update_bigo = (String[]) paramMap.get("update_bigo"); // 상세내역
		String[] update_corp_nm = (String[]) paramMap.get("update_corp_nm"); // 병원명/기관명/제약회사명
		String[] update_lesson = (String[]) paramMap.get("update_lesson"); // 전문과
		String[] update_friendship = (String[]) paramMap.get("update_friendship"); // 교우관계
		
		/*선택한 탭에 따라 분기처리*/
		if ("2".equals(currentTab)) { // 소속학회
			tableType = "CRM_HAKHEO"; 
			standardArr = insert_hakheo_nm;
		} else if ("3".equals(currentTab)) { // 가족관계
			tableType = "CRM_FAMILY";
			standardArr = insert_name;
		} else if ("4".equals(currentTab)) { // 기념일
			tableType = "CRM_SPECIALDAY";
			standardArr = insert_kind;
		} else if ("5".equals(currentTab)) { // 교우관계
			tableType = "CRM_FRIEND"; 
			standardArr = insert_corp_nm;
		} else if ("6".equals(currentTab)) { // 기타사항
			tableType = "CRM_CUSTMGITA";
			standardArr = insert_gita;
		}
		
		/*기타정보 등록*/
		if (null != standardArr) {
			for (int i = 0; i < standardArr.length; i++) {
				
				CustomerInfoVO insertParamVO = new CustomerInfoVO();
				
				/*등록번호 생성*/
				insertParamVO.setCust_id(cust_id); // 거래처 코드
				insertParamVO.setCustomer_id(customer_id); // 고객 코드
				insertParamVO.setTableType(tableType); // table 명
				
				String max = customerInfoDAO.getProcedureCall(insertParamVO); // seq 조회
				
				/*parameter setting*/
				insertParamVO.setSeq(String.format("%03d", Integer.parseInt(max))); // 등록번호
				insertParamVO.setHakheo_nm(insert_hakheo_nm[i]); // 학회명
				insertParamVO.setDatef(insert_datef[i]); // 기간 fr
				insertParamVO.setDatet(insert_datet[i]); // 기간 to
				insertParamVO.setJiwi(insert_jiwi[i]); // 직위
				insertParamVO.setGwansimdo(insert_gwansimdo[i]); // 관심도
				insertParamVO.setGita(insert_gita[i]); // 기타사항
				insertParamVO.setRelation(insert_relation[i]); // 관계
				insertParamVO.setName(insert_name[i]); // 성명
				insertParamVO.setBirthday(insert_birthday[i]); // 생일
				insertParamVO.setJob(insert_job[i]); // 직업
				insertParamVO.setSdate(insert_sdate[i]); // 날짜
				insertParamVO.setKind(insert_kind[i]); // 종류
				insertParamVO.setBigo(insert_bigo[i]); // 상세내역
				insertParamVO.setCorp_nm(insert_corp_nm[i]); // 병원명/기관명/제약회사명
				insertParamVO.setLesson(insert_lesson[i]); // 전문과
				insertParamVO.setFriendship(insert_friendship[i]); // 교우관계
				
				/*선택한 탭에 따라 분기처리*/
				if ("2".equals(currentTab)) { 
					customerInfoDAO.insertInstitute(insertParamVO); // 소속학회
				} else if ("3".equals(currentTab)) {
					customerInfoDAO.insertFamilyRelationships(insertParamVO); // 가족관계
				} else if ("4".equals(currentTab)) {
					customerInfoDAO.insertAnniversary(insertParamVO); // 기념일
				} else if ("5".equals(currentTab)) {
					customerInfoDAO.insertFriendRelationships(insertParamVO); // 교우관계
				} else if ("6".equals(currentTab)) {
					customerInfoDAO.insertOtherDetail(insertParamVO); // 기타사항
				}
				
			}
		}
		
		/*기타정보 수정*/
		if (null != update_seq) {
			for (int i = 0; i < update_seq.length; i++) {
				CustomerInfoVO updateParamVO = new CustomerInfoVO();
				
				updateParamVO.setCust_id(cust_id); // 거래처 코드
				updateParamVO.setCustomer_id(customer_id); // 고객 코드
				updateParamVO.setSeq(update_seq[i]); // 등록번호
				
				updateParamVO.setHakheo_nm(update_hakheo_nm[i]); // 학회명
				updateParamVO.setDatef(update_datef[i]); // 기간 fr
				updateParamVO.setDatet(update_datet[i]); // 기간 to
				updateParamVO.setJiwi(update_jiwi[i]); // 직위
				updateParamVO.setGwansimdo(update_gwansimdo[i]); // 관심도
				updateParamVO.setGita(update_gita[i]); // 기타사항
				updateParamVO.setRelation(update_relation[i]); // 관계
				updateParamVO.setName(update_name[i]); // 성명
				updateParamVO.setBirthday(update_birthday[i]); // 생일
				updateParamVO.setJob(update_job[i]); // 직업
				updateParamVO.setSdate(update_sdate[i]); // 날짜
				updateParamVO.setKind(update_kind[i]); // 종류
				updateParamVO.setBigo(update_bigo[i]); // 상세내역
				updateParamVO.setCorp_nm(update_corp_nm[i]); // 병원명/기관명/제약회사명
				updateParamVO.setLesson(update_lesson[i]); // 전문과
				updateParamVO.setFriendship(update_friendship[i]); // 교우관계
				
				/*현재 탭에 따라 분기처리*/
				if ("2".equals(currentTab)) {
					customerInfoDAO.updateInstitute(updateParamVO); // 소속학회
				} else if ("3".equals(currentTab)) {
					customerInfoDAO.updateFamilyRelationships(updateParamVO); // 가족관계
				} else if ("4".equals(currentTab)) { 
					customerInfoDAO.updateAnniversary(updateParamVO); // 기념일
				} else if ("5".equals(currentTab)) {
					customerInfoDAO.updateFriendRelationships(updateParamVO); // 교우관계
				} else if ("6".equals(currentTab)) {
					customerInfoDAO.updateOtherDetail(updateParamVO); // 기타사항
				}
			}
		}
		
		return "Y";
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CustomerInfoService#deleteClientDetail(java.util.Map)
	 */
	@Override
	public int deleteClientDetail(Map<String, String> paramMap) {
		
		CustomerInfoVO paramVO = new CustomerInfoVO();
		
		int resultCount = 0; // 수행 결과
		
		/*parameter*/
		String cust_id = (String) paramMap.get("cust_id"); // 거래처 코드
		String customer_id = (String) paramMap.get("customer_id"); // 고객 코드
		String currentTab = (String) paramMap.get("currentTab"); // 현재 탭
		String seq = (String) paramMap.get("seq"); // 기타정보 seq
		
		paramVO.setCust_id(cust_id);
		paramVO.setCustomer_id(customer_id);
		paramVO.setSeq(seq);
		
		/*현재 탭에 따라 분기처리*/
		if ("2".equals(currentTab)) {
			resultCount = customerInfoDAO.deleteInstitute(paramVO); // 소속학회
		} else if ("3".equals(currentTab)) {
			resultCount = customerInfoDAO.deleteFamilyRelationships(paramVO); // 가족관계
		} else if ("4".equals(currentTab)) {
			resultCount = customerInfoDAO.deleteAnniversary(paramVO); // 기념일
		} else if ("5".equals(currentTab)) {
			resultCount = customerInfoDAO.deleteFriendRelationships(paramVO); // 교우관계
		} else if ("6".equals(currentTab)) {
			resultCount = customerInfoDAO.deleteOtherDetail(paramVO); // 기타사항
		}
		
		return resultCount;
	}
}
