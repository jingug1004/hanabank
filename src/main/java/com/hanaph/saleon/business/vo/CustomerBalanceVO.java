/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

/**
 * <pre>
 * Class Name : CustomerBalanceVO.java
 * 설명 : 
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015. 2. 2.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2015. 2. 2.
 */
public class CustomerBalanceVO {
	
	private String cust_id;				//거래처 id
	private String cust_nm;				//거래처명
	private String before_amt;			//전월잔고
	private String sale_amt;			//금월판매
	private String cur_amt;				//현잔고
	private String jasu_amt;			//미도래(자수)
	private String tasu_amt;			//미도래(타수)
	private String sale_dambo_amt;		//매출담보금액
	private String end_ymd;				//만기일
	private String start_ymd;			//발행일
	private String balhang;				//발행처
	private String jigeub;				//지급처
	private String bill_no;				//어음번호
	private String bill_gb;				//담보종류
	private String bigo;				//비고
	private String chulgo_ymd;			//출고일
	private String input_seq;			//입력순서
	private String input_ymd;			//입력일자
	private String buy_dambo_amt;		//매입담보금액
	
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
	 * @return the sale_amt
	 */
	public String getSale_amt() {
		return sale_amt;
	}
	/**
	 * @param sale_amt the sale_amt to set
	 */
	public void setSale_amt(String sale_amt) {
		this.sale_amt = sale_amt;
	}
	/**
	 * @return the cur_amt
	 */
	public String getCur_amt() {
		return cur_amt;
	}
	/**
	 * @param cur_amt the cur_amt to set
	 */
	public void setCur_amt(String cur_amt) {
		this.cur_amt = cur_amt;
	}
	/**
	 * @return the jasu_amt
	 */
	public String getJasu_amt() {
		return jasu_amt;
	}
	/**
	 * @param jasu_amt the jasu_amt to set
	 */
	public void setJasu_amt(String jasu_amt) {
		this.jasu_amt = jasu_amt;
	}
	/**
	 * @return the tasu_amt
	 */
	public String getTasu_amt() {
		return tasu_amt;
	}
	/**
	 * @param tasu_amt the tasu_amt to set
	 */
	public void setTasu_amt(String tasu_amt) {
		this.tasu_amt = tasu_amt;
	}
	/**
	 * @return the sale_dambo_amt
	 */
	public String getSale_dambo_amt() {
		return sale_dambo_amt;
	}
	/**
	 * @param sale_dambo_amt the sale_dambo_amt to set
	 */
	public void setSale_dambo_amt(String sale_dambo_amt) {
		this.sale_dambo_amt = sale_dambo_amt;
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
	 * @return the buy_dambo_amt
	 */
	public String getBuy_dambo_amt() {
		return buy_dambo_amt;
	}
	/**
	 * @param buy_dambo_amt the buy_dambo_amt to set
	 */
	public void setBuy_dambo_amt(String buy_dambo_amt) {
		this.buy_dambo_amt = buy_dambo_amt;
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
	 * @return the start_ymd
	 */
	public String getStart_ymd() {
		return start_ymd;
	}
	/**
	 * @param start_ymd the start_ymd to set
	 */
	public void setStart_ymd(String start_ymd) {
		this.start_ymd = start_ymd;
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
	 * @return the jigeub
	 */
	public String getJigeub() {
		return jigeub;
	}
	/**
	 * @param jigeub the jigeub to set
	 */
	public void setJigeub(String jigeub) {
		this.jigeub = jigeub;
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
	 * @return the bill_gb
	 */
	public String getBill_gb() {
		return bill_gb;
	}
	/**
	 * @param bill_gb the bill_gb to set
	 */
	public void setBill_gb(String bill_gb) {
		this.bill_gb = bill_gb;
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
	 * @return the input_ymd
	 */
	public String getInput_ymd() {
		return input_ymd;
	}
	/**
	 * @param input_ymd the input_ymd to set
	 */
	public void setInput_ymd(String input_ymd) {
		this.input_ymd = input_ymd;
	}
	/**
	 * @return the chulgo_ymd
	 */
	public String getChulgo_ymd() {
		return chulgo_ymd;
	}
	/**
	 * @param chulgo_ymd the chulgo_ymd to set
	 */
	public void setChulgo_ymd(String chulgo_ymd) {
		this.chulgo_ymd = chulgo_ymd;
	}
	/**
	 * @return the totalCnt
	 */
}
