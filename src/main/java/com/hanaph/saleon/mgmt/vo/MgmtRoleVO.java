/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.mgmt.vo;

import java.util.List;

/**
 * <pre>
 * Class Name : MgmtRoleVO.java
 * 설명 : 권한 등록관리 정보
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
public class MgmtRoleVO {
	
	private int resultCode = 0;     // 결과 코드
    private String role_no;			// 롤 no
	private String role_cat_code;	// 롤 카테고리 코드
	private String role_name;       // 롤명
	private String dept_code;		// 부서 코드
	private String emp_code;		// 사원 코드
	private String emp_name;		// 사원 이름
	private String dept_name;		// 부서 이름
	private String pgm_use_yn;		// 프로그램 사용 여부
	private String pgm_no;			// 프로그램 no
	private String pgm_name;		// 프로그램 명
	private String pgm_id;			// 프로그램 id
	private String pgm_kind_code;	// 프로그램 메뉴 구분
	private String use_btn;			// 버튼 리스트 string형
	private String menu_use_yn;		// 메뉴 사용 여부
	private String newRoleNum;		// 신규 role 번호
	private String type;			// type
	private List<MgmtRoleVO> roleList;	// roleList
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
	 * @return the newRoleNum
	 */
	public String getNewRoleNum() {
		return newRoleNum;
	}
	/**
	 * @param newRoleNum the newRoleNum to set
	 */
	public void setNewRoleNum(String newRoleNum) {
		this.newRoleNum = newRoleNum;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the roleList
	 */
	public List<MgmtRoleVO> getRoleList() {
		return roleList;
	}
	/**
	 * @param roleList the roleList to set
	 */
	public void setRoleList(List<MgmtRoleVO> roleList) {
		this.roleList = roleList;
	}
	
}
