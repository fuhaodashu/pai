package com.pai.app.web.core.framework.engine;

import java.io.IOException;
import java.io.StringWriter;

import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * FreemarkEngine解析引擎。</br> 在spring配置文件中做如下配置。
 * 
 * <pre>
 *  &lt;bean id="templateLoader" class="freemarker.cache.StringTemplateLoader"/>  
 *  &lt;bean id="freemarkerConfiguration" 
 *  class="org.springframework.ui.freemarker.FreeMarkerConfigurationFactoryBean">
 *    &lt;property name="templateLoaderPath" value="classpath:template/" /> 
 *    &lt;property name="defaultEncoding" value="UTF-8"/>
 *  &lt;/bean>
 * &lt;bean id="freemarkEngine" class="om.hotent.base.core.engine.FreemarkEngine">
 *    &lt;property name="configuration" ref="freemarkerConfiguration"/>
 * &lt;/bean>
 * </pre>
 * 
 */
public class FreemarkEngine {
	
	private Configuration configuration;
		
	private FreeMarkerConfigurer freeMarkerConfigurer;  

	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	public void setFreeMarkerConfigurer(FreeMarkerConfigurer freeMarkerConfigurer) {
		this.freeMarkerConfigurer = freeMarkerConfigurer;
	}



	/**
	 * 把指定的模板生成对应的字符串。
	 * 
	 * @param templateName
	 *            模板名，模板的基础路径为：WEB-INF/view目录。
	 * @param model
	 *            传入数据对象。
	 * @return
	 * @throws IOException
	 * @throws TemplateException
	 */
	public String mergeTemplateIntoString(String templateName, Object model)
			throws IOException, TemplateException {
		Template template = configuration.getTemplate(templateName);
		return FreeMarkerTemplateUtils.processTemplateIntoString(template,
				model);
	}

	/**
	 * 根据字符串模版解析出内容
	 * 
	 * @param templateSource
	 *            字符串模版。
	 * @param model
	 *            需要解析的对象。
	 * @return 解析后的模板
	 * @throws TemplateException
	 * @throws IOException
	 */
	public String parseByStringTemplate(String templateSource, Object model)
			throws TemplateException, IOException {
		Configuration cfg = new Configuration();
		StringTemplateLoader loader = new StringTemplateLoader();
		cfg.setTemplateLoader(loader);
		cfg.setClassicCompatible(true);
		loader.putTemplate("freemaker", templateSource);
		Template template = cfg.getTemplate("freemaker");
		StringWriter writer = new StringWriter();
		template.process(model, writer);
		return writer.toString();
	}

}
