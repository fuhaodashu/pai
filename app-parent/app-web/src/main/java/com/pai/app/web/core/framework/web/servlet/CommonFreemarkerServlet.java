//package com.pai.app.web.core.framework.web.servlet;
//
//import java.io.InputStream;
//import java.util.Properties;
//
//import javax.servlet.ServletException;
//
//import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
//
//import com.pai.base.core.helper.SpringHelper;
//
//import freemarker.cache.TemplateLoader;
//import freemarker.ext.servlet.FreemarkerServlet; 
//
//public class CommonFreemarkerServlet extends FreemarkerServlet{
//
//	@Override
//	public void init() throws ServletException {		
//		super.init();
//		FreeMarkerConfigurer freeMarkerConfigurer = SpringHelper.getBean(FreeMarkerConfigurer.class);		
//		TemplateLoader templateLoader = freeMarkerConfigurer.getConfiguration().getTemplateLoader();
//		getConfiguration().setTemplateLoader(templateLoader);
//		
//		try {
//			Properties p = new Properties();
//			ClassLoader classloader = Thread.currentThread().getContextClassLoader();
//			InputStream is = classloader.getResourceAsStream("conf/freemarker/freemarker.properties");
//			
//			if(is!=null){
//				p.load(is);  
//				getConfiguration().setSettings(p);
//			}
//		} catch (Exception e) {
//			throw new ServletException("Failed to load freemarker.properties!");
//		}
//	}
//
//}
