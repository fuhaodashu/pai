package com.pai.app.admin.interceptor;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.pai.app.web.core.constants.UrlConstants;
import com.pai.app.web.core.constants.WebConstants;
import com.pai.app.web.core.framework.web.context.WebOnlineHolder;
import com.pai.base.api.constants.Constants;
import com.pai.base.core.util.string.StringUtils;
import com.pai.biz.auth.persistence.entity.AuthResourcesPo;
import com.pai.biz.auth.persistence.entity.AuthUserPo;

public class SecurityInterceptor implements HandlerInterceptor {
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {	
		//单元测试环境
		if(request instanceof MockHttpServletRequest){
			return true;
		}
		
		WebOnlineHolder.setSession(request.getSession());				
		
		//登录过滤
		AuthUserPo authUserPo = WebOnlineHolder.getUserPo();
		if(authUserPo == null){
			redirectToLogin(request,response);
			return true;
		}
		
		//请求权限过滤
		String path = request.getServletPath();
		//列表数据也拦截一下，edit单挑数据类型就算了
		if(path.endsWith("/listData.do"))
			path = path.replace("/listData.do", "/list.do");
		//查询请求链接是否需要拦截，因为url是登录时放置在session中保存了，所以对于给角色新配置的资源，并不会拦截，只有重新登录才回拦截
		List<String> urls = WebOnlineHolder.getAuthResUrls();
		if(!urls.contains(path))
			return true;
		List<AuthResourcesPo> authResourcesPos = authUserPo.getAuthResourcesPos();
		
		if(!resourceFilter(path, authResourcesPos))
			throw new RuntimeException("无此功能权限！");
		
		
		return true;
	}

	private boolean resourceFilter(String path, List<AuthResourcesPo> authResourcesPos) {
		for(AuthResourcesPo authResourcesPo : authResourcesPos){
			if(StringUtils.isNotEmpty(authResourcesPo.getUrl())){
				if(path.indexOf(authResourcesPo.getUrl()) != -1){
					return true;
				}
			}
		}
		return false;
	}
	
	//树结构list
	@Deprecated
	private boolean resourceTreeFilter(String path, List<AuthResourcesPo> authResourcesPos) {
		for(AuthResourcesPo authResourcesPo : authResourcesPos){
			if(StringUtils.isNotEmpty(authResourcesPo.getUrl())){
				if(path.indexOf(authResourcesPo.getUrl()) != -1){
					return true;
				}
			}
			if(authResourcesPo.getSubs().size() > 0){
				//递归不能设置一个变量做判断返回
				return resourceTreeFilter(path, authResourcesPo.getSubs());
			}
		}
		return false;
	}

	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	private void redirectToLogin(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String url = request.getAttribute(WebConstants.CONTEXT_PATH) + UrlConstants.LOGIN_URL;
		response.setCharacterEncoding(Constants.DEFAULT_ENCODING);
		response.sendRedirect(url);		
	}
}
