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
		bindFormValidation("jobTaskLogEditForm","${CtxPath}/admin/pai/common/jobTaskLog/save.do");								
		$("#jobTaskLogEditForm").ligerForm();           
	});
	var __ctxPath = "${CtxPath}";	   
    </script>
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/common/jobTaskLog.js" ></script>
</head>

<body style="padding:10px">	
    <form id="jobTaskLogEditForm" name="jobTaskLogEditForm" method="post">
    <#if jobTaskLogPo.id?exists>
    	<input type="hidden" name="pk" value="${jobTaskLogPo.id}" ltype="text"/>
    </#if>    	
    <#if jobTaskLogPo.createTime?exists>
    	<input type="hidden" name="createTime" value="${jobTaskLogPo.createTime?string('yyyy-MM-dd HH:mm:ss')}" ltype="text"/>
    </#if>
    <#if jobTaskLogPo.createBy?exists>
    	<input type="hidden" name="createBy" value="${jobTaskLogPo.createBy}" ltype="text"/>
    </#if>
    	<div>&nbsp;</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
		        <tr>
		            <td align="right" class="l-table-edit-td">作业任务键bean_id:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="jobKey" type="text" id="jobKey"  value="${jobTaskLogPo.jobKey}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">状态（1=成功；2=失败）:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="status" type="text" id="status"  value="${jobTaskLogPo.status}" ltype="text" validate='{required:true,digits:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">日志:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="jobLog" type="text" id="jobLog"  value="${jobTaskLogPo.jobLog}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
        </table>
 		<br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>   
</body>
</html>
