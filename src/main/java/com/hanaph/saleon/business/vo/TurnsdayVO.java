/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

/**
 * <pre>
 * Class Name : SaleVO.java
 * 설명 : 판매현황 VO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 22.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 10. 22.
 */
public class TurnsdayVO {
	
	private String ym; // 거래월
	private String ymd; // 거래일자
	private String ymd_fr; // 거래일자 조회 시작
	private String ymd_to; // 거래일자 조회 마지막
	private String deal_gb; // 거래 구분
	private String cust_gb1; // 거래처 구분1
	private String cust_id; // 거래처 코드
	private String cust_nm; // 거래처명
	private String vou_no; // 사업자 번호
	private String rcust_id; // 납품처 코드
	private String rcust_nm; // 납품처 명
	private String sawon_id; // 사원 코드
	private String sawon_nm; // 사원명
	private String dept_cd; // 부서코드
	private String sawon_idh; // 담당 사원
	private String sawon_nmh; // 담당 사원 명
	private String dept_cdh; // 담당 사원 부서
	private String item_id; // 제품 코드
	private String item_nm; // 제품 명
	private String standard; // 단위
	private String qty; // 수량
	private String dc_qty; // 할증 수량
	private String dc_amt; // 할인액
	private String dc_en_yn; // 할인정리여부
	private String gc_en_yn; //보상정리여부(Y:정리, N:미정리)
	private String danga; // 단가
	private String amt; // 공급가액
	private String vat; // 세액
	private String rcust_gb1; // 거래처 구분1
	private String rcust_gb1h; // 거래처 구분1
	private String tot; // 합계금액
	private int total_cnt; // 목록 총 수
	private String total_amt; // 총 공급가액 
	private String total_vat; // 총 세액
	private String total_tot; // 총 합계금액
	private String total_dc_amt; // 총 할인액
	private String bal_amt; // 발행단가
	private String qty_yul; // 발행단가 할인율
	private String yak_amt; // 약정단가
	private String amt_yul; // 약정단가 할인율
	private String act_sale; // 실매출액
	private String danga_cha; // 단가차액
	private String hal_amt; // 할인금액
	private String bosang_amt; // 보상금액
	private String bosang_yul; // 보상금액 할인율
	private String c_amt; // c금액
	private String c; // C율
	private String gc_amt; //gc금액 
	private String gc; // gc율
	private String etc; // 별도율
	private String etc_amt; // 별도금액
	private String bigo; // 비고
	
	
	
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
	 * @return the ymd_to
	 */
	public String getYmd_to() {
		return ymd_to;
	}
	/**
	 * @param ymd_to the ymd_to to set
	 */
	public void setYmd_to(String ymd_to) {
		this.ymd_to = ymd_to;
	}
	/**
	 * @return the deal_gb
	 */
	public String getDeal_gb() {
		return deal_gb;
	}
	/**
	 * @param deal_gb the deal_gb to set
	 */
	public void setDeal_gb(String deal_gb) {
		this.deal_gb = deal_gb;
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
	public String getTotal_amt() {
		return total_amt;
	}
	/**
	 * @param total_amt the total_amt to set
	 */
	public void setTotal_amt(String total_amt) {
		this.total_amt = total_amt;
	}
	/**
	 * @return the total_vat
	 */
	public String getTotal_vat() {
		return total_vat;
	}
	/**
	 * @param total_vat the total_vat to set
	 */
	public void setTotal_vat(String total_vat) {
		this.total_vat = total_vat;
	}
	/**
	 * @return the total_tot
	 */
	public String getTotal_tot() {
		return total_tot;
	}
	/**
	 * @param total_tot the total_tot to set
	 */
	public void setTotal_tot(String total_tot) {
		this.total_tot = total_tot;
	}
	/**
	 * @return the total_dc_amt
	 */
	public String getTotal_dc_amt() {
		return total_dc_amt;
	}
	/**
	 * @param total_dc_amt the total_dc_amt to set
	 */
	public void setTotal_dc_amt(String total_dc_amt) {
		this.total_dc_amt = total_dc_amt;
	}
	/**
	 * @return the ymd_fr
	 */
	public String getYmd_fr() {
		return ymd_fr;
	}
	/**
	 * @param ymd_fr the ymd_fr to set
	 */
	public void setYmd_fr(String ymd_fr) {
		this.ymd_fr = ymd_fr;
	}
	/**
	 * @return the gc_en_yn
	 */
	public String getGc_en_yn() {
		return gc_en_yn;
	}
	/**
	 * @param gc_en_yn the gc_en_yn to set
	 */
	public void setGc_en_yn(String gc_en_yn) {
		this.gc_en_yn = gc_en_yn;
	}
	/**
	 * @return the bal_amt
	 */
	public String getBal_amt() {
		return bal_amt;
	}
	/**
	 * @param bal_amt the bal_amt to set
	 */
	public void setBal_amt(String bal_amt) {
		this.bal_amt = bal_amt;
	}
	/**
	 * @return the qty_yul
	 */
	public String getQty_yul() {
		return qty_yul;
	}
	/**
	 * @param qty_yul the qty_yul to set
	 */
	public void setQty_yul(String qty_yul) {
		this.qty_yul = qty_yul;
	}
	/**
	 * @return the yak_amt
	 */
	public String getYak_amt() {
		return yak_amt;
	}
	/**
	 * @param yak_amt the yak_amt to set
	 */
	public void setYak_amt(String yak_amt) {
		this.yak_amt = yak_amt;
	}
	/**
	 * @return the amt_yul
	 */
	public String getAmt_yul() {
		return amt_yul;
	}
	/**
	 * @param amt_yul the amt_yul to set
	 */
	public void setAmt_yul(String amt_yul) {
		this.amt_yul = amt_yul;
	}
	/**
	 * @return the act_sale
	 */
	public String getAct_sale() {
		return act_sale;
	}
	/**
	 * @param act_sale the act_sale to set
	 */
	public void setAct_sale(String act_sale) {
		this.act_sale = act_sale;
	}
	/**
	 * @return the danga_cha
	 */
	public String getDanga_cha() {
		return danga_cha;
	}
	/**
	 * @param danga_cha the danga_cha to set
	 */
	public void setDanga_cha(String danga_cha) {
		this.danga_cha = danga_cha;
	}
	/**
	 * @return the hal_amt
	 */
	public String getHal_amt() {
		return hal_amt;
	}
	/**
	 * @param hal_amt the hal_amt to set
	 */
	public void setHal_amt(String hal_amt) {
		this.hal_amt = hal_amt;
	}
	/**
	 * @return the bosang_amt
	 */
	public String getBosang_amt() {
		return bosang_amt;
	}
	/**
	 * @param bosang_amt the bosang_amt to set
	 */
	public void setBosang_amt(String bosang_amt) {
		this.bosang_amt = bosang_amt;
	}
	/**
	 * @return the bosang_yul
	 */
	public String getBosang_yul() {
		return bosang_yul;
	}
	/**
	 * @param bosang_yul the bosang_yul to set
	 */
	public void setBosang_yul(String bosang_yul) {
		this.bosang_yul = bosang_yul;
	}
	/**
	 * @return the c_amt
	 */
	public String getC_amt() {
		return c_amt;
	}
	/**
	 * @param c_amt the c_amt to set
	 */
	public void setC_amt(String c_amt) {
		this.c_amt = c_amt;
	}
	/**
	 * @return the c
	 */
	public String getC() {
		return c;
	}
	/**
	 * @param c the c to set
	 */
	public void setC(String c) {
		this.c = c;
	}
	/**
	 * @return the gc_amt
	 */
	public String getGc_amt() {
		return gc_amt;
	}
	/**
	 * @param gc_amt the gc_amt to set
	 */
	public void setGc_amt(String gc_amt) {
		this.gc_amt = gc_amt;
	}
	/**
	 * @return the gc
	 */
	public String getGc() {
		return gc;
	}
	/**
	 * @param gc the gc to set
	 */
	public void setGc(String gc) {
		this.gc = gc;
	}
	/**
	 * @return the etc
	 */
	public String getEtc() {
		return etc;
	}
	/**
	 * @param etc the etc to set
	 */
	public void setEtc(String etc) {
		this.etc = etc;
	}
	/**
	 * @return the etc_amt
	 */
	public String getEtc_amt() {
		return etc_amt;
	}
	/**
	 * @param etc_amt the etc_amt to set
	 */
	public void setEtc_amt(String etc_amt) {
		this.etc_amt = etc_amt;
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
}
