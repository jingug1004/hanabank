/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.main.vo;


/**
 * <pre>
 * Class Name : CustDashboardVO.java
 * 설명 : 거래처 대시보드 정보에 사용되는 Value object class.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 9.      장일영            최초생성
 * </pre>
 * 
 * @version : 1.0
 * @author  : 장일영(goodhi@irush.co.kr)
 * @since   : 2014. 12. 9.
 */
public class CustDashboardVO {
	
	String ymd;	//년월일
	
	String cust_id;	//	거래처ID
	String cust_nm;	//	거래처명
	String vou_no;	//	사업자번호
	String addr;	//	주소
	String president;	//대표자 성함
	String email;	//	이메일
	
	String before_amt;    //	전월잔고    
	String sale_amt;    //    금월판매    
	String cash_amt;    //    금월수금    
	String cur_amt;    //    현잔고      
	String bill_amt;    //    미도래      
	String jasu_amt;    //    미도래자수  
	String tasu_amt;    //    미도래타수   
	String total_amt;    //    총여신      CUR_AMT + JASU_AMT
	String dambo;    //    연대보증인   
	String dambo_kind;    //    담보종류     if(  bill_cnt > 1,  bill_kind +' 外 '+string( bill_cnt -1) +' 건', bill_kind  )
	String sale_dambo_amt;    //     담보확보액    
	String dambo_per;    //    담보확보율   string(round( (sale_dambo_amt  / ( cur_amt  +  jasu_amt )) * 100,2))+ ' %'
	String sawon_nm;    //     담당영업사원 
	String rate_day;    //     회전일    
	String loan_limit;	//여신한도액
	String pending_sales;	//미정리매출
	String cust_sawon_nm;	//판매처담당자
	
	String month;	//월
	String month_amt;	// 월별 금액
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
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
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
	 * @return the cash_amt
	 */
	public String getCash_amt() {
		return cash_amt;
	}
	/**
	 * @param cash_amt the cash_amt to set
	 */
	public void setCash_amt(String cash_amt) {
		this.cash_amt = cash_amt;
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
	 * @return the bill_amt
	 */
	public String getBill_amt() {
		return bill_amt;
	}
	/**
	 * @param bill_amt the bill_amt to set
	 */
	public void setBill_amt(String bill_amt) {
		this.bill_amt = bill_amt;
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
	 * @return the dambo
	 */
	public String getDambo() {
		return dambo;
	}
	/**
	 * @param dambo the dambo to set
	 */
	public void setDambo(String dambo) {
		this.dambo = dambo;
	}
	/**
	 * @return the dambo_kind
	 */
	public String getDambo_kind() {
		return dambo_kind;
	}
	/**
	 * @param dambo_kind the dambo_kind to set
	 */
	public void setDambo_kind(String dambo_kind) {
		this.dambo_kind = dambo_kind;
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
	 * @return the dambo_per
	 */
	public String getDambo_per() {
		return dambo_per;
	}
	/**
	 * @param dambo_per the dambo_per to set
	 */
	public void setDambo_per(String dambo_per) {
		this.dambo_per = dambo_per;
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
	 * @return the rate_day
	 */
	public String getRate_day() {
		return rate_day;
	}
	/**
	 * @param rate_day the rate_day to set
	 */
	public void setRate_day(String rate_day) {
		this.rate_day = rate_day;
	}
	/**
	 * @return the loan_limit
	 */
	public String getLoan_limit() {
		return loan_limit;
	}
	/**
	 * @param loan_limit the loan_limit to set
	 */
	public void setLoan_limit(String loan_limit) {
		this.loan_limit = loan_limit;
	}
	/**
	 * @return the pending_sales
	 */
	public String getPending_sales() {
		return pending_sales;
	}
	/**
	 * @param pending_sales the pending_sales to set
	 */
	public void setPending_sales(String pending_sales) {
		this.pending_sales = pending_sales;
	}
	/**
	 * @return the cust_sawon_nm
	 */
	public String getCust_sawon_nm() {
		return cust_sawon_nm;
	}
	/**
	 * @param cust_sawon_nm the cust_sawon_nm to set
	 */
	public void setCust_sawon_nm(String cust_sawon_nm) {
		this.cust_sawon_nm = cust_sawon_nm;
	}
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the month_amt
	 */
	public String getMonth_amt() {
		return month_amt;
	}
	/**
	 * @param month_amt the month_amt to set
	 */
	public void setMonth_amt(String month_amt) {
		this.month_amt = month_amt;
	}
	
	
    
}
