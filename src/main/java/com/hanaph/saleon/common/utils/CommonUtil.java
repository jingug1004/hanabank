/**
  * Hana Project
  * Copyright 2014 iRush Co.,
  *
  */
package com.hanaph.saleon.common.utils;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.AbstractView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * <pre>
 * Class Name : CommonUtil.java
 * 설명 : 공통 기능 class로 메시지 스크립트 출력 후 script값 script수행,요청상태 구분 method로 구성 된 class
 * 
 * Modification Information
 *    수정일      수정자              수정 내용
 *  ------------ -------------- --------------------------------
 *  2014. 10. 20. slamwin		생성
 * </pre>
 * 
 * @version 1.0
 * @author slawin(@irush.co.kr)
 * @since 2014. 10. 20.
 */
public class CommonUtil {
	
		/**
		 * <pre>
		 * 1. 개요     : 커스터마이징 ModelAndView 
		 * 2. 처리내용 : 메시지 스크립트 출력 후 script값 script수행
		 * </pre>
		 * @Method Name : getMessageView
		 * @param msg
		 * @param script
		 * @return
		 */		
		public static ModelAndView getMessageView(final String msg, final String script, final String popupYn) {
			View view = new AbstractView() {
				
				
				
				@Override
				protected void renderMergedOutputModel(Map<String, Object> arg0,
						HttpServletRequest request, HttpServletResponse response) throws Exception {
					// TODO Auto-generated method stub
					
					Environment env = new Environment();
					String ONLINE_WEB_ROOT = env.getValue("web_root_dir.url");
					
					String popup = StringUtil.nvl(popupYn, "N");
					/*		
					response.setContentType("text/html; charset=EUC-KR");
					response.setCharacterEncoding("EUC-KR");
					ServletOutputStream outs = response.getOutputStream();
					outs.println("<script type=\"text/javascript\" charset=\"utf-8\" src=\""+ONLINE_WEB_ROOT+"/js/jquery-1.11.1.min.js\"></script>");
					outs.println("<script type=\"text/javascript\" charset=\"utf-8\" src=\""+ONLINE_WEB_ROOT+"/js/common.js\"></script>");
					outs.println("<script type=\"text/javascript\">");
					outs.println("alert(\"" + new String(msg.getBytes(), "ISO_8859_1") + "\");");
					outs.println(new String(script.getBytes(), "ISO_8859_1"));
					
					// 팝업 페이지인 경우 본창 닫기.
					if("Y".equals(popup)){
						outs.println("window.self.close();");
					}
					
					outs.println("</script>");
					
					outs.flush();
					*/
					
					response.setContentType("text/html; charset=utf-8");
					PrintWriter outs = response.getWriter();
					
					outs.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\""+ONLINE_WEB_ROOT+"/js/jquery-1.11.1.min.js\"></script>");
					outs.write("<script type=\"text/javascript\" charset=\"utf-8\" src=\""+ONLINE_WEB_ROOT+"/js/common.js\"></script>");
					outs.write("<script type=\"text/javascript\">");
					outs.write("alert(\"" + msg + "\");");
					outs.write(script);
					
					//팝업 페이지인 경우 본창 닫기.
					if("Y".equals(popup)){
						outs.write("window.self.close();");
					}
					
					outs.write("</script>");
					
					outs.flush();
				}
			};
			return new ModelAndView(view);
		}
		
		/**
		 * <pre>
		 * 1. 개요     : 웹요청이 일반요청인지 ajax요청인 구분
		 * 2. 처리내용 : 웹 요청 헤더값을 분석해서 ajax요청일 경우 true로 리턴
		 * </pre>
		 * @Method Name : isAjaxRequest
		 * @param req	HttpServletRequest
		 * @return	true : ajax요청, false : 일반 웹요청
		 */		
		public static boolean isAjaxRequest(HttpServletRequest req) {
			String[] ajaxHaeder = {"X-Requested-With", "AJAX"};
			for(String compStr : ajaxHaeder){
				if(req.getHeader(compStr) != null){
					return true;
				}
			}
			return false;
		}
}
