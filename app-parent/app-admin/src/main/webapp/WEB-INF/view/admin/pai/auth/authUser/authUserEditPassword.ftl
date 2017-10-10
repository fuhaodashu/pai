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
		bindFormValidation("authUserEditForm","${CtxPath}/admin/pai/auth/authUser/savePassword.do");								
		$("#authUserEditForm").ligerForm();           
	});
	var __ctxPath = "${CtxPath}";	   
    </script>
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/auth/authUser.js" ></script>
</head>

<body style="padding:10px">	
    <form id="authUserEditForm" name="authUserEditForm" method="post">
    <#if authUserPo.id?exists>
    	<input type="hidden" name="pk" value="${authUserPo.id}" ltype="text"/>
    </#if>    	
    	<div>&nbsp;</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
		        <tr>
		            <td align="right" class="l-table-edit-td">用户名:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="name" type="text" id="name"  value="${authUserPo.name}" ltype="text" validate='{required:true}' readonly="true"/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">密码:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="password" type="password" id="password" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">确认密码:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="passwordConfirm" type="password" id="passwordConfirm" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
        </table>
 		<br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>   
</body>
</html>
