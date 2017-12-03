/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

/**
 * <pre>
 * Class Name : OrderApplovalVO.java
 * 설명 : 주문 승인 VO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 8.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 12. 8.
 */
public class OrderApprovalVO {
	
	private String tChk;
	private String chk;
	private String cust_id; // 거래처 코드
	private String rcust_id; // 판매처 코드
	private String cust_nm; // 거래처 명
	private String rcust_nm; // 판매처 명
	private String gumae_no; // 구매번호
	private String ymd; // 요청일자
	private String req_time; // 요청일시 
	private String app_no; // 승인번호
	private String bigo; // 요청사항
	private String pbigo; // 비고
	private String receipt_gb; // 접수상태
	private String slip_gb; // 주문구분
	private String wiban_order_req_yn; // 회전일 위반 주문 여부
	private String wiban_order_conf_yn; // 회전일 위반 주문 승인 여부
	private String wiban_kind; // 위반 종류
	private String psb_qty; // 초과수량
	private String part_gb; // 파트 구분
	private String rate_day; // 회전일
	private String sawon_nm; // 사원명
	private String dept_nm; // 부서명
	private String cur_amt; // 현잔고
	private String ymdt; // 승인일
	private String adate; // 작업일시
	private String asawon_nm; // 승인자명
	private String error_gb; // 오류구분
	
	private String item_id; // 제품코드
	private String item_nm; // 제품명
	private String qty; // 승인수량
	private String danga; // 단가
	private String amt; // 공급가액
	private String vat; // 세액
	private String tot_amt; // 공급총액
	private String rate; // 사전 %
	private String dc_danga; // 할인액
	private String dc_qty; // 할증률
	private String standard; // 규격
	private String unit; // 단위
	private String color; // 규격 바탕색
	private String mavg_qty; // 주문한도
	private String mqty; // 해당 월
	private String req_qty; // 요청수량
	private String input_seq; // 상세 seq
	private String detail_gumae_no; // 상세 구매번호
	private String item_gb1;
	private String dc_amt; // 할인액
	private String error_gb_d; // 오류구분
	
	private String before_amt; // 전월잔고
	private String sale_amt; // 금월판매
	private String su_amt; // 
	private String cash_amt; // 전월수금
	private String bill_amt; // 
	private String jasu_amt; // 미도래(자수)
	private String tasu_amt; // 미도래(타수)
	private String sale_dambo_amt; // 담보확보액
	private String jumun_amt; // 주문금액
	private String jumun_amt_o; // 주문금액
	private String yeondae_3; // 연대보증(담보예외)
	private String yeondae_2; // 연대보증(제3자)
	private String dambo; // 연대보증인
	private String bill_cnt; // 담보갯수
	private String bill_kind; // 담보종류
	
	private String gumae_gb; // 구매구분
	private String sawon_id; // 사원코드
	private String rsawon_id; // 판매처 담당 사원 코드
	private String cust_gb1; // 거래처구분1
	private String rcust_gb1; // 판매처구분1
	private String rcust_gbnm; // 판매처구분명
	private String amt_sum; // 발주금액 총계
	private String vat_sum; // 발주부가세 총계
	private String gyuljae_gb; // 결제조건
	private String tax_type; // 과세유형
	private String decimal_proc; // 소수처리
	private String chamjo_nm; // 담당자
	private String chamjo_rank; // 담당자 직급
	private String rsawon_nm; // 판매담당사원명
	private String gubun; // 구분
	private String accept_yn; // 승인구분
	private String limit_yn; // 여신위반
	private String limit_desc; // 여신상태
	private String input_ymd; // 요청일
	private String input_id; // 입력 사번
	private String isawon_nm; // 사원명
	private String control_rate_day; // 에이징 테이블에 계산되어있는 회전일
	private String wiban_conf_dtm; // 위반 ㅇ더 지점장 승인 이이
	private String rdept_nm; // 판매처 부서 명
	
	private String req_date; // 주문요청일
	private String promise_date; // 약속기일
	private String status; // 상태
	private String promise_bigo; // 담보약속내용
	private String return_desc; // 반려사유
	
	private int total_cnt; // 총 목록 수
	private String total_amt; // 공급가액
	private String total_vat; // 세액
	private String total_tot_amt; // 공급총액
	private String reamt; // 미승인액
	private String total_reamt; // 총 미승인액(실조건아님)
	private String total_reamt_real; //총 미승인액(실조건)
	
	private double tot_credit; // 총 여신
	private double credit_limit_amt; // 여신한도
	private String budong_yn; // 출하중지처 여부
	private String chul_yn; // 출하중지 여부
	private String use_yn; // 단종 여부
	private String app_date; // 승인일
	private String emp_code; // 사원 코드
	private String jaego; // 재고일
	
