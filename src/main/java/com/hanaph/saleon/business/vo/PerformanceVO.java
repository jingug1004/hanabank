/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

/**
 * <pre>
 * Class Name : PerformanceVO.java
 * 설명 : 실적현황 Batch VO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 31.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 10. 31.
 */
public class PerformanceVO {
	
	private String rpt_gb; // 거래처포함 01 제거 02
	private String part_cd; // 파트코드
	private String datef;
	private String datee;
	private String siljukyul_in; // 원내 판매 실적률
	private String siljukyul_out; // 원외 판매 실적률
	private String siljukyul_in_su; // 원내 수금 실적률
	private String siljukyul_out_su; // 원외 수금 실적률
	private String siljukyul_byung; // 병원 판매 실적률
	private String siljukyul_byung_su; // 병원 수금 실적률
	private String rorder; // 정렬순서
	private String col1;
	private String col2;
	private String col3;
	private String col4;
	private String sale_amt; // 판매목표
	private String sale_amt_siljuk_in; // 
	private String sale_amt_siljuk_in_local; // 도매로컬
	private String sale_amt_siljuk_in_01; // 의원 판매액
	private String sale_amt_siljuk_in_02; // 일반간납
	private String sale_amt_siljuk_in_03; // T 판매액
	private String sale_amt_siljuk_in_04; // 입찰액
	private String sale_amt_siljuk_in_05; // 기타 판매액
	private String sale_amt_siljuk_out; // 약국 판매액
	private String sale_amt_siljuk_byung; // 종합병원 판매액
	private String sale_amt_siljuk_mbyung; // 준종합병원 판매액
	private String sale_amt_banpum; // 반품
	private String sale_amt_halin; // 매출할인외
	private String sale_amt_halins01;
	private String sale_amt_halins02;
	private String sale_amt_siljuk; // 판매실적 계
	private String sale_percent; // 판매 달성율
	private String in_amt; // 수금목표
	private String in_amt_siljuk_in;
	private String in_amt_siljuk_in_local; // 도매 수금액
	private String in_amt_siljuk_in_01; // 의원 수금액
	private String in_amt_siljuk_in_02;
	private String in_amt_siljuk_in_03;
	private String in_amt_siljuk_in_04;
	private String in_amt_siljuk_in_05; // 기타 수금액
	private String in_amt_siljuk_out; // 약국 수금액
	private String in_amt_siljuk_byung; // 종합병원 수금액
	private String in_amt_siljuk_mbyung; // 준종합병원 수금액
	private String in_amt_siljuk; // 수금실적 계
	private String in_percent; // 수금 달성율
	private String proc_date; // 프로시저 처리시간
	private String as_siljukyul_in; // 원내 판매 실적률
	private String as_siljukyul_out; // 원외 판매 실적률
	private String as_siljukyul_in_su; // 원내 수금 실적률
	private String as_siljukyul_out_su; // 원외 수금 실적률
	private String as_siljukyul_byung; // 병원 판매 실적률
	private String as_siljukyul_byung_su; // 병원 수금 실적률
	private String team_cd; // 부서코드
	private String emp_no; // 사원번호
	
	private int total_cnt; // 목록 총 수
	private String total_sale_amt; // 판매목표 계
	private String total_sale_amt_siljuk_byung; // 종합병원 총 판매
	private String total_sale_amt_siljuk_mbyung; // 준종합병원 총 판매
	private String total_sale_amt_siljuk_in_03; // T 총 판매
	private String total_sale_amt_siljuk_in_04; // 총 입찰액
	private String total_sale_amt_siljuk_in_02; // 일반 간납 총 판매액
	private String total_sale_amt_siljuk_in_local; // 도매 로컬 총액
	private String total_sale_amt_siljuk_in_01;
	private String total_sale_amt_siljuk_out;
	private String total_sale_amt_siljuk_in_05; // 기타 총 판매
	private String total_sale_amt_banpum; // 총 반품
	private String total_sale_amt_halin; // 총 매출할인외
	private String total_sale_amt_siljuk; // 총 판매 계
	private String total_sale_percent; // 총판매 달성율
	private String total_in_amt; // 수금목표 계
	private String total_in_amt_siljuk_in;
	private String total_in_amt_siljuk_in_local; // 도매 총 수금액
	private String total_in_amt_siljuk_in_01; // 의원 총 수금액
	private String total_in_amt_siljuk_in_02; // 일반 간납 총 수금액
	private String total_in_amt_siljuk_in_03; // T 총 수금
	private String total_in_amt_siljuk_in_04; 
	private String total_in_amt_siljuk_in_05; // 기타 총 수금
	private String total_in_amt_siljuk_out; // 약국 총 수금
	private String total_in_amt_siljuk_byung; // 종합병원 총 수금
	private String total_in_amt_siljuk_mbyung; // 준종합병원 총 수금
	private String total_in_amt_siljuk; // 총 수금 계
	
