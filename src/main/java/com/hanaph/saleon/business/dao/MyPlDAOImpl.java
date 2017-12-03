package com.hanaph.saleon.business.dao;

import com.hanaph.saleon.business.vo.MyPlVO;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Class Name : MyPlDAOImpl.java
 * 설명 : myP/L DAO interface implements class.
 *
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 8.      jung a Woo
 * </pre>
 *
 * @author : jung a Woo(wja@irush.co.kr)
 * @version :
 * @since : 2014. 12. 8.
 */
@Repository("myPlDAO")
public class MyPlDAOImpl extends SqlSessionDaoSupport implements MyPlDAO {

    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getMyplGroupGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MyPlVO> getMyplGroupGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("mypl.getMyplGroupGridList", paramMap);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getProcedureCall(java.util.Map)
     */
    @Override
    public int getProcedureCall(Map<String, String> paramMap) {
        paramMap.put("tableType", "SFA_MYPL_GROUP");
        getSqlSession().selectOne("mypl.getProcedureCall", paramMap);
        return Integer.parseInt(paramMap.get("ll_max"));
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#insertPlGroup(com.hanaph.saleon.business.vo.MyPlVO)
     */
    @Override
    public void insertPlGroup(MyPlVO myPlVO) {
        getSqlSession().insert("mypl.insertPlGroup", myPlVO);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#updatePlGroup(com.hanaph.saleon.business.vo.MyPlVO)
     */
    @Override
    public void updatePlGroup(MyPlVO myPlVO) {
        getSqlSession().update("mypl.updatePlGroup", myPlVO);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#deletePlGroup(com.hanaph.saleon.business.vo.MyPlVO)
     */
    @Override
    public void deletePlGroup(MyPlVO myPlVO) {
        getSqlSession().delete("mypl.deletePlGroup", myPlVO);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getItemTypeList()
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MyPlVO> getItemTypeList() {
        List<MyPlVO> list = getSqlSession().selectList("mypl.getItemTypeList");
        return list;
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getMyPlItemList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MyPlVO> getMyPlItemList(Map<String, String> paramMap) {
        return getSqlSession().selectList("mypl.getMyPlItemList", paramMap);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getItemList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MyPlVO> getItemList(Map<String, String> paramMap) {
        return getSqlSession().selectList("mypl.getItemList", paramMap);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#deleteMyPlList(com.hanaph.saleon.business.vo.MyPlVO)
     */
    @Override
    public void deleteMyPlList(MyPlVO myPlVO) {
        getSqlSession().delete("mypl.deleteMyPlList", myPlVO);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#insertMyPlList(com.hanaph.saleon.business.vo.MyPlVO)
     */
    @Override
    public void insertMyPlList(MyPlVO itemVO) {
        getSqlSession().insert("mypl.insertMyPlList", itemVO);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getPlItemList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MyPlVO> getPlItemList(Map<String, String> paramMap) {
        return getSqlSession().selectList("mypl.getPlItemList", paramMap);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getplItemCnt(java.util.Map)
     */
    @Override
    public MyPlVO getplItemCnt(Map<String, String> paramMap) {
        return (MyPlVO) getSqlSession().selectOne("mypl.getplItemCnt", paramMap);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getItemAllGridList(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<MyPlVO> getItemAllGridList(Map<String, String> paramMap) {
        return getSqlSession().selectList("mypl.getItemAllGridList", paramMap);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getItemAllCnt(java.util.Map)
     */
    @Override
    public MyPlVO getItemAllCnt(Map<String, String> paramMap) {
        return (MyPlVO) getSqlSession().selectOne("mypl.getItemAllCnt", paramMap);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getPlItemInfo(java.util.Map)
     */
    @Override
    public MyPlVO getPlItemInfo(Map<String, String> paramMap) {
        return (MyPlVO) getSqlSession().selectOne("mypl.getPlItemInfo", paramMap);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getItemOverlapCheck(java.util.Map)
     */
    @Override
    public MyPlVO getItemOverlapCheck(Map<String, String> paramMap) {
        return (MyPlVO) getSqlSession().selectOne("mypl.getItemOverlapCheck", paramMap);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#insertPlItem(com.hanaph.saleon.business.vo.MyPlVO)
     */
    @Override
    public void insertPlItem(MyPlVO myPlVO) {
        getSqlSession().insert("mypl.insertPlItem", myPlVO);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#updatePlItem(com.hanaph.saleon.business.vo.MyPlVO)
     */
    @Override
    public void updatePlItem(MyPlVO myPlVO) {
        getSqlSession().insert("mypl.updatePlItem", myPlVO);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#deletePlItem(com.hanaph.saleon.business.vo.MyPlVO)
     */
    @Override
    public void deletePlItem(MyPlVO myPlVO) {
        getSqlSession().insert("mypl.deletePlItem", myPlVO);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#getPlItemPhoto(java.util.Map)
     */
    @Override
    public MyPlVO getPlItemPhoto(Map<String, String> paramMap) {
        return (MyPlVO) getSqlSession().selectOne("mypl.getPlItemPhoto", paramMap);
    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#updateItemPhoto(com.hanaph.saleon.business.vo.MyPlVO)
     */
    @Override
    public int updateItemPhoto(MyPlVO myPlVO) {
        return (Integer) getSqlSession().update("mypl.updateItemPhoto", myPlVO);

    }


    /* (non-Javadoc)
     * @see com.hanaph.saleon.business.dao.MyPlDAO#deleteItemPhoto(com.hanaph.saleon.business.vo.MyPlVO)
     */
    @Override
    public void deleteItemPhoto(MyPlVO myPlVO) {
        getSqlSession().update("mypl.deleteItemPhoto", myPlVO);

    }
}
