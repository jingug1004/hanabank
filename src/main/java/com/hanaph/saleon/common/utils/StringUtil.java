/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.common.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * <pre>
 * Class Name : StringUtil.java
 * 설명 : 공통적으로 쓰이는 String class
 *
 * String 관련 Utility 함수
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 17. slamwin		생성          
 * </pre>
 * 
 * @version : 
 * @author slawin(@irush.co.kr)
 * @since   : 2014. 10. 20.
 */
public class StringUtil {

	/**
	 * 문자열이 있는지 확인한다.<br>
	 * Oracle nvl() 과 비슷한 기능을 한다. 자바에서 "null" 이라고 찍히는 것을 방지하기 위한 함수. null 일 경우,
	 * "" (빈스트링)을 리턴한다.
	 * 
	 * @param val 비교대상 문자열
	 * @return String
	 */
	public static String nvl(String val) {
		return nvl(val, "");
	}

	/**
	 * 문자열이 있는지 확인한다.<br>
	 * Oracle nvl() 과 비슷한 기능을 한다. 자바에서 "null" 이라고 찍히는 것을 방지하기 위한 함수. null 일 경우,
	 * rep을 리턴한다.
	 * 
	 * @param val 비교대상 문자열
	 * @param rep null 일 경우, 대체값
	 * @return String
	 */
	public static String nvl(String val, String rep) {
		
		if(val==null || ("").equals(val)){
			val = rep;
		}
		return val;
	}

	/**
	 * 문자열이 있는지 확인한다.<br>
	 * Oracle nvl() 과 비슷한 기능을 한다. 자바에서 "null" 이라고 찍히는 것을 방지하기 위한 함수.<br>
	 * 
	 * null 일 경우, rep을 리턴한다. 추가적으로 대상문자열을 trim() 한 후, 0 보다 작거나 같은 경우에도 rep 를
	 * 리턴한다.
	 * 
	 * @param val 비교대상 문자열
	 * @param rep null 일 경우, 대체값
	 * @return String
	 */
	public static String nvl2(String val, String rep) {
		if (val == null) {
			return rep;
		}
		return (val.trim().length() <= 0) ? rep : val.trim();
	}
	
	/**
	 * 입력받은 val 값이 0보다 작은 수가  rep 값으로 대체한다.
	 * @param val
	 * @param rep
	 * @return int
	 */
	public static int intNvl(int val, int rep) {
		return (val <= 0) ? rep : val;
	}

	/**
	 * 문자열의 일부를 잘라 리턴한다.<br>
	 * null 일 경우와 원하는 길이보다 작을 경우도 처리한다.
	 * 
	 * @param s 문자열
	 * @param len 원하는 길이
	 * @return String
	 */
	public static String substring(String s, int len) {
		if (s == null) {
			return "";
		}
		if (s.length() < len) {
			return s;
		}
		return s.substring(0, len);
	}
	
	/**
	 * 개행문자를 <br/>
	 * 로 바꾼다.<br>
	 * 
	 * @param content
	 * @return String
	 */
	public static String nl2br(String content) {
		//content = translate(content, "\n", "<br />\n");
		content = translate(content, "\r", "<br />\n");
		return content;
	}
	
	/**
	 * 길이가 큰 문자열 내에서 특정 문자를 대체 문자로 바꾸는 함수<br>
	 * String.replace() 하고 같은 기능을 하지만, performance() 를 고려하여 새로 제작했다. 실제로도 빠른지는
	 * 테스트해 주시기 바란다.
	 * 
	 * @param content 대상 문자열
	 * @param pattern 특정 문자열
	 * @param replace 대체 문자열
	 * @return String
	 */
	public static String translate(String content, String pattern,
			String replace) {
		if (content == null)
			return "";
		int s = 0;
		int e = 0;
		StringBuffer result = new StringBuffer();
		while ((e = content.indexOf(pattern, s)) >= 0) {
			result.append(content.substring(s, e));
			result.append(replace);
			s = e + pattern.length();
		}
		result.append(content.substring(s));
		return result.toString();
	}
	
