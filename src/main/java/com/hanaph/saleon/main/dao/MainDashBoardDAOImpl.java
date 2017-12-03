/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.main.dao;

import com.hanaph.saleon.main.vo.CustDashboardVO;
import com.hanaph.saleon.main.vo.EmpDashboardVO;
import com.hanaph.saleon.main.vo.NoticeVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : MainDashBoardDAOImpl.java
 * 설명 : 공지사항, 메인 대시보드에 사용되는 데이터를 조회하기 위한 dao implements class.
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 8.      장일영            최초생성
 * </pre>
 *
 * @author : 장일영(goodhi@irush.co.kr)
 * @version : 1.0
 * @since : 2014. 12. 8.
 */
@Repository
public class MainDashBoardDAOImpl extends SqlSessionDaoSupport implements MainDashBoardDAO {

    /* (non-Javadoc)
     * @see com.hanaph.saleon.main.dao.MainDashBoardDAO#getNoticeList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<NoticeVO> getNoticeList(Map<String, String> paramMap) {
        return getSqlSession().selectList("main.getNoticeList", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.main.dao.MainDashBoardDAO#getCustInfo(java.util.Map)
     */
    @Override
    public CustDashboardVO getCustInfo(Map<String, String> paramMap) {
        return (CustDashboardVO) getSqlSession().selectOne("main.getCustInfo", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.main.dao.MainDashBoardDAO#getCustLoanPresentCondition(java.util.Map)
     */
    @Override
    public CustDashboardVO getCustLoanPresentCondition(Map<String, String> paramMap) {
        return (CustDashboardVO) getSqlSession().selectOne("main.getCustLoanPresentCondition", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.main.dao.MainDashBoardDAO#getCustOrderPresentCondition(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<CustDashboardVO> getCustOrderPresentCondition(
            Map<String, String> paramMap) {
        return getSqlSession().selectList("main.getCustOrderPresentCondition", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.main.dao.MainDashBoardDAO#getCustOrderTotal(java.util.Map)
     */
    @Override
    public CustDashboardVO getCustOrderTotal(Map<String, String> paramMap) {
        return (CustDashboardVO) getSqlSession().selectOne("main.getCustOrderTotal", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.main.dao.MainDashBoardDAO#getEmpInfo(java.util.Map)
     */
    @Override
    public EmpDashboardVO getEmpInfo(Map<String, String> paramMap) {
        return (EmpDashboardVO) getSqlSession().selectOne("main.getEmpInfo", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.main.dao.MainDashBoardDAO#getEmpResultYear(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EmpDashboardVO> getEmpResultYear(Map<String, String> paramMap) {
        return getSqlSession().selectList("main.getEmpResultYear", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.main.dao.MainDashBoardDAO#getPartResultYear(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EmpDashboardVO> getPartResultYear(Map<String, String> paramMap) {
        return getSqlSession().selectList("main.getPartResultYear", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.main.dao.MainDashBoardDAO#getCompanyResultYear(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EmpDashboardVO> getCompanyResultYear(
            Map<String, String> paramMap) {
        return getSqlSession().selectList("main.getCompanyResultYear", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.main.dao.MainDashBoardDAO#getCompanyResultMonthByPart(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EmpDashboardVO> getCompanyResultMonthByPart(
            Map<String, String> paramMap) {
        return getSqlSession().selectList("main.getCompanyResultMonthByPart", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.main.dao.MainDashBoardDAO#getTeamResultYear(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<EmpDashboardVO> getTeamResultYear(Map<String, String> paramMap) {
        return getSqlSession().selectList("main.getTeamResultYear", paramMap);
    }


}
