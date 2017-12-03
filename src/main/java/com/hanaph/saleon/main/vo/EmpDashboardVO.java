/**
 * Hana Project
 * Copyright 2014 iRush Co.,
 *
 */
package com.hanaph.saleon.main.vo;

/**
 * <pre>
 * Class Name : EmpDashboardVO.java
 * 설명 : 영업사원 대시보드 정보에 사용되는 Value object class.
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 12. 9.      장일영            최초생성
 * </pre>
 * 
 * @version : 1.0
 * @author  : 장일영(goodhi@irush.co.kr)
 * @since   : 2014. 12. 9.
 */
public class EmpDashboardVO {
	
	String year;	//년
	String month;	//월
	
	String sale_goal_amt;	//판매 목표
	String sale_result_amt;	//판매 실적
	String sale_percent;	//판매 달성율
	
	String in_sale_goal_amt;	//수금 목표
	String in_sale_result_amt;	//수금 실적
	String in_percent;		//수금 달성율
	
	String emp_id;	//영업사원 아이디
	String sawon_nm;	//영업사원 이름
	String part_gb;	//영업사원 파트코드
	String part_nm;	//영업사원 파트명
	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return the sale_goal_amt
	 */
	public String getSale_goal_amt() {
		return sale_goal_amt;
	}
	/**
	 * @param sale_goal_amt the sale_goal_amt to set
	 */
	public void setSale_goal_amt(String sale_goal_amt) {
		this.sale_goal_amt = sale_goal_amt;
	}
	/**
	 * @return the sale_result_amt
	 */
	public String getSale_result_amt() {
		return sale_result_amt;
	}
	/**
	 * @param sale_result_amt the sale_result_amt to set
	 */
	public void setSale_result_amt(String sale_result_amt) {
		this.sale_result_amt = sale_result_amt;
	}
	/**
	 * @return the sale_percent
	 */
	public String getSale_percent() {
		return sale_percent;
	}
	/**
	 * @param sale_percent the sale_percent to set
	 */
	public void setSale_percent(String sale_percent) {
		this.sale_percent = sale_percent;
	}
	/**
	 * @return the in_sale_goal_amt
	 */
	public String getIn_sale_goal_amt() {
		return in_sale_goal_amt;
	}
	/**
	 * @param in_sale_goal_amt the in_sale_goal_amt to set
	 */
	public void setIn_sale_goal_amt(String in_sale_goal_amt) {
		this.in_sale_goal_amt = in_sale_goal_amt;
	}
	/**
	 * @return the in_sale_result_amt
	 */
	public String getIn_sale_result_amt() {
		return in_sale_result_amt;
	}
	/**
	 * @param in_sale_result_amt the in_sale_result_amt to set
	 */
	public void setIn_sale_result_amt(String in_sale_result_amt) {
		this.in_sale_result_amt = in_sale_result_amt;
	}
	/**
	 * @return the in_percent
	 */
	public String getIn_percent() {
		return in_percent;
	}
	/**
	 * @param in_percent the in_percent to set
	 */
	public void setIn_percent(String in_percent) {
		this.in_percent = in_percent;
	}
	/**
	 * @return the emp_id
	 */
	public String getEmp_id() {
		return emp_id;
	}
	/**
	 * @param emp_id the emp_id to set
	 */
	public void setEmp_id(String emp_id) {
		this.emp_id = emp_id;
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
	 * @return the part_gb
	 */
	public String getPart_gb() {
		return part_gb;
	}
	/**
	 * @param part_gb the part_gb to set
	 */
	public void setPart_gb(String part_gb) {
		this.part_gb = part_gb;
	}
	/**
	 * @return the part_nm
	 */
	public String getPart_nm() {
		return part_nm;
	}
	/**
	 * @param part_nm the part_nm to set
	 */
	public void setPart_nm(String part_nm) {
		this.part_nm = part_nm;
	}
	
}
