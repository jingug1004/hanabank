/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanaph.saleon.business.dao.CollectionDAO;
import com.hanaph.saleon.business.vo.CollectionVO;

@Service(value="collectionService")
public class CollectionServiceImpl implements CollectionService{
	
	@Autowired
	private CollectionDAO collectionDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CollectionService#getCollectionGridList(java.util.Map)
	 */
	@Override
	public List<CollectionVO> getCollectionGridList(Map<String, String> paramMap) {
		return collectionDAO.getCollectionGridList(paramMap); // 수금현황 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.CollectionService#getCollectionGridTotalCount(java.util.Map)
	 */
	@Override
	public CollectionVO getCollectionGridTotalCount(Map<String, String> paramMap) {
		return collectionDAO.getCollectionGridTotalCount(paramMap); // 수금현황 목록 총 수
	}

}
