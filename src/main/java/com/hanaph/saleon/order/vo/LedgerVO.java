/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.vo;

/**
 * <pre>
 * Class Name : LedgerVO.java
 * 설명 : 원장조회 관련 Value object.
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
public class LedgerVO {
	
	private String ym;			//년월
	private String ymd;			//거래일자
	private String cust_id;		//거래처 코드
	private String rcust_id;	//실거래처 코드
	private String vou_no;		//사업자번호
	private String cust_nm;		//거래처 명
	private String addr;        //주소
	private String president;   //대표자명
	private String sawon_id;    //사원코드
	private String rcust_nm;    //판매처 이름
	private String item_id;     //품목코드
	private String item_nm;     //품목명
	private String standard;    //기준
	private String qty;			//수량
	private String danga;       //단가
	private String amt;         //금액
	private String vat;         //부가세
	private String sukum;       //수금
	private String tot;         //전체
	private String before_amt;  //이월 금액
	private String deal_no;     //거래번호
	private String dc_amt;      //할인금액
	private String dc_qty;      //할증(할인증정)수량
	private String dc_en_amt;   //할인정리금액
	private String dc_en_yn;    //할인정리여부(Y:정리, N:미정리)
	private String tel;         //전화번호
	private String rem_amt;		//이월금액
	private String bigo;		//비고
	private Integer totalCnt;	//총 count
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
	 * @return the totalCnt
	 */
	public Integer getTotalCnt() {
		return totalCnt;
	}
	/**
	 * @param totalCnt the totalCnt to set
	 */
	public void setTotalCnt(Integer totalCnt) {
		this.totalCnt = totalCnt;
	}
	
}
