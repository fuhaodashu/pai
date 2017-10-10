<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><title></title>
	<#include "/common/jquery.ftl">
	<#include "/common/ligerUI.ftl">		 			
	<link href="${CtxPath}/scripts/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
	<link href="${CtxPath}/styles/admin/pai/form.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/common.js" ></script>		    	            	    	
    <script type="text/javascript">  
	$(function(){		
		bindFormValidation("jobTaskParamEditForm","${CtxPath}/admin/pai/common/jobTaskParam/save.do");
		var defaultVal = "${jobTaskParamPo.valueType}";
		if("${jobTaskParamPo.id}" == "")
			defaultVal = "String";
		valueTypeSelect("valueTypeSelect",defaultVal,"valueType");	
		$("#jobTaskParamEditForm").ligerForm();           
	});
	var __ctxPath = "${CtxPath}";	   
    </script>
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/common/jobTaskParam.js" ></script>
</head>

<body style="padding:10px">	
    <form id="jobTaskParamEditForm" name="jobTaskParamEditForm" method="post">
    <#if jobTaskParamPo.id?exists>
    	<input type="hidden" name="pk" value="${jobTaskParamPo.id}" ltype="text"/>
    </#if>    	
    <#if jobTaskParamPo.jobId?exists>
    	<input type="hidden" name="jobId" value="${jobTaskParamPo.jobId}" ltype="text"/>
    </#if>    	
    <#if jobTaskParamPo.createTime?exists>
    	<input type="hidden" name="createTime" value="${jobTaskParamPo.createTime?string('yyyy-MM-dd HH:mm:ss')}" ltype="text"/>
    </#if>
    <#if jobTaskParamPo.createBy?exists>
    	<input type="hidden" name="createBy" value="${jobTaskParamPo.createBy}" ltype="text"/>
    </#if>
    	<div>&nbsp;</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
		        <tr>
		            <td align="right" class="l-table-edit-td">参数键:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="paramKey" type="text" id="paramKey"  value="${jobTaskParamPo.paramKey}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">参数值:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="paramValue" type="text" id="paramValue"  value="${jobTaskParamPo.paramValue}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">参数值类型:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input type="text" id="valueTypeSelect" ltype="text"  value="${jobTaskParamPo.valueType}"/>
                		<input name="valueType" type="hidden" id="valueType"  value="${jobTaskParamPo.valueType}" ltype="text"/>
		            </td>
		            <td align="left"></td>
		        </tr> 
        </table>
 		<br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>   
</body>
</html>
