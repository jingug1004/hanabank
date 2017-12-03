/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.vo;

/**
 * <pre>
 * Class Name : ItemVO.java
 * 설명 : 제품정보 관련 Value object.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 12.      jung a Woo          
 * </pre>
 * 
 * @version : 
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 11. 12.
 */
public class ItemVO {
	
	private int ll_max;    //주문번호
	private String ldt_req_date;    //주문요청일
	private String ls_gumae_no;    //주문번호
	private String ls_input_seq;    //detail seq
	private String cust_id;    //거래처 ID
	private String rcust_id;    //실거래처 ID
	private String rcust_nm;    //실거래처 이름
	private String item_id;    //제품코드
	private String item_nm;    //제품명
	private String saupjang_cd;    //회계사업장코드
	private String standard;    //기준
	private String qty;    //수량
	private double bas_amt;    //기준금액
	private double bal_amt;    //발행금액
	private double dc_danga;    //할증(할인증정)수량
	private double percent;    //percent
	private String dc_amt;    //할인금액
	private double supply_net;    //supply_net
	private double supply_vat;    //supply_vat
	private String close_date;    //시행종료일자
	private String mavg_qty;    //mavg_qty
	private String m_qty;    //m_qty
	private String psb_qty;    //psb_qty
	private double tot_amt;    //총금액
	private String ls_item_gb1;    //제품 구분1
	private String ls_yn;    //향정.마약여부
	private String errorMsg;    //에러 메시지
	private int ll_mavg_qty;    //월평균
	private int ll_mqty;    //해당월 주문
	private int ll_psb_qty;    //주문가능수량
	private int li_cnt;    //카운트
	private String ymd;    //년월일
	private String gumae_no;    //구매번호
	private String gumae_gb;    //구매구분
	private String status;    //상태
	private String receipt_gb;    //접수구분(1.접수, 2.승인, 3.반려)
	private String bigo;    //비고
	private String input_seq;    //입력순서
	private String amend_qty;    //수량
	private double danga;    //단가
	private double amt;    //금액
	private double vat;    //부가세
	private double amend_amt;    //금액
	private double amend_vat;    //부가세
	private double amend_tot_amt;    //총금액
	private String rate;    //적용율
	private String unit;    //단위
	private String ls_receipt_gb;    //접수구분(1.접수, 2.승인, 3.반려)
	private String ls_sawon_id;    //사원코드
	private String gs_empCode;    //부서코드
	private String ls_rsawon_id;    //실거래처 사원코드
	private String ls_rcust_cd;    //실거래처 코드
	private double ld_supply_net_sum;    //총금액
	private double ld_supply_vat_sum;    //총부가세
	private String ls_bigo;    //비고
	private String ls_limit_yn;    //제한여부
	private String ls_pro_date;    //거래처약속일자
	private String ls_pro_bigo;    //거래처약속내용
	private String ld_amt_sum;    //총금액
	private String ld_vat_sum;    //총부가세
	private String app_ymd;    //승인발주일자
	private String app_no;    //승인구매번호
	private String ls_chul_yn;    //출하중지(Y-중지,N-중지아님)
	private String ls_use_yn;    //사용여부
	private String ld_jaego_qty;    //조정수량
	private String ld_invjaego_qty;    //제조번호별 재고의 합계
	private String req_qty;    //요청수량
	private String result;    //결과
	private String pbigo; // 거래처약속내용
	/**
	 * @return the ll_max
	 */
	public int getLl_max() {
		return ll_max;
	}
	/**
	 * @param ll_max the ll_max to set
	 */
	public void setLl_max(int ll_max) {
		this.ll_max = ll_max;
	}
	/**
	 * @return the ldt_req_date
	 */
	public String getLdt_req_date() {
		return ldt_req_date;
	}
	/**
	 * @param ldt_req_date the ldt_req_date to set
	 */
	public void setLdt_req_date(String ldt_req_date) {
		this.ldt_req_date = ldt_req_date;
	}
	/**
	 * @return the ls_gumae_no
	 */
	public String getLs_gumae_no() {
		return ls_gumae_no;
	}
	/**
	 * @param ls_gumae_no the ls_gumae_no to set
	 */
	public void setLs_gumae_no(String ls_gumae_no) {
		this.ls_gumae_no = ls_gumae_no;
	}
	/**
	 * @return the ls_input_seq
	 */
	public String getLs_input_seq() {
		return ls_input_seq;
	}
	/**
	 * @param ls_input_seq the ls_input_seq to set
	 */
	public void setLs_input_seq(String ls_input_seq) {
		this.ls_input_seq = ls_input_seq;
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
	 * @return the saupjang_cd
	 */
	public String getSaupjang_cd() {
		return saupjang_cd;
	}
	/**
	 * @param saupjang_cd the saupjang_cd to set
	 */
	public void setSaupjang_cd(String saupjang_cd) {
		this.saupjang_cd = saupjang_cd;
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
	 * @return the bas_amt
	 */
	public double getBas_amt() {
		return bas_amt;
	}
	/**
	 * @param bas_amt the bas_amt to set
	 */
	public void setBas_amt(double bas_amt) {
		this.bas_amt = bas_amt;
	}
	/**
	 * @return the bal_amt
	 */
	public double getBal_amt() {
		return bal_amt;
	}
	/**
	 * @param bal_amt the bal_amt to set
	 */
	public void setBal_amt(double bal_amt) {
		this.bal_amt = bal_amt;
	}
	/**
	 * @return the dc_danga
	 */
	public double getDc_danga() {
		return dc_danga;
	}
	/**
	 * @param dc_danga the dc_danga to set
	 */
	public void setDc_danga(double dc_danga) {
		this.dc_danga = dc_danga;
	}
	/**
	 * @return the percent
	 */
	public double getPercent() {
		return percent;
	}
	/**
	 * @param percent the percent to set
	 */
	public void setPercent(double percent) {
		this.percent = percent;
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
	 * @return the supply_net
	 */
	public double getSupply_net() {
		return supply_net;
	}
	/**
	 * @param supply_net the supply_net to set
	 */
	public void setSupply_net(double supply_net) {
		this.supply_net = supply_net;
	}
	/**
	 * @return the supply_vat
	 */
	public double getSupply_vat() {
		return supply_vat;
	}
	/**
	 * @param supply_vat the supply_vat to set
	 */
	public void setSupply_vat(double supply_vat) {
		this.supply_vat = supply_vat;
	}
	/**
	 * @return the close_date
	 */
	public String getClose_date() {
		return close_date;
	}
	/**
	 * @param close_date the close_date to set
	 */
	public void setClose_date(String close_date) {
		this.close_date = close_date;
	}
	/**
	 * @return the mavg_qty
	 */
	public String getMavg_qty() {
		return mavg_qty;
	}
	/**
	 * @param mavg_qty the mavg_qty to set
	 */
	public void setMavg_qty(String mavg_qty) {
		this.mavg_qty = mavg_qty;
	}
	/**
	 * @return the m_qty
	 */
	public String getM_qty() {
		return m_qty;
	}
	/**
	 * @param m_qty the m_qty to set
	 */
	public void setM_qty(String m_qty) {
		this.m_qty = m_qty;
	}
	/**
	 * @return the psb_qty
	 */
	public String getPsb_qty() {
		return psb_qty;
	}
	/**
	 * @param psb_qty the psb_qty to set
	 */
	public void setPsb_qty(String psb_qty) {
		this.psb_qty = psb_qty;
	}
	/**
	 * @return the tot_amt
	 */
	public double getTot_amt() {
		return tot_amt;
	}
	/**
	 * @param tot_amt the tot_amt to set
	 */
	public void setTot_amt(double tot_amt) {
		this.tot_amt = tot_amt;
	}
	/**
	 * @return the ls_item_gb1
	 */
	public String getLs_item_gb1() {
		return ls_item_gb1;
	}
	/**
	 * @param ls_item_gb1 the ls_item_gb1 to set
	 */
	public void setLs_item_gb1(String ls_item_gb1) {
		this.ls_item_gb1 = ls_item_gb1;
	}
	/**
	 * @return the ls_yn
	 */
	public String getLs_yn() {
		return ls_yn;
	}
	/**
	 * @param ls_yn the ls_yn to set
	 */
	public void setLs_yn(String ls_yn) {
		this.ls_yn = ls_yn;
	}
	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	/**
	 * @return the ll_mavg_qty
	 */
	public int getLl_mavg_qty() {
		return ll_mavg_qty;
	}
	/**
	 * @param ll_mavg_qty the ll_mavg_qty to set
	 */
	public void setLl_mavg_qty(int ll_mavg_qty) {
		this.ll_mavg_qty = ll_mavg_qty;
	}
	/**
	 * @return the ll_mqty
	 */
	public int getLl_mqty() {
		return ll_mqty;
	}
	/**
	 * @param ll_mqty the ll_mqty to set
	 */
	public void setLl_mqty(int ll_mqty) {
		this.ll_mqty = ll_mqty;
	}
	/**
	 * @return the ll_psb_qty
	 */
	public int getLl_psb_qty() {
		return ll_psb_qty;
	}
	/**
	 * @param ll_psb_qty the ll_psb_qty to set
	 */
	public void setLl_psb_qty(int ll_psb_qty) {
		this.ll_psb_qty = ll_psb_qty;
	}
	/**
	 * @return the li_cnt
	 */
	public int getLi_cnt() {
		return li_cnt;
	}
	/**
	 * @param li_cnt the li_cnt to set
	 */
	public void setLi_cnt(int li_cnt) {
		this.li_cnt = li_cnt;
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
	 * @return the gumae_no
	 */
	public String getGumae_no() {
		return gumae_no;
	}
	/**
	 * @param gumae_no the gumae_no to set
	 */
	public void setGumae_no(String gumae_no) {
		this.gumae_no = gumae_no;
	}
	/**
	 * @return the gumae_gb
	 */
	public String getGumae_gb() {
		return gumae_gb;
	}
	/**
	 * @param gumae_gb the gumae_gb to set
	 */
	public void setGumae_gb(String gumae_gb) {
		this.gumae_gb = gumae_gb;
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
	 * @return the receipt_gb
	 */
	public String getReceipt_gb() {
		return receipt_gb;
	}
	/**
	 * @param receipt_gb the receipt_gb to set
	 */
	public void setReceipt_gb(String receipt_gb) {
		this.receipt_gb = receipt_gb;
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
	 * @return the input_seq
	 */
	public String getInput_seq() {
		return input_seq;
	}
	/**
	 * @param input_seq the input_seq to set
	 */
	public void setInput_seq(String input_seq) {
		this.input_seq = input_seq;
	}
	/**
	 * @return the amend_qty
	 */
	public String getAmend_qty() {
		return amend_qty;
	}
	/**
	 * @param amend_qty the amend_qty to set
	 */
	public void setAmend_qty(String amend_qty) {
		this.amend_qty = amend_qty;
	}
	/**
	 * @return the danga
	 */
	public double getDanga() {
		return danga;
	}
	/**
	 * @param danga the danga to set
	 */
	public void setDanga(double danga) {
		this.danga = danga;
	}
	/**
	 * @return the amt
	 */
	public double getAmt() {
		return amt;
	}
	/**
	 * @param amt the amt to set
	 */
	public void setAmt(double amt) {
		this.amt = amt;
	}
	/**
	 * @return the vat
	 */
	public double getVat() {
		return vat;
	}
	/**
	 * @param vat the vat to set
	 */
	public void setVat(double vat) {
		this.vat = vat;
	}
	/**
	 * @return the amend_amt
	 */
	public double getAmend_amt() {
		return amend_amt;
	}
	/**
	 * @param amend_amt the amend_amt to set
	 */
	public void setAmend_amt(double amend_amt) {
		this.amend_amt = amend_amt;
	}
	/**
	 * @return the amend_vat
	 */
	public double getAmend_vat() {
		return amend_vat;
	}
	/**
	 * @param amend_vat the amend_vat to set
	 */
	public void setAmend_vat(double amend_vat) {
		this.amend_vat = amend_vat;
	}
	/**
	 * @return the amend_tot_amt
	 */
	public double getAmend_tot_amt() {
		return amend_tot_amt;
	}
	/**
	 * @param amend_tot_amt the amend_tot_amt to set
	 */
	public void setAmend_tot_amt(double amend_tot_amt) {
		this.amend_tot_amt = amend_tot_amt;
	}
	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}
	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}
	/**
	 * @return the unit
	 */
	public String getUnit() {
		return unit;
	}
	/**
	 * @param unit the unit to set
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}
	/**
	 * @return the ls_receipt_gb
	 */
	public String getLs_receipt_gb() {
		return ls_receipt_gb;
	}
	/**
	 * @param ls_receipt_gb the ls_receipt_gb to set
	 */
	public void setLs_receipt_gb(String ls_receipt_gb) {
		this.ls_receipt_gb = ls_receipt_gb;
	}
	/**
	 * @return the ls_sawon_id
	 */
	public String getLs_sawon_id() {
		return ls_sawon_id;
	}
	/**
	 * @param ls_sawon_id the ls_sawon_id to set
	 */
	public void setLs_sawon_id(String ls_sawon_id) {
		this.ls_sawon_id = ls_sawon_id;
	}
	/**
	 * @return the gs_empCode
	 */
	public String getGs_empCode() {
		return gs_empCode;
	}
	/**
	 * @param gs_empCode the gs_empCode to set
	 */
	public void setGs_empCode(String gs_empCode) {
		this.gs_empCode = gs_empCode;
	}
	/**
	 * @return the ls_rsawon_id
	 */
	public String getLs_rsawon_id() {
		return ls_rsawon_id;
	}
	/**
	 * @param ls_rsawon_id the ls_rsawon_id to set
	 */
	public void setLs_rsawon_id(String ls_rsawon_id) {
		this.ls_rsawon_id = ls_rsawon_id;
	}
	/**
	 * @return the ls_rcust_cd
	 */
	public String getLs_rcust_cd() {
		return ls_rcust_cd;
	}
	/**
	 * @param ls_rcust_cd the ls_rcust_cd to set
	 */
	public void setLs_rcust_cd(String ls_rcust_cd) {
		this.ls_rcust_cd = ls_rcust_cd;
	}
	/**
	 * @return the ld_supply_net_sum
	 */
	public double getLd_supply_net_sum() {
		return ld_supply_net_sum;
	}
	/**
	 * @param ld_supply_net_sum the ld_supply_net_sum to set
	 */
	public void setLd_supply_net_sum(double ld_supply_net_sum) {
		this.ld_supply_net_sum = ld_supply_net_sum;
	}
	/**
	 * @return the ld_supply_vat_sum
	 */
	public double getLd_supply_vat_sum() {
		return ld_supply_vat_sum;
	}
	/**
	 * @param ld_supply_vat_sum the ld_supply_vat_sum to set
	 */
	public void setLd_supply_vat_sum(double ld_supply_vat_sum) {
		this.ld_supply_vat_sum = ld_supply_vat_sum;
	}
	/**
	 * @return the ls_bigo
	 */
	public String getLs_bigo() {
		return ls_bigo;
	}
	/**
	 * @param ls_bigo the ls_bigo to set
	 */
	public void setLs_bigo(String ls_bigo) {
		this.ls_bigo = ls_bigo;
	}
	/**
	 * @return the ls_limit_yn
	 */
	public String getLs_limit_yn() {
		return ls_limit_yn;
	}
	/**
	 * @param ls_limit_yn the ls_limit_yn to set
	 */
	public void setLs_limit_yn(String ls_limit_yn) {
		this.ls_limit_yn = ls_limit_yn;
	}
	/**
	 * @return the ls_pro_date
	 */
	public String getLs_pro_date() {
		return ls_pro_date;
	}
	/**
	 * @param ls_pro_date the ls_pro_date to set
	 */
	public void setLs_pro_date(String ls_pro_date) {
		this.ls_pro_date = ls_pro_date;
	}
	/**
	 * @return the ls_pro_bigo
	 */
	public String getLs_pro_bigo() {
		return ls_pro_bigo;
	}
	/**
	 * @param ls_pro_bigo the ls_pro_bigo to set
	 */
	public void setLs_pro_bigo(String ls_pro_bigo) {
		this.ls_pro_bigo = ls_pro_bigo;
	}
	/**
	 * @return the ld_amt_sum
	 */
	public String getLd_amt_sum() {
		return ld_amt_sum;
	}
	/**
	 * @param ld_amt_sum the ld_amt_sum to set
	 */
	public void setLd_amt_sum(String ld_amt_sum) {
		this.ld_amt_sum = ld_amt_sum;
	}
	/**
	 * @return the ld_vat_sum
	 */
	public String getLd_vat_sum() {
		return ld_vat_sum;
	}
	/**
	 * @param ld_vat_sum the ld_vat_sum to set
	 */
	public void setLd_vat_sum(String ld_vat_sum) {
		this.ld_vat_sum = ld_vat_sum;
	}
	/**
	 * @return the app_ymd
	 */
	public String getApp_ymd() {
		return app_ymd;
	}
	/**
	 * @param app_ymd the app_ymd to set
	 */
	public void setApp_ymd(String app_ymd) {
		this.app_ymd = app_ymd;
	}
	/**
	 * @return the app_no
	 */
	public String getApp_no() {
		return app_no;
	}
	/**
	 * @param app_no the app_no to set
	 */
	public void setApp_no(String app_no) {
		this.app_no = app_no;
	}
	/**
	 * @return the ls_chul_yn
	 */
	public String getLs_chul_yn() {
		return ls_chul_yn;
	}
	/**
	 * @param ls_chul_yn the ls_chul_yn to set
	 */
	public void setLs_chul_yn(String ls_chul_yn) {
		this.ls_chul_yn = ls_chul_yn;
	}
	/**
	 * @return the ls_use_yn
	 */
	public String getLs_use_yn() {
		return ls_use_yn;
	}
	/**
	 * @param ls_use_yn the ls_use_yn to set
	 */
	public void setLs_use_yn(String ls_use_yn) {
		this.ls_use_yn = ls_use_yn;
	}
	/**
	 * @return the ld_jaego_qty
	 */
	public String getLd_jaego_qty() {
		return ld_jaego_qty;
	}
	/**
	 * @param ld_jaego_qty the ld_jaego_qty to set
	 */
	public void setLd_jaego_qty(String ld_jaego_qty) {
		this.ld_jaego_qty = ld_jaego_qty;
	}
	/**
	 * @return the ld_invjaego_qty
	 */
	public String getLd_invjaego_qty() {
		return ld_invjaego_qty;
	}
	/**
	 * @param ld_invjaego_qty the ld_invjaego_qty to set
	 */
	public void setLd_invjaego_qty(String ld_invjaego_qty) {
		this.ld_invjaego_qty = ld_invjaego_qty;
	}
	/**
	 * @return the req_qty
	 */
	public String getReq_qty() {
		return req_qty;
	}
	/**
	 * @param req_qty the req_qty to set
	 */
	public void setReq_qty(String req_qty) {
		this.req_qty = req_qty;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the pbigo
	 */
	public String getPbigo() {
		return pbigo;
	}
	/**
	 * @param pbigo the pbigo to set
	 */
	public void setPbigo(String pbigo) {
		this.pbigo = pbigo;
	}
	
	
}
