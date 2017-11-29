/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.member.vo;

/**
 * <pre>
 * Class Name : LoginUserVO.java
 * 설명 : 로그인, 회원정보 관련 Value object Class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 16.      KIMJAEKAP          
 * </pre>
 * 
 * @version : 
 * @author  : KIMJAEKAP(slamwin@irush.co.kr)
 * @since   : 2014. 10. 16.
 */
public class LoginUserVO {
	private String emp_code;	//사원코드
	private String emp_name;	//사원명
	private String dept_code;	//사원부서
	private String dept_name;	//사원이름
	private String part_gb;		//파트코드
	private String part_name;	//파트명
	private String password;	//비밀번호
	private String emp_gb;		//사원구분. 0-관리자, 1-거래처, 000-관리자, 111-임원진 , 999-영업사원
	private int resultcode;		//결과코드
	private String message;		//메시지
	private String grade_code;	// 직급코드
	private String grade_name;	// 직급명
	private String sawon_id;    // SALE.SALE0007 사원 ID
	private String use_yn;    // SALE0003 의 거래처사용여부
	private String dept_cd; //hanahr에 있는 부서정보
	private String assgn_cd; //hanahr에 있는 직책코드
	private String assgn_name; //hanahr에 있는 직책코드명
	
	private String out_CODE; // 프로시저 결과 코드
	private String out_MSG; // 프로시저 결과 메세지
	private String out_COUNT; // 프로시저 결과 카운트
	
	public String getAssgn_name() {
		return assgn_name;
	}
	public void setAssgn_name(String assgn_name) {
		this.assgn_name = assgn_name;
	}
	public String getAssgn_cd() {
		return assgn_cd;
	}
	public void setAssgn_cd(String assgn_cd) {
		this.assgn_cd = assgn_cd;
	}
	/**
	 * @return the dept_cd
	 */
	public String getDept_cd() {
		return dept_cd;
	}
	/**
	 * @param dept_cd the dept_cd to set
	 */
	public void setDept_cd(String dept_cd) {
		this.dept_cd = dept_cd;
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
	 * @return the part_gb
	 */
	public String getPart_gb() {
		return part_gb;
	}
	/**
	 * @param part_gb the part_gb to set
	 */
	public void setPart_gb(String part_gb) {
		this.part_gb = part_gb;
	}
	/**
	 * @return the part_name
	 */
	public String getPart_name() {
		return part_name;
	}
	/**
	 * @param part_name the part_name to set
	 */
	public void setPart_name(String part_name) {
		this.part_name = part_name;
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
	 * @return the resultcode
	 */
	public int getResultcode() {
		return resultcode;
	}
	/**
	 * @param resultcode the resultcode to set
	 */
	public void setResultcode(int resultcode) {
		this.resultcode = resultcode;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the grade_code
	 */
	public String getGrade_code() {
		return grade_code;
	}
	/**
	 * @param grade_code the grade_code to set
	 */
	public void setGrade_code(String grade_code) {
		this.grade_code = grade_code;
	}
	/**
	 * @return the grade_name
	 */
	public String getGrade_name() {
		return grade_name;
	}
	/**
	 * @param grade_name the grade_name to set
	 */
	public void setGrade_name(String grade_name) {
		this.grade_name = grade_name;
	}
	/**
	 * @return the sawon_id
	 */
	public String getSawon_id() {
		return sawon_id;
	}
	/**
	 * @param sawon_id the sawon_id to set
	 */
	public void setSawon_id(String sawon_id) {
		this.sawon_id = sawon_id;
	}
	/**
	 * @return the out_CODE
	 */
	public String getOut_CODE() {
		return out_CODE;
	}
	/**
	 * @param out_CODE the out_CODE to set
	 */
	public void setOut_CODE(String out_CODE) {
		this.out_CODE = out_CODE;
	}
	/**
	 * @return the out_MSG
	 */
	public String getOut_MSG() {
		return out_MSG;
	}
	/**
	 * @param out_MSG the out_MSG to set
	 */
	public void setOut_MSG(String out_MSG) {
		this.out_MSG = out_MSG;
	}
	/**
	 * @return the out_COUNT
	 */
	public String getOut_COUNT() {
		return out_COUNT;
	}
	/**
	 * @param out_COUNT the out_COUNT to set
	 */
	public void setOut_COUNT(String out_COUNT) {
		this.out_COUNT = out_COUNT;
	}
	
}
