/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.List;
import java.util.Map;

import com.hanaph.saleon.business.vo.CustomerInfoVO;

/**
 * <pre>
 * Class Name : CustomerInfoService.java
 * 설명 : 고객등록 Service
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
public interface CustomerInfoService {
	
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
	 * 1. 개요     : 고객 정보 입력 / 수정 / 삭제 처리
	 * 2. 처리내용 : 거래처 하위 고객의 정보를 입력 / 수정/ 삭제 처리한다.
	 * </pre>
	 * @Method Name : procCustomerInfo
	 * @param paramMap
	 * @return
	 */		
	public int procCustomerInfo(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 상세 정보
	 * 2. 처리내용 : 거래처의 상세 정보를 가져온다.
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
	 * 1. 개요     : 거래처 상세 정보 수정
	 * 2. 처리내용 : 거래처 상세 정보를 수정 한다.
	 * </pre>
	 * @Method Name : updateCustomerDetail
	 * @param paramMap
	 * @return
	 */		
	public int updateCustomerDetail(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 기타정보 등록/수정
	 * 2. 처리내용 : 거래처 기타정보를 등록/수정 한다.
	 * </pre>
	 * @Method Name : procCustomerDetailEtc
	 * @param gitaParamMap
	 * @return
	 */		
	public String procCustomerDetailEtc(Map<String, Object> gitaParamMap);

	/**
	 * <pre>
	 * 1. 개요     : 거래처 기타정보 삭제
	 * 2. 처리내용 : 거래처 기타정보를 삭제한다.
	 * </pre>
	 * @Method Name : deleteCustomerDetailEtc
	 * @param paramMap
	 * @return
	 */		
	public int deleteCustomerDetailEtc(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 고객 기타정보 jqgrid 목록
	 * 2. 처리내용 : 검색조건에 따른 고객 기타정보 목록을 json 형태로 반환한다.
	 * </pre>
	 * @Method Name : getClientDetailGridList
	 * @param paramMap
	 * @return
	 */		
	public List<CustomerInfoVO> getClientDetailGridList(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 고객 기타정보 총 갯수
	 * 2. 처리내용 : 검색조건에 따른 고객 기타정보 총 갯수를 반환한다.
	 * </pre>
	 * @Method Name : getClientDetailGridTotalCount
	 * @param paramMap
	 * @return
	 */		
	public CustomerInfoVO getClientDetailGridTotalCount(Map<String, String> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 고객 기타정보 등록/수정
	 * 2. 처리내용 : 고객 기타정보를 등록/수정한다.
	 * </pre>
	 * @Method Name : procClientDetail
	 * @param paramMap
	 * @return
	 */		
	public String procClientDetail(Map<String, Object> paramMap);

	/**
	 * <pre>
	 * 1. 개요     : 고객 기타정보 삭제
	 * 2. 처리내용 : 고객 기타정보를 삭제한다.
	 * </pre>
	 * @Method Name : deleteClientDetail
	 * @param paramMap
	 * @return
	 */		
	public int deleteClientDetail(Map<String, String> paramMap);
}
