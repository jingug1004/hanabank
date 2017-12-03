/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.vo;

/**
 * <pre>
 * Class Name : OrderVO.java
 * 설명 : 온라인 발주 주문 관련 Value object.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 30.      jung a Woo          
 * </pre>
 * 
 * @version : 
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 10. 30.
 */
public class OrderVO {

	private String cust_id;	//회사번호
	private String cust_nm;		//회사명
	private String vou_no;		//사업자번호
	private String president;	//대표자명
	private String ls_cust_nm;	//거래처명
	private String gs_empCode;	//로그인 계정
	
	
	private double ld_dambo_rate;	//여신규정 담보율
	private double ld_credit_amt;	//신용액
	private double ld_credit_dambo;	//신용담보
	
	private String ls_sawon_id;	//사원 코드
	private String ls_yeondae;	//연대 보증
	private String is_yeondae3;	//연대 보증3
	
	private double ll_count;	//거래처의 여신 count
	
	private double ld_credit_limit_amt;	//거래처등록의 여신한도

	
	private String ls_sawon_info;	//사원정보
	
	
	private double ld_tot_credit;	//총 여신액
	private double ld_tot_dambo;	//총 담보액
	
	private double ld_sale_tot_credit;	//RP상 주문 총여신
	
	private String ls_budong_yn;	//부동 여부
	private String ls_email;	//이메일 주소
	private String ls_room_cnt;	//입원실 객수
	
	private double ls_jumun_limit;	//주문한도 [설명] (평균수량=직전3개월평균 * ' + LS_JUMUN_LIMIT + '%)(주문한도=평균수량-해당월)'
		
	private double credit_dambo; //여신 담보
	private double rem_dambo;	//주문가능액
	private double ld_rem_dambo;	//온라인상 주문 가능액
	private String gumae_no;	//구매번호
	private String gumae_gb;	//구매구분
	private String status;	//상태
	private String bigo;	//비고
	private String pre_deposit;	//선입금 거래처
	private boolean result;	//결과
	
	private String control_rate_day; // 주문제어 회전일 : 기준 회전일 
	private String rate_day; // 회전일
	private double jupsu_amt; // 접수상태의주문금액
	
