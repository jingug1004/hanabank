/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

/**
 * <pre>
 * Class Name : OrderApprovalSearchVO.java
 * 설명 : 주문/승인 조회 VO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 26.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 12. 26.
 */
public class OrderApprovalSearchVO {
	
	private String cust_id; // 거래처 코드
	private String rcust_id; // 판매처 코드
	private String cust_nm; // 거래처 명
	private String rcust_nm; // 판매처 명
	private String gumae_no; // 접수번호
	private String ymd; // 주문요청일
	private String req_time; // 주문시간
	private String gumae_gb; // 주문종류
	private String status; // 주문상태
	private String app_ymd; // 주문승인일
	private String app_no; // 승인번호
	private String return_desc; // 승인/반려 사유
	private String slip_gb; // 주문구분
	private String wiban_order_req_yn; // 위반주문
	private String wiban_order_conf_yn; // 팀장승인
	private String bigo; // 비고
	private String asawon_id; // 승인사원 코드
	private String asawon_nm; // 승인사원명
	private String app_time; // 승인일시
	
	private String item_id; // 제품코드
	private String item_nm; // 제품명
	private String standard; // 규격
	private String qty; // 수량
	private String danga; // 단가
	private String amt; // 공급가액
	private String vat; // 세액
	private String tot_amt; // 총 공급총액
	private String rate; // 사전 %
	private String dc_danga; // 할인단가
	private String dc_amt; // 할인액
	private String req_qty; // 요청수량
	private String color; // 규격 바탕색
	
	private int total_cnt; // 목록 총 수
	private String total_amt; // 총 공급가액
	private String total_vat; // 총 세액
	private String total_tot_amt; // 총 공급총액
	private String total_dc_amt; // 총 할인액
	
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
	 * @return the req_time
	 */
	public String getReq_time() {
		return req_time;
	}
	/**
	 * @param req_time the req_time to set
	 */
	public void setReq_time(String req_time) {
		this.req_time = req_time;
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
	/**
	 * @return the wiban_order_req_yn
	 */
	public String getWiban_order_req_yn() {
		return wiban_order_req_yn;
	}
	/**
	 * @param wiban_order_req_yn the wiban_order_req_yn to set
	 */
	public void setWiban_order_req_yn(String wiban_order_req_yn) {
		this.wiban_order_req_yn = wiban_order_req_yn;
	}
	/**
	 * @return the wiban_order_conf_yn
	 */
	public String getWiban_order_conf_yn() {
		return wiban_order_conf_yn;
	}
	/**
	 * @param wiban_order_conf_yn the wiban_order_conf_yn to set
	 */
	public void setWiban_order_conf_yn(String wiban_order_conf_yn) {
		this.wiban_order_conf_yn = wiban_order_conf_yn;
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
	 * @return the asawon_id
	 */
	public String getAsawon_id() {
		return asawon_id;
	}
	/**
	 * @param asawon_id the asawon_id to set
	 */
	public void setAsawon_id(String asawon_id) {
		this.asawon_id = asawon_id;
	}
	/**
	 * @return the asawon_nm
	 */
	public String getAsawon_nm() {
		return asawon_nm;
	}
	/**
	 * @param asawon_nm the asawon_nm to set
	 */
	public void setAsawon_nm(String asawon_nm) {
		this.asawon_nm = asawon_nm;
	}
	/**
	 * @return the app_time
	 */
	public String getApp_time() {
		return app_time;
	}
	/**
	 * @param app_time the app_time to set
	 */
	public void setApp_time(String app_time) {
		this.app_time = app_time;
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
	 * @return the tot_amt
	 */
	public String getTot_amt() {
		return tot_amt;
	}
	/**
	 * @param tot_amt the tot_amt to set
	 */
	public void setTot_amt(String tot_amt) {
		this.tot_amt = tot_amt;
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
	 * @return the dc_danga
	 */
	public String getDc_danga() {
		return dc_danga;
	}
	/**
	 * @param dc_danga the dc_danga to set
	 */
	public void setDc_danga(String dc_danga) {
		this.dc_danga = dc_danga;
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
	 * @return the total_tot_amt
	 */
	public String getTotal_tot_amt() {
		return total_tot_amt;
	}
	/**
	 * @param total_tot_amt the total_tot_amt to set
	 */
	public void setTotal_tot_amt(String total_tot_amt) {
		this.total_tot_amt = total_tot_amt;
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
	 * @return the slip_gb
	 */
	public String getSlip_gb() {
		return slip_gb;
	}
	/**
	 * @param slip_gb the slip_gb to set
	 */
	public void setSlip_gb(String slip_gb) {
		this.slip_gb = slip_gb;
	}
	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}
}
