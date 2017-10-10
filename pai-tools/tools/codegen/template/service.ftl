<#import "function.ftl" as func>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign sys=model.variables.sys>
<#assign module=model.variables.module>
<#assign sub=model.sub>
<#assign foreignKey=func.convertUnderLine(model.foreignKey)>
package com.${sys}.biz.${module}.api.service;
import java.util.Map;

import com.${sys}.base.api.response.BaseResponse;
import com.${sys}.base.api.response.ResPage;
import com.${sys}.biz.${module}.api.model.${class}Bean;
/**
 * 对象功能:${model.tabComment} service接口
 <#if vars.company?exists>
 * 开发公司:${vars.company}
 </#if>
 <#if vars.developer?exists>
 * 开发人员:${vars.developer}
 </#if>
 * 创建时间:${date?string("yyyy-MM-dd HH:mm:ss")}
 * 命名规范：查list集合以findXx做前缀,单个po实体用getXx做前缀,数量countXx,条件查询加ByXx后缀,如getXxByName
 */
public interface ${class}Service {
	/**
	 * 查询【${model.tabComment}】列表
	 * @param map(whereSql,orderBySql)
	 * @param page
	 * @return BaseResponse
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */
	BaseResponse<ResPage<${class}Bean>> list${class}Service(Map<String, Object> map, Integer pageNo, Integer pageSize);
	
	/**
	 * 根据id获取单个对象
	 * @param id
	 * @return BaseResponse
	 * @throws Exception 
	 * @exception 
	 * @since  1.0.0
	 */	 
	BaseResponse<${class}Bean> get${class}ServiceById(String id);
	
	/**
	 * 保存【${model.tabComment}】
	 * @param ${classVar}Bean
	 * @throws Exception 
	 * void
	 * @exception 
	 * @since  1.0.0
	 */	
	BaseResponse save${class}(${class}Bean ${classVar}Bean);
	
	/**
	 * 删除【${model.tabComment}】
	 * @param id
	 * @return
	 * @throws Exception 
	 * CommonResult
	 * @exception 
	 * @since  1.0.0
	 */
	BaseResponse delete${class}ById(String id);
}
