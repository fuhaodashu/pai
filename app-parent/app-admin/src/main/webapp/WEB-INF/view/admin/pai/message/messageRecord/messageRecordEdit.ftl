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
		bindFormValidation("messageRecordEditForm","${CtxPath}/admin/pai/message/messageRecord/save.do");								
		$("#messageRecordEditForm").ligerForm();           
	});
	var __ctxPath = "${CtxPath}";	   
    </script>
	<script type="text/javascript" src="${CtxPath}/scripts/admin/pai/message/messageRecord.js" ></script>
</head>

<body style="padding:10px">	
    <form id="messageRecordEditForm" name="messageRecordEditForm" method="post">
    <#if messageRecordPo.id?exists>
    	<input type="hidden" name="pk" value="${messageRecordPo.id}" ltype="text"/>
    </#if>    	
    <#if messageRecordPo.createTime?exists>
    	<input type="hidden" name="createTime" value="${messageRecordPo.createTime?string('yyyy-MM-dd HH:mm:ss')}" ltype="text"/>
    </#if>
    <#if messageRecordPo.createBy?exists>
    	<input type="hidden" name="createBy" value="${messageRecordPo.createBy}" ltype="text"/>
    </#if>
    	<div>&nbsp;</div>
        <table cellpadding="0" cellspacing="0" class="l-table-edit">
		        <tr>
		            <td align="right" class="l-table-edit-td">状态（waiting_confirm=等待确认，sending=发送中）:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="status" type="text" id="status"  value="${messageRecordPo.status}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">回调函数，每个服务一个，确保事务的一致:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="url" type="text" id="url"  value="${messageRecordPo.url}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">消息内容:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="messageBody" type="text" id="messageBody"  value="${messageRecordPo.messageBody}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">消息重发次数:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="messageSendTimes" type="text" id="messageSendTimes"  value="${messageRecordPo.messageSendTimes}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">是否死亡:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="areadlyDead" type="text" id="areadlyDead"  value="${messageRecordPo.areadlyDead}" ltype="text" validate='{required:true}'/>
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">监听处理类handler前缀:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="msgType" type="text" id="msgType"  value="${messageRecordPo.msgType}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
		        <tr>
		            <td align="right" class="l-table-edit-td">备注:</td>
		            <td align="left" class="l-table-edit-td">
		            	<input name="remark" type="text" id="remark"  value="${messageRecordPo.remark}" ltype="text" />
		            </td>
		            <td align="left"></td>
		        </tr> 
        </table>
 		<br />
		<input type="submit" value="提交" id="Button1" class="l-button l-button-submit" /> 
    </form>   
</body>
</html>