	/**
	 * @return the rpt_gb
	 */
	public String getRpt_gb() {
		return rpt_gb;
	}
	/**
	 * @param rpt_gb the rpt_gb to set
	 */
	public void setRpt_gb(String rpt_gb) {
		this.rpt_gb = rpt_gb;
	}
	/**
	 * @return the part_cd
	 */
	public String getPart_cd() {
		return part_cd;
	}
	/**
	 * @param part_cd the part_cd to set
	 */
	public void setPart_cd(String part_cd) {
		this.part_cd = part_cd;
	}
	/**
	 * @return the datef
	 */
	public String getDatef() {
		return datef;
	}
	/**
	 * @param datef the datef to set
	 */
	public void setDatef(String datef) {
		this.datef = datef;
	}
	/**
	 * @return the datee
	 */
	public String getDatee() {
		return datee;
	}
	/**
	 * @param datee the datee to set
	 */
	public void setDatee(String datee) {
		this.datee = datee;
	}
	/**
	 * @return the siljukyul_in
	 */
	public String getSiljukyul_in() {
		return siljukyul_in;
	}
	/**
	 * @param siljukyul_in the siljukyul_in to set
	 */
	public void setSiljukyul_in(String siljukyul_in) {
		this.siljukyul_in = siljukyul_in;
	}
	/**
	 * @return the siljukyul_out
	 */
	public String getSiljukyul_out() {
		return siljukyul_out;
	}
	/**
	 * @param siljukyul_out the siljukyul_out to set
	 */
	public void setSiljukyul_out(String siljukyul_out) {
		this.siljukyul_out = siljukyul_out;
	}
	/**
	 * @return the siljukyul_in_su
	 */
	public String getSiljukyul_in_su() {
		return siljukyul_in_su;
	}
	/**
	 * @param siljukyul_in_su the siljukyul_in_su to set
	 */
	public void setSiljukyul_in_su(String siljukyul_in_su) {
		this.siljukyul_in_su = siljukyul_in_su;
	}
	/**
	 * @return the siljukyul_out_su
	 */
	public String getSiljukyul_out_su() {
		return siljukyul_out_su;
	}
	/**
	 * @param siljukyul_out_su the siljukyul_out_su to set
	 */
	public void setSiljukyul_out_su(String siljukyul_out_su) {
		this.siljukyul_out_su = siljukyul_out_su;
	}
	/**
	 * @return the siljukyul_byung
	 */
	public String getSiljukyul_byung() {
		return siljukyul_byung;
	}
	/**
	 * @param siljukyul_byung the siljukyul_byung to set
	 */
	public void setSiljukyul_byung(String siljukyul_byung) {
		this.siljukyul_byung = siljukyul_byung;
	}
	/**
	 * @return the siljukyul_byung_su
	 */
	public String getSiljukyul_byung_su() {
		return siljukyul_byung_su;
	}
	/**
	 * @param siljukyul_byung_su the siljukyul_byung_su to set
	 */
	public void setSiljukyul_byung_su(String siljukyul_byung_su) {
		this.siljukyul_byung_su = siljukyul_byung_su;
	}
	/**
	 * @return the rorder
	 */
	public String getRorder() {
		return rorder;
	}
	/**
	 * @param rorder the rorder to set
	 */
	public void setRorder(String rorder) {
		this.rorder = rorder;
	}
	/**
	 * @return the col1
	 */
	public String getCol1() {
		return col1;
	}
	/**
	 * @param col1 the col1 to set
	 */
	public void setCol1(String col1) {
		this.col1 = col1;
	}
	/**
	 * @return the col2
	 */
	public String getCol2() {
		return col2;
	}
	/**
	 * @param col2 the col2 to set
	 */
	public void setCol2(String col2) {
		this.col2 = col2;
	}
	/**
	 * @return the col3
	 */
	public String getCol3() {
		return col3;
	}
	/**
	 * @param col3 the col3 to set
	 */
	public void setCol3(String col3) {
		this.col3 = col3;
	}
	/**
	 * @return the col4
	 */
	public String getCol4() {
		return col4;
	}
	/**
	 * @param col4 the col4 to set
	 */
	public void setCol4(String col4) {
		this.col4 = col4;
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
	 * @return the sale_amt_siljuk_in
	 */
	public String getSale_amt_siljuk_in() {
		return sale_amt_siljuk_in;
	}
	/**
	 * @param sale_amt_siljuk_in the sale_amt_siljuk_in to set
	 */
	public void setSale_amt_siljuk_in(String sale_amt_siljuk_in) {
		this.sale_amt_siljuk_in = sale_amt_siljuk_in;
	}
	/**
	 * @return the sale_amt_siljuk_in_local
	 */
	public String getSale_amt_siljuk_in_local() {
		return sale_amt_siljuk_in_local;
	}
	/**
	 * @param sale_amt_siljuk_in_local the sale_amt_siljuk_in_local to set
	 */
	public void setSale_amt_siljuk_in_local(String sale_amt_siljuk_in_local) {
		this.sale_amt_siljuk_in_local = sale_amt_siljuk_in_local;
	}
	/**
	 * @return the sale_amt_siljuk_in_01
	 */
	public String getSale_amt_siljuk_in_01() {
		return sale_amt_siljuk_in_01;
	}
	/**
	 * @param sale_amt_siljuk_in_01 the sale_amt_siljuk_in_01 to set
	 */
	public void setSale_amt_siljuk_in_01(String sale_amt_siljuk_in_01) {
		this.sale_amt_siljuk_in_01 = sale_amt_siljuk_in_01;
	}
	/**
	 * @return the sale_amt_siljuk_in_02
	 */
	public String getSale_amt_siljuk_in_02() {
		return sale_amt_siljuk_in_02;
	}
	/**
	 * @param sale_amt_siljuk_in_02 the sale_amt_siljuk_in_02 to set
	 */
	public void setSale_amt_siljuk_in_02(String sale_amt_siljuk_in_02) {
		this.sale_amt_siljuk_in_02 = sale_amt_siljuk_in_02;
	}
	/**
	 * @return the sale_amt_siljuk_in_03
	 */
	public String getSale_amt_siljuk_in_03() {
		return sale_amt_siljuk_in_03;
	}
	/**
	 * @param sale_amt_siljuk_in_03 the sale_amt_siljuk_in_03 to set
	 */
	public void setSale_amt_siljuk_in_03(String sale_amt_siljuk_in_03) {
		this.sale_amt_siljuk_in_03 = sale_amt_siljuk_in_03;
	}
	/**
	 * @return the sale_amt_siljuk_in_04
	 */
	public String getSale_amt_siljuk_in_04() {
		return sale_amt_siljuk_in_04;
	}
	/**
	 * @param sale_amt_siljuk_in_04 the sale_amt_siljuk_in_04 to set
	 */
	public void setSale_amt_siljuk_in_04(String sale_amt_siljuk_in_04) {
		this.sale_amt_siljuk_in_04 = sale_amt_siljuk_in_04;
	}
	/**
	 * @return the sale_amt_siljuk_in_05
	 */
	public String getSale_amt_siljuk_in_05() {
		return sale_amt_siljuk_in_05;
	}
	/**
	 * @param sale_amt_siljuk_in_05 the sale_amt_siljuk_in_05 to set
	 */
	public void setSale_amt_siljuk_in_05(String sale_amt_siljuk_in_05) {
		this.sale_amt_siljuk_in_05 = sale_amt_siljuk_in_05;
	}
	/**
	 * @return the sale_amt_siljuk_out
	 */
	public String getSale_amt_siljuk_out() {
		return sale_amt_siljuk_out;
	}
	/**
	 * @param sale_amt_siljuk_out the sale_amt_siljuk_out to set
	 */
	public void setSale_amt_siljuk_out(String sale_amt_siljuk_out) {
		this.sale_amt_siljuk_out = sale_amt_siljuk_out;
	}
	/**
	 * @return the sale_amt_siljuk_byung
	 */
	public String getSale_amt_siljuk_byung() {
		return sale_amt_siljuk_byung;
	}
	/**
	 * @param sale_amt_siljuk_byung the sale_amt_siljuk_byung to set
	 */
	public void setSale_amt_siljuk_byung(String sale_amt_siljuk_byung) {
		this.sale_amt_siljuk_byung = sale_amt_siljuk_byung;
	}
	/**
	 * @return the sale_amt_siljuk_mbyung
	 */
	public String getSale_amt_siljuk_mbyung() {
		return sale_amt_siljuk_mbyung;
	}
	/**
	 * @param sale_amt_siljuk_mbyung the sale_amt_siljuk_mbyung to set
	 */
	public void setSale_amt_siljuk_mbyung(String sale_amt_siljuk_mbyung) {
		this.sale_amt_siljuk_mbyung = sale_amt_siljuk_mbyung;
	}
	/**
	 * @return the sale_amt_banpum
	 */
	public String getSale_amt_banpum() {
		return sale_amt_banpum;
	}
	/**
	 * @param sale_amt_banpum the sale_amt_banpum to set
	 */
	public void setSale_amt_banpum(String sale_amt_banpum) {
		this.sale_amt_banpum = sale_amt_banpum;
	}
	/**
	 * @return the sale_amt_halin
	 */
	public String getSale_amt_halin() {
		return sale_amt_halin;
	}
	/**
	 * @param sale_amt_halin the sale_amt_halin to set
	 */
	public void setSale_amt_halin(String sale_amt_halin) {
		this.sale_amt_halin = sale_amt_halin;
	}
	/**
	 * @return the sale_amt_halins01
	 */
	public String getSale_amt_halins01() {
		return sale_amt_halins01;
	}
	/**
	 * @param sale_amt_halins01 the sale_amt_halins01 to set
	 */
	public void setSale_amt_halins01(String sale_amt_halins01) {
		this.sale_amt_halins01 = sale_amt_halins01;
	}
	/**
	 * @return the sale_amt_halins02
	 */
	public String getSale_amt_halins02() {
		return sale_amt_halins02;
	}
	/**
	 * @param sale_amt_halins02 the sale_amt_halins02 to set
	 */
	public void setSale_amt_halins02(String sale_amt_halins02) {
		this.sale_amt_halins02 = sale_amt_halins02;
	}
	/**
	 * @return the sale_amt_siljuk
	 */
	public String getSale_amt_siljuk() {
		return sale_amt_siljuk;
	}
	/**
	 * @param sale_amt_siljuk the sale_amt_siljuk to set
	 */
	public void setSale_amt_siljuk(String sale_amt_siljuk) {
		this.sale_amt_siljuk = sale_amt_siljuk;
	}
	/**
	 * @return the sale_percent
	 */
	public String getSale_percent() {
		return sale_percent;
	}
	/**
	 * @param sale_percent the sale_percent to set
	 */
	public void setSale_percent(String sale_percent) {
		this.sale_percent = sale_percent;
	}
	/**
	 * @return the in_amt
	 */
	public String getIn_amt() {
		return in_amt;
	}
	/**
	 * @param in_amt the in_amt to set
	 */
	public void setIn_amt(String in_amt) {
		this.in_amt = in_amt;
	}
	/**
	 * @return the in_amt_siljuk_in
	 */
	public String getIn_amt_siljuk_in() {
		return in_amt_siljuk_in;
	}
	/**
	 * @param in_amt_siljuk_in the in_amt_siljuk_in to set
	 */
	public void setIn_amt_siljuk_in(String in_amt_siljuk_in) {
		this.in_amt_siljuk_in = in_amt_siljuk_in;
	}
	/**
	 * @return the in_amt_siljuk_in_local
	 */
	public String getIn_amt_siljuk_in_local() {
		return in_amt_siljuk_in_local;
	}
	/**
	 * @param in_amt_siljuk_in_local the in_amt_siljuk_in_local to set
	 */
	public void setIn_amt_siljuk_in_local(String in_amt_siljuk_in_local) {
		this.in_amt_siljuk_in_local = in_amt_siljuk_in_local;
	}
	/**
	 * @return the in_amt_siljuk_in_01
	 */
	public String getIn_amt_siljuk_in_01() {
		return in_amt_siljuk_in_01;
	}
	/**
	 * @param in_amt_siljuk_in_01 the in_amt_siljuk_in_01 to set
	 */
	public void setIn_amt_siljuk_in_01(String in_amt_siljuk_in_01) {
		this.in_amt_siljuk_in_01 = in_amt_siljuk_in_01;
	}
	/**
	 * @return the in_amt_siljuk_in_02
	 */
	public String getIn_amt_siljuk_in_02() {
		return in_amt_siljuk_in_02;
	}
	/**
	 * @param in_amt_siljuk_in_02 the in_amt_siljuk_in_02 to set
	 */
	public void setIn_amt_siljuk_in_02(String in_amt_siljuk_in_02) {
		this.in_amt_siljuk_in_02 = in_amt_siljuk_in_02;
	}
	/**
	 * @return the in_amt_siljuk_in_03
	 */
	public String getIn_amt_siljuk_in_03() {
		return in_amt_siljuk_in_03;
	}
	/**
	 * @param in_amt_siljuk_in_03 the in_amt_siljuk_in_03 to set
	 */
	public void setIn_amt_siljuk_in_03(String in_amt_siljuk_in_03) {
		this.in_amt_siljuk_in_03 = in_amt_siljuk_in_03;
	}
	/**
	 * @return the in_amt_siljuk_in_04
	 */
	public String getIn_amt_siljuk_in_04() {
		return in_amt_siljuk_in_04;
	}
	/**
	 * @param in_amt_siljuk_in_04 the in_amt_siljuk_in_04 to set
	 */
	public void setIn_amt_siljuk_in_04(String in_amt_siljuk_in_04) {
		this.in_amt_siljuk_in_04 = in_amt_siljuk_in_04;
	}
	/**
	 * @return the in_amt_siljuk_in_05
	 */
	public String getIn_amt_siljuk_in_05() {
		return in_amt_siljuk_in_05;
	}
	/**
	 * @param in_amt_siljuk_in_05 the in_amt_siljuk_in_05 to set
	 */
	public void setIn_amt_siljuk_in_05(String in_amt_siljuk_in_05) {
		this.in_amt_siljuk_in_05 = in_amt_siljuk_in_05;
	}
	/**
	 * @return the in_amt_siljuk_out
	 */
	public String getIn_amt_siljuk_out() {
		return in_amt_siljuk_out;
	}
	/**
	 * @param in_amt_siljuk_out the in_amt_siljuk_out to set
	 */
	public void setIn_amt_siljuk_out(String in_amt_siljuk_out) {
		this.in_amt_siljuk_out = in_amt_siljuk_out;
	}
	/**
	 * @return the in_amt_siljuk_byung
	 */
	public String getIn_amt_siljuk_byung() {
		return in_amt_siljuk_byung;
	}
	/**
	 * @param in_amt_siljuk_byung the in_amt_siljuk_byung to set
	 */
	public void setIn_amt_siljuk_byung(String in_amt_siljuk_byung) {
		this.in_amt_siljuk_byung = in_amt_siljuk_byung;
	}
	/**
	 * @return the in_amt_siljuk_mbyung
	 */
	public String getIn_amt_siljuk_mbyung() {
		return in_amt_siljuk_mbyung;
	}
	/**
	 * @param in_amt_siljuk_mbyung the in_amt_siljuk_mbyung to set
	 */
	public void setIn_amt_siljuk_mbyung(String in_amt_siljuk_mbyung) {
		this.in_amt_siljuk_mbyung = in_amt_siljuk_mbyung;
	}
	/**
	 * @return the in_amt_siljuk
	 */
	public String getIn_amt_siljuk() {
		return in_amt_siljuk;
	}
	/**
	 * @param in_amt_siljuk the in_amt_siljuk to set
	 */
	public void setIn_amt_siljuk(String in_amt_siljuk) {
		this.in_amt_siljuk = in_amt_siljuk;
	}
	/**
	 * @return the in_percent
	 */
	public String getIn_percent() {
		return in_percent;
	}
	/**
	 * @param in_percent the in_percent to set
	 */
	public void setIn_percent(String in_percent) {
		this.in_percent = in_percent;
	}
	/**
	 * @return the proc_date
	 */
	public String getProc_date() {
		return proc_date;
	}
	/**
	 * @param proc_date the proc_date to set
	 */
	public void setProc_date(String proc_date) {
		this.proc_date = proc_date;
	}
	/**
	 * @return the as_siljukyul_in
	 */
	public String getAs_siljukyul_in() {
		return as_siljukyul_in;
	}
	/**
	 * @param as_siljukyul_in the as_siljukyul_in to set
	 */
	public void setAs_siljukyul_in(String as_siljukyul_in) {
		this.as_siljukyul_in = as_siljukyul_in;
	}
	/**
	 * @return the as_siljukyul_out
	 */
	public String getAs_siljukyul_out() {
		return as_siljukyul_out;
	}
	/**
	 * @param as_siljukyul_out the as_siljukyul_out to set
	 */
	public void setAs_siljukyul_out(String as_siljukyul_out) {
		this.as_siljukyul_out = as_siljukyul_out;
	}
	/**
	 * @return the as_siljukyul_in_su
	 */
	public String getAs_siljukyul_in_su() {
		return as_siljukyul_in_su;
	}
	/**
	 * @param as_siljukyul_in_su the as_siljukyul_in_su to set
	 */
	public void setAs_siljukyul_in_su(String as_siljukyul_in_su) {
		this.as_siljukyul_in_su = as_siljukyul_in_su;
	}
	/**
	 * @return the as_siljukyul_out_su
	 */
	public String getAs_siljukyul_out_su() {
		return as_siljukyul_out_su;
	}
	/**
	 * @param as_siljukyul_out_su the as_siljukyul_out_su to set
	 */
	public void setAs_siljukyul_out_su(String as_siljukyul_out_su) {
		this.as_siljukyul_out_su = as_siljukyul_out_su;
	}
	/**
	 * @return the as_siljukyul_byung
	 */
	public String getAs_siljukyul_byung() {
		return as_siljukyul_byung;
	}
	/**
	 * @param as_siljukyul_byung the as_siljukyul_byung to set
	 */
	public void setAs_siljukyul_byung(String as_siljukyul_byung) {
		this.as_siljukyul_byung = as_siljukyul_byung;
	}
	/**
	 * @return the as_siljukyul_byung_su
	 */
	public String getAs_siljukyul_byung_su() {
		return as_siljukyul_byung_su;
	}
	/**
	 * @param as_siljukyul_byung_su the as_siljukyul_byung_su to set
	 */
	public void setAs_siljukyul_byung_su(String as_siljukyul_byung_su) {
		this.as_siljukyul_byung_su = as_siljukyul_byung_su;
	}
	/**
	 * @return the team_cd
	 */
	public String getTeam_cd() {
		return team_cd;
	}
	/**
	 * @param team_cd the team_cd to set
	 */
	public void setTeam_cd(String team_cd) {
		this.team_cd = team_cd;
	}
	/**
	 * @return the emp_no
	 */
	public String getEmp_no() {
		return emp_no;
	}
	/**
	 * @param emp_no the emp_no to set
	 */
	public void setEmp_no(String emp_no) {
		this.emp_no = emp_no;
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
	 * @return the total_sale_amt
	 */
	public String getTotal_sale_amt() {
		return total_sale_amt;
	}
	/**
	 * @param total_sale_amt the total_sale_amt to set
	 */
	public void setTotal_sale_amt(String total_sale_amt) {
		this.total_sale_amt = total_sale_amt;
	}
	/**
	 * @return the total_sale_amt_siljuk_byung
	 */
	public String getTotal_sale_amt_siljuk_byung() {
		return total_sale_amt_siljuk_byung;
	}
	/**
	 * @param total_sale_amt_siljuk_byung the total_sale_amt_siljuk_byung to set
	 */
	public void setTotal_sale_amt_siljuk_byung(String total_sale_amt_siljuk_byung) {
		this.total_sale_amt_siljuk_byung = total_sale_amt_siljuk_byung;
	}
	/**
	 * @return the total_sale_amt_siljuk_mbyung
	 */
	public String getTotal_sale_amt_siljuk_mbyung() {
		return total_sale_amt_siljuk_mbyung;
	}
	/**
	 * @param total_sale_amt_siljuk_mbyung the total_sale_amt_siljuk_mbyung to set
	 */
	public void setTotal_sale_amt_siljuk_mbyung(String total_sale_amt_siljuk_mbyung) {
		this.total_sale_amt_siljuk_mbyung = total_sale_amt_siljuk_mbyung;
	}
	/**
	 * @return the total_sale_amt_siljuk_in_03
	 */
	public String getTotal_sale_amt_siljuk_in_03() {
		return total_sale_amt_siljuk_in_03;
	}
	/**
	 * @param total_sale_amt_siljuk_in_03 the total_sale_amt_siljuk_in_03 to set
	 */
	public void setTotal_sale_amt_siljuk_in_03(String total_sale_amt_siljuk_in_03) {
		this.total_sale_amt_siljuk_in_03 = total_sale_amt_siljuk_in_03;
	}
	/**
	 * @return the total_sale_amt_siljuk_in_04
	 */
	public String getTotal_sale_amt_siljuk_in_04() {
		return total_sale_amt_siljuk_in_04;
	}
	/**
	 * @param total_sale_amt_siljuk_in_04 the total_sale_amt_siljuk_in_04 to set
	 */
	public void setTotal_sale_amt_siljuk_in_04(String total_sale_amt_siljuk_in_04) {
		this.total_sale_amt_siljuk_in_04 = total_sale_amt_siljuk_in_04;
	}
	/**
	 * @return the total_sale_amt_siljuk_in_02
	 */
	public String getTotal_sale_amt_siljuk_in_02() {
		return total_sale_amt_siljuk_in_02;
	}
	/**
	 * @param total_sale_amt_siljuk_in_02 the total_sale_amt_siljuk_in_02 to set
	 */
	public void setTotal_sale_amt_siljuk_in_02(String total_sale_amt_siljuk_in_02) {
		this.total_sale_amt_siljuk_in_02 = total_sale_amt_siljuk_in_02;
	}
	/**
	 * @return the total_sale_amt_siljuk_in_local
	 */
	public String getTotal_sale_amt_siljuk_in_local() {
		return total_sale_amt_siljuk_in_local;
	}
	/**
	 * @param total_sale_amt_siljuk_in_local the total_sale_amt_siljuk_in_local to set
	 */
	public void setTotal_sale_amt_siljuk_in_local(
			String total_sale_amt_siljuk_in_local) {
		this.total_sale_amt_siljuk_in_local = total_sale_amt_siljuk_in_local;
	}
	/**
	 * @return the total_sale_amt_siljuk_in_01
	 */
	public String getTotal_sale_amt_siljuk_in_01() {
		return total_sale_amt_siljuk_in_01;
	}
	/**
	 * @param total_sale_amt_siljuk_in_01 the total_sale_amt_siljuk_in_01 to set
	 */
	public void setTotal_sale_amt_siljuk_in_01(String total_sale_amt_siljuk_in_01) {
		this.total_sale_amt_siljuk_in_01 = total_sale_amt_siljuk_in_01;
	}
	/**
	 * @return the total_sale_amt_siljuk_out
	 */
	public String getTotal_sale_amt_siljuk_out() {
		return total_sale_amt_siljuk_out;
	}
	/**
	 * @param total_sale_amt_siljuk_out the total_sale_amt_siljuk_out to set
	 */
	public void setTotal_sale_amt_siljuk_out(String total_sale_amt_siljuk_out) {
		this.total_sale_amt_siljuk_out = total_sale_amt_siljuk_out;
	}
	/**
	 * @return the total_sale_amt_siljuk_in_05
	 */
	public String getTotal_sale_amt_siljuk_in_05() {
		return total_sale_amt_siljuk_in_05;
	}
	/**
	 * @param total_sale_amt_siljuk_in_05 the total_sale_amt_siljuk_in_05 to set
	 */
	public void setTotal_sale_amt_siljuk_in_05(String total_sale_amt_siljuk_in_05) {
		this.total_sale_amt_siljuk_in_05 = total_sale_amt_siljuk_in_05;
	}
	/**
	 * @return the total_sale_amt_banpum
	 */
	public String getTotal_sale_amt_banpum() {
		return total_sale_amt_banpum;
	}
	/**
	 * @param total_sale_amt_banpum the total_sale_amt_banpum to set
	 */
	public void setTotal_sale_amt_banpum(String total_sale_amt_banpum) {
		this.total_sale_amt_banpum = total_sale_amt_banpum;
	}
	/**
	 * @return the total_sale_amt_halin
	 */
	public String getTotal_sale_amt_halin() {
		return total_sale_amt_halin;
	}
	/**
	 * @param total_sale_amt_halin the total_sale_amt_halin to set
	 */
	public void setTotal_sale_amt_halin(String total_sale_amt_halin) {
		this.total_sale_amt_halin = total_sale_amt_halin;
	}
	/**
	 * @return the total_sale_amt_siljuk
	 */
	public String getTotal_sale_amt_siljuk() {
		return total_sale_amt_siljuk;
	}
	/**
	 * @param total_sale_amt_siljuk the total_sale_amt_siljuk to set
	 */
	public void setTotal_sale_amt_siljuk(String total_sale_amt_siljuk) {
		this.total_sale_amt_siljuk = total_sale_amt_siljuk;
	}
	/**
	 * @return the total_sale_percent
	 */
	public String getTotal_sale_percent() {
		return total_sale_percent;
	}
	/**
	 * @param total_sale_percent the total_sale_percent to set
	 */
	public void setTotal_sale_percent(String total_sale_percent) {
		this.total_sale_percent = total_sale_percent;
	}
	/**
	 * @return the total_in_amt
	 */
	public String getTotal_in_amt() {
		return total_in_amt;
	}
	/**
	 * @param total_in_amt the total_in_amt to set
	 */
	public void setTotal_in_amt(String total_in_amt) {
		this.total_in_amt = total_in_amt;
	}
	/**
	 * @return the total_in_amt_siljuk_in
	 */
	public String getTotal_in_amt_siljuk_in() {
		return total_in_amt_siljuk_in;
	}
	/**
	 * @param total_in_amt_siljuk_in the total_in_amt_siljuk_in to set
	 */
	public void setTotal_in_amt_siljuk_in(String total_in_amt_siljuk_in) {
		this.total_in_amt_siljuk_in = total_in_amt_siljuk_in;
	}
	/**
	 * @return the total_in_amt_siljuk_in_local
	 */
	public String getTotal_in_amt_siljuk_in_local() {
		return total_in_amt_siljuk_in_local;
	}
	/**
	 * @param total_in_amt_siljuk_in_local the total_in_amt_siljuk_in_local to set
	 */
	public void setTotal_in_amt_siljuk_in_local(String total_in_amt_siljuk_in_local) {
		this.total_in_amt_siljuk_in_local = total_in_amt_siljuk_in_local;
	}
	/**
	 * @return the total_in_amt_siljuk_in_01
	 */
	public String getTotal_in_amt_siljuk_in_01() {
		return total_in_amt_siljuk_in_01;
	}
	/**
	 * @param total_in_amt_siljuk_in_01 the total_in_amt_siljuk_in_01 to set
	 */
	public void setTotal_in_amt_siljuk_in_01(String total_in_amt_siljuk_in_01) {
		this.total_in_amt_siljuk_in_01 = total_in_amt_siljuk_in_01;
	}
	/**
	 * @return the total_in_amt_siljuk_in_02
	 */
	public String getTotal_in_amt_siljuk_in_02() {
		return total_in_amt_siljuk_in_02;
	}
	/**
	 * @param total_in_amt_siljuk_in_02 the total_in_amt_siljuk_in_02 to set
	 */
	public void setTotal_in_amt_siljuk_in_02(String total_in_amt_siljuk_in_02) {
		this.total_in_amt_siljuk_in_02 = total_in_amt_siljuk_in_02;
	}
	/**
	 * @return the total_in_amt_siljuk_in_03
	 */
	public String getTotal_in_amt_siljuk_in_03() {
		return total_in_amt_siljuk_in_03;
	}
	/**
	 * @param total_in_amt_siljuk_in_03 the total_in_amt_siljuk_in_03 to set
	 */
	public void setTotal_in_amt_siljuk_in_03(String total_in_amt_siljuk_in_03) {
		this.total_in_amt_siljuk_in_03 = total_in_amt_siljuk_in_03;
	}
	/**
	 * @return the total_in_amt_siljuk_in_04
	 */
	public String getTotal_in_amt_siljuk_in_04() {
		return total_in_amt_siljuk_in_04;
	}
	/**
	 * @param total_in_amt_siljuk_in_04 the total_in_amt_siljuk_in_04 to set
	 */
	public void setTotal_in_amt_siljuk_in_04(String total_in_amt_siljuk_in_04) {
		this.total_in_amt_siljuk_in_04 = total_in_amt_siljuk_in_04;
	}
	/**
	 * @return the total_in_amt_siljuk_in_05
	 */
	public String getTotal_in_amt_siljuk_in_05() {
		return total_in_amt_siljuk_in_05;
	}
	/**
	 * @param total_in_amt_siljuk_in_05 the total_in_amt_siljuk_in_05 to set
	 */
	public void setTotal_in_amt_siljuk_in_05(String total_in_amt_siljuk_in_05) {
		this.total_in_amt_siljuk_in_05 = total_in_amt_siljuk_in_05;
	}
	/**
	 * @return the total_in_amt_siljuk_out
	 */
	public String getTotal_in_amt_siljuk_out() {
		return total_in_amt_siljuk_out;
	}
	/**
	 * @param total_in_amt_siljuk_out the total_in_amt_siljuk_out to set
	 */
	public void setTotal_in_amt_siljuk_out(String total_in_amt_siljuk_out) {
		this.total_in_amt_siljuk_out = total_in_amt_siljuk_out;
	}
	/**
	 * @return the total_in_amt_siljuk_byung
	 */
	public String getTotal_in_amt_siljuk_byung() {
		return total_in_amt_siljuk_byung;
	}
	/**
	 * @param total_in_amt_siljuk_byung the total_in_amt_siljuk_byung to set
	 */
	public void setTotal_in_amt_siljuk_byung(String total_in_amt_siljuk_byung) {
		this.total_in_amt_siljuk_byung = total_in_amt_siljuk_byung;
	}
	/**
	 * @return the total_in_amt_siljuk_mbyung
	 */
	public String getTotal_in_amt_siljuk_mbyung() {
		return total_in_amt_siljuk_mbyung;
	}
	/**
	 * @param total_in_amt_siljuk_mbyung the total_in_amt_siljuk_mbyung to set
	 */
	public void setTotal_in_amt_siljuk_mbyung(String total_in_amt_siljuk_mbyung) {
		this.total_in_amt_siljuk_mbyung = total_in_amt_siljuk_mbyung;
	}
	/**
	 * @return the total_in_amt_siljuk
	 */
	public String getTotal_in_amt_siljuk() {
		return total_in_amt_siljuk;
	}
	/**
	 * @param total_in_amt_siljuk the total_in_amt_siljuk to set
	 */
	public void setTotal_in_amt_siljuk(String total_in_amt_siljuk) {
		this.total_in_amt_siljuk = total_in_amt_siljuk;
	}
}
