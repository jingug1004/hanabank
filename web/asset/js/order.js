/** 
 * @파일명 : order.js
 * @기능 : 온라인발주 메뉴 공통 스크립트. 
 * @Comments : Order.함수명() 형태로 호출.
 * @최초작성일 : 2014/10/17
 * @author : 우정아  
 * @수정내역 : 우정아(2014/10/17) : 최초작성
 */
var Order = {

	/**
	 * 판매처 조회 팝업창에서 판매처 선택하면 그 정보를 opener에게 전달하고 창닫기
	 */
	selectValue : function() {
		var targetCode = self.name;
		var targetName = targetCode + "_name";
		var id = $("#grid_list").jqGrid('getGridParam', 'selrow');

		if (id) {
			var ret = $("#grid_list").jqGrid('getRowData', id);

			var cust_id = ret.cust_id;
			var cust_nm = ret.cust_nm;

			try {
				$("#" + targetCode, opener.document).val(cust_id);
				$("#" + targetName, opener.document).val(cust_nm);
				opener.storeChkAjax(cust_id);
			} catch (e) {
			} finally {
				self.close();
			}
		} else {
			alert("판매처를 선택해주세요.");
		}
	},
	
	/**
	 *  금액 커스텀 포맷
	 *  @param	cellvalue	금액
	 */
	amountFormatter : function(cellvalue){
		var returnValue = "";
		
		if(cellvalue == null){
			cellvalue = "0";
		}
		
		if ("0" != cellvalue) {
			returnValue = Math.round(cellvalue);
			returnValue = Commons.addComma(returnValue);
		}
		
		return returnValue.toString();
	},
	
	/**
	 *  제품명 커스텀 포맷
	 *  @param	cellvalue	제품명
	 *  @param	options		jqGrid options
	 *  @param	rowObject	jqGrid row object
	 */
	saupjangFormatter : function(cellvalue, options, rowObject){
		var returnValue = "";
		if(rowObject.saupjang_cd=='104'){
			returnValue= '<font color=\"red\">[향정신성의약품]</font>'+cellvalue;
		}else{
			returnValue = cellvalue;
		}
		
		if(rowObject.ls_chul_yn=='Y'){
			returnValue += '<font color=\"blue\">[출하중지제품]</font>';
		}
		
		return returnValue.toString();
	}
	

};