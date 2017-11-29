
package com.hanaph.saleon.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hanaph.saleon.business.dao.MyPlDAO;
import com.hanaph.saleon.business.vo.MyPlVO;

/**
 * 
 * <pre>
 * Class Name : MyPlServiceImpl.java
 * 설명 :  MY P/L 관련 ServiceImpl
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 27.      jung a Woo          
 * </pre>
 * 
 * @version : 
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 10. 27.
 */
@Service(value="myPlService")
public class MyPlServiceImpl implements MyPlService {

	/**
	 * MyPlDAO
	 */
	@Autowired
	private MyPlDAO myPlDAO;
	
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#getMyplGroupGridList(java.util.Map)
	 */
	@Override
	public List<MyPlVO> getMyplGroupGridList(Map<String, String> paramMap) {
		return myPlDAO.getMyplGroupGridList(paramMap);
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#insertPlGroup(java.util.Map)
	 */
	@Override
	public boolean insertPlGroup(Map<String, String> paramMap) {
		/*
		 * P/L그룹 번호 생성
		 */
		int ll_max = myPlDAO.getProcedureCall(paramMap);	
		
		MyPlVO myPlVO = new MyPlVO();
		myPlVO.setPlgrp_no(ll_max);								//P/L그룹 번호
		myPlVO.setGs_empCode(paramMap.get("gs_empCode"));		//사원 코드 
		myPlVO.setPlgrp_nm(paramMap.get("plgrp_nm"));			//P/L그룹명
		myPlVO.setComments(paramMap.get("comments"));			//P/L그룹 설명
		myPlVO.setSort_seq(paramMap.get("sort_seq"));			//P/L그룹 정렬 순서
		
		myPlDAO.insertPlGroup(myPlVO);							//P/L그룹 정보 인서트
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#updatePlGroup(java.util.Map)
	 */
	@Transactional
	@Override
	public boolean updatePlGroup(Map<String, Object> paramMap) {
		
		String[] plgrp_no = (String[]) paramMap.get("plgrp_no");	//P/L그룹 번호		
		String[] plgrp_nm = (String[]) paramMap.get("plgrp_nm");	//P/L그룹 이름
		String[] comments = (String[])paramMap.get("comments");		//P/L그룹 설명
		String[] sort_seq = (String[])paramMap.get("sort_seq");		//P/L그룹 정렬순서
		
		/*
		 *	P/L그룹들의 갯수만큼 루프를 돌면서 정보를 수정한다. 
		 */
		if(plgrp_no!=null){
			for(int i = 0; plgrp_no.length > i; i++){
				MyPlVO myPlVO = new MyPlVO();
				myPlVO.setPlgrp_no(Integer.parseInt(plgrp_no[i]));
				myPlVO.setPlgrp_nm(plgrp_nm[i]);
				myPlVO.setComments(comments[i]);
				myPlVO.setSort_seq(sort_seq[i]);
				myPlDAO.updatePlGroup(myPlVO);	//P/L Group update 
			}	
		}
		
		return true;
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#deletePlGroup(java.util.Map)
	 */
	@Override
	@Transactional
	public boolean deletePlGroup(Map<String, String> paramMap) {
		
		MyPlVO myPlVO = new MyPlVO();
		myPlVO.setPlgrp_no(Integer.parseInt(paramMap.get("plgrp_no")));		//P/L그룹 코드
		myPlVO.setSawon_id(paramMap.get("sawon_id"));						//사원 코드
		
		/*
		 * P/L 그룹 삭제
		 */
		myPlDAO.deletePlGroup(myPlVO);	
		
		/*
		 * 해당 P/L그룹에 등록되어 있는 P/L제품 삭제
		 */
		myPlDAO.deleteMyPlList(myPlVO);
		
		return true;
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#getItemTypeList()
	 */
	@Override
	public List<MyPlVO> getItemTypeList() {
		return myPlDAO.getItemTypeList();
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#getMyPlItemList(java.util.Map)
	 */
	@Override
	public List<MyPlVO> getMyPlItemList(Map<String, String> paramMap) {
		return myPlDAO.getMyPlItemList(paramMap);
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#getItemList(java.util.Map)
	 */
	@Override
	public List<MyPlVO> getItemList(Map<String, String> paramMap) {
		return myPlDAO.getItemList(paramMap);
	}

	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#insertMyPlList(java.util.Map)
	 */
	@Override
	@Transactional
	public boolean insertMyPlList(Map<String, Object> paramMap) {
		
		String[] item_id = (String[]) paramMap.get("item_id");	//제품 코드
		String[] sort_seq = (String[])paramMap.get("sort_seq");	//정렬 순서
		
		/*
		 *  기존에 등록된 제품 목록 삭제 
		 */
		MyPlVO myPlVO = new MyPlVO();
		myPlVO.setSawon_id((String) paramMap.get("sawon_id"));						//사원 코드
		myPlVO.setPlgrp_no(Integer.parseInt((String) paramMap.get("plgrp_no")));	//P/L그룹 코드
		myPlDAO.deleteMyPlList(myPlVO);												//기존 My P/L List delete
		
		/* 
		 * 제품정보가 있을 경우 제품 갯수만큼 루프를 돌면서 인서트 
		 */
		if(item_id!=null){
			for(int i = 0; item_id.length > i; i++){
				MyPlVO itemVO = new MyPlVO();
				itemVO.setSawon_id((String) paramMap.get("sawon_id"));						//사원 코드
				itemVO.setPlgrp_no(Integer.parseInt((String) paramMap.get("plgrp_no")));	//P/L그룹 코드
				itemVO.setItem_id(item_id[i]);												//제품 코드
				itemVO.setSort_seq(sort_seq[i]);											//정렬 순서
				myPlDAO.insertMyPlList(itemVO);												//My P/L List insert 
			}	
		}
		
		return true;	
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#getPlItemList(java.util.Map)
	 */
	@Override
	public List<MyPlVO> getPlItemList(Map<String, String> paramMap) {
		return myPlDAO.getPlItemList(paramMap);
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#getplItemCnt(java.util.Map)
	 */
	@Override
	public MyPlVO getplItemCnt(Map<String, String> paramMap) {
		return myPlDAO.getplItemCnt(paramMap);
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#getItemAllGridList(java.util.Map)
	 */
	@Override
	public List<MyPlVO> getItemAllGridList(Map<String, String> paramMap) {
		return myPlDAO.getItemAllGridList(paramMap);
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#getItemAllCnt(java.util.Map)
	 */
	@Override
	public MyPlVO getItemAllCnt(Map<String, String> paramMap) {
		return myPlDAO.getItemAllCnt(paramMap);
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#getPlItemInfo(java.util.Map)
	 */
	@Override
	public MyPlVO getPlItemInfo(Map<String, String> paramMap) {
		return myPlDAO.getPlItemInfo(paramMap);
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#getItemOverlapCheck(java.util.Map)
	 */
	@Override
	public MyPlVO getItemOverlapCheck(Map<String, String> paramMap) {
		return myPlDAO.getItemOverlapCheck(paramMap);
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#insertPlItem(com.hanaph.saleon.business.vo.MyPlVO)
	 */
	@Override
	public boolean insertPlItem(MyPlVO myPlVO) {
		myPlDAO.insertPlItem(myPlVO);
		return true;
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#updatePlItem(com.hanaph.saleon.business.vo.MyPlVO)
	 */
	@Override
	public boolean updatePlItem(MyPlVO myPlVO) {
		myPlDAO.updatePlItem(myPlVO);
		return true;
	}
	
	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#deletePlItem(com.hanaph.saleon.business.vo.MyPlVO)
	 */
	@Override
	public boolean deletePlItem(MyPlVO myPlVO) {
		myPlDAO.deletePlItem(myPlVO);
		
		// 기존에 myPL 그룹에 등록된 정보도 삭제.
		
		return true;
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#getPlItemPhoto(java.util.Map)
	 */
	@Override
	public MyPlVO getPlItemPhoto(Map<String, String> paramMap) {
		return myPlDAO.getPlItemPhoto(paramMap);
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#updateItemPhoto(com.hanaph.saleon.business.vo.MyPlVO)
	 */
	@Override
	public int updateItemPhoto(MyPlVO myPlVO) {
		myPlDAO.updateItemPhoto(myPlVO);	
		return 1;	
	}


	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.MyPlService#deleteItemPhoto(com.hanaph.saleon.business.vo.MyPlVO)
	 */
	@Override
	public boolean deleteItemPhoto(MyPlVO myPlVO) {
		myPlDAO.deleteItemPhoto(myPlVO);	
		return true;
	}
}
