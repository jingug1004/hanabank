/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.hanaph.saleon.business.vo.CollectionVO;

@Repository("collectionDAO")
public class CollectionDAOImpl extends SqlSessionDaoSupport implements CollectionDAO{

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CollectionDAO#getCollectionGridList(java.util.Map)
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<CollectionVO> getCollectionGridList(Map<String, String> paramMap) {
		return (List<CollectionVO>)getSqlSession().selectList("collection.getCollectionGridList", paramMap); // 수금현황 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.dao.CollectionDAO#getCollectionGridTotalCount(java.util.Map)
	 */
	@Override
	public CollectionVO getCollectionGridTotalCount(Map<String, String> paramMap) {
		return (CollectionVO)getSqlSession().selectOne("collection.getCollectionGridTotalCount", paramMap); // 수금현황 목록 총 수
	}

}