	/**
	 * @return the cust_id
	 */
	public String getCust_id() {
		return cust_id;
	} 
	/**
	 * @return the jupsu_amt
	 */
	public double getJupsu_amt() {
		return jupsu_amt;
	}
	/**
	 * @param jupsu_amt the jupsu_amt to set
	 */
	public void setJupsu_amt(double jupsu_amt) {
		this.jupsu_amt = jupsu_amt;
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
	 * @return the ls_cust_nm
	 */
	public String getLs_cust_nm() {
		return ls_cust_nm;
	}
	/**
	 * @param ls_cust_nm the ls_cust_nm to set
	 */
	public void setLs_cust_nm(String ls_cust_nm) {
		this.ls_cust_nm = ls_cust_nm;
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
	 * @return the ld_dambo_rate
	 */
	public double getLd_dambo_rate() {
		return ld_dambo_rate;
	}
	/**
	 * @param ld_dambo_rate the ld_dambo_rate to set
	 */
	public void setLd_dambo_rate(double ld_dambo_rate) {
		this.ld_dambo_rate = ld_dambo_rate;
	}
	/**
	 * @return the ld_credit_amt
	 */
	public double getLd_credit_amt() {
		return ld_credit_amt;
	}
	/**
	 * @param ld_credit_amt the ld_credit_amt to set
	 */
	public void setLd_credit_amt(double ld_credit_amt) {
		this.ld_credit_amt = ld_credit_amt;
	}
	/**
	 * @return the ld_credit_dambo
	 */
	public double getLd_credit_dambo() {
		return ld_credit_dambo;
	}
	/**
	 * @param ld_credit_dambo the ld_credit_dambo to set
	 */
	public void setLd_credit_dambo(double ld_credit_dambo) {
		this.ld_credit_dambo = ld_credit_dambo;
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
	 * @return the ls_yeondae
	 */
	public String getLs_yeondae() {
		return ls_yeondae;
	}
	/**
	 * @param ls_yeondae the ls_yeondae to set
	 */
	public void setLs_yeondae(String ls_yeondae) {
		this.ls_yeondae = ls_yeondae;
	}
	/**
	 * @return the is_yeondae3
	 */
	public String getIs_yeondae3() {
		return is_yeondae3;
	}
	/**
	 * @param is_yeondae3 the is_yeondae3 to set
	 */
	public void setIs_yeondae3(String is_yeondae3) {
		this.is_yeondae3 = is_yeondae3;
	}
	/**
	 * @return the ll_count
	 */
	public double getLl_count() {
		return ll_count;
	}
	/**
	 * @param ll_count the ll_count to set
	 */
	public void setLl_count(double ll_count) {
		this.ll_count = ll_count;
	}
	/**
	 * @return the ld_credit_limit_amt
	 */
	public double getLd_credit_limit_amt() {
		return ld_credit_limit_amt;
	}
	/**
	 * @param ld_credit_limit_amt the ld_credit_limit_amt to set
	 */
	public void setLd_credit_limit_amt(double ld_credit_limit_amt) {
		this.ld_credit_limit_amt = ld_credit_limit_amt;
	}
	/**
	 * @return the ls_sawon_info
	 */
	public String getLs_sawon_info() {
		return ls_sawon_info;
	}
	/**
	 * @param ls_sawon_info the ls_sawon_info to set
	 */
	public void setLs_sawon_info(String ls_sawon_info) {
		this.ls_sawon_info = ls_sawon_info;
	}
	/**
	 * @return the ld_tot_credit
	 */
	public double getLd_tot_credit() {
		return ld_tot_credit;
	}
	/**
	 * @param ld_tot_credit the ld_tot_credit to set
	 */
	public void setLd_tot_credit(double ld_tot_credit) {
		this.ld_tot_credit = ld_tot_credit;
	}
	/**
	 * @return the ld_tot_dambo
	 */
	public double getLd_tot_dambo() {
		return ld_tot_dambo;
	}
	/**
	 * @param ld_tot_dambo the ld_tot_dambo to set
	 */
	public void setLd_tot_dambo(double ld_tot_dambo) {
		this.ld_tot_dambo = ld_tot_dambo;
	}
	/**
	 * @return the ld_sale_tot_credit
	 */
	public double getLd_sale_tot_credit() {
		return ld_sale_tot_credit;
	}
	/**
	 * @param ld_sale_tot_credit the ld_sale_tot_credit to set
	 */
	public void setLd_sale_tot_credit(double ld_sale_tot_credit) {
		this.ld_sale_tot_credit = ld_sale_tot_credit;
	}
	/**
	 * @return the ls_budong_yn
	 */
	public String getLs_budong_yn() {
		return ls_budong_yn;
	}
	/**
	 * @param ls_budong_yn the ls_budong_yn to set
	 */
	public void setLs_budong_yn(String ls_budong_yn) {
		this.ls_budong_yn = ls_budong_yn;
	}
	/**
	 * @return the ls_email
	 */
	public String getLs_email() {
		return ls_email;
	}
	/**
	 * @param ls_email the ls_email to set
	 */
	public void setLs_email(String ls_email) {
		this.ls_email = ls_email;
	}
	/**
	 * @return the ls_room_cnt
	 */
	public String getLs_room_cnt() {
		return ls_room_cnt;
	}
	/**
	 * @param ls_room_cnt the ls_room_cnt to set
	 */
	public void setLs_room_cnt(String ls_room_cnt) {
		this.ls_room_cnt = ls_room_cnt;
	}
	/**
	 * @return the ls_jumun_limit
	 */
	public double getLs_jumun_limit() {
		return ls_jumun_limit;
	}
	/**
	 * @param ls_jumun_limit the ls_jumun_limit to set
	 */
	public void setLs_jumun_limit(double ls_jumun_limit) {
		this.ls_jumun_limit = ls_jumun_limit;
	}
	/**
	 * @return the credit_dambo
	 */
	public double getCredit_dambo() {
		return credit_dambo;
	}
	/**
	 * @param credit_dambo the credit_dambo to set
	 */
	public void setCredit_dambo(double credit_dambo) {
		this.credit_dambo = credit_dambo;
	}
	/**
	 * @return the rem_dambo
	 */
	public double getRem_dambo() {
		return rem_dambo;
	}
	/**
	 * @param rem_dambo the rem_dambo to set
	 */
	public void setRem_dambo(double rem_dambo) {
		this.rem_dambo = rem_dambo;
	}
	/**
	 * @return the ld_rem_dambo
	 */
	public double getLd_rem_dambo() {
		return ld_rem_dambo;
	}
	/**
	 * @param ld_rem_dambo the ld_rem_dambo to set
	 */
	public void setLd_rem_dambo(double ld_rem_dambo) {
		this.ld_rem_dambo = ld_rem_dambo;
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
	 * @return the pre_deposit
	 */
	public String getPre_deposit() {
		return pre_deposit;
	}
	/**
	 * @param pre_deposit the pre_deposit to set
	 */
	public void setPre_deposit(String pre_deposit) {
		this.pre_deposit = pre_deposit;
	}
	/**
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}
	/**
	 * @return the control_rate_day
	 */
	public String getControl_rate_day() {
		return control_rate_day;
	}
	/**
	 * @param control_rate_day the control_rate_day to set
	 */
	public void setControl_rate_day(String control_rate_day) {
		this.control_rate_day = control_rate_day;
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
}
