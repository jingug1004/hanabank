/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.mgmt.vo;

import java.util.List;

/**
 * <pre>
 * Class Name : MgmtButtonVO.java
 * 설명 : 공통 버튼 정보
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 11. 4.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2014. 11. 4.
 */
public class MgmtButtonVO {
	
	private String pgm_no;			// 프로그램 no	
	private String btn_ori_id;		// 기존 버튼 id
	private String btn_id;			// 버튼 id
	private String btn_use_yn;		// 버튼 사용 여부
	private String btn_nm;			// 버튼명
	private String type;			// 페이지 타입
	private String use_btn;			// 버튼 string

	private List<MgmtButtonVO> buttonList;

	/**
	 * @return the pgm_no
	 */
	public String getPgm_no() {
		return pgm_no;
	}

	/**
	 * @param pgm_no the pgm_no to set
	 */
	public void setPgm_no(String pgm_no) {
		this.pgm_no = pgm_no;
	}

	/**
	 * @return the btn_ori_id
	 */
	public String getBtn_ori_id() {
		return btn_ori_id;
	}

	/**
	 * @param btn_ori_id the btn_ori_id to set
	 */
	public void setBtn_ori_id(String btn_ori_id) {
		this.btn_ori_id = btn_ori_id;
	}

	/**
	 * @return the btn_id
	 */
	public String getBtn_id() {
		return btn_id;
	}

	/**
	 * @param btn_id the btn_id to set
	 */
	public void setBtn_id(String btn_id) {
		this.btn_id = btn_id;
	}

	/**
	 * @return the btn_use_yn
	 */
	public String getBtn_use_yn() {
		return btn_use_yn;
	}

	/**
	 * @param btn_use_yn the btn_use_yn to set
	 */
	public void setBtn_use_yn(String btn_use_yn) {
		this.btn_use_yn = btn_use_yn;
	}

	/**
	 * @return the btn_nm
	 */
	public String getBtn_nm() {
		return btn_nm;
	}

	/**
	 * @param btn_nm the btn_nm to set
	 */
	public void setBtn_nm(String btn_nm) {
		this.btn_nm = btn_nm;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the use_btn
	 */
	public String getUse_btn() {
		return use_btn;
	}

	/**
	 * @param use_btn the use_btn to set
	 */
	public void setUse_btn(String use_btn) {
		this.use_btn = use_btn;
	}

	/**
	 * @return the buttonList
	 */
	public List<MgmtButtonVO> getButtonList() {
		return buttonList;
	}

	/**
	 * @param buttonList the buttonList to set
	 */
	public void setButtonList(List<MgmtButtonVO> buttonList) {
		this.buttonList = buttonList;
	}
	
	
}
