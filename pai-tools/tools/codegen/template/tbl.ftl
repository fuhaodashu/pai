<#import "function.ftl" as func>
<#assign class=model.variables.class>
<#assign classVar=model.variables.classVar>
<#assign tableName=model.tableName>
<#assign sys=model.variables.sys>
<#assign module=model.variables.module>
<#assign subtables=model.subTableList>
<#assign pk=func.getPk(model) >
<#assign pkVar=func.convertUnderLine(pk) >
package com.${sys}.biz.${module}.persistence.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pai.base.api.annotion.IField;
import com.pai.base.api.annotion.ITable;
import com.pai.base.db.persistence.entity.AbstractPo;

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
 @ITable(name="${classVar}",code="${tableName}")
public class ${class}Tbl extends AbstractPo<String>{
	<#list model.columnList as col>
	@IField(name="${func.convertUnderLine(col.columnName)}",column="${col.columnName}")
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	protected ${col.colType} ${func.convertUnderLine(col.columnName)}; 		/*${col.comment}*/
=======
	<#if col.colType == "java.util.Date">
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	</#if>
	protected ${col.colType}  ${func.convertUnderLine(col.columnName)}; 		/*${col.comment}*/
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		<#list model.columnList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		.append("${colName}", this.${colName}) 
		</#list>
		.toString();
	}
}
=======
	<#if col.colType == "java.util.Date">
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	</#if>
	protected ${col.colType}  ${func.convertUnderLine(col.columnName)}; 		/*${col.comment}*/
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		<#list model.columnList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		.append("${colName}", this.${colName}) 
		</#list>
		.toString();
	}
}
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
=======
	<#if col.colType == "java.util.Date">
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	</#if>
	protected ${col.colType}  ${func.convertUnderLine(col.columnName)}; 		/*${col.comment}*/
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		<#list model.columnList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		.append("${colName}", this.${colName}) 
		</#list>
		.toString();
	}
}
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
=======
	<#if col.colType == "java.util.Date">
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	</#if>
	protected ${col.colType}  ${func.convertUnderLine(col.columnName)}; 		/*${col.comment}*/
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
	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() 
	{
		return new ToStringBuilder(this)
		<#list model.columnList as col>
		<#assign colName=func.convertUnderLine(col.columnName)>
		.append("${colName}", this.${colName}) 
		</#list>
		.toString();
	}
}
>>>>>>> branch 'master' of https://github.com/fuhaodashu/pai.git
