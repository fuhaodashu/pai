package com.pai.base.db.session;

import java.util.List;

import javax.servlet.http.HttpSession;

import com.pai.base.api.constants.Constants;

/**
 * session从web层剥离到api层
 * <pre> 
 * 作者：fuhao
 * 日期：2016年12月29日-上午9:31:13
 * </pre>
 */
public class OnlineHolder{
	
	private static final ThreadLocal<HttpSession> ouOnlineHolder	= new ThreadLocal<HttpSession>();	
	
	public static void setSession(HttpSession session) {
		ouOnlineHolder.set(session);
	}
	
	public static void setAuthResUrl(List<String> urls) {
		if(ouOnlineHolder.get()!=null){
			ouOnlineHolder.get().setAttribute(Constants.PAI_AUTH_RES_URL, urls);
		}
	}
	
	public static List<String> getAuthResUrls(){
		if(ouOnlineHolder.get()!=null){
			return (List<String>) ouOnlineHolder.get().getAttribute(Constants.PAI_AUTH_RES_URL);
		}
		return null;
	}
	
	public  static HttpSession getSession(){
		return ouOnlineHolder.get();
	}
	
}
