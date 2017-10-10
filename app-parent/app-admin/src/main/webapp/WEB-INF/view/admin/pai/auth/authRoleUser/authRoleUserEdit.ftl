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
		bindFormValidation("authRoleUserEditForm","${CtxPath}/admin/pai/auth/authRoleUser/save.do");								
		$("#authRoleUserEditForm").ligerForm();           
	});
	var __ctxPath = "${CtxPath}";	   
    </script>
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/auth/authRoleUser.js" ></script>
</head>

<body style="padding:10px">	
    <form id="authRoleUserEditForm" name="authRoleUserEditForm" method="post">
    <#if authRoleUserPo.id?exists>
    	<input type="hidden" name="pk" value="${authRoleUserPo.id}" ltype="text"/>
    </#if>    	
    <#if authRoleUserPo.createTime?exists>
    	<input type="hidden" name="createTime" value="${authRoleUserPo.createTime?string('yyyy-MM-dd HH:mm:ss')}" ltype="text"/>
    </#if>
    <#if authRoleUserPo.createBy?exists>
    	<input type="hidden" name="createBy" value="${authRoleUserPo.createBy}" ltype="text"/>
    </#if>
    	<div>&nbsp;</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
		        <tr>
		            <td align="right" class="l-table-edit-td">角色Id:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="roleId" type="text" id="roleId"  value="${authRoleUserPo.roleId}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">用户Id:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="userId" type="text" id="userId"  value="${authRoleUserPo.userId}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
        </table>
 		<br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>   
</body>
</html>
