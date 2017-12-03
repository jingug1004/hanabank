/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.mgmt.vo;

import java.util.List;

/**
 * <pre>
 * Class Name : MgmtInquireJsonVO.java
 * 설명 : 권한조회 Json정보
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015. 1. 9.      slamwin          
 * </pre>
 * 
 * @version : 
 * @author  : slamwin(@irush.co.kr)
 * @since   : 2015. 1. 9.
 */
public class MgmtInquireJsonVO {
	
	private int total; 					// jqGrid에 표시할 전체 페이지 수
	private int page; 					// 현재 페이지
	private int records; 				// 전체 레코드(row)수
	private int result; 				// 결과 레코드(row)수
	private int resultCode; 			// 결과 코드
	private List<MgmtInquireVO> rows; 	// 목록
	/**
	 * @return the total
	 */
	public int getTotal() {
		return total;
	}
	/**
	 * @param total the total to set
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}
	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}
	/**
	 * @return the records
	 */
	public int getRecords() {
		return records;
	}
	/**
	 * @param records the records to set
	 */
	public void setRecords(int records) {
		this.records = records;
	}
	/**
	 * @return the result
	 */
	public int getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(int result) {
		this.result = result;
	}
	/**
	 * @return the resultCode
	 */
	public int getResultCode() {
		return resultCode;
	}
	/**
	 * @param resultCode the resultCode to set
	 */
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	/**
	 * @return the rows
	 */
	public List<MgmtInquireVO> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<MgmtInquireVO> rows) {
		this.rows = rows;
	}

}
