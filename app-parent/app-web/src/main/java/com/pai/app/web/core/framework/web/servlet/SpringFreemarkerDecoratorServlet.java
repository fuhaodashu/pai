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
//import com.opensymphony.module.sitemesh.freemarker.FreemarkerDecoratorServlet;
//
//import freemarker.cache.TemplateLoader;
//
//public class SpringFreemarkerDecoratorServlet  extends FreemarkerDecoratorServlet  {
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
//	private boolean isWindows(){
//		Properties prop = System.getProperties();
//		String os = prop.getProperty("os.name");		
//		if(os.startsWith("win") || os.startsWith("Win")){
//			return true;
//		}
//		return false;
//	}
//}
