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
		bindFormValidation("memberUserEditForm","${CtxPath}/admin/pai/member/memberUser/save.do");								
		$("#memberUserEditForm").ligerForm();           
	});
	var __ctxPath = "${CtxPath}";	   
    </script>
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/member/memberUser.js" ></script>
</head>

<body style="padding:10px">	
    <form id="memberUserEditForm" name="memberUserEditForm" method="post">
    <#if memberUserPo.id?exists>
    	<input type="hidden" name="pk" value="${memberUserPo.id}" ltype="text"/>
    </#if>    	
    <#if memberUserPo.createTime?exists>
    	<input type="hidden" name="createTime" value="${memberUserPo.createTime?string('yyyy-MM-dd HH:mm:ss')}" ltype="text"/>
    </#if>
    <#if memberUserPo.createBy?exists>
    	<input type="hidden" name="createBy" value="${memberUserPo.createBy}" ltype="text"/>
    </#if>
    	<div>&nbsp;</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
		        <tr>
		            <td align="right" class="l-table-edit-td">昵称:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="name" type="text" id="name"  value="${memberUserPo.name}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">手机号:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="phone" type="text" id="phone"  value="${memberUserPo.phone}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">密码:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="password" type="text" id="password"  value="${memberUserPo.password}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">邮箱:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="mail" type="text" id="mail"  value="${memberUserPo.mail}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">头像:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="profile" type="text" id="profile"  value="${memberUserPo.profile}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">行业:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="vocation" type="text" id="vocation"  value="${memberUserPo.vocation}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">学校:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="school" type="text" id="school"  value="${memberUserPo.school}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">专业:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="profession" type="text" id="profession"  value="${memberUserPo.profession}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">状态（1=正常；2=冻结）:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="status" type="text" id="status"  value="${memberUserPo.status}" ltype="text" validate='{required:true,digits:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">性别（男，女）:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="gender" type="text" id="gender"  value="${memberUserPo.gender}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">个人简介:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="description" type="text" id="description"  value="${memberUserPo.description}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
        </table>
 		<br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>   
</body>
</html>
