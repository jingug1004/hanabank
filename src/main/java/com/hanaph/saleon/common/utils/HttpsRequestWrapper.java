package com.hanaph.saleon.common.utils;

import javax.servlet.http.*;

public class HttpsRequestWrapper extends HttpServletRequestWrapper{
	
	private HttpServletResponse response = null;
	
	public HttpsRequestWrapper (HttpServletRequest request) {
		super(request);
	}
	
	public void setResponse (HttpServletResponse response) {
		this.response = response;
	}
	
	public HttpSession getSession () {
		HttpSession session = super.getSession();
		processSessionCookie(session);
		return session;
	}
	
	public HttpSession getSession (boolean create) {
		HttpSession session = super.getSession(create);
		processSessionCookie(session);
		return session;
	}
	
	private void processSessionCookie (HttpSession session) {
		
		if (null == response || null == session)  return;
		
		Object cookieOverWritten = getAttribute("COOKIE_OVERWRITTEN_FLAG");
		
		if (null == cookieOverWritten && isSecure() && isRequestedSessionIdFromCookie() && session.isNew())  {
			
			Cookie cookie = new Cookie("JSESSIONID", session.getId());
			cookie.setMaxAge(-1);
			
			String contextPath = getContextPath();
			
			if ((contextPath != null) && (contextPath.length() > 0))   {
				cookie.setPath(contextPath);
			} else {
				cookie.setPath("/");
			}
			
			response.addCookie(cookie);
			setAttribute("COOKIE_OVERWRITTEN_FLAG", "true");
		}
	}
}