/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.mgmt.vo;

import java.util.List;

/**
 * <pre>
 * Class Name : MgmtInquireVO.java
 * 설명 : 권한조회 정보
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015. 1. 9.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2015. 1. 9.
 */
public class MgmtInquireVO {
	
	private int resultCode = 0;
	private int sort_order;
	private String pgm_no;
	private String pgm_id;         
	private String pgm_name;        
	private String pgm_kind_code;   
	private String parent_pgm;  
	private String picture;         
	private String select_picture;
	private String role_no;
	private String role_cat_code;
	private String role_name;
	private String emp_code;
	private String dept_code;
	private String emp_name;
	private String dept_name;
	private List<MgmtInquireVO> menuList;
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
	 * @return the role_no
	 */
	public String getRole_no() {
		return role_no;
	}
	/**
	 * @param role_no the role_no to set
	 */
	public void setRole_no(String role_no) {
		this.role_no = role_no;
	}
	/**
	 * @return the role_cat_code
	 */
	public String getRole_cat_code() {
		return role_cat_code;
	}
	/**
	 * @param role_cat_code the role_cat_code to set
	 */
	public void setRole_cat_code(String role_cat_code) {
		this.role_cat_code = role_cat_code;
	}
	/**
	 * @return the role_name
	 */
	public String getRole_name() {
		return role_name;
	}
	/**
	 * @param role_name the role_name to set
	 */
	public void setRole_name(String role_name) {
		this.role_name = role_name;
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
	 * @return the dept_code
	 */
	public String getDept_code() {
		return dept_code;
	}
	/**
	 * @param dept_code the dept_code to set
	 */
	public void setDept_code(String dept_code) {
		this.dept_code = dept_code;
	}
	/**
	 * @return the emp_name
	 */
	public String getEmp_name() {
		return emp_name;
	}
	/**
	 * @param emp_name the emp_name to set
	 */
	public void setEmp_name(String emp_name) {
		this.emp_name = emp_name;
	}
	/**
	 * @return the dept_name
	 */
	public String getDept_name() {
		return dept_name;
	}
	/**
	 * @param dept_name the dept_name to set
	 */
	public void setDept_name(String dept_name) {
		this.dept_name = dept_name;
	}
	/**
	 * @return the menuList
	 */
	public List<MgmtInquireVO> getMenuList() {
		return menuList;
	}
	/**
	 * @param menuList the menuList to set
	 */
	public void setMenuList(List<MgmtInquireVO> menuList) {
		this.menuList = menuList;
	}
	
}
