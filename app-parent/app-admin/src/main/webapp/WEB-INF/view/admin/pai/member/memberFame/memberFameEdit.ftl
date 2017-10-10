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
		bindFormValidation("memberFameEditForm","${CtxPath}/admin/pai/member/memberFame/save.do");								
		$("#memberFameEditForm").ligerForm();           
	});
	var __ctxPath = "${CtxPath}";	   
    </script>
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/member/memberFame.js" ></script>
</head>

<body style="padding:10px">	
    <form id="memberFameEditForm" name="memberFameEditForm" method="post">
    <#if memberFamePo.id?exists>
    	<input type="hidden" name="pk" value="${memberFamePo.id}" ltype="text"/>
    </#if>    	
    <#if memberFamePo.createTime?exists>
    	<input type="hidden" name="createTime" value="${memberFamePo.createTime?string('yyyy-MM-dd HH:mm:ss')}" ltype="text"/>
    </#if>
    <#if memberFamePo.createBy?exists>
    	<input type="hidden" name="createBy" value="${memberFamePo.createBy}" ltype="text"/>
    </#if>
    	<div>&nbsp;</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
		        <tr>
		            <td align="right" class="l-table-edit-td">名字:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="name" type="text" id="name"  value="${memberFamePo.name}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">帮派:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="organization" type="text" id="organization"  value="${memberFamePo.organization}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">职业:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="vocation" type="text" id="vocation"  value="${memberFamePo.vocation}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">性别:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="gender" type="text" id="gender"  value="${memberFamePo.gender}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">传记:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="story" type="text" id="story"  value="${memberFamePo.story}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">故事来源:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="source" type="text" id="source"  value="${memberFamePo.source}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">主要成就，相当于xx县委书记:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="achievement" type="text" id="achievement"  value="${memberFamePo.achievement}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">学历，相当于xx大学毕业 :</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="education" type="text" id="education"  value="${memberFamePo.education}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">出身，官二代，贫农，富二代:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="background" type="text" id="background"  value="${memberFamePo.background}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
        </table>
 		<br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>   
</body>
</html>