	/**
	 * 문자열을 trim() 한다.<br>
	 * null 인지 확인하는 부분이 추가되어 있슴.
	 * 
	 * @param s
	 * @return String
	 */
	public static String cleanup(String s) {
		return s != null ? s.trim() : "";
	}
	
	/**
	 * 파일명 확장자를 추출 한다.<br>
	 * 명시된 결과가 없을 경우 etc 반환.
	 * 
	 * @param fileName
	 * @return String
	 */
	public static String getFileNameExt(String fileName) {
		String ext = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		
		if (!"doc".equals(ext) && !"hwp".equals(ext) && !"pdf".equals(ext) && !"ppt".equals(ext) && !"xls".equals(ext)) {
			ext = "etc";
		}
	
		return ext;
	}
	
	/**
	 * 문자열을 입력받아서, HTML 태그를 제거한다.
	 * 
	 * @param str
	 * @return String
	 */	
	public static String removeTag(String str){
		String newStr = "";
		if(str != null){
			newStr = str.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");	
			newStr = newStr.replaceAll("(<([a-z!/]+)[^>]*>)|([\\t\\x0B\\f]+)|(([\\r\\n][\\r\\n])+)|(-->)", "");
		}
		return newStr;
	}	
	
	/**
	 * 문자열을 입력받아서, 3자리 단위마다 구분자처리한다.
	 * 
	 * @Method Name : makeMoneyType
	 * @param moneyString
	 * @return
	 */
	public static String makeMoneyType(int money){ 
        DecimalFormat df = (DecimalFormat)NumberFormat.getInstance(); 
        df = new DecimalFormat("##,###.##"); 
        return df.format(money); 
    }
	
	/**
	 * 문자열을 입력받아서, 3자리 단위마다 구분자처리한다.
	 * 
	 * @Method Name : makeMoneyType
	 * @param moneyString
	 * @return
	 */
	public static String makeMoneyType(double money){ 
        DecimalFormat df = (DecimalFormat)NumberFormat.getInstance(); 
        df = new DecimalFormat("##,###.##"); 
        return df.format(money); 
    }
	
	/**
	 * String으로 받은 문자열을 가격 ,를 찍어서 변환 해주는 유틸
	 * </pre>
	 * @Method Name : makeMoneyType
	 * @param money
	 * @return
	 */		
	public static String makeMoneyType(String money){ 
		String newMoney = money;
		if(newMoney == null || newMoney.isEmpty()){
			newMoney = "0";
		}
        DecimalFormat df = (DecimalFormat)NumberFormat.getInstance(); 
        df = new DecimalFormat("##,###.##"); 
        return df.format(Float.parseFloat(newMoney)); 
    } 
	
	
	/**
	 * String으로 받은 문자열을 가격 ,를 찍어서 변환 해주는 유틸
	 * </pre>
	 * @Method Name : makeMoneyType
	 * @param money
	 * @return
	 */		
	public static String makeMoneyTypeInt(String money){ 
		String newMoney = money;
		if(newMoney == null || newMoney.isEmpty()){
			newMoney = "0";
		}
        DecimalFormat df = (DecimalFormat)NumberFormat.getInstance(); 
        df = new DecimalFormat("##,###.##"); 
        return df.format(Double.parseDouble(newMoney)); 
    } 
	
	/**
	 * String으로 받은 문자열을 가격 ,를 찍어서 변환 해주는 유틸. 소수점 두자리 반드시 찍어줌
	 * </pre>
	 * @Method Name : makePercentType
	 * @param str
	 * @return
	 */		
	public static String makePercentType(String str){ 
		String newStr = str;
		if(newStr == null || newStr.isEmpty()){
			newStr = "0";
		}
        DecimalFormat df = (DecimalFormat)NumberFormat.getInstance(); 
        df = new DecimalFormat("##,###.00"); 
        return df.format(Float.parseFloat(newStr)); 
    }
}
