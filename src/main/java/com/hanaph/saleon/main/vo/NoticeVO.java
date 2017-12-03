/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.main.vo;


/**
 * <pre>
 * Class Name : NoticeVO.java
 * 설명 : 공지사항 관련 Value object class.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 8.      장일영            최초생성
 * </pre>
 * 
 * @version : 1.0
 * @author  : 장일영(goodhi@irush.co.kr)
 * @since   : 2014. 12. 8.
 */
public class NoticeVO {
	
	String notice_id;	// 공지사항 글 ID
    String groupcd; 	// 공지사항 그룹 코드
    String notice_title; 	// 공지사항 제목
    String notice_desc; 	// 공지사항 내용
    String input_date; 	//공지사항 등록일시, to_char(A.INPUT_DATE, 'YYYY-MM-DD AM HH12:MI:SS') 
    String input_userid;	//공지사항 등록자 ID
    String sawon_nm;	//공지사항 등록자 이름
    int total_cnt;	//공지사항 전체 조회 건수
    
	/**
	 * @return the notice_id
	 */
	public String getNotice_id() {
		return notice_id;
	}
	/**
	 * @param notice_id the notice_id to set
	 */
	public void setNotice_id(String notice_id) {
		this.notice_id = notice_id;
	}
	/**
	 * @return the groupcd
	 */
	public String getGroupcd() {
		return groupcd;
	}
	/**
	 * @param groupcd the groupcd to set
	 */
	public void setGroupcd(String groupcd) {
		this.groupcd = groupcd;
	}
	/**
	 * @return the notice_title
	 */
	public String getNotice_title() {
		return notice_title;
	}
	/**
	 * @param notice_title the notice_title to set
	 */
	public void setNotice_title(String notice_title) {
		this.notice_title = notice_title;
	}
	/**
	 * @return the notice_desc
	 */
	public String getNotice_desc() {
		return notice_desc;
	}
	/**
	 * @param notice_desc the notice_desc to set
	 */
	public void setNotice_desc(String notice_desc) {
		this.notice_desc = notice_desc;
	}
	/**
	 * @return the input_date
	 */
	public String getInput_date() {
		return input_date;
	}
	/**
	 * @param input_date the input_date to set
	 */
	public void setInput_date(String input_date) {
		this.input_date = input_date;
	}
	/**
	 * @return the input_userid
	 */
	public String getInput_userid() {
		return input_userid;
	}
	/**
	 * @param input_userid the input_userid to set
	 */
	public void setInput_userid(String input_userid) {
		this.input_userid = input_userid;
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
    
}
