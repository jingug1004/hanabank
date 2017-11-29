/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

/**
 * <pre>
 * Class Name : CustomerLedgerVO.java
 * 설명 : 거래처원장조회 VO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 14.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 11. 14.
 */
public class CustomerLedgerVO {
	
	private String ym; // 거래월
	private String ymd; // 거래일자
	private String rcust_id; // 판매처 코드
	private String cust_id; // 거래처 코드
	private String vou_no; // 사업자 번호
	private String rcust_nm; // 판매처명
	private String addr; // 주소
	private String president; // 대표자명
	private String sawon_id; // 사원 코드
	private String sawon_nm; // 사원 명
	private String dept_nm; // 부서 명
	private String cust_nm; // 거래처명
	private String item_id; // 제품 코드
	private String item_nm; // 제품 명
	private String standard; // 단위
	private String qty; // 수량
	private String danga; // 단가
	private String amt; // 공급가액
	private String vat; // 세액
	private String sukum; // 수금액
	private String tot; // 합계금액
	private String before_amt; // 이월잔액
	private String deal_no; // 거래번호
	private String dc_amt; // 할인액
	private String dc_qty; // 할증수량
	private String dc_en_amt; // 할인정리금액
	private String dc_en_yn; // 할인정리여부
	private String tel; // 전화번호
	private String rem_amt; // 잔액
	private String bigo; // 비고
	private int total_cnt; // 목록 총 수
	private int total_amt; // 총 공급가액
	private int total_vat; // 총 세액
	private int total_tot; // 총 합계금액
	private int total_sukum; // 총 수금액
	private int total_dc_amt; // 총 할인액
	

	
	
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
	 * @return the addr
	 */
	public String getAddr() {
		return addr;
	}
	/**
	 * @param addr the addr to set
	 */
	public void setAddr(String addr) {
		this.addr = addr;
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
	 * @return the item_nm
	 */
	public String getItem_nm() {
		return item_nm;
	}
	/**
	 * @param item_nm the item_nm to set
	 */
	public void setItem_nm(String item_nm) {
		this.item_nm = item_nm;
	}
	/**
	 * @return the standard
	 */
	public String getStandard() {
		return standard;
	}
	/**
	 * @param standard the standard to set
	 */
	public void setStandard(String standard) {
		this.standard = standard;
	}
	/**
	 * @return the qty
	 */
	public String getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(String qty) {
		this.qty = qty;
	}
	/**
	 * @return the danga
	 */
	public String getDanga() {
		return danga;
	}
	/**
	 * @param danga the danga to set
	 */
	public void setDanga(String danga) {
		this.danga = danga;
	}
	/**
	 * @return the amt
	 */
	public String getAmt() {
		return amt;
	}
	/**
	 * @param amt the amt to set
	 */
	public void setAmt(String amt) {
		this.amt = amt;
	}
	/**
	 * @return the vat
	 */
	public String getVat() {
		return vat;
	}
	/**
	 * @param vat the vat to set
	 */
	public void setVat(String vat) {
		this.vat = vat;
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
	 * @return the before_amt
	 */
	public String getBefore_amt() {
		return before_amt;
	}
	/**
	 * @param before_amt the before_amt to set
	 */
	public void setBefore_amt(String before_amt) {
		this.before_amt = before_amt;
	}
	/**
	 * @return the deal_no
	 */
	public String getDeal_no() {
		return deal_no;
	}
	/**
	 * @param deal_no the deal_no to set
	 */
	public void setDeal_no(String deal_no) {
		this.deal_no = deal_no;
	}
	/**
	 * @return the dc_amt
	 */
	public String getDc_amt() {
		return dc_amt;
	}
	/**
	 * @param dc_amt the dc_amt to set
	 */
	public void setDc_amt(String dc_amt) {
		this.dc_amt = dc_amt;
	}
	/**
	 * @return the dc_qty
	 */
	public String getDc_qty() {
		return dc_qty;
	}
	/**
	 * @param dc_qty the dc_qty to set
	 */
	public void setDc_qty(String dc_qty) {
		this.dc_qty = dc_qty;
	}
	/**
	 * @return the dc_en_amt
	 */
	public String getDc_en_amt() {
		return dc_en_amt;
	}
	/**
	 * @param dc_en_amt the dc_en_amt to set
	 */
	public void setDc_en_amt(String dc_en_amt) {
		this.dc_en_amt = dc_en_amt;
	}
	/**
	 * @return the dc_en_yn
	 */
	public String getDc_en_yn() {
		return dc_en_yn;
	}
	/**
	 * @param dc_en_yn the dc_en_yn to set
	 */
	public void setDc_en_yn(String dc_en_yn) {
		this.dc_en_yn = dc_en_yn;
	}
	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}
	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}
	/**
	 * @return the rem_amt
	 */
	public String getRem_amt() {
		return rem_amt;
	}
	/**
	 * @param rem_amt the rem_amt to set
	 */
	public void setRem_amt(String rem_amt) {
		this.rem_amt = rem_amt;
	}
	/**
	 * @return the bigo
	 */
	public String getBigo() {
		return bigo;
	}
	/**
	 * @param bigo the bigo to set
	 */
	public void setBigo(String bigo) {
		this.bigo = bigo;
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
	 * @return the total_amt
	 */
	public int getTotal_amt() {
		return total_amt;
	}
	/**
	 * @param total_amt the total_amt to set
	 */
	public void setTotal_amt(int total_amt) {
		this.total_amt = total_amt;
	}
	/**
	 * @return the total_vat
	 */
	public int getTotal_vat() {
		return total_vat;
	}
	/**
	 * @param total_vat the total_vat to set
	 */
	public void setTotal_vat(int total_vat) {
		this.total_vat = total_vat;
	}
	/**
	 * @return the total_tot
	 */
	public int getTotal_tot() {
		return total_tot;
	}
	/**
	 * @param total_tot the total_tot to set
	 */
	public void setTotal_tot(int total_tot) {
		this.total_tot = total_tot;
	}
	/**
	 * @return the total_sukum
	 */
	public int getTotal_sukum() {
		return total_sukum;
	}
	/**
	 * @param total_sukum the total_sukum to set
	 */
	public void setTotal_sukum(int total_sukum) {
		this.total_sukum = total_sukum;
	}
	/**
	 * @return the total_dc_amt
	 */
	public int getTotal_dc_amt() {
		return total_dc_amt;
	}
	/**
	 * @param total_dc_amt the total_dc_amt to set
	 */
	public void setTotal_dc_amt(int total_dc_amt) {
		this.total_dc_amt = total_dc_amt;
	}
}
