/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

/**
 * <pre>
 * Class Name : CollectionVO.java
 * 설명 : 수금현황 VO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 28.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 10. 28.
 */
public class CollectionVO {
	
	private String ym; // 거래월
	private String ymd; // 거래일자
	private String item_id; // 제품 코드
	private String cust_id; // 거래처 코드
	private String cust_nm; // 거래처 명
	private String vou_no; // 사업자 번호
	private String rcust_id; // 판매처 코드
	private String rcust_nm; // 판매처 명
	private String sawon_id; // 사원 코드
	private String sawon_nm; // 사원 명
	private String dept_cd; // 부서 코드
	private String dept_nm; // 부서 명
	private String sawon_idh; // 담당 사원
	private String sawon_nmh; // 담당 사원명
	private String dept_cdh; // 담당 사원 부서
	private String dept_cmh; // 담당 사원 부서명
	private String sukum; // 수금액
	private String tot; // 합계금액
	private String bill_no; // 어음 번호
	private String end_ymd; // 만기일
	private String cust_gb1; // 거래처 구분1
	private String rcust_gb1; // 거래처 구분1
	private String rcust_gb1h; // 거래처 구분1
	private String balhang; // 발행처
	private int total_cnt; // 목록 총 수
	private String total_sukum; // 총 수금액
	
	/**
	 * @return the ym
	 */
	public String getYm() {
		return ym;
	}
	/**
	 * @param ym the ym to set
	 */
	public void setYm(String ym) {
		this.ym = ym;
	}
	/**
	 * @return the ymd
	 */
	public String getYmd() {
		return ymd;
	}
	/**
	 * @param ymd the ymd to set
	 */
	public void setYmd(String ymd) {
		this.ymd = ymd;
	}
	/**
	 * @return the item_id
	 */
	public String getItem_id() {
		return item_id;
	}
	/**
	 * @param item_id the item_id to set
	 */
	public void setItem_id(String item_id) {
		this.item_id = item_id;
	}
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
	 * @return the rcust_id
	 */
	public String getRcust_id() {
		return rcust_id;
	}
	/**
	 * @param rcust_id the rcust_id to set
	 */
	public void setRcust_id(String rcust_id) {
		this.rcust_id = rcust_id;
	}
	/**
	 * @return the rcust_nm
	 */
	public String getRcust_nm() {
		return rcust_nm;
	}
	/**
	 * @param rcust_nm the rcust_nm to set
	 */
	public void setRcust_nm(String rcust_nm) {
		this.rcust_nm = rcust_nm;
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
	 * @return the sawon_idh
	 */
	public String getSawon_idh() {
		return sawon_idh;
	}
	/**
	 * @param sawon_idh the sawon_idh to set
	 */
	public void setSawon_idh(String sawon_idh) {
		this.sawon_idh = sawon_idh;
	}
	/**
	 * @return the sawon_nmh
	 */
	public String getSawon_nmh() {
		return sawon_nmh;
	}
	/**
	 * @param sawon_nmh the sawon_nmh to set
	 */
	public void setSawon_nmh(String sawon_nmh) {
		this.sawon_nmh = sawon_nmh;
	}
	/**
	 * @return the dept_cdh
	 */
	public String getDept_cdh() {
		return dept_cdh;
	}
	/**
	 * @param dept_cdh the dept_cdh to set
	 */
	public void setDept_cdh(String dept_cdh) {
		this.dept_cdh = dept_cdh;
	}
	/**
	 * @return the dept_cmh
	 */
	public String getDept_cmh() {
		return dept_cmh;
	}
	/**
	 * @param dept_cmh the dept_cmh to set
	 */
	public void setDept_cmh(String dept_cmh) {
		this.dept_cmh = dept_cmh;
	}
	/**
	 * @return the sukum
	 */
	public String getSukum() {
		return sukum;
	}
	/**
	 * @param sukum the sukum to set
	 */
	public void setSukum(String sukum) {
		this.sukum = sukum;
	}
	/**
	 * @return the tot
	 */
	public String getTot() {
		return tot;
	}
	/**
	 * @param tot the tot to set
	 */
	public void setTot(String tot) {
		this.tot = tot;
	}
	/**
	 * @return the bill_no
	 */
	public String getBill_no() {
		return bill_no;
	}
	/**
	 * @param bill_no the bill_no to set
	 */
	public void setBill_no(String bill_no) {
		this.bill_no = bill_no;
	}
	/**
	 * @return the end_ymd
	 */
	public String getEnd_ymd() {
		return end_ymd;
	}
	/**
	 * @param end_ymd the end_ymd to set
	 */
	public void setEnd_ymd(String end_ymd) {
		this.end_ymd = end_ymd;
	}
	/**
	 * @return the cust_gb1
	 */
	public String getCust_gb1() {
		return cust_gb1;
	}
	/**
	 * @param cust_gb1 the cust_gb1 to set
	 */
	public void setCust_gb1(String cust_gb1) {
		this.cust_gb1 = cust_gb1;
	}
	/**
	 * @return the rcust_gb1
	 */
	public String getRcust_gb1() {
		return rcust_gb1;
	}
	/**
	 * @param rcust_gb1 the rcust_gb1 to set
	 */
	public void setRcust_gb1(String rcust_gb1) {
		this.rcust_gb1 = rcust_gb1;
	}
	/**
	 * @return the rcust_gb1h
	 */
	public String getRcust_gb1h() {
		return rcust_gb1h;
	}
	/**
	 * @param rcust_gb1h the rcust_gb1h to set
	 */
	public void setRcust_gb1h(String rcust_gb1h) {
		this.rcust_gb1h = rcust_gb1h;
	}
	/**
	 * @return the balhang
	 */
	public String getBalhang() {
		return balhang;
	}
	/**
	 * @param balhang the balhang to set
	 */
	public void setBalhang(String balhang) {
		this.balhang = balhang;
	}
	/**
	 * @return the total_cnt
	 */
	public int getTotal_cnt() {
		return total_cnt;
	}
	/**
	 * @param total_cnt the total_cnt to set
	 */
	public void setTotal_cnt(int total_cnt) {
		this.total_cnt = total_cnt;
	}
	/**
	 * @return the total_sukum
	 */
	public String getTotal_sukum() {
		return total_sukum;
	}
	/**
	 * @param total_sukum the total_sukum to set
	 */
	public void setTotal_sukum(String total_sukum) {
		this.total_sukum = total_sukum;
	}
}
