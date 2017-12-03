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

import com.hanaph.saleon.business.dao.TurnsdayDAO;
import com.hanaph.saleon.business.vo.TurnsdayVO;

/**
 * <pre>
 * Class Name : TurnsdayServiceImpl.java
 * 설명 : 회전일 현황 조회 관련 serviceImpl
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015. 2. 2.      장일영            최초생성
 * </pre>
 * 
 * @version : 1.0
 * @author  : 윤범진
 * @since   : 2015. 2. 2.
 */
@Service(value="turnsdayService")
public class TurnsdayServiceImpl implements TurnsdayService{
	
	@Autowired
	private TurnsdayDAO TurnsdayDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.TurnsdayService#getTurnsdayGridList(java.util.Map)
	 */
	public List<TurnsdayVO> getTurnsdayGridList(Map<String, String> paramMap) {
		return TurnsdayDAO.getTurnsdayGridList(paramMap); // 회전일 현황 목록
	}


}
