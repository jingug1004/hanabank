/** 
 * @파일명 : formCheck.js
 * @기능 : form value 유효성 체크  
 * @Comments : formCheck.함수명() 형태로 호출
 * @최초작성일 : 2014/10/27
 * @author : 우정아
 * @수정내역 : 우정아(2014/10/27) : 최초작성
 */
var formCheck = {
		
	/**
	 * 입력값이  null 인지 체크한다
	 * @param	str	문자열
	 */
	isNull : function(str) {
		if (str == null || str == "") {
			return true;
		} else {
			return false;
		}
	},
	
	/**
	 * 입력값이 스페이스 이외의 의미있는 값이 있는지 체크한다
	 * if (isEmpty(form.keyword)){
	 *       alert('값을 입력하여주세요');
	 * }
	 * @param	str	문자열
	 */
	isEmpty : function(str) {
		if (str == null || str.replace(/ /gi, "") == "") {
			return true;
		} else {
			return false;
		}
	},
	
	/**
	 * 입력값에 특정 문자가 있는지 체크하는 로직이며
	 * 특정문자를 허용하고 싶지 않을때 사용할수도 있다
	 * char : 제외문자
	 * if (containsChars(form.name, "!,*&^%$#@~;")){
	 *       alert("특수문자를 사용할수 없습니다");
	 * }
	 * @param	str	문자열
	 */
	containsChars : function(str, chars) {
		for (var i = 0; i < str.length; i++) {
			if (chars.indexOf(str.charAt(i)) != -1) {
				return true;
			}
		}
		return false;
	},
	
	/**
	 * 입력값이 특정 문자만으로 되어있는지 체크하며
	 * 특정문자만을 허용하려 할때 사용한다.
	 * chars : 허용문자
	 * if (containsChars(form.name, "ABO")){
	 *    alert("혈액형 필드에는 A,B,O 문자만 사용할수 있습니다.");
	 * }
	 * @param	str	문자열
	 * @param	chars	char배열
	 */
	containsCharsOnly : function(str, chars) {
		if(str && str.length){
			for (var i = 0; i < str.length; i++) {
				if (chars.indexOf(str.charAt(i)) == -1) {
					return true;
				}
			}
		}
		
		return false;
	},
	
	/**
	 * 입력값이 알파벳인지 체크
	 * 아래 isAlphabet() 부터 isNumComma()까지의 메소드가 자주 쓰이는 경우에는
	 * var chars 변수를 global 변수로 선언하고 사용하도록 한다.
	 * var uppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	 * var lowercase = "abcdefghijklmnopqrstuvwxyz";
	 * var number = "0123456789";
	 * function isAlphaNum(str){
	 *       var chars = uppercase + lowercase + number;
	 *    return containsCharsOnly(str, chars);
	 * }
	 * @param	str	문자열
	 */
	isAlphabet : function(str) {
		var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		return formCheck.containsCharsOnly(str, chars);
	},
	
	/**
	 * 입력값이 알파벳 대문자인지 체크한다
	 * @param	str	문자열
	 */
	isUpperCase : function(str) {
		var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		return formCheck.containsCharsOnly(str, chars);
	},
	
	/**
	 * 입력값이 알파벳 소문자인지 체크한다
	 * @param	str	문자열
	 */
	isLowerCase : function(str) {
		var chars = "abcdefghijklmnopqrstuvwxyz";
		return formCheck.containsCharsOnly(str, chars);
	},
	
	/**
	 * 입력값이 숫자만 있는지 체크한다.
	 * @param	str	문자열
	 */
	isNumer : function(str) {
		var chars = "0123456789";
		return formCheck.containsCharsOnly(str, chars);
	},
	
	/**
	 * 입려값이 알파벳, 숫자로 되어있는지 체크한다
	 * @param	str	문자열
	 */
	isAlphaNum : function(str) {
		var chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		return formCheck.containsCharsOnly(str, chars);
	},
	
	/**
	 * 입력값이 숫자, 대시"-" 로 되어있는지 체크한다 전화번호나 우편번호, 계좌번호에 - 체크할때 유용하다
	 * @param	str	문자열
	 */
	isNumDash : function(str) {

		var chars = "-0123456789";
		return formCheck.containsCharsOnly(str, chars);
	},
	
	/**
	 * 입력값이 숫자, 콤마',' 로 되어있는지 체크한다
	 * @param	str	문자열
	 */
	isNumComma : function(str) {
		var chars = ",0123456789";
		return formCheck.containsCharsOnly(str, chars);
	},
	
	/**
	 * 입력값이 숫자, 마침표'.' 로 되어있는지 체크한다
	 * @param	str	문자열
	 */
	isNumPeriod : function(str) {
		var chars = ".0123456789";
		return formCheck.containsCharsOnly(str, chars);
	},
	
	/**
	 * 입력값이 소숫점 제외한 1~100 사이의 정수인지 체크한다
	 * @param	value	문자열
	 */
	isPercent : function(value){
		if (value.indexOf(".") > 0) {
			value = value.substring(0, value.indexOf("."));
			
			if (Number(value) < 1 || Number(value) > 100) {
				return false;
			} else {
				return true;
			}
		} else {
			return false; 
		}
	},
	
	/**
	 * 입력값이 사용자가 정의한 포맷 형식인지 체크
	 * 자세한 format 형식은 자바스크립트의 'reqular expression' 참고한다
	 * @param	str	문자열
	 * @param	format	format형식
	 */
	isValidFormat : function(str, format) {
		if (str.search(format) != -1) {
			return true; // 올바른 포멧형식
		}
		return false;
	},
	
	/**
	 * 시작 날짜가 종료날짜보다 차이계산
	 * @param	date1	시작날짜
	 * @param	date2	종료날짜
	 */
	dateChk: function(date1,date2){
		var v1=date1.split('-');
		var v2=date2.split('-');
		
		var a1=new Date(v1[0],v1[1],v1[2]).getTime();
		var a2=new Date(v2[0],v2[1],v2[2]).getTime();
		var b=(a2-a1)/(1000*60*60*24);
		
		return b;//0이하이면 시작날짜가 종료날짜 이후
	},
	
	/**
	 * 입력값이 이메일 형식인지 체크한다
	 * if (!isValidEmail(form.email)){
	 *       alert("올바른 이메일 주소가 아닙니다");
	 * }
	 * @param	str	문자열
	 */
	isValidEmail : function(str) {
		var format = /^((\w|[\-\.])+)@((\w|[\-\.])+)\.([A-Za-z]+)$/;
		return formCheck.isValidFormat(str, format);
	},
	
	/**
	 * 입력값이 전화번호 형식(숫자-숫자-숫자)인지 체크한다
	 * @param	str	문자열
	 */
	isValidPhone : function(str) {
		var format = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/; 
		return formCheck.isValidFormat(str, format);
	},
	
	/**
	 * 입력값이 날짜 형식(0000-00-00)인지 체크한다(1970-01-01부터 2099-12-31까지 검색 가능)
	 * 정상 format일 경우 true, 잘못된 format일 경우 false
	 * @param	str	문자열
	 */
	isDateFormat: function(str) {
		
		/*형식 체크*/
		var format = /^(19[7-9][0-9]|20\d{2})-(0[0-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$/;
	    
	    if (!formCheck.isValidFormat(str, format)) {
	    	return false;
		}
		
        var year = Number(str.substring(0, 4));
        var month = Number(str.substring(5, 7));
        var day = Number(str.substring(8, 10));
         
        if( month<1 || month>12 ) {
            return false;
        }
        
        var maxDaysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
        var maxDay = maxDaysInMonth[month-1];
         
        /*윤년 체크*/
        if( month==2 && ( year%4==0 && year%100!=0 || year%400==0 ) ) {
            maxDay = 29;
        }
        
        if( day<=0 || day>maxDay ) {
            return false;
        }
	    
        return true;
	},
	
	/**
	 * 입력값이 날짜 형식(0000-00)인지 체크한다(1970-01-01부터 2099-12-31까지 검색 가능)
	 * 정상 format일 경우 true, 잘못된 format일 경우 false
	 * @param	str	문자열
	 */
	isMonthFormat: function(str) {
	    var format = /^(19[7-9][0-9]|20\d{2})-(0[0-9]|1[0-2])$/;
	    return formCheck.isValidFormat(str, format);
	},
	
	/**
	 * 입력값의 바이트 길이를 리턴한다.
	 * if (getByteLength(form.title) > 100){
	 *    alert("제목은 한글 50자, 영문 100자로 입력해주세요(100byte)");
	 * }
	 * @param	str	문자열
	 */
	getByteLength : function(str) {
		var byteLength = 0;
		for (var inx = 0; inx < str.length; inx++) {
			var oneChar = escape(str.charAt(inx));
			if (oneChar.length == 1) {
				byteLength++;
			} else if (oneChar.indexOf("%u") != -1) {
				byteLength += 2;
			} else if (oneChar.indexOf("%") != -1) {
				byteLength += oneChar.length / 3;
			}
		}
		return byteLength;
	},
	
	/**
	 * 입력값에서 콤마를 없앤다
	 * @param	str	문자열
	 */
	removeComma : function(str) {
		return str.replace(/,/gi, "");
	},
	
	/**
	 * 선택된 라디오버튼이 있는지 체크한다
	 * @param	obj	라디오버튼 배열
	 */
	hasCheckedRadio : function(obj) {
		if (obj.length > 1) {
			for (var inx = 0; inx < obj.length; inx++) {
				if (obj[inx].checked)
					return true;
			}
		} else {
			if (obj.checked)
				return true;
		}
		return false;
	},
	
	/**
	 * 선택된 체크박스가 있는지 체크
	 * @param	obj	체크박스 배열
	 */
	hasCheckedBox : function(obj) {
		return formCheck.hasCheckedRadio(obj);
	},
	
	/**
	 * 시간 체크
	 * @param	obj	문자열
	 */
	isHour : function(obj){
		var val = $(obj).val();
		if (val.length == 1) { // 한자리일 경우 앞에 0 붙임
			$(obj).val("0" + val);
			val = $(obj).val();
		}
		if (formCheck.isEmpty(val)) { // 공백인지
			return false;
		}
		if (formCheck.isNumer(val)) { // 숫자만 있는지
			return false;
		}
		if (val > 23 || val < 0) { // 0~23의 범위를 갖는지
			return false;
		}
		return true;
	},
	
	/**
	 * 분 체크
	 * @param	obj	문자열
	 */
	isMinutes : function(obj){
		var val = $(obj).val();
		if (val.length == 1) { // 한자리일 경우 앞에 0 붙임
			$(obj).val("0" + val);
			val = $(obj).val();
		}
		if (formCheck.isEmpty(val)) { // 공백인지
			return false;
		}
		if (formCheck.isNumer(val)) { // 숫자만 있는지
			return false;
		}
		if (val > 59 || val < 0) { // 0~59의 범위를 갖는지
			return false;
		}
		return true;
	},
	
	/**
	 * 시작 일+시간과 종료 일+시간의 차이를 구한다.
	 * 각 input의 이름은
	 * target + "_date"
	 * target + "_hour"
	 * target + "_minute" 처럼 규칙적으로 명시 되어있어야 함.
	 * @param	fr_target	문자열
	 * @param	to_target	문자열
	 */
	timeChk : function(fr_target, to_target){
		var fr_date = $("#" + fr_target + "_date").val().replace(/-/gi, "");
		var fr_hour = $("#" + fr_target + "_hour").val();
		var fr_minute = $("#" + fr_target + "_minute").val();
		var to_date = $("#" + to_target + "_date").val().replace(/-/gi, "");
		var to_hour = $("#" + to_target + "_hour").val();
		var to_minute = $("#" + to_target + "_minute").val();
		
		var fr_dateTime = fr_date + fr_hour + fr_minute;
		var to_dateTime = to_date + to_hour + to_minute;
		
		return to_dateTime - fr_dateTime;
	}
	
};
