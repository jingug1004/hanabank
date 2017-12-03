/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.business.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.hanaph.saleon.business.controller.CompanyCardMgmtContoller;
import com.hanaph.saleon.business.dao.OrderApprovalDAO;
import com.hanaph.saleon.business.vo.OrderApprovalVO;
import com.hanaph.saleon.common.utils.StringUtil;
import com.hanaph.saleon.order.dao.OrderDAO;
import com.hanaph.saleon.order.vo.ItemVO;

@Service(value="orderApprovalService")
public class OrderApprovalServiceImpl implements OrderApprovalService {
	
	private static final Logger logger = Logger.getLogger(CompanyCardMgmtContoller.class);
	
	@Autowired
	private OrderApprovalDAO orderApprovalDAO;
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Autowired
	private DataSourceTransactionManager transactionManager;

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#getOrderApprovalGridList(java.util.Map)
	 */
	@Override
	public List<OrderApprovalVO> getOrderApprovalGridList(Map<String, String> paramMap) {
		List<OrderApprovalVO> orderApprovalList = orderApprovalDAO.getOrderApprovalGridList(paramMap); // 주문내역 목록
		
		return orderApprovalList;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#getOrderApprovalGridListExcel(java.util.Map)
	 * kta 
	 */
	@Override
	public List<OrderApprovalVO> getOrderApprovalGridListExcel(Map<String, String> paramMap) {
		List<OrderApprovalVO> orderApprovalList = orderApprovalDAO.getOrderApprovalGridListExcel(paramMap); // 주문내역 엑셀 목록
		
		return orderApprovalList;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#getOrderApprovalGridTotalCount(java.util.Map)
	 */
	@Override
	public OrderApprovalVO getOrderApprovalGridTotalCount(Map<String, String> paramMap) {
				
		OrderApprovalVO returnVO = new OrderApprovalVO();
		returnVO.setTotal_reamt(orderApprovalDAO.getUnapprovedAmount(paramMap)); // 총 미승인금액
		returnVO.setTotal_reamt_real(orderApprovalDAO.getUnapprovalAmountReal(paramMap));// 총 미승인금액(실조건)			
		
		return returnVO;
	}
 	
	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#getOrderDetailGridList(java.util.Map)
	 */
	@Override
	public List<OrderApprovalVO> getOrderDetailGridList(Map<String, String> paramMap) {
		return orderApprovalDAO.getOrderDetailGridList(paramMap); // 주문 상세 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#getOrderDetailGridTotalCount(java.util.Map)
	 */
	@Override
	public OrderApprovalVO getOrderDetailGridTotalCount(Map<String, String> paramMap) {
		
		OrderApprovalVO returnVO = orderApprovalDAO.getOrderDetailGridTotalCount(paramMap); // 주문 상세 목록 총 수
		returnVO.setReamt(orderApprovalDAO.getUnapprovedAmount(paramMap)); // 미승인금액
		
		return returnVO;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#getCustomerInfo(java.util.Map)
	 */
	@Override
	public OrderApprovalVO getCustomerInfo(Map<String, String> paramMap) {
		return orderApprovalDAO.getCustomerInfo(paramMap); // 거래처 정보
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#getApprovalInfo(java.util.Map)
	 */
	@Override
	public OrderApprovalVO getApprovalInfo(Map<String, String> paramMap) {
		return orderApprovalDAO.getApprovalInfo(paramMap); // 승인 정보
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#getPromiseGridList(java.util.Map)
	 */
	@Override
	public List<OrderApprovalVO> getPromiseGridList(Map<String, String> paramMap) {
		return orderApprovalDAO.getPromiseGridList(paramMap); // 담보약속 목록
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#getPromiseGridTotalCount(java.util.Map)
	 */
	@Override
	public OrderApprovalVO getPromiseGridTotalCount(Map<String, String> paramMap) {
		return orderApprovalDAO.getPromiseGridTotalCount(paramMap); // 담보약속 목록 총 수
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#updateOrderApproval(java.util.Map)
	 */
	@Override
	public OrderApprovalVO updateOrderApproval(Map<String, Object> paramMap) {
		
		OrderApprovalVO returnVO = new OrderApprovalVO();
		
		/*parameter*/
		String emp_code = (String) paramMap.get("emp_code"); // 사원 코드
		String app_date = (String) paramMap.get("app_date"); // 승인일
		String receipt_gb = (String) paramMap.get("receipt_gb"); // 승인 구분
		String return_desc = (String) paramMap.get("return_desc"); // 반려 사유
		String jaego = app_date.substring(0, 8) + "01"; // 재고일 : 재고테이블의 제품별 재고일 매달1일로 정해짐
		String[] gumae_no = (String[]) paramMap.get("gumae_no"); // 접수번호
		String[] req_date = (String[]) paramMap.get("req_date"); // 주문요청일
		String[] cust_id = (String[]) paramMap.get("cust_id"); // 거래처 코드
		String[] cust_nm = (String[]) paramMap.get("cust_nm"); // 거래처 명
		String[] rcust_id = (String[]) paramMap.get("rcust_id"); // 판매처 코드
		String[] rcust_nm = (String[]) paramMap.get("rcust_nm"); // 판매처 명
		String[] slip_gb = (String[]) paramMap.get("slip_gb"); // 주문구분
		String[] ymd = (String[]) paramMap.get("ymd"); // 요청일
		String[] detail_gumae_no = (String[]) paramMap.get("detail_gumae_no"); // 접수번호
		String[] input_seq = (String[]) paramMap.get("input_seq"); // 등록번호
		String[] qty = (String[]) paramMap.get("qty"); // 승인수량
		String[] amt = (String[]) paramMap.get("amt"); // 공급가액
		String[] vat = (String[]) paramMap.get("vat"); // 세액
		String[] dc_amt = (String[]) paramMap.get("dc_amt"); // 할인액 
		String   thisorder_amt =  (String) paramMap.get("thisorder_amt"); // 현주문의 공급총액 
		
		/*수행 결과를 담을 배열 생성*/
		boolean[] rowResultArr = new boolean[gumae_no.length]; // 각 row 별 프로세스 결과
		String[] resultCodeArr = new String[gumae_no.length]; // 각 row 별 결과 코드
		String[] resultMessageArr = new String[gumae_no.length]; // 각 row 별 결과 메세지
		
		for (int i = 0; i < gumae_no.length; i++) {
			
			boolean rowResult = false; // row별 수행 결과
			String resultCode = ""; // 결과 코드
			String resultMessage = ""; // 에러메세지
			
			resultMessage += "요청일자:" + req_date[i] + " 접수번호:" + gumae_no[i] + "\n";
			resultMessage += "거래처:" + cust_id[i] + " " + cust_nm[i] + " 판매처:" + rcust_id[i] + " " + rcust_nm[i] + "\n";
			
			/*parameter 셋팅*/
			OrderApprovalVO paramVO = new OrderApprovalVO();
			
			paramVO.setEmp_code(emp_code); // 사원 코드
			paramVO.setApp_date(app_date.replace("-", "")); // 승인일
			paramVO.setReceipt_gb(receipt_gb); // 승인 구분
			paramVO.setReturn_desc(return_desc); // 반려 사유
			paramVO.setJaego(jaego); // 재고일
			paramVO.setGumae_no(gumae_no[i].replace("-", "")); // 접수번호
			paramVO.setReq_date(req_date[i]); // 주문요청일
			paramVO.setCust_id(cust_id[i]); // 거래처 코드
			paramVO.setRcust_id(rcust_id[i]); // 판매처 코드
			paramVO.setSlip_gb(slip_gb[i]); // 판매처 코드
			
			/* 트랜잭션 생성 */
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
			def.setTimeout(5000);
	        def.setReadOnly(false);
	        def.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
			TransactionStatus status = transactionManager.getTransaction(def);
			
			try{
				/*승인 또는 추가 승인 시 체크 사항*/
				if ("approval".equals(receipt_gb) || "add_order".equals(receipt_gb)) {
					if ("".equals(resultCode)) {
						/*여신한도 체크 : 주문이후 관리부에서 여신한도 변경가능성 있음*/
						OrderApprovalVO creditLimitVO = new OrderApprovalVO();
						creditLimitVO = orderApprovalDAO.getCreditLimit(paramVO); // 여신한도 
						
						double credit_limit_amt = creditLimitVO.getCredit_limit_amt(); // 여신한도
						double tot_credit = creditLimitVO.getTot_credit();             // 총여신
						//공급총액 계산
						double order_amt =  0;
						if (detail_gumae_no != null && gumae_no[i].replace("-", "").equals(detail_gumae_no[0])) {
							order_amt = Double.parseDouble(thisorder_amt);
						}else{
							order_amt =  orderApprovalDAO.getThisorderAmt(paramVO).getThisorder_amt();
						} 
                        
						
						
						if (credit_limit_amt > 0 && tot_credit + order_amt > credit_limit_amt) {
							resultCode = "F_003";
							resultMessage += "여신한도 초과입니다.(여신한도:" + StringUtil.makeMoneyType(String.valueOf(Math.round(credit_limit_amt))) + ")";
						}
					}
					
					if ("".equals(resultCode)) {
						 /*출하중지처 체크*/
						String budong_yn = orderApprovalDAO.getBudongFlag(paramVO); // 출하중지 여부
						
						if ("Y".equals(budong_yn)) {
							resultCode = "F_004";
							resultMessage += "이 주문의 거래처가 출하 중지처 입니다.";
						}
					}
					
					if ("".equals(resultCode)) {
						/*제품 체크*/
						OrderApprovalVO itemVO = orderApprovalDAO.getItemInfo(paramVO); // 제품 체크
						
						if (null == itemVO) { // 검색 결과가 없을 경우 셋팅
							itemVO = new OrderApprovalVO();
							itemVO.setUse_yn("Y");
							itemVO.setChul_yn("N");
						}
						
						String use_yn = itemVO.getUse_yn();
						String chul_yn = itemVO.getChul_yn();
						
						if ("N".equals(use_yn) || "Y".equals(chul_yn)) {
							resultCode = "F_005";
							resultMessage += "주문 자료 중 제품이 단종 또는 출하중지 되었습니다.";
						}
					} 
					
					
					/*
					if ("".equals(resultCode)) {
					
						 kta 2016.09.30 단가*수량 이 금액과 다른주문 체크
						 이 로직을 쓰고 싶었으나 쓰지 못함. 이유는 체크는 되는데 승인수량을  변경할수 없기 때문임. 수량변경후 저장하면 이 에러체크때문에 저장이 안됨. 이 로직 대신 주문내역에 오류여부를 미리 보여주는것으로 바꿈.
						
						OrderApprovalVO itemVO1 = orderApprovalDAO.getDangaQtyAmtErrJumun(paramVO); // 주문금액체크 		
						
						if (null == itemVO1) { // 검색 결과가 없을 경우 셋팅
						}else{
							String errvalue = itemVO1.getDangga_qty_amt_errvalue(); 
							if ("".equals(errvalue) == false) { 
								resultCode = "F_014";
								resultMessage += errvalue + " 의 단가*수량이 금액과 일치하지 않습니다.";
							}  
						}  
					} */ 
					
					if ("".equals(resultCode)) {
						/*허가증 체크*/
						if(cust_id[i].startsWith("1")){
							/*해당 주문의 상세에서 마약류, 향정신성의약품이 있는지 체크*/
							Map<String, String> paramMap2 = new HashMap<String, String>();
							
							paramMap2.put("gumae_no", gumae_no[i].replace("-", "")); // 구매번호
							paramMap2.put("gs_empCode", cust_id[i]); // 거래처 코드
							
							boolean mayak = false; // 마약류 여부
							boolean hangjung = false; // 향정신성의약품 여부
							
							/*제품 구분코드 ('010' - 마약류, '020' - 향정신성의약품)*/
							List<OrderApprovalVO> itemYnList = orderApprovalDAO.getItemYnList(paramMap2); // 주문상세의 제품들에 대한 마약류, 향정신성의약품 여부
							
							for (int j = 0; j < itemYnList.size(); j++) {
								OrderApprovalVO itemVO = itemYnList.get(j);
								
								if ("010".equals(itemVO.getItem_gb1())) { // 마약류
									mayak = true;
								} else if ("020".equals(itemVO.getItem_gb1())) { // 향정신성의약품
									hangjung = true;
								}
							}
							
							/*마약류, 향정신성의약품이 있다면 허가증이 있는지 체크*/
							if (mayak) {
								paramMap2.put("ls_item_gb1", "010");
								ItemVO itemVO = orderDAO.getItemGb(paramMap2);
								
								if ("N".equals(itemVO.getLs_yn())) { // 허가증이 없을 경우
									resultCode = "F_011";
									resultMessage += "해당 거래처에 마약류 취급 허가증이 없습니다.";
								}
							}
							
							if (hangjung) {
								paramMap2.put("ls_item_gb1", "020");
								ItemVO itemVO = orderDAO.getItemGb(paramMap2);
								
								if ("N".equals(itemVO.getLs_yn())) { // 허가증이 없을 경우
									resultCode = "F_012";
									resultMessage += "해당 거래처에 향정신성의약품 취급 허가증이 없습니다.";
								}
							}
						}
					}
				}
				
				/*승인 구분에 따른 프로세스 수행*/
				if (("approval".equals(receipt_gb) || "add_order".equals(receipt_gb)) && "".equals(resultCode)) { // 승인, 추가 승인처리
					
					/*2015-01-21 수정 : 승인 상태에서도 바로 반려가 되도록 수정 */
					
					/*주문상태 체크*/
					OrderApprovalVO orderVO = orderApprovalDAO.getReceiptGb(paramVO); // 주문상태
					String as_receipt_gb = orderVO.getReceipt_gb(); // 주문 상태
					String as_ymdt = orderVO.getYmdt(); // 승인일자
					Date today = new Date(); // 현재날짜
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); // date 포맷
					String as_today = format.format(today);
					
					/*승인 날짜가 과거인 경우 승인취소 불가*/
					if (("2".equals(as_receipt_gb)) && (Integer.parseInt(as_today) - Integer.parseInt(as_ymdt) > 0)) {
						resultCode = "F_013";
						resultMessage += "승인일이 하루이상 지나서 승인취소 불가합니다.";
					}
					
					/*주문상태에 따른 분기처리*/
					if (null == as_receipt_gb || "".equals(as_receipt_gb)) {
						resultCode = "F_006";
						resultMessage += "이 주문의 자료가 없습니다.";
					} else if ("2".equals(as_receipt_gb)) {
						resultCode = "F_007";
						resultMessage += "이 주문은 이미 승인 되었습니다.";
					}
					
					if ("".equals(resultCode)) {
						if ("3".equals(as_receipt_gb)) { // 반려 -> 승인 일 경우 접수로 먼저 변경해준다.
							paramVO.setApp_no(orderVO.getApp_no());
							
							/*SALE_ON 주문 MASTER 변경*/
							orderApprovalDAO.cancelOrderMasterApproval(paramVO);
							
							/*SALE_ON 주문 Detail 변경*/
							orderApprovalDAO.cancelOrderDetailApproval(paramVO);
							
							/*ERP 재고 변경*/
							orderApprovalDAO.updateCancelOrderStock(paramVO);
							
						}
						
						/*승인번호 생성*/
						String max = orderApprovalDAO.getProcedureCall(paramVO);
						String app_no = app_date.replace("-", "") + String.format("%04d", Integer.parseInt(max));
						paramVO.setApp_no(app_no); // 승인번호 셋팅
						
						/*
						 * 주문 상세 승인 수량 변경 20150113 추가
						 * 승인, 추가 승인 된 주문일 경우에만 수행
						 */
						if (detail_gumae_no != null && gumae_no[i].replace("-", "").equals(detail_gumae_no[0])) {
							for (int j = 0; j < input_seq.length; j++) {
								OrderApprovalVO qtyUpdateVO = new OrderApprovalVO();
								
								qtyUpdateVO.setYmd(ymd[j]); // 요청일
								qtyUpdateVO.setDetail_gumae_no(detail_gumae_no[j]); // 접수번호
								qtyUpdateVO.setInput_seq(input_seq[j]); // 등록번호
								qtyUpdateVO.setQty(qty[j]); // 승인수량
								qtyUpdateVO.setAmt(amt[j]); // 공급가액
								qtyUpdateVO.setVat(vat[j]); // 세액
								qtyUpdateVO.setDc_amt(dc_amt[j]); // 할인액
								
								orderApprovalDAO.updateOrderDetailQty(qtyUpdateVO);
							}
						}
						
						/*마스터 변경*/
						int updateMasterResult = orderApprovalDAO.updateOrderMasterApproval(paramVO);
						
						if (updateMasterResult > 0) { // 마스터 변경 성공
							if ("approval".equals(receipt_gb)) { // 승인 시
								orderApprovalDAO.updateOrderStock(paramVO); // 재고 변경 : 승인월의 1일자 가출고(온라인주문용출고수량) 빼주고 가용재고 더해준다.(주문시 가출고 더하고 가용재고 뺐으니까) 이렇게 하면 가용재고는 늘어나게 됨
								orderApprovalDAO.insertTransferOrderMaster(paramVO); // ERP 주문 MASTER 이관
								
							} else if ("add_order".equals(receipt_gb)) { // 추가 승인 시
								orderApprovalDAO.updateAddOrderStock(paramVO); // ERP 재고 변경
								
								paramVO.setBigo(StringUtil.nvl(return_desc, "(추가)")); // 2015-01-21 추가 : 추가 승인일 경우 사유가 입력되어있다면 추가 대신 사유가 들어가도록 수정
								
								orderApprovalDAO.insertTransferAddOrderMaster(paramVO); // ERP 주문 MASTER 이관
							}
							orderApprovalDAO.insertTransferOrderDetail(paramVO); // ERP 주문 DETAIL 이관
							
							/*수행 결과*/
							if ("approval".equals(receipt_gb)) { // 승인 시
								resultCode = "S_001";
								resultMessage += "승인 성공";
							} else if ("add_order".equals(receipt_gb)) { // 추가 승인 시
								resultCode = "S_003";
								resultMessage += "추가 승인 성공";
							}
							
							rowResult = true;
						} else { // 마스터 변경 실패
							resultCode = "F_006";
							resultMessage += "이 주문의 자료가 없습니다.";
						}
					}
				} else if ("return".equals(receipt_gb) && "".equals(resultCode)) { // 반려처리
					
					/*2015-01-21 수정 : 승인 상태에서도 바로 반려가 되도록 수정 */
					
					/*주문상태 체크*/
					OrderApprovalVO orderVO = orderApprovalDAO.getReceiptGb(paramVO); // 주문상태
					String as_receipt_gb = orderVO.getReceipt_gb(); // 주문 상태
					String as_ymdt = orderVO.getYmdt(); // 승인일자
					Date today = new Date(); // 현재날짜
					SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); // date 포맷
					String as_today = format.format(today);
					
					/*승인 날짜가 과거인 경우 승인취소 불가*/
					if (("2".equals(as_receipt_gb)) && (Integer.parseInt(as_today) - Integer.parseInt(as_ymdt) > 0)) {
						resultCode = "F_013";
						resultMessage += "승인일이 하루이상 지나서 승인취소 불가합니다.";
					}
					
					/*주문상태에 따른 분기처리*/
					if (null == as_receipt_gb || "".equals(as_receipt_gb)) {
						resultCode = "F_006";
						resultMessage += "이 주문의 자료가 없습니다.";
					} else if ("3".equals(as_receipt_gb)) {
						resultCode = "F_008";
						resultMessage += "이 주문은 이미 반려 되었습니다.";
					}
					
					if ("".equals(resultCode)) {
						if ("2".equals(as_receipt_gb)) { // 승인 -> 반려 일 경우 접수로 먼저 변경해준다.
							paramVO.setApp_no(orderVO.getApp_no());
							
							/*SALE_ON 주문 MASTER 변경*/
							orderApprovalDAO.cancelOrderMasterApproval(paramVO);
							
							/*SALE_ON 주문 Detail 변경*/
							orderApprovalDAO.cancelOrderDetailApproval(paramVO);
							
							/*ERP 재고 변경*/
							orderApprovalDAO.updateCancelOrderStock(paramVO);
							
							/*ERP 주문 DETAIL 이관 자료 삭제*/
							orderApprovalDAO.deleteTransferOrderDetail(paramVO);
							
							/*ERP 주문 MASTER 이관 자료 삭제*/
							orderApprovalDAO.deleteTransferOrderMaster(paramVO);
							
						}
						
						/*마스터 변경*/
						int updateMasterResult = orderApprovalDAO.updateOrderMasterReturn(paramVO);
						
						if (updateMasterResult > 0) { // 마스터 변경 성공
							orderApprovalDAO.updateOrderStock(paramVO); // ERP 재고 변경 : 승인월의 1일자 가출고(온라인주문용출고수량) 빼주고 가용재고 더해준다.(주문시 가출고 더하고 가용재고 뺐으니까) 이렇게 하면 가용재고는 늘어나게 됨
							
							rowResult = true;
							resultCode = "S_002";
							resultMessage = "반려 성공";
						} else { // 마스터 변경 실패
							resultCode = "F_006";
							resultMessage += "이 주문의 자료가 없습니다.";
						}
					}
				}
				
				/*성공시 커밋, 실패시 롤백 */
				if(rowResult){
					transactionManager.commit(status);
					
				} else {
					transactionManager.rollback(status);
				}
				
			}catch(Exception e){
				rowResult = false;
				logger.error(" :::: Exception ===> "+rowResult + "//" +resultCode + "//" + resultMessage + System.getProperty("line.separator") + e.getMessage().replaceAll(System.getProperty("line.separator"), " "));
				transactionManager.rollback(status);
				resultMessage += "	[rollbackForException:"+e.getMessage().replaceAll(System.getProperty("line.separator"), " ")+"]";
			}
			
			//logger.info(resultMessage);
			rowResultArr[i] = rowResult; // 수행결과
			resultCodeArr[i] = resultCode; // 결과 코드
			resultMessageArr[i] = resultMessage; // 결과 메세지
		}
		
		/*각 row 별 수행 결과를 담은 배열을 return 한다.*/
		returnVO.setRowResultArr(rowResultArr);
		returnVO.setResultCodeArr(resultCodeArr);
		returnVO.setResultMessageArr(resultMessageArr);
		
		return returnVO;
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#getStoreLocCount()
	 */
	@Override
	public int getStoreLocCount() {
		return orderApprovalDAO.getStoreLocCount(); // 이월작업 체크
	}

	/* (non-Javadoc)
	 * @see com.hanaph.saleon.business.service.OrderApprovalService#cancelOrderApproval(java.util.Map)
	 */
	@Override
	public OrderApprovalVO cancelOrderApproval(Map<String, Object> paramMap) {
		
		OrderApprovalVO returnVO = new OrderApprovalVO();
		
		/*parameter*/
		String emp_code = (String) paramMap.get("emp_code"); // 사원 코드
		String app_date = (String) paramMap.get("app_date"); // 승인일
		String receipt_gb = (String) paramMap.get("receipt_gb"); // 승인 구분
		String return_desc = (String) paramMap.get("return_desc"); // 반려 사유
		String jaego = app_date.substring(0, 8) + "01"; // 재고일
		String[] gumae_no = (String[]) paramMap.get("gumae_no"); // 접수번호
		String[] app_no = (String[]) paramMap.get("app_no"); // 승인번호
		String[] req_date = (String[]) paramMap.get("req_date"); // 주문요청일
		String[] cust_id = (String[]) paramMap.get("cust_id"); // 거래처 코드
		String[] cust_nm = (String[]) paramMap.get("cust_nm"); // 거래처 명
		String[] rcust_id = (String[]) paramMap.get("rcust_id"); // 판매처 코드
		String[] rcust_nm = (String[]) paramMap.get("rcust_nm"); // 판매처 명 
		String[] slip_gb = (String[]) paramMap.get("slip_gb"); // 주문구분
		
		/*수행 결과를 담을 배열 생성*/
		boolean[] rowResultArr = new boolean[gumae_no.length]; // 각 row 별 프로세스 결과
		String[] resultCodeArr = new String[gumae_no.length]; // 각 row 별 결과 코드
		String[] resultMessageArr = new String[gumae_no.length]; // 각 row 별 결과 메세지
		
		for (int i = 0; i < gumae_no.length; i++) {
			
			boolean rowResult = false; // row별 수행 결과
			String resultCode = ""; // 결과 코드
			String resultMessage = ""; // 에러메세지
			
			resultMessage += "요청일자:" + req_date[i] + " 접수번호:" + gumae_no[i] + "\n";
			resultMessage += "거래처:" + cust_id[i] + " " + cust_nm[i] + " 판매처:" + rcust_id[i] + " " + rcust_nm[i] + "\n";
			
			/*parameter 셋팅*/
			OrderApprovalVO paramVO = new OrderApprovalVO();
			
			paramVO.setEmp_code(emp_code); // 사원 코드
			paramVO.setApp_date(app_date); // 승인일
			paramVO.setReceipt_gb(receipt_gb); // 승인 구분
			paramVO.setReturn_desc(return_desc); // 반려 사유
			paramVO.setJaego(jaego); // 재고일
			paramVO.setGumae_no(gumae_no[i].replace("-", "")); // 접수번호
			paramVO.setApp_no(app_no[i].replace("-", "")); // 접수번호
			paramVO.setReq_date(req_date[i]); // 주문요청일
			paramVO.setCust_id(cust_id[i]); // 거래처 코드
			paramVO.setRcust_id(rcust_id[i]); // 판매처 코드
			paramVO.setSlip_gb(slip_gb[i]); // 주문구분
			
			/*주문상태 체크*/
			OrderApprovalVO orderVO = orderApprovalDAO.getReceiptGb(paramVO);
			
			String as_receipt_gb = orderVO.getReceipt_gb(); // 주문 상태
			String as_accept_yn = orderVO.getAccept_yn(); // 출하처리 여부
			String as_ymdt = orderVO.getYmdt(); // 승인일자
			Date today = new Date(); // 현재날짜
			SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd"); // date 포맷
			String as_today = format.format(today);
			
			/*주문 상태, 출하처리 여부에 따라 분기*/
			if (null == as_receipt_gb || "".equals(as_receipt_gb)) {
				resultCode = "F_006";
				resultMessage += "이 주문의 승인 자료가 없습니다.";
			} else if ("X".equals(as_accept_yn)) {
				resultCode = "F_009";
				resultMessage += "이 주문은 이미 출하처리 되었습니다.";
			} else if ("1".equals(as_receipt_gb)) {
				resultCode = "F_010";
				resultMessage += "이 주문은 이미 승인취소 되었습니다.";
			}
			
			/*승인 날짜가 과거인 경우 승인취소 불가*/
			if (("2".equals(as_receipt_gb)) && (Integer.parseInt(as_today) - Integer.parseInt(as_ymdt) > 0)) {
				resultCode = "F_013";
				resultMessage += "승인일이 하루이상 지나서 승인취소 불가합니다.";
			}
			
			if ("".equals(resultCode)) {
				/*SALE_ON 주문 MASTER 변경*/
				orderApprovalDAO.cancelOrderMasterApproval(paramVO);
				
				/*SALE_ON 주문 Detail 변경*/
				orderApprovalDAO.cancelOrderDetailApproval(paramVO);
				
				/*ERP 재고 변경*/
				orderApprovalDAO.updateCancelOrderStock(paramVO);
				
				if ("2".equals(as_receipt_gb)) { // 승인 취소일 경우
					
					/*ERP 주문 DETAIL 이관 자료 삭제*/
					orderApprovalDAO.deleteTransferOrderDetail(paramVO);
					
					/*ERP 주문 MASTER 이관 자료 삭제*/
					orderApprovalDAO.deleteTransferOrderMaster(paramVO);
					
					resultCode = "S_004";
					resultMessage = "승인 취소 성공";
				} else { // 반려 취소일 경우
					resultCode = "S_005";
					resultMessage = "반려 취소 성공";
				}
				
				rowResult = true;
			}
			
			rowResultArr[i] = rowResult; // 수행결과
			resultCodeArr[i] = resultCode; // 결과 코드
			resultMessageArr[i] = resultMessage; // 결과 메세지
		}
		
		/*각 row 별 수행 결과를 담은 배열을 return 한다.*/
		returnVO.setRowResultArr(rowResultArr);
		returnVO.setResultCodeArr(resultCodeArr);
		returnVO.setResultMessageArr(resultMessageArr);
		
		return returnVO; 
	}
}
