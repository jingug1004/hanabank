/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.order.vo;

import java.util.List;

/**
 * <pre>
 * Class Name : DeliveryJsonVO.java
 * 설명 : 원장조회 관련 json변환용 Value object.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2015.08.07   김태안           최초작성                
 * </pre>
 * 
 * @version : 
 * @author  : 김태안
 * @since   : 2015. 08. 07.
 */
public class DeliveryJsonVO {
	
	private int total; 			// jqGrid에 표시할 전체 페이지 수
	private int page; 			// 현재 페이지
	private int records; 		// 전체 레코드(row)수
	private int result; 		// 결과 레코드(row)수
	private String message;		// 결과 메시지
	
	private List<DeliveryVO> rows;	//리스트 데이터
	private DeliveryVO sumDelivery;		//원장조회 관련 정보
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
	public List<DeliveryVO> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<DeliveryVO> rows) {
		this.rows = rows;
	}
	/**
	 * @return the sumDelivery
	 */
	public DeliveryVO getSumDelivery() {
		return sumDelivery;
	}
	/**
	 * @param sumDelivery the sumDelivery to set
	 */
	public void setSumDelivery(DeliveryVO sumDelivery) {
		this.sumDelivery = sumDelivery;
	}
	
}
