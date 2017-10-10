package com.pai.app.web.core.framework.web.context;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RequestHolder {
	private static ThreadLocal<HttpServletRequest> currentHttpRequestHolder = new ThreadLocal<HttpServletRequest>();
	private static final Log logger = LogFactory.getLog(RequestHolder.class);

	public static void clearCurrentHttpRequest() {
		currentHttpRequestHolder.set(null);
	}

	public static HttpServletRequest getCurrentHttpRequest() {
		return currentHttpRequestHolder.get();
	}

	public static void setCurrentHttpRequest(HttpServletRequest request) {
		currentHttpRequestHolder.set(request);
	}
	
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		Cookie returnCookie = null;
		if (cookies == null) {
			return returnCookie;
		}
		for (int i = 0; i < cookies.length; i++) {
			Cookie thisCookie = cookies[i];
			if (thisCookie.getName().equals(name)) {
				// cookies with no value do me no good!
				if (!thisCookie.getValue().equals("")) {
					returnCookie = thisCookie;

					break;
				}
			}
		}
		return returnCookie;
	}

	public static void setCookie(HttpServletResponse response, String name,
			String value, String path) {
		// FIXME 将maxAge抽取出来放入系统配置中
		setCookie(response, name, value, path, 30 * 24 * 60 * 60);
	}

	public static void setCookie(HttpServletResponse response, String name,
			String value, String path, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setSecure(false);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge); // 30 days
		
		response.addCookie(cookie);
	}
	
	public static void setCookie(HttpServletResponse response, String name,
			String value, String domain, String path, int maxAge) {
		Cookie cookie = new Cookie(name, value);
		cookie.setSecure(false);
		cookie.setPath(path);
		cookie.setMaxAge(maxAge); // 30 days
		cookie.setDomain(domain); 
		response.addCookie(cookie);
	}
	
	public static String getRequestUriExcludeContextPath(){
		String requestUri = RequestHolder.getCurrentHttpRequest().getRequestURI();
		String contextPath = RequestHolder.getCurrentHttpRequest().getContextPath();
		int index = requestUri.indexOf(contextPath) + contextPath.length();
		return requestUri.substring(index);
	}
}
