/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.vo;

import java.util.List;

/**
 * <pre>
 * Class Name : LedgerJsonVO.java
 * 설명 : 제품 관련 json변환용 Value object.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 27.      jung a Woo          
 * </pre>
 * 
 * @version : 
 * @author  : jung a Woo(wja@irush.co.kr)
 * @since   : 2014. 10. 27.
 */
public class ItemJsonVO {
	
	private int total; 			// jqGrid에 표시할 전체 페이지 수
	private int page; 			// 현재 페이지
	private int records; 		// 전체 레코드(row)수
	private int result; 		// 결과 레코드(row)수

	private List<ItemVO> rows;	// 리스트 데이터
	private LedgerVO sumLedger;	// 원장조회 관련 정보
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
	 * @return the rows
	 */
	public List<ItemVO> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<ItemVO> rows) {
		this.rows = rows;
	}
	/**
	 * @return the sumLedger
	 */
	public LedgerVO getSumLedger() {
		return sumLedger;
	}
	/**
	 * @param sumLedger the sumLedger to set
	 */
	public void setSumLedger(LedgerVO sumLedger) {
		this.sumLedger = sumLedger;
	}
	
}
