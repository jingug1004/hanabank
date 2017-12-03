/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.mgmt.vo;

/**
 * <pre>
 * Class Name : MgmtUserVO.java
 * 설명 : 사용자관리 정보
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
public class MgmtUserVO {
	
	private String emp_code;			// 사원코드
	private String copy_code;			// 복사 한 사원 코드
	private String emp_name; 			// 사원 명
	private String dept_code;			// 부서 코드
	private String password;			// 비밀번호
	private String emp_gb;				// 사원 구분
	private String role_no;				// 롤 no
	private String role_name;			// 롤명
	private String use_yn;				// 사용여부
	private int resultCode;				// 응답코드
	private String[] empCodes;			// 사원코드 배열
	private String[] empNames;			// 사원명 배열
	private String[] empPasswords;		// 비밀번호 배열
	private String check_yn;			// 체크박스 체크 여부
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
	 * @return the copy_code
	 */
	public String getCopy_code() {
		return copy_code;
	}
	/**
	 * @param copy_code the copy_code to set
	 */
	public void setCopy_code(String copy_code) {
		this.copy_code = copy_code;
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
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the emp_gb
	 */
	public String getEmp_gb() {
		return emp_gb;
	}
	/**
	 * @param emp_gb the emp_gb to set
	 */
	public void setEmp_gb(String emp_gb) {
		this.emp_gb = emp_gb;
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
	 * @return the use_yn
	 */
	public String getUse_yn() {
		return use_yn;
	}
	/**
	 * @param use_yn the use_yn to set
	 */
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
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
	 * @return the empCodes
	 */
	public String[] getEmpCodes() {
		return empCodes;
	}
	/**
	 * @param empCodes the empCodes to set
	 */
	public void setEmpCodes(String[] empCodes) {
		this.empCodes = empCodes;
	}
	/**
	 * @return the empNames
	 */
	public String[] getEmpNames() {
		return empNames;
	}
	/**
	 * @param empNames the empNames to set
	 */
	public void setEmpNames(String[] empNames) {
		this.empNames = empNames;
	}
	/**
	 * @return the empPasswords
	 */
	public String[] getEmpPasswords() {
		return empPasswords;
	}
	/**
	 * @param empPasswords the empPasswords to set
	 */
	public void setEmpPasswords(String[] empPasswords) {
		this.empPasswords = empPasswords;
	}
	/**
	 * @return the check_yn
	 */
	public String getCheck_yn() {
		return check_yn;
	}
	/**
	 * @param check_yn the check_yn to set
	 */
	public void setCheck_yn(String check_yn) {
		this.check_yn = check_yn;
	}
	
}
