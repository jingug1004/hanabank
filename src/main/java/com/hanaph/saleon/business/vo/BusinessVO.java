/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

/**
 * <pre>
 * Class Name : BusinessVO.java
 * 설명 : 영업관리 공통 VO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 24.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 10. 24.
 */
public class BusinessVO {

	/*거래처 검색*/
	private String cust_id; // 거래처 코드
	private String cust_nm; // 거래처 명
	private String sawon_id; // 사원 코드
	private String sawon_nm; // 사원 명
	private String vou_no; // 사업자 번호
	private String president; // 대표자 명
	
	/*상세 거래처명 검색*/
	private String cust_type; // 상세 거래처 명
	
	/*파트 검색*/
	private String part_id; // 파트 코드
	private String part_nm; // 파트 명
	
	/*부서 검색*/
	private String dept_id; // 부서 코드
	private String dept_nm; // 부서 명
	
	/*사원 검색*/
	private String emp_id; // 사원 코드
	private String emp_nm; // 사원 명
	private String gubun; // 재직 구분
	
	/*통합 검색*/
	private String result_nm; // 검색결과
	
	/*사원정보*/
	private String emp_cd; // 사원 코드
	private String dept_cd; // 부서 코드
	private String pda_auth; // pda 접근 권한
	
	/*담보약속*/
	private String req_date; // 주문요청일
	private String promise_date; // 약속기일
	private String status; // 상태
	private String promise_bigo; // 약속내용
	private String return_desc; // 반려사유
	
	/**
	 * @return the cust_id
	 */
	public String getCust_id() {
		return cust_id;
	}
	/**
	 * @param cust_id the cust_id to set
	 */
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	/**
	 * @return the cust_nm
	 */
	public String getCust_nm() {
		return cust_nm;
	}
	/**
	 * @param cust_nm the cust_nm to set
	 */
	public void setCust_nm(String cust_nm) {
		this.cust_nm = cust_nm;
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
	 * @return the sawon_nm
	 */
	public String getSawon_nm() {
		return sawon_nm;
	}
	/**
	 * @param sawon_nm the sawon_nm to set
	 */
	public void setSawon_nm(String sawon_nm) {
		this.sawon_nm = sawon_nm;
	}
	/**
	 * @return the vou_no
	 */
	public String getVou_no() {
		return vou_no;
	}
	/**
	 * @param vou_no the vou_no to set
	 */
	public void setVou_no(String vou_no) {
		this.vou_no = vou_no;
	}
	/**
	 * @return the president
	 */
	public String getPresident() {
		return president;
	}
	/**
	 * @param president the president to set
	 */
	public void setPresident(String president) {
		this.president = president;
	}
	/**
	 * @return the cust_type
	 */
	public String getCust_type() {
		return cust_type;
	}
	/**
	 * @param cust_type the cust_type to set
	 */
	public void setCust_type(String cust_type) {
		this.cust_type = cust_type;
	}
	/**
	 * @return the part_id
	 */
	public String getPart_id() {
		return part_id;
	}
	/**
	 * @param part_id the part_id to set
	 */
	public void setPart_id(String part_id) {
		this.part_id = part_id;
	}
	/**
	 * @return the part_nm
	 */
	public String getPart_nm() {
		return part_nm;
	}
	/**
	 * @param part_nm the part_nm to set
	 */
	public void setPart_nm(String part_nm) {
		this.part_nm = part_nm;
	}
	/**
	 * @return the dept_id
	 */
	public String getDept_id() {
		return dept_id;
	}
	/**
	 * @param dept_id the dept_id to set
	 */
	public void setDept_id(String dept_id) {
		this.dept_id = dept_id;
	}
	/**
	 * @return the dept_nm
	 */
	public String getDept_nm() {
		return dept_nm;
	}
	/**
	 * @param dept_nm the dept_nm to set
	 */
	public void setDept_nm(String dept_nm) {
		this.dept_nm = dept_nm;
	}
	/**
	 * @return the emp_id
	 */
	public String getEmp_id() {
		return emp_id;
	}
	/**
	 * @param emp_id the emp_id to set
	 */
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
	}
	/**
	 * @return the emp_nm
	 */
	public String getEmp_nm() {
		return emp_nm;
	}
	/**
	 * @param emp_nm the emp_nm to set
	 */
	public void setEmp_nm(String emp_nm) {
		this.emp_nm = emp_nm;
	}
	/**
	 * @return the gubun
	 */
	public String getGubun() {
		return gubun;
	}
	/**
	 * @param gubun the gubun to set
	 */
	public void setGubun(String gubun) {
		this.gubun = gubun;
	}
	/**
	 * @return the result_nm
	 */
	public String getResult_nm() {
		return result_nm;
	}
	/**
	 * @param result_nm the result_nm to set
	 */
	public void setResult_nm(String result_nm) {
		this.result_nm = result_nm;
	}
	/**
	 * @return the emp_cd
	 */
	public String getEmp_cd() {
		return emp_cd;
	}
	/**
	 * @param emp_cd the emp_cd to set
	 */
	public void setEmp_cd(String emp_cd) {
		this.emp_cd = emp_cd;
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
	 * @return the pda_auth
	 */
	public String getPda_auth() {
		return pda_auth;
	}
	/**
	 * @param pda_auth the pda_auth to set
	 */
	public void setPda_auth(String pda_auth) {
		this.pda_auth = pda_auth;
	}
	/**
	 * @return the req_date
	 */
	public String getReq_date() {
		return req_date;
	}
	/**
	 * @param req_date the req_date to set
	 */
	public void setReq_date(String req_date) {
		this.req_date = req_date;
	}
	/**
	 * @return the promise_date
	 */
	public String getPromise_date() {
		return promise_date;
	}
	/**
	 * @param promise_date the promise_date to set
	 */
	public void setPromise_date(String promise_date) {
		this.promise_date = promise_date;
	}
	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return the promise_bigo
	 */
	public String getPromise_bigo() {
		return promise_bigo;
	}
	/**
	 * @param promise_bigo the promise_bigo to set
	 */
	public void setPromise_bigo(String promise_bigo) {
		this.promise_bigo = promise_bigo;
	}
	/**
	 * @return the return_desc
	 */
	public String getReturn_desc() {
		return return_desc;
	}
	/**
	 * @param return_desc the return_desc to set
	 */
	public void setReturn_desc(String return_desc) {
		this.return_desc = return_desc;
	}
}
