/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

/**
 * <pre>
 * Class Name : CompanyCardMgmtVO.java
 * 설명 : 법인카드관리 IBK VO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 24.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 11. 24.
 */
public class CompanyCardMgmtVO {
	
	private String ls_sawon_id; // 접속 사원 코드
	private String ls_sawon_nm; // 접속 사원 명
	private String ls_dept_cd; // 접속 사원 부서 코드
	private String ls_dept_nm; // 접속 사원 부서 명
	private String is_assgn_cd; // 접속 사원 직책
	
	private String card_no; // 카드 번호
	private String use_dt; // 사용일자
	private String use_tm; // 사용시간
	private String card_ok_no; // 카드 승인 번호
	private String use_gubun; // 사용구분
	private String sawon_id; // 사원 코드
	private String dept_cd; // 부서 코드
	private String use_amt; // 사용 금액
	private String saupjang_nm; // 사업장 명
	private String saup_no; // 사업장번호
	private String tax_gb; // 가맹점 과세구분
	private String gaejung_cd; // 계정과목 코드
	private String gaejung_nm; // 계정과목 명
	private String use_detail; // 사용 내역
	private String teamjang_conf_yn; // 입력 완료 여부
	private String teamjang_conf_sabun; // 입력 확인자
	private String salecamp_conf_yn; // 영업관리부승인
	private String salecamp_conf_sabun; // 영업관리 승인자
	private String junpyo_yn; // 전표처리상태
	private String junpyo_no; // 전표번호
	private String junpyo_sabun; // 전표생성자
	private String junpyo_crtdt; // 전표생성일시
	private String junpyo_result; // 전표처리결과
	private String jukyo; // 전표 마스터에 들어갈 적요
	private String gongjae_yn; // 공제여부
	private String sel_yn; // N 
	private String teamjang_conf_sabun_nm; // 입력 확인자 명
	private String sawon_nm; // 사원명
	private String dept_nm; // 부서명
	private String salecamp_conf_sabun_nm; // 영업관리 승인자 명
	private String seungin_yn; // 승인여부
	private String bigo; // 기타사항
	
