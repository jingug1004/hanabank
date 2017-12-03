/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

/**
 * <pre>
 * Class Name : MyPlVO.java
 * 설명 : MyPl 관련 Value object.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 04.      jung a Woo          
 * </pre>
 * 
 * @version : 1.0
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 12. 04.
 */
public class MyPlVO {
	
	
	private String gs_empCode;			//로그인 계정
	private String sawon_id;			//회사번호
	private int plgrp_no;				//PL 그룹
	private String plgrp_nm;			//PL 그룹명
	private String comments;			//설명
	private String sort_seq;			//정렬순서
	private String item_id;				//제품코드
	private String orgn_item_id;		//선택된 원래 제품코드
	private String item_nm;				//제품명
	private String standard;			//규격
	private String item_kind;			//제품분류코드
	private String item_kind_nm;		//제품분류명
	private String item_kind1;			//구분1
	private String item_kind2;			//구분2
	private String item_kd_no;			//KD
	private String item_out_danga;		//보험약가
	private String item_main_source;	//주성분.함량
	private String item_effect;			//적응증
	private String item_use_does;		//용법.용량
	private String item_pojang_unit;	//포장단위
	private String fontsize_effect;		//글크기_적응증
	private String fontsize_use_does;	//글크기_용법용량
	private byte[] item_photo;			//제품 사진			
	private String use_yn;				//사용여부
	private String item_main_source_size;	//주성분.함량
	private int cnt;					// count
	private String result;				// result
	
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
	 * @return the plgrp_no
	 */
	public int getPlgrp_no() {
		return plgrp_no;
	}
	/**
	 * @param plgrp_no the plgrp_no to set
	 */
	public void setPlgrp_no(int plgrp_no) {
		this.plgrp_no = plgrp_no;
	}
	/**
	 * @return the plgrp_nm
	 */
	public String getPlgrp_nm() {
		return plgrp_nm;
	}
	/**
	 * @param plgrp_nm the plgrp_nm to set
	 */
	public void setPlgrp_nm(String plgrp_nm) {
		this.plgrp_nm = plgrp_nm;
	}
	/**
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	/**
	 * @return the sort_seq
	 */
	public String getSort_seq() {
		return sort_seq;
	}
	/**
	 * @param sort_seq the sort_seq to set
	 */
	public void setSort_seq(String sort_seq) {
		this.sort_seq = sort_seq;
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
	 * @return the orgn_item_id
	 */
	public String getOrgn_item_id() {
		return orgn_item_id;
	}
	/**
	 * @param orgn_item_id the orgn_item_id to set
	 */
	public void setOrgn_item_id(String orgn_item_id) {
		this.orgn_item_id = orgn_item_id;
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
	 * @return the item_kind
	 */
	public String getItem_kind() {
		return item_kind;
	}
	/**
	 * @param item_kind the item_kind to set
	 */
	public void setItem_kind(String item_kind) {
		this.item_kind = item_kind;
	}
	/**
	 * @return the item_kind_nm
	 */
	public String getItem_kind_nm() {
		return item_kind_nm;
	}
	/**
	 * @param item_kind_nm the item_kind_nm to set
	 */
	public void setItem_kind_nm(String item_kind_nm) {
		this.item_kind_nm = item_kind_nm;
	}
	/**
	 * @return the item_kind1
	 */
	public String getItem_kind1() {
		return item_kind1;
	}
	/**
	 * @param item_kind1 the item_kind1 to set
	 */
	public void setItem_kind1(String item_kind1) {
		this.item_kind1 = item_kind1;
	}
	/**
	 * @return the item_kind2
	 */
	public String getItem_kind2() {
		return item_kind2;
	}
	/**
	 * @param item_kind2 the item_kind2 to set
	 */
	public void setItem_kind2(String item_kind2) {
		this.item_kind2 = item_kind2;
	}
	/**
	 * @return the item_kd_no
	 */
	public String getItem_kd_no() {
		return item_kd_no;
	}
	/**
	 * @param item_kd_no the item_kd_no to set
	 */
	public void setItem_kd_no(String item_kd_no) {
		this.item_kd_no = item_kd_no;
	}
	/**
	 * @return the item_out_danga
	 */
	public String getItem_out_danga() {
		return item_out_danga;
	}
	/**
	 * @param item_out_danga the item_out_danga to set
	 */
	public void setItem_out_danga(String item_out_danga) {
		this.item_out_danga = item_out_danga;
	}
	/**
	 * @return the item_main_source
	 */
	public String getItem_main_source() {
		return item_main_source;
	}
	/**
	 * @param item_main_source the item_main_source to set
	 */
	public void setItem_main_source(String item_main_source) {
		this.item_main_source = item_main_source;
	}
	/**
	 * @return the item_effect
	 */
	public String getItem_effect() {
		return item_effect;
	}
	/**
	 * @param item_effect the item_effect to set
	 */
	public void setItem_effect(String item_effect) {
		this.item_effect = item_effect;
	}
	/**
	 * @return the item_use_does
	 */
	public String getItem_use_does() {
		return item_use_does;
	}
	/**
	 * @param item_use_does the item_use_does to set
	 */
	public void setItem_use_does(String item_use_does) {
		this.item_use_does = item_use_does;
	}
	/**
	 * @return the item_pojang_unit
	 */
	public String getItem_pojang_unit() {
		return item_pojang_unit;
	}
	/**
	 * @param item_pojang_unit the item_pojang_unit to set
	 */
	public void setItem_pojang_unit(String item_pojang_unit) {
		this.item_pojang_unit = item_pojang_unit;
	}
	/**
	 * @return the fontsize_effect
	 */
	public String getFontsize_effect() {
		return fontsize_effect;
	}
	/**
	 * @param fontsize_effect the fontsize_effect to set
	 */
	public void setFontsize_effect(String fontsize_effect) {
		this.fontsize_effect = fontsize_effect;
	}
	/**
	 * @return the fontsize_use_does
	 */
	public String getFontsize_use_does() {
		return fontsize_use_does;
	}
	/**
	 * @param fontsize_use_does the fontsize_use_does to set
	 */
	public void setFontsize_use_does(String fontsize_use_does) {
		this.fontsize_use_does = fontsize_use_does;
	}
	/**
	 * @return the item_photo
	 */
	public byte[] getItem_photo() {
		return item_photo;
	}
	/**
	 * @param item_photo the item_photo to set
	 */
	public void setItem_photo(byte[] item_photo) {
		this.item_photo = item_photo;
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
	 * @return the item_main_source_size
	 */
	public String getItem_main_source_size() {
		return item_main_source_size;
	}
	/**
	 * @param item_main_source_size the item_main_source_size to set
	 */
	public void setItem_main_source_size(String item_main_source_size) {
		this.item_main_source_size = item_main_source_size;
	}
	/**
	 * @return the cnt
	 */
	public int getCnt() {
		return cnt;
	}
	/**
	 * @param cnt the cnt to set
	 */
	public void setCnt(int cnt) {
		this.cnt = cnt;
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
	
}
