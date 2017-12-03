/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.vo;

import java.util.List;

/**
 * <pre>
 * Class Name : OrderApprovalJsonVO.java
 * 설명 : 주문 승인 json VO
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 8.      Beomjin          
 * </pre>
 * 
 * @version : 
 * @author  : Beomjin(@irush.co.kr)
 * @since   : 2014. 12. 8.
 */
public class OrderApprovalJsonVO {
	
	private int total; 			// jqGrid에 표시할 전체 페이지 수
	private int page; 			// 현재 페이지
	private int records; 		// 전체 레코드(row)수
	private int result; 		// 결과 레코드(row)수
	private String message;		// 결과 메시지
	
	private List<OrderApprovalVO> rows; // list
	private OrderApprovalVO totalCountInfo; // 합계 정보
	private OrderApprovalVO customerInfo; // 거래처 정보
	private OrderApprovalVO approvalInfo; // 승인 정보
	private List<OrderApprovalVO> promiseList; // 담보약속 목록
	
	private boolean updateResult; // 전체 수행 결과
	private boolean[] rowResultArr; // row별 수행 결과
	private String[] resultCodeArr; // row별 결과 코드
	private String[] resultMessageArr; // row별 결과 메세지
	
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
	public List<OrderApprovalVO> getRows() {
		return rows;
	}
	/**
	 * @param rows the rows to set
	 */
	public void setRows(List<OrderApprovalVO> rows) {
		this.rows = rows;
	}
	/**
	 * @return the totalCountInfo
	 */
	public OrderApprovalVO getTotalCountInfo() {
		return totalCountInfo;
	}
	/**
	 * @param totalCountInfo the totalCountInfo to set
	 */
	public void setTotalCountInfo(OrderApprovalVO totalCountInfo) {
		this.totalCountInfo = totalCountInfo;
	}
	/**
	 * @return the customerInfo
	 */
	public OrderApprovalVO getCustomerInfo() {
		return customerInfo;
	}
	/**
	 * @param customerInfo the customerInfo to set
	 */
	public void setCustomerInfo(OrderApprovalVO customerInfo) {
		this.customerInfo = customerInfo;
	}
	/**
	 * @return the approvalInfo
	 */
	public OrderApprovalVO getApprovalInfo() {
		return approvalInfo;
	}
	/**
	 * @param approvalInfo the approvalInfo to set
	 */
	public void setApprovalInfo(OrderApprovalVO approvalInfo) {
		this.approvalInfo = approvalInfo;
	}
	/**
	 * @return the promiseList
	 */
	public List<OrderApprovalVO> getPromiseList() {
		return promiseList;
	}
	/**
	 * @param promiseList the promiseList to set
	 */
	public void setPromiseList(List<OrderApprovalVO> promiseList) {
		this.promiseList = promiseList;
	}
	/**
	 * @return the updateResult
	 */
	public boolean isUpdateResult() {
		return updateResult;
	}
	/**
	 * @param updateResult the updateResult to set
	 */
	public void setUpdateResult(boolean updateResult) {
		this.updateResult = updateResult;
	}
	/**
	 * @return the rowResultArr
	 */
	public boolean[] getRowResultArr() {
		return rowResultArr;
	}
	/**
	 * @param rowResultArr the rowResultArr to set
	 */
	public void setRowResultArr(boolean[] rowResultArr) {
		this.rowResultArr = rowResultArr;
	}
	/**
	 * @return the resultCodeArr
	 */
	public String[] getResultCodeArr() {
		return resultCodeArr;
	}
	/**
	 * @param resultCodeArr the resultCodeArr to set
	 */
	public void setResultCodeArr(String[] resultCodeArr) {
		this.resultCodeArr = resultCodeArr;
	}
	/**
	 * @return the resultMessageArr
	 */
	public String[] getResultMessageArr() {
		return resultMessageArr;
	}
	/**
	 * @param resultMessageArr the resultMessageArr to set
	 */
	public void setResultMessageArr(String[] resultMessageArr) {
		this.resultMessageArr = resultMessageArr;
	}
}
