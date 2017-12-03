/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 */
package com.hanaph.saleon.member.dao;

import com.hanaph.saleon.member.vo.LoginUserVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : LoginUserDAOImpl.java
 * 설명 : 로그인/로그아웃/비밀번호변경 관련 DAO 구현 class
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 16.      KIMJAEKAP          
 * </pre>
 *
 * @version : 
 * @author  : KIMJAEKAP(slamwin@irush.co.kr)
 * @since   : 2014. 10. 16.
 */
@Repository("loginUserDAO")
public class LoginUserDAOImpl extends SqlSessionDaoSupport implements LoginUserDAO {

    /* (non-Javadoc)
     * @see com.hanaph.saleon.member.dao.LoginUserDAO#getLogin(java.util.Map)
     */
    @Override
    public LoginUserVO getLogin(Map<String, String> paramMap) {
        LoginUserVO loginUserVO = (LoginUserVO) getSqlSession().selectOne("member.getLogin", paramMap);
        return loginUserVO;
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.member.dao.LoginUserDAO#updatePassword(java.util.Map)
     */
    @Override
    public LoginUserVO updatePassword(Map<String, String> paramMap, String type) {

        LoginUserVO resultVO = new LoginUserVO();

        Map<String, String> updateParamMap = new HashMap<String, String>();
        updateParamMap.put("in_EMP_NO", paramMap.get("empCode"));
        updateParamMap.put("in_PASSWORD", paramMap.get("newPassword"));
        updateParamMap.put("in_FRAG", type);

        getSqlSession().selectOne("member.callPasswordAccord", updateParamMap);

        resultVO.setOut_CODE(updateParamMap.get("out_CODE"));
        resultVO.setOut_MSG(updateParamMap.get("out_MSG"));
        resultVO.setOut_COUNT(updateParamMap.get("out_COUNT"));

        return resultVO;
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.member.dao.LoginUserDAO#getOracleUserList()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, String>> getOracleUserList() {
        return getSqlSession().selectList("member.getOracleUserList");
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.member.dao.LoginUserDAO#getLoginForEmployee(java.util.Map)
     */
    @Override
    public LoginUserVO getLoginForEmployee(Map<String, String> paramMap) {
        return (LoginUserVO) getSqlSession().selectOne("member.getLoginForEmployee", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.member.dao.LoginUserDAO#updatePasswordForEmployee(java.util.Map)
     */
    @Override
    public LoginUserVO updatePasswordForEmployee(Map<String, String> paramMap) {

        LoginUserVO resultVO = new LoginUserVO();

        Map<String, String> updateParamMap = new HashMap<String, String>();
        updateParamMap.put("in_EMP_NO", paramMap.get("empCode"));
        updateParamMap.put("in_PASSWORD", paramMap.get("newPassword"));

        getSqlSession().selectOne("member.callPasswordAccord", updateParamMap);

        resultVO.setOut_CODE(updateParamMap.get("out_CODE"));
        resultVO.setOut_MSG(updateParamMap.get("out_MSG"));
        resultVO.setOut_COUNT(updateParamMap.get("out_COUNT"));

        return resultVO;
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.member.dao.LoginUserDAO#insertHisPasswordForEmployee(java.util.Map)
     */
    @Override
    public void insertHisPasswordForEmployee(Map<String, String> paramMap) {
        getSqlSession().insert("member.insertHisPasswordForEmployee", paramMap);
    }

    /* (non-Javadoc)
     * @see com.hanaph.saleon.member.dao.LoginUserDAO#callPasswordValidateProcedure(java.util.Map)
     */
    @Override
    public LoginUserVO callPasswordValidateProcedure(Map<String, String> paramMap) {

        LoginUserVO resultVO = new LoginUserVO();

        getSqlSession().selectOne("member.callPasswordValidate", paramMap);

        resultVO.setOut_CODE(paramMap.get("out_CODE"));
        resultVO.setOut_MSG(paramMap.get("out_MSG"));
        resultVO.setOut_COUNT(paramMap.get("out_COUNT"));

        return resultVO;
    }

}
