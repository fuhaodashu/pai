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
		bindFormValidation("authUserEditForm","${CtxPath}/admin/pai/auth/authUser/save.do");
		$("#chk1").change(function () {
		    if(this.checked){
		        $("#reset").show();
		    }else{
		        $("#reset").hide();
		        $("#password").val(null);
		    } 
		 });	
		var options=new Object();
		options.value="${authUserPo.roleIds}";
		options.text="${authUserPo.roleNames}";			
		bindSelectTree("roleIdSelect","roleId",options);
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
    <#if authUserPo.createTime?exists>
    	<input type="hidden" name="createTime" value="${authUserPo.createTime?string('yyyy-MM-dd HH:mm:ss')}" ltype="text"/>
    </#if>
    <#if authUserPo.createBy?exists>
    	<input type="hidden" name="createBy" value="${authUserPo.createBy}" ltype="text"/>
    </#if>
    	<div>&nbsp;</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
		        <tr>
		            <td align="right" class="l-table-edit-td">用户名:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="name" type="text" id="name"  value="${authUserPo.name}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		        	<td align="right" class="l-table-edit-td">密码:</td>
		        	<#if authUserPo.id?exists>
			                <td align="left"  colspan="4"><div style="float:left;margin-left:10px;"><input type="checkbox" name="chbox" id="chk1" style="float:left;"/> <span>重置</span></div>
			                     <div id="reset" style="margin-left:10px;float:left;display:none;">
					                       <div style="float:left;" ><span>新密码：</span></div><div style="float:right;margin-left:10px;"><input name="password" type="password" id="password"/></div>
				                </div>
			                </td>
		        		<#else>
				            <td align="left" class="l-table-edit-td">
				            	<input name="password" type="password" id="password" ltype="text" validate='{required:true}'/>
				            </td>
		        	</#if>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">手机号:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="phone" type="text" id="phone"  value="${authUserPo.phone}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr>
		        <tr>
		            <td align="right" class="l-table-edit-td">状态:</td>
		            <td align="left" class="l-table-edit-td">
	                	<input id="status_1" type="radio" name="status" value="1" <#if authUserPo.status != 2>checked="checked"</#if>/><label for="status_1">正常</label> 
	                	&nbsp;&nbsp;&nbsp;&nbsp;
	                	<input id="status_2" type="radio" name="status" value="2" <#if authUserPo.status == 2>checked="checked"</#if>/><label for="status_2">冻结</label>
	                </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">所属角色:</td>
		            <td align="left" class="l-table-edit-td">
	                	<input name="roleNames" type="text" id="roleIdSelect" ltype="text" validate='{required:true}'/>
			            <input name="roleIds" type="hidden" id="roleId"  value="${authUserPo.roleIds}" ltype="text" />
	                </td>
		            <td align="left"></td>
		        </tr>
        </table>
 		<br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>   
</body>
</html>
