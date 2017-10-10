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
		//树
        $("#tree").ligerTree({
        	enabledCompleteCheckbox:false, 
            url : '${CtxPath}/admin/pai/auth/authResources/findResourcesWithByRoleId.do?roleId=${authRolePo.id}'
        });
        manager = $("#tree").ligerGetTreeManager();
        $("#Button1").click(function(){
			var nodes = manager.getCheckedData();
			var resourcesId = "";
			for (var i = 0; i < nodes.length; i++)
            {	
				var id = nodes[i].tabid;
				if(id != "" && id != null && id.indexOf("TabId") > 0)
					resourcesId += id.substring(0,id.indexOf("TabId"))+";";
            }
			$("#resourcesId").val(resourcesId);
		});
		
        bindFormValidation("authRoleEditForm","${CtxPath}/admin/pai/auth/authRole/save.do");
		$("#authRoleEditForm").ligerForm(); 
	});
	var __ctxPath = "${CtxPath}";	   
    </script>
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/auth/authRole.js" ></script>
</head>

<body style="padding:10px">	
    <form id="authRoleEditForm" name="authRoleEditForm" method="post">
    <#if authRolePo.id?exists>
    	<input type="hidden" name="pk" value="${authRolePo.id}" ltype="text"/>
    </#if>    	
    <#if authRolePo.createTime?exists>
    	<input type="hidden" name="createTime" value="${authRolePo.createTime?string('yyyy-MM-dd HH:mm:ss')}" ltype="text"/>
    </#if>
    <#if authRolePo.createBy?exists>
    	<input type="hidden" name="createBy" value="${authRolePo.createBy}" ltype="text"/>
    </#if>
    	<div>&nbsp;</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
		        <tr>
		            <td align="right" class="l-table-edit-td">名称:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="name" type="text" id="name"  value="${authRolePo.name}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">描述:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="descript" type="text" id="descript"  value="${authRolePo.descript}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">状态:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input id="status_1" type="radio" name="status" value="1" <#if authRolePo.status != 2>checked="checked"</#if>/><label for="status_1">有效</label> 
	                	&nbsp;&nbsp;&nbsp;&nbsp;
	                	<input id="status_2" type="radio" name="status" value="2" <#if authRolePo.status == 2>checked="checked"</#if>/><label for="status_2">无效</label>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		        	<td align="right" class="l-table-edit-td">分配资源:</td>
		        	<td align="left">
				    	<div style="width:200px; height:300px; border:1px solid #ccc; overflow:auto; clear:both;">
					    	<ul id="tree"></ul>
					    </div>
					    <input id="resourcesId" name="resourcesId" type="hidden"/>
		            </td>
		        </tr>
        </table>
 		<br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>   
</body>
</html>