	private int total_cnt; // 목록 총 수
	private int total_use_amt; // 총 사용 금액
	
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
	 * @return the ls_sawon_nm
	 */
	public String getLs_sawon_nm() {
		return ls_sawon_nm;
	}
	/**
	 * @param ls_sawon_nm the ls_sawon_nm to set
	 */
	public void setLs_sawon_nm(String ls_sawon_nm) {
		this.ls_sawon_nm = ls_sawon_nm;
	}
	/**
	 * @return the ls_dept_cd
	 */
	public String getLs_dept_cd() {
		return ls_dept_cd;
	}
	/**
	 * @param ls_dept_cd the ls_dept_cd to set
	 */
	public void setLs_dept_cd(String ls_dept_cd) {
		this.ls_dept_cd = ls_dept_cd;
	}
	/**
	 * @return the ls_dept_nm
	 */
	public String getLs_dept_nm() {
		return ls_dept_nm;
	}
	/**
	 * @param ls_dept_nm the ls_dept_nm to set
	 */
	public void setLs_dept_nm(String ls_dept_nm) {
		this.ls_dept_nm = ls_dept_nm;
	}
	/**
	 * @return the is_assgn_cd
	 */
	public String getIs_assgn_cd() {
		return is_assgn_cd;
	}
	/**
	 * @param is_assgn_cd the is_assgn_cd to set
	 */
	public void setIs_assgn_cd(String is_assgn_cd) {
		this.is_assgn_cd = is_assgn_cd;
	}
	/**
	 * @return the card_no
	 */
	public String getCard_no() {
		return card_no;
	}
	/**
	 * @param card_no the card_no to set
	 */
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	/**
	 * @return the use_dt
	 */
	public String getUse_dt() {
		return use_dt;
	}
	/**
	 * @param use_dt the use_dt to set
	 */
	public void setUse_dt(String use_dt) {
		this.use_dt = use_dt;
	}
	/**
	 * @return the use_tm
	 */
	public String getUse_tm() {
		return use_tm;
	}
	/**
	 * @param use_tm the use_tm to set
	 */
	public void setUse_tm(String use_tm) {
		this.use_tm = use_tm;
	}
	/**
	 * @return the card_ok_no
	 */
	public String getCard_ok_no() {
		return card_ok_no;
	}
	/**
	 * @param card_ok_no the card_ok_no to set
	 */
	public void setCard_ok_no(String card_ok_no) {
		this.card_ok_no = card_ok_no;
	}
	/**
	 * @return the use_gubun
	 */
	public String getUse_gubun() {
		return use_gubun;
	}
	/**
	 * @param use_gubun the use_gubun to set
	 */
	public void setUse_gubun(String use_gubun) {
		this.use_gubun = use_gubun;
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
	 * @return the use_amt
	 */
	public String getUse_amt() {
		return use_amt;
	}
	/**
	 * @param use_amt the use_amt to set
	 */
	public void setUse_amt(String use_amt) {
		this.use_amt = use_amt;
	}
	/**
	 * @return the saupjang_nm
	 */
	public String getSaupjang_nm() {
		return saupjang_nm;
	}
	/**
	 * @param saupjang_nm the saupjang_nm to set
	 */
	public void setSaupjang_nm(String saupjang_nm) {
		this.saupjang_nm = saupjang_nm;
	}
	/**
	 * @return the saup_no
	 */
	public String getSaup_no() {
		return saup_no;
	}
	/**
	 * @param saup_no the saup_no to set
	 */
	public void setSaup_no(String saup_no) {
		this.saup_no = saup_no;
	}
	/**
	 * @return the tax_gb
	 */
	public String getTax_gb() {
		return tax_gb;
	}
	/**
	 * @param tax_gb the tax_gb to set
	 */
	public void setTax_gb(String tax_gb) {
		this.tax_gb = tax_gb;
	}
	/**
	 * @return the gaejung_cd
	 */
	public String getGaejung_cd() {
		return gaejung_cd;
	}
	/**
	 * @param gaejung_cd the gaejung_cd to set
	 */
	public void setGaejung_cd(String gaejung_cd) {
		this.gaejung_cd = gaejung_cd;
	}
	/**
	 * @return the gaejung_nm
	 */
	public String getGaejung_nm() {
		return gaejung_nm;
	}
	/**
	 * @param gaejung_nm the gaejung_nm to set
	 */
	public void setGaejung_nm(String gaejung_nm) {
		this.gaejung_nm = gaejung_nm;
	}
	/**
	 * @return the use_detail
	 */
	public String getUse_detail() {
		return use_detail;
	}
	/**
	 * @param use_detail the use_detail to set
	 */
	public void setUse_detail(String use_detail) {
		this.use_detail = use_detail;
	}
	/**
	 * @return the teamjang_conf_yn
	 */
	public String getTeamjang_conf_yn() {
		return teamjang_conf_yn;
	}
	/**
	 * @param teamjang_conf_yn the teamjang_conf_yn to set
	 */
	public void setTeamjang_conf_yn(String teamjang_conf_yn) {
		this.teamjang_conf_yn = teamjang_conf_yn;
	}
	/**
	 * @return the teamjang_conf_sabun
	 */
	public String getTeamjang_conf_sabun() {
		return teamjang_conf_sabun;
	}
	/**
	 * @param teamjang_conf_sabun the teamjang_conf_sabun to set
	 */
	public void setTeamjang_conf_sabun(String teamjang_conf_sabun) {
		this.teamjang_conf_sabun = teamjang_conf_sabun;
	}
	/**
	 * @return the salecamp_conf_yn
	 */
	public String getSalecamp_conf_yn() {
		return salecamp_conf_yn;
	}
	/**
	 * @param salecamp_conf_yn the salecamp_conf_yn to set
	 */
	public void setSalecamp_conf_yn(String salecamp_conf_yn) {
		this.salecamp_conf_yn = salecamp_conf_yn;
	}
	/**
	 * @return the salecamp_conf_sabun
	 */
	public String getSalecamp_conf_sabun() {
		return salecamp_conf_sabun;
	}
	/**
	 * @param salecamp_conf_sabun the salecamp_conf_sabun to set
	 */
	public void setSalecamp_conf_sabun(String salecamp_conf_sabun) {
		this.salecamp_conf_sabun = salecamp_conf_sabun;
	}
	/**
	 * @return the junpyo_yn
	 */
	public String getJunpyo_yn() {
		return junpyo_yn;
	}
	/**
	 * @param junpyo_yn the junpyo_yn to set
	 */
	public void setJunpyo_yn(String junpyo_yn) {
		this.junpyo_yn = junpyo_yn;
	}
	/**
	 * @return the junpyo_no
	 */
	public String getJunpyo_no() {
		return junpyo_no;
	}
	/**
	 * @param junpyo_no the junpyo_no to set
	 */
	public void setJunpyo_no(String junpyo_no) {
		this.junpyo_no = junpyo_no;
	}
	/**
	 * @return the junpyo_sabun
	 */
	public String getJunpyo_sabun() {
		return junpyo_sabun;
	}
	/**
	 * @param junpyo_sabun the junpyo_sabun to set
	 */
	public void setJunpyo_sabun(String junpyo_sabun) {
		this.junpyo_sabun = junpyo_sabun;
	}
	/**
	 * @return the junpyo_crtdt
	 */
	public String getJunpyo_crtdt() {
		return junpyo_crtdt;
	}
	/**
	 * @param junpyo_crtdt the junpyo_crtdt to set
	 */
	public void setJunpyo_crtdt(String junpyo_crtdt) {
		this.junpyo_crtdt = junpyo_crtdt;
	}
	/**
	 * @return the junpyo_result
	 */
	public String getJunpyo_result() {
		return junpyo_result;
	}
	/**
	 * @param junpyo_result the junpyo_result to set
	 */
	public void setJunpyo_result(String junpyo_result) {
		this.junpyo_result = junpyo_result;
	}
	/**
	 * @return the jukyo
	 */
	public String getJukyo() {
		return jukyo;
	}
	/**
	 * @param jukyo the jukyo to set
	 */
	public void setJukyo(String jukyo) {
		this.jukyo = jukyo;
	}
	/**
	 * @return the gongjae_yn
	 */
	public String getGongjae_yn() {
		return gongjae_yn;
	}
	/**
	 * @param gongjae_yn the gongjae_yn to set
	 */
	public void setGongjae_yn(String gongjae_yn) {
		this.gongjae_yn = gongjae_yn;
	}
	/**
	 * @return the sel_yn
	 */
	public String getSel_yn() {
		return sel_yn;
	}
	/**
	 * @param sel_yn the sel_yn to set
	 */
	public void setSel_yn(String sel_yn) {
		this.sel_yn = sel_yn;
	}
	/**
	 * @return the teamjang_conf_sabun_nm
	 */
	public String getTeamjang_conf_sabun_nm() {
		return teamjang_conf_sabun_nm;
	}
	/**
	 * @param teamjang_conf_sabun_nm the teamjang_conf_sabun_nm to set
	 */
	public void setTeamjang_conf_sabun_nm(String teamjang_conf_sabun_nm) {
		this.teamjang_conf_sabun_nm = teamjang_conf_sabun_nm;
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
	 * @return the salecamp_conf_sabun_nm
	 */
	public String getSalecamp_conf_sabun_nm() {
		return salecamp_conf_sabun_nm;
	}
	/**
	 * @param salecamp_conf_sabun_nm the salecamp_conf_sabun_nm to set
	 */
	public void setSalecamp_conf_sabun_nm(String salecamp_conf_sabun_nm) {
		this.salecamp_conf_sabun_nm = salecamp_conf_sabun_nm;
	}
	/**
	 * @return the seungin_yn
	 */
	public String getSeungin_yn() {
		return seungin_yn;
	}
	/**
	 * @param seungin_yn the seungin_yn to set
	 */
	public void setSeungin_yn(String seungin_yn) {
		this.seungin_yn = seungin_yn;
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
	 * @return the total_use_amt
	 */
	public int getTotal_use_amt() {
		return total_use_amt;
	}
	/**
	 * @param total_use_amt the total_use_amt to set
	 */
	public void setTotal_use_amt(int total_use_amt) {
		this.total_use_amt = total_use_amt;
	}
}
