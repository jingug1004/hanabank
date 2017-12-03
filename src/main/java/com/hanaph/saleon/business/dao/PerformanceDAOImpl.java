/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.business.dao;

import com.hanaph.saleon.business.vo.PerformanceVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository("performanceDAO")
public class PerformanceDAOImpl extends SqlSessionDaoSupport implements PerformanceDAO {

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.PerformanceDAO#getPerformanceForPartGridList(java.util.Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<PerformanceVO> getPerformanceForPartGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("performance.getPerformanceForPartGridList", paramMap); // 파트별 실적현황
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.PerformanceDAO#getPerformanceForTeamGridList(java.util.Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<PerformanceVO> getPerformanceForTeamGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("performance.getPerformanceForTeamGridList", paramMap); // 부서별 실적현황
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.PerformanceDAO#getPerformanceForEmpGridList(java.util.Map)
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<PerformanceVO> getPerformanceForEmpGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("performance.getPerformanceForEmpGridList", paramMap); // 사원별 실적현황
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.PerformanceDAO#getPerformanceForPartGridTotalCount(java.util.Map)
     */
    @Override
    public PerformanceVO getPerformanceForPartGridTotalCount(Map<String, String> paramMap) {
        return (PerformanceVO) getSqlSession().selectOne("performance.getPerformanceForPartGridTotalCount", paramMap); // 파트별 실적현황 총 수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.PerformanceDAO#getPerformanceForTeamGridTotalCount(java.util.Map)
     */
    @Override
    public PerformanceVO getPerformanceForTeamGridTotalCount(Map<String, String> paramMap) {
        return (PerformanceVO) getSqlSession().selectOne("performance.getPerformanceForTeamGridTotalCount", paramMap); // 부서별 실적현황 총 수
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.PerformanceDAO#getPerformanceForEmpGridTotalCount(java.util.Map)
     */
    @Override
    public PerformanceVO getPerformanceForEmpGridTotalCount(Map<String, String> paramMap) {
        return (PerformanceVO) getSqlSession().selectOne("performance.getPerformanceForEmpGridTotalCount", paramMap); // 사원별 실적현황 총 수
    }

}
