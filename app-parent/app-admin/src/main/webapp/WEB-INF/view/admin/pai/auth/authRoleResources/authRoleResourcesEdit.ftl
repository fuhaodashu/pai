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
		bindFormValidation("authRoleResourcesEditForm","${CtxPath}/admin/pai/auth/authRoleResources/save.do");								
		$("#authRoleResourcesEditForm").ligerForm();           
	});
	var __ctxPath = "${CtxPath}";	   
    </script>
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/auth/authRoleResources.js" ></script>
</head>

<body style="padding:10px">	
    <form id="authRoleResourcesEditForm" name="authRoleResourcesEditForm" method="post">
    <#if authRoleResourcesPo.id?exists>
    	<input type="hidden" name="pk" value="${authRoleResourcesPo.id}" ltype="text"/>
    </#if>    	
    <#if authRoleResourcesPo.createTime?exists>
    	<input type="hidden" name="createTime" value="${authRoleResourcesPo.createTime?string('yyyy-MM-dd HH:mm:ss')}" ltype="text"/>
    </#if>
    <#if authRoleResourcesPo.createBy?exists>
    	<input type="hidden" name="createBy" value="${authRoleResourcesPo.createBy}" ltype="text"/>
    </#if>
    	<div>&nbsp;</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
		        <tr>
		            <td align="right" class="l-table-edit-td">角色Id:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="roleId" type="text" id="roleId"  value="${authRoleResourcesPo.roleId}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">资源Id:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="resourceId" type="text" id="resourceId"  value="${authRoleResourcesPo.resourceId}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">状态（1=有效；2=无效）:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="status" type="text" id="status"  value="${authRoleResourcesPo.status}" ltype="text" validate='{digits:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
        </table>
 		<br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>   
</body>
</html>
