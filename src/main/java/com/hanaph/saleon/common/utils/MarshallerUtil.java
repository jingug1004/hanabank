/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.common.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <pre>
 * Class Name : MarshallerUtil.java
 * 설명 : 공통 오브젝트를 마샬링 class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 17. slamwin          생성
 * </pre>
 * 
 * @version 1.0
 * @author slawin(@irush.co.kr)
 * @since 2014. 10. 17.
 */
public class MarshallerUtil {

	/**
	 * <pre>
	 * 1. 개요     : 오브젝트를 마샬링 하는 메소드
	 * 2. 처리내용 :	전달받은 오브젝트를 json으로 변환해서 HttpServletResponse 객체에 응답해준다.
	 * type이 "txt"일 경우 html로 응답하고, 그 외의 경우에는 json으로 응답한다.
	 * </pre>
	 * @Method Name : marshalling
	 * @param type	포맷 선택(txt일때만 html, 그 외에는 json)
	 * @param response	HttpServletResponse
	 * @param obj	마샬링할 Object
	 * @throws IOException	response.getWriter()에서 발생할 수 있는 IOException
	 */		
	public static void marshalling(String type, HttpServletResponse response, Object obj) throws IOException {
        
		if ("txt".equals(type)) {
            response.setContentType("text/html; charset=utf-8");	//응답 content type를 html로 설정
            response.getWriter().print((String)obj);				//전달 받은 내용을 그대로 응답함.
            
        } else {
            response.setContentType("application/json; charset=utf-8");		//응답 content type를 json으로 설정
            String json = "";		//Object를 json으로 변환
            
            try{
            	/**
            	 * 일반적은 Object를 json으로 변환
            	 */
        		JSONObject jsonObject = JSONObject.fromObject(obj); 
        		json = jsonObject.toString();
            } catch(Exception e) {
            	/**
            	 * List형태의 Object를 json으로 변환
            	 */
            	JSONArray jsonArray=JSONArray.fromObject(obj); 
        		json = jsonArray.toString();
            }
            
            response.getWriter().print(json);
        }
    }



}
