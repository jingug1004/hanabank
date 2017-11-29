/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.common.utils;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

/**
 * <pre>
 * Class Name : RequestFilterUtil.java
 * 설명 : 요청 받은 request를 가공 하여 return하는 공통 class
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
public class RequestFilterUtil {
	
	/**
	 * HttpServletRequest 객체에 대한 value 값을 필터링하여 Map 형식으로 return
	 * 
	 * @param httpservletrequest
	 * @return Map
	 */
	@SuppressWarnings("rawtypes")
	public static Map<String, String> getReqData(HttpServletRequest httpservletrequest) {
		Map<String, String> requestMap = new HashMap<String, String>();
		String s;
		for (Enumeration enumeration = httpservletrequest.getParameterNames(); 
				enumeration.hasMoreElements(); 
				requestMap.put(s, toHtmlTagReplace(s, httpservletrequest.getParameter(s)))) {
			s = (String) enumeration.nextElement();
		}

		return requestMap;
	}
	
	/**
	 * MultipartRequest로 넘어온 value 값을 필터링하여 Map 형식  으로 return
	 * @param httpservletrequest
	 * @param maxSize
	 * @param dirPath
	 * @param encoding
	 * @return Map
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> getReqMultiPartData(HttpServletRequest httpservletrequest, int maxSize, String dirPath, String encoding) {  
		
		Map<String, String> requestMap = new HashMap<String, String>();
		
		int postMaxSize = maxSize * 1024 * 1024;
		
		try {
		
			MultipartRequest mRequest = new MultipartRequest(httpservletrequest, dirPath, postMaxSize, encoding, new DefaultFileRenamePolicy());
			
			String key, value;    
			Enumeration<String> enumer = mRequest.getParameterNames();    
			
			// 일반 파라미터 처리
			while (enumer.hasMoreElements()) {        
				key = enumer.nextElement();        
				value = mRequest.getParameter(key);
				
				requestMap.put(key, toHtmlTagReplace(key, value));
			} 
			
			File file;
	 
			enumer = mRequest.getFileNames();    
			
			// 파일 파라미터 처리
			while (enumer != null && enumer.hasMoreElements()) {    
				key = enumer.nextElement();        
				file = mRequest.getFile(key);
				
				if ( file != null ) {
					requestMap.put(key, file.getName());
					requestMap.put(key+"Size", Long.toString(file.length()));
				}else {
					continue;
				}     
			}
		}catch ( IOException ioe ) {
			ioe.getStackTrace();
		}
		
		return requestMap;
	}

	/**
	 * HTML 태그를 쓰지 못하도록, 태그 문자를 변경한다.
	 * 
	 * @param paramKey  파라미터 key
	 * @param s  파라미터 value
	 * @return String 태그가 제거된 문자
	 */
	public static String toHtmlTagReplace(String paramKey, String s) {

		if (s == null) {
			return "";
		}
		
		s = changeString(s, "&", "&#38;");	
		//s = changeString(s, ";", "&#59;");
		s = changeString(s, "<", "&#60;");
		s = changeString(s, ">", "&#62;");
		s = changeString(s, "'", "&#39;");
		s = changeString(s, "\"", "&#34;");
		s = changeString(s, "..", "&#46;&#46;");
		s = changeString(s, "(", "&#40;");
		s = changeString(s, ")", "&#41;");
		s = changeString(s, "{", "&#123;");
		s = changeString(s, "}", "&#125;");
		s = changeString(s, "%", "&#37;");
		//s = changeString(s, "+", "&#43;");	//암호화 문자 변환문제로 주석처리.
		//s = changeString(s, "-", "&#45;");		//암호화 문자 변환문제로 주석처리.
		//s = changeString(s, "#", "&#35;");
		//s = changeString(s, "/",  "&#47;");
		//s = changeString(s, "\\", "&#92;"); // 개행문자가 처리되는것을 막기위해 주석처리.
		s = allReplaceToIgnoreCase(s, "SCRIPT", "&#83;&#99;&#114;&#105;&#112;&#116;");
		s = allReplaceToIgnoreCase(s, "IFRAME", "&#73;&#102;&#114;&#97;&#109;&#101;");
		s = allReplaceToIgnoreCase(s, "OBJECT", "&#79;&#66;&#74;&#69;&#67;&#84;");
		s = allReplaceToIgnoreCase(s, "APPLET", "&#65;&#80;&#80;&#76;&#69;&#84;");
		s = allReplaceToIgnoreCase(s, "EMBED", "&#69;&#77;&#66;&#69;&#68;");
		s = allReplaceToIgnoreCase(s, "FORM", "&#70;&#79;&#82;&#77;");
		s = allReplaceToIgnoreCase(s, "xmp", "&#120;&#109;&#112;");
		s = allReplaceToIgnoreCase(s, "drop table",
				"&#100;&#114;&#111;&#112;&#28;&#116;&#97;&#98;&#108;&#101;");
		s = allReplaceToIgnoreCase(s, "delete from",
				"&#100;&#101;&#108;&#101;&#116;&#101;&#28;&#102;&#114;&#111;&#109;");
		s = allReplaceToIgnoreCase(s, "create table",
				"&#99;&#114;&#101;&#97;&#116;&#101;&#28;&#116;&#97;&#98;&#108;&#101;");
		
		s = allReplaceToIgnoreCase(s, "onabort", "0nabort");  
		s = allReplaceToIgnoreCase(s, "onactivate", "0nactivate");  
		s = allReplaceToIgnoreCase(s, "onafterprint", "0nafterprint");  
		s = allReplaceToIgnoreCase(s, "onafterupdate", "0nafterupdate");  
		s = allReplaceToIgnoreCase(s, "onbeforeactivate", "0nbeforeactivate");  
		s = allReplaceToIgnoreCase(s, "onbeforecopy", "0nbeforecopy");  
		s = allReplaceToIgnoreCase(s, "onbeforecut", "0nbeforecut");  
		s = allReplaceToIgnoreCase(s, "onbeforedeactivate", "0nbeforedeactivate");  
		s = allReplaceToIgnoreCase(s, "onbeforeeditfocus", "0nbeforeeditfocus");  
		s = allReplaceToIgnoreCase(s, "onbeforepaste", "0nbeforepaste");  
		s = allReplaceToIgnoreCase(s, "onbeforeprint", "0nbeforeprint");  
		s = allReplaceToIgnoreCase(s, "onbeforeunload", "0nbeforeunload");  
		s = allReplaceToIgnoreCase(s, "onbeforeupdate", "0nbeforeupdate");  
		s = allReplaceToIgnoreCase(s, "onblur", "0nblur");  
		s = allReplaceToIgnoreCase(s, "onbounce", "0nbounce");  
		s = allReplaceToIgnoreCase(s, "oncellchange", "0ncellchange");  
		s = allReplaceToIgnoreCase(s, "onchange", "0nchange");  
		s = allReplaceToIgnoreCase(s, "onclick", "0nclick");  
		s = allReplaceToIgnoreCase(s, "oncontextmenu", "0ncontextmenu");  
		s = allReplaceToIgnoreCase(s, "oncontrolselect", "0ncontrolselect");  
		s = allReplaceToIgnoreCase(s, "oncopy", "0ncopy");  
		s = allReplaceToIgnoreCase(s, "oncut", "0ncut");  
		s = allReplaceToIgnoreCase(s, "ondataavailable", "0ndataavailable");  
		s = allReplaceToIgnoreCase(s, "ondatasetchanged", "0ndatasetchanged");  
		s = allReplaceToIgnoreCase(s, "ondatasetcomplete", "0ndatasetcomplete");  
		s = allReplaceToIgnoreCase(s, "ondblclick", "0ndblclick");  
		s = allReplaceToIgnoreCase(s, "ondeactivate", "0ndeactivate");  
		s = allReplaceToIgnoreCase(s, "ondrag", "0ndrag");  
		s = allReplaceToIgnoreCase(s, "ondragend", "0ndragend");  
		s = allReplaceToIgnoreCase(s, "ondragenter", "0ndragenter");  
		s = allReplaceToIgnoreCase(s, "ondragleave", "0ndragleave");  
		s = allReplaceToIgnoreCase(s, "ondragover", "0ndragover");  
		s = allReplaceToIgnoreCase(s, "ondragstart", "0ndragstart");  
		s = allReplaceToIgnoreCase(s, "ondrop", "0ndrop");  
		s = allReplaceToIgnoreCase(s, "onerror", "0nerror");  
		s = allReplaceToIgnoreCase(s, "onerrorupdate", "0nerrorupdate");  
		s = allReplaceToIgnoreCase(s, "onfilterchange", "0nfilterchange");  
		s = allReplaceToIgnoreCase(s, "onfinish", "0nfinish");  
		s = allReplaceToIgnoreCase(s, "onfocus", "0nfocus");  
		s = allReplaceToIgnoreCase(s, "onfocusin", "0nfocusin");  
		s = allReplaceToIgnoreCase(s, "onfocusout", "0nfocusout");  
		s = allReplaceToIgnoreCase(s, "onhelp", "0nhelp");  
		s = allReplaceToIgnoreCase(s, "onkeydown", "0nkeydown");  
		s = allReplaceToIgnoreCase(s, "onkeypress", "0nkeypress");  
		s = allReplaceToIgnoreCase(s, "onkeyup", "0nkeyup");  
		s = allReplaceToIgnoreCase(s, "onlayoutcomplete", "0nlayoutcomplete");  
		s = allReplaceToIgnoreCase(s, "onload", "0nload");  
		s = allReplaceToIgnoreCase(s, "onlosecapture", "0nlosecapture");  
		s = allReplaceToIgnoreCase(s, "onmousedown", "0nmousedown");  
		s = allReplaceToIgnoreCase(s, "onmouseenter", "0nmouseenter");  
		s = allReplaceToIgnoreCase(s, "onmouseleave", "0nmouseleave");  
		s = allReplaceToIgnoreCase(s, "onmousemove", "0nmousemove");  
		s = allReplaceToIgnoreCase(s, "onmouseout", "0nmouseout");  
		s = allReplaceToIgnoreCase(s, "onmouseover", "0nmouseover");  
		s = allReplaceToIgnoreCase(s, "onmouseup", "0nmouseup");  
		s = allReplaceToIgnoreCase(s, "onmousewheel", "0nmousewheel");  
		s = allReplaceToIgnoreCase(s, "onmove", "0nmove");  
		s = allReplaceToIgnoreCase(s, "onmoveend", "0nmoveend");  
		s = allReplaceToIgnoreCase(s, "onmovestart", "0nmovestart");  
		s = allReplaceToIgnoreCase(s, "onpaste", "0npaste");  
		s = allReplaceToIgnoreCase(s, "onpropertychange", "0npropertychange");  
		s = allReplaceToIgnoreCase(s, "onreadystatechange", "0nreadystatechange");  
		s = allReplaceToIgnoreCase(s, "onreset", "0nreset");  
		s = allReplaceToIgnoreCase(s, "onresize", "0nresize");  
		s = allReplaceToIgnoreCase(s, "onresizeend", "0nresizeend");  
		s = allReplaceToIgnoreCase(s, "onresizestart", "0nresizestart");  
		s = allReplaceToIgnoreCase(s, "onrowenter", "0nrowenter");  
		s = allReplaceToIgnoreCase(s, "onrowexit", "0nrowexit");  
		s = allReplaceToIgnoreCase(s, "onrowsdelete", "0nrowsdelete");  
		s = allReplaceToIgnoreCase(s, "onrowsinserted", "0nrowsinserted");  
		s = allReplaceToIgnoreCase(s, "onscroll", "0nscroll");  
		s = allReplaceToIgnoreCase(s, "onselect", "0nselect");  
		s = allReplaceToIgnoreCase(s, "onselectionchange", "0nselectionchange");  
		s = allReplaceToIgnoreCase(s, "onselectstart", "0nselectstart");  
		s = allReplaceToIgnoreCase(s, "onstart", "0nstart");  
		s = allReplaceToIgnoreCase(s, "onstop", "0nstop");  
		s = allReplaceToIgnoreCase(s, "onsubmit", "0nsubmit");  
		s = allReplaceToIgnoreCase(s, "onunload", "0nunload");  
		
		
		return s;
	}
	
	/**
	 * 변경된 태그 문자를 HTML 문자로 변환
	 * 
	 * @param s 대상 문자열
	 * @return String 태그로 변환된 문자
	 */
	public static String convertToHtmlTagReplace(String s) {

		if (s == null) {
			return "";
		}

		s = changeString(s, "&#34;", "\"");
		s = changeString(s, "&#39;",  "'");
		s = changeString(s, "&#62;", ">");
		s = changeString(s, "&#60;", "<");
		s = changeString(s, "&#38;", "&");
		return s;
	}	
	
	/**
	 * 검색어 검색 시 특수문자 제거 (제목 또는 키워드 검색에서 사용)
	 * @param s
	 * @return String
	 */
	public static String convertToSearchParameter(String s) {
		
		if (s == null) {
			return "";
		}

		s = changeString(s, "&#34;", ""); // "
		s = changeString(s, "&#39;", ""); // '
		s = changeString(s, "&#62;", ""); // >
		s = changeString(s, "&#60;", ""); // <
		s = changeString(s, "&#38;", ""); // &
		s = changeString(s, "&#40;", ""); // (
		s = changeString(s, "&#41;", ""); // )
		s = changeString(s, "&#37;", ""); // % 
		return s;
	}

	private static String changeString(String s, String s1, String s2) {
		return replace(s, s1, s2);
	}

	private static String replace(String s, String s1, String s2) {
		if (s == null) {
			return null;
		}
		StringBuffer stringbuffer = new StringBuffer("");
		int i = s1.length();
		int j = s.length();
		int k;
		int l;
		for (l = 0; (k = s.indexOf(s1, l)) >= 0; l = k + i) {
			stringbuffer.append(s.substring(l, k));
			stringbuffer.append(s2);
		}

		if (l < j) {
			stringbuffer.append(s.substring(l, j));
		}
		return stringbuffer.toString();
	}

	/**
	 * 대소문자 구분없이 대상문자열에 변환대상 문자열이 있을 경우 변환 처리
	 * @param str
	 * @param compStr
	 * @param sepStr
	 * @return String
	 */
	@SuppressWarnings("unused")
	public static String allReplaceToIgnoreCase(String str, String compStr,
			String sepStr) {
		String ret = "";
		String tempStr = str.toUpperCase();
		int cutLength = 0;
		int compMax = compStr.length();
		int strLength = str.length();
		int compLength = tempStr.indexOf(compStr.toUpperCase());
		int tempStrLen = tempStr.length();
		for (int i = 0; tempStr.indexOf(compStr.toUpperCase()) > -1; i++) {
			compLength = tempStr.indexOf(compStr.toUpperCase());
			tempStrLen = tempStr.length();
			ret += str.substring(cutLength, cutLength + compLength) + sepStr;
			tempStr = tempStr.substring(compLength + compMax, tempStrLen);
			cutLength = cutLength + compLength + compMax;
		}
		if (compLength < 0) {
			ret = str;
		} else {
			ret += str.substring(cutLength, strLength);
		}
		return ret;
	}

	/**
	 * Map 객체의 값을 Clon 처리하여 return
	 * @param map
	 * @return Map
	 */
	public static Map<String, String> getCloneMap(Map<String, String> map) {
		Map<String, String> resultMap = new HashMap<String, String>();
		
		Set<Entry<String, String>> set = map.entrySet();
		Iterator<Entry<String, String>> it = set.iterator();
		
		while(it.hasNext()) {
			Entry<String, String> e = (Entry<String, String>)it.next();
			resultMap.put(e.getKey(), e.getValue());
		}

		return resultMap;
	}
	
}