	private double thisorder_amt; //현주문의 주문금액
	
	private boolean[] rowResultArr; // row별 프로세스 결과
	private String[] resultCodeArr; // 에러 코드 배열
	private String[] resultMessageArr; // 에러 메세지 배열
	 
	/**
	 * @return the thisorder_amt
	 */
	public double getThisorder_amt() {
		return thisorder_amt;
	}
	/**
	 * @param thisorder_amt the thisorder_amt to set
	 */
	public void setThisorder_amt(double thisorder_amt) {
		this.thisorder_amt = thisorder_amt;
	}
	/**
	 * @return the tChk
	 */
	public String gettChk() {
		return tChk;
	}
	/**
	 * @param tChk the tChk to set
	 */
	public void settChk(String tChk) {
		this.tChk = tChk;
	}
	/**
	 * @return the chk
	 */
	public String getChk() {
		return chk;
	}
	/**
	 * @param chk the chk to set
	 */
	public void setChk(String chk) {
		this.chk = chk;
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
	 * @return the wiban_kind
	 */
	public String getWiban_kind() {
		return wiban_kind;
	}
	/**
	 * @param wiban_kind the wiban_kind to set
	 */
	public void setWiban_kind(String wiban_kind) {
		this.wiban_kind = wiban_kind;
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
	 * @return the part_gb
	 */
	public String getPart_gb() {
		return part_gb;
	}
	/**
	 * @param part_gb the part_gb to set
	 */
	public void setPart_gb(String part_gb) {
		this.part_gb = part_gb;
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
	 * @return the mqty
	 */
	public String getMqty() {
		return mqty;
	}
	/**
	 * @param mqty the mqty to set
	 */
	public void setMqty(String mqty) {
		this.mqty = mqty;
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
	 * @return the su_amt
	 */
	public String getSu_amt() {
		return su_amt;
	}
	/**
	 * @param su_amt the su_amt to set
	 */
	public void setSu_amt(String su_amt) {
		this.su_amt = su_amt;
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
	 * @return the jumun_amt
	 */
	public String getJumun_amt() {
		return jumun_amt;
	}
	/**
	 * @param jumun_amt the jumun_amt to set
	 */
	public void setJumun_amt(String jumun_amt) {
		this.jumun_amt = jumun_amt;
	}
	/**
	 * @return the jumun_amt_o
	 */
	public String getJumun_amt_o() {
		return jumun_amt_o;
	}
	/**
	 * @param jumun_amt_o the jumun_amt_o to set
	 */
	public void setJumun_amt_o(String jumun_amt_o) {
		this.jumun_amt_o = jumun_amt_o;
	}
	/**
	 * @return the yeondae_3
	 */
	public String getYeondae_3() {
		return yeondae_3;
	}
	/**
	 * @param yeondae_3 the yeondae_3 to set
	 */
	public void setYeondae_3(String yeondae_3) {
		this.yeondae_3 = yeondae_3;
	}
	/**
	 * @return the yeondae_2
	 */
	public String getYeondae_2() {
		return yeondae_2;
	}
	/**
	 * @param yeondae_2 the yeondae_2 to set
	 */
	public void setYeondae_2(String yeondae_2) {
		this.yeondae_2 = yeondae_2;
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
	 * @return the bill_cnt
	 */
	public String getBill_cnt() {
		return bill_cnt;
	}
	/**
	 * @param bill_cnt the bill_cnt to set
	 */
	public void setBill_cnt(String bill_cnt) {
		this.bill_cnt = bill_cnt;
	}
	/**
	 * @return the bill_kind
	 */
	public String getBill_kind() {
		return bill_kind;
	}
	/**
	 * @param bill_kind the bill_kind to set
	 */
	public void setBill_kind(String bill_kind) {
		this.bill_kind = bill_kind;
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
	 * @return the rsawon_id
	 */
	public String getRsawon_id() {
		return rsawon_id;
	}
	/**
	 * @param rsawon_id the rsawon_id to set
	 */
	public void setRsawon_id(String rsawon_id) {
		this.rsawon_id = rsawon_id;
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
	 * @return the rcust_gbnm
	 */
	public String getRcust_gbnm() {
		return rcust_gbnm;
	}
	/**
	 * @param rcust_gbnm the rcust_gbnm to set
	 */
	public void setRcust_gbnm(String rcust_gbnm) {
		this.rcust_gbnm = rcust_gbnm;
	}
	/**
	 * @return the amt_sum
	 */
	public String getAmt_sum() {
		return amt_sum;
	}
	/**
	 * @param amt_sum the amt_sum to set
	 */
	public void setAmt_sum(String amt_sum) {
		this.amt_sum = amt_sum;
	}
	/**
	 * @return the vat_sum
	 */
	public String getVat_sum() {
		return vat_sum;
	}
	/**
	 * @param vat_sum the vat_sum to set
	 */
	public void setVat_sum(String vat_sum) {
		this.vat_sum = vat_sum;
	}
	/**
	 * @return the gyuljae_gb
	 */
	public String getGyuljae_gb() {
		return gyuljae_gb;
	}
	/**
	 * @param gyuljae_gb the gyuljae_gb to set
	 */
	public void setGyuljae_gb(String gyuljae_gb) {
		this.gyuljae_gb = gyuljae_gb;
	}
	/**
	 * @return the tax_type
	 */
	public String getTax_type() {
		return tax_type;
	}
	/**
	 * @param tax_type the tax_type to set
	 */
	public void setTax_type(String tax_type) {
		this.tax_type = tax_type;
	}
	/**
	 * @return the decimal_proc
	 */
	public String getDecimal_proc() {
		return decimal_proc;
	}
	/**
	 * @param decimal_proc the decimal_proc to set
	 */
	public void setDecimal_proc(String decimal_proc) {
		this.decimal_proc = decimal_proc;
	}
	/**
	 * @return the chamjo_nm
	 */
	public String getChamjo_nm() {
		return chamjo_nm;
	}
	/**
	 * @param chamjo_nm the chamjo_nm to set
	 */
	public void setChamjo_nm(String chamjo_nm) {
		this.chamjo_nm = chamjo_nm;
	}
	/**
	 * @return the chamjo_rank
	 */
	public String getChamjo_rank() {
		return chamjo_rank;
	}
	/**
	 * @param chamjo_rank the chamjo_rank to set
	 */
	public void setChamjo_rank(String chamjo_rank) {
		this.chamjo_rank = chamjo_rank;
	}
	/**
	 * @return the rsawon_nm
	 */
	public String getRsawon_nm() {
		return rsawon_nm;
	}
	/**
	 * @param rsawon_nm the rsawon_nm to set
	 */
	public void setRsawon_nm(String rsawon_nm) {
		this.rsawon_nm = rsawon_nm;
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
	 * @return the accept_yn
	 */
	public String getAccept_yn() {
		return accept_yn;
	}
	/**
	 * @param accept_yn the accept_yn to set
	 */
	public void setAccept_yn(String accept_yn) {
		this.accept_yn = accept_yn;
	}
	/**
	 * @return the limit_yn
	 */
	public String getLimit_yn() {
		return limit_yn;
	}
	/**
	 * @param limit_yn the limit_yn to set
	 */
	public void setLimit_yn(String limit_yn) {
		this.limit_yn = limit_yn;
	}
	/**
	 * @return the limit_desc
	 */
	public String getLimit_desc() {
		return limit_desc;
	}
	/**
	 * @param limit_desc the limit_desc to set
	 */
	public void setLimit_desc(String limit_desc) {
		this.limit_desc = limit_desc;
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
	 * @return the input_id
	 */
	public String getInput_id() {
		return input_id;
	}
	/**
	 * @param input_id the input_id to set
	 */
	public void setInput_id(String input_id) {
		this.input_id = input_id;
	}
	/**
	 * @return the isawon_nm
	 */
	public String getIsawon_nm() {
		return isawon_nm;
	}
	/**
	 * @param isawon_nm the isawon_nm to set
	 */
	public void setIsawon_nm(String isawon_nm) {
		this.isawon_nm = isawon_nm;
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
	 * @return the wiban_conf_dtm
	 */
	public String getWiban_conf_dtm() {
		return wiban_conf_dtm;
	}
	/**
	 * @param wiban_conf_dtm the wiban_conf_dtm to set
	 */
	public void setWiban_conf_dtm(String wiban_conf_dtm) {
		this.wiban_conf_dtm = wiban_conf_dtm;
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
	 * @return the reamt
	 */
	public String getReamt() {
		return reamt;
	}
	/**
	 * @param reamt the reamt to set
	 */
	public void setReamt(String reamt) {
		this.reamt = reamt;
	}
	/**
	 * @return the total_reamt
	 */
	public String getTotal_reamt() {
		return total_reamt;
	}
	/**
	 * @param total_reamt the total_reamt to set
	 */
	public void setTotal_reamt(String total_reamt) {
		this.total_reamt = total_reamt;
	}
	
	
	/**
	 * @return the tot_credit
	 */
	public double getTot_credit() {
		return tot_credit;
	}
	/**
	 * @param tot_credit the tot_credit to set
	 */
	public void setTot_credit(double tot_credit) {
		this.tot_credit = tot_credit;
	}
	/**
	 * @return the credit_limit_amt
	 */
	public double getCredit_limit_amt() {
		return credit_limit_amt;
	}
	/**
	 * @param credit_limit_amt the credit_limit_amt to set
	 */
	public void setCredit_limit_amt(double credit_limit_amt) {
		this.credit_limit_amt = credit_limit_amt;
	}
	/**
	 * @return the budong_yn
	 */
	public String getBudong_yn() {
		return budong_yn;
	}
	/**
	 * @param budong_yn the budong_yn to set
	 */
	public void setBudong_yn(String budong_yn) {
		this.budong_yn = budong_yn;
	}
	/**
	 * @return the chul_yn
	 */
	public String getChul_yn() {
		return chul_yn;
	}
	/**
	 * @param chul_yn the chul_yn to set
	 */
	public void setChul_yn(String chul_yn) {
		this.chul_yn = chul_yn;
	}
	/**
	 * @return the use_yn
	 */
	public String getUse_yn() {
		return use_yn;
	}
	/**
	 * @param use_yn the use_yn to set
	 */
	public void setUse_yn(String use_yn) {
		this.use_yn = use_yn;
	}
	/**
	 * @return the app_date
	 */
	public String getApp_date() {
		return app_date;
	}
	/**
	 * @param app_date the app_date to set
	 */
	public void setApp_date(String app_date) {
		this.app_date = app_date;
	}
	/**
	 * @return the emp_code
	 */
	public String getEmp_code() {
		return emp_code;
	}
	/**
	 * @param emp_code the emp_code to set
	 */
	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
	}
	/**
	 * @return the jaego
	 */
	public String getJaego() {
		return jaego;
	}
	/**
	 * @param jaego the jaego to set
	 */
	public void setJaego(String jaego) {
		this.jaego = jaego;
	}
	/**
	 * @return the rowResultArr
	 */
	public boolean[] getRowResultArr() {
		return rowResultArr;
	}
	/**
	 * @param rowResultArr the rowResultArr to set
	 */
	public void setRowResultArr(boolean[] rowResultArr) {
		this.rowResultArr = rowResultArr;
	}
	/**
	 * @return the resultCodeArr
	 */
	public String[] getResultCodeArr() {
		return resultCodeArr;
	}
	/**
	 * @param resultCodeArr the resultCodeArr to set
	 */
	public void setResultCodeArr(String[] resultCodeArr) {
		this.resultCodeArr = resultCodeArr;
	}
	/**
	 * @return the resultMessageArr
	 */
	public String[] getResultMessageArr() {
		return resultMessageArr;
	}
	/**
	 * @param resultMessageArr the resultMessageArr to set
	 */
	public void setResultMessageArr(String[] resultMessageArr) {
		this.resultMessageArr = resultMessageArr;
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
	 * @return the detail_gumae_no
	 */
	public String getDetail_gumae_no() {
		return detail_gumae_no;
	}
	/**
	 * @param detail_gumae_no the detail_gumae_no to set
	 */
	public void setDetail_gumae_no(String detail_gumae_no) {
		this.detail_gumae_no = detail_gumae_no;
	}
	/**
	 * @return the rdept_nm
	 */
	public String getRdept_nm() {
		return rdept_nm;
	}
	/**
	 * @param rdept_nm the rdept_nm to set
	 */
	public void setRdept_nm(String rdept_nm) {
		this.rdept_nm = rdept_nm;
	}
	/**
	 * @return the ymdt
	 */
	public String getYmdt() {
		return ymdt;
	}
	/**
	 * @param ymdt the ymdt to set
	 */
	public void setYmdt(String ymdt) {
		this.ymdt = ymdt;
	}
	/**
	 * @return the adate
	 */
	public String getAdate() {
		return adate;
	}
	/**
	 * @param adate the adate to set
	 */
	public void setAdate(String adate) {
		this.adate = adate;
	}
	/**
	 * @return the item_gb1
	 */
	public String getItem_gb1() {
		return item_gb1;
	}
	/**
	 * @param item_gb1 the item_gb1 to set
	 */
	public void setItem_gb1(String item_gb1) {
		this.item_gb1 = item_gb1;
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
	 * @return the total_reamt_real
	 */
	public String getTotal_reamt_real() {
		return total_reamt_real;
	}
	/**
	 * @param total_reamt_Real the total_reamt_Real to set
	 */
	public void setTotal_reamt_real(String total_reamt_real) {
		this.total_reamt_real = total_reamt_real;
	}
	/**
	 * @return the error_gb
	 */
	public String getError_gb() {
		return error_gb;
	}
	/**
	 * @param error_gb the error_gb to set
	 */
	public void setError_gb(String error_gb) {
		this.error_gb = error_gb;
	}
	/**
	 * @return the error_gb_d
	 */
	public String getError_gb_d() {
		return error_gb_d;
	}
	/**
	 * @param error_gb_d the error_gb_d to set
	 */
	public void setError_gb_d(String error_gb_d) {
		this.error_gb_d = error_gb_d;
	}
}
