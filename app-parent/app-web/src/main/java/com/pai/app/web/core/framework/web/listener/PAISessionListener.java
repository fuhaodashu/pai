package com.pai.app.web.core.framework.web.listener;

import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.pai.app.web.core.framework.web.event.SessionDestroyedEvent;
import com.pai.base.core.helper.SpringHelper;

/**
 * session监听，配置角色权限的时候，获取该session并重新设值
 * <pre> 
 * 构建组：app-web
 * 作者：fuhao
 * 日期：2016年12月28日-下午8:39:22
 * </pre>
 */
public class PAISessionListener implements HttpSessionListener{

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		ServletContext application = session.getServletContext();
		HashSet sessions = (HashSet)application.getAttribute("sessions");
		if(sessions==null){
			sessions = new HashSet();
			application.setAttribute("sessions", sessions);
		}
		sessions.add(session);
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		//用户退出后抛出事件，待处理
		SessionDestroyedEvent sessionDestroyedEvent = new SessionDestroyedEvent(se,session); 
		SpringHelper.publishEvent(sessionDestroyedEvent);
		
		ServletContext  application = session.getServletContext();
		HashSet sessionsHashSet = (HashSet) application.getAttribute("sessions");
		if(sessionsHashSet!=null){
			sessionsHashSet.remove(session);	
		}
	}
	
}
