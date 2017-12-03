/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.business.dao;

import com.hanaph.saleon.business.vo.CustomerInfoVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("customerInfoDAO")
public class CustomerInfoDAOImpl extends SqlSessionDaoSupport implements CustomerInfoDAO {

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getCustomerInfoGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerInfoVO> getCustomerInfoGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("customerInfo.getCustomerInfoGridList", paramMap); // 거래처 jqgrid 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getCustomerInfoGridTotalCount(java.util.Map)
     */
    @Override
    public CustomerInfoVO getCustomerInfoGridTotalCount(Map<String, String> paramMap) {
        return (CustomerInfoVO) getSqlSession().selectOne("customerInfo.getCustomerInfoGridTotalCount", paramMap); // 거래처 총 갯수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getClientGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerInfoVO> getClientGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("customerInfo.getClientGridList", paramMap); // 고객 jqgrid 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getClientGridTotalCount(java.util.Map)
     */
    @Override
    public CustomerInfoVO getClientGridTotalCount(Map<String, String> paramMap) {
        return (CustomerInfoVO) getSqlSession().selectOne("customerInfo.getClientGridTotalCount", paramMap); // 고객 총 수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getProcedureCall(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public String getProcedureCall(CustomerInfoVO paramVO) { // 고객 번호 생성
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("tableType", paramVO.getTableType());
        paramMap.put("cust_id", paramVO.getCust_id());
        paramMap.put("customer_id", paramVO.getCustomer_id());
        getSqlSession().selectOne("customerInfo.getProcedureCall", paramMap);
        return paramMap.get("max");
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#updateCustomerInfo(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int updateCustomerInfo(CustomerInfoVO paramVO) {
        return getSqlSession().update("customerInfo.updateCustomerInfo", paramVO); // 고객 개인정보 수정
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#insertCustomerInfo(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int insertCustomerInfo(CustomerInfoVO paramVO) {
        return getSqlSession().insert("customerInfo.insertCustomerInfo", paramVO); // 고객 개인정보 등록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#deleteCustomerInfo(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int deleteCustomerInfo(CustomerInfoVO paramVO) {
        return getSqlSession().delete("customerInfo.deleteCustomerInfo", paramVO); // 고객 개인정보 삭제
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getCustomerDetail(java.util.Map)
     */
    @Override
    public CustomerInfoVO getCustomerDetail(Map<String, String> paramMap) {
        return (CustomerInfoVO) getSqlSession().selectOne("customerInfo.getCustomerDetail", paramMap); // 거래처 상세 정보
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getCustomerDetailEtcGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerInfoVO> getCustomerDetailEtcGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("customerInfo.getCustomerDetailEtcGridList", paramMap); // 거래처 상세 정보(기타 사항)
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getCustomerDetailEtcGridTotalCount(java.util.Map)
     */
    @Override
    public CustomerInfoVO getCustomerDetailEtcGridTotalCount(Map<String, String> paramMap) {
        return (CustomerInfoVO) getSqlSession().selectOne("customerInfo.getCustomerDetailEtcGridTotalCount", paramMap); // 거래처 상세 정보(기타 사항) 총 갯수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#updateCustomerDetail(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int updateCustomerDetail(CustomerInfoVO paramVO) {
        return getSqlSession().update("customerInfo.updateCustomerDetail", paramVO); // 거래처 상세정보 수정
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#insertCustomerDetailEtc(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int insertCustomerDetailEtc(CustomerInfoVO paramVO) {
        return getSqlSession().insert("customerInfo.insertCustomerDetailEtc", paramVO); // 거래처 기타사항 등록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#updateCustomerDetailEtc(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int updateCustomerDetailEtc(CustomerInfoVO paramVO) {
        return getSqlSession().update("customerInfo.updateCustomerDetailEtc", paramVO); // 거래처 기타사항 수정
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#deleteCustomerDetailEtc(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int deleteCustomerDetailEtc(CustomerInfoVO paramVO) {
        return getSqlSession().delete("customerInfo.deleteCustomerDetailEtc", paramVO); // 거래처 기타사항 삭제
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getInstituteGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerInfoVO> getInstituteGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("customerInfo.getInstituteGridList", paramMap); // 소속학회 jqgrid 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getInstituteGridTotalCount(java.util.Map)
     */
    @Override
    public CustomerInfoVO getInstituteGridTotalCount(Map<String, String> paramMap) {
        return (CustomerInfoVO) getSqlSession().selectOne("customerInfo.getInstituteGridTotalCount", paramMap); // 소속학회 총 갯수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getFamilyRelationshipsGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerInfoVO> getFamilyRelationshipsGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("customerInfo.getFamilyRelationshipsGridList", paramMap); // 가족관계 jqgrid 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getFamilyRelationshipsGridTotalCount(java.util.Map)
     */
    @Override
    public CustomerInfoVO getFamilyRelationshipsGridTotalCount(Map<String, String> paramMap) {
        return (CustomerInfoVO) getSqlSession().selectOne("customerInfo.getFamilyRelationshipsGridTotalCount", paramMap); // 가족관계 총 갯수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getAnniversaryGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerInfoVO> getAnniversaryGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("customerInfo.getAnniversaryGridList", paramMap); // 기념일 jqgrid 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getAnniversaryGridTotalCount(java.util.Map)
     */
    @Override
    public CustomerInfoVO getAnniversaryGridTotalCount(Map<String, String> paramMap) {
        return (CustomerInfoVO) getSqlSession().selectOne("customerInfo.getAnniversaryGridTotalCount", paramMap); // 기념일 총 갯수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getFriendRelationshipsGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerInfoVO> getFriendRelationshipsGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("customerInfo.getFriendRelationshipsGridList", paramMap); // 교우관계 jqgrid 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getFriendRelationshipsGridTotalCount(java.util.Map)
     */
    @Override
    public CustomerInfoVO getFriendRelationshipsGridTotalCount(Map<String, String> paramMap) {
        return (CustomerInfoVO) getSqlSession().selectOne("customerInfo.getFriendRelationshipsGridTotalCount", paramMap); // 교우관계 총 갯수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getOtherDetailGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustomerInfoVO> getOtherDetailGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("customerInfo.getOtherDetailGridList", paramMap); // 기타사항 jqgrid 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#getOtherDetailGridTotalCount(java.util.Map)
     */
    @Override
    public CustomerInfoVO getOtherDetailGridTotalCount(Map<String, String> paramMap) {
        return (CustomerInfoVO) getSqlSession().selectOne("customerInfo.getOtherDetailGridTotalCount", paramMap); // 기타사항 총 갯수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#insertOtherDetail(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int insertOtherDetail(CustomerInfoVO paramVO) {
        return getSqlSession().insert("customerInfo.insertOtherDetail", paramVO); // 기타사항 등록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#updateOtherDetail(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int updateOtherDetail(CustomerInfoVO paramVO) {
        return getSqlSession().update("customerInfo.updateOtherDetail", paramVO); // 기타사항 수정
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#insertInstitute(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int insertInstitute(CustomerInfoVO paramVO) {
        return getSqlSession().insert("customerInfo.insertInstitute", paramVO); // 소속학회 등록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#updateInstitute(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int updateInstitute(CustomerInfoVO paramVO) {
        return getSqlSession().update("customerInfo.updateInstitute", paramVO); // 소속학회 수정
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#insertFamilyRelationships(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int insertFamilyRelationships(CustomerInfoVO paramVO) {
        return getSqlSession().insert("customerInfo.insertFamilyRelationships", paramVO); // 가족관계 등록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#updateFamilyRelationships(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int updateFamilyRelationships(CustomerInfoVO paramVO) {
        return getSqlSession().update("customerInfo.updateFamilyRelationships", paramVO); // 가족관계 수정
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#insertAnniversary(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int insertAnniversary(CustomerInfoVO paramVO) {
        return getSqlSession().insert("customerInfo.insertAnniversary", paramVO); // 기념일 등록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#updateAnniversary(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int updateAnniversary(CustomerInfoVO paramVO) {
        return getSqlSession().update("customerInfo.updateAnniversary", paramVO); // 기념일 수정
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#insertFriendRelationships(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int insertFriendRelationships(CustomerInfoVO paramVO) {
        return getSqlSession().insert("customerInfo.insertFriendRelationships", paramVO); // 교우관계 등록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#updateFriendRelationships(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int updateFriendRelationships(CustomerInfoVO paramVO) {
        return getSqlSession().update("customerInfo.updateFriendRelationships", paramVO); // 교우관계 수정
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#deleteInstitute(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int deleteInstitute(CustomerInfoVO paramVO) {
        return getSqlSession().delete("customerInfo.deleteInstitute", paramVO); // 소속학회 삭제
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#deleteFamilyRelationships(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int deleteFamilyRelationships(CustomerInfoVO paramVO) {
        return getSqlSession().delete("customerInfo.deleteFamilyRelationships", paramVO); // 가족관계 삭제
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#deleteAnniversary(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int deleteAnniversary(CustomerInfoVO paramVO) {
        return getSqlSession().delete("customerInfo.deleteAnniversary", paramVO); // 기념일 삭제
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#deleteFriendRelationships(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int deleteFriendRelationships(CustomerInfoVO paramVO) {
        return getSqlSession().delete("customerInfo.deleteFriendRelationships", paramVO); // 교우관계 삭제
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.CustomerInfoDAO#deleteOtherDetail(com.hanaph.saleon.business.vo.CustomerInfoVO)
     */
    @Override
    public int deleteOtherDetail(CustomerInfoVO paramVO) {
        return getSqlSession().delete("customerInfo.deleteOtherDetail", paramVO); // 기타사항 삭제
    }
}
