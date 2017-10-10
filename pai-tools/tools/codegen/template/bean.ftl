<#import "function.ftl" as func>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign tableName=model.tableName>
<#assign sys=model.variables.sys>
<#assign module=model.variables.module>
<#assign subtables=model.subTableList>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
package com.${sys}.biz.${module}.api.model;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.${sys}.base.api.annotion.doc.AutoDocField;
import com.${sys}.base.api.model.Bean;
/**
 * 对象功能:${model.tabComment} Tbl对象
 <#if vars.company?exists>
 * 开发公司:${vars.company}
 </#if>
 <#if vars.developer?exists>
 * 开发人员:${vars.developer}
 </#if>
 * 创建时间:${date?string("yyyy-MM-dd HH:mm:ss")}
 */
public class ${class}Bean extends Bean{
	
	private static final long serialVersionUID = 2L;
	
	<#list model.columnList as col>
	@AutoDocField("${col.comment}")
	<#if col.colType == "java.util.Date">
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	</#if>
	private ${col.colType}  ${func.convertUnderLine(col.columnName)}; 
	</#list>
	
<#if (pkVar!="id")>
	@Override
	public void setId(String ${pkVar}) {
		this.${pkVar} = ${pkVar};
	}
	@Override
	public String getId() {
		return ${pkVar};
	}	
</#if>
<#list model.columnList as col>
	<#assign colName=func.convertUnderLine(col.columnName)>
	public void set${colName?cap_first}(${col.colType} ${colName}) 
	{
		this.${colName} = ${colName};
	}
	/**
	 * 返回 ${col.comment}
	 * @return
	 */
	public ${col.colType} get${colName?cap_first}() 
	{
		return this.${colName};
	}
</#list>
}