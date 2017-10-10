package com.pai.app.web.core.framework.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.pai.app.web.core.constants.WebConstants;
import com.pai.app.web.core.framework.web.propertieseditors.DoubleEditor;
import com.pai.app.web.core.framework.web.propertieseditors.FloatEditor;
import com.pai.app.web.core.framework.web.propertieseditors.IntegerEditor;
import com.pai.app.web.core.framework.web.propertieseditors.LongEditor;
import com.pai.app.web.core.framework.web.propertieseditors.StringEditor;
import com.pai.app.web.core.framework.web.util.ViewUtil;
import com.pai.base.api.constants.Constants;
import com.pai.base.core.entity.MapBuilder;
import com.pai.base.core.util.string.StringUtils;

public abstract class GenericController implements InitializingBean{
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected void initBinder(WebDataBinder binder) {
	     binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), true));    	        
	     binder.registerCustomEditor(int.class, new IntegerEditor());
	     binder.registerCustomEditor(Integer.class, new IntegerEditor());  
	     binder.registerCustomEditor(long.class, new LongEditor());    
	     binder.registerCustomEditor(double.class, new DoubleEditor());    
	     binder.registerCustomEditor(float.class, new FloatEditor());   
	     binder.registerCustomEditor(String.class, new StringEditor());
	}
	protected ModelAndView buildAutoView(HttpServletRequest request) {
		String viewPath = getViewPath(request, "",WebConstants.ACTION_POSTFIX);
		return new ModelAndView(viewPath);
	}		
	
	protected ModelAndView buildAutoView(HttpServletRequest request,String exclude,String action) {
		String viewPath = getViewPath(request, exclude,action);
		
		return new ModelAndView(viewPath);
	}
	
	protected ModelAndView buildFrontAutoView(HttpServletRequest request) {
		String viewPath = getViewPath(request,"",WebConstants.FRONT_ACTION_POSTFIX);
		
		return new ModelAndView(viewPath);
	}		
	
	protected ModelAndView buildFrontAutoView(HttpServletRequest request,String exclude) {
		String viewPath = getViewPath(request,exclude,WebConstants.FRONT_ACTION_POSTFIX);
		
		return new ModelAndView(viewPath);
	}
	
	private String getViewPath(HttpServletRequest request,String exclude,String action){		
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		
		// 处理RequestURI
		logger.debug("requestURI:" + requestURI);
		String viewPath = null;
		if(WebConstants.ACTION_POSTFIX.equals(action)){
			viewPath = ViewUtil.getByURI(requestURI, contextPath,action);
			viewPath = StringUtils.remove(viewPath, exclude);	
		}else if(WebConstants.FRONT_ACTION_POSTFIX.equals(action)){
			viewPath = ViewUtil.getFrontTmplByURI(requestURI, contextPath, action);
			viewPath = StringUtils.remove(viewPath, exclude);
		}		
		return viewPath;
	}
	
	protected final void printResult(HttpServletResponse response,Object result){
		try{
			response.setContentType("application/json; charset=" + Constants.DEFAULT_ENCODING);
			response.getWriter().print(result);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	protected final void redirectUrl(HttpServletResponse response,String url){
		try{
			response.setCharacterEncoding(Constants.DEFAULT_ENCODING);
			response.sendRedirect(url);
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public final void afterPropertiesSet() throws Exception {			
		initController();		
	}
	
	/**
	 *工具方法
	 *@author chenxiaohua 
	 * @exception 
	 * @since  1.0.0
	 */
	public List<NameValuePair> buildNameValuePairs(Map<String, Object> map){
		List<NameValuePair> params = new ArrayList<NameValuePair>();  
		for(Iterator<String> it=map.keySet().iterator();it.hasNext();){
			String key = it.next();
			params.add(new BasicNameValuePair(key, map.get(key)==null?"":map.get(key).toString()));
		}		
		return params;
	}
	
	/**
	 * 在子类设置实体类名 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */
	protected abstract void initController();
	
    protected MapBuilder b() {    	
		return new MapBuilder();
	}
}
