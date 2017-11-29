/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hanaph.saleon.order.dao.DeliveryDAO;
import com.hanaph.saleon.order.vo.DeliveryVO;

/**
 * 
 * <pre>
 * Class Name : DeliveryServiceImpl.java
 * 설명 : 원장조회 ServiceImpl
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015.08.07   김태안           최초작성                
 * </pre>
 * 
 * @version : 
 * @author  : 김태안
 * @since   : 2015. 08. 07.
 */
@Service(value="deliveryService")
public class DeliveryServiceImpl implements DeliveryService {
	
	/**
	 * DeliveryDAO
	 */
	@Autowired
	private DeliveryDAO deliveryDAO;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.service.DeliveryService#getDeliveryGridList(java.util.Map)
	 */
	@Override
	public List<DeliveryVO> getDeliveryGridList(Map<String, String> paramMap) {
		
		String arrDay[] = paramMap.get("adt_from").split("-");		//- 를 구분자로 짤라서 array객체로 
		String adt_from_first = arrDay[0]+"-"+arrDay[1]+"-01";		//조회 시작일 format (yyyy-mm-01)
		
		paramMap.put("adt_from_first", adt_from_first);
		
		return deliveryDAO.getDeliveryGridList(paramMap);
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.order.service.DeliveryService#getSumDelivery(java.util.Map)
	 */
	@Override
	public DeliveryVO getSumDelivery(Map<String, String> paramMap) {
		
		String arrDay[] = paramMap.get("adt_from").split("-");		//- 를 구분자로 짤라서 array객체로 
		String adt_from_first = arrDay[0]+"-"+arrDay[1]+"-01";		//조회 시작일 format (yyyy-mm-01)
		
		paramMap.put("adt_from_first", adt_from_first);
		
		return deliveryDAO.getSumDelivery(paramMap);
	}

}
