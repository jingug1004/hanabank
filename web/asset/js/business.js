/** 
 * @파일명 : business.js
 * @기능 : 영업관리 공통 스크립트. 
 * @Comments : Business.함수명() 형태로 호출.
 * 			   영업관리에서 공통으로 쓰이는 함수
 * @최초작성일 : 2014/10/27
 * @author : 윤범진  
 * @수정내역 : 
 */
var Business = {
	
	/**
	 * 거래처 선택하고 팝업창 닫기	
	 * @param type	팝업 타입
	 */
	selectValue : function(type){
		var targetCode = self.name; // code를 셋팅해줄 target
		var targetName = targetCode + "_name"; // name을 셋팅해줄 target
		var id = $("#grid_list").jqGrid('getGridParam','selrow'); // 현재 선택한 row
		
		if (id)	{
			var ret = $("#grid_list").jqGrid('getRowData',id);
			var value_id = "";
			var value_nm = "";
			
			/*
			 * 팝업 타입에 따라 셋팅해줘야 할 값과 셋팅 될 위치를 설정한다. 
			 */
			if ("customer" == type) { // 거래처
				value_id = ret.cust_id;
				value_nm = ret.cust_nm;
			} else if ("part" == type) { // 파트
				value_id = ret.part_id;
				value_nm = ret.part_nm;
			} else if ("team" == type) { // 부서
				value_id = ret.dept_id;
				value_nm = ret.dept_nm;
			} else if ("emp" == type) { // 사원
				value_id = ret.emp_id;
				value_nm = ret.emp_nm;
			} else if ("item" == type) { // 제품
				value_id = ret.item_id;
				value_nm = ret.item_nm;
			}
			
			/*
			 * 부모창이 없어도 에러가 나지 않도록 예외처리
			 */
			try {
				$("#"+targetCode, opener.document).val(value_id);
				$("#"+targetName, opener.document).val(value_nm);
			} catch (e) {
			} finally {
				self.close(); // 창 닫기
			}
		} else {
			alert("선택해주세요.");
		}
	},
	
	/**
	 * 거래처명 검색 ajax
	 * @param target	거래처명이 셋팅 될 target
	 */
	getCustomerName : function(target){
		var cust_id = $("#" + target).val();
		
		if ("" != cust_id) { // 거래처 코드가 있을 경우에만 수행
			$.ajax({
				type:"POST",
				url:ONLINE_ROOT + "/business/common/customerNameAjax.do",
				data:{ cust_id : cust_id },
				dataType:"json",
				success:function(data){
					if ("" != data.cust_nm) {
						$("#" + target + "_name").val(data.cust_nm);
						
						try {
							callback(); // 완료 후 callback 함수 실행
						} catch (e) {
						}
						
					} else {
						alert("등록된 거래처 코드가 아닙니다.");
						$("#" + target + "_name").val("");
						$("#" + target).val("");
						$("#" + target).focus();
					}
				}
			});
		} else {
			$("#" + target + "_name").val("");
		}
	},
	
	/**
	 * 이번달 셋팅
	 * @param target	이번달 정보가 셋팅 될 target
	 */
	setThisMonth : function(target){
		var now = new Date();
		var year= now.getFullYear();
		var mon = (now.getMonth()+1)>9 ? ""+(now.getMonth()+1) : "0"+(now.getMonth()+1);
		
		var today = year + "-" + mon;
		
		$("#"+target).val(today);
	},
	
	/**
	 * 배열에 데이터 셋팅
	 * @param array	배열명
	 * @param value	값
	 */
	addArray : function(array, value){
		// 배열에 같은 값 존재여부 검색
		var isExist = false; // 존재여부 flag
		for (var i = 0; i < array.length; i++) { // value 위치 검색
			if (array[i] == value) {
				isExist = true;
				break;
			}
		}
		
		if (!isExist) { // 없을 경우
			array.push(value); // 배열에 현재 value 더함	
		}
	}
};