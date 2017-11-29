/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

import java.util.List;

/**
 * <pre>
 * Class Name : CustomerInfoJsonVO.java
 * 설명 : 고객등록 json VO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 17.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 12. 17.
 */
public class CustomerInfoJsonVO {
	
	private int total; 			// jqGrid에 표시할 전체 페이지 수
	private int page; 			// 현재 페이지
	private int records; 		// 전체 레코드(row)수
	private int result; 		// 결과 레코드(row)수
	private String message;		// 결과 메시지
	
	private List<CustomerInfoVO> rows; // list
	private CustomerInfoVO totalCountInfo; // 합계 정보
	private int resultCount; // 기타사항 삭제 결과
	
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
	public List<CustomerInfoVO> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<CustomerInfoVO> rows) {
		this.rows = rows;
	}
	/**
	 * @return the totalCountInfo
	 */
	public CustomerInfoVO getTotalCountInfo() {
		return totalCountInfo;
	}
	/**
	 * @param totalCountInfo the totalCountInfo to set
	 */
	public void setTotalCountInfo(CustomerInfoVO totalCountInfo) {
		this.totalCountInfo = totalCountInfo;
	}
	/**
	 * @return the resultCount
	 */
	public int getResultCount() {
		return resultCount;
	}
	/**
	 * @param resultCount the resultCount to set
	 */
	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

}
