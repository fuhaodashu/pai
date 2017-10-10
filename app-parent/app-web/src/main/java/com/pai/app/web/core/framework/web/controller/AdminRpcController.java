package com.pai.app.web.core.framework.web.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.pai.base.api.model.Bean;
import com.pai.base.core.util.GenericsUtils;
import com.pai.base.core.util.string.StringUtils;

public abstract class AdminRpcController<P extends Bean> extends LigerUIController{			
	
	protected Class<P> poClass;	
	/**
	 * 列表集合名称，在页面使用
	 */
	protected String poListName;
	/**
	 * 表单页实体名称
	 */
	protected String poEntityName;
	/**
	 * 列表记录主键名称，在页面使用
	 */
	protected String poListIdName;
	
	protected String poTabId;
	
	protected String poListTabId;

	@Override
	protected void initController() {
		poClass = GenericsUtils.getSuperClassGenricType(getClass(), 1);
		poEntityName = StringUtils.lowerFirst(poClass.getSimpleName());
		poTabId = poEntityName + "TabId";
		poListTabId = poEntityName + "ListTabId";
	}
	
	protected String buildListData(List<P> poList,Integer total) throws JsonGenerationException, JsonMappingException, IOException {
		Map<String, String> exProperties = new HashMap<String, String>();
		return buildListData(poList, total, exProperties);
	}
	
	protected String buildListData(List<P> poList,Integer total,Map<String, String> exProperties) throws JsonGenerationException, JsonMappingException, IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.getSerializationConfig().setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));  
		String listData = objectMapper.writeValueAsString(poList);
		
		StringBuilder sb = new StringBuilder();		
		sb.append("{\"Rows\":");
		sb.append(listData);
		sb.append(",\"Total\":");
		sb.append(total);
		if(exProperties!=null && exProperties.size()>0){
			for(Iterator<String> it = exProperties.keySet().iterator();it.hasNext();){
				String key = it.next();
				String value = exProperties.get(key);
				sb.append(",\""+key+"\":");
				sb.append("\""+value+"\"");				
			}
		}
		sb.append("}");
		//System.out.println(sb.toString());
		return sb.toString();
	}
	
	protected abstract String getPoEntityComment();
	
}
