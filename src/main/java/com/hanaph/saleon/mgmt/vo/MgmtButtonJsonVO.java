/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.mgmt.vo;

import java.util.List;

/**
 * <pre>
 * Class Name : MgmtButtonJsonVO.java
 * 설명 : 공통 버튼 Json정보
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
public class MgmtButtonJsonVO {
	
	private int total; 				// jqGrid에 표시할 전체 페이지 수
	private int page; 				// 현재 페이지
	private int records; 			// 전체 레코드(row)수
	private int result; 			// 결과 레코드(row)수
	private int resultCode; 		// 결과 코드
	private String message;			// 결과 메시지
	
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
	 * @return rows
	 */
	public List<MgmtButtonVO> getRows() {
		return rows;
	}
	
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<MgmtButtonVO> rows) {
		this.rows = rows;
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
	
	private List<MgmtButtonVO> rows;

}
