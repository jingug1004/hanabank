/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.dao;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.CustomerInfoVO;

/**
 * <pre>
 * Class Name : CustomerInfoDAO.java
 * 설명 : 고객등록 DAO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 17.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 12. 17.
 */
public interface CustomerInfoDAO {

	/**
	 * <pre>
	 * 1. 개요     : 거래처 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 거래처의 목록을 반환한다.
	 * </pre>
	 * @Method Name : getCustomerInfoGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerInfoVO> getCustomerInfoGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 거래처의 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getCustomerInfoGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerInfoVO getCustomerInfoGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 고객 jqgrid 목록
	 * 2. 처리내용 : 거래처 하위의 고객 목록을 반환한다.
	 * </pre>
	 * @Method Name : getClientGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerInfoVO> getClientGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 고객 총 수
	 * 2. 처리내용 : 거래처 하위의 고객 총 수를 반환한다.
	 * </pre>
	 * @Method Name : getClientGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerInfoVO getClientGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 고객 번호 생성
	 * 2. 처리내용 : 고객 번호를 생성한다.
	 * </pre>
	 * @Method Name : getProcedureCall
	 * @param paramVO
	 * @return
	 */		
	public String getProcedureCall(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 고객 개인정보 수정
	 * 2. 처리내용 : 고객의 개인정보를 수정한다.
	 * </pre>
	 * @Method Name : updateCustomerInfo
	 * @param paramVO
	 * @return
	 */		
	public int updateCustomerInfo(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 고객 개인정보 등록
	 * 2. 처리내용 : 고객의 개인정보를 등록한다.
	 * </pre>
	 * @Method Name : insertCustomerInfo
	 * @param paramVO
	 * @return
	 */		
	public int insertCustomerInfo(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 고객 개인정보 삭제
	 * 2. 처리내용 : 고객의 개인정보를 삭제한다.
	 * </pre>
	 * @Method Name : deleteCustomerInfo
	 * @param paramVO
	 * @return
	 */		
	public int deleteCustomerInfo(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 상세 정보
	 * 2. 처리내용 : 거래처 상세 정보를 반환한다.
	 * </pre>
	 * @Method Name : getCustomerDetail
	 * @param paramMap
	 * @return
	 */		
	public CustomerInfoVO getCustomerDetail(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 상세 정보(기타 사항)
	 * 2. 처리내용 : 거래처의 상세 정보(기타 사항)을 반환한다.
	 * </pre>
	 * @Method Name : getCustomerDetailEtcGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerInfoVO> getCustomerDetailEtcGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 상세 정보(기타 사항) 총 갯수
	 * 2. 처리내용 : 거래처의 상세 정보(기타 사항)의 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getCustomerDetailEtcGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerInfoVO getCustomerDetailEtcGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 상세정보 수정
	 * 2. 처리내용 : 거래처 상세정보를 수정한다.
	 * </pre>
	 * @Method Name : updateCustomerDetail
	 * @param paramVO
	 * @return
	 */		
	public int updateCustomerDetail(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 기타사항 등록
	 * 2. 처리내용 : 거래처 기타사항을 등록한다.
	 * </pre>
	 * @Method Name : insertCustomerDetailEtc
	 * @param paramVO
	 * @return
	 */		
	public int insertCustomerDetailEtc(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 기타사항 수정
	 * 2. 처리내용 : 거래처 기타사항을 수정한다.
	 * </pre>
	 * @Method Name : updateCustomerDetailEtc
	 * @param paramVO
	 * @return
	 */		
	public int updateCustomerDetailEtc(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 기타사항 삭제
	 * 2. 처리내용 : 거래처 기타사항을 삭제한다.
	 * </pre>
	 * @Method Name : deleteCustomerDetailEtc
	 * @param paramVO
	 * @return
	 */		
	public int deleteCustomerDetailEtc(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 소속학회 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 소속학회 목록을 반환한다. 
	 * </pre>
	 * @Method Name : getinstituteGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerInfoVO> getInstituteGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 소속학회 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 소속학회 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getinstituteGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerInfoVO getInstituteGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 가족관계 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 가족관계 목록을 반환한다. 
	 * </pre>
	 * @Method Name : getFamilyRelationshipsGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerInfoVO> getFamilyRelationshipsGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 가족관계 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 가족관계 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getFamilyRelationshipsGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerInfoVO getFamilyRelationshipsGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 기념일 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 기념일 목록을 반환한다. 
	 * </pre>
	 * @Method Name : getAnniversaryGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerInfoVO> getAnniversaryGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 기념일 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 기념일 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getAnniversaryGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerInfoVO getAnniversaryGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 교우관계 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 교우관계 목록을 반환한다. 
	 * </pre>
	 * @Method Name : getFriendRelationshipsGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerInfoVO> getFriendRelationshipsGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 교우관계 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 교우관계 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getFriendRelationshipsGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerInfoVO getFriendRelationshipsGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 기타사항 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 기타사항 목록을 반환한다. 
	 * </pre>
	 * @Method Name : getOtherDetailGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerInfoVO> getOtherDetailGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 기타사항 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 기타사항 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getOtherDetailGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerInfoVO getOtherDetailGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 기타사항 등록
	 * 2. 처리내용 : 기타사항을 등록한다.
	 * </pre>
	 * @Method Name : insertOtherDetail
	 * @param paramVO
	 */		
	public int insertOtherDetail(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 기타사항 수정
	 * 2. 처리내용 : 기타사항을 수정한다.
	 * </pre>
	 * @Method Name : updateOtherDetail
	 * @param paramVO
	 */		
	public int updateOtherDetail(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 소속학회 등록
	 * 2. 처리내용 : 소속학회를 등록한다.
	 * </pre>
	 * @Method Name : insertInstitute
	 * @param paramVO
	 */		
	public int insertInstitute(CustomerInfoVO paramVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 소속학회 수정
	 * 2. 처리내용 : 소속학회를 수정한다.
	 * </pre>
	 * @Method Name : updateInstitute
	 * @param paramVO
	 */		
	public int updateInstitute(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 가족관계 등록
	 * 2. 처리내용 : 가족관계를 등록한다.
	 * </pre>
	 * @Method Name : insertFamilyRelationships
	 * @param paramVO
	 */		
	public int insertFamilyRelationships(CustomerInfoVO paramVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 가족관계 수정
	 * 2. 처리내용 : 가족관계를 수정한다.
	 * </pre>
	 * @Method Name : updateFamilyRelationships
	 * @param paramVO
	 */		
	public int updateFamilyRelationships(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 기념일 등록
	 * 2. 처리내용 : 기념일을 등록한다.
	 * </pre>
	 * @Method Name : insertAnniversary
	 * @param paramVO
	 */		
	public int insertAnniversary(CustomerInfoVO paramVO);
	
	/**
	 * <pre>
	 * 1. 개요     : 기념일 수정
	 * 2. 처리내용 : 기념일을 수정한다.
	 * </pre>
	 * @Method Name : updateAnniversary
	 * @param paramVO
	 */		
	public int updateAnniversary(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 교우관계 등록
	 * 2. 처리내용 : 교우관계를 등록한다.
	 * </pre>
	 * @Method Name : insertFriendRelationships
	 * @param paramVO
	 */		
	public int insertFriendRelationships(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 교우관계 수정
	 * 2. 처리내용 : 교우관계를 수정한다.
	 * </pre>
	 * @Method Name : updateFriendRelationships
	 * @param paramVO
	 */		
	public int updateFriendRelationships(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 소속학회 삭제
	 * 2. 처리내용 : 소속학회를 삭제한다.
	 * </pre>
	 * @Method Name : deleteInstitute
	 * @param paramVO
	 * @return 
	 */		
	public int deleteInstitute(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 가족관계 삭제
	 * 2. 처리내용 : 가족관계를 삭제한다.
	 * </pre>
	 * @Method Name : deleteFamilyRelationships
	 * @param paramVO
	 */		
	public int deleteFamilyRelationships(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 기념일 삭제
	 * 2. 처리내용 : 기념일을 삭제한다.
	 * </pre>
	 * @Method Name : deleteAnniversary
	 * @param paramVO
	 */		
	public int deleteAnniversary(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 교우관계 삭제
	 * 2. 처리내용 : 교우관계를 삭제한다.
	 * </pre>
	 * @Method Name : deleteFriendRelationships
	 * @param paramVO
	 */		
	public int deleteFriendRelationships(CustomerInfoVO paramVO);

	/**
	 * <pre>
	 * 1. 개요     : 기타사항 삭제
	 * 2. 처리내용 : 기타사항을 삭제한다.
	 * </pre>
	 * @Method Name : deleteOtherDetail
	 * @param paramVO
	 */		
	public int deleteOtherDetail(CustomerInfoVO paramVO);

}
