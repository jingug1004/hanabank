/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

import java.util.List;

/**
 * <pre>
 * Class Name : MyPlJsonVO.java
 * 설명 : MyPl 관련 데이터를 json형태로 변환하기 위한 Value object.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 27.      jung a Woo          
 * </pre>
 * 
 * @version : 1.0
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 10. 27.
 */
public class MyPlJsonVO {
	
	private int total; 			// jqGrid에 표시할 전체 페이지 수
	private int page; 			// 현재 페이지
	private int records; 		// 전체 레코드(row)수
	private int result; 		// 결과 레코드(row)수
	private String message;		// 결과 메시지
	private List<MyPlVO> rows;	// 실제 그리드 데이터
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
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the rows
	 */
	public List<MyPlVO> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<MyPlVO> rows) {
		this.rows = rows;
	}

}
