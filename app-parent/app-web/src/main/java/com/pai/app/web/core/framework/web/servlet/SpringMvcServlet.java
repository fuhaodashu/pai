package com.pai.app.web.core.framework.web.servlet;

import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

import com.pai.app.web.core.constants.WebConstants;
import com.pai.app.web.core.framework.web.util.ViewUtil;
import com.pai.base.core.util.RequestUtil;

/**
 * 重写Spring Mvc Servlet，处理输入URL没有requestMapping处理的情况。<br>
 * <pre>
 * 让它直接跳至其url对应的jsp。
 * 跳转规则分为两种：
 * 1.输入地址符合下列规则的情况的处理方法。
 *  /{1}/{2}/{3}/{4}.ht
 *  对应的jsp为 /{1}/{2}/{3}{4}.jsp ,注意这里需要将{4}首字母修改为大写。
 * 	 如/platform/system/appRole/add.ht 其对应的jsp路径则为:
 *   /platform/system/appRoleAdd.jsp，
 *   /platform/system/appRoleGrant.ht,其对应的jsp路径则为
 *   /platform/system/appRoleGrant.jsp
 * 2.输入的地址不符合上面的规则，那么就把ht直接换成jsp。
 *   例如：
 *   /platform/system.ht -->/platform/system.jsp
 *   /platform.ht -->/platform.jsp
 *   这些jsp均放在/WEB-INF/view目录下
 *   
 *   在web.xml配置如下：
 *   &lt;servlet>
 *       &lt;servlet-name>action&lt;/servlet-name>
 *        &lt;servlet-class>com.hotent.core.web.servlet.SpringMvcServlet&lt;/servlet-class>
 *       &lt;init-param>
 *			&lt;param-name>contextConfigLocation&lt;/param-name>
 *			&lt;param-value>classpath:conf/app-action.xml&lt;/param-value>
 *		&lt;/init-param>
 *       &lt;load-on-startup>2&lt;/load-on-startup>
 *   &lt;/servlet>
 *  </pre>
 *   
 * @author csx
 */
public class SpringMvcServlet extends DispatcherServlet
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void noHandlerFound(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		
		String requestURI=request.getRequestURI();

		//处理RequestURI
		String contextPath=request.getContextPath();
		
		String viewPath = ViewUtil.getByURI(requestURI, contextPath, WebConstants.ACTION_POSTFIX);

		Map<String, Object> params = RequestUtil.getRequestMap(request);		
		for(Iterator<String> it = params.keySet().iterator();it.hasNext();){
			String key = it.next();
			Object value = params.get(key);
			request.setAttribute(key, value);
		}
		
		request.getRequestDispatcher("/WEB-INF/view"+viewPath + WebConstants.VIEW_POSTFIX).forward(request, response);
		return;
	}

}
