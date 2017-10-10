package com.pai.service.redis.aop;

import java.io.StringWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.ProceedingJoinPoint;

import com.pai.base.api.annotion.PAICacheEvict;
import com.pai.base.core.util.string.StringCollections;
import com.pai.base.core.util.string.StringUtils;
import com.pai.service.redis.JedisUtil;
import com.pai.service.redis.RedisDb;

import freemarker.cache.StringTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

public class PAICacheEvictAOPAspect {
	
//	@Resource
//	private FreemarkEngine freemarkEngine;
	
	private JedisUtil jedisUtil;
	
	public JedisUtil getJedisUtil() {
		return jedisUtil;
	}

	public void setJedisUtil(JedisUtil jedisUtil) {
		this.jedisUtil = jedisUtil;
	}

	//添加FreeMarker可访问的类静态方法的字段
	static Map<String,TemplateHashModel> STATIC_CLASSES = new HashMap<String, TemplateHashModel>();
	static{
		try {
			BeansWrapper beansWrapper = BeansWrapper.getDefaultInstance();
			TemplateHashModel staticModel = beansWrapper.getStaticModels();
			STATIC_CLASSES.put(Long.class.getSimpleName(), (TemplateHashModel) staticModel.get(java.lang.Long.class.getName()));
			STATIC_CLASSES.put(Integer.class.getSimpleName(), (TemplateHashModel) staticModel.get(java.lang.Integer.class.getName()));
			STATIC_CLASSES.put(java.lang.String.class.getSimpleName(), (TemplateHashModel) staticModel.get(java.lang.String.class.getName()));
			STATIC_CLASSES.put(Short.class.getSimpleName(), (TemplateHashModel) staticModel.get(java.lang.Short.class.getName()));
			STATIC_CLASSES.put(Boolean.class.getSimpleName(), (TemplateHashModel) staticModel.get(java.lang.Boolean.class.getName()));
			STATIC_CLASSES.put(StringUtils.class.getSimpleName(),(TemplateHashModel) staticModel.get(StringUtils.class.getName()));
		} catch (TemplateModelException e) {
			e.printStackTrace();
		} 
	}	
	
	public Object doCacheEvict(ProceedingJoinPoint point) throws Throwable{
		
		String methodName = point.getSignature().getName();
		//类
		Class<?> targetClass = point.getTarget().getClass();
		//方法
		Method[] methods = targetClass.getMethods();
		Method method = null;
		for (int i = 0; i < methods.length; i++){
			if (methods[i].getName() == methodName){
				method = methods[i];	 				
				break;
			}
		}
		//如果横切点不是方法，返回
		if (method == null)
			return point.proceed();

		PAICacheEvict paiCacheEvict = method.getAnnotation(PAICacheEvict.class);
		 
		if(paiCacheEvict!=null){
			
			List<String> paramNames = StringCollections.toList(paiCacheEvict.params(), ",");
			
			Map<String, Object> map=new HashMap<String, Object>();
			map.putAll(STATIC_CLASSES);
			if(point.getArgs()!=null && point.getArgs().length>0 && paramNames!=null && paramNames.size()>0){
				int i = 0;
				for(String paramName:paramNames){
					map.put(StringUtils.lowerFirst(paramName), point.getArgs()[i]);
					i++;
				}
			}
			try {
				String type=paiCacheEvict.type();
				int db=paiCacheEvict.db();
				if(type.equals(RedisDb.EVICT_ALL_TYPE)){//清空当前数据库
					jedisUtil.flushDB(db);
				}else if(type.equals(RedisDb.EVICT_KEY_TYPE)) {//根据key删除缓存
					if(paiCacheEvict.key()!=null&&!paiCacheEvict.key().equals("")){
						String key = parseByStringTemplate(paiCacheEvict.key(), map);
						jedisUtil.delByKey(key,db);
					}
					
				}else if(type.equals(RedisDb.EVICT_PREFIX_TYPE)){//根据前缀匹配删除
					String key = paiCacheEvict.key();
					jedisUtil.delByPrefix(key,db);
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}		
		}
		
		return point.proceed();
	}
	
	private String parseByStringTemplate(String templateSource, Object model)
			throws Exception {
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
