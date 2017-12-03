/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.business.dao;

import com.hanaph.saleon.business.vo.BusinessVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("businessDAO")
public class BusinessDAOImpl extends SqlSessionDaoSupport implements BusinessDAO {

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getCustomerGridList(java.util.Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BusinessVO> getCustomerGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("business.getCustomerGridList", paramMap); // 거래처 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getCustomerName(java.util.Map)
     */
    @Override
    public String getCustomerName(Map<String, String> paramMap) {
        return (String) getSqlSession().selectOne("business.getCustomerName", paramMap); // 거래처 명
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getCustomerGridTotalCount(java.util.Map)
     */
    @Override
    public int getCustomerGridTotalCount(Map<String, String> paramMap) {
        return (Integer) getSqlSession().selectOne("business.getCustomerGridTotalCount", paramMap); // 거래처 목록 총 수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getPartGridList(java.util.Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BusinessVO> getPartGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("business.getPartGridList", paramMap); // 파트 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getPartName(java.util.Map)
     */
    @Override
    public String getPartName(Map<String, String> paramMap) {
        return (String) getSqlSession().selectOne("business.getPartName", paramMap); // 파트 명
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getPartGridTotalCount(java.util.Map)
     */
    @Override
    public int getPartGridTotalCount(Map<String, String> paramMap) {
        return (Integer) getSqlSession().selectOne("business.getPartGridTotalCount", paramMap); // 파트 목록 총 수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getTeamGridList(java.util.Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BusinessVO> getTeamGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("business.getTeamGridList", paramMap); // 부서 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getTeamName(java.util.Map)
     */
    @Override
    public String getTeamName(Map<String, String> paramMap) {
        return (String) getSqlSession().selectOne("business.getTeamName", paramMap); // 부서 명
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getTeamGridTotalCount(java.util.Map)
     */
    @Override
    public int getTeamGridTotalCount(Map<String, String> paramMap) {
        return (Integer) getSqlSession().selectOne("business.getTeamGridTotalCount", paramMap); // 부서 목록 총 수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getEmpGridList(java.util.Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<BusinessVO> getEmpGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("business.getEmpGridList", paramMap); // 사원 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getEmpName(java.util.Map)
     */
    @Override
    public String getEmpName(Map<String, String> paramMap) {
        return (String) getSqlSession().selectOne("business.getEmpName", paramMap); // 사원 명
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getEmpGridTotalCount(java.util.Map)
     */
    @Override
    public int getEmpGridTotalCount(Map<String, String> paramMap) {
        return (Integer) getSqlSession().selectOne("business.getEmpGridTotalCount", paramMap); // 사원 목록 총 수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getCommonEmpInfo(java.util.Map)
     */
    @Override
    public BusinessVO getCommonEmpInfo(Map<String, String> paramMap) {
        return (BusinessVO) getSqlSession().selectOne("business.getCommonEmpInfo", paramMap); // 사용자 정보
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getCustomerType(java.util.Map)
     */
    @Override
    public BusinessVO getCustomerType(Map<String, String> paramMap) {
        return (BusinessVO) getSqlSession().selectOne("business.getCustomerType", paramMap); // 상세 거래처 명
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getPromiseGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<BusinessVO> getPromiseGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("business.getPromiseGridList", paramMap); // 담보 약속 목록
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.BusinessDAO#getPromiseGridTotalCount(java.util.Map)
     */
    @Override
    public int getPromiseGridTotalCount(Map<String, String> paramMap) {
        return (Integer) getSqlSession().selectOne("business.getPromiseGridTotalCount", paramMap); // 담보 약속 목록 총 수
    }

}
