/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.mgmt.vo;

import java.util.List;

/**
 * <pre>
 * Class Name : MgmtVO.java
 * 설명 : 프로그램 등록관리 정보
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 4.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2014. 11. 4.
 */
public class MgmtProgramVO {
	
	private int resultCode;					// 응답코드
	private int max_sort;					// 가장 큰 정렬 순위
	private int sort_order;					// 정렬순서
	private String pgm_no;					// 프로그램 no
	private String pgm_id; 					// 프로그램 id        
	private String pgm_name;  				// 프로그램 명         
	private String pgm_kind_code;			// 프로그램 메뉴 구분  
	private String pgm_use_yn; 				// 프로그램 사용여부     
	private String menu_use_yn; 			// 메뉴 사용여부    
	private String parent_pgm;				// 상위 프로그램 코드     
	private String picture; 				// tree icon        
	private String select_picture;			// selected tree icon
	private String use_btn;					// 사용여부
	private String emp_code;				// 사원코드
	private String resultMsg;				// 결과 메시지
	private String[] arrUseBtns;			// 버튼 리스트 텍스트 배열
	private List<MgmtButtonVO> btnList;		// 버튼 리스트
	private List<MgmtProgramVO> menuList;	// 메뉴 리스트
	/**
	 * @return the resultCode
	 */
	public int getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the max_sort
	 */
	public int getMax_sort() {
		return max_sort;
	}
	/**
	 * @param max_sort the max_sort to set
	 */
	public void setMax_sort(int max_sort) {
		this.max_sort = max_sort;
	}
	/**
	 * @return the sort_order
	 */
	public int getSort_order() {
		return sort_order;
	}
	/**
	 * @param sort_order the sort_order to set
	 */
	public void setSort_order(int sort_order) {
		this.sort_order = sort_order;
	}
	/**
	 * @return the pgm_no
	 */
	public String getPgm_no() {
		return pgm_no;
	}
	/**
	 * @param pgm_no the pgm_no to set
	 */
	public void setPgm_no(String pgm_no) {
		this.pgm_no = pgm_no;
	}
	/**
	 * @return the pgm_id
	 */
	public String getPgm_id() {
		return pgm_id;
	}
	/**
	 * @param pgm_id the pgm_id to set
	 */
	public void setPgm_id(String pgm_id) {
		this.pgm_id = pgm_id;
	}
	/**
	 * @return the pgm_name
	 */
	public String getPgm_name() {
		return pgm_name;
	}
	/**
	 * @param pgm_name the pgm_name to set
	 */
	public void setPgm_name(String pgm_name) {
		this.pgm_name = pgm_name;
	}
	/**
	 * @return the pgm_kind_code
	 */
	public String getPgm_kind_code() {
		return pgm_kind_code;
	}
	/**
	 * @param pgm_kind_code the pgm_kind_code to set
	 */
	public void setPgm_kind_code(String pgm_kind_code) {
		this.pgm_kind_code = pgm_kind_code;
	}
	/**
	 * @return the pgm_use_yn
	 */
	public String getPgm_use_yn() {
		return pgm_use_yn;
	}
	/**
	 * @param pgm_use_yn the pgm_use_yn to set
	 */
	public void setPgm_use_yn(String pgm_use_yn) {
		this.pgm_use_yn = pgm_use_yn;
	}
	/**
	 * @return the menu_use_yn
	 */
	public String getMenu_use_yn() {
		return menu_use_yn;
	}
	/**
	 * @param menu_use_yn the menu_use_yn to set
	 */
	public void setMenu_use_yn(String menu_use_yn) {
		this.menu_use_yn = menu_use_yn;
	}
	/**
	 * @return the parent_pgm
	 */
	public String getParent_pgm() {
		return parent_pgm;
	}
	/**
	 * @param parent_pgm the parent_pgm to set
	 */
	public void setParent_pgm(String parent_pgm) {
		this.parent_pgm = parent_pgm;
	}
	/**
	 * @return the picture
	 */
	public String getPicture() {
		return picture;
	}
	/**
	 * @param picture the picture to set
	 */
	public void setPicture(String picture) {
		this.picture = picture;
	}
	/**
	 * @return the select_picture
	 */
	public String getSelect_picture() {
		return select_picture;
	}
	/**
	 * @param select_picture the select_picture to set
	 */
	public void setSelect_picture(String select_picture) {
		this.select_picture = select_picture;
	}
	/**
	 * @return the use_btn
	 */
	public String getUse_btn() {
		return use_btn;
	}
	/**
	 * @param use_btn the use_btn to set
	 */
	public void setUse_btn(String use_btn) {
		this.use_btn = use_btn;
	}
	/**
	 * @return the emp_code
	 */
	public String getEmp_code() {
		return emp_code;
	}
	/**
	 * @param emp_code the emp_code to set
	 */
	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
	}
	/**
	 * @return the resultMsg
	 */
	public String getResultMsg() {
		return resultMsg;
	}
	/**
	 * @param resultMsg the resultMsg to set
	 */
	public void setResultMsg(String resultMsg) {
		this.resultMsg = resultMsg;
	}
	/**
	 * @return the arrUseBtns
	 */
	public String[] getArrUseBtns() {
		return arrUseBtns;
	}
	/**
	 * @param arrUseBtns the arrUseBtns to set
	 */
	public void setArrUseBtns(String[] arrUseBtns) {
		this.arrUseBtns = arrUseBtns;
	}
	/**
	 * @return the btnList
	 */
	public List<MgmtButtonVO> getBtnList() {
		return btnList;
	}
	/**
	 * @param btnList the btnList to set
	 */
	public void setBtnList(List<MgmtButtonVO> btnList) {
		this.btnList = btnList;
	}
	/**
	 * @return the menuList
	 */
	public List<MgmtProgramVO> getMenuList() {
		return menuList;
	}
	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<MgmtProgramVO> menuList) {
		this.menuList = menuList;
	}
	
}
