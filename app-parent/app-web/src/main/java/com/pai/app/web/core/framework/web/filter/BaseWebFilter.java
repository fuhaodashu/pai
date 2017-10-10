package com.pai.app.web.core.framework.web.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.pai.app.web.core.constants.WebConstants;
import com.pai.app.web.core.framework.web.context.WebOnlineHolder;
import com.pai.app.web.core.framework.web.context.RequestHolder;
import com.pai.app.web.core.framework.web.xss.XssHttpServletRequestWrapper;
import com.pai.base.core.util.ConfigHelper;

public class BaseWebFilter extends OncePerRequestFilter{
	private static final Log log	= LogFactory.getLog(BaseWebFilter.class);
	@Override
	protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String uri=   request.getRequestURI().toString();
		
		if(ignore(uri)){	//忽略非请求
			chain.doFilter(request, response);
			return;
		}
		
		String url=   request.getRequestURL().toString();
		RequestHolder.setCurrentHttpRequest(request);
		request.setCharacterEncoding(WebConstants.DEFAULT_ENCODING);
		StringBuffer baseUrl = request.getRequestURL();  
		String ctxPath = baseUrl.delete(url.length() - request.getRequestURI().length(), baseUrl.length()).append(request.getContextPath()).toString();
		
		String _ctxPath = ConfigHelper.getInstance().getParamValue("staticServ.domain");
		if(_ctxPath == null || _ctxPath.length() ==0){
			_ctxPath = ctxPath;
		}
		request.setAttribute(WebConstants.CONTEXT_PATH,_ctxPath);
		
		WebOnlineHolder.setSession(request.getSession());
				
		chain.doFilter(new XssHttpServletRequestWrapper((HttpServletRequest) request), response);		
	}		
	private boolean ignore(String url){
		if(url.endsWith(".png") || 
				url.endsWith(".jpg") || 
				url.endsWith(".gif") ||
				url.endsWith(".bmp") ||
				url.endsWith(".ico") ||
				url.endsWith(".css") ||
				url.endsWith(".js") ||
				url.endsWith(".txt") ||
				url.endsWith(".xml")){
			return true;
		}
		return false;
	}
	
}
